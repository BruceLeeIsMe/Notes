# nginx（轻度使用）

## 功能：

1. 反向代理
2. 负载均衡
3. HTTP服务器（静态资源服务器）

## 静态资源服务器：

```
server {
  listen       80;  # 配置端口                    
  server_name  localhost;                                                
  root  /usr/xxx/yyy; #配置资源根目录
#访问localhost:80/ 时， 返回的是目录/usr/xxx/yyy下的文件和目录

  location / {
    index  index.html;
  }
}
```

## 反向代理

反向代理应该是Nginx做的最多的一件事了，什么是反向代理呢，以下是百度百科的说法：反向代理（Reverse Proxy）方式是指以代理服务器来接受internet上的连接请求，然后将请求转发给内部网络上的服务器，并将从服务器上得到的结果返回给internet上请求连接的客户端，此时代理服务器对外就表现为一个反向代理服务器。简单来说就是真实的服务器不能直接被外部网络访问，所以需要一台代理服务器，而代理服务器能被外部网络访问的同时又跟真实服务器在同一个网络环境，当然也可能是同一台服务器，端口不同而已。 下面贴上一段简单的实现反向代理的代码

```
server {  
  listen       80;                                                         
  server_name  localhost;                                               
  client_max_body_size 1024M;

  location / {
    proxy_pass http://localhost:8080;
    proxy_set_header Host $host:$server_port;
  }
}
```



## 负载均衡

负载均衡也是Nginx常用的一个功能，负载均衡其意思就是分摊到多个操作单元上进行执行，例如Web服务器、FTP服务器、企业关键应用服务器和其它关键任务服务器等，从而共同完成工作任务。简单而言就是当有2台或以上服务器时，根据规则随机的将请求分发到指定的服务器上处理，负载均衡配置一般都需要同时配置反向代理，通过反向代理跳转到负载均衡。而Nginx目前支持自带3种负载均衡策略，还有2种常用的第三方策略。



### RR（默认轮询）

每个请求按时间顺序逐一分配到不同的后端服务器，如果后端服务器down掉，能自动剔除。 简单配置

```
upstream test {
  server localhost:8080;
  server localhost:8081;
}
server {
  listen       81;                                                         
  server_name  localhost;                                               
  client_max_body_size 1024M;

  location / {
    proxy_pass http://test;
    proxy_set_header Host $host:$server_port;
  }
}
```

### 权重

指定轮询几率，weight和访问比率成正比，用于后端服务器性能不均的情况。 例如

```
  upstream test {
    server localhost:8080 weight=9;
    server localhost:8081 weight=1;
  }
复制代码
```

那么10次一般只会有1次会访问到8081，而有9次会访问到8080

### ip_hash

上面的2种方式都有一个问题，那就是下一个请求来的时候请求可能分发到另外一个服务器，当我们的程序不是无状态的时候（采用了session保存数据），这时候就有一个很大的很问题了，比如把登录信息保存到了session中，那么跳转到另外一台服务器的时候就需要重新登录了，所以很多时候我们需要一个客户只访问一个服务器，那么就需要用iphash了，iphash的每个请求按访问ip的hash结果分配，这样每个访客固定访问一个后端服务器，可以解决session的问题。

```
upstream test {
  ip_hash;
  server localhost:8080;
  server localhost:8081;
}
```


