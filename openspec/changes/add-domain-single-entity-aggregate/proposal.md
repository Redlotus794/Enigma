## Why

`enigma-ddd-core` 已提供 `DomainEntity`、`DomainAggregateRoot` 与 `DomainAggregate`，但单实体聚合场景仍需要业务代码显式创建只包装一个实体的聚合类型，存在样板代码和概念表达不够直接的问题。

新增单实体聚合接口可以让“实体本身就是聚合根、聚合根也是聚合入口”的常见建模方式具备统一契约，减少重复 `root()` 实现，并保持 DDD 核心接口的表达一致性。

## What Changes

- 在 `enigma-ddd-core` 中新增单实体聚合接口。
- 单实体聚合接口同时具备 `DomainEntity` 与 `DomainAggregate` 语义。
- 默认 `root()` 返回当前实体自身。
- 补充单元测试验证默认根对象行为和身份标识行为。

## Capabilities

### New Capabilities
- `domain-single-entity-aggregate`: 为 `enigma-ddd-core` 提供单实体聚合契约，支持实体直接作为自身聚合根。

### Modified Capabilities
- 无。

## Impact

- 受影响模块：`enigma-ddd/enigma-ddd-core`。
- 受影响代码层：领域层核心契约。
- DDD 边界影响：新增接口保持在 `enigma-ddd-core`，不引入 Spring 依赖。
- 文档影响：新增 OpenSpec 变更文档和操作日志。
- 验证范围：新增接口单元测试，并执行 `enigma-ddd-core` 模块测试。
- Non-goals：不修改现有 `DomainEntity`、`DomainAggregate`、`DomainAggregateRoot` 的方法签名；不调整仓储、事件发布或 Spring Starter 行为。
