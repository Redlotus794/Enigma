package com.rdlts.enigma.demo.infrastructure;

import com.rdlts.enigma.ddd.spring.repository.InMemoryDomainRepository;
import com.rdlts.enigma.demo.domain.DemoTask;
import com.rdlts.enigma.demo.domain.DemoTaskRepository;
import org.springframework.stereotype.Repository;

import javax.annotation.Nonnull;
import java.util.UUID;

/**
 * DemoTask 的内存资源库实现。
 *
 * @author wangjialong
 * @since 2026/05/17 10:00
 */
@Repository
public class InMemoryDemoTaskRepository extends InMemoryDomainRepository<DemoTask, String>
        implements DemoTaskRepository {

    /**
     * 生成新的随机主键。
     *
     * @return 主键字符串
     */
    @Nonnull
    @Override
    public String nextIdentity() {
        return UUID.randomUUID().toString().replace("-", "");
    }
}

