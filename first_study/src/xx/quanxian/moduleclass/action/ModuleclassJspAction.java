/*
 *@(#)xx.quanxian.moduleclass.action
 *@ModuleclassJspAction.java.java  
 *@����ʱ��:2012-3-4����10:03:40
 *@���ߣ�hp
 *@Copyright 2009-2010 �ӱ�����ѧԺ��Ϣ��ѧ�빤��ѧԺ������ 
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
 * @ModuleclassJspAction <code>{������}</code>
 * @author  {���ߣ�gq}
 * @version {�汾,����ʱ�����}
 * @{��������:} 
 */
@Controller
@Scope("prototype")
@Namespace("")
@ParentPackage("default")
public class ModuleclassJspAction extends ActionSupport {

	private static final long serialVersionUID = -8944047682058490289L;

	/**
	 * 
	 * @{������:listMclass.action}
	 * @param {���������} {�������˵��}
	 * @return {���ز�����} {���ز���˵��}
	 * @{�����Ĺ���/��������:��ת���������ģ��ҳ��}
	 * @exception {˵����ĳ�����,������ʲô�쳣}
	 */
	@Action(value="/listMclass",results={@Result(name="success",location="/page/moduleclass/listMclass.jsp")})
	public String listMclass() throws Exception{
			return SUCCESS;
	
		}

}
