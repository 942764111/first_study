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
import xx.collection.bean.Functions;
import xx.collection.bean.Moduleclass;
import xx.collection.bean.Rolefunction;
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
@ParentPackage("login-default")
@SuppressWarnings("serial")
public class AdminFunctions extends ActionSupport {
	
	@Resource(name="baseService")
	private BaseService baseService;
	@Resource(name="adminService")
	private AdminService adminService;
	private Userinfo edu;
	private List<List> adminlist;
	private List<String> modulesclassname = new ArrayList<String>();
	private List<String> classname = new ArrayList<String>();
	private List<ActionList> als = new ArrayList<ActionList>();
	private List<Moduleclass> mds = new ArrayList<Moduleclass>();
	private String username;
	private String userlogin;
	private String userid;
	
	private String main;
	
	
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@JSON(serialize=false)
	public BaseService getBaseService() {
		return baseService;
	}

	public void setBaseService(BaseService baseService) {
		this.baseService = baseService;
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
	 * @return the main
	 */
	public String getMain() {
		return main;
	}

	/**
	 * @param main the main to set
	 */
	public void setMain(String main) {
		this.main = main;
	}

	/**
	 * @{findallfunction}
	 * @param {roleid} {引入参数说明}
	 * @return {用户的function，及function所属于的moduleclassname，modulename} {返回参数说明}
	 * @{显示main.jsp}
	 * studentLogin.jsp为学生登录所返回的jsp页面
	*/
	@Action(value="/adminfunctions",results={@Result(name="success",location="/main.jsp"),
			                                @Result(name="login",location="/login1.jsp"),
			                                @Result(name="studentLogin",type="json")})
	public String adminfunctions(){
		if("login1".equals(userlogin)) {
			String role = "";
			List<String> useraction = new ArrayList<String>();//往session中放的该roleid的所有action 的url
			HttpSession hs = ServletActionContext.getRequest().getSession();//qiang
			
			
			int id = (Integer)hs.getAttribute("role");
			String hql=null;
			
			//根据id来判断是否为学生角色
			if(id==108) {
				role = "studentLogin";
				//学生登录时不用先进入jsjh_tu_stu.action,所以在这里存session
		        hs.setAttribute("kkch", 0);hs.setAttribute("wword", "");
				//若是学生，从studentinfo表中查其姓名
				hql="select SName from Studentifno where userinfo.userId='" + userid + "'";
				
				useraction.add("userAction");
			} else {
				userid = (String)hs.getAttribute("userid");
				hql="select jsxm from Teacher where userinfo.userId='" + userid + "'";
				if("teacher".equals(main)) {
					//若是老师，从teacher表中查其姓名
					adminlist = this.adminService.findallfunction(id);//根据角色id查出角色的function，调用存储过程
					System.out.println("问题!");					
					for(int i=0;i<adminlist.size();i++){						//对从数据库返回的值遍历
						List list = new ArrayList();
						list = adminlist.get(i);
						String str = (String) list.get(1);
						
						if(!classname.contains(str)){
							classname.add(str);
						}
					}
					for(int i=0;i<adminlist.size();i++){						//对从数据库返回的值遍历
						
						List list = adminlist.get(i);
						String str = (String) list.get(0);
						if(!modulesclassname.contains(str)){
							
							modulesclassname.add(str);
							Moduleclass md = new Moduleclass();
							md.setMclassname(str);
							mds.add(md);									//将admin的功能模块名称放入moculesclassname中
						}
						
					}
					
					for(Moduleclass mdc:mds){						//将module放入ActionList中
						for(String gn:classname){
							ActionList al=new ActionList();
							List<Functions> fs=new ArrayList<Functions>();
							for(List list:adminlist){
								if(list.get(1).equals(gn)&&list.get(0).equals(mdc.getMclassname())){
									Functions ft = new Functions();
									ft.setActionname((String)list.get(3));
									ft.setFunctionname((String)list.get(2));			
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
								als.add(al);
							}
						}
					}
					role = "success";
				} else {
					role = "studentLogin";
					main = "teacher";
				}
				
			}
 	        //用户姓名username
			try {
				username=this.baseService.findHql(String.class, hql).get(0);
			} catch (Exception e) {
				
			}
			System.out.println("adminfunctions:"+hs.getId());
			
			hs.setAttribute("useraction", useraction);
			//往客户端返回一个cookie
			Cookie cookie=new Cookie((String)hs.getAttribute("uid"),(String)hs.getAttribute("uid"));
			ServletActionContext.getResponse().addCookie(cookie);
			return role;
		} else {
			if(edu==null) { 
				return "login";
			} else {
				String role = "";
				List<String> useraction = new ArrayList<String>();//往session中放的该roleid的所有action 的url
				HttpSession hs = ServletActionContext.getRequest().getSession();//qiang
				
				
				int id = (Integer)hs.getAttribute("role");
				String hql=null;
				
				//根据id来判断是否为学生角色
				if(id==108) {
					role = "studentLogin";
					//学生登录时不用先进入jsjh_tu_stu.action,所以在这里存session
			        hs.setAttribute("kkch", 0);hs.setAttribute("wword", "");
					//若是学生，从studentinfo表中查其姓名
					hql="select SName from Studentifno where userinfo.userId='"+edu.getUserId()+"'";
				} else {
					//若是老师，从teacher表中查其姓名
					hql="select jsxm from Teacher where userinfo.userId='"+edu.getUserId()+"'";
					
					adminlist = this.adminService.findallfunction(id);//根据角色id查出角色的function，调用存储过程
					System.out.println("问题!");					
					for(int i=0;i<adminlist.size();i++){						//对从数据库返回的值遍历
						List list = new ArrayList();
						list = adminlist.get(i);
						String str = (String) list.get(1);
						
						if(!classname.contains(str)){
							classname.add(str);
						}
					}
					for(int i=0;i<adminlist.size();i++){						//对从数据库返回的值遍历
						
						List list = adminlist.get(i);
						String str = (String) list.get(0);
						if(!modulesclassname.contains(str)){
							
							modulesclassname.add(str);
							Moduleclass md = new Moduleclass();
							md.setMclassname(str);
							mds.add(md);									//将admin的功能模块名称放入moculesclassname中
						}
						
					}
					
					for(Moduleclass mdc:mds){						//将module放入ActionList中
						for(String gn:classname){
							ActionList al=new ActionList();
							List<Functions> fs=new ArrayList<Functions>();
							for(List list:adminlist){
								if(list.get(1).equals(gn)&&list.get(0).equals(mdc.getMclassname())){
									Functions ft = new Functions();
									ft.setActionname((String)list.get(3));
									ft.setFunctionname((String)list.get(2));			
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
								als.add(al);
							}
						}
					}
					role = "success";
				}
	 	        //用户姓名username
				username=this.baseService.findHql(String.class, hql).get(0);
				System.out.println("adminfunctions:"+hs.getId());
				
				hs.setAttribute("useraction", useraction);
				//往客户端返回一个cookie
				Cookie cookie=new Cookie((String)hs.getAttribute("uid"),(String)hs.getAttribute("uid"));
				ServletActionContext.getResponse().addCookie(cookie);
				return role;
			}
		}
		
	}
}

