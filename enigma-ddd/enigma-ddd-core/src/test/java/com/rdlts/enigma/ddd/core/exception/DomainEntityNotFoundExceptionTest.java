package com.rdlts.enigma.ddd.core.exception;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DomainEntityNotFoundExceptionTest {

    @Test
    void testDefaultConstructor() {
        // 测试默认构造函数
        DomainEntityNotFoundException exception = new DomainEntityNotFoundException();
        assertNotNull(exception);
        assertEquals("Domain Entity null not found", exception.getMessage());
        assertNull(exception.identity);
    }

    @Test
    void testConstructorWithNullIdentity() {
        // 测试传入null身份标识的构造函数
        DomainEntityNotFoundException exception = new DomainEntityNotFoundException(null);
        assertNotNull(exception);
        assertEquals("Domain Entity null not found", exception.getMessage());
        assertNull(exception.identity);
    }

    @Test
    void testConstructorWithValidIdentity() {
        // 测试传入有效身份标识的构造函数
        String identity = "test-id-123";
        DomainEntityNotFoundException exception = new DomainEntityNotFoundException(identity);
        assertNotNull(exception);
        assertEquals("Domain Entity test-id-123 not found", exception.getMessage());
        assertEquals(identity, exception.identity);
    }

    @Test
    void testConstructorWithNumericIdentity() {
        // 测试传入数字身份标识的构造函数
        Long identity = 12345L;
        DomainEntityNotFoundException exception = new DomainEntityNotFoundException(identity);
        assertNotNull(exception);
        assertEquals("Domain Entity 12345 not found", exception.getMessage());
        assertEquals(identity, exception.identity);
    }

}