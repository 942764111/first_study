/*
 *@(#)xx.bean.bean.util
 *@ZC.java.java  
 *@创建时间:2016-4-11下午3:32:25
 *@作者：朱永科
 *@Copyright 2009-2010 河北北方学院信息科学与工程学院科研所 
 */

package xx.bean.bean.util;

/**
 * @ZC <code>{类名称}</code>
 * @author  {朱永科}
 * @version {版本,常用时间代替}
 * @{功能描述:} 
 */

public class ZC {

	private String c_name;
	private String zbh;
	public String getC_name() {
		return c_name;
	}
	public void setC_name(String c_name) {
		this.c_name = c_name;
	}
	public String getZbh() {
		return zbh;
	}
	public void setZbh(String zbh) {
		this.zbh = zbh;
	}
	@Override
	public String toString() {
		return "ZC [c_name=" + c_name + ", zbh=" + zbh + "]";
	}
	
	
}
