package com.rdlts.enigma.ddd.core;

import com.rdlts.enigma.ddd.core.exception.DomainEntityNotFoundException;

import javax.annotation.Nonnull;
import java.util.function.Supplier;

/**
 * 领域服务工具类方法
 *
 * @author wangjialong
 * @since 2025/12/2 11:25
 * @param <PKType>
 * @param <T>
 */
public interface DomainServiceUtils<PKType, T extends DomainEntity<PKType>> {

    /**
     * 查询实体，为空报异常
     * 
     * @see DomainRepository#findRequired(Object) 
     * @param pkType PKType
     * @return T
     * @exception DomainEntityNotFoundException 实体未找到异常
     */
    @Nonnull
    default T findBy(PKType pkType) throws DomainEntityNotFoundException {
        return this.findBy(pkType, DomainEntityNotFoundException::new);
    }

    /**
     * 查询实体，为空报异常
     * @param pkType PKType
     * @param notFoundException 自定义未找到实体异常
     * @return T
     */
    @Nonnull
    default T findBy(PKType pkType, Supplier<RuntimeException> notFoundException) {
        return repository().find(pkType).orElseThrow(notFoundException);
    }

    /**
     * 领域资源库
     * @return DomainRepository
     */
    @Nonnull
    DomainRepository<T, PKType> repository();
}
