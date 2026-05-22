package io.github.redlotus794.enigma.ddd.core.test.domain;

import io.github.redlotus794.enigma.ddd.core.DomainAggregate;
import lombok.*;

import org.jspecify.annotations.NonNull;

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

    @NonNull
    TestDomainEntity testDomainEntity;

    String name;

    @Override
    @NonNull
    public TestDomainEntity root() {
        return this.testDomainEntity;
    }
}
