package com.rdlts.enigma.random.test.model;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

/**
 * Employee
 * 实体对象，有特定的随机生成器
 *
 * @author wangjialong
 * @since 2025/12/10 10:14
 */
@Data
@ToString
@Builder
public class Employee {

    String name;

    Gender gender;


}
