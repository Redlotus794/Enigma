package io.github.redlotus794.enigma.test.core;

import io.github.redlotus794.enigma.EnigmaTestSpringBootApplication;
import io.github.redlotus794.enigma.test.annotation.EnigmaCucumberSpringBootTest;

/**
 * EnigmaCucumberSpringBootBasedTest
 * Cucumber 与 Spring Boot 集成基础测试配置。
 * 默认使用 Enigma 提供的测试启动类；业务项目如需切换为自己的启动类，
 * 可直接在桥接类上使用 {@link EnigmaCucumberSpringBootTest} 并指定 classes。
 *
 * @author wangjialong
 * @since 2026/05/11 14:30
 */
@EnigmaCucumberSpringBootTest(classes = EnigmaTestSpringBootApplication.class)
public abstract class EnigmaCucumberSpringBootBasedTest {
}

