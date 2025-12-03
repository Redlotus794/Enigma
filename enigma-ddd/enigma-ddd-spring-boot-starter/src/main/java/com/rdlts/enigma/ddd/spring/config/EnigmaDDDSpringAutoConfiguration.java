package com.rdlts.enigma.ddd.spring.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * EnigmaDDDSpringAutoConfiguration
 *
 * @author wangjialong
 * @since 2025/12/3 08:42
 */
@Configuration
@ComponentScan(basePackages = "com.rdlts.enigma.ddd.spring")
public class EnigmaDDDSpringAutoConfiguration {

    public EnigmaDDDSpringAutoConfiguration() {
    }
}
