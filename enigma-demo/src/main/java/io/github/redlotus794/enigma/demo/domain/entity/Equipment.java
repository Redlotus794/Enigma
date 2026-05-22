package io.github.redlotus794.enigma.demo.domain.entity;

import io.github.redlotus794.enigma.ddd.core.DomainSingleEntityAggregate;
import io.github.redlotus794.enigma.ddd.core.EntityVersion;
import io.github.redlotus794.enigma.demo.domain.valueobject.EquipmentId;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import org.jspecify.annotations.NonNull;
import java.util.Objects;

/**
 * Equipment
 *
 * @author wangjialong
 * @since 2026/05/17 11:40
 */
@Getter
@EqualsAndHashCode(exclude = "version")
@ToString
public class Equipment implements DomainSingleEntityAggregate<Equipment, EquipmentId> {

    @NonNull
    private final EquipmentId id;

    @NonNull
    private String name;

    @NonNull
    private EntityVersion version;

    private Equipment(@NonNull EquipmentId id, @NonNull String name, @NonNull EntityVersion version) {
        this.id = Objects.requireNonNull(id, "equipment id must not be null");
        this.name = normalizeName(name);
        this.version = Objects.requireNonNull(version, "equipment version must not be null");
    }

    /**
     * 创建装备实体。
     *
     * @param id 装备标识
     * @param name 装备名称
     * @return Equipment
     */
    @NonNull
    public static Equipment create(@NonNull EquipmentId id, @NonNull String name) {
        return new Equipment(id, name, EntityVersion.zeroVersion());
    }

    /**
     * 从已有状态恢复装备实体。
     *
     * @param id 装备标识
     * @param name 装备名称
     * @param version 实体版本
     * @return Equipment
     */
    @NonNull
    public static Equipment restore(@NonNull EquipmentId id, @NonNull String name, @NonNull EntityVersion version) {
        return new Equipment(id, name, version);
    }

    /**
     * 装备名称。
     *
     * @return 装备名称
     */
    @NonNull
    public String name() {
        return name;
    }

    /**
     * 重命名装备。
     *
     * @param newName 新名称
     */
    public void rename(@NonNull String newName) {
        String normalizedName = normalizeName(newName);
        if (normalizedName.equals(this.name)) {
            return;
        }
        this.name = normalizedName;
        this.version = this.version.next();
    }

    @NonNull
    @Override
    public EquipmentId identity() {
        return id;
    }

    @NonNull
    @Override
    public EntityVersion version() {
        return version;
    }

    @NonNull
    private static String normalizeName(@NonNull String name) {
        Objects.requireNonNull(name, "equipment name must not be null");
        String trimmedName = name.trim();
        if (trimmedName.isEmpty()) {
            throw new IllegalArgumentException("equipment name must not be blank");
        }
        return trimmedName;
    }

}
