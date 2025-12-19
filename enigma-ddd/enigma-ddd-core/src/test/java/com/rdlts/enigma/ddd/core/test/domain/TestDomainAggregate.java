package com.rdlts.enigma.ddd.core.test.domain;

import com.rdlts.enigma.ddd.core.DomainAggregate;
import lombok.*;

import javax.annotation.Nonnull;

/**
 * TestDomainAggregate
 *
 * @author wangjialong
 * @since 2025/12/1 15:13
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Builder
public class TestDomainAggregate implements DomainAggregate<TestDomainEntity> {

    @Nonnull
    TestDomainEntity testDomainEntity;

    String name;

    @Override
    @Nonnull
    public TestDomainEntity root() {
        return this.testDomainEntity;
    }
}
