package com.ozr.test;

import org.junit.Test;

import java.util.Arrays;
import java.util.Scanner;

public class Test_test {
    public static void main(String[] args) {
        String name = test3();
    }
    @Test
    public void test1(){
        int[][] a = {{1, 2, 3, 4, 5, 6},{2,5,6,7,8,9}};
        System.out.println(Arrays.deepToString(a));//快速的打印二维数组


    }

    @Test
    public  void test2(){
        //构造不一样的二维数组
        int[][] arrayTest=new int[10][];
        for (int i = 0; i <10 ; i++) {
            arrayTest[i]=new int[i+1];
            for (int j = 0; j <=i ; j++) {
                arrayTest[i][j]=j;
            }
        }
        for(int[] temp:arrayTest){
            for(int b: temp){
                System.out.print(b);
            }
            System.out.print("\n");
        }

/*      打印结果
        012
        0123
        01234
        012345
        0123456
        01234567
        012345678
        0123456789*/

    }

    public static String test3(){
        Scanner in = new Scanner(System.in);
        String name=in.next();
        System.out.println(name);
        return name;
    }


    }



