package com.rdlts.enigma.ddd.core.support;

import com.google.testing.compile.Compilation;
import com.google.testing.compile.Compiler;
import org.junit.jupiter.api.Test;

import javax.tools.JavaFileObject;

import static com.google.testing.compile.CompilationSubject.assertThat;
import static com.google.testing.compile.JavaFileObjects.forSourceLines;

/**
 * 测试ValueObjectProcessor
 *
 * @see ValueObjectProcessor
 */
class ValueObjectProcessorTest {

    @Test
    void test_valueObjectWithoutSetter_compile_withoutWarning() {
        JavaFileObject source = forSourceLines(
                "test.ValidValueObject",
                "package test;",
                "",
                "import com.rdlts.enigma.ddd.core.ValueObject;",
                "",
                "@ValueObject",
                "public class ValidValueObject {",
                "  private String value;",
                "",
                "  public ValidValueObject(String value) {",
                "    this.value = value;",
                "  }",
                "",
                "  public String getValue() {",
                "    return value;",
                "  }",
                "}"
        );

        Compilation compilation = Compiler.javac()
                .withProcessors(new ValueObjectProcessor())
                .compile(source);

        assertThat(compilation).succeededWithoutWarnings();
    }

    @Test
    void test_valueObjectWithSetter_compile_generateWarning() {
        JavaFileObject source = forSourceLines(
                "test.InvalidValueObject",
                "package test;",
                "",
                "import com.rdlts.enigma.ddd.core.ValueObject;",
                "",
                "@ValueObject",
                "public class InvalidValueObject {",
                "  private String value;",
                "",
                "  public void setValue(String value) {",
                "    this.value = value;",
                "  }",
                "",
                "  public String getValue() {",
                "    return value;",
                "  }",
                "}"
        );

        Compilation compilation = Compiler.javac()
                .withProcessors(new ValueObjectProcessor())
                .compile(source);

        assertThat(compilation).hadWarningContaining("ValueObject 类 [InvalidValueObject] 包含 setter 方法 [setValue]，这违反了值对象不可变原则");
    }

    @Test
    void test_valueObjectWithMultipleSetters_compile_GenerateMultipleWarnings() {
        JavaFileObject source = forSourceLines(
                "test.MultiSetterValueObject",
                "package test;",
                "",
                "import com.rdlts.enigma.ddd.core.ValueObject;",
                "",
                "@ValueObject",
                "public class MultiSetterValueObject {",
                "  private String value;",
                "  private int count;",
                "",
                "  public void setValue(String value) {",
                "    this.value = value;",
                "  }",
                "",
                "  public void setCount(int count) {",
                "    this.count = count;",
                "  }",
                "",
                "  public String getValue() {",
                "    return value;",
                "  }",
                "",
                "  public int getCount() {",
                "    return count;",
                "  }",
                "}"
        );

        Compilation compilation = Compiler.javac()
                .withProcessors(new ValueObjectProcessor())
                .compile(source);

        assertThat(compilation).hadWarningContaining("ValueObject 类 [MultiSetterValueObject] 包含 setter 方法 [setValue]，这违反了值对象不可变原则");
        assertThat(compilation).hadWarningContaining("ValueObject 类 [MultiSetterValueObject] 包含 setter 方法 [setCount]，这违反了值对象不可变原则");
    }

    @Test
    void test_valueObjectWithSetMethod_compile_withoutWarning() {
        JavaFileObject source = forSourceLines(
                "test.ValidValueObject",
                "package test;",
                "",
                "import com.rdlts.enigma.ddd.core.ValueObject;",
                "",
                "@ValueObject",
                "public class ValidValueObject {",
                "  private String value;",
                "",
                "  public void set(String value) {",
                "    this.value = value;",
                "  }",
                "",
                "  public String getValue() {",
                "    return value;",
                "  }",
                "}"
        );

        Compilation compilation = Compiler.javac()
                .withProcessors(new ValueObjectProcessor())
                .compile(source);

        assertThat(compilation).succeededWithoutWarnings();
    }

    @Test
    void test_valueObjectWithElementKindEnum_compile_withoutWarning() {
        JavaFileObject source =forSourceLines(
                "test.ValidValueObject",
                "package test;",
                "",
                "import com.rdlts.enigma.ddd.core.ValueObject;",
                "",
                "@ValueObject",
                "enum ValidValueObject {",
                "}"
        );
        Compilation compilation = Compiler.javac()
                .withProcessors(new ValueObjectProcessor())
                .compile(source);
        assertThat(compilation).succeededWithoutWarnings();
    }
}