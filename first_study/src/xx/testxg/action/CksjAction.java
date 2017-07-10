/*
 *@(#)xx.testxg.action
 *@CksjAction.java.java  
 *@����ʱ��:2011-10-10����10:13:40
 *@���ߣ�Administrator
 *@Copyright 2009-2010 �ӱ�����ѧԺ��Ϣ��ѧ�빤��ѧԺ������ 
 */

package xx.testxg.action;

import javax.annotation.Resource;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import xx.adminservice.AdminService;
import xx.collection.bean.Sjnr;
import xx.quanxian.service.BaseService;

import com.opensymphony.xwork2.ActionSupport;

/**
 * @CksjAction <code>{������}</code>
 * @author  {ţ��ΰ}
 * @version {�汾,����ʱ�����}
 * @{��������:} 
 */
@Controller
@Scope("prototype")
@ParentPackage("default-package")
@Namespace("")
public class CksjAction extends ActionSupport{

	
	@Resource(name = "baseService")
	private BaseService baseservice;
	@Resource(name = "adminService")
    private AdminService adminservice;
	
	String kcmc;
	String zmc;
	String jmc;
	String sjno;
	
	
	
	
	public BaseService getBaseservice() {
		return baseservice;
	}
	public void setBaseservice(BaseService baseservice) {
		this.baseservice = baseservice;
	}
	public AdminService getAdminservice() {
		return adminservice;
	}
	public void setAdminservice(AdminService adminservice) {
		this.adminservice = adminservice;
	}
	public String getKcmc() {
		return kcmc;
	}
	public void setKcmc(String kcmc) {
		this.kcmc = kcmc;
	}
	public String getZmc() {
		return zmc;
	}
	public void setZmc(String zmc) {
		this.zmc = zmc;
	}
	public String getJmc() {
		return jmc;
	}
	public void setJmc(String jmc) {
		this.jmc = jmc;
	}
	public String getSjno() {
		return sjno;
	}
	public void setSjno(String sjno) {
		this.sjno = sjno;
	}
	
	@Action(value = "/sjno", results = { @Result(name = "success", location="/testxg/sj.jsp") })
	public String sjno(){
		
		return SUCCESS;
	}
	
}
