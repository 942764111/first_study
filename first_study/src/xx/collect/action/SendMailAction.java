/*
 *@(#)xx.quanxian.action
 *@UserAction.java.java  
 *@创建时间:2011-4-3上午09:03:00
 *@作者：guoqiang
 *@Copyright 2009-2010 河北北方学院信息科学与工程学院科研所 
 */

package xx.collect.action;

import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import xx.collection.bean.Bjxx;
import xx.collection.bean.Roles;
import xx.collection.bean.Studentifno;
import xx.collection.bean.Userinfo;
import xx.email.B;
import xx.email.Constant;
import xx.email.SendMail;
import xx.quanxian.service.BaseService;

import com.opensymphony.xwork2.ActionSupport;

/**
 * @UserAction <code>{类名称}</code>
 * @author  {guoqiang}
 * @version {版本,常用时间代替}
 * @{...功能描述} 
 */


@Controller
@Scope("prototype")
@Namespace("")
@ParentPackage("default-package")
@SuppressWarnings("serial")
public class SendMailAction extends ActionSupport {


	private String email;
	private String state;
	@Resource(name="baseService")
    private BaseService baseService; // 分页管理类，用它来调用函数返回PageModule
	
    private String userid;
    private String userPw;

    private String yanzhengma;
    private String code;

	/**
	 * @return the student
	 */

	/**
	 * @return the code
	 */
	public String getCode() {
		return code;
	}



	/**
	 * @param code the code to set
	 */
	public void setCode(String code) {
		this.code = code;
	}



	/**
	 * @return the userid
	 */
	public String getUserid() {
		return userid;
	}



	/**
	 * @param userid the userid to set
	 */
	public void setUserid(String userid) {
		this.userid = userid;
	}



	/**
	 * @return the password
	 */
	



	/**
	 * @return the yanzhengma
	 */
	public String getYanzhengma() {
		return yanzhengma;
	}



	/**
	 * @return the userPw
	 */
	public String getUserPw() {
		return userPw;
	}



	/**
	 * @param userPw the userPw to set
	 */
	public void setUserPw(String userPw) {
		this.userPw = userPw;
	}



	/**
	 * @param yanzhengma the yanzhengma to set
	 */
	public void setYanzhengma(String yanzhengma) {
		this.yanzhengma = yanzhengma;
	}



	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}



	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}



	/**
	 * @return the state
	 */
	public String getState() {
		return state;
	}



	/**
	 * @param state the state to set
	 */
	public void setState(String state) {
		this.state = state;
	}



	/**
	 * @{userAction 显示用户详细信息的action}
	 * @param {} {引入参数说明}
	 * @return {SUCCESS} {返回参数说明}
	 * @{跳转到user.jsp}
	 * @exception {说明在某情况下,将发生什么异常}
	 */
	@Action(value="sendEmail",results={@Result(name="success",type="json")})
	public String sendEmail(){
		if (email==null||email.equals("")) {
			
			state="-1";//不能为空
			return SUCCESS;
		}
		if (!checkEmail(email)) {
			state="-2";//邮箱格式不正确
			return SUCCESS;
		}
		int random=(int)(Math.random()*1000000); 
		System.out.println(random);
		HttpSession hs = ServletActionContext.getRequest().getSession();
		hs.setAttribute("yanzhengma", String.valueOf(random));
		
		try {
		
			new B().send(email, "您本次的验证码为:"+random+"  请30分钟之内验证，注意保密，千万不要告诉任何人！");
			
		} catch (Exception e) {
			// TODO: handle exception
			state="-3";
		}
		
		state="0";
		return SUCCESS;
		
	}
	
	@Action(value="studentRegister",results={@Result(name="success",type="json")})
	public String studentRegister(){
		Userinfo userinfo=new Userinfo();
		
		if (userid==null||userid.equals("")||userid.contains(" ")) {
			state="-1";//用户名不能为空
			return SUCCESS;
			
		}
		
		int useridlength=userid.length();
		if (useridlength<4||useridlength>10) {
			state="-6";//
			return SUCCESS;
			
		}
		if (userPw==null||userPw.equals("")) {
			state="-2";//密码不能为空
			return SUCCESS;
		}
		HttpSession hs = ServletActionContext.getRequest().getSession();
		String yanzhengma2=(String) hs.getAttribute("yanzhengma");
		if (!yanzhengma2.equals(yanzhengma)) {
			state="-3";//验证码不正确
			return SUCCESS;
		}
		Userinfo userinfo2=this.baseService.find(Userinfo.class, userid);
		if (userinfo2==null||userinfo2.equals("")) {
			
			
			String[] keys={"email"};
			Object[] value={email};
			List<Studentifno> list=this.baseService.find(Studentifno.class, "Studentifno", keys, value);
			if (list==null||list.size()==0) {
				userinfo.setUserId(userid);
				userinfo.setUserPw(userPw);
				Roles roles=new Roles();
				roles.setRoleid(108);
				userinfo.setRoles(roles);
				userinfo.setSafeAnswer("安全问题答案");
				userinfo.setSafeQuestion("安全问题？");
				userinfo.setTypeId("1");
				userinfo.setType("S");
				this.baseService.save(userinfo);
				
				Studentifno studentifno=new Studentifno();
				studentifno.setSNo(userid);
				studentifno.setEmail(email);
				studentifno.setUserinfo(userinfo);
				studentifno.setHandphone("");
				studentifno.setSSex("");
			    Bjxx bjxx=new Bjxx();
			    bjxx.setBjbh(412);
				studentifno.setBjxx(bjxx);
				Date rxny=new Date();
				studentifno.setRxny(rxny);
				this.baseService.save(studentifno);
				
				state="0";
				return SUCCESS;
			}else {
				state="-4";//验证码不正确
				return SUCCESS;
			}
		}else{
			state="-5";//该用户名已存在
			return SUCCESS;
		}
		
		
		
	}

	@Action(value="findPW",results={@Result(name="success",type="json")})
	public String findPW(){
		if (email==null||email.equals("")) {
			
			state="-2";//不能为空
			return SUCCESS;
		}
		if (code==null||code.equals("")) {
			state="-1";//邮箱格式不正确
			return SUCCESS;
		}
		HttpSession hs = ServletActionContext.getRequest().getSession();
		String yanzhengma2=(String) hs.getAttribute("yanzhengma");
		if (!yanzhengma2.equals(code)) {
			state="-3";//验证码不正确
			return SUCCESS;
		}
		
		String[] keys={"email"};
		Object[] values={email};
		List<Studentifno> uList=this.baseService.find(Studentifno.class, "Studentifno", keys, values);
		if (uList==null||uList.size()==0) {
			state="-4";//邮箱不存在
			return SUCCESS;
		}
		
		Userinfo userinfo=this.baseService.find(Userinfo.class, uList.get(0).getUserinfo().getUserId());
		int random=(int)(Math.random()*1000000); 
		userinfo.setUserPw(String.valueOf(random));
		this.baseService.update(userinfo);
		state="0";
		userPw=String.valueOf(random);
		return SUCCESS;
		
	}
	 public static boolean checkEmail(String email){
	        boolean flag = false;
	        try{
	                String check = "^([a-z0-9A-Z]+[-|_|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
	                Pattern regex = Pattern.compile(check);
	                Matcher matcher = regex.matcher(email);
	                flag = matcher.matches();
	            }catch(Exception e){
	                flag = false;
	            }
	        return flag;
	    }
}
