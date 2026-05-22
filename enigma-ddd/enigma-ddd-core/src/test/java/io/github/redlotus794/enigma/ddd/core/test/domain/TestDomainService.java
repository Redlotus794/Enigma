package io.github.redlotus794.enigma.ddd.core.test.domain;

import io.github.redlotus794.enigma.ddd.core.DomainRepository;
import io.github.redlotus794.enigma.ddd.core.service.DomainService;
import io.github.redlotus794.enigma.ddd.core.service.DomainServiceUtils;

import org.jspecify.annotations.NonNull;

/**
 * TestDomainService
 *
 * @author wangjialong
 * @since 2025/12/1 16:13
 */
public class TestDomainService implements DomainService,
        DomainServiceUtils<TestId, TestDomainEntity, TestDomainEntity> {

    // new repository instance
    TestDomainEntityRepository repository = new TestDomainEntityRepository();

    public boolean doSomething() {
        System.out.println("Doing something in TestDomainService");
        return true;
    }

    @NonNull
    @Override
    public DomainRepository<TestDomainEntity, TestDomainEntity, TestId> repository() {
        return repository;
    }
}
