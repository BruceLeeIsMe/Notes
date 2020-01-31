# excel

## apach-poi
以二维数组的形式存储行列。

## ali-easyexcel
整个机制是通过监听器实现的，通过对每一个行进行监听，并对其进行处理。
缓存数据的方式：将每一行对各列映射为一个POJO的各属性。



# json

## ali-fastjson

Student student = StudentUtils.genStudent();
String jsonString = JSON.toJSONString(student);// 序列化
String jsonString1 = StudentUtils.STUDENT_STRING;
Student student1 = JSON.parseObject(jsonString1, Student.class);// 反序列化

##  Jackson

Student student = StudentUtils.genStudent();
ObjectMapper objectMapper = new ObjectMapper();
String jsonString = objectMapper.writeValueAsString(student); // 序列化
System.out.println(jsonString);

String jsonString1 = StudentUtils.STUDENT_STRING;
Student student1 = objectMapper.readValue(jsonString1, Student.class); // 反序列化
System.out.println(student1);

##  Gson

Student student = StudentUtils.genStudent();
Gson gson = new Gson();
String jsonString = gson.toJson(student); // 序列化
System.out.println(jsonString);

String jsonString1 = StudentUtils.STUDENT_STRING;
Student student1 = gson.fromJson(jsonString1, Student.class); // 反序列化
System.out.println(student1);