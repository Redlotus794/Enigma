package io.github.redlotus794.enigma.ddd.core.service;

import io.github.redlotus794.enigma.ddd.core.AggregateRoot;
import io.github.redlotus794.enigma.ddd.core.DomainRepository;
import io.github.redlotus794.enigma.ddd.core.exception.DomainAggregateRootNotFoundException;

import org.jspecify.annotations.NonNull;
import java.util.function.Supplier;

/**
 * 领域服务工具类方法
 *
 * @author wangjialong
 * @since 2025/12/2 11:25
 * @param <PKType> 聚合根主键类型
 * @param <DAR> 聚合根类型
 */
public interface DomainServiceUtils<PKType, DAR extends AggregateRoot<PKType>> {

    /**
     * 查询实体，为空报异常
     * 
     * @see DomainRepository#findRequired(Object)
     * @param pkType PKType
     * @return DAR
     * @exception DomainAggregateRootNotFoundException 聚合根未找到异常
     */
    @NonNull
    default DAR findBy(PKType pkType) throws DomainAggregateRootNotFoundException {
        return this.findBy(pkType, () -> new DomainAggregateRootNotFoundException(pkType));
    }

    /**
     * 查询实体，为空报异常
     * @param pkType PKType
     * @param notFoundException 自定义未找到实体异常
     * @return DAR
     */
    @NonNull
    default DAR findBy(PKType pkType, Supplier<RuntimeException> notFoundException) {
        return repository().find(pkType).orElseThrow(notFoundException);
    }

    /**
     * 领域资源库
     * @return DomainRepository
     */
    @NonNull
    DomainRepository<DAR, PKType> repository();
}
