
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
import java.util.List;

import javax.annotation.Resource;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.interceptor.ServletRequestAware;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import xx.adminservice.JxjhService;
import xx.collection.bean.CourseChapter;

import xx.collection.bean.Jxjh;


import xx.collection.bean.Scwj;
import xx.collection.bean.Teacher;
import xx.collection.bean.Userinfo;
import xx.collection.bean.Wjlx;
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
public class JxjhTjAction extends ActionSupport  {

	@Resource(name="baseService")
	private BaseService baseService;
	@Resource(name="jxjhservice")
	private JxjhService jxjhService;
	private String kcm;
	private String xq;

	/**
	 * @return the kcm
	 */
	@JSON(serialize=false)
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
	 * @return the xq
	 */
	@JSON(serialize=false)
	public String getXq() {
		return xq;
	}
   /**
	 * @param xq the xq to set
	 */
	public void setXq(String xq) {
		this.xq = xq;
	}


   //教学计划统计页面的查询，之后会到jxjh_tj_json.action中
	@Action(value="/queryJxjhTj",results={@Result(name="root",type="json")})
	 public String queryJxjhTj(){

	  String word = xq;
	  String hql="select distinct zbh from CourseChapter where TCName ='"+kcm+"'";
	  int kch=this.baseService.findSql(Integer.class, hql, 1, 1).get(0);

	  word=word.substring(0, 4)+word.substring(13, 14);

	  HttpSession hs = ServletActionContext.getRequest().getSession();
	  hs.setAttribute("kkch", kch);
	  hs.setAttribute("wword", word);

	  return "root";
	}

	
	
	
}
