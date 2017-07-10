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


import javax.servlet.http.Cookie;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import xx.collection.bean.Moduleclass;
import xx.collection.bean.Teacher;

import xx.collection.bean.Userinfo;


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
@ParentPackage("default")
public class RegistSuccessAction extends ActionSupport {
	
	private Userinfo edu;
	
	private List<ActionList> als = new ArrayList<ActionList>();
	private List<Moduleclass> mds = new ArrayList<Moduleclass>();

    private String username;
	
	
	
	public String getUsername() {
		return username;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	@JSON(serialize=false)
	public List<Moduleclass> getMds() {
		return mds;
	}

	public void setMds(List<Moduleclass> mds) {
		this.mds = mds;
	}
	@JSON(serialize=false)
	public List<ActionList> getAls() {
		return als;
	}

	public void setAls(List<ActionList> als) {
		this.als = als;
	}

	@JSON(serialize=false)
	public Userinfo getEdu() {
		return edu;
	}
	@JSON(deserialize=true)
	public void setEdu(Userinfo edu) {
		this.edu = edu;
	}
	/**
	 * @{registSuccessjsp}
	 * @param {/main.jsp} {引入参数说明}
	 * @return {success} {返回参数说明}
	 * @{用户成功注册}
	 * @exception {说明在某情况下,将发生什么异常}
	*/
	@SuppressWarnings("unchecked")
	@Action(value="/registSuccessjsp",results={@Result(name="success",location="/regSuccessjsp.action",type="redirect")})
    public String registSuccessjsp(){
				
		return SUCCESS;
	}
		
	/**
	 * @{regSuccessjsp}
	 * @param {/main.jsp} {引入参数说明}
	 * @return {success} {返回参数说明}
	 * @{用户成功注册}
	 * @exception {说明在某情况下,将发生什么异常}
	*/
	@SuppressWarnings("unchecked")
	@Action(value="/regSuccessjsp",results={@Result(name="success",location="/main.jsp")})
    public String regSuccessjsp(){
		HttpSession hs = ServletActionContext.getRequest().getSession();
		edu=(Userinfo) hs.getAttribute("userifno");
       
		mds=(List<Moduleclass>) hs.getAttribute("mds");;
		als=(List<ActionList>) hs.getAttribute("als");
		
	  //用户姓名username
		Teacher tea=(Teacher)hs.getAttribute("teacher");//从userAction.action中获得，是在用户注册之后，完善了个人信息之后刷新时
		if(tea!=null){
			if(StringUtils.isNotBlank(tea.getJsxm())){
				username=tea.getJsxm();
			}
		}else{
			username="空(请完善个人信息)";
		}
        
      //往客户端返回一个cookie
		Cookie cookie=new Cookie((String)hs.getAttribute("uid"),(String)hs.getAttribute("uid"));
		ServletActionContext.getResponse().addCookie(cookie);
		
		return SUCCESS;
	}
	

}
