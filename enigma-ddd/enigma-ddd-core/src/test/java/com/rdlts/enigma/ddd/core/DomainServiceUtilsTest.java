package com.rdlts.enigma.ddd.core;

import com.rdlts.enigma.ddd.core.exception.DomainEntityNotFoundException;
import com.rdlts.enigma.ddd.core.test.entity.TestDomainEntity;
import com.rdlts.enigma.ddd.core.test.service.TestDomainService;
import com.rdlts.enigma.ddd.core.test.valueobject.TestId;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class DomainServiceUtilsTest {

    TestDomainService testDomainService = new TestDomainService();
    TestDomainEntity testEntity1;
    TestDomainEntity testEntity2;
    TestId testId1;
    TestId testId2;

    @BeforeEach
    public void setUp() {
        testId1 = new TestId("test-id-1");
        testId2 = new TestId("test-id-2");
        testEntity1 = TestDomainEntity.builder()
                .testId(testId1)
                .build();
        testEntity2 = TestDomainEntity.builder()
                .testId(testId2)
                .build();
    }

    @Test
    void findBy() {
        Assertions.assertThrows(DomainEntityNotFoundException.class, () -> testDomainService.findBy(testId1));
        testDomainService.repository().save(testEntity1);
        TestDomainEntity foundEntity = testDomainService.findBy(testId1);
        assertEquals(testEntity1, foundEntity);
    }

    @Test
    void test_entityNotExists_findBy_throwCustomException() {
        Assertions.assertThrows(IllegalStateException.class, () -> testDomainService.findBy(testId2, IllegalStateException::new));
    }

    @Test
    void repository() {
        DomainRepository<TestDomainEntity, TestId> repository = testDomainService.repository();
        assertNotNull(repository);
    }
}