package io.github.redlotus794.enigma.ddd.core.event;

import io.github.redlotus794.enigma.ddd.core.test.domain.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.Instant;
import java.util.Optional;

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

        final TestDomainEvent testDomainEvent1 = new TestDomainEvent(testDomainEntity, Instant.now(), "test");
        Assertions.assertEquals("test", testDomainEvent1.createdBy);
        Assertions.assertNotNull(testDomainEvent1.eventUuid);
        Assertions.assertEquals(TestDomainEvent.class.getName(), testDomainEvent1.domainEventName());
        Assertions.assertNotNull(testDomainEvent1.eventContent);
    }

    @Test
    void testPersistable() {
        TestDomainEntity testDomainEntity = TestDomainEntity
                .builder()
                .testId(TestId.builder().id("1").build())
                .build();
        TestDomainEvent testDomainEvent = new TestDomainEvent(testDomainEntity);
        Assertions.assertTrue(testDomainEvent.isPersistable());
        Assertions.assertThrows(UnsupportedOperationException.class, testDomainEvent::replay);

        DomainEventRepository testDomainEntityRepository = new TestDomainEventRepositoryImpl();
        testDomainEntityRepository.save(testDomainEvent);

        final Optional<DomainEvent<?>> domainEvent = testDomainEntityRepository.find(testDomainEvent.eventUuid);
        Assertions.assertTrue(domainEvent.isPresent());
    }

    @Test
    void testRebuild_throwUnsupportedException() {
        final DomainEventReproducer DefaultReproducer = new DefaultReproducer();
        Assertions.assertThrows(UnsupportedOperationException.class, () -> DefaultReproducer.rebuild(null, null));
    }

    static class DefaultReproducer implements DomainEventReproducer {
    }
}
