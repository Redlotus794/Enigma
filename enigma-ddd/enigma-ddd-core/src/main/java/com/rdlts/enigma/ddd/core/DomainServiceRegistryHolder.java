package com.rdlts.enigma.ddd.core;

import java.util.ServiceLoader;
import java.util.logging.Logger;

/**
 * DomainServiceRegistryHolder
 * <p>
 * 用于持有和加载 DomainServiceRegistry 的单例实例。
 * 通过 ServiceLoader (resources/META-INF.services) 自动发现并加载实现类。
 * </p>
 *
 * @author wangjialong
 * @since 2025/12/1 16:11
 */
public class DomainServiceRegistryHolder {

    /**
     * 日志记录器，用于记录 DomainServiceRegistry 的加载信息。
     */
    private static final Logger logger = Logger.getLogger(DomainServiceRegistryHolder.class.getName());

    /**
     * 单例实例，使用 ServiceLoader 加载 DomainServiceRegistry 的实现。
     */
    protected static final DomainServiceRegistry INSTANCE = load();

    /**
     * 加载 DomainServiceRegistry 的单例实例。
     * @return DomainServiceRegistry 实例
     */
    protected static DomainServiceRegistry load() {
        ServiceLoader<DomainServiceRegistry> loader = ServiceLoader.load(DomainServiceRegistry.class);
        for (DomainServiceRegistry registry: loader) {
            logger.info("找到DomainServiceRegistry的实现: " + registry.getClass().getName());
            return registry.getInstance();
        }

        throw new IllegalStateException("未找到 DomainServiceRegistry 的实现类，请确保已正确配置服务提供者接口 (SPI)。");
    }
}
