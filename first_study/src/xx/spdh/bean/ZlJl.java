/*
 *@(#)xx.spdh.bean
 *@ZlJl.java.java  
 *@创建时间:2011-11-10下午11:57:03
 *@作者：ZYK
 *@Copyright 2009-2010 河北北方学院信息科学与工程学院科研所 
 */

package xx.spdh.bean;

import java.util.Date;

import org.hibernate.annotations.Entity;

/**
 * @ZlJl <code>{类名称}</code>
 * @author  {作者}
 * @version {版本,常用时间代替}
 * @{功能描述:} 
 */
@Entity
public class ZlJl {

	private String userId;
	private Date open_time; 
	private String zlmc;
	private Integer zljl_no;
	private Integer weizhi;
	private Integer jxjh_id;
	private String path;
	private String img;
	private int zlbh;
	
	public ZlJl() {
		
	}
	
	public ZlJl(String userId, Date open_time, String zlmc, Integer zljl_no, Integer weizhi,
			Integer jxjh_id, String path, Integer zlbh,String img) {
		this.userId = userId;
		this.open_time = open_time;
		this.zlmc = zlmc;
		this.zljl_no = zljl_no;
		this.weizhi = weizhi;
		this.jxjh_id = jxjh_id;
		this.path = path;
		this.zlbh = zlbh;
		this.img=img;
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
	 * @return the open_time
	 */
	public Date getOpen_time() {
		return open_time;
	}

	/**
	 * @param open_time the open_time to set
	 */
	public void setOpen_time(Date open_time) {
		this.open_time = open_time;
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

	/**
	 * @return the zljl_no
	 */
	public Integer getZljl_no() {
		return zljl_no;
	}

	/**
	 * @param zljl_no the zljl_no to set
	 */
	public void setZljl_no(Integer zljl_no) {
		this.zljl_no = zljl_no;
	}

	/**
	 * @return the weizhi
	 */
	public Integer getWeizhi() {
		return weizhi;
	}

	/**
	 * @param weizhi the weizhi to set
	 */
	public void setWeizhi(Integer weizhi) {
		this.weizhi = weizhi;
	}

	/**
	 * @return the jxjh_id
	 */
	public Integer getJxjh_id() {
		return jxjh_id;
	}

	/**
	 * @param jxjh_id the jxjh_id to set
	 */
	public void setJxjh_id(Integer jxjh_id) {
		this.jxjh_id = jxjh_id;
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
	 * @return the img
	 */
	public String getImg() {
		return img;
	}

	/**
	 * @param path the path to set
	 */
	public void setImg(String img) {
		this.img = img;
	}
	
	/**
	 * @return the zlbh
	 */
	public int getZlbh() {
		return zlbh;
	}

	/**
	 * @param zlbh the zlbh to set
	 */
	public void setZlbh(int zlbh) {
		this.zlbh = zlbh;
	}
	
}
