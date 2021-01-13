package com.Thread.threadSaveLearning;
/*同步代码块解决继承Thread这种方式创建多线程的安全问题
*
*
*
*
* */
public class ThreadSave1 {

    public static void main(String[] args) {
        Window2 w1=new Window2();
        Window2 w2=new Window2();
        Window2 w3=new Window2();
        w1.setName("窗口1");
        w2.setName("窗口2");
        w3.setName("窗口3");
        w1.start();
        w2.start();
        w3.start();
    }
}
class Window2 extends Thread{
    private static int ticket=100;
    private static Object ob = new Object();  //static 让所有的线程对象使用的是一把锁
    /*加static 用为所有对象都会共用这个static修饰的变量*/

    @Override
    public void run() {
        while (true) {

            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (ob) {
                if (ticket > 0) {
                    System.out.println(getName() + ":买票，票号为：" + ticket);
                    ticket--;
                } else break;
            }
        }
    }
}