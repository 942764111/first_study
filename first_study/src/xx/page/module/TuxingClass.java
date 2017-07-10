/*
 *@(#)xx.page.module
 *@Conmen.java.java  
 *@创建时间:2011-5-7下午07:45:05
 *@作者：hp
 *@Copyright 2009-2010 河北北方学院信息科学与工程学院科研所 
 */

package xx.page.module;

import javax.persistence.Entity;

/**
 * @Conmen <code>{类名称}</code>
 * @author  {作者}
 * @version {版本,常用时间代替}
 * @{...功能描述} 
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
