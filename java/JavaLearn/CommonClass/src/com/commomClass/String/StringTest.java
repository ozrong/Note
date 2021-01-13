package com.commomClass.String;

import org.junit.Test;

/*
* String实例化方式
* 方法一：通过字面量定义方式
* 方法二：通过new
* */
public class StringTest {
    /*
    * String:
    * 1.String 声明为final 不可被继承的类 而不是像int、char这些时关键字
    * 2.Stirng 实现了 Serializable接口，表明字符串是可以序列化的
    *          实现了Comparable接口，表明字符串是可以比较的
    * 3.String内部定义了final char9[] value用于存储字符串数据
    * 4.String 代表了不可变的字符序列（不可变性）
    *   体现：1.当对字符串变量重新赋值的时候，需要在内存中重新制定内存区域赋值，不能更改原有的value值
    *        2.当对字符串进行连续的操作时，需要在内存中重新制定内存区域赋值，不能更改原有的value值
    *        3.使用String 的replace()更改字符串的时候依旧是重新建的，而不是改变了原来的值
    * 5.通过字面量方式给莪你个字符串赋值。此时的字符串声明在常量池中
    * /
     */
    @Test
    public void test2(){
        String s1=new String("abc");//这是在堆空间中
           /*面试题  这句建立了几个对象
           *  两个，一个是在堆空间中new结构，另一个实在char数组对应在常量池中的”adc“
           *
           * */
    }
    @Test
    public void test1(){
        String s1 = "adc"; //字面量的定义方式 （s1，s2是一个对象）
        String s2 = "adc";
            /*s1,s2其实是一样的，这两个指向同一个地址的*/
        System.out.println(s1==s2);//输出 ture
        s2="hello"; //表示在常量池新建一个“hello”
        String s3="abc"; //还是在s1 s2一样的地址处
        s3+="def"; //重新开辟了地址

        String s4=s2.replace("a","m");
        System.out.println(s2);
    }
    @Test
    public void test3(){
        //字面量的方式，s1,s2的数据在方法去的字符串常量池中
       String s1="javaEE";
       String s2="javaEE";
//通过new这种构造器的方式  s3,s4保存的是一个地址值，数据实在对空间中
       String s3=new String("javaEE");
       String s4=new String("javaEE");

        System.out.println(s1==s2);//ture
        System.out.println(s1==s3);//false
        System.out.println(s1==s4);//false
        System.out.println(s3==s4);//false 因为s3,s4是不同的对象所以不同
        System.out.println("###################");

        Person p1 = new Person("Tom", 20);
        Person p2 = new Person("Tom", 20);
        System.out.println(p1.name.equals(p2.name));//ture  equals比较的内容
        System.out.println(p1.name == p2.name);//ture
        ///因为name这个是通过字面量定义的  两个name的值是一样的 共用一个地址所以一样

        p1.name="jack";//这时会重新造一个字符串  而不是修改之前的字符串
        System.out.println(p2.name);//Tom


    }
    @Test
    public void test4(){
        String s1="javaEE";
        String s2="hadoop";
        String s3="javaEEhadoop";
        String s4="javaEE"+"hadoop";//这种就是在常量池
        String s5=s1+"hadoop"; //有变量名参与了  就不在常量池了就在堆空间了
        String s6="javaEE"+s2;
        String s7=s1+s2;
//==比的是地址
        System.out.println(s3 == s4);//ture
        System.out.println(s3 == s5);//false
        System.out.println(s3 == s6);//false
        System.out.println(s3 == s7);//false

        System.out.println(s5 == s6);//false
        System.out.println(s5 == s7);//false
        System.out.println(s6 == s7);//false

        String s8 = s5.intern();//返回得到的常量就是常量池里面的“javaEEhadoop”

    }


}
