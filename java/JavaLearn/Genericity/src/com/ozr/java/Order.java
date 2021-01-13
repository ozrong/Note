package com.ozr.java;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @Author OZR
 * @Date 2021/1/4 20:25
 *
 *
 * 自定义一个泛型类
 */
public class Order<E> { //E 其实是一个参数

//类的内部结构就可以使用类的泛型

    String orderName;
    int orderId;
    E orderT;
//    public Order(){};
    public Order(String orderName,int orderId,E orderT){
        this.orderId = orderId;
        this.orderName = orderName;
        this.orderT = orderT;
    }
    public Order(){
//        E[] arr = new E[10] //不可以的
        E[] arr =(E[]) new Objects[10];
    }

    public String getOrderName() {
        return orderName;
    }

    public void setOrderName(String orderName) {
        this.orderName = orderName;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }


    // getorderT(),setOrderT,这两个方法都不是泛型方法。
    public E getorderT(){
        return orderT;
    }

    public void setOrderT(E orderT) {
        this.orderT = orderT;
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderName='" + orderName + '\'' +
                ", orderId=" + orderId +
                ", orderT=" + orderT +
                '}';
    }


    //什么叫泛型方法，在方法中出现了泛型结构 泛型参数与类的泛型参数没有任何的关系
    //other words     泛型方法所属的类是不是泛型类都没有关系
    //eg
    public <k> List<k> copyFromArrayToList(k[] arr){  //这个才是泛型方法
        ArrayList<k>  list= new ArrayList<>();
        for(k e :arr){
            list.add(e);
        }
        return  list;

    }

//    public  static void show(E orderT){ //报错了，（这不是泛型方法）
//                                        //静态方法中不能使用类的泛型  注意泛型方法是可以使用静态，
//                                       //静态结构是在类的实例化的之前创建的，所以在静态方法创建的时候，T泛型结构还没有指定
//        System.out.println(orderT);
//
//    }

    //静态的泛型方法
    //这个情况是泛型参数是在调用这个方法的时候确定的，而不是在实例化类的时候指定的
    public  static <T> List<T> hahah(T[] arr){ //这是可以的
        return null;
    }


//    public  void show(){
//        try {
//
//        }catch (E,e){//错误，异常类不能使用泛型
//
//        }
//    }



}
