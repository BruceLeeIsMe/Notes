# 如何使用.gitignore

## gitignore有什么用

可以用来忽略提交一些文件，在.gitignore中配置忽略过的文件，无法add到缓存区![ignore force](.\img\ignore force.png)

## 如何使用gitigonre

1》首先在.git同级目录下创建文件 .gitignore 

2》编写配置文件

/ (斜杠)代表以该仓库根目录开始，即代表的是与.git文件同级的目录

3》让该配置生效：git config core.excludeFile .gitignore

# 可能遇到的问题

## 正确配置流程下，ignore不生效问题

原因：如果在配置ignore文件之前，该文件已经被track了，ignore不会对该文件生效

解决方案：取消该文件的track状态，git rm -r --cached filename

结果：ignore重新生效

tips：使用git rm -r --cached filename命令后，该filename会进入到stage区，commit后，该file会在远程仓库中删除。

## 文件已经ignore了，但是强制提交 

我的预期猜想（错误）：本次强制提交到仓库，以后该文件会继续被ignore

真实结果：ignore失效，以后该文件可以被add到stage区

并且gitignore配置文件中，对该文件的配置依然保存在当中

