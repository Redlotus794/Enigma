package io.github.redlotus794.enigma.ddd.spring.test.infrastructure;

import io.github.redlotus794.enigma.ddd.spring.event.autolog.RepositoryEventAutoLog;
import org.springframework.stereotype.Component;

/**
 * NonRepositoryAutoLog
 *
 * @author wangjialong
 * @since 2025/12/18 14:20
 */
@Component
@RepositoryEventAutoLog
public class NonRepositoryAutoLog {

    public void doSomething() {

    }
}
