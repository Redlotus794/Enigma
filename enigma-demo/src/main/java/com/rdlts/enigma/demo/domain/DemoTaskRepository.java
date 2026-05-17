package com.rdlts.enigma.demo.domain;

import com.rdlts.enigma.ddd.core.DomainRepository;

import javax.annotation.Nonnull;

/**
 * DemoTask 领域资源库。
 *
 * @author wangjialong
 * @since 2026/05/17 10:00
 */
public interface DemoTaskRepository extends DomainRepository<DemoTask, String> {

    /**
     * 生成新的任务主键。
     *
     * @return 新主键
     */
    @Nonnull
    @Override
    String nextIdentity();
}

