package io.github.redlotus794.enigma.ddd.core;

import io.github.redlotus794.enigma.ddd.core.test.domain.TestEntity;
import io.github.redlotus794.enigma.ddd.core.test.domain.TestId;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * DomainEntityTest 实体测试类
 * @see Entity
 */
class DomainEntityTest {

    @Test
    public void testIdentity() {
        // Given
        TestId id = new TestId("test-id");
        TestEntity entity = TestEntity.builder()
                .testId(id)
                .build();

        // When
        TestId actualId = entity.identity();

        // Then
        assertNotNull(actualId);
        assertEquals(id, actualId);
    }

    /**
     * 默认抛出异常
     * @see UnsupportedOperationException
     */
    @Test
    public void testDefaultVersion() {
        // Given
        Entity<String> entity = () -> "id";
        // When & Then
        assertThrows(UnsupportedOperationException.class, entity::version);
    }

    @Test
    public void testCustomVersion() {
        // Given
        TestId id = new TestId("test-id");
        EntityVersion version = new EntityVersion(5L);
        TestEntity entity = TestEntity.builder()
                .testId(id)
                .entityVersion(version)
                .build();

        // When
        EntityVersion actualVersion = entity.version();

        // Then
        assertNotNull(actualVersion);
        assertEquals(version.getVersion(), actualVersion.getVersion());
    }

    @Test
    public void testDomainEntityIsNotAggregateRootByDefault() {
        Entity<String> entity = () -> "id";

        assertFalse(entity instanceof AggregateRoot);
    }
}
