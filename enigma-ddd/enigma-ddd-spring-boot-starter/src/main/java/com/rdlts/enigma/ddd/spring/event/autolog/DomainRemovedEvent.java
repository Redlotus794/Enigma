package com.rdlts.enigma.ddd.spring.event.autolog;

import com.rdlts.enigma.ddd.core.event.DomainEvent;

import javax.annotation.Nonnull;

/**
 * DomainRemovedEvent
 *
 * @author wangjialong
 * @since 2025/12/18 08:25
 */
public class DomainRemovedEvent extends DomainEvent<DomainRemovedEventParam> {

    public DomainRemovedEvent(@Nonnull DomainRemovedEventParam eventContent) {
        super(eventContent);
    }
}
