package com.Thread.threadSaveLearning;
/*创建三个窗口买票 总票数100张
 *依旧有线程安全问题 （重复的票号,错的票号）
 * 出现原因：当前线程操作没有完，另一个线程进入了来操作这个相同的操作
 * 解决：当一个线程操作这个数据的时候，不让其他的线程加入进来，即使当前线程被阻塞都不能进来起来线程。
 *
 *
 * java解决方法：同步
 * 法一：同步代码块
 * synchronized(同步监视器){
 * //需要同步的代码
 * }
 * 说明：1.操作共享数据的代码就是需要被同步的代码
 *      2.共享数据：就是被多个线程操作的变量
 *      3.同步监视器：就是锁。任何一个类的对象都可以当锁
 *         要求：多个线程必须共用一个锁
 * 法二：同步方法
 * 如果操作共享数据的操作都在一个方法中，就可以将这个方法声明为同步方法
 * 见ThreadSave2
 *
 *
 *
 * 同步解决了线程安全，但是在同步中只是一个线程参与，速度慢了
 * 使用Runnable方式实现多线程*/
public class ThreadSave {

    public static void main(String[] args) {
        Win1 w = new Win1();//这就不用再new3个对象了 （对象是Runable对象）
        Thread t1 = new Thread(w,"线程1");
        Thread t2 = new Thread(w,"线程2");
        Thread t3 = new Thread(w,"线程3");//但是还是要3个线程
        t1.start();
        t2.start();
        t3.start();
    }
}

class Win1 implements Runnable{
    private  int  ticket=100;
    Object ob = new Object();
    @Override
    public void run() {
        while (true) {
            synchronized (ob) {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if (ticket > 0) {
                    System.out.println(Thread.currentThread().getName() + ":卖票，票号" + ticket);
                    ticket--;
                } else break;
            }
        }
    }
}
