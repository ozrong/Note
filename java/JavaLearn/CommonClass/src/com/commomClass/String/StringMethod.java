package com.commomClass.String;

import org.junit.Test;

public class StringMethod {
    @Test
    public void test(){
        String s1 = "helloword";
        System.out.println(s1.length());//查看长度
        System.out.println(s1.charAt(3));//返回指定位置的字符
        boolean flag = s1.isEmpty();//判断是不是空的
        System.out.println(s1.toLowerCase());//转为小写
        //但是并不修改原来的字符串，是新造的
        System.out.println(s1.toUpperCase());//转大写
        String s3= "   hellowo ";
        System.out.println(s3.trim());//去除首未所有空格
        String s4 = s1.substring(2);//从这个索引开始 截取之后所有的字符(包括索引的位置)
        System.out.println(s4);//"lloword"

        String s5 = s1.substring(2,6);//从2开始截取字符到6 但不包含6
        System.out.println(s5);//"llow"


    }
    @Test
    public void test2(){
        String s1="Helloword";
        String s2="helloword";
        System.out.println(s1.equals(s2));//flase
        System.out.println(s1.equalsIgnoreCase(s2));//ture 忽略大小写比较大小
        String s3 = s1.concat(s2);//连接字符串 （等于+）

        String s5 = "asdf";
        System.out.println(s5.compareTo("adb"));//比较大小 正数就是大  负数就是小 0相等
    }
    @Test
    public void test3(){
        String s1 = "helloworld";
        boolean flag = s1.endsWith("ld");//看是不是以ld结尾的 是返回ture 否则false
        boolean flag1 = s1.startsWith("he");//看是不是以he开始的
        boolean flag3 = s1.startsWith("lo",3);
        System.out.println(flag3);
        //看置顶位置的索引开始的子串是不是以指定的字符串开始的 包含索引为3的字符
    }
    @Test
    public  void test4(){
        String s1 = "helloword";
        System.out.println(s1.contains("w"));//看s1中是否包含“w”字符
        System.out.println(s1.indexOf("l"));//返回“l”字符在s1中的索引位置（最先出现的位置）没找到返回-1
        System.out.println(s1.indexOf("o",3));//从指定位置开始查找“l”出现的所以(但是返回的索引依旧是从字符串的开始计数的：也就是从指定位置加上和后面的位置)
        System.out.println(s1.lastIndexOf("o"));//从后面找 (还是从前面开始计数的)


    }
    @Test
    public void test5(){
        String str1 = "北北京尚硅谷北京";
        String str2 = str1.replace("北"/*被替换的字符*/,"东");//替换所有的“北”
        System.out.println(str2);

        /*
        * replaceAll("正则表达式",String)
        *boolean matches();匹配
        * String[] split();拆字符串，根据正则表达式
        *  以上都涉及到了正则表达式 先不举例子
        *
        * */





    }

}


