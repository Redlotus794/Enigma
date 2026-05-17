package com.rdlts.enigma.demo.application;

import com.rdlts.enigma.demo.domain.DemoTask;
import com.rdlts.enigma.demo.domain.DemoTaskRepository;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.annotation.Nonnull;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

/**
 * DemoTask 应用服务，负责编排示例领域对象。
 *
 * @author wangjialong
 * @since 2026/05/17 10:00
 */
@Service
public class DemoTaskApplicationService {

    private final DemoTaskRepository demoTaskRepository;

    /**
     * 创建应用服务。
     *
     * @param demoTaskRepository 任务资源库
     */
    public DemoTaskApplicationService(DemoTaskRepository demoTaskRepository) {
        this.demoTaskRepository = demoTaskRepository;
    }

    /**
     * 创建任务。
     *
     * @param name 任务名称
     * @return 新建任务
     */
    @Nonnull
    public DemoTask create(@Nonnull String name) {
        Assert.hasText(name, "demo task name must not be blank");
        DemoTask demoTask = new DemoTask(demoTaskRepository.nextIdentity(), name.trim(), Instant.now());
        demoTaskRepository.save(demoTask);
        return demoTask;
    }

    /**
     * 查询所有任务。
     *
     * @return 任务列表
     */
    @Nonnull
    public List<DemoTask> list() {
        return new ArrayList<>(demoTaskRepository.findAll());
    }

    /**
     * 查询单个任务。
     *
     * @param id 任务主键
     * @return 任务实体
     */
    @Nonnull
    public DemoTask get(@Nonnull String id) {
        Assert.hasText(id, "demo task id must not be blank");
        return demoTaskRepository.findRequired(id);
    }

    /**
     * 删除任务。
     *
     * @param id 任务主键
     */
    public void delete(@Nonnull String id) {
        demoTaskRepository.remove(get(id));
    }
}

