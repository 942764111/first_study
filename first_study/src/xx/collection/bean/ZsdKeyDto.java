/*
 *@(#)xx.collection.bean
 *@ZsdKeyDto.java.java  
 *@创建时间:2011-9-26下午10:17:23
 *@作者：Administrator
 *@Copyright 2009-2010 河北北方学院信息科学与工程学院科研所 
 */

package xx.collection.bean;

/**
 * @ZsdKeyDto <code>{类名称}</code>
 * @author  {作者}
 * @version {版本,常用时间代替}
 * @{功能描述:} 
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
