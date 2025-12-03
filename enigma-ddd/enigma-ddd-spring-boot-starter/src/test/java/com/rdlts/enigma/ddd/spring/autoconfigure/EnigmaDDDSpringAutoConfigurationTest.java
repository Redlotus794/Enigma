package com.rdlts.enigma.ddd.spring.autoconfigure;

import com.rdlts.enigma.ddd.core.DomainEventPublisher;
import com.rdlts.enigma.ddd.core.DomainEventRepository;
import com.rdlts.enigma.ddd.spring.test.EnigmaDDDSpringTestApplication;
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
        classes = EnigmaDDDSpringTestApplication.class)
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