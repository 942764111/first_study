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
public class Bflb {
	
	private String str1;
	
	public Bflb() {
		
	}

	public Bflb(String str1) {
		this.str1 = str1;
	}
	
	public String getStr1() {
		return str1;
	}

	public void setStr1(String str1) {
		this.str1 = str1;
	}
	
	
}
