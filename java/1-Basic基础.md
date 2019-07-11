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

		float f=1.0f;
		double d=1.0d;
基本数据类型转换：



**注意：**

​	byte、short在数学运算的时候，会自动转化为int类型进行计算。

**典例：**

**例题一：**

byte b=1;

b=b+1;//编译不通过，需要强制类型转换

b+=1;//编译通过

为什么呢？底层原理：

​	通过反编译得知，字节码文件内容如下：

![avap](H:\GitRepository\Notes\java\img\javap.png)

在add操作结束之后，使用了i2b命令（int to byte），对结果类型进行了强制类型转换。

----

**例题二：**

i++与++i问题：

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

























## 引用数据类型

1. 类
2. 接口
3. 数组

