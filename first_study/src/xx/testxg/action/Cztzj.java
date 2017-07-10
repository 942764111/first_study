/*
 *@(#)xx.testxg.action
 *@Cztzj.java.java  
 *@创建时间:2011-11-3下午07:38:33
 *@作者：zxl
 *@Copyright 2009-2010 河北北方学院信息科学与工程学院科研所 
 */

package xx.testxg.action;

import java.util.List;

import javax.persistence.Entity;

/**
 * @Cztzj <code>{类名称}</code>
 * @author  {zxl}
 * @version {版本,常用时间代替}
 * @{功能描述:用于存放从数据库取出的操作题} 
 */
@Entity
public class Cztzj{
	public String dttg;
	public List<String> xttg;
	public int dtth;
	public List<Integer> xtth;
	
	public Cztzj(){
		
	}
	
	public Cztzj(String dttg,List<String> xttg,int dtth,List<Integer> xtth){
			this.dttg=dttg;
			this.dtth=dtth;
			this.xttg=xttg;
			this.xtth=xtth;
		}

	public String getDttg() {
		return dttg;
	}

	public void setDttg(String dttg) {
		this.dttg = dttg;
	}

	public int getDtth() {
		return dtth;
	}

	public void setDtth(int dtth) {
		this.dtth = dtth;
	}

	public List<String> getXttg() {
		return xttg;
	}

	public void setXttg(List<String> xttg) {
		this.xttg = xttg;
	}

	public List<Integer> getXtth() {
		return xtth;
	}

	public void setXtth(List<Integer> xtth) {
		this.xtth = xtth;
	}


	
	
}
