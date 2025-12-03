package com.rdlts.enigma.ddd.core.test.domain;

import com.rdlts.enigma.ddd.core.DomainEvent;
import com.rdlts.enigma.ddd.core.DomainEventRepository;
import com.rdlts.enigma.ddd.core.DomainEventUUID;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

/**
 * TestDomainEventRepositoryImpl
 *
 * @author wangjialong
 * @since 2025/12/3 10:11
 */
public class TestDomainEventRepositoryImpl implements DomainEventRepository {

    private static final Map<DomainEventUUID, DomainEvent<?>> STORE = new ConcurrentHashMap<>();

    @Override
    public void save(DomainEvent<?> domainEvent) {
        STORE.put(domainEvent.getEventUuid(), domainEvent);
    }

    @Override
    public Optional<DomainEvent<?>> find(DomainEventUUID domainEventUuid) {
        return Optional.ofNullable(STORE.get(domainEventUuid));
    }
}
