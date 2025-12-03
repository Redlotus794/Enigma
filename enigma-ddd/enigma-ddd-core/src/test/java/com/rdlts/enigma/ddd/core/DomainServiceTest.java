package com.rdlts.enigma.ddd.core;

import com.rdlts.enigma.ddd.core.test.domain.NotExistsDomainService;
import com.rdlts.enigma.ddd.core.test.domain.TestDomainService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * DomainServiceTest
 *
 * @author wangjialong
 * @since 2025/12/1 16:13
 */
public class DomainServiceTest {

    @Test
    void testDoSomething() {
        Assertions.assertTrue(
                DomainServiceRegistry
                        .instance()
                        .findService(TestDomainService.class)
                        .doSomething());
    }

    @Test
    void testServiceNotFound() {
        Assertions.assertThrows(
                RuntimeException.class,
                () -> DomainServiceRegistry
                        .instance()
                        .findService(NotExistsDomainService.class));
    }

}
