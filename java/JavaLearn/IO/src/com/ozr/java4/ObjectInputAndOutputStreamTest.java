package com.ozr.java4;

import org.junit.Test;

import java.io.*;

/**
 * @Author OZR
 * @Date 2021/1/22 16:57
 *
 *
 *
 * 对象流的使用
 *  ObjectInputStream 和 OjbectOutputSteam
 * 作用：用于存储和读取基本数据类型数据或对象的处理流。它的强大之处就是可
 * 以把 Java 中的对象写入到数据源中，也能把对象从数据源中还原回来。
 *
 *
 * 序列化： 用 ObjectOutputStream类保存基本类型数据或对象的机制
 * 反序列化： 用 ObjectInputStream类读取基本类型数据或对象的机制
 *
 *
 *
 * 对象序列化机制：允许把内存中的 Java 对象转换成平台无关的二进制流，从
 * 而允许把这种二进制流持久地保存在磁盘上，或通过网络将这种二进制流传
 * 输到另一个网络节点
 */
public class ObjectInputAndOutputStreamTest {

    /**
     * 序列化：将内存中的java对象保存到磁盘中或者通过网络传输 使用ObjectOutputStream
     */
    @Test
    public void test(){
        ObjectOutput oos = null;
        try {
            oos = new ObjectOutputStream(new FileOutputStream("object.bat"));


            /*
            将new String("")这个对象保存到磁盘
             */
            oos.writeObject(new String("我爱北京天安门"));

            oos.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(oos!=null)
            try {
                oos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }


    /**
     * 反序列化：将磁盘文件中的对象还原为内存中的Java对象，使用ObjectInputStream
     */
    @Test
    public  void test2(){
        ObjectInputStream ois = null;
        try {
            /*
            将保存的对象还原到内存中
             */
            ois = new ObjectInputStream(new FileInputStream("object.bat"));
            Object obj = ois.readObject();
            String str = (String)obj;
            System.out.println(str);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                ois.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }


    /**
     * 保存和还原Person对象
     */

    @Test
    public void test3(){
        Person ozr = new Person("ozr", 24);
        ObjectOutput oos = null;
        try {
            oos = new ObjectOutputStream(new FileOutputStream("Personobject.bat"));
            oos.writeObject(new String("我爱北京天安门"));
            /*
            将Person对象保存到磁盘


            会报错 java.io.NotSerializableException: com.ozr.java4.Person
            因为Person类是不支持序列化的



            如果需要让某个对象支持序列化机制，则 必须 让对象所属的类及其属性是 可
            序列化的，为了让 某个 类是 可序列化的，该类 必须实现如下两个接口 之一。
            否则，会抛出 NotSerializableException 异常
            Serializable
            Externalizable
             */
            oos.writeObject(ozr);

            oos.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(oos!=null)
                try {
                    oos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
        }

    }


    @Test
    public  void test4(){
        ObjectInputStream ois = null;
        try {
            /*
            将保存的对象还原到内存中
             */
            ois = new ObjectInputStream(new FileInputStream("Personobject.bat"));
            Object obj = ois.readObject();
            String str = (String)obj;

            Person per = (Person)ois.readObject();
            /*
            注意：按什么顺序保存的就按什么顺序读取
             */
            System.out.println(str);
            System.out.println(per);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                ois.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}
