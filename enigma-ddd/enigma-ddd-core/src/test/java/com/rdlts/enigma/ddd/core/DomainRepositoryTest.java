package com.rdlts.enigma.ddd.core;

import com.rdlts.enigma.ddd.core.exception.DomainEntityNotFoundException;
import com.rdlts.enigma.ddd.core.test.domain.TestDomainEntity;
import com.rdlts.enigma.ddd.core.test.domain.TestDomainEntityRepository;
import com.rdlts.enigma.ddd.core.test.domain.TestId;
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
    TestDomainEntity testEntity1;
    TestDomainEntity testEntity2;
    TestId testId1;
    TestId testId2;

    @BeforeEach
    public void setUp() {
        repository = new TestDomainEntityRepository();
        testId1 = new TestId("test-id-1");
        testId2 = new TestId("test-id-2");
        testEntity1 = TestDomainEntity.builder()
                .testId(testId1)
                .build();
        testEntity2 = TestDomainEntity.builder()
                .testId(testId2)
                .build();
    }

    @Test
    public void testNextIdentityShouldThrowExceptionByDefault() {
        assertThrows(UnsupportedOperationException.class, repository::nextIdentity);
    }

    @Test
    public void testFindShouldReturnEmptyWhenEntityNotExists() {
        Optional<TestDomainEntity> result = repository.find(testId1);
        assertFalse(result.isPresent());
    }

    @Test
    public void testFindShouldReturnEntityWhenEntityExists() {
        // Given
        repository.save(testEntity1);

        // When
        Optional<TestDomainEntity> result = repository.find(testId1);

        // Then
        assertTrue(result.isPresent());
        assertEquals(testEntity1, result.get());
    }

    @Test
    public void testFindAllShouldReturnEmptyCollectionWhenNoEntities() {
        Collection<TestDomainEntity> result = repository.findAll();
        assertNotNull(result);
        assertTrue(result.isEmpty());
    }

    @Test
    public void testFindAllShouldReturnAllEntities() {
        // Given
        repository.save(testEntity1);
        repository.save(testEntity2);

        // When
        Collection<TestDomainEntity> result = repository.findAll();

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
        Optional<TestDomainEntity> result = repository.find(testId1);
        assertTrue(result.isPresent());
        assertEquals(testEntity1, result.get());
    }

    @Test
    public void testSaveAllShouldAddAllEntitiesToRepository() {
        // When
        repository.saveAll(Arrays.asList(testEntity1, testEntity2));

        // Then
        Collection<TestDomainEntity> result = repository.findAll();
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
        Optional<TestDomainEntity> result = repository.find(testId1);
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
        Collection<TestDomainEntity> result = repository.findAll();
        assertTrue(result.isEmpty());
    }

    @Test
    public void testFindRequired() {
        assertThrows(DomainEntityNotFoundException.class, () -> repository.findRequired(testId1));

        repository.save(testEntity1);
        TestDomainEntity entity = repository.findRequired(testId1);
        assertEquals(testEntity1, entity);
    }
}