package com.rdlts.enigma.ddd.spring.autoconfigure;

import com.rdlts.enigma.ddd.core.DomainEventPublisher;
import com.rdlts.enigma.ddd.core.DomainEventRepository;
import com.rdlts.enigma.ddd.core.DomainServiceRegistry;
import com.rdlts.enigma.ddd.spring.event.EnigmaDomainEventRepository;
import com.rdlts.enigma.ddd.spring.event.EnigmaSpringDomainEventPublisher;
import com.rdlts.enigma.ddd.spring.service.EnigmaDomainServiceRegistry;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * EnigmaDDDAutoConfiguration
 *
 * @author wangjialong
 * @since 2025/12/4 13:40
 */
@Configuration
public class EnigmaSpringAutoConfiguration {

    @Bean
    @ConditionalOnMissingBean(DomainEventPublisher.class)
    public DomainEventPublisher domainEventPublisher(
            DomainEventRepository domainEventRepository,
            ApplicationEventPublisher applicationEventPublisher) {
        return new EnigmaSpringDomainEventPublisher(applicationEventPublisher, domainEventRepository);
    }

    @Bean
    @ConditionalOnMissingBean(DomainEventRepository.class)
    public DomainEventRepository domainEventRepository() {
        return new EnigmaDomainEventRepository();
    }

    @Bean
    @ConditionalOnMissingBean(DomainServiceRegistry.class)
    public DomainServiceRegistry domainServiceRegistry(ApplicationContext applicationContext) {
        return new EnigmaDomainServiceRegistry(applicationContext);
    }
}

