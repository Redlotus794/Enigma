package com.rdlts.enigma.ddd.spring.event.autolog;

import com.rdlts.enigma.ddd.core.DomainEntity;
import com.rdlts.enigma.ddd.core.DomainRepository;
import com.rdlts.enigma.ddd.core.event.DomainEventParam;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import javax.annotation.Nonnull;
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

    @Nonnull
    DomainEntity domainEntity;

    @Nonnull
    Class<? extends DomainRepository> domainRepository;

    public DomainSavedEventParam(@Nonnull DomainEntity domainEntity,
                                 @Nonnull Class<? extends DomainRepository> domainRepository) {
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
