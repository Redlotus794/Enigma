package io.github.redlotus794.enigma.ddd.spring.autoconfigure;

import io.github.redlotus794.enigma.ddd.core.event.DomainEventPublisher;
import io.github.redlotus794.enigma.ddd.core.event.DomainEventRepository;
import io.github.redlotus794.enigma.ddd.core.service.DomainServiceRegistry;
import io.github.redlotus794.enigma.ddd.spring.event.EnigmaDomainEventRepository;
import io.github.redlotus794.enigma.ddd.spring.event.EnigmaSpringDomainEventPublisher;
import io.github.redlotus794.enigma.ddd.spring.service.EnigmaDomainServiceRegistry;
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