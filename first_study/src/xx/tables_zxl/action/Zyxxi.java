/*
 *@(#)xx.tables_zxl.action
 *@zyxx.java.java  
 *@创建时间:2011-5-19下午01:10:18
 *@作者：zxl
 *@Copyright 2009-2010 河北北方学院信息科学与工程学院科研所 
 */

package xx.tables_zxl.action;

import javax.persistence.Entity;

/**
 * @zyxx <code>{类名称}</code>
 * @author  {zxl}
 * @version {版本,常用时间代替}
 * @{...功能描述} 
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
