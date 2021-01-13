package com.Thread.threadSaveLearning;
//使用同步方法的方式实现Runnable接口的方式创建的多线程的安全问题
public class ThreadSave2 {
    public static void main(String[] args) {
        Win2 w = new Win2();//这就不用再new3个对象了 （对象是Runable对象）
        Thread t1 = new Thread(w,"线程1");
        Thread t2 = new Thread(w,"线程2");
        Thread t3 = new Thread(w,"线程3");//但是还是要3个线程
        t1.start();
        t2.start();
        t3.start();
    }
}
class Win2 implements Runnable{
    private  int  ticket=100;
    @Override
    public void run() {
        while (true){
            show();
            if(ticket==0) break;
        }
    }
    private synchronized void show(){//此时的同步监视器就是 this当前对象
        if(ticket>0){
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName()+":卖票，票号"+ticket);
            ticket--;
        }

    }
}