package com.ozr.java1;

import org.junit.Test;

import java.io.*;

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



/**
 * 测试FileInputStream和FileOutputStream流的使用
 *
 */
public class FileInputStreamAndFileOutputStream {
    //使用字符流处理图片（字节文件）  复制一份
    //文件不报错
    //但是复制后的文件是打不开的，文件是错误的
    @Test
    public void test7(){
        FileReader fr = null;
        FileWriter fw = null;

        try {
            //使用字符流处理图片（字节文件）  复制一份
            //文件不报错
            //但是复制后的文件是打不开的，文件是错误的
            //不能使用字符流来处理字节流数据


            //1.创建File类的对象，指明读入和写出的文件
            File srcfile = new File("test.jpg");
            File desFile = new File("test1.jpg");

            //2.创建输入流和输出流的对象
            fr = new FileReader(srcfile);
            fw = new FileWriter(desFile);


            //3.数据的读入和写出操作
            char[] cbuf = new char[5];
            int len;
            while((len = fr.read(cbuf)) != -1){
                //每次读到len个字符，写入len个字符
                fw.write(cbuf,0,len);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            //4.关闭流资源
            try {
                fr.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

            //即使在上面的try-catch出现异常了的话  下面的try-catch也是会执行的
            try {
                fw.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


    }

    @Test
    //使用字节流处理字符
    public void test1() {
        //使用字节流处理字符流是没有问题的，但是文件里面不能用中文。
        /**
         * 在utf-8，gdk,编码里面，一个字符（英文）还是使用的一个字节存的，所以无论是字符流还是字节
         * 流来处理数字和字母都是可以的，一个字符就是一个字符/数字，一个字节还是一个字母/数字。但是，
         * 中文不可以，中文不是一个字节保存的了（utf-8中使用的是3个字节的）使用字节流来读取的话，
         * 就有可能一个字只取到了一部分，这就出现了乱码。
         */
        FileInputStream fis = null;
        try {
            File file = new File("hello.txt");

            fis = new FileInputStream(file);


            byte[] buffer = new byte[5];
            int len;
            while ((len = fis.read(buffer)) != -1) {
                String str = new String(buffer, 0, len);
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


    /**
     * 字节流复制图片
     */
    @Test
    public void test2(){

        FileInputStream fis = null;
        FileOutputStream fos = null;
        try {
            File src = new File("test.jpg");
            File des = new File("test2.jpg");

            fis = new FileInputStream(src);
            fos = new FileOutputStream(des);


            byte[] buffer = new byte[5];
            int len;
            while((len = fis.read(buffer)) != -1){
                fos.write(buffer,0,len);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(fos!=null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(fis!=null)
            try {
                fis.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
}
}
