package com.rdlts.enigma.ddd.spring.exception;

import java.io.File;
import java.nio.charset.Charset;

/**
 * EnigmaRebuildDomainEventException
 * 重构事件源异常
 * 
 * @see com.rdlts.enigma.ddd.spring.event.EnigmaDomainEventReproducer#rebuild(File, Charset)
 * @author wangjialong
 * @since 2025/12/4 15:44
 */
public class EnigmaRebuildDomainEventException extends EnigmaDDDSpringRuntimeException {

    public EnigmaRebuildDomainEventException(String s, Throwable throwable) {
        super(s, throwable);
    }
}
