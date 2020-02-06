# 什么是springcloud？

springcloud，是一套完整的微服务解决方案，本质上是一个框架容器，他的作用是整合其他框架到一起，其目的是用于提升开发效率。

**与springboot的关系？**

 	SpringCloud是基于SpringBoot ，本质上就是springcloud将springboot整合为自己的组件，将其纳入自己的容器中。

# 架构模式

1、单体架构：
一个项目包含了所有的应用程序，这种是最传统的架构风格。
2、SOA 架构：
面向服务的架构，SpringBoot 系列中集成Dubbo 就是典型的SOA架构，controller 和 service 层为独立的两个工程。
3、微服务架构：
简单理解为一个完整的系统由很多单体系统组成。可以围绕项目的业务来划分，每一种业务划分为一个服务，拥有自己的controller、service、dao，独立开发，独立运行。这样的一组微服务共同构建整个系统。
————————————————
版权声明：本文为CSDN博主「cp026la」的原创文章，遵循 CC 4.0 BY-SA 版权协议，转载请附上原文出处链接及本声明。
原文链接：https://blog.csdn.net/cp026la/article/details/86551468