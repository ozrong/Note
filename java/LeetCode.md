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

