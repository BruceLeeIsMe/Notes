# 网页生成springboot项目

url： io.start.spring



# 打包方式：jar or war



spring boot既可以打成war发布，也可以找成jar包发布。说一下区别

jar包：直接通过内置tomcat运行，不需要额外安装tomcat。如需修改内置tomcat的配置，只需要在spring boot的配置文件中配置。内置tomcat没有自己的日志输出，全靠jar包应用输出日志。但是比较方便，快速，比较简单。

war包：传统的应用交付方式，需要安装tomcat，然后放到waeapps目录下运行war包，可以灵活选择tomcat版本，可以直接修改tomcat的配置，有自己的tomcat日志输出，可以灵活配置安全策略。相对打成jar包来说没那么快速方便。



# 自动配置

## 运用

## 原理

Springboot自动配置：
查询资料后再完善。



# 配置

## 项目url

**配置了server.servlet.context-path**

```
server.servlet.context-path=/demo2

@RequestMapping("/index")

访问url为：  host:port/demo2/index
```

**没有配置server.servlet.context-path**

```
@RequestMapping("/index")

访问url为：  host:port/index
```

## 启动端口配置

```
server.port=8085
```

## 数据库连接池 配置key



## 注解：

spring组件扫描回顾：
ComponntScan注解
@SpringBootApplicattion中继承了ComponnetScan注解，所以默认springboot项目会扫描 springapplication启动所在包下一级的包。

如果需要自定义扫描包，只需要在SpringApplication类上加上注解@ComponentScan(basePackages={“packagePath1”,”packagePath2”})

注意：如果pom导入的公用模块中含有相同的包结构，那么符合组件扫描条件的bean也会被spring装配





# issue

## 注意：不同版本的springboot 配置的key值可能有变化

