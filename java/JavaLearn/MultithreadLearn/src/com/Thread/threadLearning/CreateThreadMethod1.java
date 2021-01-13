package com.Thread.threadLearning;


/*多线程的创建
 * 方法一：
 * 1.创建一个类继承Thread
 * 2.重写Thread类的run方法,写想想在线程中执行的操作写在run中
 * 3.创建Thread的子类对象
 * 4.通过这个对象调用start()
 * */


//1.创建一个类继承Thread
class MyThread extends Thread{
    //2.重写Thread类的run方法

    @Override
    public void run() {
        for (int i=0;i<100;i++){
            if(i%2==0){
                System.out.println(i);
            }
        }
    }
}
public class CreateThreadMethod1 {
    public static void main(String[] args) {

        //3.创建Thread的子类对象
        MyThread t1 = new MyThread();
        //4.通过这个对象调用start()
        t1.start();//启动线程然后调用run方法；
        //问题1： 如果直接调run方法只是调用的方法，没有创建线程
        //在启动一个线程eg:再次使用t1.start()(这是不行的，也就是一个线程对象只能使用一次start())
        for(int i=0;i<20;i++){
            System.out.println("++++++++++++++++++++");
        }
    }


}
