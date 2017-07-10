/*
 *@(#)xx.tables_zxl.action
 *@Zxx.java.java  
 *@创建时间:2011-6-2下午09:58:50
 *@作者：Administrator
 *@Copyright 2009-2010 河北北方学院信息科学与工程学院科研所 
 */

package xx.tables_zxl.action;

import javax.persistence.Entity;

/**
 * @Zxx <code>{类名称}</code>
 * @author  {zxl}
 * @version {版本,常用时间代替}
 * @{...功能描述} 
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
