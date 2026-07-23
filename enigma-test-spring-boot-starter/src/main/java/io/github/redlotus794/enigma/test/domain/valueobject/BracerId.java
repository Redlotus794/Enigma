package io.github.redlotus794.enigma.test.domain.valueobject;

import io.github.redlotus794.enigma.ddd.core.ValueObject;
import lombok.*;

import org.jspecify.annotations.NonNull;

/**
 * BracerId
 *
 * @author wangjialong
 * @since 2025/12/11 10:07
 */
@ValueObject
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Builder
@ToString
public class BracerId {

    @NonNull
    String val;
}
