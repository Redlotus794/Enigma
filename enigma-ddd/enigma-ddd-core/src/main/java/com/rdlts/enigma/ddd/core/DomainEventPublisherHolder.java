package com.rdlts.enigma.ddd.core;

import java.util.ServiceLoader;
import java.util.logging.Logger;

/**
 * DomainEventPublisherHolder
 * 领域事件发布器实例持有者
 *
 * @author wangjialong
 * @since 2025/12/3 09:02
 */
public class DomainEventPublisherHolder {

    /**
     * 日志
     */
    private static final Logger logger = Logger.getLogger(DomainEventPublisherHolder.class.getName());

    /**
     * 单例实例，使用 ServiceLoader 加载 DomainEventPublisher 的实现。
     */
    public static final DomainEventPublisher INSTANCE = load();

    /**
     * 加载 DomainServiceRegistry 的单例实例。
     * @return DomainServiceRegistry 实例
     */
    protected static DomainEventPublisher load() {
        ServiceLoader<DomainEventPublisher> loader = ServiceLoader.load(DomainEventPublisher.class);
        for (DomainEventPublisher registry: loader) {
            logger.info("找到DomainEventPublisher: " + registry.getClass().getName());
            return registry.getInstance();
        }
        throw new IllegalStateException("未找到 DomainEventPublisher 的实现类，请确保已正确配置服务提供者接口 (SPI)。");
    }

}
