package com.rdlts.enigma.ddd.core.test.ejo;

import com.rdlts.enigma.ddd.core.EntityJsonObject;
import com.rdlts.enigma.ddd.core.EntityVersion;
import com.rdlts.enigma.ddd.core.test.domain.TestDomainEntity;
import com.rdlts.enigma.ddd.core.test.domain.TestId;
import lombok.*;

/**
 * TestDomainEntityJson
 *
 * @author wangjialong
 * @since 2025/12/2 12:20
 */
@Data
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Builder
public class TestDomainEntityJson extends EntityJsonObject<TestDomainEntity> {

    String testId;

    long entityVersion;

    public TestDomainEntityJson() {
        super();
    }

    public TestDomainEntityJson(TestDomainEntity entity) {
        super(entity);
    }

    @Override
    public TestDomainEntity toEntity() {
        return new TestDomainEntity(new TestId((testId)),
                new EntityVersion(entityVersion));
    }

    @Override
    public void asJson(TestDomainEntity entity) {
        if (entity != null) {
            this.testId = entity.identity().getId();
            this.entityVersion = entity.version().getVersion();
        }
    }
}
