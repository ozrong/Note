package com.Collection.set;


import org.junit.Test;

import java.util.Comparator;
import java.util.Iterator;
import java.util.TreeSet;

/**
 * @Author OZR
 * @Date 2020/9/17 15:10
 *
 *
 *
 * 1.向TreeSet中添加数据,要求是相同类的对象
 * 2.两种排序方式：自然排序（实现Comparable接口,那个类里面) 和 定制排序（Comparator,就在使用TreeSet的类里面实现comparator的对象）
 *
 *
 */
public class TreeSetTest {
    @Test
    public void test1(){

        TreeSet set = new TreeSet();
    //    set.add(123);
    //    set.add(456);
    //    set.add("AA");
    //    错误示例
        set.add(34);
        set.add(-34);
        set.add(341);
        set.add(4);
        Iterator iterator = set.iterator();
        while (iterator.hasNext()){
            System.out.println(iterator.next());
        }
//        -34
//        4
//        34
//        341
//        显然是从小到到排序的
    }


    @Test
    public void test2(){
        TreeSet set = new TreeSet();
        set.add(new User("tom",5));
        set.add(new User("tim",452));
        set.add(new User("jim",8));
        set.add(new User("jack",162));
        set.add(new User("merry",62));


        set.add(new User("merry",45));//添加不了,因为这个比较不同是使用的这个类里面的compareTO()这个方法(自定义的)
        //这个compareTO()比较的是name,这个name和之前保存了所以不会在保存了
//        会报错,没有指定怎么排序
        Iterator iterator = set.iterator();
        while (iterator.hasNext()){
            System.out.println(iterator.next());
        }

    }

    @Test
    public void test3(){
        //按年龄从大到小排列

        //定制排序
        Comparator com = new Comparator() {
            @Override
            public int compare(Object o1, Object o2) {
                if (o1 instanceof  User && o2 instanceof  User  ){
                    User u1 = (User)o1;
                    User u2 = (User)o2;
                    return Integer.compare(u1.getAge(),u2.getAge());
                }else{
                    throw new RuntimeException("不配");
                }
            }
        };

        TreeSet set = new TreeSet(com); //就变成这个com的方法来比较了
        set.add(new User("tom",5));
        set.add(new User("tim",8));
        set.add(new User("jim",8));
        set.add(new User("jack",162));
        set.add(new User("merry",62));


        set.add(new User("merry",45));//没问题了,在com中比较的是age,这个age之前没出现过
//        会报错,没有指定怎么排序
        Iterator iterator = set.iterator();
        while (iterator.hasNext()){
            System.out.println(iterator.next());
        }



    }

}
