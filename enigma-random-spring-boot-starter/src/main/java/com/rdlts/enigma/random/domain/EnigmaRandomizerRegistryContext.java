package com.rdlts.enigma.random.domain;

import java.util.Collection;

/**
 * EnigmaRandomizerRegistryContext
 * Object随机生成器注册的上下文对象，用以读取系统中的所有Object随机生成器
 *
 * @author wangjialong
 * @since 2025/12/10 10:11
 */
public interface EnigmaRandomizerRegistryContext {

    /**
     * 获取所有Object随机生成器的注册信息
     * @return Collection<EnigmaRandomizerRegistry>
     */
    Collection<EnigmaRandomizerRegistry> enigmaRandomizerRegistries();
}
