/*
 *@(#)xx.quanxian.action
 *@UserAction.java.java  
 *@����ʱ��:2011-4-3����09:03:00
 *@���ߣ�guoqiang
 *@Copyright 2009-2010 �ӱ�����ѧԺ��Ϣ��ѧ�빤��ѧԺ������ 
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
 * @UserAction <code>{������}</code>
 * @author  {guoqiang}
 * @version {�汾,����ʱ�����}
 * @{...��������} 
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
    private BaseService baseService; // ��ҳ�����࣬���������ú�������PageModule
	
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
	 * @{userAction ��ʾ�û���ϸ��Ϣ��action}
	 * @param {} {�������˵��}
	 * @return {SUCCESS} {���ز���˵��}
	 * @{��ת��user.jsp}
	 * @exception {˵����ĳ�����,������ʲô�쳣}
	 */
	@Action(value="sendEmail",results={@Result(name="success",type="json")})
	public String sendEmail(){
		if (email==null||email.equals("")) {
			
			state="-1";//����Ϊ��
			return SUCCESS;
		}
		if (!checkEmail(email)) {
			state="-2";//�����ʽ����ȷ
			return SUCCESS;
		}
		int random=(int)(Math.random()*1000000); 
		System.out.println(random);
		HttpSession hs = ServletActionContext.getRequest().getSession();
		hs.setAttribute("yanzhengma", String.valueOf(random));
		
		try {
		
			new B().send(email, "�����ε���֤��Ϊ:"+random+"  ��30����֮����֤��ע�Ᵽ�ܣ�ǧ��Ҫ�����κ��ˣ�");
			
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
			state="-1";//�û�������Ϊ��
			return SUCCESS;
			
		}
		
		int useridlength=userid.length();
		if (useridlength<4||useridlength>10) {
			state="-6";//
			return SUCCESS;
			
		}
		if (userPw==null||userPw.equals("")) {
			state="-2";//���벻��Ϊ��
			return SUCCESS;
		}
		HttpSession hs = ServletActionContext.getRequest().getSession();
		String yanzhengma2=(String) hs.getAttribute("yanzhengma");
		if (!yanzhengma2.equals(yanzhengma)) {
			state="-3";//��֤�벻��ȷ
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
				userinfo.setSafeAnswer("��ȫ�����");
				userinfo.setSafeQuestion("��ȫ���⣿");
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
				state="-4";//��֤�벻��ȷ
				return SUCCESS;
			}
		}else{
			state="-5";//���û����Ѵ���
			return SUCCESS;
		}
		
		
		
	}

	@Action(value="findPW",results={@Result(name="success",type="json")})
	public String findPW(){
		if (email==null||email.equals("")) {
			
			state="-2";//����Ϊ��
			return SUCCESS;
		}
		if (code==null||code.equals("")) {
			state="-1";//�����ʽ����ȷ
			return SUCCESS;
		}
		HttpSession hs = ServletActionContext.getRequest().getSession();
		String yanzhengma2=(String) hs.getAttribute("yanzhengma");
		if (!yanzhengma2.equals(code)) {
			state="-3";//��֤�벻��ȷ
			return SUCCESS;
		}
		
		String[] keys={"email"};
		Object[] values={email};
		List<Studentifno> uList=this.baseService.find(Studentifno.class, "Studentifno", keys, values);
		if (uList==null||uList.size()==0) {
			state="-4";//���䲻����
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
