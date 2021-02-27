# IO

## IO流的分类

按操作单位不同：

+ 字节流（8 Bit ）010101...型:处理图片、声音，视频
+ 字符流 （16 Bit ）char型 ： 处理文本数据（因为里面是一个一个的字符）

按流的角色不同：

+ 节点流：
+ 处理流：

按数据的流向：

+ 输入流
+ 输出流

## 抽象基类

| 抽象基类 |    字节流    | 字符流 |
| :------: | :----------: | :----: |
|   输入   | InputStream  | Reader |
|   输出   | OutputStream | Writer |

java中IO流共涉及40多个子类，但是都是从4个**抽象**基类派生的  （抽象类不能实例化，抽象类不一定有抽象方法）

![image-20210112202559367](C:\Users\hp\AppData\Roaming\Typora\typora-user-images\image-20210112202559367.png)

加颜色的流是常用的

# 输入

输出除了Scanner还有这个方法 

```java
package com.ozr.java3;

import org.junit.Test;

import java.io.*;
import java.util.Scanner;

/**
 * 其他流的使用
 * 1.标准的输入、输出流
 * 2.打印流
 * 3.数据流
 *
 * @author shkstart
 * @create 2019 下午 6:11
 */
public class OtherStreamTest {

    /**
    1.标准的输入、输出流
    1.1
    System.in:标准的输入流，默认从键盘输入
    System.out:标准的输出流，默认从控制台输出
    1.2
    System类的setIn(InputStream is) / setOut(PrintStream ps)方式重新指定输入和输出的流。

    1.3练习：
    从键盘输入字符串，要求将读取到的整行字符串转成大写输出。然后继续进行输入操作，
    直至当输入“e”或者“exit”时，退出程序。

    方法一：使用Scanner实现，调用next()返回一个字符串
    方法二：使用System.in实现。System.in  --->  转换流 ---> BufferedReader的readLine()

     */
    public static void main(String[] args) {
        BufferedReader br = null;
        try {
            InputStreamReader isr = new InputStreamReader(System.in);//转换流： 将字符流转换成了字节流
            br = new BufferedReader(isr); //使用缓冲流

            Scanner in = new Scanner(System.in);
            while (true) {
                
//                  方法一
//                System.out.println("请输入字符串：");
//                String data=in.next();



//                  方法二
                System.out.println("请输入字符串：");
                String data1 = br.readLine();  //data这个字符串中是不带换行符的
                if ("e".equalsIgnoreCase(data) || "exit".equalsIgnoreCase(data)) {
                    System.out.println("程序结束");
                    break;
                }

                String upperCase = data.toUpperCase();
                System.out.println(upperCase);

            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }
    }

    /*
    2. 打印流：PrintStream 和PrintWriter

    2.1 提供了一系列重载的print() 和 println()
    2.2 练习：



     */

    @Test
    public void test2() {
        PrintStream ps = null;
        try {
            FileOutputStream fos = new FileOutputStream(new File("D:\\IO\\text.txt"));
            // 创建打印输出流,设置为自动刷新模式(写入换行符或字节 '\n' 时都会刷新输出缓冲区)
            ps = new PrintStream(fos, true);
            if (ps != null) {// 把标准输出流(控制台输出)改成文件
                System.setOut(ps);
            }


            for (int i = 0; i <= 255; i++) { // 输出ASCII字符
                System.out.print((char) i);
                if (i % 50 == 0) { // 每50个数据一行
                    System.out.println(); // 换行
                }
            }


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (ps != null) {
                ps.close();
            }
        }

    }

    /*
    3. 数据流
    3.1 DataInputStream 和 DataOutputStream
    3.2 作用：用于读取或写出基本数据类型的变量或字符串

    练习：将内存中的字符串、基本数据类型的变量写出到文件中。

    注意：处理异常的话，仍然应该使用try-catch-finally.
     */
    @Test
    public void test3() throws IOException {
        //1.
        DataOutputStream dos = new DataOutputStream(new FileOutputStream("data.txt"));
        //2.
        dos.writeUTF("刘建辰");
        dos.flush();//刷新操作，将内存中的数据写入文件
        dos.writeInt(23);
        dos.flush();
        dos.writeBoolean(true);
        dos.flush();
        //3.
        dos.close();


    }
    /*
    将文件中存储的基本数据类型变量和字符串读取到内存中，保存在变量中。

    注意点：读取不同类型的数据的顺序要与当初写入文件时，保存的数据的顺序一致！

     */
    @Test
    public void test4() throws IOException {
        //1.
        DataInputStream dis = new DataInputStream(new FileInputStream("data.txt"));
        //2.
        String name = dis.readUTF();
        int age = dis.readInt();
        boolean isMale = dis.readBoolean();

        System.out.println("name = " + name);
        System.out.println("age = " + age);
        System.out.println("isMale = " + isMale);

        //3.
        dis.close();

    }

}

```

# 输入/输出

## File类

不管是文件还是目录都可以使用File类来进行操作。包含新建、删除、重命名文件和目录。但是不能访问文件的内容进行操作。

# 面向对象

## OOA\OOD\OOP

OOA：面向对象分析

OOD:面向对象设计

OOP:面向对象编程

使用UML来描述并记录OOA和OOD的结果。

# 封装\继承\多态

+ 封装：将对象的实现细节隐藏起来。然后通过一些公用的方法来暴露该对象的功能
+ 继承：实现软件复用的重要手段。当子类继承父类的时候，子类可以直接获得父类的属性以及方法
+ 多态：子类对象可以直接赋值给父类的变量，但运行是依旧表现出子类的行为特征，也就是同一个类型的对象在执行同一个方法的时候表现出多种行为的特征

# var定义变量

java10引入的，但这并不是关键字。

为了简化局部变量的声明，var相当于一个动态类型，使用var定义的局部变量的类型是由编译器自动的推断，也就是定义变量的时候分配的初始值来决定他是什么类型。因此在使用var定义局部变量的时候一定要赋予初始值。

# 基本数据类型

```java
byte a = 56; //正确  系统会自动把56当作byte类型来处理
long bigValue = 99999999999999999999; //错误  系统不会把99999999999999999当作long型，超出了int的范围
long bigValue1 = 9999999999999999998L;//正确 使用L后缀强制使用long型

```

## 八进制0开头

## 十六进制0x\0X开头

## 二进制0b\0B开头

## 数值下面使用下划线分割

这个实在java7中引进的

数值下面使用下划线分割避免位数太多造成混淆

```java
int a = 12_56_45_41_45; 这是对的
```

# transient关键字

+ transient关键字只能修饰变量，而不能修饰方法和类。注意，本地变量是不能被transient关键字修饰的。
+  被transient关键字修饰的变量不再能被序列化，一个静态变量不管是否被transient修饰，均不能被序列化。
+ 一旦变量被transient修饰，变量将不再是对象持久化的一部分，该变量内容在序列化后无法获得访问。也可以认为在将持久化的对象反序列化后，被transient修饰的变量将按照普通类成员变量一样被初始化。

# **Java中的变量**

+ 成员变量(实例变量，属性)

+ 本地变量(局部变量)

+ 类变量（静态属性）

# java中冒号(:)的用法

你见到的冒号是在哪个地方的，java中不同的地方的冒号的作用是不同的，例如： 
 一、 

```java
 String[] abc = new String[3]{"a","b","c"}; 
 for （String str : abc）｛ 

 System.out.println(str);  //这个地方的冒号就是遍历abc的集合，取出每一个元素
 ｝
```

 二、三元运算符boolean?true的内容:false的内容

```java
 int a=1;
 int b=3;
 int i =a>b?3:1;//这个地方的冒号用来选择值，如果a大于b，那么i=3，否则i=1
```

 三、跳转标记

使用goto语句的话   就需要这样的标记，才能指定goto到哪儿。

```java
  public static void main(String[] args) {
        for(int i=0;i<5;i++){
            for(int j=0;j<5;j++){
                if(j==3){
                    continue;   //这个是当j=3的时候  就跳过这次的内循环
                }
                System.out.println(i+"  "+j);
            }
        }

    }
 /*
 0  0
0  1
0  2
0  4
1  0
1  1
1  2
1  4
2  0
2  1
2  2
2  4
3  0
3  1
3  2
3  4
4  0
4  1
4  2
4  4

Process finished with exit code 0

 */
 public static void main(String[] args) {
        out:for(int i=0;i<5;i++){
            for(int j=0;j<5;j++){
                if(j==3){
                    continue out;   //这儿是j=3的时候 直接让外循环跳过这次循环
                }
                System.out.println(i+"  "+j);
            }
        }

    }
/*
0  0
0  1
0  2
1  0
1  1
1  2
2  0
2  1
2  2
3  0
3  1
3  2
4  0
4  1
4  2

Process finished with exit code 0
*/
```

 4.switch选择

```java
 switch(i){
 case 1:oprate....；break；
 case 2:oprate....；break；
 case 3:oprate....；break；
 //用于switch选择，当i等于下面的值事，执行后面的操作，然后break跳出，如果没有break，那么将执行下面的oprate操作，一直到底！
 default：break；
 }
```

