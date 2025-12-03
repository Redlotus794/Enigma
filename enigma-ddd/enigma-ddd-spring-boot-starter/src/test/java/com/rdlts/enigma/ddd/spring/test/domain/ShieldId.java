package com.rdlts.enigma.ddd.spring.test.domain;

import com.rdlts.enigma.ddd.core.ValueObject;
import lombok.*;

import javax.annotation.Nonnull;

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
public class ShieldId {

    @Nonnull
    String val;

    public static ShieldId of(String s) {
        return ShieldId.builder().val(s).build();
    }
}
