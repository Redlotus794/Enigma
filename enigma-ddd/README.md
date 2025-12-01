# Enigma Domain Driven Design (Enigma DDD)

## 目录
- [项目简介](#项目简介)
- [快速开始](#快速开始)
- [核心概念](#核心概念)
- [最佳实践](#最佳实践)

### 项目简介

Enigma DDD 是一个基于领域驱动设计（Domain Driven Design）理念构建的框架或项目模板，旨在帮助开发团队更好地实现复杂业务系统的建模与开发。

### 快速开始

- 引入依赖：将 Enigma DDD 相关依赖添加到项目的构建配置文件中（如 Maven 的 `pom.xml` 或 Gradle 的 `build.gradle`）。
```xml
<!-- maven版本 -->
<dependency>
    <groupId>com.rdlts.enigma</groupId>
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
                <groupId>com.rdlts.enigma</groupId>
                <artifactId>enigma-ddd-core</artifactId>
                <version>1.0.0</version>
            </path>
        </annotationProcessorPaths>
    </configuration>
</plugin>
```


### 核心概念

**领域驱动设计**：一种软件开发方法论，强调以业务领域为核心进行软件设计

**限界上下文**：定义领域模型的边界，确保模型的统一语言在特定边界内保持一致

**统一语言**：团队成员之间沟通使用的通用术语集合


### 最佳实践

待补充


