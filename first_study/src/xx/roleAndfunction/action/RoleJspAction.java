/*
 *@(#)xx.roleAndfunction.action
 *@RoleJspAction.java.java  
 *@创建时间:2012-3-4上午10:23:42
 *@作者：hp
 *@Copyright 2009-2010 河北北方学院信息科学与工程学院科研所 
 */

package xx.roleAndfunction.action;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionSupport;

/**
 * @RoleJspAction <code>{类名称}</code>
 * @author  {作者：gq}
 * @version {版本,常用时间代替}
 * @{功能描述:} 
 */
@Controller
@Scope("prototype")
@Namespace("")
@ParentPackage("default")
public class RoleJspAction extends ActionSupport {

	private static final long serialVersionUID = 8592877011905253541L;
	
	/**
	 * @{方法名:listRole.action}
	 * @param {引入参数名} {引入参数说明}
	 * @return {返回参数名} {返回参数说明}
	 * @{方法的功能/动作描述:跳转到角色管理页面}
	 * @exception {说明在某情况下,将发生什么异常}
	 */
	@Action(value="/listRole",results={@Result(name="success",location="/page/role/listrole.jsp")})
	public String listRole() throws Exception{
		return SUCCESS;
	}
	
	
	@Action(value="/mindMap",results={@Result(name="success",location="/page/mindMap/index.html")})
	public String mindMap()throws Exception{
		
		return "success";
	}
	
	@Action(value="/searchCharts",results={@Result(name="success",location="/page/search/searchCharts.html")})
	public String searchCharts()throws Exception{
		
		return "success";
	}


}
