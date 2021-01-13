package com.Collection.collection2;

import java.util.ArrayList;
/* 基本类型
*  byte            Byte
*  short           Short
*  int             Integer
*  long            Long
*  float           Float
*  double          Double
*  char            Character
*  boolean         Boolean
*
*
*
* */
public class ArrayListTest1 {
    public static void main(String[] args) {
        ArrayList<String> list1 = new ArrayList<String>();
     //   ArrayList<int> list2 = new ArrayList<>();//这是错误的  泛型只能是引用类型  不能是基本类型
      //解决方法：如果希望ArratList存储基本类型的数据，只需要使用对应的封装类
        ArrayList<Integer> list3 = new ArrayList<>(); //就可以存储数字

    }
}
