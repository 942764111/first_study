/*
 *@(#)xx.page.module
 *@Conmen.java.java  
 *@����ʱ��:2011-5-7����07:45:05
 *@���ߣ�hp
 *@Copyright 2009-2010 �ӱ�����ѧԺ��Ϣ��ѧ�빤��ѧԺ������ 
 */

package xx.page.module;

import javax.persistence.Entity;

/**
 * @Conmen <code>{������}</code>
 * @author  {����}
 * @version {�汾,����ʱ�����}
 * @{...��������} 
 */
@Entity
public class TuxingClass {
   private String name;
 
   private String bfb;
   public TuxingClass(){
	   
   }
   public TuxingClass(String name,String bfb){
	  this.name=name;
	 
	  this.bfb=bfb;
   }
/**
 * @return the name
 */
public String getName() {
	return name;
}
/**
 * @param name the name to set
 */
public void setName(String name) {
	this.name = name;
}

/**
 * @return the color
 */
public String getBfb() {
	return bfb;
}
/**
 * @param color the color to set
 */
public void setBfb(String bfb) {
	this.bfb = bfb;
}

   
}
