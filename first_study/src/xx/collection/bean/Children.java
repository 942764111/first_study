/*
 *@(#)xx.collection.bean
 *@Children.java.java  
 *@����ʱ��:2011-8-16����08:45:01
 *@���ߣ�Administrator
 *@Copyright 2009-2010 �ӱ�����ѧԺ��Ϣ��ѧ�빤��ѧԺ������ 
 */

package xx.collection.bean;

/**
 * @Children <code>{������}</code>
 * @author  {����}
 * @version {�汾,����ʱ�����}
 * @{��������:} 
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
