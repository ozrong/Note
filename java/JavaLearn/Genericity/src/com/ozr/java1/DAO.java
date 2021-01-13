package com.ozr.java1;

import java.util.List;

/**
 * @Author OZR
 * @Date 2021/1/8 20:20
 */

//data(base) access object  数据访问对象
public class DAO<T> {   //很多表 ，一个表对应一个类，所以使用泛型来控制你操作那个表
    //添加一条数据
    public  void add(T t){

    }

    //删除一条数据
    public Boolean delect(){
        return null;
    }

    //修改一条数据
   public void updata(){}
    //查询一条数据
   public T getIndex(){
        return null;

   }
    //查询多条数据
    public List<T> getForList(int index){
        return  null;

    }

    //泛型方法
    public <E> E getValue(){
        return null;
    }

    //假如这个类提供了通用的一些功能
    //使用一个子类来继承它  写一些具体的功能
}
