package com.rdlts.enigma.ddd.core.test.domain;

import com.rdlts.enigma.ddd.core.DomainService;
import com.rdlts.enigma.ddd.core.DomainServiceRegistry;

import javax.annotation.Nonnull;
import java.util.HashMap;
import java.util.Map;

/**
 * TestDomainServiceRegistryImpl
 *
 * @author wangjialong
 * @since 2025/12/1 16:15
 */
@SuppressWarnings("all")
public class TestDomainServiceRegistryImpl implements DomainServiceRegistry {

    private static final TestDomainServiceRegistryImpl INSTANCE = new TestDomainServiceRegistryImpl();

    private static Map<Class<? extends DomainService>, DomainService> SERVICES = new HashMap<>();

    static {
        SERVICES.put(TestDomainService.class, new TestDomainService());
    }

    @Override
    public DomainServiceRegistry getInstance() {
        return TestDomainServiceRegistryImpl.INSTANCE;
    }

    @Override
    @Nonnull
    public <T extends DomainService> T findService(Class<T> clazz) {
        if (!SERVICES.containsKey(clazz)) {
            throw new RuntimeException("未找到服务: " + clazz.getName());
        }
        return (T) SERVICES.get(clazz);
    }
}
