package io.github.redlotus794.enigma.ddd.spring.event;

import org.springframework.transaction.event.TransactionPhase;

public interface TransactionalEventProxy {

    /**
     * 根据事务阶段，执行函数
     * @param runnable Runnable
     * @param phase TransactionPhase
     */
    void execute(Runnable runnable, TransactionPhase phase);
}
