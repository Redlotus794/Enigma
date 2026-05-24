package io.github.redlotus794.enigma.ddd.spring.test.infrastructure;

import io.github.redlotus794.enigma.ddd.spring.event.autolog.RepositoryEventAutoLog;
import io.github.redlotus794.enigma.ddd.spring.repository.InMemoryDomainRepository;
import io.github.redlotus794.enigma.ddd.spring.test.domain.Shield;
import io.github.redlotus794.enigma.ddd.spring.test.domain.ShieldId;
import org.springframework.stereotype.Component;

/**
 * AutoLogRepository
 *
 * @author wangjialong
 * @since 2025/12/18 14:15
 */
@Component
@RepositoryEventAutoLog
public class AutoLogRepository extends InMemoryDomainRepository<Shield, ShieldId> {

    @Override
    public void save(Shield entity) {
        if (entity == null) {
            return;
        }
        super.save(entity);
    }

    @Override
    public void remove(Shield entity) {
        if (entity == null) {
            return;
        }
        super.remove(entity);
    }
}
