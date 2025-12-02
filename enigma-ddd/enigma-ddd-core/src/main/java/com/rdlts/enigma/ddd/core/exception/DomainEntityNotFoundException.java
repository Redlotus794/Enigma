package com.rdlts.enigma.ddd.core.exception;

import javax.annotation.Nullable;

/**
 * DomainEntityNotFoundException
 *
 * @author wangjialong
 * @since 2025/12/2 11:21
 */
public class DomainEntityNotFoundException extends EnigmaDDDRuntimeException {

    public static final String ERROR_MESSAGE_TEMPLATE = "Domain Entity %s not found";

    @Nullable
    Object identity;

    public DomainEntityNotFoundException() {
        this(null);
    }

    public DomainEntityNotFoundException(@Nullable Object identity) {
        super(String.format(ERROR_MESSAGE_TEMPLATE, identity));
        this.identity = identity;
    }
}
