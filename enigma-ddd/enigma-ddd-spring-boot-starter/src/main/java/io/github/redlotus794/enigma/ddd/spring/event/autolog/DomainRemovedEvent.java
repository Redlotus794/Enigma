package io.github.redlotus794.enigma.ddd.spring.event.autolog;

import io.github.redlotus794.enigma.ddd.core.event.DomainEvent;

import org.jspecify.annotations.NonNull;

/**
 * DomainRemovedEvent
 *
 * @author wangjialong
 * @since 2025/12/18 08:25
 */
public class DomainRemovedEvent extends DomainEvent<DomainRemovedEventParam> {

    public DomainRemovedEvent(@NonNull DomainRemovedEventParam eventContent) {
        super(eventContent);
    }
}
