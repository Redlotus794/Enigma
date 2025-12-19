package com.rdlts.enigma.ddd.core.test.domain;

import com.rdlts.enigma.ddd.core.DomainRepository;

import java.util.Collection;
import java.util.Collections;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

/**
 * TestDomainEntityRepository
 *
 * @author wangjialong
 * @since 2025/12/1 15:58
 */
public class TestDomainEntityRepository implements DomainRepository<TestDomainEntity, TestId> {

    protected ConcurrentHashMap<TestId, TestDomainEntity> testDomainEntityMap = new ConcurrentHashMap<>();

    public TestDomainEntityRepository() {

    }

    @Override
    public Optional<TestDomainEntity> find(TestId testId) {
        return Optional.ofNullable(testDomainEntityMap.get(testId));
    }

    @Override
    public Collection<TestDomainEntity> findAll() {
        return Collections.unmodifiableCollection(testDomainEntityMap.values());
    }

    @Override
    public void save(TestDomainEntity entity) {
        testDomainEntityMap.put(entity.identity(), entity);
    }

    @Override
    public void saveAll(Collection<TestDomainEntity> entityCollection) {
        entityCollection.forEach(this::save);
    }

    @Override
    public void remove(TestDomainEntity entity) {
        testDomainEntityMap.remove(entity.identity(), entity);
    }

    @Override
    public void removeAll(Collection<TestDomainEntity> entityCollection) {
        entityCollection.forEach(this::remove);
    }
}
