/*
 * @(#)xx.tables_zxl.action.{file_name} 
 * @ListXlxwAction.java.java 
 * @ 创建时间：2011-4-18     
 * @ 作者：zxl
 * @ Copyright 2009-2010 河北北方学院信息科学与工程学院科研所 
 */
package xx.tables_zxl.action;


/**
 * @ListJszcAction <code>{类名称}</code>
 * @author  {zxl}
 * @version {版本,常用时间代替}
 * @{学历学位信息的增删改查} 
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

import xx.collection.bean.Xlxw;
import xx.collection.bean.Xlxwbh;

import xx.quanxian.service.BaseService;

import com.googlecode.jsonplugin.annotations.JSON;

import com.opensymphony.xwork2.ActionSupport;
@Controller
@Scope("prototype")
@ParentPackage("default-package")
@Namespace("")
public class ListXlxwAction extends ActionSupport {	
	
	@Resource(name="baseService")
	private BaseService baseservice;
	
	private List<Xlxw> xlxwlist;
	private Xlxw xlxw;
	private List<Xlxw> rows = new ArrayList<Xlxw>();
	private int page;//当前页
	private int rows_s;//每页显示的条数
	private String xlmc;
	private String type;	  
    private int xlbh;
    private String queryType;	//查询功能时的查询参数，所要根据查询的类型
	private String queryWord;	//查询功能时的查询参数，所要根据查询的关键字
	private List<String> xljy = new ArrayList<String>();
	private List<String> xx = new ArrayList<String>();
	private int b;
	private int a;
	private int total1;
   
	
	public int getA() {
		return a;
	}
	public void setA(int a) {
		this.a = a;
	}
	
	@JSON(serialize=false)
    public List<String> getXljy() {
		return xljy;
	}
	public void setXljy(List<String> xljy) {
		this.xljy = xljy;
	}
	@JSON(serialize=false)
	public List<String> getXx() {
		return xx;
	}
	public void setXx(List<String> xx) {
		this.xx = xx;
	}
	public int getB() {
		return b;
	}
	public void setB(int b) {
		this.b = b;
	}
	@JSON(serialize=false)
	public int getXlbh() {
		return xlbh;
	}
    @JSON(deserialize=true)
	public void setXlbh(int xlbh) {
		this.xlbh = xlbh;
	}
    
	@JSON(serialize=false)
	public String getXlmc() {
		return xlmc;
	}
	
	public void setXlmc(String xlmc) {
		this.xlmc = xlmc;
	}
	
	@JSON(serialize=false)
	public String getType() {
		return type;
	}
	
	public void setType(String type) {
		this.type = type;
	}
	@JSON(serialize=false)
	public List<Xlxw> getXlxwlist() {
		return xlxwlist;
	}
	public void setXlxwlist(List<Xlxw> xlxwlist) {
		this.xlxwlist = xlxwlist;
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
	public void setBaseservice(BaseService baseservice) {
		this.baseservice = baseservice;
	}
	
	@JSON(serialize=false)
	public Xlxw getXlxw() {
		return xlxw;
	}
	
	public void setXlxw(Xlxw xlxw) {
		this.xlxw = xlxw;
	}

    public List<Xlxw> getRows() {
		return rows;
	}
	public void setRows(List<Xlxw> rows) {
		this.rows = rows;
	}

	//记录数量  
    private int total=0;  
     
 
    public int getTotal() {  
        return total;  
    }  
    public void setTotal(int total) {  
        this.total = total;  
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
	/**
	 * @{insertxlxw.action}
	 * @param {xlxwlist} {显示学历学位信息}
	 * @return {xlxwlist} {显示学历学位信息}
	 * @{添加学历学位信息}
	 * @exception {说明在某情况下,将发生什么异常}
	*/
	@Action(value="insertxlxw",results={@Result(name="success",type="json")})
	public String insertxlxw(){	
		Xlxw xlxw = new Xlxw();		
		//xlxw.setXlbh(xlbh);
		xlxw.setXlmc(xlmc);		
		xlxw.setType(type);
		this.baseservice.save(xlxw);//添加数据
		return SUCCESS;
	}
	
	/**
	 * @{xlxwlist.action}
	 * @param {xlxwlist} {显示学历学位信息}
	 * @return {/tables_zxl/datagrid_xlxw.jsp} {显示学历学位信息页面}
	 * @{显示功能}
	 * @exception {说明在某情况下,将发生什么异常}
	*/
	@Action(value="/xlxwlist",results={@Result(name="root",type="json")})
	public String xlxwlist(){
		rows=this.baseservice.findAll(Xlxw.class, "Xlxw", page, rows_s);//把所有数据放在rows里面以实现分页查询    
        total=this.baseservice.getTotal("Xlxw") ;  //记录条数的记录    
        return "root";  
	}
	/**
	 * @{deletexlxw.action}
	 * @param {xlxwlist.action} {显示学历学位信息}
	 * @return {xlxwlist.action} {显学历学位信息}
	 * @{删除学历学位信息}
	 * @exception {说明在某情况下,将发生什么异常}
	*/
	@Action(value="deletexlxw",results={@Result(name="success",type="json")})
	public String deletexlxw(){
		String hql ="from Xlxwbh";
		List<Xlxwbh> xlbh2=this.baseservice.findHql(Xlxwbh.class, hql);
		List<Integer> xlbh1=new ArrayList<Integer>();
		if(xlbh2.size()>0){
			for(int i=0;i<xlbh2.size();i++){
				xlbh1.add(xlbh2.get(i).getXlxw().getXlbh());
			}
			if(!xlbh1.contains(xlbh)){
				xlxw=this.baseservice.find(Xlxw.class,xlbh);//通过xlbh删除一条数据，xlbh要进行反序列化。
				this.baseservice.delete(xlxw);//删除数据
				b=1;
			}else{
				b=0;
			}
		}else{
			xlxw=this.baseservice.find(Xlxw.class,xlbh);//通过xlbh删除一条数据，xlbh要进行反序列化。
			this.baseservice.delete(xlxw);//删除数据
			b=1;
		}
		
		
		return SUCCESS;
	}

	/**
	 * @{updatexlxw}
	 * @param {xlxwlist.action} {显示学历学位信息}
	 * @return {xlxwlist.action} {显示学历学位信息}
	 * @{修改学历学位信息}
	 * @exception {说明在某情况下,将发生什么异常}
	*/
	@Action(value="updatexlxw",results={@Result(name="success",type="json")})
	public String updatexlxw(){
		Xlxw xlxw = new Xlxw();		
		xlxw.setXlbh(xlbh);
		xlxw.setXlmc(xlmc);		
		xlxw.setType(type);
		this.baseservice.update(xlxw);	//更新数据
		return SUCCESS;		
	}	
	//检验查询是否为空,并得出数据条数
	@Action(value="/queryxl1",results={@Result(name="root",type="json")})
	public String queryxl1(){
		String hql = "select count(*) from Xlxw where "+queryType+" like '%"+queryWord+"%'";
		total1 = this.baseservice.getTotalSql(hql);
		HttpSession hs = ServletActionContext.getRequest().getSession();
		hs.setAttribute("xlxwtotal", total1);
		if(total1!=0){
			a=1;
		}else{
			a=0;
		}
		return "root";
	}
	/**
	 * @{queryxl.action}
	 * 该方法是用来根据关键字查询学历信息的，查询时会从页面接收两个参数，queryType，queryWord
	 * queryType是要查询时所根据的类型，queryWord是关键字
	 * 查询出的结果页要以分页的形式显示
	*/
	@Action(value="/queryxl",results={@Result(name="root",type="json")})
	public String queryxl(){
		rows = this.baseservice.findByTypage(Xlxw.class, "Xlxw", queryType, queryWord, "order by xlbh asc", page, rows_s);//按关键字查询
		HttpSession hs = ServletActionContext.getRequest().getSession();
		total=(Integer)hs.getAttribute("xlxwtotal");
		return "root";
	}
	
	
	//页面校验
	@Action(value="/xljy",results={@Result(name="success",type="json")})
	public String  xljy(){
		xljy = this.baseservice.find(String.class, "Xlxw", "xlmc");
		if(xljy.contains(xlmc)){
		b = 0;
		}else{b=1;}
		return SUCCESS;
	}
	//修改信息校验
	@Action(value="/xljy1",results={@Result(name="success",type="json")})
	public String  xljy1(){
		xljy = this.baseservice.find(String.class, "Xlxw", "xlmc");
		xlxw=this.baseservice.find(Xlxw.class, xlbh);
		for(int i=0;i<xljy.size();i++){
			if(!xljy.get(i).equals(xlxw.getXlmc())){
				xx.add(xljy.get(i));
			}
		}
		if(!xx.contains(xlmc)){
			b=0;
		}else if(!xljy.contains(xlmc)){
			b=0;
		}else{
			b=1;
		}
		
		return SUCCESS;
	}
	/**
	 * @{xlxw}
	 * @return {/tables_zxl/datagrid_xlxw.jsp} {显示学历学位信息}
	 * @{教学  学历学位管理}
	 */	
//	@Action(value="xlxw",results={@Result(name="success",location="/tables_zxl/datagrid_xlxw.jsp")})
//	public String xlxw(){
//		//this.xlxwlist = this.baseservice.find(Xlxw.class);//通过Xlxw_simple.class查询所有数据并放在xlxwlist中。
//		return SUCCESS;
//	}
}
