package com.rdlts.enigma.ddd.core;

/**
 * DomainEventReproducible
 *
 * @author wangjialong
 * @since 2025/12/3 09:41
 */
public interface DomainEventReproducible {

    /**
     * 事件源重放
     */
    default void replay() {
        throw new UnsupportedOperationException("Domain event replay is not supported.");
    }
}
