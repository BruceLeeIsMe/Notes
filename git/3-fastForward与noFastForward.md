# 合并时采取的模式：

## 默认的fastForward模式

快进模式，即将HEAD指针移动的模式。merge时将指针后移，**不生成**一次commit。

**如果删除分支，会导致该分支的提交记录消失**

## --no-ff模式（此次禁用fastForward）

合并时候**生成**一次commit

**删除merge的分支，不会导致提交记录消失**

## --squash模式

例如：在master分支merge其他分支如dev分支，此时dev上有五次提交记录，使用--squash模式合并，会将该五次提交记录合并为一个记录。![merge模式区别](H:\GitRepository\ProblemsNotes\git\img\merge模式区别.png)

