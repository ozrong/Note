package com.Collection.Iterator;

import com.Collection.collection1.Penson;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

/**
 * @Author OZR
 * @Date 2020/9/16 14:52
 *
 *
 * 集合元素的遍历操作，使用iterator接口
 */
public class IteratorTest {

    @Test
    public  void test(){
        Collection coll = new ArrayList();
        coll.add(123);
        coll.add(456);
        coll.add(new String("tom"));
        coll.add(false);
        coll.add(new Penson("ozr",12));

        Iterator iterator = coll.iterator();
//        遍历方式一
//        System.out.println(iterator.next()); //获取集合中的一个元素，如果遍历的个数比集合多了就会报错

//        遍历方式二，
        for (int i = 0; i < coll.size() ; i++) {
            System.out.println(iterator.next());
        }

//        遍历方式三，(推荐方式)
//        while (iterator.hasNext()){
//            System.out.println(iterator.next());
//        }
//
    }
    @Test
    public void test2(){
        Collection coll = new ArrayList();
        coll.add(123);
        coll.add(456);
        coll.add(new String("tom"));
        coll.add(false);
        coll.add(new Penson("ozr",12));
        Iterator iterator  = coll.iterator();

//        错误方式1
        while ((iterator.next()) != null){
            System.out.println(iterator.next());
        }
//        1.会出现空指针异常；
//        2.数据跳着出现，因为一次循环使用了两次next(让指针移动了两次)
//        错误方式2
        while (coll.iterator().hasNext()){
            System.out.println(coll.iterator().next());
        }
//          1.死循环，不停的在构造新的iterator对象；
//          2.不停输出第一个元素，永远输出新的iterator对象的第一个元素
    }
    @Test
    public void test3() {
        Collection coll = new ArrayList();
        coll.add(123);
        coll.add(456);
        coll.add(new String("tom"));
        coll.add(false);
        coll.add(new Penson("ozr", 12));
        Iterator iterator = coll.iterator();
        while (iterator.hasNext()){
            Object obj = iterator.next();
            if ("tom".equals(obj)){
                iterator.remove();
            }
//         移除tom这个数据
//          注意，后边想遍历的话，还要重新生成Iterator
        }

    }

}
