package com.Collection.collection2;

import java.util.ArrayList;

/*
* 1.添加元素public boolean add(E e)
* 2.取出元素 get(位置)
* 3.删除 remove(位置)
* 4.获取元素的个数 size()
*
*
* */
public class ArrayListComMethod {
    public static void main(String[] args) {
        ArrayList<String> list1 = new ArrayList<>();
        list1.add("what");//添加元素
        list1.add("what1");//添加元素
        list1.add("what2");//添加元素
        list1.add("what3");//添加元素

        String value = list1.get(2);//获得元素
        System.out.println(value);

        String value1=list1.remove(3);//删除元素  返回值是被删除的值
        System.out.println(value1);

        System.out.println(list1.size());//获取元素的个数


        //遍历集合
        for (int i = 0; i <list1.size() ; i++) {
            System.out.println(list1.get(i));

        }

        for (int i = 0; i < list1.size(); i++) {//list1.fori  快捷键
        }
    //list1.fori



    }

}
