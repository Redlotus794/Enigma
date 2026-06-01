package io.github.redlotus794.enigma.ddd.spring.event;

import lombok.extern.log4j.Log4j2;
import org.springframework.transaction.event.TransactionPhase;

@Log4j2
public class DefaultTransactionalEventProxy implements TransactionalEventProxy {

    public static final DefaultTransactionalEventProxy INSTANCE = new DefaultTransactionalEventProxy();

    @Override
    public void execute(Runnable runnable, TransactionPhase phase) {
        log.debug("Do Transactional Event Phase: {}", phase);
        runnable.run();
    }
}
