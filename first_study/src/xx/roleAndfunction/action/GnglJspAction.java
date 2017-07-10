/*
 *@(#)xx.roleAndfunction.action
 *@GnglJspAction.java.java  
 *@����ʱ��:2012-3-4����10:10:49
 *@���ߣ�hp
 *@Copyright 2009-2010 �ӱ�����ѧԺ��Ϣ��ѧ�빤��ѧԺ������ 
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
 * @GnglJspAction <code>{������}</code>
 * @author  {����:gq}
 * @version {�汾,����ʱ�����}
 * @{��������:} 
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
	 * @{������:listfunc.action}
	 * @param {���������} {�������˵��}
	 * @return {���ز�����:modulelist} {���ز���˵��}
	 * @{�����Ĺ���/��������:��ת�����ܹ���ҳ��}
	 * @exception {˵����ĳ�����,������ʲô�쳣}
	 */

	@Action(value="/listfunc",results={@Result(name="success",location="/page/role/gngl.jsp")})
	public String listFunc(){
		this.modulelist = this.baseService.find(Modules.class);
		return SUCCESS;
	}

}
