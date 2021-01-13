package com.CollectionToolClass;


import org.junit.Test;

import javax.sound.midi.Soundbank;
import java.util.*;

/**
 * @Author OZR
 * @Date 2020/9/19 21:23
 */
/*
Collections 是一个操作 Set 、 List 和 Map 等集合的工具类
Collections 中提供了一系列静态的方法对集合元素进行排序、查询和修改等操作，还提供了对集合对象设置不可变、对集合对象实现同步控制等方法
        排序操作 ：（均为 static 方法）
        reverse(List) 反转 List 中元素的顺序
        shuffle(List) 对 List 集合元素进行随机排序
        sort(List) 根据元素的自然顺序对指定 List 集合元素按升序排序
        sort(List Comparator) 根据指定的 Comparator 产生的顺序对 List 集合元素进行排序
        swap(List int int 将指定 list 集合中的 i 处元素和 j 处元素进行交换
*/
/*
* 查找、替换
Object max(Collection) 根据元素的自然顺序，返回给定集合中的最大元素
Object max(Collection Comparator) 根据 Comparator 指定的顺序，返回给定集合中的最大元素
Object min(Collection)
Object min(Collection Comparator)
int frequency(Collection Object) 返回指定集合中指定元素的出现次数
void copy(List dest,List src ))：将 src 中的内容复制到 dest 中
boolean replaceAll (List list Object oldVal Object newVal 使用新值替换
*
*
*
* */
public class CollectionToolClassTest {
    @Test
    public void test1(){
        ArrayList list = new ArrayList();
        list.add(122);
        list.add(12);
        list.add(2);
        list.add(22);
        list.add(1);
        list.add(-122);
        System.out.println(list);//[122, 12, 2, 22, 1, -122]

        Collections.reverse(list);//直接对原list进行的修改
        System.out.println(list);//[-122, 1, 22, 2, 12, 122]

        Collections.shuffle(list);
        System.out.println(list);

        Collections.sort(list);//根据元素的自然顺序对指定 List 集合元素按升序排序
        System.out.println(list);

        Collections.swap(list,2,2);
        System.out.println(list);
    }

    @Test
    public void test2(){
        ArrayList list = new ArrayList();
        list.add(122);
        list.add(12);
        list.add(2);
        list.add(22);
        list.add(1);
        list.add(-122);
        System.out.println(list);//[122, 12, 2, 22, 1, -122]

//        void copy(List dest,List src ))：将 src 中的内容复制到 dest 中
     //============================
     /*   ArrayList dest = new ArrayList();
        Collections.copy(dest,list);//报错，因为list的长度为5，dest的长度为0（没添加元素）*/

     /*   ArrayList dest = new ArrayList(list.size());//这个是让底层的数组长度为list.size()
        System.out.println(dest.size());//0  dest.size()是说这个动态的数组的长度（也就是数据的大小，当然为0）
                                       // 这儿的copy还是会出错
        Collections.copy(dest,list);*/


        List dest = Arrays.asList(new Object[list.size()]);//这就是创建一个与list一样长的dast
        System.out.println(dest.size());//6
        System.out.println(dest);//[null, null, null, null, null, null]
        Collections.copy(dest,list);
        System.out.println(dest);//[122, 12, 2, 22, 1, -122]



        /*
        Collections 类中提供了多个 synchronizedXxx () 方法，该方法可使将指定集
        合包装成线程同步的集合，从而可以解决多线程并发访问集合时的线程安全
         */
        List list1 = Collections.synchronizedList(list);//此时的list1就会变成线程安全的
    }
}
