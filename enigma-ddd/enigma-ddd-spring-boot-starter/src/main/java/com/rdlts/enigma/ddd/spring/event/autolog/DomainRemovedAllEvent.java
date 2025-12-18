package com.rdlts.enigma.ddd.spring.event.autolog;

import com.rdlts.enigma.ddd.core.event.DomainEvent;

import javax.annotation.Nonnull;

/**
 * DomainRemovedAllEvent
 *
 * @author wangjialong
 * @since 2025/12/18 09:02
 */
public class DomainRemovedAllEvent extends DomainEvent<DomainRemovedAllEventParam> {

    public DomainRemovedAllEvent(@Nonnull DomainRemovedAllEventParam eventContent) {
        super(eventContent);
    }
}
