package com.Thread.threadSaveLearning;
/*创建三个窗口买票 总票数100张
*依旧有线程安全问题 （重复的票号）
*
* 使用Runnable方式实现多线程*/
public class WinTest1 {
    public static void main(String[] args) {
        Win w = new Win();//这就不用再new3个对象了 （对象是Runable对象）
        Thread t1 = new Thread(w,"线程1");
        Thread t2 = new Thread(w,"线程2");
        Thread t3 = new Thread(w,"线程3");//但是还是要3个线程
        t1.start();
        t2.start();
        t3.start();
    }
}
class Win implements Runnable{
    private  int  ticket=100;
    @Override
    public void run() {
        while (true){
            if(ticket>0){
                System.out.println(Thread.currentThread().getName()+":卖票，票号"+ticket);
                ticket--;
            }
            else break;
        }
    }
}
/*问题：为什么ticket不加static，3个线程为啥也共用了100张票
* 因为只有一个对象（Runnable）所以只是存储了一个ticket
* */

/*比较创建线程的两种方式：（实现Runnable和继承Thread）
* 实现Runnable要好一些，1.因为让类继承Thread的话，这个类就不能再继承了，万一这个类有父类就不行了
*                    2.让类继承Thread的话，多个线程就要多个这样的对象
*
* */