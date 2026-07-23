package io.github.redlotus794.enigma.ddd.spring.event.builder;

import com.alibaba.fastjson2.JSONObject;
import io.github.redlotus794.enigma.ddd.core.event.DomainEvent;
import io.github.redlotus794.enigma.ddd.core.event.DomainEventParam;

import java.util.List;

/**
 * DomainEventStoreJsonBuilder
 *
 * @author wangjialong
 * @since 2022/11/1
 */
public interface DomainEventStoreJsonBuilder {

    /**
     * Support Event Name
     * @return List
     */
    List<String> supportEventName();

    /**
     * 构建事件
     * @param jsonObject JSONObject
     * @param <T> 参数类型
     * @return AbstractDomainEvent
     * @exception ClassNotFoundException 参数或者事件类不存在
     */
    <T extends DomainEventParam> DomainEvent<T> buildEvent(JSONObject jsonObject) throws ClassNotFoundException;
}
