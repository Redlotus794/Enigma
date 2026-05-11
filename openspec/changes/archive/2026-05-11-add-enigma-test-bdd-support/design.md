## Context

当前 `enigma-test-spring-boot-starter` 只提供基础的 Spring Boot 测试启动类与公共测试依赖，尚未提供 BDD 测试所需的统一依赖、Gherkin 场景约定、步骤定义集成方式和可参考的示例。对于 Enigma 业务项目来说，这意味着每个项目如果想引入 BDD，都需要自行选择框架、处理 Spring 集成、编写启动器，并自行沉淀示例，接入成本较高且风格不统一。

本次变更横跨测试基础设施、依赖管理、模块文档与测试示例，属于一个适合先完成设计再实施的跨模块增强：
- `enigma-bom` 需要统一管理 BDD 依赖版本；
- `enigma-test-spring-boot-starter` 需要暴露 BDD 测试所需的公共能力；
- 模块文档需要新增接入说明与 demo；
- 实际 demo 需要能够在 Java 8 + Spring Boot 2.0.7 环境下稳定运行。

## Goals / Non-Goals

**Goals：**
- 为 `enigma-test-spring-boot-starter` 增加一套与现有测试体系兼容的 BDD 测试支持。
- 让业务项目仅通过引入 `enigma-test-spring-boot-starter`（`scope=test`）即可获得 BDD 所需核心依赖。
- 提供一个真实、可运行、易理解的 demo，展示 Feature、Step Definitions、Spring 上下文和断言协作方式。
- 保持 Java 8、Spring Boot 2.0.7、Maven 多模块和 BOM 版本统一管理约束。
- 保持测试模块职责边界，新增能力仅服务于测试场景。

**Non-Goals：**
- 不修改 `enigma-ddd-core`、`enigma-common` 等生产模块的对外接口。
- 不为生产代码新增 BDD 运行时依赖。
- 不引入需要 Java 11+ 或更高版本的测试框架。
- 不在 `enigma-test-spring-boot-starter` 中放入真实业务系统实现，仅提供演示性质的测试样板。

## Decisions

### 1. 选择 Cucumber JVM 作为 BDD 框架
**决策：** 使用 `cucumber-java` + `cucumber-spring` + `cucumber-junit` 作为 BDD 支撑方案。

**原因：**
- Cucumber 的 Gherkin 语法天然适合 BDD 场景表达，便于产品、测试、开发协作。
- 与 `Spring Boot Test` 集成成熟，适合当前 `enigma-test-spring-boot-starter` 的定位。
- Java 8 兼容性明确，易于纳入 `enigma-bom` 管理。
- 相较 JGiven 等方案，Feature 文件形式更贴近“业务可读文档 + 自动化测试”的目标。

**备选方案：**
- JGiven：更偏 Java DSL，业务可读性不如 Gherkin 文件直观。
- Spock：Groovy 生态不符合当前 Java 8 + Maven 主技术栈，且会引入额外语言成本。

### 2. BDD 依赖由测试 Starter 暴露，版本由 BOM 统一管理
**决策：** BDD 依赖版本统一放到 `enigma-bom/pom.xml`，`enigma-test-spring-boot-starter` 直接声明所需依赖但不写版本号。

**原因：**
- 符合 Enigma 现有 Maven 规范：版本号统一由 BOM 管理。
- 业务项目以 `scope=test` 引入 `enigma-test-spring-boot-starter` 后，可在测试范围获得 BDD 能力，不污染生产运行时。
- 避免各业务项目自行维护 Cucumber 版本造成漂移。

**备选方案：**
- 在业务项目自行声明 Cucumber 依赖：会削弱测试 Starter 的统一价值，也不符合集中管理思路。

### 3. 公共 BDD 支撑放在 main，实际 demo 放在 test
**决策：**
- 可复用的 BDD 支撑类（如基础配置注解、通用 Spring 测试桥接类、公共常量/辅助工具）放在 `enigma-test-spring-boot-starter/src/main/java`。
- 实际 demo 放在 `src/test/java` 与 `src/test/resources/features`，作为模块内自校验样板。

**原因：**
- main 中的内容可被其他模块复用。
- demo 只用于说明和验证，不应进入发布产物的生产主代码职责范围。
- 通过模块自身测试运行 demo，可以确保示例持续可用。

### 4. demo 采用“轨迹系列风格的任务评级 / 组织声望”场景
**决策：** demo 选择一个无外部系统依赖、具备明确领域行为的场景：围绕日式 JRPG《轨迹》系列常见的委托、支援请求、游击士等级评定、组织贡献点等风格，设计一个“任务评级 / 组织声望晋升”的测试样例。

**原因：**
- 场景足够业务化，能够体现 BDD 的 Given / When / Then 表达优势。
- JRPG 尤其是《轨迹》系列天然适合“任务 → 结算 → 评级 / 晋升”的行为建模，便于写出可读性强的 Feature。
- 不依赖数据库、消息队列或第三方服务，适合稳定运行。
- 可以同时演示纯领域服务、Spring Bean 注入、步骤定义状态共享与断言。
- 使用“轨迹系列风格”而非复刻具体剧情，可以保留系列氛围，同时避免把 demo 绑定到某一部作品的主线内容。

**示例场景：**
- Given 某支援任务的完成度为 `95`
- And 该任务未造成平民伤亡
- When 执行任务评级结算
- Then 任务评级应为 `S`

**可选扩展示例：**
- 游击士协会委托完成后，根据完成度与附加目标达成情况评定 `B / A / S` 等级
- 特别任务支援科处理支援请求后，根据市民满意度与事件控制情况提升科室评价
- 导力工房接收订单后，根据交付时效与品质判定工房口碑等级
- 共和国地下任务完成后，根据情报回收完整度与隐蔽性结算贡献点

### 5. BDD 测试执行沿用 Maven 测试生命周期
**决策：** 让 BDD demo 能通过 Maven 测试生命周期执行，避免引入额外构建入口。

**原因：**
- 符合当前项目统一使用 Surefire/Failsafe 的构建习惯。
- 业务项目迁移时更容易理解与复制。
- 便于在 `mvn clean verify` 中纳入持续验证。

## Risks / Trade-offs

- **[Cucumber 与当前 JUnit 体系兼容性存在适配成本]** → 优先选择与 Java 8、Spring Boot 2.0.7 兼容性成熟的 Cucumber 组合，并在模块内 demo 中验证 Maven 执行链路。
- **[测试 Starter 内新增依赖可能被误解为生产依赖]** → 在 README 与 JavaDoc 中明确要求消费者以 `scope=test` 引入该模块。
- **[BDD demo 过于简化，可能无法体现真实项目复杂度]** → 选择有明确业务规则但无外部依赖的场景，重点演示接入方式与测试结构，而不是完整业务系统。
- **[示例过度贴近具体 IP 剧情会带来表达和迁移限制]** → 采用“轨迹系列风格化场景”而非直接复现具体章节剧情、人物关系或原作台词，以保证示例更中性、更易迁移。
- **[Feature 文件与步骤定义可能逐步分散]** → 约定统一目录结构，例如 `src/test/resources/features/bdd/` 与 `src/test/java/.../bdd/steps/`。
- **[新增依赖会增加测试模块构建时间]** → 限制依赖集合，只引入 BDD 所需最小依赖并通过 BOM 锁定版本。

## Migration Plan

1. 在 `enigma-bom/pom.xml` 中增加 BDD 依赖版本管理。
2. 在 `enigma-test-spring-boot-starter/pom.xml` 中声明 BDD 相关依赖，不写版本号。
3. 增加公共 BDD 测试支撑类与注解约定。
4. 在模块测试目录中增加 `轨迹风格任务评级` demo：Feature 文件、步骤定义、测试启动器、示例服务与断言。
5. 更新 `enigma-test-spring-boot-starter/README.md`，补充引入方式、目录说明和 demo 使用方式。
6. 运行模块级 Maven 测试验证变更可执行。
7. 如出现兼容性问题，优先回滚 BDD 依赖与支撑类，再恢复 README；该变更不涉及生产数据库或外部迁移，无数据回滚成本。

## Open Questions

- 最终选用的 Cucumber 具体版本需要结合 Java 8 与现有 Maven/JUnit 体系做一次兼容性验证。
- BDD demo 是否需要同时演示 HTTP 接口场景，还是先以纯 Spring Bean/领域服务场景为主。
- 是否需要在后续迭代中额外提供一个业务项目接入示例，而不仅限于测试模块内部 demo。
- “轨迹风格”示例最终采用哪一类任务模型更合适：任务评级、支援请求评分、游击士等级晋升，还是工房订单结算。

