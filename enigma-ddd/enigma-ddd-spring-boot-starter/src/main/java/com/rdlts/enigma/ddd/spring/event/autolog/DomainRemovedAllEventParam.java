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
 * DomainRemovedAllEventParam
 *
 * @author wangjialong
 * @since 2025/12/18 09:02
 */
@Getter
@EqualsAndHashCode
@SuppressWarnings("rawtypes")
public class DomainRemovedAllEventParam implements DomainEventParam {

    @Nonnull
    Collection<DomainEntity> domainEntities;

    @Nonnull
    Class<? extends DomainRepository> domainRepository;

    public DomainRemovedAllEventParam(@Nonnull Collection<DomainEntity> domainEntities,
                                      @Nonnull Class<? extends DomainRepository> domainRepository) {
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
