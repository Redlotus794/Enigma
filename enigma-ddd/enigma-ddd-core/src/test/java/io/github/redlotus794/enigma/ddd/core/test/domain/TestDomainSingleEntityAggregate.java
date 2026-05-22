package io.github.redlotus794.enigma.ddd.core.test.domain;

import io.github.redlotus794.enigma.ddd.core.DomainSingleEntityAggregate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import org.jspecify.annotations.NonNull;

/**
 * TestDomainSingleEntityAggregate
 *
 * @author wangjialong
 * @since 2026/05/22 10:12
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Builder
public class TestDomainSingleEntityAggregate
        implements DomainSingleEntityAggregate<TestDomainSingleEntityAggregate, TestId> {

    @NonNull
    private TestId testId;

    @NonNull
    @Override
    public TestId identity() {
        return testId;
    }
}
