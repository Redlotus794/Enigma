package io.github.redlotus794.enigma.ddd.core;

import io.github.redlotus794.enigma.ddd.core.exception.DomainAggregateRootNotFoundException;

import org.jspecify.annotations.NonNull;
import java.util.Collection;
import java.util.Optional;

/**
 * <a href="file:///Users/wangjialong/baidusync/domain-driven-design/html/资源库.html">
 * Domain Repository 领域资源库
 * </a>
 *
 * @param <DAR> 聚合根类型
 * @param <IdentityType> 聚合根主键类型
 * @author wangjialong
 * @since 2025/12/1 15:51
 */
@SuppressWarnings({"unused", "UnusedReturnValue"})
public interface DomainRepository<DAR extends AggregateRoot<IdentityType>, IdentityType> {

    /**
     * 自生成策略：返回下一个聚合根主键对象。
     * 如无自生成策略，则不需要实现该接口。
     *
     * @return IdentityType
     */
    default IdentityType nextIdentity() {
        throw new UnsupportedOperationException("Next Identity not implement!");
    }

    /**
     * 根据聚合根主键查找一个聚合根
     * @param identity IdentityType
     * @return Optional Domain Aggregate Root
     */
    Optional<DAR> find(IdentityType identity);

    /**
     * 根据聚合根主键查找一个聚合根，找不到则抛异常
     * @param identity IdentityType
     * @return Domain Aggregate Root
     */
    @NonNull
    default DAR findRequired(IdentityType identity) throws DomainAggregateRootNotFoundException {
        return find(identity)
                .orElseThrow(() -> new DomainAggregateRootNotFoundException(identity));
    }

    /**
     * 查找所有聚合根
     * @return Collection DAR
     */
    Collection<DAR> findAll();

    /**
     * 保存一个聚合根
     * @param aggregateRoot DAR
     */
    void save(DAR aggregateRoot);

    /**
     * 批量保存聚合根
     * @param aggregateRootCollection Collection DAR
     */
    void saveAll(Collection<DAR> aggregateRootCollection);

    /**
     * 删除一个聚合根
     * 是否是物理还是逻辑删除由底层架构实现，领域层不关心。
     * @param aggregateRoot DAR
     */
    void remove(DAR aggregateRoot);

    /**
     * 删除所有聚合根
     * @param aggregateRootCollection Collection DAR
     */
    void removeAll(Collection<DAR> aggregateRootCollection);
}
