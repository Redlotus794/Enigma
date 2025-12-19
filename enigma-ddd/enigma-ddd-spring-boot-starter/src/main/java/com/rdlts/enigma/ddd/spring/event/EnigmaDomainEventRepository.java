package com.rdlts.enigma.ddd.spring.event;

import com.rdlts.enigma.ddd.core.event.DomainEvent;
import com.rdlts.enigma.ddd.core.event.DomainEventRepository;
import com.rdlts.enigma.ddd.core.event.DomainEventUUID;
import lombok.extern.log4j.Log4j2;

import java.util.Collection;
import java.util.Optional;

/**
 * EnigmaDomainEventRepository
 *
 * @author wangjialong
 * @since 2025/12/3 14:06
 */
@Log4j2
public class EnigmaDomainEventRepository implements DomainEventRepository {

    /**
     * 需要持久化的事件，保存到资源库中
     * @param domainEvent DomainEvent
     */
    @Override
    public void save(DomainEvent<?> domainEvent) {
        if (domainEvent.isPersistable()) {
            log.info("Enigma资源库记录事件日志: {}", domainEvent);
        }
    }

    @Override
    public Optional<DomainEvent<?>> find(DomainEventUUID domainEventUuid) {
        throw new UnsupportedOperationException("默认领域事件资源库无法支持查询事件日志");
    }

    @Override
    public Collection<DomainEvent<?>> findAll() {
        throw new UnsupportedOperationException("默认领域事件资源库无法支持查询所有事件");
    }
}
