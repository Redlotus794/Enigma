package io.github.redlotus794.enigma.ddd.core.test.domain;

import io.github.redlotus794.enigma.ddd.core.AggregateRoot;
import io.github.redlotus794.enigma.ddd.core.event.DomainEventParam;
import io.github.redlotus794.enigma.ddd.core.EntityVersion;
import lombok.*;

import org.jspecify.annotations.NonNull;

/**
 * TestDomainEntity
 *
 * @author wangjialong
 * @since 2025/11/27 14:59
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Builder
public class TestEntity implements AggregateRoot<TestId>, DomainEventParam {

    @NonNull
    TestId testId;

    @NonNull
    @Builder.Default
    EntityVersion entityVersion = EntityVersion.ZERO_VERSION;

    @NonNull
    @Override
    public TestId identity() {
        return testId;
    }

    @NonNull
    @Override
    public EntityVersion version() {
        return entityVersion;
    }
}
