package com.rdlts.enigma.ddd.spring.autoconfigure;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import static com.rdlts.enigma.ddd.spring.autoconfigure.EnigmaDDDSpringAutoConfiguration.ENIGMA_SPRING_ENABLED;

/**
 * EnigmaDDDSpringAutoConfiguration
 *
 * @author wangjialong
 * @since 2025/12/3 08:42
 */
@Configuration
@ComponentScan(basePackages = "com.rdlts.enigma.ddd.spring")
@ConditionalOnProperty(
        value=ENIGMA_SPRING_ENABLED,
        havingValue = "true",
        matchIfMissing = true
)
public class EnigmaDDDSpringAutoConfiguration {

    public static final String ENIGMA_SPRING_ENABLED = "enigma.spring.enabled";

    public EnigmaDDDSpringAutoConfiguration() {
    }
}
