
	/**
	 * �ļ�����JxjhAction.java
	 *
	 * �汾��Ϣ��
	 * ���ڣ�2011-8-5
	 * ���ߣ�tlq
	 * Copyright �ӱ�����ѧԺ��Ϣ��ѧ�빤��ѧԺ������ Corporation 2011 
	 * ��Ȩ����
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
 * �����������ǣ�
 * @author: tlq
 * @version: 2011-8-5 ����04:56:26 
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
	 * @param {���������} {�������˵��}
	 * @return {���ز�����} {���ز���˵��}
	 * @{ת����ʦ�����ѧҳ��}
	 * @exception {˵����ĳ�����,������ʲô�쳣}
	 */
	@SuppressWarnings("unchecked")
	@Action(value="/jxjh_tj",results={@Result(name="success",location="/page/xuexi/jxjh_tj.jsp")})
	public String jxjh_tj(){
		HttpSession hs = ServletActionContext.getRequest().getSession();
        
		hs.setAttribute("kkch", 0);hs.setAttribute("wword", "");
            String[] prop={"jsbh","jsxm"}; String[] keys={"UserId"};Object[] values={(String)hs.getAttribute("uid")};
			List<List<String>> listtea=this.baseService.find(String.class, "Teacher", prop,keys,values);//̫����
			
			
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
	 * @param {���������} {�������˵��}
	 * @return {���ز�����} {���ز���˵��}
	 * @{ת��ѧ������ѧϰҳ��}
	 * @exception {˵����ĳ�����,������ʲô�쳣}
	 */
	@SuppressWarnings("unchecked")
	@Action(value="/jxjh_tj_stu",results={@Result(name="success",location="/page/xuexi/jxjh_tj_stu.jsp")})
	public String jxjh_tj_stu(){
		HttpSession hs = ServletActionContext.getRequest().getSession();
        hs.setAttribute("kkch", 0);hs.setAttribute("wword", "");

	    return SUCCESS;
	
	}
	
}
