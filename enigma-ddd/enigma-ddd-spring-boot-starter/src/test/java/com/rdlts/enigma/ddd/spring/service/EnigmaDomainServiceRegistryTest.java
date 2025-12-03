package com.rdlts.enigma.ddd.spring.service;

import com.rdlts.enigma.ddd.core.DomainServiceRegistry;
import com.rdlts.enigma.ddd.spring.EnigmaDomainServiceRegistry;
import com.rdlts.enigma.ddd.spring.EnigmaSpringBootBasedTest;
import com.rdlts.enigma.ddd.spring.test.service.EnigmaTestDomainService;
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