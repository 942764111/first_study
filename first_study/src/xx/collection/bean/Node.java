/*
 *@(#)xx.collection.bean
 *@Node.java.java  
 *@����ʱ��:2012-4-11����05:11:49
 *@���ߣ�������
 *@Copyright 2009-2010 �ӱ�����ѧԺ��Ϣ��ѧ�빤��ѧԺ������ 
 */

package xx.collection.bean;

import java.util.List;

/**
 * @Node <code>{������}</code>
 * @author  {������}
 * @version {�汾,����ʱ�����}
 * @{��������:} 
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
