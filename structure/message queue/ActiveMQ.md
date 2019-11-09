# 前导概念：

## 什么是中间件？

非操作系统软件，非业务应用软件，不是直接给最终用户使用，不能直接给用户带来价值的软件，我们就可以称为中间件（比如Dubbo，Tomcat，Jetty，Jboss都是属于的）。

## 什么是消息中间件？

百度百科解释：消息中间件利用高效可靠的消息传递机制进行平台无关的数据交流，并基于数据通信来进行分布式系统的集成。通过提供消息传递和消息排队模型，它可以在分布式环境下扩展进程间的通信。
关键点：关注于数据的发送和接受，利用高效可靠的异步消息机制传递机制集成分布式系统。![img1](.\img\img1.png)

### 使用消息中间件的优点

- 异步

  系统A给系统B进行通信，而系统B需要对A的消息进行相应处理之后才能给A反馈，可能造成系统A的线程因等待而阻塞，使用异步则可以立即返回，防止阻塞。

- 解耦

## 什么是JMS（系统消息交互接口）

**Java Message Service**

​		Java消息服务（Java Message Service）应用程序接口是一个Java平台中关于面向消息中间件（MOM）的API，用于在两个应用程序之间，或分布式系统中发送消息，进行异步通信。Java消息服务是一个与具体平台无关的API，绝大多数MOM提供商都对JMS提供支持。

总结起来说就是：Java对于应用程序之间进行信息交互的API（而且是异步）。

## 重要基本概念

- 消息服务提供者（服务端）：实现JMS的消息服务中间件服务器（运行中间件的server）
- 消息服务使用者（客户端）：发布消息与消费消息的终端。

- 队列模式 queue

- 主题模式 topic

- 生产者/ 发布者 producer/publisher

- 消费者/订阅者 consumer/subscriber

- 浏览者 browser

  在队列模式下，只查看消息，并不消费掉消息。

  `session.createBrowser(queue); `此api只支持队列模式，但是如果我想查看订阅模式的消息呢？
  好像不存在这个问题，因为订阅模式下，订阅者只能接收到订阅后发送的消息。


### 主要模式

- 队列模式

  对应生产者与消费者，一条消息只能被一个消费者消费，消费者可以随时消费。

- 主题模式

  对应订阅者与发布者，一条消息可被多个订阅者接受，发布者发布消息时，所有订阅者都会收到消息。
  新的订阅者无法接受到之前发布的消息。

# ActiveMQ简介
apache出品的，开源消息总线。完全支持JMS1.1和J2EE1.4规范的JMS Provider实现。
## 重要class和api：
* ConnectionFactory
    通过BrokerURL建立该对象

* Connection：
    通过factory.createConnectio（）获取connection；
    然后再connection.start（）；
    如果不call start方法， 则后续的任何消息传递操作都不会生效。
* Session：表示一个单线程的上下文，用于创建生产者消费者，发送和接收消息。
获取session = connection.createSession（TransactionFlag，acknowledgeMode）；
参数解析：
param1：是否开启事务，如果开启，操作之后需要session.commit（）；才会生效，并且启动事务后会忽略param2。
param2：ack模式：
Session.AUTO_ACKNOWLEDGE，自动确认，最常用的
Session.CLIENT_ACKNOWLEDGE，客户端需要call onMessagge 方法进行确认
Session.DUPS_OK_ACKNOWLEDGE，待查

* Destination：queue和topic的上层接口
    通过session进行创建 createQueue（queueName）
    createTopic（TopicName）；
    还可以创建零时queue和topic
    createTemporaryQueue（）；
    createTemporaryTopic（）；

* MessageConsumer
    通过session创建 session.createConsumer（）；
    api：message = consuer.recerive（）；该方法是无限期阻塞方法，如果取不到消息，将会一直等待。
    当然会有一个重载的限时方法，message = consuer.recerive（long timeout）；
    消息到达的时候或超时，该方法返回。

* MessageProducer
    通过session创建 session.createProducer（）；
    多个send方法

* Message ：在生产者与消费者之间传递的对象
    通过session创建，createMessage（）；

## 延迟消息队列

# web后台

localhost:8161
默认账号密码。acct： admin   pwd：admin