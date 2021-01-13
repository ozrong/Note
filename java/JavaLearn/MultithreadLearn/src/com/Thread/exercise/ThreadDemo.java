package com.Thread.exercise;



public class ThreadDemo {
    public static void main(String[] args) {
        MyThread1 t1 = new MyThread1();
        MyThread2 t2 = new MyThread2();
        t1.start();
        t2.start();


    }
}
class MyThread1 extends Thread{
    //2.重写Thread类的run方法

    @Override
    public void run() {
        for (int i=0;i<100;i++){
            if(i%2==0){
                System.out.println("Thread-1:"+i);
            }
        }
    }
}
class MyThread2 extends Thread{
    //2.重写Thread类的run方法

    @Override
    public void run() {
        for (int i=0;i<100;i++){
            if(i%2!=0){
                System.out.println("Thread-2:"+i);
            }
        }
    }
}
//可以使用匿名类
/* new Thread(){
            @Override
            public void run() {
                //操作
            }
        }.start();
*
*
* */
