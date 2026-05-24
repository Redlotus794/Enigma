package io.github.redlotus794.enigma.ddd.core.exception;

import org.junit.jupiter.api.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.Modifier;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class AggregateRootNotFoundExceptionTest {

    @Test
    void testDefaultConstructorIsPrivate() throws NoSuchMethodException {
        Constructor<DomainAggregateRootNotFoundException> constructor =
                DomainAggregateRootNotFoundException.class.getDeclaredConstructor();

        assertTrue(Modifier.isPrivate(constructor.getModifiers()));
    }

    @Test
    void testNullIdentity() {
        DomainAggregateRootNotFoundException exception = new DomainAggregateRootNotFoundException(null);

        assertEquals("Domain Aggregate Root null not found", exception.getMessage());
    }

    @Test
    void testStringIdentity() {
        String identity = "test-id";

        DomainAggregateRootNotFoundException exception = new DomainAggregateRootNotFoundException(identity);

        assertEquals("Domain Aggregate Root test-id not found", exception.getMessage());
    }

    @Test
    void testObjectIdentity() {
        Object identity = new Object() {
            @Override
            public String toString() {
                return "object-id";
            }
        };

        DomainAggregateRootNotFoundException exception = new DomainAggregateRootNotFoundException(identity);

        assertEquals("Domain Aggregate Root object-id not found", exception.getMessage());
    }
}
