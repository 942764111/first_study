/*
 *@(#)xx.collection.bean
 *@Cxjg.java.java  
 *@创建时间:2011-9-20下午02:47:44
 *@作者：xupengfei
 *@Copyright 2009-2010 河北北方学院信息科学与工程学院科研所 
 */

package xx.collection.bean;

/**
 * @Cxjg <code>{Cxjg}</code>
 * @author  {徐鹏飞}
 * @version {2011-9-20下午02:47:44}
 * @{资源查询结果返回的中间类} 
 */

public class Cxjg {
	private int id;
	private String lx;
	private int no;
	private String tg;
	private int _parentId;
	private String state;
	private String iconCls;
	
	public String getLx() {
		return lx;
	}
	public void setLx(String lx) {
		this.lx = lx;
	}
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public String getTg() {
		return tg;
	}
	public void setTg(String tg) {
		this.tg = tg;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int get_parentId() {
		return _parentId;
	}
	public void set_parentId(int id) {
		_parentId = id;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getIconCls() {
		return iconCls;
	}
	public void setIconCls(String iconCls) {
		this.iconCls = iconCls;
	}
	
	
}
