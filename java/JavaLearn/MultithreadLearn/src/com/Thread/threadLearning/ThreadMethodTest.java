package com.Thread.threadLearning;


/*试试Thread常用的方法
 * start()
 * run()
 * currentThread()获取执行当前代码的线程（静态方法）
 * getName() 获取当前线程名称
 * setName() 设置当前线程的名称
 * yield() 释放cpu的占用
 * jion()
 * stop()强制结束线程 过时了不用了
 * sleep(时间) 让线程睡眠，单位时间毫秒
 * isAlive()看下线程是否存活*/

/*线程的优先级
 * MIN_PRIORITY = 1
 * NORM_PRIORITY = 5 默认的是这个优先级
 * MAX_PRIORITY = 10
 * 设置当前线程的优先级
 * getPriority()获取当前线程的优先级
 * setPriority()设置当前线程的优先级*/


class HelloThread extends Thread{
    @Override
    public void run() {
        for (int i=0;i<100;i++){
            if(i%2==0){
                System.out.println(Thread.currentThread().getName()+":"+Thread.currentThread().getPriority()+":"+i);
                /*这个可以省略Thread.currentThread(),因为就在这个Thread类中*/

            }
//           if(i==20) yield();当i=20就让出这个线程的cpu的执行权；但是它还是有机会在次抢到的
        }
    }
}

public class ThreadMethodTest {
    public static void main(String[] args) {
        HelloThread t1 = new HelloThread();
        t1.setName("线程1");
        t1.setPriority(10);//范围（1-10）
        t1.start();
        for (int i=0;i<100;i++){
            if(i%2==0){
                System.out.println(Thread.currentThread().getName()+":"+i);
            }
            if(i==20) {
                try {
                    t1.join();//当i==10时，t1这个线程加入进来，并且让当前线程停下，让t1执行完，再让当前线性继续执行
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }
    }
}


/*class NameThread extends Thread{
    NameThread(String name){
        super(name);
        这样也可以给线程命名
    }
    在main中：  new NameThread（name）就ok
}*/
