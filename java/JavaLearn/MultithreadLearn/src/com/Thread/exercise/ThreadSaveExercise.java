package com.Thread.exercise;
import java.util.concurrent.locks.ReentrantLock;
/*
* 银行有一个账户，有1个用户同时向这个账户存3000元，存3次，每次存完打印余额
*
* 分析：是否存在线程安全问题
* 1.是否多线程  （两用户  同时存）
* 2.是否共享数据 （一个账户）
*存在线程安全问题
* */


import java.util.concurrent.locks.ReentrantLock;

class Account{
    private double banlance;

    public Account(double banlance) {
        this.banlance = banlance;
    }
    public void save(double money){ //方法一 使用同步方法 synchronized
        if(money>0){
            banlance+=money;
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName()+"本次存钱："+money+"元；当前余额为："+banlance);
        }
    }
}
class Customer extends Thread {
    private Account acct;
    private static ReentrantLock lock = new ReentrantLock();

    public Customer(Account acct) {
        this.acct = acct;
    }

    @Override
    public void run() {
        for (int i = 0; i < 3; i++) {
            acct.save(1000);

        }
    }


/* 方法二
   @Override
    public void run() {
        for(int i=0;i<3;i++){

            try {
                lock.lock();
                acct.save(1000);
            } finally {
                lock.unlock();
            }

        }
    }*/

/*    //方法三
    private static Object ob=new Object();
    @Override
    public void run() {
        for(int i=0;i<3;i++){

            synchronized(ob) {
                acct.save(1000);
            }

        }
    }
}*/
}


public class ThreadSaveExercise {
    public static void main(String[] args) {
        Account acct = new Account(0);
        Customer c1 = new Customer(acct);
        Customer c2 = new Customer(acct);
        c1.setName("甲");
        c2.setName("乙");
        c1.start();
        c2.start();

    }
}

