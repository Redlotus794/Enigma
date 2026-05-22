package io.github.redlotus794.enigma.random.infrastructure;

import io.github.redlotus794.enigma.random.domain.EnigmaRandomGenerator;
import lombok.Getter;
import org.jeasy.random.EasyRandom;
import org.jeasy.random.EasyRandomParameters;

import java.util.List;
import java.util.stream.Collectors;

/**
 * EasyRandomizer
 *
 * @see <a href="https://github.com/j-easy/easy-random">easy-random</a>
 * @author wangjialong
 * @since 2025/12/9 14:46
 */
@Getter
public class EasyRandomGenerator implements EnigmaRandomGenerator {

    EasyRandom easyRandom;

    public EasyRandomGenerator(EasyRandomParameters easyRandomParameters) {
        easyRandom = new EasyRandom(easyRandomParameters);
    }

    @Override
    public <T> T nextObject(Class<T> clazz) {
        return easyRandom.nextObject(clazz);
    }

    @Override
    public <T> List<T> nextList(Class<T> clz, int size) {
        return easyRandom.objects(clz, size).collect(Collectors.toList());
    }
}
