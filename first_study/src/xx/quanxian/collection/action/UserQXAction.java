/*
 *@(#)xx.quanxian.moduleclass.action
 *@UserQXAction.java.java  
 *@创建时间:2011-9-23上午09:26:24
 *@作者：xupengfei
 *@Copyright 2009-2010 河北北方学院信息科学与工程学院科研所 
 */

package xx.quanxian.collection.action;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import xx.collection.bean.Roles;
import xx.collection.bean.Userinfo;
import xx.quanxian.service.BaseService;

import com.googlecode.jsonplugin.annotations.JSON;
import com.opensymphony.xwork2.ActionSupport;

/**
 * @UserQXAction <code>{类名称}</code>
 * @author  {作者}
 * @version {版本,常用时间代替}
 * @{功能描述:} 
 */
@Controller
@Scope("prototype")
@Namespace("")
@ParentPackage("default-package")
public class UserQXAction extends ActionSupport {
	@Resource(name="baseService")
	private BaseService service;
	private String userId;
	private int roleid;
	@JSON(serialize=false)
	public String getUserid() {
		return userId;
	}
	public void setUserid(String userid) {
		this.userId = userid;
	}
	public int getRoleid() {
		return roleid;
	}
	public void setRoleid(int roleid) {
		this.roleid = roleid;
	}
	@JSON(serialize=false)
	public BaseService getService() {
		return service;
	}
	public void setService(BaseService service) {
		this.service = service;
	}
	@Action(value="/panduanbutton",results={@Result(name="success",type="json")})
	public String panduan(){
		HttpSession session1 = ServletActionContext.getRequest().getSession();
		userId = (String) session1.getAttribute("userid");
		Userinfo userinfo = new Userinfo();
		userinfo = this.service.find(Userinfo.class,userId);
		Roles roles = new Roles();
		roles = userinfo.getRoles();
		roleid = roles.getRoleid();
		return SUCCESS;
		
	}

}
