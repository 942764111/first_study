/*
 *@(#)xx.kgt.bean
 *@Da.java.java  
 *@创建时间:2011-10-14上午10:10:24
 *@作者：Administrator
 *@Copyright 2009-2010 河北北方学院信息科学与工程学院科研所 
 */

package xx.kgt.bean;

import javax.persistence.Entity;

/**
 * @Da <code>{类名称}</code>
 * @author  {张晓莉}
 * @version {版本,常用时间代替}
 * @{功能描述:} 
 */
@Entity
public class Da {

	private String tg;
	private String xzanswer;
	private String xzda;
	private int pdda;
	private int pdanswer;
	private int zsdbh;
	private String zsdmc;
	
	public Da(){
		
	}
	
	public Da(String tg,String xzanswer,String xzda,int pdda,int pdanswer,int zsdbh,String zsdmc){
			this.pdda=pdda;
			this.xzda=xzda;
			this.tg=tg;
			this.zsdbh=zsdbh;
			this.zsdmc=zsdmc;
			this.pdanswer=pdanswer;
			this.xzanswer=xzanswer;
		}

	public String getXzanswer() {
		return xzanswer;
	}

	public void setXzanswer(String xzanswer) {
		this.xzanswer = xzanswer;
	}

	public int getPdanswer() {
		return pdanswer;
	}

	public void setPdanswer(int pdanswer) {
		this.pdanswer = pdanswer;
	}

	public String getTg() {
		return tg;
	}

	public void setTg(String tg) {
		this.tg = tg;
	}

	public String getXzda() {
		return xzda;
	}

	public void setXzda(String xzda) {
		this.xzda = xzda;
	}

	public int getPdda() {
		return pdda;
	}

	public void setPdda(int pdda) {
		this.pdda = pdda;
	}

	public int getZsdbh() {
		return zsdbh;
	}

	public void setZsdbh(int zsdbh) {
		this.zsdbh = zsdbh;
	}

	public String getZsdmc() {
		return zsdmc;
	}

	public void setZsdmc(String zsdmc) {
		this.zsdmc = zsdmc;
	}
	
	
}
