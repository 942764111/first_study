/*
 *@(#)xx.page.module
 *@String2File.java.java  
 *@创建时间:2016-4-6上午10:47:12
 *@作者：朱永科
 *@Copyright 2009-2010 河北北方学院信息科学与工程学院科研所 
 */

package xx.page.module;

import java.io.File;
import java.io.FileOutputStream;
import java.io.RandomAccessFile;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @String2File <code>{类名称}</code>
 * @author  {朱永科}
 * @version {版本,常用时间代替}
 * @{功能描述:} 
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
