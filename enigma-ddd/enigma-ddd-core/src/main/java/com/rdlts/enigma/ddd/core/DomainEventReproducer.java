package com.rdlts.enigma.ddd.core;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;

/**
 * DomainEventReproducer
 * 事件源重放
 *
 * @author wangjialong
 * @since 2025/12/3 09:41
 */
public interface DomainEventReproducer {

    /**
     * 根据文件 重放事件源对象
     *
     * @param file JSON file Event的JSON格式
     * @param charset Charset 文件编码方式
     * @return T 事件
     */
    default <T extends DomainEventReproducible> T rebuild(File file, Charset charset) throws IOException, ClassNotFoundException {
        throw new UnsupportedOperationException("DomainEventReproducer rebuild method is not implemented.");
    }
}
