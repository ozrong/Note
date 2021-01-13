package com.Thread.threadLearning;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/*创建线程的方式四：线程池
*
*
* */
public class ThreadPool {
    public static void main(String[] args) {

        //1.声明指定数量的线程池
        ExecutorService service = Executors.newFixedThreadPool(10);

        //执行指定的线程操作，参数为Runnable接口实现类的对象
        service.execute(new Number1Thread()); //适用于RUnnable
        service.execute(new Number2Thread());
      //  service.submit()   //适用于Callable
        // service.submit(Callable的对象)
        service.shutdown();//释放线程（关闭线程，但是没有死亡）
    }
}


class Number1Thread implements Runnable{
    @Override
    public void run() {
        for (int i = 1; i <=100 ; i++) {
            if(i%2==0){
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName()+":"+i);
            }
            
        }
    }
}
class Number2Thread implements Runnable{
    @Override
    public void run() {
        for (int i = 1; i <=100 ; i++) {
            if(i%2!=0){
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName()+":"+i);
            }

        }
    }
}
