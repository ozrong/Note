package com.ozr.java1;

import org.junit.Test;

import java.io.*;

/**
 * @Author OZR
 * @Date 2021/1/21 15:01
 *
 *
 * 缓冲流的使用
 *BufferInputStream
 * BufferOutputStream
 * BufferReader
 * BufferWriter
 * 作用：提升流的读取、写入的速度。
 */
public class BufferedTest {

    //实现非文本复制
    @Test
    public void test(){
        BufferedInputStream bis = null;
        BufferedOutputStream bos = null;
        try {
            //1.指定文件
            File src = new File("test.jpg");
            File des = new File("test3.jpg");

            //2.指定流
            /**
             * 缓存流是处理流，不能直接作用到文件上面的
             * 所以先实现内部流（例如节点流），在实现处理流
             */
            //2.1 节点流
            FileInputStream fis = new FileInputStream(src);
            FileOutputStream fos = new FileOutputStream(des);

            //2.2 缓冲流
            bis = new BufferedInputStream(fis);
            bos = new BufferedOutputStream(fos);

            //3.具体的操作，这里是复制
            byte[] buffer = new byte[10];
            int len ;
            while ((len = bis.read(buffer))!=-1 ) {
                bos.write(buffer,0,len);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
        }

        //4. 关闭资源
        /**
         * 关闭资源的要求：先关闭外层的流，在关闭内层的流
         */
        if(bos !=null)
        try {
            bos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if(bis !=null)
        try {
            bis.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //说明 ：在关闭外层流的时候，会自动的关闭内层的流，也就是只用关闭外层流就行了
//        fis.close();
//        fos.close();




    }


    /**
     * 使用buffer流（缓冲流）实现文件复制的方法
     */
    public void copyFileWithBuffer(String srcPath,String desPath){
        BufferedInputStream bis = null;
        BufferedOutputStream bos = null;
        try {
            //1.指定文件
            File src = new File(srcPath);
            File des = new File(desPath);

            //2.指定流
            /**
             * 缓存流是处理流，不能直接作用到文件上面的
             * 所以先实现节点流，在实现处理流
             */
            //2.1 节点流
            FileInputStream fis = new FileInputStream(src);
            FileOutputStream fos = new FileOutputStream(des);

            //2.2 缓冲流
            bis = new BufferedInputStream(fis);
            bos = new BufferedOutputStream(fos);

            //3.具体的操作，这里是复制
            byte[] buffer = new byte[8192];
            int len ;
            while ((len = bis.read(buffer))!=-1 ) {
                bos.write(buffer,0,len);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
        }

        //4. 关闭资源
        /**
         * 关闭资源的要求：先关闭外层的流，在关闭内层的流
         */
        if(bos !=null)
            try {
                bos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        if(bis !=null)
            try {
                bis.close();
            } catch (IOException e) {
                e.printStackTrace();
            }


    }

    /**
     *
     * @param srcPath
     * @param desPath
     * 使用字节流来复制文件
     */
    public void copyFile(String srcPath,String desPath){

        FileInputStream fis = null;
        FileOutputStream fos = null;
        try {
            File src = new File(srcPath);
            File des = new File(desPath);

            fis = new FileInputStream(src);
            fos = new FileOutputStream(des);


            byte[] buffer = new byte[8192];
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



    /**
     * 测试一下buffer流和一般的节点流那个快
     */
    @Test
    public void test2(){
        long start = System.currentTimeMillis();

        String src = "D:\\BaiduNetdiskDownload\\java基础\\30天java基础\\day25_泛型与File\\hahah.avi";
        String des = "C:\\Users\\hp\\Desktop\\hahah.avi";
        copyFileWithBuffer(src,des); //缓冲流
        long end = System.currentTimeMillis();
        System.out.println("花费的时间为："+(end-start)); //83


//        long start1 = System.currentTimeMillis();
//        copyFile(src,des); //缓冲流
//        long end1 = System.currentTimeMillis();
//        System.out.println("花费的时间为："+(end1-start1));//277


        /**
         * 结论：显然使用缓冲流要快得多
         * 这是因为缓冲流的类里面提供了一个缓冲区、也就是它先把读取的文件先放入到缓冲区里面，达到了缓冲
         * 的指定大小了，在一次性的去写出，
         */

    }

    /*
  使用BufferedReader和BufferedWriter实现文本文件的复制

   */
    @Test
    public void testBufferedReaderBufferedWriter(){
        BufferedReader br = null;
        BufferedWriter bw = null;
        try {
            //创建文件和相应的流
            br = new BufferedReader(new FileReader(new File("dbcp.txt")));
            bw = new BufferedWriter(new FileWriter(new File("dbcp1.txt")));

            //读写操作
            //方式一：使用char[]数组
//            char[] cbuf = new char[1024];
//            int len;
//            while((len = br.read(cbuf)) != -1){
//                bw.write(cbuf,0,len);
//    //            bw.flush();
//            }

            //方式二：使用String
            String data;
            while((data = br.readLine()) != null){
                //方法一：
//                bw.write(data + "\n");//data中不包含换行符
                //方法二：
                bw.write(data);//data中不包含换行符
                bw.newLine();//提供换行的操作

            }


        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            //关闭资源
            if(bw != null){

                try {
                    bw.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(br != null){
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }

    }
}
