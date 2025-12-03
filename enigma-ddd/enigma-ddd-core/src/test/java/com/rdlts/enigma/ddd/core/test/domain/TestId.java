package com.rdlts.enigma.ddd.core.test.domain;

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
@ValueObject
public class TestId {

    @Nonnull
    String id;
}
