package com.rdlts.enigma.ddd.core;

/**
 * DomainEventPublisher - 事件源发布
 * 事件源发布器，用于发布领域事件，处理领域驱动设计中的事件分发机制。
 * 不同限界上下文之间应该通过领域事件来进行通信和协作，以保持各自的独立性和一致性。
 * <p>
 * 参考文档：
 * <a href="https://wcnn2j4xsnan.feishu.cn/wiki/Z67Ww1yZSiZL8okXgAkc4IsdnPf">
 *     事件驱动设计 - Event-Driven Design
 * </a>
 * </p>
 * @author wangjialong
 * @since 2025/12/3 09:00
 */
public interface DomainEventPublisher {

    /**
     * 静态方法使用
     * @return DomainEventPublisher
     */
    static DomainEventPublisher instance() {
        return DomainEventPublisherHolder.INSTANCE;
    }

    /**
     * 发布事件
     * @param domainEvent AbstractDomainEvent
     * @param <T> DomainEventParam
     */
    <T extends DomainEventParam> void publish(DomainEvent<T> domainEvent);

    /**
     * 内部instance的实现
     * 普通java可以new一个类
     * Spring的实现可以使用component
     * @return DomainEventPublisher
     */
    DomainEventPublisher getInstance();
}
