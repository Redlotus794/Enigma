package io.github.redlotus794.enigma.ddd.core.event;

import lombok.Data;

import org.jspecify.annotations.NonNull;
import java.time.Instant;

/**
 * <a href="https://wcnn2j4xsnan.feishu.cn/wiki/XQ3lwTTBLiazVQkfmKNcmdCanmg">
 *  DomainEvent - 领域事件
 * </a>
 * @author wangjialong
 * @since 2025/12/2 15:41
 */
@Data
public abstract class DomainEvent<T extends DomainEventParam>
        implements DomainEventReproducible, DomainEventPersistable {

    /**
     * 事件唯一标识
     * @see DomainEventUUID
     */
    protected DomainEventUUID eventUuid;

    /**
     * 事件内容
     * @see DomainEventParam
     */
    protected T eventContent;

    /**
     * 事件发生时间
     */
    protected Instant eventTime;

    /**
     * 事件创建人
     */
    protected String createdBy;

    /**
     * 构造器
     */
    protected DomainEvent() {
    }

    /**
     * 构造器
     * @param eventContent T
     */
    public DomainEvent(@NonNull T eventContent) {
        this.eventUuid = DomainEventUUID.next();
        this.eventContent = eventContent;
        this.eventTime = Instant.now();
        this.createdBy = null;
    }

    /**
     * 构造器
     * @param eventContent T
     * @param eventTime 事件发生时间
     * @param createdBy 创建人
     */
    public DomainEvent(T eventContent, Instant eventTime, String createdBy) {
        this.eventUuid = DomainEventUUID.next();
        this.eventContent = eventContent;
        this.eventTime = eventTime;
        this.createdBy = createdBy;
    }

    public String domainEventName() {
        return this.getClass().getName();
    }
}
