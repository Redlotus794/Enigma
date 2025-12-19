package com.rdlts.enigma.random.test.model;

import lombok.Data;
import lombok.ToString;

/**
 * Person
 * Easy Random Examples
 * <img src="https://raw.githubusercontent.com/wiki/j-easy/easy-random/images/person.png">
 *
 * @author wangjialong
 * @since 2025/12/9 15:34
 */
@Data
@ToString
public class Person {

    private String email;

    private String lastName;

    private Gender gender;

    private String firstName;

    private Address address;

}
