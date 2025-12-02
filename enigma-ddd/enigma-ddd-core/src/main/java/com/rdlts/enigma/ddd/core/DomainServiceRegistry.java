package com.rdlts.enigma.ddd.core;

/**
 * DomainServiceRegistry 是一个标记接口，用于在 DDD（领域驱动设计）核心模块中注册和发现领域服务。
 * 实现该接口的类可以用于管理领域服务的生命周期和查找，促进服务的解耦。
 * <p>
 * 典型用法：为领域服务提供注册表，支持在运行时注入或发现服务，便于服务消费者与提供者的解耦。
 * </p>
 *
 * @author wangjialong
 * @since 2025/12/1 16:11
 */
@SuppressWarnings("all")
public interface DomainServiceRegistry {

    /**
     * instance - 获取领域服务注册表实例
     * @return DomainServiceRegistry
     */
    static DomainServiceRegistry instance() {
        return DomainServiceRegistryHolder.INSTANCE;
    }

    /**
     * getInstance - 获取领域服务注册表实例
     *
     * @return DomainServiceRegistry
     */
    DomainServiceRegistry getInstance();

    /**
     * findService - 获取领域服务
     *
     * @param clazz Class
     * @return DomainService
     */
    <T extends DomainService> T findService(Class<T> clazz);
}
