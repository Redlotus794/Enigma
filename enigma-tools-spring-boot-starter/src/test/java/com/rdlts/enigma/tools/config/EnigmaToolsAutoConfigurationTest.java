package com.rdlts.enigma.tools.config;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * EnigmaToolsAutoConfiguration
 *
 * @author wangjialong
 * @since 2026/1/6 15:58
 */
public class EnigmaToolsAutoConfigurationTest {

    @Test
    public void testInit() {
        final EnigmaToolsAutoConfiguration enigmaToolsAutoConfiguration = new EnigmaToolsAutoConfiguration();
        Assertions.assertNotNull(enigmaToolsAutoConfiguration);
    }
}
