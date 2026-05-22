package io.github.redlotus794.enigma.demo.domain.valueobject;

import io.github.redlotus794.enigma.ddd.core.ValueObject;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import org.jspecify.annotations.NonNull;
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

    @NonNull
    private final String value;

    @Builder(toBuilder = true)
    private EquipmentId(@NonNull String value) {
        this.value = normalize(value);
    }

    /**
     * 创建装备标识。
     *
     * @param value 标识值
     * @return EquipmentId
     */
    @NonNull
    public static EquipmentId of(@NonNull String value) {
        return EquipmentId.builder().value(value).build();
    }

    @NonNull
    private static String normalize(@NonNull String value) {
        Objects.requireNonNull(value, "equipment id must not be null");
        String trimmedValue = value.trim();
        if (trimmedValue.isEmpty()) {
            throw new IllegalArgumentException("equipment id must not be blank");
        }
        return trimmedValue;
    }
}

