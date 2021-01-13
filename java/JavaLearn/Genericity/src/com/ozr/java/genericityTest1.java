package com.ozr.java;

import org.junit.Test;

import java.awt.print.PrinterGraphics;
import java.util.List;

/**
 * @Author OZR
 * @Date 2021/1/4 20:33
 *
 *
 *
 * 3.自定义一个泛型类
 */
public class genericityTest1 {
    @Test
    public void test1(){

        //定义了泛型，实例化没用使用泛型  则认为是使用的是Object(不建议)
//        Order<Object> objectOrder = new Order<>();

        Order<String> stringOrder1 = new Order<>("orderAA",1001,"orderaa:1011");
        stringOrder1.setOrderT("AA:445");
    }

    @Test
    public void test2(){
        SubOrder subOrder = new SubOrder();

        SubOrder1<Integer> integerSubOrder1 = new SubOrder1<>();

    }

    @Test
    public  void test33(){
        Order<String>  order = new Order<>();
        Integer[] arr = new Integer[]{1,2,3,4};
        List<Integer> integers = order.copyFromArrayToList(arr);
        System.out.println(integers);


    }
}
