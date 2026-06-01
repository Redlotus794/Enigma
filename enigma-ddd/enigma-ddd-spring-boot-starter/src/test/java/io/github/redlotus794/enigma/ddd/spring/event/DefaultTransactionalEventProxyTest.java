package io.github.redlotus794.enigma.ddd.spring.event;

import org.junit.jupiter.api.Test;
import org.springframework.transaction.event.TransactionPhase;

import java.util.concurrent.atomic.AtomicBoolean;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

class DefaultTransactionalEventProxyTest {

    @Test
    void testInstance() {
        assertSame(DefaultTransactionalEventProxy.INSTANCE, DefaultTransactionalEventProxy.INSTANCE);
    }

    @Test
    void testExecute() {
        AtomicBoolean executed = new AtomicBoolean(false);

        DefaultTransactionalEventProxy.INSTANCE.execute(() -> executed.set(true), TransactionPhase.AFTER_COMPLETION);

        assertTrue(executed.get());
    }
}
