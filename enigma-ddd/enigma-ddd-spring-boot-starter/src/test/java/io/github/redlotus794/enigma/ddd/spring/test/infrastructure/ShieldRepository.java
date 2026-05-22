package io.github.redlotus794.enigma.ddd.spring.test.infrastructure;

import io.github.redlotus794.enigma.ddd.spring.repository.InMemoryDomainRepository;
import io.github.redlotus794.enigma.ddd.spring.test.domain.Shield;
import io.github.redlotus794.enigma.ddd.spring.test.domain.ShieldId;
import org.springframework.stereotype.Component;

/**
 * ShieldRepository
 *
 * @author wangjialong
 * @since 2025/12/17 11:31
 */
@Component
public class ShieldRepository extends InMemoryDomainRepository<Shield, Shield, ShieldId> {

    public synchronized void clearAll() {
        super.store.clear();
    }
}
