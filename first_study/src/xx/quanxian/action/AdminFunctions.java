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
 * @LoginAction <code>{������}</code>
 * @author  {guoqiang}
 * @version {�汾,����ʱ�����}
 * @{...��������} 
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
	 * @param {roleid} {�������˵��}
	 * @return {�û���function����function�����ڵ�moduleclassname��modulename} {���ز���˵��}
	 * @{��ʾmain.jsp}
	 * studentLogin.jspΪѧ����¼�����ص�jspҳ��
	*/
	@Action(value="/adminfunctions",results={@Result(name="success",location="/main.jsp"),
			                                @Result(name="login",location="/login1.jsp"),
			                                @Result(name="studentLogin",type="json")})
	public String adminfunctions(){
		if("login1".equals(userlogin)) {
			String role = "";
			List<String> useraction = new ArrayList<String>();//��session�зŵĸ�roleid������action ��url
			HttpSession hs = ServletActionContext.getRequest().getSession();//qiang
			
			
			int id = (Integer)hs.getAttribute("role");
			String hql=null;
			
			//����id���ж��Ƿ�Ϊѧ����ɫ
			if(id==108) {
				role = "studentLogin";
				//ѧ����¼ʱ�����Ƚ���jsjh_tu_stu.action,�����������session
		        hs.setAttribute("kkch", 0);hs.setAttribute("wword", "");
				//����ѧ������studentinfo���в�������
				hql="select SName from Studentifno where userinfo.userId='" + userid + "'";
				
				useraction.add("userAction");
			} else {
				userid = (String)hs.getAttribute("userid");
				hql="select jsxm from Teacher where userinfo.userId='" + userid + "'";
				if("teacher".equals(main)) {
					//������ʦ����teacher���в�������
					adminlist = this.adminService.findallfunction(id);//���ݽ�ɫid�����ɫ��function�����ô洢����
					System.out.println("����!");					
					for(int i=0;i<adminlist.size();i++){						//�Դ����ݿⷵ�ص�ֵ����
						List list = new ArrayList();
						list = adminlist.get(i);
						String str = (String) list.get(1);
						
						if(!classname.contains(str)){
							classname.add(str);
						}
					}
					for(int i=0;i<adminlist.size();i++){						//�Դ����ݿⷵ�ص�ֵ����
						
						List list = adminlist.get(i);
						String str = (String) list.get(0);
						if(!modulesclassname.contains(str)){
							
							modulesclassname.add(str);
							Moduleclass md = new Moduleclass();
							md.setMclassname(str);
							mds.add(md);									//��admin�Ĺ���ģ�����Ʒ���moculesclassname��
						}
						
					}
					
					for(Moduleclass mdc:mds){						//��module����ActionList��
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
 	        //�û�����username
			try {
				username=this.baseService.findHql(String.class, hql).get(0);
			} catch (Exception e) {
				
			}
			System.out.println("adminfunctions:"+hs.getId());
			
			hs.setAttribute("useraction", useraction);
			//���ͻ��˷���һ��cookie
			Cookie cookie=new Cookie((String)hs.getAttribute("uid"),(String)hs.getAttribute("uid"));
			ServletActionContext.getResponse().addCookie(cookie);
			return role;
		} else {
			if(edu==null) { 
				return "login";
			} else {
				String role = "";
				List<String> useraction = new ArrayList<String>();//��session�зŵĸ�roleid������action ��url
				HttpSession hs = ServletActionContext.getRequest().getSession();//qiang
				
				
				int id = (Integer)hs.getAttribute("role");
				String hql=null;
				
				//����id���ж��Ƿ�Ϊѧ����ɫ
				if(id==108) {
					role = "studentLogin";
					//ѧ����¼ʱ�����Ƚ���jsjh_tu_stu.action,�����������session
			        hs.setAttribute("kkch", 0);hs.setAttribute("wword", "");
					//����ѧ������studentinfo���в�������
					hql="select SName from Studentifno where userinfo.userId='"+edu.getUserId()+"'";
				} else {
					//������ʦ����teacher���в�������
					hql="select jsxm from Teacher where userinfo.userId='"+edu.getUserId()+"'";
					
					adminlist = this.adminService.findallfunction(id);//���ݽ�ɫid�����ɫ��function�����ô洢����
					System.out.println("����!");					
					for(int i=0;i<adminlist.size();i++){						//�Դ����ݿⷵ�ص�ֵ����
						List list = new ArrayList();
						list = adminlist.get(i);
						String str = (String) list.get(1);
						
						if(!classname.contains(str)){
							classname.add(str);
						}
					}
					for(int i=0;i<adminlist.size();i++){						//�Դ����ݿⷵ�ص�ֵ����
						
						List list = adminlist.get(i);
						String str = (String) list.get(0);
						if(!modulesclassname.contains(str)){
							
							modulesclassname.add(str);
							Moduleclass md = new Moduleclass();
							md.setMclassname(str);
							mds.add(md);									//��admin�Ĺ���ģ�����Ʒ���moculesclassname��
						}
						
					}
					
					for(Moduleclass mdc:mds){						//��module����ActionList��
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
	 	        //�û�����username
				username=this.baseService.findHql(String.class, hql).get(0);
				System.out.println("adminfunctions:"+hs.getId());
				
				hs.setAttribute("useraction", useraction);
				//���ͻ��˷���һ��cookie
				Cookie cookie=new Cookie((String)hs.getAttribute("uid"),(String)hs.getAttribute("uid"));
				ServletActionContext.getResponse().addCookie(cookie);
				return role;
			}
		}
		
	}
}

