package com.rdlts.enigma.ddd.spring.config;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EnigmaDDDSpringAutoConfigurationTest {


    @Test
    void testConfigInit() {
        EnigmaDDDSpringAutoConfiguration enigmaDDDSpringAutoConfiguration = new EnigmaDDDSpringAutoConfiguration();
        assertNotNull(enigmaDDDSpringAutoConfiguration);
    }

}