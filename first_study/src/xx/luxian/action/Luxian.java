/*
 *@(#)xx.spdh.action
 *@UploadAction.java.java  
 *@����ʱ��:2011-11-12����08:43:29
 *@���ߣ�ZYK
 *@Copyright 2009-2010 �ӱ�����ѧԺ��Ϣ��ѧ�빤��ѧԺ������ 
 */

package xx.luxian.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import java.util.List;

import javax.annotation.Resource;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import xx.collection.bean.Collect;
import xx.collection.bean.CourseChapter;
import xx.collection.bean.Jie;
import xx.collection.bean.Scwj;
import xx.collection.bean.Zsd2;

import xx.quanxian.service.BaseService;

import com.opensymphony.xwork2.ActionSupport;

@Controller
@Scope("prototype")
@Namespace("")
@ParentPackage("default-package")
@SuppressWarnings("serial")
public class Luxian extends ActionSupport{
	
	@Resource(name="baseService")
	private BaseService baseservice;
	private String kcmc;
	private String zcid;
	
	private String state;
	private List<Map<String, Object>>list=new ArrayList<Map<String,Object>>();
	
	private List<Map<String, Object>> rows=new ArrayList<Map<String,Object>>();
	
	public List<Map<String, Object>> getRows() {
		return rows;
	}


	/**
	 * @param rows the rows to set
	 */
	public void setRows(List<Map<String, Object>> rows) {
		this.rows = rows;
	}


	/**
	 * @return the kcmc
	 */
	public String getKcmc() {
		return kcmc;
	}

	
	/**
	 * @param kcmc the kcmc to set
	 */
	public void setKcmc(String kcmc) {
		this.kcmc = kcmc;
	}

	/**
	 * @return the zcid
	 */
	public String getZcid() {
		return zcid;
	}

	/**
	 * @return the state
	 */
	public String getState() {
		return state;
	}


	/**
	 * @param state the state to set
	 */
	public void setState(String state) {
		this.state = state;
	}


	/**
	 * @return the list
	 */
	public List<Map<String, Object>> getList() {
		return list;
	}


	/**
	 * @param list the list to set
	 */
	public void setList(List<Map<String, Object>> list) {
		this.list = list;
	}


	/**
	 * @param zcid the zcid to set
	 */
	public void setZcid(String zcid) {
		this.zcid = zcid;
	}

	@Action(value="/getzsd",results={@Result(name="success",type="json")})
	public String getCollect(){
		
		String[] keys={"jie.id.zbh"};
		Object[] values={Integer.parseInt(zcid)};
		List<Zsd2> list2=this.baseservice.find(Zsd2.class, "Zsd2", keys, values);
		
		if (list2==null||list2.size()<=0) {
			state="1";
			return SUCCESS;
		};
		state="0";
		for (int i = 0; i < list2.size(); i++) {
			Map<String, Object> map=new HashMap<String, Object>();
			Zsd2 zsd2=list2.get(i);
		
			map.put("zsdid", zsd2.getZsdid());
			map.put("zsdmc", zsd2.getZsdmc());
			list.add(map);
		}
		return SUCCESS;
	}
	
	
	
}
