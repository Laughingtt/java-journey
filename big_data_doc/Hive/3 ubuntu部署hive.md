<!-- TOC -->
* [前言](#前言)
* [一、安装Hive](#一安装hive)
    * [1. 下载压缩包并解压](#1-下载压缩包并解压)
    * [2. 安装Hive](#2-安装hive)
    * [3. 配置环境变量](#3-配置环境变量)
    * [4. 修改配置文件](#4-修改配置文件)
* [二、MySQL安装与配置](#二mysql安装与配置)
    * [1. 安装MySQL](#1-安装mysql)
    * [2. 安装MySQL jdbc包](#2-安装mysql-jdbc包)
    * [3. 为Hive创建MySQL账号](#3-为hive创建mysql账号)
* [三、验证Hive安装及错误处理](#三验证hive安装及错误处理)
    * [1. 启动Hadoop](#1-启动hadoop)
    * [2. 启动hive](#2-启动hive)
    * [3. 运行Hive实例](#3-运行hive实例)
<!-- TOC -->



前言
===
Hive 的安装需要建立在 Hadoop 的之上（类似于Hbase），关于 Hadoop 的单机安装以及伪分布式安装可以参考作者的另两篇博客：

Ubuntu下单机安装Hadoop详细教程（附所需安装包下载）
Ubuntu下伪分布式安装Hadoop详细教程
本文安装的 Hadoop 及 Java 环境基于林子雨老师的《大数据技术原理与应用（第3版）》中所要求，其中 Java 版本为1.8.0_301，Hadoop 版本为3.2.2，这里我的操作系统环境是 Ubuntu 20.04，此安装方法同样适用于低版本。

# 一、安装Hive
### 1. 下载压缩包并解压
   官网下载目录如下：https://dlcdn.apache.org/hive/

建议清华镜像网站下载：https://mirrors.tuna.tsinghua.edu.cn/apache/hive/

下载文件如下：apache-hive-3.1.2-bin.tar.gz，进入下载目录，解压压缩包：

```shell
cd ~/Downloads
sudo tar -zxvf ./apache-hive-3.1.2-bin.tar.gz -C /usr/local
```
### 2. 安装Hive
进入到 /usr/local 目录下，更改文件夹名，赋予用户权限：

```shell
cd /usr/local
sudo mv apache-hive-3.1.2-bin hive     
sudo chown -R hadoop ./hive
```

### 3. 配置环境变量
修改 ~/.bashrc 文件：

```shell
vim ~/.bashrc
```
增添下面几行：

```shell
export HIVE_HOME=/usr/local/hive
export PATH=$PATH:$HIVE_HOME/bin
export HADOOP_HOME=/usr/local/hadoop
```

运行如下命令使配置立即生效：

```shell
source ~/.bashrc
```

### 4. 修改配置文件
修改配置 hive-default.xml 文件：

```shell
cd /usr/local/hive/conf
sudo mv hive-default.xml.template hive-default.xml
```

新建 hive-site.xml 文件：

```shell
sudo vim hive-site.xml
```

将以下内容写入文件：
```xml
<?xml version="1.0" encoding="UTF-8" standalone="no"?>
<?xml-stylesheet type="text/xsl" href="configuration.xsl"?>
<configuration>
  <property>
    <name>javax.jdo.option.ConnectionURL</name>
    <value>jdbc:mysql://localhost:3306/hive?createDatabaseIfNotExist=true&amp;useSSL=false&amp;allowPublicKeyRetrieval=true</value>
    <description>JDBC connect string for a JDBC metastore</description>
  </property>
  <property>
    <name>javax.jdo.option.ConnectionDriverName</name>
    <value>com.mysql.cj.jdbc.Driver</value>
    <description>Driver class name for a JDBC metastore</description>
  </property>
  <property>
    <name>javax.jdo.option.ConnectionUserName</name>
    <value>hive</value>
    <description>username to use against metastore database</description>
  </property>
  <property>
    <name>javax.jdo.option.ConnectionPassword</name>
    <value>hive</value>
    <description>password to use against metastore database</description>
  </property>
  <property>
    <name>datanucleus.autoCreateTables</name>
    <value>True</value>
  </property>
</configuration>
```

按下 ESC 键，:wq，保存并退出。

# 二、MySQL安装与配置

```text
hive 数据库启动时为什么需要连接mysql  jdbc


Hive数据库在启动过程中需要连接到MySQL数据库，主要是为了以下几个原因：

元数据存储：Hive使用MySQL（或其他关系型数据库）来存储元数据，包括表结构、分区信息、表的属性等。通过连接到MySQL数据库，Hive可以读取和写入元数据，从而实现对数据仓库的管理和查询优化。

共享元数据：Hive通常被用作大规模数据仓库的管理工具，多个用户和应用程序可能同时使用Hive进行查询和操作数据。通过连接到共享的MySQL数据库，Hive可以保证多个用户和应用程序之间共享和同步元数据，避免冲突和数据一致性问题。

配置和权限管理：Hive的配置信息和权限管理信息通常也存储在MySQL数据库中。通过连接到MySQL数据库，Hive可以获取配置信息并进行相应的初始化，同时也可以根据数据库中的权限信息对用户的查询和操作进行验证和控制。

总而言之，连接到MySQL数据库是Hive启动和正常运行所必需的，它提供了元数据存储、共享元数据和配置权限管理等功能，使得Hive能够有效管理数据仓库并提供查询和优化功能。

```

### 1. 安装MySQL
   参考我的这篇博客安装最新版本的 MySQL：https://blog.csdn.net/weixin_46584887/article/details/121432061?spm=1001.2014.3001.5501

### 2. 安装MySQL jdbc包
   上述博客安装的 MySQL 版本为 8.0.27，所以我们需要下载对应版本的 MySQL jdbc 包，下载链接如下：mysql-connector-java-8.0.27.tar.xz(https://dev.mysql.com/downloads/connector/j/)

下载好后，提取 mysql-connector-java-8.0.27.jar 包到指定路径：

```shell
cd ~/Downloads
tar -xf mysql-connector-java-8.0.27.tar.xz   #解压
cp mysql-connector-java-8.0.27/mysql-connector-java-8.0.27.jar  /usr/local/hive/lib
```

### 3. 为Hive创建MySQL账号
启动 MySQL 服务，登录 shell：

```shell
service mysql start #启动mysql服务

sudo mysql -u root -p  #登陆shell界面
```

新建 hive 数据库：

```shell
CREATE DATABASE hive;
```

创建用户 hive，设置密码（这里根据配置文件设置为 hive），使其能连接上 hive 数据库：

```shell
create user 'hive'@'%' identified by  'hive';
```

```shell
grant all privileges on hive.* to 'hive'@'%' with grant option;
```

```shell
flush privileges;
```

这里若提示密码不符合，则先执行以下命令，再执行上述命令：

```shell
set global validate_password.policy=LOW;

set global validate_password.length=4;
```

退出 MySQL：

`quit`;

# 三、验证Hive安装及错误处理
### 1. 启动Hadoop
```shell
cd /usr/local/hadoop
sbin/start-dfs.sh

```
   
### 2. 启动hive
   ```
   cd /usr/local/hive
   
    ./bin/schematool -dbType mysql -initSchema
   ```

```shell
bin/hive
```

正常启动会出现一个交互界面如下：

```
hive>
```

继续运行此步：

```shell
./bin/schematool -dbType mysql -initSchema
```

### 3. 运行Hive实例
在 hive 交互界面下，运行以下命令：

```shell
create database if not exists hive; #创建数据库
show databases; #查看Hive中包含数据库
show databases like 'h.*'; #查看Hive中以h开头数据库
```

输出如下，运行成功！

```shell
hive> create database if not exists hive; #创建数据库
OK
Time taken: 0.59 seconds

hive> show databases; #查看Hive中包含数据库
OK
datazq
default
hive
Time taken: 0.148 seconds, Fetched: 3 row(s)

hive> show databases like 'h.*'; #查看Hive中以h开头数据库
OK
hive
Time taken: 0.04 seconds, Fetched: 1 row(s)

hive>
```

总结
采用Hive实现最大的优势是，对于非程序员，不用学习编写Java MapReduce代码了，只需要用户学习使用HiveQL就可以了，而这对于有SQL基础的用户而言是非常容易的。

# 四、部署遇到的问题

### 1. 启动hive找不到 hadoop_home

启用hive时报以下错误：

`Cannot find hadoop installation: $HADOOP_HOME or $HADOOP_PREFIX must be set or hadoop must be in the path
`
解决方法：

```shell
[admin1@hadoop1 ~]$ cd ~/hive/conf/
[admin1@hadoop1 ~]$ cp hive-env.sh.template hive-env.sh
```

在hive-env.sh文件里加下面的内容：

```shell
vi hive-env.sh
```
                                                                                                        
```shell
# Set HADOOP_HOME to point to a specific hadoop install directory
# HADOOP_HOME=${bin}/../../hadoop
HADOOP_HOME=/home/tianjian/projects/hadoop/hadoop
# Hive Configuration Directory can be controlled by:
export HIVE_CONF_DIR=/home/tianjian/projects/hive/hive/conf
```

### 2. 启动hive guava包引用存错

Hive启动报错java.lang.NoSuchMethodError: com.google.common.base.Preconditions.checkArgument

错误分析
其实这类错误一般两种思考思路：

1. 系统找不到相关jar包
2. 同一类型的 jar 包有不同版本存在，系统无法决定使用哪一个

该包在hadoop中存储的位置是hadoop\share\hadoop\common\lib
```shell
ls | grep "guava"
```

该包在hive中存储的位置是hive/lib
```shell
ls | grep "guava"
```

删除 hive/lib 下的guava的包，复制hadoop 下的包到hive/lib下