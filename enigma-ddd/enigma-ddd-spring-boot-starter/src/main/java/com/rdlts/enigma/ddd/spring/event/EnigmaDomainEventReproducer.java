package com.rdlts.enigma.ddd.spring.event;

import com.rdlts.enigma.ddd.core.DomainEventReproducer;
import com.rdlts.enigma.ddd.core.DomainEventReproducible;
import com.rdlts.enigma.ddd.spring.exception.EnigmaDDDSpringRuntimeException;
import com.rdlts.enigma.ddd.spring.exception.EnigmaRebuildDomainEventException;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;

/**
 * EnigmaDomainEventReproducer
 * 领域事件重放，Enigma实现。
 *
 * @see DomainEventReproducer
 * @author wangjialong
 * @since 2025/12/3 16:14
 */
@Component
@Log4j2
public class EnigmaDomainEventReproducer implements DomainEventReproducer {

    /**
     * rebuild
     * 根据文件内容重新构建事件源
     *
     * @param file    JSON file Event的JSON格式
     * @param charset Charset 文件编码方式
     * @param <T>     事件类型
     * @return DomainEventReproducible
     * @throws ClassNotFoundException 反射类型失败
     * @see com.rdlts.enigma.ddd.core.DomainEvent
     */
    @Override
    @SuppressWarnings("all")
    public <T extends DomainEventReproducible> T rebuild(File file, Charset charset) throws IOException, ClassNotFoundException {
        try {
            if (file == null) {
                throw new EnigmaDDDSpringRuntimeException("Rebuild file is empty!");
            }
            return (T) DomainEventStore.rebuildBy(file, charset);
        } catch (Exception e) {
            log.error("重构领域事件失败, {} | {}", e.getCause(), e.getMessage());
            throw new EnigmaRebuildDomainEventException("Fail to rebuild domain event", e);
        }
    }
}
