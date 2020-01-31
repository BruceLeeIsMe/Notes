# 内部排序

内部排序，即将内存上的数据排序

 

 

## 插入排序：

 

### 直接插入排序

性能分析：

 

时间复杂度： 

\1. 最好情况：O(n) 

\2. 平均情况：O(n^2) 

\3. 最坏情况：O(n^2) 

空间复杂度：O(1) 

稳定性：稳定(相同元素的相对位置不会改变)

 

适用场景：

\1.       当n <= 50时，适合适用直接插入排序和简单选择排序，如果元素包含的内容过大，就不适合直接插入排序，因为直接插入排序需要移动元素的次数比较多.

 

\2.       当数组基本有序的情况下适合使用直接插入排序和冒泡排序，它们在基本有序的情况下排序的时间复杂度接近O(n).

 

 

 

 

算法描述：

\1.       将数组的第一个数据 作为记录总数为1的初始数组

\2.       然后将剩余的数插入到初始数组的合适位置（大于前面且小于后面），完成排序。

 

 

实现第二步：如何插入到合适位置？

如果小于，就后移，如果小于，就后移，直到不满足条件

此时就合适的位置就出现了（即大于前面且小于后面的位置） 

//temp为当前关键字

​         while(p>=0&&para[p]>temp){     //将当前关键字与初始数组记录的逐一比较 如果

​                                     para[p+1]=para[p];                 //小于，则将被比较记录后移一次

​                                     --p;

​                            }

 

 

算法代码：

	public static void quickSort(Integer para[],int left,int right){
		
		if(left>=right) return;
		int key=para[left];
		int temp;
		int i=left,j=right;
		while(i<j){						
			while(i<j && para[j] > key){     		//哨兵j移动  哨兵j必须先动  只有J先动的时候 最后key归位 i,J指向的值 才小于key
	            j--;  
	        }  
			while( i<j && para[i] <= key) {  		//哨兵i移动
	            i++;  
	        }  
			if(i<j){								//i j停下，交换i j哨兵的关键字
				temp=para[j];					
				para[j]=para[i];
				para[i]=temp;
				System.out.println("交换");
				
			}		
		}
		System.out.println("key归位");				//key归位
		temp=para[left];
		para[left]=para[i];
		para[i]=temp;	
		quickSort(para,left,i-1);					//递归快速排序 剩下的两部分
		quickSort(para,i+1,right);	
		
	}
​                                                  

### 希尔排序

 

希尔排序：增强版的直接插入排序。

 

性能分析：

 

会根据增量的不同而不同，一般来说： 

时间复杂度： 

\1. 最好情况：O(n^1.3) 

\2. 最坏情况：O(n^2) 

空间复杂度：O(1) 

稳定性：不稳定(相同元素的相对位置会改变)

 

适用场景：

可以用于大型的数组，希尔排序比插入排序和选择排序要快的多，并且数组越大，优势越大。

 

 

 

算法描述：对数组进行 多次 步长逐次递减 的直接插入排序。

 

初始步长为length/2，再进行除2主次递减步长

最后一次步长为1



```JAVA
	// https://www.cnblogs.com/chengxiao/p/6104371.html
public static void shellSort(Integer para[]){			
	//分组 初始增量为 d1=length/2 二次增量d2=d1/2;
	int d=para.length/2;									//增量  即组内间距（步长）		
	int temp=0;
	while(d>1){												//按照增量分组
		for(int j=0;j<d;j++){								//遍历分组
			for(int i=j+d;i<para.length;i+=d){				//分组内插入排序,从分组内的第二个记录开始
				temp=para[i];
				int p=i-d;
				while(p>=j&&temp<para[p]){
					para[p+d]=para[p];
					p-=d;
				}
				para[p+d]=temp;								//插入到正确位置 
			}
		}
		
		d=d/2;												//逐渐缩小增量，最后一次排序增量为1， 即整体为一组 作为
	}
	
}
```
​    

 

 

## 快速排序：

快速排序：采用类似二分思想的排序方式

 

性能分析：

时间复杂度： 

\1. 最好情况：O(nlog2(n)) 

\2. 平均情况：O(nlog2(n)) 

\3. 最坏情况：O(n^2) 

空间复杂度：O(log2(n)) 

稳定性：不稳定(相同元素的相对位置会改变)

 

适用场景:

1：一般应用中，比其他排序快得多，适用于数组长度比较大的情况，对于小数组，快速排序比插入排序慢。

2：如果数组中有大量重复元素，则三向取样的快排比标准的快排效率高很多。

 

算法描述：

\1.       选取一个界限值 将数组一分为二，左边全比界限值小，右边全比界限值大

\2.       被分开的部分，递归执行步骤1。

		//快速排序的主要步骤：
		//折半思想（二分思想）， 以一个值为界限，讲数组分为两部分，
		//左边全小于界限值，右边全大于界限值
		//然后分别对每部分进行 递归快速排序（再切分，再排序） 
		
		//遇到的问题：执行操作时，需要判定是否满足操作的条件
		//1.比如释放资源之前，需要先判断资源是否为空
		//2.移动数组下标的时候，需要先判断下标是否会越界
		//3.使用变量或者对象时，需要判断变量是否赋予初值，对象是否为空
		//实现  左边全小于界限值，右边全大于界限值 功能的算法  
		//1.取数组第一个元素作为界限值，安排两个哨兵，进行巡逻
		//2.哨兵j从数组末端向左移动   哨兵i向数组右端移动
		//3.如果哨兵j遇到小于界限值的数，就停止移动 否则继续移动
		//4.如果哨兵i遇到了大于界限值的数，就停止移动 否则继续移动
		//5.如果两个哨兵都停止了移动，交换两个哨兵所在位置的数值，即让右边的数大于界限值，左边的数小于界限值。
		//6.最终哨兵相遇，再将界限值归位。
		
	public static void quickSort(Integer para[],int left,int right){
		
		if(left>=right) return;
		int key=para[left];
		int temp;
		int i=left,j=right;
		while(i<j){						
			while(i<j && para[j] > key){     		//哨兵j移动  哨兵j必须先动  只有J先动的时候 最后key归位 i,J指向的值 才小于key
	            j--;  
	        }  
			while( i<j && para[i] <= key) {  		//哨兵i移动
	            i++;  
	        }  
			if(i<j){								//i j停下，交换i j哨兵的关键字
				temp=para[j];					
				para[j]=para[i];
				para[i]=temp;
				System.out.println("交换");
				
			}		
		}
		System.out.println("key归位");				//key归位
		temp=para[left];
		para[left]=para[i];
		para[i]=temp;	
		quickSort(para,left,i-1);					//递归快速排序 剩下的两部分
		quickSort(para,i+1,right);	
		
	}
​    

 

 

## 归并排序：

性能分析：

适用场景：

 

## 基数排序：

性能分析：

适用场景：

 

# 外部排序

外部排序，即将外存上的数据排序，需要涉及多次内、外存之间的数据交换。

