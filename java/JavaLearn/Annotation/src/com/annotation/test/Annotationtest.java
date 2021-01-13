package com.annotation.test;
/*
* 注解的使用：Annotation其实就是代码里面的一些特殊的标记，这些标记可以在编译，类加载，运行的时候被读取
*          并执行相应的处理。可以使用注解在不改变原有逻辑的情况下，在源文件中嵌入一下补充信息
* Annotation示例：
* 1.生成文档相关的注解
* 2.在编译的时候进行格式检查（jdk内置的注解）
*  @override
*  @Deprecated 用于表示修饰的元素已经过时，
*  @suppressWarnings:抑制编译器警告
* 3.跟踪代码的依赖性，实现替代配置文件的功能
*
* */
public class Annotationtest {
}

interface Info{
    public void show();
}

class Person{
    private String name;

    public Person(String name) {
        this.name = name;
    }

    public Person() {
    }
    public void eat(){
        System.out.println("chi");
    }
}
class  Student extends Person implements Info{

    @Override
    public void eat() {
        System.out.println("吃");
    }

    @Override//重载
    public void show() {

    }
}
