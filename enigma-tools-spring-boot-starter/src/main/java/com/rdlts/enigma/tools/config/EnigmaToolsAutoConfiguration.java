package com.rdlts.enigma.tools.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * EnigmaToolsAutoConfiguration
 * @author wangjialong
 * @since 2026/1/6 15:56
 */
@Configuration
@ComponentScan(basePackages = "com.rdlts.enigma.tools")
public class EnigmaToolsAutoConfiguration {

    EnigmaToolsAutoConfiguration() {
    }
}
