package com.ozr.java4;

/**
 * @Author OZR
 * @Date 2021/1/22 17:16
 */

/*
以下的Person类是不支持序列化的
 */
//public class Person {
//
//    private String name;
//    private int age;
//
//    public Person() {
//    }
//
//    public Person(String name, int age) {
//        this.name = name;
//        this.age = age;
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    public int getAge() {
//        return age;
//    }
//
//    public void setAge(int age) {
//        this.age = age;
//    }
//
//    @Override
//    public String toString() {
//        return "Person{" +
//                "name='" + name + '\'' +
//                ", age=" + age +
//                '}';
//    }
//}


import java.io.Serializable;

/**
 *
 *这是支持序列化的
 *
 *
 * Person需要满足以下要求才可以支持序列化
 * 1.需要实现Serializabel
 * 2.在当前类定一个全局常量：serialVersionUID
 * 3.除了当前Person类需要实现serializabel接口之外，还必须要保证其内部所有属性也必须是可序列化的（也就是说里面涉及到的其他自定义类的对象的时候）
 *   默认情况下基本数据类型是可序列化的
 *
 *
 * serialVersionUID 用来表明类的不同版本间的兼容性。 简言之，其目的是以序列化对象
 * 进行版本控制，有关各版本反序列化时是否兼容。
 * 如果 类没有显示定义 这个静态 常 量 ，它的值是 Java 运行时环境根据类的内部细节自
 * 动生成的 。 若类的实例变量做了修改 serialVersionUID 可能发生变化。 故 建议显式声明。
 *
 *
 * 注意：ObjectOutputStream 和 ObjectInputStream 不能序列化static和transient修饰的成员变量
 */
public class Person implements Serializable {
    public static final long serialVersionUID = 1531231345L; //序列版本号  在自定义Exception（自定义异常类）

    private String name;
    private int age;

    public Person() {
    }

    public Person(String name, int age) {
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
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
