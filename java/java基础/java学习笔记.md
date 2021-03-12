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

#  类方法\变量和实例方法\变量

```JAVA
类变量  实例变量
    如同类的成员变量有实例变量和类变量两种一样，类的方法也有两种：实例方法和类方法。在方法定义时，冠以修饰字static的方法称为类方法，没有冠以static修饰字的方法是实例方法。
class D{
    int a;//实例变量
    static int c;//类变量
    float max(int x,int y)//实例方法
        ｛a = x>y?x:y;｝
    static int setCount(int c0)//类方法
        { c=c0;}
    static void incCount(int step)//类方法
        ｛c+=step;｝
}
//注意这是直接在类里面写的，不是在main方法中

调用关系
    
    类中的实例方法可以互相调用，并可调用类方法。类方法也能相互调用，但不能直接调用实例方法，除非类方法引入局部对象，然后通过局部对象调用实例方法。另外，类方法能直接引用类变量，不能引用实例变量。实例方法可引用实例变量，也可引用类变量
class E{
    float u;
    static float v;
    static void setUV(boolean f){
        u = s_m(f);// 非法，类方法不可以调用实例变量u
        v = s_m(f);//合法，类方法可以调用类方法
        v = r_m(!f);//非法，类方法不能直接调用实例方法。
    }
    static float s_m(boolean f){
        return f?u:v;//非法，类方法只能 类变量
    }
    float r_m(boolean f){
        return f?u:v;//合法，实例方法能引用实例变量和类变量
    }
}
//注意这是直接在类里面写的，不是在main方法中

```

# default方法

==default不是修饰符==



default修饰方法只能在接口中使用，在接口中被default标记的方法为普通方法，可以直接写方法体

Note:

实现类会继承接口中的default方法
如果接口A中有default方法：

public interface A {
	public default void a(){
		System.out.println("这是A");
	}
}
Test类实现接口A：

public class Test implements A{
	
}
那么Test类将会继承接口A中的a方法：

public class Test2 {
	public static void main(String[] args) {
		Test t = new Test();
		t.a();
	}
}
2.如果一个类同时实现接口A和B，接口A和B中有相同的default方法，这时，该类必须重写接口中的default方法,为什么要重写呢？是因为，类在继承接口中的default方法时，不知道应该继承哪一个接口中的default方法。



3.如果子类继承父类，父类中有b方法，该子类同时实现的接口中也有b方法（被default修饰），那么子类会继承父类的b方法而不是继承接口中的b方法

接口A：

public interface A {
	public default void b(){
		System.out.println("AAA");
	}
}
类C：

public class C {
	public void b(){
		System.out.println("CCC");
	}
}
子类：

public class Test extends C implements A{
	
}
说明子类继承的b方法为父类C中的b方法，不是接口中的default b(){}方法。

# Java中访问修饰符public、default、protect、private范围

public：Java语言中访问限制最宽的修饰符，一般称之为“公共的”。被其修饰的类、属性以及方法不仅可以跨类访问，而且允许跨包（package）访问。
protect：介于public 和 private 之间的一种访问修饰符，一般称之为“保护形”。被其修饰的类、属性以及方法只能被类本身的方法及子类访问，即使子类在不同的包中也可以访问。
default：即不加任何访问修饰符，通常称为“默认访问模式“。该模式下，只允许在同一个包中进行访问。
private：Java语言中对访问权限限制的最窄的修饰符，一般称之为“私有的”。被其修饰的类、属性以及方法只能被该类的对象访问，其子类不能访问，更不能允许跨包访问。
![image-20210301112042749](C:\Users\hp\AppData\Roaming\Typora\typora-user-images\image-20210301112042749.png)

# 函数式接口

函数式接口表示只包含一个抽象方法的接口，但是里面可以包含多个默认方法、类方法。





# java集合

Java的集合类主要由两个接口派生而出： Collection（Set、List、Queue）和Map （这两个是并列的）

集合类也叫容器类

数组元素可以是基本数据的值，也可以是对象。

集合里面只能是对象（基本数据类型也都变成的对应的封装类）

对于Set、List、Queue和Map四种集合， 最常用的实现类分别是HashSet、TreeSet、ArrayList、ArrayDeque 、LinkedList和HashMap、TreeMap等实现类。

## 1.Collection

### 1.1实现的方法

Collection 接口是List、Set和Queue接口的父接口

► boolean add(Object o): 该方法用千向集合里添加一个元素。如果集合对象被添加操作改变了，
则返回true。
► boolean addAll(Collection c): 该方法把集合c 里的所有元素添加到指定集合里。如果集合对象被
添加操作改变了， 则返回true。
► void clear(): 清除集合里的所有元素， 将集合长度变为0。

► boolean contains(Object o): 返回集合里是否包含指定元素。
► boolean containsAll(Collection c): 返回集合里是否包含集合c 里的所有元素。
► boolean isEmpty(): 返同集合是否为空。当集合长度为0 时返回true, 否则返同false。
► Iterator iterator(): 返同一个Iterator 对象， 用于遍历集合里的元素。
► boolean remove(Object o): 删除集合中的指定元素o ,当集合中包含 一个或多个元素o 时， 该方法只删除第一个符合条件的元素， 该方法将返回true。
► boolean removeAll(Collection c): 从集合中删除集合c 里包含的所有元素（相当于用调用该方法的集合减集合C), 如果删除了一个或一个以上的元素， 则该方法返回true。
► boolean retainAll(Collection c): 从集合中删除集合c 里不包含的元索（相当于把调用该方法的集合变成该集合和集合c 的交集）， 如果该操作改变了调用该方法的集合， 则该方法返回true 。
► int size(): 该方法返回集合里元素的个数。
►==Object[] toArray(): 该方法把集合转换成一个数组， 所有的集合元索变成对应的数组元素==

### 1.2遍历方法

#### 1.2.1.使用Lambda表达式遍历集合

Java 8 为lterable 接口新增了一个forEach(Consumer action)默认方法， 该方法所需参数的类型是一个函数式接口， 而Iterable 接口是Collection 接口的父接口， 因此Collection 集合也可直接调用该方法。(Collection 继承了lterable )

当程序调用Iterable 的forEach(Consumer action)遍历集合元素时， 程序会依次将集合元素传给Consumer 的accept(T t)方法（该接口中唯一的抽象方法）。正因为Consumer 是函数式接口， 因此可以使用Lambda 表达式来遍历集合元素。

```java
public class CollectionEach
public static void main(String[] args)
//创建一个集合
var books = new HashSet();
books.add("轻豐级Java EE企业应用实战ti ") ;
books.add("疯狂Java讲义" ) ;
books.add("疯狂Android讲义") ;
//调用forEach()方法遍历集合
books.forEach(obj -> System.outt.println("迭代集合元素： " + obj)) ;
}}
```

#### 1.2.2. 使用Iterator 遍历集合元素

Iterator 接口也是Java 集合框架的成员，但它与Collection 系列、Map 系列的集合不一样： Collection系列集合、Map 系列集合主要用千盛装其他对象， 而Iterator 则主要用千遍历（即迭代访问） Collection集合中的元素， Iterator 对象也被称为迭代器。

Iterator 接口里定义了如下4 个方法。
► boolean hasNext(): 如果被迭代的集合元素还没有被遍历完， 则返回true 
► Object next(): 返回集合里的下一个元素。
► void remove(): 删除集合里上一次next 方法返回的元素。
► void forEachRemaining(Consumer action), 这是Java 8 为Iterator 新增的默认方法， 该方法可使用Lambda 表达式来遍历集合元素。
![image-20210301210432545](C:\Users\hp\AppData\Roaming\Typora\typora-user-images\image-20210301210432545.png)

==Iterator 仅用于遍历集合， Iterator 本身并不提供盛装对象的能力。如果需
要创建Iterator 对象，则必须有一个被迭代的集合。没有集合的Iterator 仿佛无本之木，没有存在的价值==。



==Iterator 必须依附于Collection 对象， 若有一个Iterator 对象， 则必然有一个与之关联的Collection 对象。Iterator 提供了两个方法来迭代访问Collection 集合里的元素， 并可通过remove方法来删除集合中上一次next方法返回的集合元素。==

==当使用Iterator 对集合元素进行迭代时， Iterator 并不是把集合元素本身传给了迭代变量，而是把集合元素的值传给了迭代变量，所以修改迭代变量的值对集合元素本身没有任何影响==。

```java
public class ArrayQueueDemo {
	@Test
	public void  test(){
		Collection<Integer> ans = new ArrayList<>();
		ans.add(1);
		ans.add(2);
		ans.add(3);
		ans.add(4);
		Iterator<Integer> iterator = ans.iterator();
		while(iterator.hasNext()){
			int a = iterator.next();
			if(a==1){
				iterator.remove();  //这一步直接对ans这个列表进行操作的 删除了1
			}
			if(a==4){
				a=6;  //改变不了ans列表的值
			}
			System.out.println(a);
		}
		System.out.println("------------------------------");

		Iterator<Integer> iterator2 = ans.iterator();
		while(iterator2.hasNext()){
			int a2 = iterator2.next();
			System.out.println(a2);
		}
	}
}
/**
1
2
3
6
------------------------------
2
3
4

*/

```

#### 1.2.3.使用foreach 循环遍历集合元素

![image-20210301211737707](C:\Users\hp\AppData\Roaming\Typora\typora-user-images\image-20210301211737707.png)

==foreach 循环中的迭代变量也不是集合元素本身， 系统只是依次把集合元素的值赋给迭代变量， 因此在foreach 循环中修改迭代变量的值也
没有任何实际意义==

### 1.3对集合的操作

#### 1.3.1.使用Predicate 操作集合

Java 8 为Collection 集合新增了一个removelf(Predicate filter)方法， 该方法将会批量删除符合filter条件的所有元素。该方法需要一个Predicate (谓词）对象作为参数， Predicate 也是函数式接口， 因此可使用Lambda 表达式作为参数。

<img src="C:\Users\hp\AppData\Roaming\Typora\typora-user-images\image-20210301212241975.png" alt="image-20210301212241975" style="zoom:150%;" />

#### 1.3.2. 使用Stream 操作集合（这是流的知识可以先不管吧）

```java
	@Test
	public void  test2(){
		IntStream is = IntStream.builder().add(20).add(13).add(-2).add(18).build();//添加了元素的集合（等价于吧！！！）
//		System.out.println(is.max());//对is的操作只能执行一句
//		System.out.println(is.min());
//		System.out.println(is.sum());
		is.forEach(System.out::println);
	}
```

<img src="C:\Users\hp\AppData\Roaming\Typora\typora-user-images\image-20210301214613051.png" alt="image-20210301214613051" style="zoom:150%;" />![image-20210301214706931](C:\Users\hp\AppData\Roaming\Typora\typora-user-images\image-20210301214706931.png)

<img src="C:\Users\hp\AppData\Roaming\Typora\typora-user-images\image-20210301214613051.png" alt="image-20210301214613051" style="zoom:150%;" />![image-20210301214706931](C:\Users\hp\AppData\Roaming\Typora\typora-user-images\image-20210301214706931.png)

<img src="C:\Users\hp\AppData\Roaming\Typora\typora-user-images\image-20210301214726523.png" alt="image-20210301214726523" style="zoom:150%;" />

### 1.4 Set    继承Collection

实际上Set 就是Collection, 只是行为略有不同(Set不允许包含重复元素）。

#### 1.4.1 HashSet 

HashSet是set接口的典型实现，注意并不是继承的set

```java
public class HashSet<E>extends AbstractSet<E>implements Set<E>, Cloneable, java.io.Serializable
```



HashSet按Hash算法来存储集合中的元素， 因此具有很好的存取和查找性能。

HashSet具有以下特点。
► 不能保证元素的排列顺序， 顺序可能与添加顺序不同， 顺序也有可能发生变化。
► HashSet不是同步的， 如果多个线程同时访问一个HashSet, 假设有两个或者两个以上线程同时
修改了HashSet集合时， 则必须通过代码来保证其同步。
► 集合元素值可以是null。

==当向HashSet集合中存入一个元素时， HashSet会调用该对象的hashCode()方法来得到该对象的
hashCode值，然后根据该hashCode值决定该对象在HashSet 中的存储位置。如果有两个元素通过equals()
方法比较返回true, 但它们的hashCode()方法返回值不相等， HashSet 将会把它们存储在不同的位置，
依然可以添加成功。
也就是说， HashSet集合判断两个元素相等的标准是两个对象通过equals()方法比较相等， 并且两
个对象的hashCodeQ方法返回值也相等==。



当把一个对象放入HashSet中时， 如果需要重写该对象对应类的equals()方法， 则也应该重
写其hashCode()方法。规则是： 如果两个对象通过equals()方法比较返回true, 这两个对象的hashCode
值也应该相同。
如果两个对象通过equals()方法比较返回true,但这两个对象的hashCode()方法返回不同的hashCode
值时，这将导致HashSet会把这两个对象保存在Hash表的不同位置，从而使两个对象都可以添加成功，
这就与Set集合的规则冲突了。
如果两个对象的hashCodeQ方法返回的hashCode值相同，但它们通过equals()方法比较返回false
时将更麻烦：因为两个对象的hashCode值相同，HashSet将试图把它们保存在同一个位置，但又不行（否
则将只剩下一个对象），所以实际上会在这个位置用链式结构来保存多个对象； 而HashSet访问集合元
素时也是根据元素的hashCode值来快速定位的，如果HashSet中两个以上的元素具有相同的hashCode
值， 将会导致性能下降。

##### 1.4.1.1 HashSet实现原理总结

HashSet的实现原理总结如下：

①是基于HashMap实现的，默认构造函数是构建一个初始容量为16，负载因子为0.75 的HashMap。封装了一个 HashMap 对象来存储所有的集合元素，所有放入 HashSet 中的集合元素实际上由 HashMap 的 key 来保存，而 HashMap 的 value 则存储了一个 PRESENT，它是一个静态的 Object 对象。

②当我们试图把某个类的对象当成 HashMap的 key，或试图将这个类的对象放入 HashSet 中保存时，重写该类的equals(Object obj)方法和 hashCode() 方法很重要，而且这两个方法的返回值必须保持一致：当该类的两个的 hashCode() 返回值相同时，它们通过 equals() 方法比较也应该返回 true。通常来说，所有参与计算 hashCode() 返回值的关键属性，都应该用于作为 equals() 比较的标准。

③HashSet的其他操作都是基于HashMap的。

==补充==

JDK 8.0 之前，只要产生碰撞，就在链表上加一个元素，随着元素增多，HashSet 的性能降低。
JDK 8.0 及之后，当碰撞阈值达到 8 时，自动把链表转换成红黑树，目的是为了提高 HashSet 的性能

##### 1.4.1.2   HashSet 集合不可重复的原理 (判断是否添加的步骤)

当第一个元素添加到 Set 集合中时，会优先调用该对象的 hashCode() 方法得到哈希值，根据这个哈希值来决定该对象存放在集合的哪个位置。
当第二个元素添加到 Set 集合中时，会继续调用该对象的 hashCode() 方法得到哈希值，此时会有两种情况：

+ 哈希值不同，说明是不同的对象，则根据这个哈希值来决定新元素存放的位置。
+ 哈希值相同，此时还不能说明是相同的对象，所以会继续调用 equals() 方法。
  如果 equals() 方法返回 true，说明是相同对象，则该元素将不被添加到集合中；如果 equals() 方法返回 false，说明这两个对象虽然哈希值一样，但是是不同的对象，则在该哈希值对应的位置产生链表。

#####  1.4.1.3 HashSet中是可能出现重复的元素的

如果向HashSet 中添加一个可变对象后， 后面程序修改了该可变对象的实例变量， 则可能导致它与集合中的其他元素相同（即两个对象通过equ als() 方法比较回true, 两个对象的hashCode 值也相等），
这就有可能导致HashSet 中包含两个相同的对象。下面程序演示了这种情况。

**注意**：这儿的重复不是指添加了重复的元素，而是添加了元素过后，这个元素发生了改变，变成了与Set中的元素一样的了

```java
class R
{
	int count;
	public R(int count)
	{
		this.count = count;
	}
	public String toString()
	{
		return "R[count:" + count + "]";
	}
	public boolean equals(Object obj)
	{
		if (this == obj)
			return true;
		if (obj != null && obj.getClass() == R.class)
		{
			R r = (R) obj;
			return this.count == r.count;
		}
		return false;
	}
	public int hashCode()
	{
		return this.count;
	}
}
public class HashSetTest2
{
	public static void main(String[] args)
	{
		HashSet<R> hs = new HashSet();
		hs.add(new R(5));
		hs.add(new R(-3));
		hs.add(new R(9));
		hs.add(new R(-2));
		// 打印HashSet集合，集合元素没有重复
		System.out.println(hs);
		// 取出第一个元素
		Iterator<R> it = hs.iterator();
		R first = (R) it.next();
		// 为第一个元素的count实例变量赋值
		first.count = -3;     // ①
		// 再次输出HashSet集合，集合元素有重复元素
		System.out.println(hs);
		// 删除count为-3的R对象
		hs.remove(new R(-3));    // ②
		// 可以看到被删除了一个R元素
		System.out.println(hs);
		System.out.println("hs是否包含count为-3的R对象？"
			+ hs.contains(new R(-3))); // 输出false
		System.out.println("hs是否包含count为-2的R对象？"
			+ hs.contains(new R(-2))); // 输出false
	}
}

/**
[R[count:-2], R[count:-3], R[count:5], R[count:9]]
[R[count:-3], R[count:-3], R[count:5], R[count:9]]
[R[count:-3], R[count:5], R[count:9]]
hs是否包含count为-3的R对象？false
hs是否包含count为-2的R对象？false
*/
```

上面程序中提供了R 类， R 类重写了equals(Objectobj)方法和hashCode() 方法， 这两个方法都是根据R 对
象的count 实例变量来判断的。上面程序的＠号粗体字代码处改变了Set 集合中第一个R 对象的count 实例变
量的值，这将导致该R 对象与集合中的其他对象相同。

HashSet 集合中的第1 个元素和第2 个元素完全相同， 这表明两个元素已
经重复。此时HashSet 会比较混乱： 当试图删除count 为-3 的R 对象时， HashSet 会计算出该对象的
hashCode 值， 从而找出该对象在集合中的保存位置， 然后把此处的对象与count为-3 的R 对象通过
equals() 方法进行比较， 如果相等则删除该对象— -HashSet 只有第2 个元素才满足该条件（第1 个元素
实际上保存在count为-2 的R 对象对应的位置）， 所以第2 个元素被删除。至于第一个count为-3 的R
对象， 它保存在count 为-2 的R 对象对应的位置， 但使用equals() 方法拿它和count 为-2 的R 对象比较
时又返回false-——这将导致HashSet 不可能准确访问该元素。

```java
第一个对象和第二个对象一样吗？？？？ 

Iterator<R> it = hs.iterator();
        R first = (R) it.next();
        // 为第一个元素的count实例变量赋值
        first.count = -3;     // ①
        // 再次输出HashSet集合，集合元素有重复元素
        System.out.println(hs);

        R second = (R) it.next();


        System.out.println("输出一下第一个对象和第二个对象一样吗？"+(first.equals(second)));//输出：ture

```

#### 1.4.2 LinkedHashSet类(set接口的实现类 继承HashSet)

```java
public class LinkedHashSet<E>
    extends HashSet<E>
    implements Set<E>, Cloneable, java.io.Serializable
```



LinkedHashSet集合也是根据元素的hashCode值来决定元素的存储位置， 但它同时使用双向链表维护元素的次序， 这样使得元素看起来是以插入的顺序保存的。也
就是说， 当遍历LinkedHashSet集合里的元素时，LinkedHashSet将会按元素的添加顺序来访问集合里的元素。

**注意：**输出LinkedHashSet集合的元素时， 元素的顺序总是与添加顺序一致。虽然LinkedHashSet使用了双向链表记录集合元素的添加顺序， 但LinkedHashSet依然是HashSet , 因此它依然不允许集合元素重复。

#### 1.4.3 TreeSet

#### 1.4.4 EnumSet

#### 1.4.5 各Set实现类的性能分析

HashSet 和TreeSet 是Set 的两个典型实现，到底如何选择HashSet 和TreeSet 呢? HashSet 的性能总是比TreeSet 好（特别是最常用的添加、查询元素等操作）， 因为TreeSet 需要额外的红黑树算法来维护集合元素的次序。只有当需要一个保持排序的Set 时， 才应该使用TreeSet, 否则都应该使用HashSet。HashSet 还有一个子类： LinkedHashSet, 对于普通的插入、删除操作， LinkedHashSet 比HashSet要略微慢一点， 这是由维护链表所带来的额外开销造成的， 但由于有了链表， 遍历LinkedHashSet 会更快。

### 1.5 List  继承Collection

#### 1.5.1 list的方法

List 作为Collection 接口的子接口， 当然可以使用Collection 接口里的全部方法。而且由千List 是有序集合， 因此List 集合里增加了一些根据索引来操作集合元素的方法。
► void add(int index, Object element): 将元素element 插入到List 集合的index 处。
► boolean addAll(int index, Collection c): 将集合c 所包含的所有元素都插入到List 集合的index
处。
► Object get(int index): 返回集合index 索引处的元素。
► int indexOf(Object o): 返回对象o 在List 集合中第一次出现的位置索引。
► int lastlndexOf(Object o): 返回对象o 在List 集合中最后一次出现的位置索引。
► Object remove(int index): 删除并返回index 索引处的元素。
► Object set(int index, Object element): 将index 索引处的元素替换成element 对象， 返回被替换的旧元素。
► List subList(int fromlndex, int tolndex): 返回从索引fromlndex (包含）到索引tolndex (不包含）
处所有集合元素组成的子集合。所有的List 实现类都可以调用这些方法来操作集合元素。与Set 集合相比， List 增加了根据索引来插入、替换和删除集合元素的方法。除此之外， Java 8还为List 接口添加了如下两个默认方法。
► void replaceAll(UnaryOperator operator): 根据operator 指定的计算规则重新设置List 集合的所有元素。
► void sort(Comparator c): 根据Comparator 参数对List 集合的元素排序。



与Set 只提供了一个iterator()方法不同， List 还额外提供了一个listlterator()方法， 该方法返回一个Listlterator 对象， Listlterator接口继承了Iterator 接口， 提供了专门操作List 的方法。Listlterator接口在Iterator 接口基础上增加了如下方法。
► boolean hasPrevious(): 返回该迭代器关联的集合是否还有上一个元素。

► Object previous(): 返回该迭代器的上一个元素。
► void add(Object o): 在指定位置插入一个元素。
拿Listlterator 与普通的Iterator 进行对比， 不难发现Listlterator 增加了向前迭代的功能(Iterator 只
能向后迭代）， 而且Listlterator 还可通过add()方法向List 集合中添加元素(Iterator 只能删除元素）。

```java
public class ListIteratorTest
{
	public static void main(String[] args)
	{
		String[] books = {
			"疯狂Java讲义", "疯狂iOS讲义",
			"轻量级Java EE企业应用实战"
		};
		var bookList = new ArrayList();
		for (var i = 0; i < books.length; i++)
		{
			bookList.add(books[i]);
		}
		var lit = bookList.listIterator();
		// 从前向后遍历
		while (lit.hasNext())
		{
			System.out.println(lit.next());
			lit.add("-------分隔符-------");
		}
		System.out.println("=======下面开始反向迭代=======");
		// 从后向前遍历
		while (lit.hasPrevious())
		{
			System.out.println(lit.previous());
		}
	}
}
```

#### 1.5.2  Arraylist和Vector实现类（List类的实现）

两者都是基于数组实现的，都在底层封装了一个动态的、允许在分配的Object[]数组。ArrayList或Vector 对象使用initialCapacity 参数来设置该数组的长度， 当向ArrayList或Vector 中添加元素超出了该数组的长度时， 它们的initialCapacity 会自动增加。

如果向ArrayList或Vector 集合中添加大量元素时， 可使用ensureCapacity(int minCapacity) 方法一次性地增加
initialCapacity。这可以减少重分配的次数， 从而提高性能。

如果开始就知道ArrayList或Vector 集合需要保存多少个元素， 则可以在创建它们时就指定initialCapacity大小。==如果创建空的ArrayList或Vector集合时不指定initial Capacity参数， 则Object[]数组的长度默认为10==。

**ArrayList和Vector的显著区别是**： ArrayList是线程不安全的， 当多个线程访问同一个
ArrayList集合时， 如果有超过一个线程修改了ArrayList集合， 则程序必须手动保证该集合的同步性；
但Vector集合则是线程安全的， 无须程序保证该集合的同步性。因为Vector是线程安全的，所以Vector
的性能比ArrayList的性能要低。实际上， 即使需要保证List集合线程安全， 也同样不推荐使用Vector
实现类。后而会介绍一个Collections工具类， 它可以将一个ArrayList变成线程安全的。

#### 1.5.3 固定长度的List

前面讲数组时介绍了一个操作数组的工具类： Arrays, 该工具类里提供了asList(Object... a)方法，
该方法可以把一个数组或指定个数的对象转换成一个List集合， 这个List集合既不是ArrayList实现类
的实例， 也不是Vector实现类的实例， 而是Arrays的内部类ArrayList的实例。
Arrays.ArrayList是一个固定长度的List 集合， 程序只能遍历访问该集合里的元素， 不可增加、删
除该集合里的元素。如下程序所示。

### 1.6 Queue集合

Queue用于模拟队列这种数据结构， 队列通常是指“ 先进先出" (FIFO)的容器

#### 1.6.1 Queue接口中定义了如下几个方法。
► void add(Object e): 将指定元素加入此队列的尾部。
► Object element(): 获取队列头部的元素， 但是不删除该元素。
► boolean offer(Object e): 将指定元素加入此队列的尾部。当使用有容量限制的队列时， 此方法通
常比add(Object e)方法更好。
► Object peek(): 获取队列头部的元素， 但是不删除该元素。如果此队列为空， 则返回null。
► Object poll(): 获取队列头部的元素， 并删除该元素。如果此队列为空， 则返回null。
► Object remove(): 获取队列头部的元素， 并删除该元素。

Queue接口有一个PriorityQueue实现类。除此之外， Queue还有一个Deque接口， Deque代表一个
“ 双端队列“， 双端队列可以同时从两端来添加、删除元素， 因此Deque的实现类既可当成队列使用，
也可当成栈使用。Java为Deque提供了ArrayDeque和LinkedList两个实现类。

#### 1.6.2 PriorityQueue实现类

PriorityQueue是一个比较标准的队列实现类。之所以说它是比较标准的队列实现，而不是绝对标准
的队列实现， 是因为PriorityQueue保存队列元素的顺序并不是按加入队列的顺序，而是按队列元素的
大小进行重新排序。因此当调用peekO方法或者pollO方法取出队列中的元素时， 并不是取出最先进入
队列的元素， 而是取出队列中最小的元素。从这个意义上来看，PriorityQueue已经违反了队列的最基本
规则： 先进先出(FIFO)。

```java
public class PriorityQueueTest
{
	public static void main(String[] args)
	{
		var pq = new PriorityQueue();
		// 下面代码依次向pq中加入四个元素
		pq.offer(6);
		pq.offer(-3);
		pq.offer(20);
		pq.offer(18);
		// 输出pq队列，并不是按元素的加入顺序排列
		System.out.println(pq); // 输出[-3, 6, 20, 18]
		// 访问队列第一个元素，其实就是队列中最小的元素：-3
		System.out.println(pq.poll());
	}
}
```

运行上面程序直接输出PriorityQueue集合时， 可能看到该队列里的元素并没有很好地按大小进行排序，但这只是受到PriorityQueue的toString()方法的返回值的影响。实际上，程序多次调用PriorityQueue集合对象的poll()方法， 即可看到元素按从小到大的顺序“ 移出队列”。

#### 1.6.3 Deque 接口与ArrayDeque 实现类

==Deque 接口是Queue 接口的子接口==， 它代表一个双端队列， Deque 接口里定义了一些双端队列的方法， 这些方法允许从两端来操作队列的元素。
► void addFirst(Object e): 将指定元素插入该双端队列的开头。
► void addLast(Object e): 将指定元素插入该双端队列的末尾。
► Iterator descendinglterator(): 返回该双端队列对应的迭代器， 该迭代器将以逆向顺序来迭代队列
中的元素。
► Object getFirst(): 获取但不删除双端队列的第一个元素。
► Object getLast(): 获取但不删除双端队列的最后一个元素。
► boolean offerFirst(Object e): 将指定元素插入该双端队列的开头。
► boolean offerLast(Object e): 将指定元素插入该双端队列的末尾。
► Object peekFirst(): 获取但不删除该双端队列的第一个元素；如果此双端队列为空， 则返回nuJl。
► Object peekLast(): 获取但不删除该双端队列的最后一个元素；如果此双端队列为空，则返回null。
► Object pollFirst(): 获取并删除该双端队列的第一个元素； 如果此双端队列为空， 则返回null。
► Object pollLast{): 获取并删除该双端队列的最后一个元素；如果此双端队列为空， 则返回null。
► Object pop() (栈方法）： pop 出该双端队列所表示的栈的栈顶元素。相当于removeFirst()。
► void push(Object e) (栈方法）： 将一个元素push 进该双端队列所表示的栈的栈顶。相当千
addFirst(e)。
► Object remove First(): 获取并删除该双端队列的第一个元素。
► Object removefirstOccurrence(Object o): 删除该双端队列的第一次出现的元素o。
► Object removeLastQ: 获取并删除该双端队列的最后一个元素。
► boolean removeLastOccurrence(Object o): 删除该双端队列的最后一次出现的元素o。



==Deque 不仅可以当成双端队列使用， 而且可以被当成栈来使用==



**Deque 接口提供了一个典型的实现类： ArrayDeque**



**==注意：单端队列的插入都是插到队尾的==**



**ArrayDeque可以作为==队列==也可以作为==栈==**

```java
栈：
public class ArrayDequeStack
{
	public static void main(String[] args)
	{
		var stack = new ArrayDeque();
		// 依次将三个元素push入"栈"
		stack.push("疯狂Java讲义");
		stack.push("轻量级Java EE企业应用实战");
		stack.push("疯狂Android讲义");
		// 输出：[疯狂Android讲义, 轻量级Java EE企业应用实战, 疯狂Java讲义]
		System.out.println(stack);
        
		// 访问第一个元素，但并不将其pop出"栈"，输出：疯狂Android讲义
		System.out.println(stack.peek());
        
		// 依然输出：[疯狂Android讲义, 疯狂Java讲义, 轻量级Java EE企业应用实战]
		System.out.println(stack);
        
		// pop出第一个元素，输出：疯狂Android讲义
		System.out.println(stack.pop());
        
		// 输出：[轻量级Java EE企业应用实战, 疯狂Java讲义]
		System.out.println(stack);
	}
}

队列
public class ArrayDequeQueue
{
	public static void main(String[] args)
	{
		var queue = new ArrayDeque();
		// 依次将三个元素加入队列
		queue.offer("疯狂Java讲义");
		queue.offer("轻量级Java EE企业应用实战");
		queue.offer("疯狂Android讲义");
		// 输出：[疯狂Java讲义, 轻量级Java EE企业应用实战, 疯狂Android讲义]
		System.out.println(queue);
        
		// 访问队列头部的元素，但并不将其poll出队列"栈"，输出：疯狂Java讲义
		System.out.println(queue.peek());
        
		// 依然输出：[疯狂Java讲义, 轻量级Java EE企业应用实战, 疯狂Android讲义]
		System.out.println(queue);
        
		// poll出第一个元素，输出：疯狂Java讲义
		System.out.println(queue.poll());
        
		// 输出：[轻量级Java EE企业应用实战, 疯狂Android讲义]
		System.out.println(queue);
	}
}    
    
```

#### 1.6.4 Deque 的方法与Queue 的方法

| Queue 的方法     | Deque 的方法              |
| ---------------- | ------------------------- |
| add(e)/offer(e)  | addFirst(e)/offerFirst(e) |
| remove(e)        | removeFirst()/pollFirst() |
| element()/peek() | getFirst()/peekFirst()    |

#### 1.6.5 Deque 的方法与Stack 的方法

| Stack 的方法 | Deque 的方法              |
| ------------ | ------------------------- |
| push(e)      | addFirst(e)/offerFirst(e) |
| Pop()        | removeFirst()/pollFirst() |
| peek()       | getFirst()/peekFirst()    |

#### 1.6.5 Deque /ArrayDeque作为队列和栈的理解

**一.作为队列**

+ addLast(e)/offerLast(e)才是等价于Queue的add(e),因为队列是从队尾添加的。

**二.作为栈**

+ 作为栈的时候使用push(e)等价于offerFirst()/addFirst()。显然使用push是插入队列的头部 
+ 当出栈的时候是从头部开始出（这一点和队列保持一样），pop()方法就相当于pollFirst()/removeFirst()



### 1.7 LinkedList

```jAVA
public class LinkedList<E>
    extends AbstractSequentialList<E>
    implements List<E>, Deque<E>, Cloneable, java.io.Serializable
```

==LinkedList 类是List接口的实现类——这意味着它是一个List集合==

==LinkedList还实现了Deque接口， 可以被当成双端队列来使用,这也意味着这个也可以当做栈==



```java
public class LinkedListTest
{
    public static void main(String[] args)
    {
        LinkedList books = new LinkedList();
        // 将字符串元素加入队列的尾部
        books.offer("A");

        // 将一个字符串元素加入栈的顶部
     //   books.push("B");
        books.offer("B");
        books.offer("H");
        books.offer("J");


        // 将字符串元素添加到队列的头部（相当于栈的顶部）
        books.offerFirst("C");

        // 将字符串元素添加到队列的尾部（相当于栈的底部）
        books.offerLast("D");

        // 以List的方式（按索引访问的方式）来遍历集合元素
        for (int i = 0; i < books.size(); i++)
        {
            System.out.println("遍历中：" + books.get(i));
        }

        // 访问、并不删除栈顶的元素
        System.out.println(books.peekFirst());
        // 访问、并不删除队列的最后一个元素
        System.out.println(books.peekLast());
        // 将栈顶的元素弹出“栈”
        System.out.println(books.pop());
        // 下面输出将看到队列中第一个元素被删除
        System.out.println(books);
        // 访问、并删除队列的最后一个元素
        System.out.println(books.pollLast());
        // 下面输出：[A, B, H, J]
        System.out.println(books);
    }
}
```



上面程序中粗体字代码分别示范了==LinkedList作为List集合、双端队列、栈的用法==。由此可见，LinkedList是一个功能非常强大的集合类。

LinkedList与ArrayList、ArrayDeque的实现机制完全不同，ArrayList、ArrayDeque内部以数组的形式来保存集合中的元素，因此随机访问集合元素时有较好的性能；而LinkedList内部以链表的形式来保存集合中的元素，==因此随机访问集合元素时性能较差，但在插入、删除元素时性能比较出色==（只需改变指针所指的地址即可）。需要指出的是， 虽然Vector也是以数组的形式来存储集合元素的， 但因为它实现了线程同步功能（而且实现机制也不好）， 所以各方面性能都比较差。

==注意==：对于所有的内部基于数组的集合实现， 例如ArrayList、ArrayDeque等，使用随机访问的性能比使用Iterator迭代访问的性能要好， 因为随机访问会被映射成对数组元素的访问。

### 1.8 各种线性表的性能分析

一般来说，由于数组以一块连续内存区来保存所有的数组元素，所以数组在随机访问时性能最好，所有的内部以数组作为底层实现的集合在随机访问时性能都比较好；而内部以链表作为底层实现的集合在执行插入、删除操作时有较好的性能。但总体来说， ArrayList的性能比LinkedList的性能要好， 因此大部分时候都应该考虑使用ArrayList。
**关于使用List集合有如下建议。**
► 如果需要遍历List集合元素，对于ArrayList、Vector集合，应该使用随机访问方法(get)来遍历集合元素，这样性能更好；对千LinkedList集合，则应该采用迭代器(Iterator)来遍历集合元素。
► 如果需要经常执行插入、删除操作来改变包含大量数据的List 集合的大小，可考虑使用LinkedList集合。使用ArrayList、Vector集合可能需要经常重新分配内部数组的大小，效果可能较差。
► 如果有多个线程需要同时访问List集合中的元素，开发者可考虑使用Collections将集合包装成线程安全的集合。

## 2. Map

### 2.1 Map和Set的关系

Set与 Map之间的关系非常密切。虽然 Map中放的元素是key-value对，Set 集合中放的元素是单个对象,但如果把key-value对中的 value当成key的附庸:key在哪里, value就跟在哪里。这样就可以像对待Set一样来对待Map了。事实上，Map提供了一个 Entry内部类来封装key-value对，而计算Entry存储时则只考虑Entry封装的key。从Java源码来看，Java是先实现了Map，然后通过包装一个所有 value 都为空对象的Map 就实现了Set集合。

### 2.2 Map实现的方法

► void clear(): 删除该Map 对象中的所有key-value 对。
► boolean containsKey(Object key): 查询Map 中是否包含指定的key, 如果包含则返回true。
► boolean contains Value(Object value): 查询Map 中是否包含一个或多个value, 如果包含则返回true。
► Set entrySet(): 返回Map 中包含的key-value 对所组成的Set 集合，每个集合元素都是Map.Entry(Entry 是Map 的内部类）对象。
► Object get(Object key): 返回指定key 所对应的value; 如果此Map 中不包含该key, 则返回null。
► boolean isEmpty(): 查询该Map 是否为空（即不包含任何key-value 对）， 如果为空则返回true。
► Set keySet(): 返回该Map 中所有key 组成的Set 集合。
► Object put(Object key, Object value): 添加一个key-value 对， 如果当前Map 中已有一个与该key相等的key-value 对， 则新的key-value 对会覆盖原来的key-value 对。
► void putAll(Map m): 将指定Map 中的key-value 对复制到本Map 中。
► Object remove(Object key): 删除指定key 所对应的key-value 对，返回被删除key 所关联的value,如果该key 不存在， 则返回null。
► boolean remove(Object key, Object value): 这是Java 8 新增的方法， 删除指定key 、value 所对应的key-value 对。如果从该Map 中成功地删除该key-value 对，该方法返回true, 否则返回false。
► int size(): 返回该Map 里的key-value 对的个数。
► Collection values(): 返回该Map 里所有value 组成的Collection。Map 接门提供了大量的实现类， 典型实现如HashMap 和Hashtable 等、HashMap 的子类LinkedHashMap , 还有SortedMap 子接口及该接口的实现类TreeMap , 以及WeakHashMap 、IdentityHashMap 等。下面将详细介绍Map接口实现类。



**Map 中包括一个内部类Entry, 该类封装一个key-value 对。Entry 包含如下三个方法。**
► Object getKey(): 返回该Entry 里包含的key 值。
► Object get Value(): 返回该Entry 里包含的value 值。
► Object setValue(V value): 设置该Entry 里包含的value 值， 并返回新设置的value 值。

#### 2.2.1 java8 为Map增加的方法

Java 8 除为Map 增加了remove(Object key, Object value)默认方法之外， 还增加了如下方法。
► Object compute(Object key, BiFunction remappingFunction): 该方法使用remappingFunction 根据原key-value 对计算一个新value。只要新value 不为null, 就使用新value 覆盖原value; 如果原value 不为null, 但新value 为null, 则删除原key-value 对；如果原value 、新value 同时为null,那么该方法不改变任何key-value 对， 直接返回null。

► Object computelfAbsent(Object key, Function mappingFunction): 如果传给该方法的key 参数在Map 中对应的value 为null, 则使用mappingFunction 根据key 计算一个新的结果， 如果计算结果不为null, 则用计算结果稷盖原有的value。如果原Map 原来不包括该key, 那么该方法可能会添加一组key-value 对。

► Object computelfPresent(Object key, BiFunction remappingFunction): 如果传给该方法的key 参数在Map 中对应的value 不为null, 该方法将使用remappingfunction 根据原key 、value 计算一个新的结果， 如果计算结果不为null, 则使用该结果投盖原来的va]ue; 如果计算结果为null, 则删除原key-value 对。

► void forEach(BiConsumer action): 该方法是Java 8 为Map 新增的一个遍历key-value 对的方法，通过该方法可以更简洁地遍历Map 的key-value 对。

► Object getOrDefault(Object key, V defaultValue): 获取指定key 对应的value。如果该key 不存在，则返回defau] t Value。

► Object merge(Object key, Object value, BiFunction remappingFunction): 该方法会先根据key 参数获取该Map 中对应的value。如果获取的value 为null, 则直接用传入的value 覆盖原有的value（在这种情况下， 可能要添加一组key-value 对）； 如果获取的value 不为null , 则使用
remappingFunction 函数根据原value、新vaJue 计算一个新的结果， 并用得到的结果去覆盖原有的value。

► Object putlfAbsent(Object key, Object value): 该方法会自动检测指定key 对应的value 是否为null,如果该key 对应的value 为null, 该方法将会用新value 代替原来的null 值。

► Object replace(Object key, Object value): 将Map 中指定key 对应的value 替换成新value。与传统put()方法不同的是， 该方法不可能添加新的key-value 对。如果尝试替换的key 在原Map 中不存在， 该方法不会添加key-value 对， 而是返回null。

► boolean replace(K key, V oldValue, V newValue): 将Map 中指定key-value 对的原value 替换成新value。如果在Map 中找到指定的key-value 对， 则执行替换并返回true, 否则返回false。

► replaceAll(BiFunction function): 该方法使用BiFunction对原key-value对执行计算， 并将计算结果作为该key-value对的value值。

### 2.3 HashMap和Hashtable

==Hashtable，注意不是HashTable。从Hashtable 的类名上就可以看出它是一个古老的类，它的命名甚至没有遵守Java 的命名规范==



Java 8改进了HashMap的实现， 使用HashMap存在key冲突时依然具有较好的性能。

**此外， Hashtable和HashMap存在两点典型区别。**
► Hashtable是一个线程安全的Map 实现，但HashMap是线程不安全的实现， 所以HashMap比Hashtable的性能高一点；但如果有多个线程访问同一个Map对象时， 使用Hasbtable实现类会更好。

**注意**：与Vector 类似的是， 尽量少用Hashtable实现类， 即使需要创建线程安全的Map 实现类， 也无须使用Hashtable 实现类， 可以通过==Collections 工具类把HashMap 变成线程安全的==。

► Hashtable 不允许使用null作为key和value, 如果试图把null值放进Hashtable中， 将会引发NullPointerException异常；但HashMap可以使用null作为key或value。由千HashMap里的key不能重复，所以HashMap里最多只有一个key-value对的key为null, 但可以有无数多个key-value对的value为null。下面程序示范了用null值作为HashMap的key和value的情形。

### 2.4 自定义的类作为Key要注意的

为了成功地在HashMap、Hashtable 中存储、获取对象， 用作key 的对象必须实现hashCode()方法和equals()方法。

**注意：**当使用自定义类作为HashMap、Hashtable 的key 时，如果重写该类的equals(Object obj) I和hashCodeQ方法， 则应该保证两个方法的判断标准一致—— 当两个key 通过equals()方法比较返回true 时， 两个key 的hashCode()返回值也应该相同。因为HashMap、Hashtable保存key 的方式与HashSet 保存集合元素的方式完全相同， 所以HashMap、Hashtable 对key 的要求与HashSet 对集合元素的要求完全相同。



除此之外， HashMap、Hashtable 中还包含一个containsValue()方法，用于判断是否包含指定的value。那么HashMap 、Hashtable 如何判断两个value 相等呢? HashMap 、Hasbtable 判断两个value 相等的标准更简单： 只要两个对象通过equals()方法比较返回true 即可

### 2.5 HashMap也可能出现重复的key

这儿的出现不是指添加了重复的key，而是之前添加的key是不一样的，但是在后面这个key变成了和另一个key一样的了，和Hash情况一样的

### 2.6 LinkedHashMap(继承HashMap 实现Map接口)

```java
public class LinkedHashMap<K,V>
    extends HashMap<K,V>
    implements Map<K,V>
```

LinkedHashMap和LinkedHashSet的底层都是使用的双向链表。LinkedHashMap使用双向链表来维护key-value 对的顺序（其实只需要考虑key 的顺序）， 该链表负责维护Map 的迭代顺序， 迭代顺序与key-value 对的插入顺序保待一致。

==LinkedHashMap 可以记住key-value 对的添加顺序。==

LinkedHashMap 可以避免对HashMap、Hashtable 里的key-value 对进行排序（只要插入key-value对时保持顺序即可，HashMap/Hashtabel是要按照key的HashCode来进行的排序）。 同时又可避免使用TreeMap 所增加的成本。

```java
public void  test3(){
		LinkedHashMap<String,Integer> scores = new LinkedHashMap();
		scores.put("语文", 80);
		scores.put("英文", 82);
		scores.put("数学", 76);
		// 调用forEach方法遍历scores里的所有key-value对
		scores.forEach((key, value) -> System.out.println(key + "-->" + value));

	}
/**
语文-->80
英文-->82
数学-->76
*/
```

### 2.7 使用Properties读写属性文件

==Properties相当于一个Map<String,String>==。

Properties 类是Hashtable 类的子类， 正如它的名字所暗示的， 该对象在处理属性文件时特别方便(Windows 操作平台上的ini 文件就是一种属性文件）。Properties 类可以把Map 对象和属性文件关联起来，从而可以把Map 对象中的key-value 对写入属性文件中，也可以把属性文件中的“ 属性名＝属性值”
加载到Map 对象中。由千属性文件里的属性名、属性值只能是字符串类型， 所以Properties里的key 、value 都是字符串类型。该类提供了如下三个方法来修改Properties 里的key 、value 值。

► String getProperty(String key): 获取Properties 中指定属性名对应的属性值， 类似于Map 的get(Object key)方法。► String getProperty(String key, String default Value): 该方法与前一个方法基本相似。该方法多一个功能， 如果Properties中不存在指定的key 时， 则该方法指定默认值。

► Object setProperty(String key, String value): 设置属性值， 类似于Hashtable 的put()方法。除此之外， 它还提供了两个读写属性文件的方法。

► void load(lnputStream inStream): 从属性文件（以输入流表示）中加载key-value 对，把加载到的key-value 对追加到Properties 里(Properties 是Hashtable 的子类， 它不保证key-value 对之间的次序）。

► void store(OutputStream ou􀄫String comments): 将Properties中的key-value 对输出到指定的属性
文件（以输出流表示）中。

```java
public class PropertiesTest
{
	public static void main(String[] args)
		throws Exception
	{
		var props = new Properties();
		// 向Properties中增加属性
		props.setProperty("username", "yeeku");
		props.setProperty("password", "123456");
		// 将Properties中的key-value对保存到a.ini文件中
		props.store(new FileOutputStream("a.ini"),
			"comment line");   // ①
		// 新建一个Properties对象
		var props2 = new Properties();
		// 向Properties中增加属性
		props2.setProperty("gender", "male");
		// 将a.ini文件中的key-value对追加到props2中
		props2.load(new FileInputStream("a.ini"));   // ②
		System.out.println(props2);
	}
}
```

上面程序示范了Properties 类的用法， 其中＠号粗体字代码将Properties 对象中的key-value 对写入a.ini 文件中；＠号粗体字代码则从a.ini 文件中读取key-value 对， 并添加到props2 对象中。编译、运行上面程序， 该程序输出结果如下：

```java
{password=l23456, gender=male, username=yeeku}
```

还在当前路径下生成了一个a.ini 文件，

==Properties 可以把key-value 对以XML 文件的形式保存起来， 也可以从XML 文件中加载key-value对， 用法与此类似==

### 2.8 SortedMap 接口和TreeMap 实现类

正如Set 接口派生出SortedSet 子接口， SortedSet 接口有一个TreeSet 实现类一样， Map 接口也派生出一个SortedMap 子接口， SortedMap 接口也有一个TreeMap 实现类。

TreeMap 就是一个红黑树数据结构， 每个key-value 对即作为红黑树的一个节点。TreeMap 存储key-value 对（节点） 时， 需要根据key 对节点进行排序。TreeMap 可以保证所有的key-value 对处于有序状态。TreeMap 也有两种排序方式。
► 自然排序： TreeMap 的所有key 必须实现Comparable 接口， 而且所有的key 应该是同一个类的对象， 否则将会抛出ClassCastException 异常。
► 定制排序： 创建TreeMap 时， 传入一个Comparator 对象， 该对象负责对TreeMap 中的所有key进行排序。采用定制排序时不要求Map 的key 实现Comparable 接口。

类似于TreeSet 中判断两个元素相等的标准， TreeMap 中判断两个key 相等的标准是： 两个key 通过compareTo()方法返回O, TreeMap 即认为这两个key 是相等的。如果使用自定义类作为TreeMap 的key, 且想让TreeMap 良好地工作， 则重写该类的equalsO方和compareTo()方法时应保持一致的返回结果： 两个key 通过equals()方法比较返回true 时， 它们通过compare To()方法比较应该返回0。如果equals()方法与compareTo()方法的返回结果不一致， TreeMap与Map 接口的规则就会冲突。 

### 2.9 Map各种实现类的性能比较

对千Map的常用实现类而言， 虽然HashMap和Hashtable的实现机制几乎一样， 但由千Hashtable是一个古老的、线程安全的集合， 因此HashMap通常比Hashtable要快。
TreeMap通常比HashMap、Hashtable要慢（尤其在插入、删除key-value对时更慢）， 因为TreeMap底层采用红黑树来管理key-value对（红黑树的每个节点就是一个key-value对）。使用TreeMap有一个好处： TreeMap中的key-value对总足处f有序状态，无须专门进行排序操作。当TreeMap被填允之后，就可以调用keySet(), 取得山key组成的Set, 然后使川t oArray ()方法生成key的数组， 接下来使用Arrays的binarySearch()方法在已排序的数组中快速地查询对象。

对于一般的应用场景， 程序应该多考虑使用HashMap, 因为HashMap正是为快速查询设计的(HashMap底层其实也是采用数组来存储key-value对）。但如果程序需要一个总是排好序的Map时， 则可以考虑使用TreeMap。LinkedHashMap比HashMap慢点，因为它需要维护链表米保持Map 中key-value时的添加顺序。
ldentityHashMap性能没有特别出色之处，因为它采用与HashMap基本相似的实现， 只是它使用一而不是equals()方法来判断元素相等。EnumMap的性能最好，但它只能使用同－个枚举类的枚举值作为key。



## 3 Set和Map

再次强调： Set 和Map 的关系十分密切， Java 源码就是先实现了HashMap 、TreeMap等集合， 然后通过包装一个所有的value 都为空对象的Map 集合实现了Set 集合类。

#### 3.1 HashSet和HashMap的性能选项

对于HashSet及其子类而言， 它们采用hash算法来决定集合中元素的存储位置， 并通过hash算法来控制集合的大小；对于HashMap、Hashtable及其子类而言， 它们采用hash算法来决定Map中key的存储， 并通过hash算法来增加key集合的大小。



hash表里可以存储元素的位置被称为“ 桶(bucket) ", 在通常情况下， 单个“ 桶” 里存储一个元素，此时有最好的性能： hash 算法可以根据hashCode 值计
算出“ 桶＂ 的存储位置， 接着从“ 桶” 中取出元素。但hash 表的状态是open 的： 在发生"hash 冲突＂ 的情况下， 单个桶会存储多个元素， 这些元素以链表形
式存储， 必须按顺序搜索。如图8.8 所示是hash 表保存各元素， 且发生"hash 冲突＂ 的示意图。

![image-20210305155508185](C:\Users\hp\AppData\Roaming\Typora\typora-user-images\image-20210305155508185.png)

因为HashSet 和HashMap、Hashtable 都使用hash算法来决定其元素(HashMap 则只考虑key) 的存储，因此HashSet、HashMap 的hash 表包含如下属性。

► 容量(capacity): hash 表中桶的数噩。
► 初始化容量(initial capacity): 创建hash 表时桶的数量。HashMap 和HashSet 都允许在构造器
中指定初始化容量。
► 尺寸(size): 当前hash 表中记录的数量。
► 负载因子(load factor): 负载因子等千"size/capacity"。负载因子为o, 表示空的hash 表， 0.5
表示半满的hash 表， 依此类推。轻负载的hash 表具有冲突少、适宜插入与查询的特点（但是使
用Iterator 迭代元素时比较慢）。

除此之外，hash 表里还有一个“ 负载极限"'"负载极限” 是一个0~1 的数值， “ 负载极限＂ 决定了hash 表的最大填满程度。当hash 表中的负载因子达到指定的“ 负载极限” 时， hash 表会自动成倍地增加容量（桶的数量）， 并将原有的对象重新分配， 放入新的桶内， 这称为rehashing。HashSet 和HashMap、Hashtable 的构造器允许指定－ 个负载极限， HashSet 和HashMap、Hashtable
默认的＂ 负载极限” 为0.75,

## 4.有对象A和B， A.equals(B) == true, A和B的 hashCode一定相同，两个对象 hashCode相同，他们可以不 equals

面试中曾经有这么一道题目，考察的是开发者对于 equals()和 hashCode()的理解，
题目是这样的，

> 有对象A和B， A.equals(B) == true, A和B的 hashCode可以不同。

答案是否定的。如果A和B equals的话，那么他们的哈希值一定要相同。
理解这个问题，首先要明白 equals和 hashCode扮演的是什么角色。

### equals的原则

在Java中对 equals有这么几个原则，
· 自反性: A.equals(A) == true
· 对称性: if(A.equals(B)), then B.equals(A) == true
· 一致性: 在A B没有被修改的前提下，多次 A.equals(B)的结果应该一致
· 传递性: A.eqausl(B) == true, B.eqauls(C) == true, then A.equals(C) == true

### hashCode的计算

举个例子，在没有 hashCode的情况下，在 Set集合中存储1000个对象的话需要用 equals来比较对象的值是否重复，
我们知道 Set是不允许重复对象存在的，
那么当这一千个对象都不重复的情况下，
第1000个对象的存储需要调用1000次 equals去进行比较，这是非常低效的。

而hashCode能解决这种问题，对象的存储不再是顺序存放，而是通过 hashCode直接计算出存储的位置，
(可以理解为内存地址，虽然并不是)
之后新对象在存储的时候如果 hashCode跟之前的没有重复则直接存储，如果重复了则用 equals()校验是否相等，
如果不相等的话，以 HashMap作为例子，默认是在同一个地址上用链表存储起来新的对象，
这在之前介绍哈希冲突的解决办法那篇文章里提到过。

### equals和 hashCode的总结

在理解了上面 equals的原则和 hashCode的原则之后我们可以推导出这么个结论，
· 如果两个对象 equals，那么他们的 hashCode一定要相同(否则在Set中就会出现重复元素)
· 如果两个对象 hashCode相同，他们可以不 equals
所以如果不好记住这俩的关系的话，可以试着从数据集合的存储这个角度出发来理解eqauls和 hashCode哦。

## 5 操作集合的工具类： Collections（多了个s ）

### 5.1 排序

Collections 提供了如下常用的类方法用千对List 集合元素进行排序。
► void reverse(List list): 反转指定List 集合中元素的顺序。
► void shuffle(List list): 对List 集合元素进行随机排序(shuffle 方法模拟了“ 洗牌“ 动作）。
► void sort(List list): 根据元素的自然顺序对指定List 集合的元素按升序进行排序。
► void sort(List list, Comparator c): 根据指定Comparator 产生的顺序对List 集合元素进行排序。
► void swap(List list, int i, int j): 将指定List 集合中的t处元素和j处元素进行交换。
► void rotate(List list, int distance): 当distance 为正数时， 将list 集合的后distance 个元素“ 整体”移到前面； 当distance 为负数时， 将list 集合的前distance 个元素“ 整体＂ 移到后面。该方法不会改变集合的长度。

### 5.2 查找、替换操作

Collections还提供了如下常用的用千查找、替换集合元素的类方法。

► int binarySearch(List list, Object key): 使用二分搜索法搜索指定的List集合，以获得指定对象在List集合中的索引。如果要使该方法可以正常工作， ==则必须保证List 中的元素已经处与有序状态==。
► Object max(Collection coll): 根据元素的自然顺序， 返回给定集合中的最大元素。
► Object max(Collection coll, Comparator comp): 根据Comparator 指定的顺序， 返同给定集合中的最大元素。
► Object min(Collection coll): 根据元素的自然顺序， 返回给定集合中的最小元素。
► Object min(Collection coll, Comparator comp): 根据Comparator 指定的顺序， 返回给定集合中的最小元素。
► void fill(List list, Object obj): 使用指定元素obj 替换指定List 集合中的所有元素。
► int frequency(Collection c, Object o): 返回指定集合中指定元素的出现次数。
► int indexOfSubList(List source, List target): 返回子List 对象在父List 对象中第一次出现的位置索引； 如果父List 中没有出现这样的子List, 则返回-1 。
► int lastlndexOfSubList(List source, List target): 返回子List 对象在父List 对象中最后一次出现的位置索引；如果父List 中没有出现这样的子List, 则返回-l 。
► boolean replaceAll(List list, Object oldVal, Object new Val): 使用一个新值newVal 替换List 对象的所有旧值oldVal。

### 5.3 同步控制（线程安全）

Collections 类中提供了多个synchronizedXxx()方法，该方法可以将指定集合包装成线程同步的集合，从而可以解决多线程并发访问集合时的线程安全问题。
Java 中常用的集合框架中的实现类HashSet 、TreeSet 、ArrayList 、ArrayDeque 、LinkedList 、HashMap和TreeMap 都是线程不安全的。如果有多个线程访问它们， 而且有超过一个的线程试图修改它们， 则存在线程安全的问题。Collections 提供了多个类方法可以把它们包装成线程同步的集合。

下面的示例程序创建了4个线程安全的集合对象。

```java
public class SynchronizedTest
{
	public static void main(String[] args)
	{
		// 下面程序创建了四个线程安全的集合对象
		var c = Collections.synchronizedCollection(new ArrayList());
		var list = Collections.synchronizedList(new ArrayList());
		var s = Collections.synchronizedSet(new HashSet());
		var m = Collections.synchronizedMap(new HashMap());
	}
}

```

### 5.4 Java 9新增的不可变集合

Java 9终千增加这个功能了—— 以前假如要创建一个包含6个元素的Set 集合，程序需要先创建Set集合，然后调用6次addO方法向Set集合中添加元素。Java 9对此进行了简化，程序直接调用Set、List、Map的of()方法即可创建包含N个兀素的不可变集合， 这样一行代码就可创建包含N个元素的集合。

==不可变意味着程序不能向集合中添加元素， 也不能从集合中删除元素==。

```java
public class Java9Collection
{
	public static void main(String[] args)
	{
		// 创建包含4个元素的Set集合
		var set = Set.of("Java", "Kotlin", "Go", "Swift");
		System.out.println(set);
		// 不可变集合，下面代码导致运行时错误
//		set.add("Ruby");
		// 创建包含4个元素的List集合
        
		var list = List.of(34, -25, 67, 231);
		System.out.println(list);
		// 不可变集合，下面代码导致运行时错误
//		list.remove(1);
		// 创建包含3组key-value对的Map集合
        
		var map = Map.of("语文", 89, "数学", 82, "英语", 92);
		System.out.println(map);
		// 不可变集合，下面代码导致运行时错误
//		map.remove("语文");
		// 使用Map.entry()方法显式构建key-value对
		var map2 = Map.ofEntries(Map.entry("语文", 89),
			Map.entry("数学", 82),
			Map.entry("英语", 92));
		System.out.println(map2);
	}
}
```

上面粗体字代码示范了如何使用集合元素创建不可变集合， 其中Set 、List 比较简单， 程序只要为它们的of() 方法传入N 个集合元素即可创建Set 、List 集合。
从上面粗体字代码可以看出， **创建不可变的Map 集合有两个方法： 使用of()方法时只要依次传入多个key-value 对即可；还可使用ofEntries()**

# 泛型

包含泛型声明的类型可以在定义变量、创建对象时传入一个类型实参，从而可以动态地生成无数多个==逻辑上的子类==，但这种子类==在物理上并不存在==。







# java基础类库

## 1. public static void main(String[]  args)

**为什么时public**：Java类由JVM调用，为了让JVM可以自由调用这个main()方法，所以使用public
修饰符把这个方法暴露出来

**为什么是static**：因为调用这个方法的时候不会先创建这个类的对象，在通过对象再来调用这个主方法。而是直接调用的这个主方法。

**为什么是void**：既然是JVM调用的这个方法，那么返回值也是返回给JVM。这没有任何意义

```java
public class Aclass{
 public static void main(String[]  args){}
}
使用 java Aclass str1 str2
    那么  args[0]=str1, args[1]=str2
某参数本身包含了空格， 则应该将该参数用双引号"" 括起来，   
```

## 2.键盘输出

```java
Scanner in = new Scanner(System.in);
```

Scanner主要提供了两个方法来扫描输入。
► hasNextXxx(): 是否还有下一个输入项，其中Xxx可以是Int、Long等代表基本数据类型的字符
串。如果只是判断是否包含下一个字符串， 则直接使用hasNext()。
► nextXxx(): 获取下一个输入项。Xxx的含义与前一个方法中的Xxx相同。

```java
public class ScannerKeyBoardTest
{
	public static void main(String[] args)
	{
		// System.in代表标准输入，就是键盘输入
		var sc = new Scanner(System.in);
		// 增加下面一行将只把回车作为分隔符
		// sc.useDelimiter("\n");
		// 判断是否还有下一个输入项
		while (sc.hasNext())
		{
			// 输出输入项
			System.out.println("键盘输入的内容是："
				+ sc.next());
		}
	}
}
```





► boolean hasNextLine(): 返回输入源中是否还有下一行。
► String nextLine(): 返回输入源中下一行的字符串。

==Scanner不仅能读取用户的键盘输入， 还可以读取文件输入。只要在创建Scanner对象时传入一个File对象作为参数， 就可以让Scanner读取该文件的内容。例如如下程序。==

```java
public class ScannerFileTest
{
	public static void main(String[] args)
		throws Exception
	{
		// 将一个File对象作为Scanner的构造器参数，Scanner读取文件内容
		var sc = new Scanner(new File("ScannerFileTest.java"));
		System.out.println("ScannerFileTest.java文件内容如下：");
		// 判断是否还有下一行
		while (sc.hasNextLine())
		{
			// 输出文件中的下一行
			System.out.println(sc.nextLine());
		}
	}
}
```

## 3. Object类



Object类是所有类、数组、枚举类的父类， 也就是说， Java允许把任何类型的对象赋给Object类
型的变量。==当定义一个类时没有使用extends关键字为它显式指定父类， 则该类默认继承Object父类==

### 3.1 操作对象的Objects 工具类

## 4. Java 9 改进的String 、String Buffer 和String Builder类

字符串就是一连串的字符序列， Java 提供了String、StringBuffer 和StringBuilder 三个类来封装字符串， 并提供了一系列方法来操作字符串对象。

==String 类是不可变类， 即一旦一个String 对象被创建以后， 包含在这个对象中的字符序列是不可改变的， 直至这个对象被销毁==。

StringBuffer对象则代表一个字符序列可变的字符串， 当一个StringBuffer被创建以后， 通过
StringBuffer提供的append() 、insert()、reverse()、setCharAt()、setLength()等方法可以改变这个字符串对象的字符序列。一旦通过StringBuffer 生成了最终想要的字符串， 就可以调用它的toString()方法将其转为String对象。

StringBuffer和StringBuilder（JDK1.5新增）基本相似。只是StringBuffer是线程安全的，而StringBuilder不是。所以StringBuilder的效率要高于StringBuffer。如果只是要创建一个可以变的字符串就可以使用StringBuilder

### 4.1 String

**构造器**

String 类提供了大量构造器来创建String 对象， 其中如下几个有特殊用途。
► String(): 创建一个包含0 个字符串序列的String 对象（并不是返回null)。
► String(byteO bytes, Charset charset): 使用指定的字符集将指定的byte[]数组解码成一个新的String对象。
► String(byte[] bytes, int offset, int length): 使用平台的默认字符集将指定的byte[]数组从offset 开始、长度为length 的子数组解码成一个新的String 对象。
► String(byte[] bytes, int offset, int length, String charsetName): 使用指定的字符集将指定的byte[]数组从offset 开始、长度为Jength 的子数组解码成一个新的String 对象。
► String(byte[] bytes, String charsetName): 使用指定的字符集将指定的byte[]数组解码成一个新的String 对象。
► String(charO value, int offset, int count): 将指定的字符数组从offset 开始、长度为count 的字符元素连缀成字符串。
► String(String original): 根据字符串直接量来创建一个String 对象。也就是说， 新创建的String对象是该参数字符串的副本。
► String(StringBuffer buffer): 根据StringBuffer 对象来创建对应的String 对象。
► String(StringBuilder builder): 根据StringBuilder 对象来创建对应的String 对象。

**操作字符串的方法：**

**► char charAt(int index): 获取字符串中指定位置的字符。**

**►int compareTo(S tring  anotherString): 比较两个字符串的大小。如果两个字符串的字符序列相等，则返回0; 不相等时， 从两个字符串第0 个字符开始比较， 返回第一个不相等的字符差。另一种情况，较长字符串的前面部分恰巧是较短的字符串， 则返回它们的长度差。**

**► String concat(String str): 将该String 对象与str 连接在一起。与Java 提供的字符串连接运算符‘ +’的功能相同。**
**► boolean contentEquals(StringBuffer sh): 将该String 对象与StringBuffer 对象sh 进行比较， 当它们包含的字符序列相同时返回true。**
► static String copyValueOf(char[] data): 将字符数组连缀成字符串， 与String(char[] content)构造器的功能相同。
► static String copyValueOf(char[] data, int offset, int count): 将char 数组的子数组中的元素连缀成字符串， 与String(char[] value, int offset, int cou nt)构造器的功能相同。
► boolean endsWith(String suffix): 返回该String 对象是否以suffix 结尾。

**► boolean equals(Object anObject): 将该字符串与指定对象比较， 如果二者包含的字符序列相等，则返回true; 否则返回false。**
► boolean equalslgnoreCase(String str): 与前一个方法基本相似， 只是忽略字符的大小写。
► byte[] getBytes(): 将该String 对象转换成byte 数组。
► void getChars(int srcBegin, int srcEnd, char[] dst, int dstBegin): 该方法将字符串中从srcBegin 开始，到srcEnd 结束的字符复制到dst 字符数组中， 其中dstBegin 为目标字符数组的起始复制位置。

**► int indexOf(int ch): 找出ch 字符在该字符串中第一次出现的位置。**
► int indexOf(int ch, int fromlndex): 找出ch 字符在该字符串中从fromlndex 开始后第一次出现的位置。
**► int indexOf(String str): 找出str 子字符串在该字符串中第一次出现的位置。**
► int indexOf(String str, int fromlndex ): 找出str 子字符串在该字符串中从fromlndex 开始后第一次出现的位置。

► int lastlndexOf(int ch): 找出ch 字符在该字符串中最后一次出现的位置。
► int lastlndexOf(int ch, int fromlndex): 找出ch 字符在该字符串中从fromlndex 开始后最后一次出现的位置。
**► int lastlndexOf(String str): 找出str 子字符串在该字符串中最后一次出现的位置。**
► int lastlndexOf(String str, int fromlndex): 找出str 子字符串在该字符串中从fromlndex 开始后最后一次出现的位置。
► int length(): 返同当前字符串长度。
► String replace(char oldChar, char newChar): 将字符串中的第一个oldChar 替换成newChar。
► boolean starts With(String prefix): 该String 对象是否以prefix 开始。
► boolean startsWith(String prefix, int toffset): 该String 对象从toffset 位置算起，是否以prefix 开始。

► String substring(int beginlndex): 获取从beginlndex 位置开始到结束的子字符串。
► String substring(int beginlndex, int endlndex): 获取从beginlndex 位置开始到endlndex 位置的子字
符串。
**► char[]  toCharArray(): 将该String 对象转换成char 数组。**
**► String toLowerCase(): 将字符串转换成小写。**
**► String toUpperCase(): 将字符串转换成大写。**

**► static String valueOf(X x): 一系列用千将基本类型值转换为String 对象的方法。**

==String 类是不可变的， String 的实例一旦生成就不会再改变了，==

```java
    public void test1() {
        String str = "de";
        str = str+"s"; //des
        str = str+"oz";//desoz
        System.out.println(str);
    }
不会报错，但是会生成一些额外的字符串,也就是"de","des"依旧在内存中的。当然这个"desoz"也在内存中。只不过str指向了它。
    
```

### StringBuffer和StringBuilder

因为String 是不可变的， 所以会额外产生很多临时变量， 使用StringBuffer或StringBuilder 就可以避免这个问题。
StringBuilder 提供了一系列插入、追加、改变该字符串里包含的字符序列的方法。而StringBuffer与其用法完全相同， 只是Stri ngBuffer 是线程安全的

StringBuilder、StringBuffer 有两个属性： length 和capacity, 其中length 属性表示其包含的字符序列的长度。与String 对象的length 不同的是， StringBuilder、StringBuffer 的length 是可以改变的， 可以通过length()、setLength(int len)方法来访问和修改其字符序列的长度。capacity 属性表示StringBuilder的容量， capacity 通常比length 大， 程序通常无须关心capacity 属性。

==也就是capacity 表是可以装多少，而length就是装了多少==



如下程序示范了StringBuilder 类的用法。

```java
public class StringBuilderTest
{
	public static void main(String[] args)
	{
		var sb = new StringBuilder();
		// 追加字符串
		sb.append("java");//sb = "java"
		// 插入
		sb.insert(0, "hello "); // sb="hello java"
		// 替换
		sb.replace(5, 6, ","); // sb="hello,java"
		// 删除
		sb.delete(5, 6); // sb="hellojava"
		System.out.println(sb);
		// 反转
		sb.reverse(); // sb="avajolleh"
		System.out.println(sb);
		System.out.println(sb.length()); // 输出9
		System.out.println(sb.capacity()); // 输出16
		// 改变StringBuilder的长度，将只保留前面部分
		sb.setLength(5); // sb="avajo"
		System.out.println(sb);
	}
}
```

## 5.Math类

Java 提供了Math 工具类来完成这些复杂的运算， Math类是一个工具类， 它的构造器被定义成private 的，==因此无法创建Math 类的对象； Math 类中的所有方
法都是类方法， 可以直接通过类名来调用它们==

Math 类除提供了大量静态方法之外， 还提供了两个类变量：==PI和E, 正如它们名字所暗示的， 它们的值分别等于兀和e==

```java
public class MathTest
{
	public static void main(String[] args)
	{
		/*---------下面是三角运算---------*/
		// 将弧度转换角度
		System.out.println("Math.toDegrees(1.57)："
			+ Math.toDegrees(1.57));
		// 将角度转换为弧度
		System.out.println("Math.toRadians(90)："
			+ Math.toRadians(90));
		// 计算反余弦，返回的角度范围在 0.0 到 pi 之间。
		System.out.println("Math.acos(1.2)：" + Math.acos(1.2));
		// 计算反正弦；返回的角度范围在 -pi/2 到 pi/2 之间。
		System.out.println("Math.asin(0.8)：" + Math.asin(0.8));
		// 计算反正切；返回的角度范围在 -pi/2 到 pi/2 之间。
		System.out.println("Math.atan(2.3)：" + Math.atan(2.3));
		// 计算三角余弦。
		System.out.println("Math.cos(1.57)：" + Math.cos(1.57));
		// 计算值的双曲余弦。
		System.out.println("Math.cosh(1.2 )：" + Math.cosh(1.2 ));
		// 计算正弦
		System.out.println("Math.sin(1.57 )：" + Math.sin(1.57 ));
		// 计算双曲正弦
		System.out.println("Math.sinh(1.2 )：" + Math.sinh(1.2 ));
		// 计算三角正切
		System.out.println("Math.tan(0.8 )：" + Math.tan(0.8 ));
		// 计算双曲正切
		System.out.println("Math.tanh(2.1 )：" + Math.tanh(2.1));
		// 将矩形坐标 (x, y) 转换成极坐标 (r, thet));
		System.out.println("Math.atan2(0.1, 0.2)：" + Math.atan2(0.1, 0.2));
		/*---------下面是取整运算---------*/
		// 取整，返回小于目标数的最大整数。
		System.out.println("Math.floor(-1.2 )：" + Math.floor(-1.2 ));
		// 取整，返回大于目标数的最小整数。
		System.out.println("Math.ceil(1.2)：" + Math.ceil(1.2));
		// 四舍五入取整
		System.out.println("Math.round(2.3 )：" + Math.round(2.3 ));
		/*---------下面是乘方、开方、指数运算---------*/
		// 计算平方根。
		System.out.println("Math.sqrt(2.3 )：" + Math.sqrt(2.3 ));
		// 计算立方根。
		System.out.println("Math.cbrt(9)：" + Math.cbrt(9));
		// 返回欧拉数 e 的n次幂。
		System.out.println("Math.exp(2)：" + Math.exp(2));
		// 返回 sqrt(x2 +y2)
		System.out.println("Math.hypot(4, 4)：" + Math.hypot(4, 4));
		// 按照 IEEE 754 标准的规定，对两个参数进行余数运算。
		System.out.println("Math.IEEEremainder(5, 2)："
			+ Math.IEEEremainder(5, 2));
		// 计算乘方
		System.out.println("Math.pow(3, 2)：" + Math.pow(3, 2));
		// 计算自然对数
		System.out.println("Math.log(12)：" + Math.log(12));
		// 计算底数为 10 的对数。
		System.out.println("Math.log10(9)：" + Math.log10(9));
		// 返回参数与 1 之和的自然对数。
		System.out.println("Math.log1p(9)：" + Math.log1p(9));
		/*---------下面是符号相关的运算---------*/
		// 计算绝对值。
		System.out.println("Math.abs(-4.5)：" + Math.abs(-4.5));
		// 符号赋值，返回带有第二个浮点数符号的第一个浮点参数。
		System.out.println("Math.copySign(1.2, -1.0)："
			+ Math.copySign(1.2, -1.0));
		// 符号函数；如果参数为 0，则返回 0；如果参数大于 0，
		// 则返回 1.0；如果参数小于 0，则返回 -1.0。
		System.out.println("Math.signum(2.3)：" + Math.signum(2.3));
		/*---------下面是大小相关的运算---------*/
		// 找出最大值
		System.out.println("Math.max(2.3, 4.5)：" + Math.max(2.3, 4.5));
		// 计算最小值
		System.out.println("Math.min(1.2, 3.4)：" + Math.min(1.2, 3.4));
		// 返回第一个参数和第二个参数之间与第一个参数相邻的浮点数。
		System.out.println("Math.nextAfter(1.2, 1.0)："
			+ Math.nextAfter(1.2, 1.0));
		// 返回比目标数略大的浮点数
		System.out.println("Math.nextUp(1.2 )：" + Math.nextUp(1.2 ));
		// 返回一个伪随机数，该值大于等于 0.0 且小于 1.0。
		System.out.println("Math.random()：" + Math.random());
	}
}

```

# 树


![image-20210312085027329](C:\Users\hp\AppData\Roaming\Typora\typora-user-images\image-20210312085027329.png)

**结点的度：**指的是结点所拥有子树的棵数。如A的度为3，F的度为0，即叶子结点的度为0，而树的度则是树中各个结点度的最大值，如图（d）树的度为3（A结点）

**树的层：**又称结点的层，该属性反映结点处于树中的层次位置，我们约定根结点的层为1，如上图所示，A层为1，B层为2，E的层为3。
**树的高度(深度)：**是指树中结点的最大层数，图（d）的高度为3。
**边：**边表示从父母结点到孩子结点的链接线，如上图（d）中A到B间的连线则称为边。

## 1.二叉树

![image-20210312094120109](C:\Users\hp\AppData\Roaming\Typora\typora-user-images\image-20210312094120109.png)

**性质**

1. 若根结点的层次为1，则二叉树第i层最多有$2^{i-1}(i \geq 1)$个结点
2. 在高度为h的二叉树中，最多有$2^h−1$个结点$h≥0$
3. 满二叉树和完全二叉树,一棵高度为h的满二叉树（Full Binary Tree）是具有$2^h−1$个结点的二叉树。满二叉树的最大特点是每一层次的结点数都达到最大值，我们可以对满二叉树的结点进行连续编号并约定根结点的序号为0，从根结点开始，自上而下，每层自左向右编号。如下图所示（a）：![image-20210312094538294](C:\Users\hp\AppData\Roaming\Typora\typora-user-images\image-20210312094538294.png)     对于完全二叉树，假设二叉树的深度为h，除第 h 层外，其它各层 $(1～h-1) $的结点数都达到最大个数，第 h 层所有的结点都连续集中在最左边，这就是完全二叉树。如同上图（b）所示，后面我们会通过层序遍历的算法来构造完全二叉树。

  ### 1.1 二叉查找树

参考：https://blog.csdn.net/javazejian/article/details/53727333

#### 1.1.1 二叉查找树的插入算法的设计与实现

```java
@Override
public void insert(T data) {
    if (data==null)
        throw new RuntimeException("data can\'Comparable be null !");
    //插入操作
    root=insert(data,root);
}

/**
 * 插入操作,递归实现
 * @param data
 * @param p
 * @return
 */
private BinaryNode<T> insert(T data,BinaryNode<T> p){
    if(p==null){
        p=new BinaryNode<>(data,null,null);
    }

    //比较插入结点的值，决定向左子树还是右子树搜索
    int compareResult=data.compareTo(p.data);

    if (compareResult<0){//左
        p.left=insert(data,p.left);
    }else if(compareResult>0){//右
        p.right=insert(data,p.right);
    }else {
        ;//已有元素就没必要重复插入了
    }
    return p;
}
```

#### 1.1.2 二叉查找树的删除算法的设计与实现

```java
@Override
public void remove(T data) {
  if(data==null)
      throw new RuntimeException("data can\'Comparable be null !");
  //删除结点
  root=remove(data,root);
}

/**
* 分3种情况
* 1.删除叶子结点(也就是没有孩子结点)
* 2.删除拥有一个孩子结点的结点(可能是左孩子也可能是右孩子)
* 3.删除拥有两个孩子结点的结点
* @param data
* @param p
* @return
*/
private BinaryNode<T> remove(T data,BinaryNode<T> p){
  //没有找到要删除的元素,递归结束
  if (p==null){
      return p;
  }
  int compareResult=data.compareTo(p.data);
  if (compareResult<0){//左边查找删除结点
      p.left=remove(data,p.left);
  }else if (compareResult>0) {
      p.right=remove(data,p.right);
  }else if (p.left!=null&&p.right!=null){//已找到结点并判断是否有两个子结点(情况3)
      //中继替换，找到右子树中最小的元素并替换需要删除的元素值
      p.data = findMin( p.right ).data; //这个函数在下面1.1.3
      //移除用于替换的结点
      p.right = remove( p.data, p.right );
  }else {
      //拥有一个孩子结点的结点和叶子结点的情况
      p=(p.left!=null)? p.left : p.right;
  }

  return p;//返回该结点
}
```

#### 1.1.3 二叉查找树的最大和最小值的查找算法与实现

```java
@Override
public T findMin() {
   if(isEmpty())
       throw new EmptyTreeException("BinarySearchTree is empty!");

   return findMin(root).data;
}

@Override
public T findMax() {
   if(isEmpty())
       throw new EmptyTreeException("BinarySearchTree is empty!");

   return findMax(root).data;
}

/**
* 查找最小值结点
* @param p
* @return
*/
private BinaryNode<T> findMin(BinaryNode<T> p){

   if (p==null)//结束条件
       return null;
   else if (p.left==null)//如果没有左结点,那么t就是最小的
       return p;
   return findMin(p.left);
}

/**
* 查找最大值结点
* @param p
* @return
*/
private BinaryNode<T> findMax(BinaryNode<T> p){
   if (p==null)//结束条件
       return null;
   else if (p.right==null)
       return p;
   return findMax(p.right);
}

```

#### 1.1.4二叉查找树的深度(height)和大小(size节点数)计算的设计与实现

```java
/**
 * 计算深度
 * @return
 */
@Override
public int height() {
    return height(root);
}

/**
 * 递归实现
 * @param subtree
 * @return
 */
private int height(BinaryNode<T> subtree){
    if (subtree==null){
        return 0;
    }else {
        int l=height(subtree.left);
        int r=height(subtree.right);
        return (l>r) ? (l+1):(r+1);//返回并加上当前层
    }
}


    /**
    * 计算大小
    * @return
    */
    @Override
    public int size() {
       return size(root);
    }

    /**
    * 递归实现：定义根节点root后，再用subtree实现递归
    * @param subtree
    * @return
    */
    private int size(BinaryNode<T> subtree){
       if (subtree == null)
           return 0;
       else
       {
           //对比汉诺塔:H(n)=H(n-1) + 1 + H(n-1)
           return size(subtree.left) + 1 + size(subtree.right);
       }
    }
```

### 1.2 二叉树遍历

#### 1.2.1 先根次序遍历

先根次序遍历算法，其访问规则是先遍历根结点，再遍历左子树，最后遍历右子树。如下图先根遍历的次序为ABEFC

![image-20210312103803900](C:\Users\hp\AppData\Roaming\Typora\typora-user-images\image-20210312103803900.png)

```java
@Override
public String begin() { //这儿是调用
    String sb=preOrder(root);
    if(sb.length()>0){
        //去掉尾部","号
        sb=sb.substring(0,sb.length()-1);
    }

    return sb;
}

/**
 * 先根遍历
 * Blog : http://blog.csdn.net/javazejian [原文地址,请尊重原创]
 * @param subtree
 * @return
 */
private String preOrder(BinaryNode<T> subtree){
    StringBuffer sb=new StringBuffer();
    if (subtree!=null) {//递归结束条件
        //先访问根结点
        sb.append(subtree.data+",");
        //遍历左子树
        sb.append(preOrder(subtree.left));
        //遍历右子树
        sb.append(preOrder(subtree.right));
    }
    return sb.toString();
}
```

```java
非递归的方式实现先序遍历
/**
 * 非递归的先根遍历
 * @return
 */
public String preOrderTraverse(){
    StringBuffer sb=new StringBuffer();
    //构建用于存放结点的栈
    LinkedStack<BinaryNode<T>> stack=new LinkedStack<>();

    BinaryNode<T> p=this.root;

    while (p!=null||!stack.isEmpty()){

        if (p!=null){
            //访问该结点
            sb.append(p.data+",");

            //将已访问过的结点入栈
            stack.push(p);

            //继续访问其左孩子，直到p为null
            p=p.left;

        }else { //若p=null 栈不为空,则说明已沿左子树访问完一条路径, 从栈中弹出栈顶结点,并访问其右孩子
            p=stack.pop();//获取已访问过的结点记录
            p=p.right;
        }

    }
    //去掉最后一个逗号
    if(sb.length()>0){
        return sb.toString().substring(0,sb.length()-1);
    }else {
        return sb.toString();
    }
}
```



#### 1.2.2 中根次序遍历

中根次序遍历的算法规则是，先遍历左子树，再遍历根结点，最后遍历右子树。过程如下图（同样利用的是递归思维）

![image-20210312105246736](C:\Users\hp\AppData\Roaming\Typora\typora-user-images\image-20210312105246736.png)

```java
@Override
public String inOrder() {
    String sb=inOrder(root);
    if(sb.length()>0){
        //去掉尾部","号
        sb=sb.substring(0,sb.length()-1);
    }
    return sb;
}

/**
 * 中根遍历
 * Blog : http://blog.csdn.net/javazejian [原文地址,请尊重原创]
 * @return
 */
public String inOrder(BinaryNode<T> subtree) {
    StringBuffer sb=new StringBuffer();
    if (subtree!=null) {//递归结束条件
        //先遍历左子树
        sb.append(inOrder(subtree.left));
        //再遍历根结点
        sb.append(subtree.data+",");
        //最后遍历右子树
        sb.append(inOrder(subtree.right));
    }
    return sb.toString();
}

/**
* 非递归的中根遍历
* Blog : http://blog.csdn.net/javazejian [原文地址,请尊重原创]
* @return
*/
public String inOrderTraverse(){
   StringBuffer sb=new StringBuffer();
   //构建用于存放结点的栈
   LinkedStack<BinaryNode<T>> stack=new LinkedStack<>();

   BinaryNode<T> p=this.root;

   while (p!=null||!stack.isEmpty()){
       while (p!=null){//把左孩子都入栈,至到左孩子为null
           stack.push(p);
           p=p.left;
       }
       //如果栈不为空,因为前面左孩子已全部入栈
       if(!stack.isEmpty()){
           p=stack.pop();
           //访问p结点
           sb.append(p.data+",");
           //访问p结点的右孩子
           p=p.right;
       }
   }

   if(sb.length()>0){
       return sb.toString().substring(0,sb.length()-1);
   }else {
       return sb.toString();
   }
}
```



#### 1.2.3 后根次序遍历

后根次序遍历的算法规则是，先访问左子树，再访问右子树，最后访问根结点，如下图(递归思维)：

```java
@Override
public String postOrder() {
   String sb=postOrder(root);
   if(sb.length()>0){
       //去掉尾部","号
       sb=sb.substring(0,sb.length()-1);
   }

   return sb;
}

/**
* 后根遍历
* Blog : http://blog.csdn.net/javazejian [原文地址,请尊重原创]
* @param subtree
* @return
*/
public String postOrder(BinaryNode<T> subtree) {
   StringBuffer sb=new StringBuffer();
   if (subtree!=null) {//递归结束条件
       //先遍历左子树
       sb.append(postOrder(subtree.left));

       //再遍历右子树
       sb.append(postOrder(subtree.right));

       //最后遍历根结点
       sb.append(subtree.data+",");
   }
   return sb.toString();
}


/**
* 非递归后根遍历
* @return
*/
public String postOrderTraverse(){
   StringBuffer sb=new StringBuffer();
   //构建用于存放结点的栈
   LinkedStack<BinaryNode<T>> stack=new LinkedStack<>();

   BinaryNode<T> currentNode =this.root;
   BinaryNode<T> prev=this.root;

   while (currentNode!=null||!stack.isEmpty()){
       //把左子树加入栈中,直到叶子结点为止
       while (currentNode!=null){
           stack.push(currentNode);
           currentNode=currentNode.left;
       }

       //开始访问当前结点父结点的右孩子
       if(!stack.isEmpty()){
           //获取右孩子，先不弹出
           BinaryNode<T> temp=stack.peek().right;
           //先判断是否有右孩子或者右孩子是否已被访问过
           if(temp==null||temp==prev){//没有右孩子||右孩子已被访问过
               //如果没有右孩子或者右孩子已被访问,则弹出父结点并访问
               currentNode=stack.pop();
               //访问
               sb.append(currentNode.data+",");
               //记录已访问过的结点
               prev=currentNode;
               //置空当前结点
               currentNode=null;
           }else {
               //有右孩子,则开始遍历右子树
               currentNode=temp;
           }
       }

   }

   //去掉最后一个逗号
   if(sb.length()>0){
       return sb.toString().substring(0,sb.length()-1);
   }else {
       return sb.toString();
   }
}
```



#### 1.2.4 层次遍历



![image-20210312105522959](C:\Users\hp\AppData\Roaming\Typora\typora-user-images\image-20210312105522959.png)

分析完前面的3种遍历算法，我们最后来分析一下层次遍历，二叉查找树的层次遍历特性就是兄弟优先访问，两个兄弟结点的访问顺序是先左后右的。同样它们的后代结点的访问次序也是先左后右，左兄弟的所有孩子结点一定优先右兄弟的孩子访问。对于二叉查找树的层次遍历算法，我们需要明确如何解决一下的存在的问题（假设p从根结点开始访问）：

p点如何到达其兄弟结点? B->C
p点如何到达它同层下一个结点(非兄弟结点)？D->E
p点如何在访问完当前层的最后一个结点时，进入下一层的第一个结点？C->D
————————————————
很显然，我们现在遇到的问题跟前面非递归算法遍历的问题有些类似，也就是二叉链表的本身根本无法满足以上任意一个问题，因为从B到C，从D到E，从C到D根本没有桥梁，此时肯定得借助第3方容器来满足需求，那么这个容器该如何选呢？该容器必须告诉我们下一个访问结点是谁？层次遍历的规则是兄弟优先，从左往右，因此，在访问时，必须先将当前正在访问的结点P的左右孩子依次放入容器，如P=C时，E、H必须已在栈中，而且先进入必须先访问，即先进E再进H，然后先访问E再访问H，显然该容器必须满足“先进先出”的原则，那也就是队列了，这里我们选择LinkedQueue队列，层次遍历算法描述如下：
p点从根结点开始访问，设一个空队列，当前p结点不为空时，重复以下操作：
① 访问p结点，将p结点的左右孩子依次入队。
② 使p指向一个出队结点，重复①的操作，直到队列为空。
其过程如下图所示：
————————————————
![image-20210312105736982](C:\Users\hp\AppData\Roaming\Typora\typora-user-images\image-20210312105736982.png)

```java
/**
* 层次遍历
* @return
*/
@Override
public String levelOrder() {
   /**
    * 存放需要遍历的结点,左结点一定优先右节点遍历
    */
   LinkedQueue<BinaryNode<T>> queue=new LinkedQueue<>();
   StringBuffer sb=new StringBuffer();
   BinaryNode<T> p=this.root;

   while (p!=null){
       //记录经过的结点
       sb.append(p.data);

       //先按层次遍历结点,左结点一定在右结点之前访问
       if(p.left!=null){
           //孩子结点入队
           queue.add(p.left);
       }

       if (p.right!=null){
           queue.add(p.right);
       }
       //访问下一个结点
       p=queue.poll();
   }
   return sb.toString();
}
```

==知道层次遍历后，我们可以利用层次遍历算法来构建一棵完全二叉树，但是不能构建普通的二叉树==

### 1.3 完全二叉树的构造与实现

明白了层次遍历算法后，我们可以利用层次遍历算法来构建一棵完全二叉树



那为什么层次遍历就可以确定完全二叉树呢? 这是因为完全二叉树的特性所决定的, 一棵具有n 个结点的完全二叉树, 对于序号为i $(0 \leq \mathrm{i}<\mathrm{n})$ 的结点, 则有如下规则
(1)若i=0, 则i为根结点, 无父母结点; 若i>0, 则的父母结点序号为 $\left\lfloor\frac{i-1}{2}\right\rfloor$ (向下取整)。
(2)若2i+1 $<n$,则的左孩子结点序号为2i+1，否则无左孩子。
(3)若2i+2 > $\mathrm{n}$,则的右孩子结点序号为2i+2, 否则i无右孩子。 因此很容易知道第0个结点就是完全二叉树，而左孩子结点序号为2i+1，否则没有左孩子, 右结点序号 为2i+2, 否则没有右孩子, 这样的编号恰好符合层次遍历的访问顺序，因此层次遍历确实可以确定一 棵完全二叉树，如下图：
![image-20210312110833067](C:\Users\hp\AppData\Roaming\Typora\typora-user-images\image-20210312110833067.png)

```java
package com.zejian.structures.Tree.BinaryTree;

import com.zejian.structures.Queue.LinkedQueue;

/**
* Created by zejian on 2016/12/17.
* Blog : http://blog.csdn.net/javazejian [原文地址,请尊重原创]
* 利用层次遍历原理构造完全二叉树
*/
public class CompleteBinaryTree<T extends Comparable> extends BinarySearchTree <T>  {


   /**
    * 构建空完全二叉树
    */
   public CompleteBinaryTree()
   {
       super();
   }

   /**
    * 以层序遍历构造完全二叉树
    * @param levelOrderArray
    */
   public CompleteBinaryTree(T[] levelOrderArray)
   {
       this.root = create(levelOrderArray, 0);
   }

   /**
    * 层次遍历构造完全二叉树
    * @param levelOrderArray
    * @param i
    * @return
    */
   public BinaryNode<T> create(T[] levelOrderArray ,int i){

       if(levelOrderArray ==null){
           throw new RuntimeException("the param 'array' of create method can\'t be null !");
       }
       BinaryNode<T> p = null;

       if (i<levelOrderArray.length){//递归结束条件
           p=new BinaryNode<>(levelOrderArray[i],null,null);
           p.left=create(levelOrderArray,2*i+1);  //根据完全二叉树的性质 2*i+1 为左孩子结点
           p.right=create(levelOrderArray,2*i+2); //2*i+2 为右孩子结点
       }

       return p;
   }

   /**
    * 搜索二叉树的包含操作和移除操作不适合层次遍历构造的完全二叉树
    * 根据层次遍历构建的二叉树必须用层次遍历来判断(仅适用层次遍历构建的完全二叉树)
    * @param data
    * @return
    */
   @Override
   public boolean contains(T data) {
       /**
        * 存放需要遍历的结点,左结点一定优先右节点遍历
        */
       LinkedQueue<BinaryNode<T>> queue=new LinkedQueue<>();
       StringBuffer sb=new StringBuffer();
       BinaryNode<T> p=this.root;

       while (p!=null){

           //判断是否存在data
           if(data.compareTo(p.data)==0){
               return true;
           }

           //先按层次遍历结点,左结点一定在右结点之前访问
           if(p.left!=null){
               //孩子结点入队
               queue.add(p.left);
           }

           if (p.right!=null){
               queue.add(p.right);
           }
           //访问下一个结点
           p=queue.poll();
       }

       return false;
   }


   /**
    * 搜索二叉树的包含操作和移除操作不适合层次遍历构造的完全二叉树
    * @param data
    * @return
    */
   @Override
   public void remove(T data) {
       //do nothing 取消删除操作
   }

   /**
    * 完全二叉树只通过层次遍历来构建,取消insert操作
    * @param data
    */
   @Override
   public void insert(T data) {
       //do nothing //取消insert操作
   }

   /**
    * 测试
    * @param args
    */
   public static void main(String args[])
   {

       String[] levelorderArray = {"A","B","C","D","E","F"};
       CompleteBinaryTree<String> cbtree = new CompleteBinaryTree<>(levelorderArray);
       System.out.println("先根遍历:"+cbtree.preOrder());
       System.out.println("非递归先根遍历:"+cbtree.preOrderTraverse());
       System.out.println("中根遍历:"+cbtree.inOrder());
       System.out.println("非递归中根遍历:"+cbtree.inOrderTraverse());
       System.out.println("后根遍历:"+cbtree.postOrder());
       System.out.println("非递归后根遍历:"+cbtree.postOrderTraverse());
       System.out.println("查找最大结点(根据搜索二叉树):"+cbtree.findMax());
       System.out.println("查找最小结点(根据搜索二叉树):"+cbtree.findMin());
       System.out.println("判断二叉树中是否存在E:"+cbtree.contains("E"));

   }
}

```

### 1.4 二叉树的构造与实现

了解了完全二叉树的构造，现在我们回过头来看看二叉树又该如何构造呢？显然从完全二叉树的分析中发现，无论是先根遍历或者是中根遍历还是后根遍历，都无法唯一确定一棵树，都将面临之前的问题，遍历顺序为AB的树都可能有两种情况。

==因此已知二叉树的一种遍历顺序，不能确定唯一一棵二叉树 ===。这是因为后根和先根次序反映的都是父母与孩子结点间的关系而没有反映兄弟间的关系，而中根次序反映的则是兄弟结点间的关系。既然这样，我们能不能考虑结合两种遍历顺序来构造一个二叉树呢？答案是肯定的，

确实可以通过先根遍历和中根遍历次序或者后根和中根遍历次序唯一确定一棵二叉树，

==而先根和后根遍历反应的都是父母与孩子结点的关系，自然也就无法确定一棵唯一二叉树了==，如给出先根顺序AB和后根顺序BA，可以确定A是根结点，但B呢，是左孩子还是右孩子呢？无法确定，下面我们案例来分析上面两种情况。

#### 1.4.1 先根与中根次序构建二叉树及其代码实现

![img](https://img-blog.csdn.net/20161221234108454?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvamF2YXplamlhbg==/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)

```java
/**
 *Blog : http://blog.csdn.net/javazejian [原文地址,请尊重原创]
 * 根据先根和中根遍历算法构造二叉树
 * @param preList 先根遍历次序数组
 * @param inList 中根遍历次序数组
 * @param preStart
 * @param preEnd
 * @param inStart
 * @param inEnd
 * return root 最终返回的根结点
 */
public  BinaryNode<T>  createBinarySearchTreeByPreIn(T[] preList , T[] inList,int preStart ,int preEnd ,int inStart ,int inEnd){
    //preList[preStart]必须根结点数据,创建根结点root
    BinaryNode<T> p=new BinaryNode<>(preList[preStart]);
    //如果没有其他元素,就说明结点已构建完成
    if (preStart == preEnd && inStart == inEnd) {
        return p;
    }
    //找出中根次序的根结点下标root
    int root=0;

    for (root = inStart; root < inEnd; root++) {
            //如果中根次序中的元素值与先根次序的根结点相当,则该下标index即为inList中的根结点下标
            if (preList[preStart].compareTo(inList[root])==0){
                break;
            }
    }

    //获取左子树的长度
    int leftLength=root-inStart;
    //获取右子树的长度
    int rightLength=inEnd-root;

    //递归构建左子树
    if(leftLength>0){
        //左子树的先根序列：preList[1] , ... , preList[i]
        //左子树的中根序列：inList[0] , ... , inList[i-1]
        p.left=createBinarySearchTreeByPreIn(preList,inList,preStart+1,preStart+leftLength,inStart,root-1);
    }

    //构建右子树
    if (rightLength>0){
        //右子树的先根序列：preList[i+1] , ... , preList[n-1]
        //右子树的中根序列：inList[i+1] , ... , inList[n-1]
        p.right=createBinarySearchTreeByPreIn(preList,inList,preStart+leftLength+1,preEnd,root+1,inEnd);
    }
    return p;
}
```



#### 1.4.2 后根与中根次序构建二叉树及其代码实现

![img](https://img-blog.csdn.net/20161222070916337?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvamF2YXplamlhbg==/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)

```java
/**
 *Blog : http://blog.csdn.net/javazejian [原文地址,请尊重原创]
 * 后根/中根遍历构建二叉树
 * @param postList 后根遍历序列
 * @param inList 中根遍历序列
 * @param postStart
 * @param postEnd
 * @param inStart
 * @param inEnd
 * @return 根结点
 */
public BinaryNode<T> createBinarySearchTreeByPostIn(T[] postList,T[] inList,int postStart,int postEnd,int inStart,int inEnd){

    //构建根结点
    BinaryNode<T> p=new BinaryNode<>(postList[postEnd]);

    if(postStart==postEnd && inStart==inEnd){
        return p;
    }

    //查找中根序列的根结点下标root
    int root=0;

    for (root=inStart;root<inEnd;root++){
        //查找到
        if (postList[postEnd].compareTo(inList[root])==0){
            break;
        }
    }

    //左子树的长度
    int leftLenght=root-inStart;
    //右子树的长度
    int rightLenght=inEnd-root;

    //递归构建左子树
    if(leftLenght>0){
        //postStart+leftLenght-1:后根左子树的结束下标
        p.left=createBinarySearchTreeByPostIn(postList,inList,postStart,postStart+leftLenght-1,inStart,root-1);
    }

    //递归构建右子树
    if(rightLenght>0){
        p.right=createBinarySearchTreeByPostIn(postList,inList,postStart+leftLenght,postEnd-1,root+1,inEnd);
    }

    return p;
}
```

