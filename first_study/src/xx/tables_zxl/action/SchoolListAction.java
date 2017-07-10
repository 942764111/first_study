/*
 * @(#)xx.tables_zxl.action.{file_name} 
 * @SchoollistAction.java.java 
 * @ 创建时间：2011-4-18     
 * @ 作者：zxl
 * @ Copyright 2009-2010 河北北方学院信息科学与工程学院科研所 
 */

package xx.tables_zxl.action;



/**
 * @ListJszcAction <code>{类名称}</code>
 * @author  {zxl}
 * @version {版本,常用时间代替}
 * @{学校信息的增删改查} 
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

import xx.collection.bean.CourseChapter;
import xx.collection.bean.Schoollist;
import xx.collection.bean.Xlxw;
import xx.collection.bean.Xlxwbh;
import xx.quanxian.service.BaseService;

import com.googlecode.jsonplugin.annotations.JSON;
import com.opensymphony.xwork2.ActionSupport;
@Controller
@Scope("prototype")
@ParentPackage("default-package")
@Namespace("")
public class SchoolListAction extends ActionSupport {	
	
	@Resource(name="baseService")
	private BaseService baseservice;
	
	
	private Schoollist schoollist;
	private List<Schoollist> rows = new ArrayList<Schoollist>();
	private int page;//当前页
	private int rows_s;//每页显示的条数
	private List<Schoollist> xxlist;
	private String xxmc;
	private String xxms;
	private int xxbh;
	private String queryType;	//查询功能时的查询参数，所要根据查询的类型
	private String queryWord;	//查询功能时的查询参数，所要根据查询的关键字
	private List<String> xxjy = new ArrayList<String>();
	private List<String> xx = new ArrayList<String>();
	private int b;
	private int a;
    private int total=0; //记录数量  
    private int total1=0;
 
	
	
	public String getQueryType() {
		return queryType;
	}

	public void setQueryType(String queryType) {
		this.queryType = queryType;
	}
	public String getQueryWord() {
		return queryWord;
	}

	public void setQueryWord(String queryWord) {
		this.queryWord = queryWord;
	}

	@JSON(serialize=false)
	public List<Schoollist> getXxlist() {
		return xxlist;
	}

	public void setXxlist(List<Schoollist> xxlist) {
		this.xxlist = xxlist;
	}
	@JSON(serialize=false)
	public List<String> getXx() {
		return xx;
	}

	public void setXx(List<String> xx) {
		this.xx = xx;
	}

	@JSON(serialize=false)
	public String getXxmc() {
		return xxmc;
	}
	
	public void setXxmc(String xxmc) {
		this.xxmc = xxmc;
	}
	
	@JSON(serialize=false)
	public String getXxms() {
		return xxms;
	}
	
	public void setXxms(String xxms) {
		this.xxms = xxms;
	}
	
	@JSON(serialize=false)
	public int getXxbh() {
		return xxbh;
	}
	@JSON(deserialize=true)
	public void setXxbh(int xxbh) {
		this.xxbh = xxbh;
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

	@JSON(serialize=false)
	public BaseService getBaseservice() {
		return baseservice;
	}
	
	@JSON(serialize=false)
	public Schoollist getSchoollist() {
		return schoollist;
	}

	public void setSchoollist(Schoollist schoollist) {
		this.schoollist = schoollist;
	}


	public List<Schoollist> getRows() {
		return rows;
	}

	public void setRows(List<Schoollist> rows) {
		this.rows = rows;
	}

	public void setBaseservice(BaseService baseservice) {
		this.baseservice = baseservice;
	}
   
    public int getTotal() {  
        return total;  
    }  
    public void setTotal(int total) {  
        this.total = total;  
    }  
    @JSON(serialize=false)
	public List<String> getXxjy() {
		return xxjy;
	}

	public void setXxjy(List<String> xxjy) {
		this.xxjy = xxjy;
	}

	public int getB() {
		return b;
	}

	public void setB(int b) {
		this.b = b;
	}
	
	public int getA() {
		return a;
	}

	public void setA(int a) {
		this.a = a;
	}


	/**
	 * @{insertxx.action}
	 * @param {schoollist} {显示学校信息}
	 * @return {schoollist} {显示学校信息}
	 * @{添加学校信息}
	 * @exception {说明在某情况下,将发生什么异常}
	*/
	@Action(value="insertxx",results={@Result(name="success",type="json")})
	public String insertxx(){
		Schoollist schoollist = new Schoollist();
		//schoollist.setXxbh(xxbh);
		schoollist.setXxmc(xxmc);
		schoollist.setXxms(xxms);
		this.baseservice.save(schoollist);
		return SUCCESS;
	}
	
	
	/**
	 * @{schoollist.action}
	 * @param {schoollist} {显示学校信息}
	 * @return {/tables_zxl/datagrid_schoollist.jsp} {显示学校信息页面}
	 * @{显示学校信息}
	 * @exception {说明在某情况下,将发生什么异常}
	*/
	@Action(value="/schoollist",results={@Result(name="root",type="json")})
	public String schoollist(){		
        rows=this.baseservice.findAll(Schoollist.class, "Schoollist", page, rows_s);//分页查询
        total=this.baseservice.getTotal("Schoollist");        //记录条数的记录  
        return "root";  
	}
	
	
	//检验查询是否为空,并得出数据条数
	@Action(value="/queryschool1",results={@Result(name="root",type="json")})
	public String queryschool1(){
		String hql = "select count(*) from Schoollist where "+queryType+" like '%"+queryWord+"%'";
		total1 = this.baseservice.getTotalSql(hql);
		HttpSession hs = ServletActionContext.getRequest().getSession();
		hs.setAttribute("schooltotal", total1);
		if(total1!=0){
			a=1;
		}else{
			a=0;
		}
		return "root";
	}
	
	/**
	 * @{queryschool.action}
	 * 该方法是用来根据关键字查询学校信息的，查询时会从页面接收两个参数，queryType，queryWord
	 * queryType是要查询时所根据的类型，queryWord是关键字
	 * 查询出的结果页要以分页的形式显示
	*/
	@Action(value="/queryschool",results={@Result(name="root",type="json")})
	public String queryschool(){
		rows = this.baseservice.findByTypage(Schoollist.class, "Schoollist", queryType, queryWord, "order by xxbh asc", page, rows_s);//按关键字查询
		HttpSession hs = ServletActionContext.getRequest().getSession();
		total=(Integer)hs.getAttribute("schooltotal");
		return "root";
	}
	
	/**
	 * @{deletexx.action}
	 * @param {schoollist} {显示学校信息}
	 * @return {schoollist.action} {显示学校信息}
	 * @{删除学校信息}
	 * @exception {说明在某情况下,将发生什么异常}
	*/
	@Action(value="deletexx",results={@Result(name="success",type="json")})
	public String deletexx(){
		String hql ="from Xlxwbh";
		List<Xlxwbh> xlbh2=this.baseservice.findHql(Xlxwbh.class, hql);
		List<Integer> xlbh1=new ArrayList<Integer>();
		if(xlbh2.size()>0){
			for(int i=0;i<xlbh2.size();i++){
				xlbh1.add(xlbh2.get(i).getSchoollist().getXxbh());
			}
			if(!xlbh1.contains(xxbh)){
				schoollist=this.baseservice.find(Schoollist.class,xxbh);
				this.baseservice.delete(schoollist);
				b=1;
			}else{
				b=0;
			}
		}else{
			schoollist=this.baseservice.find(Schoollist.class,xxbh);
			this.baseservice.delete(schoollist);
			b=1;
		}
		return SUCCESS;
	}

	
	/**
	 * @{updatexx}
	 * @param {schoollist} {显示学校信息}
	 * @return {schoollist.action} {显示学校信息}
	 * @{修改学校信息}
	 * @exception {说明在某情况下,将发生什么异常}
	*/
	@Action(value="updatexx",results={@Result(name="success",type="json")})
	public String updatexx(){
		Schoollist schoollist = new Schoollist();
		schoollist.setXxbh(xxbh);
		schoollist.setXxmc(xxmc);
		schoollist.setXxms(xxms);
		this.baseservice.update(schoollist);	
		return SUCCESS;		
	}	
	
	
	//页面校验
	@Action(value="/xxjy",results={@Result(name="success",type="json")})
	public String  xxjy(){
		xxjy = this.baseservice.find(String.class, "Schoollist", "xxmc");
		if(xxjy.contains(xxmc)){
		b = 0;
		}else{b=1;}
		return SUCCESS;
	}
	//修改信息校验
	@Action(value="/xxjy1",results={@Result(name="success",type="json")})
	public String  xxjy1(){
		xxjy = this.baseservice.find(String.class, "Schoollist", "xxmc");
		schoollist=this.baseservice.find(Schoollist.class, xxbh);
		for(int i=0;i<xxjy.size();i++){
			if(!xxjy.get(i).equals(schoollist.getXxmc())){
				xx.add(xxjy.get(i));
			}
		}
		if(!xx.contains(xxmc)){
			b=0;
		}else if(!xxjy.contains(xxmc)){
			b=0;
		}else{
			b=1;
		}
		
		return SUCCESS;
	}
	/**
	 * @{zyxx}
	 * @return {/tables_zxl/datagrid_schoollist.jsp} {显示学校信息}
	 * @{教学  学校信息管理}
	 */	
//	@Action(value="xxlb",results={@Result(name="success",location="/tables_zxl/datagrid_schoollist.jsp")})
//	public String xxlb(){
//		//this.xxlist=this.baseservice.find(Schoollist.class);//通过Schoollist_simple.class查询所有数据并放在xxlist中。
//		return SUCCESS;
//	}
}
