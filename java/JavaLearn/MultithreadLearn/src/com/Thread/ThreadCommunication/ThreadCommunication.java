package com.Thread.ThreadCommunication;
/*1.wait() 调用wait()的线程将要阻塞 并且会释放锁，让别的线程进来
* 2.notify();唤醒一个被wait（）的线程
* 3.notifyAll();唤醒一个所有被wait()的线程
*
*
* 说明：
* 1. 以上3个方法都必须在同步代码块或者同步方法中
* 2. 以上3个方法的调用必须是同一个同步监视器
* 3. 以上上个方法的创建在java.lang.Object中
* */

/*线程通信的应用：金典生产者/消费者的问题*/


/*面试题 sleep()和wait()异同
* 1 相同点：一旦调用就会让当前线程进入阻塞状态
* 2 不同点：1)声明的位置不同，sleep()在Thread类中声明的，而wait()实在Object类中
*         2)sleep()可以在任意需要的位置调用，而wait必须在同步代码块或者同步方法中
*         3)sleep不会释放同步监视器，wait会释放同步监视器
*
*
*
* */
public class ThreadCommunication {
    public static void main(String[] args) {
        Number number = new Number();
        Thread t1 = new Thread(number,"线程1");
        Thread t2 = new Thread(number,"线程2");
        t1.start();
        t2.start();

    }
}
class Number implements Runnable{
    private  int number=1;
    @Override
    public void run() {
        while (true){
            synchronized (this) {
                notify();
                if(number<=100) {
                    try {
                        Thread.sleep(50);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(Thread.currentThread().getName()+":"+number);
                    number++;

                    try {
                        wait(); //调用wait()的线程将要阻塞 并且会释放锁，让别的线程进来
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                else break;
            }
        }

    }
}
