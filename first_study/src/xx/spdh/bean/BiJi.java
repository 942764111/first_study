/*
 *@(#)xx.spdh.bean
 *@BiJi.java.java  
 *@����ʱ��:2011-11-12����02:33:38
 *@���ߣ�ZYK
 *@Copyright 2009-2010 �ӱ�����ѧԺ��Ϣ��ѧ�빤��ѧԺ������ 
 */

package xx.spdh.bean;

import javax.persistence.Entity;

/**
 * @BiJi <code>{������}</code>
 * @author  {����}
 * @version {�汾,����ʱ�����}
 * @{��������:} 
 */
@Entity
public class BiJi {

	private String userId;
	private Integer xxsxh;
	private String tmnr;
	private String weizhi;
	private String path;
	private String classno;
	private Integer num;
	private Integer tmbh;
	
	
	public BiJi() {
		
	}
	
	public BiJi(String userId) {
		this.userId = userId;
	}

	/**
	 * @return the userId
	 */
	public String getUserId() {
		return userId;
	}

	/**
	 * @param userId the userId to set
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}

	/**
	 * @return the xxsxh
	 */
	public Integer getXxsxh() {
		return xxsxh;
	}

	/**
	 * @param xxsxh the xxsxh to set
	 */
	public void setXxsxh(Integer xxsxh) {
		this.xxsxh = xxsxh;
	}

	/**
	 * @return the tmnr
	 */
	public String getTmnr() {
		return tmnr;
	}

	/**
	 * @param tmnr the tmnr to set
	 */
	public void setTmnr(String tmnr) {
		this.tmnr = tmnr;
	}

	/**
	 * @return the weizhi
	 */
	public String getWeizhi() {
		return weizhi;
	}

	/**
	 * @param weizhi the weizhi to set
	 */
	public void setWeizhi(String weizhi) {
		this.weizhi = weizhi;
	}

	/**
	 * @return the path
	 */
	public String getPath() {
		return path;
	}

	/**
	 * @param path the path to set
	 */
	public void setPath(String path) {
		this.path = path;
	}

	/**
	 * @return the classno
	 */
	public String getClassno() {
		return classno;
	}

	/**
	 * @param classno the classno to set
	 */
	public void setClassno(String classno) {
		this.classno = classno;
	}

	/**
	 * @return the num
	 */
	public Integer getNum() {
		return num;
	}

	/**
	 * @param num the num to set
	 */
	public void setNum(Integer num) {
		this.num = num;
	}

	/**
	 * @return the tmbh
	 */
	public Integer getTmbh() {
		return tmbh;
	}

	/**
	 * @param tmbh the tmbh to set
	 */
	public void setTmbh(Integer tmbh) {
		this.tmbh = tmbh;
	}

	
	
}
