# Enigma Test Project

Enigma 测试模块，主要为各项目提供测试所使用的常量、对象、启动程序等公共类，避免各项目重复创建相同内容。

## 模块职责

- 提供基于 Spring Boot 的公共测试启动能力
- 提供可配置的 Web Spring Boot 测试注解
- 提供测试专用领域对象与辅助类
- 提供 BDD（Behavior Driven Development）测试基础设施
- 提供可直接参考的 BDD demo

> `enigma-test-spring-boot-starter` 仅用于测试场景，业务项目必须以 `scope=test` 的方式引入。

## Maven 引入方式

```xml
<dependency>
	<groupId>com.rdlts.enigma</groupId>
	<artifactId>enigma-test-spring-boot-starter</artifactId>
	<scope>test</scope>
</dependency>
```

引入后，业务项目在测试范围内可获得：

- `spring-boot-starter-test`
- `cucumber-java`
- `cucumber-junit`
- `cucumber-spring`

这些依赖不会进入业务项目的生产运行时。

## Web Spring Boot 公共能力

模块提供 Web 测试基类与注解：

- `com.rdlts.enigma.EnigmaSpringBootBasedTest`
- `com.rdlts.enigma.test.annotation.EnigmaWebSpringBootTest`

其中：

- `EnigmaSpringBootBasedTest` 适用于直接继承的默认场景
- `EnigmaWebSpringBootTest` 适用于业务项目显式指定自己的 `SpringBootApplication` 与 `active profiles`

示例：

```java
package com.example.order.test;

import com.example.order.OrderApplication;
import com.rdlts.enigma.test.annotation.EnigmaWebSpringBootTest;

/**
 * 订单 Web 测试上下文。
 *
 * @author wangjialong
 * @since 2026/05/11 15:45
 */
@EnigmaWebSpringBootTest(
        classes = OrderApplication.class,
        activeProfiles = {"test", "web"}
)
public class OrderWebTestContext {
}
```

## BDD 公共能力

模块提供公共 BDD 测试基类：

- `com.rdlts.enigma.test.core.EnigmaCucumberSpringBootBasedTest`
- `com.rdlts.enigma.test.annotation.EnigmaCucumberSpringBootTest`

该基类用于桥接：

- `@CucumberContextConfiguration`
- `@SpringBootTest`
- `test` profile

业务项目可通过继承该类快速获得 Spring Boot + Cucumber 的测试上下文。

如果业务项目需要绑定自己的 `SpringBootApplication`，推荐直接在桥接类上使用
`@EnigmaCucumberSpringBootTest(classes = YourApplication.class)`，例如：

```java
package com.example.order.bdd;

import com.example.order.OrderApplication;
import com.rdlts.enigma.test.annotation.EnigmaCucumberSpringBootTest;

/**
 * 订单领域 BDD 测试上下文。
 *
 * @author wangjialong
 * @since 2026/05/11 15:30
 */
@EnigmaCucumberSpringBootTest(classes = OrderApplication.class)
public class OrderBddContext {
}
```

这样每个业务项目都可以在测试范围内复用 Enigma 的 BDD 集成能力，同时使用自己的应用启动类，
而不是固定绑定 `enigma-test-spring-boot-starter` 内置的 `EnigmaTestSpringBootApplication`。

## 实际 Demo

当前模块内置了一个“游击士支援任务评级” BDD demo，用于演示更贴近日式 JRPG《轨迹》系列风格的真实业务规则测试：

- 当支援任务完成度达到 `95` 且未造成平民伤亡时，任务评级应为 `S`
- 当任务即使完成度较高，但造成了平民伤亡时，评级仍会降为 `B`

### Demo 目录结构

```text
src/test/resources/
├── cucumber.properties
└── features/
	└── bdd/
		└── bracer-quest-rank.feature

src/test/java/com/rdlts/enigma/test/bdd/demo/
├── EnigmaBracerQuestBddContext.java
├── EnigmaBracerQuestBddTest.java
├── bracer/
│   ├── BracerQuestRank.java
│   └── BracerQuestEvaluationService.java
└── steps/
	└── BracerQuestStepDefinitions.java
```

### Demo Feature

`bracer-quest-rank.feature` 展示了两个场景：

- 高完成度且无伤亡的支援任务应被评为 `S` 级
- 即使完成度较高但造成平民伤亡，也不能获得高评级

## 运行方式

在模块目录执行：

```shell
cd enigma-test-spring-boot-starter
mvn clean test
```

如果从聚合构建入口验证：

```shell
cd enigma-parent
./mvnw -pl ../enigma-test-spring-boot-starter -am verify
```

## 环境说明

- 项目标准运行环境仍以 **Java 8** 为准
- 当前模块为兼容高版本 JDK 的本地测试执行，已在测试插件中增加最小化 `--add-opens` 配置
- 发布到 Smil Nexus 时请使用 settings 文件：`/Users/wangjialong/maven/settings/settings-smil.xml`
- 连接 Smil Nexus 时必须关闭 VPN，否则 deploy / download 可能失败
