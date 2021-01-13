package com.ozr.java;

import org.junit.Test;

import java.io.File;
import java.io.IOException;

/**
 * @Author OZR
 * @Date 2021/1/11 20:31
 *
 * File类的一个对象，代表一个文件 或者一个文件目录（文件夹）
 * File类中涉及到关于文件或者文件目录的创建、删除、重命名、修改时间、文件大小等方法都不
 * 涉及到对文件里面的内容进行操作，如果需要对文件的内容进行操作就需要IO流
 */
public class FileTest {

    //创建File类的实例

    /*
     public File(String pathname)
    以pathname为路径创建File对象，可以是 绝对路径或者相对路径，如果
    pathname是相对路径，则默认的当前路径在系统属性user.dir中存储。
     绝对路径：是一个固定的路径,从盘符开始
     相对路径：是相对于某个位置开始

     public File(String parent,String child)
    以parent为父路径，child为子路径创建File对象。

     public File(File parent,String child)
    根据一个父File对象和子文件路径创建File对象
    */
    @Test
    public void test1(){//构造器
        /**
         *分隔符：
         * windows: \\
         * lunix:/
         */

        //构造器1

        File file = new File("hello.txt");//hello.txt相对于当前modul的
        File file1 = new File("E:\\CODE\\IDEA_code\\JavaLearn\\IO\\hello.txt");//绝对路径

        System.out.println(file);
        System.out.println(file1); //这是的file只是一个内存层面的一个对象，所以不会报错
        //hello.txt
        //E:\CODE\IDEA_code\JavaLearn\IO\hello.txt

        //构造器2
        File file3 = new File("E:\\CODE\\IDEA_code","JavaLearn");
        System.out.println(file3);
        //E:\CODE\IDEA_code\JavaLearn

        //构造器3
        File file4 = new File(file3,"IO");
        System.out.println(file4);
        //E:\CODE\IDEA_code\JavaLearn\IO
        //构造器 2和3就是相当于把两个路径拼接起来而已
    }

    /*
     File 类的获取功能
     public String getAbsolutePath()：获取绝对路径
     public String getPath() ：获取路径
     public String getName() ：获取名称
     public String getParent()：获取上层文件目录路径。若无，返回null
     public long length() ：获取文件长度（即：字节数）。不能获取目录的长度。
     public long lastModified() ：获取最后一次的修改时间，毫秒值 new Data(lastModified())就可以看了

    下面两个方法是适用于文件目录
     public String[] list() ：获取指定目录下的所有文件或者文件目录的名称数组
     public File[] listFiles() ：获取指定目录下的所有文件或者文件目录的File数组
    */
    @Test
    public void test2(){
        File file1 = new File("hello.md");
        File file2 = new File("E:\\CODE\\IDEA_code\\JavaLearn\\IO\\hello.md");
        System.out.println(file1.getAbsoluteFile());
        System.out.println(file1.getPath());
        System.out.println(file1.getName());
        System.out.println(file1.getParent());
        System.out.println(file1.length());
        System.out.println(file1.lastModified());
        //文件不存在时
        //E:\CODE\IDEA_code\JavaLearn\IO\hello.txt
        //hello.txt
        //hello.txt
        //null
        //0
        //0

        //文件存在
        //E:\CODE\IDEA_code\JavaLearn\IO\hello.md
        //hello.md
        //hello.md
        //null
        //9
        //1610369938172

        System.out.println();

        System.out.println(file2.getAbsoluteFile());
        System.out.println(file2.getPath());
        System.out.println(file2.getName());
        System.out.println(file2.getParent());
        System.out.println(file2.length());
        System.out.println(file2.lastModified());
        //文件不存在时
        //E:\CODE\IDEA_code\JavaLearn\IO\hello.md
        //E:\CODE\IDEA_code\JavaLearn\IO\hello.md
        //hello.md
        //E:\CODE\IDEA_code\JavaLearn\IO
        //0
        //0

        //文件存在
        // E:\CODE\IDEA_code\JavaLearn\IO\hello.md
        //E:\CODE\IDEA_code\JavaLearn\IO\hello.md
        //hello.md
        //E:\CODE\IDEA_code\JavaLearn\IO
        //9
        //1610369938172
        //
        //Process finished with exit code 0

        //以上操作时 文件可以不存在的，也不会报错


        //  public String[] list() ：获取指定目录下的所有文件或者文件目录的名称数组
        //  public File[] listFiles() ：获取指定目录下的所有文件或者文件目录的File数组
        File file = new File("E:\\CODE");
        String[] list = file.list();
        for(String s:list){
            System.out.println(s);
        }
        //android_code
        //IDEA_code
        //python_code


    }



    /*
     File 类的重命名功能
     public boolean renameTo(File dest):把文件重命名为指定的文件路径
    */
    @Test
    public void test3(){
        File flie = new File("hello.md");//必须在
        File flie2 = new File("hello_change.md");//可以是别的目录

        //要想改名成功 file要存在，file2要不存在。
        boolean renameTo = flie.renameTo(flie2);
        System.out.println(renameTo);
    }



    /*
     File 类的判断功能
     public boolean isDirectory()：判断是否是文件目录
     public boolean isFile() ：判断是否是文件
     public boolean exists() ：判断是否存在
     public boolean canRead() ：判断是否可读
     public boolean canWrite() ：判断是否可写
     public boolean isHidden() ：判断是否隐藏
    */
    @Test
    public void test4(){
        File file1 = new File("hello_change.md");
        System.out.println(file1.isDirectory());
        System.out.println(file1.isFile());
        System.out.println(file1.exists());
        System.out.println(file1.canRead());
        System.out.println(file1.canWrite());
        System.out.println(file1.isHidden());
        //false
        //true
        //true
        //true
        //true
        //false
        System.out.println("------------");



        File file2 = new File("hello.md"); //文件不存在
        System.out.println(file2.isDirectory());
        System.out.println(file2.isFile());
        System.out.println(file2.exists());
        System.out.println(file2.canRead());
        System.out.println(file2.canWrite());
        System.out.println(file2.isHidden());
        //false
        //false
        //false
        //false
        //false
        //false


    }
    /*
     File 类的创建功能
     public boolean createNewFile() ：创建文件。若文件存在，则不创建，返回false
     public boolean mkdir() ：创建文件目录。如果此文件目录存在，就不创建了。
    如果此文件目录的上层目录不存在，也不创建。
     public boolean mkdirs() ：创建文件目录。如果上层文件目录不存在，一并创建
     注意事项：如果你创建文件或者 文件 目录没有 写 盘符路径 ， 那么 ， 默认在项目
     路径下 。


     File 类的删除功能
     public boolean delete()：删除文件或者文件夹
    删除注意事项：
    Java中的删除不走 回收站。
    要删除一个文件目录，请注意该文件目录内不能包含文件或者文件目录
     */
    @Test
    public void test5(){
        File file1 = new File("hello_test.md");

        //创建文件
        if(!file1.exists()){
            try {
                file1.createNewFile();
                System.out.println("创建成功");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else{
            System.out.println("文件存在  在删除");
            file1.delete();
            System.out.println("删除成功");

        }

        File file2 = new File("test");
        System.out.println(file2.delete());//要想删除成功test下不能有子目录


    }

    //文件目录的创建
    @Test
    public void test6(){
        File file = new File("E:\\CODE\\IDEA_code\\JavaLearn\\IO\\test\\test1\\hahah2");
        boolean mkdir = file.mkdir();
        if(mkdir){
            System.out.println("创建成功1");
        }

        File file1 = new File("E:\\CODE\\IDEA_code\\JavaLearn\\IO\\test\\test2\\hahah2");
        boolean mkdir1 = file1.mkdirs();
        if(mkdir1){
            System.out.println("创建成功2");
        }


    }

}
