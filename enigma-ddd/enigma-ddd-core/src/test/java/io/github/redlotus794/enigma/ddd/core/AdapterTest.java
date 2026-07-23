package io.github.redlotus794.enigma.ddd.core;

import io.github.redlotus794.enigma.ddd.core.test.domain.TestEntity;
import io.github.redlotus794.enigma.ddd.core.test.domain.TestId;
import io.github.redlotus794.enigma.ddd.core.test.po.TestDomainEntityPO;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AdapterTest {

    @Test
    void testAdapt() {
        TestEntity testDomainEntity = TestEntity.builder()
                .testId(new TestId("test-id-1"))
                .entityVersion(new EntityVersion(1))
                .build();

        final TestDomainEntityPO adapt = new TestDomainEntityPOAdapter().adapt(testDomainEntity);
        assertEquals(testDomainEntity.getTestId().getId(), adapt.getTest_id());
        assertEquals(testDomainEntity.getEntityVersion().getVersion(), adapt.getEntity_version());
    }

    private static class TestDomainEntityPOAdapter implements Adapter<TestEntity, TestDomainEntityPO> {

        @Override
        public TestDomainEntityPO adapt(TestEntity testDomainEntity) {
            return new TestDomainEntityPO(
                    testDomainEntity.getTestId().getId(),
                    testDomainEntity.getEntityVersion().getVersion());
        }
    }

}