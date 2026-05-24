# 上下文间集成策略

> 本文档定义 enigma-demo 限界上下文之间的通信机制和技术选型。

## 集成原则

1. **核心上下文优先保持独立**：角色任务准备上下文不依赖展示层 DTO、测试夹具或外部系统模型。
2. **第一阶段优先同步调用**：demo 范围小、运行环境简单，应用服务或 REST API 足以表达业务闭环。
3. **领域事件作为后续展示能力**：若需要展示 Enigma 事件能力，再引入领域事件，不在 MVP 中强制增加复杂度。
4. **避免共享数据库**：上下文之间不直接共享数据存储。
5. **防腐层隔离外部模型**：后续接入外部游戏资料或物品库时，必须通过 ACL 转换为本地模型。

## 集成方式对比

| 方式 | 适用场景 | 优点 | 缺点 | 本项目建议 |
|------|----------|------|------|------------|
| Domain Event | 后续展示领域事件、记录角色状态变化 | 松耦合，可追溯 | MVP 会增加复杂度 | 候选，不作为第一阶段必需 |
| REST API | demo 对外演示创建、查询、调整等能力 | 简单直观，易调试 | 需要 DTO 和异常响应规范 | 推荐作为展示入口 |
| Application Service | 同进程内编排领域行为 | 简单、贴近 DDD 分层 | 不便于外部直接调用 | 推荐作为核心调用方式 |
| Message Queue | 异步解耦、高并发 | 可扩展 | 引入中间件，不符合非目标 | 第一阶段不使用 |
| 防腐层（ACL） | 接入外部装备、回路或游戏资料 | 保护内部模型 | 增加转换成本 | 后续外部集成时使用 |
| Shared Database | 跨上下文直接读写数据 | 表面简单 | 高耦合，不推荐 | 禁止 |

## 集成策略详情

### 示例展示上下文 -> 角色任务准备上下文

**集成方式**：Application Service / REST API

**触发时机**：使用者创建角色、查看角色状态、调整角色状态、更换装备、配置导力器时。

**数据格式**：命令 DTO、查询 DTO、角色状态视图 DTO。

**接口定义：**

| 能力 | 输入 | 输出 |
|------|------|------|
| 创建角色 | CreateCharacterCommand | CharacterStatusView |
| 查看角色状态 | CharacterId | CharacterStatusView |
| 调整角色状态 | AdjustCharacterStatusCommand | CharacterStatusView |
| 更换装备 | ChangeEquipmentCommand | EquipmentLoadoutView |
| 配置导力器 | ConfigureOrbmentCommand | OrbmentView |

**示例契约：**

```java
public class CreateCharacterCommand {

    private String characterName;

    private Integer level;

    private Integer hp;

    private Integer mp;

    private Integer cp;

    private Integer str;

    private Integer def;

    private Integer agl;

    private Integer ats;

    private Integer adf;

    private Integer spd;

    private Integer dex;

    private Integer mov;

    private Integer rng;
}
```

### 规则验证上下文 -> 角色任务准备上下文

**集成方式**：测试直接调用领域对象或应用服务。

**触发时机**：执行单元测试、模块级 Maven 测试或验收测试时。

**数据格式**：测试构造对象、命令对象、断言结果、异常断言。

**接口定义：**

| 验证目标 | 调用对象 | 断言重点 |
|----------|----------|----------|
| 角色创建规则 | Character 创建行为或应用服务 | 空名称、等级小于 1、负数属性被拒绝 |
| 状态调整规则 | Character 状态调整行为 | 新状态必须满足业务规则 |
| 装备栏规则 | Character 更换装备行为 | 栏位存在、单栏位单装备、替换和卸下结果 |
| 导力器规则 | Character 配置导力器行为 | 槽位存在、单槽位单回路、核心槽位和普通槽位数量 |

### 应用运行上下文 -> 其他上下文

**集成方式**：Spring Boot 自动装配、Maven 构建。

**触发时机**：应用启动、测试执行、构建验证。

**数据格式**：Spring Bean、配置属性、Maven 依赖。

**接口定义**：启动类、配置文件、POM 依赖声明。

## 跨上下文领域事件清单

第一阶段不强制实现领域事件，以下事件作为后续展示 Enigma DDD 事件能力的候选。

| 事件名 | 事件类型 | 发布上下文 | 订阅上下文 | 事件内容概要 |
|--------|----------|------------|------------|--------------|
| CharacterCreated | Event | 角色任务准备上下文 | 示例展示上下文、规则验证上下文 | 角色创建成功后的标识、名称和初始状态 |
| CharacterStatusAdjusted | Event | 角色任务准备上下文 | 示例展示上下文、规则验证上下文 | 角色状态调整后的状态摘要 |
| EquipmentChanged | Event | 角色任务准备上下文 | 示例展示上下文、规则验证上下文 | 装备栏位、装备、操作类型 |
| OrbmentConfigured | Event | 角色任务准备上下文 | 示例展示上下文、规则验证上下文 | 导力器槽位、回路、操作类型 |

## 事件详细定义（候选）

### CharacterCreated

```json
{
  "eventType": "CharacterCreated",
  "eventId": "uuid",
  "occurredAt": "2026-05-24T00:00:00Z",
  "aggregateId": "characterId",
  "payload": {
    "characterId": "characterId",
    "characterName": "Lloyd Bannings",
    "level": 1
  }
}
```

### EquipmentChanged

```json
{
  "eventType": "EquipmentChanged",
  "eventId": "uuid",
  "occurredAt": "2026-05-24T00:00:00Z",
  "aggregateId": "characterId",
  "payload": {
    "characterId": "characterId",
    "equipmentSlot": "WEAPON",
    "operation": "REPLACE"
  }
}
```

## 错误处理策略

| 场景 | 处理方式 | 重试策略 | 补偿机制 |
|------|----------|----------|----------|
| 创建角色输入非法 | 返回业务错误或抛出业务异常 | 不重试 | 不创建角色 |
| 调整状态输入非法 | 返回业务错误或抛出业务异常 | 不重试 | 保持原状态 |
| 角色不存在 | 返回未找到错误 | 不重试 | 无 |
| 装备栏位不存在 | 返回业务错误或抛出业务异常 | 不重试 | 保持原装备栏 |
| 导力器槽位不存在 | 返回业务错误或抛出业务异常 | 不重试 | 保持原导力器配置 |
| 后续事件重复投递 | 订阅方按事件 ID 幂等处理 | 可重试 | 忽略重复事件 |

## 幂等性设计

第一阶段以同步命令为主，幂等性要求较低。若后续引入事件或重复提交场景，应遵循：

- 事件 ID 作为幂等键。
- 订阅方记录已处理事件 ID。
- 命令侧可按业务请求号避免重复创建角色。
- 对替换和卸下类操作保持结果幂等。

## MVP 集成结论

MVP 使用应用服务作为核心集成方式，REST API 作为可选展示入口，测试直接调用领域行为或应用服务。消息队列、防腐层和跨上下文领域事件作为后续迭代能力，不进入第一阶段强制范围。

