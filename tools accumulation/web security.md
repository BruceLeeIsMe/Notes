# 鉴权相关的基本概念

## 认证

向服务器证明你是你自己。

常见方式认证方式：

1. 账号密码
2. 短信（邮箱）验证码

## 授权

授予访问资源的权限。

实现授权的方式有：

- cookie-session
- token
- OAuth （第三方授权，比如个人网站接入QQ登陆，微信登陆等）



#  常见的前后端鉴权方式

1. Session-Cookie
2. Token 验证（包括 JWT，SSO）
3. OAuth2.0（开放授权）

# Session-Cookie

- session：调用api Servlet.getSeesion()时创建并保存在服务器端，服务器停止会服务器应用关闭时销毁，或者seesion有效期结束失效（tomcat默认有效期30分钟）。


- cookie：服务端创建，保存在客户端maxSize为4K的数据块，可设置过期时间。


流程：

session在服务端创建之后，在向浏览器response时，将sessionId保存在cookie中，以key：JSessionId存放。下次客户端请求时，带上此cookie，服务端通过jssesionId就可以识别会话，从而知道 `刚才访问的人是A，现在访问的也是A`  。

session也可以持久化：将其保存在redis中。

缺点：

1. 存在服务器集群时，存在cookie跨域问题
2. session存放在多态服务器上，无法统一管理，（可用redis统一保存）



# Token-SSO

## 构成：

是唯一的即可。长度与内容自定义。

## 流程：

- 发放token：用户认证通过根据自定义算法生成token，并将其保存在数据库中
- 验证token：获取请求token，查询数据库验证其正确性。







# Token-JWT

## 构成

jwt（json web token），是一种无状态的认证令牌。

JWT主要由三部分构成：

header：包含加密方式，类型信息

payload：主要包含用户的 信息负载

signature： 将header和payload的内容base64编码后，再进行加盐（秘钥），加密后得到签名（signature）

JWT最终组成：base64后的header + . +  base64后的payload  + . + 签名

## 流程：

- 发放token：用户认证通过，将用户必要信息（非敏感信息）存放在JWT的payload中，将header和payload的内容base64编码后，再进行加盐（秘钥），加密后得到签名（signature）
- 验证token：因为jwt特性，具体啥特性我也说不明白，服务端拿到token只需要验证token合法性，如果合法，则token中包含的信息则是正确无误的。（验证过程无需查询数据库）
- 续签token：？？ 大概知道一种  access token 与 refresh token实现的 续签token 的方式，后期深入了解后再来完善。

## 特点：

- 服务端无需保存用户信息，因为请求携带的jwt包含了必要的用户信息，客户端只需要验证该JWT是否合法（鉴权），只要合法，验证就通过。


- jwt一旦颁发就无法撤销，只能等到jwt的过期时间结束，jwt才会失效。
- jwt是无状态的



# OAuth（第三方授权）

