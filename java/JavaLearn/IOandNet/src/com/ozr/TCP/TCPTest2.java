package com.ozr.TCP;

import org.junit.Test;

import java.io.*;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @Author OZR
 * @Date 2021/1/24 13:50
 *
 *
 *
 * 实现TCP的网络编程
 * 实例2：客户端发送文件给 服务端， 服务端将文件保存在本地。
 */
public class TCPTest2 {

    /**
     * 客户端
     */
    @Test
    public void client(){
        Socket socket = null;
        OutputStream os= null;
        FileInputStream fis = null;
        try {
            //1.
            socket = new Socket(InetAddress.getByName("127.0.0.1"),9090);

            //2.
            os = socket.getOutputStream();

            //3.
            fis = new FileInputStream("test.jpg");

            //4.
            byte[] buffer = new byte[1024];
            int len;
            while((len = fis.read(buffer)) !=-1){
                os.write(buffer,0,len);

            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            //5.
            try {
                fis.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                os.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }



    }


    /**
     * 服务器
     */
    @Test
    public void server(){
        ServerSocket serversocket = null;
        Socket socket = null;
        InputStream is = null;
        FileOutputStream fos = null;
        try {
            serversocket = new ServerSocket(9090);
            socket = serversocket.accept();

            is = socket.getInputStream();

            fos = new FileOutputStream("test2.jpg");

            byte[] buffer = new byte[1024];
            int len;
            while((len = is.read(buffer)) !=-1){
                fos.write(buffer,0,len);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                serversocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }




    }
}
