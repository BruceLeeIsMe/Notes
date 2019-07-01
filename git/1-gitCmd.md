# git 常用命令



**参考阮老师整理的部分命令：<http://www.ruanyifeng.com/blog/2015/12/git-cheat-sheet.html>**



## 新建代码库



在当前目录新建一个Git代码库

$ git init

新建一个目录，将其初始化为Git代码库

$ git init [project-name]

下载一个项目和它的整个代码历史，并且下载下来的分支自动与remote相关联

$ git clone [url]

## 配置



显示当前的Git配置*

$ git config --list



编辑Git配置文件

$ git config -e [--global]



 设置提交代码时的用户信息

$ git config [--global] user.name "[name]"

git config [--global] user.email "[email address]"



使.gitignore文件生效

$ git config core.excludeFile .gitignore



# 增加/删除文件（取消跟踪）

添加指定文件到暂存区
$ git add [file1] [file2] ...

添加指定目录到暂存区，包括子目录
$ git add [dir]

**添加当前目录的所有文件到暂存区**
**$ git add .**

添加每个变化前，都会要求确认
对于同一个文件的多处变化，可以实现分次提交
$ git add -p

删除工作区文件，并且将这次删除放入暂存区
$ git rm [file1] [file2] ...

停止追踪指定文件，但该文件会保留在工作区
$ git rm --cached [file]

改名文件，并且将这个改名放入暂存区
$ git mv [file-original] [file-renamed]



# 代码提交

提交暂存区到仓库区
$ git commit -m [message]

提交暂存区的指定文件到仓库区
$ git commit [file1] [file2] ... -m [message]

提交工作区自上次commit之后的变化，直接到仓库区
$ git commit -a

提交时显示所有diff信息
$ git commit -v

**使用一次新的commit，替代上一次提交**
**如果代码没有任何新变化，则用来改写上一次commit的提交信息**
**$ git commit --amend -m [message]**

**重做上一次commit，并包括指定文件的新变化**
**$ git commit --amend [file1] [file2] ...**







# 后悔药相关

# 分支操作