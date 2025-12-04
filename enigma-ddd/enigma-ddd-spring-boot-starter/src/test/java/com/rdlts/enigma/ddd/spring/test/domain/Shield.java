package com.rdlts.enigma.ddd.spring.test.domain;

import com.rdlts.enigma.ddd.core.DomainEntity;
import com.rdlts.enigma.ddd.core.EntityVersion;
import lombok.*;

import javax.annotation.Nonnull;

/**
 * Shield
 *
 * @author wangjialong
 * @since 2025/12/3 13:35
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Builder
public class Shield implements DomainEntity<ShieldId> {

    @Nonnull
    ShieldId shieldId;

    @Nonnull
    @Builder.Default
    EntityVersion entityVersion = EntityVersion.ZERO_VERSION;

    public Shield(@Nonnull ShieldId shieldId) {
        this.shieldId = shieldId;
        this.entityVersion = EntityVersion.ZERO_VERSION;
    }

    @Nonnull
    @Override
    public ShieldId identity() {
        return this.shieldId;
    }

    @Nonnull
    @Override
    public EntityVersion version() {
        return this.entityVersion;
    }
}
