/*
 *@(#)xx.xuexi.action
 *@Xgfx.java.java  
 *@创建时间:2011-8-5上午11:15:59
 *@作者：hp
 *@Copyright 2009-2010 河北北方学院信息科学与工程学院科研所 
 */

package xx.xuexi.action;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionSupport;

/**
 * @Xgfx <code>{类名称}</code>
 * @author  {gq}
 * @version {版本,常用时间代替}
 * @{学习效果分析} 
 */
@Controller
@Scope("prototype")
@Namespace("")
@ParentPackage("default")
public class XgfxAction extends ActionSupport {

	/**
	 * @{跳转到教师分析内容页面/page/xgfx/xgfx.jsp}
	 * @param {} {引入参数说明}
	 * @return {SUCCESS} {返回参数说明}
	 * @{跳转到xgfx.jsp}
	 * @exception {说明在某情况下,将发生什么异常}
	**/
	//@Action(value="xgfx",results={@Result(name="success",location="/page/xgfx/xgfx.jsp")})
	@Action(value="/xxggffxx",results={@Result(name="root",location="/page/xgfx/xgfx.jsp")})
	public String xgfx(){
		
		return "root";
	}
	/**
	 * @{跳转到学生分析内容页面/page/xgfx/stuSj.jsp}
	 * @param {} {引入参数说明}
	 * @return {SUCCESS} {返回参数说明}
	 * @{跳转到stuSj.jsp}
	 * @exception {说明在某情况下,将发生什么异常}
	**/
	@Action(value="/stuSj",results={@Result(name="success",location="/page/xgfx/stuSj.jsp")})
	public String stuSj(){
		
		return SUCCESS;
	}
	
}
