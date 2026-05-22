## Requirements

### Requirement: enigma-ddd-core 必须提供单实体聚合契约
`enigma-ddd-core` SHALL 提供单实体聚合接口，用于表达一个领域实体自身就是聚合根且不需要额外聚合包装对象的建模场景。

#### Scenario: 单实体聚合返回自身作为聚合根
- **WHEN** 一个领域对象实现单实体聚合接口
- **THEN** 该对象 MUST 同时满足 `DomainEntity` 和 `DomainAggregate` 契约
- **THEN** 调用 `root()` MUST 返回当前对象自身

### Requirement: 单实体聚合不得引入 Spring 依赖
单实体聚合接口 MUST 保持在 `enigma-ddd-core` 的纯领域核心边界内，不得引入 Spring Framework、Spring Boot 或其他运行时基础设施依赖。

#### Scenario: 编译 enigma-ddd-core
- **WHEN** 编译 `enigma-ddd-core`
- **THEN** 单实体聚合接口 MUST 只依赖 DDD 核心契约和已有通用注解依赖
- **THEN** 该接口 MUST 不要求 Spring 容器或 Spring 注解参与

### Requirement: 单实体聚合必须保留实体身份契约
单实体聚合接口 MUST 保留 `DomainEntity.identity()` 身份标识契约，使仓储、事件参数和实体版本行为能够继续按实体语义使用。

#### Scenario: 读取单实体聚合身份标识
- **WHEN** 调用单实体聚合对象的 `identity()`
- **THEN** 返回值 MUST 来自该实体自身的身份标识实现
- **THEN** 返回值 MUST 可用于现有 `DomainRepository` 风格的实体定位
