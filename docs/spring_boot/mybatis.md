<!-- TOC -->
* [MyBatis 介绍](#mybatis-介绍)
  * [1. application.yml 配置](#1-applicationyml-配置)
  * [2. 映射文件](#2-映射文件)
  * [3. java对象](#3-java对象)
  * [4. Dao 定义SQL查询接口](#4-dao-定义sql查询接口)
  * [5.封装查询](#5封装查询)
<!-- TOC -->

# MyBatis 介绍
MyBatis（以前称为iBatis）是一个轻量级的、基于 Java 的持久层框架，由Apache软件基金会维护。它的目标是通过提供简单的方法来处理数据库操作，同时允许开发人员使用原生 SQL 查询。与其他 ORM 框架不同，MyBatis 不会强制开发者将对象与数据库表进行一对一的映射，而是提供了灵活的映射机制，使开发者能够更自由地设计数据库访问层。

## 1. application.yml 配置

配置文件： 首先，需要配置 MyBatis 的主配置文件。该文件包含了一些全局配置，如数据库连接信息、映射文件的位置等。

```yaml
server:
  tomcat:
    uri-encoding: UTF-8
  port: 8080
  servlet:
    context-path: /api
spring:
  datasource:
    driver-class-name: com.mysql.cj.jdbc.Driver
    url: jdbc:mysql://127.0.0.1:3306/test?serverTimezone=GMT%2B8&characterEncoding=utf-8&autoReconnect=true&allowMultiQueries=true
    username: test
    password: test
    hikari:
      connection-timeout: 10000
      validation-timeout: 3000
      idle-timeout: 50000
      login-timeout: 5
      max-lifetime: 60000
      maximum-pool-size: 10
      minimum-idle: 5
      read-only: false
    task:
      execution:
        pool:
          core-size: 2        # 异步任务执行线程池的核心线程数
          max-size: 10        # 异步任务执行线程池的最大线程数
          queue-capacity: 30  # 异步任务执行线程池的队列容量
          thread-name-prefix: async-task-  # 异步任务执行线程池的线程前缀


mybatis-plus:
  type-aliases-package: com.laughing.microservice.**.domain
  mapperLocations: classpath*:mapper/**/*Mapper.xml
```

## 2. 映射文件

创建映射文件，定义 SQL 查询和操作以及结果集与 Java 对象之间的映射关系。

```xml
<!-- MyMapper.xml -->
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="com.laughing.microservice.dataprovider.dao.OrdersDao">

    <resultMap id="OrdersMap" type="com.laughing.microservice.dataprovider.entity.Orders">
        <result property="OrderID" column="OrderID"/>
        <result property="CustomerID" column="CustomerID"/>
        <result property="ProductID" column="ProductID"/>
        <result property="Quantity" column="Quantity"/>
        <result property="Price" column="Price"/>
        <result property="OrderDate" column="OrderDate"/>
        <result property="OrderName" column="OrderName"/>
    </resultMap>

    <select id="selectById" parameterType="Integer" resultMap="OrdersMap">
        select
        OrderID,
        CustomerID,
        ProductID,
        Quantity,
        Price,
        OrderDate,
        OrderName
        from Orders
        where CustomerID= #{CustomerID}
    </select>

</mapper>

```

## 3. java对象

```java
package com.laughing.microservice.dataprovider.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @description: 订单表
 * @author: TJ
 * @date: 2023/7/11 17:25
 */
@Data
@TableName(value = "Orders")
public class Orders {

    // 证件号
    @TableId(type = IdType.AUTO)
    Integer OrderID;

    Integer CustomerID;
    Integer ProductID;
    Integer Quantity;
    Double Price;

    // 数据更新时间
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    Date OrderDate;

    String OrderName;

}

```

## 4. Dao 定义SQL查询接口

```java
package com.laughing.microservice.dataprovider.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.laughing.microservice.dataprovider.entity.Orders;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @description: CustomerID
 * @author: XXM
 * @date: 2023/7/12 15:29
 */
@Repository
public interface OrdersDao extends BaseMapper<Orders> {

    List<Orders> selectById(Integer CustomerID);

//    List<VerifyTripartiteAgreement> selectAll();
}

```

## 5.封装查询

```java

public JSONObject getQueryResult(Object object){

        OrdersIn OrdersIn=(OrdersIn)object;

        Integer customerID=Integer.valueOf(OrdersIn.getCustomerId());

        List<Orders> orders=OrdersDao.selectById(customerID);

        log.info("query result is {}",orders);
        JSONObject jsonObject=new JSONObject();
        jsonObject.put("result",Arrays.toString(orders.toArray()));

        return jsonObject;
        }
```