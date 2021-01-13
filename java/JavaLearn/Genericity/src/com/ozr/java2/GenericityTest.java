package com.ozr.java2;

/**
 * @Author OZR
 * @Date 2021/1/8 20:45
 */

import org.junit.Test;


import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

/**
 *1.泛型在继承方面的体现
 *
 *
 * 2.通配符的使用
 *
 */

public class GenericityTest {
    /*
    * 类A是类B的父类，但是G<A>和G<B>是没有子父类关系 二者是并列的关系 eg:test1
    *
    * 注意：类A是类B的父类(接口也行)， A<G>和B<G>是子父类的关系。就是可以子类的对象赋给父类 eg:test2
    *
    * */
    @Test
    public  void test1(){
        Object obj = null;
        String str = null;
        obj = str; //多态的体现


        List<Object> list1 = null;
        List<String> list2 = null;
        //此时的list1和list2是不具备子父类关系的
//        list1 = list2  //错误的
 /*
        Date data = new Date();
        //此时的str和data是不具备子父类关系的  不能赋值的
        str = data
*/

    }
    @Test
    public void test2(){
        List<String> list1 = null;
        ArrayList<String> list2 = null;
        list1 = list2;

    }


    /*
    * 2.通配符的使用  通配符 ：?
    *
    * 类A是类B的父类，G<A>和G<B>是没有关系的 但是G<?>可以看成是二者的父类
    *
    *
    *3.有限制的通配符：
    * */

    @Test
    public  void test3(){
        List<String> list1 = null;
        List<Object> list2 = null;

        List<?> list = null; //?就表示占了一个位置一样的感觉，可以匹配任何的类型
        list = list1;
        list = list2;

//        print(list1);
//        print(list2);


        List<String> list3 = new ArrayList<>();
        list3.add("AA");
        list3.add("BB");
        list3.add("CC");

        list = list3; //问题: list可以使用添加操作吗？？？，可以读吗？？？

        //添加  对于List<?>就不能向内部添加数据了，除了添加null
//        list.add("DD");//错误
        list.add(null);

        //获取  List<?>可以读取的，类型为Object
        Object obj = list.get(1);
        System.out.println(obj); //BB




    }

    public void print(List<?> list){//这个参数可以放List<String>,List<Integer>等 通用的一个输出方法。
        Iterator<?> iterator = list.iterator();
        while (iterator.hasNext()){
            Object obj = iterator.next();
            System.out.println(obj);
        }
    }

    /*
    *3. 有限制条件的通配符
    * ? extends A :  G<? extends A>是可以作为G<A>,G<B>的父类 ，其中B是A的子类   ? extends A:<=A
    *
    *
    * ? super A   : G<? super A> 是可以作为G<A>,G<B>的父类 其中A是B的子类   ? super A : >=A
    *
    * */
    @Test
    public void test4(){



        List<Person> list3 = null;
        List<Student> list4 = null;
        List<Object> list5 = null;


        List<? extends Person> list1 = null;
        list1  = list3;   //可以赋予Penson以及Penson的子类
        list1  = list4;
//        list1  = list5;



        List<? super Person> list2 = null;
        list2 = list3;  //可以赋予Penson以及Penson的父类
//        list2 = list4;
        list2 = list5;

    }

}

