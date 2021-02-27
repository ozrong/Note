package com.ozr.java2;

import org.junit.Test;

import java.io.*;

/**
 * @Author OZR
 * @Date 2021/1/21 17:57
 *
 *
 * 转换流
 *
 * 1. 转换流属于字符流
 *   InputStreamReader  将字节的输入流转换成字符的输入流
 *   OutputStreamWriter 将字符的输出流转换成字节的输出流
 *
 * 2. 作用：提供字节流与字符流的转换
 *
 * 3. 解码：字节、字节数组  --->字符数组、字符串
 *    编码：字符数组、字符串 ---> 字节、字节数组
 *
 * 4.字符集
 * ASCII：美国标准信息交换码。
         用一个字节的7位可以表示。
 * ISO8859-1：拉丁码表。欧洲码表
         用一个字节的8位表示。
 * GB2312：中国的中文编码表。最多两个字节编码所有字符
 * GBK：中国的中文编码表升级，融合了更多的中文文字符号。最多两个字节编码
 * Unicode：国际标准码，融合了目前人类使用的所有字符。为每个字符分配唯一的字符码。所有的文字都用两个字节来表示。
 * UTF-8：变长的编码方式，可用1-4个字节来表示一个字符。
 *
 *
 */
public class InputStreamReaderTest {

    @Test
    public void test(){
        InputStreamReader isr = null; //使用默认的字符集
        try {
            FileInputStream fis = new FileInputStream("dbcp.txt");  //字节流

            //转化流    将字节的输入流转换成字符的输入流
            isr = new InputStreamReader(fis);

            /**
             * 指定字符集
             *
             * 这个使用那个字符集是由当初建立它的时候使用的字符集
             */
            //InputStreamReader isr = new InputStreamReader(fis,"UTF-8");

            char[] cbuf = new char[20];
            int len;
            while ((len = isr.read(cbuf))  != -1) {
                String  str = new String(cbuf,0,len);
                System.out.print(str);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(isr != null)

            try {
                isr.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }




    }

    /**
     *    综合使用InputStreamReader和OutputStreamWriter
     */

    @Test
    public void test2() throws Exception {
        //1.造文件、造流
        File file1 = new File("dbcp.txt");
        File file2 = new File("dbcp_gbk.txt");

        FileInputStream fis = new FileInputStream(file1);
        FileOutputStream fos = new FileOutputStream(file2);

        //        InputStreamReader  将字节的输入流转换成字符的输入流
        InputStreamReader isr = new InputStreamReader(fis,"utf-8");



       //    OutputStreamWriter 将字符的输出流转换成字节的输出流
        OutputStreamWriter osw = new OutputStreamWriter(fos,"gbk");

        //2.读写过程
        char[] cbuf = new char[20];
        int len;
        while((len = isr.read(cbuf)) != -1){
            osw.write(cbuf,0,len);
        }

        //3.关闭资源
        isr.close();
        osw.close();


    }


}
