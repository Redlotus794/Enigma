package com.rdlts.enigma.ddd.spring;

import com.alibaba.fastjson2.JSON;
import com.rdlts.enigma.ddd.spring.test.domain.TestVO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * DemoTest
 *
 * @author wangjialong
 * @since 2025/12/1 08:51
 */
public class DemoTest {

    @Test
    void test() {
        TestVO testVO = new TestVO("test");
        Assertions.assertNotNull(testVO);
        final TestVO testVO1 = JSON.parseObject("{\"val\":\"test\"}", TestVO.class);
        Assertions.assertNotNull(testVO1);
    }
}
