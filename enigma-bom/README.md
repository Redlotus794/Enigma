# Enigma BOM

Enigma 项目的 BOM (Bill of Materials) 模块，负责管理项目中的依赖版本，确保各个子模块使用一致的依赖版本，简化依赖管理过程。

## Quick Start

1. 使用 Maven :

```xml
<dependencyManagement>
    <dependencies>
        <dependency>
            <groupId>com.rdlts.enigma</groupId>
            <artifactId>enigma-bom</artifactId>
            <version>1.0.0-SNAPSHOT</version>
            <type>pom</type>
            <scope>import</scope>
        </dependency>
    </dependencies>
</dependencyManagement>
```
