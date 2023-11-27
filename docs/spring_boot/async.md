<!-- TOC -->
* [Async异步](#async异步)
  * [1.application.yml 配置](#1applicationyml-配置)
  * [2. 启动类打开EnableAsync](#2-启动类打开enableasync)
  * [3. service 定义异步方法](#3-service-定义异步方法)
  * [4. 调用异步方法](#4-调用异步方法)
<!-- TOC -->

# Async异步
"Async" 是 "Asynchronous" 的缩写，指的是异步的编程模式。异步编程是一种用于处理可能会花费较长时间的操作的方式，而不会阻塞程序的执行。在传统的同步编程中，一个任务需要等待另一个任务完成后才能继续执行，而异步编程允许程序在等待的同时继续执行其他任务。

在计算机编程中，异步通常涉及到异步任务（asynchronous tasks）或异步操作（asynchronous operations）。这些任务或操作在执行时不会阻塞主程序的执行，而是在后台或另一个线程中执行。当异步任务完成时，通常通过回调函数、事件或者其他方式来通知主程序。

异步编程的优势在于能够提高程序的性能和响应性。特别是在处理网络请求、文件读写、数据库查询等可能耗时的操作时，使用异步编程可以允许程序在等待这些操作完成的同时继续执行其他任务，而不会因为等待而造成程序的阻塞。

在Java中，使用`CompletableFuture`、`Future`、`ExecutorService`等类来实现异步编程。在Spring框架中，通过`@Async`注解和`AsyncConfigurer`接口支持异步方法的定义和管理。异步编程也在JavaScript、Python、C#等多种编程语言中有着不同的实现方式，但核心思想是相似的：在需要等待的操作上使用异步机制，以提高程序的并发性和响应性。


## 1.application.yml 配置

```yaml
spring:
    task:
      execution:
        pool:
          core-size: 2        # 异步任务执行线程池的核心线程数
          max-size: 10        # 异步任务执行线程池的最大线程数
          queue-capacity: 30  # 异步任务执行线程池的队列容量
          thread-name-prefix: async-task-  # 异步任务执行线程池的线程前缀
```


## 2. 启动类打开EnableAsync
```java
package com.laughing.microservice.dataprovider;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * @author
 * @date 2023/10/23
 */
@Slf4j
@ComponentScan("com.laughing.microservice")
@MapperScan("com.laughing.microservice.**.dao")
@SpringBootApplication
@EnableAsync
public class Application {

    public static void main(String[] args) {
        try {
            SpringApplication.run(DataproviderApplication.class, args);
        } catch (Exception e) {
            log.error("Server启动失败,异常信息:{}", e.getMessage(), e);
        }
    }
}

```

## 3. service 定义异步方法

```java
package com.laughing.microservice.dataprovider.service;

import com.laughing.microservice.dataprovider.dao.ReqLogInfoDao;
import com.laughing.microservice.dataprovider.entity.ReqLogInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
public class ReqLogInfoService {

    @Autowired
    private ReqLogInfoDao reqLogInfoDao;

    @Async
    public CompletableFuture<Void> insertInfoAsync(ReqLogInfo reqLogInfo) {

        CompletableFuture<Void> future = new CompletableFuture<>();
        try {
            reqLogInfoDao.insert(reqLogInfo);
            future.complete(null); // 表示异步操作成功完成
        } catch (Exception e) {
            future.completeExceptionally(e); // 表示异步操作发生异常
        }
        return future;
    }
}

```

## 4. 调用异步方法
```java
  // 异步插入
        ReqLogInfoService.insertInfoAsync(reqLogInfo).handle((result, exception) -> {
            if (exception != null) {
                // 处理异常
                log.info(exception.getMessage());
            } else {
                // 异步方法执行成功
                log.info("Async Insert request completed successfully.");
            }
            return null;
        });
```