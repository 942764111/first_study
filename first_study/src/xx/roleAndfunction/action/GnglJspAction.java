/*
 *@(#)xx.roleAndfunction.action
 *@GnglJspAction.java.java  
 *@创建时间:2012-3-4上午10:10:49
 *@作者：hp
 *@Copyright 2009-2010 河北北方学院信息科学与工程学院科研所 
 */

package xx.roleAndfunction.action;

import java.util.List;

import javax.annotation.Resource;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import xx.collection.bean.Modules;
import xx.quanxian.service.BaseService;

import com.googlecode.jsonplugin.annotations.JSON;
import com.opensymphony.xwork2.ActionSupport;

/**
 * @GnglJspAction <code>{类名称}</code>
 * @author  {作者:gq}
 * @version {版本,常用时间代替}
 * @{功能描述:} 
 */
@Controller
@Scope("prototype")
@Namespace("")
@ParentPackage("default")
public class GnglJspAction extends ActionSupport {

	private static final long serialVersionUID = 3484775152227425166L;
	@Resource(name="baseService")
	private BaseService baseService;
	private List<Modules> modulelist;
	

	@JSON(serialize=false)
	public List<Modules> getModulelist() {
		return modulelist;
	}
	
	public void setModulelist(List<Modules> modulelist) {
		this.modulelist = modulelist;
	}
	/**
	 * 
	 * @{方法名:listfunc.action}
	 * @param {引入参数名} {引入参数说明}
	 * @return {返回参数名:modulelist} {返回参数说明}
	 * @{方法的功能/动作描述:跳转到功能管理页面}
	 * @exception {说明在某情况下,将发生什么异常}
	 */

	@Action(value="/listfunc",results={@Result(name="success",location="/page/role/gngl.jsp")})
	public String listFunc(){
		this.modulelist = this.baseService.find(Modules.class);
		return SUCCESS;
	}

}
