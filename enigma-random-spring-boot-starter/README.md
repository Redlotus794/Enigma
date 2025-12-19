# Enigma Random 

本项目是Spring boot starter项目，旨在帮助项目快速实现对象的自定义随机化过程，创建符合业务意义的数据。


## 快速开始

- 添加项目依赖
```xml
<dependency>
    <groupId>com.rdlts.enigma</groupId>
    <artifactId>enigma-random-spring-boot-starter</artifactId>
</dependency>
```
- 一般对象的随机创建

```java
import com.rdlts.enigma.random.domain.EnigmaRandomGenerator;
import org.springframework.beans.factory.annotation.Autowired;

@Autowired
EnigmaRandomGenerator enigmaRandomGenerator;

final Person person = easyRandomGenerator.nextObject(Person.class);
```

- 自定义对象的随机创建 (Easy Random)

创建 Randomizer对象
```java
public class EmployeeRandomizer implements EnigmaRandomizer, Randomizer<Employee> {

    @Override
    public Employee getRandomValue() {
        return Employee.builder()
                .name("wangjialong")
                .gender(Gender.MALE)
                .build();
    }
}
```

创建 Registry对象
```java
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
```