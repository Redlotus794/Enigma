package io.github.redlotus794.enigma.ddd.spring.autoconfigure;

import io.github.redlotus794.enigma.ddd.core.event.DomainEventPublisher;
import io.github.redlotus794.enigma.ddd.core.event.DomainEventRepository;
import io.github.redlotus794.enigma.ddd.core.service.DomainServiceRegistry;
import io.github.redlotus794.enigma.ddd.spring.event.DefaultTransactionalEventProxy;
import io.github.redlotus794.enigma.ddd.spring.event.EnigmaDomainEventRepository;
import io.github.redlotus794.enigma.ddd.spring.event.EnigmaSpringDomainEventPublisher;
import io.github.redlotus794.enigma.ddd.spring.event.RealTimeExecutionEventListener;
import io.github.redlotus794.enigma.ddd.spring.event.TransactionalEventProxy;
import io.github.redlotus794.enigma.ddd.spring.service.EnigmaDomainServiceRegistry;
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
public class EnigmaDomainComponentAutoConfiguration {

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

    @Bean
    @ConditionalOnMissingBean(TransactionalEventProxy.class)
    public TransactionalEventProxy transactionalEventProxy() {
        return DefaultTransactionalEventProxy.INSTANCE;
    }

    @Bean
    @ConditionalOnMissingBean(RealTimeExecutionEventListener.class)
    public RealTimeExecutionEventListener realTimeExecutionEventListener(TransactionalEventProxy proxy) {
        return new RealTimeExecutionEventListener(proxy);
    }
}
