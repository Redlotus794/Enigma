package com.rdlts.enigma.ddd.core.test.domain;

import com.rdlts.enigma.ddd.core.event.DomainEvent;

import javax.annotation.Nonnull;
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

    public TestDomainEvent(@Nonnull TestDomainEntity eventContent) {
        super(eventContent);
    }

    public TestDomainEvent(TestDomainEntity eventContent, Instant eventTime, String createdBy) {
        super(eventContent, eventTime, createdBy);
    }
}
