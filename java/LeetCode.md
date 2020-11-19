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

## [283. 移动零](https://leetcode-cn.com/problems/move-zeroes/)

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

