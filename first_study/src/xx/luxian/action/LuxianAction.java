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

import xx.collection.bean.CourseChapter;

import xx.collection.bean.Luxian;

import xx.quanxian.service.BaseService;

import com.opensymphony.xwork2.ActionSupport;

@Controller
@Scope("prototype")
@Namespace("")
@ParentPackage("default-package")
@SuppressWarnings("serial")
public class LuxianAction extends ActionSupport{
	
	@Resource(name="baseService")
	private BaseService baseservice;
	
	private List<Luxian> list=new ArrayList<Luxian>();
	private List<Map<String, Object>> rows2=new ArrayList<Map<String,Object>>();
	private String state;
	private String id;
	
	/**
	 * @return the id
	 */
	


	/**
	 * @return the rows2
	 */
	public List<Map<String, Object>> getRows2() {
		return rows2;
	}


	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}


	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}


	/**
	 * @param rows2 the rows2 to set
	 */
	public void setRows2(List<Map<String, Object>> rows2) {
		this.rows2 = rows2;
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
	public List<Luxian> getList() {
		return list;
	}


	/**
	 * @param list the list to set
	 */
	public void setList(List<Luxian> list) {
		this.list = list;
	}


	@Action(value="/getluxian",results={@Result(name="success",type="json")})
	public String Collect(){
	 
	    String[] keys1={"fangxiang"};
		Object[] values1={"0"};
		list=this.baseservice.find(Luxian.class, "Luxian", keys1, values1);
	
		return SUCCESS;
	}
	@Action(value="/getzhang",results={@Result(name="success",type="json")})
	public String Collect1(){
	 
	    String[] keys1={"fangxiang"};
		Object[] values1={id};
		List<Luxian> list2=this.baseservice.find(Luxian.class, "Luxian", keys1, values1);
	    for (int i = 0; i < list2.size(); i++) {
	    	Map<String, Object> map=new HashMap<String, Object>();
	    	
			Luxian luxian=list2.get(i);
			String kecheng=luxian.getKecheng();
			map.put("kecheng", kecheng);
			
			String[] keys={"TCName"};
			Object[] values={kecheng};
			List<CourseChapter> rows=this.baseservice.find(CourseChapter.class, "CourseChapter", keys, values);
			
			List<Map<String, Object>> list3=new ArrayList<Map<String,Object>>();
			for (int j = 0; j < rows.size(); j++) {
				CourseChapter chapter=rows.get(j);
				Map<String, Object> map9=new HashMap<String, Object>();
				map9.put("zhang", chapter.getCName());
				map9.put("zbh", chapter.getZbh());
				
				list3.add(map9);
			}
			

			map.put("zhang", list3);
			rows2.add(map);
		}
		return SUCCESS;
	}
	
	
}
