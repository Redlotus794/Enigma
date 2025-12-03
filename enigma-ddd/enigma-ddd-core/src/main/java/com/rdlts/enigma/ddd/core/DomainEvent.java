package com.rdlts.enigma.ddd.core;

import javax.annotation.Nonnull;

/**
 * <a href="https://wcnn2j4xsnan.feishu.cn/wiki/XQ3lwTTBLiazVQkfmKNcmdCanmg">
 *  DomainEvent - 领域事件
 * </a>
 * @author wangjialong
 * @since 2025/12/2 15:41
 */
public abstract class DomainEvent<T extends DomainEventParam> {

    /**
     * 事件唯一标识
     *
     * @see DomainEventUUID
     */
    protected DomainEventUUID eventUuid;

    /**
     * 事件内容
     *
     * @see DomainEventParam
     */
    protected T eventContent;

    /**
     * 构造器
     */
    protected DomainEvent() {
    }

    /**
     * 构造器
     * @param eventContent T
     */
    public DomainEvent(@Nonnull T eventContent) {
        this.eventUuid = DomainEventUUID.next();
        this.eventContent = eventContent;
    }

    public String domainEventName() {
        return this.getClass().getName();
    }
}
