/*
 *@(#)xx.quanxian.role.action
 *@MkglAction.java.java  
 *@����ʱ��:2011-5-3����10:11:53
 *@���ߣ�guoqiang
 *@Copyright 2009-2010 �ӱ�����ѧԺ��Ϣ��ѧ�빤��ѧԺ������ 
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
 * @ManagerModules <code>{������}</code>
 * @author  {gq}
 * @version {�汾,����ʱ�����}
 * @{ģ�����} 
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
	 * @{��ת��ģ�����ҳ��}
	 * @param {} {�������˵��}
	 * @return {SUCCESS} {���ز���˵��}
	 * @{��ת��listModules.jsp}
	 * @exception {˵����ĳ�����,������ʲô�쳣}
	**/
	
	@Action(value="/mModules",results={@Result(name="success",location="/page/role/mkgl.jsp")})
	public String mkgljsp(){
		feileiname=this.baseService.find(String.class,"Moduleclass","mclassname");

		return SUCCESS;
	}
	

}
