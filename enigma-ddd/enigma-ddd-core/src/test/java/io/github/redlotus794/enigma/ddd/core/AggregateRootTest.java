package io.github.redlotus794.enigma.ddd.core;

import org.jspecify.annotations.NonNull;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * DomainAggregateRootTest
 *
 * @author wangjialong
 * @since 2026/05/22
 */
class AggregateRootTest {

    @Test
    void testAggregateRootContract() {
        AggregateRoot<String> aggregateRoot = new AggregateRoot<String>() {
            @Override
            public @NonNull String identity() {
                return "root-id";
            }
        };

        assertNotNull(aggregateRoot);
        assertEquals("root-id", aggregateRoot.identity());
    }

    @Test
    void testAggregateRootIsDomainEntity() {
        AggregateRoot<String> aggregateRoot = new AggregateRoot<String>() {
            @Override
            public @NonNull String identity() {
                return "root-id";
            }
        };

        assertNotNull((Entity<String>) aggregateRoot);
    }

    @Test
    void testDefaultVersionFromEntityContract() {
        AggregateRoot<String> aggregateRoot = new AggregateRoot<String>() {
            @Override
            public @NonNull String identity() {
                return "root-id";
            }
        };

        assertThrows(UnsupportedOperationException.class, aggregateRoot::version);
    }
}
