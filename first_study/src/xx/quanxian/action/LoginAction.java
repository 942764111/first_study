/*
 *@(#)xx.quanxian.action
 *@LoginAction.java.java  
 *@创建时间:2011-4-3上午09:57:53
 *@作者：guoqiang
 *@Copyright 2009-2010 河北北方学院信息科学与工程学院科研所 
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
 * @LoginAction <code>{类名称}</code>
 * @author  {guoqiang}
 * @version {版本,常用时间代替}
 * @{...功能描述} 
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
	private String teajoinNo;//教师注册编号就是提前导入数据库中的所有参加教学工作的老师的教师编号
	
	
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
	 * @param {} {引入参数说明}
	 * @return {SUCCESS} {返回参数说明}
	 * @{跳转到/page/user/register.jsp}
	 * @exception {说明在某情况下,将发生什么异常}
	*/
	@Action(value="/register",results={@Result(name="success",location="/page/user/regist.jsp")})
	public String register(){
		
		return SUCCESS;
	}
	
	/**
	 * @{existUser}
	 * @param {/page/user/regist.jsp} {引入参数说明}
	 * @return {返回参数名} {返回参数说明}
	 * @{注册页面,AJAX判断UID是否已经存在}
	 * @exception {说明在某情况下,将发生什么异常}
	*/
	@Action(value="RegUser",results={@Result(name="success",type="json")})
	public String existUser(){
		tip=baseService.regJson(userid);	 
		return SUCCESS;
	}
	/**
	 * @{existUser}
	 * @param {} {引入参数说明}
	 * @return {返回参数名} {返回参数说明}
	 * @{详细信息页面,AJAX判断UID是否已经存在}
	 * @exception {说明在某情况下,将发生什么异常}
	*/
	@Action(value="RegUser1",results={@Result(name="success",type="json")})
	public String existUser1(){
		HttpSession hs = ServletActionContext.getRequest().getSession();
        String uid=(String) hs.getAttribute("uid");
        if(userid.equals(uid)){
        	//表示同一个uid，标号1
        	tip=false;
        }else{
        	//表示不同的uid，标号2
        	tip=baseService.regJson(userid);
        }
        if(tip){
        }else{
        	//来自标号1时，表示同一个uid，任然会执行它；
        	//来自标号2时，表示不同的uid，且它可以进行注册。
        	hs.setAttribute("newuid", userid);
        }
	 
		return SUCCESS;
	}

	/**
	 * @{newUser}
	 * @param {/page/user/regist.jsp} {引入参数说明}
	 * @return {login.jsp} {返回参数说明}
	 * @{新用户注册}
	 * @exception {说明在某情况下,将发生什么异常}
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
        	addFieldError("safecode", "请重新登录");
        	return INPUT;
        }
		if(sf.equals(safecode)){
			String t=edu.getType();
			if(t.equals("S")){
			   edu.getRoles().setRoleid(108);
			   edu.setTypeId("1");              //学生的TypeId为"1"
			}else{
				edu.getRoles().setRoleid(109);
				edu.setTypeId("2");             //教师的TypeId为"2"
			}
			
			hs.setAttribute("userifno", edu);
			this.baseService.save(edu);
			if(!t.equals("S")){
				Teacher tt = new Teacher(teajoinNo);//保存注册教师实体到表teacher中
				tt.setUserinfo(edu);
				this.baseService.save(tt);
			}
			
			//类似登录时，为了今后拦截器查看用的
			hs.setAttribute("uid", edu.getUserId());
			hs.setAttribute("pass", edu.getUserPw());
			hs.setAttribute("role", edu.getRoles().getRoleid());
			//===================往main.jsp传mds和als=============
	        userList = this.adminService.findUserFunctions(edu.getRoles().getRoleid());			//根据角色id查出角色的function，调用存储过程
			
			for(int i=0;i<userList.size();i++){						                //对从数据库返回的值遍历
				List list = new ArrayList();
				list = userList.get(i);
				String str = (String) list.get(2);
				
				if(!classname.contains(str)){
					classname.add(str);
				}
			}
			for(int i=0;i<userList.size();i++){						//对从数据库返回的值遍历
				
				List list = userList.get(i);
				String str = (String) list.get(3);
				if(!modulesclassname.contains(str)){
					
					modulesclassname.add(str);
					Moduleclass md = new Moduleclass();
					md.setMclassname(str);
					mds.add(md);									//将admin的功能模块名称放入moculesclassname中
				}
				
			}
			List<String> useraction = new ArrayList<String>();//往session中放的该roleid的所有action 的url
			for(Moduleclass mdc:mds){						//将module放入ActionList中
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
						als.add(al);                      //一个als中放了许多“分类名相同”的al对象
					}
				}
			}                                             //这不完成之后als既有分类名同的也有不同的
			

			useraction.add("registSuccessjsp");//每一个注册用户都有这个权限
			useraction.add("regSuccessjsp");
			
			hs.setAttribute("userifno", edu);
			hs.setAttribute("useraction", useraction);
            hs.setAttribute("mds",mds);
            hs.setAttribute("als",als);
           
			return SUCCESS;
	  }else {
			addFieldError("safecode", "验证码错误！");
			return INPUT;
	  }
		
	}
//	/**
//	 * @{registSuccessjsp}
//	 * @param {/main.jsp} {引入参数说明}
//	 * @return {success} {返回参数说明}
//	 * @{用户成功注册}
//	 * @exception {说明在某情况下,将发生什么异常}
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
	 * @param {login.jsp} {引入参数说明}
	 * @return {返回参数名} {返回参数说明}
	 * @{找回密码}
	 * @exception {说明在某情况下,将发生什么异常}
	*/
	@Action(value="/findPasswd",results={@Result(name="success",location="/page/user/findPPasswd.jsp")})
	public String findPasswd(){
		 return SUCCESS;
	}
	
	/**
	 * @{passwdFind}
	 * @param {login.jsp} {引入参数说明}
	 * @return {返回参数名} {返回参数说明}
	 * @{找回密码}
	 * @exception {说明在某情况下,将发生什么异常}
	*/
	@Action(value="/passwdFind",results={@Result(name="success",location="/page/user/findPasswd.jsp"),
			                              @Result(name="input",location="/page/user/findPPasswd.jsp")})
	public String passwdFind(){
		HttpSession hs = ServletActionContext.getRequest().getSession();
		String sf = (String) hs.getAttribute("rand");
		if(sf == null){
        	addFieldError("safecode", "请重新登录");
        	return INPUT;
        }
		if(sf.equals(safecode)){
			edu=this.baseService.find(Userinfo.class, userid);
			edu.setSafeAnswer(null);
			hs.setAttribute("user", edu);
			safecode=null;
			return SUCCESS;
			
		}else {
			addFieldError("safecode", "验证码错误！");
			return INPUT;
	   }		 
		
	}
	
	/**
	 * @{passwdPFind}
	 * @param {login.jsp} {引入参数说明}
	 * @return {返回参数名} {返回参数说明}
	 * @{找回密码}
	 * @exception {说明在某情况下,将发生什么异常}
	*/
	@Action(value="passwdPFind",results={@Result(name="success",location="/page/user/findPasswd.jsp"),@Result(name="input",location="/page/user/findPasswd.jsp")})
	public String passwdPFind(){
		HttpSession hs = ServletActionContext.getRequest().getSession();
		Userinfo user=(Userinfo) hs.getAttribute("user");
		String sf = (String) hs.getAttribute("rand");
		if(sf == null){
        	addFieldError("safecode", "请重新登录");
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
							addFieldError("safeAnswer", "答案错误！");
							edu=user;
							return INPUT;
						}
			
			
		}else {
						addFieldError("safecode", "验证码错误！");
						edu=user;
						return INPUT;
	   }		 
		
	  
	}
	
	/**
	 * 此action用来处理用户登录，对用户登录进行校验
	 * 校验的内容包括用户名，密码，验证码以及该用户是否登录
	 * 处理方式是用ajax提交，当不符合登录条件时，利用“tip”标示出错信息
	 * 登录时会从前台传来UserInfo的对象 edu
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
						//这里必须再获得session对象，防止this.baseServce.login()方法中因为同一个浏览器先后登录两个不同用户，而把session.invalidate()
						HttpSession hs = ServletActionContext.getRequest().getSession();System.out.println("hs:"+hs.getId());
						hs.setAttribute("uid", edu.getUserId());//登录一块
						hs.setAttribute("userid", userid);//防止this.baseServce.login()方法中因为同一个浏览器先后登录两个不同用户，而把session.invalidate()
						hs.setAttribute("pass", edu.getUserPw());
						hs.setAttribute("role", edu.getRoles().getRoleid());
						hs.setAttribute("userifno", edu);//注册一块 
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
				         System.out.println("访问者ip地址:"+ip);
				         SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
				        
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
//				if(sf.equals(safecode)){									//****验证码问题
//					if(edu!=null){
//						if(userPw.equals(edu.getUserPw())){
//							String str = this.baseService.login(edu);
//							if(str.equals("islogin")){
//								this.tip1="islogin";
//								return "root";
//							}else{
//								//这里必须再获得session对象，防止this.baseServce.login()方法中因为同一个浏览器先后登录两个不同用户，而把session.invalidate()
//								HttpSession hs = ServletActionContext.getRequest().getSession();System.out.println("hs:"+hs.getId());
//								hs.setAttribute("uid", edu.getUserId());//登录一块
//								hs.setAttribute("userid", userid);//防止this.baseServce.login()方法中因为同一个浏览器先后登录两个不同用户，而把session.invalidate()
//								hs.setAttribute("pass", edu.getUserPw());
//								hs.setAttribute("role", edu.getRoles().getRoleid());
//								hs.setAttribute("userifno", edu);//注册一块 
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
	 * 此action用来处理退出登录，当关闭页面时会触动该action
	 * 在该方法中，会将登录时保存到session中的uid，psw，roleid等信息清除
	*/
	@Action(value="/quitLogin",results={@Result(name="success",location="/login1.jsp")})
	public String quitLogin(){
		Cookie[] cookies=ServletActionContext.getRequest().getCookies();
		HttpSession session = ServletActionContext.getRequest().getSession();System.out.println("session2:"+session.getId());
		String uid=(String) session.getAttribute("uid");
		Cookie cookie=null;
		for(int j=0;j<cookies.length;j++){
			System.out.println("有多少----------》"+cookies[j].getValue());
		}
		
		System.out.println("第一个是cookies jsessionid---------->"+cookies[0].getValue()+"长度："+cookies.length);
		if(cookies.length<=2){
			//表示关闭最后一个main.jsp页面（最后一个main.jsp一定是当前用户的cookie）
			for(int i=0;i<cookies.length;i++){
				if(!session.getId().equals(cookies[i].getValue())){
					//表示每次关闭一个main.jsp页面就消除一个非当前用户的cookie（cookie个数与main.jsp页面打开的个数相同），跳出循环
					cookies[i].setMaxAge(0);
					System.out.println("被消掉的cookie----->"+cookies[i].getValue());
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
					//表示每次关闭一个main.jsp页面就消除一个非当前用户的cookie（cookie个数与main.jsp页面打开的个数相同），跳出循环
					cookies[i].setMaxAge(0);
					System.out.println("被消掉的cookie----->"+cookies[i].getValue());
					cookie=cookies[i];
					break;
				}
			}
			
		}
		ServletActionContext.getResponse().addCookie(cookie);
		return SUCCESS;
	} 
	
	/**
	 * 此action用来处理退出登录，当关闭页面时会触动该action
	 * 在该方法中，会将登录时保存到session中的uid，psw，roleid等信息清除
	*/
	@Action(value="/quitLogin1",results={@Result(name="success",type="json")})
	public String quitLogin1(){
		Cookie[] cookies=ServletActionContext.getRequest().getCookies();
		HttpSession session = ServletActionContext.getRequest().getSession();System.out.println("session2:"+session.getId());
		String uid=(String) session.getAttribute("uid");
		Cookie cookie=null;
		for(int j=0;j<cookies.length;j++){
			System.out.println("有多少----------》"+cookies[j].getValue());
		}
		
		System.out.println("第一个是cookies jsessionid---------->"+cookies[0].getValue()+"长度："+cookies.length);
		if(cookies.length<=2){
			//表示关闭最后一个main.jsp页面（最后一个main.jsp一定是当前用户的cookie）
			for(int i=0;i<cookies.length;i++){
				if(!session.getId().equals(cookies[i].getValue())){
					//表示每次关闭一个main.jsp页面就消除一个非当前用户的cookie（cookie个数与main.jsp页面打开的个数相同），跳出循环
					cookies[i].setMaxAge(0);
					System.out.println("被消掉的cookie----->"+cookies[i].getValue());
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
					//表示每次关闭一个main.jsp页面就消除一个非当前用户的cookie（cookie个数与main.jsp页面打开的个数相同），跳出循环
					cookies[i].setMaxAge(0);
					System.out.println("被消掉的cookie----->"+cookies[i].getValue());
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
	 * 该action是在登录的时候用来显示验证码的
	 * 通过返回success，会将image.jsp随机产生的验证码显示到login.jsp页面
	*/
	@Action(value="/imagejsp",results={@Result(name = "success",location="/page/user/image.jsp")})
	public String imagejsp(){
		return SUCCESS;
	}
	
}
