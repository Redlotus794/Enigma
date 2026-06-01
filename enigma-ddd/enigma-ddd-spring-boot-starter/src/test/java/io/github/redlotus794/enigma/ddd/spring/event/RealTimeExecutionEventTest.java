package io.github.redlotus794.enigma.ddd.spring.event;

import org.junit.jupiter.api.Test;

import java.util.concurrent.atomic.AtomicBoolean;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;

class RealTimeExecutionEventTest {

    @Test
    void testConstructor() {
        TestRealTimeExecutionParam param = new TestRealTimeExecutionParam();

        RealTimeExecutionEvent event = new RealTimeExecutionEvent(param);

        assertNotNull(event.getEventUuid());
        assertSame(param, event.getEventContent());
        assertNotNull(event.getEventTime());
        assertFalse(event.isPersistable());
    }

    @Test
    void testNoArgsConstructor() {
        RealTimeExecutionEvent event = new RealTimeExecutionEvent();

        assertNotNull(event);
    }

    @Test
    void testReplay() {
        RealTimeExecutionEvent event = new RealTimeExecutionEvent(new TestRealTimeExecutionParam());

        assertThrows(UnsupportedOperationException.class, event::replay);
    }

    static class TestRealTimeExecutionParam implements RealTimeExecutionParam {

        private final AtomicBoolean executed = new AtomicBoolean(false);

        @Override
        public void run() {
            executed.set(true);
        }
    }
}
