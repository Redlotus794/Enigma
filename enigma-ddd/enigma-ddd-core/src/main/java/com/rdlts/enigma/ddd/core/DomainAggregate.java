package com.rdlts.enigma.ddd.core;

import javax.annotation.Nonnull;

/**
 * <a href="https://wcnn2j4xsnan.feishu.cn/wiki/AQrFwGZg4ileJnkdOZccBdK0nSb">
 *     领域聚合 - Domain Aggregate
 *     </a>
 * 领域聚合概念
 * 
 * @author wangjialong
 * @since 2025/12/01 15:11
 */
public interface DomainAggregate<T extends DomainAggregateRoot> {

    /**
     * 聚合根对象，非空
     * @return {@link DomainAggregateRoot}
     */
    @Nonnull
    T root();
}
