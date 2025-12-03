package com.rdlts.enigma.ddd.spring.utils;

import com.rdlts.enigma.ddd.spring.EnigmaSpringBootBasedTest;
import com.rdlts.enigma.ddd.spring.EnigmaSpringContextUtils;
import com.rdlts.enigma.ddd.spring.test.domain.TestValueObject;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertSame;

/**
 * EnigmaSpringContextUtilsTest
 *
 * @see EnigmaSpringContextUtils
 * @author wangjialong
 * @since 2025/12/2 15:08
 */
class EnigmaSpringContextUtilsTest extends EnigmaSpringBootBasedTest {

    @Test
    void testInstance() {
        // 测试单例实例是否正确获取
        EnigmaSpringContextUtils utils = EnigmaSpringContextUtils.instance();
        assertNotNull(utils);
    }

    @Test
    void testApplicationContext() {
        // 测试应用上下文是否正确获取
        EnigmaSpringContextUtils utils = EnigmaSpringContextUtils.instance();
        ApplicationContext context = utils.applicationContext();
        assertNotNull(context);
    }

    @Test
    void testInstanceSingleton() {
        // 测试获取的实例是同一个单例
        EnigmaSpringContextUtils utils1 = EnigmaSpringContextUtils.instance();
        EnigmaSpringContextUtils utils2 = EnigmaSpringContextUtils.instance();
        assertSame(utils1, utils2);
    }

    @Test
    void testLoadAndUnloadBean() {
        // 测试动态加载和卸载Bean
        EnigmaSpringContextUtils utils = EnigmaSpringContextUtils.instance();
        
        // 加载一个Bean
        String beanName = "testVO";
        utils.loadBean(beanName, TestValueObject.class);
        
        // 卸载Bean
        utils.unloadBean(beanName);
    }
}