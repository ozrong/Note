package com.Thread.threadSaveLearning;
/*创建三个窗口买票 总票数100张
*
* 这个会出现线程安全问题（不同步）*/

public class WinTest {
    public static void main(String[] args) {
        Window w1=new Window();
        Window w2=new Window();
        Window w3=new Window();
        w1.setName("窗口1");
        w2.setName("窗口2");
        w3.setName("窗口3");
        w1.start();
        w2.start();
        w3.start();
    }
}
class Window extends Thread{
    private static int ticket=100;

/*加static 用为所有对象都会共用这个static修饰的变量*/

    @Override
    public void run() {
      while (true){
          if(ticket>0){
              System.out.println(getName()+":买票，票号为："+ticket);
              ticket--;
          }
          else break;
      }
    }
}


