# 日志框架：

门面 对应 日志接口

具体框架  对应 具体实现

通常依赖上层接口

## 日志门面

**日志上层接口，与实现解耦，便于项目日志框架变动**



### commons-logging

### 为啥会与osgi这种分模块多个类加载器的框架冲突？

apache提供的通用的日志接口commons-logging，通过动态查找具体实现的日志库，但是缺点是：使用动态查找使用classloader会与像osgi这类框架产生冲突，因为osgi的分模块使用的不同的类加载器，然后？导致commons-logging无法使用

动态查找范围：从log4j，jdk-log，commons-logging的自身简单实现

### slf4j（simple log facade for java）



同为上层接口，不过该门面是在编译的时候，用静态绑定来确定实现框架。



**这就是为啥：使用slf4j经常出现 StaticLoggerBinder之类的异常，因为maven一旦以来传递导致有多个适配器（也就是多个binder），这个时候编译器懵逼了，不晓得跟哪个绑定。**



使用slf4j门面时，必须要有一个一个适配实现 org.slf4j.impl.StaticLoggerBinder，只有实现了该类才能与门面正确绑定。

所以在此类情况下，除了接口和实现类，还需要一个适配器才能正常工作。



```
接口 ： 	slf4j-api		slf4j-api		slf4j-api

适配器：	slf4j-log4j		slf4j-jdk		none

具体实现	log4j			jdk-util.log	logback-classic&logback-core
```

**log-back日志框架**是特殊的存在，因为他直接实现了slf4j-api中的所有接口，所以无需适配器可直接使用。





## 日志框架具体实现

### log4j

只需要知道 效率没有logback好，逐渐会被logback取代。



### logback

效率吊打log4j，逐渐称霸日志界。

两个具体实现 出自apache的同一个作者。

### log-jdk（java.util.log）

少有人用，此处不做介绍，反正是实现之一嘛



### 使用

与门面搭配使用的时候，代码中均使用门面的接口，此处拿slf4j举例，因为毕竟是主流。

slf4j.Logger logger= slf4j.getLoggerFactory(String/clazz);

拿到日志对象就可以愉快的玩耍啦。

### 配置

log4j使用properties文件

logback采用json和xml文件



