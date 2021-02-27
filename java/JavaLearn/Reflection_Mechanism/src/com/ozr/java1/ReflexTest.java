package com.ozr.java1;

import org.junit.Test;

import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapters;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

/**
 * @Author OZR
 * @Date 2021/2/11 16:40
 */
public class ReflexTest {

    //反射之前，对于Person类的操作
    @Test
    public  void test(){
        //1.创建对象
        Person p1 = new Person("tom",23);

        //2.通过对象调用内部属性和方法
        p1.age = 10; //因为Person类这个age是public的
        System.out.println(p1.toString());

        p1.show();

    }
    //总结：在pensonl类的外部是不可以使用对象来调用其内部的私有结构



    //反射之后，对于Person类的操作
    @Test
    public void test2() throws Exception {

        Class personClass = Person.class;
        //通过反射创建Person对象
        Constructor cons = personClass.getConstructor(String.class, int.class);
        Object obj = cons.newInstance("tom", 12);
        System.out.println(obj.toString());

        Person tom = (Person)obj;
        System.out.println(tom.toString());

        //通过反射，调用对象指定的属性、方法
        Field age = personClass.getDeclaredField("age");
        age.set(tom,10);


    }

}
