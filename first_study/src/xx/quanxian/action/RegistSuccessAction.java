/*
 *@(#)xx.quanxian.action
 *@LoginAction.java.java  
 *@����ʱ��:2011-4-3����09:57:53
 *@���ߣ�guoqiang
 *@Copyright 2009-2010 �ӱ�����ѧԺ��Ϣ��ѧ�빤��ѧԺ������ 
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
 * @LoginAction <code>{������}</code>
 * @author  {guoqiang}
 * @version {�汾,����ʱ�����}
 * @{...��������} 
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
	 * @param {/main.jsp} {�������˵��}
	 * @return {success} {���ز���˵��}
	 * @{�û��ɹ�ע��}
	 * @exception {˵����ĳ�����,������ʲô�쳣}
	*/
	@SuppressWarnings("unchecked")
	@Action(value="/registSuccessjsp",results={@Result(name="success",location="/regSuccessjsp.action",type="redirect")})
    public String registSuccessjsp(){
				
		return SUCCESS;
	}
		
	/**
	 * @{regSuccessjsp}
	 * @param {/main.jsp} {�������˵��}
	 * @return {success} {���ز���˵��}
	 * @{�û��ɹ�ע��}
	 * @exception {˵����ĳ�����,������ʲô�쳣}
	*/
	@SuppressWarnings("unchecked")
	@Action(value="/regSuccessjsp",results={@Result(name="success",location="/main.jsp")})
    public String regSuccessjsp(){
		HttpSession hs = ServletActionContext.getRequest().getSession();
		edu=(Userinfo) hs.getAttribute("userifno");
       
		mds=(List<Moduleclass>) hs.getAttribute("mds");;
		als=(List<ActionList>) hs.getAttribute("als");
		
	  //�û�����username
		Teacher tea=(Teacher)hs.getAttribute("teacher");//��userAction.action�л�ã������û�ע��֮�������˸�����Ϣ֮��ˢ��ʱ
		if(tea!=null){
			if(StringUtils.isNotBlank(tea.getJsxm())){
				username=tea.getJsxm();
			}
		}else{
			username="��(�����Ƹ�����Ϣ)";
		}
        
      //���ͻ��˷���һ��cookie
		Cookie cookie=new Cookie((String)hs.getAttribute("uid"),(String)hs.getAttribute("uid"));
		ServletActionContext.getResponse().addCookie(cookie);
		
		return SUCCESS;
	}
	

}
