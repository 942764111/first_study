/*
 *@(#)xx.page.module
 *@Conmen.java.java  
 *@创建时间:2011-5-7下午07:45:05
 *@作者：hp
 *@Copyright 2009-2010 河北北方学院信息科学与工程学院科研所 
 */

package xx.page.module;

import java.util.List;

import javax.persistence.Entity;

/**
 * @Conmen <code>{类名称}</code>
 * @author  {作者}
 * @version {版本,常用时间代替}
 * @{...功能描述} 
 */
@Entity
public class Conmen {
   private String str1;
   private String str2;
   private byte[] str3;
   private String str4;
   private String str5;
   private String str6;
   private String str7;
   private String str8;
   private int int1;
   private int int2;
   private int int3;
   private int int4;
   private int int5;
   private List<Object> arr1;
   public Conmen(){
	   
   }
   public Conmen(String str1,String str2,byte[] str3,String str4,String str5,String str6,String str7,String str8,
		   int int1,int int2,int int3,int int4,int int5,List<Object> arr1){
	   this.str1=str1;
	   this.str2=str2;
	   this.str3=str3;
	   this.str4=str4;
	   this.str5=str5;
	   this.str6=str6;
	   this.str7=str7;
	   this.str8=str8;
	   this.int1=int1;
	   this.int2=int2;
	   this.int3=int3;
	   this.int4=int4;
	   this.int5=int5;
	   this.arr1=arr1;
   }
   
/**
 * @return the int1
 */
public int getInt1() {
	return int1;
}
/**
 * @param int1 the int1 to set
 */
public void setInt1(int int1) {
	this.int1 = int1;
}
/**
 * @return the int2
 */
public int getInt2() {
	return int2;
}
/**
 * @param int2 the int2 to set
 */
public void setInt2(int int2) {
	this.int2 = int2;
}

/**
 * @return the int3
 */
public int getInt3() {
	return int3;
}
/**
 * @param int3 the int3 to set
 */
public void setInt3(int int3) {
	this.int3 = int3;
}
/**
 * @return the int4
 */
public int getInt4() {
	return int4;
}
/**
 * @param int4 the int4 to set
 */
public void setInt4(int int4) {
	this.int4 = int4;
}
/**
 * @return the int5
 */
public int getInt5() {
	return int5;
}
/**
 * @param int5 the int5 to set
 */
public void setInt5(int int5) {
	this.int5 = int5;
}
/**
 * @return the str1
 */
public String getStr1() {
	return str1;
}

/**
 * @param str1 the str1 to set
 */
public void setStr1(String str1) {
	this.str1 = str1;
}
/**
 * @return the str2
 */
public String getStr2() {
	return str2;
}
/**
 * @param str2 the str2 to set
 */
public void setStr2(String str2) {
	this.str2 = str2;
}
/**
 * @return the str3
 */
public byte[] getStr3() {
	return str3;
}
/**
 * @param bs the str3 to set
 */
public void setStr3(byte[] bs) {
	this.str3 = bs;
}
/**
 * @return the str4
 */
public String getStr4() {
	return str4;
}
/**
 * @param str4 the str4 to set
 */
public void setStr4(String str4) {
	this.str4 = str4;
}
/**
 * @return the str5
 */
public String getStr5() {
	return str5;
}
/**
 * @param str5 the str5 to set
 */
public void setStr5(String str5) {
	this.str5 = str5;
}
/**
 * @return the str6
 */
public String getStr6() {
	return str6;
}
/**
 * @param str6 the str6 to set
 */
public void setStr6(String str6) {
	this.str6 = str6;
}
/**
 * @return the str7
 */
public String getStr7() {
	return str7;
}
/**
 * @param str7 the str7 to set
 */
public void setStr7(String str7) {
	this.str7 = str7;
}

/**
 * @return the str8
 */
public String getStr8() {
	return str8;
}
/**
 * @param str8 the str8 to set
 */
public void setStr8(String str8) {
	this.str8 = str8;
}
/**
 * @return the arr1
 */
public List<Object> getArr1() {
	return arr1;
}
/**
 * @param arr1 the arr1 to set
 */
public void setArr1(List<Object> arr1) {
	this.arr1 = arr1;
}

   
}
