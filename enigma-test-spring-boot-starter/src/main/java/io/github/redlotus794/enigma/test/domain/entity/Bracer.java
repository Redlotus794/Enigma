package io.github.redlotus794.enigma.test.domain.entity;

import io.github.redlotus794.enigma.ddd.core.AggregateRoot;
import io.github.redlotus794.enigma.ddd.core.EntityVersion;
import io.github.redlotus794.enigma.test.domain.valueobject.BracerId;
import lombok.*;

import org.jspecify.annotations.NonNull;

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
public class Bracer implements AggregateRoot<BracerId> {

    @NonNull
    BracerId id;

    @NonNull
    String name;

    @NonNull
    @Builder.Default
    EntityVersion version = EntityVersion.zeroVersion();

    @NonNull
    @Override
    public BracerId identity() {
        return this.id;
    }

    @NonNull
    @Override
    public EntityVersion version() {
        return this.version;
    }
}
