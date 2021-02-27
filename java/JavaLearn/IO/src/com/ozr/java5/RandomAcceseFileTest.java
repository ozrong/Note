package com.ozr.java5;

import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

/**
 * @Author OZR
 * @Date 2021/1/22 20:59
 *
 *
 *RandomAccessFile的使用
 * 1. RandomAccessFile 声明在 java.io 包下，但直接继承于 java.lang.Object类。并
 * 且它实现了 DataInput 、 DataOutput 这两个接口，也就意味着这个类既可以读也可以写。
 *
 * 2.RandomAccessFile既可以作为输出流也可以作为输入流
 *
 */
public class RandomAcceseFileTest {
    @Test
    public void test(){
        RandomAccessFile raf = null;
        RandomAccessFile raf1 = null;
        try {
            /**
             * 创建 RandomAccessFile 类实例需要指定一个 mode 参数，该参数指定 RandomAccessFile 的访问模式：
             * r: 以只读方式打开
             * rw ：打开以便读取和写入
             * rwd 打开以便读取和 写入；同步文件内容的更新
             * rws 打开以便读取和 写入； 同步文件内容和元数据 的 更新
             *
             *
             * 注意：
             *    RandomAccessFile既可以作为输出流也可以作为输入流,但是一个对象只能作为一种流，
             *    也就是说你要做输入和输出需要构造两个RandomAcceseFile对象
             * */



            raf = new RandomAccessFile(new File("test.jpg"),"r");
            raf1 = new RandomAccessFile(new File("testRandom.jpg"),"rw");
            byte[] buffer = new byte[1024];
            int len;
            while((len = raf.read(buffer))!=-1){
                raf1.write(buffer,0,len);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                raf.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                raf1.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


    }


    /**
     *
     * 3.如果RandomAccessFile作为输出流时，写出到的文件如果不存在，则在执行过程中自动创建。
     *   如果写出到的文件存在，则会对原有文件的内容进行覆盖。（默认情况下，从头覆盖，如果写入的内容没有那么长的话，之前的字符依旧还会存在）
     *
     */
    @Test
    public void test2() throws IOException {

        RandomAccessFile raf1 = new RandomAccessFile("hello.txt","rw");

        raf1.write("xyz".getBytes());//这儿的write是一个覆盖行为

        raf1.close();

    }

    /*
  使用RandomAccessFile实现数据的插入效果
   */
    @Test
    public void test3() throws IOException {

        RandomAccessFile raf1 = new RandomAccessFile("hello.txt","rw");

        raf1.seek(3);//将指针调到角标为3的位置（从0开始）
        //保存指针3后面的所有数据到StringBuilder中
        StringBuilder builder = new StringBuilder((int) new File("hello.txt").length());
        byte[] buffer = new byte[20];
        int len;
        while((len = raf1.read(buffer)) != -1){
            builder.append(new String(buffer,0,len)) ;
        }
        //调回指针，写入“xyz”
        raf1.seek(3);
        raf1.write("xyz".getBytes());

        //将StringBuilder中的数据写入到文件中
        raf1.write(builder.toString().getBytes());

        raf1.close();

        //思考：将StringBuilder替换为ByteArrayOutputStream
    }
}
