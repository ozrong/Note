package com.Collection.set;

import java.util.Objects;

/**
 * @Author OZR
 * @Date 2020/9/17 15:17
 */
public class User implements Comparable {
    private  String name ;
    private  int age ;
    public User( String name,int age) {
        this.name =name;
        this.age = age;
    }
    public User( ) {}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {return true;}
        if (o == null || getClass() != o.getClass()) {return false;}
        User user = (User) o;
        return age == user.age &&
                Objects.equals(name, user.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, age);
    }
//指定 按照姓名的从小到大排列
    @Override
    public int compareTo(Object o) {
        if(o instanceof  User){
            User user = (User)o;
            return this.name.compareTo(user.name);
        } else{
            throw new RuntimeException("类型不匹配");
        }
    }
}
