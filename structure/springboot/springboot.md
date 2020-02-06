# 网页生成springboot项目

url： io.start.spring



# 打包方式：jar or war



spring boot既可以打成war发布，也可以找成jar包发布。说一下区别

jar包：直接通过内置tomcat运行，不需要额外安装tomcat。如需修改内置tomcat的配置，只需要在spring boot的配置文件中配置。内置tomcat没有自己的日志输出，全靠jar包应用输出日志。但是比较方便，快速，比较简单。

war包：传统的应用交付方式，需要安装tomcat，然后放到waeapps目录下运行war包，可以灵活选择tomcat版本，可以直接修改tomcat的配置，有自己的tomcat日志输出，可以灵活配置安全策略。相对打成jar包来说没那么快速方便。



# 自动配置

## 原理

1. Springboot 主类上的注解 `@SpringBootApplication`

   ```
   @Target({ElementType.TYPE})
   @Retention(RetentionPolicy.RUNTIME)
   @Documented
   @Inherited
   @SpringBootConfiguration
   @EnableAutoConfiguration@ComponentScan(    excludeFilters = {@Filter(    type = FilterType.CUSTOM,    classes = {TypeExcludeFilter.class}), @Filter(    type = FilterType.CUSTOM,    classes = {AutoConfigurationExcludeFilter.class})})@ConfigurationPropertiesScanpublic @interface SpringBootApplication {
   ```

   

2. 这个注解配置了组件扫描，@ComponentScan，开启了@EnableAutoConfiguration

3. @EnableAutoConfiguration具体是怎么发挥作用的？

   ```
   @Target({ElementType.TYPE})
   @Retention(RetentionPolicy.RUNTIME)
   @Documented
   @Inherited
   @AutoConfigurationPackage
   @Import({AutoConfigurationImportSelector.class})
   public @interface EnableAutoConfiguration {
   ```

以上可只该注解上使用了@Import注解，

Import的作用是什么呢？顾名思义，导入的意思

@Import({AutoConfigurationImportSelector.class})：导入自动配置导入选择器

查看AutoConfigurationImportSelector的源码：selectImports方法

```
public String[] selectImports(AnnotationMetadata annotationMetadata) {
	if (!this.isEnabled(annotationMetadata)) {
		return NO_IMPORTS;
	} else {
		AutoConfigurationMetadata autoConfigurationMetadata = AutoConfigurationMetadataLoader.loadMetadata(this.beanClassLoader);
		AutoConfigurationImportSelector.AutoConfigurationEntry autoConfigurationEntry = this.getAutoConfigurationEntry(autoConfigurationMetadata, annotationMetadata);
		return StringUtils.toStringArray(autoConfigurationEntry.getConfigurations());
	}
}
```

其实这里我有一个疑问： selectImports是在哪里被调用的呢？ 是注解处理器在检测到这个注解的时候，获取注解的参数，然后通过反射调用的？ 嗯 应该就是这样被调用的。

- List<String> configurations = getCandidateConfigurations(annotationMetadata,  attributes);获取候选的配置
- SpringFactoriesLoader.loadFactoryNames()
  扫描所有jar包类路径下  META-INF/spring.factories
  把扫描到的这些文件的内容包装成properties对象
  从properties中获取到EnableAutoConfiguration.class类（类名）对应的值，然后把他们添加在容器中

将类路径下  META-INF/spring.factories 里面配置的所有EnableAutoConfiguration的值加入到了容器中

每一个这样的  xxxAutoConfiguration类都是容器中的一个组件，都加入到容器中,用他们来做自动配置。

## 举例详细介绍：

```
//此段为摘抄：https://mp.weixin.qq.com/s/jkLgqM35TrlryyUb7mmhjQ
@Configuration  //表示这是一个配置类，以前编写的配置文件一样，也可以给容器中添加组件
@EnableConfigurationProperties(HttpEncodingProperties.class) //启动指定类的ConfigurationProperties功能；将配置文件中对应的值和HttpEncodingProperties绑定起来；并把HttpEncodingProperties加入到ioc容器中

@ConditionalOnWebApplication 
//Spring底层@Conditional注解（Spring注解版），根据不同的条件，如果满足指定的条件，整个配置类里面的配置就会生效；判断当前应用是否是web应用，如果是，当前配置类生效

@ConditionalOnClass(CharacterEncodingFilter.class) 
//判断当前项目有没有这个类CharacterEncodingFilter；SpringMVC中进行乱码解决的过滤器；

@ConditionalOnProperty(prefix = "spring.http.encoding", value = "enabled", matchIfMissing = true) 
//判断配置文件中是否存在某个配置 spring.http.encoding.enabled；如果不存在，判断也是成立的
//即使我们配置文件中不配置pring.http.encoding.enabled=true，也是默认生效的；
public class HttpEncodingAutoConfiguration {

   //他已经和SpringBoot的配置文件映射了
   private final HttpEncodingProperties properties;

  //只有一个有参构造器的情况下，参数的值就会从容器中拿
   public HttpEncodingAutoConfiguration(HttpEncodingProperties properties) {
    this.properties = properties;
  }

  @Bean  //给容器中添加一个组件，这个组件的某些值需要从properties中获取
  @ConditionalOnMissingBean(CharacterEncodingFilter.class) //判断容器没有这个组件
  public CharacterEncodingFilter characterEncodingFilter() {
    CharacterEncodingFilter filter = new OrderedCharacterEncodingFilter();
    filter.setEncoding(this.properties.getCharset().name());
    filter.setForceRequestEncoding(this.properties.shouldForce(Type.REQUEST));
    filter.setForceResponseEncoding(this.properties.shouldForce(Type.RESPONSE));
    return filter;
  } 
```

## 个人理解与总结：

1. 每一个XXAutoConfiguration 都有一个对应的XXProperty类对应，XXProperty的字段对应其配置文件的key

2. @EnableConfigurationProperties(HttpEncodingProperties.class)注解作用：将HttpEncodingProperties的属性绑定到HttpEncodingAutoConfiguration中

3. 那配置文件中的是如何绑定到HttpEncodingProperties的呢？

   ```
   @ConfigurationProperties(prefix = "spring.http")
   public class HttpProperties {
   ```

如上，使用注解@ConfigurationProperties(prefix = "spring.http")将其与配置文件中的内容进行绑定

4. @Import注解，其实可以理解为导入XX配置类到本类的意思。

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



# spring注解回顾：



1. @ComponntScan注解
   @SpringBootApplicattion中继承了ComponnetScan注解，所以默认springboot项目会扫描 springapplication启动所在包下一级的包。

   如果需要自定义扫描包，只需要在SpringApplication类上加上注解@ComponentScan(basePackages={“packagePath1”,”packagePath2”})

   注意：如果pom导入的公用模块中含有相同的包结构，那么符合组件扫描条件的bean也会被spring装配。

2. @Bean注解，这是一个方法上的注解，这个方法的返回对象被spring管理，同时Bean的名字为方法名。





# issue

## 注意：不同版本的springboot 配置的key值可能有变化

主要取决于*property文件中的 field 名字的变化。

