

# regex in java



```java
String regex="";
String content=""
Patter pattern = Pattern.compile(regex);
Matcher matcher= pattern.matcher(content);
boolean isFound=matcher.find();
String result=matcher.group();
```

