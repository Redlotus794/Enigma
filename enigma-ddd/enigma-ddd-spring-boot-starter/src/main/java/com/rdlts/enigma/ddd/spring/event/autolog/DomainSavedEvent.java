package com.rdlts.enigma.ddd.spring.event.autolog;

import com.rdlts.enigma.ddd.core.DomainEntity;
import com.rdlts.enigma.ddd.core.event.DomainEvent;

import javax.annotation.Nonnull;

/**
 * DomainSavedEvent
 *
 * @see com.rdlts.enigma.ddd.core.DomainRepository#save(DomainEntity)
 * @author wangjialong
 * @since 2025/12/17 10:55
 */
public class DomainSavedEvent extends DomainEvent<DomainSavedEventParam> {

    public DomainSavedEvent(@Nonnull DomainSavedEventParam eventContent) {
        super(eventContent);
    }
}
