package com.rdlts.enigma;

import com.rdlts.enigma.common.constant.ProfileConstant;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

/**
 * EnigmaSpringBootBasedTest
 *
 * @author wangjialong
 * @since 2025/12/11 09:54
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
        classes = EnigmaTestSpringBootApplication.class)
@ExtendWith(SpringExtension.class)
@ActiveProfiles(ProfileConstant.TEST)
public class EnigmaSpringBootBasedTest {
}
