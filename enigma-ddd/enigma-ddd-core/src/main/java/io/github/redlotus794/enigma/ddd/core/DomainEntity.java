package io.github.redlotus794.enigma.ddd.core;

import io.github.redlotus794.enigma.ddd.core.event.DomainEventParam;

import org.jspecify.annotations.NonNull;

/**
 * <a href="file:///Users/wangjialong/baidusync/domain-driven-design/html/实体.html">
 *     领域实体 - Domain Entity
 * </a>
 *
 * @author wangjialong
 * @since 2025/11/27 14:31
 */
public interface DomainEntity<PKType> extends DomainAggregateRoot<PKType>, DomainEventParam {

    /**
     * 实体唯一主键
     * 通常为资源库生成的随机主键编号，尽量与业务主键分离
     *
     * @return PKType @NonNull
     */
    @NonNull
    PKType identity();

    /**
     * 实体版本号，控制更新
     *
     * @see EntityVersion
     * @return EntityVersion
     * @throws UnsupportedOperationException 默认抛出异常，表示未实现版本控制
     */
    @NonNull
    default EntityVersion version() {
        throw new UnsupportedOperationException("version control not implement!");
    }
}
