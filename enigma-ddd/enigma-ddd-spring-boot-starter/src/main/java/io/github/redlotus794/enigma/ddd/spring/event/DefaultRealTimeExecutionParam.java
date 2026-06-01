package io.github.redlotus794.enigma.ddd.spring.event;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DefaultRealTimeExecutionParam implements RealTimeExecutionParam {

    Runnable runnable;

    @Override
    public void run() {
        runnable.run();
    }
}
