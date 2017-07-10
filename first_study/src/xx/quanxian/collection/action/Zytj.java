/*
 *@(#)xx.collection.bean
 *@zytj.java.java  
 *@创建时间:2011-8-22下午04:38:39
 *@作者：xupengfei
 *@Copyright 2009-2010 河北北方学院信息科学与工程学院科研所 
 */

package xx.quanxian.collection.action;

import java.util.ArrayList;
import java.util.List;

/**
 * @zytj Zytj
 * @author  徐鹏飞
 * @version 2011-8-22下午04:38:39
 * @{实现资源统计的一个固化类} 
 */

public class Zytj {
	private String cname;
	private String pd_sys;
	private String tk_sys;
	private String xz_sys;
	private String dmt_sys;
	private String czt_sys;
	private String zysc_sys;
	private List<Zytjchild> children = new ArrayList<Zytjchild>();
	private String state;
	
	
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public List<Zytjchild> getChildren() {
		return children;
	}
	public void setChildren(List<Zytjchild> children) {
		this.children = children;
	}
	public String getCname() {
		return cname;
	}
	public void setCname(String cname) {
		this.cname = cname;
	}
	public String getPd_sys() {
		return pd_sys;
	}
	public void setPd_sys(String pdSys) {
		pd_sys = pdSys;
	}
	public String getTk_sys() {
		return tk_sys;
	}
	public void setTk_sys(String tkSys) {
		tk_sys = tkSys;
	}
	public String getXz_sys() {
		return xz_sys;
	}
	public void setXz_sys(String xzSys) {
		xz_sys = xzSys;
	}
	public String getDmt_sys() {
		return dmt_sys;
	}
	public void setDmt_sys(String dmtSys) {
		dmt_sys = dmtSys;
	}
	public String getCzt_sys() {
		return czt_sys;
	}
	public void setCzt_sys(String cztSys) {
		czt_sys = cztSys;
	}
	public String getZysc_sys() {
		return zysc_sys;
	}
	public void setZysc_sys(String zyscSys) {
		zysc_sys = zyscSys;
	}
	
}
