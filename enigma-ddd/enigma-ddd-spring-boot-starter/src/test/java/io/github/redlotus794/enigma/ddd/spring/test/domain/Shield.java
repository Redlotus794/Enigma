package io.github.redlotus794.enigma.ddd.spring.test.domain;

import io.github.redlotus794.enigma.ddd.core.AggregateRoot;
import io.github.redlotus794.enigma.ddd.core.EntityVersion;
import io.github.redlotus794.enigma.ddd.core.event.DomainEventParam;
import lombok.*;

import org.jspecify.annotations.NonNull;

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
public class Shield implements AggregateRoot<ShieldId>, DomainEventParam {

    @NonNull
    ShieldId shieldId;

    @NonNull
    @Builder.Default
    EntityVersion entityVersion = EntityVersion.ZERO_VERSION;

    public Shield(@NonNull ShieldId shieldId) {
        this.shieldId = shieldId;
        this.entityVersion = EntityVersion.ZERO_VERSION;
    }

    @NonNull
    @Override
    public ShieldId identity() {
        return this.shieldId;
    }

    @NonNull
    @Override
    public EntityVersion version() {
        return this.entityVersion;
    }
}
