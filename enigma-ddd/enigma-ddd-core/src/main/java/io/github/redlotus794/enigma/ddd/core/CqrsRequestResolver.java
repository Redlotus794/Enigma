package io.github.redlotus794.enigma.ddd.core;

import io.github.redlotus794.enigma.ddd.core.cqrs.CqrsRequest;

/**
 * CqrsRequestResolver - 指令解析器。
 * <p>
 * 对象转换为CQRS指令的能力
 * </p>
 *
 * @author wangjialong
 * @since 2026/06/04
 */
public interface CqrsRequestResolver<V, Request extends CqrsRequest> {

    /**
     * 将一个视图对象解析为一个指令对象。
     *
     * @param v 待转换对象
     * @return 解析得到的指令对象
     */
    Request resolve(V v);
}
