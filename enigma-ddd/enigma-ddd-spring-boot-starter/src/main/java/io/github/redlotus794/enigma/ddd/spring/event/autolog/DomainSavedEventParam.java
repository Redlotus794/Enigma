package io.github.redlotus794.enigma.ddd.spring.event.autolog;

import io.github.redlotus794.enigma.ddd.core.DomainEntity;
import io.github.redlotus794.enigma.ddd.core.DomainRepository;
import io.github.redlotus794.enigma.ddd.core.event.DomainEventParam;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import org.jspecify.annotations.NonNull;
import java.util.Objects;

/**
 * DomainSavedEventParam
 *
 * @author wangjialong
 * @since 2025/12/17 10:56
 */

@SuppressWarnings("rawtypes")
@Getter
@EqualsAndHashCode
public class DomainSavedEventParam implements DomainEventParam {

    @NonNull
    DomainEntity domainEntity;

    @NonNull
    Class<? extends DomainRepository> domainRepository;

    public DomainSavedEventParam(@NonNull DomainEntity domainEntity,
                                 @NonNull Class<? extends DomainRepository> domainRepository) {
        this.domainEntity = Objects.requireNonNull(domainEntity);
        this.domainRepository = Objects.requireNonNull(domainRepository);
    }

    @Override
    public String toString() {
        return "DomainSavedEventParam{" +
                "domainEntity=" + domainEntity +
                ", domainRepository=" + domainRepository +
                '}';
    }
}
