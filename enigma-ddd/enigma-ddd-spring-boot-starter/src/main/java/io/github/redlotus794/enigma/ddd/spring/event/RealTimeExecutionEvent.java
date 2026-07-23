package io.github.redlotus794.enigma.ddd.spring.event;

import io.github.redlotus794.enigma.ddd.core.event.DomainEvent;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.jspecify.annotations.NonNull;

/**
 * 准实时事件
 * 处理事件监听机制中，需要等待事务完成后的事件。
 * - 不支持持久化
 * - 不支持重放机制
 */
@Getter
@NoArgsConstructor
@Log4j2
public class RealTimeExecutionEvent extends DomainEvent<RealTimeExecutionParam> {

    public RealTimeExecutionEvent(@NonNull RealTimeExecutionParam param) {
        super(param);
        log.debug("创建准实时事件: {}", param);
    }

    @Override
    public boolean isPersistable() {
        return false;
    }

    @Override
    public void replay() {
        throw new UnsupportedOperationException("RealTime Execution Event do not support replay!");
    }
}
