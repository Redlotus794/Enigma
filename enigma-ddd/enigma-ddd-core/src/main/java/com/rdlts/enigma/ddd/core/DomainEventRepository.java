package com.rdlts.enigma.ddd.core;

/**
 * DomainEventRepository
 * 领域事件资源库，用以持久化领域事件。
 *
 * @see DomainRepository
 * @author jialong.wang
 * @since 2025/12/3 09:28
 */
public interface DomainEventRepository {

    /**
     * 保存领域事件对象
     * @param domainEvent DomainEvent
     */
    void save(DomainEvent<?> domainEvent);
}
