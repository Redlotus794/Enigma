package com.rdlts.enigma.ddd.spring.test.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.rdlts.enigma.ddd.core.DomainEvent;
import net.bytebuddy.utility.RandomString;
import org.apache.commons.lang3.RandomStringUtils;

import javax.annotation.Nonnull;

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

    public ShieldSavedEvent(@Nonnull Shield eventContent) {
        super(eventContent);
    }

    /**
     * 测试方法
     */
    public static ShieldSavedEvent next() {
        return new ShieldSavedEvent(
                Shield.builder().shieldId(new ShieldId(RandomStringUtils.random(6))).build()
        );
    }
}
