# 配置国内yum源（阿里）：

1) 安装wget

```
yum install -y wget
```

2) 备份/etc/yum.repos.d/CentOS-Base.repo文件

```
cd /etc/yum.repos.d/
mv CentOS-Base.repo CentOS-Base.repo.back
```

3) 下载阿里云的Centos-7.repo文件（下载对应的系统版本repo文件）

```
wget -O /etc/yum.repos.d/CentOS-Base.repo http://mirrors.aliyun.com/repo/Centos-7.repo
```

4) 重新加载yum

```
yum clean all
yum makecache
```





# 开启FTP(file transfor protel)



## CentOS 7 安装vsftpd 服务器

https://www.cnblogs.com/Dylansuns/p/7294343.html



安装服务：

```
yum -y install vsftpd
```



##  vsftp不允许root用户登录的，默认情况下。

可以通过修改限制来解决这个问题。 

首先找到vsftp的配置目录
cd /etc/vsftpd
chroot_list  ftpusers  user_list  vsftpd.conf  vsftpd_conf_migrate.sh
删除ftpusers、user_list中的root用户
最后重启vsftpd
service vsftpd restart
最好设置为开机自启动
chkconfig vsftpd on

## Linux 实例搭建 FTP 服务

https://cloud.tencent.com/document/product/213/10912



## **引用：**

------

FTP协议有两种工作方式：PORT方式和PASV方式，中文意思为主动式和被动式。 Port模式：ftp server:tcp 21 <------client:dynamic ftp server:tcp 20 ------>client:dynamic Pasv模式：ftp server:tcp 21 <----client:dynamic ftp server:tcp dynamic <----client:dynamic PORT（主动）方式的连接过程是：客户端向服务器的FTP端口（默认是21）发送连接请求，服务器接受连接，建立一条命令链路。当需要传送数据时，客户 端在命令链路上用PORT命令告诉服务器：“我打开了XXXX端口，你过来连接我”。于是服务器从20端口向客户端的XXXX端口发送连接请求，建立一条 数据链路来传送数据。 PASV（被动）方式的连接过程是：客户端向服务器的FTP端口（默认是21）发送连接请求，服务器接受连接，建立一条命令链路。当需要传送数据时，服务 器在命令链路上用PASV命令告诉客户端：“我打开了XXXX端口，你过来连接我”。于是客户端向服务器的XXXX端口发送连接请求，建立一条数据链路来 传送数据。



# 安装软件

## 三种安装方式（我知道的）

 一种是提供提供源代码 然后进行 make  ，  make install

另一种是提供rpm结尾的安装包， 使用命令 rpm -ivh `***`进行安装

使用yum命令安装

## yum 命令安装目录

默认安装在 /usr/share/local目录下

## 目录规范

用户软件一般安装在 /usr/local/下

如 /usr/local/jdk

软件源码或安装包放在 /usr/local/src下

如/usr/local/src/jdk

## 软件依赖问题



# 安全问题

一定要使用防火墙

任何软件开放了端口必须设置用户和密码

血的教学：redis bind 0.0.0.0（未设置账号密码） 导致被挖矿病毒感染

# 遇到病毒怎么办

## 特征： 

1. 一般中毒特征会导致CPU和网络带宽资源被占满。

2. 很多病毒都会劫持掉系统命令： 比如 ls ps 等，使用这些劫持掉的命令就会启动病毒程序



## 解决

排查起来需要专业水平，搞不来 只得重装系统 233333

