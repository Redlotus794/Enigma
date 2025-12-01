package com.rdlts.enigma.ddd.spring;

import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

/**
 * EnigmaSpringBootBasedTest
 *
 * @author wangjialong
 * @since 2025/12/1 08:49
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
        classes = EnigmaDDDSpringTestApplication.class)
@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
public class EnigmaSpringBootBasedTest {
}
