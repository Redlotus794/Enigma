package io.github.redlotus794.enigma.ddd.spring.event.autolog;

import io.github.redlotus794.enigma.ddd.core.Entity;
import io.github.redlotus794.enigma.ddd.core.event.DomainEvent;

import org.jspecify.annotations.NonNull;

/**
 * DomainSavedEvent
 *
 * @see io.github.redlotus794.enigma.ddd.core.DomainRepository#save(Entity)
 * @author wangjialong
 * @since 2025/12/17 10:55
 */
public class DomainSavedEvent extends DomainEvent<DomainSavedEventParam> {

    public DomainSavedEvent(@NonNull DomainSavedEventParam eventContent) {
        super(eventContent);
    }
}
