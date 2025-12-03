package com.rdlts.enigma.ddd.spring;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class EnigmaDomainEventRepositoryTest {

    @Test
    void testFind() {
        EnigmaDomainEventRepository enigmaDomainEventRepository = new EnigmaDomainEventRepository();
        Assertions.assertThrows(UnsupportedOperationException.class, () -> enigmaDomainEventRepository.find(null));
    }

}