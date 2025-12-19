package com.rdlts.enigma.ddd.spring.event;

import com.rdlts.enigma.ddd.core.event.DomainEvent;
import com.rdlts.enigma.ddd.spring.event.autolog.DomainSavedEvent;
import com.rdlts.enigma.ddd.spring.event.autolog.DomainSavedEventParam;
import com.rdlts.enigma.ddd.spring.test.domain.Shield;
import com.rdlts.enigma.ddd.spring.test.infrastructure.ShieldRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class EnigmaDomainEventRepositoryTest {

    @Test
    void testFind() {
        EnigmaDomainEventRepository enigmaDomainEventRepository = new EnigmaDomainEventRepository();
        Assertions.assertThrows(UnsupportedOperationException.class, () -> enigmaDomainEventRepository.find(null));
    }

    @Test
    void testFindAll() {
        EnigmaDomainEventRepository enigmaDomainEventRepository = new EnigmaDomainEventRepository();
        Assertions.assertThrows(UnsupportedOperationException.class, enigmaDomainEventRepository::findAll);
    }

    @Test
    void testSave() {
        Shield shield = new Shield();
        EnigmaDomainEventRepository enigmaDomainEventRepository = new EnigmaDomainEventRepository();
        enigmaDomainEventRepository.save(new NotPersistableEvent(shield));

        enigmaDomainEventRepository.save(new DomainSavedEvent(new DomainSavedEventParam(shield, ShieldRepository.class)));
    }

    static class NotPersistableEvent extends DomainEvent<Shield> {

        public NotPersistableEvent(Shield eventContent) {
            super(eventContent);
        }

        @Override
        public boolean isPersistable() {
            return false;
        }
    }

}