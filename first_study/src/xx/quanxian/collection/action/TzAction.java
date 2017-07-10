/*
 *@(#)xx.kgt.action
 *@JspAction.java.java  
 *@创建时间:2011-10-26下午08:37:07
 *@作者：Administrator
 *@Copyright 2009-2010 河北北方学院信息科学与工程学院科研所 
 */

package xx.quanxian.collection.action;


import javax.annotation.Resource;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import xx.quanxian.service.BaseService;

import com.googlecode.jsonplugin.annotations.JSON;
import com.opensymphony.xwork2.ActionSupport;

/**
 * @JspAction <code>{类名称}</code>
 * @author  {张晓莉}
 * @version {版本,常用时间代替}
 * @{功能描述:存放跳转页面的action} 
 */

@Controller
@Scope("prototype")
@ParentPackage("default")
@Namespace("")
public class TzAction extends ActionSupport{
	
	
	@Resource(name="baseService")
	private BaseService baseservice;
	
	private String userId;
	
	@JSON(serialize=false)
	public BaseService getBaseservice() {
		return baseservice;
	}
	public void setBaseservice(BaseService baseservice) {
		this.baseservice = baseservice;
	}
	/**
	 * listSclt <code>listSclt</code>
	 * @author  徐鹏飞
	 * @version 2011-8-21上午11:25:21
	 * @实现下拉框功能 
	 */
	@Action(value="/listSclt",results={@Result(name="success",location="/page/collection/sourcecollection.jsp")})
	public String listSclt(){
		return SUCCESS;
	}
	

	/**
	 * 用来跳转页面的
	 */
	@Action(value="/jxjhscym",results={@Result(name="success",location="/page/collection/jxjhshoucang.jsp")})
	public String jxjhscym(){
		return SUCCESS;
	}
	/**
	 * @Statistics <code>{list_zycx}</code>
	 * @author  {徐鹏飞}
	 * @version {2011-9-20下午02:43:27}
	 * @{实现跳转功能} 
	 */
	@Action(value="/zysearch",results={@Result(name="success",location="/page/resource/zysearch.jsp")})
	public String zysearch(){
		return SUCCESS;
	}
	/**
	 * @Statistics <code>{listy}</code>
	 * @author  {徐鹏飞}
	 * @version {2011-8-22下午03:12:47}
	 * @{实现页面的跳转} 
	 */
	@Action(value="/listy",results={@Result(name="success",location="/page/collection/listyhzdy.jsp")})
	public String listy(){
		return SUCCESS;
	}
	/**
	 * Rsjop <code>{Rsjop}</code>
	 * @author  {徐鹏飞}
	 * @version {2011-8-22下午03:12:47}
	 * @{statistics页面跳转功能} 
	 */
	@Action(value="/Rsjop",results={@Result(name="success",location="/page/resource/statistics.jsp")})
	public String Rsjop(){
		return SUCCESS;
	}
}
