package io.github.redlotus794.enigma.random.autoconfigure;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * EnigmaRandomAutoConfiguration
 *
 * @author wangjialong
 * @since 2025/12/9 10:40
 */
@Configuration
@ComponentScan(basePackages = "io.github.redlotus794.enigma.random")
@ConditionalOnProperty(name = "enigma.random.enabled", havingValue = "true", matchIfMissing = true)
public class EnigmaRandomAutoConfiguration {

}
