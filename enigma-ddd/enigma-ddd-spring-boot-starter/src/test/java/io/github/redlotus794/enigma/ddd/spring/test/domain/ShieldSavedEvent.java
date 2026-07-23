package io.github.redlotus794.enigma.ddd.spring.test.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.github.redlotus794.enigma.ddd.core.event.DomainEvent;
import org.apache.commons.lang3.RandomStringUtils;

import org.jspecify.annotations.NonNull;

/**
 * ShieldSavedEvent
 *
 * @author wangjialong
 * @since 2025/12/3 13:56
 */
@JsonIgnoreProperties({"persistable"})
public class ShieldSavedEvent extends DomainEvent<Shield> {

    public ShieldSavedEvent() {
    }

    public ShieldSavedEvent(@NonNull Shield eventContent) {
        super(eventContent);
    }

    /**
     * 测试方法
     */
    public static ShieldSavedEvent next() {
        return new ShieldSavedEvent(
                Shield.builder().shieldId(new ShieldId(RandomStringUtils.randomAlphanumeric(6))).build()
        );
    }
}
