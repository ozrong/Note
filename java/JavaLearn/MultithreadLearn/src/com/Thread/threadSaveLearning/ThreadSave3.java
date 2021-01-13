package com.Thread.threadSaveLearning;

public class ThreadSave3 {
    public static void main(String[] args) {
        Window3 w1=new Window3();
        Window3 w2=new Window3();
        Window3 w3=new Window3();
        w1.setName("窗口1");
        w2.setName("窗口2");
        w3.setName("窗口3");
        w1.start();
        w2.start();
        w3.start();
    }
}
class Window3 extends Thread {
    private static int ticket = 100;

    /*加static 用为所有对象都会共用这个static修饰的变量*/

    @Override
    public void run() {
        while (true) {
            show();
            if(ticket==0) break;

        }
    }
    private static synchronized void show(){
        /*private synchronized void show()  这种方式是不可以的，w1，w2，w3这3个线程都会调用自己的这个方法，static后所有对象都共用这个方法了*/
        if(ticket>0){

            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName()+":买票，票号为："+ticket);
            ticket--;
        }
    }
}
