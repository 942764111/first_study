/*
 *@(#)xx.tables.action
 *@KcxxActions.java.java  
 *@创建时间:2011-6-2下午05:53:44
 *@作者：zxl
 *@Copyright 2009-2010 河北北方学院信息科学与工程学院科研所 
 */

package com.easysearching.action;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import xx.adminservice.AdminService;
import xx.collection.bean.CourseChapter;
import xx.collection.bean.Jie;
import xx.collection.bean.Jxjh;
import xx.collection.bean.Sjnr;
import xx.quanxian.service.BaseService;

import com.googlecode.jsonplugin.annotations.JSON;
import com.opensymphony.xwork2.ActionSupport;
/**
 * @KcxxActions <code>{KcxxActions}</code>
 * @author  {zxl}
 * @version {版本,常用时间代替}
 * @{实现课程信息表的增删改查} 
 */
@Controller
@Scope("prototype")
@Namespace("")
@ParentPackage("default-package")
public class AdvSearchAction extends ActionSupport {

	@Resource(name="baseService")
	private BaseService baseService;


	private String zhangId;//章ID
	

	public String getZhangId() {
		return zhangId;
	}
	public void setZhangId(String zhangId) {
		this.zhangId = zhangId;
	}
	
	private List<Map<String, String>> ZCList=new ArrayList<Map<String,String>>();

	public List<Map<String, String>> getZCList() {
		return ZCList;
	}
	public void setZCList(List<Map<String, String>> zCList) {
		ZCList = zCList;
	}

	

	@JSON(serialize=false)
	public BaseService getBaseService() {
		return baseService;
	}
	public void setBaseService(BaseService baseService) {
		this.baseService = baseService;
	}

	////	//根据章名称取出节名称
	@Action(value="/getJCName",results={@Result(name="success",type="json")})
	public String getJC(){

		String[] keys=new String[1];
		keys[0]="courseChapter.zbh";		
		Object[] values=new Object[1];
		values[0]=Integer.parseInt(zhangId);	
		List<Jie> j = this.baseService.find(Jie.class, "Jie", keys, values);

		for (int i = 0; i < j.size(); i++) {
			Map<String, String>map=new HashMap<String, String>();
			Jie chapter=j.get(i);
			map.put("id", String.valueOf(chapter.getId().getCId()));
			map.put("jmc", chapter.getZmc());

			ZCList.add(map);

		}
		return SUCCESS;
	}
	


}
