package com.toolClass.array;

import java.util.Arrays;

/*  java.util.Arrays是数组操作相关的方法（工具类）
*    提供了大量的静态方法，来操作数组
*  public static String toString(数组) 将数组变为字符串
*  public static void sort(数组)按照升序对数组排序.直接改变原数组并不会返回新的数组
*                    数值就是从大到小
*                    字符串就是字母的升序
*                    自定义的类型 那么这个自定义的类要有comparable或者comparator的支持
* */
public class ArraysTest {
    public static void main(String[] args) {
        int[] intArray = {0,5,8,9,6,3,41,25,89,78,14};
        Arrays.sort(intArray);
        System.out.println(Arrays.toString(intArray));

    }
}
