
	/**
	 * 文件名：jxjh_z.java
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



	/**
 * 此类描述的是：
 * @author: tlq
 * @version: 2011-8-8 下午02:57:36 
 */
@Entity
public class Jxjh_z {

	private Integer id;
	private String tname;
	private String kname;
	private String jhmc;
	private String xq;
	private String label;//该字段用来返回处理后的课次
	private int xsh;
	private int zbh;
	
	
	
	public int getZbh() {
		return zbh;
	}
	
	public void setZbh(int zbh) {
		this.zbh = zbh;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getTname() {
		return tname;
	}
	public void setTname(String tname) {
		this.tname = tname;
	}
	public String getKname() {
		return kname;
	}
	public void setKname(String kname) {
		this.kname = kname;
	}
	public String getJhmc() {
		return jhmc;
	}
	public void setJhmc(String jhmc) {
		this.jhmc = jhmc;
	}
	public String getXq() {
		return xq;
	}
	public void setXq(String xq) {
		this.xq = xq;
	}
	
	/**
	 * @return the label
	 */
	public String getLabel() {
		return label;
	}

	/**
	 * @param label the label to set
	 */
	public void setLabel(String label) {
		this.label = label;
	}

	public int getXsh() {
		return xsh;
	}
	public void setXsh(int xsh) {
		this.xsh = xsh;
	}
	
	
}
