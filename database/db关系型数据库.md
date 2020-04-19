# 基本概念&语法
select：投影表的某一列或几列
from：引用单或多表，也可以是自查询生成的零时表
where： 过滤引用的表记录
    in，exsit ，not in ，not exsit ，= , <>,  >,<  ,  between and , like , and , or
group by：对引用的表以某一列进行分组
having：过滤分组的记录
order by：排序 asc ，desc
连接：以某条件对多表进行连接（做笛卡尔集再筛选）， table1 t1 left join table2 t2 on t1.tid=t2.tid ··
内连接（inner join，缩写 join）:
外连接（outer join）:
    左外连接（left outer join，缩写：left join）：保留左边的表所有记录，右边没有的符合连接条件的以null显示
    右外连接：反之亦然
交叉连接（做笛卡尔集）：from table1, table2 就是在做交叉连接
聚合函数：只可以在select和having后使用
as： 给列或者表/零时表起别名，部分情况起别名也可不用as，直接跟在后面即可。

# 执行顺序
- FROM
- WHERE
- GROUP BY
- HAVING
- SELECT
- DISTINCT
- UNION
- ORDER BY







# ----查询优化------

# 底层数据结构
树的基础知识
二叉搜索树（二叉排序树）
2-3树
b树
b加数
红黑树？

# 索引
## 聚簇索引

数据按照聚簇索引的顺序存储，这样数据查找速度较快，但更新速度慢。

一张表只能有一个聚簇索引，因为存储方式只存在一种。

## 非聚簇索引

数据不按照索引顺序存储，而采用在索引中维护指向数据指针的方式，查找慢，更新快



# 数据库铁律（规范）

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

## IN与Exist区别

### 语意区别

**IN：**select * from A where age=11 and name IN (select name from B)

​	in()返回子查询的所有记录（并缓存结果）

**Exsist：**select * from A where age=11 and  exist(select 1 from B A.name=B.name)

​	exist()该表达式返回一个布尔值（不缓存结果），有结果返回true，无结果false

boolean flag= exist(select 1 from B  A.name=B.name);

可等价的理解为 :select * from A where age=11 and flag;



### 执行顺序

- IN先执行子查询，将查询结果作为主查询的条件（缓存结果），再进行主查询
- Exsist先执行主查询，在条件判断时进行子查询，子查询每次查询的结果可能都不一样（不缓存结果）。





