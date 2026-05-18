# enigma-demo

`enigma-demo` 是基于 `enigma-ddd-spring-boot-starter` 构建的示例业务模块。
当前已补充最小领域模型示例，包含 `Equipment` 实体与 `EquipmentId` 值对象，其余 Demo 内容可继续按新的设计扩展。

## 模块职责

- 作为 Enigma 业务项目模板，演示父 POM 继承方式
- 预留 `userinterface`、`application`、`domain`、`infrastructure` 等 DDD 分层包结构
- 供后续重新设计 Demo 示例内容时继续扩展

## Maven 引入方式

本模块本身为业务示例工程，已通过以下方式继承 Enigma 父工程：

```xml
<parent>
    <groupId>com.rdlts.enigma</groupId>
    <artifactId>enigma-parent</artifactId>
    <version>1.2.0-SNAPSHOT</version>
    <relativePath>../enigma-parent/pom.xml</relativePath>
</parent>
```

核心依赖：

```xml
<dependency>
    <groupId>com.rdlts.enigma</groupId>
    <artifactId>enigma-ddd-spring-boot-starter</artifactId>
</dependency>
```

## 当前状态

当前模块已包含以下基础包结构与领域对象：

- `com.rdlts.enigma.demo`
- `com.rdlts.enigma.demo.application`
- `com.rdlts.enigma.demo.domain`
- `com.rdlts.enigma.demo.infrastructure`
- `com.rdlts.enigma.demo.userinterface`
- `com.rdlts.enigma.demo.userinterface.openapi`
- `com.rdlts.enigma.demo.domain.entity.Equipment`
- `com.rdlts.enigma.demo.domain.valueobject.EquipmentId`

## 配置说明

当前保留基础配置文件，后续可按新的 Demo 设计继续扩展：

```yaml
spring:
  application:
    name: enigma-demo

enigma:
  spring:
    enabled: true
```

## 构建与验证

```bash
cd /Users/wangjialong/github/Enigma/enigma-demo
mvn -Djacoco.skip=true test
mvn -Djacoco.skip=true verify
```

