import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author Dengyouchao
 * @date 2013-9-26����4:03:27
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
        
        System.out.println("��ʼ���ݣ�" + list.toString());
        //��list�е�����ת����msp,ȥ��ͬһidֵ�������ݣ��������ҵ���һ��idֵ��Ӧ������
        for(int i = list.size()-1 ; i>=0; i--){
            Map map = list.get(i);
            String id = (String)map.get("id");
            map.remove("id");
            msp.put(id, map);
        }
         //��msp��ת����list,�ͻ�õ�����ĳһ�ֶ�ȥ���ظ������ݵ�List<Map>
        Set<String> mspKey = msp.keySet();
        for(String key: mspKey){
            Map newMap = msp.get(key);
            newMap.put("id", key);
            listMap.add(newMap);
        }
        
        System.out.println("ȥ���ظ����ݺ�����ݣ�" + listMap.toString());
    }
    
}