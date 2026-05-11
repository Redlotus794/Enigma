## 1. 依赖与构建准备

- [x] 1.1 在 `enigma-bom/pom.xml` 中增加 BDD 相关依赖版本管理，确保版本统一且不在子模块硬编码。
- [x] 1.2 在 `enigma-test-spring-boot-starter/pom.xml` 中声明 BDD 所需依赖，并确认该模块继续以测试基础设施角色对外提供能力。
- [x] 1.3 检查 `maven-surefire-plugin` / `maven-failsafe-plugin` 对 BDD demo 的执行方式，必要时补充兼容配置。

## 2. enigma-test 公共 BDD 支撑实现

- [x] 2.1 在 `enigma-test-spring-boot-starter/src/main/java` 中新增 BDD 测试公共支撑类或注解，提供 Spring Boot 上下文桥接能力。
- [x] 2.2 为公共支撑类补充 JavaDoc、非空约束和必要的使用说明，符合 Java 8 与项目规范。
- [x] 2.3 校验新增支撑类不引入生产业务逻辑，保持 `enigma-test-spring-boot-starter` 的测试模块职责边界。

## 3. 实际 BDD demo 实现

- [x] 3.1 在 `enigma-test-spring-boot-starter/src/test/resources/features` 下新增“轨迹风格任务评级” Feature 文件。
- [x] 3.2 在 `enigma-test-spring-boot-starter/src/test/java` 下新增步骤定义、测试入口类和 demo 用示例服务。
- [x] 3.3 实现“高完成度且无伤亡的支援任务被评为 S 级”的断言链路，确保场景具备真实业务规则。
- [x] 3.4 确认 demo 目录结构可作为业务项目复制样板，包括 Feature、steps、runner/入口类与示例服务位置。

## 4. 文档与变更记录

- [x] 4.1 更新 `enigma-test-spring-boot-starter/README.md`，补充 BDD 依赖引入、目录结构、运行方式和 demo 说明。
- [x] 4.2 按需更新根目录 `README.md` 或相关文档，说明 `enigma-test-spring-boot-starter` 已具备 BDD 测试能力。
- [x] 4.3 更新 `CHANGELOG.md`，记录新增 BDD 测试组件与 demo。

## 5. 验证与交付

- [ ] 5.1 在 `enigma-test-spring-boot-starter` 模块执行 Maven 测试，验证 BDD demo 可运行。
- [ ] 5.2 在聚合构建入口执行校验，确认 BOM、测试模块和相关依赖没有破坏现有构建。
- [x] 5.3 记录推荐验证命令：`cd enigma-test-spring-boot-starter && mvn clean test`、`cd enigma-parent && ./mvnw clean verify`。
- [x] 5.4 在需要发布或部署时，检查版本号、`CHANGELOG.md`、settings 文件路径 `/Users/wangjialong/maven/settings/settings-smil.xml`，并确认连接 Smil Nexus 前已关闭 VPN。

