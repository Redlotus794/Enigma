package io.github.redlotus794.enigma.random.test;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * TestApplication
 *
 * @author wangjialong
 * @since 2025/12/16 14:45
 */
@SpringBootApplication
@ComponentScan(basePackages = {"io.github.redlotus794.enigma.tools.spring"})
public class TestApplication {

    public static void main(String[] args) {
        SpringApplication.run(TestApplication.class, args);
    }
}
