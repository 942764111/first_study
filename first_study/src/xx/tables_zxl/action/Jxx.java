/*
 *@(#)xx.tables_zxl.action
 *@Zxx.java.java  
 *@����ʱ��:2011-6-2����09:58:50
 *@���ߣ�Administrator
 *@Copyright 2009-2010 �ӱ�����ѧԺ��Ϣ��ѧ�빤��ѧԺ������ 
 */

package xx.tables_zxl.action;

import javax.persistence.Entity;

/**
 * @Zxx <code>{������}</code>
 * @author  {zxl}
 * @version {�汾,����ʱ�����}
 * @{...��������} 
 */

@Entity
public class Jxx {
	private int jbh;
	private String zhangm;
	private String jmc;
	private String jms;

	
	
	public Jxx(){
		
	}
	
	
	public Jxx(int jbh,String zhangm,String jmc,String jms){
		this.jbh=jbh;
		this.zhangm=zhangm;
		this.jmc=jmc;
		this.jms=jms;

	}


	public int getJbh() {
		return jbh;
	}


	public void setJbh(int jbh) {
		this.jbh = jbh;
	}


	public String getZhangm() {
		return zhangm;
	}


	public void setZhangm(String zhangm) {
		this.zhangm = zhangm;
	}


	public String getJmc() {
		return jmc;
	}


	public void setJmc(String jmc) {
		this.jmc = jmc;
	}


	public String getJms() {
		return jms;
	}


	public void setJms(String jms) {
		this.jms = jms;
	}


}
