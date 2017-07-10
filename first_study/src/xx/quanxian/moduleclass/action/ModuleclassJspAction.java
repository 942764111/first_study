/*
 *@(#)xx.quanxian.moduleclass.action
 *@ModuleclassJspAction.java.java  
 *@创建时间:2012-3-4上午10:03:40
 *@作者：hp
 *@Copyright 2009-2010 河北北方学院信息科学与工程学院科研所 
 */

package xx.quanxian.moduleclass.action;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionSupport;

/**
 * @ModuleclassJspAction <code>{类名称}</code>
 * @author  {作者：gq}
 * @version {版本,常用时间代替}
 * @{功能描述:} 
 */
@Controller
@Scope("prototype")
@Namespace("")
@ParentPackage("default")
public class ModuleclassJspAction extends ActionSupport {

	private static final long serialVersionUID = -8944047682058490289L;

	/**
	 * 
	 * @{方法名:listMclass.action}
	 * @param {引入参数名} {引入参数说明}
	 * @return {返回参数名} {返回参数说明}
	 * @{方法的功能/动作描述:跳转到分类管理模块页面}
	 * @exception {说明在某情况下,将发生什么异常}
	 */
	@Action(value="/listMclass",results={@Result(name="success",location="/page/moduleclass/listMclass.jsp")})
	public String listMclass() throws Exception{
			return SUCCESS;
	
		}

}
