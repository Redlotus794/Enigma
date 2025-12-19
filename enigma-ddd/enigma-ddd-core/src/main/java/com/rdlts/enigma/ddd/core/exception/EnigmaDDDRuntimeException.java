package com.rdlts.enigma.ddd.core.exception;

import java.io.Serializable;

/**
 * EnigmaDDDRuntimeException
 *
 * @author wangjialong
 * @since 2025/12/1 15:37
 */
public class EnigmaDDDRuntimeException extends RuntimeException implements Serializable {

    private static final long serialVersionUID = 1L;

    public EnigmaDDDRuntimeException() {
    }

    public EnigmaDDDRuntimeException(String s) {
        super(s);
    }

    public EnigmaDDDRuntimeException(String s, Throwable throwable) {
        super(s, throwable);
    }

    public EnigmaDDDRuntimeException(Throwable throwable) {
        super(throwable);
    }

    public EnigmaDDDRuntimeException(String s, Throwable throwable, boolean b, boolean b1) {
        super(s, throwable, b, b1);
    }
}
