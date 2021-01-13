package com.ozr.exer1;

import java.util.*;

/**
 * @Author OZR
 * @Date 2021/1/11 19:51
 *
 * 定义个泛型类 DAO<T>，在其中定义一个 Map 成员变量，Map 的键
 * 为 String 类型，值为 T 类型
 *
 *分别创建以下方法：
 * public void save(String id,T entity)： 保存 T 类型的对象到 Map 成员
 * 变量中
 * public T get(String id)：从 map 中获取 id 对应的对象
 * public void update(String id,T entity)：替换 map 中 key 为 id 的内容,
 * 改为 entity 对象
 * public List<T> list()：返回 map 中存放的所有 T 对象
 * public void delete(String id)：删除指定 id 对象
 *
 *
 *
 */
public class DAO<T> {
    private Map<String,T> map;

    public Map<String, T> getMap() {
        return map;
    }

    public void setMap(Map<String, T> map) {   //提供这个是因为在使用sava以及其他的方法之前要先实例化这个map
        this.map = map;
    }

    //public void save(String id,T entity)： 保存 T 类型的对象到 Map 成员
    public void save(String id,T entity){
        map.put(id,entity);

    }
    //public T get(String id)：从 map 中获取 id 对应的对象
    public T get(String id){
        return map.get(id);

    }
    //public void update(String id,T entity)：替换 map 中 key 为 id 的内容,
    public void update(String id,T entity){
        if(map.containsKey(id)){
            map.put(id,entity);
        }
    }
    //public List<T> list()：返回 map 中存放的所有 T 对象
    public List<T> list(){
        List<T> list = new ArrayList<>();
        for(Map.Entry<String,T> entry : map.entrySet()){
            list.add(entry.getValue());
        }

        //这样读取也可以
//        Collection<T> values = map.values();
//        for (T t:values){
//            list.add(t);
//        }

//       这是错误的
//       Collection<T> values = map.values();
//       return  (List<T>)values;
        return list;

    }
    //public void delete(String id)：删除指定 id 对象
    public void delete(String id){
        map.remove(id);
    }


}
