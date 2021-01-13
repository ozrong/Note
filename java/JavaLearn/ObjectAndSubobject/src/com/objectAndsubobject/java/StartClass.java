package com.objectAndsubobject.java;
import com.objectAndsubobject.classExtends.*;

public class StartClass {
    public static void main(String[] args) {
        OOPTest oop = new OOPTest();
        KindClass kind = new KindClass();/*在同一包下 可以使用友好类创建对象*/

        Parent parent = new Parent();
        parent.test1();
/*      parent.test2();
        parent.test3();
        parent.test4(); 不同包下,只可以访问public方法  */
        System.out.println(parent.public_string);
/*        System.out.println(parent.undefined_string);
        System.out.println(parent.protected_string);
        System.out.println(parent.private_string);*/


    }
}
