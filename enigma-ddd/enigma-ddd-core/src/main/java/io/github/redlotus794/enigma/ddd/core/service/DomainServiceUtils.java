package io.github.redlotus794.enigma.ddd.core.service;

import io.github.redlotus794.enigma.ddd.core.DomainAggregate;
import io.github.redlotus794.enigma.ddd.core.DomainAggregateRoot;
import io.github.redlotus794.enigma.ddd.core.DomainRepository;
import io.github.redlotus794.enigma.ddd.core.exception.DomainEntityNotFoundException;

import org.jspecify.annotations.NonNull;
import java.util.function.Supplier;

/**
 * 领域服务工具类方法
 *
 * @author wangjialong
 * @since 2025/12/2 11:25
 * @param <PKType>
 * @param <T>
 */
public interface DomainServiceUtils<PKType, DAR extends DomainAggregateRoot<PKType>, DA extends DomainAggregate<DAR>> {

    /**
     * 查询实体，为空报异常
     * 
     * @see DomainRepository#findRequired(Object)
     * @param pkType PKType
     * @return DA
     * @exception DomainEntityNotFoundException 实体未找到异常
     */
    @NonNull
    default DA findBy(PKType pkType) throws DomainEntityNotFoundException {
        return this.findBy(pkType, DomainEntityNotFoundException::new);
    }

    /**
     * 查询实体，为空报异常
     * @param pkType PKType
     * @param notFoundException 自定义未找到实体异常
     * @return DA
     */
    @NonNull
    default DA findBy(PKType pkType, Supplier<RuntimeException> notFoundException) {
        return repository().find(pkType).orElseThrow(notFoundException);
    }

    /**
     * 领域资源库
     * @return DomainRepository
     */
    @NonNull
    DomainRepository<DA, DAR, PKType> repository();
}
