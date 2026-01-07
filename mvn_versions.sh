#!/bin/bash

# SDD编程
# 执行./mvn_versions.sh set <new_version> 来更新所有项目的版本号
# 执行./mvn_versions.sh commit 来提交所有项目的版本更改（可选）
# 执行./mvn_versions.sh revert 来回滚所有项目的版本更改（可选）
# 执行./mvn_versions.sh revert -Dmaven.wagon.http.ssl.insecure=true -Dmaven.wagon.http.ssl.allowall=true 需要将mvn的参数添加到命令后面

# 检查参数数量
if [ $# -lt 1 ]; then
    echo "用法: $0 set <new_version> | commit | rollback"
    echo "例如: $0 set 1.2.3"
    echo "      $0 commit"
    echo "      $0 rollback"
    exit 1
fi

COMMAND=$1
NEW_VERSION=""
if [ "$COMMAND" = "set" ]; then
    if [ $# -ne 2 ]; then
        echo "用法: $0 set <new_version>"
        exit 1
    fi
    NEW_VERSION=$2
    shift

    # 验证版本号格式
    if [[ ! $NEW_VERSION =~ ^[0-9]+\.[0-9]+\.[0-9]+.*$ ]]; then
        echo "错误: 版本号格式不正确。请使用类似 1.2.3 的格式。"
        exit 1
    fi
fi

shift
ADDITIONAL_MAVEN_OPTS="$*"

# 定义项目目录数组
PROJECTS=("enigma-bom" "enigma-parent" "enigma-ddd")

case $COMMAND in
    "set")
        echo "开始更新版本到: $NEW_VERSION"
        for project in "${PROJECTS[@]}"; do
            if [ -d "$project" ]; then
                echo "正在更新 $project 的版本..."
                cd "$project"

                mvn versions:set -DnewVersion=$NEW_VERSION $ADDITIONAL_MAVEN_OPTS

                if [ $? -eq 0 ]; then
                    echo "成功更新 $project 的版本"
                else
                    echo "错误: 更新 $project 的版本失败"
                fi

                cd ..
            else
                echo "警告: 项目目录 $project 不存在"
            fi
        done
        ;;
    "commit")
        echo "提交版本更改..."
        for project in "${PROJECTS[@]}"; do
            if [ -d "$project" ]; then
                echo "正在提交 $project 的版本更改..."
                cd "$project"

                mvn versions:commit $ADDITIONAL_MAVEN_OPTS

                if [ $? -eq 0 ]; then
                    echo "成功提交 $project 的版本更改"
                else
                    echo "错误: 提交 $project 的版本更改失败"
                fi

                cd ..
            else
                echo "警告: 项目目录 $project 不存在"
            fi
        done
        ;;
    "revert")
        echo "恢复版本更改..."
        for project in "${PROJECTS[@]}"; do
            if [ -d "$project" ]; then
                echo "正在恢复 $project 的版本更改..."
                cd "$project"

                mvn versions:revert $ADDITIONAL_MAVEN_OPTS

                if [ $? -eq 0 ]; then
                    echo "成功恢复 $project 的版本更改"
                else
                    echo "错误: 恢复 $project 的版本更改失败"
                fi

                cd ..
            else
                echo "警告: 项目目录 $project 不存在"
            fi
        done
        ;;
    *)
        echo "错误: 不支持的命令 '$COMMAND'"
        echo "支持的命令: set, commit, revert"
        exit 1
        ;;
esac

echo "操作完成！"