
/**
 * @Author OZR
 * @Date 2020/8/11 19:36
 */
public class test11 {
    public static void main(String[] args) {
        out:for(int i=0;i<5;i++){
            for(int j=0;j<5;j++){
                if(j==3){
                    continue out;
                }
                System.out.println(i+"  "+j);
            }
        }

    }
}

















