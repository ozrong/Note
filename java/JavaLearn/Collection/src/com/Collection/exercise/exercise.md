1.定义一个 Employee 类。  
该类包含：
private 成员变量 name,age,birthday ，其中 birthday 为MyDate 类的对象；
并为每一个属性定义getter, setter 方法；
并重写toString 方法输出 name, age, birthday
MyDate类包含
private 成员变量 year,month,day ；并为每一个属性定义 getter, setter方法；
创建该类的5 个对象，并把这些对象放入 TreeSet 集合中（ 下一章：TreeSet 需使用泛型来定义）
分别按以下两种方式对集合中的元素进行排序，并遍历输出：
1).使 Employee 实现 Comparable 接口，并按 name 排序
2).创建 TreeSet 时传入 Comparator 对象，按生日日期的先后排序。