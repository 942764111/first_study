package xx.kgt.action;

/*
 *@(#)xx.testxg.action
 *@ChangeXzAction.java.java  
 *@创建时间:2011-12-1下午02:37:30
 *@作者：zxl
 *@Copyright 2009-2010 河北北方学院信息科学与工程学院科研所 
 */


import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import xx.collection.bean.Xz_sim;
import xx.quanxian.service.BaseService;

import com.googlecode.jsonplugin.annotations.JSON;
import com.opensymphony.xwork2.ActionSupport;

/**
 * @ChangeXzAction <code>{类名称}</code>
 * @author  {张晓莉}
 * @version {版本,常用时间代替}
 * @{功能描述:} 
 */
@Controller
@Scope("prototype")
@ParentPackage("default-package")
@Namespace("")
public class XzdaAction extends ActionSupport {
	
	@Resource(name = "baseService")
	private BaseService baseservice;
	private int page;//当前页
	private int rows_s;//每页显示的条数
	private int total;//记录数据条数
	private List<Xz_sim> rows =new ArrayList<Xz_sim>();
	private int a;
	private String queryType;	//查询功能时的查询参数，所要根据查询的类型
	private String queryWord;	//查询功能时的查询参数，所要根据查询的关键字
	
	@JSON(serialize=false)
	public String getQueryType() {
		return queryType;
	}
	public void setQueryType(String queryType) {
		this.queryType = queryType;
	}
	@JSON(serialize=false)
	public String getQueryWord() {
		return queryWord;
	}
	public void setQueryWord(String queryWord) {
		this.queryWord = queryWord;
	}
	@JSON(serialize=false)
	public BaseService getBaseservice() {
		return baseservice;
	}
	public void setBaseservice(BaseService baseservice) {
		this.baseservice = baseservice;
	}
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public int getRows_s() {
		return rows_s;
	}
	public void setRows_s(int rows_s) {
		this.rows_s = rows_s;
	}
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public List<Xz_sim> getRows() {
		return rows;
	}
	public void setRows(List<Xz_sim> rows) {
		this.rows = rows;
	}
	public int getA() {
		return a;
	}
	public void setA(int a) {
		this.a = a;
	}
	
	
	@Action(value="/xzanswer",results={@Result(name="root",type="json")})
	public String changexz1(){
		rows=this.baseservice.findSql(Xz_sim.class,"from Xz_sim", page, rows_s);
		total = this.baseservice.getTotalSql("select count(*) from Xz_sim");
		return "root";
	}
	
	

	@Action(value="/zyqueryxzjy",results={@Result(name="root",type="json")})
	public String ghqueryxzjy1(){
		HttpSession hs = ServletActionContext.getRequest().getSession();
		String hql = "select count(*) from Xz_sim where "+queryType+" like '%"+queryWord+"%'";
		int to1 = this.baseservice.getTotalSql(hql);
		hs.setAttribute("tempxzto", to1);
		if(to1!=0){
			a=1;
		}else{
			a=0;
		}
		return "root";
	}
	
	
	@Action(value="/zyqueryxz",results={@Result(name="root",type="json")})
	public String ghqueryxz1(){
		HttpSession hs = ServletActionContext.getRequest().getSession();
		total=(Integer)hs.getAttribute("tempxzto");
		rows=this.baseservice.findSql(Xz_sim.class,"from Xz_sim where "+queryType+" like '%"+queryWord+"%'", page, rows_s);
		return "root";
	}

}
