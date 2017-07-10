/*
 *@(#)xx.collection.bean
 *@Node.java.java  
 *@创建时间:2012-4-11下午05:11:49
 *@作者：朱永科
 *@Copyright 2009-2010 河北北方学院信息科学与工程学院科研所 
 */

package xx.collection.bean;

import java.util.List;

/**
 * @Node <code>{类名称}</code>
 * @author  {朱永科}
 * @version {版本,常用时间代替}
 * @{功能描述:} 
 */

public class Node {
	private String id;
	private String text;
	private String iconCls;
	private String state;
	private List<Nodec> children;
	
	
	

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getIconCls() {
		return iconCls;
	}
	public void setIconCls(String iconCls) {
		this.iconCls = iconCls;
	}
	public List<Nodec> getChildren() {
		return children;
	}
	public void setChildren(List<Nodec> children) {
		this.children = children;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}

}
