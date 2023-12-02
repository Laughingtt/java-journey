<!-- TOC -->
* [application配置](#application配置)
    * [1. 简单属性配置](#1-简单属性配置)
    * [2. 多环境配置](#2-多环境配置)
    * [3. 数据源配置](#3-数据源配置)
    * [4. 日志配置](#4-日志配置)
    * [5. 配置列表](#5-配置列表)
    * [6. 嵌套配置](#6-嵌套配置)
    * [7. 配置激活的 profile](#7-配置激活的-profile)
    * [8. 自定义属性](#8-自定义属性)
<!-- TOC -->

# application配置
`application.yml` 是 Spring Boot 中用于配置应用程序属性的配置文件之一。它使用 YAML（YAML Ain't Markup Language）格式，相对于传统的 `application.properties` 文件，YAML 提供了更加清晰、可读性更强的配置方式。

以下是一些常见的 `application.yml` 文件配置规则和用法：

### 1. 简单属性配置

```yaml
# 设置端口号
server:
  port: 8080

# 应用名称
spring:
  application:
    name: my-application
```

### 2. 多环境配置

```yaml
# 公共配置
common:
  key: value

# 开发环境配置
dev:
  <<: *common
  database:
    url: jdbc:mysql://localhost:3306/devdb
    username: devuser
    password: devpassword

# 生产环境配置
prod:
  <<: *common
  database:
    url: jdbc:mysql://localhost:3306/proddb
    username: produser
    password: prodpassword
```

### 3. 数据源配置

```yaml
spring:
  datasource:
    url: jdbc:mysql://localhost:3306/mydb
    username: myuser
    password: mypassword
    driver-class-name: com.mysql.cj.jdbc.Driver
```

### 4. 日志配置

```yaml
logging:
  level:
    root: info
    com.example.mypackage: debug
```

### 5. 配置列表

```yaml
mylist:
  - item1
  - item2
  - item3
```

### 6. 嵌套配置

```yaml
server:
  port: 8080
  servlet:
    context-path: /myapp
```

### 7. 配置激活的 profile

```yaml
spring:
  profiles:
    active: dev
```

### 8. 自定义属性

```yaml
custom:
  property:
    key: value
```

在应用程序中，你可以通过 `@Value` 注解、`@ConfigurationProperties` 注解等方式来读取这些配置。例如：

```java
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class MyComponent {

    @Value("${server.port}")
    private int serverPort;

    @Value("${spring.application.name}")
    private String applicationName;

    // 省略其他代码
}
```

或者使用 `@ConfigurationProperties` 注解：

```java
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties("custom.property")
public class MyComponent {

    private String key;
    private String value;

    // 省略其他代码，包括 getter 和 setter
}
```