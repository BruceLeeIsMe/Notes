





# 技术栈踩坑



## springcloud：

环境问题：

​	jar版本不要用太新的，容易遇到问题，且容易炸心态

微服务调用：被调用的api 必须是RestController注解才能被调用 （使用Controller的不行）



## spring security：

划重点： 框架使用的组件 一定要被spring管理！！！！！

以后尽量养成习惯，除了POJO，其余百分之90的都需要被spring管理，记得加上 @controller @service @repository @component等注解！！！



## SpringMvc：

使用springboot后的资源访问问题， 多了static template

以及静态资源放行问题



## Aop日志记录

# 计算机网络问题

家庭网络 可以访问 公网ip

但是 公网是  不可以访问 家庭网络ip的，需要使用内网穿透才可以。

我的傻逼操作：把注册中心放云服务器上，然后以后调试就不用再本地启动 注册中心了 23333



# linux服务器搭建问题：



