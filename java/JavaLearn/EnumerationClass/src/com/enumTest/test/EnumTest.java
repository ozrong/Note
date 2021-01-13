package com.enumTest.test;
/*枚举类
* 当需要定义一组常量时，就可以定义枚举类
*
* 定义枚举类：
* 1.jdk5.0之前，自定义枚举类
* 2.jdk5.0是使用enum关键字  定义的枚举类默认继承java.lang.Enum
* */
public class EnumTest {
    public static void main(String[] args) {
        Season spring = Season.SPRING;
        System.out.println(spring);//


        //使用eunm定义的枚举类
        EnumSeason spring1 = EnumSeason.SPRING;
        System.out.println(spring1);//SPRING


        /*一下演示Enum的常用方法*/
        EnumSeason[] values = EnumSeason.values();//values()获取枚举的值，返回一个数组
        for (int i = 0; i <values.length ; i++) {
            System.out.println(values[i]);
        }

         EnumSeason spring2 = EnumSeason.valueOf("SPRING");//valueOf(String str) 获取指定str的枚举类对象。
        System.out.println(spring2);

    }

}
//    自定义枚举类
class Season{
 //1.使用private final修饰对象属性
    private final String seasonName;
    private final String seasonDesc;

//  2.私有化构造器

    private Season(String seasonName, String seasonDesc) {
        this.seasonName = seasonName;
        this.seasonDesc = seasonDesc;
    }
//    3.提供当前枚举类的多个对象
public static final Season SPRING = new Season("春天","春暖花开");
public static final Season SUMMER = new Season("夏天","炎炎夏日");
public static final Season AUTUMN = new Season("秋天","秋高气爽");
public static final Season WINTER = new Season("冬天","冰天雪地");

//4.其他诉求

    //4.1获取枚举类的对象属性
    public String getSeasonName() {
        return seasonName;
    }

    public String getSeasonDesc() {
        return seasonDesc;
    }
    //4.2提供toString()
    public String toString() {
        return "Season{" +
                "seasonName='" + seasonName + '\'' +
                ", seasonDesc='" + seasonDesc + '\'' +
                '}';
    }
}

//使用enum关键字
enum EnumSeason{
    //1.直接先申明枚举类对象对象    对象名（属性1，属性2） 多个对象使用逗号隔开，最后一个对象使用分号
    SPRING("春天","春暖花开"),
    SUMMER("夏天","炎炎夏日"),
    AUTUMN("秋天","秋高气爽"),
    WINTER("冬天","冰天雪地");
    //2.声明对象属性
    private final String seasonName;
    private final String seasonDesc;
    //3.构造器
    EnumSeason(String seasonName, String seasonDesc) {
        this.seasonName = seasonName;
        this.seasonDesc = seasonDesc;
    }

    @Override
    public String toString() {
        return "EnumSeason{" +
                "seasonName='" + seasonName + '\'' +
                ", seasonDesc='" + seasonDesc + '\'' +
                '}';
    }
}
