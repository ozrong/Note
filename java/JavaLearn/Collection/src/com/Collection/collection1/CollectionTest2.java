package com.Collection.collection1;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

/**
 * @Author OZR
 * @Date 2020/9/13 16:38
 *
 *
 *
 * collection的方法的使用
 *
 * 向collection添加自定义的类的对象时，在自定义类的时候要求重写equals方法。
 */
public class CollectionTest2 {
    @Test
    public void test1(){
        Collection coll = new ArrayList();
//        coll.contains()
        coll.add(123);
        coll.add(456);
        coll.add(new String("tom"));
        coll.add(false);
        coll.add(new Penson("ozr",12));
//        Penson p = new Penson("kk",25);
        coll.add(new Penson("kk",25));

//        coll.contains(Object obj)判断这个集合包含这个obj吗
        boolean flag = coll.contains(123);
        System.out.println(flag);//true

        /*=========================================*/
        System.out.println(coll.contains(new String("tom")));
        //注意 这个的new String和之前的new String是两个对象，地址不同的
        //返回的是true,显然这个contains()比较的是内容
        //显然这个使用的equals,比较内容
        //String这个类里面重写了equals的

        System.out.println(coll.contains(new Penson("kk",25)));//这个是false
        //contains()判断时会使用这个obj对象所在的类的equals()
        // 而我们自定的类里面没有重写equals()，会自动调用Object里面的equals()
        //但是Object里面的equals里面比较方时“==”所以这两个对象不一样了
        //想要变成true可以在自定义的类里面重写equals


        /*===============================*/
//        coll.containsAll(collection coll1) 判断coll1中的所有对象在不在coll中

//        coll.remove(Collection coll1) 在coll中移除coll1中的所有元素（差集）



    }
    @Test
    public  void test2(){
        Collection coll = new ArrayList();
        coll.add(123);
        coll.add(456);
        coll.add(new String("tom"));
        coll.add(false);
        coll.add(new Penson("ozr",12));

        Collection coll1 = Arrays.asList(123,456);

        /* Arrays.asList()
         * 该方法是将数组转化成List集合的方法（返回指定数组支持的固定大小列表）
         *（1）该方法适用于对象型数据的数组（String、Integer...）
         *（2）该方法不建议使用于基本数据类型的数组（byte,short,int,long,float,double,boolean）
         *（3）该方法将数组与List列表链接起来：当更新其一个时，另一个自动更新
         *（4）不支持add()、remove()、clear()等方法
         *用此方法得到的List的长度是不可改变的
         *
         */
//        coll.retainAll(Collection coll1) 求出coll和coll1中共有的元素，并赋给coll (交集)
        coll.retainAll(coll1);
        System.out.println(coll);





    }
@Test
    public void test3(){
    Collection coll = new ArrayList();
    coll.add(123);
    coll.add(456);
    coll.add(new String("tom"));
    coll.add(false);
    coll.add(new Penson("ozr",12));


    Collection coll1 = new ArrayList();
    coll1.add(123);
    coll1.add(456);
    coll1.add(new String("tom"));
    coll1.add(false);
    coll1.add(new Penson("ozr",12));

    System.out.println(coll.equals(coll1));//ture

    Collection coll2 = new ArrayList();
    coll2.add(123);
    coll2.add(456);
    coll2.add(new Penson("ozr",12));
    coll2.add(new String("tom"));
    coll2.add(false);
    System.out.println(coll.equals(coll2));//flase
    /*
    注意：虽然coll和coll2的元素是一样的，但是顺序不一样，因为ArrayList是有序的，所以这两者不一样
     */


//    coll.hashCode();返回当前对象的哈希值
    }
@Test
    public void test4(){
    Collection coll = new ArrayList();
    coll.add(123);
    coll.add(456);
    coll.add(new String("tom"));
    coll.add(false);
    coll.add(new Penson("ozr",12));


//    集合---->数组  toArray(),返回的是Object对象（在ArrayList添加的是对象，转换成数组当然是对象）
    Object[] arr = coll.toArray();





}
}
