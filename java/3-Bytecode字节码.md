# 初步了解：

mark一下，以后再深入学习，

强化一下，JVM知识。

![avap](H:\GitRepository\Notes\java\img\javap.png)

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

