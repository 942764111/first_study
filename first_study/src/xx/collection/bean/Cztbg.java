/*
 *@(#)xx.collection.bean
 *@Cztbg.java.java  
 *@创建时间:2011-8-16上午08:37:24
 *@作者：Administrator
 *@Copyright 2009-2010 河北北方学院信息科学与工程学院科研所 
 */

package xx.collection.bean;

import java.util.ArrayList;
import java.util.List;



/**
 * @Cztbg <code>{类名称}</code>
 * @author  {作者}
 * @version {版本,常用时间代替}
 * @{功能描述:} 
 */

public class Cztbg {
	private int id;
	private String jytg;
	private String sjdf;
	private String ydf;
	private String zsd;
	private List<Children> children=new ArrayList<Children>();
	private String state;

	
	
	

	public String getJytg() {
		return jytg;
	}
	public void setJytg(String jytg) {
		this.jytg = jytg;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public int getId() {
		return id;
	}
	public List<Children> getChildren() {
		return children;
	}
	public void setChildren(List<Children> children) {
		this.children = children;
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
	

}
