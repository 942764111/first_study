/*
 *@(#)xx.spdh.bean
 *@Spxx.java.java  
 *@创建时间:2011-9-23上午09:00:30
 *@作者：ZYK
 *@Copyright 2009-2010 河北北方学院信息科学与工程学院科研所 
 */

package xx.spdh.bean;

import org.hibernate.annotations.Entity;

/**
 * @Spxx <code>{类名称}</code>
 * @author  {作者}
 * @version {版本,常用时间代替}
 * @{功能描述:} 
 */

@Entity
public class Spxx {
	
	private String str1;
	private String str2;
	private int zlid;
	
	public Spxx() {
		
	}
	
	public Spxx(String str1, String str2) {
		this.str1 = str1;
		this.str2 = str2;
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

	/**
	 * @return the zlid
	 */
	public int getZlid() {
		return zlid;
	}

	/**
	 * @param zlid the zlid to set
	 */
	public void setZlid(int zlid) {
		this.zlid = zlid;
	}
	
}
