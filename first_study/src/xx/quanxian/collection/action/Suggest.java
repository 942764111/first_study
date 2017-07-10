/*
 *@(#)xx.quanxian.collection.action
 *@Souxuexi.java.java  
 *@创建时间:2011-10-17下午03:04:09
 *@作者：xupengfei
 *@Copyright 2009-2010 河北北方学院信息科学与工程学院科研所 
 */

package xx.quanxian.collection.action;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import xx.adminservice.AdminService;
import xx.quanxian.service.BaseService;

import com.googlecode.jsonplugin.annotations.JSON;
import com.opensymphony.xwork2.ActionSupport;

/**
 * @Suggest <code>{类名称}</code>
 * @author  {作者}
 * @version {版本,常用时间代替}
 * @{功能描述:} 
 */
@Controller
@Scope("prototype")
@Namespace("")
@ParentPackage("default-package")
public class Suggest extends ActionSupport {
	@Resource(name="baseService")
	private BaseService service;
	@Resource(name="adminService")
	private AdminService adminservice;
	private List<String> rows= new ArrayList<String>();
	private List<Map<String,String>> zhishidian= new ArrayList<Map<String,String>>();
	private String q;
	private String radio2;
	@JSON(serialize=false)
	public BaseService getService() {
		return service;
	}
	public void setService(BaseService service) {
		this.service = service;
	}
	@JSON(serialize=false)
	public AdminService getAdminservice() {
		return adminservice;
	}
	public void setAdminservice(AdminService adminservice) {
		this.adminservice = adminservice;
	}
	@JSON(serialize=false)
	public String getQ() {
		return q;
	}
	public void setQ(String q) {
		this.q = q;
	}
	@JSON(serialize=false)
	public String getRadio2() {
		return radio2;
	}
	public void setRadio2(String radio2) {
		this.radio2 = radio2;
	}
	@JSON
	public List<String> getRows() {
		return rows;
	}
	public void setRows(List<String> rows) {
		this.rows = rows;
	}
	
	/**
	 * @return the zhishidian
	 */
	public List<Map<String, String>> getZhishidian() {
		return zhishidian;
	}
	/**
	 * @param zhishidian the zhishidian to set
	 */
	public void setZhishidian(List<Map<String, String>> zhishidian) {
		this.zhishidian = zhishidian;
	}
	/**
	 * @{suggest}
	 * 该方法是调出搜索建议的
	 */
	@Action(value="/suggest",results={@Result(name="success",type="json")})
	public String suggest(){
		String queryword = q;
		try {
			queryword = new String(queryword.getBytes("iso8859-1"),
			"utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		String qw = "%"+queryword+"%";
		int a = Integer.parseInt(radio2);
		System.out.println("查询："+queryword+" radio:"+a);
		List<String> ll = this.adminservice.findsg(a, qw);
		if(ll.size()>0){
			for(String s:ll)
			{
				if(s.length()>0){
					rows.add(s);
//					if(a==1){
//						rows.add(s);
//					}
//					else{
//						String ss[] = new String[2];
//						System.out.println(queryword.length());
//						System.out.println(s.substring(0, queryword.length()));
//						
//						if(queryword.equals(s.substring(0, queryword.length())))
//						{
//							if(s.length()>8){
//								String sss = s.substring(0, 8);
//								rows.add(sss);
//							}
//							else{
//								rows.add(s);
//							}
//						}
//						else{
//							ss = s.split(queryword);
//							System.out.println(ss.length);
//							String sss = queryword+ss[1];
//							if(sss.length()>8)
//							{
//								String ssss = sss.substring(0, 8);
//								rows.add(ssss);
//							}
//							else {
//								rows.add(sss);
//							}
//							
//						}
//						
//					}
					
				}
			}
			
		}
		else {
			rows.add(null);
		}
		return SUCCESS;
	}
	
	@Action(value="/suggest2",results={@Result(name="success",type="json")})
	public String suggest2(){
		String queryword = q;
		
		String qw = "%"+queryword+"%";
		int a = Integer.parseInt(radio2);
		System.out.println("查询："+queryword+" radio:"+a);
		List<String> ll = this.adminservice.findsg(a, qw);
		if(ll.size()>0){
			for(String s:ll)
			{
				if(s.length()>0){
					rows.add(s);
//					if(a==1){
//						rows.add(s);
//					}
//					else{
//						String ss[] = new String[2];
//						System.out.println(queryword.length());
//						System.out.println(s.substring(0, queryword.length()));
//						
//						if(queryword.equals(s.substring(0, queryword.length())))
//						{
//							if(s.length()>8){
//								String sss = s.substring(0, 8);
//								rows.add(sss);
//							}
//							else{
//								rows.add(s);
//							}
//						}
//						else{
//							ss = s.split(queryword);
//							System.out.println(ss.length);
//							String sss = queryword+ss[1];
//							if(sss.length()>8)
//							{
//								String ssss = sss.substring(0, 8);
//								rows.add(ssss);
//							}
//							else {
//								rows.add(sss);
//							}
//							
//						}
//						
//					}
					
				}
			}
			
		}
		else {
			rows.add(null);
		}
		return SUCCESS;
	}
	
	@Action(value="/suggest3",results={@Result(name="success",type="json")})
	public String suggest3() throws UnsupportedEncodingException{
		String queryword = q;
		queryword=new String(q.getBytes("ISO-8859-1"),"utf-8");
		String qw = "%"+queryword+"%";
	
		List<Map<String, String>> ll = this.adminservice.findsg2(qw);
		if(ll.size()>0){
			
			for (int j = 0; j < ll.size(); j++) {
				zhishidian.add(ll.get(j));
			}
	
		}
		else {
			rows.add(null);
		}
		return SUCCESS;
	}
}