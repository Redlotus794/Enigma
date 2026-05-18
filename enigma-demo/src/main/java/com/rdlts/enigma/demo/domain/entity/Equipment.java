package com.rdlts.enigma.demo.domain.entity;

import com.rdlts.enigma.ddd.core.DomainEntity;
import com.rdlts.enigma.ddd.core.EntityVersion;
import com.rdlts.enigma.demo.domain.valueobject.EquipmentId;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import javax.annotation.Nonnull;
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
public class Equipment implements DomainEntity<EquipmentId> {

    @Nonnull
    private final EquipmentId id;

    @Nonnull
    private String name;

    @Nonnull
    private EntityVersion version;

    private Equipment(@Nonnull EquipmentId id, @Nonnull String name, @Nonnull EntityVersion version) {
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
    @Nonnull
    public static Equipment create(@Nonnull EquipmentId id, @Nonnull String name) {
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
    @Nonnull
    public static Equipment restore(@Nonnull EquipmentId id, @Nonnull String name, @Nonnull EntityVersion version) {
        return new Equipment(id, name, version);
    }

    /**
     * 装备名称。
     *
     * @return 装备名称
     */
    @Nonnull
    public String name() {
        return name;
    }

    /**
     * 重命名装备。
     *
     * @param newName 新名称
     */
    public void rename(@Nonnull String newName) {
        String normalizedName = normalizeName(newName);
        if (normalizedName.equals(this.name)) {
            return;
        }
        this.name = normalizedName;
        this.version = this.version.next();
    }

    @Nonnull
    @Override
    public EquipmentId identity() {
        return id;
    }

    @Nonnull
    @Override
    public EntityVersion version() {
        return version;
    }

    @Nonnull
    private static String normalizeName(@Nonnull String name) {
        Objects.requireNonNull(name, "equipment name must not be null");
        String trimmedName = name.trim();
        if (trimmedName.isEmpty()) {
            throw new IllegalArgumentException("equipment name must not be blank");
        }
        return trimmedName;
    }

}

