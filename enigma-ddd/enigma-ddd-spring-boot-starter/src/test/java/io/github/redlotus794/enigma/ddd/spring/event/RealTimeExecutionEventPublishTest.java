package io.github.redlotus794.enigma.ddd.spring.event;

import io.github.redlotus794.enigma.ddd.core.event.DomainEventPublisher;
import io.github.redlotus794.enigma.ddd.spring.autoconfigure.EnigmaDomainComponentAutoConfiguration;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.concurrent.atomic.AtomicBoolean;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class RealTimeExecutionEventPublishTest {

    private static final AtomicBoolean EXECUTED = new AtomicBoolean(false);

    private AnnotationConfigApplicationContext applicationContext;

    private DomainEventPublisher publisher;

    private DomainEventPublisher previousPublisher;

    @BeforeEach
    void setUp() {
        previousPublisher = EnigmaSpringDomainEventPublisher.SINGLETON_INSTANCE;
        applicationContext = new AnnotationConfigApplicationContext(
                EnigmaDomainComponentAutoConfiguration.class);
        publisher = applicationContext.getBean(DomainEventPublisher.class);
        EXECUTED.set(false);
        publisher().setActive(true);
    }

    @AfterEach
    void tearDown() {
        applicationContext.close();
        EnigmaSpringDomainEventPublisher.SINGLETON_INSTANCE = previousPublisher;
    }

    @Test
    void givenRealTimeExecutionEvent_whenPublishWithoutTransaction_thenExecuteByFallback() {
        publisher.publish(new RealTimeExecutionEvent(new TestRealTimeExecutionParam()));

        assertTrue(EXECUTED.get());
    }

    @Test
    void givenInactivePublisher_whenPublishRealTimeExecutionEvent_thenSkipExecution() {
        publisher().setActive(false);

        publisher.publish(new RealTimeExecutionEvent(new TestRealTimeExecutionParam()));

        assertFalse(EXECUTED.get());
    }

    private EnigmaSpringDomainEventPublisher publisher() {
        return (EnigmaSpringDomainEventPublisher) this.publisher;
    }

    static class TestRealTimeExecutionParam implements RealTimeExecutionParam {

        @Override
        public void run() {
            EXECUTED.set(true);
        }
    }
}
