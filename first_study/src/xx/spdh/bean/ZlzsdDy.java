/*
 *@(#)xx.spdh.bean
 *@BflbAction.java.java  
 *@创建时间:2011-8-29上午09:00:03
 *@作者：朱永科
 *@Copyright 2009-2010 河北北方学院信息科学与工程学院科研所 
 */

package xx.spdh.bean;

import org.hibernate.annotations.Entity;

/**
 * @BflbAction <code>{类名称}</code>
 * @author  {作者}
 * @version {版本,常用时间代替}
 * @{功能描述:} 
 */

@Entity
public class ZlzsdDy {
	
	private String str1;
	private String str2;
	private String str3;
	private String str4;
	private String str5;
	private String str6;
	
	public ZlzsdDy() {
		
	}
	
	public ZlzsdDy(String str1, String str2, String str3, String str4, String str5, String str6) {
		
		this.str1 = str1;
		this.str2 = str2;
		this.str3 = str3;
		this.str4 = str4;
		this.str5 = str5;
		this.str6 = str6;
	}

	public String getStr1() {
		return str1;
	}

	public void setStr1(String str1) {
		this.str1 = str1;
	}

	public String getStr2() {
		return str2;
	}

	public void setStr2(String str2) {
		this.str2 = str2;
	}

	public String getStr3() {
		return str3;
	}

	public void setStr3(String str3) {
		this.str3 = str3;
	}

	public String getStr4() {
		return str4;
	}

	public void setStr4(String str4) {
		this.str4 = str4;
	}

	public String getStr5() {
		return str5;
	}

	public void setStr5(String str5) {
		this.str5 = str5;
	}

	public String getStr6() {
		return str6;
	}

	public void setStr6(String str6) {
		this.str6 = str6;
	}
	
}
