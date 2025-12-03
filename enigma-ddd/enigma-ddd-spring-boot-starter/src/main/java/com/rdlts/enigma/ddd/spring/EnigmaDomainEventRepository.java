package com.rdlts.enigma.ddd.spring;

import com.rdlts.enigma.ddd.core.DomainEvent;
import com.rdlts.enigma.ddd.core.DomainEventRepository;
import com.rdlts.enigma.ddd.core.DomainEventUUID;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * EnigmaDomainEventRepository
 *
 * @author wangjialong
 * @since 2025/12/3 14:06
 */
@Component
@Log4j2
public class EnigmaDomainEventRepository implements DomainEventRepository {

    @Override
    public void save(DomainEvent<?> domainEvent) {
        log.info("Enigma资源库记录事件日志: {}", domainEvent);
    }

    @Override
    public Optional<DomainEvent<?>> find(DomainEventUUID domainEventUuid) {
        throw new UnsupportedOperationException("默认资源库无法支持查询事件日志");
    }
}
