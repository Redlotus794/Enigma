package com.rdlts.enigma.ddd.spring.repository;

import com.rdlts.enigma.ddd.core.DomainEntity;
import com.rdlts.enigma.ddd.core.DomainRepository;

/**
 * DomainMemoryCachedRepository
 * 领域内存缓存仓库
 *
 * @author wangjialong
 * @since 2025/12/16 10:01
 */
public interface DomainMemoryCachedRepository<DE extends DomainEntity<IdentityType>, IdentityType>
        extends DomainRepository<DE, IdentityType> {

}
