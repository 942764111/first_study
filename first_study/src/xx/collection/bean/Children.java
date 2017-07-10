/*
 *@(#)xx.collection.bean
 *@Children.java.java  
 *@创建时间:2011-8-16上午08:45:01
 *@作者：Administrator
 *@Copyright 2009-2010 河北北方学院信息科学与工程学院科研所 
 */

package xx.collection.bean;

/**
 * @Children <code>{类名称}</code>
 * @author  {作者}
 * @version {版本,常用时间代替}
 * @{功能描述:} 
 */

public class Children {
	private int id;
	private int jytg;
	private String sjdf;
	private String  ydf;
	private String zsd;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the sjdf
	 */
	public String getSjdf() {
		return sjdf;
	}
	/**
	 * @param sjdf the sjdf to set
	 */
	public void setSjdf(String sjdf) {
		this.sjdf = sjdf;
	}
	/**
	 * @return the ydf
	 */
	public String getYdf() {
		return ydf;
	}
	/**
	 * @param ydf the ydf to set
	 */
	public void setYdf(String ydf) {
		this.ydf = ydf;
	}
	public String getZsd() {
		return zsd;
	}
	public void setZsd(String zsd) {
		this.zsd = zsd;
	}
	public int getJytg() {
		return jytg;
	}

	public void setJytg(int jytg) {
		this.jytg = jytg;
	}

}
