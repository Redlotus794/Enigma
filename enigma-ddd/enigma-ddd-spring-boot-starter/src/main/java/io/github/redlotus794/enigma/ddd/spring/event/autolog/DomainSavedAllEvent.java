package io.github.redlotus794.enigma.ddd.spring.event.autolog;

import io.github.redlotus794.enigma.ddd.core.event.DomainEvent;

import org.jspecify.annotations.NonNull;
import java.util.Collection;

/**
 * DomainSavedAllEvent
 *
 * @see io.github.redlotus794.enigma.ddd.core.DomainRepository#saveAll(Collection)
 * @author wangjialong
 * @since 2025/12/17 15:58
 */
public class DomainSavedAllEvent extends DomainEvent<DomainSavedAllEventParam> {

    public DomainSavedAllEvent(@NonNull DomainSavedAllEventParam eventContent) {
        super(eventContent);
    }
}
