/*
 *@(#)xx.quanxian.action
 *@LoginAction.java.java  
 *@����ʱ��:2011-4-3����09:57:53
 *@���ߣ�guoqiang
 *@Copyright 2009-2010 �ӱ�����ѧԺ��Ϣ��ѧ�빤��ѧԺ������ 
 */

package xx.quanxian.action;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import xx.adminservice.AdminService;
import xx.collection.bean.Allteachers;
import xx.collection.bean.AssessLog;
import xx.collection.bean.Functions;
import xx.collection.bean.Moduleclass;
import xx.collection.bean.Teacher;
import xx.collection.bean.Userinfo;
import xx.quanxian.service.BaseService;

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
@ParentPackage("default-package")
public class LoginAction extends ActionSupport {
	
	@Resource(name="baseService")
	private BaseService baseService;
	@Resource(name="adminService")
	private AdminService adminService;
	static int roleid;
	private boolean tip;
	private String userid;
	private Userinfo edu;
	private List<List> adminlist;
	private List<List> userList;
 	private List<String> modulesclassname = new ArrayList<String>();
	private List<String> classname = new ArrayList<String>();
	private List<ActionList> als = new ArrayList<ActionList>();
	private List<Moduleclass> mds = new ArrayList<Moduleclass>();

	private String userPw;
	private String tip1;
	private String safecode;
	private Userinfo edu1;
	private String safeQuestion;
	private String userlogin;
	private String teajoinNo;//��ʦע���ž�����ǰ�������ݿ��е����вμӽ�ѧ��������ʦ�Ľ�ʦ���
	
	
	/**
	 * @return the teajoinNo
	 */
	public String getTeajoinNo() {
		return teajoinNo;
	}
	/**
	 * @param teajoinNo the teajoinNo to set
	 */
	public void setTeajoinNo(String teajoinNo) {
		this.teajoinNo = teajoinNo;
	}
	/**
	 * @return the safeQuestion
	 */
	public String getSafeQuestion() {
		return safeQuestion;
	}
	/**
	 * @param safeQuestion the safeQuestion to set
	 */
	public void setSafeQuestion(String safeQuestion) {
		this.safeQuestion = safeQuestion;
	}
	@JSON(serialize=false)
	public Userinfo getEdu1() {
		return edu1;
	}
	/**
	 * @param edu1 the edu1 to set
	 */
	public void setEdu1(Userinfo edu1) {
		this.edu1 = edu1;
	}
	/**
	 * @return the safecode
	 */
	@JSON(serialize=false)
	public String getSafecode() {
		return safecode;
	}
	/**
	 * @param safecode the safecode to set
	 */
	public void setSafecode(String safecode) {
		this.safecode = safecode;
	}
	
	/**
	 * @return the userList
	 */
	@JSON(serialize=false)
	public List<List> getUserList() {
		return userList;
	}
	/**
	 * @param userList the userList to set
	 */
	public void setUserList(List<List> userList) {
		this.userList = userList;
	}
	public String getTip1() {
		return tip1;
	}
	
	public void setTip1(String tip1) {
		this.tip1 = tip1;
	}
	@JSON(serialize=false)
	public String getUserPw() {
		return userPw;
	}
	@JSON(deserialize=true)
	public void setUserPw(String userPw) {
		this.userPw = userPw;
	}
	@JSON(serialize=false)
	public BaseService getBaseService() {
		return baseService;
	}

	public void setBaseService(BaseService baseService) {
		this.baseService = baseService;
	}
	@JSON(serialize=false)
	public int getRoleid() {
		return roleid;
	}

	public void setRoleid(int roleid) {
		this.roleid = roleid;
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
	public List<String> getModulesclassname() {
		return modulesclassname;
	}

	public void setModulesclassname(List<String> modulesclassname) {
		this.modulesclassname = modulesclassname;
	}
	@JSON(serialize=false)
	public List<String> getClassname() {
		return classname;
	}

	public void setClassname(List<String> classname) {
		this.classname = classname;
	}
	@JSON(serialize=false)
	public List<List> getAdminlist() {
		return adminlist;
	}

	public void setAdminlist(List<List> adminlist) {
		this.adminlist = adminlist;
	}
	@JSON(serialize=false)
	public Userinfo getEdu() {
		return edu;
	}
	@JSON(deserialize=true)
	public void setEdu(Userinfo edu) {
		this.edu = edu;
	}
	
	public boolean getTip() {
		return tip;
	}

	public void setTip(boolean tip) {
		this.tip = tip;
	}
	
	public String getUserid() {
		return userid;
	}
	
	public void setUserid(String userid) {
		this.userid = userid;
	}

	/**
	 * @return the userlogin
	 */
	public String getUserlogin() {
		return userlogin;
	}
	/**
	 * @param userlogin the userlogin to set
	 */
	public void setUserlogin(String userlogin) {
		this.userlogin = userlogin;
	}
	
	/**
	 * @{register}
	 * @param {} {�������˵��}
	 * @return {SUCCESS} {���ز���˵��}
	 * @{��ת��/page/user/register.jsp}
	 * @exception {˵����ĳ�����,������ʲô�쳣}
	*/
	@Action(value="/register",results={@Result(name="success",location="/page/user/regist.jsp")})
	public String register(){
		
		return SUCCESS;
	}
	
	/**
	 * @{existUser}
	 * @param {/page/user/regist.jsp} {�������˵��}
	 * @return {���ز�����} {���ز���˵��}
	 * @{ע��ҳ��,AJAX�ж�UID�Ƿ��Ѿ�����}
	 * @exception {˵����ĳ�����,������ʲô�쳣}
	*/
	@Action(value="RegUser",results={@Result(name="success",type="json")})
	public String existUser(){
		tip=baseService.regJson(userid);	 
		return SUCCESS;
	}
	/**
	 * @{existUser}
	 * @param {} {�������˵��}
	 * @return {���ز�����} {���ز���˵��}
	 * @{��ϸ��Ϣҳ��,AJAX�ж�UID�Ƿ��Ѿ�����}
	 * @exception {˵����ĳ�����,������ʲô�쳣}
	*/
	@Action(value="RegUser1",results={@Result(name="success",type="json")})
	public String existUser1(){
		HttpSession hs = ServletActionContext.getRequest().getSession();
        String uid=(String) hs.getAttribute("uid");
        if(userid.equals(uid)){
        	//��ʾͬһ��uid�����1
        	tip=false;
        }else{
        	//��ʾ��ͬ��uid�����2
        	tip=baseService.regJson(userid);
        }
        if(tip){
        }else{
        	//���Ա��1ʱ����ʾͬһ��uid����Ȼ��ִ������
        	//���Ա��2ʱ����ʾ��ͬ��uid���������Խ���ע�ᡣ
        	hs.setAttribute("newuid", userid);
        }
	 
		return SUCCESS;
	}

	/**
	 * @{newUser}
	 * @param {/page/user/regist.jsp} {�������˵��}
	 * @return {login.jsp} {���ز���˵��}
	 * @{���û�ע��}
	 * @exception {˵����ĳ�����,������ʲô�쳣}
	*/
	/*@Action(value="/newUser",results={@Result(name="success",location="/main.jsp")})*/
	@Action(value="/newUser",results={@Result(name="success",location="/page/user/registSuccess.jsp"),
			                          @Result(name="input",location="/page/user/regist.jsp")})
	public String newUser() {
		System.out.println("safeQuestion:"+safeQuestion);
		if(safeQuestion!=null&&!safeQuestion.equals("zdy")){
			   edu.setSafeQuestion(safeQuestion);
			   
			}
		HttpSession hs = ServletActionContext.getRequest().getSession();
		String sf = (String) hs.getAttribute("rand");
		if(sf == null){
        	addFieldError("safecode", "�����µ�¼");
        	return INPUT;
        }
		if(sf.equals(safecode)){
			String t=edu.getType();
			if(t.equals("S")){
			   edu.getRoles().setRoleid(108);
			   edu.setTypeId("1");              //ѧ����TypeIdΪ"1"
			}else{
				edu.getRoles().setRoleid(109);
				edu.setTypeId("2");             //��ʦ��TypeIdΪ"2"
			}
			
			hs.setAttribute("userifno", edu);
			this.baseService.save(edu);
			if(!t.equals("S")){
				Teacher tt = new Teacher(teajoinNo);//����ע���ʦʵ�嵽��teacher��
				tt.setUserinfo(edu);
				this.baseService.save(tt);
			}
			
			//���Ƶ�¼ʱ��Ϊ�˽���������鿴�õ�
			hs.setAttribute("uid", edu.getUserId());
			hs.setAttribute("pass", edu.getUserPw());
			hs.setAttribute("role", edu.getRoles().getRoleid());
			//===================��main.jsp��mds��als=============
	        userList = this.adminService.findUserFunctions(edu.getRoles().getRoleid());			//���ݽ�ɫid�����ɫ��function�����ô洢����
			
			for(int i=0;i<userList.size();i++){						                //�Դ����ݿⷵ�ص�ֵ����
				List list = new ArrayList();
				list = userList.get(i);
				String str = (String) list.get(2);
				
				if(!classname.contains(str)){
					classname.add(str);
				}
			}
			for(int i=0;i<userList.size();i++){						//�Դ����ݿⷵ�ص�ֵ����
				
				List list = userList.get(i);
				String str = (String) list.get(3);
				if(!modulesclassname.contains(str)){
					
					modulesclassname.add(str);
					Moduleclass md = new Moduleclass();
					md.setMclassname(str);
					mds.add(md);									//��admin�Ĺ���ģ�����Ʒ���moculesclassname��
				}
				
			}
			List<String> useraction = new ArrayList<String>();//��session�зŵĸ�roleid������action ��url
			for(Moduleclass mdc:mds){						//��module����ActionList��
				for(String gn:classname){
					ActionList al=new ActionList();
					List<Functions> fs=new ArrayList<Functions>();
					for(List list:userList){
						if(list.get(2).equals(gn)&&list.get(3).equals(mdc.getMclassname())){
							Functions ft = new Functions();
							ft.setActionname((String)list.get(0));
							ft.setFunctionname((String)list.get(1));			
							fs.add(ft);
						}
					}
					if(!fs.isEmpty()){
						al.setModuleclass((mdc.getMclassname()));
						al.setModulename(gn);
						al.setFunctionname(fs);
						for(Functions f:fs){
							useraction.add(f.getActionname());
						}
						als.add(al);                      //һ��als�з�����ࡰ��������ͬ����al����
					}
				}
			}                                             //�ⲻ���֮��als���з�����ͬ��Ҳ�в�ͬ��
			

			useraction.add("registSuccessjsp");//ÿһ��ע���û��������Ȩ��
			useraction.add("regSuccessjsp");
			
			hs.setAttribute("userifno", edu);
			hs.setAttribute("useraction", useraction);
            hs.setAttribute("mds",mds);
            hs.setAttribute("als",als);
           
			return SUCCESS;
	  }else {
			addFieldError("safecode", "��֤�����");
			return INPUT;
	  }
		
	}
//	/**
//	 * @{registSuccessjsp}
//	 * @param {/main.jsp} {�������˵��}
//	 * @return {success} {���ز���˵��}
//	 * @{�û��ɹ�ע��}
//	 * @exception {˵����ĳ�����,������ʲô�쳣}
//	*/
//	@SuppressWarnings("unchecked")
//	@Action(value="/registSuccessjsp",results={@Result(name="success",location="/main.jsp")})
//    public String registSuccessjsp(){
//		HttpSession hs = ServletActionContext.getRequest().getSession();
//		edu=(Userinfo) hs.getAttribute("userifno");
//
////		mds=mdss;
////		als=alss;
//		return SUCCESS;
//	}
	
	/**
	 * @{findPasswd}
	 * @param {login.jsp} {�������˵��}
	 * @return {���ز�����} {���ز���˵��}
	 * @{�һ�����}
	 * @exception {˵����ĳ�����,������ʲô�쳣}
	*/
	@Action(value="/findPasswd",results={@Result(name="success",location="/page/user/findPPasswd.jsp")})
	public String findPasswd(){
		 return SUCCESS;
	}
	
	/**
	 * @{passwdFind}
	 * @param {login.jsp} {�������˵��}
	 * @return {���ز�����} {���ز���˵��}
	 * @{�һ�����}
	 * @exception {˵����ĳ�����,������ʲô�쳣}
	*/
	@Action(value="/passwdFind",results={@Result(name="success",location="/page/user/findPasswd.jsp"),
			                              @Result(name="input",location="/page/user/findPPasswd.jsp")})
	public String passwdFind(){
		HttpSession hs = ServletActionContext.getRequest().getSession();
		String sf = (String) hs.getAttribute("rand");
		if(sf == null){
        	addFieldError("safecode", "�����µ�¼");
        	return INPUT;
        }
		if(sf.equals(safecode)){
			edu=this.baseService.find(Userinfo.class, userid);
			edu.setSafeAnswer(null);
			hs.setAttribute("user", edu);
			safecode=null;
			return SUCCESS;
			
		}else {
			addFieldError("safecode", "��֤�����");
			return INPUT;
	   }		 
		
	}
	
	/**
	 * @{passwdPFind}
	 * @param {login.jsp} {�������˵��}
	 * @return {���ز�����} {���ز���˵��}
	 * @{�һ�����}
	 * @exception {˵����ĳ�����,������ʲô�쳣}
	*/
	@Action(value="passwdPFind",results={@Result(name="success",location="/page/user/findPasswd.jsp"),@Result(name="input",location="/page/user/findPasswd.jsp")})
	public String passwdPFind(){
		HttpSession hs = ServletActionContext.getRequest().getSession();
		Userinfo user=(Userinfo) hs.getAttribute("user");
		String sf = (String) hs.getAttribute("rand");
		if(sf == null){
        	addFieldError("safecode", "�����µ�¼");
        	return INPUT;
        }
		if(sf.equals(safecode)){
			
						Userinfo edu2=this.baseService.find(Userinfo.class,user.getUserId());
						String answer=edu2.getSafeAnswer();
						if(answer.equals(edu.getSafeAnswer())){
							edu1=edu2;
							edu=edu2;
							safecode=null;
							return SUCCESS;
						}else{
							addFieldError("safeAnswer", "�𰸴���");
							edu=user;
							return INPUT;
						}
			
			
		}else {
						addFieldError("safecode", "��֤�����");
						edu=user;
						return INPUT;
	   }		 
		
	  
	}
	
	/**
	 * ��action���������û���¼�����û���¼����У��
	 * У������ݰ����û��������룬��֤���Լ����û��Ƿ��¼
	 * ����ʽ����ajax�ύ���������ϵ�¼����ʱ�����á�tip����ʾ������Ϣ
	 * ��¼ʱ���ǰ̨����UserInfo�Ķ��� edu
	*/
	@Action(value="/login",results={@Result(name="root",type="json")})
	public String login(){
		Userinfo edu = new Userinfo();
		edu.setUserId(userid);
		HttpSession session1 = ServletActionContext.getRequest().getSession(); System.out.println("session1:"+session1.getId());
		session1.setAttribute("userid",userid);
		edu = this.baseService.find(Userinfo.class, edu.getUserId());
		
		HttpSession session = ServletActionContext.getRequest().getSession();System.out.println("session:"+session.getId());
		
		if("login1".equals(userlogin)) {
			if(edu!=null){
				if(userPw.equals(edu.getUserPw())){
					String str = this.baseService.login(edu);
					if(str.equals("islogin")){
						this.tip1="islogin";
						return "root";
					}else{
						//��������ٻ��session���󣬷�ֹthis.baseServce.login()��������Ϊͬһ��������Ⱥ��¼������ͬ�û�������session.invalidate()
						HttpSession hs = ServletActionContext.getRequest().getSession();System.out.println("hs:"+hs.getId());
						hs.setAttribute("uid", edu.getUserId());//��¼һ��
						hs.setAttribute("userid", userid);//��ֹthis.baseServce.login()��������Ϊͬһ��������Ⱥ��¼������ͬ�û�������session.invalidate()
						hs.setAttribute("pass", edu.getUserPw());
						hs.setAttribute("role", edu.getRoles().getRoleid());
						hs.setAttribute("userifno", edu);//ע��һ�� 
						this.tip1="success";
						
						String ip=ServletActionContext.getRequest().getHeader("x-forwarded-for");
						 if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {      
				              ip = ServletActionContext.getRequest().getHeader("Proxy-Client-IP");      
				          }      
				          if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {      
				              ip = ServletActionContext.getRequest().getHeader("WL-Proxy-Client-IP");      
				           }      
				         if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {      
				               ip = ServletActionContext.getRequest().getRemoteAddr();      
				          }   
				         System.out.println("������ip��ַ:"+ip);
				         SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//�������ڸ�ʽ
				        
				         AssessLog aLog=new AssessLog();
				         aLog.setUsername(userid);
				         aLog.setIpaddress(ip);
				         aLog.setLogintime(df.format(new Date()));
				         baseService.save(aLog);
						return "root";
					}
				}else{
					//session.invalidate();
					session.removeAttribute("userid");
					this.tip1="PwError";
					return "root";
				}
			}else{
				//session.invalidate();
				session.removeAttribute("userid");
				this.tip1="nameError";
				return "root";
			}
		} else {
			String sf = (String) session.getAttribute("rand");    
			if(sf==null){
				session.invalidate();
	        	tip1="safeerror";
	        	return "root";
			}else{
//				if(sf.equals(safecode)){									//****��֤������
//					if(edu!=null){
//						if(userPw.equals(edu.getUserPw())){
//							String str = this.baseService.login(edu);
//							if(str.equals("islogin")){
//								this.tip1="islogin";
//								return "root";
//							}else{
//								//��������ٻ��session���󣬷�ֹthis.baseServce.login()��������Ϊͬһ��������Ⱥ��¼������ͬ�û�������session.invalidate()
//								HttpSession hs = ServletActionContext.getRequest().getSession();System.out.println("hs:"+hs.getId());
//								hs.setAttribute("uid", edu.getUserId());//��¼һ��
//								hs.setAttribute("userid", userid);//��ֹthis.baseServce.login()��������Ϊͬһ��������Ⱥ��¼������ͬ�û�������session.invalidate()
//								hs.setAttribute("pass", edu.getUserPw());
//								hs.setAttribute("role", edu.getRoles().getRoleid());
//								hs.setAttribute("userifno", edu);//ע��һ�� 
//								this.tip1="success";
//								return "root";
//							}
//						}else{
//							//session.invalidate();
//							session.removeAttribute("userid");
//							this.tip1="PwError";
//							return "root";
//						}
//					}else{
//						//session.invalidate();
//						session.removeAttribute("userid");
//						this.tip1="nameError";
//						return "root";
//					}
//		        }else{
//		        	//session.invalidate();
//		        	session.removeAttribute("userid");
//		        	tip1="safeerror";
//		        	return "root";
//		        }
			}
		}
		return null;
	}
	
	/**
	 * ��action���������˳���¼�����ر�ҳ��ʱ�ᴥ����action
	 * �ڸ÷����У��Ὣ��¼ʱ���浽session�е�uid��psw��roleid����Ϣ���
	*/
	@Action(value="/quitLogin",results={@Result(name="success",location="/login1.jsp")})
	public String quitLogin(){
		Cookie[] cookies=ServletActionContext.getRequest().getCookies();
		HttpSession session = ServletActionContext.getRequest().getSession();System.out.println("session2:"+session.getId());
		String uid=(String) session.getAttribute("uid");
		Cookie cookie=null;
		for(int j=0;j<cookies.length;j++){
			System.out.println("�ж���----------��"+cookies[j].getValue());
		}
		
		System.out.println("��һ����cookies jsessionid---------->"+cookies[0].getValue()+"���ȣ�"+cookies.length);
		if(cookies.length<=2){
			//��ʾ�ر����һ��main.jspҳ�棨���һ��main.jspһ���ǵ�ǰ�û���cookie��
			for(int i=0;i<cookies.length;i++){
				if(!session.getId().equals(cookies[i].getValue())){
					//��ʾÿ�ιر�һ��main.jspҳ�������һ���ǵ�ǰ�û���cookie��cookie������main.jspҳ��򿪵ĸ�����ͬ��������ѭ��
					cookies[i].setMaxAge(0);
					System.out.println("��������cookie----->"+cookies[i].getValue());
					cookie=cookies[i];
					break;
				}
			}
			if(OnlineSessionList.hm.containsKey(uid)){
				OnlineSessionList.hm.remove(uid);
			}
			session.invalidate();
		}else{
			for(int i=0;i<cookies.length;i++){
				if(!uid.equals(cookies[i].getValue())&&!session.getId().equals(cookies[i].getValue())){
					//��ʾÿ�ιر�һ��main.jspҳ�������һ���ǵ�ǰ�û���cookie��cookie������main.jspҳ��򿪵ĸ�����ͬ��������ѭ��
					cookies[i].setMaxAge(0);
					System.out.println("��������cookie----->"+cookies[i].getValue());
					cookie=cookies[i];
					break;
				}
			}
			
		}
		ServletActionContext.getResponse().addCookie(cookie);
		return SUCCESS;
	} 
	
	/**
	 * ��action���������˳���¼�����ر�ҳ��ʱ�ᴥ����action
	 * �ڸ÷����У��Ὣ��¼ʱ���浽session�е�uid��psw��roleid����Ϣ���
	*/
	@Action(value="/quitLogin1",results={@Result(name="success",type="json")})
	public String quitLogin1(){
		Cookie[] cookies=ServletActionContext.getRequest().getCookies();
		HttpSession session = ServletActionContext.getRequest().getSession();System.out.println("session2:"+session.getId());
		String uid=(String) session.getAttribute("uid");
		Cookie cookie=null;
		for(int j=0;j<cookies.length;j++){
			System.out.println("�ж���----------��"+cookies[j].getValue());
		}
		
		System.out.println("��һ����cookies jsessionid---------->"+cookies[0].getValue()+"���ȣ�"+cookies.length);
		if(cookies.length<=2){
			//��ʾ�ر����һ��main.jspҳ�棨���һ��main.jspһ���ǵ�ǰ�û���cookie��
			for(int i=0;i<cookies.length;i++){
				if(!session.getId().equals(cookies[i].getValue())){
					//��ʾÿ�ιر�һ��main.jspҳ�������һ���ǵ�ǰ�û���cookie��cookie������main.jspҳ��򿪵ĸ�����ͬ��������ѭ��
					cookies[i].setMaxAge(0);
					System.out.println("��������cookie----->"+cookies[i].getValue());
					cookie=cookies[i];
					break;
				}
			}
			if(OnlineSessionList.hm.containsKey(uid)){
				OnlineSessionList.hm.remove(uid);
			}
			session.invalidate();
		}else{
			for(int i=0;i<cookies.length;i++){
				if(!uid.equals(cookies[i].getValue())&&!session.getId().equals(cookies[i].getValue())){
					//��ʾÿ�ιر�һ��main.jspҳ�������һ���ǵ�ǰ�û���cookie��cookie������main.jspҳ��򿪵ĸ�����ͬ��������ѭ��
					cookies[i].setMaxAge(0);
					System.out.println("��������cookie----->"+cookies[i].getValue());
					cookie=cookies[i];
					break;
				}
			}
			
		}
		ServletActionContext.getResponse().addCookie(cookie);
		return SUCCESS;
	} 
	
	/**
	 * @{imagejsp}
	 * ��action���ڵ�¼��ʱ��������ʾ��֤���
	 * ͨ������success���Ὣimage.jsp�����������֤����ʾ��login.jspҳ��
	*/
	@Action(value="/imagejsp",results={@Result(name = "success",location="/page/user/image.jsp")})
	public String imagejsp(){
		return SUCCESS;
	}
	
}
