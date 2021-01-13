package com.objectAndsubobject.java;



/*   OOP（面向对象）
*   java类集类的成员：属性，方法，构造器，代码块，内部块
*   面对对象的三大特征：封装  继承 多态 （抽象性）
*   其他关键字：this super static final abstract
*             interface package import....
*/


public class OOPTest {
    Sup te;

    OOPTest(){
        this.te = new Sup() ;
    }

    class In_class{    /*这才是内部类*/

    }
}
class KindClass{               /*这可不是内部类啊*/
 int a =2020;
 void muliptul(){
     int year;
     year = a;
 }
}

class Sup{
    int a =0;
    static int n=0;
    int ozr=10;
    void ass(){
        int a=20;
        a = this.add(a);
        System.out.println(a);
    }
    int add(int a){
        return a+10;
    }

}


