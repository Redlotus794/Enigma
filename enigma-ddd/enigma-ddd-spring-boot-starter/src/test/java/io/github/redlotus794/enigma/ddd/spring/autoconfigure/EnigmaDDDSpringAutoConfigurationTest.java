package io.github.redlotus794.enigma.ddd.spring.autoconfigure;

import io.github.redlotus794.enigma.ddd.core.event.DomainEventPublisher;
import io.github.redlotus794.enigma.ddd.core.event.DomainEventRepository;
import io.github.redlotus794.enigma.ddd.spring.autoconfigure.bootstrap.ConfigurationApplication;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertNotNull;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
        classes = ConfigurationApplication.class)
@ExtendWith(SpringExtension.class)
@TestPropertySource(properties = "enigma.spring.enabled = false")
class EnigmaDDDSpringAutoConfigurationTest implements ApplicationContextAware {

    private static ApplicationContext applicationContext;

    @Test
    void testConfigInit() {
        EnigmaDDDSpringAutoConfiguration enigmaDDDSpringAutoConfiguration = new EnigmaDDDSpringAutoConfiguration();
        assertNotNull(enigmaDDDSpringAutoConfiguration);
    }

    @Test
    void testEnabledFalse() {
        Assertions.assertThrows(NoSuchBeanDefinitionException.class, () -> applicationContext.getBean(DomainEventRepository.class));
        Assertions.assertThrows(NoSuchBeanDefinitionException.class, () -> applicationContext.getBean(DomainEventPublisher.class));
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        EnigmaDDDSpringAutoConfigurationTest.applicationContext = applicationContext;
    }
}