## ADDED Requirements

### Requirement: 系统必须提供一个可运行的 BDD 示例工程结构
`enigma-test-spring-boot-starter` SHALL 提供一个模块内可执行的 BDD demo，用于展示 Feature 文件、步骤定义、Spring 测试启动类和断言逻辑之间的完整协作方式。

#### Scenario: 开发者运行模块测试
- **WHEN** 开发者执行模块测试命令
- **THEN** BDD demo MUST 被纳入测试执行范围
- **THEN** 开发者 MUST 能通过测试结果确认示例场景通过

### Requirement: BDD demo 必须展示一个真实的领域行为场景
BDD demo SHALL 使用一个具备明确业务规则的示例场景来演示 Given / When / Then 结构，而不是仅展示静态字符串断言。

#### Scenario: 轨迹风格的游击士支援任务评级场景
- **WHEN** Feature 文件描述“高完成度且无伤亡的支援任务被评为 S 级”的业务行为
- **THEN** 步骤定义 MUST 调用实际的 Spring Bean 或领域服务执行评级逻辑
- **THEN** 断言 MUST 验证最终任务评级结果符合业务规则

### Requirement: BDD demo 必须可作为业务项目接入样板
BDD demo SHALL 采用可复制到其他 Enigma 项目的目录结构与命名方式，帮助业务项目快速复用。

#### Scenario: 开发者复制 demo 结构
- **WHEN** 开发者参考 demo 创建新的业务项目 BDD 测试
- **THEN** 开发者 MUST 能识别 Feature 文件目录、Step Definitions 目录、测试入口类和示例服务所在位置
- **THEN** demo 中的说明 MUST 足以支持开发者在不阅读额外源码的情况下完成基础接入

