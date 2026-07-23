## Requirements

### Requirement: Enigma Test 模块必须提供统一的 BDD 依赖接入能力
`enigma-test-spring-boot-starter` SHALL 提供业务项目接入 BDD 测试所需的核心依赖能力，并且相关版本 MUST 由 `enigma-bom` 统一管理，子模块不得硬编码版本号。

#### Scenario: 业务项目以 test scope 引入测试 Starter
- **WHEN** 业务项目通过 `scope=test` 引入 `enigma-test-spring-boot-starter`
- **THEN** 业务项目 MUST 能在测试范围内获得 BDD 所需核心依赖
- **THEN** 这些依赖 MUST 不进入业务项目的生产运行时依赖集合

### Requirement: Enigma Test 模块必须提供 Spring Boot BDD 集成支撑
`enigma-test-spring-boot-starter` SHALL 提供与 Spring Boot 测试上下文兼容的 BDD 集成支撑，使业务项目能够在 BDD 场景中直接装配 Spring Bean、执行领域服务并完成断言。

#### Scenario: BDD 场景中使用 Spring Bean
- **WHEN** 开发者在 BDD Step Definitions 中引用 Spring 管理的 Bean
- **THEN** 测试运行器 MUST 能正常启动 Spring 测试上下文
- **THEN** Step Definitions MUST 能访问到所需的 Spring Bean

### Requirement: Enigma Test 模块必须提供清晰的 BDD 目录与接入约定
`enigma-test-spring-boot-starter` SHALL 提供明确的 BDD 目录结构、测试入口约定和说明文档，以保证各业务项目接入风格一致。

#### Scenario: 开发者参考模块文档进行接入
- **WHEN** 开发者查阅 `enigma-test-spring-boot-starter` 文档
- **THEN** 文档 MUST 说明 BDD 依赖引入方式、Feature 文件位置、步骤定义位置和测试入口组织方式
- **THEN** 文档 MUST 明确该模块只能以 `scope=test` 方式被业务项目引入

