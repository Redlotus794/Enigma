package io.github.redlotus794.enigma.ddd.core;

import org.jspecify.annotations.NonNull;

/**
 * <a href="file:///Users/wangjialong/baidusync/domain-driven-design/html/聚合.html">
 *     领域聚合 - Domain Aggregate
 *     </a>
 * <p>
 * @author wangjialong
 * @since 2025/12/01 15:11
 */
public interface DomainAggregate<T extends DomainAggregateRoot<?>> {

    /**
     * 聚合根对象，非空
     * @return {@link DomainAggregateRoot}
     */
    @NonNull
    T root();
}
