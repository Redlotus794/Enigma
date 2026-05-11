package com.rdlts.enigma;

import com.rdlts.enigma.test.annotation.EnigmaWebSpringBootTest;

/**
 * EnigmaSpringBootBasedTest
 * 默认 Web Spring Boot 测试基类。
 * 业务项目如需绑定自己的启动类或 profile，可直接使用 {@link EnigmaWebSpringBootTest}。
 *
 * @author wangjialong
 * @since 2025/12/11 09:54
 */
@EnigmaWebSpringBootTest(classes = EnigmaTestSpringBootApplication.class)
public class EnigmaSpringBootBasedTest {
}
