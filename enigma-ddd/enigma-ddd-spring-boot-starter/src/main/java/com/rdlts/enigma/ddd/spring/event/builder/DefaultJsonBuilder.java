package com.rdlts.enigma.ddd.spring.event.builder;

import com.alibaba.fastjson2.JSONObject;
import com.rdlts.enigma.ddd.core.DomainEvent;
import com.rdlts.enigma.ddd.core.DomainEventParam;
import com.rdlts.enigma.ddd.core.DomainEventUUID;
import com.rdlts.enigma.ddd.spring.utils.FastJsonUtils;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;

/**
 * DefaultJsonBuilder
 * 使用fastjson2 来解析事件源
 *
 * @author wangjialong
 * @since 2025/12/4 11:28
 */
@Component
@Log4j2
public class DefaultJsonBuilder implements DomainEventStoreJsonBuilder {

    @Override
    public List<String> supportEventName() {
        // 默认builder
        return Collections.emptyList();
    }

    @Override
    @SuppressWarnings("all")
    public <T extends DomainEventParam> DomainEvent<T> buildEvent(JSONObject jsonObject) throws ClassNotFoundException {
        final String eventUuid = jsonObject.getString("eventUuid");
        final String domainEventName = jsonObject.getString("domainEventName");
        final String paramType = jsonObject.getString("paramType");
        final Object eventContent = jsonObject.get("eventContent");
        log.info("重构事件, {} | {}", domainEventName, eventUuid);

        Class<T> paramTypeClazz = (Class<T>) Class.forName(paramType);
        Class<?> domainEventClazz = Class.forName(domainEventName);

        DomainEvent<T> domainEvent = (DomainEvent<T>) new JSONObject().to(domainEventClazz);
        if (eventContent instanceof JSONObject) {
            final T paramContent = FastJsonUtils.parseObject(eventContent.toString(), paramTypeClazz);
            domainEvent.setEventContent(paramContent);
            domainEvent.setEventUuid(new DomainEventUUID(eventUuid));
        } else {
            throw new IllegalArgumentException("Illegal format, Event content is not object or array!");
        }
        return domainEvent;
    }
}
