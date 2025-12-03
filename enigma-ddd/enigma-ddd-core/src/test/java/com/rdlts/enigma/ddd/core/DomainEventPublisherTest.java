package com.rdlts.enigma.ddd.core;

import com.rdlts.enigma.ddd.core.test.io.FileBackup;
import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Enumeration;
import java.util.ServiceLoader;

class DomainEventPublisherTest {

    @Test
    void testInstance() {
        final DomainEventPublisher instance = DomainEventPublisher.instance();
        Assertions.assertNotNull(instance);
    }

    @Test
    void test_NoPublisherImpl_load_shouldThrowException() throws Exception {
        // 保证初始化成功
        Assertions.assertNotNull(DomainEventPublisherHolder.INSTANCE);
        // 读取services文件，置空，测试load方法
        Collection<FileBackup> fileBackups = new ArrayList<>();
        try {
            final Enumeration<URL> systemResources = ClassLoader.getSystemResources("META-INF/services/com.rdlts.enigma.ddd.core.DomainEventPublisher");
            while (systemResources.hasMoreElements()) {
                final File file = new File(systemResources.nextElement().toURI());
                final FileBackup fileBackup = new FileBackup(file, FileUtils.readFileToString(file));
                fileBackup.cleanup();
                fileBackups.add(fileBackup);
            }

            final ServiceLoader<DomainEventPublisher> load = ServiceLoader.load(DomainEventPublisher.class);
            Collection<DomainEventPublisher> registries = new ArrayList<>();
            for (DomainEventPublisher registry : load) {
                registries.add(registry);
            }
            Assertions.assertEquals(0, registries.size());
            Assertions.assertThrows(IllegalStateException.class, DomainEventPublisherHolder::load);
        } finally {
            fileBackups.forEach(FileBackup::restore);
        }
    }
}