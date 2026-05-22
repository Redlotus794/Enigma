package io.github.redlotus794.enigma.ddd.spring.event.autolog;

import io.github.redlotus794.enigma.ddd.core.DomainEntity;
import io.github.redlotus794.enigma.ddd.core.DomainRepository;
import io.github.redlotus794.enigma.ddd.core.event.DomainEventParam;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import org.jspecify.annotations.NonNull;
import java.util.Objects;

/**
 * DomainRemovedEventParam
 *
 * @author wangjialong
 * @since 2025/12/18 08:25
 */
@Getter
@EqualsAndHashCode
@SuppressWarnings("rawtypes")
public class DomainRemovedEventParam implements DomainEventParam {

    @NonNull
    DomainEntity domainEntity;

    @NonNull
    Class<? extends DomainRepository> domainRepository;

    public DomainRemovedEventParam(@NonNull DomainEntity domainEntity,
                                   @NonNull Class<? extends DomainRepository> domainRepository) {
        this.domainEntity = Objects.requireNonNull(domainEntity);
        this.domainRepository = Objects.requireNonNull(domainRepository);
    }

    @Override
    public String toString() {
        return "DomainRemovedEventParam{" +
                "domainEntity=" + domainEntity +
                ", domainRepository=" + domainRepository +
                '}';
    }
}
