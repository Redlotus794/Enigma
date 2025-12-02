package com.rdlts.enigma.ddd.core.test.ejo;

import com.rdlts.enigma.ddd.core.EntityJsonObject;
import com.rdlts.enigma.ddd.core.test.entity.TestDomainEntity;

import javax.annotation.Nullable;

/**
 * PrivateConstructorEntityJsonObject
 *
 * @author wangjialong
 * @since 2025/12/2 13:21
 */
public class PrivateConstructorEntityJsonObject extends EntityJsonObject<TestDomainEntity> {

    private PrivateConstructorEntityJsonObject(TestDomainEntity entity) {
        super(entity);
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
