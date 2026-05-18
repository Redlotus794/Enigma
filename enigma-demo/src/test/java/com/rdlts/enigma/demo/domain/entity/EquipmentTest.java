package com.rdlts.enigma.demo.domain.entity;

import com.rdlts.enigma.ddd.core.EntityVersion;
import com.rdlts.enigma.demo.domain.valueobject.EquipmentId;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * EquipmentTest
 *
 * @author wangjialong
 * @since 2026/05/17 11:40
 */
class EquipmentTest {

    @Test
    void shouldCreateEquipmentWhenInputIsValid() {
        Equipment equipment = Equipment.create(EquipmentId.of("eq-001"), "Silver Sword");

        Assertions.assertEquals("eq-001", equipment.identity().getValue());
        Assertions.assertEquals("Silver Sword", equipment.name());
        Assertions.assertEquals(EntityVersion.zeroVersion(), equipment.version());
    }

    @Test
    void shouldRenameEquipmentAndIncreaseVersion() {
        Equipment equipment = Equipment.create(EquipmentId.of("eq-001"), "Silver Sword");

        equipment.rename("Golden Sword");

        Assertions.assertEquals("Golden Sword", equipment.name());
        Assertions.assertEquals(EntityVersion.zeroVersion().next(), equipment.version());
    }

    @Test
    void shouldKeepVersionWhenRenameToSameName() {
        Equipment equipment = Equipment.create(EquipmentId.of("eq-001"), "Silver Sword");

        equipment.rename("Silver Sword");

        Assertions.assertEquals(EntityVersion.zeroVersion(), equipment.version());
    }

    @Test
    void shouldCompareByIdentityOnly() {
        Equipment left = Equipment.create(EquipmentId.of("eq-001"), "Silver Sword");
        Equipment right = Equipment.restore(
                EquipmentId.of("eq-001"),
                "Golden Sword",
                EntityVersion.zeroVersion().next()
        );

        Assertions.assertEquals(left, right);
        Assertions.assertEquals(left.hashCode(), right.hashCode());
    }

    @Test
    void shouldRejectBlankEquipmentName() {
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> Equipment.create(EquipmentId.of("eq-001"), "   "));
    }

    @Test
    void shouldRejectNullEquipmentNameWhenRename() {
        Equipment equipment = Equipment.create(EquipmentId.of("eq-001"), "Silver Sword");

        Assertions.assertThrows(NullPointerException.class, () -> equipment.rename(nullableEquipmentName()));
    }

    private String nullableEquipmentName() {
        return null;
    }
}

