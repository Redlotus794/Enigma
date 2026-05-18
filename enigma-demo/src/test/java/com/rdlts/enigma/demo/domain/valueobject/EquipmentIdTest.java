package com.rdlts.enigma.demo.domain.valueobject;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * EquipmentIdTest
 *
 * @author wangjialong
 * @since 2026/05/17 11:40
 */
class EquipmentIdTest {

    @Test
    void shouldCreateEquipmentIdWhenValueIsValid() {
        EquipmentId equipmentId = EquipmentId.of("  eq-001  ");

        Assertions.assertEquals("eq-001", equipmentId.getValue());
    }

    @Test
    void shouldRejectBlankEquipmentId() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> EquipmentId.of("   "));
    }

}

