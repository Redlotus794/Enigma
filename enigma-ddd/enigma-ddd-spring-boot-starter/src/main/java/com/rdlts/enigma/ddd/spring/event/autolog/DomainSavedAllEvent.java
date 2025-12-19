package com.rdlts.enigma.ddd.spring.event.autolog;

import com.rdlts.enigma.ddd.core.event.DomainEvent;

import javax.annotation.Nonnull;
import java.util.Collection;

/**
 * DomainSavedAllEvent
 *
 * @see com.rdlts.enigma.ddd.core.DomainRepository#saveAll(Collection)
 * @author wangjialong
 * @since 2025/12/17 15:58
 */
public class DomainSavedAllEvent extends DomainEvent<DomainSavedAllEventParam> {

    public DomainSavedAllEvent(@Nonnull DomainSavedAllEventParam eventContent) {
        super(eventContent);
    }
}
