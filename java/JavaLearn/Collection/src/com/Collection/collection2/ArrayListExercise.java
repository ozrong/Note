package com.Collection.collection2;
import java.util.ArrayList;
/*
* 打印指定格式{元素之间用@分隔}
*
* */
public class ArrayListExercise {
    public static void main(String[] args) {
        ArrayList<String> list = new ArrayList<>();
        list.add("张三丰");
        list.add("宋元桥");
        list.add("掌握及");
        list.add("张翠山");
        printArrayList(list);
    }
    public static void printArrayList(ArrayList<String> list){
        System.out.print("{");
        for (int i = 0; i <list.size() ; i++) {
            System.out.print(list.get(i));
            if(i==list.size()-1)
            System.out.println("}");
            else System.out.print("@");
        }
    }
    void a(){

    }
}
