package com.rdlts.enigma.ddd.spring.autoconfigure;

import com.rdlts.enigma.ddd.core.event.DomainEventPublisher;
import com.rdlts.enigma.ddd.core.event.DomainEventRepository;
import com.rdlts.enigma.ddd.core.service.DomainServiceRegistry;
import com.rdlts.enigma.ddd.spring.event.EnigmaDomainEventRepository;
import com.rdlts.enigma.ddd.spring.event.EnigmaSpringDomainEventPublisher;
import com.rdlts.enigma.ddd.spring.service.EnigmaDomainServiceRegistry;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EnigmaDomainComponentAutoConfigurationTest {

    public static EnigmaDomainComponentAutoConfiguration enigmaDomainComponentAutoConfiguration;

    @BeforeAll
    public static void beforeClass() {
        enigmaDomainComponentAutoConfiguration = new EnigmaDomainComponentAutoConfiguration();
    }

    @Test
    void testDomainEventRepository() {
        final DomainEventRepository domainEventRepository = enigmaDomainComponentAutoConfiguration.domainEventRepository();
        assertNotNull(domainEventRepository);
        assertEquals(EnigmaDomainEventRepository.class, domainEventRepository.getClass());
    }


    @Test
    void domainEventPublisher() {
        final DomainEventPublisher domainEventPublisher = enigmaDomainComponentAutoConfiguration.domainEventPublisher(null, null);
        assertNotNull(domainEventPublisher);
        assertEquals(EnigmaSpringDomainEventPublisher.class, domainEventPublisher.getClass());
    }

    @Test
    void domainServiceRegistry() {
        final DomainServiceRegistry domainServiceRegistry = enigmaDomainComponentAutoConfiguration.domainServiceRegistry(null);
        assertNotNull(domainServiceRegistry);
        assertEquals(EnigmaDomainServiceRegistry.class, domainServiceRegistry.getClass());
    }
}