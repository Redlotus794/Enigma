package io.github.redlotus794.enigma.ddd.spring.repository;

import io.github.redlotus794.enigma.ddd.core.AggregateRoot;
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
public abstract class InMemoryDomainRepository<DAR extends AggregateRoot<IdentityType>, IdentityType>
        implements DomainRepository<DAR, IdentityType> {

    /**
     * In memory store.
     */
    protected final Map<IdentityType, DAR> store = new ConcurrentHashMap<>();

    @Override
    public Optional<DAR> find(IdentityType identity) {
        if (identity == null) {
            return Optional.empty();
        }
        return Optional.ofNullable(store.get(identity));
    }

    @Override
    public Collection<DAR> findAll() {
        return Collections.unmodifiableCollection(store.values());
    }

    @Override
    public void save(DAR aggregateRoot) {
        if (aggregateRoot == null) {
            throw new EnigmaSaveNullObjectException();
        }
        store.put(aggregateRoot.identity(), aggregateRoot);
    }

    @Override
    public void saveAll(Collection<DAR> aggregateRootCollection) {
        aggregateRootCollection.forEach(this::save);
    }

    @Override
    public void remove(DAR aggregateRoot) {
        if (aggregateRoot == null) {
            throw new EnigmaRemoveNullObjectException();
        }
        store.remove(aggregateRoot.identity());
    }

    @Override
    public void removeAll(Collection<DAR> aggregateRootCollection) {
        aggregateRootCollection.forEach(this::remove);
    }
}
