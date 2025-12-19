package com.rdlts.enigma.ddd.spring.exception;

/**
 * EnigmaDDDSpringRuntimeException
 *
 * @author wangjialong
 * @since 2025/12/4 10:45
 */
public class EnigmaDDDSpringRuntimeException extends RuntimeException {

    public EnigmaDDDSpringRuntimeException() {
    }

    public EnigmaDDDSpringRuntimeException(String s) {
        super(s);
    }

    public EnigmaDDDSpringRuntimeException(String s, Throwable throwable) {
        super(s, throwable);
    }

    public EnigmaDDDSpringRuntimeException(Throwable throwable) {
        super(throwable);
    }

    public EnigmaDDDSpringRuntimeException(String s, Throwable throwable, boolean b, boolean b1) {
        super(s, throwable, b, b1);
    }
}
