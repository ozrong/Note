package com.Collection.list;

import org.junit.Test;

import java.net.Socket;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.Collection.collection1.Penson;

/**
 * @Author OZR
 * @Date 2020/9/17 12:17
 * List 除了从 Collection 集合继承的方法外， List 集合里添加了一些根据索引来
 * 操作集合元素的方法:
 * 1.void add( int index, Object ele 在 index 位置插入 ele 元素
 * 2.boolean addAll int index, Collection eles 从 index 位置开始将 eles 中
 * 的所有元素添加进来
 * 3.Object get( int index): 获取指定 index 位置的元素
 * 4.int indexOf (Object obj 返回 obj 在集合中首次出现的位置
 * 5.int lastIndexOf (Object obj 返回 obj 在当前集合中末次出现的位置
 * 6.Object remove( int index): 移除指定 index 位置的元素，并返回此元素
 * 7.Object set( int index, Object ele 设置指定 index 位置的元素为 ele
 * 8.List subList int fromIndex , int toIndex 返回从 fromIndex 到 toIndex
 * 位置的子集合
 *
 *
 *
 *
 * 以ArrayList为例
 */
public class ListTest {
    @Test
    public void test1(){
        ArrayList list = new ArrayList();
        list.add(123);
        list.add(456);
        list.add("AA");
        list.add(new Penson("ozr",12));
        list.add(456);

        System.out.println(list.toString());



//        1.void add( int index, Object ele 在 index 位置插入 ele 元素

        list.add(1,"KK");//在索引为1的地方添加"KK"
        System.out.println(list.toString());
//         2.boolean addAll int index, Collection eles 从 index 位置开始将 eles 中的所有元素添加进来
        List list1 = Arrays.asList(1, 2, 3);
        list.addAll(list1);
        System.out.println(list.size());//9

//        list.add(list1);//这是会将list1看作一个元素添加到list中
//        System.out.println(list.size());//7

//         3.Object get( int index): 获取指定 index 位置的元素
        System.out.println(list.get(2));//456



    }
    @Test
    public void test2(){
        ArrayList list = new ArrayList();
        list.add(123);
        list.add(456);
        list.add("AA");
        list.add(new Penson("ozr",12));
        list.add(456);
        int index = list.indexOf(4567);

//         4.int indexOf (Object obj 返回 obj 在集合中首次出现的位置
        System.out.println(index);//-1,没找到返回-1,找到了返回首次出现的索引
        System.out.println(list.indexOf(123));//0

//         5.int lastIndexOf (Object obj 返回 obj 在当前集合中末次出现的位置

//         6.Object remove( int index): 移除指定 index 位置的元素，并返回此元素
        Object ele = list.remove(0);
        System.out.println(ele);//123


//         7.Object set( int index, Object ele 设置指定 index 位置的元素为 ele
        list.set(1,8888);
        System.out.println(list);
//         8.List subList int fromIndex , int toIndex 返回从 fromIndex 到 toIndex
        List sublist = list.subList(2,4);
        System.out.println(list);//[456, 8888, Penson{name='ozr', age=12}, 456]
        System.out.println(sublist);//[Penson{name='ozr', age=12}, 456]
        //不会改变原来的list

    }
}
