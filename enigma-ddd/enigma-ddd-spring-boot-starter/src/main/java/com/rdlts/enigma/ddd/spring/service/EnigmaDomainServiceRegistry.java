package com.rdlts.enigma.ddd.spring.service;

import com.rdlts.enigma.ddd.core.DomainService;
import com.rdlts.enigma.ddd.core.DomainServiceRegistry;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;

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
public class EnigmaDomainServiceRegistry implements DomainServiceRegistry, InitializingBean {

    /**
     * Spring注入实例. 单例
     */
    protected static DomainServiceRegistry SINGLETON_INSTANCE;

    /**
     * Spring的上下文
     */
    private ApplicationContext applicationContext;

    /**
     * 默认构造器
     */
    public EnigmaDomainServiceRegistry() {
    }

    public EnigmaDomainServiceRegistry(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    /**
     * 提供Instance注册到DomainServiceRegistry
     * @see com.rdlts.enigma.ddd.core.DomainServiceRegistryHolder
     * @return DomainServiceRegistry
     */
    @Override
    public DomainServiceRegistry getInstance() {
        // Spring的注入优先于Service loader的发现。
        return EnigmaDomainServiceRegistry.SINGLETON_INSTANCE;
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
    public void afterPropertiesSet() throws Exception {
        EnigmaDomainServiceRegistry.SINGLETON_INSTANCE = this;
    }
}
