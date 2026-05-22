package io.github.redlotus794.enigma.random.test.easyrandom;

import io.github.redlotus794.enigma.random.domain.EnigmaRandomizer;
import io.github.redlotus794.enigma.random.test.model.Employee;
import io.github.redlotus794.enigma.random.test.model.Gender;
import org.jeasy.random.api.Randomizer;

/**
 * EmployeeRandomizer
 * 固定的随机生成器，生成一个固定的对象用来测试
 *
 * @author wangjialong
 * @since 2025/12/10 10:28
 */
public class EmployeeRandomizer implements EnigmaRandomizer, Randomizer<Employee> {

    @Override
    public Employee getRandomValue() {
        return Employee.builder()
                .name("wangjialong")
                .gender(Gender.MALE)
                .build();
    }
}
