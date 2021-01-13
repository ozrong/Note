package com.ozr.java1;

import org.junit.Test;

/**
 * @Author OZR
 * @Date 2021/1/8 20:32
 */
public class TsetDAO {
    @Test
    public void test(){    //假设在这个类里面要对数据表进行操作
        CustomerDAO dao1 = new CustomerDAO();
        dao1.add(new Customer());

    }
}
