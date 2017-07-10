
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


import xx.collection.bean.JxjhYck;

import xx.collection.bean.Teacher;

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
@ParentPackage("default")
public class JxjhJspAction extends ActionSupport {
    
	@Resource(name="baseService")
	private BaseService baseService;
	private String tname;
	private Map kcnamelist=new HashMap();
	
	
	/**
	 * @return the kcnamelist
	 */
	public Map getKcnamelist() {
		return kcnamelist;
	}
	/**
	 * @param kcnamelist the kcnamelist to set
	 */
	public void setKcnamelist(Map kcnamelist) {
		this.kcnamelist = kcnamelist;
	}
	
	
	@JSON(serialize=false)
	public String getTname() {
		return tname;
	}
	public void setTname(String tname) {
		this.tname = tname;
	}
	
	/**
	 * 
	 * @{jxjh_tj.action}
	 * @param {引入参数名} {引入参数说明}
	 * @return {返回参数名} {返回参数说明}
	 * @{转到老师管理教学页面}
	 * @exception {说明在某情况下,将发生什么异常}
	 */
	@SuppressWarnings("unchecked")
	@Action(value="/jxjh_tj",results={@Result(name="success",location="/page/xuexi/jxjh_tj.jsp")})
	public String jxjh_tj(){
		HttpSession hs = ServletActionContext.getRequest().getSession();
        
		hs.setAttribute("kkch", 0);hs.setAttribute("wword", "");
            String[] prop={"jsbh","jsxm"}; String[] keys={"UserId"};Object[] values={(String)hs.getAttribute("uid")};
			List<List<String>> listtea=this.baseService.find(String.class, "Teacher", prop,keys,values);//太棒了
			
			
				hs.setAttribute("jsbh", listtea.get(0).get(0));
				tname=listtea.get(0).get(1);
			
			String hql="select distinct TCName from CourseChapter";
			List<String> liststr=this.baseService.findHql(String.class, hql);
			for(String str:liststr){
				kcnamelist.put(str, str);
			}
			return SUCCESS;

		
	}
	/**
	 * 
	 * @{jxjh_tj_stu.action}
	 * @param {引入参数名} {引入参数说明}
	 * @return {返回参数名} {返回参数说明}
	 * @{转到学生随堂学习页面}
	 * @exception {说明在某情况下,将发生什么异常}
	 */
	@SuppressWarnings("unchecked")
	@Action(value="/jxjh_tj_stu",results={@Result(name="success",location="/page/xuexi/jxjh_tj_stu.jsp")})
	public String jxjh_tj_stu(){
		HttpSession hs = ServletActionContext.getRequest().getSession();
        hs.setAttribute("kkch", 0);hs.setAttribute("wword", "");

	    return SUCCESS;
	
	}
	
}
