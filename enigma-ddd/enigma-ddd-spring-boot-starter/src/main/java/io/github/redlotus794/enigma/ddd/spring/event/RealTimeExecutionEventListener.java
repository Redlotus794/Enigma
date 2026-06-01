package io.github.redlotus794.enigma.ddd.spring.event;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

@Log4j2
@RequiredArgsConstructor
public class RealTimeExecutionEventListener {

    private final TransactionalEventProxy proxy;

    /**
     * doRealTimeExecution 处理准实时事件
     * 由事务事件代理{@link TransactionalEventProxy} 在事务提交后，执行准实时事件。
     * @param realTimeExecutionEvent RealTimeExecutionEvent
     */
    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMPLETION, fallbackExecution = true)
    public void doRealTimeExecution(RealTimeExecutionEvent realTimeExecutionEvent) {
        RealTimeExecutionParam eventContent = realTimeExecutionEvent.getEventContent();
        log.debug("Do RealTime Execution Event: {}", eventContent);
        proxy.execute(eventContent, TransactionPhase.AFTER_COMPLETION);
    }
}
