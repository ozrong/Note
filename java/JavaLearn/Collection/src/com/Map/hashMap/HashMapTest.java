package com.Map.hashMap;

import org.junit.Test;
import sun.nio.cs.ext.MacArabic;

import java.net.SocketTimeoutException;
import java.util.*;

/**
 * @Author OZR
 * @Date 2020/9/17 22:06

|----Map:双列数据，存储key-value对的数据   ---类似于高中的函数：y = f(x)
        *       |----HashMap:作为Map的主要实现类；线程不安全的，效率高；存储null的key和value
        *              |----LinkedHashMap:保证在遍历map元素时，可以照添加的顺序实现遍历。
        *                    原因：在原的HashMap底层结构基础上，添加了一对指针，指向前一个和后一个元素。
        *                    对于频繁的遍历操作，此类执行效率高于HashMap。
        *       |----TreeMap:保证照添加的key-value对进行排序，实现排序遍历。此时考虑key的自然排序或定制排序
        *                      底层使用红黑树
        *       |----Hashtable:作为古老的实现类；线程安全的，效率低；不能存储null的key和value
        *              |----Properties:常用来处理配置文件。key和value都是String类型
        *
        *
        *      HashMap的底层：数组+链表  （jdk7及之前)
        *                    数组+链表+红黑树 （jdk 8)
*/


/*
* Map中的方法

添加 、 删除、修改操作

1.Object put(Object key,Object value) value)：将 指定 key value 添加到 或修改 当前 map 对象中

2.void putAll(Map m): 将 m 中的所有 key value 对存放到当前 map 中

3.Object remove(Object key) key)：移除指定 key 的 key value 对，并返回 value

4.void clear()clear()：清空当前 map 中的所有数据

元素 查询的操作：

1.Object get(Object key) key)：获取指定 key 对应的 value

2.boolean containsKey(Object key) key)：是否包含指定的 key

3.boolean containsValue(Object value) value)：是否包含指定的 value

4.int size()size()：返回 map 中 key value 对的个数

5.boolean isEmpty()isEmpty()：判断当前 map 是否为空

6.boolean equals(Object obj) obj)：判断当前 map 和参数对象 obj 是否相等

元视图操作的方法：

1.Set keySet()keySet()：返回所有 key 构成的 Set 集合

2.Collection values()values()：返回所有 value 构成的 Collection 集合

3.Set entrySet()entrySet()：返回所有 key value 对构成的 Set 集合
*
*
 */



public class HashMapTest {
//    添加 、 删除、修改操作
    @Test
    public void test1(){
        Map map = new HashMap();
//1.Object put(Object key,Object value) value)：将 指定 key value 添加到 或修改 当前 map 对象中
        map.put("AA",123);
        map.put(45,123);
        map.put("BB",56);
        map.put("AA",87); //这儿其实是一个修改
        System.out.println(map);//{AA=87, BB=56, 45=123}
//2.void putAll(Map m): 将 m 中的所有 key value 对存放到当前 map 中
        Map map1 = new HashMap();
        map1.put("CC",123);
        map1.put("DD",123);
        map.putAll(map1);
        System.out.println(map);//{AA=87, BB=56, CC=123, DD=123, 45=123}

//3.Object remove(Object key) key)：移除指定 key 的 key value 对，并返回 value
        Object value = map.remove("CC");
        System.out.println(value);//123
        System.out.println(map);//{AA=87, BB=56, DD=123, 45=123}
//4.void clear()clear()：清空当前 map 中的所有数据
        map.clear();//清空数据而已(不是null是清空，啥也没有)，结构还是在的
        System.out.println(map.size());//0

    }


//元素 查询的操作：
    @Test
    public void test2(){
        Map map = new HashMap();
        map.put("AA",123);
        map.put(45,123);
        map.put("BB",56);

//1.Object get(Object key) key)：获取指定 key 对应的 value
        map.get("BB");
//2.boolean containsKey(Object key) key)：是否包含指定的 key
        boolean flag = map.containsKey("CC");
//3.boolean containsValue(Object value) value)：是否包含指定的 value
        boolean flag1 = map.containsValue(123);
//4.int size()size()：返回 map 中 key value 对的个数
        System.out.println(map.size());
//5.boolean isEmpty()isEmpty()：判断当前 map 是否为空
        boolean flag3 = map.isEmpty();
//6.boolean equals(Object obj) obj)：判断当前 map 和参数对象 obj 是否相等


    }

//元视图操作的方法：
//1.Set keySet()：返回所有 key 构成的 Set 集合
//2.Collection values()：返回所有 value 构成的 Collection 集合
//3.Set entrySet()：返回所有 key value 对构成的 Set 集合
    @Test
    public void test3(){
        Map map = new HashMap();
        map.put("AA",123);
        map.put(45,1234);
        map.put("BB",56);

        //1.Set keySet()keySet()：返回所有 key 构成的 Set 集合
        Set keySet = map.keySet();
        Iterator iterator = keySet.iterator();
        while (iterator.hasNext()){
            System.out.println(iterator.next());
        }
        System.out.println();
//        2.Collection values()：返回所有 value 构成的 Collection 集合
        Collection values = map.values();
        for(Object value:values){
            System.out.println(value);
        }
//3.Set entrySet()：返回所有 key value 对构成的 Set 集合
        Set entrySet = map.entrySet();
        Iterator iterator1 = entrySet.iterator();
        //输出方法一
        while(iterator1.hasNext()){
            Object obj = iterator1.next();//此时返回的其实是一个entry
            Map.Entry entry = (Map.Entry) obj;//强转;
            System.out.println(entry.getKey()+"------>"+entry.getValue());
        }
        //输出方法二
        Set keySet1 = map.keySet();
        Iterator iterator2 = keySet.iterator();
        while (iterator2.hasNext()){
            Object key = iterator2.next();
            Object va = map.get(key);
            System.out.println(key+"---->"+va);

        }
    }


    @Test
    public void test(){
        Map map = new HashMap();
//        Map map1 = new Hashtable();//不能存null
        map.put("null","null");
        map.clear();
        System.out.println(map);
    }

}
