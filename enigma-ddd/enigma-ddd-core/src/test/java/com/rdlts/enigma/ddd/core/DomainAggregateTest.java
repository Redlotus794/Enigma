package com.rdlts.enigma.ddd.core;

import com.rdlts.enigma.ddd.core.test.entity.TestDomainAggregate;
import com.rdlts.enigma.ddd.core.test.entity.TestDomainEntity;
import com.rdlts.enigma.ddd.core.test.valueobject.TestId;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * DomainAggregateTest
 *
 * @author wangjialong
 * @since 2025/12/1 15:15
 */
public class DomainAggregateTest {

    @Test
    public void testRoot() {
        TestDomainAggregate aggregate = TestDomainAggregate.builder()
                .testDomainEntity(TestDomainEntity.builder()
                        .testId(TestId.builder().id("1").build())
                        .build())
                .build();
        TestDomainEntity entity = aggregate.root();
        Assertions.assertEquals("1", entity.getTestId().getId());
    }
}
