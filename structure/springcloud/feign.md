

# 问题

feign在将请求转发的时候，必须写上注解RequestParam，否则转发时不会带上参数

```java
@FeignClient(value = "bbb-user-service",fallback = UserServiceHystrix.class)
public interface MUserService {

    @RequestMapping("/user")
    // 如果不写requestparam ，参数无法传递
    User getUserByUid(@RequestParam(required = true) Integer uid);
}

```