package io.github.redlotus794.enigma.ddd.core.test.domain;

import io.github.redlotus794.enigma.ddd.core.event.DomainEvent;
import io.github.redlotus794.enigma.ddd.core.event.DomainEventParam;
import io.github.redlotus794.enigma.ddd.core.event.DomainEventPublisher;

import java.util.logging.Logger;

/**
 * TestDomainEventPublisherImpl
 *
 * @author wangjialong
 * @since 2025/12/3 09:08
 */
public class TestDomainEventPublisherImpl implements DomainEventPublisher {

    private static final Logger logger = Logger.getLogger(TestDomainEventPublisherImpl.class.getName());

    public static final TestDomainEventPublisherImpl INSTANCE = new TestDomainEventPublisherImpl();

    @Override
    public <T extends DomainEventParam> void publish(DomainEvent<T> domainEvent) {
        logger.info("发布领域事件: " + domainEvent.domainEventName());
    }

    @Override
    public DomainEventPublisher getInstance() {
        return INSTANCE;
    }
}
