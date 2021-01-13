package com.Thread.threadSaveLearning;

import java.util.concurrent.locks.ReentrantLock;

/* 解决线程安全问题方式三： Lock锁，------jdk5.0后新增
*
* 1.实例化ReentrantLock
* */
public class ThreadSaveLock {

    public static void main(String[] args) {
        Win4 w = new Win4();//这就不用再new3个对象了 （对象是Runable对象）
        Thread t1 = new Thread(w,"线程1");
        Thread t2 = new Thread(w,"线程2");
        Thread t3 = new Thread(w,"线程3");//但是还是要3个线程
        t1.start();
        t2.start();
        t3.start();
    }
}
class Win4 implements Runnable{
    private  int  ticket=100;
    //1.实例化ReentrantLock
    private ReentrantLock lock = new ReentrantLock();
    @Override
    public void run() {
        while (true){
            try {
                //2.调用lock()方法
                lock.lock();//加锁

                if(ticket>0){
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(Thread.currentThread().getName()+":卖票，票号"+ticket);
                    ticket--;
                }
                else break;
            } finally {
                lock.unlock();//最后必须解锁
            }
        }
    }
}