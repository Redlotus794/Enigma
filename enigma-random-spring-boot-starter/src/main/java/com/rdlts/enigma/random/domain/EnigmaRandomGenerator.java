package com.rdlts.enigma.random.domain;

import java.util.List;

/**
 * EnigmaRandomGenerator
 * 随机生成器接口，Facade模式，屏蔽底层可能得技术细节
 * 常见技术实现：EasyRandom, DataFaker
 *
 * @author wangjialong
 * @since 2025/12/9 14:41
 */
public interface EnigmaRandomGenerator {

    /**
     * 随机生成一个对象
     * @param clazz 类
     * @param <T> 类型泛型
     * @return T
     */
    <T> T nextObject(Class<T> clazz);

    /**
     * 随机生成一个对象List
     * @param clz 类
     * @param <T> 类型泛型
     * @param size list长度 非负 大于0
     * @return List<T>
     */
    <T> List<T> nextList(Class<T> clz, int size);
}
