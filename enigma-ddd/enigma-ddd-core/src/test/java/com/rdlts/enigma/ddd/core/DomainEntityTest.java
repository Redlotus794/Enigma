package com.rdlts.enigma.ddd.core;

import com.rdlts.enigma.ddd.core.test.entity.TestDomainEntity;
import com.rdlts.enigma.ddd.core.test.valueobject.TestId;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * DomainEntityTest 实体测试类
 * @see DomainEntity
 */
class DomainEntityTest {

    @Test
    public void testIdentity() {
        // Given
        TestId id = new TestId("test-id");
        TestDomainEntity entity = TestDomainEntity.builder()
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
        DomainEntity<String> domainEntity = () -> "id";
        // When & Then
        assertThrows(UnsupportedOperationException.class, domainEntity::version);
    }

    @Test
    public void testCustomVersion() {
        // Given
        TestId id = new TestId("test-id");
        EntityVersion version = new EntityVersion(5L);
        TestDomainEntity entity = TestDomainEntity.builder()
                .testId(id)
                .entityVersion(version)
                .build();

        // When
        EntityVersion actualVersion = entity.version();

        // Then
        assertNotNull(actualVersion);
        assertEquals(version.getVersion(), actualVersion.getVersion());
    }
}