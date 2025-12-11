package com.rdlts.enigma.random.test.model;

import lombok.Data;
import lombok.ToString;

/**
 * Address
 *
 * @author wangjialong
 * @since 2025/12/9 15:37
 */
@Data
@ToString
public class Address {

    Street street;

    String country;

    String city;

    String zipCode;
}
