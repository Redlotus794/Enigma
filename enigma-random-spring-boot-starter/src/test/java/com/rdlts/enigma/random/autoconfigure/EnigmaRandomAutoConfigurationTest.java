package com.rdlts.enigma.random.autoconfigure;

import com.rdlts.enigma.EnigmaTestSpringBootApplication;
import com.rdlts.enigma.common.constant.ProfileConstant;
import com.rdlts.enigma.random.domain.EnigmaRandomGenerator;
import com.rdlts.enigma.random.test.TestApplication;
import com.rdlts.enigma.tools.spring.EnigmaSpringContextUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

/**
 * EnigmaRandomAutoConfigurationTest
 *
 * @author wangjialong
 * @since 2025/12/16 14:43
 */
@SpringBootTest(classes = TestApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ExtendWith(SpringExtension.class)
@ActiveProfiles(ProfileConstant.TEST)
public class EnigmaRandomAutoConfigurationTest {

    /**
     * 测试应用是否加载自动配置
     */
    @Test
    void testLoadSpringFactories() {
        final EnigmaRandomGenerator bean = EnigmaSpringContextUtils
                .instance()
                .getApplicationContext()
                .getBean(EnigmaRandomGenerator.class);
        Assertions.assertNotNull(bean);
    }
}
