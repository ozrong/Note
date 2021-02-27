package com.ozr.java1;

import java.security.spec.DSAPrivateKeySpec;

/**
 * @Author OZR
 * @Date 2021/2/11 16:41
 */
public class Person {
    private String name;
    public int age;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public Person() {
    }

    private Person(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
    public void show(){
        System.out.println("你好， 我是好人");
    }
    private String showNation(String nation){
        System.out.println("我的国籍："+nation);
        return nation;

    }
}
