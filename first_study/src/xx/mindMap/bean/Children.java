package xx.mindMap.bean;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

class Children {  
 private List list = new ArrayList();  
   
 public int getSize() {  
  return list.size();  
 }  
   
 public void addChild(Node2 node) {  
  list.add(node);  
 }  
   
 // ƴ�Ӻ��ӽڵ��JSON�ַ���  
 public String toString() {  
  String result = "[";    
  for (Iterator it = list.iterator(); it.hasNext();) {  
   result += ((Node2) it.next()).toString();  
   result += ",";  
  }  
  result = result.substring(0, result.length() - 1);  
  result += "]";  
  return result;  
 }  
   
 // ���ӽڵ�����  
 public void sortChildren() {  
  // �Ա���ڵ��������  
  // �ɸ��ݲ�ͬ���������ԣ����벻ͬ�ıȽ��������ﴫ��ID�Ƚ���  
  Collections.sort(list, new NodeIDComparator());  
  // ��ÿ���ڵ����һ��ڵ��������  
  for (Iterator it = list.iterator(); it.hasNext();) {  
   ((Node2) it.next()).sortChildren();  
  }  
 }  
}  
  
/** 
 * �ڵ�Ƚ��� 
 */  
class NodeIDComparator implements Comparator {  
 // ���սڵ��űȽ�  
 public int compare(Object o1, Object o2) {  
  int j1 = Integer.parseInt(((Node2)o1).id);  
     int j2 = Integer.parseInt(((Node2)o2).id);  
     return (j1 < j2 ? -1 : (j1 == j2 ? 0 : 1));  
 }   
}  

class VirtualDataGenerator {  
	 // ��������Ľ�����б�ʵ��Ӧ���У�������Ӧ�ô����ݿ��в�ѯ��ã�  
	 public static List getVirtualResult() {      
	  List dataList = new ArrayList();  
	    
	  HashMap dataRecord1 = new HashMap();  
	  dataRecord1.put("id", "112000");  
	  dataRecord1.put("topic", "�ȷ����н�ŵ�֧��");  
	  dataRecord1.put("parentid", "110000");  
	    
	  HashMap dataRecord2 = new HashMap();  
	  dataRecord2.put("id", "112200");  
	  dataRecord2.put("topic", "�ȷ����������֧��");  
	  dataRecord2.put("parentid", "112000");  
	    
	  HashMap dataRecord3 = new HashMap();  
	  dataRecord3.put("id", "112100");  
	  dataRecord3.put("topic", "�ȷ����й�����֧��");  
	  dataRecord3.put("parentid", "112000");  
	        
	  HashMap dataRecord4 = new HashMap();  
	  dataRecord4.put("id", "113000");  
	  dataRecord4.put("topic", "�ȷ����п�����֧��");  
	  dataRecord4.put("parentid", "110000");  
	        
	  HashMap dataRecord5 = new HashMap();  
	  dataRecord5.put("id", "100000");  
	  dataRecord5.put("topic", "�ȷ���������");  
	  dataRecord5.put("parentid", "");  
	    
	  HashMap dataRecord6 = new HashMap();  
	  dataRecord6.put("id", "110000");  
	  dataRecord6.put("topic", "�ȷ�����");  
	  dataRecord6.put("parentid", "100000");  
	    
	  HashMap dataRecord7 = new HashMap();  
	  dataRecord7.put("id", "111000");  
	  dataRecord7.put("topic", "�ȷ����н���֧��");  
	  dataRecord7.put("parentid", "110000");    
	      
	  dataList.add(dataRecord1);  
	  dataList.add(dataRecord2);  
	  dataList.add(dataRecord3);  
	  dataList.add(dataRecord4);  
	  dataList.add(dataRecord5);  
	  dataList.add(dataRecord6);  
	  dataList.add(dataRecord7);  
	    
	  return dataList;  
	 }   
}