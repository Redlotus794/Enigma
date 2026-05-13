# DDD 代码规范约束

## 总体边界

- 领域层（`domain` 包）仅包含领域模型（聚合、实体、值对象）、领域服务接口、领域事件、领域仓储接口等核心业务模型与抽象，不包含任何技术实现细节（如持久化对象、ORM 映射、外部客户端调用等）。技术实现细节应完全封装在基础设施层（`infrastructure` 包）中。
  - 其中包括 Spring 相关的注解 @Component, @Service, @Repository 等也不应出现在领域层代码中。

## 实体

- `DomainEntity` 的 PKType 尽可能避免Java的基本类型（比如String, Integer等），建议使用值对象（ValueObject）作为主键类型，使用值对象。

