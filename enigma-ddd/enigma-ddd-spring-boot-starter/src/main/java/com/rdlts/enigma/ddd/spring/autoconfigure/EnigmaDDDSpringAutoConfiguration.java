package com.rdlts.enigma.ddd.spring.autoconfigure;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
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
@ConditionalOnProperty(
        value="enigma.spring.enabled",
        havingValue = "true",
        matchIfMissing = true
)
public class EnigmaDDDSpringAutoConfiguration {

    public EnigmaDDDSpringAutoConfiguration() {
    }
}
