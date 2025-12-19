# 变更项目版本
mvn versions:set -DnewVersion=1.1.0.RELEASE -DprocessAllModules=true -DgenerateBackupPoms=true
# 提交
mvn versions:commit
# 撤回
mvn versions:revert