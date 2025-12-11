package com.rdlts.enigma.random.autoconfigure;

import com.rdlts.enigma.random.domain.EnigmaRandomGenerator;
import com.rdlts.enigma.random.infrastructure.EasyRandomGenerator;
import org.jeasy.random.EasyRandomParameters;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * EnigmaRandomAutoConfiguration
 *
 * @author wangjialong
 * @since 2025/12/9 10:40
 */
@Configuration
public class EnigmaRandomAutoConfiguration {

    /**
     * 默认使用Easy Random的随机生成方式
     * @return EnigmaRandomGenerator
     */
    @Bean
    @ConditionalOnMissingBean(EnigmaRandomGenerator.class)
    public EnigmaRandomGenerator enigmaRandomGenerator(EasyRandomParameters easyRandomParameters) {
        return new EasyRandomGenerator(easyRandomParameters);
    }
}
