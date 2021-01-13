import javax.sound.midi.Soundbank;
import javax.swing.text.AbstractDocument;
import java.beans.IntrospectionException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.net.SocketTimeoutException;
import java.security.Principal;
import java.util.*;

/**
 * @Author OZR
 * @Date 2020/8/11 19:36
 */
public class test11 {
    public static void main(String[] args) {
        Map<String,String> map = new HashMap<>();
        map.put("1","one");
        map.put("2","two");
        map.put("3","three");
        map.put("","four");

        Set<Map.Entry<String,String>> set = map.entrySet();
        Iterator<Map.Entry<String, String>> iterator = set.iterator();
        while (iterator.hasNext()){
            Map.Entry<String,String> entry = iterator.next();
            entry.getKey();
            entry.getValue();
        }

        Collection<String> values = map.values();

        for (String v : values) {
            System.out.println("value= " + v);
        }

        List<Integer> aa = new ArrayList<>();
        aa.add(1);
        aa.add(6);
        aa.add(5);
        aa.add(4);


        for (int v : aa) {
            System.out.println("value= " + v);
        }







    }

}

















