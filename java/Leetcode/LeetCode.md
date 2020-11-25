  # 14.1 环形链表

```java
//方法一
public class Solution {
    public boolean hasCycle(ListNode head) {
        Set<ListNode> set = new HashSet<>();//利用set不可以存储相同的值的特性来遍历这个链表
        while(head!= null){
            if(!set.add(head)){
                return true;
            }
            head = head.next;
            }
            
        }
    }
}
//方式二：快慢指针
public class Solution {
    public boolean hasCycle(ListNode head) {
        if (head == null || head.next == null) {
            return false;
        }
        ListNode slow = head;
        ListNode fast = head.next;
        /*
        使用while的时候，初始化slow和fast的时候 slow要在fast前面
        使用do-while就可以在同一起点
        
        
        就想操场跑圈一样，快指针必然会追上慢指针（两者重逢）
        */
        while (slow != fast){
            if(fast==null||fast.next !=null){
                return false;
            }
            slow = slow.next;
            fast = fast.next.next;
        }
        return true;   
    }
}
```

## java链表

```java
链表结构;
class NodeList{
    int val;
    NodeList next;
    NodeList (int val){
        this.val = val;
    }
}
以上就是一个链表类;

NodeList head = new NodeList(23);
NodeList frist = new NodeList(25);

head.next = frist; //这就连接起来了

```



#  1002. 查找常用字符

```java
方法一
class Solution {
    public List<String> commonChars(String[] A) {
        int[] minlist = new int[26];
        Arrays.fill(minlist,Integer.MAX_VALUE);
        for (String word:A){
            int[] list1 = new int[26];
            int length = word.length();
            for (int i = 0; i <length ; i++) {//统计在字符串中字符出现的次数
                char ch = word.charAt(i);
                ++list1[ch-'a'];//表示第几个字母的那个为位置加一
                // list1[ch-'a'] = list1[ch-'a']+1
            }
            for (int i = 0; i <26 ; i++) {
                minlist[i] = Math.min(minlist[i],list1[i]);
            }
        }
        List<String> ans = new ArrayList<>();
        for (int i = 0; i <26 ; i++) {
            for (int j = 0; j <minlist[i] ; j++) {
                ans.add(String.valueOf((char)(i+'a')));
                //这是因为出现了几次这个字符就要显示几次，所以是两次循环                
            }
        }
        return ans;
    }
}
方法二
    //思想的差不多，没理解到
    class Solution {
    public List<String> commonChars(String[] A) {
        //先得到第一个字符串里面每个字符的情况
        int[] charCount = new int[26];
        for(int i = 0; i < A[0].length(); i++){
            charCount[A[0].charAt(i) - 'a']++;
        }
        //从第1个开始逐个进行比较
        for(int i = 1; i < A.length; i++){
            int[] curCount = new int[26];
            
            //遍历第i个字符串
            for(int j = 0; j < A[i].length(); j++){
                curCount[A[i].charAt(j) - 'a']++;
            }

            //将此时关于第i个字符串的情况与charCount对比，取小的即可
            for(int k = 0; k < 26; k++){
                charCount[k] = Math.min(charCount[k], curCount[k]);
            }
        }

        //此时的charCount即为所有的字符串出现的共有的最小元素的个数
        List<String> result = new ArrayList<>();
        for(int i = 0; i < 26; i++){
            while(charCount[i] != 0){
                result.add(String.valueOf((char)(i + 'a')));
                charCount[i]--;
            }
        }

        return result;
    }
}

```

# [116. 填充每个节点的下一个右侧节点指针](https://leetcode-cn.com/problems/populating-next-right-pointers-in-each-node/)

```java
/*
// Definition for a Node.
class Node {
    public int val;
    public Node left;
    public Node right;
    public Node next;

    public Node() {}
    
    public Node(int _val) {
        val = _val;
    }

    public Node(int _val, Node _left, Node _right, Node _next) {
        val = _val;
        left = _left;
        right = _right;
        next = _next;
    }
};
*/

class Solution {
    public Node connect(Node root) {
        if (root == null) {
            return root;
        }
   
        // 初始化队列同时将第一层节点加入队列中，即根节点
        Queue<Node> queue = new LinkedList<Node>(); 
        queue.add(root);
        
        // 外层的 while 循环迭代的是层数
        while (!queue.isEmpty()) {
            
            // 记录当前队列大小
            int size = queue.size();
            
            // 遍历这一层的所有节点
            for (int i = 0; i < size; i++) {
                
                // 从队首取出元素
                Node node = queue.poll();
                
                // 连接
                if (i < size - 1) {
                    node.next = queue.peek();
                }
                
                // 拓展下一层节点
                if (node.left != null) {
                    queue.add(node.left);
                }
                if (node.right != null) {
                    queue.add(node.right);
                }
            }
        }
        
        // 返回根节点
        return root;
    }
}

```

# [143. 重排链表](https://leetcode-cn.com/problems/reorder-list/)

自己的错误代码：

```java
class Solution {
    public void reorderList(ListNode head) {
        ListNode begin = head;
        while (head !=null && head.next!=null){
            ListNode first = head;
            head = head.next;
            ListNode temp = head;
            while (true){
                if(temp!=null && temp.next!=null && temp.next.next==null){
                    break;
                }else{
                    temp = temp.next;
                }
            }
            ListNode end = temp.next;
            temp.next=null;

            first.next = end;
            end.next = head;
        }
        head = begin; 
        return head;
    }
}
绝对可行，下次再调
```

思路：    
    1.快慢指针找到中点 2.拆成两个链表 3.遍历两个链表，后面的塞到前面的“缝隙里”
    

方法一：

因为链表不支持下标访问，所以我们无法随机访问链表中任意位置的元素。

因此比较容易想到的一个方法是，我们利用线性表存储该链表，然后利用线性表可以下标访问的特点，直接按顺序访问指定元素，重建该链表即可。

```java
class Solution {
    public void reorderList(ListNode head) {
        if (head == null) {
            return;
        }
        List<ListNode> list = new ArrayList<ListNode>();
        ListNode node = head;
        while (node != null) {
            list.add(node);
            node = node.next;
        }
        int i = 0, j = list.size() - 1;
        while (i < j) {
            list.get(i).next = list.get(j);
            i++;
            if (i == j) {
                break;
            }
            list.get(j).next = list.get(i);
            j--;
        }
        list.get(i).next = null;
    }
}
这个方法最简单
```

方法二：

方法二：寻找链表中点 + 链表逆序 + 合并链表
注意到目标链表即为将原链表的左半端和反转后的右半端合并后的结果。

这样我们的任务即可划分为三步：

+ 找到原链表的中点（参考[876. 链表的中间结点](https://leetcode-cn.com/problems/middle-of-the-linked-list/)）。

我们可以使用快慢指针来 O(N)O(N) 地找到链表的中间节点。

+ 将原链表的右半端反转（参考[206. 反转链表](https://leetcode-cn.com/problems/reverse-linked-list/)）。

我们可以使用迭代法实现链表的反转。

+ 将原链表的两端合并。

因为两链表长度相差不超过 1，因此直接合并即可。

```java
class Solution {
    public void reorderList(ListNode head) {
        if (head == null) {
            return;
        }
        ListNode mid = middleNode(head);
        ListNode l1 = head;
        ListNode l2 = mid.next;
        mid.next = null;
        l2 = reverseList(l2);
        mergeList(l1, l2);
    }

    public ListNode middleNode(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    public ListNode reverseList(ListNode head) {
        ListNode prev = null;
        ListNode curr = head;
        while (curr != null) {
            ListNode nextTemp = curr.next;
            curr.next = prev;
            prev = curr;
            curr = nextTemp;
        }
        return prev;
    }

    public void mergeList(ListNode l1, ListNode l2) {
        ListNode l1_tmp;
        ListNode l2_tmp;
        while (l1 != null && l2 != null) {
            l1_tmp = l1.next;
            l2_tmp = l2.next;

            l1.next = l2;
            l1 = l1_tmp;

            l2.next = l1;
            l2 = l2_tmp;
        }
    }
}
```

# 406[根据身高重建队列](https://leetcode-cn.com/problems/queue-reconstruction-by-height/)

假设有打乱顺序的一群人站成一个队列。 每个人由一个整数对(h, k)表示，其中h是这个人的身高，k是排在这个人前面且身高大于或等于h的人数。 编写一个算法来重建这个队列。

注意：
总人数少于1100人。

示例

```
输入:
[[7,0], [4,4], [7,1], [5,0], [6,1], [5,2]]

输出:
[[5,0], [7,0], [5,2], [6,1], [4,4], [7,1]]
```

```java
有点难
class Solution {
    public int[][] reconstructQueue(int[][] people) {
        //首先对数组进行排序（按第一个关键字升序，第二个关键字降序）
        Arrays.sort(people, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if(o1[0] != o2[0]){
                    return o1[0]-o2[0];////返回0则o1和o2的位置不变，返回负数也不变，返回正数则交换位置
                }else{
                    return o2[1]-o1[1];
                }
            }
        });
        int n = people.length;
        int[][] ans = new int[n][];
        for(int[] person:people){
            int space = person[1]+1;
            for (int i = 0; i <n ; i++) {
                if(ans[i] == null){
                    --space;
                    if(space == 0){
                        ans[i] = person;
                        break;
                    }
                }
            }
        }
        return ans;
    }
}
```



## 比较器（Comparator）

对数组或者List列表，或者Map(非hash)等排序是我们经常使用的一种处理数据的手段

Comparator接口是java中很方便的一个接口

我们只需要重写compare方法即可实现排序

int compare(Object o1, Object o2) 返回一个基本类型的整型
如果要按照升序排序，
则o1 小于o2，返回-1（负数），相等返回0，o1大于o2返回1（正数）
如果要按照降序排序
则o1 小于o2，返回1（正数），相等返回0，o1大于o2返回-1（负数）

使用方法：

```java
新建比较器并重写compare方法;
Comparator<User> comp=new Comparator<User>(){
    //升序
    public int compare(User o1,User o2){
        if(o1.getAge()>o2.getAge()){
            return 1;
        }else if(o1.getAge()==o2.getAge()){
            return 0;
        }else{
            return -1;
        }                   
    }
    
也可以新建内部类或者外部类如下
  public class UserAgeComparator implements Comparator<User> {  
         //升序
			public int compare(User o1,User o2){
				if(o1.getAge()>o2.getAge()){
                                     return 1;
                                 }else if(o1.getAge()==o2.getAge()){
                                      return 0;
                                 }else{
                                   return -1;
                                 }                   
			   }
}

    
        
使用比较器：
    users是可以排序的对象
    1.直接使用
    	Collections.sort(users,comp);	
                Collections.reverse(users);//反转排序
   2.用class继承就先实例化class
         UserAgeCompartor mc = new UserAgeCompartor ();
                Collections.sort(list, mc);
    
    
    
Eg:::
    
    public static void main(String[] args) {
        Integer[] a =new Integer[]{2,5};
        Arrays.sort(a, new Comparator<Integer>() {//泛型里面装的对象（封装类）
            @Override
            public int compare(Integer o1, Integer o2) {
                if (o1 != o2){
                    return  (o1-o2);
                }else{
                    return 0;
                }
            }
        });
        System.out.println(Arrays.toString(a));
    }
```

# [1030. 距离顺序排列矩阵单元格](https://leetcode-cn.com/problems/matrix-cells-in-distance-order/)

## hashmap

我自己的想法是把距离当作Key,对应的单元格当成value.显然这样是不行的，因为hashmap的key是不可以重复的

基于哈希表的实现的`Map`接口。 此实现提供了所有可选的Map操作

```java
public int size();返回此地图中键值映射的数量;
public boolean isEmpty()如果此地图不包含键值映射，则返回 true;
public V get(Object key)返回到指定键所映射的值，或null如果此映射包含该键的映射;
public V put(K key,V value)将指定的值与此映射中的指定键相关联。 如果地图先前包含了该键的映射，则替换旧值;
public boolean containsKey(Object key)如果此映射包含指定键的映射，则返回 true ;
public void putAll(Map<? extends K,? extends V> m)将指定地图的所有映射复制到此地图。 这些映射将替换此映射对当前在指定映射中的任何键的任何映射。;
public V remove(Object key)从该地图中删除指定键的映射（如果存在）;
public void clear()从这张地图中删除所有的映射。 此呼叫返回后，地图将为空;

public Set<K> keySet()返回此地图中包含的键的Set视图。 该集合由地图支持，因此对地图的更改将反映在集合中，反之亦然。 如果在集合中的迭代正在进行中修改映射（除了通过迭代器自己的remove操作），迭代的结果是未定义的。 该组支持元件移除，即从映射中相应的映射，经由Iterator.remove，Set.remove，removeAll，retainAll和clear操作。 它不支持add或addAll操作;

public Collection<V> values()返回此地图中包含的值的Collection视图。 集合由地图支持，因此对地图的更改将反映在集合中，反之亦然。 如果在集合中的迭代正在进行中修改映射（除了通过迭代器自己的remove操作），迭代的结果是未定义的。 该collection支持元素移除，即从映射中相应的映射，经由Iterator.remove，Collection.remove，removeAll，retainAll和clear操作。 它不支持add或addAll操作;


```



```java

    


class Solution {
    public int[][] allCellsDistOrder(int R, int C, int r0, int c0) {
    int[][] re = new int[R*C][]; //定义一个可以容纳所有的坐标的二维数组
        
        //装下所有的数组
                for (int r = 0; r <R ; r++) {
            for (int c = 0; c <C ; c++) {
                re[r*C+c] = new int[]{r,c};
            }
                }
        //使用Arrays直接操作二维数组，使用Comparator指定的规则排序
        Arrays.sort(re, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return ((Math.abs(o1[0]-r0) + Math.abs(o1[1]-c0))-(Math.abs(o2[0]-r0) + Math.abs(o2[1]-c0)));
            }
        });
                return re;

    }
}
这种方法的复杂度有点高
    
    
  方法二：使用桶排序
```

## 桶排序

# [134. 加油站](https://leetcode-cn.com/problems/gas-station/)

```java
//在一条环路上有 N 个加油站，其中第 i 个加油站有汽油 gas[i] 升。 
//
// 你有一辆油箱容量无限的的汽车，从第 i 个加油站开往第 i+1 个加油站需要消耗汽油 cost[i] 升。你从其中的一个加油站出发，开始时油箱为空。 
//
// 如果你可以绕环路行驶一周，则返回出发时加油站的编号，否则返回 -1。 
//
// 说明: 
//
// 
// 如果题目有解，该答案即为唯一答案。 
// 输入数组均为非空数组，且长度相同。 
// 输入数组中的元素均为非负数。 
// 
//
// 示例 1: 
//
// 输入: 
//gas  = [1,2,3,4,5]
//cost = [3,4,5,1,2]
//
//输出: 3
//
//解释:
//从 3 号加油站(索引为 3 处)出发，可获得 4 升汽油。此时油箱有 = 0 + 4 = 4 升汽油
//开往 4 号加油站，此时油箱有 4 - 1 + 5 = 8 升汽油
//开往 0 号加油站，此时油箱有 8 - 2 + 1 = 7 升汽油
//开往 1 号加油站，此时油箱有 7 - 3 + 2 = 6 升汽油
//开往 2 号加油站，此时油箱有 6 - 4 + 3 = 5 升汽油
//开往 3 号加油站，你需要消耗 5 升汽油，正好足够你返回到 3 号加油站。
//因此，3 可为起始索引。 
//
// 示例 2: 
//
// 输入: 
//gas  = [2,3,4]
//cost = [3,4,3]
//
//输出: -1
//
//解释:
//你不能从 0 号或 1 号加油站出发，因为没有足够的汽油可以让你行驶到下一个加油站。
//我们从 2 号加油站出发，可以获得 4 升汽油。 此时油箱有 = 0 + 4 = 4 升汽油
//开往 0 号加油站，此时油箱有 4 - 3 + 2 = 3 升汽油
//开往 1 号加油站，此时油箱有 3 - 3 + 3 = 3 升汽油
//你无法返回 2 号加油站，因为返程需要消耗 4 升汽油，但是你的油箱只有 3 升汽油。
//因此，无论怎样，你都不可能绕环路行驶一周。 
// Related Topics 贪心算法


import java.util.concurrent.ForkJoinPool;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int canCompleteCircuit(int[] gas, int[] cost) {
        int len = gas.length;
        int begin = 0;
        int index = -1;
        int flag = 0;
        for (int k = 0; k < len; k++) {
            if ((gas[k] - cost[k]) >= 0) {
                index = k;

                for (int i = k; i < len; i++) {
                    if (index == i && flag == 1) return index;
                    begin = begin + gas[i];
                    begin = begin - cost[i];
                    if (begin < 0) {
                        begin = 0;
                        index = -1;
                        flag = 0;
                        break;
                    }
                    if ((i+1)==len) {
                        i = -1;
                        flag = 1;
                    }
                }
            }
    }
        return -1;
        }

    }
//leetcode submit region end(Prohibit modification and deletion)
效率太低了
```

**方法二：**

题解：
    这个图是从0点（i=0,即第一个站）出发的折线图，那么改变出发点时，这个图会怎么变化呢？你可以自己去画一画，你会发现，整体折线图的形状是没有变的，改变的是y值，相当于将折线图在Y轴方向上上下平移。那么，当最小点落在X轴上时（也就是使得最小点y=0时），整体折线在X轴上方，y值恒大于等于0，也就是剩余油量一直不为负，可以绕行一圈。对于本例，也就是使得i=3时，y=0。此时，意味着从i=3，第四个站出发，到此站时即没有加油也没有耗油，剩余油量为0



也就是说从某个起点到在回到某个起点这个油的剩余是要一直都是大于等于0的。



![image-20201119084755411](C:\Users\hp\AppData\Roaming\Typora\typora-user-images\image-20201119084755411.png)



```java
public int canCompleteCircuit(int[] gas, int[] cost) {
    int len = gas.length;
    int spare = 0;
    int minSpare = Integer.MAX_VALUE;
    int minIndex = 0;

    for (int i = 0; i < len; i++) {
        spare += gas[i] - cost[i];
        if (spare < minSpare) {
            minSpare = spare;
            minIndex = i;
        }
    }

    return spare < 0 ? -1 : (minIndex + 1) % len;
}
```

## %运算符

把自己搞蒙了，%是取模运算（取余）不是取小数

# [283. 移动零](https://leetcode-cn.com/problems/move-zeroes/)

最简单的方法就是像冒泡排序一样把0向后面丢就可以了

```java
//给定一个数组 nums，编写一个函数将所有 0 移动到数组的末尾，同时保持非零元素的相对顺序。 
//
// 示例: 
//
// 输入: [0,1,0,3,12]
//输出: [1,3,12,0,0] 
//
// 说明: 
//
// 
// 必须在原数组上操作，不能拷贝额外的数组。 
// 尽量减少操作次数。 
// 
// Related Topics 数组 双指针


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public void moveZeroes(int[] nums) {
        for (int i = 0; i <nums.length ; i++) {
            for (int j = i+1; j <nums.length ; j++) {
                if (nums[i] == 0) {
                    nums[i] = nums[j];
                    nums[j]=0;
                }


            }

        }
    }
}
//leetcode submit region end(Prohibit modification and deletion)

```

# [242. 有效的字母异位词](https://leetcode-cn.com/problems/valid-anagram/)

# [452. 用最少数量的箭引爆气球](https://leetcode-cn.com/problems/minimum-number-of-arrows-to-burst-balloons/)

```java
//在二维空间中有许多球形的气球。对于每个气球，提供的输入是水平方向上，气球直径的开始和结束坐标。由于它是水平的，所以纵坐标并不重要，因此只要知道开始和结束的横
//坐标就足够了。开始坐标总是小于结束坐标。 
//
// 一支弓箭可以沿着 x 轴从不同点完全垂直地射出。在坐标 x 处射出一支箭，若有一个气球的直径的开始和结束坐标为 xstart，xend， 且满足 xsta
//rt ≤ x ≤ xend，则该气球会被引爆。可以射出的弓箭的数量没有限制。 弓箭一旦被射出之后，可以无限地前进。我们想找到使得所有气球全部被引爆，所需的弓箭的
//最小数量。 
//
// 给你一个数组 points ，其中 points [i] = [xstart,xend] ，返回引爆所有气球所必须射出的最小弓箭数。 
// 
//
// 示例 1： 
//
// 
//输入：points = [[10,16],[2,8],[1,6],[7,12]]
//输出：2
//解释：对于该样例，x = 6 可以射爆 [2,8],[1,6] 两个气球，以及 x = 11 射爆另外两个气球 
//
// 示例 2： 
//
// 
//输入：points = [[1,2],[3,4],[5,6],[7,8]]
//输出：4
// 
//
// 示例 3： 
//
// 
//输入：points = [[1,2],[2,3],[3,4],[4,5]]
//输出：2
// 
//
// 示例 4： 
//
// 
//输入：points = [[1,2]]
//输出：1
// 
//
// 示例 5： 
//
// 
//输入：points = [[2,3],[2,3]]
//输出：1
// 
//
// 
//
// 提示： 
//
// 
// 0 <= points.length <= 104 
// points[i].length == 2 
// -231 <= xstart < xend <= 231 - 1 
// 
// Related Topics 贪心算法 排序


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int findMinArrowShots(int[][] points) {
        int min = 0;
        int len =points.length;
        List<Integer[]> temp = new ArrayList<>();
        for (int i = 0; i <len ; i++) {
            Integer[] t = new Integer[]{points[i][0],points[i][1]};
            temp.add(t);
        }
        while (!temp.isEmpty()) {
            if(temp.size()==1){
                min = min+1;
            }
            Integer[] one = temp.get(0);
            for (int i = 1; i <temp.size() ; i++) {
                Integer[] next = temp.get(i);
                if((one[0] <=next[0]  && one[1] >= next[0]) || (next[0] <= one[0] && next[1] >=one[0])){
                    temp.remove(next);
                }
            }
            min++;

        }
        return min;
    }
}
超时------------------
//leetcode submit region end(Prohibit modification and deletion)

```

```java
两个是一样的原理：排序的写法不一样
class Solution {
    public int findMinArrowShots(int[][] points) {
        if (points.length == 0) {
            return 0;
        }
        Arrays.sort(points, new Comparator<int[]>() {
            public int compare(int[] point1, int[] point2) {
                if (point1[1] > point2[1]) {
                    return 1;
                } else if (point1[1] < point2[1]) {
                    return -1;
                } else {
                    return 0;
                }
            }
        });
        int pos = points[0][1];
        int ans = 1;
        for (int[] balloon: points) {
            if (balloon[0] > pos) {
                pos = balloon[1];
                ++ans;
            }
        }
        return ans;
    }
}

+++++++++++++++++++++++++++++++++++这是分界线++++++++++++++++++++++++++++++++
    
    

class Solution {
    public int findMinArrowShots(int[][] points) {
        if(points.length == 0) return 0;
        Arrays.sort(points, (p1, p2) -> p1[1] < p2[1] ? -1 : 1);lambda表达式
        int res = 1;
        int pre = points[0][1];
        for (int i = 1; i < points.length; i++) {
            if (points[i][0] > pre) {
                res++;
                pre = points[i][1];
            }
        }
        return res;

    }
}


```

## lambda

# [222. 完全二叉树的节点个数](https://leetcode-cn.com/problems/count-complete-tree-nodes/)（太难了）

绝对可以用递归

```java
果然
    class Solution {
    public int countNodes(TreeNode root) {
    	if(root == null) {
    		return 0;
    	}
    	int left = countNodes(root.left);
    	int right = countNodes(root.right);
    	
    	return left+right+1;
    	
    }
}    
```



## 树的知识

### 完全二叉树

当 $0<=i<=h$时，第$ i$层包含$2^i$个节点，最底层包含的节点数最少为 1，最多为$2^h$

当最底层包含 1个节点时，完全二叉树的节点个数是
$$
\sum_{i=0}^{h-1} 2^{i}+1=2^{h}
$$
当最底层包含$2^h$个节点时，完全二叉树的节点个数是
$$
\sum_{i=0}^{h} 2^{i}=2^{h+1}-1
$$
因此对于最大层数为 hhh 的完全二叉树，节点个数一定在$\left[2^{h}, 2^{h+1}-1\right]$ 的范围内，可以在该范围内通过二分查找的方式得到完全二叉树的节点个数

## 二分查找

## 位运算

# [1370. 上升下降字符串](https://leetcode-cn.com/problems/increasing-decreasing-string/)

```java
//给你一个字符串 s ，请你根据下面的算法重新构造字符串： 
//
// 
// 从 s 中选出 最小 的字符，将它 接在 结果字符串的后面。 
// 从 s 剩余字符中选出 最小 的字符，且该字符比上一个添加的字符大，将它 接在 结果字符串后面。 
// 重复步骤 2 ，直到你没法从 s 中选择字符。 
// 从 s 中选出 最大 的字符，将它 接在 结果字符串的后面。 
// 从 s 剩余字符中选出 最大 的字符，且该字符比上一个添加的字符小，将它 接在 结果字符串后面。 
// 重复步骤 5 ，直到你没法从 s 中选择字符。 
// 重复步骤 1 到 6 ，直到 s 中所有字符都已经被选过。 
// 
//
// 在任何一步中，如果最小或者最大字符不止一个 ，你可以选择其中任意一个，并将其添加到结果字符串。 
//
// 请你返回将 s 中字符重新排序后的 结果字符串 。 
//
// 
//
// 示例 1： 
//
// 输入：s = "aaaabbbbcccc"
//输出："abccbaabccba"
//解释：第一轮的步骤 1，2，3 后，结果字符串为 result = "abc"
//第一轮的步骤 4，5，6 后，结果字符串为 result = "abccba"
//第一轮结束，现在 s = "aabbcc" ，我们再次回到步骤 1
//第二轮的步骤 1，2，3 后，结果字符串为 result = "abccbaabc"
//第二轮的步骤 4，5，6 后，结果字符串为 result = "abccbaabccba"
// 
//
// 示例 2： 
//
// 输入：s = "rat"
//输出："art"
//解释：单词 "rat" 在上述算法重排序以后变成 "art"
// 
//
// 示例 3： 
//
// 输入：s = "leetcode"
//输出："cdelotee"
// 
//
// 示例 4： 
//
// 输入：s = "ggggggg"
//输出："ggggggg"
// 
//
// 示例 5： 
//
// 输入：s = "spo"
//输出："ops"
// 
//
// 
//
// 提示： 
//
// 
// 1 <= s.length <= 500 
// s 只包含小写英文字母。 
// 
// Related Topics 排序 字符串


import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public String sortString(String s) {
        int[] number = new int[26];
        for (int i = 0; i <s.length() ; i++) {
            number[s.charAt(i)-'a']++;
        }
        StringBuffer ret = new StringBuffer();
        while(ret.length() < s.length()){
            for (int i = 0; i <26 ; i++) {
                if(number[i] > 0){
                ret.append((char)(i+'a'));
                number[i]--;
            }
              }
            for (int i = 25; i >=0 ; i--) {
                if(number[i] >0){
                    ret.append((char)(i+'a'));
                    number[i]--;
                }
            }
        }
        return ret.toString();
    }
}
//leetcode submit region end(Prohibit modification and deletion)

```









# 字符串

## 1. charAt

```
public char charAt(int index)  返回指定位置的字符
```

## 2. StringBuffer

StringBuffer ret = new StringBuffer();

可以向python中列表一样添加字符

```java
StringBuffer ret = new StringBuffer();
ret.append("ssss");
ret.append('h');
ret.append(2);
StringBuffer kk = new StringBuffer("hahahahhahaha");
ret.append(kk);
System.out.println(ret.toString());

"""
    append() 可以添加很多东西，如下面的图 所有的应该都会转化为字符
"""
```



![image-20201125203905318](C:\Users\hp\AppData\Roaming\Typora\typora-user-images\image-20201125203905318.png)

