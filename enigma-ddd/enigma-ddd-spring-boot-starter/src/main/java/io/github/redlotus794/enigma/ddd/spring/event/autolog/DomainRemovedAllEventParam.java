package io.github.redlotus794.enigma.ddd.spring.event.autolog;

import io.github.redlotus794.enigma.ddd.core.DomainEntity;
import io.github.redlotus794.enigma.ddd.core.DomainRepository;
import io.github.redlotus794.enigma.ddd.core.event.DomainEventParam;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import org.jspecify.annotations.NonNull;
import java.util.Collection;
import java.util.Objects;

/**
 * DomainRemovedAllEventParam
 *
 * @author wangjialong
 * @since 2025/12/18 09:02
 */
@Getter
@EqualsAndHashCode
@SuppressWarnings("rawtypes")
public class DomainRemovedAllEventParam implements DomainEventParam {

    @NonNull
    Collection<DomainEntity> domainEntities;

    @NonNull
    Class<? extends DomainRepository> domainRepository;

    public DomainRemovedAllEventParam(@NonNull Collection<DomainEntity> domainEntities,
                                      @NonNull Class<? extends DomainRepository> domainRepository) {
        this.domainEntities = domainEntities;
        this.domainRepository = Objects.requireNonNull(domainRepository);
    }

    @Override
    public String toString() {
        return "DomainRemovedAllEventParam{" +
                "domainEntities(num)=" + domainEntities.size() +
                ", domainRepository=" + domainRepository +
                '}';
    }
}
