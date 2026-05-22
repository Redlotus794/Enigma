package io.github.redlotus794.enigma.ddd.spring.repository;

import io.github.redlotus794.enigma.ddd.core.DomainAggregate;
import io.github.redlotus794.enigma.ddd.core.DomainEntity;
import io.github.redlotus794.enigma.ddd.core.DomainRepository;
import io.github.redlotus794.enigma.ddd.spring.event.autolog.RepositoryEventAutoLog;
import io.github.redlotus794.enigma.ddd.spring.exception.EnigmaRemoveNullObjectException;
import io.github.redlotus794.enigma.ddd.spring.exception.EnigmaSaveNullObjectException;

import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

/**
 * <h1>InMemoryDomainRepository</h1>
 * 内存领域资源库，自动记录资源库事件。
 *
 * @see RepositoryEventAutoLog
 * @see io.github.redlotus794.enigma.ddd.spring.event.autolog.RepositoryEventAutoLogAspect
 * @see io.github.redlotus794.enigma.ddd.core.event.DomainEventRepository
 * @author wangjialong
 * @since 2025/12/17 13:51
 */
@RepositoryEventAutoLog
public abstract class InMemoryDomainRepository<DAR extends DomainEntity<IdentityType>,
        DA extends DomainAggregate<DAR>, IdentityType>
        implements DomainRepository<DA, DAR, IdentityType> {

    /**
     * In memory store.
     */
    protected final Map<IdentityType, DA> store = new ConcurrentHashMap<>();

    @Override
    public Optional<DA> find(IdentityType identity) {
        if (identity == null) {
            return Optional.empty();
        }
        return Optional.ofNullable(store.get(identity));
    }

    @Override
    public Collection<DA> findAll() {
        return Collections.unmodifiableCollection(store.values());
    }

    @Override
    public void save(DA aggregate) {
        if (aggregate == null) {
            throw new EnigmaSaveNullObjectException();
        }
        store.put(aggregate.root().identity(), aggregate);
    }

    @Override
    public void saveAll(Collection<DA> aggregateCollection) {
        aggregateCollection.forEach(this::save);
    }

    @Override
    public void remove(DA aggregate) {
        if (aggregate == null) {
            throw new EnigmaRemoveNullObjectException();
        }
        store.remove(aggregate.root().identity());
    }

    @Override
    public void removeAll(Collection<DA> aggregateCollection) {
        aggregateCollection.forEach(this::remove);
    }
}
