/*
 *@(#)xx.tables_zxl.action
 *@zyxx.java.java  
 *@����ʱ��:2011-5-19����01:10:18
 *@���ߣ�zxl
 *@Copyright 2009-2010 �ӱ�����ѧԺ��Ϣ��ѧ�빤��ѧԺ������ 
 */

package xx.tables_zxl.action;

import javax.persistence.Entity;

/**
 * @zyxx <code>{������}</code>
 * @author  {zxl}
 * @version {�汾,����ʱ�����}
 * @{...��������} 
 */
@Entity
public class Zyxxi {
	private int int1;  
	private  String str1;
	private String str2;
	private int int2;
	
	
	public Zyxxi(){
		
	}
	public Zyxxi(int int1,int int2,String str1,String str2){
		this.int1=int1;
		this.int2=int2;
		this.str1=str1;
		this.str2=str2;
		
	}
	
	public String getStr2() {
		return str2;
	}
	public void setStr2(String str2) {
		this.str2 = str2;
	}
	public int getInt2() {
		return int2;
	}
	public void setInt2(int int2) {
		this.int2 = int2;
	}
	public int getInt1() {
		return int1;
	}
	public void setInt1(int int1) {
		this.int1 = int1;
	}
	public String getStr1() {
		return str1;
	}
	public void setStr1(String str1) {
		this.str1 = str1;
	}

	
	
}
