package io.github.redlotus794.enigma.ddd.core;

import io.github.redlotus794.enigma.ddd.core.exception.DomainAggregateRootNotFoundException;
import io.github.redlotus794.enigma.ddd.core.test.domain.TestEntity;
import io.github.redlotus794.enigma.ddd.core.test.domain.TestDomainEntityRepository;
import io.github.redlotus794.enigma.ddd.core.test.domain.TestId;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collection;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

/**
 * DomainRepositoryTest
 *
 * @author wangjialong
 * @since 2025/12/1 15:58
 */
public class DomainRepositoryTest {

    TestDomainEntityRepository repository;
    TestEntity testEntity1;
    TestEntity testEntity2;
    TestId testId1;
    TestId testId2;

    @BeforeEach
    public void setUp() {
        repository = new TestDomainEntityRepository();
        testId1 = new TestId("test-id-1");
        testId2 = new TestId("test-id-2");
        testEntity1 = TestEntity.builder()
                .testId(testId1)
                .build();
        testEntity2 = TestEntity.builder()
                .testId(testId2)
                .build();
    }

    @Test
    public void testFindShouldReturnEmptyWhenEntityNotExists() {
        Optional<TestEntity> result = repository.find(testId1);
        assertFalse(result.isPresent());
    }

    @Test
    public void testFindShouldReturnEntityWhenEntityExists() {
        // Given
        repository.save(testEntity1);

        // When
        Optional<TestEntity> result = repository.find(testId1);

        // Then
        assertTrue(result.isPresent());
        assertEquals(testEntity1, result.get());
    }

    @Test
    public void testFindAllShouldReturnEmptyCollectionWhenNoEntities() {
        Collection<TestEntity> result = repository.findAll();
        assertNotNull(result);
        assertTrue(result.isEmpty());
    }

    @Test
    public void testFindAllShouldReturnAllEntities() {
        // Given
        repository.save(testEntity1);
        repository.save(testEntity2);

        // When
        Collection<TestEntity> result = repository.findAll();

        // Then
        assertNotNull(result);
        assertEquals(2, result.size());
        assertTrue(result.contains(testEntity1));
        assertTrue(result.contains(testEntity2));
    }

    @Test
    public void testSaveShouldAddEntityToRepository() {
        // When
        repository.save(testEntity1);

        // Then
        Optional<TestEntity> result = repository.find(testId1);
        assertTrue(result.isPresent());
        assertEquals(testEntity1, result.get());
    }

    @Test
    public void testSaveAllShouldAddAllEntitiesToRepository() {
        // When
        repository.saveAll(Arrays.asList(testEntity1, testEntity2));

        // Then
        Collection<TestEntity> result = repository.findAll();
        assertEquals(2, result.size());
        assertTrue(result.contains(testEntity1));
        assertTrue(result.contains(testEntity2));
    }

    @Test
    public void testRemoveShouldDeleteEntityFromRepository() {
        // Given
        repository.save(testEntity1);

        // When
        repository.remove(testEntity1);

        // Then
        Optional<TestEntity> result = repository.find(testId1);
        assertFalse(result.isPresent());
    }

    @Test
    public void testRemoveAllShouldDeleteAllEntitiesFromRepository() {
        // Given
        repository.save(testEntity1);
        repository.save(testEntity2);

        // When
        repository.removeAll(Arrays.asList(testEntity1, testEntity2));

        // Then
        Collection<TestEntity> result = repository.findAll();
        assertTrue(result.isEmpty());
    }

    @Test
    public void testFindRequired() {
        assertThrows(DomainAggregateRootNotFoundException.class, () -> repository.findRequired(testId1));

        repository.save(testEntity1);
        TestEntity entity = repository.findRequired(testId1);
        assertEquals(testEntity1, entity);
    }

    @Test
    public void testDefaultNextIdentity() {
        assertThrows(UnsupportedOperationException.class, repository::nextIdentity);
    }
}
