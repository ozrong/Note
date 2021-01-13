package com.Collection.exercise;

import com.Collection.set.TreeSetTest;
import org.junit.Test;
import sun.plugin2.message.BestJREAvailableMessage;

import javax.management.relation.RoleUnresolved;
import java.util.Comparator;
import java.util.Iterator;
import java.util.TreeSet;
/**
 * @Author OZR
 * @Date 2020/9/17 21:16
 */
public class EmployeeTest {
    @Test
    public void test(){
        TreeSet set = new TreeSet();

        Employee e1 = new Employee("刘德华",55,new MyDate(1995,8,9));
        Employee e2 = new Employee("张学友",45,new MyDate(1997,8,21));
        Employee e3 = new Employee("郭富城",44,new MyDate(1999,11,8));
        Employee e4 = new Employee("黎明",51,new MyDate(1997,9,5));
        Employee e5 = new Employee("梁朝伟",55,new MyDate(1998,7,5));

        set.add(e1);
        set.add(e2);
        set.add(e3);
        set.add(e4);
        set.add(e5);

        Iterator iterator = set.iterator();
        while (iterator.hasNext()){
            System.out.println(iterator.next());
        }

    }




    @Test
    public void test2(){

//        按birthday排序
        TreeSet set = new TreeSet(new Comparator() {
            @Override
            public int compare(Object o1, Object o2) {
                if(o1 instanceof Employee && o2 instanceof Employee){
                    Employee e1 = (Employee)o1;
                    Employee e2 = (Employee)o2;
                    MyDate d1 = e1.getBirthday();
                    MyDate d2 = e2.getBirthday();
                    int minyear = d1.getYear() - d2.getYear();
                    if(minyear !=0){
                        return minyear;
                    }else{
                        int minmonth = d1.getMonth() - d2.getMonth();
                        if (minmonth !=0){
                            return minmonth;
                        }else{
                            return d1.getDay()-d2.getDay();
                        }
                    }
                }else{
                    throw  new RuntimeException("类型不同");
                }
            }
        });

        Employee e1 = new Employee("刘德华",55,new MyDate(1995,8,9));
        Employee e2 = new Employee("张学友",45,new MyDate(1997,8,21));
        Employee e3 = new Employee("郭富城",44,new MyDate(1999,11,8));
        Employee e4 = new Employee("黎明",51,new MyDate(1997,9,5));
        Employee e5 = new Employee("梁朝伟",55,new MyDate(1998,7,5));

        set.add(e1);
        set.add(e2);
        set.add(e3);
        set.add(e4);
        set.add(e5);

        Iterator iterator = set.iterator();
        while (iterator.hasNext()){
            System.out.println(iterator.next());
        }

    }
}
