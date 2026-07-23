package io.github.redlotus794.enigma.ddd.core.exception;

import org.jspecify.annotations.Nullable;

/**
 * DomainAggregateRootNotFoundException
 *
 * @author wangjialong
 * @since 2025/12/2 11:21
 */
public class DomainAggregateRootNotFoundException extends EnigmaDDDRuntimeException {

    public static final String ERROR_MESSAGE_TEMPLATE = "Domain Aggregate Root %s not found";

    @Nullable
    Object identity;

    private DomainAggregateRootNotFoundException() {
        this(null);
    }

    public DomainAggregateRootNotFoundException(@Nullable Object identity) {
        super(String.format(ERROR_MESSAGE_TEMPLATE, identity));
        this.identity = identity;
    }
}
