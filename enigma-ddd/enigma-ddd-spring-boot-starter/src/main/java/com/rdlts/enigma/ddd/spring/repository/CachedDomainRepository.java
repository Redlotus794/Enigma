package com.rdlts.enigma.ddd.spring.repository;

import com.rdlts.enigma.ddd.core.DomainEntity;
import com.rdlts.enigma.ddd.core.DomainRepository;

/**
 * <h1>CachedDomainRepository - 领域内存缓存仓库</h1>
 *
 * <h2>简介</h2>
 * 通过缓存组件来实现领域资源库的缓存功能，提高查询效率。
 *
 * <h2>技术实现</h2>
 * Java Map，Caffeine，Redis。
 * 单体应用使用，Java Map或者Caffeine，分布式使用Redis
 *
 * @author wangjialong
 * @since 2025/12/16 10:01
 */
public interface CachedDomainRepository<DE extends DomainEntity<IdentityType>, IdentityType>
        extends DomainRepository<DE, IdentityType> {

}
