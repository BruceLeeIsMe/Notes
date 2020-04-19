

# **命令帮助**

- `command --help`
- `man command`
- `info command`
- 列出命令的简短使用信息(当使用`whatis`报错时，需要运行`mandb`命令生成索引文件) `whatis command`

# **日常使用命令** 

- 显示或者更改日期 `date`
- 显示日历 `cal`
- 统计文本行数或字符数以及其他相关信息 `wc`
- 找出命令的绝对路径 `which`
- 列出最近使用过的number条命令(rehl下默认保存1000条) `history number`
- 默认显示文本前10行内容，如需要显示更多行可以加减number实现 `head [+- number]`
- 默认显示文本后10行内容, 如需显示更多可以加减number `tail [+- number]`
- 自上而下显示文本内容 `cat`
- 自下而上显示文本内容 `tac`
- 切换工作路径 `cd`
- 显示目录内容 `ls`
- 复制文件或目录，复制目录时，加上`-r`选项表示递归复制 `cp`
- 重命名/移动文件或者目录 `mv`
- 删除文件或目录，删除目录时，加上`-r`选项表示递归,加上`-f`选项表示强制删除并且不提醒 `rm`
- 创建目录,递归创建加上`-p`选项  `mkdir`
- 创建空文件，或者更新时间戳 `touch`
- 列出目录树 `tree`
- 文件校验 `sha1sum    sha224sum  sha256sum  sha384sum  sha512sum`
- 校验文件`md5`的值 `md5sum`
- 逐屏浏览文本内容 `less`
- `ps -ef|grep content` 搜索进程
- `kill -9 processId`：-9强制杀掉某个进程

- `netstat -lntup`：查看端口

# **用户, 组以及权限相关** 

- 打印用户身份信息 `id`
- 更改`user`用户的密码 `passwd user`
- 添加用户 `useradd`
- 更改已添加用户的相关信息(uid, gid以及groups) `usermod`
- 删除用户 `userdel`
- 添加组 `groupadd`
- 删除组 `groupdel`
- 更改用户权限和组以及id等 `change`
- 同时更改file文件的所属用户及属组为student `chown student.student file`
- 更改文件的所属组 `chgrp`
- 更改文件权限 `chmod`
- 掩码方式更改 `umask`

# **解压缩相关** 

- tar
  - `c` 创建
  - `t` 列出
  - `x` 解压
  - `f` 文件名称
  - `C` 解压到指定目录
  - `z` 采用`gzip`压缩
  - `j` 采用`bzip2`压缩
  - `J` 采用`xz`进行压缩
- 打包 `tar cvf filename.tar /path`
- 打包并压缩成gzip格式 `tar czvf filename.tar.gz /path`
- 解压到指定文件夹 `tar xvf filename.tar /path`
- 查看压缩包内容但不解压 `tar tvf filename.tar`

#  **系统基础相关** 

- 使用root用户的环境变量切换到root用户 `su -`
- 显示当前工作路径 `pwd`
- 显示当前系统默认语言及键盘布局 `localectl`
- 显示系统中能支持的所有语言 `localectl list-locales`
- 配置系统默认语言为中文 `localectl set-locale LANG=zh_CN.gb2312`
- 重启机器 `reboot`
- 关机 `poweroff`
- 退出当前的shell  `logout/exit`

# **软件包管理相关**

 `yum`常用命令 

```
yum install a b c d    #安装软件包a b c d    (加上-y选项，可以在安装软件包时，不弹出是否继续的提示)
yum remove a b c d    #卸载软件包a b c d
yum groups list    #查看已安装的软件组和可用的软件组
yum groups  install "Infiniband Support"    #安装软件组
yum groups remove "Infiniband Support"    #卸载软件组
yum info a b c    #查看软件包a b c d的相关信息，如大小，版本等...
yum update a b c d    #更新软件包a b c d
yum update    #整体更新所有可更新的软件包
yum provides 文件或目录        #查看文件由哪个rpm包提供的
yum search tree        #从仓库中搜索关键词为tree的包
yum history        #查看yum运行历史记录
```



`rpm` 常用命令

```
yum install a b c d    #安装软件包a b c d    (加上-y选项，可以在安装软件包时，不弹出是否继续的提示)
yum remove a b c d    #卸载软件包a b c d
yum groups list    #查看已安装的软件组和可用的软件组
yum groups  install "Infiniband Support"    #安装软件组
yum groups remove "Infiniband Support"    #卸载软件组
yum info a b c    #查看软件包a b c d的相关信息，如大小，版本等...
yum update a b c d    #更新软件包a b c d
yum update    #整体更新所有可更新的软件包
yum provides 文件或目录        #查看文件由哪个rpm包提供的
yum search tree        #从仓库中搜索关键词为tree的包
yum history        #查看yum运行历史记录
```



# **文件搜索** 

- 搜索前, 先执行`updatedb`建立索引数据库然后再执行 `locate filename`

  ```
  find搜索
  find / -name ccie    #从/分区遍历所有子目录，然后根据文件名称查找
  find / -type d -name ccie    #从/分区遍历所有子目录，然后只查找名为ccie的目录
  find / -size 10M        #从/分区遍历所有子目录，然后查找大小为差不多10M的文件
  find / -perm 0755    #从/分区遍历所有子目录，然后查找权限为0755的文件
  find / -user student    #从/分区遍历所有子目录，然后查找student用户的文件
  ```


作者：猫xian森链接：https://juejin.im/post/58cd11915c497d0057bf3829来源：掘金著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。



# **文件系统相关** 

- 设备文件命名规则

  ```
  Linux下的设备文件命名规则
  /dev/sda        #第一块串口硬盘
  /dev/hda        #第一块并口硬盘
  /dev/vda        #基于KVM下的virtio驱动的第一块虚拟化磁盘
  /dev/xvda    #基于Xen虚拟化技术的虚拟磁盘
  /dev/cdrom    #CD/DVD设备，该文件通常链接到/dev/sr0，也就是第一个CD/DVD设备，第二个光驱设备，则是/dev/sr1，以此类推
  /dev/vgname/lvname    #逻辑卷磁盘
  /dev/sda1        #第一块串口硬盘的第一个分区
  /dev/hda1    #第一块并口硬盘的第一个分区
  备注: 当Linux下的磁盘超过24个时，比如从/dev/sda>/dev/sdz，那么则多余的磁盘会继续以/dev/sdaa,/dev/sdab排列
  df    #显示文件系统使用情况
  du    #统计文件大小
  mount    #挂载分区至某个目录，或者显示挂载情况
  ```

# **服务与进程相关** 

- 在centos7 中使用 systemctl 进行管理

  ```
  systemctl    -t help    #列出所有的单元类型
  systemctl --type "unit"    #查看指定单元类型的状况
  systemctl --failed        #查看所有加载失败的单元信息
  systemctl status cups.service    #查看cups服务单元状况
  systemctl start cups.service    #启动cups服务单元
  systemctl stop cups.service    #停止cups服务单元
  systemctl restart cups.service    #重启cups服务单元
  systemctl enable cups.service    #配置cups服务单元开机自动启动
  systemctl disable cups.service    #配置cups服务单元开机不启动
  systemctl reload cups.service    #重新加载cups服务单元的配置文件
  systemctl is-active cups.service    #查看cups服务单元当前是否运行
  systemctl is-enabled cups.service    #查看cups服务单元开机是否自动运行
  systemctl mask NetworkManager.service        #彻底屏蔽NM服务单元
  systemctl unmask NetworkManager.service    #取消屏蔽NM服务单元
  ```

