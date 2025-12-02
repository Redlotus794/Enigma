package com.rdlts.enigma.ddd.core.test;

import com.rdlts.enigma.ddd.core.DomainEvent;
import com.rdlts.enigma.ddd.core.test.entity.TestDomainEntity;

import javax.annotation.Nonnull;

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
}
