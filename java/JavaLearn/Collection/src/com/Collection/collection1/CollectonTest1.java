package com.Collection.collection1;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

/**
 * @Author OZR
 * @Date 2020/9/13 16:21
 *
 * z这是使用时没用使用泛型，
 *在 Java5 之前， Java 集合会丢失容器中所有对象的数据类型，把所有对象都
 * 当成 Object 类型处理； 从 JDK 5.0 增加了 泛型 以后， Java 集合可以记住容
 * 器中对象 的数据类型。
 *
 */
public class CollectonTest1 {
    @Test
    public void test(){
        Collection coll = new ArrayList();
        // add() 添加元素
        coll.add("AA");
        coll.add("bb");
        coll.add(123); //123会自动装箱
        coll.add(new Date());
        //size()获取元素个数
        System.out.println(coll.size());//4


        //addAll(collection coll)将coll的所有元素添加到某个collection中
        Collection coll1 = new ArrayList();
        coll1.add(456);
        coll1.add("cc");
        coll1.addAll(coll);
        System.out.println(coll1.size());//6
        System.out.println(coll1);//这样就可以直接输出元素

//        coll1.isEmpty() 判断集合是否分空
        System.out.println(coll1.isEmpty());//false


//        coll1.clear();将这个集合的元素清空，但是这个对象是存在的
        coll1.clear();
        System.out.println(coll1.isEmpty());//true








    }

}
