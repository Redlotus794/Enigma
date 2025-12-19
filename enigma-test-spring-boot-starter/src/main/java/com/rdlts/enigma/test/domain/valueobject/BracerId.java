package com.rdlts.enigma.test.domain.valueobject;

import com.rdlts.enigma.ddd.core.ValueObject;
import lombok.*;

import javax.annotation.Nonnull;

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

    @Nonnull
    String val;
}
