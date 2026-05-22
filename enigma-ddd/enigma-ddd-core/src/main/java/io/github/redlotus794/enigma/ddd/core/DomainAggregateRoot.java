package io.github.redlotus794.enigma.ddd.core;

/**
 * <a href="file:///Users/wangjialong/baidusync/domain-driven-design/html/聚合根.html">
 *  聚合根 - Domain Aggregate Root
 * </a>
 *
 * @param <IdentityType> 聚合根唯一标识
 * @author wangjialong
 * @since 2025/12/01 15:11
 */
public interface DomainAggregateRoot<IdentityType> {

    /**
     * 自生成策略：返回下一个聚合根主键对象。
     * 如无自生成策略，则不需要实现该接口。
     *
     * @return IdentityType
     */
    default IdentityType nextIdentity() {
        throw new UnsupportedOperationException("Next Identity not implement!");
    }
}
