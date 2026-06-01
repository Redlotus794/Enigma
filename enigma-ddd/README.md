# Enigma Domain Driven Design (Enigma DDD)

## 目录
- [项目简介](#项目简介)
- [快速开始](#快速开始)
- [核心概念](#核心概念)
- [准实时事件](#准实时事件)
- [最佳实践](#最佳实践)

### 项目简介

Enigma DDD 是一个基于领域驱动设计（Domain Driven Design）理念构建的框架或项目模板，旨在帮助开发团队更好地实现复杂业务系统的建模与开发。


### 快速开始

- 引入依赖：将 Enigma DDD 相关依赖添加到项目的构建配置文件中（如 Maven 的 `pom.xml` 或 Gradle 的 `build.gradle`）。
```xml
<!-- maven版本 -->
<dependency>
    <groupId>io.github.redlotus794</groupId>
    <artifactId>enigma-ddd</artifactId>
    <version>1.0.0</version>
</dependency>
```

- 可选：添加 Enigma 编译器插件
    - ValueObjectProcessor: 编译时检查@ValueObject类是否有setter方法。
```xml
<!-- maven编译插件 -->
<plugin>
    <groupId>org.apache.maven.plugins</groupId>
    <artifactId>maven-compiler-plugin</artifactId>
    <version>3.8.1</version>
    <configuration>
        <source>1.8</source>
        <target>1.8</target>
        <showWarnings>true</showWarnings>
        <compilerArgs>
            <arg>-Xlint:-processing</arg>
        </compilerArgs>
        <annotationProcessorPaths>
            <path>
                <groupId>io.github.redlotus794</groupId>
                <artifactId>enigma-ddd-core</artifactId>
                <version>1.0.0</version>
            </path>
        </annotationProcessorPaths>
    </configuration>
</plugin>
```

- Enigma-spring 配置
```yaml
enigma:
  spring: 
    enabled: true # 默认为true，
```

- 自定义DDD组件

通过覆盖META-INF/services，实现自定义功能：
```text
io.github.redlotus794.enigma.ddd.core.event.DomainEventPublisher
领域事件发布: EnigmaDomainEventPublisher，默认基于Spring Event的发布机制实现

io.github.redlotus794.enigma.ddd.core.service.DomainServiceRegistry
领域服务注册: EnigmaDomainServiceRegistry，默认基于Spring ApplicationContext实现
```

可以通过实现对应的接口组件，来覆盖默认的组件行为
```text
io.github.redlotus794.enigma.ddd.core.event.DomainEventRepository
领域事件资源库持久化: EnigmaDomainEventRepository，默认只记录在日志中
```

- 创建领域模型：领域实体，值对象，聚合，领域事件，领域服务等
```java
/**
 * TestDomainEntity
 *
 * @author wangjialong
 * @since 2025/11/27 14:59
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Builder
public class TestDomainEntity implements DomainEntity<TestId>, DomainEventParam {

    @NonNull
    TestId testId;

    @NonNull
    @Builder.Default
    EntityVersion entityVersion = EntityVersion.ZERO_VERSION;

    @NonNull
    @Override
    public TestId identity() {
        return testId;
    }

    @NonNull
    @Override
    public EntityVersion version() {
        return entityVersion;
    }
}

```

- 使用DomainEvent解耦领域逻辑，实现限界上下文之间的通信
```java
/**
 * TestDomainEvent
 *
 * @author wangjialong
 * @since 2025/12/2 16:09
 */
public class TestDomainEvent extends DomainEvent<TestDomainEntity> {

    public TestDomainEvent() {
        super();
    }

    public TestDomainEvent(@NonNull TestDomainEntity eventContent) {
        super(eventContent);
    }
}
```

### 准实时事件

准实时事件用于处理“需要在事务完成后尽快执行，但不需要进入持久化事件流”的本地回调逻辑。该机制由 `enigma-ddd-spring-boot-starter` 提供，核心类型如下：

| 类型 | 作用 |
|------|------|
| `RealTimeExecutionEvent` | 准实时事件，不持久化，不支持重放 |
| `RealTimeExecutionParam` | 准实时事件参数，同时继承 `Runnable`，具体执行逻辑写在 `run()` 中 |
| `RealTimeExecutionEventListener` | Spring 事务事件监听器，在 `AFTER_COMPLETION` 阶段处理事件 |
| `TransactionalEventProxy` | 事务事件执行代理，可替换默认执行策略 |
| `DefaultTransactionalEventProxy` | 默认代理，直接在当前线程执行 `Runnable` |

#### 执行时机

`RealTimeExecutionEventListener` 使用 `@TransactionalEventListener(phase = TransactionPhase.AFTER_COMPLETION, fallbackExecution = true)`：

- 存在 Spring 事务时，在事务完成后执行，包括提交完成和回滚完成。
- 不存在 Spring 事务时，因为启用了 `fallbackExecution`，事件会按 Spring Event 的普通监听流程执行。
- 默认 `TransactionalEventProxy` 为同步执行，耗时任务、重试、异步线程池和异常隔离需要通过自定义代理实现。
- `RealTimeExecutionEvent` 的 `isPersistable()` 返回 `false`，`replay()` 会抛出 `UnsupportedOperationException`，因此不能作为可追溯、可补偿、可重放的领域事件使用。

#### 适用场景

- 事务完成后刷新本地缓存。
- 事务完成后触发轻量级本地通知。
- 领域事件发布过程中，延后执行事件仓库保存等本地基础设施动作。

不建议将准实时事件用于跨进程消息投递、强一致外部调用、长耗时任务或必须在事务提交成功后才执行的逻辑。当前监听阶段是 `AFTER_COMPLETION`，如果业务只允许提交后执行，应单独扩展提交后事件监听策略。

#### 使用示例

定义准实时事件参数：

```java
import io.github.redlotus794.enigma.ddd.spring.event.RealTimeExecutionParam;

public class RefreshOrderCacheParam implements RealTimeExecutionParam {

    private final String orderId;

    private final OrderCacheService orderCacheService;

    public RefreshOrderCacheParam(String orderId, OrderCacheService orderCacheService) {
        this.orderId = orderId;
        this.orderCacheService = orderCacheService;
    }

    @Override
    public void run() {
        orderCacheService.refresh(orderId);
    }
}
```

发布准实时事件：

```java
import io.github.redlotus794.enigma.ddd.core.event.DomainEventPublisher;
import io.github.redlotus794.enigma.ddd.spring.event.RealTimeExecutionEvent;

public class OrderApplicationService {

    private final DomainEventPublisher domainEventPublisher;

    private final OrderCacheService orderCacheService;

    public OrderApplicationService(
            DomainEventPublisher domainEventPublisher,
            OrderCacheService orderCacheService) {
        this.domainEventPublisher = domainEventPublisher;
        this.orderCacheService = orderCacheService;
    }

    public void confirm(String orderId) {
        // 执行业务事务内的领域逻辑和资源库保存

        domainEventPublisher.publish(new RealTimeExecutionEvent(
                new RefreshOrderCacheParam(orderId, orderCacheService)
        ));
    }
}
```

如果只想发布 Spring 本地事件，也可以直接使用 `ApplicationEventPublisher` 发布 `RealTimeExecutionEvent`。如果通过 `DomainEventPublisher` 发布，需要注意 `EnigmaSpringDomainEventPublisher#setActive(false)` 会在线程级别跳过事件发布。

#### 自定义执行代理

默认代理会同步执行 `Runnable`。如果项目需要异步执行，可以提供自己的 `TransactionalEventProxy` Bean 覆盖默认装配：

```java
import io.github.redlotus794.enigma.ddd.spring.event.TransactionalEventProxy;
import org.springframework.context.annotation.Bean;
import org.springframework.core.task.TaskExecutor;
import org.springframework.transaction.event.TransactionPhase;

@Bean
public TransactionalEventProxy transactionalEventProxy(TaskExecutor taskExecutor) {
    return new TransactionalEventProxy() {
        @Override
        public void execute(Runnable runnable, TransactionPhase phase) {
            taskExecutor.execute(runnable);
        }
    };
}
```

异步代理需要自行处理异常记录、重试、线程池容量和上下文传递，避免准实时任务失败后影响主业务链路的可观测性。


### 核心概念

**领域驱动设计**：一种软件开发方法论，强调以业务领域为核心进行软件设计

**限界上下文**：定义领域模型的边界，确保模型的统一语言在特定边界内保持一致

**统一语言**：团队成员之间沟通使用的通用术语集合


### 最佳实践

待补充


### 版本变更命令
```shell
mvn versions:set -DnewVersion=2.0.0-SNAPSHOT
# 提交
mvn versions:commit
# 回退
mvn versions:revert
```
