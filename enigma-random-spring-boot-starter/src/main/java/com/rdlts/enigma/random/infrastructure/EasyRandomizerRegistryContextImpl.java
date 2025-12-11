package com.rdlts.enigma.random.infrastructure;

import com.rdlts.enigma.random.domain.EnigmaRandomizerRegistry;
import com.rdlts.enigma.random.domain.EnigmaRandomizerRegistryContext;
import lombok.extern.log4j.Log4j2;
import org.jeasy.random.api.RandomizerRegistry;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * <h1>EasyRandomizerRegistryContextImpl</h1>
 * <p>Easy Random的随机器注册对象上下文实现。</p>
 * <p>Easy Random Registry必须是实现了RandomizerRegistry的类，未实现的类不会加载进上下文</p>
 *
 * @see org.jeasy.random.api.RandomizerRegistry
 * @see org.jeasy.random.api.ObjectFactory
 * @author wangjialong
 * @since 2025/12/10 15:57
 */
@Component
@Log4j2
public class EasyRandomizerRegistryContextImpl implements EnigmaRandomizerRegistryContext, ApplicationContextAware {

    /** copy of randomizerRegistries */
    protected Collection<EnigmaRandomizerRegistry> enigmaRandomizerRegistries = new CopyOnWriteArrayList<>();

    /** easy random的registry对象集合 */
    protected Collection<RandomizerRegistry> randomizerRegistries = new CopyOnWriteArrayList<>();

    @Override
    public Collection<EnigmaRandomizerRegistry> enigmaRandomizerRegistries() {
        return enigmaRandomizerRegistries;
    }

    public Collection<RandomizerRegistry> randomizerRegistries() {
        return Collections.unmodifiableCollection(randomizerRegistries);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        final Map<String, EnigmaRandomizerRegistry> beansOfType = applicationContext.getBeansOfType(EnigmaRandomizerRegistry.class);
        // 过滤掉不是 RandomizerRegistry 的类型数据
        beansOfType.values().forEach(t -> {
            log.info("EasyRandomizerRegistry: {}", t.getClass().getName());
            if (t instanceof RandomizerRegistry) {
                randomizerRegistries.add((RandomizerRegistry) t);
                enigmaRandomizerRegistries.add(t);
            }
        });
    }
}
