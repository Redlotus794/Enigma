package com.rdlts.enigma.ddd.core;

import com.rdlts.enigma.ddd.core.exception.EnigmaDDDRuntimeException;
import com.rdlts.enigma.ddd.core.test.ejo.PrivateConstructorEntityJsonObject;
import com.rdlts.enigma.ddd.core.test.ejo.TestDomainEntityJson;
import com.rdlts.enigma.ddd.core.test.domain.TestDomainEntity;
import com.rdlts.enigma.ddd.core.test.domain.TestId;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import javax.annotation.Nullable;

import static org.junit.jupiter.api.Assertions.*;

class EntityJsonObjectTest {

    @Test
    void testConstructor() {
        TestDomainEntityJson testDomainEntityJson = new TestDomainEntityJson();
        Assertions.assertNull(testDomainEntityJson.getTestId());
        Assertions.assertEquals(0L, testDomainEntityJson.getEntityVersion());
    }


    @Test
    void toEntity() {
        TestDomainEntity testDomainEntity = new TestDomainEntity(
                new TestId("testId"),
                new EntityVersion(1L)
        );
        TestDomainEntityJson testDomainEntityJson = new TestDomainEntityJson(testDomainEntity);
        final TestDomainEntity entity = TestDomainEntityJson.toEntity(testDomainEntityJson);
        Assertions.assertEquals("testId", entity.getTestId().getId());
        Assertions.assertEquals(1L, entity.getEntityVersion().getVersion());
    }

    @Test
    void asJson() {
        TestDomainEntity testDomainEntity = new TestDomainEntity(
                new TestId("testId"),
                new EntityVersion(1L)
        );
        TestDomainEntityJson testDomainEntityJson = new TestDomainEntityJson();
        testDomainEntityJson.asJson(testDomainEntity);
        Assertions.assertEquals("testId", testDomainEntityJson.getTestId());
        Assertions.assertEquals(1L, testDomainEntityJson.getEntityVersion());
    }

    @Test
    void toEntityJson() {
        TestDomainEntity testDomainEntity = new TestDomainEntity(
                new TestId("testId"),
                new EntityVersion(1L)
        );
        TestDomainEntityJson testDomainEntityJson = TestDomainEntityJson.toEntityJson(TestDomainEntityJson.class, testDomainEntity);
        Assertions.assertEquals("testId", testDomainEntityJson.getTestId());
        Assertions.assertEquals(1L, testDomainEntityJson.getEntityVersion());
    }

    // 测试构造函数不存在的情况
    @Test
    public void testToEntityJson_NoSuchMethod_ExceptionThrown() {
        // Given
        TestDomainEntity entity = new TestDomainEntity(
                new TestId("testId"),
                new EntityVersion(1L)
        );

        // When & Then
        EnigmaDDDRuntimeException exception = assertThrows(
                EnigmaDDDRuntimeException.class,
                () -> EntityJsonObject.toEntityJson(NoConstructorEntityJsonObject.class, entity)
        );

        assertTrue(exception.getMessage().contains("No such constructor found"));
    }

    @Test
    public void testToEntityJson_InvocationTargetException_ExceptionThrown() {
        // Given
        TestDomainEntity entity = new TestDomainEntity(
                new TestId("testId"),
                new EntityVersion(1L)
        );
        final EnigmaDDDRuntimeException exception = assertThrows(EnigmaDDDRuntimeException.class,
                () -> TestDomainEntityJson.toEntityJson(InvocationTargetExceptionEntityJsonObject.class, entity));

        assertTrue(exception.getMessage().contains("Parameters not match for constructor in"));
    }

    @Test
    void testToEntityJson_AbstractClass_ExceptionThrown() {
        // Given
        TestDomainEntity entity = new TestDomainEntity(
                new TestId("testId"),
                new EntityVersion(1L)
        );

        // When & Then
        EnigmaDDDRuntimeException exception = assertThrows(
                EnigmaDDDRuntimeException.class,
                () -> EntityJsonObject.toEntityJson(AbstractEntityJsonObject.class, entity)
        );

        assertTrue(exception.getMessage().contains("Cannot instantiate"));
        assertTrue(exception.getMessage().contains("abstract class"));
    }

    @Test
    void testToEntityJson_IllegalAccess_ExceptionThrown() {
        // Given
        TestDomainEntity entity = new TestDomainEntity(
                new TestId("testId"),
                new EntityVersion(1L)
        );

        // When & Then
        EnigmaDDDRuntimeException exception = assertThrows(
                EnigmaDDDRuntimeException.class,
                () -> EntityJsonObject.toEntityJson(PrivateConstructorEntityJsonObject.class, entity)
        );

        assertTrue(exception.getMessage().contains("Cannot access constructor"));
    }
    @Test
    void testToEntity() {
        TestDomainEntityJson testDomainEntityJson = new TestDomainEntityJson();
        testDomainEntityJson.setTestId("test-id-1");
        testDomainEntityJson.setEntityVersion(1L);
        TestDomainEntity testDomainEntity = testDomainEntityJson.toEntity();
        Assertions.assertEquals("test-id-1", testDomainEntity.identity().getId());
        Assertions.assertEquals(1L, testDomainEntity.version().getVersion());
    }

    // 缺少构造函数的 EntityJsonObject 实现
    @SuppressWarnings("unused")
    static class NoConstructorEntityJsonObject extends EntityJsonObject<TestDomainEntity> {
        public NoConstructorEntityJsonObject(String dummy) {
            // 只有一个带String参数的构造函数，没有接受TestEntity的构造函数
        }

        @Override
        public TestDomainEntity toEntity() {
            return null;
        }

        @Override
        public void asJson(@Nullable TestDomainEntity entity) {
            // Do nothing
        }
    }

    static class InvocationTargetExceptionEntityJsonObject extends EntityJsonObject<TestDomainEntity> {
        public InvocationTargetExceptionEntityJsonObject(TestDomainEntity entity) {
            super(entity);
            throw new RuntimeException("模拟构造函数异常");
        }

        @Override
        public TestDomainEntity toEntity() {
            return null;
        }

        @Override
        public void asJson(@Nullable TestDomainEntity entity) {
            // 模拟抛出 InvocationTargetException
//            throw new InvocationTargetException(new Exception("InvocationTargetException"));
        }
    }

    // 抽象的 EntityJsonObject 实现
    static abstract class AbstractEntityJsonObject extends EntityJsonObject<TestDomainEntity> {
        public AbstractEntityJsonObject(TestDomainEntity entity) {
            super(entity);
        }

        @Override
        public abstract TestDomainEntity toEntity();

        @Override
        public abstract void asJson(@Nullable TestDomainEntity entity);
    }
}