/*
 *@(#)xx.testxg.action
 *@ChangeXzAction.java.java  
 *@创建时间:2011-12-1下午02:37:30
 *@作者：zxl
 *@Copyright 2009-2010 河北北方学院信息科学与工程学院科研所 
 */

package xx.testxg.action;

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
public class ChangeXzAction extends ActionSupport {
	
	@Resource(name = "baseService")
	private BaseService baseservice;
	private int ghth;
	private int page;//当前页
	private int rows_s;//每页显示的条数
	private int total;//记录数据条数
	private List<Xz_sim> rows =new ArrayList<Xz_sim>();
	private String queryType;	//查询功能时的查询参数，所要根据查询的类型
	private String queryWord;	//查询功能时的查询参数，所要根据查询的关键字
	private int a;
	
	@JSON(serialize=false)
	public BaseService getBaseservice() {
		return baseservice;
	}
	public void setBaseservice(BaseService baseservice) {
		this.baseservice = baseservice;
	}
	public int getGhth() {
		return ghth;
	}
	public void setGhth(int ghth) {
		this.ghth = ghth;
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
	public int getA() {
		return a;
	}
	public void setA(int a) {
		this.a = a;
	}
	
	
	//本方法用来出卷完成时改变试卷里的单选题时用
	@Action(value="/changexz1",results={@Result(name="root",type="json")})
	public String changexz1(){
		HttpSession hs = ServletActionContext.getRequest().getSession();
		int zbh1=(Integer)hs.getAttribute("ghzno1");
		int zbh2=(Integer)hs.getAttribute("ghzno2");
		rows=this.baseservice.findSql(Xz_sim.class,"from Xz_sim where zbh>='"+zbh1+"' and zbh<='"+zbh2+"' and ddx=0", page, rows_s);
		total = this.baseservice.getTotalSql("select count(*) from Xz_sim where zbh>='"+zbh1+"' and zbh<='"+zbh2+"'");
		return "root";
	}
	
	

	//检验查询的单选题是否为空,并得出数据条数
	@Action(value="/ghqueryxzjy1",results={@Result(name="root",type="json")})
	public String ghqueryxzjy1(){
		HttpSession hs = ServletActionContext.getRequest().getSession();
		int zbh1=(Integer)hs.getAttribute("ghzno1");
		int zbh2=(Integer)hs.getAttribute("ghzno2");
		String hql = "select count(*) from Xz_sim where "+queryType+" like '%"+queryWord+"%' and zbh>='"+zbh1+"' and zbh<='"+zbh2+"' and ddx=0";
		int to1 = this.baseservice.getTotalSql(hql);
		hs.setAttribute("tempto", to1);
		if(to1!=0){
			a=1;
		}else{
			a=0;
		}
		return "root";
	}
	
	
	//根据ddx、queryType查询单选题
	@Action(value="/ghqueryxz1",results={@Result(name="root",type="json")})
	public String ghqueryxz1(){
		HttpSession hs = ServletActionContext.getRequest().getSession();
		total=(Integer)hs.getAttribute("tempto");
		int zbh1=(Integer)hs.getAttribute("ghzno1");
		int zbh2=(Integer)hs.getAttribute("ghzno2");
		rows=this.baseservice.findSql(Xz_sim.class,"from Xz_sim where "+queryType+" like '%"+queryWord+"%' and zbh>='"+zbh1+"' and zbh<='"+zbh2+"' and ddx=0", page, rows_s);
		return "root";
	}
	
	
	
	//本方法用来出卷完成时改变试卷里的多选题时用
	@Action(value="/changexz2",results={@Result(name="root",type="json")})
	public String changexz2(){
		HttpSession hs = ServletActionContext.getRequest().getSession();
		int zbh1=(Integer)hs.getAttribute("ghzno1");
		int zbh2=(Integer)hs.getAttribute("ghzno2");
		rows=this.baseservice.findSql(Xz_sim.class,"from Xz_sim where zbh>='"+zbh1+"' and zbh<='"+zbh2+"' and ddx=1", page, rows_s);
		total = this.baseservice.getTotalSql("select count(*) from Xz_sim where zbh>='"+zbh1+"' and zbh<='"+zbh2+"'");
		return "root";
	}
	
	

	//检验查询的多选题是否为空,并得出数据条数
	@Action(value="/ghqueryxzjy2",results={@Result(name="root",type="json")})
	public String ghqueryxzjy2(){
		HttpSession hs = ServletActionContext.getRequest().getSession();
		int zbh1=(Integer)hs.getAttribute("ghzno1");
		int zbh2=(Integer)hs.getAttribute("ghzno2");
		String hql = "select count(*) from Xz_sim where "+queryType+" like '%"+queryWord+"%' and zbh>='"+zbh1+"' and zbh<='"+zbh2+"' and ddx=1";
		int to1 = this.baseservice.getTotalSql(hql);
		hs.setAttribute("tempto", to1);
		if(to1!=0){
			a=1;
		}else{
			a=0;
		}
		return "root";
	}
	
	
	//根据ddx、queryType查询多选题
	@Action(value="/ghqueryxz2",results={@Result(name="root",type="json")})
	public String ghqueryxz2(){
		HttpSession hs = ServletActionContext.getRequest().getSession();
		total=(Integer)hs.getAttribute("tempto");
		int zbh1=(Integer)hs.getAttribute("ghzno1");
		int zbh2=(Integer)hs.getAttribute("ghzno2");
		rows=this.baseservice.findSql(Xz_sim.class,"from Xz_sim where "+queryType+" like '%"+queryWord+"%' and zbh>='"+zbh1+"' and zbh<='"+zbh2+"' and ddx=1", page, rows_s);
		return "root";
	}
}
