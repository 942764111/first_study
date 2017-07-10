import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author Dengyouchao
 * @date 2013-9-26下午4:03:27
 * @Copyright(c) UCIT
 */
public class RemoveDuplicatesData {
    
    
    @SuppressWarnings({"rawtypes", "unchecked"})
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        Map<String, Map> msp = new HashMap<String, Map>();
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        List<Map<String, Object>> listMap = new ArrayList<Map<String,Object>>();
        Map<String, Object> map1 = new HashMap<String, Object>();
        map1.put("id", "1");
        map1.put("name", "p");
        Map<String, Object> map2 = new HashMap<String, Object>();
        map2.put("id", "3");
        map2.put("name", "h");
        Map<String, Object> map3 = new HashMap<String, Object>();
        map3.put("id", "3");
        map3.put("name", "f");
        list.add(map1);
        list.add(map3);
        list.add(map2);
        
        System.out.println("初始数据：" + list.toString());
        //把list中的数据转换成msp,去掉同一id值多余数据，保留查找到第一个id值对应的数据
        for(int i = list.size()-1 ; i>=0; i--){
            Map map = list.get(i);
            String id = (String)map.get("id");
            map.remove("id");
            msp.put(id, map);
        }
         //把msp再转换成list,就会得到根据某一字段去掉重复的数据的List<Map>
        Set<String> mspKey = msp.keySet();
        for(String key: mspKey){
            Map newMap = msp.get(key);
            newMap.put("id", key);
            listMap.add(newMap);
        }
        
        System.out.println("去掉重复数据后的数据：" + listMap.toString());
    }
    
}