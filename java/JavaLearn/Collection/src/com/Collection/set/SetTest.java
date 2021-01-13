package com.Collection.set;

import com.Collection.collection1.Penson;
import org.junit.Test;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * @Author OZR
 * @Date 2020/9/17 12:56
 *
 *
 * set接口中没有添加新的方法,意味着在set只有使用collection的方法
 *
 * 要求:向set中添加数据时,其所在的类一定要重写equals和hashcode这两个方法
 * 要求:重写equals和hashcode尽可能要保持一致性,即:相等的对象必须具有相等的散列码
 * 一般调用IDE的方法就可以了
 */
public class SetTest {
    @Test
    public void test1(){
        Set set = new HashSet();
        set.add(456);
        set.add(123);
        set.add("AA");
        set.add("CC");
        set.add(new Penson("ton",12));
        set.add(129);
        Iterator iterator = set.iterator();
        while (iterator.hasNext()){
            System.out.println(iterator.next());
        }


    }
}
