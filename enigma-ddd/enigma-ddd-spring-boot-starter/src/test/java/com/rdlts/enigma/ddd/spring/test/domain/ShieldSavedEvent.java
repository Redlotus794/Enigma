package com.rdlts.enigma.ddd.spring.test.domain;

import com.rdlts.enigma.ddd.core.DomainEvent;

import javax.annotation.Nonnull;

/**
 * ShieldSavedEvent
 *
 * @author wangjialong
 * @since 2025/12/3 13:56
 */
public class ShieldSavedEvent extends DomainEvent<Shield> {

    public ShieldSavedEvent() {
    }

    public ShieldSavedEvent(@Nonnull Shield eventContent) {
        super(eventContent);
    }
}
