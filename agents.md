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
| 文档平台 | `docs/domain-driven-design/` |
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
├── docs/                              # 项目文档、规范、操作日志
├── openspec/                          # OpenSpec 变更规格
├── enigma-bom/                        # BOM 依赖版本统一管理
├── enigma-parent/                     # 父 POM 与统一插件配置
├── enigma-common/                     # 公共基础能力
├── enigma-ddd/                        # DDD 核心与 Spring Boot Starter
├── enigma-demo/                       # 示例项目
├── enigma-random-spring-boot-starter/ # 随机对象生成 Starter
├── enigma-test-spring-boot-starter/   # 测试基础设施 Starter
├── enigma-tools-spring-boot-starter/  # 工具能力 Starter
└── enigma-tpc-spring-boot-starter/    # 第三方中心 Starter
```

### 核心包路径规则
- 根包：`com.rdlts.enigma`
- 各模块子包：`com.rdlts.enigma.<module>.<layer>`（如 `com.rdlts.enigma.ddd.core.event`）

---

## 4. 事实来源

所有代码生成、设计决策、规范判断须优先参考以下来源，**不得凭空推断**：

| 来源 | 用途 |
|------|------|
| `docs/dev/convention/` | **代码规范唯一事实来源**；Java、Spring Boot、DDD、API、测试、Maven、数据库等开发规范必须以该目录文档为准 |
| `enigma-parent/pom.xml` | 插件版本、覆盖率阈值、编译参数的唯一权威 |
| `enigma-bom/pom.xml` | 所有第三方依赖版本的权威来源，子模块**不得**重复声明版本号 |
| `enigma-ddd/enigma-ddd-core/` | DDD 核心概念接口定义，任何 DDD 类必须实现这里的接口 |
| `README.md` | 项目背景、技术栈列表、模块导航 |
| `CHANGELOG.md` | 版本历史，版本号命名规范参考 |
| `docs/domain-driven-design/` | 领域设计基本概念的唯一事实来源；领域实体、聚合、值对象、资源库、领域服务、领域事件、CQRS 等概念定义必须以该目录文档为准 |

> 说明：自本次规范起，**`agents.md` 与 `README.md` 不再承载代码规范明细**。凡涉及代码风格、分层、命名、接口设计、测试、Maven、数据库等规范判断，**必须仅参考** `docs/dev/convention/`；若出现重复描述或冲突，以 `docs/dev/convention/` 为准。

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
用户接口层 (User Interface)      → 接收 HTTP 请求，调用应用服务，返回 DTO
应用服务层 (Application Service) → 编排领域对象，不含业务规则，调用 Repository
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

### 7.1 代码规范唯一事实来源

所有代码规范均以 `docs/dev/convention/` 为准，本文件不再重复维护代码规范细则。

当前应优先参考的规范文档包括但不限于：

- `docs/dev/convention/java-convention.md`
- `docs/dev/convention/java-ddd-convention.md`
- `docs/dev/convention/java-api-convention.md`
- `docs/dev/convention/java-directory-structure-convention.md`
- `docs/dev/convention/java-test-convention.md`
- `docs/dev/convention/java-persistence-object-convention.md`
- `docs/dev/convention/spring-boot-convention.md`
- `docs/dev/convention/maven-convention.md`
- `docs/dev/convention/db-mysql-convention.md`
- `docs/dev/convention/db-mongo-convention.md`
- `docs/dev/convention/redis-convention.md`
- `docs/dev/convention/rabbitmq-convention.md`

### 7.2 使用规则

- 在 `agents.md`、`README.md` 中出现的任何代码规范、风格示例、命名约束、分层约束、API 约束，均不应再作为判断依据
- AI Agent 生成代码、修改代码、评审代码时，必须先查阅 `docs/dev/convention/` 中对应主题文档
- 若 `agents.md`、`README.md`、历史文档、示例代码与 `docs/dev/convention/` 冲突，一律以 `docs/dev/convention/` 为准
- 若 `docs/dev/convention/` 尚未覆盖某一代码规范主题，不得改用 `pom.xml`、DDD 核心接口、`docs/domain-driven-design/`、历史文档或示例代码作为代码规范来源；应明确说明规范缺口，并按需求补充 `docs/dev/convention/` 后再执行
- 非代码规范事实（如依赖版本、插件配置、DDD 概念定义）才可参考 `pom.xml`、DDD 核心接口、`docs/domain-driven-design/` 等对应事实来源

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

测试相关命名、目录结构、测试分层与实现规范，统一参考 `docs/dev/convention/java-test-convention.md`；
测试依赖与插件版本仍以 `enigma-bom/pom.xml`、`enigma-parent/pom.xml` 为准。

### 9.3 构建验证

在提交代码前，必须通过以下验证：
```shell
# 在对应模块目录执行
mvn clean verify

# 或在根目录（enigma-parent）执行全量验证
cd enigma-parent && ./mvnw clean verify
```

### 9.4 代码质量

代码质量、JavaDoc、注解使用、静态检查等规范统一参考 `docs/dev/convention/` 对应文档，
本文件不再重复定义细则。

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
│   ├── log/               # 有意义改动的操作日志
│   └── scripts/           # 运维脚本文档
├── dev/                   # 开发规范
├── domain-driven-design/  # 领域设计基本概念
├── requirements/          # 需求文档
└── project-management/    # 项目管理文档
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
| `docs/domain-driven-design/` | DDD 基本概念定义、架构决策变更时 |
| `docs/ops/log/` | 每一次有意义的代码、配置、文档、规范或架构改动后，按日期记录变更日志 |

### 11.3 操作日志规则

- 每一次有意义的改动均须记录到 `docs/ops/log/`，包括代码实现、配置调整、文档规范、架构决策、构建脚本、依赖版本等变更
- 日志文件按日期命名，格式为 `yyyy-MM-dd.md`
- 每条日志至少包含：时间、改动范围、改动摘要、验证方式；如未验证，须明确说明原因
- 纯查看、搜索、无文件变更的操作无需记录
- 操作日志不得替代 `CHANGELOG.md`；发布级别变更仍须按发布规则更新 `CHANGELOG.md`

### 11.4 JavaDoc 规范

JavaDoc 规范统一参考 `docs/dev/convention/java-convention.md` 与相关专题规范文档，本文件不再维护示例模板。

### 11.5 CHANGELOG 格式

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

### 11.6 参考文档链接

| 文档 | 链接 |
|------|------|
| 领域设计基本概念 | `docs/domain-driven-design/` |
| 标准项目示例 | https://github.com/Redlotus794/java-spring-boot-ddd-example |

---

*最后更新：2026-05-22*
