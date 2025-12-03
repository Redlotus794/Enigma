package com.rdlts.enigma.ddd.core;

/**
 * 领域事件持久化接口
 * 用于标识领域事件是否需要持久化
 * 当领域事件不需要持久化时，可以实现该接口，重写isPersistable方法，返回false
 *
 * @see DomainEventRepository
 * @author wangjialong
 * @since 2025/12/3 09:42
 */
public interface DomainEventPersistable {

    /**
     * 是否需要持久化
     * 基于当前的设计，默认为true
     *
     * @return boolean
     */
    default boolean isPersistable() {
        return true;
    }
}
