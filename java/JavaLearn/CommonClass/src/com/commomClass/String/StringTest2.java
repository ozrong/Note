package com.commomClass.String;
import org.junit.Test;
import static java.lang.Math.*;//静态导入
/**
 * @Author OZR
 * @Date 2020/6/26 16:33
 */
/*
*  个人测试代码的
*
*
* */
public class StringTest2 {
    @Test
    public void test() {
        String str = "Hello";
        /*码点？？？？？是指编码字符集中，字符所对应的数字。有效范围从U+0000到U+10FFFF。其中U+0000到U+FFFF为基本字符，U+10000到U+10FFFF为增补字符
        代码单元(code unit)：对代码点进行编码得到的1或2个16位序列。其中基本字符的代码点直接用一个相同值的代码单元表示，增补字符的代码点用两个代码单元的进行编码，这个范围内没有数字用于表示字符，因此程序可以识别出当前字符是单单元的基本字符，还是双单元的增补字符。
        */
        int cpCount = str.codePointCount(0, str.length());
        System.out.println(cpCount);
    }
    //得到第i个码点   (返回字符所对应的数字)
    @Test
    public void test2(){
        String str = "Hello";
        int index = str.offsetByCodePoints(0,1);
        int cp = str.codePointAt(index);
        System.out.println(index);
        System.out.println(cp);
        System.out.println("\u006c");
    }



}
