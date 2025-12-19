package com.rdlts.enigma.ddd.core;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * EntityVersionTest 实体版本测试类
 * @see EntityVersion
 */
class EntityVersionTest {

    @Test
    public void testDefaultConstructor() {
        // When
        EntityVersion version = new EntityVersion();
        
        // Then
        assertNotNull(version);
        assertEquals(0L, version.getVersion());
    }
    
    @Test
    public void testConstructorWithVersion() {
        // Given
        long versionValue = 5L;
        
        // When
        EntityVersion version = new EntityVersion(versionValue);
        
        // Then
        assertNotNull(version);
        assertEquals(versionValue, version.getVersion());
    }
    
    @Test
    public void testZeroVersionConstant() {
        // When
        EntityVersion zeroVersion = EntityVersion.ZERO_VERSION;
        
        // Then
        assertNotNull(zeroVersion);
        assertEquals(0L, zeroVersion.getVersion());
    }
    
    @Test
    public void testZeroVersionMethod() {
        // When
        EntityVersion zeroVersion = EntityVersion.zeroVersion();
        
        // Then
        assertNotNull(zeroVersion);
        assertEquals(0L, zeroVersion.getVersion());
        assertSame(EntityVersion.ZERO_VERSION, zeroVersion);
    }
    
    @Test
    public void testNextVersion() {
        // Given
        EntityVersion currentVersion = new EntityVersion(5L);
        
        // When
        EntityVersion nextVersion = currentVersion.next();
        
        // Then
        assertNotNull(nextVersion);
        assertEquals(6L, nextVersion.getVersion());
        assertNotSame(currentVersion, nextVersion);
    }
    
    @Test
    public void testEqualsAndHashCode() {
        // Given
        EntityVersion version1 = new EntityVersion(5L);
        EntityVersion version2 = new EntityVersion(5L);
        EntityVersion version3 = new EntityVersion(6L);
        
        // Then
        assertEquals(version1, version2);
        assertEquals(version1.hashCode(), version2.hashCode());
        assertNotEquals(version1, version3);
        assertNotEquals(version1.hashCode(), version3.hashCode());
    }
    
    @Test
    public void testToString() {
        // Given
        EntityVersion version = new EntityVersion(5L);
        
        // When
        String toStringResult = version.toString();
        
        // Then
        assertNotNull(toStringResult);
        assertTrue(toStringResult.contains("EntityVersion"));
        assertTrue(toStringResult.contains("version=5"));
    }
}