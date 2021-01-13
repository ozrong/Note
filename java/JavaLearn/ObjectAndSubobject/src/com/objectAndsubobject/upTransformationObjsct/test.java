package com.objectAndsubobject.upTransformationObjsct;
/*
* 讲解上转型
*
*
* */
public class test {
    public static void main(String[] args) {
        Animal animal;
        animal = new Cat("喵喵",12);  //animal就是上转型对象
        System.out.println(animal.age);//上转型可以调用子类继承父类的属性（变量）
        System.out.println(animal.supSkill);//上转型可以调用子类隐藏的父类的属性（变量）
        animal.eat();//上转型可以调用子类重写的父类的对象
        animal.jump();//上转型可以调用子类继承父类的方法
        animal.run();//
     //   animal.Skill();  ////////上转型不可以调用子类新添加的方法
     //   System.out.println(animal.clever);////////上转型不可以调用子类新添加的属性（变量）
/*
* 总结：
* 1.上转型调用的属性（变量）都是父类中属性，即使在子类中再次声明了一个同名的变量（这个操作叫隐藏父类的变量）,也是调用的是父类声明的那个
* 2.如果字类重写了父类的方法，那么上转型调用这个方法的话就是调用子类重写过后的那个方法
* 2.上转型调用不了子类新增加的方法和变量
*
* 感觉这个子类就是修改了父类的方法而已，（重写的方法被修改了，但是修改不了变量）
* */
    }
}
class Animal{
    String name;
    int age;
    String supSkill = "yayayayaya";

    public Animal(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public Animal() {
    }
    public void eat(){
        System.out.println("我可以吃东西");
    }
    public void run(){
        System.out.println("我可以奔跑");
    }
    public void jump(){
        System.out.println("我可以跳");
    }
}

class Cat extends Animal{
    String supSkill = "喵喵喵";
    String clever = "可爱";
    public Cat(String name, int age) {
        super(name, age);
    }
    public Cat() {
    }

    @Override
    public void eat() {
        System.out.println("我喜欢吃鱼");
    }
    public void Skill(){
        System.out.println("我擅长抓老鼠");
    }
}