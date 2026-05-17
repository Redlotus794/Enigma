# enigma-demo

`enigma-demo` 是基于 `enigma-ddd-spring-boot-starter` 构建的示例业务项目，演示如何在继承 `enigma-parent` 的前提下快速搭建一个可运行的 Spring Boot + DDD 应用。

## 模块职责

- 作为 Enigma 业务项目模板，演示父 POM 继承方式
- 演示 `enigma-ddd-spring-boot-starter` 的自动装配能力
- 提供一个最小可运行的 DDD 分层示例：接口层、应用层、领域层、基础设施层

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

## 核心功能示例

启动后可使用如下接口体验示例能力：

- `GET /api/v1/demo-tasks`：查询任务列表
- `POST /api/v1/demo-tasks`：创建任务
- `GET /api/v1/demo-tasks/{id}`：查询单个任务
- `DELETE /api/v1/demo-tasks/{id}`：删除任务

示例请求：

```bash
curl -X POST 'http://localhost:8080/api/v1/demo-tasks' \
  -H 'Content-Type: application/json; charset=UTF-8' \
  -d '{"name":"learn enigma starter"}'
```

## 配置说明

默认配置如下：

```yaml
spring:
  application:
    name: enigma-demo

enigma:
  spring:
    enabled: true
```

## 构建与运行

```bash
cd /Users/wangjialong/github/Enigma/enigma-parent
./mvnw -pl ../enigma-demo -am clean test
./mvnw -pl ../enigma-demo -am spring-boot:run
```

