package com.rdlts.enigma.ddd.core;

import com.rdlts.enigma.ddd.core.test.domain.TestDomainEvent;
import com.rdlts.enigma.ddd.core.test.domain.TestDomainEntity;
import com.rdlts.enigma.ddd.core.test.domain.TestId;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * DomainEventTest
 *
 * @author wangjialong
 * @since 2025/12/2 16:11
 */
public class DomainEventTest {

    @Test
    void testDomainEvent() {
        final TestDomainEvent empty = new TestDomainEvent();
        Assertions.assertNull(empty.eventContent);
        Assertions.assertNull(empty.eventUuid);
        Assertions.assertEquals(TestDomainEvent.class.getName(), empty.domainEventName());

        TestDomainEntity testDomainEntity = TestDomainEntity
                .builder()
                .testId(TestId.builder().id("1").build())
                .build();
        TestDomainEvent testDomainEvent = new TestDomainEvent(testDomainEntity);
        Assertions.assertEquals("1", testDomainEvent.eventContent.getTestId().getId());
    }
}
