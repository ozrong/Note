package com.Thread.threadLearning;
/*创建线程方法二：实现Runnable接口
* 1. 创建一个实现Runnable接口的类
* 2. 实现Runnable中的抽象方法run()
* 3. 创建实现类的对象
* 4. 将这个对象作为参数传入Thread类的构造器中，创建Thread的对象
* 5. 通过Thread的对象调用Start()*/



//1. 创建一个实现Runnable接口的类
class MyThead1 implements Runnable{

    //实现Runnable中的抽象方法run()
    @Override
    public void run() {
        for (int i=0;i<100;i++){
            if(i%2==0){
                System.out.println(i);
            }
        }
    }
}
public class CreateThreadMethod2 {
    public static void main(String[] args) {
        //3. 创建实现类的对象
        MyThead1 thead1 = new MyThead1();
        // 4. 将这个对象作为参数传入Thread类的构造器中，创建Thread的对象
        Thread t1 = new Thread((thead1),"线程一");
        //通过Thread的对象调用Start()
        t1.start();//谁start()这个线程就是谁的
    }
}
