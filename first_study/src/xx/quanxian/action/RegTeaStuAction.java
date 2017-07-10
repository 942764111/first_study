/*
 *@(#)xx.quanxian.action
 *@LoginAction.java.java  
 *@创建时间:2011-4-3上午09:57:53
 *@作者：guoqiang
 *@Copyright 2009-2010 河北北方学院信息科学与工程学院科研所 
 */

package xx.quanxian.action;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import xx.adminservice.AdminService;
import xx.bean.Teacher;
import xx.collection.bean.Allteachers;
import xx.collection.bean.Functions;
import xx.collection.bean.Moduleclass;
import xx.collection.bean.Rolefunction;
import xx.collection.bean.Studentifno;
import xx.collection.bean.Userinfo;
import xx.quanxian.service.BaseService;

import com.googlecode.jsonplugin.annotations.JSON;
import com.opensymphony.xwork2.ActionSupport;

/**
 * @LoginAction <code>{类名称}</code>
 * @author  {guoqiang}
 * @version {版本,常用时间代替}
 * @{...功能描述} 
 */
@Controller
@Scope("prototype")
@Namespace("")
@ParentPackage("default-package")
public class RegTeaStuAction extends ActionSupport {
	
	@Resource(name="baseService")
	private BaseService baseService;
	private boolean tip;
	private String jsbh;
	private String sno;
	private String type;
	
	

	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * @return the jsbh
	 */
	public String getJsbh() {
		return jsbh;
	}

	/**
	 * @param jsbh the jsbh to set
	 */
	public void setJsbh(String jsbh) {
		this.jsbh = jsbh;
	}

	/**
	 * @return the sno
	 */
	public String getSno() {
		return sno;
	}

	/**
	 * @param sno the sno to set
	 */
	public void setSno(String sno) {
		this.sno = sno;
	}

	public boolean getTip() {
		return tip;
	}

	public void setTip(boolean tip) {
		this.tip = tip;
	}

	
	
	/**
	 * @{RegTea}
	 * @param {jsbh} {引入参数说明}
	 * @return {返回参数名} {返回参数说明}
	 * @{判断教师注册编号是否有效}
	 * @exception {说明在某情况下,将发生什么异常}
	*/
	@Action(value="RegTea",results={@Result(name="success",type="json")})
	public String RegTea(){ 
		HttpSession hs = ServletActionContext.getRequest().getSession();
		List<Teacher> listui=new ArrayList<Teacher>();
		if(type.equals("1")){
			String[] keys={"jsbh"};
			String[] values={jsbh};
			listui=this.baseService.find(Teacher.class, "Teacher", keys, values);
		}else{
			String[] keys={"jsbh","UserId"};
			String[] values={jsbh,(String)hs.getAttribute("uid")};
			listui=this.baseService.find(Teacher.class, "Teacher", keys, values);
		}
		
		List<Allteachers>  listallteachers = this.baseService.findHql(Allteachers.class, "from Allteachers where jsbh = "+jsbh);
		if(listallteachers.size()==0){
			tip=true;//判断教师注册编号是无效
		}else{
			if(!listui.isEmpty()&&listui.size() == 1){
				tip= true;
			}else{
				tip= false;//判断教师注册编号是有效
			}
		}
		return SUCCESS;
	}
	

	/**
	 * @{RegStu}
	 * @param {sno} {引入参数说明}
	 * @return {返回参数名} {返回参数说明}
	 * @{学生修改详细信息,AJAX判断s_no是否已经存在}
	 * @exception {说明在某情况下,将发生什么异常}
	*/
	@Action(value="RegStu",results={@Result(name="success",type="json")})
	public String RegStu(){
		String[] keys={"s_no"};
		String[] values={sno};
		List<Studentifno> listui=this.baseService.find(Studentifno.class, "Studentifno", keys, values);
		if(!listui.isEmpty()&&listui.size() == 1){
			tip= true;
		}else{
			tip= false;
		}
	 
		return SUCCESS;
	}
	
	
	
}
