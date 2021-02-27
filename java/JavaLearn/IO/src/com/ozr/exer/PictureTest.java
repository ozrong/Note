package com.ozr.exer;

import org.junit.Test;

import javax.swing.plaf.nimbus.NimbusLookAndFeel;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @Author OZR
 * @Date 2021/1/21 17:08
 */
public class PictureTest {

    //图片加密
    @Test
    public void test(){
        FileInputStream fis = null;
        FileOutputStream fos = null;
        try {
            fis = new FileInputStream("test.jpg");
            fos = new FileOutputStream("testAndsecreat.jpg");
            byte[] buffer = new byte[20];
            int len;
            while ((len = fis.read(buffer)) != -1){
                for (int i = 0; i <len ; i++) {
                    buffer[i] =(byte) (buffer[i] ^ 5) ;
                }

                fos.write(buffer,0,len
                );
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {

            try {
                fis.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    //图片解密
    @Test
    public void test2(){
        FileInputStream fis = null;
        FileOutputStream fos = null;
        try {
            fis = new FileInputStream("testAndsecreat.jpg");
            fos = new FileOutputStream("testAndsecreat1.jpg");
            byte[] buffer = new byte[20];
            int len;
            while ((len = fis.read(buffer)) != -1){
                for (int i = 0; i <len ; i++) {
                    buffer[i] =(byte) (buffer[i] ^5) ;
                }

                fos.write(buffer,0,len);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {

            try {
                fis.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
