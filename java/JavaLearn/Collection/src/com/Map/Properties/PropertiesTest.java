package com.Map.Properties;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

/**
 * @Author OZR
 * @Date 2020/9/19 21:11
 *
 *
 *
 * Properties 类是 Hashtable 的子类，该对象用于处理属性文件
 * 由于属性文件里的 key 、 value 都是字符串类型，所以 Properties 里的 key和 value 都是字符串类型
 */
public class PropertiesTest {
    //使用jdbc.properties这个配置文件的数据
    public static void main(String[] args) throws IOException {
        Properties pros = new Properties();
        FileInputStream fis = new FileInputStream("jdbc.properties");
        pros.load(fis);

        String name = pros.getProperty("name");
        String password = pros.getProperty("password");
        System.out.println(name+"------"+password);


    }
}
