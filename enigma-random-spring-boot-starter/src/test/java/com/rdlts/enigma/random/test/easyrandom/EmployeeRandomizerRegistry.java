package com.rdlts.enigma.random.test.easyrandom;

import com.rdlts.enigma.random.domain.EnigmaRandomizerRegistry;
import com.rdlts.enigma.random.test.model.Employee;
import org.jeasy.random.EasyRandomParameters;
import org.jeasy.random.api.Randomizer;
import org.jeasy.random.api.RandomizerRegistry;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;

/**
 * EmployeeRandomizer
 *
 * @author wangjialong
 * @since 2025/12/10 10:26
 */
@Component
public class EmployeeRandomizerRegistry implements EnigmaRandomizerRegistry, RandomizerRegistry {

    EmployeeRandomizer employeeRandomizer = new EmployeeRandomizer();

    @Override
    public void init(EasyRandomParameters parameters) {
    }

    @Override
    public Randomizer<?> getRandomizer(Field field) {
        if (field.getType() == Employee.class) {
            return employeeRandomizer;
        }
        return null;
    }

    @Override
    public Randomizer<?> getRandomizer(Class<?> type) {
        if (type == Employee.class) {
            return employeeRandomizer;
        }
        return null;
    }
}
