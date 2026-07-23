# Change Log

## 版本索引

- [2.0.0](#200)
- [1.1.1-RELEASE](#111-release)
- [1.1.0-RELEASE](#110-release)
- [Enigma 立项](#enigma-立项)

## 2.0.0

发布时间: 2026-07-23

### 新增

- 新增领域提供者（Provider）能力，以及相关核心接口、Spring 实现和测试。
- 新增 `CqrsRequest` 公共请求模型，统一 Command 与 Query 的请求契约。
- 新增准实时领域事件发布能力及对应测试。
- `enigma-test-spring-boot-starter` 新增 Cucumber BDD 测试组件。
- 新增 `enigma-demo` 示例业务模块、产品需求与领域设计文档。
- 新增第三方中心（TPC）产品需求、使用说明和静态原型。
- 新增开发规范、OpenSpec 配置和 `2.0.0` 发布路线图。

### 修改

- Maven GroupId 与 Java 根包由 `com.rdlts` 迁移为 `io.github.redlotus794`。
- 资源库契约调整为直接面向聚合根，并将标识生成职责归入资源库。
- 统一聚合根、实体和值对象的语义，移除单实体聚合模型。
- 更新 JUnit 至 5.9.3，并在 BOM 中统一管理 Cucumber 7.12.0 等依赖。
- Lombok 调整为可选依赖，降低业务项目的传递依赖影响。
- 全部 Maven 模块版本统一为正式版本 `2.0.0`。

### 废弃

- 移除旧的 `com.rdlts.enigma` 包路径。
- 移除 `DomainAggregate` 与单实体聚合相关类型。

### 修复

- 修复事务代理单例注册和自动装配导入问题。
- 修复 BOM 中重复的 `compile-testing` 依赖管理配置。
- 修复领域资源库泛型契约及其测试中的不一致。

## 1.1.1-RELEASE

- 增加 maven版本更新脚本
- 增加enigma-tools的自动装配功能
- 增加EntityJsonable

## 1.1.0-RELEASE

发布时间: 2025-12-19

### 新增
- enigma-bom 依赖管理模块
- enigma-common 公共模块
- enigma ddd 领域驱动设计模块
- enigma-random 随机数模块
- enigma-test 测试模块
- enigma-tools 工具模块
- enigma-tpc 第三方中心模块

## Enigma 立项

发布时间: 2025-11-27

- 新增Enigma项目，相关文档  
