/*
 *@(#)xx.quanxian.action
 *@StudentFunctions.java.java  
 *@创建时间:2012-3-23下午08:31:59
 *@作者：朱永科
 *@Copyright 2009-2010 河北北方学院信息科学与工程学院科研所 
 */

package xx.quanxian.action;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import xx.adminservice.AdminService;
import xx.collection.bean.Functions;
import xx.collection.bean.Moduleclass;

import com.googlecode.jsonplugin.annotations.JSON;
import com.opensymphony.xwork2.ActionSupport;

/**
 * @StudentFunctions <code>{类名称}</code>
 * @author  {朱永科}
 * @version {版本,常用时间代替}
 * @{功能描述:学生登录时根据角id查出角色的function} 
 */
@Controller
@Scope("prototype")
@Namespace("")
@ParentPackage("default-package")
@SuppressWarnings("serial")
public class StudentFunctions extends ActionSupport {
	
	@Resource(name="adminService")
	private AdminService adminService;
	
	private List<List> adminlist;
	
	//模块分类名称
	private ArrayList<String> modulesclassname = new ArrayList<String>();
	
	//模块名称
	private ArrayList<String> classname = new ArrayList<String>();
	
	private ArrayList<ActionList> als = new ArrayList<ActionList>();
	
	private ArrayList<Moduleclass> mds = new ArrayList<Moduleclass>();
	
	/**
	 * @return the adminService
	 */
	@JSON(serialize=false)
	public AdminService getAdminService() {
		return adminService;
	}
	/**
	 * @param adminService the adminService to set
	 */
	public void setAdminService(AdminService adminService) {
		this.adminService = adminService;
	}

	/**
	 * @return the adminlist
	 */
	@JSON(serialize=false)
	public List<List> getAdminlist() {
		return adminlist;
	}
	/**
	 * @param adminlist the adminlist to set
	 */
	public void setAdminlist(List<List> adminlist) {
		this.adminlist = adminlist;
	}

	/**
	 * @return the modulesclassname
	 */
	public ArrayList<String> getModulesclassname() {
		return modulesclassname;
	}
	/**
	 * @param modulesclassname the modulesclassname to set
	 */
	public void setModulesclassname(ArrayList<String> modulesclassname) {
		this.modulesclassname = modulesclassname;
	}

	/**
	 * @return the classname
	 */
	public ArrayList<String> getClassname() {
		return classname;
	}
	/**
	 * @param classname the classname to set
	 */
	public void setClassname(ArrayList<String> classname) {
		this.classname = classname;
	}

	/**
	 * @return the als
	 */
	public ArrayList<ActionList> getAls() {
		return als;
	}
	/**
	 * @param als the als to set
	 */
	public void setAls(ArrayList<ActionList> als) {
		this.als = als;
	}

	/**
	 * @return the mds
	 */
	public ArrayList<Moduleclass> getMds() {
		return mds;
	}
	/**
	 * @param mds the mds to set
	 */
	public void setMds(ArrayList<Moduleclass> mds) {
		this.mds = mds;
	}

	/**
	 * @{findallfunction}
	 * @param {roleid} {引入参数说明}
	 * @return {用户的function，及function所属于的moduleclassname，modulename} {返回参数说明}
	 * @{显示studentLogin.jsp}
	*/
	@Action(value="/moduleClassFunctions", results={@Result(name="success",type="json")})
	public String moduleClassFunctions() {
		int id = 108;
		List<String> useraction = new ArrayList<String>();//往session中放的该roleid的所有action 的url
		adminlist = this.adminService.findallfunction(id);//根据角色id查出角色的function，调用存储过程
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
		return SUCCESS;
	}
}
