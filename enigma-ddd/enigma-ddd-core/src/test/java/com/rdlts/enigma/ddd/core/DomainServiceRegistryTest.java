package com.rdlts.enigma.ddd.core;

import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.*;

/**
 * DomainServiceRegistryTest
 *
 * @see DomainServiceRegistry
 * @see DomainServiceRegistryHolder
 * @author wangjialong
 * @since 2025/12/2 09:27
 */
public class DomainServiceRegistryTest {

    @Test
    void testInstance() {
        final DomainServiceRegistry instance = DomainServiceRegistry.instance();
        Assertions.assertNotNull(instance);
    }


    @Test
    void test_noDomainService_load_shouldThrowException() throws Exception {
        // 保证初始化成功
        Assertions.assertNotNull(DomainServiceRegistryHolder.INSTANCE);
        // 读取services文件，置空，测试load方法
        Collection<FileBackup> fileBackups = new ArrayList<>();
        try {
            final Enumeration<URL> systemResources = ClassLoader.getSystemResources("META-INF/services/com.rdlts.enigma.ddd.core.DomainServiceRegistry");
            while (systemResources.hasMoreElements()) {
                final File file = new File(systemResources.nextElement().toURI());
                final FileBackup fileBackup = new FileBackup(file, FileUtils.readFileToString(file));
                fileBackup.cleanup();
                fileBackups.add(fileBackup);
            }

            final ServiceLoader<DomainServiceRegistry> load = ServiceLoader.load(DomainServiceRegistry.class);
            Collection<DomainServiceRegistry> registries = new ArrayList<>();
            for (DomainServiceRegistry registry : load) {
                registries.add(registry);
            }
            Assertions.assertEquals(0, registries.size());
            Assertions.assertThrows(IllegalStateException.class, DomainServiceRegistryHolder::load);
        } finally {
            fileBackups.forEach(FileBackup::restore);
        }
    }


    // 辅助类用于备份文件
    private static class FileBackup {
        final File file;
        final String content;

        FileBackup(File file, String content) {
            this.file = file;
            this.content = content;
        }

        public void restore() {
            try {
                FileUtils.writeLines(file, Collections.singletonList(content));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        public void cleanup() {
            try {
                FileUtils.writeLines(file, Collections.singletonList(""));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
