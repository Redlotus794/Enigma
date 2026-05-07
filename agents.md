# Enigma — AI Agent 工作指南

> 本文档为 AI Agent（GitHub Copilot 等）在 Enigma 工作空间内工作时的行为规范与上下文参考。  
> 所有 AI 建议、代码生成、文档更新均须遵循本文档约定。

---

## 1. 项目上下文

| 属性 | 值 |
|------|-----|
| 项目名称 | Enigma |
| GroupId | `com.rdlts.enigma` |
| 当前版本 | `1.2.0-SNAPSHOT` / `1.1.1-RELEASE` |
| 协议 | GNU General Public License v3.0 (GPL-3.0) |
| 仓库地址 | https://github.com/Redlotus794/Enigma |
| Maven 私服 | http://nexus.smil.com:10022/repository/ |
| 文档平台 | https://wcnn2j4xsnan.feishu.cn/wiki/H8iEwnymcir1B1kut1UcKDThnOg |
| 标准示例 | https://github.com/Redlotus794/java-spring-boot-ddd-example |
| 启动时间 | 2025-11-27 |

**项目定位**：Enigma 是基于 Spring Boot + Java 8 + Maven 的企业级开发标准框架，融合领域驱动设计（DDD）理念，提供可复用的基础设施 Starter 组件，供业务项目快速引入。

---

## 2. 工作空间语言

| 类型 | 技术 | 版本 |
|------|------|------|
| 主语言 | Java | 8 (source/target: 1.8) |
| 框架 | Spring Boot | 2.0.7.RELEASE |
| 框架 | Spring Framework | 5.0.11.RELEASE |
| 构建工具 | Apache Maven | 3.5+ |
| 代码简化 | Lombok | 1.18.38 |
| JSON | Fastjson2 | 2.0.52 |
| JSON | Jackson Databind | 2.9.7 |
| 工具库 | Apache Commons Lang3 | 3.12.0 |
| 工具库 | Commons IO | 2.21.0 |
| 随机化 | Easy Random Core | 4.3.0 |
| 测试框架 | JUnit Jupiter | 5.1.1 |
| Mock 框架 | Mockito | 4.6.1 |
| 覆盖率 | JaCoCo | 0.8.7 |
| 测试插件 | Maven Surefire | 3.0.0-M7 |
| 测试插件 | Maven Failsafe | 3.0.0-M7 |
| 静态分析 | Google FindBugs JSR305 | 3.0.2 |
| 注解处理 | Google Auto Service | 1.0.1 |
| 编译测试 | Google Compile Testing | 0.19 |
| 数据库驱动 | MySQL Connector/J | 5.1.47 |
| 脚本 | Bash / Maven Wrapper | — |

---

## 3. 目录结构

```
Enigma/
├── agents.md                          # AI Agent 工作指南（本文件）
├── CHANGELOG.md                       # 根项目变更日志
├── FAQ.md                             # 常见问题解答
├── LICENSE                            # GPL-3.0 协议
├── README.md                          # 项目说明
├── mvn_versions.sh                    # 多模块版本管理脚本
├── docs/                              # 项目文档目录
│   ├── ops/scripts/                   # 运维脚本（maven_wrapper.sh, versions.sh）
│   └── project-management/            # 项目管理文档
├── enigma-bom/                        # BOM 依赖版本统一管理（不继承 parent）
│   └── pom.xml
├── enigma-parent/                     # 所有模块的父 POM，统一插件配置
│   └── pom.xml
├── enigma-common/                     # 公共基础类（常量、日期工具等）
│   └── src/main/java/com/rdlts/enigma/common/
├── enigma-ddd/                        # DDD 多模块子项目
│   ├── enigma-ddd-core/               # DDD 核心接口与注解（无 Spring 依赖）
│   │   └── src/main/java/com/rdlts/enigma/ddd/core/
│   │       ├── cqrs/                  # CQRS：Command / Query
│   │       ├── event/                 # 领域事件体系
│   │       ├── exception/             # 领域异常
│   │       ├── service/               # 领域服务注册
│   │       ├── support/               # 注解处理器（ValueObjectProcessor）
│   │       ├── DomainAggregate.java
│   │       ├── DomainAggregateRoot.java
│   │       ├── DomainEntity.java
│   │       ├── DomainRepository.java
│   │       ├── EntityJsonable.java
│   │       ├── EntityJsonObject.java
│   │       ├── EntityVersion.java
│   │       ├── ValueObject.java       # 编译期值对象注解
│   │       └── Adapter.java
│   └── enigma-ddd-spring-boot-starter/ # DDD Spring Boot 自动装配实现
├── enigma-random-spring-boot-starter/ # 随机对象生成（基于 Easy Random）
├── enigma-test-spring-boot-starter/   # 测试公共基础设施（常量、启动类等）
├── enigma-tools-spring-boot-starter/  # 工具类（Spring 上下文工具等）
└── enigma-tpc-spring-boot-starter/    # 第三方中心（Third Party Center）
```

### 核心包路径规则
- 根包：`com.rdlts.enigma`
- 各模块子包：`com.rdlts.enigma.<module>.<layer>`（如 `com.rdlts.enigma.ddd.core.event`）

---

## 4. 事实来源

所有代码生成、设计决策、规范判断须优先参考以下来源，**不得凭空推断**：

| 来源 | 用途 |
|------|------|
| `enigma-parent/pom.xml` | 插件版本、覆盖率阈值、编译参数的唯一权威 |
| `enigma-bom/pom.xml` | 所有第三方依赖版本的权威来源，子模块**不得**重复声明版本号 |
| `enigma-ddd/enigma-ddd-core/` | DDD 核心概念接口定义，任何 DDD 类必须实现这里的接口 |
| `README.md` | 项目背景、技术栈列表、覆盖率要求 |
| `CHANGELOG.md` | 版本历史，版本号命名规范参考 |
| 飞书文档 (feishu.cn) | 领域驱动设计概念文档，每个核心接口的 JavaDoc 链接均指向飞书 |

---

## 5. 后端职责

### 5.1 模块职责边界

| 模块 | 职责 | 不应包含 |
|------|------|---------|
| `enigma-bom` | 统一管理所有依赖版本（import scope） | 业务代码、Spring Bean |
| `enigma-parent` | 统一插件配置（compiler、surefire、failsafe、jacoco）、distributionManagement | 业务代码 |
| `enigma-common` | 跨模块共享的常量、纯工具类（无 Spring 依赖） | 业务逻辑、DDD 概念 |
| `enigma-ddd-core` | DDD 核心接口与注解，**零 Spring 依赖**，可被非 Spring 项目复用 | Spring 注解、自动装配 |
| `enigma-ddd-spring-boot-starter` | DDD 的 Spring 实现（事件发布、服务注册、AOP） | 纯领域逻辑 |
| `enigma-random-spring-boot-starter` | 对象随机化生成，支持自定义 Randomizer/Registry | 业务数据初始化 |
| `enigma-test-spring-boot-starter` | 测试公共常量、测试启动类，`scope=test` | 生产代码 |
| `enigma-tools-spring-boot-starter` | Spring 上下文工具（`EnigmaSpringContextUtils` 等） | 业务逻辑 |
| `enigma-tpc-spring-boot-starter` | 第三方服务集成抽象（Third Party Center） | 领域核心逻辑 |

### 5.2 DDD 层次职责

```
表现层 (Controller/API)      → 接收 HTTP 请求，调用应用服务，返回 DTO
应用层 (Application Service) → 编排领域对象，不含业务规则，调用 Repository
领域层 (Domain)              → 实现 DomainEntity / DomainAggregate / DomainService，包含核心业务规则
基础设施层 (Infrastructure)  → 实现 DomainRepository，与数据库/外部系统交互
```

---

## 6. 边界与安全

### 6.1 模块依赖边界
- `enigma-ddd-core` **禁止**依赖 Spring（`spring-context`、`spring-boot` 等）
- `enigma-bom` **禁止**继承 `enigma-parent`（存在循环依赖）
- 子模块依赖版本**必须**通过 `enigma-bom` 统一管理，不得在子模块 `pom.xml` 中硬编码版本号（`enigma-ddd-spring-boot-starter` 中明确引入自身版本的除外）
- `enigma-test-spring-boot-starter` 依赖**只能**以 `scope=test` 引入生产模块

### 6.2 代码安全边界
- 所有对外暴露的返回值字段不得包含内部系统路径、数据库结构信息
- 领域实体的 `identity()` 方法返回值应为系统生成的随机主键，与业务主键分离
- 值对象（`@ValueObject`）类**禁止**提供 setter 方法（由 `ValueObjectProcessor` 在编译期检查）
- `DomainRepository.remove()` 的物理/逻辑删除策略由基础设施层决定，领域层不关心

### 6.3 版本发布安全
- SNAPSHOT 版本用于开发迭代，RELEASE 版本用于正式发布
- 版本号格式：`{major}.{minor}.{patch}-RELEASE` 或 `{major}.{minor}.{patch}-SNAPSHOT`
- 发布到 `http://nexus.smil.com:10022/repository/` 需要对应的 `settings.xml` 认证配置

---

## 7. 项目规范

### 7.1 Java 8 代码规范

- **编译级别**：`source=1.8`，`target=1.8`，严格遵守，**不得使用 Java 9+ API**
- **注解工具**：使用 Lombok（`@Data`、`@Builder`、`@Slf4j` 等），Lombok 统一版本 `1.18.38`
- **Null 安全**：使用 `javax.annotation.Nonnull` / `@Nullable` 标注方法返回值与参数
- **集合**：优先使用接口类型（`List`、`Map`、`Collection`），返回值不返回 `null`，返回空集合
- **Optional**：`Repository.find()` 返回 `Optional<T>`，`findRequired()` 直接返回实体或抛 `DomainEntityNotFoundException`
- **异常**：领域异常继承 `EnigmaDDDRuntimeException`，使用 RuntimeException，不强制 checked exception
- **日期时间**：使用 `java.time` 包（`Instant`、`LocalDate` 等），工具类使用 `LocalDateUtils`
- **日志**：通过 `spring-boot-starter-logging`（Logback），使用 `@Slf4j` 注入，**禁止** System.out.println
- **代码静态检查**：安装并使用 **Alibaba Coding Guidelines** IDE 插件，生成代码不得有 Blocker/Critical 级别警告

### 7.2 DDD 项目规范

核心接口均位于 `enigma-ddd-core`，实现时**必须**遵循：

| 概念 | 接口/注解 | 规则 |
|------|----------|------|
| 聚合根 | `DomainAggregateRoot` | 每个聚合有且只有一个聚合根 |
| 领域实体 | `DomainEntity<PKType>` | 必须实现 `identity()` 返回非空主键；按需实现 `version()` |
| 值对象 | `@ValueObject` | 不可变，无 setter，通过 `ValueObjectProcessor` 编译期校验 |
| 聚合 | `DomainAggregate<T extends DomainAggregateRoot>` | `root()` 返回非空聚合根 |
| 领域资源库 | `DomainRepository<DE, IdentityType>` | 必须实现 `find()`、`findAll()`、`save()`、`saveAll()`、`remove()`、`removeAll()` |
| 领域服务 | `DomainService` | 无状态，通过 `DomainServiceRegistry` 注册与获取 |
| 领域事件 | `DomainEvent<T extends DomainEventParam>` | 继承该抽象类，通过 `DomainEventPublisher` 发布 |
| CQRS | `Command` / `Query` / `CQRS` | 查询与命令分离，Command 改变状态，Query 只读 |
| 适配器 | `Adapter` | 外部模型与领域模型转换 |

**DDD 事件机制**（`enigma-ddd-spring-boot-starter` 默认实现）：
- `DomainEventPublisher` → 默认实现 `EnigmaDomainEventPublisher`（基于 Spring Event）
- `DomainServiceRegistry` → 默认实现 `EnigmaDomainServiceRegistry`（基于 Spring ApplicationContext）
- `DomainEventRepository` → 默认实现 `EnigmaDomainEventRepository`（日志记录）

**自定义覆盖**：通过 `META-INF/services/` SPI 机制替换默认实现。

### 7.3 Spring Boot 2.0.7 规范

- **版本锁定**：`spring-boot-maven-plugin` 版本为 `2.0.7.RELEASE`，**不得随意升级**
- **自动装配**：Spring Boot Starter 使用 `spring-boot-autoconfigure`，在 `META-INF/spring.factories` 中声明 `EnableAutoConfiguration`
- **配置前缀**：
  ```yaml
  enigma:
    spring:
      enabled: true   # DDD Spring 装配开关，默认 true
  ```
- **日志框架**：`spring-boot-starter-logging`（Logback），**禁止**同时引入 `spring-boot-starter-log4j2`（存在冲突）
- **AOP**：通过 `spring-boot-starter-aop` 启用，用于 DDD 事件拦截等横切关注点
- **测试**：使用 `spring-boot-starter-test`（`scope=test`），JUnit 5（`junit-jupiter-api/engine 5.1.1`）

### 7.4 API 规范

- **HTTP 方法语义**：GET（查询）、POST（创建）、PUT（全量更新）、PATCH（部分更新）、DELETE（删除）
- **URI 风格**：全小写，单词间使用连字符（`-`），资源名使用复数（`/domain-entities/{id}`）
- **响应结构**：统一包装响应体，包含 `code`、`message`、`data` 字段
- **错误码**：使用有意义的业务错误码，领域异常转换为标准 HTTP 错误响应
- **版本管理**：API 版本通过路径前缀管理（如 `/api/v1/`）
- **Content-Type**：请求与响应均使用 `application/json; charset=UTF-8`
- **DTO 命名**：请求对象以 `Request` 结尾，响应对象以 `Response` 或 `DTO` 结尾，与领域实体严格分离

### 7.5 Maven 规范

- **依赖声明**：所有依赖版本**统一**在 `enigma-bom/pom.xml` 的 `<dependencyManagement>` 中声明
- **子模块引用**：子模块只声明 `groupId` 和 `artifactId`，不写版本号
- **模块聚合**：`enigma-parent/pom.xml` 的 `<modules>` 列表为全量模块清单
- **编码**：`<project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>` 全局统一
- **Wrapper**：使用 `enigma-ddd/mvnw`（Maven Wrapper）构建，确保 CI/CD 环境一致
- **版本号格式**：`{major}.{minor}.{patch}-RELEASE` 或 `{major}.{minor}.{patch}-SNAPSHOT`
- **禁止**：子模块 `pom.xml` 中不得出现未经 BOM 管理的硬编码版本号（Lombok 注解处理路径中的版本除外）

---

## 8. 环境与配置

### 8.1 本地开发环境要求

| 工具 | 最低版本 | 说明 |
|------|---------|------|
| JDK | 8 (1.8.0_xxx) | Oracle JDK 或 OpenJDK 均可 |
| Maven | 3.5.0 | 推荐使用项目自带的 `mvnw` |
| IDE | IntelliJ IDEA | 推荐，需安装 Lombok 插件、Alibaba Coding Guidelines 插件 |

### 8.2 Maven 私服配置

**Smil Nexus 专用 settings 文件路径**：`/Users/wangjialong/maven/settings/settings-smil.xml`

> ⚠️ **重要**：连接 Smil Nexus 私服（`nexus.smil.com:10022`）时**必须关闭 VPN**，开启 VPN 会导致网络路由异常，deploy / download 均会失败。

该文件已预配置以下内容：
- `snapshots` / `releases` / `nexus` 三个 server 认证信息
- mirror 指向 `http://nexus.smil.com:10022/repository/maven-public/`
- `dev` profile 激活 nexus 仓库（releases + snapshots 均启用）

deploy 时通过 `-s` 参数指定：
```shell
./mvnw clean deploy -DskipTests -s /Users/wangjialong/maven/settings/settings-smil.xml
```

如需手动配置 `~/.m2/settings.xml`，参考结构如下：
```xml
<servers>
  <server>
    <id>releases</id>
    <username>your-username</username>
    <password>your-password</password>
  </server>
  <server>
    <id>snapshots</id>
    <username>your-username</username>
    <password>your-password</password>
  </server>
</servers>
<mirrors>
  <mirror>
    <id>nexus</id>
    <url>http://nexus.smil.com:10022/repository/maven-public/</url>
    <mirrorOf>central</mirrorOf>
  </mirror>
</mirrors>
```

### 8.3 日志配置

- 默认使用 `spring-boot-starter-logging`（Logback）
- 日志输出目录：模块输出至 `~/logs/` 或 `logs/enigma-*/` 目录（见各模块 `logback-spring.xml`）
- 日志级别：开发环境 `DEBUG`，生产环境 `INFO`，异常统一 `ERROR`

### 8.4 Spring 配置示例

```yaml
# application.yml 示例
spring:
  application:
    name: your-app-name
  profiles:
    active: dev

enigma:
  spring:
    enabled: true  # 启用 Enigma DDD Spring 装配
```

---

## 9. 验证与交付要求

### 9.1 代码覆盖率（JaCoCo）

覆盖率阈值以 `enigma-ddd-spring-boot-starter` 中的 `pom.xml` 配置为准：

| 指标 | 最低要求 |
|------|---------|
| 行覆盖率（LINE） | ≥ 95% |
| 分支覆盖率（BRANCH） | ≥ 95% |
| 方法覆盖率（METHOD） | ≥ 95% |

> `enigma-parent` 中 `<minimum>85%</minimum>` 为**基础默认阈值**，各模块可在自身 `pom.xml` 中提升至 95%。

### 9.2 测试规范

- 单元测试文件命名：`*Test.java`（由 `maven-surefire-plugin` 识别，`**/*Test.java`）
- 集成测试文件命名：`*IT.java`（由 `maven-failsafe-plugin` 识别）
- 测试公共组件：通过 `enigma-test-spring-boot-starter`（`scope=test`）引入
- Mockito 版本：`4.6.1`，用于 Mock 外部依赖

### 9.3 构建验证

在提交代码前，必须通过以下验证：
```shell
# 在对应模块目录执行
mvn clean verify

# 或在根目录（enigma-parent）执行全量验证
cd enigma-parent && ./mvnw clean verify
```

### 9.4 代码质量

- 使用 IntelliJ IDEA 的 **Alibaba Coding Guidelines** 插件分析，消除 Blocker 和 Critical 级别警告
- JavaDoc 注释：所有 `public` 接口、类、方法必须有 JavaDoc，包含 `@author`、`@since`
- 参数非空标注：使用 `@Nonnull`（`javax.annotation`）标注非空返回值和参数

### 9.5 发布检查清单

- [ ] 所有测试通过（`mvn verify`）
- [ ] 覆盖率达到模块要求的阈值
- [ ] 代码无 Alibaba 规约 Blocker/Critical 警告
- [ ] `CHANGELOG.md` 更新
- [ ] 版本号从 `SNAPSHOT` 改为 `RELEASE`（使用 `mvn_versions.sh`）
- [ ] 版本提交后部署到 Nexus 私服

---

## 10. 命令集（Commands）

### 10.1 构建命令

```shell
# 编译并安装（跳过测试）
cd enigma-ddd && ./mvnw clean install -DskipTests

# 完整构建（含测试与覆盖率）
cd enigma-ddd && ./mvnw clean verify

# 仅运行单元测试
cd enigma-ddd && ./mvnw test

# 运行集成测试
cd enigma-ddd && ./mvnw verify -Pfailsafe
```

### 10.2 版本管理命令

```shell
# 更新所有模块版本（enigma-bom、enigma-parent、enigma-ddd）
./mvn_versions.sh set 1.2.0-SNAPSHOT

# 提交版本更改（删除 .versionsBackup 文件）
./mvn_versions.sh commit

# 回滚版本更改（从 .versionsBackup 恢复）
./mvn_versions.sh revert

# 带 SSL 跳过参数（私服 SSL 问题时使用）
./mvn_versions.sh set 1.2.0-SNAPSHOT -Dmaven.wagon.http.ssl.insecure=true -Dmaven.wagon.http.ssl.allowall=true

# 单模块版本更新（在对应目录执行）
mvn versions:set -DnewVersion=1.2.0-SNAPSHOT
mvn versions:commit
mvn versions:revert
```

### 10.3 发布命令

> **Smil Nexus 专用 settings 文件**：`/Users/wangjialong/maven/settings/settings-smil.xml`  
> deploy 时必须通过 `-s` 参数指定，该文件包含 snapshots / releases / nexus 三个 server 认证配置及 mirror 设置。
>
> ⚠️ **重要**：deploy 到 Smil Nexus 时**必须关闭 VPN**，否则无法连接到私服，deploy 将失败。

```shell
# 发布全量模块到 Smil Nexus 私服（推荐）
cd enigma-parent && ./mvnw clean deploy -DskipTests -s /Users/wangjialong/maven/settings/settings-smil.xml

# 发布 DDD 子模块到 Smil Nexus 私服
cd enigma-ddd && ./mvnw clean deploy -DskipTests -s /Users/wangjialong/maven/settings/settings-smil.xml

# 发布指定单模块（在对应目录执行）
./mvnw clean deploy -DskipTests -s /Users/wangjialong/maven/settings/settings-smil.xml
```

### 10.4 代码分析命令

```shell
# 生成 JaCoCo 覆盖率报告（位于 target/site/jacoco/index.html）
cd enigma-ddd && ./mvnw test jacoco:report

# 查看依赖树
mvn dependency:tree

# 检查过时依赖
mvn versions:display-dependency-updates

# 检查插件更新
mvn versions:display-plugin-updates
```

---

## 11. 文档维护

### 11.1 文档结构

```
docs/
├── ops/
│   └── scripts/           # 运维脚本文档
│       ├── maven_wrapper.sh
│       └── versions.sh
└── project-management/
    └── 2do.md             # 项目管理待办事项
```

各模块 `README.md` 为**模块级文档**，必须包含：
- 模块职责描述
- Maven 依赖引入方式
- 核心功能使用示例
- 配置说明（如有）

### 11.2 文档更新规则

| 文档 | 何时更新 |
|------|---------|
| `CHANGELOG.md` | 每次发布版本时，记录新增、修改、废弃内容 |
| `README.md`（根目录） | 技术栈版本变动、模块新增/删除时 |
| 各模块 `README.md` | 模块 API 变更、新功能上线时 |
| `agents.md`（本文件） | 规范变更、新技术引入、项目结构调整时 |
| 飞书文档 | DDD 概念定义、架构决策变更时 |

### 11.3 JavaDoc 规范

```java
/**
 * 类/接口描述，一句话说明职责。
 * 可选：附飞书文档链接。
 *
 * @param <T> 泛型说明
 * @author wangjialong
 * @since yyyy/MM/dd HH:mm
 */
```

### 11.4 CHANGELOG 格式

```markdown
## {version}-{RELEASE|SNAPSHOT}

发布时间: yyyy-MM-dd

### 新增
- 功能描述

### 修改
- 修改描述

### 废弃
- 废弃内容

### 修复
- Bug 修复描述
```

### 11.5 参考文档链接

| 文档 | 链接 |
|------|------|
| 领域驱动设计总览 | https://wcnn2j4xsnan.feishu.cn/wiki/H8iEwnymcir1B1kut1UcKDThnOg |
| 领域实体 | https://wcnn2j4xsnan.feishu.cn/wiki/LnApwD7IgiC5WgknXP1c1pZxn5d |
| 聚合根 / 聚合 | https://wcnn2j4xsnan.feishu.cn/wiki/AQrFwGZg4ileJnkdOZccBdK0nSb |
| 值对象 | https://wcnn2j4xsnan.feishu.cn/wiki/XDWYwYtZyiZptjkooSMcM3rynNf |
| 领域资源库 | https://wcnn2j4xsnan.feishu.cn/wiki/Np1qwDKAAivbULkkmHTcZptqnBf |
| 领域服务 | https://wcnn2j4xsnan.feishu.cn/wiki/LVcNwUs2yiamFkkyQIWch7UUnab |
| 领域事件 | https://wcnn2j4xsnan.feishu.cn/wiki/XQ3lwTTBLiazVQkfmKNcmdCanmg |
| CQRS | https://wcnn2j4xsnan.feishu.cn/wiki/BMrRwOVQQie0BTklA56c04ywnge |
| 标准项目示例 | https://github.com/Redlotus794/java-spring-boot-ddd-example |

---

*最后更新：2026-05-07*

