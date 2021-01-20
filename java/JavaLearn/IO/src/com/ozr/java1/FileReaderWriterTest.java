package com.ozr.java1;

import com.sun.org.apache.xml.internal.resolver.helpers.PublicId;
import org.junit.Test;

import java.io.*;

/**
 * @Author OZR
 * @Date 2021/1/12 20:30
 *
 *
 *
 *
 *
 * 一.流的分类
 *    按数据单位：    字节流；字符流。
 *    按数据的流向：  输出流；输入流
 *    流的角色：      节点流 处理流
 *
 * 二.流的体系结构
 * 抽象基类                  节点流（或者文件流）     缓冲流（处理流的一种）
 * InputStream               FileInputStream         BufferInputStream
 * OutputStream              FileOutputStream        BufferOutputStream
 * Reader                    FileReader              BufferReader
 * Writer                    FileWriter              BufferWriter
 *
 */
public class FileReaderWriterTest {
    public static void main(String[] args) {
        File file = new File("hello.txt");//相对于当前的工程
        System.out.println(file.getAbsolutePath());//E:\CODE\IDEA_code\JavaLearn\hello.txt

    }

    /*
    * 将hello.txt读到程序中
    *说明：
    *   1.read():返回一个字符，如果达到文件末尾返回-1
    *   2.异常的处理，为了保证流一定会关闭需要使用try-catch-finally处理
    *   3.读入的文件一定要存在
    *
    *
    *
    * */
    @Test

    /*
    这种处理异常的方式不好，因为建立好流以后，一旦抛异常这个流是关闭不了的
     */
    public void test() throws IOException {
        //1.指明操作的文件
        File file = new File("hello.txt");//相对于当前的modul
        System.out.println(file.getAbsolutePath());//E:\CODE\IDEA_code\JavaLearn\IO\hello.txt
        //2.提供具体的流
        FileReader in = new FileReader(file);
        //3.数据的读入
        //read():返回一个字符，如果达到文件末尾返回-1
//方式一
//        int data =  in.read();
//        while(data !=-1){
//            System.out.print((char)data);
//            data = in.read();//read()返回的是一个字符
//        }

        int data;
        while((data = in.read()) !=-1){
            System.out.print((char)data);

        }
        //4.流的关闭
        in.close();

    }


    @Test
    /*
    将关于流的操作放入到try中
     */
    public void test2() {
        FileReader in = null;
        try {
            //1.指明操作的文件
            File file = new File("hello.txt");//相对于当前的modul
            System.out.println(file.getAbsolutePath());//E:\CODE\IDEA_code\JavaLearn\IO\hello.txt
            //2.提供具体的流
            in = new FileReader(file);
            //3.数据的读入
            //read():返回一个字符，如果达到文件末尾返回-1

            int data;
            while((data = in.read()) !=-1){
                System.out.print((char)data);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            //4.流的关闭
//            try {
//                if(in !=null)
//                in.close();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
            //这样也可以
            if(in !=null){
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }


    }

    /**
     * 对read()操作的升级，使用read的重载方法
     */
    @Test
    public void test3() {
        FileReader in = null;
        try {
            //1.File类的实例化
            File file = new File("hello.txt");
            //2.FileReader流的实例化
             in = new FileReader(file);


            //3.读入的操作
            //read(char[] )返回每次读入到数字中的数字，如果达到文件末尾返回-1
            char[] temp  = new char[5];
            int len;
            while ((len=in.read(temp))!=-1 ){
//
//                //方式一
//                for (int i = 0; i <len; i++) {
//                    System.out.print(temp[i]);
//                }
//                //hello123


//                //错误的
//                for (int i = 0; i <temp.length; i++) {
//                    System.out.print(temp[i]);
//                }
//                //output:hello123lo   为啥和我们文件里面的不一样
//                //每次把字符装进数组里面的时候，并没有把数组给清空，只是把数据给替换了，假如剩下的字符没有数组那么
//                //长，从左到右来替换，剩下的位置还是上一次的字符

                //方式二
                //错误的写法，和上面的错误一样
//                String str = new String(temp);
//                System.out.print(str);

                //正确的
                String str1 = new String(temp,0,len);
                System.out.print(str1);





            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
              //4.流的关闭
            if(in != null){
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

   

    }


    /**
     * 上面是读入，下面是写出
     * 从内存中写出到磁盘中
     *
     * 1.输出操作，对应的文件File可以不存在
     * 2.如果不存在，在输出的过程中会创建这个文件
     * 3.如果存在：看使用的方法，如果使用的流的构造器，FileWriter(file,false)=FileWriter(file)就会直接覆盖
     *                         如果使用的流的构造器    FileWriter(file,ture)就会添加
     */
    @Test
    public void test4(){
        FileWriter out  = null;
        try {
            //1.提供File类对象，指明文件
            File file = new File("hello1.txt");

            //2.提供FileWriter的对象（流）
            out = new FileWriter(file);

            //3.数据的操作（写出操作）
            out.write("I have a dream!");
            out.write("\n");
            out.write("you should a dream!"); //如果这个文件存在会覆盖，不存在会创建
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            //4.关闭流
            try {
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


    }
    @Test
    public void test6(){
        FileReader fr = null;
        FileWriter fw = null;

        try {
            //1.创建File类的对象，指明读入和写出的文件
            File srcfile = new File("hello.txt");
            File desFile = new File("helo.txt");

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
}
