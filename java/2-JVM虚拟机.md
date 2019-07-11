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

byte b=1;

b=b+1;//编译不通过，需要强制类型转换

b+=1;//编译通过



## 引用数据类型

1. 类
2. 接口
3. 数组