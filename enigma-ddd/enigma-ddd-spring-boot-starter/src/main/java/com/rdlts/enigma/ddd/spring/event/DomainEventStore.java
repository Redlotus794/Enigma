package com.rdlts.enigma.ddd.spring.event;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import com.rdlts.enigma.ddd.core.DomainEvent;
import com.rdlts.enigma.ddd.core.DomainEventParam;
import com.rdlts.enigma.ddd.spring.event.builder.DomainEventStoreJsonBuilderContext;
import lombok.*;
import lombok.extern.log4j.Log4j2;

import javax.annotation.Nonnull;
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;

/**
 * DomainEventStore
 *
 * @param <T> 参数类型
 * @author wangjialong
 * @since 2025/12/3 16:14
 */
@Data
@EqualsAndHashCode
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Log4j2
public class DomainEventStore<T> {

    @Nonnull
    String eventUuid;

    /**
     * Event类全限定名，或者自定义名称
     */
    @Nonnull
    String domainEventName;

    /**
     * 参数类的全限定名
     */
    @Nonnull
    String paramType;

    /**
     * 参数的序列化内容
     */
    @Nonnull
    T eventContent;

    /**
     * of
     * @param domainEvent DomainEvent
     * @return DomainEventStore
     */
    public static <T extends DomainEventParam> DomainEventStore<T> of(DomainEvent<T> domainEvent) {
        return DomainEventStore.<T> builder()
                .domainEventName(domainEvent.domainEventName())
                .eventContent(domainEvent.getEventContent())
                .paramType(domainEvent.getEventContent().getClass().getName())
                .eventUuid(domainEvent.getEventUuid().getUuid())
                .build();
    }

    /**
     * rebuildBy 根据文件重新构建事件源
     *
     * @param jsonFile JSON文件
     * @param charset 文件编码方式
     * @return DomainEventStore
     * @throws IOException 读取文件失败
     * @throws ClassNotFoundException 未找到事件源类型
     */
    public static DomainEvent<?> rebuildBy(@Nonnull File jsonFile, @Nonnull Charset charset)
            throws IOException, ClassNotFoundException {
        final JSONObject jsonObject = JSON.parseObject(Files.newInputStream(jsonFile.toPath()), charset);
        final String eventName = jsonObject.getString("domainEventName");
        return DomainEventStoreJsonBuilderContext
                .instance()
                .withBuilder(eventName)
                .buildEvent(jsonObject);
    }
}
