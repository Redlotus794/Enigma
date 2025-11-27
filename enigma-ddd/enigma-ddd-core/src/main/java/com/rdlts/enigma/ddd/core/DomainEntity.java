package com.rdlts.enigma.ddd.core;

import javax.annotation.Nonnull;

/**
 * <a href="https://wcnn2j4xsnan.feishu.cn/wiki/LnApwD7IgiC5WgknXP1c1pZxn5d?fromScene=spaceOverview">
 *     领域实体 - Domain Entity
 * </a>
 *
 * @author wangjialong
 * @since 2025/11/27 14:31
 */
public interface DomainEntity<PKType> {

    /**
     * 实体唯一主键
     * 通常为资源库生成的随机主键编号，尽量与业务主键分离
     *
     * @return PKType @Nonnull
     */
    @Nonnull
    PKType identity();

    /**
     * 实体版本号，控制更新
     *
     * @see EntityVersion
     * @return EntityVersion
     * @throws UnsupportedOperationException 默认抛出异常，表示未实现版本控制
     */
    @Nonnull
    default EntityVersion version() {
        throw new UnsupportedOperationException("version control not implement!");
    }
}