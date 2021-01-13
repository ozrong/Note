package com.enumTest.test;

public class EnumTest1 {
    public static void main(String[] args) {

/*        //1.实现接口，直接在Enum类中实现 调用
        EnumSeason1 Spring = EnumSeason1.SPRING;
        Spring.show();////在哪儿啊哪儿啊
        EnumSeason1 winter = EnumSeason1.WINTER;
        winter.show();//在哪儿啊哪儿啊*/

        EnumSeason1 Spring = EnumSeason1.SPRING;
        Spring.show();//春天在哪儿
        EnumSeason1 winter = EnumSeason1.WINTER;
        winter.show();//大约冬季
    }
}


/*使用enum类来实现接口的情况
* 1.实现接口，直接在Enum类中实现
* 2.枚举类的每一个对象实现接口
*
*
* */
enum EnumSeason1 implements Info {
    //1.直接先申明枚举类对象对象    对象名（属性1，属性2） 多个对象使用逗号隔开，最后一个对象使用分号



   // 2.枚举类的每一个对象实现接口   这样每个对象show就不一样了
    SPRING("春天", "春暖花开"){
       @Override
       public void show() {
           System.out.println("春天在哪儿");
       }
   },
    SUMMER("夏天", "炎炎夏日"){
        @Override
        public void show() {
            System.out.println("夏天的风");
        }
    },
    AUTUMN("秋天", "秋高气爽"){
        @Override
        public void show() {
            System.out.println("秋天不回来");
        }
    },
    WINTER("冬天", "冰天雪地"){
        @Override
        public void show() {
            System.out.println("大约冬季");
        }
    };
    //2.声明对象属性
    private final String seasonName;
    private final String seasonDesc;

    //3.构造器
    EnumSeason1(String seasonName, String seasonDesc) {
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


    //1.实现接口，直接在Enum类中实现
    //但是这种无论是SPRING还是其他的类来调用show都是一样的输出
 /*   @Override
    public void show() {
        System.out.println("在哪儿啊哪儿啊");
    }*/
}
interface Info{
    void show();

}