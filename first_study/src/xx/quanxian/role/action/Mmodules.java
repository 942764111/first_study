/*
 *@(#)xx.quanxian.role.action
 *@MkglAction.java.java  
 *@创建时间:2011-5-3下午10:11:53
 *@作者：guoqiang
 *@Copyright 2009-2010 河北北方学院信息科学与工程学院科研所 
 */

package xx.quanxian.role.action;


import java.util.ArrayList;
import java.util.List;

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
 * @ManagerModules <code>{类名称}</code>
 * @author  {gq}
 * @version {版本,常用时间代替}
 * @{模块管理} 
 */

@Controller
@Scope("prototype")
@Namespace("")
@ParentPackage("default")
@SuppressWarnings("serial")
public class Mmodules extends ActionSupport {
	@Resource(name="baseService")
	private BaseService baseService;
	private List<String> feileiname=new ArrayList<String>();
	
	/**
	 * @return the feileiname
	 */
	@JSON(serialize=false)
	public List<String> getFeileiname() {
		return feileiname;
	}

	/**
	 * @param feileiname the feileiname to set
	 */
	public void setFeileiname(List<String> feileiname) {
		this.feileiname = feileiname;
	}

	/**
	 * @{跳转到模块管理页面}
	 * @param {} {引入参数说明}
	 * @return {SUCCESS} {返回参数说明}
	 * @{跳转到listModules.jsp}
	 * @exception {说明在某情况下,将发生什么异常}
	**/
	
	@Action(value="/mModules",results={@Result(name="success",location="/page/role/mkgl.jsp")})
	public String mkgljsp(){
		feileiname=this.baseService.find(String.class,"Moduleclass","mclassname");

		return SUCCESS;
	}
	

}
