package com.rdlts.enigma.ddd.spring.event.autolog;

import com.rdlts.enigma.ddd.core.DomainEntity;
import com.rdlts.enigma.ddd.core.DomainRepository;
import com.rdlts.enigma.ddd.core.event.DomainEventParam;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import javax.annotation.Nonnull;
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

    @Nonnull
    Collection<DomainEntity> domainEntities;

    @Nonnull
    Class<? extends DomainRepository> domainRepository;

    public DomainSavedAllEventParam(@Nonnull Collection<DomainEntity> domainEntities,
                                    @Nonnull Class<? extends DomainRepository> domainRepository) {
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
