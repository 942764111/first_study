/*
 *@(#)xx.roleAndfunction.action
 *@RoleJspAction.java.java  
 *@����ʱ��:2012-3-4����10:23:42
 *@���ߣ�hp
 *@Copyright 2009-2010 �ӱ�����ѧԺ��Ϣ��ѧ�빤��ѧԺ������ 
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
 * @RoleJspAction <code>{������}</code>
 * @author  {���ߣ�gq}
 * @version {�汾,����ʱ�����}
 * @{��������:} 
 */
@Controller
@Scope("prototype")
@Namespace("")
@ParentPackage("default")
public class RoleJspAction extends ActionSupport {

	private static final long serialVersionUID = 8592877011905253541L;
	
	/**
	 * @{������:listRole.action}
	 * @param {���������} {�������˵��}
	 * @return {���ز�����} {���ز���˵��}
	 * @{�����Ĺ���/��������:��ת����ɫ����ҳ��}
	 * @exception {˵����ĳ�����,������ʲô�쳣}
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
