package io.github.redlotus794.enigma.ddd.core.test.domain;

import io.github.redlotus794.enigma.ddd.core.DomainRepository;

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
public class TestDomainEntityRepository implements DomainRepository<TestEntity, TestId> {

    protected ConcurrentHashMap<TestId, TestEntity> testDomainEntityMap = new ConcurrentHashMap<>();

    public TestDomainEntityRepository() {

    }

    @Override
    public Optional<TestEntity> find(TestId testId) {
        return Optional.ofNullable(testDomainEntityMap.get(testId));
    }

    @Override
    public Collection<TestEntity> findAll() {
        return Collections.unmodifiableCollection(testDomainEntityMap.values());
    }

    @Override
    public void save(TestEntity entity) {
        testDomainEntityMap.put(entity.identity(), entity);
    }

    @Override
    public void saveAll(Collection<TestEntity> entityCollection) {
        entityCollection.forEach(this::save);
    }

    @Override
    public void remove(TestEntity entity) {
        testDomainEntityMap.remove(entity.identity(), entity);
    }

    @Override
    public void removeAll(Collection<TestEntity> entityCollection) {
        entityCollection.forEach(this::remove);
    }
}
