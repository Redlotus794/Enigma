package io.github.redlotus794.enigma.ddd.spring.autoconfigure.bootstrap;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * ConfigurationApplication
 * 不能放在autoconfigure下，因为autoconfigure的配置文件在这个包下，会加载到。
 * @author wangjialong
 * @since 2025/12/18 10:52
 */
@SpringBootApplication
public class ConfigurationApplication {

    public static void main(String[] args) {
        System.setProperty("file.encoding", "UTF-8");
        SpringApplication.run(ConfigurationApplication.class, args);
    }
}
