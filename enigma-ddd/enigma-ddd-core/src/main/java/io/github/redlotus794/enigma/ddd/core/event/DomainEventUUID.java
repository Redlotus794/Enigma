package io.github.redlotus794.enigma.ddd.core.event;

import io.github.redlotus794.enigma.ddd.core.ValueObject;
import lombok.*;

import org.jspecify.annotations.NonNull;
import java.util.UUID;

/**
 * DomainEventUUID
 *
 * @author wangjialong
 * @since 2025/12/2 15:49
 */
@ValueObject
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Builder
public class DomainEventUUID {

    @NonNull
    String uuid;

    public static DomainEventUUID next() {
        return new DomainEventUUID(UUID.randomUUID().toString());
    }
}
