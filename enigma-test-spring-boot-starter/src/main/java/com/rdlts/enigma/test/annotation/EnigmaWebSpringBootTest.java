package com.rdlts.enigma.test.annotation;

import com.rdlts.enigma.common.constant.ProfileConstant;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.annotation.AliasFor;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * EnigmaWebSpringBootTest
 * 用于桥接 Spring Boot Web 测试上下文，并允许业务项目显式指定启动类与激活的 profile。
 *
 * @author wangjialong
 * @since 2026/05/11 15:45
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles(ProfileConstant.TEST)
public @interface EnigmaWebSpringBootTest {

    /**
     * 映射到 {@link SpringBootTest#classes()}，允许业务项目配置自己的 Spring Boot 启动类。
     *
     * @return Spring Boot 测试启动类
     */
    @AliasFor(annotation = SpringBootTest.class, attribute = "classes")
    Class<?>[] classes() default {};

    /**
     * 映射到 {@link ActiveProfiles#profiles()}，允许业务项目配置激活的 profile。
     * 默认使用测试环境 profile。
     *
     * @return 激活的 Spring profiles
     */
    @AliasFor(annotation = ActiveProfiles.class, attribute = "profiles")
    String[] activeProfiles() default {ProfileConstant.TEST};
}
