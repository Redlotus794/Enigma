package io.github.redlotus794.enigma.ddd.core.service;

import io.github.redlotus794.enigma.ddd.core.DomainRepository;
import io.github.redlotus794.enigma.ddd.core.exception.DomainAggregateRootNotFoundException;
import io.github.redlotus794.enigma.ddd.core.test.domain.TestEntity;
import io.github.redlotus794.enigma.ddd.core.test.domain.TestDomainService;
import io.github.redlotus794.enigma.ddd.core.test.domain.TestId;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class DomainServiceUtilsTest {

    TestDomainService testDomainService = new TestDomainService();
    TestEntity testEntity1;
    TestEntity testEntity2;
    TestId testId1;
    TestId testId2;

    @BeforeEach
    public void setUp() {
        testId1 = new TestId("test-id-1");
        testId2 = new TestId("test-id-2");
        testEntity1 = TestEntity.builder()
                .testId(testId1)
                .build();
        testEntity2 = TestEntity.builder()
                .testId(testId2)
                .build();
    }

    @Test
    void findBy() {
        Assertions.assertThrows(DomainAggregateRootNotFoundException.class, () -> testDomainService.findBy(testId1));
        testDomainService.repository().save(testEntity1);
        TestEntity foundEntity = testDomainService.findBy(testId1);
        assertEquals(testEntity1, foundEntity);
    }

    @Test
    void test_entityNotExists_findBy_throwCustomException() {
        Assertions.assertThrows(IllegalStateException.class, () -> testDomainService.findBy(testId2, IllegalStateException::new));
    }

    @Test
    void repository() {
        DomainRepository<TestEntity, TestId> repository = testDomainService.repository();
        assertNotNull(repository);
    }
}
