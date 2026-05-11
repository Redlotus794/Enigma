# Enigma

命名由来：艾尼格玛（Enigma）   
《零》由爱普斯泰恩财团研发的第五代战术导力器，造型如同现实世界的折叠式移动电话，也附带了通讯的功能。通讯功能只在设有导力网络的地点才可使用。


## ⚽️ 项目信息

- Github: [Enigma](https://github.com/Redlotus794/Enigma)
- 通用文档项目：[领域驱动设计](https://wcnn2j4xsnan.feishu.cn/wiki/H8iEwnymcir1B1kut1UcKDThnOg)
- 标准项目参考: [java-spring-boot-ddd-example](https://github.com/Redlotus794/java-spring-boot-ddd-example)
- 其他参考项目：Java 17版本 - [Arcus](https://github.com/Redlotus794/Arucs)


## 🚴‍♂️ 背景

本项目是基于Spring Boot + Java 8 + Maven 的开发标准框架，用于快速开发Spring Boot项目。     
项目基于GNU General Public License v3.0（GPL-3.0）协议。  


## 🔯 快速开始
1. 使用maven编译项目
```shell
cd enigma-ddd
# 自动使用 .mvn 目录中的配置
mvn clean install

# 或者使用 Maven Wrapper（推荐）
./mvnw clean install
```

2. maven变更版本
```shell
./mvn_versions set 1.0.0 
./mvn_versions commit 
```

## 🧩 技术栈使用

Enigma 提供技术栈列表

| 组件           | 版本            | 部署        |
|--------------|---------------|-----------|
| Maven        | 3.5+ (Java 8) | Apache 官网 |
| Java         | 8             | JVM       |
| JaCoCo       | 0.8.7         | 单元测试框架    |
| Maven Surefire | 3.0.0-M7      | 单元测试框架    |


## 🚀 项目列表
- [enigma-bom](enigma-bom/README.md): maven 包管理项目
- [enigma-common](enigma-common/README.md): 基础类通用项目
- [enigma-ddd](enigma-ddd/README.md): 领域驱动设计项目
    - enigma-ddd-core: 领域驱动设计核心模块
    - enigma-ddd-spring-boot-starter: 领域驱动设计Spring实现
- enigma-parent: Enigma父项目
- [enigma-random-spring-boot-starter](enigma-random-spring-boot-starter/README.md): 随机对象构建的Spring boot项目
- [enigma-test-spring-boot-starter](enigma-test-spring-boot-starter/README.md): 测试框架Spring Boot项目，提供公共测试启动类与 BDD 测试样板
- [enigma-tools-spring-boot-starter](enigma-tools-spring-boot-starter/README.md): 工具类项目
- [enigma-tpc-spring-boot-starter](enigma-tpc-spring-boot-starter/README.md): TPC Spring Boot项目

## 🎹 项目规范

- 文档

**文档结构参考项目**: [java-spring-boot-ddd-example](https://github.com/Redlotus794/java-spring-boot-ddd-example)

**项目文档目录**: docs/

- 覆盖率要求

**插件**: maven-surefire-plugin, maven-failsafe-plugin, jacoco-maven-plugin

```text
Class : 95% 以上
Method: 95% 以上
Line: 95% 以上
Branch: 95% 以上
```

- 代码质量

**Alibaba Coding guidelines analyze**: IDE插件，阿里巴巴开源的代码质量检查工具。
