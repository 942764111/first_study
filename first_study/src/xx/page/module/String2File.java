/*
 *@(#)xx.page.module
 *@String2File.java.java  
 *@����ʱ��:2016-4-6����10:47:12
 *@���ߣ�������
 *@Copyright 2009-2010 �ӱ�����ѧԺ��Ϣ��ѧ�빤��ѧԺ������ 
 */

package xx.page.module;

import java.io.File;
import java.io.FileOutputStream;
import java.io.RandomAccessFile;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @String2File <code>{������}</code>
 * @author  {������}
 * @version {�汾,����ʱ�����}
 * @{��������:} 
 */
@Service("string2File")
@Transactional
public class String2File {

	 public static boolean createFile(File fileName)throws Exception{  
		  boolean flag=false;  
		  try{  
		   if(!fileName.exists()){  
		    fileName.createNewFile();  
		    flag=true;  
		   }  
		  }catch(Exception e){  
		   e.printStackTrace();  
		   flag=false;
		  }  
		  return flag;  
		 }
	 
	 public static boolean writeTxtFile(String content,File  fileName)throws Exception{  
		 
		  createFile(fileName);
		  RandomAccessFile mm=null;  
		  boolean flag=false;  
		  FileOutputStream o=null;  
		  try {  
		   o = new FileOutputStream(fileName);  
		      o.write(content.getBytes("GBK"));  
		      o.close();  
		   flag=true;  
		  } catch (Exception e) {  
		   // TODO: handle exception  
		   e.printStackTrace();  
		  }finally{  
		   if(mm!=null){  
		    mm.close();  
		   }  
		  }  
		  return flag;  
		 }  
}
