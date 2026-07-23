package io.github.redlotus794.enigma.ddd.spring.test.domain;

import io.github.redlotus794.enigma.ddd.core.ValueObject;
import lombok.*;

import org.jspecify.annotations.NonNull;
import java.util.UUID;

/**
 * ShieldId
 *
 * @author wangjialong
 * @since 2025/12/3 13:35
 */
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Builder
@ValueObject
@ToString
public class ShieldId {

    @NonNull
    String val;

    public static ShieldId of(String s) {
        return ShieldId.builder().val(s).build();
    }
}
