package io.github.redlotus794.enigma.ddd.spring.event.autolog;

import io.github.redlotus794.enigma.ddd.core.Entity;
import io.github.redlotus794.enigma.ddd.core.DomainRepository;
import io.github.redlotus794.enigma.ddd.core.event.DomainEventParam;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import org.jspecify.annotations.NonNull;
import java.util.Collection;
import java.util.Objects;

/**
 * DomainSavedAllEventParam
 *
 * @author wangjialong
 * @since 2025/12/17 15:59
 */
@Getter
@EqualsAndHashCode
@SuppressWarnings("rawtypes")
public class DomainSavedAllEventParam implements DomainEventParam {

    @NonNull
    Collection<Entity> domainEntities;

    @NonNull
    Class<? extends DomainRepository> domainRepository;

    public DomainSavedAllEventParam(@NonNull Collection<Entity> domainEntities,
                                    @NonNull Class<? extends DomainRepository> domainRepository) {
        this.domainEntities = Objects.requireNonNull(domainEntities);
        this.domainRepository = Objects.requireNonNull(domainRepository);
    }

    @Override
    public String toString() {
        return "DomainSavedAllEventParam{" +
                "domainEntity(num)=" + domainEntities.size() +
                ", domainRepository=" + domainRepository +
                '}';
    }
}
