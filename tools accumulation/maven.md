# 回顾深化maven

dependencyManagement

解决maven单继承问题，可实现多继承



举例：

 如果我的项目本来就有父pom.xml了，但是我现在想使用另外一个项目A的dependencyManagement中声明的依赖 。

tip：  我本想在 直接依赖A进来，结果不行，无法获取其dependencyManagement内容

使用如下代码即可实现引入A项目的dependencyManagement中的内容。

```
<dependencyManagement>
    <dependencies>
        <dependency>
            <groupId>XXX</groupId>
            <artifactId>A</artifactId>
            <version>1.0-SNAPSHOT</version>
            <type>pom</type>
            <scope>import</scope>
        </dependency>

    </dependencies>
</dependencyManagement>
```

`<scope>import</scope>`只能在dependencyManagement中使用！！



maven 的type、scope classifier 等各种标签的深入学习！！！！

加油 奥利给