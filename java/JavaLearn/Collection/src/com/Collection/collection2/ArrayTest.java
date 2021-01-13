package com.Collection.collection2;

import java.util.ArrayList;
/*
* 数组的长度可以该变
* ArrayList<String>中<String>泛型
* 泛型 也就是说装在集合里的所有元素都是统一的类型
* 注意：泛型只能是引用类型  不能是基本类型
*
*
* */
public class ArrayTest {
    public static void main(String[] args) {
        ArrayList<String> list = new ArrayList<>();//<String>表明里面只能放String
    //    System.out.println(list);//直接打印的是数组的内容而不是地址

        //向集合中添加数据  add()
        list.add("what is fuck");
        list.add("you are ");
        list.add("long");
        System.out.println(list);
    }
}
