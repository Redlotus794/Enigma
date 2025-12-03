package com.rdlts.enigma.ddd.spring.event;

import com.rdlts.enigma.ddd.core.DomainEvent;
import com.rdlts.enigma.ddd.core.DomainEventParam;
import com.rdlts.enigma.ddd.core.DomainEventPublisher;
import com.rdlts.enigma.ddd.core.DomainEventRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.BeansException;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

import javax.annotation.Nonnull;
import javax.annotation.Resource;

/**
 * EnigmaSpringDomainEventPublisher
 *
 * @author wangjialong
 * @since 2025/12/3 11:11
 */
@Log4j2
@Component
@ConditionalOnMissingBean(DomainEventPublisher.class)
public class EnigmaSpringDomainEventPublisher implements DomainEventPublisher, ApplicationContextAware {

    /**
     * 实例
     */
    public static EnigmaSpringDomainEventPublisher SINGLETON_INSTANCE;

    /**
     * 领域事件发布激活开关，基于线程级别。默认为true
     */
    private final ThreadLocal<Boolean> active = new ThreadLocal<>();

    /**
     * Spring Event事件发布
     */
    @Resource
    ApplicationEventPublisher applicationEventPublisher;

    /**
     * 领域事件仓库
     */
    @Resource
    DomainEventRepository domainEventRepository;

    /**
     * 实例获取
     * @return DomainEventPublisher
     */
    @Override
    public DomainEventPublisher getInstance() {
        return SINGLETON_INSTANCE;
    }

    /**
     * 设置事件触发激活状态
     * 线程级别的控制，不会影响全局
     *
     * @param active Boolean
     */
    public void setActive(Boolean active) {
        this.active.set(active);
    }

    /**
     * 获取事件触发激活状态
     * @return  Boolean default is True
     */
    public boolean getActive() {
        if (this.active.get() == null) {
            return true;
        }

        return this.active.get();
    }

    /**
     * 发布领域事件
     * @param domainEvent AbstractDomainEvent
     * @param <T> DomainEventParam
     */
    @Override
    public <T extends DomainEventParam> void publish(DomainEvent<T> domainEvent) {
        log.debug("发布事件 {}", domainEvent.domainEventName());
        log.debug("内容: {}", domainEvent);
        if (!getActive()) {
            log.debug("跳过事件发布");
            return;
        }
        domainEventRepository.save(domainEvent);
        applicationEventPublisher.publishEvent(domainEvent);
    }

    @Override
    public void setApplicationContext(@Nonnull ApplicationContext applicationContext) throws BeansException {
        SINGLETON_INSTANCE = applicationContext.getBean(EnigmaSpringDomainEventPublisher.class);
    }
}
