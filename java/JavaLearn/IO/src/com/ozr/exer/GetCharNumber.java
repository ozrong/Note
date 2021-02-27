package com.ozr.exer;

import org.junit.Test;


import java.io.*;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author OZR
 * @Date 2021/1/21 17:20
 *
 *
 *  * 练习3:获取文本上字符出现的次数,把数据写入文件
 *  *
 *  * 思路：
 *  * 1.遍历文本每一个字符
 *  * 2.字符出现的次数存在Map中
 *  *
 *  * Map<Character,Integer> map = new HashMap<Character,Integer>();
 *  * map.put('a',18);
 *  * map.put('你',2);
 *  *
 *  * 3.把map中的数据写入文件
 */
public class GetCharNumber {
    @Test
    public void test() {
        FileReader fr = null;
        BufferedWriter bw = null;
        try {
            fr = new FileReader("dbcp.txt");
            bw = new BufferedWriter(new FileWriter("wordsCount.txt"));
            Map<Character,Integer> ans = new HashMap<>();

            int word = 0;
            while ((word = fr.read()) !=-1){
                char temp = (char)word;
                int num = ans.getOrDefault(temp,0);
                num++;
                ans.put(temp,num);
            }

            for (Map.Entry<Character,Integer> entry : ans.entrySet()){
                switch (entry.getKey()){
                    case ' ' :
                        bw.write("空格=" + entry.getValue());
                        break;
                    case '\t'://\t表示tab 键字符
                        bw.write("tab键=" + entry.getValue());
                        break;
                    case '\r'://
                        bw.write("回车=" + entry.getValue());
                        break;
                    case '\n'://
                        bw.write("换行=" + entry.getValue());
                        break;
                    default:
                        bw.write(entry.getKey() + "=" + entry.getValue());
                        break;
                }
                bw.newLine();


                }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(fr !=null)
            try {
                fr.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            if(bw !=null)
            try {
                bw.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


        }


    }



