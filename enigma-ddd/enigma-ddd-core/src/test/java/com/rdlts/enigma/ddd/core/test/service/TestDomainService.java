package com.rdlts.enigma.ddd.core.test.service;

import com.rdlts.enigma.ddd.core.DomainService;

/**
 * TestDomainService
 *
 * @author wangjialong
 * @since 2025/12/1 16:13
 */
public class TestDomainService implements DomainService {

    public boolean doSomething() {
        System.out.println("Doing something in TestDomainService");
        return true;
    }
}
