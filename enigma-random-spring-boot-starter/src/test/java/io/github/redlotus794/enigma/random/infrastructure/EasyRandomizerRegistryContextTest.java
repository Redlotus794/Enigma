package io.github.redlotus794.enigma.random.infrastructure;

import io.github.redlotus794.enigma.EnigmaSpringBootBasedTest;
import io.github.redlotus794.enigma.random.domain.EnigmaRandomizerRegistry;
import io.github.redlotus794.enigma.random.test.easyrandom.EmployeeRandomizerRegistry;
import io.github.redlotus794.enigma.random.test.easyrandom.NonEasyRandomRegistry;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;

/**
 * EasyRandomizerRegistryContextTest
 *
 * @author wangjialong
 * @since 2025/12/10 14:35
 */
@Component
public class EasyRandomizerRegistryContextTest extends EnigmaSpringBootBasedTest {

    @Autowired(required = false)
    EasyRandomizerRegistryContextImpl easyRandomizerRegistryContext;

    @Autowired
    EmployeeRandomizerRegistry employeeRandomizerRegistry;

    @Autowired
    NonEasyRandomRegistry nonEasyRandomRegistry;

    @Test
    void testEnigmaRandomizerRegistries() {
        final Collection<EnigmaRandomizerRegistry> enigmaRandomizerRegistries = easyRandomizerRegistryContext.enigmaRandomizerRegistries();
        Assertions.assertTrue(enigmaRandomizerRegistries.contains(employeeRandomizerRegistry));
        Assertions.assertFalse(enigmaRandomizerRegistries.contains(nonEasyRandomRegistry));
    }
}
