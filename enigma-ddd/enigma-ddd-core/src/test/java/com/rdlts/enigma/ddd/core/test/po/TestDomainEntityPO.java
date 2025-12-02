package com.rdlts.enigma.ddd.core.test.po;

import lombok.*;

/**
 * TestDomainEntityPO
 *
 * @author wangjialong
 * @since 2025/12/2 12:03
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Builder
public class TestDomainEntityPO {

    String test_id;

    long entity_version;
}
