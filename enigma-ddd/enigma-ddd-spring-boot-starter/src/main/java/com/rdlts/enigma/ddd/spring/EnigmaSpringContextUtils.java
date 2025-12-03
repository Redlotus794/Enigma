package com.rdlts.enigma.ddd.spring;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;

/**
 * DDDSpringContextUtils
 *
 * @author wangjialong
 * @since 2025/12/2 15:04
 */
@Component
public class EnigmaSpringContextUtils implements ApplicationContextAware {

    static EnigmaSpringContextUtils instance;

    ApplicationContext applicationContext;

    public static EnigmaSpringContextUtils instance() {
        return instance;
    }

    public ApplicationContext applicationContext() {
        return this.applicationContext;
    }

    /**
     * 加载/替换一个Spring Bean
     * @param name Bean名称
     * @param clazz Bean类
     */
    public void loadBean(String name, Class<?> clazz) {
        ConfigurableApplicationContext context = (ConfigurableApplicationContext) this.applicationContext;
        DefaultListableBeanFactory beanFactory = (DefaultListableBeanFactory) context.getBeanFactory();
        final BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder.genericBeanDefinition(clazz);
        beanDefinitionBuilder.addPropertyValue("name", name);
        beanFactory.registerBeanDefinition(name, beanDefinitionBuilder.getRawBeanDefinition());
    }

    /**
     * 移除一个Spring Bean
     * @param beanName String
     */
    public void unloadBean(String beanName) {
        ConfigurableApplicationContext context = (ConfigurableApplicationContext) this.applicationContext;
        DefaultListableBeanFactory beanFactory = (DefaultListableBeanFactory) context.getBeanFactory();
        beanFactory.removeBeanDefinition(beanName);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
        EnigmaSpringContextUtils.instance = applicationContext.getBean(EnigmaSpringContextUtils.class);
    }
}
