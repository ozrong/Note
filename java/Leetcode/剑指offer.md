# [剑指 Offer 29. 顺时针打印矩阵](https://leetcode-cn.com/problems/shun-shi-zhen-da-yin-ju-zhen-lcof/)

输入一个矩阵，按照从外向里以顺时针的顺序依次打印出每一个数字。

 

示例 1：

输入：matrix = [[1,2,3],[4,5,6],[7,8,9]]
输出：[1,2,3,6,9,8,7,4,5]

示例 2：

输入：matrix = [[1,2,3,4],[5,6,7,8],[9,10,11,12]]
输出：[1,2,3,4,8,12,11,10,9,5,6,7]

```java
答案：
class Solution {
    public int[] spiralOrder(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return new int[0];
        }
        int rows = matrix.length, columns = matrix[0].length;
        int[] order = new int[rows * columns];
        int index = 0;
        int left = 0, right = columns - 1, top = 0, bottom = rows - 1;
        while (left <= right && top <= bottom) {
            for (int column = left; column <= right; column++) {
                order[index++] = matrix[top][column];
            }
            for (int row = top + 1; row <= bottom; row++) {
                order[index++] = matrix[row][right];
            }
            if (left < right && top < bottom) {
                for (int column = right - 1; column > left; column--) {
                    order[index++] = matrix[bottom][column];
                }
                for (int row = bottom; row > top; row--) {
                    order[index++] = matrix[row][left];
                }
            }
            left++;
            right--;
            top++;
            bottom--;
        }
        return order;
    }
}




自己敲得
class Solution {
    public int[] spiralOrder(int[][] matrix) {
        int len = matrix.length;
      
        if(matrix==null || len==0){
            return new int[0];
        }
        int wide = matrix[0].length;
        int[] ans = new int[len*wide];
        int index = 0;
        int left = 0,right = wide-1,top = 0,bottom = len-1;
        while(left<=right && top<=bottom ){  可以理解为4个指针  上  下  左  右
            

        for(int j=left;j<=right;j++ ){     左->右
            ans[index] = matrix[top][j];
            index++;
        }
        for(int i = top+1;i<=bottom;i++ ){ 上->下
            ans[index] = matrix[i][right];
            index++;
        }

        if(left < right && top < bottom){  //为什么需要这个判断尼？？？？？
                                          
            
            /*
            答：
            例如：
            1  2  3  4
            5  6  7  8
            9  10 11 12
            
            
            本来ans这个数组的长度就是12  索引到11。  但是在在第二次遍历内圈的时候
            出现了  left=1 right=2 top=bottom=1 的情况，如果执行 左->右 的操作这会在读一个6到ans中  但是这时其实ans已经装满了
            所以数组越界了
            
            
            
            这是不要这个判断，并增加ans的长度得到的答案
            [1,2,3,4,8,12,11,10,9,5,6,7,6,0,0,0,0,0,0,0,0,0]
            显然重复读了一次6
            */
            

            for(int j = right-1;j>=left; j--){  右—>左
                ans[index] = matrix[bottom][j];
                index++;
            }
            for(int i=bottom-1;i>top;i--){  下->上
                ans[index] = matrix[i][left];
                index++;
            }
    
        }
        left++;
        right--;
        top++;
        bottom--;
        }

        return ans;
       
    }
}
```

# [剑指 Offer 10- II. 青蛙跳台阶问题](https://leetcode-cn.com/problems/qing-wa-tiao-tai-jie-wen-ti-lcof/)

一只青蛙一次可以跳上1级台阶，也可以跳上2级台阶。求该青蛙跳上一个 n 级的台阶总共有多少种跳法。

答案需要取模 1e9+7（1000000007），如计算初始结果为：1000000008，请返回 1。

示例 1：

输入：n = 2
输出：2

示例 2：

输入：n = 7
输出：21

示例 3：

输入：n = 0
输出：1

```java
递归：
    原理： 把 f(n)f(n)f(n) 问题的计算拆分成 f(n−1)f(n-1)f(n−1) 和 f(n−2)f(n-2)f(n−2) 两个子问题的计算，并递归，以 f(0)f(0)f(0) 和 f(1)f(1)f(1) 为终止条件。
缺点： 大量重复的递归计算，例如 f(n)f(n)f(n) 和 f(n−1)f(n - 1)f(n−1) 两者向下递归都需要计算 f(n−2)f(n - 2)f(n−2) 的值。
    
class Solution {
    public int numWays(int n) {
        if(n==0 ||n==1){
              return 1;
        }else  {
            return numWays(n-2)+numWays(n-1);}

    }
}


动态规划
    class Solution {
    public int numWays(int n) {
        if(n==0) return 1;
        if(n==1) return 1;
        if(n==2) return 2;

        int[] ans = new int[n+1];
        ans[0]=1;
        ans[1]=1;
        ans[2]=2;

        for(int i=3;i<=n;i++){
             ans[i] = (ans[i-1] + ans[i-2])% 1000000007; 题目要求要取模
        }
        return ans[n];
    }
}
    
```

​       

# [剑指 Offer 03. 数组中重复的数字](https://leetcode-cn.com/problems/shu-zu-zhong-zhong-fu-de-shu-zi-lcof/)

在一个长度为 n 的数组 nums 里的所有数字都在 0～n-1 的范围内。数组中某些数字是重复的，但不知道有几个数字重复了，也不知道每个数字重复了几次。请找出数组中任意一个重复的数字。

示例 1：

输入：
[2, 3, 1, 0, 2, 5, 3]
输出：2 或 3 

```java
使用哈希表，来统计每个数字出现的次数，输出一个重复的数字即可，
class Solution {
    public int findRepeatNumber(int[] nums) {
        int len = nums.length;
        HashMap<Integer,Integer> ans = new HashMap<Integer,Integer>();
        for(int i = 0 ;i<len;i++){
            Integer temp = ans.getOrDefault(nums[i],0);
            if(temp!=0){
                return nums[i];//这种方法不会遍历完数组的
            }else{
                temp++;
                ans.put(nums[i],temp);
            }
        }
        return -1;

    }
}


由于只需要找出数组中任意一个重复的数字，因此遍历数组，遇到重复的数字即返回。为了判断一个数字是否重复遇到，使用集合存储已经遇到的数字，如果遇到的一个数字已经在集合中，则当前的数字是重复数字

class Solution {
    public int findRepeatNumber(int[] nums) {
        int len = nums.length;
        Set<Integer> ans = new HashSet<>();
        int repeat = -1;

      
        for(int i = 0 ;i<len;i++){
            if(! ans.add(nums[i])){
                repeat = nums[i];
                break;
            }
         
        }
        return repeat;

    }
}

```

# [剑指 Offer 42. 连续子数组的最大和](https://leetcode-cn.com/problems/lian-xu-zi-shu-zu-de-zui-da-he-lcof/)

输入一个整型数组，数组中的一个或连续多个整数组成一个子数组。求所有子数组的和的最大值。

要求时间复杂度为O(n)。

 

示例1:

输入: nums = [-2,1,-3,4,-1,2,1,-5,4]
输出: 6
解释: 连续子数组 [4,-1,2,1] 的和最大，为 6。

```java
class Solution {
    public int maxSubArray(int[] nums) {
        int max = nums[0];
        int len = nums.length;
        int[] dp = new int[len];
        dp[0] = nums[0];
        for(int i = 1;i<len;i++){
            if((dp[i-1]+nums[i]) <= nums[i]){
                dp[i] = nums[i];
            }else{
                dp[i] = dp[i-1]+nums[i];
            }
            if(max < dp[i]){
                max = dp[i];
            }
           
        }
        return max;
    }
}

/**
定义一个dp数组 设动态规划列表dp ，dp[i] 代表以元素 nums[i] 为结尾的连续子数组最大和

转移方程就是：
当dp[i-1]+nums[i] <= nums[i] 这个最大数就是本身，否则就是dp[i-1]+nums[i]


*/


简化版本
    
    
    class Solution {
    public int maxSubArray(int[] nums) {
        int[] dp = new int[nums.length];
        dp[0] = nums[0];
        int max = dp[0];
        for(int i=1;i < nums.length;i++){
            dp[i] = Math.max(dp[i-1] + nums[i],nums[i]);
            max = Math.max(max,dp[i]);
        }
        return max;
    }
}

    

```

# [剑指 Offer 09. 用两个栈实现队列](https://leetcode-cn.com/problems/yong-liang-ge-zhan-shi-xian-dui-lie-lcof/)

用两个栈实现一个队列。队列的声明如下，请实现它的两个函数 appendTail 和 deleteHead ，分别完成在队列尾部插入整数和在队列头部删除整数的功能。(若队列中没有元素，deleteHead 操作返回 -1 )

```java
class CQueue {
    Deque<Integer>  stark1;
    Deque<Integer>  stark2;
    public CQueue() {
        stark1 = new ArrayDeque<>();
        stark2 = new ArrayDeque<>();
    } 
    public void appendTail(int value) {
        stark1.push(value);
    }
    public int deleteHead() {
        if(!stark2.isEmpty()){
           return stark2.pop();
        }else{
            if(!stark1.isEmpty()){
                while(!stark1.isEmpty()){
                    stark2.push(stark1.pop());
                }
                return stark2.pop();
            }else{
                return -1;
            }
        }
    }
}
栈1只管近，栈2只管出
删除数据首先看栈2是不是空的，如果不是就直接删除在栈2顶部的数据（也就等于队列头），否则把栈1的数据倒进栈2里面，在删除栈2顶部的数据。


/**
 * Your CQueue object will be instantiated and called as such:
 * CQueue obj = new CQueue();
 * obj.appendTail(value);
 * int param_2 = obj.deleteHead();
 */
```

# [剑指 Offer 04. 二维数组中的查找](https://leetcode-cn.com/problems/er-wei-shu-zu-zhong-de-cha-zhao-lcof/)

在一个 n * m 的二维数组中，每一行都按照从左到右递增的顺序排序，每一列都按照从上到下递增的顺序排序。请完成一个高效的函数，输入这样的一个二维数组和一个整数，判断数组中是否含有该整数。

 

示例:

现有矩阵 matrix 如下：

[
  [1,   4,  7, 11, 15],
  [2,   5,  8, 12, 19],
  [3,   6,  9, 16, 22],
  [10, 13, 14, 17, 24],
  [18, 21, 23, 26, 30]
]

给定 target = 5，返回 true。

给定 target = 20，返回 false。

```java
比较每一列最大的数（最后一个数）是否大于等于目标数，如果满足则从后向前遍历这一行看是否包含目标数  否则下一行在重复从后往前的遍历
class Solution {
    public boolean findNumberIn2DArray(int[][] matrix, int target) {
        
        int row = matrix.length;
        if(row!=0 && matrix[0].length!=0) {
        int col = matrix[0].length;
        for(int i=0;i<row;i++){
            if(matrix[i][col-1] >= target ){
                for(int j=col-1;j>=0;j--){
                    if(matrix[i][j]==target){
                        return true;
                    }
                }
            }

        }
    }
        return false;

    }
}
```

# [剑指 Offer 06. 从尾到头打印链表](https://leetcode-cn.com/problems/cong-wei-dao-tou-da-yin-lian-biao-lcof/)

输入一个链表的头节点，从尾到头反过来返回每个节点的值（用数组返回）。

 

示例 1：

输入：head = [1,3,2]
输出：[2,3,1]

```java
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    public int[] reversePrint(ListNode head) {
        ArrayDeque<Integer> temp = new ArrayDeque<>();
        while(head!=null){
            temp.push(head.val);
            head = head.next;
        }

        int[] ans = new int[temp.size()];
        int i=0;
        while(!temp.isEmpty()){
            ans[i] = temp.pop();
            i++;

        }
        return ans;


    }
}
```

# [剑指 Offer 45. 把数组排成最小的数](https://leetcode-cn.com/problems/ba-shu-zu-pai-cheng-zui-xiao-de-shu-lcof/)

输入一个非负整数数组，把数组里所有数字拼接起来排成一个数，打印能拼接出的所有数字中最小的一个。

 

示例 1:

输入: [10,2]
输出: "102"

示例 2:

输入: [3,30,34,5,9]
输出: "3033459"

解题思路：

此题求拼接起来的 “最小数字” ，本质上是一个排序问题。

看似是拼接一个最小的数字，其实是拼接成一个最小的字符串（字符串对应的ACSII码最小）

所以把数字变成字符串，然后在进行一个排序（让前后两个字符串拼接起来的ACSII最小就可以保证所有的字符串拼接起来最小）

排序规则如下





![image-20210302214250500](C:\Users\hp\AppData\Roaming\Typora\typora-user-images\image-20210302214250500.png)

```java
class Solution {
    public String minNumber(int[] nums) {
        String[] temp = new String[nums.length];
        for(int i=0;i<nums.length;i++){
            temp[i] = nums[i]+"";
        }
        Arrays.sort(temp,new Comparator<String>(){
            public int compare(String s1,String s2 ){
              return (s1+s2).compareTo(s2+s1);
                  
        }});
        String ans="";

        for(int i = 0;i<temp.length;i++){
            ans += temp[i];
        }
        return ans;

    }
}


Compatator是一个函数式接口，所以可以使用lambda表达式简写
class Solution {
    public String minNumber(int[] nums) {
        String[] temp = new String[nums.length];
        for(int i=0;i<nums.length;i++){
            temp[i] = nums[i]+"";
        }
        Arrays.sort(temp,(x,y)->(x+y).compareTo(y+x));
        String ans="";

        for(int i = 0;i<temp.length;i++){
            ans += temp[i];
        }
        return ans;

    }
}


其中
(x,y)->(x+y).compareTo(y+x) 
=
new Comparator<String>(){
            public int compare(String s1,String s2 ){
              return (s1+s2).compareTo(s2+s1);
                  
        }} 
    
```

# [剑指 Offer 47. 礼物的最大价值](https://leetcode-cn.com/problems/li-wu-de-zui-da-jie-zhi-lcof/)

在一个 m*n 的棋盘的每一格都放有一个礼物，每个礼物都有一定的价值（价值大于 0）。你可以从棋盘的左上角开始拿格子里的礼物，并每次向右或者向下移动一格、直到到达棋盘的右下角。给定一个棋盘及其上面的礼物的价值，请计算你最多能拿到多少价值的礼物？

 

示例 1:

输入: 
[
  [1,3,1],
  [1,5,1],
  [4,2,1]
]
输出: 12
解释: 路径 1→3→5→2→1 可以拿到最多价值的礼物

思路：

显然一个动态规划问题

1.定义一个和输入同等大小的二维数组，位置（i，j）表示从左上角位置到（i，j）位置可以得到的最大的数，

2.初始化：$dp[0][0] = grid[0][0]$  首行$ dp[0][j] = grid[0][j]+dp[0][j-1];$ 首列$ dp[i][0] = grid[i][0]+dp[i-1][0];$

3.状态转移方程：$ dp[i][j] = Math.max(grid[i][j]+dp[i-1][j],grid[i][j]+dp[i][j-1]);$

```java
class Solution {
    public int maxValue(int[][] grid) {
        if(grid.length==0 || grid[0].length==0) return 0;
        int[][] dp = new int[grid.length][grid[0].length];
        dp[0][0] = grid[0][0];
         for(int i=1;i<grid.length;i++){
             dp[i][0] = grid[i][0]+dp[i-1][0];
         }

         for(int j=1;j<grid[0].length;j++){
             dp[0][j] = grid[0][j]+dp[0][j-1];

            }
        for(int i=1;i<grid.length;i++){
            for(int j=1;j<grid[0].length;j++){
                dp[i][j] = Math.max(grid[i][j]+dp[i-1][j],grid[i][j]+dp[i][j-1]);
            }
        }
        return dp[grid.length-1][grid[0].length-1];


    }
}




代码优化版本
    
直接在原来的数组里面进行    
class Solution {
    public int maxValue(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                if(i == 0 && j == 0) continue;
                if(i == 0) grid[i][j] += grid[i][j - 1] ;
                else if(j == 0) grid[i][j] += grid[i - 1][j];
                else grid[i][j] += Math.max(grid[i][j - 1], grid[i - 1][j]);
            }
        }
        return grid[m - 1][n - 1];
    }
}
```

# [剑指 Offer 59 - II. 队列的最大值](https://leetcode-cn.com/problems/dui-lie-de-zui-da-zhi-lcof/)

思路：

例如  插入一串数字 $5,3,6,7,4,3,8,9,1,0$ 。按这个顺序插入。当取最大值的时候，只要9前面有数字，最大值就会是9 。9前面插入的数字对最大值没有影响。

所以同时维护两个队列，一个是原队列（A），一个降序排的队列(B)（队首的数字最大）。但不是对所有的数字进行排列。每放入一个数（i）都与B队尾的元素来比较如果i大于B队尾i的元素就删除这个队尾元素，知道遇到比i大的队尾元素。

A: 5367438910

B:910

```java
class MaxQueue {
    ArrayDeque<Integer> queue ;
    ArrayDeque<Integer> maxqueue ;
    public MaxQueue() {
        queue = new ArrayDeque<>();
        maxqueue = new ArrayDeque<>();
    }
    public int max_value() {
        if(queue.isEmpty()) return -1;
        return maxqueue.peekFirst(); 
    }
    public void push_back(int value) {
      
        while(!maxqueue.isEmpty() && maxqueue.peekLast() < value){
            maxqueue.pollLast();
        }
        queue.offerLast(value);
        maxqueue.offerLast(value);
    }
    public int pop_front() {
        if(queue.isEmpty()) return -1;
        int ans = queue.pollFirst();
        if(ans == maxqueue.peekFirst())
           maxqueue.pollFirst();
        return ans;
    }
}

/**
 * Your MaxQueue object will be instantiated and called as such:
 * MaxQueue obj = new MaxQueue();
 * int param_1 = obj.max_value();
 * obj.push_back(value);
 * int param_3 = obj.pop_front();
 */
```

# [剑指 Offer 49. 丑数](https://leetcode-cn.com/problems/chou-shu-lcof/)

直接看



```java
class Solution {
    public int nthUglyNumber(int n) {
        int[] dp = new int[n];
        dp[0] = 1;
        int a =0,b=0,c=0;
        for(int i = 1;i<dp.length;i++){
            int n2 = dp[a]*2,n3 = dp[b]*3,n5=dp[c]*5;
            dp[i] = Math.min(Math.min(n2,n3),n5);
            if(dp[i] == n2) a++;
            if(dp[i] == n3) b++;
            if(dp[i] == n5) c++;
        }
        return dp[n-1];


    }
}
```

# [剑指 Offer 05. 替换空格](https://leetcode-cn.com/problems/ti-huan-kong-ge-lcof/)

请实现一个函数，把字符串 s 中的每个空格替换成"%20"。

 

示例 1：

输入：s = "We are happy."
输出："We%20are%20happy."

```java
class Solution {
    public String replaceSpace(String s) {
        StringBuffer str = new StringBuffer();
        for(int i=0;i<s.length();i++){
            if(' '==(s.charAt(i))){
                str.append("%20");
            }else{
             str.append(s.charAt(i));
            }
          
        }
        return str.toString();
    }
}
```

# [剑指 Offer 10- I. 斐波那契数列](https://leetcode-cn.com/problems/fei-bo-na-qi-shu-lie-lcof/)

写一个函数，输入 n ，求斐波那契（Fibonacci）数列的第 n 项（即 F(N)）。斐波那契数列的定义如下：

F(0) = 0,   F(1) = 1
F(N) = F(N - 1) + F(N - 2), 其中 N > 1.

斐波那契数列由 0 和 1 开始，之后的斐波那契数就是由之前的两数相加而得出。

答案需要取模 1e9+7（1000000007），如计算初始结果为：1000000008，请返回 1。

```java
动态规划
class Solution {
    public int fib(int n) {
        if(n==0) return 0;
        if(n==1) return 1;
        int[] dp = new int[n+1];
        dp[0] = 0;
        dp[1] = 1;
        for(int i = 2;i <=n;i++){
            dp[i] = (dp[i-1]+dp[i-2]) % 1000000007;
        }
        return dp[n];
 
    }
}

```

# [剑指 Offer 11. 旋转数组的最小数字](https://leetcode-cn.com/problems/xuan-zhuan-shu-zu-de-zui-xiao-shu-zi-lcof/)

把一个数组最开始的若干个元素搬到数组的末尾，我们称之为数组的旋转。输入一个递增排序的数组的一个旋转，输出旋转数组的最小元素。例如，数组 [3,4,5,1,2] 为 [1,2,3,4,5] 的一个旋转，该数组的最小值为1。  

示例 1：

输入：[3,4,5,1,2]
输出：1

示例 2：

输入：[2,2,2,0,1]
输出：0

```java
class Solution {
    public int minArray(int[] numbers) {
        int left = 0;
        int right = numbers.length-1;
        while(left < right ){
            int mid = left+(right-left)/2;
            if(numbers[right] > numbers[mid]){
                right = mid;
            }else if(numbers[right] < numbers[mid]){
                left = mid+1;
            }else{
                right--;
            }
        }
        return numbers[left];
    }
}

/*
left+(right-left)/2; 可以避免溢出  如果数组很长的话
*/


错误的解法
 class Solution {
        public int minArray(int[] numbers) {
            int left = 0;
            int right = numbers.length-1;
            while(left < right ){
                int mid = left + (right-left)/2;
                if(numbers[right] > numbers[mid]){
                    right = mid;        //满足了这个条件的话  right就改变了，  有可能有让下面的条件得到了满足，所以这儿不能使用这个if-if-if
                }
                if(numbers[right] < numbers[mid]){
                    left = mid+1;

                }
                if(numbers[right] == numbers[mid]){
                    right--;
                }
            }
            return numbers[left];



        }
    } 
/**
if-else if-else，满足了一个条件就不会在判断了，而if-if-if 满足了条件还是要继续判断，

*/



```

# [剑指 Offer 07. 重建二叉树](https://leetcode-cn.com/problems/zhong-jian-er-cha-shu-lcof/)

输入某二叉树的前序遍历和中序遍历的结果，请重建该二叉树。假设输入的前序遍历和中序遍历的结果中都不含重复的数字。

 

例如，给出

前序遍历 preorder = [3,9,20,15,7]
中序遍历 inorder = [9,3,15,20,7]

返回如下的二叉树：

        3
       / \
      9  20
        /  \
       15   7
  ```java
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */

递归
class Solution {
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        if(preorder.length==0 || inorder.length==0) return null;
        return getTree(preorder,inorder,0,preorder.length-1,0,inorder.length-1);
    }
    public  TreeNode getTree(int[] preorder, int[] inorder,int prestart,int preend,int minstart,int minend ){
        TreeNode  p = new TreeNode(preorder[prestart]);
        int root=0;

        if(prestart==preend && minstart==minend){
            return p;
        }
        for(int i=minstart;i<=minend;i++){
            if(p.val==inorder[i]){
                root = i;
                break;
            }
        }
        int leftlength = root-minstart;
        int rightlength = minend-root;
        if(leftlength > 0){
             p.left = getTree(preorder,inorder,prestart+1,prestart+leftlength,minstart,root-1);
            
        }
        if(rightlength>0){
             p.right = getTree(preorder,inorder,prestart+leftlength+1,preend,root+1,minend);

        }
         return p; 
    }
   
}

迭代 不会

  ```



# [剑指 Offer 14- I. 剪绳子](https://leetcode-cn.com/problems/jian-sheng-zi-lcof/)（好题）

直接看网站吧！！！！|

```java
class Solution {
    public int cuttingRope(int n) {
        if(n <= 3) return n - 1;
        int a = n / 3, b = n % 3;
        if(b == 0) return (int)Math.pow(3, a);
        if(b == 1) return (int)Math.pow(3, a - 1) * 4;
        return (int)Math.pow(3, a) * 2;
    }
}



```



