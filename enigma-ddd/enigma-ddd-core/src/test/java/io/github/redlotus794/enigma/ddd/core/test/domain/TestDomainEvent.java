package io.github.redlotus794.enigma.ddd.core.test.domain;

import io.github.redlotus794.enigma.ddd.core.event.DomainEvent;

import org.jspecify.annotations.NonNull;
import java.time.Instant;

/**
 * TestDomainEvent
 *
 * @author wangjialong
 * @since 2025/12/2 16:09
 */
public class TestDomainEvent extends DomainEvent<TestDomainEntity> {

    public TestDomainEvent() {
        super();
    }

    public TestDomainEvent(@NonNull TestDomainEntity eventContent) {
        super(eventContent);
    }

    public TestDomainEvent(TestDomainEntity eventContent, Instant eventTime, String createdBy) {
        super(eventContent, eventTime, createdBy);
    }
}
