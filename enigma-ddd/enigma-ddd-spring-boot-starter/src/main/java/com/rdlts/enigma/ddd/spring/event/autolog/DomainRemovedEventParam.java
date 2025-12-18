package com.rdlts.enigma.ddd.spring.event.autolog;

import com.rdlts.enigma.ddd.core.DomainEntity;
import com.rdlts.enigma.ddd.core.DomainRepository;
import com.rdlts.enigma.ddd.core.event.DomainEventParam;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import javax.annotation.Nonnull;
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

    @Nonnull
    DomainEntity domainEntity;

    @Nonnull
    Class<? extends DomainRepository> domainRepository;

    public DomainRemovedEventParam(@Nonnull DomainEntity domainEntity,
                                   @Nonnull Class<? extends DomainRepository> domainRepository) {
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
