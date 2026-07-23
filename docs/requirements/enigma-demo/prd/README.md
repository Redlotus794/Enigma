# enigma-demo PRD 索引

> 本目录按业务模块维护 `enigma-demo` 产品需求，避免单一 PRD 文件过大。

## 文档结构

| 序号 | 文档 | 内容 |
|------|------|------|
| 00 | [00-overview.md](00-overview.md) | 背景、产品定位、目标用户、产品目标 |
| 01 | [01-product-scope.md](01-product-scope.md) | 示例业务方向、业务范围、产品边界 |
| 02 | [02-business-scenarios.md](02-business-scenarios.md) | 核心业务场景、基础流程、展示范围 |
| 03 | [03-character-management.md](03-character-management.md) | 角色创建、查看、状态调整、装备栏 |
| 04 | [04-orbment-system.md](04-orbment-system.md) | 导力器槽位、回路配置、属性值和导力魔法 |
| 05 | [05-map-system.md](05-map-system.md) | 城市地图、区域、地点、NPC、商店、任务入口、剧情触发点 |
| 06 | [06-inventory-and-shop.md](06-inventory-and-shop.md) | 背包系统、物品数量、商店购买 |
| 07 | [07-mission-management.md](07-mission-management.md) | 任务来源、任务状态、任务推进 |
| 08 | [08-exceptions-and-acceptance.md](08-exceptions-and-acceptance.md) | 异常操作处理、测试与验收标准 |

## 维护规则

- 产品定位、目标用户、非目标更新到 `00-overview.md`。
- 子域范围、阶段边界更新到 `01-product-scope.md`。
- 新增业务模块时新增独立 Markdown，并在本索引登记。
- 跨模块规则优先放入对应模块文档；只有全局异常和验收标准放入 `08-exceptions-and-acceptance.md`。

