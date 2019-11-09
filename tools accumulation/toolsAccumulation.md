# excel

## apach-poi

## ali-easyexcel



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