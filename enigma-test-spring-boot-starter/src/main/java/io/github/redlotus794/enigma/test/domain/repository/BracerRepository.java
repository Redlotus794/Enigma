package io.github.redlotus794.enigma.test.domain.repository;

import io.github.redlotus794.enigma.ddd.core.DomainRepository;
import io.github.redlotus794.enigma.test.domain.entity.Bracer;
import io.github.redlotus794.enigma.test.domain.valueobject.BracerId;

/**
 * BracerRepository
 *
 * @author wangjialong
 * @since 2025/12/18 15:31
 */
public interface BracerRepository extends DomainRepository<Bracer, Bracer, BracerId> {
}
