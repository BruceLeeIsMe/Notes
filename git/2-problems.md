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

   疑问？是清除的版本差异的工作区内容？还是我修改了代码未提交的工作区内容？还是两者一起清楚？

# merge request

多人协作的时，master分支为最稳定分支，我们没有直接向master分支提交代码的权限。于是需要向master发起merge request，由项目老大批准后方可merge。

情境：现有紧急bug需要修复，于是开启分支hotfix：

1. 将代码从 master 拉回到hotfix：git pull origin master

2. 对代码进行修改，提交

3. 进行发起merge request

   注意：在自己merge之前，可能已经有其他人向master提交了代码，所以此时直接发起merge request 到master，则可能会发生冲突，项目老大需要处理冲突才可以同意merge（让老大处理冲突，你怕是没睡醒）。

   解决：自己merge 之前，将master的代码pull到自己分支，自己处理完冲突再去网页发起merge request。这样老大看到了，review你代码没问题，同意merge。

4. merge request  流程完成

