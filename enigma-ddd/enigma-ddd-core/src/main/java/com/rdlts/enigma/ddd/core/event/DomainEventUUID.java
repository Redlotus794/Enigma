package com.rdlts.enigma.ddd.core.event;

import com.rdlts.enigma.ddd.core.ValueObject;
import lombok.*;

import javax.annotation.Nonnull;
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

    @Nonnull
    String uuid;

    public static DomainEventUUID next() {
        return new DomainEventUUID(UUID.randomUUID().toString());
    }
}
