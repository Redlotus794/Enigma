package io.github.redlotus794.enigma.ddd.core;

/**
 * EntityJsonable
 * 表示领域实体可以转换为 JSON 对象的能力标记接口。
 *
 * @author wangjialong
 * @since 2026/1/15 14:14
 */
public interface EntityJsonable<T extends EntityJsonObject<?>> {

    /**
     * 将当前实体转换为对应的 JSON 对象表示。
     * 替代EntityJsonObject中toEntityJson静态方法的使用。
     *
     * @see EntityJsonObject#toEntityJson(Class, DomainEntity)
     * @return T 对应的 JSON 对象
     */
    T toEntityJsonObject();
}
