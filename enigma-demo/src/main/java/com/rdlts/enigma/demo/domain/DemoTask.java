package com.rdlts.enigma.demo.domain;

import com.rdlts.enigma.ddd.core.DomainEntity;
import org.springframework.util.Assert;

import javax.annotation.Nonnull;
import java.time.Instant;

/**
 * DemoTask 示例领域实体。
 *
 * @author wangjialong
 * @since 2026/05/17 10:00
 */
public class DemoTask implements DomainEntity<String> {

    private final String id;
    private final String name;
    private final Instant createdAt;

    /**
     * 构造一个示例任务实体。
     *
     * @param id 主键
     * @param name 任务名称
     * @param createdAt 创建时间
     */
    public DemoTask(@Nonnull String id, @Nonnull String name, @Nonnull Instant createdAt) {
        Assert.hasText(id, "demo task id must not be blank");
        Assert.hasText(name, "demo task name must not be blank");
        Assert.notNull(createdAt, "demo task createdAt must not be null");
        this.id = id;
        this.name = name;
        this.createdAt = createdAt;
    }

    /**
     * 任务名称。
     *
     * @return 任务名称
     */
    @Nonnull
    public String name() {
        return name;
    }

    /**
     * 创建时间。
     *
     * @return 创建时间
     */
    @Nonnull
    public Instant createdAt() {
        return createdAt;
    }

    /**
     * 返回实体唯一标识。
     *
     * @return 主键
     */
    @Nonnull
    @Override
    public String identity() {
        return id;
    }
}

