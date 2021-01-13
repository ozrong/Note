package com.Map.TreeMap;


import org.junit.Test;

import java.util.Comparator;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeMap;

/**
 * @Author OZR
 * @Date 2020/9/19 20:56
 */
public class TreeMapTest {
    //TreeMap是按照Key排序的，所以这个Key一定是一个类创造的对象，排序当然是之前的哪两种方式(自然排序，定制排序)

    //自然排序
    @Test
    public void test1() {

        TreeMap tree = new TreeMap();
        User u1 = new User("tom", 91);
        User u2 = new User("jim", 81);
        User u3 = new User("merry", 11);
        User u4 = new User("Rose", 21);
        tree.put(u1, 98);
        tree.put(u2, 89);
        tree.put(u3, 76);
        tree.put(u4, 100);

        Set keySet1 = tree.keySet();
        Iterator iterator2 = keySet1.iterator();
        while (iterator2.hasNext()) {
            Object key = iterator2.next();
            Object va = tree.get(key);
            System.out.println(key + "---->" + va);

        }
    }
    //定制排序
    @Test
    public void test2() {

        TreeMap tree = new TreeMap(new Comparator() {
            @Override
            public int compare(Object o1, Object o2) {
                if (o1 instanceof User && o2 instanceof User){
                    User u1 = (User)o1;
                    User u2 = (User)o2;
                    return Integer.compare(u1.getAge(),u2.getAge());
                }else{
                    throw new RuntimeException("不配");
                }
            }
        });
        User u1 = new User("tom", 91);
        User u2 = new User("jim", 81);
        User u3 = new User("merry", 11);
        User u4 = new User("Rose", 21);
        tree.put(u1, 98);
        tree.put(u2, 89);
        tree.put(u3, 76);
        tree.put(u4, 100);

        Set keySet1 = tree.keySet();
        Iterator iterator2 = keySet1.iterator();
        while (iterator2.hasNext()) {
            Object key = iterator2.next();
            Object va = tree.get(key);
            System.out.println(key + "---->" + va);

        }
    }


}
