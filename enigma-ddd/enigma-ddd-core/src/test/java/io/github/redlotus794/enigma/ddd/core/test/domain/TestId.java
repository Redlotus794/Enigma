package io.github.redlotus794.enigma.ddd.core.test.domain;

import io.github.redlotus794.enigma.ddd.core.ValueObject;
import lombok.*;

import org.jspecify.annotations.NonNull;

/**
 * TestId
 *
 * @author wangjialong
 * @since 2025/11/27 14:59
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Builder
@ValueObject
public class TestId {

    @NonNull
    String id;
}
