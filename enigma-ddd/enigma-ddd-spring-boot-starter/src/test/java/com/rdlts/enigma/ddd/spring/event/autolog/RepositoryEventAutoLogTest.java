package com.rdlts.enigma.ddd.spring.event.autolog;

import com.rdlts.enigma.EnigmaSpringBootBasedTest;
import com.rdlts.enigma.ddd.core.event.DomainEvent;
import com.rdlts.enigma.ddd.core.event.DomainEventRepository;
import com.rdlts.enigma.ddd.spring.test.domain.Shield;
import com.rdlts.enigma.ddd.spring.test.infrastructure.EnigmaSpringDomainEventRepository;
import com.rdlts.enigma.ddd.spring.test.infrastructure.ShieldRepository;
import com.rdlts.enigma.random.domain.EnigmaRandomGenerator;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collection;
import java.util.Optional;

@Log4j2
public class RepositoryEventAutoLogTest extends EnigmaSpringBootBasedTest {

    @Autowired
    ShieldRepository shieldRepository;
    @Autowired
    DomainEventRepository domainEventRepository;
    @Autowired
    EnigmaRandomGenerator enigmaRandomGenerator;

    @BeforeEach
    void beforeEach() {
        ((EnigmaSpringDomainEventRepository) domainEventRepository).clearAll();
    }

    @Test
    void testSave() {
        final Shield shield = enigmaRandomGenerator.nextObject(Shield.class);
        shieldRepository.save(shield);

        final Collection<DomainEvent<DomainSavedEventParam>> savedEvent
                = ((EnigmaSpringDomainEventRepository) domainEventRepository).findBy(DomainSavedEventParam.class);

        final Optional<DomainEvent<DomainSavedEventParam>> first = savedEvent.stream()
                .filter(t -> t.getEventContent().getDomainEntity().equals(shield))
                .findFirst();

        Assertions.assertTrue(first.isPresent());
        Assertions.assertEquals(RepositoryEventAutoLogAspect.CREATOR, first.get().getCreatedBy());
        Assertions.assertNotNull(first.get().getEventTime());
        Assertions.assertNotNull(first.get().getEventUuid());
        log.info(first.get());
    }

    @Test
    void testSaveAll() {
        final Collection<Shield> shields = enigmaRandomGenerator.nextList(Shield.class, 5);
        shieldRepository.saveAll(shields);
        final Collection<DomainEvent<DomainSavedAllEventParam>> savedEvent
                = ((EnigmaSpringDomainEventRepository) domainEventRepository).findBy(DomainSavedAllEventParam.class);

        final Optional<DomainEvent<DomainSavedAllEventParam>> first = savedEvent
                .stream()
                .findFirst();

        Assertions.assertTrue(first.isPresent());
        Assertions.assertEquals(5, first.get().getEventContent().getDomainEntities().size());
    }

    @Test
    void testRemove() {
        final Shield shield = enigmaRandomGenerator.nextObject(Shield.class);
        shieldRepository.save(shield);
        shieldRepository.remove(shield);

        final Collection<DomainEvent<DomainRemovedEventParam>> savedEvent
                = ((EnigmaSpringDomainEventRepository) domainEventRepository).findBy(DomainRemovedEventParam.class);

        final Optional<DomainEvent<DomainRemovedEventParam>> first = savedEvent.stream()
                .filter(t -> t.getEventContent().getDomainEntity().equals(shield))
                .findFirst();

        Assertions.assertTrue(first.isPresent());
        Assertions.assertEquals(RepositoryEventAutoLogAspect.CREATOR, first.get().getCreatedBy());
        Assertions.assertNotNull(first.get().getEventTime());
        Assertions.assertNotNull(first.get().getEventUuid());
        log.info(first.get());
    }

    @Test
    void testRemoveAll() {
        final Collection<Shield> shields = enigmaRandomGenerator.nextList(Shield.class, 5);
        shieldRepository.saveAll(shields);
        shieldRepository.removeAll(shields);
        final Collection<DomainEvent<DomainRemovedAllEventParam>> savedEvent
                = ((EnigmaSpringDomainEventRepository) domainEventRepository).findBy(DomainRemovedAllEventParam.class);

        final Optional<DomainEvent<DomainRemovedAllEventParam>> first = savedEvent
                .stream()
                .findFirst();

        Assertions.assertTrue(first.isPresent());
        Assertions.assertEquals(5, first.get().getEventContent().getDomainEntities().size());
    }
}