<!-- TOC -->
* [Hive数据库操作](#hive数据库操作)
  * [一、库操作](#一库操作)
    * [创建库](#创建库)
    * [使用库](#使用库)
    * [显示所有库](#显示所有库)
    * [删除库](#删除库)
  * [二、数据导入导出](#二数据导入导出)
    * [导入数据](#导入数据)
    * [导出数据](#导出数据)
  * [三、表的操作](#三表的操作)
    * [创建表](#创建表)
    * [插入数据](#插入数据)
    * [查询数据](#查询数据)
    * [更新数据](#更新数据)
    * [删除数据](#删除数据)
    * [删除表](#删除表)
  * [四、示例代码](#四示例代码)
    * [1. 简单示例](#1-简单示例)
    * [2.分区建表](#2分区建表)
<!-- TOC -->

Hive数据库操作
=========

一、库操作
---

### 创建库

创建一个名为`my_database`的数据库：

```sql
CREATE DATABASE IF NOT EXISTS my_database;
```

### 使用库

使用`my_database`数据库：

```sql
USE my_database;
```

### 显示所有库

显示所有数据库：

```sql
SHOW DATABASES;
```

### 删除库

删除`my_database`数据库：

```sql
DROP DATABASE IF EXISTS my_database;
```

二、数据导入导出
------

### 导入数据

从本地文件系统导入数据到Hive表中：

```sql
LOAD DATA LOCAL INPATH '/path/to/local/file' OVERWRITE INTO TABLE my_table;
```

从hdfs文件系统导入数据到Hive表中：

```sql
LOAD DATA INPATH '/path/to/local/file' OVERWRITE INTO TABLE my_table;
```

在Hive中使用LOAD DATA INPATH语句加载HDFS上的数据时，默认情况下不会复制数据。Hive仅会在加载数据时创建对数据的元数据引用。

Hive表的数据存储在HDFS上，而Hive的元数据存储在关系型数据库（如MySQL）中。当使用LOAD DATA INPATH语句加载数据时，Hive会创建一个指向HDFS数据位置的元数据引用，而不会将数据复制到Hive的数据目录中。

这种方式的优点是节省了存储空间，因为数据仍然保留在HDFS上，没有在Hive服务器上复制一份。同时，如果在HDFS上的数据发生变化，Hive表的数据也会自动反映这些变化，而无需重新加载数据。

但需要注意的是，当从HDFS中删除数据时，元数据引用仍然存在，因此Hive表中的数据仍然可见，但无法访问实际数据。如果需要完全删除数据，可以使用Hive的DROP TABLE语句将表及其元数据一起删除。

### 导出数据

将Hive表中的数据导出到本地文件系统：

```sql
INSERT OVERWRITE LOCAL DIRECTORY '/path/to/output/dir' SELECT * FROM my_table;
```

三、表的操作
----

### 创建表

创建一个名为`my_table`的表，包含id、name和age三个列：

```sql
CREATE TABLE IF NOT EXISTS my_table (
  id INT,
  name STRING,
  age INT
);
```

### 插入数据

向`my_table`表插入数据：

```sql
INSERT INTO my_table VALUES
  (1, 'John', 25),
  (2, 'Alice', 30),
  (3, 'Bob', 28);
```

### 查询数据

查询`my_table`表中的所有数据：

```sql
SELECT * FROM my_table;
```

### 更新数据

更新`my_table`表中id为1的记录的name字段：

```sql
UPDATE my_table SET name = 'Jane' WHERE id = 1;
```

### 删除数据

删除`my_table`表中id为2的记录：

```sql
DELETE FROM my_table WHERE id = 2;
```

### 删除表

删除`my_table`表：

```sql
DROP TABLE IF EXISTS my_table;
```

四、示例代码
----

下面是一个完整的示例代码，包括库操作、数据导入导出和表的操作：

### 1.简单示例
```bash
# 创建库
CREATE DATABASE IF NOT EXISTS my_database;

# 使用库
USE my_database;

# 创建表
CREATE TABLE IF NOT EXISTS my_table (
  id INT,
  name STRING,
  age INT
);

# 导入数据
LOAD DATA LOCAL INPATH '/path/to/local/file' OVERWRITE INTO TABLE my_table;

# 查询数据
SELECT * FROM my_table;

# 导出数据
INSERT OVERWRITE LOCAL DIRECTORY '/path/to/output/dir' SELECT * FROM my_table;

# 关闭Hive连接
quit;
```

以上是对Hive数据库的库操作、数据导入导出和表的操作的示例代码。根据实际需求，可以进行更多操作，如分区表、查询条件等。

注意：在示例代码中，需要根据你的环境配置修改数据库名、表名、文件路径等参数。


### 2.分区建表
```text
PARTITION (sale_date, region)  分区的逻辑和原理是什么

ChatGPT
在Hive中，分区是将表的数据按照指定的列进行逻辑上的划分和存储。分区可以帮助提高查询性能和数据管理的灵活性。

分区的逻辑是基于表中的某个或多个列的值进行划分。在创建表时，通过使用PARTITIONED BY子句指定分区键（也可以有多个分区键）。在插入数据时，需要指定每条数据所属的分区键值，将数据存储到相应的分区目录中。

例如，在你提供的示例中，表"sale_detail"使用了两个分区键：sale_date和region。这意味着数据将按照这两个列的值进行划分。

假设你执行了以下语句插入一条数据：

sql
INSERT INTO sale_detail PARTITION (sale_date='2023-06-01', region='North')
VALUES ('Shop A', 'C001', 100.0);
这条数据将被存储在sale_detail表的sale_date='2023-06-01'和region='North'的分区目录下。这样，你可以通过查询特定的分区来快速检索分区内的数据，而无需扫描整个表。

分区的原理是在Hive中基于分区键值来组织数据存储。Hive会在HDFS中为每个分区创建一个目录，并将相应的数据文件存储在该目录下。当查询时，Hive可以根据查询条件中指定的分区键值，仅扫描相关分区的数据文件，提高查询效率。

此外，分区还可以帮助你进行更灵活的数据管理。你可以根据业务需求添加、删除、修改分区，实现数据的动态调整和控制。

总而言之，分区是Hive中一种重要的数据组织和管理方式，通过逻辑上的数据划分和存储，提高查询性能和数据管理的灵活性。
```

以下是生成10个分区的示例数据的代码, 按照分区sale_date，region 对数据表进行分区操作，对不同的数据sale_date_region存入不同的分区文件中

sql
```roomsql
-- 创建表
CREATE TABLE IF NOT EXISTS sale_detail (
shop_name     STRING,
customer_id   STRING,
total_price   DOUBLE
)
PARTITIONED BY (sale_date STRING, region STRING);

-- 插入示例数据
INSERT INTO sale_detail PARTITION (sale_date, region)
VALUES
('Shop A', 'C001', 100.0, '2023-06-01', 'North'),
('Shop B', 'C002', 150.0, '2023-06-02', 'South'),
('Shop C', 'C003', 200.0, '2023-06-03', 'East'),
('Shop D', 'C004', 120.0, '2023-06-04', 'West'),
('Shop E', 'C005', 180.0, '2023-06-05', 'North'),
('Shop F', 'C006', 90.0, '2023-06-06', 'South'),
('Shop G', 'C007', 300.0, '2023-06-07', 'East'),
('Shop H', 'C008', 250.0, '2023-06-08', 'West'),
('Shop I', 'C009', 140.0, '2023-06-09', 'North'),
('Shop J', 'C010', 170.0, '2023-06-10', 'South');
```
以上示例代码创建了一个名为"sale_detail"的表，并指定了分区键为"sale_date"和"region"。然后使用INSERT INTO语句插入了10条示例数据，每条数据都指定了相应的分区键值。

partition 动态分配
set hive.exec.dynamic.partition=true;
set hive.exec.dynamic.partition.mode=nonstrict;

-- 设置并行度为4
SET hive.exec.parallel=true;
SET hive.exec.parallel.thread.number=4;

-- 执行查询语句
SELECT * FROM my_table;

