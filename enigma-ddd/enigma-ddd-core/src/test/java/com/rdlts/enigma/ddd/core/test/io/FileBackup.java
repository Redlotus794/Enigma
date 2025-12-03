package com.rdlts.enigma.ddd.core.test.io;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.Collections;

/**
 * po
 *
 * @author wangjialong
 * @since 2025/12/3 09:11
 */
public class FileBackup {
    // 辅助类用于备份文件
    final File file;
    final String content;

    public FileBackup(File file, String content) {
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
