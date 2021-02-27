package com.ozr.UDP;

import org.junit.Test;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

/**
 * @Author OZR
 * @Date 2021/1/24 16:14
 *
 *
 * UDP协议的网络编程
 */
public class UDPTest {
    //发送端
    @Test
    public void sender(){

        DatagramSocket socket = null;//没有参数，因为UDP发的数据不再socket里面了
        try {
            socket = new DatagramSocket();

            String str = "我是UDP发送的数据";
            byte[] data = str.getBytes(); //发送的数据都会在这里面
            InetAddress inet = InetAddress.getByName("127.0.0.1");

            //发送的时候是要指定发送的位置的（位置是IP+端口号）
            DatagramPacket packet = new DatagramPacket(data,0,data.length,inet,9090);
            socket.send(packet);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (socket != null) {
                socket.close();
            }
        }
    }
    //接收端
    @Test
    public void receiver(){
        DatagramSocket socket = null;
        try {
            socket = new DatagramSocket(9090);

            byte[] buffer = new byte[100];//指定数据保存在这

            //这是接收，不需要指定位置
            DatagramPacket packet = new DatagramPacket(buffer,0,buffer.length);//虽然要放到这个里面
                                                                                      //但是数据是存在这个buffer里面的

            socket.receive(packet);

            //下面两种都是可以的得到数据的
            System.out.println(new String(packet.getData(),0,packet.getLength()));
            System.out.println(new String(buffer,0,buffer.length));
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (socket != null) {
                socket.close();
            }
        }
    }
}
