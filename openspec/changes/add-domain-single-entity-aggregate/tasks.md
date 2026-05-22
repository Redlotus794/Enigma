## 1. OpenSpec

- [x] 1.1 创建单实体聚合能力的 proposal。
- [x] 1.2 创建单实体聚合能力的 specs。
- [x] 1.3 拆分实现、测试、日志和验证任务。

## 2. 实现

- [x] 2.0 补充 `docs/dev/convention/java-ddd-convention.md`，明确 DDD Core 接口新增规范。
- [x] 2.1 在 `enigma-ddd-core` 中新增单实体聚合接口。
- [x] 2.2 保持接口位于 DDD Core 边界内，不引入 Spring 依赖。
- [x] 2.3 为接口补充 JavaDoc、非空约束和默认 `root()` 行为。

## 3. 测试

- [x] 3.1 新增测试用单实体聚合类。
- [x] 3.2 新增单元测试验证 `root()` 返回自身。
- [x] 3.3 新增单元测试验证身份标识仍来自实体自身。

## 4. 文档与日志

- [x] 4.1 更新 `docs/ops/log/` 记录本次有意义改动。

## 5. 验证

- [x] 5.1 执行 `cd enigma-ddd/enigma-ddd-core && mvn test`。
