package com.Collection.set;

import com.Collection.collection1.Penson;
import org.junit.Test;

import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;


/**
 * @Author OZR
 * @Date 2020/9/17 15:03
 *
 * LinkedHashSet的使用
 *
 * LinkedHashSet这是hashset的一个子类
 *
 */
public class LinkedHashSetTest {
    @Test
    public void test1(){
        Set set = new LinkedHashSet();
        set.add(456);
        set.add(123);
        set.add("AA");
        set.add("CC");
        set.add(new Penson("ton",12));
        set.add(129);
        Iterator iterator = set.iterator();
        while (iterator.hasNext()){
            System.out.println(iterator.next());
        }


    }
}
