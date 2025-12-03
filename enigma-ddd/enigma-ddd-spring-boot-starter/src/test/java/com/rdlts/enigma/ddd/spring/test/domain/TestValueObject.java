package com.rdlts.enigma.ddd.spring.test.domain;

import com.rdlts.enigma.ddd.core.ValueObject;
import lombok.*;

/**
 * TestVO
 *
 * @author wangjialong
 * @since 2025/12/1 08:28
 */
@ValueObject
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
@ToString
@SuppressWarnings("all")
public class TestValueObject {

    private String val;

    /**
     * Build编译器报警：
     * ValueObject 类 [TestVO] 包含 setter 方法 [setVal]，这违反了值对象不可变原则
     *
     * @see com.rdlts.enigma.ddd.core.support.ValueObjectProcessor
     */
    public void setVal() {
        this.val = val;
    }
}
