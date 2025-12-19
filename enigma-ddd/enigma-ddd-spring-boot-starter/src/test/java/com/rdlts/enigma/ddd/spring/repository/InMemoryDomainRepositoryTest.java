package com.rdlts.enigma.ddd.spring.repository;

import com.rdlts.enigma.EnigmaSpringBootBasedTest;
import com.rdlts.enigma.ddd.spring.exception.EnigmaRemoveNullObjectException;
import com.rdlts.enigma.ddd.spring.exception.EnigmaSaveNullObjectException;
import com.rdlts.enigma.ddd.spring.test.domain.Shield;
import com.rdlts.enigma.ddd.spring.test.infrastructure.ShieldRepository;
import com.rdlts.enigma.random.domain.EnigmaRandomGenerator;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class InMemoryDomainRepositoryTest extends EnigmaSpringBootBasedTest {

    @Autowired
    ShieldRepository shieldRepository;

    @Autowired
    EnigmaRandomGenerator enigmaRandomGenerator;

    List<Shield> shields;

    @BeforeEach
    void beforeEach() {
        shieldRepository.clearAll();
        shields = enigmaRandomGenerator.nextList(Shield.class, 2);
    }

    @AfterEach
    void afterEach() {
        shieldRepository.removeAll(shields);
    }

    @Test
    void find() {
        final Optional<Shield> shield = shieldRepository.find(null);
        assertFalse(shield.isPresent());
    }

    @Test
    void findAll() {
        shieldRepository.saveAll(shields);
        final Collection<Shield> all = shieldRepository.findAll();
        assertEquals(shields.size(), all.size());
    }

    @Test
    void save() {
        Assertions.assertThrows(EnigmaSaveNullObjectException.class, () -> shieldRepository.save(null));

        shieldRepository.save(shields.get(0));
        final Optional<Shield> shield = shieldRepository.find(shields.get(0).identity());
        assertTrue(shield.isPresent());
        assertEquals(shields.get(0).identity(), shield.get().identity());
    }

    @Test
    void saveAll() {
        shieldRepository.saveAll(shields);
        final Collection<Shield> all = shieldRepository.findAll();
        assertEquals(shields.size(), all.size());
        for (Shield shield : shields) {
            final Optional<Shield> find = shieldRepository.find(shield.identity());
            assertTrue(find.isPresent());
            assertEquals(shield.identity(), find.get().identity());
        }
    }

    @Test
    void remove() {
        Assertions.assertThrows(EnigmaRemoveNullObjectException.class, () -> shieldRepository.remove(null));
        shieldRepository.saveAll(shields);
        shieldRepository.remove(shields.get(0));
        final Collection<Shield> all = shieldRepository.findAll();
        assertEquals(shields.size() - 1, all.size());
        for (Shield shield : shields) {
            final Optional<Shield> find = shieldRepository.find(shield.identity());
            if (shield.equals(shields.get(0))) {
                assertFalse(find.isPresent());
            } else {
                assertTrue(find.isPresent());
                assertEquals(shield.identity(), find.get().identity());
            }
        }
    }

    @Test
    void removeAll() {
        shieldRepository.removeAll(Collections.emptyList());
        shieldRepository.saveAll(shields);
        shieldRepository.removeAll(shields);
        final Collection<Shield> all = shieldRepository.findAll();
        assertEquals(0, all.size());
        for (Shield shield : shields) {
            final Optional<Shield> find = shieldRepository.find(shield.identity());
            assertFalse(find.isPresent());
        }
    }
}