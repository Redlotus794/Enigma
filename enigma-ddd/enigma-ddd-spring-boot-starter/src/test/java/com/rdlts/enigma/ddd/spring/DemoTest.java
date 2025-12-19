package com.rdlts.enigma.ddd.spring;

import com.alibaba.fastjson2.JSON;
import com.rdlts.enigma.ddd.spring.test.domain.TestValueObject;
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
        TestValueObject testValueObject = new TestValueObject("test");
        Assertions.assertNotNull(testValueObject);
        final TestValueObject testValueObject1 = JSON.parseObject("{\"val\":\"test\"}", TestValueObject.class);
        Assertions.assertNotNull(testValueObject1);
    }
}
