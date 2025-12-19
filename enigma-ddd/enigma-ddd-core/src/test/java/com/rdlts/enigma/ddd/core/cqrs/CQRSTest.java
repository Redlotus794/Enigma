package com.rdlts.enigma.ddd.core.cqrs;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * CQRS 测试类
 *
 * @see CQRS
 */
class CQRSTest {

    @Test
    void testCQRSInterfaceExists() {
        // Given
        Class<CQRS> cqrsClass = CQRS.class;

        // When & Then
        assertNotNull(cqrsClass);
        assertTrue(cqrsClass.isInterface());
    }

    @Test
    void testCommandExtendsCQRS() {
        // Given
        Class<Command> commandClass = Command.class;

        // When & Then
        assertTrue(CQRS.class.isAssignableFrom(commandClass));
    }

    @Test
    void testQueryExtendsCQRS() {
        // Given
        Class<Query> queryClass = Query.class;

        // When & Then
        assertTrue(CQRS.class.isAssignableFrom(queryClass));
    }
}