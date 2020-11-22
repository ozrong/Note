# 字符串

## 1.不考虑反射，String类型变量所指向内存空间中的内容是不能被改变的

## 2.String str = new String(“abc”)，“abc”在内存中是怎么分配的？

答案：堆、字符常量区

**“abc”保存在常量池，str引用的对象保存在堆里，而java7中又把常量池移到了堆中，所以这题题目就不够严谨**

题目考查的为Java中的字符串常量池和JVM运行时数据区的相关概念。

**什么是字符串常量池？**

JVM为了减少字符串对象的重复创建，其维护了一个特殊的内存，这段内存被成为字符串常量池或者字符串字面量池

**工作原理**

当代码中出现字面量形式创建字符串对象时，JVM首先会对这个字面量进行检查，如果字符串常量池中存在相同内容的字符串对象的引用，则将这个引用返回，否则新的字符串对象被创建，然后将这个引用放入字符串常量池，并返回该引用。

**实现前提**

字符串常量池实现的前提条件就是==Java中String对象是不可变的==，这样可以安全保证多个变量共享同一个对象。如果Java中的String对象可变的话，一个引用操作改变了对象的值，那么其他的变量也会受到影响，显然这样是不合理的。

更详细的关于字符串常量池  http://droidyue.com/blog/2014/12/21/string-literal-pool-in-java/

**关于堆和栈**

Java中所有由类实例化的对象和数组都存放在堆内存中，无论是成员变量，局部变量，还是类变量，它们指向的对象都存储在堆内存中。而栈内存用来存储局部变量和方法调用。

更详细的关于堆和栈的区别 http://droidyue.com/blog/2014/12/07/differences-between-stack-and-heap-in-java/

**关于寄存器**

Java中运行时数据区有一个程序寄存器（又称程序计数器），该寄存器为线程私有。Java中的程序计数器用来记录当前线程中正在执行的指令。如果当前正在执行的方法是本地方法，那么此刻程序计数器的值为undefined

关于JVM运行时数据区的 http://droidyue.com/blog/2014/12/21/java-runtime-data-areas/

关于本题目中，"abc"为字面量对象，其存储在堆内存中。而字符串常量池则存储的是字符串对象的一个引用。

# 异常处理

## try-catch-finally

规则： 

​    1.try块是必须的，catch块和finally块都是可选的，但必须存在一个或都存在。try块不能单独存在。 

​    2.try块里的语句运行中出现异常会跳过try块里其他语句，直接运行catch里的语句。 

​    3.无论try块中是否有异常，无论catch块中的语句是否实现，都会执行finally块里的语句。 

​    4.如果try块或catch块中有return语句，finally块里的语句会执行在try块或catch块中的return语句前。 

​    5.如果finally块里有return语句，则直接返回，而不执行try块或catch块里的return语句。 

​    6.只有一种办法不执行finally块里的语句，那就是调用System.exit(1);方法，即退出java虚拟机。 

​    **强调：finally块里的语句在try或catch里的人return前执行**

## trowable 的子类 error 和 Exception

链接：https://www.nowcoder.com/questionTerminal/7aa513db3a124bb9bea49cd6fc184c0d
来源：牛客网

Exception 包括非检查性异常 RuntimeException，  及其子类，即运行时的异常，运行时的异常是代码的BUG，

  和检查性异常，即非运行时异常，程序在编译的时候会发现的异常 如: IOException之类,在处理类似文件流的时候，java强制规定必须处理可能遇到的文件流异常。

  runtimeException是运行时的异常，在运行期间抛出异常的超类，程序可以选择是否try-catch处理。

  其他的检查性异常（非运行时的异常，如IOException），是必须try-catch的，否则程序在编译的时候就会发现错误。

翻译：

a.虚拟机会自己抛出RuntimeException异常 

 b.RuntimeException异常不需要声明抛出或者被捕获 

  c.RuntimeException是Throwable的一个子类，它指示一个合理的应用程序不应该试图捕捉的严重问题（这是错误的）

  d:空指针异常（NullPointException）是 RuntimeException

# 线程

1.run()方法是用于定义线程的执行体

2.start()方法是启动线程

# 网络编程

## socket

socket编程中，以下哪个socket的操作是不属于服务端操作的(listen)

![img](https://uploadfiles.nowcoder.com/images/20180316/8955099_1521189690989_0BB28C2A1ECCC47EC020E89E8A554BBC)

链接：https://www.nowcoder.com/questionTerminal/38fb0886232b42b6a542c288d256eee1
来源：牛客网

**TCP客户端：** 

+ 1.建立连接套接字，设置Ip和端口监听，socket() 

+ 2.建立连接 connect 

+ 3.write() 获取网络流对象 发送数据 

+ 4.read()获取网络流对象 接收数据 

+ 5.关闭套接字 

  **TCP服务器端** 

+ 1.建立端口监听 socket() 

+ 2.绑定指定端口 bind() 

+ 3.listen 进行端口监听 

+ 4.accept() 阻塞式 直到有客户端访问 

+ 5.read()获取客户端发送数据 

+ 6.write()发送返回数据 

+ 7.close关闭端口监听

# JVM

## 关于JVM

+ 程序计数器是一个比较小的内存区域，用于指示当前线程所执行的字节码执行到了第几行，是线程隔离的
+ 虚拟机栈描述的是Java方法执行的内存模型，用于存储局部变量，操作数栈，动态链接，方法出口等信息，是线程隔离的
+ 原则上讲，所有的对象都在堆区上分配内存，是线程之间共享的
+ ==(**错误选项**)方法区用于存储JVM加载的类信息、常量、静态变量、以及编译器编译后的代码等数据，是线程隔离的==

## jvm内存模型图

![img](https://uploadfiles.nowcoder.com/images/20161223/415611_1482452184396_6FB53C51539B47559CF0D122A832CF63)

http://www.cnblogs.com/sunada2005/p/3577799.html

# 关键字

## final

final修饰的类肯定不能被继承,final修饰的方法不能被重载,final修饰的变量不允许被再次赋值

**(错误的final是java中的修饰符，可以修饰类、接口、抽象类、方法和属性)**

# others

## java工具

java,exe是java虚拟机：

javadoc.exe用来制作java文档

jdb.exe是java的调试器：如果编译器返回程序代码的错误，可以用它对程序进行调试

javaprof,exe是剖析工具