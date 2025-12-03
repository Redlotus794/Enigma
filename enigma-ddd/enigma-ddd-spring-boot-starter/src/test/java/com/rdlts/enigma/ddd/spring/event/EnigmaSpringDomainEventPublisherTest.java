package com.rdlts.enigma.ddd.spring.event;

import com.rdlts.enigma.ddd.spring.EnigmaSpringBootBasedTest;
import com.rdlts.enigma.ddd.spring.test.domain.Shield;
import com.rdlts.enigma.ddd.spring.test.domain.ShieldId;
import com.rdlts.enigma.ddd.spring.test.domain.ShieldSavedEvent;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import static org.junit.jupiter.api.Assertions.*;

@Component
@Log4j2
class EnigmaSpringDomainEventPublisherTest extends EnigmaSpringBootBasedTest {

    protected static boolean isEventReceived = false;

    @Autowired
    private EnigmaSpringDomainEventPublisher publisher;

    @Test
    void testGetInstance() {
        // 测试获取实例
        assertNotNull(publisher.getInstance());
        assertEquals(publisher, publisher.getInstance());
        assertNotNull(new ShieldSavedEvent());
    }

    @Test
    void testSetActiveAndGetActive() {
        // 测试设置和获取激活状态
        assertTrue(publisher.getActive(), "默认应该是激活状态");

        publisher.setActive(false);
        assertFalse(publisher.getActive(), "设置为非激活状态后应返回false");

        publisher.setActive(true);
        assertTrue(publisher.getActive(), "重新设置为激活状态后应返回true");

        publisher.setActive(null);
        assertTrue(publisher.getActive(), "设置为null后应返回默认值true");
    }

    @Test
    void testPublishEventWhenActive() {
        // 测试在激活状态下发布事件
        publisher.setActive(true);
        isEventReceived = false;
        Shield entity = Shield.builder().shieldId(ShieldId.of("test-id")).build();
        ShieldSavedEvent shieldSavedEvent = new ShieldSavedEvent(entity);
        publisher.publish(shieldSavedEvent);
        Assertions.assertTrue(isEventReceived);
    }

    @Test
    void testPublishEventWhenNotActive() {
        // 测试在非激活状态下发布事件
        publisher.setActive(false);

        isEventReceived = false;
        Shield entity = Shield.builder().shieldId(ShieldId.of("test-id")).build();
        ShieldSavedEvent shieldSavedEvent = new ShieldSavedEvent(entity);
        publisher.publish(shieldSavedEvent);
        Assertions.assertFalse(isEventReceived);
    }

    @EventListener
    public void ShieldSavedEventListener(ShieldSavedEvent shieldSavedEvent) {
        log.info("接受事件 {}", shieldSavedEvent);
        isEventReceived = true;
    }
}