package io.github.redlotus794.enigma.ddd.core;

import org.jspecify.annotations.NonNull;

/**
 * <a href="file:///Users/wangjialong/baidusync/domain-driven-design/html/单实体聚合.html">
 *     单实体聚合 - Domain Single Entity Aggregate
 * </a>
 * 一个领域实体自身即为聚合根时使用的聚合契约。
 *
 * @param <SELF> 单实体聚合自身类型
 * @param <PKType> 实体主键类型
 * @author wangjialong
 * @since 2026/05/22 10:12
 */
public interface DomainSingleEntityAggregate<SELF extends DomainSingleEntityAggregate<SELF, PKType>, PKType>
        extends DomainEntity<PKType>, DomainAggregate<SELF> {

    /**
     * 聚合根对象，单实体聚合中聚合根即为实体自身。
     *
     * @return 当前实体自身
     */
    @NonNull
    @Override
    @SuppressWarnings("unchecked")
    default SELF root() {
        return (SELF) this;
    }
}
