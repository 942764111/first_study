/*
 *@(#)xx.collection.bean
 *@ZsdKeyDto.java.java  
 *@����ʱ��:2011-9-26����10:17:23
 *@���ߣ�Administrator
 *@Copyright 2009-2010 �ӱ�����ѧԺ��Ϣ��ѧ�빤��ѧԺ������ 
 */

package xx.collection.bean;

/**
 * @ZsdKeyDto <code>{������}</code>
 * @author  {����}
 * @version {�汾,����ʱ�����}
 * @{��������:} 
 */

public class ZsdKeyDto {
	private int id;
	private String value;
	
	private Boolean selected;
	
	

	public Boolean getSelected() {
		return selected;
	}
	public void setSelected(Boolean selected) {
		this.selected = selected;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	

}
