package com.ozr.java;

import org.junit.Test;

import java.util.*;

/**
 * @Author OZR
 * @Date 2021/1/4 19:47
 *
 *
 *
 *
 *1. JDK5.0新增加的特性
 *
 *
 * 2.在集合中使用泛型
 *
 * 在声明时没使用泛型，默认使用的Object类型
 *
 *
 *
 *
 *
 *
 */
public class genericityTest {
    @Test
    public void test(){

        //jdk5.0之前
        ArrayList list = new ArrayList();
        list.add(78);
        list.add(76);
        list.add(89);
        list.add(88);
        // 问题一 类型不安全
        list.add("tom");

        for(Object score : list){
            int score_a = (Integer)score;
            //注意这是将Integer转换为int，integer自动拆箱
            //同时之前的Arraylist是没有泛型来限制的，所以这个list里面可能存在不能转换的数据
            //所以这个一般会出现classCastException,使用异常处理。
            System.out.println(score_a);
        }



    }
    @Test
    public void test2(){
//        ArrayList<int> list = new ArrayList<int>();这是不行的，泛型不能是基本数据类型
        ArrayList<Integer> list = new ArrayList<Integer>();
        list.add(78);
        list.add(78);
        list.add(78);
        list.add(758);

       // list.add("tom");//编译就会进行数据类型检测，不是所有的类型就可以放进来。


        //读取方式一
        for(Integer socre:list){
            int out = socre;//强转就不用使用了
            System.out.println(out);
        }


        //读取方式二
        Iterator<Integer> iterator = list.iterator();
        while(iterator.hasNext()){
            int out = iterator.next();
            System.out.println(out);
        }



    }

    @Test
    public void test3(){
//        Map<String, Integer> map = new HashMap<>();//这样也是对的
        HashMap<String, Integer> map = new HashMap<>();
        map.put("sdad",544);
        map.put("as",45);
        map.put("aqe",4685132);
        map.put("qqe",85);

        //泛型的嵌套
        Set<Map.Entry<String, Integer>> entries = map.entrySet();
        Iterator<Map.Entry<String, Integer>> iterator = entries.iterator();
        while (iterator.hasNext()){
            Map.Entry<String, Integer> next = iterator.next();
            String key = next.getKey();
            Integer value = next.getValue();
        }


    }


}
