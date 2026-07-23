# Enigma 2.0.0 操作日志汇总

## 汇总信息

- 版本：`2.0.0`
- 汇总日期：2026-07-23
- 日志覆盖范围：2026-05-22 至 2026-07-23
- 纳入汇总的源日志：7 份

## 来源文件

- `docs/ops/log/2026-05-22.md`
- `docs/ops/log/2026-05-23.md`
- `docs/ops/log/2026-05-24.md`
- `docs/ops/log/2026-05-29.md`
- `docs/ops/log/2026-06-01.md`
- `docs/ops/log/2026-06-04.md`
- `docs/ops/log/2026-07-23.md`

## 版本与工程坐标

- 项目版本在开发阶段切换为 `2.0.0-SNAPSHOT`，发布准备阶段统一切换为正式版本 `2.0.0`。
- Maven GroupId 和 Java 根包从 `com.rdlts` 迁移为 `io.github.redlotus794`。
- 父 POM 补充项目元数据，并清理 BOM 中重复的 `compile-testing` 依赖管理配置。
- Lombok 调整为可选依赖，减少对下游项目的传递影响。

## DDD 核心模型与资源库

- 统一聚合根与实体语义，移除 `DomainAggregate` 和单实体聚合模型。
- 资源库契约改为直接面向聚合根，并将 `nextIdentity` 标识生成职责归入资源库。
- 调整资源库异常命名、泛型约束、接口文档和配套测试。
- 补充 Provider 领域提供者概念、核心接口、Spring 实现和测试。
- 新增 `CqrsRequest` 公共请求模型，统一 Command 与 Query 的请求契约。

## Spring 与事件能力

- 修复事务代理单例注册与自动装配导入问题。
- 增加准实时领域事件发布能力及测试。
- 补充 ViewResolver 等公共接口的 JavaDoc 和约束说明。

## 测试、示例与产品文档

- `enigma-test-spring-boot-starter` 增加 Cucumber BDD 测试组件。
- 新增 `enigma-demo` 示例模块及相关产品需求、战略设计与业务概念文档。
- 新增第三方中心（TPC）产品需求、README 说明和静态原型。

## 规范与发布管理

- 明确 `docs/dev/convention/` 是代码规范的唯一事实来源。
- 完善操作日志规则、领域设计 Markdown/HTML 同步规则和 OpenSpec 配置。
- 新增 `2.0.0` 发布路线图和 release 分支发布准备流程。
- 将 `dev/2.0.0` 的 29 个提交 squash 为单一提交，并合入 `develop-2.x.x` 后创建 `release/v2.0.0`。

## 验证记录

- 开发整合阶段已使用 Java 8 执行 Maven 全量验证并通过。
- 正式版本 `2.0.0` 使用 Temurin Java `1.8.0_482` 执行 `cd enigma-parent && ./mvnw clean verify`。
- Maven Reactor 中 10 个模块全部构建成功，单元测试、集成测试和已配置的 JaCoCo 覆盖率检查均通过。
- 验证完成时间：2026-07-23 16:58（Asia/Shanghai）。

## 归档结果

- 上述 7 份源操作日志的有效信息已合并到本文件。
- 汇总核对完成后，源日志 Markdown 文件按发布流程删除。
- 本汇总文件保留在 `docs/ops/log/summary/`，作为 `2.0.0` 的操作记录。
