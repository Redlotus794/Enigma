package com.rdlts.enigma.tools.spring;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;

import javax.annotation.Nonnull;

/**
 * <h1>EnigmaSpringContextUtils</h1>
 * Spring上下文工具，Spring boot启动时加载。
 * <h2>功能列表</h2>
 * <ul>
 *     <li>Spring ApplicationContext 获取</li>
 *     <li>Spring 加载bean</li>
 *     <li>Spring 卸载bean</li>
 * </ul>
 *
 * <h2>示例代码：</h2>s
 * {@code
 *   EnigmaSpringContextUtils.instance().getApplicationContext();
 * }
 *
 * @author wangjialong
 * @since 2025/12/2 15:04
 */
@Component
public class EnigmaSpringContextUtils implements ApplicationContextAware {

    /**
     * 单例对象，Spring Context注入
     */
    private static EnigmaSpringContextUtils instance;

    /**
     * Spring ApplicationContext
     */
    private ApplicationContext applicationContext;

    /**
     * 单例对象获取
     * @return EnigmaSpringContextUtils
     */
    public static EnigmaSpringContextUtils instance() {
        return instance;
    }

    /**
     * 获取Spring上下文
     * @see ApplicationContext
     * @return ApplicationContext
     */
    public ApplicationContext getApplicationContext() {
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
    public void setApplicationContext(@Nonnull ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
        EnigmaSpringContextUtils.instance = applicationContext.getBean(EnigmaSpringContextUtils.class);
    }
}
