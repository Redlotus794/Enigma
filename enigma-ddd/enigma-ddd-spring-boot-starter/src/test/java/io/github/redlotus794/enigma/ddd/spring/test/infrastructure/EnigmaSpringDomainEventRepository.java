package io.github.redlotus794.enigma.ddd.spring.test.infrastructure;

import io.github.redlotus794.enigma.ddd.core.event.DomainEvent;
import io.github.redlotus794.enigma.ddd.core.event.DomainEventParam;
import io.github.redlotus794.enigma.ddd.core.event.DomainEventRepository;
import io.github.redlotus794.enigma.ddd.core.event.DomainEventUUID;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

/**
 * EnigmaSpringDomainEventRepository
 * 测试使用的领域事件资源库
 *
 * @author wangjialong
 * @since 2025/12/17 13:39
 */
@Component
@Log4j2
public class EnigmaSpringDomainEventRepository implements DomainEventRepository {

    protected Map<DomainEventUUID, DomainEvent<?>> store = new ConcurrentHashMap<>();

    @Override
    public void save(DomainEvent<?> domainEvent) {
        log.info("Enigma Spring Domain Event Repository save: {}", domainEvent);
        store.put(domainEvent.getEventUuid(), domainEvent);
    }

    @Override
    public Optional<DomainEvent<?>> find(DomainEventUUID domainEventUuid) {
        return Optional.ofNullable(store.get(domainEventUuid));
    }

    @Override
    public Collection<DomainEvent<?>> findAll() {
        return store.values();
    }

    public <T extends DomainEventParam> Collection<DomainEvent<T>> findBy(Class<T> eventType) {
        return store.values().stream()
                .filter(t -> t.getEventContent().getClass().equals(eventType))
                .map(t -> (DomainEvent<T>) t)
                .collect(Collectors.toList());
    }

    public synchronized void clearAll() {
        store.clear();
    }
}
