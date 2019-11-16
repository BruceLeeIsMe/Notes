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

# 配置

log4j使用properties文件

logback采用json和xml文件



配置的对象主要有二：

## loggers：

- 配置日志级别 ： **trace、debug、info、warn、error** 
- 需要指定appender才能真的打印日志

### root Logger：默认的全局logger

    <root level="info">
        <!--定义了两个appender，日志会通过往这两个appender里面写-->
        <appender-ref ref="stdout"/>
        <appender-ref ref="file"/>
    </root>
### logger：自定义logger

继承自root logger，用于为某个包或者具体类的设置日志级别。

只有三个属性： 

- name：指定包或类 
- level :  **trace、debug、info、warn、error** 
- additivity：是否继承root logger的appender，默认为true

    <!--对于类路径以 com.example.logback 开头的Logger,输出级别设置为warn,并且只输出到控制台-->
    <!--这个logger没有指定appender，它会继承root节点中定义的那些appender-->
    <logger name="com.example.logback" level="warn"/>
    
    <!--通过 LoggerFactory.getLogger("mytest") 可以获取到这个logger-->
    <!--由于这个logger自动继承了root的appender，root中已经有stdout的appender了，自己这边又引入了stdout的appender-->
    <!--如果没有设置 additivity="false" ,就会导致一条日志在控制台输出两次的情况-->
    <!--additivity表示要不要使用rootLogger配置的appender进行输出-->
    <logger name="mytest" level="info" additivity="false">
        <appender-ref ref="stdout"/>
    </logger>
    
    <!--由于设置了 additivity="false" ，所以输出时不会使用rootLogger的appender-->
    <!--但是这个logger本身又没有配置appender，所以使用这个logger输出日志的话就不会输出到任何地方-->
    <logger name="mytest2" level="info" additivity="false"/>




## appender：

三种类型，此处只对前两种进行介绍

### 输出到控制台：

```
<appender name="stdout" class="ch.qos.logback.core.ConsoleAppender">
        <!--定义了一个过滤器,在LEVEL之下的日志输出不会被打印出来-->
        <!--这里定义了ERROR，也就是控制台不会输出比ERROR级别小的日志-->
        <filter class="ch.qos.logback.classic.filter.ThresholdFilter">
            <level>ERROR</level>
        </filter>
        <!-- encoder 默认配置为PatternLayoutEncoder -->
        <!--定义控制台输出格式-->
        <encoder>
            <pattern>%d [%thread] %-5level %logger{36} [%file : %line] - %msg%n</pattern>
        </encoder>
</appender>
```

### 输出到文件

```
<appender name="file" class="ch.qos.logback.core.rolling.RollingFileAppender">
        <!--定义日志输出的路径-->
        <!--这里的scheduler.manager.server.home 没有在上面的配置中设定，所以会使用java启动时配置的值-->
        <!--比如通过 java -Dscheduler.manager.server.home=/path/to XXXX 配置该属性-->
        <file>${scheduler.manager.server.home}/logs/${app.name}.log</file>
        <!--定义日志滚动的策略-->
        <rollingPolicy class="ch.qos.logback.core.rolling.TimeBasedRollingPolicy">
            <!--定义文件滚动时的文件名的格式-->
            <fileNamePattern>${scheduler.manager.server.home}/logs/${app.name}.%d{yyyy-MM-dd.HH}.log.gz
            </fileNamePattern>
            <!--60天的时间周期，日志量最大20GB-->
            <maxHistory>60</maxHistory>
            <!-- 该属性在 1.1.6版本后 才开始支持-->
            <totalSizeCap>20GB</totalSizeCap>
        </rollingPolicy>
        <triggeringPolicy class="ch.qos.logback.core.rolling.SizeBasedTriggeringPolicy">
            <!--每个日志文件最大100MB-->
            <maxFileSize>100MB</maxFileSize>
        </triggeringPolicy>
        <!--定义输出格式-->
        <encoder>
            <pattern>%d [%thread] %-5level %logger{36} [%file : %line] - %msg%n</pattern>
        </encoder>
</appender>
```



### 输出到数据库

以后用到了再来做补充



### 举例：logback.xml 配置文件

    <?xml version="1.0" encoding="UTF-8"?>
    <configuration debug="true" scan="true" scanPeriod="1 seconds">
    
        <contextName>logback</contextName>
        <!--定义参数,后面可以通过${app.name}使用-->
        <property name="app.name" value="logback_test"/>
        <!--ConsoleAppender 用于在屏幕上输出日志-->
        <appender name="stdout" class="ch.qos.logback.core.ConsoleAppender">
            <!--定义了一个过滤器,在LEVEL之下的日志输出不会被打印出来-->
            <!--这里定义了ERROR，也就是控制台不会输出比ERROR级别小的日志-->
            <filter class="ch.qos.logback.classic.filter.ThresholdFilter">
                <level>ERROR</level>
            </filter>
            <!-- encoder 默认配置为PatternLayoutEncoder -->
            <!--定义控制台输出格式-->
            <encoder>
                <pattern>%d [%thread] %-5level %logger{36} [%file : %line] - %msg%n</pattern>
            </encoder>
        </appender>
    
        <appender name="file" class="ch.qos.logback.core.rolling.RollingFileAppender">
            <!--定义日志输出的路径-->
            <!--这里的scheduler.manager.server.home 没有在上面的配置中设定，所以会使用java启动时配置的值-->
            <!--比如通过 java -Dscheduler.manager.server.home=/path/to XXXX 配置该属性-->
            <file>${scheduler.manager.server.home}/logs/${app.name}.log</file>
            <!--定义日志滚动的策略-->
            <rollingPolicy class="ch.qos.logback.core.rolling.TimeBasedRollingPolicy">
                <!--定义文件滚动时的文件名的格式-->
                <fileNamePattern>${scheduler.manager.server.home}/logs/${app.name}.%d{yyyy-MM-dd.HH}.log.gz
                </fileNamePattern>
                <!--60天的时间周期，日志量最大20GB-->
                <maxHistory>60</maxHistory>
                <!-- 该属性在 1.1.6版本后 才开始支持-->
                <totalSizeCap>20GB</totalSizeCap>
            </rollingPolicy>
            <triggeringPolicy class="ch.qos.logback.core.rolling.SizeBasedTriggeringPolicy">
                <!--每个日志文件最大100MB-->
                <maxFileSize>100MB</maxFileSize>
            </triggeringPolicy>
            <!--定义输出格式-->
            <encoder>
                <pattern>%d [%thread] %-5level %logger{36} [%file : %line] - %msg%n</pattern>
            </encoder>
        </appender>
    
        <!--root是默认的logger 这里设定输出级别是debug-->
        <root level="info">
            <!--定义了两个appender，日志会通过往这两个appender里面写-->
            <appender-ref ref="stdout"/>
            <appender-ref ref="file"/>
        </root>
    
        <!--对于类路径以 com.example.logback 开头的Logger,输出级别设置为warn,并且只输出到控制台-->
        <!--这个logger没有指定appender，它会继承root节点中定义的那些appender-->
        <logger name="com.example.logback" level="warn"/>
    
        <!--通过 LoggerFactory.getLogger("mytest") 可以获取到这个logger-->
        <!--由于这个logger自动继承了root的appender，root中已经有stdout的appender了，自己这边又引入了stdout的appender-->
        <!--如果没有设置 additivity="false" ,就会导致一条日志在控制台输出两次的情况-->
        <!--additivity表示要不要使用rootLogger配置的appender进行输出-->
        <logger name="mytest" level="info" additivity="false">
            <appender-ref ref="stdout"/>
        </logger>
    
        <!--由于设置了 additivity="false" ，所以输出时不会使用rootLogger的appender-->
        <!--但是这个logger本身又没有配置appender，所以使用这个logger输出日志的话就不会输出到任何地方-->
        <logger name="mytest2" level="info" additivity="false"/>
    </configuration>

# 日志规范-Ali

1. 【强制】应用中不可直接使用日志系统（Log4j、Logback）中的API，而应依赖使用日志框架SLF4J中的API，使用门面模式的日志框架，有利于维护和各个类的日志处理方式统一。

  ```
  import org.slf4j.Logger;
  import org.slf4j.LoggerFactory;
  private static final Logger logger = LoggerFactory.getLogger(Abc.class);
  ```

  

2. 【强制】日志文件推荐至少保存15天，因为有些异常具备以“周”为频次发生的特点。

3. 【强制】应用中的扩展日志（如打点、临时监控、访问日志等）命名方式：appName_logType_logName.log。logType:日志类型，推荐分类有stats/desc/monitor/visit等；logName:日志描述。这种命名的好处：通过文件名就可知道日志文件属于什么应用，什么类型，什么目的，也有利于归类查找。
正例：mppserver应用中单独监控时区转换异常，如： mppserver_monitor_timeZoneConvert.log 说明：推荐对日志进行分类，如将错误日志和业务日志分开存放，便于开发人员查看，也便于通过日志对系统进行及时监控。

4. ```
  正例：（条件）
  if (logger.isDebugEnabled()) {
  logger.debug("Processing trade with id: " + id + " and symbol: " + symbol);
  }
  正例：（占位符）
  logger.debug("Processing trade with id: {} and symbol : {} ", id, symbol);
  ```

  

5. 【强制】避免重复打印日志，浪费磁盘空间，务必在log4j.xml中设置additivity=false。 正例：

   ```
   <logger name="com.taobao.dubbo.config" additivity="false">
   ```

6. 【强制】异常信息应该包括两类信息：案发现场信息和异常堆栈信息。如果不处理，那么通过关键字throws往上抛出。 正例：logger.error(各类参数或者对象toString + "_" + e.getMessage(), e);

7. 【推荐】谨慎地记录日志。生产环境禁止输出debug日志；有选择地输出info日志；如果使用warn来记录刚上线时的业务行为信息，一定要注意日志输出量的问题，避免把服务器磁盘撑爆，并记得及时删除这些观察日志。 说明：大量地输出无效日志，不利于系统性能提升，也不利于快速定位错误点。记录日志时请思考：这些日志真的有人看吗？看到这条日志你能做什么？能不能给问题排查带来好处？

8. 【参考】可以使用warn日志级别来记录用户输入参数错误的情况，避免用户投诉时，无所适从。注意日志输出的级别，error级别只记录系统逻辑出错、异常等重要的错误信息。如非必要，请不要在此场景打出error级别。

# 参考文章

https://blog.csdn.net/u013332124/article/details/82286573

