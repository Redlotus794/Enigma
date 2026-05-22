package io.github.redlotus794.enigma.ddd.core;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * <a href="file:///Users/wangjialong/baidusync/domain-driven-design/html/值对象.html">
 * ValueObject 值对象
 * </a>
 * @see io.github.redlotus794.enigma.ddd.core.support.ValueObjectProcessor
 * @author wangjialong
 * @since 2025/11/27 14:59
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface ValueObject {

}
