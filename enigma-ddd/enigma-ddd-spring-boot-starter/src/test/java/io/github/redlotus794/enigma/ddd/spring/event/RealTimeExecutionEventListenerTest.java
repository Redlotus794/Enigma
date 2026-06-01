package io.github.redlotus794.enigma.ddd.spring.event;

import org.junit.jupiter.api.Test;
import org.springframework.transaction.event.TransactionPhase;

import static org.junit.jupiter.api.Assertions.assertSame;

class RealTimeExecutionEventListenerTest {

    @Test
    void testDoRealTimeExecution() {
        TestTransactionalEventProxy proxy = new TestTransactionalEventProxy();
        RealTimeExecutionEventListener listener = new RealTimeExecutionEventListener(proxy);
        TestRealTimeExecutionParam param = new TestRealTimeExecutionParam();
        RealTimeExecutionEvent event = new RealTimeExecutionEvent(param);

        listener.doRealTimeExecution(event);

        assertSame(param, proxy.runnable);
        assertSame(TransactionPhase.AFTER_COMPLETION, proxy.phase);
    }

    static class TestTransactionalEventProxy implements TransactionalEventProxy {

        private Runnable runnable;

        private TransactionPhase phase;

        @Override
        public void execute(Runnable runnable, TransactionPhase phase) {
            this.runnable = runnable;
            this.phase = phase;
        }
    }

    static class TestRealTimeExecutionParam implements RealTimeExecutionParam {

        @Override
        public void run() {
        }
    }
}
