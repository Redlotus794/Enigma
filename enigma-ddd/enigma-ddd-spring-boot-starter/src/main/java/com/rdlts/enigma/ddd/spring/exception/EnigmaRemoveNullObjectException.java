package com.rdlts.enigma.ddd.spring.exception;

/**
 * EnigmaRemoveNullObjectException
 *
 * @author wangjialong
 * @since 2025/12/18 14:03
 */
public class EnigmaRemoveNullObjectException extends EnigmaDDDSpringRuntimeException {

    public EnigmaRemoveNullObjectException() {
        super("Remove null object exception!");
    }

}
