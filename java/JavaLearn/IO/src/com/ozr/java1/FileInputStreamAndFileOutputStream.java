package com.ozr.java1;

import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * @Author OZR
 * @Date 2021/1/17 14:17
 *
 *
 * FileInputStream和FileOutputStream的使用
 *
 * 1.对于文本文件使用字符流处理（。txt  .java  .c .cpp）
 * 2.非文件格式使用字节流处理(.jpg  .mp3  .mp4  .doc )
 */
public class FileInputStreamAndFileOutputStream {
    @Test
    //使用字节流处理字符
    public void test1(){
        //使用字节流处理字符流是没有问题的，但是文件里面不能用中文。
        FileInputStream fis = null;
        try {
            File file = new File("hello.txt");

            fis = new FileInputStream(file);


            byte[] buffer = new byte[5];
            int len;
            while((len = fis.read(buffer)) != -1){
                String str = new String(buffer,0,len);
                System.out.println(str);

            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                fis.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


    }
    @Test
    public void test3(){
        File flie = new File("zhi.jpg");
        FileInputStream fis = new FileInputStream(flie);
        F

    }
}
