package io.github.redlotus794.enigma.test.annotation;

import io.github.redlotus794.enigma.common.constant.ProfileConstant;
import io.cucumber.spring.CucumberContextConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.annotation.AliasFor;
import org.springframework.test.context.ActiveProfiles;

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * EnigmaCucumberSpringBootTest
 * 用于桥接 Cucumber 与 Spring Boot 测试上下文，并允许业务项目显式指定自己的启动类。
 *
 * @author wangjialong
 * @since 2026/05/11 15:30
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@CucumberContextConfiguration
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles(ProfileConstant.TEST)
public @interface EnigmaCucumberSpringBootTest {

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
