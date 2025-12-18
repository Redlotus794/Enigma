package com.rdlts.enigma.ddd.spring.exception;

/**
 * EnigmaSaveNullObjectException
 *
 * @author wangjialong
 * @since 2025/12/18 14:02
 */
public class EnigmaSaveNullObjectException extends EnigmaDDDSpringRuntimeException {

    public EnigmaSaveNullObjectException() {
        super("Save null object exception!");
    }
}
