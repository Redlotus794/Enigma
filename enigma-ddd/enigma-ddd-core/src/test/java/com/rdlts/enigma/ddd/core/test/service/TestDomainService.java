package com.rdlts.enigma.ddd.core.test.service;

import com.rdlts.enigma.ddd.core.DomainRepository;
import com.rdlts.enigma.ddd.core.DomainService;
import com.rdlts.enigma.ddd.core.DomainServiceUtils;
import com.rdlts.enigma.ddd.core.test.entity.TestDomainEntity;
import com.rdlts.enigma.ddd.core.test.repository.TestDomainEntityRepository;
import com.rdlts.enigma.ddd.core.test.valueobject.TestId;

import javax.annotation.Nonnull;

/**
 * TestDomainService
 *
 * @author wangjialong
 * @since 2025/12/1 16:13
 */
public class TestDomainService implements DomainService, DomainServiceUtils<TestId, TestDomainEntity> {

    // new repository instance
    TestDomainEntityRepository repository = new TestDomainEntityRepository();

    public boolean doSomething() {
        System.out.println("Doing something in TestDomainService");
        return true;
    }

    @Nonnull
    @Override
    public DomainRepository<TestDomainEntity, TestId> repository() {
        return repository;
    }
}
