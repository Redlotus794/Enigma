package io.github.redlotus794.enigma.ddd.spring.service;

import io.github.redlotus794.enigma.EnigmaSpringBootBasedTest;
import io.github.redlotus794.enigma.ddd.core.service.DomainServiceRegistry;
import io.github.redlotus794.enigma.ddd.spring.test.domain.service.EnigmaTestDomainService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class EnigmaDomainServiceRegistryTest extends EnigmaSpringBootBasedTest {

    @Test
    void testConstructor() {
        EnigmaDomainServiceRegistry registry = new EnigmaDomainServiceRegistry();
        assertNotNull(registry);

        final DomainServiceRegistry instance = DomainServiceRegistry.instance();
        assertNotNull(instance);
        Assertions.assertTrue(instance instanceof EnigmaDomainServiceRegistry);
    }

    @Test
    void testFindService() {
        final EnigmaTestDomainService service = DomainServiceRegistry.instance().findService(EnigmaTestDomainService.class);
        Assertions.assertNotNull(service);
    }
}