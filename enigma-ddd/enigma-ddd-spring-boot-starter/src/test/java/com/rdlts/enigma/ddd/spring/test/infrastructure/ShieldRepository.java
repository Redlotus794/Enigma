package com.rdlts.enigma.ddd.spring.test.infrastructure;

import com.rdlts.enigma.ddd.spring.repository.InMemoryDomainRepository;
import com.rdlts.enigma.ddd.spring.test.domain.Shield;
import com.rdlts.enigma.ddd.spring.test.domain.ShieldId;
import org.springframework.stereotype.Component;

import java.util.UUID;

/**
 * ShieldRepository
 *
 * @author wangjialong
 * @since 2025/12/17 11:31
 */
@Component
public class ShieldRepository extends InMemoryDomainRepository<Shield, ShieldId> {

    @Override
    public ShieldId nextIdentity() {
        return ShieldId.of(UUID.randomUUID().toString());
    }

    public synchronized void clearAll() {
        super.store.clear();
    }
}
