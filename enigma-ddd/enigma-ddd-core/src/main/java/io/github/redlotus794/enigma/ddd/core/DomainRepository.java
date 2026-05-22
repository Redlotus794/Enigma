package io.github.redlotus794.enigma.ddd.core;

import io.github.redlotus794.enigma.ddd.core.exception.DomainEntityNotFoundException;

import org.jspecify.annotations.NonNull;
import java.util.Collection;
import java.util.Optional;

/**
 * <a href="docs/domain-driven-design/资源库.md">
 * Domain Repository 领域资源库
 * </a>
 *
 * @param <DA> 聚合类型
 * @param <DAR> 聚合根类型
 * @param <IdentityType> 聚合根主键类型
 * @author wangjialong
 * @since 2025/12/1 15:51
 */
@SuppressWarnings("unused")
public interface DomainRepository<
        DA extends DomainAggregate<DAR>,
        DAR extends DomainAggregateRoot<IdentityType>,
        IdentityType> {

    /**
     * 根据聚合根主键查找一个聚合
     * @param identity IdentityType
     * @return Optional Domain Aggregate
     */
    Optional<DA> find(IdentityType identity);

    /**
     * 根据聚合根主键查找一个聚合，找不到则抛异常
     * @param identity IdentityType
     * @return Domain Aggregate
     */
    @NonNull
    default DA findRequired(IdentityType identity) throws DomainEntityNotFoundException {
        return find(identity)
                .orElseThrow(() -> new DomainEntityNotFoundException(identity));
    }

    /**
     * 查找所有仓库聚合
     * @return Collection DA
     */
    Collection<DA> findAll();

    /**
     * 保存一个聚合
     * @param aggregate DA
     */
    void save(DA aggregate);

    /**
     * 批量保存聚合
     * @param aggregateCollection Collection DA
     */
    void saveAll(Collection<DA> aggregateCollection);

    /**
     * 删除一个聚合
     * 是否是物理还是逻辑删除由底层架构实现，领域层不关心。
     * @param aggregate DA
     */
    void remove(DA aggregate);

    /**
     * 删除所有聚合
     * @param aggregateCollection Collection DA
     */
    void removeAll(Collection<DA> aggregateCollection);
}
