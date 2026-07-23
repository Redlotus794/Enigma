package io.github.redlotus794.enigma.ddd.core.test.ejo;

import io.github.redlotus794.enigma.ddd.core.EntityJsonObject;
import io.github.redlotus794.enigma.ddd.core.test.domain.TestEntity;

import org.jspecify.annotations.Nullable;

/**
 * PrivateConstructorEntityJsonObject
 *
 * @author wangjialong
 * @since 2025/12/2 13:21
 */
public class PrivateConstructorEntityJsonObject extends EntityJsonObject<TestEntity> {

    private PrivateConstructorEntityJsonObject(TestEntity entity) {
        super(entity);
    }

    @Override
    public TestEntity toEntity() {
        return null;
    }

    @Override
    public void asJson(@Nullable TestEntity entity) {
        // Do nothing
    }
}
