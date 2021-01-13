package com.Collection.collection1;

import java.util.Objects;

/**
 * @Author OZR
 * @Date 2020/9/13 16:41
 */
public class Penson {
    private  String name;
    private  int age;

    public Penson() {
    }

    public Penson(String name, int age) {
        this.name = name;
        this.age = age;
    }

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
        return "Penson{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {return true;};
        if (o == null || getClass() != o.getClass()) {return false;}
        Penson penson = (Penson) o;
        return age == penson.age &&
                Objects.equals(name, penson.name);
    }

//    @Override
//    public int hashCode() {
//        return Objects.hash(name, age);
//    }
}
