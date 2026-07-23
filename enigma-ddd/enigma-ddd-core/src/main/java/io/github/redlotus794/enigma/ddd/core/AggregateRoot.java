package io.github.redlotus794.enigma.ddd.core;

/**
 * <a href="file:///Users/wangjialong/baidusync/domain-driven-design/html/聚合根.html">
 *  聚合根 - Domain Aggregate Root
 * </a>
 * 聚合根首先是一个实体，同时代表整个聚合对外暴露，
 * 负责作为聚合内部对象的管理入口并保护聚合内部一致性规则。
 *
 * @param <IdentityType> 聚合根唯一标识
 * @author wangjialong
 * @since 2025/12/01 15:11
 */
public interface AggregateRoot<IdentityType> extends Entity<IdentityType> {
}
