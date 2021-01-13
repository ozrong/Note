package com.Thread.threadLearning;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.ThreadLocalRandom;

/*jdk5.0后新增加的线程创建方式
* 1.实现Callable接口
*    这个方式比Runnable更加的强大：（与Runnable的区别）
*           1）相比于run这个可以有返回值
*           2）可以抛出异常
*           3）支持泛型的返回值
*           4）需要借助FutureTask类，比如获取结果
*
*1.创建一个Callable的实现类
* 2.重写call方法 将这个线程的操作写在call中
* 3.创建Callable实现类的对象
* 4.将Callable对象传入FutureTask的构造器中
* 5.将FutureTask的对象放入Thread的构造器中，创建线程并启动
* 6.FutureTask的对象可以获得线程的返回值
* */


//1.创建一个Callable的实现类
class Number implements Callable{
   //2.重写call方法 将这个线程的操作写在call中
    @Override
    public Object call() throws Exception {
        int sum=0;
        for (int i = 1; i <=100 ; i++) {
            if(i%2==0){
                System.out.println(i);
                sum += i;
            }

        }
        return sum;
    }
}

public class ThreadCallable {
    public static void main(String[] args) {
        //3 创建Callable实现类的对象
        Number number = new Number();
        //4.将Callable对象传入FutureTask的构造器中
        FutureTask futureTask = new FutureTask(number);

        //5.将FutureTask的对象放入Thread的构造器中，创建线程并启动
        new Thread(futureTask).start();
        try {
            //6.FutureTask的对象可以获得线程的返回值
            Object sum=futureTask.get();
            //get()返回值就是FutureTask构造器的参数callable对象重写的call的返回值
            System.out.println("100以内偶数之和"+sum);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}
