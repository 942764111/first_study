/*
 *@(#)xx.spdh.bean
 *@BflbAction.java.java  
 *@����ʱ��:2011-8-29����09:00:03
 *@���ߣ�������
 *@Copyright 2009-2010 �ӱ�����ѧԺ��Ϣ��ѧ�빤��ѧԺ������ 
 */

package xx.spdh.bean;

import org.hibernate.annotations.Entity;

/**
 * @BflbAction <code>{������}</code>
 * @author  {����}
 * @version {�汾,����ʱ�����}
 * @{��������:} 
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
