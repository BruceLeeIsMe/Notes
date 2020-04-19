# 记录自己刷过的leetcode



#### [94. 二叉树的中序遍历](https://leetcode-cn.com/problems/binary-tree-inorder-traversal/)

给定一个二叉树，返回它的中序 遍历。

示例:

输入: [1,null,2,3]
   1
    \
     2
    /
   3

输出: [1,3,2]
进阶: 递归算法很简单，你可以通过迭代算法完成吗？

```
/**

 * Definition for a binary tree node.
 * public class TreeNode {
 * int val;
 * TreeNode left;
 * TreeNode right;
 * TreeNode(int x) { val = x; }
 * }
   */
   class Solution {
   List<Integer> list=new ArrayList<Integer>();
   public List<Integer> inorderTraversal(TreeNode root) {
       traversal(root);
       return list;
   }
   public void traversal(TreeNode root){
       if(root==null) return;
       if(root.left!=null){
           traversal(root.left);
       }
       list.add(root.val);
       if(root.right!=null){
           traversal(root.right);
       }
       
   }
   //     public void traversal(TreeNode root){
   //        // 中序遍历
   //        //  先遍历左
   //        //  再根
   //        //  再右
   //         if(root==null)return;
   //         if(root.left!=null)
   //             traversal(root.left);
   //         if(root!=null) list.add(root.val);
   //         if(root.right!=null)
   //             traversal(root.right);
           

//     }
}
```



#### [169. 多数元素](https://leetcode-cn.com/problems/majority-element/)

给定一个大小为 n 的数组，找到其中的多数元素。多数元素是指在数组中出现次数大于 ⌊ n/2 ⌋ 的元素。

你可以假设数组是非空的，并且给定的数组总是存在多数元素。

 

示例 1:

输入: [3,2,3]
输出: 3
示例 2:

输入: [2,2,1,1,1,2,2]
输出: 2



```
import java.util.Map;
import java.util.HashMap;
class Solution {
    public int majorityElement(int[] nums) {
        if(nums.length==1) return nums[0];
        Map<Integer,Integer> map=new HashMap<Integer,Integer>();
        
        for(int i=0;i<nums.length;i++){
            if(map.containsKey(nums[i])){
                Integer t=map.get(nums[i]);
                map.put(nums[i],++t);
            }else{
                map.put(nums[i],1);
            }
        }
        int res=0;
        for(Integer t:map.keySet()){
            if(map.get(t)>nums.length/2){
                res=t;
            }
        }
        return res;
    }
}
```



#### [70. 爬楼梯](https://leetcode-cn.com/problems/climbing-stairs/)

假设你正在爬楼梯。需要 n 阶你才能到达楼顶。

每次你可以爬 1 或 2 个台阶。你有多少种不同的方法可以爬到楼顶呢？

注意：给定 n 是一个正整数。

示例 1：

输入： 2
输出： 2
解释： 有两种方法可以爬到楼顶。

1.  1 阶 + 1 阶
2.  2 阶
示例 2：

输入： 3
输出： 3
解释： 有三种方法可以爬到楼顶。
1.  1 阶 + 1 阶 + 1 阶
2.  1 阶 + 2 阶
3.  2 阶 + 1 阶

```
class Solution {
    public int climbStairs(int n) {
        if(n==1) return 1;
        if(n==2) return 2;
        int firstNum=1,secondNum=2,result=0;
        for(int i=3;i<=n;i++){
            result=firstNum+secondNum;
            firstNum=secondNum;
            secondNum=result;
        }
        return result;
        

    }

}
```



#### [171. Excel表列序号](https://leetcode-cn.com/problems/excel-sheet-column-number/)

难度简单127收藏分享切换为英文关注反馈

给定一个Excel表格中的列名称，返回其相应的列序号。

例如，

```
    A -> 1
    B -> 2
    C -> 3
    ...
    Z -> 26
    AA -> 27
    AB -> 28 
    ...
```

**示例 1:**

```
输入: "A"
输出: 1
```

**示例 2:**

```
输入: "AB"
输出: 28
```

**示例 3:**

```
输入: "ZY"
输出: 701
```

```
class Solution {
    public int titleToNumber(String s) {
        if(s==null) return 0;
        int res=0;
        for(int i=0;i<s.length();i++){
            res=res*26+(int)s.charAt(i)-'@';
        }
        return res;
    }
}
```





#### [104. 二叉树的最大深度](https://leetcode-cn.com/problems/maximum-depth-of-binary-tree/)

难度简单494收藏分享切换为英文关注反馈

给定一个二叉树，找出其最大深度。

二叉树的深度为根节点到最远叶子节点的最长路径上的节点数。

**说明:** 叶子节点是指没有子节点的节点。

**示例：**
给定二叉树 `[3,9,20,null,null,15,7]`，

```
    3
   / \
  9  20
    /  \
   15   7
```

返回它的最大深度 3 。

```
/**

Definition for a binary tree node.

public class TreeNode {

int val;

TreeNode left;

TreeNode right;

TreeNode(int x) { val = x; }

}
*/
class Solution {
    public int maxDepth(TreeNode root) {
        if(root==null) return 0;
        int left=maxDepth(root.left);
        int right=maxDepth(root.right);
        return left>right?left+1:right+1;
    }
}
```



#### [136. 只出现一次的数字](https://leetcode-cn.com/problems/single-number/)

难度简单1143收藏分享切换为英文关注反馈

给定一个**非空**整数数组，除了某个元素只出现一次以外，其余每个元素均出现两次。找出那个只出现了一次的元素。

**说明：**

你的算法应该具有线性时间复杂度。 你可以不使用额外空间来实现吗？

**示例 1:**

```
输入: [2,2,1]
输出: 1
```

**示例 2:**

```
输入: [4,1,2,1,2]
输出: 4
```



```
class Solution {
    public int singleNumber(int[] nums) {
        

        // 异或的性质：
        //     3、对于任何数x，都有x^x=0，x^0=x
        //     4、自反性 A XOR B XOR B = A xor  0 = A
        int num=0;
        for(int i=0;i<nums.length;i++){
            num^=nums[i];
        }
        return num;
    }

}
```





#### [66. 加一](https://leetcode-cn.com/problems/plus-one/)

难度简单453收藏分享切换为英文关注反馈

给定一个由**整数**组成的**非空**数组所表示的非负整数，在该数的基础上加一。

最高位数字存放在数组的首位， 数组中每个元素只存储**单个**数字。

你可以假设除了整数 0 之外，这个整数不会以零开头。

**示例 1:**

```
输入: [1,2,3]
输出: [1,2,4]
解释: 输入数组表示数字 123。
```

**示例 2:**

```
输入: [4,3,2,1]
输出: [4,3,2,2]
解释: 输入数组表示数字 4321。
```

```
class Solution {
    public int[] plusOne(int[] a) {
        int corr=0;
        int tempRes;
        for(int i=a.length-1;i>=0;i--){
            if(i==a.length-1){
                tempRes=a[i]+1;
                if(tempRes>=10){
                    corr=1;
                    a[i]=tempRes%10;
                }else{
                     a[i]=tempRes;
                     corr=0;
                }
            }else{
                tempRes=a[i]+corr;
                if(tempRes>=10){
                     corr=1;
                     a[i]=tempRes%10;
                }else{
                    a[i]=tempRes;
                    corr=0;
                }
            }
        }
        if(corr==1){
            int r[]=new int[a.length+1];
            for(int i=1;i<r.length-1;i++){
                r[i]=a[i];
            }
            r[0]=1;
            return r;
        }else{
           return a; 
        }
        
        

        // StringBuilder sb=new StringBuilder("");
        //  for(int i=0;i<digits.length;i++){
        //      sb.append(Integer.toString(digits[i]));
        //  }  
        // String str=sb.toString();
        // int res=Integer.parseInt(str)+1;
        
    }

}
```



#### [24. 两两交换链表中的节点](https://leetcode-cn.com/problems/swap-nodes-in-pairs/)

难度中等456收藏分享切换为英文关注反馈

给定一个链表，两两交换其中相邻的节点，并返回交换后的链表。

**你不能只是单纯的改变节点内部的值**，而是需要实际的进行节点交换。

 

**示例:**

```
给定 1->2->3->4, 你应该返回 2->1->4->3.
```

```
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    public ListNode swapPairs(ListNode head) {
        ListNode pHead=new ListNode(0);
         pHead.next=head;
        //ListNode pHead=head;
        
        ListNode p=pHead;
        ListNode node1=null,node2=null;
        while(p!=null){
			try{
				node1=p.next;
				node2=p.next.next;
				//交换
				
				//System.out.println("node1:"+node1.val);
				//System.out.println("node2:"+node2.val);
				
				//1->3
				node1.next=node2.next;
				//2->1
				node2.next=node1;
				//p=head->2
				p.next=node2;
				
				//move ptr twice
				p=p.next.next;
					
            }catch(Exception e){
                break;
            }
            //如果是奇数，则可能 空指针异常 异常就退出交换结束
        }
        return pHead.next;
//         

        
    }
}
```



#### [6. Z 字形变换](https://leetcode-cn.com/problems/zigzag-conversion/)

难度中等627收藏分享切换为英文关注反馈

将一个给定字符串根据给定的行数，以从上往下、从左到右进行 Z 字形排列。

比如输入字符串为 `"LEETCODEISHIRING"` 行数为 3 时，排列如下：

```
L   C   I   R
E T O E S I I G
E   D   H   N
```

之后，你的输出需要从左往右逐行读取，产生出一个新的字符串，比如：`"LCIRETOESIIGEDHN"`。

请你实现这个将字符串进行指定行数变换的函数：

```
string convert(string s, int numRows);
```

**示例 1:**

```
输入: s = "LEETCODEISHIRING", numRows = 3
输出: "LCIRETOESIIGEDHN"
```

**示例 2:**

```
输入: s = "LEETCODEISHIRING", numRows = 4
输出: "LDREOEIIECIHNTSG"
解释:

L     D     R
E   O E   I I
E C   I H   N
T     S     G
```



```
class Solution {
    public String convert(String s, int numRows) {
        if(numRows==1) return s;
        StringBuilder p[]=new StringBuilder[numRows];
        for(int i=0;i<numRows;i++){
			p[i]=new StringBuilder("");
		}
        int t=0;//0kaishi
        boolean dir=true;
        for(int i=0;i<s.length();i++){
            p[t].append(s.charAt(i));
            if(dir){
                t++;
            }else{
                t--;
            }
            if(t>numRows-1){// 0kaishi  
                t=numRows-2;//0kaishi
                dir=!dir;
            }
            if(t<0){//0kaishi
                t=1;//0kaishi
                dir=!dir;
            }
        }
        int j=1;
        while(j<numRows){
            p[0].append(p[j].toString());
            j++;
        }
        return p[0].toString();
    }
}
```



#### [11. 盛最多水的容器](https://leetcode-cn.com/problems/container-with-most-water/)

难度中等1259收藏分享切换为英文关注反馈

给你 *n* 个非负整数 *a*1，*a*2，...，*a*n，每个数代表坐标中的一个点 (*i*, *ai*) 。在坐标内画 *n* 条垂直线，垂直线 *i* 的两个端点分别为 (*i*, *ai*) 和 (*i*, 0)。找出其中的两条线，使得它们与 *x* 轴共同构成的容器可以容纳最多的水。

**说明：**你不能倾斜容器，且 *n* 的值至少为 2。

 

![img](https://aliyun-lc-upload.oss-cn-hangzhou.aliyuncs.com/aliyun-lc-upload/uploads/2018/07/25/question_11.jpg)

图中垂直线代表输入数组 [1,8,6,2,5,4,8,3,7]。在此情况下，容器能够容纳水（表示为蓝色部分）的最大值为 49。

 

**示例：**

```
输入：[1,8,6,2,5,4,8,3,7]
输出：49
```



```
class Solution {
    public int maxArea(int[] height) {
        int len=0;
        int hei=0;
        int maxV=0;
        int res=0;
        for(int i=0;i<height.length;i++){
            for(int j=i+1;j<height.length;j++){
                if(height[i]<height[j]){
                    hei=height[i];
                }else{
                    hei=height[j];
                }
                len=j-i;
                res=hei*len;
                if(res>maxV){
                    maxV=res;
                }
            }
        }
        return maxV;
    }
}
```



#### [125. 验证回文串](https://leetcode-cn.com/problems/valid-palindrome/)

难度简单176收藏分享切换为英文关注反馈

给定一个字符串，验证它是否是回文串，只考虑字母和数字字符，可以忽略字母的大小写。

**说明：**本题中，我们将空字符串定义为有效的回文串。

**示例 1:**

```
输入: "A man, a plan, a canal: Panama"
输出: true
```

**示例 2:**

```
输入: "race a car"
输出: false
```

```
class Solution {
    public boolean isPalindrome(String s) {
        if(s.length()==0) return true;
        int i=0,j=s.length()-1;
        char left,right;
        while(i<j){
            left=s.charAt(i);
            right=s.charAt(j);
            if(left>='A'&&left<='Z'){
                left=(char)(left+32);
            }
            if(right>='A'&&right<='Z'){
                right=(char)(right+32);
            }
            while(i<=s.length()-2&&!Character.isDigit(left)&&(left>'z'||left<'a')){
                i++;
                left=s.charAt(i);
                if(left>='A'&&left<='Z'){
                    left=(char)(left+32);
                  }
            
           
            }
             while(j>0&&!Character.isDigit(right)&&(right>'z'||right<'a')){
               j--;
               right=s.charAt(j);
               if(right>='A'&&right<='Z'){
                   right=(char)(right+32);
                  }

            }
            if(i>=j) return true;
            if(left!=right) return false;
            i++;
            j--;
        }
        return true;
       
    }
}
```



#### [88. 合并两个有序数组](https://leetcode-cn.com/problems/merge-sorted-array/)

难度简单463收藏分享切换为英文关注反馈

给你两个有序整数数组 *nums1* 和 *nums2*，请你将 *nums2* 合并到 *nums1* 中*，*使 *num1* 成为一个有序数组。

 

**说明:**

- 初始化 *nums1* 和 *nums2* 的元素数量分别为 *m* 和 *n* 。
- 你可以假设 *nums1* 有足够的空间（空间大小大于或等于 *m + n*）来保存 *nums2* 中的元素。

 

**示例:**

```
输入:
nums1 = [1,2,3,0,0,0], m = 3
nums2 = [2,5,6],       n = 3

输出: [1,2,2,3,5,6]
```

```
class Solution {
    public void merge(int[] a, int m, int[] b, int n) {
        int q=0;
        int total=0;
        int j=0;
        int i=m+total-1;
        while(j<b.length){
            while(i>=0&&b[j]<a[i]){
                a[i+1]=a[i];
                i--;
            }   //找位置停下
            a[i+1]=b[j];//charu
            total++;
            j++;
            i=m+total-1;
        }
        
        
            
    }
}

```



#### [83. 删除排序链表中的重复元素](https://leetcode-cn.com/problems/remove-duplicates-from-sorted-list/)

难度简单282收藏分享切换为英文关注反馈

给定一个排序链表，删除所有重复的元素，使得每个元素只出现一次。

**示例 1:**

```
输入: 1->1->2
输出: 1->2
```

**示例 2:**

```
输入: 1->1->2->3->3
输出: 1->2->3
```

```
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    public ListNode deleteDuplicates(ListNode head){
        if(head==null) return null;
        ListNode pHead=new ListNode(0);
        pHead.next=head;
        ListNode p=pHead.next;
        ListNode lastNode=pHead.next;
        p=p.next;
        while(p!=null){
            if(p.val==lastNode.val){
                //删除当前节点
                lastNode.next=p.next;
                p=lastNode;
            }else{
                 lastNode=p;
            }
            p=p.next;
        }
        return pHead.next;
    }
}
```



#### [118. 杨辉三角](https://leetcode-cn.com/problems/pascals-triangle/)

难度简单275收藏分享切换为英文关注反馈

给定一个非负整数 *numRows，*生成杨辉三角的前 *numRows* 行。

![img](https://upload.wikimedia.org/wikipedia/commons/0/0d/PascalTriangleAnimated2.gif)

在杨辉三角中，每个数是它左上方和右上方的数的和。

**示例:**

```
输入: 5
输出:
[
     [1],
    [1,1],
   [1,2,1],
  [1,3,3,1],
 [1,4,6,4,1]
]
```

```
class Solution {
    public List<List<Integer>> generate(int n) {
        
        
        List<List<Integer>> resList=new ArrayList<List<Integer>>();
        if(n==0) return resList;
        int  i=0;
        int tempRes;
        List<Integer>  tempList=null;
        while(i<n){
            tempList=new ArrayList<Integer>();
            resList.add(tempList);
            for(int j=0;j<=i;j++){
                if(j==0||j==i){
                    tempList.add(1);
                }else{
                    tempRes=resList.get(i-1).get(j)+resList.get(i-1).get(j-1);
                    tempList.add(tempRes);
                }
            }
            i++;
        }
        return resList;
    }
}
```

#### [67. 二进制求和](https://leetcode-cn.com/problems/add-binary/)

难度简单336收藏分享切换为英文关注反馈

给你两个二进制字符串，返回它们的和（用二进制表示）。

输入为 **非空** 字符串且只包含数字 `1` 和 `0`。

 

**示例 1:**

```
输入: a = "11", b = "1"
输出: "100"
```

**示例 2:**

```
输入: a = "1010", b = "1011"
输出: "10101"
```

 

**提示：**

- 每个字符串仅由字符 `'0'` 或 `'1'` 组成。
- `1 <= a.length, b.length <= 10^4`
- 字符串如果不是 `"0"` ，就都不含前导零。

```
class Solution {
    public String addBinary(String aStr, String bStr) {
        int aLength=aStr.length();
        int bLength=bStr.length();
        int i=aLength-1,j=bLength-1;
        int a,b,carry=48,res=0;
         Stack<Integer> stack=new Stack<Integer>();
        
        while(i>=0||j>=0){
            try{
                 a=aStr.charAt(i);
            }catch(Exception e){
                a=48;
            }
            try{
                 b=bStr.charAt(j);
            }catch(Exception e){
                b=48;
            }
            //0=48  1=49
            res=a+b+carry;
           switch(res){
               case 48*3:carry=48; stack.push(0);break;//0 0 0
               case 49+48*2:carry=48; stack.push(1);break;//1 0 0
               case 49*2+48:carry=49; stack.push(0);break;//2 0    49+49
               case 49*3:carry=49; stack.push(1);break;//3  49+(+49+49)=98+49=147
               default:break;
           }
            i--;
            j--;
        }
        if(carry!=48){
            stack.push(1);
        }
         StringBuilder resSb=new StringBuilder("");
        while(!stack.empty()){
            resSb.append(stack.pop());
        }
        return resSb.toString();
    }
}
```



#### [21. 合并两个有序链表](https://leetcode-cn.com/problems/merge-two-sorted-lists/)

难度简单934收藏分享切换为英文关注反馈

将两个升序链表合并为一个新的升序链表并返回。新链表是通过拼接给定的两个链表的所有节点组成的。 

**示例：**

```
输入：1->2->4, 1->3->4
输出：1->1->2->3->4->4
```

```
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
         ListNode pHead=new ListNode(0);
         ListNode qHead=new ListNode(0);
         ListNode resHead=new ListNode(0);
        
         pHead.next=l1;
         qHead.next=l2;
         resHead.next=null;
        
        
         ListNode p=pHead.next,q=qHead.next;
         ListNode temp=null;
        
        //两个指针分别指向其头结点，如A表指针指向的关键字更小，则将其插入到res链表，并下移A表指针，此次比较 B表不做任何操作 
        //如B表指针指向的关键字更小，则将其插入到res链表，并下移B表指针，此次比较 A表不做任何操作 
         while(p!=null&&q!=null){
             if(p.val<=q.val){
                 temp=p;
                  p=p.next;
                 insertThail(resHead,temp);
                
             }else{
                 temp=q;
                 q=q.next;
                 insertThail(resHead,temp);
                 
             }
        }
        
        //两个表长不一样，未插入到目标链表的 L1或者L2的剩余节点 插入到目标链表
        
        
        //l1更长  剩余的l1插入
        while(p!=null){
           temp=p;
            p=p.next;
           insertThail(resHead,temp);
            
        }
        //l2更长 剩余的l2插入
        while(q!=null){
            temp=q;
            q=q.next;
           insertThail(resHead,temp);
           
        }
        return resHead.next;
        
     }
    
    public static void insertHead(ListNode l,ListNode cur){
        cur.next=l.next;
        l.next=cur;
    }
    public static void insertThail(ListNode l,ListNode cur){
        ListNode p=l;
        while(p.next!=null){
            p=p.next;
        }
       p.next=cur;
       cur.next=null;
    }
}

```

#### [27. 移除元素](https://leetcode-cn.com/problems/remove-element/)

难度简单519收藏分享切换为英文关注反馈

给你一个数组 *nums* 和一个值 *val*，你需要 **[原地](https://baike.baidu.com/item/原地算法)** 移除所有数值等于 *val* 的元素，并返回移除后数组的新长度。

不要使用额外的数组空间，你必须仅使用 O(1) 额外空间并 **[原地 ](https://baike.baidu.com/item/原地算法)修改输入数组**。

元素的顺序可以改变。你不需要考虑数组中超出新长度后面的元素。

 

**示例 1:**

```
给定 nums = [3,2,2,3], val = 3,

函数应该返回新的长度 2, 并且 nums 中的前两个元素均为 2。

你不需要考虑数组中超出新长度后面的元素。
```

**示例 2:**

```
给定 nums = [0,1,2,2,3,0,4,2], val = 2,

函数应该返回新的长度 5, 并且 nums 中的前五个元素为 0, 1, 3, 0, 4。

注意这五个元素可为任意顺序。

你不需要考虑数组中超出新长度后面的元素。
```

 

**说明:**

为什么返回数值是整数，但输出的答案是数组呢?

请注意，输入数组是以**「引用」**方式传递的，这意味着在函数里修改输入数组对于调用者是可见的。

你可以想象内部操作如下:

```
// nums 是以“引用”方式传递的。也就是说，不对实参作任何拷贝
int len = removeElement(nums, val);

// 在函数里修改输入数组对于调用者是可见的。
// 根据你的函数返回的长度, 它会打印出数组中 该长度范围内 的所有元素。
for (int i = 0; i < len; i++) {
    print(nums[i]);
}
```

```
class Solution {
    public int removeElement(int[] nums, int val) {
        int p=0,total=0;
        while(p<nums.length){
            if(nums[p]==val){
                total++;
            }
            if(p+1>=nums.length) return nums.length-total;
             nums[p+1-total]=nums[p+1];
            p++;
        }
        return nums.length-total;
    }
}
```

#### [35. 搜索插入位置](https://leetcode-cn.com/problems/search-insert-position/)

难度简单477收藏分享切换为英文关注反馈

给定一个排序数组和一个目标值，在数组中找到目标值，并返回其索引。如果目标值不存在于数组中，返回它将会被按顺序插入的位置。

你可以假设数组中无重复元素。

**示例 1:**

```
输入: [1,3,5,6], 5
输出: 2
```

**示例 2:**

```
输入: [1,3,5,6], 2
输出: 1
```

**示例 3:**

```
输入: [1,3,5,6], 7
输出: 4
```

**示例 4:**

```
输入: [1,3,5,6], 0
输出: 0
```

```
class Solution {
    public int searchInsert(int[] nums, int target) {
        int tpNums[]=new int[nums.length+1];
        int p=nums.length-1;
        while(p>=0&&target<nums[p]){
            tpNums[p+1]=nums[p];
                p--;
        }
        if(p>=0){
            if(nums[p]==target) 
                return p;
            else return p+1;
        }
        return p+1;
    }
}
```

#### [26. 删除排序数组中的重复项](https://leetcode-cn.com/problems/remove-duplicates-from-sorted-array/)

难度简单1378收藏分享切换为英文关注反馈

给定一个排序数组，你需要在**[ 原地](http://baike.baidu.com/item/原地算法)** 删除重复出现的元素，使得每个元素只出现一次，返回移除后数组的新长度。

不要使用额外的数组空间，你必须在 **[原地 ](https://baike.baidu.com/item/原地算法)修改输入数组** 并在使用 O(1) 额外空间的条件下完成。

 

**示例 1:**

```
给定数组 nums = [1,1,2], 

函数应该返回新的长度 2, 并且原数组 nums 的前两个元素被修改为 1, 2。 

你不需要考虑数组中超出新长度后面的元素。
```

**示例 2:**

```
给定 nums = [0,0,1,1,1,2,2,3,3,4],

函数应该返回新的长度 5, 并且原数组 nums 的前五个元素被修改为 0, 1, 2, 3, 4。

你不需要考虑数组中超出新长度后面的元素。
```

 

**说明:**

为什么返回数值是整数，但输出的答案是数组呢?

请注意，输入数组是以**「引用」**方式传递的，这意味着在函数里修改输入数组对于调用者是可见的。

你可以想象内部操作如下:

```
// nums 是以“引用”方式传递的。也就是说，不对实参做任何拷贝
int len = removeDuplicates(nums);

// 在函数里修改输入数组对于调用者是可见的。
// 根据你的函数返回的长度, 它会打印出数组中该长度范围内的所有元素。
for (int i = 0; i < len; i++) {
    print(nums[i]);
}
```

```
import  java.util.ArrayList;
class Solution {
    public int removeDuplicates(int[] nums) {
        ArrayList<Integer> repeat=new ArrayList<Integer>();
      
        int repeatTotal=0;
        for(int i=1;i<nums.length;i++){
             //获取所有重复数字的下标
            if(nums[i]==nums[i-1]){
                repeat.add(i);
            }
            //移动
            // if(repeat.contains(i)){
            //     repeatTotal++;
            //     continue;
            // }
            nums[i-repeat.size()]=nums[i];
        }
        return nums.length-repeat.size();
        
    }
}
```



#### [7. 整数反转](https://leetcode-cn.com/problems/reverse-integer/)

难度简单1802收藏分享切换为英文关注反馈

给出一个 32 位的有符号整数，你需要将这个整数中每位上的数字进行反转。

**示例 1:**

```
输入: 123
输出: 321
```

 **示例 2:**

```
输入: -123
输出: -321
```

**示例 3:**

```
输入: 120
输出: 21
```

**注意:**

假设我们的环境只能存储得下 32 位的有符号整数，则其数值范围为 [−231, 231 − 1]。请根据这个假设，如果反转后整数溢出那么就返回 0。

```
class Solution {

    public int reverse(int x) {
        
        
        int startIndex,endIndex;
        String str=String.valueOf(x);
        int result;
        if(x>=0){
            startIndex=0;
            endIndex=str.length();
        }else{
            startIndex=1;
            endIndex=str.length()-1;
        }
        
        char cur[]=new char[endIndex];
        str.getChars(startIndex,str.length(),cur,0);
        char temp='1';
        int len=cur.length;
        for(int i=0;i<=len/2-1;i++){
            temp=cur[i];
            cur[i]=cur[len-1-i];
            cur[len-1-i]=temp;
        }
        
        if(x<0){
			System.out.println("xiaoyu 0");
            String endStr=null;
            String res =new String(cur);
			System.out.println("cur:"+res);
            StringBuilder sb=new StringBuilder(res);
            sb.insert(0,'-');
            endStr=sb.toString();
			System.out.println("endStr:"+endStr);
            try{
                result= Integer.parseInt(endStr);
            }
            catch(Exception e){
                return 0;
            }
			System.out.println("result:"+result);
			return result;
            
        }
        
        try{
            result= Integer.parseInt(new String(cur));
        }
        catch(Exception e){
            return 0;
        }
        
        return result;
        
    }
}
```

#### [14. 最长公共前缀](https://leetcode-cn.com/problems/longest-common-prefix/)

难度简单940收藏分享切换为英文关注反馈

编写一个函数来查找字符串数组中的最长公共前缀。

如果不存在公共前缀，返回空字符串 `""`。

**示例 1:**

```
输入: ["flower","flow","flight"]
输出: "fl"
```

**示例 2:**

```
输入: ["dog","racecar","car"]
输出: ""
解释: 输入不存在公共前缀。
```

**说明:**

所有输入只包含小写字母 `a-z` 。

```
class Solution {
    public String longestCommonPrefix(String[] strs) {
        if(strs.length==0){
            return "";
        }
        for(int i=0;i<strs[0].length();i++){
            try{
                  char c=strs[0].charAt(i);
                  for(int j=1;j<strs.length;j++){
                       if(strs[j].charAt(i)!=c)
                           return strs[0].substring(0,i);
                    }
                }catch(Exception e){
                    return strs[0].substring(0,i);
                }
            }
        
        return strs[0];
    }
}
```

#### [20. 有效的括号](https://leetcode-cn.com/problems/valid-parentheses/)

难度简单1495收藏分享切换为英文关注反馈

给定一个只包括 `'('`，`')'`，`'{'`，`'}'`，`'['`，`']'` 的字符串，判断字符串是否有效。

有效字符串需满足：

1. 左括号必须用相同类型的右括号闭合。
2. 左括号必须以正确的顺序闭合。

注意空字符串可被认为是有效字符串。

**示例 1:**

```
输入: "()"
输出: true
```

**示例 2:**

```
输入: "()[]{}"
输出: true
```

**示例 3:**

```
输入: "(]"
输出: false
```

**示例 4:**

```
输入: "([)]"
输出: false
```

**示例 5:**

```
输入: "{[]}"
输出: true
```

```
class Solution {
    class Stack{
        final int listSize=20;
        int incrSize=5;
        int curSize;
        char list[]=null;
        int top=0;
        int bottom=0;
         Stack(){
            list=new char[listSize];
             curSize=listSize;
            top=bottom=0;
        }
        boolean isEmpty(){
            if(top==bottom)
                return true;
            return false;
        }
        char pop(){
             return list[top--];
        }
        char getTop(){
		    return list[top];
    	}
        void push(char c){
            if(top>=listSize-1) {
                char newList[]=new char[curSize+incrSize];
                curSize=curSize+incrSize;
                for(int i=0;i<list.length;i++){
                    newList[i]=list[i];
                }
                list=newList;
            }
            list[++top]=c;
        }
    }
    static boolean  match(char a,char b){
        if(a=='('&&b==')') return true;
        if(a=='['&&b==']') return true;
        if(a=='{'&&b=='}') return true;
        return false;
    }
    public boolean isValid(String s) {
        Stack st=new Stack();
        char curChar;
        for(int i=0;i<s.length();i++){
            curChar=s.charAt(i);
            switch(curChar){
                case '{':st.push(curChar);break;
                case '[':st.push(curChar);break;
                case '(':st.push(curChar);break;
                default:if(match(st.getTop(),curChar)) {
                    st.pop();
                    break;
                }else{
                    return false;
                }
                    
                    // switch(curChar){
                    //         case ")":if(st.getTop()=='(') st.pop();break;
                    //         case "}":if(st.getTop()=='{') st.pop();break;
                    //         case "]":if(st.getTop()=='[') st.pop();break;
                    // default:return false;
                    //     } 
            }
        }
       if(st.isEmpty()){
           return true;
       }
        else return false;
    }
    
}

```



#### [13. 罗马数字转整数](https://leetcode-cn.com/problems/roman-to-integer/)

难度简单853收藏分享切换为英文关注反馈

罗马数字包含以下七种字符: `I`， `V`， `X`， `L`，`C`，`D` 和 `M`。

```
字符          数值
I             1
V             5
X             10
L             50
C             100
D             500
M             1000
```

例如， 罗马数字 2 写做 `II` ，即为两个并列的 1。12 写做 `XII` ，即为 `X` + `II` 。 27 写做 `XXVII`, 即为 `XX` + `V` + `II` 。

通常情况下，罗马数字中小的数字在大的数字的右边。但也存在特例，例如 4 不写做 `IIII`，而是 `IV`。数字 1 在数字 5 的左边，所表示的数等于大数 5 减小数 1 得到的数值 4 。同样地，数字 9 表示为 `IX`。这个特殊的规则只适用于以下六种情况：

- `I` 可以放在 `V` (5) 和 `X` (10) 的左边，来表示 4 和 9。
- `X` 可以放在 `L` (50) 和 `C` (100) 的左边，来表示 40 和 90。 
- `C` 可以放在 `D` (500) 和 `M` (1000) 的左边，来表示 400 和 900。

给定一个罗马数字，将其转换成整数。输入确保在 1 到 3999 的范围内。

**示例 1:**

```
输入: "III"
输出: 3
```

**示例 2:**

```
输入: "IV"
输出: 4
```

**示例 3:**

```
输入: "IX"
输出: 9
```

**示例 4:**

```
输入: "LVIII"
输出: 58
解释: L = 50, V= 5, III = 3.
```

**示例 5:**

```
输入: "MCMXCIV"
输出: 1994
解释: M = 1000, CM = 900, XC = 90, IV = 4.
```

```
class Solution {
    public int romanToInt(String str) {
        int sum=0;
        for(int i=0;i<str.length();i++){
           char cur=str.charAt(i);
            if(i==0){
                sum+=getValue(cur);
            }else{
                if(compare(str.charAt(i-1),cur)){
                    sum+=getValue(cur);
                }else{
                    sum-=getValue(str.charAt(i-1));
                    sum+=getValue(cur)-getValue(str.charAt(i-1));
                }
            }
        }
        return sum;
    }
    public boolean compare(char s1,char s2){
        return Solution.getLevel(s1) >= Solution.getLevel(s2);
    }
    public int getValue(char s){
        switch(s){
            case 'I':return 1;
            case 'V':return 5;
            case 'X':return 10;
            case 'L':return 50;
            case 'C':return 100;
            case 'D':return 500;
            case 'M':return 1000;
        }
        return 0;
    }
    public static int getLevel(char s){
       switch(s){
            case 'I':return 1;
            case 'V':return 2;
            case 'X':return 3;
            case 'L':return 4;
            case 'C':return 5;
            case 'D':return 6;
            case 'M':return 7;
        }
         return 0;
    }
   
}
// I             1
// V             5
// X             10
// L             50
// C             100
// D             500
// M   
```

#### [9. 回文数](https://leetcode-cn.com/problems/palindrome-number/)

难度简单990收藏分享切换为英文关注反馈

判断一个整数是否是回文数。回文数是指正序（从左向右）和倒序（从右向左）读都是一样的整数。

**示例 1:**

```
输入: 121
输出: true
```

**示例 2:**

```
输入: -121
输出: false
解释: 从左向右读, 为 -121 。 从右向左读, 为 121- 。因此它不是一个回文数。
```

**示例 3:**

```
输入: 10
输出: false
解释: 从右向左读, 为 01 。因此它不是一个回文数。
```

**进阶:**

你能不将整数转为字符串来解决这个问题吗？

```
class Solution {
    public boolean isPalindrome(int x) {
        String str=String.valueOf(x);
        int len=str.length();
        for(int i=0;i<=len/2-1;i++){
            if(str.charAt(i)==str.charAt(len-1-i))
                continue;
            else return false;
        }
        return true;
        
    }
}
```



#### [2. 两数相加](https://leetcode-cn.com/problems/add-two-numbers/)

难度中等4143收藏分享切换为英文关注反馈

给出两个 **非空** 的链表用来表示两个非负的整数。其中，它们各自的位数是按照 **逆序** 的方式存储的，并且它们的每个节点只能存储 **一位** 数字。

如果，我们将这两个数相加起来，则会返回一个新的链表来表示它们的和。

您可以假设除了数字 0 之外，这两个数都不会以 0 开头。

**示例：**

```
输入：(2 -> 4 -> 3) + (5 -> 6 -> 4)
输出：7 -> 0 -> 8
原因：342 + 465 = 807
```

```
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode l3=new ListNode(0);
        ListNode p=l1;
        ListNode k=l2;
        ListNode t=l3;
        int i=1;
         int corr=0;
        ListNode tp=null;
          while(p!=null||k!=null){
              int op1= p==null? 0:p.val;
              int op2= k==null? 0:k.val;  
              int res=op1+op2+corr;




               if(i==1) {
                  res=op1+op2;
                   i++;
                   t.val=res%10;

               }
               else {
                    tp=new ListNode(res%10);
                    t.next=tp;
                     t=t.next;
               }


              if(p!=null) p=p.next;
              if(k!=null) k=k.next;
              corr=res/10;

           }
        if(corr!=0){
            t.next=new ListNode(1);
        }
         return l3;
       
    }
}

```

