# Java中的数据类型

## 基本数据类型

又名：原生类型、内置类型。

1. 整型 **byte** **short**(2 byte) **int**(4 byte) **long**(8 byte)
2. 浮点型 **float**(4 byte) **double**(8 byte)
3. 字符型 **char**(2 byte) Java使用Unicode编码，可以存储汉字
4. 布尔型 **boolean**(1 byte) 理论占1 bit 实际占用1 byte



默认类型：

​	int为整型的默认类型

​	double为浮点型的默认类型



如何输入基本数据类型：

整型：

```java
	byte b=12;
	short s=12;
	long l=12L;
```
浮点型：

```java
	float f=1.0f;
	double d=1.0d;：
```


### 类型转换相关：

​	byte、short在数学运算的时候，会自动转化为int类型进行计算。

#### 自动类型转换：

**情境一：小类型与大类型运算时，自动转化为大类型**

byte b=1;

b=b+1;//编译不通过，需要强制类型转换

b+=1;//编译通过

为什么呢？底层原理：

​	通过反编译得知，字节码文件内容如下：

![avap](H:\GitRepository\Notes\java\img\javap.png)

在add操作结束之后，使用了i2b命令（int to byte），**自动**对结果类型进行了**强制类型转换**。

**情境二：小类型赋值给大类型，自动转化为大类型**

byte b=1;

int i=b;

**情境三：复合运算符的自动转换**

```java
int a=1;

long b=2;

a+=b; // 隐含了 两次 类型转换，实际为 a=(int)((long)a+b)
	  // 一次为 运算时自动转换，另一次则为复合运算符进行的类型转换。
```

此时突然回忆起一个小知识点：两个相同类型的数运算时，那么运算结果类型不变，如果运算结果超出范围，则会造成精度丢失。

比如：

```java
int a=3;

double c = a/2;		// 错误，精度丢失

result: c=1;

===============================

double c = a/2.0;	// 正确

result: c=1.5;

===============================

int a=2147483647;
int c=a*2;			// 错误，精度丢失
System.out.println(c);// result: -2

===============================

long c=a*2L;			// 正确
System.out.println(c);// result: 4294967294

===============================
```

回到正题：

符合运算符用于简化书写,同时也有自动类型转换的作用。



**符合运算符们：**

`+=`：加法赋值

`-=`：

`*=`：

`/=`

`%=`

`>>=`：右移赋值

`<<=`：左移赋值

`&=`： 位与赋值

`|=`：位或赋值

`^=`：异或赋值



#### 强制类型转换

小类型转换为大类型时，强制类型转换，截取低位（保留低位）。

```java
byte b=byte(1); // 此处保留低八位

// b值为1
```

**注意：**

强制转换为小类型时，大类型的 **数据位** 可能会变成小类型的  **符号位**！

```java
byte b=byte(129);// 此处保留低八位

// b值为-127
```

129:	补码为：0000 0000,0000 0000,0000 0000,1000 0001

截取byte大小的低位: 1000 0001(补码)



疑问？补码的符号位与原码符号位是一样的吗？？

​	除`-128`以外，他们符号位都是一样的！



所以，负数 补码转原码，1000 0001取反+1 ：1111 1111即：-127

----

### i++与++i问题：

int i=0;

int j=i++;

结果：i=1;j=0;

![223](H:\GitRepository\Notes\java\img\123.png)

i++完成后，i并没有进入操作数栈，于是之前存入栈顶的 0 弹出到 j

----

i=0;

j=++j;

结果：i=1;j=1;

![223](H:\GitRepository\Notes\java\img\223.png)

完成++j后，将 j 再载入到操作数栈，再将其存储弹出 j





但是如何记忆i++与++i的区别呢。总不能每次都去反编译读字节码吧！

？？？？

**总结：**

如：

```java
int i=0;

i++;//++i;
```

​	理解为，i++与++i其实都有返回值。

​		i++先自增，再返回自增前的值。

​		++i先自增，再返回自增后的值。









## 引用数据类型

1. 类
2. 接口
3. 数组

### 类型转换

1. 父类型转子类型
2. 子类型转父类型
3. 

**个人理解总结：**

是否能强制转换成功，主要取决于此时堆中的实例对象是父类还是子类，而与引用类型无关。

堆中的是子对象则其引用可以随意向上转型为父对象引用，如果为父类型对象，则无法转为子类型引用。

```java
Fu f=(Fu)new Zi();	// 引用 向上转型成功
Zi z=(Zi)f;			// 引用 向下转型成功
```

```java
Fu f=new Fu();
Zi f=(Zi) f;	// 引用 向下转型失败，因为f引用指向的是父类实例对象，引用无法向下转型。
```

**复习继承相关知识：**

1. 父类实例变量会随继承到子类，如果子父类变量重名，那么父类变量被隐藏。
2. 父类实例方法会随继承到子类，如果子父类方法重名，那么父类方法被重写(overide)。
3. 静态方法以及静态字段，这是属于类的元素，不会在extends与implements中遗传到子类。



```java
class Fu{
	public static int staticVar=1;
	int instVar=2;
	public void showName(){
		System.out.println("this is Fu");		
	}
}
class Zi extends Fu{
	public static int staticVar=2;
	int instVar=1;
	public void showName(){
		System.out.println("this is Zi");
	}
    public void ziFun(){
        System.out.println("this is 子类 fun");
    }
    
    public static void main(String args[]){
        Fu f=(Fu)new Zi();	// 向上转型
        Zi z=(Zi)f;			// 向下转型
        /* 静态方法与字段 start */
        
        System.out.println(f.staticVar);// 为f引用所属类的静态字段，即1
        System.out.println(z.staticVar);// 为z引用所属类的静态字段，即2
        // ali编码规范中，说到不要用引用去访问静态字段，无端增加编译器负担，此处学习需要。
        
        /* 静态方法与字段 end */
        
        /*------------分割线---------------*/
        
        /* 实例变量以及方法 start */
        
        System.out.println(f.instVar);
        System.out.println(z.instVar);
        // 引用f,z 都是指向运行时堆中的同一个对象，所以两次输出都为上面实例化的Zi类的实例变量。
        // 当然执行的方法也是对应其实例化对象的方法
        
        // 父类引用调用 子类特有方法，编译失败
        f.ziFun();// 因为在编译时间，编译器通过符号引用f，只能找到符号引用f（ 即Fu）的方
        		  // 法，父类引用没有子类方法，所以报错
        
        /* 实例变量以及方法 end */
    }
}

```



# OOP特性

**object oriented programming：**



**四大特性：**



## 抽象

## 封装

## 继承

**java继承与实现：**

extends继承：

implements实现：

## 多态

1. 重写
2. 重载
3. 动态绑定



# 关键字：

