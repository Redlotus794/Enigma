package com.rdlts.enigma.random.infrastructure;

import com.rdlts.enigma.EnigmaSpringBootBasedTest;
import com.rdlts.enigma.random.domain.EnigmaRandomGenerator;
import com.rdlts.enigma.random.test.model.Person;
import org.jeasy.random.EasyRandomParameters;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * EasyRandomizerTest
 *
 * @author wangjialong
 * @since 2025/12/9 15:38
 */
public class EasyRandomGeneratorTest extends EnigmaSpringBootBasedTest {

    @Autowired
    EnigmaRandomGenerator enigmaRandomGenerator;

    @Test
    void testEasyRandomGenerator() {
        Assertions.assertTrue(enigmaRandomGenerator instanceof EasyRandomGenerator);
        final Person person = enigmaRandomGenerator.nextObject(Person.class);
        Assertions.assertNotNull(person);
        Assertions.assertNotNull(person.getGender());
        Assertions.assertNotNull(person.getAddress());
        Assertions.assertNotNull(person.getAddress().getStreet());
        System.out.println(person);
    }

    @Test
    void testNextObject() {
        EasyRandomGenerator easyRandomizer = new EasyRandomGenerator(new EasyRandomParameters());
        final Person person = easyRandomizer.nextObject(Person.class);
        Assertions.assertNotNull(person);
        Assertions.assertNotNull(person.getGender());
        Assertions.assertNotNull(person.getAddress());
        Assertions.assertNotNull(person.getAddress().getStreet());
        System.out.println(person);
    }

    @Test
    void testNextList() {
        EasyRandomGenerator easyRandomGenerator = new EasyRandomGenerator(new EasyRandomParameters());
        final List<Person> people = easyRandomGenerator.nextList(Person.class, 5);
        Assertions.assertEquals(5, people.size());
    }
}
