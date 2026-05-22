package io.github.redlotus794.enigma.random.autoconfigure;

import io.github.redlotus794.enigma.random.domain.EnigmaRandomGenerator;
import io.github.redlotus794.enigma.random.infrastructure.EasyRandomGenerator;
import io.github.redlotus794.enigma.random.infrastructure.EasyRandomizerRegistryContextImpl;
import org.jeasy.random.EasyRandom;
import org.jeasy.random.EasyRandomParameters;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.nio.charset.StandardCharsets;

/**
 * EasyRandomAutoConfiguration
 *
 * @see <a href="https://github.com/j-easy/easy-random/wiki/Randomization-parameters">Randomization Parameters</a>
 * @author wangjialong
 * @since 2025/12/10 10:50
 */
@Configuration
@ConditionalOnClass(EasyRandom.class)
public class EasyRandomAutoConfiguration {

    /**
     * 默认使用Easy Random的随机生成方式
     * @return EnigmaRandomGenerator
     */
    @Bean
    @ConditionalOnMissingBean(EnigmaRandomGenerator.class)
    public EnigmaRandomGenerator enigmaRandomGenerator(EasyRandomParameters easyRandomParameters) {
        return new EasyRandomGenerator(easyRandomParameters);
    }

    /**
     * easyRandomParameters
     * @see <a href="https://github.com/j-easy/easy-random/wiki/Randomization-parameters">Randomization Parameters</a>
     * @param easyRandomizerRegistryContext EasyRandomizerRegistryContextImpl
     * @return EasyRandomParameters
     */
    @Bean
    @ConditionalOnMissingBean(EasyRandomParameters.class)
    public EasyRandomParameters easyRandomParameters(EasyRandomizerRegistryContextImpl easyRandomizerRegistryContext) {
        EasyRandomParameters easyRandomParameters = new EasyRandomParameters();
        easyRandomizerRegistryContext.randomizerRegistries().forEach(easyRandomParameters::randomizerRegistry);
        easyRandomParameters.setSeed(123L);
        easyRandomParameters.objectPoolSize(100);
        easyRandomParameters.charset(StandardCharsets.UTF_8);
        easyRandomParameters.stringLengthRange(5, 50);
        easyRandomParameters.collectionSizeRange(1, 10);
        easyRandomParameters.setScanClasspathForConcreteTypes(true);
        easyRandomParameters.setOverrideDefaultInitialization(false);
        easyRandomParameters.setIgnoreRandomizationErrors(true);
        easyRandomParameters.setBypassSetters(true);
        return easyRandomParameters;
    }
}
