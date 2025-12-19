package com.rdlts.enigma.ddd.core;

import com.rdlts.enigma.ddd.core.exception.DomainEntityNotFoundException;

import javax.annotation.Nonnull;
import java.util.Collection;
import java.util.Optional;

/**
 * <a href="https://wcnn2j4xsnan.feishu.cn/wiki/Np1qwDKAAivbULkkmHTcZptqnBf">
 * Domain Repository 领域资源库
 * </a>
 *
 * @param <DE> 聚合根/实体
 * @param <IdentityType> 实体主键类型
 * @author wangjialong
 * @since 2025/12/1 15:51
 */
@SuppressWarnings("unused")
public interface DomainRepository<DE extends DomainEntity<IdentityType>, IdentityType> {

    /**
     * 自生成策略 ：返回下一个实体主键对象
     * 如无 则不需要实现该接口
     *
     * @return IdentityType
     */
    default IdentityType nextIdentity() {
        throw new UnsupportedOperationException("Next Identity not implement!");
    }

    /**
     * 根据实体主键 查找一个实体
     * @param identity IdentityType
     * @return Optional Domain Entity
     */
    Optional<DE> find(IdentityType identity);

    /**
     * 根据实体主键 查找一个实体，找不到则抛异常
     * @param identity IdentityType
     * @return Domain Entity
     */
    @Nonnull
    default DE findRequired(IdentityType identity) throws DomainEntityNotFoundException {
        return find(identity)
                .orElseThrow(() -> new DomainEntityNotFoundException(identity));
    }

    /**
     * 查找所有仓库实体
     * @return Collection DE
     */
    Collection<DE> findAll();

    /**
     * 保存一个实体
     * @param entity DE
     */
    void save(DE entity);

    /**
     * 批量保存实体
     * @param entityCollection Collection DE
     */
    void saveAll(Collection<DE> entityCollection);

    /**
     * 删除一个实体
     * 是否是物理还是逻辑删除由底层架构实现，领域层不关心。
     * @param entity DE
     */
    void remove(DE entity);

    /**
     * 删除所有实体
     * @param entityCollection Collection DE
     */
    void removeAll(Collection<DE> entityCollection);
}