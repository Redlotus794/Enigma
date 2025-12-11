package com.rdlts.enigma.test.domain.entity;

import com.rdlts.enigma.ddd.core.DomainEntity;
import com.rdlts.enigma.ddd.core.EntityVersion;
import com.rdlts.enigma.test.domain.valueobject.BracerId;
import lombok.*;

import javax.annotation.Nonnull;

/**
 * Bracer
 *
 * @author wangjialong
 * @since 2025/12/11 10:06
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Builder
@ToString
public class Bracer implements DomainEntity<BracerId> {

    @Nonnull
    BracerId id;

    @Nonnull
    String name;

    @Nonnull
    @Builder.Default
    EntityVersion version = EntityVersion.zeroVersion();

    @Nonnull
    @Override
    public BracerId identity() {
        return this.id;
    }

    @Nonnull
    @Override
    public EntityVersion version() {
        return this.version;
    }
}
