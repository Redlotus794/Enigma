package com.rdlts.enigma.ddd.spring.repository;

import com.rdlts.enigma.ddd.core.DomainEntity;
import com.rdlts.enigma.ddd.core.DomainRepository;
import com.rdlts.enigma.ddd.spring.event.autolog.RepositoryEventAutoLog;
import com.rdlts.enigma.ddd.spring.exception.EnigmaRemoveNullObjectException;
import com.rdlts.enigma.ddd.spring.exception.EnigmaSaveNullObjectException;

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
 * @see com.rdlts.enigma.ddd.spring.event.autolog.RepositoryEventAutoLogAspect
 * @see com.rdlts.enigma.ddd.core.event.DomainEventRepository
 * @author wangjialong
 * @since 2025/12/17 13:51
 */
@RepositoryEventAutoLog
public abstract class InMemoryDomainRepository<DE extends DomainEntity<IdentityType>, IdentityType>
        implements DomainRepository<DE, IdentityType> {

    /**
     * In memory store.
     */
    protected final Map<IdentityType, DE> store = new ConcurrentHashMap<>();

    @Override
    public Optional<DE> find(IdentityType identity) {
        if (identity == null) {
            return Optional.empty();
        }
        return Optional.ofNullable(store.get(identity));
    }

    @Override
    public Collection<DE> findAll() {
        return Collections.unmodifiableCollection(store.values());
    }

    @Override
    public void save(DE entity) {
        if (entity == null) {
            throw new EnigmaSaveNullObjectException();
        }
        store.put(entity.identity(), entity);
    }

    @Override
    public void saveAll(Collection<DE> entityCollection) {
        entityCollection.forEach(this::save);
    }

    @Override
    public void remove(DE entity) {
        if (entity == null) {
            throw new EnigmaRemoveNullObjectException();
        }
        store.remove(entity.identity());
    }

    @Override
    public void removeAll(Collection<DE> entityCollection) {
        entityCollection.forEach(this::remove);
    }
}
