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



使.gitignore文件生效（文件没有被跟踪的情况下才有效）

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

# 分支操作：

创建分支：git branch branchName

切换分支：git checkout branchName

删除分支：git branch -d branchName

**远程相关：**

新建远程分支：git push origin branchName(将本地分支推送到远程)

切换到远程分支：git checkout -b branchName origin/branchName

删除远程分支：git push origin --delete branchName



# 分支合并

情境：在branch1上使用merge命令：

merge branch2：将分支2的内容合并到分支1



# 后悔药相关

清除暂存区内容到工作区：git reset HEAD

清除工作区与暂存区内容：git reset HEAD --hard



reset参数： --hard： 清楚暂存区与工作区内容



**版本回退：**

根据版本号回退：git reset versionNo

回退到上一个版本：git reset HEAD^

重置当前版本：git reset HEAD





# 冲突处理

**冲突出现的场景：**

1. 分支操作--merge的时候
2. git stash pop的时候
3. git pull 的时候（pull其实是fetch+merge）

处理冲突：

1. 发生冲突时，git会标识出冲突文件，并且会在文本文件中标记出冲突的两个版本之间的异同。

2. 在我们处理了内容的冲突之后，告诉git，我们冲突处理好啦，如何告诉呢？

   将修改的文件提交到本地仓库，git就知道我们处理好了，然后再进行合并，并让我们填写merge消息。

3. 冲突处理完成



# --------问题-----------

# pull相关问题：

**本质上还是merge的问题**

1. 远程删除了文件，但本地还有
2. xxx
3. xxx

# reset相关问题

1. git reset HEAD^：将本地仓库回退到上一个版本

   tips：上个版本与当前版本的差异，会保存在工作区。

   **例如**：HEAD-> version1：a.file B.file

   version2：a.file

   git reset HEAD^后，仓库版本回退到version2，并且工作区会留下B.file文件

2. git reset HEAD^ --hard

   本地仓库回退到上一个版本，切清除工作区内容。

**疑问？**

--hard是清除的版本差异的工作区内容？还是我修改了代码未提交的工作区内容？还是两者一起清楚？

解答：清楚所有工作区与暂存区内容

# merge request

多人协作的时，master分支为最稳定分支，我们没有直接向master分支提交代码的权限。于是需要向master发起merge request，由项目老大批准后方可merge。

情境：现有紧急bug需要修复，于是开启分支hotfix：

1. 将代码从 master 拉回到hotfix：git pull origin master

2. 对代码进行修改，提交

3. 进行发起merge request

   注意：在自己merge之前，可能已经有其他人向master提交了代码，所以此时直接发起merge request 到master，则可能会发生冲突，项目老大需要处理冲突才可以同意merge（让老大处理冲突，你怕是没睡醒）。

   解决：自己merge 之前，将master的代码pull到自己分支，自己处理完冲突再去网页发起merge request。这样老大看到了，review你代码没问题，同意merge。

4. merge request  流程完成

# ignore与取消跟踪

## 取消跟踪文件

**git rm  filename -r --cached**

​		使用git rm -r --cached filename命令后，该文件会以delete状态存储在暂存区，

### **疑问?**

可不可以做到我一个人取消跟踪某个文件，而不影响到其他人。

答;不可以，git本身就是分布式版本管理，每一个人的版本应该都是应该保持最新（相同）的。



**达到单独取消跟踪的尝试：**

实验：

1. git rm -r --cached file3.md，并且不提交到远程
2. 远程修改 file3.md
3. 本地pull代码

结果：

error: Your local changes to the following files would be overwritten by merge:
        file3.md
Please commit your changes or stash them before you merge.
Aborting
Updating 811c7f8..7e73467

**git在合并之前，必须先提交暂存区的内容，否则无法合并**

**结论：此方法不可行**



# 文件改动过大（换行符问题）

现象：代码中只改动了几行，但是git检测的结果是整个文件都改动了

原因：仓库中的代码与工作区代码的回车换行不一致所导致

windows 换行：\r\n

linux  换行: \n

mac 换行: \n

'\r'是回车，前者使光标到行首，（carriage return，CR）
'\n'是换行，后者使光标下移一格，（line feed，LF）

**解决命令：**

自动转换换行符：

- 提交时转换为LF，检出时转换为CRLF
  git config --global core.autocrlf true

- 提交时转换为LF，检出时不转换
  git config --global core.autocrlf input

- 提交检出均不转换
  git config --global core.autocrlf false

换行符检测：

- 提交包含混合换行符的文件时给出警告
  git config --global core.safecrlf warn

- 拒绝提交包含混合换行符的文件
  git config --global core.safecrlf true

- 允许提交包含混合换行符的文件
  git config --global core.safecrlf false











