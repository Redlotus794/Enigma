package io.github.redlotus794.enigma.ddd.core.test.ejo;

import io.github.redlotus794.enigma.ddd.core.EntityJsonObject;
import io.github.redlotus794.enigma.ddd.core.EntityVersion;
import io.github.redlotus794.enigma.ddd.core.test.domain.TestEntity;
import io.github.redlotus794.enigma.ddd.core.test.domain.TestId;
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
public class TestDomainEntityJson extends EntityJsonObject<TestEntity> {

    String testId;

    long entityVersion;

    public TestDomainEntityJson() {
        super();
    }

    public TestDomainEntityJson(TestEntity entity) {
        super(entity);
    }

    @Override
    public TestEntity toEntity() {
        return new TestEntity(new TestId((testId)),
                new EntityVersion(entityVersion));
    }

    @Override
    public void asJson(TestEntity entity) {
        if (entity != null) {
            this.testId = entity.identity().getId();
            this.entityVersion = entity.version().getVersion();
        }
    }
}
