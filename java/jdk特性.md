# jdk1

# jdk2

# jdk3

# jdk4

# jdk5

# jdk6

# jdk7

## try with resource

实现了Closedable接口和AutoClosedable接口可以使用该特性，自动关闭资源。

举例：

```
        try (InputStream in = new FileInputStream(new File("F:\\dnfdouble.data"));
        ) {
            in.read();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
```



# jdk8

什么是函数式接口？

只有一个方法的接口，比如 runnable ， closeable ，Comparable这种。

## lambda表达式

 λ表达式本质上是一个匿名方法 

是函数式接口的匿名类对象的替代写法。

```
        // 以前：
        Runnable run1 =new Runnable(){
            @Override
            public void run() {
                System.out.println("before");
            }
        };
        new Thread(run1).start();
        
        // 现在: lambda
        Runnable run2 = () -> {
            int x = 0, y = 0;
            System.out.println("now ");
        };
        new Thread(run2).start();
```

如果接口方法有返回值和参数的时候：

比如Comparable：public int compareTo(T o);

```
        String a="asd";
        Comparable comp=(x)->{
          return  1;
        };
        // 括号部分的就是匿名方法的参数表
        // 大括号部分就是方法体
```



# jdk9

# jdk10

# jdk11



