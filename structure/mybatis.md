# 转自https://github.com/ZQ0815/MyBlog/blob/master/MyBatis/MyBatis.md

# Mybatis的核心类

## SqlSessionFactoryBuilder
>该类会根据xml文件配置信息创建对应的SqlSessionFactory。
## SqlSessionFactory
>一个工厂接口，任务是创建SqlSession，类似于一个JDBC的Connection。
## SqlSession
>一个接口类，根据提供的信息（参数）返回对应的结果（Result）.类似于Connection接口，每次用完都需要关闭。
## 映射器
由java接口和XML文件组成，目的如下：
- 定义参数类型
- 描述缓存
- 描述SQL语句
- 定义查询结果和POJO的映射关系

## 接口与XML文件
- 接口中定义方法名字和返回类型
```
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper
        PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
        "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="com.codeam.springmvc.mapper.PersonMapper">
    <select id="getPersonById" parameterType="int" resultType="com.codeam.springmvc.model.Person">
    SELECT * FROM USER WHERE id = #{id}
</select>
</mapper>
```
>namespace: 用来注册Mapper
- 配置文件中指明SQL语句及其参数，返回结果。

```
public interface PersonMapper {
    public Person getPersonById(int id);
}
```

# Mybatis的创建流程
- 第一步创建XML文件，用SqlSessionFactoryBuild读取XML的信息来创建SqlSessionFactory。xml的配置信息会解析到Configuration类对象中。
- 创建SqlSession，使用SqlSession中的接口。
- 创建映射器，及java 接口和XML配置文件。

# 映射器的介绍
>Mybatis是针对映射器构造的SQL构建的轻量级框架，并且通过配置生成对应的JAVABean给调用者。


# 映射器的注册方式

## 文件引入
```
<mappers>
    <mapper resource="mappers/PersonMapper.xml"></mapper>
</mappers>
```
## 包名引入
```
<mappers>
    <package name="com.codeam.springmvc.mapper"/>
</mappers>
```

## 接口引入
```
<mappers>
    <mapper class="com.codeam.springmvc.mapper.PersonMapper"></mapper>
</mappers>
```

# 映射器的主要元素

## select
>最常用也是功能最强大的语言。主要用于从数据库中读取数据。

### 自动映射
>autoMappingBehavior参数部位none的时候，MyBatis会提供自动映射功能，只要返回的SQL列名和JavaBean的属性一致，MyBatis就会回填这些字段而无需任何配置。

```
默认规范：
SQL命名：USER_NAME
POJO命名：userName 驼峰命名

<select id="getPersonById" parameterType="int" resultType="com.codeam.springmvc.model.Person">
    Select id, user_name as userName from t_role where id = #{id}
</select>
```
### 自动映射属性
- NONE：取消自动映射
- PARTIAL：只会自动映射，没有定义嵌套结果集映射的结果集。
- FULL：自动映射任何复杂的结果集

### 传递多个属性
- 使用注解传递参数
```
1.接口处用注解标明
public Person getPersonById(@Param("id") int id);
2.XML文件中无需写明
```
- 使用javaBean传递参数
```
1.XML文件中写明参数类型是一个JavaBean对象
parameterType="com.codeam.springmvc.model.Person"
2.接口的参数设置为JavaBean类型
```

## insert
>insert元素相对简单，执行插入后返回一个整数。
### 简单的插入实例
```
<insert parameterType="com.codeam.springmvc.model.Person" id="insertPerson">
    INSERT INTO USER(ID, USERNAME) VALUES (#{id},#{userName})
</insert>
```

### 主键回填和自定义
>插入时需要处理的常见问题有：主键自增字段，和特殊规则操作主键。
>**设置主键**
```
keyProperty： 指定哪个是主键
useGeneratedKeys：告诉MyBatis主键是否使用数据库内置策略生成
<insert parameterType="com.codeam.springmvc.model.Person" id="insertPerson" keyProperty="id" useGeneratedKeys="true">
    INSERT INTO USER(ID, USERNAME) VALUES (#{id},#{userName})
</insert>
```
**特殊处理**
```
定义自己的规则来处理主键：
<insert parameterType="com.codeam.springmvc.model.Person" id="insertPerson" keyProperty="id" useGeneratedKeys="true">
    <selectKey keyProperty="id" resultType="int" order="BEFORE">
        SELECT if(max(id) is null, 1, max(id) + 2) as newId from USER
    </selectKey>
    INSERT INTO USER(ID, USERNAME) VALUES (#{id},#{userName})
</insert>
```

# 参数
> 定义参数属性的时候不能换行！

## 参数配置
>特殊情况的参数处理
- 表明转换类型
`#{age, javaType=int, jdbcType=NUMERIC}`
- 指明使用哪个typeHandle
`#{age, javaType=NUMERIC, typeHandle=MyTypeHandle}`
- 设置保存精度
`#{price, javaType=double, jdbcType=NUMERIC, numericScale=2}`

## 存储过程的支持

## 特俗字符串替换和处理
>在MyBatis中我们常常传递字符串，我们设置的参数#{name}在大部分的情况下MyBatis会用预编译的语句，然后MyBatis为它设值，而有时候我们需要的是传递SQL语句本身，而不是SQL所需要的参数。
- 例如，在程序中传递变量columns="col1, col2, col3"给SQL。
```
select ${columns} from table
```

## sql元素
>定义一串sql语句的组成部分，其他的语句可以通过引用来使用它。

```
1.定义
<sql id="role_columns">
id, role_name, note
</sql>
2.使用
<select >
    select <include refid="role_columns">
</select>
```

# resultMap
>resultMap是MyBatis里面最复杂的元素。它的作用是定义映射规则、级联的更新、定义类型转化器等。

## resultMap元素构成

**constructor**
- 用于配置构造方法。

```
<resultMap id="" type="">
    <constructor>
        <idArg></idArg>//主键,多个则为联合主键
        <arg></arg>
    </constructor>
</resultMap>
```


## 级联
>在数据库中包含一对多、一对一的关系。
- association: 代表一对一关系， 比如中国公民和身份证是一对一的关系
- collection：代表一对多关系，比如班级和学生是一对多关系，一个班级可以有多个学生
- discriminator：鉴别器，根据实际选择采用那个类作为实例，允许你根据特定的条件去关联不同的结果集。

### 一对一级联
>以学生信息管理为例，一个学生拥有一张学生证，是一对一关系。
```
1.学生类：
public class Student {
    private int id;
    private String name;
    private String sex;
    private SelfCard selfCard;
    //省略get/set方法  
}
2.学生证类：
 public class SelfCard {
     private int id;
     private int studentId;
     private String note;
 }
```
- 我们希望在对学生进行查询的时候，同时也查询出该学生的学生证信息，并且注入到selfCard属性中去。

- **以下为配置实例**
```
1.学生证mapper

<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE mapper  
  PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"  
  "http://mybatis.org/dtd/mybatis-3-mapper.dtd"> 
<mapper namespace="day_8_mybatis.mapper.SelfCardMapper">
    <resultMap type="day_8_mybatis.pojo.SelfCard" id="studentSelfCardMap">
        <id property="id" column="id"/>
        <result property="studentId" column="student_id"/>
        <result property="note" column="note"/>
    </resultMap>
    <select id="findSelfCardByStudentId" parameterType="int" resultMap="studentSelfCardMap">
        select id, student_id, note from t_student_selfcard where student_id = #{id}
    </select>
</mapper>

2.学生mapper

<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE mapper  
  PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"  
  "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="day_8_mybatis.mapper.StudentMapper">
    <resultMap type="day_8_mybatis.pojo.Student" id="studentMap">
        <id property="id" column="id"/>
        <result property="name" column="name"/>
        <result property="sex" column="sex"/>
        <association property="selfCard" column="id" select="day_8_mybatis.mapper.SelfCardMapper.findSelfCardByStudentId"/>
    </resultMap>
    <select id="getStudent" parameterType="int" resultMap="studentMap">
        select id, name, sex from t_student where id = #{id}
    </select>
</mapper>
```
>通过association来级联学生证表，该标签的作用，通过column的值查找该学生的学生证信息，然后注入到学生证属性中去。

### 一对多级联
>一个学生需要上多门课，但是每门课只有一个成绩。涉及三张表：学生表、课程表、成绩表
- 类设计
```
1.学生类：
public class Student {
    private int id;
    private String name;
    private String sex;
    private SelfCard selfCard;
    private List<CourseScore> courseScoreList;     
}

2.成绩类：
public class CourseScore {
    private int id;
    private int studentId;
    private Course course;　　//在学生id确认的情况下，课程和成绩是一对一的关系。
    private String score;
    //省略set/get方法  
}

3.课程类
public class Course {
    private int id;
    private String courseName;
    private String note;
    //省略set/get方法
}
```

- mapper配置
主要配置student类中的courseScoreList集合。
```
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE mapper  
  PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"  
  "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="day_8_mybatis.mapper.StudentMapper">
    <resultMap type="day_8_mybatis.pojo.Student" id="studentMap">
        <id property="id" column="id"/>
        <result property="name" column="name"/>
        <result property="sex" column="sex"/>
        <association property="selfCard" column="id" select="day_8_mybatis.mapper.SelfCardMapper.findSelfCardByStudentId"/>
        <collection property="courseScoreList" column="id" select="day_8_mybatis.mapper.CourseScoreMapper.findCourseScoreByStudentId" />
        </resultMap>
    <select id="getStudent" parameterType="int" resultMap="studentMap">
        select id, name, sex from t_student where id = #{id}
    </select>
</mapper>
```
>和association一样，column指定参数，表明通过学生的id去获取学生所有的课程成绩信息，并将结果封装成List集合注入studen的courseScoreList属性中。

### 鉴别器

>鉴别器（discriminator）是MyBatis为我们提供的第三个级联也是最后一个。基于之前两篇级联中的场景，现增加学生们去体检，但男女体检项目不一样，我们把男女体检表做成两张表，当然我想也可以设计为一张表，只有女生的项目男生不填就行了，为了讲解鉴别器就把男女体检表分开。鉴别器的作用在这里就是根据性别的不同去不同的表里进行查询体检情况，例如是男生就在男生体检表里查询，是女生就在女生体检表里查询。

**配置实例**
```
1.两个继承学生类的不同性别类
public class MaleStudent extends Student {
    List<MaleStudentHealth> studentHealthList;
    //省略getter/setter方法
}

public class FemaleStudent extends Student{
    List<FemaleStudentHealth> studentHealthList;
    //省略getter/setter方法
}

2.不同性别的不同体检表对应的类
public class MaleStudentHealth {
    private int id;
    private int studentId;
    private String date;
    private String prostate;   //前列腺
    //省略setter/getter方法
}

public class FemaleStudentHealth {
    private int id;
    private int studentId;
    private String date;
    private String womb;
    //省略setter/getter方法
}

3.配置studentmapper
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE mapper  
  PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"  
  "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="day_8_mybatis.mapper.StudentMapper">
    <resultMap type="day_8_mybatis.pojo.Student" id="studentMap">
        <id property="id" column="id"/>
        <result property="name" column="name"/>
        <result property="sex" column="sex"/>
        <association property="selfCard" column="id" select="day_8_mybatis.mapper.SelfCardMapper.findSelfCardByStudentId"/>
        <collection property="courseScoreList" column="id" select="day_8_mybatis.mapper.CourseScoreMapper.findCourseScoreByStudentId" />
        <discriminator javaType="string" column="sex">
            <case value="男" resultMap="maleStudentMap"/>
            <case value="女" resultMap="femaleStudentMap"/>
        </discriminator>
    </resultMap>

    <select id="getStudent" parameterType="int" resultMap="studentMap">
        select id, name, sex from t_student where id = #{id}
    </select>
        
    <resultMap id="maleStudentMap" type="day_8_mybatis.pojo.MaleStudent" extends="studentMap">
        <collection property="studentHealthList" select="day_8_mybatis.mapper.MaleStudentHealthMapper.findMaleStudentHealthByStudentId" column="id" />
    </resultMap>
    
    <resultMap id="femaleStudentMap" type="day_8_mybatis.pojo.FemaleStudent" extends="studentMap">
        <collection property="studentHealthList" select="day_8_mybatis.mapper.FemaleStudentHealthMapper.findFemaleStudentHealthByStudentById" column="id" />
    </resultMap>
</mapper>
```
>`discriminator `标签里面的内容指的是，根据查找对象的性别，添加不容的返回属性，并给该返回类型。如：如果性别为男，那么添加一个返回属性为`studentHealthList`，并且返回结果的类型为`MaleStudent`

# 延迟加载
>级联的优势是能够方便快捷地获取数据。比如学生和学生成绩信息往往是最常用的关联信息，这个时候级联是完全有必要的。但是如果出现多层级联的时候情况会变得非常复杂，不利于他人的理解和维护。

## 延迟加载
>延迟加载的意义在于，一开始并不取出级联数据，只有当使用它了才发送SQL去取回数据。

### **配置延迟加载**
- 在MyBatis全局设置中有2个属性和加载方式有关。
```
1. 设置延迟加载
<setting name="lazyLoadingEnabled" value="true"/>
2.默认采用按层级延迟加载，当value为false时，采用按需加载
<setting name="aggressiveLazyLoading" value="true"/>
```

- **指定具体属性的加载方式**

```
1.在association和collection中通过设置fetchType，先设置aggressiveLazyLoading为false
fetchType="lazy"时，采用延迟加载。
fetchType="eager"时，采用即时加载。
```

### 延迟加载的实现原理