package com.commomClass.String;

import org.junit.Test;

import java.io.UnsupportedEncodingException;
import java.util.Arrays;

/*String类与其他结构之间的转换*/
public class StringChange {
    /*
    * String与其他数据类型、包装类之间的转换：调用parseXxx(str)
    * 数据类型、包装类->转String：调用String.valueof(xxxx)
    *
    *
    * */


    @Test
    public void test(){
        String srt1="123";
       // int num = (int)str1//错误
        int num = Integer.parseInt(srt1);
        String str2=String.valueOf(num);//
        String str3 = num+"";            //等价
    }


    @Test
    public void test2(){
        /*String -> char[]调用String.toCharArray()
        * char[] -> String 调用构造器*/
        String str1="adc123";
        char[] charArray= str1.toCharArray();//String -> char[]
        for(char a:charArray){
            System.out.println(a);
        }
        char[] charArray1 = new char[]{'h','e','l','l','o'};
        String str2 = new String(charArray1);
        System.out.println(str2);
    }
    @Test
    public  void test3() throws UnsupportedEncodingException {
        /*String -> byte[] :调用String.getByte()
        *byte[] ->String:构造器
        * */

        /*编码 字符串->字节
        * 解码  字节->字符串*/
        String str1 = "adc123中国";
        byte[] bytes = str1.getBytes();//默认字符集
        System.out.println(Arrays.toString(bytes));


        byte[]  gbks = str1.getBytes("gbk");//使用gbk字符集
            System.out.println(Arrays.toString(gbks));

        String s = new String(bytes);
        System.out.println(s);

        String str3 = new String(gbks);
        System.out.println(str3);//乱码  因为编码和解码的字符集不一样

        String gbk2 = new String(gbks, "gbk");//设置了编码集
        System.out.println(gbk2);//不会乱码

    }


}
