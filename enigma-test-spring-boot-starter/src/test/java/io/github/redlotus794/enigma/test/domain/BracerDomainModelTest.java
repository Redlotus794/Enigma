package io.github.redlotus794.enigma.test.domain;

import io.github.redlotus794.enigma.ddd.core.EntityVersion;
import io.github.redlotus794.enigma.test.domain.entity.Bracer;
import io.github.redlotus794.enigma.test.domain.valueobject.BracerId;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class BracerDomainModelTest {

    @Test
    void shouldCreateBracerIdThroughBuilder() {
        final BracerId bracerId = BracerId.builder()
                .val("BR-001")
                .build();

        Assertions.assertEquals("BR-001", bracerId.getVal());
        Assertions.assertEquals(new BracerId("BR-001"), bracerId);
        Assertions.assertTrue(bracerId.toString().contains("BR-001"));
    }

    @Test
    void shouldUseZeroVersionByDefaultWhenBuildingBracer() {
        final BracerId bracerId = new BracerId("BR-002");
        final Bracer bracer = Bracer.builder()
                .id(bracerId)
                .name("Estelle Bright")
                .build();

        Assertions.assertEquals(bracerId, bracer.identity());
        Assertions.assertEquals("Estelle Bright", bracer.getName());
        Assertions.assertEquals(EntityVersion.zeroVersion(), bracer.version());
    }

    @Test
    void shouldExposeExplicitVersionFromAllArgsConstructor() {
        final BracerId bracerId = new BracerId("BR-003");
        final EntityVersion version = new EntityVersion(3L);
        final Bracer bracer = new Bracer(bracerId, "Joshua Bright", version);

        Assertions.assertEquals(bracerId, bracer.identity());
        Assertions.assertEquals(version, bracer.version());
        Assertions.assertTrue(bracer.toString().contains("Joshua Bright"));
    }
}

