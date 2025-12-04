package com.rdlts.enigma.ddd.spring.test;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * EnigmaDDDSpringTestApplication
 *
 * @author wangjialong
 * @since 2025/12/1 08:46
 */
@SpringBootApplication
public class EnigmaDDDSpringTestApplication {

    public static void main(String[] args) {
        System.setProperty("file.encoding", "UTF-8");
        SpringApplication.run(EnigmaDDDSpringTestApplication.class, args);
    }
}
