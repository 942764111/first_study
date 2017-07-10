
	/**
	 * 文件名：Jxnr_z.java
	 *
	 * 版本信息：
	 * 日期：2011-8-8
	 * 作者：tlq
	 * Copyright 河北北方学院信息科学与工程学院科研所 Corporation 2011 
	 * 版权所有
	 *
	 */
	
package xx.xuexi.action;

import javax.persistence.Entity;

import xx.collection.bean.Jxjh;


	/**
 * 此类描述的是：
 * @author: tlq
 * @version: 2011-8-8 上午09:36:14 
 */
@Entity
public class Jxnr_z {

	private Integer id;
	private String jhmc;
	private Integer xsh;
	private String nr;
	private String wjms;
	private String fpath;
	private Integer total;
	private String zlmc;
	private int zlid;
	
	
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

	public String getFpath() {
		return fpath;
	}
	
	public void setFpath(String fpath) {
		this.fpath = fpath;
	}
	public String getWjms() {
		return wjms;
	}
	public void setWjms(String wjms) {
		this.wjms = wjms;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}


	public String getJhmc() {
		return jhmc;
	}
	public void setJhmc(String jhmc) {
		this.jhmc = jhmc;
	}
	public Integer getXsh() {
		return xsh;
	}
	public void setXsh(Integer xsh) {
		this.xsh = xsh;
	}
	public String getNr() {
		return nr;
	}
	public void setNr(String nr) {
		this.nr = nr;
	}

	/**
	 * @return the total
	 */
	public Integer getTotal() {
		return total;
	}

	/**
	 * @param total the total to set
	 */
	public void setTotal(Integer total) {
		this.total = total;
	}

	/**
	 * @return the zlmc
	 */
	public String getZlmc() {
		return zlmc;
	}

	/**
	 * @param zlmc the zlmc to set
	 */
	public void setZlmc(String zlmc) {
		this.zlmc = zlmc;
	}
	
}
