# 链表问题：

 

红标记为踩过的坑：

## 元素插入：

-   插入到头结点

p.next=head.next;     // 连接新节点与非头结点

head.next=p;             //连接新节点到头结点。

 

-   插入到尾节点

 

只能用while(p.next!=null) 定位到最后一个节点 再p.next=待插入节点;

 

while(p!=null)  定位到最后的p 为空，无法连接到链表上。

并且将带插入节点的next 置空,否则插入的是一条链表，而不是节点。

 

-   插入到目标节点

找到 目标节点的前一个节点 作为头结点，进行插入。

 

## 遍历链表：

- 遍历单条：

while(p!=null){                             //在最后一个节点的next停下来

​                   //操作数据….此处p为当前节点

​                   p=p.next;

}               //遍历结束 p为最后一个节点的next，是空值。     

 

 

while(p.next!=null){                    //在最后第一个节点停下来

​         //操作数据….此处p为当前节点

​              p=p.next;

}            //遍历结束  p为最后一个节点，

- 同时遍历多条：

​         while(p!=null||q!=null){

​                   if(p!=null) p=p.next;

​                   if(q!=null) q=q.next;

}

 

## 查找元素：

while(p.next!=null){

​          if(p==obj){

​              return p;

​			}

}

​                  

## 元素移除：

​         先找到该元素的前一个元素A，并记录地址值。

​         指针后移，指向目标元素的下一个C，将C连接到A上。

```java
while(p.next!=null){

    if(p==obj){

        tp=p;

        p=p.next.next;

        tp.next=p;

    }

}
```



