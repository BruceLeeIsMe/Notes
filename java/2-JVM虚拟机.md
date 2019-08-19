# Java内存区域：

线程共享区域：方法区，堆
线程私有区域：本地方法栈，程序计数器，本地方法栈

## 堆

数组与对象在此区域分配内存。
数组是特殊的对象。

对象包含：

- 对象头

- 实例数据

- 填充数据

  

数组是特殊的对象，在对象头会有一个额外的一个字段用于存储数组的长度。

## 方法区

类 类型信息：

- 当前类全限定名，直接父类的全限定名，直接接口的全限定名
- 类修饰符
- 实例字段描述信息（）：字段名字，类型，修饰符（权限，final，volatile），字段实际所需内存在堆中分配。
- 方法信息：方法名 返回值 参数表 修饰符（权限，static？，synchornized），以及方法执行字节码（方法体).
- 静态字段信息：
  non-final静态字段信息：字段内容存储在类信息中（在方法区中开辟内存）。
  final静态字段信息：final static 修饰的变量，其实就像是一个全局访问的变量。他不仅存储在常量池中，还存储在任意一个使用到他的类中。
  假想的例如：

```s
class Hong{
       public final static int m=1;
 }
class Ming{
        Private int n=Hong.m;
} 
```

如果实例化B类，此时final static int m 就被存储在Ming对象的实例数据里了，也就是在堆内存中。           

- 持有加载该类的类加载的引用
- 持有该类的class对象的引用
- 方法表（用于快速的访问到方法，提高效率）

​            
​            

## 虚拟机栈：         

前导概念：
    栈帧：栈帧为一种数据结构，其中存储了方法执行的环境信息，包含：

- 局部变量表
- 操作数栈
- 动态链接
- 返回地址

附应用举例：（以后来补，加一个超链接跳到指定位置，指定位置再添加一个返回链接）

本地方法栈中存储的就是栈帧，栈帧的入栈与出栈，对应方法的调用与结束。

## 本地方法栈

作用类似于虚拟机栈，但是此处存储的栈帧所属于native方法。

## 程序计数器

当中存储了下一条字节码指令的地址。

其用于控制字节码解释器的解释顺序，程序流程控制就是通过程序计数器实现。
    
# 内存分配
什么东西分配到什么区域

# 垃圾回收
垃圾回收之前，需标记出需要清除的对象。

**如何标记？**
		引用计数法：存在一个引用则将计数+1，如果计数为0，则标记。缺点：无法解决循环引用问题。

```java
class A{
    private B b;
    puiblic setB(B b){
        this.b=b;
    }
}
class B{
    private A a;
    puiblic setA(A a){
        this.a=a;   
}
public class Test{
    public static void main(String args[]){
        A a=new A();
        B b=new B();
        a.setB(b);
        b.setA(b);
        //引用置空
        a=null;
        b=null;
        //a，b互相持有对方引用，计数均不为0，使用引用计数法无法回收。
    }
}
    
```

**可达性分析法：**

判断对象是否"可达"，若不可达则标记等待被清除。

**如何判断“可达”？**

以GC ROOTS为起点，往下进行搜索，搜索走过的路径称为引用链，如果一个对象没有与任何引用链相连，则会被标记：

- 虚拟机栈-本地变量表引用的对象
- 类静态变量引用的对象
- 方法区中常量引用的对象（final修饰的类字段）
- jni（native本地方法）的参数表引用的对象

几种引用类型：

- 强引用
- 弱应用
- 软引用
- 虚引用



垃圾回收的在哪个区域进行？
    堆和方法区（hotspot虚拟机把永久代实现在了方法区）
    
何时垃圾回收？
    新生代或者老年代空间不够
垃圾回收时，有什么条件？
    stop the world，然后枚举根结点，在各个线程处于安全点或者安全区域时
        （引用关系不会改变的点或者代码段区域）
垃圾回收的种类，以及各自 特点？
    minor gc，回收新生代，采用复制算法
    full gc（major gc） ，回收老年代，采用标记整理算法，一般比MinorGc慢十倍以上（出现了一次Full GC，经常会伴随至少一次的minor gc,当然也会有例外的情况）

## 算法
- 标记清除算法：标记然后清除。

  缺点：清理后导致内存碎片化严重，并且效率低

- 复制算法

   将内存一分为二（按照比例1:1），内存开辟时，只在其中一片区域开辟，当内存大小无法满足下一次开辟请求时，进行垃圾回收。回收：将A区域中存活的对象复制到B区域，此时A区域则被腾空用于内存开辟。

  适用场景：对象存活率低的区域。（若存活率高，复制多，导致效率低）

- 标记整理算法

  GC时，将存活的对象全都移动整理到区域的另一边，然后清空存活对象之外的区域。

  适用场景：存活率高的区域。（存活率低用复制算法）

- 分代垃圾收集

  分代垃圾回收其实是综合了前面几个算法的应用。

  分代，分为新生代和老年代。

  在新生代，有98%的对象都是朝生夕死，所以才用**复制**算法，由于极低的存活率，所以将内存以8:1:1进行分配，分别为Eden，from survivor，to survivor 三个区域。

  当Eden区域与from survivor区域的空间无法满足下一次内存分配，触发MinorGC（新生代GC），将存活对象复制到to survivor区域，然后清空eden与from survivor。

  **注：**from 和to 是相对的，两者会在下一次GC后，进行互换。

## 垃圾收集器
根据不同区域采用不同特点的收集器

​    







### 

# 字节码文件

初步了解：

mark一下，以后再深入学习，

强化一下，JVM知识。
字节码文件是平台无关性的基石。
    无论是什么编译器，只要是符合规范的虚拟机class文件，虚拟机都能解释执行。
    

字节码文件中只有两种数据类型，无符号数 和 表（一种复合数据类型）。
    无符号数：
    u1，u2，u4，u8分别代表1，2，4，8字节的无符号数。
        作用：可用于表示数字，索引引用，数量值，或者按照UTF-8编码构成字符串值。
    表：复合数据类型
        组成：由其他无符号数或者其他表构成。
        所有的表都习惯性的以info结尾。
        表一般都有一个count属性在前，表达这个表中数据的长度。
            例如：（u2）             fields_count
                       （fields_info）fields
                       fileds_info表结构：
                       附fileds_info表详细数据结构。
                       

## class文件结构

class文件本质上就是一张表，表中包含无符号数和其他表，其他表又包含副无符号数与表。

附Class文件格式表：

这里是表

* 魔数（magic）
    0xcafebabe
    作用：标识此文件为class文件
        为什么不用文件名后缀来表示呢？
            因为文件名后缀可以随意更改，而使用二进制码不易进行更改。
    
* 版本号：
    major version（主版本）
    minor version（次版本）

* 整个类的常量池（ constant pool）

* 访问标志（access_flags）
两个字节，每一位代表不同的信息。

* 直接父类

* 直接父接口表
  
* 字段表集合

* 方法表集合

* 属性表集合（部分属性是字段和方法的附加属性，比如：Code和ConstantValve）
  

![javap](.\img\javap.png)

### Code属性：

解释：

flags：方法修饰符标志



stack=2，操作数栈深度为2。深度由编译器时确定。

locals=2，本地变量表长度为2，索引从0开始！长度编译器决定，		包含方法参数，以及方法内局部变量。

args_size，方法参数大小



命令解释：

iconst_1: intContstant 1:定义int类型的常量1放入操作数栈中。

istore_1:将栈顶的int类型的数据存入 到 索引为1的局部变量表中。

iload_1: 将局部变量表中索引为1的int型数据加载到栈顶。

iadd: 将操作数栈中的两个int型元素依次弹出，再进行加法，最后		   将结果推入栈中。

i2b: int to byte: int类型转byte类型




## 字节码指令简介
指令操作的完成都是基于操作数栈与局部变量表完成的。

指令大致分为9类：
* 加载和存储指令
* 存储指令
* 类型转换指令
* 对象创建与访问指令
* 操作数帧管理指令
* 控制转移指令
* 方法调用与返回指令
* 异常处理指令
* 同步指令

# 虚拟机字节码执行引擎



# 类加载

类加载是将class字节码数据读取到虚拟机在方法区生成class对象的过程。

## 何时触发类加载

new ClaaName();

clazz.forName("com.xx.xx");

classsLoader.loadClass("com.xx.xx");

调用静态方法

使用非编译期常量的静态字段



## 类加载过程

主要分为三个阶段，这三个阶段是交叉执行的，没有严格的执行顺序。

* 加载
  1)通过类的全限定名获取class字节流将其加载到内存中（来源没有规定，可从多个途径获取class字节流，如.class文件，jar包，网络-ejb？dubbo？，数据库，计算生成-动态代理）
  2)将class文件的静态存储结构转化为方法区的运行时数据结构（这个结构具体是啥样没有规定，不同虚拟机有不同实现）
  3)生成class对象，作为程序代码访问方法区数据的接口。（所以在这个class对象中存了许多引用，都是指向运行时数据区的方法、字段、注解等数据）
  
* 连接
   连接再细分为三个阶段：
    1. 验证(文件格式验证，元数据验证，字节码验证，符号引用验证)
    2. 准备(虚拟机给静态变量赋初值，基本数据类型赋0，引用类型赋null，布尔赋false)
    3. 解析(将常量池的部分符号引用转换为直接引用，被重写的方法需要在运行时才能明确直接引用)
* 初始化
     执行程序员给予的初值，例如private static int a=3； 将3赋值给a。
     再执行静态代码块，对类进行初始化。
       多个静态代码块执行顺序，按照编写的顺序从上至下进行执行。
     如果静态变量定义在静态代码块前，块中对静态变量可以赋值，但是不能读取，读区
     会出错。
     `private static int a=3`语句和静态代码块都被虚拟机收集起来生成<cinit>方法。
##      数组的加载
## 类加载器

每一个类加载器都指定唯一的路径去加载类，且只能在该路径下加载。

一个类加载器维护一个**命名空间**！



**如何确定class唯一性**：通过类加载器（命名空间）+全限定名！

```java
Class<?> class1=new MyClassLoader().loadClass("com.jornah.A");
Class<?> class2=new MyClassLoader().loadClass("com.jornah.A");
System.out.println(class1==class2);
/*
result:	false
*/
```



### JVM中的类加载器：

启动类加载器 `Bootstrap`（加载JVM的lib路径）：该加载器由C++实现

扩展类加载器 `ExtClassLoader`（加载JVM的lib/ext路径）

应用类加载器 `AppClassLoader`（加载classPath路径）

**'继承体系结构'：**

Bootstrap

|--ExtClassLoader

​	|--AppClassLoader

​		|--自定义类加载器

### 自定义类加载器：

**破坏双亲委派方式：**重写loadClass方法，双亲委派机制的逻辑存在于该方法中，重写即可使其失效。

```java
public class DIYClassLoader extends ClassLoader{
    private String classPath;
    @Override
    public Class<?> loadClass(String className){
        // implements
        // to load class in its classPath
        // get class byteStream
        // call defineClass()
    }

}
```



**不破坏双亲委派方式：**重写findClass，双亲委派机制中（loadClass方法逻辑中）如果父加载器都找不到class，则调用findClass加载。

```java
public class DIYClassLoader extends ClassLoader{
    private String classPath;
    @Override
    public Class<?> findClass(String className){
        // implements
        // to load class in its classPath
        // get class byteStream
        // call defineClass()
    }

}
```



### 双亲委派机制

双亲委派机制，此处的父亲并非使用的`OOP`中的继承，而是使用的'组合'。

组合：子加载器存放父类引用。

双亲委派机制逻辑代码存放于ClassLoader.loadClass()方法中:

```java
// 省略部分代码
protected Class<?> loadClass(String name, boolean resolve)
    throws ClassNotFoundException
{
    synchronized (getClassLoadingLock(name)) {
        // First, check if the class has already been loaded
        Class<?> c = findLoadedClass(name);
        if (c == null) {
            try {
                if (parent != null) {
                    c = parent.loadClass(name, false);
                } else {
                    c = findBootstrapClassOrNull(name);
                }
            } catch (ClassNotFoundException e) {
                // ClassNotFoundException thrown if class not found
                // from the non-null parent class loader
            }

            if (c == null) {
                c = findClass(name);
            }
        }
        if (resolve) {
            resolveClass(c);
        }
        return c;
    }
}
```


**类加载器遇到加载要求时：**

1. 先从自己的明明空间里找这个类，if (get) return class; 
2. 若有爹，则将请求委托给父类去加载
3. 判断flag:`resolve`，若真，则链接此class（默认不链接false）。





### 打破双亲委派机制（非贬义）

- `jdk1.2`之前未出现双亲委派机制
- 上下文类加载器（JNDI，JDBC，JDBC接口使用extCLder或BtStrap加载，JDBC实现则有各大数据库厂商实现，使用appClassLoader加载，父加载器无法访问子加载器空间，所以上下文类加载器应运而生）
- OSGI（网状的类加载器架构）：热部署

#### 不同命名空间，类的可见性

父加载器命名空间中的类对子加载器可见。

子加载器命令空间中的类对父加载器不可见。

无子父关系的类加载器命名空间中的类相互不可见。

**特殊：**上下文加载器，此情况下，父加载器**可见**上下文类加载器的命名空间

```
Thread.getCurrentThread().getContextClassLoader();
```



[巨大的疑问](#question 1)：（已解决）

这个疑问困扰了我两周，到处都没有文献将这个问题。



# 反射

利用class对象。

获取class对象的方法：

forName 与loadClass区别：

- forName：默认连接并且初始化class
- loadClass：默认不连接

# Java内存模型（多线程）

这是逻辑模型。没有实际硬件区域对应。

## 主内存

## 直接内存

## 三大特性

### 原子性

### 可见性

### 有序性

## 什么是线程

## 线程的实现方式

## 锁以及锁优化



















































































































# Questions

## question 1

之前看书的时候有一个巨大的**疑问**:不同的两个类加载器加载了同一个class文件，那我在程序代码中 new 这个类的时候，虚拟机new的是哪个类加载的class呢？

解答：

取决于 `当前代码块所属类` 所处于的命名空间！

按照双亲委派从当前空间向上查找，找到就不再继续往上查照。



## question 2

## question 3

## question 4

## question 5

## question 6

## question 7

## question 8

## question 9

## question 10