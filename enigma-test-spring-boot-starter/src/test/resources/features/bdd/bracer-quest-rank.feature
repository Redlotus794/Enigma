Feature: 游击士支援任务评级

  Scenario: 高完成度且无伤亡的支援任务应被评为 S 级
    Given 支援任务完成度为 95
    And 该任务未造成平民伤亡
    When 执行任务评级结算
    Then 任务评级应为 S

  Scenario: 即使完成度较高但造成平民伤亡也不能获得高评级
    Given 支援任务完成度为 92
    And 该任务造成了平民伤亡
    When 执行任务评级结算
    Then 任务评级应为 B


