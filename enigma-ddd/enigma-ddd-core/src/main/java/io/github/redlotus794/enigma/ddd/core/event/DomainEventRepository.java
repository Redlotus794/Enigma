package io.github.redlotus794.enigma.ddd.core.event;

import io.github.redlotus794.enigma.ddd.core.DomainRepository;

import java.util.Collection;
import java.util.Optional;

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

    /**
     * 根据领域事件UUID查找领域事件对象
     * @param domainEventUuid DomainEventUUID
     * @return Optional DomainEvent
     */
    Optional<DomainEvent<?>> find(DomainEventUUID domainEventUuid);

    /**
     * 查找所有领域事件对象
     * @return Optional DomainEvent
     */
    Collection<DomainEvent<?>> findAll();
}
