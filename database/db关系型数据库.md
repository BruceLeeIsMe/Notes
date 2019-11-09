# -----语法--------

# IN与Exist区别

## 语意区别

**IN：**select * from A where age=11 and name IN (select name from B)

​	in()返回子查询的所有记录（并缓存结果）

**Exsist：**select * from A where age=11 and  exist(select 1 from B A.name=B.name)

​	exist()该表达式返回一个布尔值（不缓存结果），有结果返回true，无结果false

boolean flag= exist(select 1 from B  A.name=B.name);

可等价的理解为 :select * from A where age=11 and flag;



## 执行顺序

- IN先执行子查询，将查询结果作为主查询的条件（缓存结果），再进行主查询
- Exsist先执行主查询，在条件判断时进行子查询，子查询每次查询的结果可能都不一样（不缓存结果）。



# ----查询优化------

# 底层数据结构

# 索引

# ----高并发，高可用----

## 分表

### 垂直拆分

### 水平拆分



## 分库分表



# 问题

## 数据库资源未关闭

- Connection未关闭会导致长时间占用连接，从而致使其他线程因无法获取连接而报错
- ResultSet未关闭，会导致数据库游标资源长时间占用，有资源消耗殆尽致使数据库挂掉的风险
- statement/prepareStatement 未关闭，后果如 resultSet





