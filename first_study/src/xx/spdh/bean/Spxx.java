/*
 *@(#)xx.spdh.bean
 *@Spxx.java.java  
 *@����ʱ��:2011-9-23����09:00:30
 *@���ߣ�ZYK
 *@Copyright 2009-2010 �ӱ�����ѧԺ��Ϣ��ѧ�빤��ѧԺ������ 
 */

package xx.spdh.bean;

import org.hibernate.annotations.Entity;

/**
 * @Spxx <code>{������}</code>
 * @author  {����}
 * @version {�汾,����ʱ�����}
 * @{��������:} 
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
