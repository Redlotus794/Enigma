package io.github.redlotus794.enigma.ddd.core;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;

/**
 * ProviderTest
 *
 * @author wangjialong
 * @since 2026/06/04
 */
class ProviderTest {

    @Test
    void shouldAllowCustomerToDefineProviderContract() {
        CharacterSnapshotProvider provider = characterId -> "character-" + characterId;

        assertNotNull(provider);
        assertEquals("character-eq-001", provider.provideCharacterSnapshot("eq-001"));
    }

    @Test
    void shouldMarkSpecializedProviderAsProviderConcept() {
        Object provider = (CharacterSnapshotProvider) characterId -> characterId;

        assertInstanceOf(Provider.class, provider);
    }

    private interface CharacterSnapshotProvider extends Provider {

        String provideCharacterSnapshot(String characterId);
    }
}
