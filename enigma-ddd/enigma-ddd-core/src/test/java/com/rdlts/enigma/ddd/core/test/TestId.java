package com.rdlts.enigma.ddd.core.test;

import com.rdlts.enigma.ddd.core.ValueObject;
import lombok.*;

import javax.annotation.Nonnull;

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
public class TestId implements ValueObject {

    @Nonnull
    String id;
}
