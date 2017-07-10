/*
 *@(#)xx.quanxian.action
 *@StudentFunctions.java.java  
 *@����ʱ��:2012-3-23����08:31:59
 *@���ߣ�������
 *@Copyright 2009-2010 �ӱ�����ѧԺ��Ϣ��ѧ�빤��ѧԺ������ 
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
 * @StudentFunctions <code>{������}</code>
 * @author  {������}
 * @version {�汾,����ʱ�����}
 * @{��������:ѧ����¼ʱ���ݽ�id�����ɫ��function} 
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
	
	//ģ���������
	private ArrayList<String> modulesclassname = new ArrayList<String>();
	
	//ģ������
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
	 * @param {roleid} {�������˵��}
	 * @return {�û���function����function�����ڵ�moduleclassname��modulename} {���ز���˵��}
	 * @{��ʾstudentLogin.jsp}
	*/
	@Action(value="/moduleClassFunctions", results={@Result(name="success",type="json")})
	public String moduleClassFunctions() {
		int id = 108;
		List<String> useraction = new ArrayList<String>();//��session�зŵĸ�roleid������action ��url
		adminlist = this.adminService.findallfunction(id);//���ݽ�ɫid�����ɫ��function�����ô洢����
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
		return SUCCESS;
	}
}
