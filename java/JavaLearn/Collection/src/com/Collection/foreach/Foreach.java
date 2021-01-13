package com.Collection.foreach;

import com.Collection.collection1.Penson;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collection;

/**
 * @Author OZR
 * @Date 2020/9/16 15:23
 *
 * foreach增强for循环
 * foreach也可以遍历collection,数组
 */
public class Foreach {
    @Test
    public void test1() {
        Collection coll = new ArrayList();
        coll.add(123);
        coll.add(456);
        coll.add(new String("tom"));
        coll.add(false);
        coll.add(new Penson("ozr", 12));

//        for(集合对象的类型  局部变量名  ： 集合对象)  (数组一样)
//        内部其实使用的迭代器
        for(Object obj : coll){
            System.out.println(obj);
    //如果在这儿改变obj的值是改变不了原来coll的值的,因为这是coll把值赋给了obj,改变obj是不会影响到coll的

        }
    }

}
