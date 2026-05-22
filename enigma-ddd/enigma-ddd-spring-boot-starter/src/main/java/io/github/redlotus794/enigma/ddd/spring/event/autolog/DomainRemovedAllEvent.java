package io.github.redlotus794.enigma.ddd.spring.event.autolog;

import io.github.redlotus794.enigma.ddd.core.event.DomainEvent;

import org.jspecify.annotations.NonNull;

/**
 * DomainRemovedAllEvent
 *
 * @author wangjialong
 * @since 2025/12/18 09:02
 */
public class DomainRemovedAllEvent extends DomainEvent<DomainRemovedAllEventParam> {

    public DomainRemovedAllEvent(@NonNull DomainRemovedAllEventParam eventContent) {
        super(eventContent);
    }
}
