/*
 *@(#)xx.testxg.action
 *@Cztzj.java.java  
 *@����ʱ��:2011-11-3����07:38:33
 *@���ߣ�zxl
 *@Copyright 2009-2010 �ӱ�����ѧԺ��Ϣ��ѧ�빤��ѧԺ������ 
 */

package xx.testxg.action;

import java.util.List;

import javax.persistence.Entity;

/**
 * @Cztzj <code>{������}</code>
 * @author  {zxl}
 * @version {�汾,����ʱ�����}
 * @{��������:���ڴ�Ŵ����ݿ�ȡ���Ĳ�����} 
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
