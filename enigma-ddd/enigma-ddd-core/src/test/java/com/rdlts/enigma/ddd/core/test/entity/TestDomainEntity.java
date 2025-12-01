package com.rdlts.enigma.ddd.core.test.entity;

import com.rdlts.enigma.ddd.core.DomainEntity;
import com.rdlts.enigma.ddd.core.EntityVersion;
import com.rdlts.enigma.ddd.core.test.valueobject.TestId;
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
public class TestDomainEntity implements DomainEntity<TestId> {

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
