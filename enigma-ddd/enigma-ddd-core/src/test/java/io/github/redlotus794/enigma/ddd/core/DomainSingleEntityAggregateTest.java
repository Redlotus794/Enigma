package io.github.redlotus794.enigma.ddd.core;

import io.github.redlotus794.enigma.ddd.core.test.domain.TestDomainSingleEntityAggregate;
import io.github.redlotus794.enigma.ddd.core.test.domain.TestId;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;

/**
 * DomainSingleEntityAggregateTest
 *
 * @author wangjialong
 * @since 2026/05/22 10:12
 */
class DomainSingleEntityAggregateTest {

    @Test
    void testRootReturnsSelf() {
        TestDomainSingleEntityAggregate aggregate = TestDomainSingleEntityAggregate.builder()
                .testId(new TestId("single-entity-aggregate-id"))
                .build();

        DomainSingleEntityAggregate<TestDomainSingleEntityAggregate, TestId> root = aggregate.root();

        assertSame(aggregate, root);
    }

    @Test
    void testIdentity() {
        TestId testId = new TestId("single-entity-aggregate-id");
        TestDomainSingleEntityAggregate aggregate = TestDomainSingleEntityAggregate.builder()
                .testId(testId)
                .build();

        TestId identity = aggregate.identity();

        assertEquals(testId, identity);
    }
}
