package com.rdlts.enigma.ddd.spring.event.builder;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanInitializationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import javax.annotation.Nonnull;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * DomainEventStoreJsonBuilderContext
 *
 * @author wangjialong
 * @since 2025/12/4 10:48
 */
@Component
@Log4j2
public class DomainEventStoreJsonBuilderContext implements ApplicationContextAware {

    protected static DomainEventStoreJsonBuilderContext instance;

    protected ApplicationContext applicationContext;

    protected Map<String, DomainEventStoreJsonBuilder> jsonBuilderMap = new ConcurrentHashMap<>();

    @Autowired
    protected DefaultJsonBuilder defaultJsonBuilder;

    public static DomainEventStoreJsonBuilderContext instance() {
        return instance;
    }

    /**
     * withBuilder
     * @param eventName String
     * @return DomainEventStoreJsonBuilder
     */
    @Nonnull
    public DomainEventStoreJsonBuilder withBuilder(String eventName) {
        DomainEventStoreJsonBuilder builder;
        if (jsonBuilderMap.containsKey(eventName)) {
            builder = jsonBuilderMap.get(eventName);
        } else {
            builder = defaultJsonBuilder;
        }
        return builder;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
        DomainEventStoreJsonBuilderContext.instance = applicationContext.getBean(DomainEventStoreJsonBuilderContext.class);
        final Map<String, DomainEventStoreJsonBuilder> beansOfType = applicationContext.getBeansOfType(DomainEventStoreJsonBuilder.class);
        beansOfType.values().forEach(t -> {
            final List<String> list = t.supportEventName();
            if (CollectionUtils.isEmpty(list)) {
                return;
            }

            for (String eventName : list) {
                if (jsonBuilderMap.containsKey(eventName)) {
                    log.debug("Duplicate json builder registration! {}", eventName);
                    throw new BeanInitializationException("Duplicate json builder registration! " + eventName);
                }
                jsonBuilderMap.put(eventName, t);
            }
        });
    }
}
