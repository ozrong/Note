package com.ozr.TCP;


import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;

/**
 * 实现TCP的网络编程
 * 例子1：客户端发送信息给服务端，服务端将数据显示在控制台上
 *
 * @author shkstart
 * @create 2019 下午 3:30
 */
public class TCPTest1 {

    //客服端
    @Test
    public void client(){
        Socket socket = null;
        OutputStream outputStream = null;
        try {

            //1.创建Socket对象，指明服务器的IP和端口号
            InetAddress inet =InetAddress.getByName("127.0.0.1");
            socket = new Socket(inet,8899);

            //2.获取输出流，输出数据
            outputStream = socket.getOutputStream();

            //3.需要写出的数据
            outputStream.write("你好我收客服端".getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            //4.关闭资源
            if(outputStream != null)
            try {
                outputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            if(socket !=null)
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }





    }

    //服务器
    @Test
    public void server(){
        Socket socket = null;
        InputStream is = null;
        ServerSocket serverSocket = null;
        ByteArrayOutputStream baos = null;
        try {
            //1.创建服务的ServerSocket ，指明自己的端口号
            serverSocket = new ServerSocket(8899);//指明自己的端口号

            //2.创建Socket对象，这里与客户端的Socket不一样，这儿是创建一个Socket用于接收从
            //客户端传来的socket。调用accept()方法来接收
            socket = serverSocket.accept();

            //3.获取输入流
            is = socket.getInputStream();

            //4.读取数据

            //方式一
            // 这种方式可能会造成中文乱码
//            byte[] buffer = new byte[1024];
//            int len;
//            while((len = is.read(buffer)) != -1){
//                String str = new String(buffer,0,len);
//                System.out.println(str);
//            }
            //-------------------

            //方式二
             baos = new ByteArrayOutputStream();
            /**
             * 这个类的意思就是你来多少数据我都暂时的保存住，先不进行任何的操作
             */


            byte[] buffer = new byte[5];
            int len;
            while((len = is.read(buffer)) != -1){
                baos.write(buffer,0,len);
                //就是写到这个类里面，也就是把写进来的数据进行一个保存，不进行任何的操作
                //所以可以避免一个中文只取到一半 造成乱码
            }

            System.out.println(baos.toString());//保存完数据过后在一起转换成字符串

            System.out.println("收到了来自于：" + socket.getInetAddress().getHostAddress()+"的数据");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            //5.关闭资源
            if(baos!= null) {
                try {
                    baos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(is!=null) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(socket !=null)
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            if(serverSocket != null ) {
                try {
                    serverSocket.close ();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }






    }



}
