package com.objectAndsubobject.classExtends;

public class Start {
    public static void main(String[] args) {
        Son son = new Son();
        System.out.println(son.add(2));
        Parent parent = new Parent();
        parent.test1();  /*同一包下,不同类可以访问 protected public 友好*/
        parent.test3();
        parent.test4();
        //parent.test2(); 同一包下,不同类也不可以访问别的类的private 方法

        System.out.println(parent.public_string);
        System.out.println(parent.undefined_string);
        System.out.println(parent.protected_string);


    }
}
