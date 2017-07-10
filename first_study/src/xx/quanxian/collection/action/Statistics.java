/*
 *@(#)xx.quanxian.collection.action
 *@Statistics.java.java  
 *@����ʱ��:2011-8-22����03:12:47
 *@���ߣ�xupengfei
 *@Copyright 2009-2010 �ӱ�����ѧԺ��Ϣ��ѧ�빤��ѧԺ������ 
 */

package xx.quanxian.collection.action;

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
import xx.collection.bean.Tjb;
import xx.collection.bean.TjbId;
import xx.quanxian.service.BaseService;

import com.googlecode.jsonplugin.annotations.JSON;
import com.opensymphony.xwork2.ActionSupport;

/**
 * @Statistics <code>{������}</code>
 * @author  {������}
 * @version {2011-8-22����03:12:47}
 * @{ʵ��ͳ�ƹ���} 
 */
@Controller
@Scope("prototype")
@Namespace("")
@ParentPackage("default-package")
public class Statistics extends ActionSupport {
	@Resource(name="baseService")
	private BaseService service;
	@Resource(name="adminService")
	private AdminService adminservice;

	private List<Zytj> rows = new ArrayList<Zytj>();
	private int total;
	private int rows_s;
	private int page;
	@JSON(serialize=false)
	public BaseService getService() {
		return service;
	}
	public void setService(BaseService service) {
		this.service = service;
	}
	public AdminService getAdminservice() {
		return adminservice;
	}
	public void setAdminservice(AdminService adminservice) {
		this.adminservice = adminservice;
	}
	public List<Zytj> getRows() {
		return rows;
	}
	public void setRows(List<Zytj> rows) {
		this.rows = rows;
	}
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	@JSON(serialize=false)
	public int getRows_s() {
		return rows_s;
	}
	public void setRows_s(int rows_s) {
		this.rows_s = rows_s;
	}
	@JSON(serialize=false)
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	/**
	 * ResourceStatistics <code>{ResourceStatistics}</code>
	 * @author  {������}
	 * @version {2011-8-22����03:12:47}
	 * @{ʵ��������Դ��ͳ��д�����ݿ��Tjb��} 
	 */
	@Action(value="/ResourceStatistics",results={@Result(name="success",location="/page/resource/statistics.jsp")})
	public String ResourceStatistics(){
		List<String> ll = this.adminservice.zytjcc();
		for(String s:ll)
		{
			String ss[]=new String[7];
			ss=s.split(",");
			Tjb tjb=new Tjb();
			TjbId tid=new TjbId();
			tid.setCName(ss[0]);
			tid.setZmc(ss[1]);
			tjb.setPdSl(Integer.parseInt(ss[2]));
			tjb.setXzSl(Integer.parseInt(ss[3]));
			tjb.setTkSl(Integer.parseInt(ss[4]));
			tjb.setCztSl(Integer.parseInt(ss[5]));
			tjb.setDmtSl(Integer.parseInt(ss[6]));
			tjb.setZyscSl(Integer.parseInt(ss[7]));
			tjb.setId(tid);
			this.service.save(tjb);
		}
		return SUCCESS;
	}
	
}
