# pull相关问题：

# reset相关问题

1> 

git reset HEAD^：将本地仓库回退到上一个版本

tips：上个版本与当前版本的差异，会保存在工作区。

**例如**：HEAD-> version1：a.file B.file

version2：a.file

git reset HEAD^后，仓库版本回退到version2，并且工作区会留下B.file文件

