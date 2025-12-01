# FAQ

## 目录

* [如何调试Annotation Processor](#如何调试Annotation Processor)


## 如何调试Annotation Processor

使用maven参数定义
```shell
# 1. 设置MAVEN_OPTS (MACOS / Linux)
export MAVEN_OPTS=-agentlib:jdwp=transport=dt_socket,server=y,suspend=y,address=5005

# 2. 执行编译命令
mvn clean compile
```
执行mvn clean compile后，控制台会输出：Listening for transport dt_socket at address: 5005

这表示 Maven 的 JVM 进程已进入调试等待状态，此时再启动 IDEA 的 Remote Debug 配置即可连接。

正常参数

```shell
export MAVEN_OPTS="-Xms512m -Xmx2048m -XX:MetaspaceSize=128m -XX:MaxMetaspaceSize=512m"
```
