package com.rdlts.enigma.ddd.spring.service;

import com.rdlts.enigma.ddd.core.DomainService;
import com.rdlts.enigma.ddd.core.DomainServiceRegistry;
import org.springframework.beans.BeansException;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import javax.annotation.Nonnull;
import java.util.Objects;

/**
 * 领域服务注册表的 Spring 实现。
 * <p>
 * 通过容器初始化，发现并注册所有{@link DomainService}
 * 当需要在非spring的实例中调用时，可以通过{@link DomainServiceRegistry#instance()} 获取实例。
 * </p>
 *
 * e.g.
 * <pre>{@code
 *  DomainServiceRegistry.instance()
 *  .findService(TestDomainService.class)
 *  .testMethod();
 * }
 * </pre>
 *
 * @see DomainService
 * @see DomainServiceRegistry
 * @author wangjialong
 * @since 2025/12/2 14:41
 */
@Component
@ConditionalOnMissingBean(DomainServiceRegistry.class)
public class EnigmaDomainServiceRegistry implements DomainServiceRegistry, ApplicationContextAware {

    /**
     * Spring注入实例. 单例
     */
    private static EnigmaDomainServiceRegistry INSTANCE;

    /**
     * Spring的上下文
     */
    private ApplicationContext applicationContext;

    /**
     * 默认构造器
     */
    public EnigmaDomainServiceRegistry() {

    }

    /**
     * 提供Instance注册到DomainServiceRegistry
     * @see com.rdlts.enigma.ddd.core.DomainServiceRegistryHolder
     * @return DomainServiceRegistry
     */
    @Override
    public DomainServiceRegistry getInstance() {
        // Spring的注入优先于Service loader的发现。
        return EnigmaDomainServiceRegistry.INSTANCE;
    }

    /**
     * findService
     * @param clazz Class
     * @return T Domain Service
     * @param <T> 领域服务类型
     * @exception BeansException 未找到spring bean
     */
    @Override
    @Nonnull
    public <T extends DomainService> T findService(Class<T> clazz) {
        Objects.requireNonNull(applicationContext, "Application Context is null!");
        return applicationContext.getBean(clazz);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
        EnigmaDomainServiceRegistry.INSTANCE = applicationContext.getBean(EnigmaDomainServiceRegistry.class);
    }
}
