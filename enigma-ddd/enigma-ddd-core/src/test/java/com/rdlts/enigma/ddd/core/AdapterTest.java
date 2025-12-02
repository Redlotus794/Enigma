package com.rdlts.enigma.ddd.core;

import com.rdlts.enigma.ddd.core.test.entity.TestDomainEntity;
import com.rdlts.enigma.ddd.core.test.po.TestDomainEntityPO;
import com.rdlts.enigma.ddd.core.test.valueobject.TestId;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AdapterTest {

    @Test
    void testAdapt() {
        TestDomainEntity testDomainEntity = TestDomainEntity.builder()
                .testId(new TestId("test-id-1"))
                .entityVersion(new EntityVersion(1))
                .build();

        final TestDomainEntityPO adapt = new TestDomainEntityPOAdapter().adapt(testDomainEntity);
        assertEquals(testDomainEntity.getTestId().getId(), adapt.getTest_id());
        assertEquals(testDomainEntity.getEntityVersion().getVersion(), adapt.getEntity_version());
    }

    private static class TestDomainEntityPOAdapter implements Adapter<TestDomainEntity, TestDomainEntityPO> {

        @Override
        public TestDomainEntityPO adapt(TestDomainEntity testDomainEntity) {
            return new TestDomainEntityPO(
                    testDomainEntity.getTestId().getId(),
                    testDomainEntity.getEntityVersion().getVersion());
        }
    }

}