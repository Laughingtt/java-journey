<!-- TOC -->
* [log](#log)
  * [logback和log4j的区别](#logback和log4j的区别)
  * [1. 添加Log4j2依赖：](#1-添加log4j2依赖)
  * [2. 配置Log4j2：](#2-配置log4j2)
  * [3. 在应用程序中使用日志：](#3-在应用程序中使用日志)
  * [4. logback](#4-logback)
  * [5. 配置文件](#5-配置文件)
  * [6. 使用示例](#6-使用示例)
  * [7. 将日志内容输出到文件](#7-将日志内容输出到文件)
<!-- TOC -->

# log

在Spring Boot中，你可以使用Log4j（或者Log4j2）作为日志框架，记录应用程序的日志信息。

## logback和log4j的区别

1. **性能：**

    * Logback 在性能方面通常被认为更优越。它是由 Ceki Gülcü 开发的，他也是 Log4j 的创始人，旨在解决 Log4j 中的一些性能问题。
    * Log4j2 是 Log4j 的新版本，也在性能上进行了改进，但在某些情况下可能略逊于 Logback。
2. **配置文件：**

    * Logback 的配置文件是 XML 格式的，但同时也支持 Groovy 格式。它的配置相对简单。
    * Log4j 使用 XML、JSON、YAML 等不同的格式，并且其配置文件可以更复杂，具有更多的选项。
3. **插件支持：**

    * Logback 对插件支持较差，它的核心功能相对单一，通常不需要太多的插件。
    * Log4j2 提供了更强大的插件系统，可以方便地扩展和定制，满足更复杂的需求。
4. **异步日志：**

    * Logback 支持异步日志，它有一个内建的异步 Appender。
    * Log4j2 在设计上更注重异步日志，通过异步日志可以提高性能，特别是在高并发的情况下。
5. **日志结构化：**

    * Logback 支持基本的日志结构化，可以使用 MDC（Mapped Diagnostic Context）来添加上下文信息。
    * Log4j2 在这方面更为强大，支持更灵活的上下文和标记，也提供了自定义的 ThreadContext。
6. **社区和维护：**

    * Logback 有一个较小但活跃的社区，由于性能和简单性，仍然是一些项目的首选。
    * Log4j2 作为 Log4j 的后继者，有一个庞大的社区，并且在功能和性能上有持续的改进。

## 1. 添加Log4j2依赖：
在你的项目中，确保在构建工具（例如Maven或Gradle）中添加Log4j2的依赖。在Maven中，可以在 pom.xml 文件中添加：


```xml
<dependencies>
    <!-- 其他依赖 -->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter</artifactId>
    </dependency>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-log4j2</artifactId>
    </dependency>
</dependencies>

```

## 2. 配置Log4j2：
在src/main/resources目录下创建一个log4j2.xml文件，用于配置Log4j2。

```yaml
<?xml version="1.0" encoding="UTF-8"?>
<Configuration status="WARN">
    <Appenders>
        <Console name="Console" target="SYSTEM_OUT">
            <PatternLayout pattern="%d{HH:mm:ss.SSS} [%t] %-5level %logger{36} - %msg%n"/>
        </Console>
    </Appenders>
    <Loggers>
        <Root level="info">
            <AppenderRef ref="Console"/>
        </Root>
    </Loggers>
</Configuration>

```

上述配置会将日志输出到控制台，并使用指定的格式进行输出。


## 3. 在应用程序中使用日志：
创建一个Spring Boot应用程序，并在代码中使用Log4j2记录日志。下面是一个简单的示例：

```java
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MyApplication {

    private static final Logger logger = LoggerFactory.getLogger(MyApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(MyApplication.class, args);
        
        // 记录日志
        logger.debug("Debug message");
        logger.info("Info message");
        logger.warn("Warning message");
        logger.error("Error message");
    }
}

```

在上述示例中，使用LoggerFactory.getLogger()方法获取日志记录器，然后使用不同的日志级别记录不同的日志信息。


## 4. logback

依赖
```xml
<dependencies>
    <!-- 其他依赖 -->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter</artifactId>
    </dependency>
</dependencies>

```

## 5. 配置文件
在 src/main/resources 目录下创建 logback-spring.xml 文件。Spring Boot 会自动识别并加载该配置文件。

```yaml
<?xml version="1.0" encoding="UTF-8"?>
<configuration>

    <!-- 定义日志输出格式 -->
    <property name="LOG_PATTERN" value="%d{HH:mm:ss.SSS} [%thread] %-5level %logger{36} - %msg%n" />

    <!-- 控制台输出 -->
    <appender name="CONSOLE" class="ch.qos.logback.core.ConsoleAppender">
        <encoder>
            <pattern>${LOG_PATTERN}</pattern>
        </encoder>
    </appender>

    <!-- 指定日志级别 -->
    <root level="info">
        <appender-ref ref="CONSOLE" />
    </root>

</configuration>

```
上述配置文件定义了一个简单的控制台输出 appender，输出格式为包含时间、线程、日志级别、logger 名称和消息。可以根据需要修改格式和添加其他 appender。

## 6. 使用示例
```java
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/example")
public class ExampleController {

    private static final Logger logger = LoggerFactory.getLogger(ExampleController.class);

    @GetMapping("/log")
    public String logExample() {
        logger.debug("Debug message");
        logger.info("Info message");
        logger.warn("Warning message");
        logger.error("Error message");

        return "Check the console for log messages";
    }
}

```
在上述例子中，使用 LoggerFactory.getLogger() 获取一个日志记录器，然后使用不同的日志级别记录不同的日志信息。

## 7. 将日志内容输出到文件

LOG_PATTERN 定义了日志输出的格式，包含日期、线程、日志级别、logger 名称和消息。
CONSOLE appender 将日志输出到控制台。
FILE appender 将日志输出到文件中，并使用滚动策略按日期分割文件，保留最近7天的日志文件。

```yaml
<?xml version="1.0" encoding="UTF-8"?>
<configuration>

    <!-- 定义日志输出格式 -->
    <property name="LOG_PATTERN" value="%d{yyyy-MM-dd HH:mm:ss.SSS} [%thread] %-5level %logger{36} - %msg%n" />

    <!-- 控制台输出 -->
    <appender name="CONSOLE" class="ch.qos.logback.core.ConsoleAppender">
        <encoder>
            <pattern>${LOG_PATTERN}</pattern>
        </encoder>
    </appender>

    <!-- 文件输出 -->
    <appender name="FILE" class="ch.qos.logback.core.rolling.RollingFileAppender">
        <file>logs/application.log</file>
        <rollingPolicy class="ch.qos.logback.core.rolling.TimeBasedRollingPolicy">
            <fileNamePattern>logs/application.%d{yyyy-MM-dd}.log</fileNamePattern>
            <maxHistory>7</maxHistory>
        </rollingPolicy>
        <encoder>
            <pattern>${LOG_PATTERN}</pattern>
        </encoder>
    </appender>

    <!-- 指定日志级别 -->
    <root level="info">
        <appender-ref ref="CONSOLE" />
        <appender-ref ref="FILE" />
    </root>

</configuration>

```

## 8.完整logback-srping.xml
```xml
<?xml version="1.0" encoding="UTF-8"?>
<!-- 日志级别从低到高分为TRACE < DEBUG < INFO < WARN < ERROR < FATAL，如果设置为WARN，则低于WARN的信息都不会输出 -->
<!-- scan:当此属性设置为true时，配置文档如果发生改变，将会被重新加载，默认值为true -->
<!-- scanPeriod:设置监测配置文档是否有修改的时间间隔，如果没有给出时间单位，默认单位是毫秒。
                         当scan为true时，此属性生效。默认的时间间隔为1分钟。 -->
<!-- debug:当此属性设置为true时，将打印出logback内部日志信息，实时查看logback运行状态。默认值为false。 -->
<configuration  scan="true" scanPeriod="10 seconds">
<contextName>logback</contextName>
 
<!-- name的值是变量的名称，value的值时变量定义的值。通过定义的值会被插入到logger上下文中。定义后，可以使“${}”来使用变量。 -->
<property name="log.path" value="log.path" />
 
<!--0. 日志格式和颜色渲染 -->
<!-- 彩色日志依赖的渲染类 -->
<conversionRule conversionWord="clr" converterClass="org.springframework.boot.logging.logback.ColorConverter" />
<conversionRule conversionWord="wex" converterClass="org.springframework.boot.logging.logback.WhitespaceThrowableProxyConverter" />
<conversionRule conversionWord="wEx" converterClass="org.springframework.boot.logging.logback.ExtendedWhitespaceThrowableProxyConverter" />
<!-- 彩色日志格式 -->
<property name="CONSOLE_LOG_PATTERN" value="${CONSOLE_LOG_PATTERN:-%clr(%d{yyyy-MM-dd HH:mm:ss.SSS}){faint} %clr(${LOG_LEVEL_PATTERN:-%5p}) %clr(${PID:- }){magenta} %clr(---){faint} %clr([%15.15t]){faint} %clr(%-40.40logger{39}){cyan} %clr(:){faint} %m%n${LOG_EXCEPTION_CONVERSION_WORD:-%wEx}}"/>
 
<!--1. 输出到控制台-->
<appender name="CONSOLE" class="ch.qos.logback.core.ConsoleAppender">
    <!--此日志appender是为开发使用，只配置最底级别，控制台输出的日志级别是大于或等于此级别的日志信息-->
    <filter class="ch.qos.logback.classic.filter.ThresholdFilter">
        <level>debug</level>
    </filter>
    <encoder>
        <Pattern>${CONSOLE_LOG_PATTERN}</Pattern>
        <!-- 设置字符集 -->
        <charset>UTF-8</charset>
    </encoder>
</appender>
 
<!--2. 输出到文档-->
<!-- 2.1 level为 DEBUG 日志，时间滚动输出  -->
<appender name="DEBUG_FILE" class="ch.qos.logback.core.rolling.RollingFileAppender">
    <!-- 正在记录的日志文档的路径及文档名 -->
    <file>${log.path}/web_debug.log</file>
    <!--日志文档输出格式-->
    <encoder>
        <pattern>%d{yyyy-MM-dd HH:mm:ss.SSS} [%thread] %-5level %logger{50} - %msg%n</pattern>
        <charset>UTF-8</charset> <!-- 设置字符集 -->
    </encoder>
    <!-- 日志记录器的滚动策略，按日期，按大小记录 -->
    <rollingPolicy class="ch.qos.logback.core.rolling.TimeBasedRollingPolicy">
        <!-- 日志归档 -->
        <fileNamePattern>${log.path}/web-debug-%d{yyyy-MM-dd}.%i.log</fileNamePattern>
        <timeBasedFileNamingAndTriggeringPolicy class="ch.qos.logback.core.rolling.SizeAndTimeBasedFNATP">
            <maxFileSize>100MB</maxFileSize>
        </timeBasedFileNamingAndTriggeringPolicy>
        <!--日志文档保留天数-->
        <maxHistory>15</maxHistory>
    </rollingPolicy>
    <!-- 此日志文档只记录debug级别的 -->
    <filter class="ch.qos.logback.classic.filter.LevelFilter">
        <level>debug</level>
        <onMatch>ACCEPT</onMatch>
        <onMismatch>DENY</onMismatch>
    </filter>
</appender>
 
<!-- 2.2 level为 INFO 日志，时间滚动输出  -->
<appender name="INFO_FILE" class="ch.qos.logback.core.rolling.RollingFileAppender">
    <!-- 正在记录的日志文档的路径及文档名 -->
    <file>${log.path}/web_info.log</file>
    <!--日志文档输出格式-->
    <encoder>
        <pattern>%d{yyyy-MM-dd HH:mm:ss.SSS} [%thread] %-5level %logger{50} - %msg%n</pattern>
        <charset>UTF-8</charset>
    </encoder>
    <!-- 日志记录器的滚动策略，按日期，按大小记录 -->
    <rollingPolicy class="ch.qos.logback.core.rolling.TimeBasedRollingPolicy">
        <!-- 每天日志归档路径以及格式 -->
        <fileNamePattern>${log.path}/web-info-%d{yyyy-MM-dd}.%i.log</fileNamePattern>
        <timeBasedFileNamingAndTriggeringPolicy class="ch.qos.logback.core.rolling.SizeAndTimeBasedFNATP">
            <maxFileSize>100MB</maxFileSize>
        </timeBasedFileNamingAndTriggeringPolicy>
        <!--日志文档保留天数-->
        <maxHistory>15</maxHistory>
    </rollingPolicy>
    <!-- 此日志文档只记录info级别的 -->
    <filter class="ch.qos.logback.classic.filter.LevelFilter">
        <level>info</level>
        <onMatch>ACCEPT</onMatch>
        <onMismatch>DENY</onMismatch>
    </filter>
</appender>
 
<!-- 2.3 level为 WARN 日志，时间滚动输出  -->
<appender name="WARN_FILE" class="ch.qos.logback.core.rolling.RollingFileAppender">
    <!-- 正在记录的日志文档的路径及文档名 -->
    <file>${log.path}/web_warn.log</file>
    <!--日志文档输出格式-->
    <encoder>
        <pattern>%d{yyyy-MM-dd HH:mm:ss.SSS} [%thread] %-5level %logger{50} - %msg%n</pattern>
        <charset>UTF-8</charset> <!-- 此处设置字符集 -->
    </encoder>
    <!-- 日志记录器的滚动策略，按日期，按大小记录 -->
    <rollingPolicy class="ch.qos.logback.core.rolling.TimeBasedRollingPolicy">
        <fileNamePattern>${log.path}/web-warn-%d{yyyy-MM-dd}.%i.log</fileNamePattern>
        <timeBasedFileNamingAndTriggeringPolicy class="ch.qos.logback.core.rolling.SizeAndTimeBasedFNATP">
            <maxFileSize>100MB</maxFileSize>
        </timeBasedFileNamingAndTriggeringPolicy>
        <!--日志文档保留天数-->
        <maxHistory>15</maxHistory>
    </rollingPolicy>
    <!-- 此日志文档只记录warn级别的 -->
    <filter class="ch.qos.logback.classic.filter.LevelFilter">
        <level>warn</level>
        <onMatch>ACCEPT</onMatch>
        <onMismatch>DENY</onMismatch>
    </filter>
</appender>
 
<!-- 2.4 level为 ERROR 日志，时间滚动输出  -->
<appender name="ERROR_FILE" class="ch.qos.logback.core.rolling.RollingFileAppender">
    <!-- 正在记录的日志文档的路径及文档名 -->
    <file>${log.path}/web_error.log</file>
    <!--日志文档输出格式-->
    <encoder>
        <pattern>%d{yyyy-MM-dd HH:mm:ss.SSS} [%thread] %-5level %logger{50} - %msg%n</pattern>
        <charset>UTF-8</charset> <!-- 此处设置字符集 -->
    </encoder>
    <!-- 日志记录器的滚动策略，按日期，按大小记录 -->
    <rollingPolicy class="ch.qos.logback.core.rolling.TimeBasedRollingPolicy">
        <fileNamePattern>${log.path}/web-error-%d{yyyy-MM-dd}.%i.log</fileNamePattern>
        <timeBasedFileNamingAndTriggeringPolicy class="ch.qos.logback.core.rolling.SizeAndTimeBasedFNATP">
            <maxFileSize>100MB</maxFileSize>
        </timeBasedFileNamingAndTriggeringPolicy>
        <!--日志文档保留天数-->
        <maxHistory>15</maxHistory>
    </rollingPolicy>
    <!-- 此日志文档只记录ERROR级别的 -->
    <filter class="ch.qos.logback.classic.filter.LevelFilter">
        <level>ERROR</level>
        <onMatch>ACCEPT</onMatch>
        <onMismatch>DENY</onMismatch>
    </filter>
</appender>
 
<!--
    <logger>用来设置某一个包或者具体的某一个类的日志打印级别、
    以及指定<appender>。<logger>仅有一个name属性，
    一个可选的level和一个可选的addtivity属性。
    name:用来指定受此logger约束的某一个包或者具体的某一个类。
    level:用来设置打印级别，大小写无关：TRACE, DEBUG, INFO, WARN, ERROR, ALL 和 OFF，
          还有一个特殊值INHERITED或者同义词NULL，代表强制执行上级的级别。
          如果未设置此属性，那么当前logger将会继承上级的级别。
    addtivity:是否向上级logger传递打印信息。默认是true。
    <logger name="org.springframework.web" level="info"/>
    <logger name="org.springframework.scheduling.annotation.ScheduledAnnotationBeanPostProcessor" level="INFO"/>
-->
 
<!--
    使用mybatis的时候，sql语句是debug下才会打印，而这里我们只配置了info，所以想要查看sql语句的话，有以下两种操作：
    第一种把<root level="info">改成<root level="DEBUG">这样就会打印sql，不过这样日志那边会出现很多其他消息
    第二种就是单独给dao下目录配置debug模式，代码如下，这样配置sql语句会打印，其他还是正常info级别：
    【logging.level.org.mybatis=debug logging.level.dao=debug】
 -->
 
<!--
    root节点是必选节点，用来指定最基础的日志输出级别，只有一个level属性
    level:用来设置打印级别，大小写无关：TRACE, DEBUG, INFO, WARN, ERROR, ALL 和 OFF，
    不能设置为INHERITED或者同义词NULL。默认是DEBUG
    可以包含零个或多个元素，标识这个appender将会添加到这个logger。
-->
 
<!-- 4. 最终的策略 -->
<!-- 4.1 开发环境:打印控制台-->
<springProfile name="dev">
    <logger name="com.sdcm.pmp" level="debug"/>
</springProfile>
 
<root level="info">
    <appender-ref ref="CONSOLE" />
    <appender-ref ref="DEBUG_FILE" />
    <appender-ref ref="INFO_FILE" />
    <appender-ref ref="WARN_FILE" />
    <appender-ref ref="ERROR_FILE" />
</root>
 
<!-- 4.2 生产环境:输出到文档
<springProfile name="pro">
    <root level="info">
        <appender-ref ref="CONSOLE" />
        <appender-ref ref="DEBUG_FILE" />
        <appender-ref ref="INFO_FILE" />
        <appender-ref ref="ERROR_FILE" />
        <appender-ref ref="WARN_FILE" />
    </root>
</springProfile> -->
 
</configuration>
```