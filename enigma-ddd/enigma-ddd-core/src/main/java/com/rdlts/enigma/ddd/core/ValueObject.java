package com.rdlts.enigma.ddd.core;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * <a href="https://wcnn2j4xsnan.feishu.cn/wiki/XDWYwYtZyiZptjkooSMcM3rynNf?fromScene=spaceOverview">
 * ValueObject 值对象
 * </a>
 * @see com.rdlts.enigma.ddd.core.support.ValueObjectProcessor
 * @author wangjialong
 * @since 2025/11/27 14:59
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface ValueObject {

}
