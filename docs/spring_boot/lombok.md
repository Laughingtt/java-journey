<!-- TOC -->
* [lombok](#lombok)
  * [1. pom依赖](#1-pom依赖)
  * [2. 在实体类中使用 Lombok 注解](#2-在实体类中使用-lombok-注解)
  * [3. 使用Lombok的日志记录功能：](#3-使用lombok的日志记录功能)
<!-- TOC -->

# lombok

Lombok 是一个 Java 库，它通过注解消除了一些常见的样板代码，例如 getters、setters、equals 和 hashCode 方法。在Spring Boot中，使用 Lombok 可以减少冗长的代码，提高开发效率。

## 1. pom依赖

```xml
<dependencies>
    <!-- 其他依赖 -->
    <dependency>
        <groupId>org.projectlombok</groupId>
        <artifactId>lombok</artifactId>
        <version>1.18.22</version> <!-- 根据实际情况选择最新版本 -->
        <scope>provided</scope>
    </dependency>
</dependencies>

```

## 2. 在实体类中使用 Lombok 注解

使用 Lombok 的注解来简化实体类的代码。以下是一些常用的注解：

@Data: 自动生成 toString、equals、hashCode、Getter、Setter 方法。
@NoArgsConstructor: 自动生成无参构造方法。
@AllArgsConstructor: 自动生成包含所有字段的构造方法。
@Builder: 自动生成构造器，用于构建对象。
@Slf4j: 自动生成 Slf4j 日志变量。

## 3. 使用Lombok的日志记录功能：

使用@Slf4j注解，Lombok可以为你自动生成日志变量，使得日志记录更加简单。


```java
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class LogExampleService {

    public void performSomeAction() {
        log.debug("Debug message");
        log.info("Info message");
        log.warn("Warning message");
        log.error("Error message");
    }
}

```