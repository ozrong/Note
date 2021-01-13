package com.objectAndsubobject.classExtends;
public class Parent {
    int a=10;
    public String public_string = "i am public string";
    String undefined_string = "i am undefined string";
    private String private_string = "i am private string";
    protected String protected_string = "i am protected string";
    int add(int a){
        this.a=this.a+a;
        return this.a;
    }
    public void test1(){
        System.out.println("i am public method");
    }
    private void test2(){
        System.out.println("i am private method");
    }
    protected void test3(){
        System.out.println("i am protected method");
    }
    void test4(){
        System.out.println("i am undefined method");
    }
    void use_Test_a(){
        Test te=new Test();
        te.a();
    }
    class Test{
        void a() {
            Parent parent = new Parent();
            parent.test1();  /*同一包下,不同类可以访问 protected public 友好*/
            parent.test3();
            parent.test4();
            parent.test2(); //同一包下,不同类也不可以访问别的类的private 方法

            System.out.println(parent.public_string);
            System.out.println(parent.undefined_string);
            System.out.println(parent.protected_string);
            System.out.println(parent.private_string);
        }
    }
}
class Un_Test{
    void a() {
        Parent parent = new Parent();
        parent.test1();  /*同一包下,不同类可以访问 protected public 友好*/
        parent.test3();
        parent.test4();
//        parent.test2(); //同一包下,不同类也不可以访问别的类的private 方法

        System.out.println(parent.public_string);
        System.out.println(parent.undefined_string);
        System.out.println(parent.protected_string);
//        System.out.println(parent.private_string);
    }
}