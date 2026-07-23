package io.github.redlotus794.enigma.ddd.core.cqrs;

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
        Class<?>[] interfaces = commandClass.getInterfaces();

        // When & Then
        assertEquals(1, interfaces.length);
        assertSame(CqrsRequest.class, interfaces[0]);
    }

    @Test
    void testQueryExtendsCQRS() {
        // Given
        Class<Query> queryClass = Query.class;
        Class<?>[] interfaces = queryClass.getInterfaces();

        // When & Then
        assertEquals(1, interfaces.length);
        assertSame(CqrsRequest.class, interfaces[0]);
    }

    @Test
    void testCqrsRequestExtendsCQRS() {
        // Given
        Class<CqrsRequest> cqrsRequestClass = CqrsRequest.class;
        Class<?>[] interfaces = cqrsRequestClass.getInterfaces();

        // When & Then
        assertNotNull(cqrsRequestClass);
        assertTrue(cqrsRequestClass.isInterface());
        assertEquals(1, interfaces.length);
        assertSame(CQRS.class, interfaces[0]);
    }

    @Test
    void testCommandAndQueryExtendsCqrsRequest() {
        // When & Then
        assertSame(CqrsRequest.class, Command.class.getInterfaces()[0]);
        assertSame(CqrsRequest.class, Query.class.getInterfaces()[0]);
    }
}