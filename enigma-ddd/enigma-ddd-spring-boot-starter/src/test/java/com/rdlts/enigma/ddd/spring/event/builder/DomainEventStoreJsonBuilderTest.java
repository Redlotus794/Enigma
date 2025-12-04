package com.rdlts.enigma.ddd.spring.event.builder;

import com.alibaba.fastjson2.JSONObject;
import com.rdlts.enigma.ddd.core.DomainEvent;
import com.rdlts.enigma.ddd.core.DomainEventParam;
import com.rdlts.enigma.ddd.spring.EnigmaSpringBootBasedTest;
import com.rdlts.enigma.ddd.spring.utils.EnigmaSpringContextUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.BeanInitializationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

class DomainEventStoreJsonBuilderTest extends EnigmaSpringBootBasedTest {

    @Autowired
    DomainEventStoreJsonBuilderContext domainEventStoreJsonBuilderContext;

    @Autowired
    ApplicationContext applicationContext;

    @Test
    void testMultipleSupportEvent() {
        final DomainEventStoreJsonBuilder a = domainEventStoreJsonBuilderContext.withBuilder("a");
        final DomainEventStoreJsonBuilder b = domainEventStoreJsonBuilderContext.withBuilder("b");
        Assertions.assertTrue(a instanceof MultipleSupportEvent);
        Assertions.assertTrue(b instanceof MultipleSupportEvent);
    }

    @Test
    void testDuplicateSupportEvent() {
        try {
            EnigmaSpringContextUtils.instance().loadBean("duplicateSupportEvent", DuplicateSupportEvent.class);
            Assertions.assertThrows(BeanInitializationException.class,
                    () -> DomainEventStoreJsonBuilderContext.instance().setApplicationContext(applicationContext));
        } finally {
            EnigmaSpringContextUtils.instance().unloadBean("duplicateSupportEvent");
        }

    }

    @Component
    static class MultipleSupportEvent implements DomainEventStoreJsonBuilder {

        @Override
        public List<String> supportEventName() {
            return Arrays.asList("a", "b");
        }

        @Override
        public <T extends DomainEventParam> DomainEvent<T> buildEvent(JSONObject jsonObject) throws ClassNotFoundException {
            return null;
        }
    }

    static class DuplicateSupportEvent implements DomainEventStoreJsonBuilder {

        @Override
        public List<String> supportEventName() {
            return Arrays.asList("a", "a");
        }

        @Override
        public <T extends DomainEventParam> DomainEvent<T> buildEvent(JSONObject jsonObject) throws ClassNotFoundException {
            return null;
        }
    }

}