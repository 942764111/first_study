package xx.mindMap.bean;
public class Node2 {  
 /** 
  * �ڵ��� 
  */  
 public String id;  
 /** 
  * �ڵ����� 
  */  
 public String topic;  
 /** 
  * ���ڵ��� 
  */  
 public String parentid;  
 
 public String direction;
 /** 
  * ���ӽڵ��б� 
  */  
 
 private Children children = new Children();  
 
 // ���������ƴ��JSON�ַ���  
 public String toString() {    
  String result = "{"  
   + "\'id\' : '" + id + "'"  
   + ", \'topic\' : '" + topic + "'";  
    
  if (children != null && children.getSize() != 0) {  
   result += ", \'children\' : " + children.toString();  
  }
//  else {  
//   result += ", leaf : true";  
//  }  
      
  return result + "}";  
 }  
   
 // �ֵܽڵ��������  
 public void sortChildren() {  
  if (children != null && children.getSize() != 0) {  
   children.sortChildren();  
  }  
 }  
   
 // ��Ӻ��ӽڵ�  
 public void addChild(Node2 node) {  
  this.children.addChild(node);  
 }  
}  