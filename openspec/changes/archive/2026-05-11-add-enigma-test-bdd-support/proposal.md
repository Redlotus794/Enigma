## Why

`enigma-test-spring-boot-starter` 当前只提供基础的 Spring Boot 测试启动能力，缺少面向业务协作的 BDD（Behavior Driven Development）测试组件。现有测试更偏向技术实现视角，不利于通过“场景-步骤-断言”的方式表达领域行为，也缺少可以直接复用的示例来指导业务项目快速落地。

为 `enigma-test-spring-boot-starter` 增加 BDD 测试组件，可以让 Enigma 业务项目在保持 Java 8、Spring Boot 2.0.7 和 Maven 多模块约束的前提下，统一编写可读性更强的行为测试，并提供一个可直接运行的 demo 作为接入样板。

## What Changes

- 在 `enigma-test-spring-boot-starter` 中新增 BDD 测试支持能力，提供面向 Spring Boot 项目的 BDD 集成基础设施。
- 为 BDD 组件引入一套与 Java 8、JUnit 5 和 Spring Boot 2.0.7 兼容的测试依赖管理方案。
- 在测试组件中补充可复用的 BDD 基类、配置约定或辅助工具，降低业务项目接入成本。
- 提供一个实际 demo，演示如何在 Enigma 项目中编写 Feature、Step Definitions、测试启动类与断言逻辑。
- 更新 `enigma-test-spring-boot-starter` 模块文档，说明依赖引入方式、使用方式和 demo 结构。

## Capabilities

### New Capabilities
- `bdd-test-support`: 为 `enigma-test-spring-boot-starter` 提供可复用的 BDD 测试组件，包括依赖管理、Spring Boot 集成约定和基础测试支撑。
- `bdd-test-demo`: 提供一个基于 Enigma 测试模块的实际 BDD demo，展示从业务场景描述到步骤实现、再到 Spring 上下文验证的完整链路。

### Modified Capabilities
- None.

## Impact

- 受影响模块：`enigma-test-spring-boot-starter`、`enigma-bom`、可能涉及根级或模块级 `README.md`/`CHANGELOG.md`。
- 依赖影响：需要在 BOM 中统一管理 BDD 相关依赖版本，子模块不得硬编码版本号。
- 模块边界影响：新增能力仅限测试基础设施，不应把生产业务逻辑放入 `enigma-test-spring-boot-starter`。
- DDD 边界影响：demo 可以展示领域行为测试，但不应改变 `enigma-ddd-core` 现有接口约定。
- 文档影响：需要在模块文档中补充 BDD 场景说明、使用方法和 demo 目录指引。
- Non-goals：本次变更不调整生产模块的对外 API，不引入新的生产运行时能力，也不修改 `enigma-ddd-core` 的核心领域契约。

