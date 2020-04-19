# IOC

IoC不是一种技术，只是一种思想，一个重要的面向对象编程的法则，它能指导我们如何设计出松耦合、更优良的程序。传统应用程序都是由我们在类内部主动创建依赖对象，从而导致类与类之间高耦合，难于测试；有了IoC容器后，把创建和查找依赖对象的控制权交给了容器，由容器进行注入组合对象，所以对象与对象之间是松散耦合，这样也方便测试，利于功能复用，更重要的是使得程序的整个体系结构变得非常灵活。

Spring的IoC容器是 Spring 的核心，Spring AOP是 Spring 框架的重要组成部分。

在传统的面向对象程序设计中，调用者A 依赖于 被调用者 B，当 A 需要使用到 B 的功能时，往往是由 A 来创建 B 的实例。而在 Spring 中，A 对 B对象的控制权不再由自己管理了，而是交由 Spring 容器来管理，即控制反转。Spring 容器创建 B 对象后，将其注入给 A 使用，即依赖注入。

依赖注入：从外部注入 A 依赖的对象 B。

控制反转：把 对 B 的控制权，由 A 的内部 反转到 外部来了。

- 控制指的是：当前对象对内部成员的控制权。
- 反转指的是：这种控制权不由当前对象内部管理了，由其他外部(类,第三方容器)来管理。
- 对象的创建交给外部容器完成，这个就做控制反转。 IoC(思想，设计模式)主要的实现方式有两种：依赖查找，依赖注入。

依赖注入是一种更可取的方式(实现的方式)

使用IOC的好处：

1. 不用自己组装，拿来就用。
2. 享受单例的好处，效率高，不浪费空间。
3. 便于单元测试，方便切换mock组件。
4. 便于进行AOP操作，对于使用者是透明的。
5. 统一配置，便于修改。

## IOC容器启动步骤

1. 资源文件（Resource）位置的定位
2. 获取document对象并对其进行解析
3. 容器通过 BeanDefinitionReader来完成信息的解析和 Bean 信息的注册，将bean信息存入到BeanDefinition中对象
4. 然后将BeanDefinition信息注册到一个ConcurrentHashMap中。

## 依赖注入过程简介

1. 发生时机：
   1. 第一次通过getBean方法向IoC容索要Bean时，IoC容器触发依赖注入
   2. 元素配置了lazy-init=false，默认懒加载，即让容器在解析注册Bean定义时进行预实例化，触发依赖注入
2. 通过doGetBean方法，获取对应的bean对象。
   1. 单例模式：创建之前先看之前是否已经创建过，如果创建过直接从缓存中找。
   2. 原型模式：每次都创建一个新的实例对象。
3. 具体的依赖注入实现（Bean对象实例设置到它所依赖的Bean对象属性上去）
   1. createBeanInstance：生成Bean所包含的java对象实例
   2. populateBean ：对Bean属性的依赖注入进行处理
      1. 属性值类型不需要转换时，不需要解析属性值，直接准备进行依赖注入。
      2. 属性值需要进行类型转换时，如对其他对象的引用等，首先需要解析属性值，然后对解析后的属性值进行依赖注入。
4. 查需要进行依赖注入的属性：autowireByName在RootBeanDefinition中查找属性的信息如果是依赖的属性，初始化该属性实例对象，然后调用applyPropertyValues设置bean实例的属性值完成依赖注入。

## spring常用注解

```
@Configuration： //表示这个Java类充当XML配置文件
@ComponentScan(basePackages = "com.bravo.javaconfig")： //相当于XML中的<context:component-scan>标签，设置需要扫描的包
@ImportResource：("classpath:annotationDemoOne.xml")		//引入某个xml配置文件
@Required: 注释应用于 bean 属性的 setter 方法，它表明受影响的 bean 属性在配置时必须放在 XML 配置文件中，否则容器就会抛出一个 BeanInitializationException 异常。下面显示的是一个使用 @Required 注释的示例。
@Resource：按属性名注入（byName）
@PropertiesResource：用于指定properties文件的位置
@Autowired: 按类型自动注入属性。（byType）
@Qualifier: 可能会有这样一种情况，当你创建多个具有相同类型的 bean 时，并且想要用一个属性只为它们其中的一个进行装配，在这种情况下，你可以使用 @Qualifier 注释和 @Autowired 注释通过指定哪一个真正的 bean 将会被装配来消除混乱。下面显示的是使用 @Qualifier 注释的一个示例.
@Bean： 用在方法上，用于把当前方法的返回值作为Bean对象存入spring的IOC容器中，name：用于指定Bean的id，不写默认是当前方法的名称。
@Component：注册一个Bean默认为dao层
@Repository：注册一个Bean默认为mapper层
@Controller：注册一个Bean默认为Controller层
@Service：注册一个Bean默认在Service层
```

# AOP

**AOP中的概念：**

### 横切关注点
对哪些方法进行拦截，拦截后怎么处理，这些关注点称之为横切关注点
切面
类是对物体特征的抽象，切面就是对横切关注点的抽象
### 连接点
被拦截到的点，因为Spring只支持方法类型的连接点，所以在Spring中连接点指的就是被拦截到的方法，实际上连接点还可以是字段或者构造器
### 切入点
对连接点进行拦截的定义
### 通知
所谓通知指的就是指拦截到连接点之后要执行的代码，通知分为前置、后置、异常、最终、环绕通知五类
### 目标对象
代理的目标对象
### 织入

将切面应用到目标对象并导致代理对象创建的过程
### 引入

在不修改代码的前提下，引入可以在运行期为类动态地添加一些方法或字段

### 切入点表达式

```
返回类型		包名.类名.方法名.（参数）
通配：* *.*.*(..)
```
### 通知方法类型

```
前置通知：
<aop:before method="beforeMethod" pointcut-ref="addtohello"></aop:before>
环绕通知：
<aop:around method="aroundMethod" pointcut-ref="addtohello"></aop:around>
后置通知：
<aop:after method="afterMethod" pointcut-ref="addtohello"></aop:after>
异常通知：
<aop:after-throwing method 	="exceptionMethod" pointcut-ref="addtohello"></aop:after-throwing>
```


## 配置AOP

### XML配置
```
<!-- 1. 目标类 -->
<bean id="helloworld" class="com.CodeAm.springstudy.AOP.SpringAopDemoOne.HelloWorld"></bean>
<!-- 2. 通知类 -->
<bean id="logger" class="com.CodeAm.springstudy.AOP.SpringAopDemoOne.Logger"></bean>
<!-- 3. aop编程 -->
<aop:config>
    <!-- 配置切面，指定哪些方法需要被代理加强 -->
    <aop:pointcut id="addtohello" expression="execution(* com.CodeAm.springstudy.AOP.SpringAopDemoOne.HelloWorld.*(..))"/>
    <!-- 配置通知，具体加强的内容 -->
    <aop:aspect ref="logger">
        <aop:before method="beforeMethod" pointcut-ref="addtohello"></aop:before>
        <aop:around method="aroundMethod" pointcut-ref="addtohello"></aop:around>
        <aop:after method="afterMethod" pointcut-ref="addtohello"></aop:after>
        <aop:after-throwing method="exceptionMethod" pointcut-ref="addtohello"></aop:after-throwing>
    </aop:aspect>
</aop:config>
```

### 注解配置

```
@Configuration
@ComponentScan(basePackages = "com.CodeAm.springstudy.AOP")
@EnableAspectJAutoProxy：指明xml支持扫描配置aop
@Aspect：某个类作为切面类
@Pointcut("execution()")：指明切入点
@Around("pointMethod()")：指明连接点之间的环绕方法
```

# spring事务

Transactional注解：

## 事务传播级别：

- propagation_required:

总是需要事务，如果没有事务，则创建事务；有事务，则加入事务。

- propagation_required_new

总是以新事务执行，没有事务，创建事务；有事务，则挂起事务，新建事务。

- propagation_supported

  有事务，则加入事务；没有事务，则无事务执行。

- propagation_not_supported

总是非事务地执行，有事务，则挂起任何存在的事务。

- propagation_mandatory

必须要事务，没有事务则抛出异常。

- propagation_never

总是无事务，有事务则抛出异常。

- propagation_nested

如果一个活动的事务存在，则运行在一个嵌套的事务中. 如果没有活动事务, 则按TransactionDefinition.PROPAGATION_REQUIRED 属性执行。

回滚：内层事务回滚不会导致外层事务回滚，但是外层事务回滚会导致内层事务回滚。