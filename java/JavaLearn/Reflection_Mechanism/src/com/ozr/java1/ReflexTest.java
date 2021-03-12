package com.ozr.java1;

import org.junit.Test;

import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapters;
import java.lang.annotation.ElementType;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @Author OZR
 * @Date 2021/2/11 16:40
 *
 *      反射相关的主要API
 * java lang Class 代表一个类
 * java lang reflect Method 代表类的方法
 * java lang reflect Field 代表类的成员变量
 * java lang reflect Constructor 代表类的构造器
 *
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
    //总结：在penson类的外部是不可以使用对象来调用其内部的私有结构



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

        //调用属性
        Field age = personClass.getDeclaredField("age");
        age.set(tom,10);
        System.out.println(tom.toString());
        //调用方法
        Method show = personClass.getDeclaredMethod("show");
        show.invoke(tom);
        System.out.println("***************************************");

        //通过反射可以调用person类私有结构 例如 属性 方法 构造器
        Constructor cons1 = personClass.getDeclaredConstructor(String.class);
        cons1.setAccessible(true);
        Person p1 = (Person) cons1.newInstance("jerry");
        System.out.println(p1);

        //调用私有属性
        Field name = personClass.getDeclaredField("name");
        name.setAccessible(true);
        name.set(p1,"寒梅咩");
        System.out.println(p1);
        //调用私有方法
        Method showNation = personClass.getDeclaredMethod("showNation", String.class);
        showNation.setAccessible(true);
        String nation =(String) showNation.invoke(p1,"中国");//相当于这个p1.showNation("中国") 可以得到这个方法的返回值
        System.out.println(nation);
        /**  1. 通过直接new的方式和反射的方式也可以调用公共的结构，那到底是用哪个？？？
         *     还是使用new的方式。
         *     什么时候用放射：反射的方式，反射的特性：动态性
         *
         *
         *
         *   2.反射机制和封装性是不是矛盾的？？？？？？ 如何理解
         *   不矛盾，.....
         */

    }



    /**
     *  关于java.lang.Class类的理解
     *     1.类的加载过程：
     *     程序经过javac.exe命令以后，会生成一个或多个字节码文件(.class结尾)。
     *     接着我们使用java.exe命令对某个字节码文件进行解释运行。相当于将某个字节码文件
     *     加载到内存中。此过程就称为类的加载。加载到内存中的类，我们就称为运行时类，此
     *     运行时类，就作为Class的一个实例。
     *
     *     2.换句话说，Class的实例就对应着一个运行时类。
     *     3.加载到内存中的运行时类，会缓存一定的时间。在此时间之内，我们可以通过不同的方式
     *     来获取此运行时类。
     *
     *
     */


    //获取Class实例（注意是获取不是创建）（前三种方式需要掌握）
    @Test
    public void test22() throws ClassNotFoundException {
        //方式一：调用运行时类的属性：.class
        Class clazz1 = Person.class;
        System.out.println(clazz1);//class com.ozr.java1.Person

        //方式二：通过运行时类的对象,调用getClass()
        Person p1 = new Person();
        Class clazz2 = p1.getClass();
        System.out.println(clazz2);//class com.ozr.java1.Person

        //方式三：调用Class的静态方法：forName(String classPath)
        Class clazz3 = Class.forName("com.ozr.java1.Person");
        System.out.println(clazz3);//class com.ozr.java1.Person

        System.out.println(clazz1==clazz2);//true
        System.out.println(clazz2==clazz3);//true
        System.out.println(clazz1==clazz3);//true
        /**
         * 加载到内存中的运行时类，会缓存一定的时间。在此时间之内，我们可以通过不同的方式来获取此运行时类。
         */


        //方式四：使用类的加载器：ClassLoader  (了解)
        ClassLoader classLoader = ReflexTest.class.getClassLoader();
        Class clazz4 = classLoader.loadClass("com.ozr.java1.Person");
        System.out.println(clazz4);//class com.ozr.java1.Person


        System.out.println(clazz1 == clazz4);///       true
    }



    //万事万物皆对象？对象.xxx,File,URL,反射,前端、数据库操作


    //Class实例可以是哪些结构的说明：
    @Test
    public void test4(){
        Class c1 = Object.class;
        Class c2 = Comparable.class;
        Class c3 = String[].class;
        Class c4 = int[][].class;
        Class c5 = ElementType.class;
        Class c6 = Override.class;
        Class c7 = int.class;
        Class c8 = void.class;
        Class c9 = Class.class;

        int[] a = new int[10];
        int[] b = new int[100];
        Class c10 = a.getClass();
        Class c11 = b.getClass();
        // 只要数组的元素类型与维度一样，就是同一个Class
        System.out.println(c10 == c11);

    }

}
