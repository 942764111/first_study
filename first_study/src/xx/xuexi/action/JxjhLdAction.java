
	/**
	 * 文件名：JxjhAction.java
	 *
	 * 版本信息：
	 * 日期：2011-8-5
	 * 作者：tlq
	 * Copyright 河北北方学院信息科学与工程学院科研所 Corporation 2011 
	 * 版权所有
	 *
	 */
	
package xx.xuexi.action;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import xx.adminservice.JxjhService;
import xx.collection.bean.CourseChapter;

import xx.collection.bean.Jxjh;


import xx.collection.bean.Scwj;
import xx.collection.bean.Sjnr;
import xx.collection.bean.Teacher;
import xx.collection.bean.Userinfo;
import xx.collection.bean.Wjlx;
import xx.collection.bean.Xsdyjl;
import xx.quanxian.service.BaseService;

import com.googlecode.jsonplugin.annotations.JSON;
import com.opensymphony.xwork2.ActionSupport;
	/**
 * 此类描述的是：
 * @author: tlq
 * @version: 2011-8-5 下午04:56:26 
 */
@Controller
@Scope("prototype")
@Namespace("")
@ParentPackage("default-package")
public class JxjhLdAction extends ActionSupport {

	@Resource(name="baseService")
	private BaseService baseService;
	List<String> list=new ArrayList<String>();
	private String kcm;
	private int kch;
	private List<CourseChapter> listcc=new ArrayList<CourseChapter>();
	
	

	/**
	 * @return the kch
	 */
	public int getKch() {
		return kch;
	}


	/**
	 * @param kch the kch to set
	 */
	public void setKch(int kch) {
		this.kch = kch;
	}


	/**
	 * @return the listcc
	 */
	public List<CourseChapter> getListcc() {
		return listcc;
	}


	/**
	 * @param listcc the listcc to set
	 */
	public void setListcc(List<CourseChapter> listcc) {
		this.listcc = listcc;
	}


	/**
	 * @return the kcm
	 */
	public String getKcm() {
		return kcm;
	}


	/**
	 * @param kcm the kcm to set
	 */
	public void setKcm(String kcm) {
		this.kcm = kcm;
	}



	/**
	 * @return the list
	 */
	public List<String> getList() {
		return list;
	}


	/**
	 * @param list the list to set
	 */
	public void setList(List<String> list) {
		this.list = list;
	}


	/**
	 * @{kcmAction.action}
	 * @param {} {为教学计划统计页面提供json数据}
	 * 
	*/
	@Action(value="/kcmAction",results={@Result(name="success",type="json")})
	public String kcmAction(){
		
		String hql="select distinct TCName from CourseChapter where zbh in(select distinct courseChapter.zbh from Jxjh)";
		list=this.baseService.findHql(String.class, hql);
		
		return SUCCESS;
	}
	
	/**
	 * @{xqAction.action}
	 * @param {} {为教学计划统计页面提供json数据}
	 * @throws UnsupportedEncodingException 
	 * 
	*/
	@Action(value="/xqAction",results={@Result(name="success",type="json")})
	public String xqAction() throws UnsupportedEncodingException{
		
		kcm = new String(kcm.getBytes("ISO-8859-1"),"UTF-8");
		System.out.println("kcm:"+kcm);
		String hql="select zbh from CourseChapter where TCName='"+kcm+"'";
		List<Integer> listzbh=this.baseService.findHql(Integer.class, hql);
		
		if(listzbh.get(0)>10){
			String str=listzbh.get(0).toString();
			str=str.substring(0, 4);
			hql="select distinct xq from Jxjh where courseChapter.zbh like '"+str+"%'";
		}else{
			hql="select distinct xq from Jxjh where courseChapter.zbh like '_'";
		}
		List<String> li=this.baseService.findHql(String.class, hql);
		for(String str:li){
			String nian=str.substring(0, 4);
			int nianD=Integer.valueOf(nian)+1;
			String xueqi=str.substring(4);
			String xnxq=nian+"-"+nianD+"学年之第"+xueqi+"学期";
			list.add(xnxq);
		}
		
		return SUCCESS;
	}
	
	/**
	 * @{dyzAction.action}
	 * @param {} {为教学计划统计页面或内容概述页面中“对应章下拉菜单”提供下拉菜单数据数据}
	 * 
	*/
	@Action(value="/dyzAction",results={@Result(name="success",type="json")})
	public String dyzAction(){
		HttpSession hs = ServletActionContext.getRequest().getSession();
		
		String hql;
		if(kcm==null||kcm.equals("")){
			if(kch==0){
				kch=(Integer)hs.getAttribute("kkch");
				 hql="from CourseChapter where TCName in(select TCName from CourseChapter where zbh ='"+kch+"')";
			}else{
				hql="from CourseChapter where TCName in(select TCName from CourseChapter where zbh ='"+kch+"')";
			}
		}else{
			hql="from CourseChapter where TCName ='"+kcm+"'";
		}
		
		
		listcc=this.baseService.findHql(CourseChapter.class, hql);
		
		return SUCCESS;
	}
	
}
