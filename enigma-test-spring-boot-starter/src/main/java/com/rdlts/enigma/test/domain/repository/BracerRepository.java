package com.rdlts.enigma.test.domain.repository;

import com.rdlts.enigma.ddd.core.DomainRepository;
import com.rdlts.enigma.test.domain.entity.Bracer;
import com.rdlts.enigma.test.domain.valueobject.BracerId;

/**
 * BracerRepository
 *
 * @author wangjialong
 * @since 2025/12/18 15:31
 */
public interface BracerRepository extends DomainRepository<Bracer, BracerId> {
}
