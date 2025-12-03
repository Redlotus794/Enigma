package com.rdlts.enigma.ddd.core.test.domain;

import com.rdlts.enigma.ddd.core.DomainEntity;
import com.rdlts.enigma.ddd.core.DomainEventParam;
import com.rdlts.enigma.ddd.core.EntityVersion;
import lombok.*;

import javax.annotation.Nonnull;

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
public class TestDomainEntity implements DomainEntity<TestId>, DomainEventParam {

    @Nonnull
    TestId testId;

    @Nonnull
    @Builder.Default
    EntityVersion entityVersion = EntityVersion.ZERO_VERSION;

    @Nonnull
    @Override
    public TestId identity() {
        return testId;
    }

    @Nonnull
    @Override
    public EntityVersion version() {
        return entityVersion;
    }
}
