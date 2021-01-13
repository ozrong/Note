package com.objectAndsubobject.super_keyword;

public class Example {
    public static void main(String[] args) {
        UniverStudent student = new UniverStudent(1001,"zzz",false);
    }

}

class Student{
    int number;
    String name;
    Student(){
        System.out.println("你是个傻逼");
    }
    Student(int number,String name){
        this.name =name;
        this.number=number;
        System.out.println("my name is"+this.name+"and number is"+this.number);
    }
}

class UniverStudent extends Student{
    boolean 婚否;
    UniverStudent(int number,String name,boolean b){
//        super(number,name);
        婚否=b;
        System.out.println("婚否="+婚否);
    }
}
