package com.ozr.exer1;

import java.util.HashMap;
import java.util.List;

/**
 * @Author OZR
 * @Date 2021/1/11 20:13
 *
 *
 *  定义一个 测试类：
 * 创建 DAO 类的对象， 分别调用其 save、 get、 update、 list、 delete 方
 * 法来操作 User 对象，
 * 使用 Junit 单元测试类进行测试
 */
public class DAOTest {
    public  static void main(String[] args){
        DAO<User> dao = new DAO<>();

        //实例化map
        dao.setMap(new HashMap<String,User>());
        dao.save("1001",new User(1001,34,"周杰伦"));
        dao.save("1002",new User(1002,44,"赵凌云"));
        dao.save("1003",new User(1003,34,"两层"));

        List<User> list =  dao.list();
//        System.out.println(list);


        //java 8 新特性
        list.forEach(System.out::println);//????




    }

}
