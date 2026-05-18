package com.rdlts.enigma.demo.domain.valueobject;

import com.rdlts.enigma.ddd.core.ValueObject;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import javax.annotation.Nonnull;
import java.util.Objects;

/**
 * EquipmentId
 *
 * @author wangjialong
 * @since 2026/05/17 11:40
 */
@ValueObject
@Getter
@EqualsAndHashCode
@ToString
public class EquipmentId {

    @Nonnull
    private final String value;

    @Builder(toBuilder = true)
    private EquipmentId(@Nonnull String value) {
        this.value = normalize(value);
    }

    /**
     * 创建装备标识。
     *
     * @param value 标识值
     * @return EquipmentId
     */
    @Nonnull
    public static EquipmentId of(@Nonnull String value) {
        return EquipmentId.builder().value(value).build();
    }

    @Nonnull
    private static String normalize(@Nonnull String value) {
        Objects.requireNonNull(value, "equipment id must not be null");
        String trimmedValue = value.trim();
        if (trimmedValue.isEmpty()) {
            throw new IllegalArgumentException("equipment id must not be blank");
        }
        return trimmedValue;
    }
}

