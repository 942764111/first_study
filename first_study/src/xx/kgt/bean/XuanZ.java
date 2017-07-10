/*
 *@(#)xx.kgt.bean
 *@XuanZ.java.java  
 *@创建时间:2011-9-25下午04:23:48
 *@作者：张晓莉
 *@Copyright 2009-2010 河北北方学院信息科学与工程学院科研所 
 */

package xx.kgt.bean;

import javax.persistence.Entity;

/**
 * @XuanZ <code>{类名称}</code>
 * @author  {张晓莉}
 * @version {版本,常用时间代替}
 * @{功能描述:} 
 */

@Entity
public class XuanZ{
	private String tg;
	private String xx1;
	private String xx2;
	private String xx3;
	private String xx4;
	private String da;
	private int zsdbh;
	private int ddx;
	private String zhangname;
	private int th;
		
	public XuanZ(){
			
		}
	
	public XuanZ(String tg,String xx1,String xx2,String xx3,String xx4,String da, int zsdbh,int ddx,String zhangname,int th){
		this.tg=tg;
		this.xx1=xx1;
		this.xx2=xx2;
		this.xx3=xx3;
		this.xx4=xx4;
		this.da=da;
		this.zsdbh=zsdbh;
		this.ddx=ddx;
		this.zhangname=zhangname;
		this.th=th;
	}

	public String getTg() {
		return tg;
	}

	public void setTg(String tg) {
		this.tg = tg;
	}

	public String getXx1() {
		return xx1;
	}

	public void setXx1(String xx1) {
		this.xx1 = xx1;
	}

	public String getXx2() {
		return xx2;
	}

	public void setXx2(String xx2) {
		this.xx2 = xx2;
	}

	public String getXx3() {
		return xx3;
	}

	public void setXx3(String xx3) {
		this.xx3 = xx3;
	}

	public String getXx4() {
		return xx4;
	}

	public void setXx4(String xx4) {
		this.xx4 = xx4;
	}

	public String getDa() {
		return da;
	}

	public void setDa(String da) {
		this.da = da;
	}

	
	public int getZsdbh() {
		return zsdbh;
	}

	public void setZsdbh(int zsdbh) {
		this.zsdbh = zsdbh;
	}

	public int getDdx() {
		return ddx;
	}

	public void setDdx(int ddx) {
		this.ddx = ddx;
	}

	/**
	 * @return the zhangname
	 */
	public String getZhangname() {
		return zhangname;
	}

	/**
	 * @param zhangname the zhangname to set
	 */
	public void setZhangname(String zhangname) {
		this.zhangname = zhangname;
	}

	/**
	 * @return the th
	 */
	public int getTh() {
		return th;
	}

	/**
	 * @param th the th to set
	 */
	public void setTh(int th) {
		this.th = th;
	}

	
	
}
