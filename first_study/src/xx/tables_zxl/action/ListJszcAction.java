/*
 *@(#)xx.tables_zxl.action
 *@ListJszcAction.java.java  
 *@创建时间:2011-5-18下午12:47:45
 *@作者：zxl
 *@Copyright 2009-2010 河北北方学院信息科学与工程学院科研所 
 */

package xx.tables_zxl.action;

/**
 * @ListJszcAction <code>{类名称}</code>
 * @author  {zxl}
 * @version {版本,常用时间代替}
 * @{教师职称信息的增删改查} 
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
import xx.collection.bean.Jszc;
import xx.collection.bean.Jszcbh;
import xx.collection.bean.Xlxw;
import xx.quanxian.service.BaseService;

import com.googlecode.jsonplugin.annotations.JSON;
import com.opensymphony.xwork2.ActionSupport;
@Controller
@Scope("prototype")
@ParentPackage("default-package")
@Namespace("")
public class ListJszcAction extends ActionSupport {
	
	@Resource(name="baseService")
	private BaseService baseservice;
	
	
	private Jszc jszc;//对象实例  	
	private List<Jszc> rows = new ArrayList<Jszc>();//对象集合    
	private int page;//当前页
	private int rows_s;//每一页显示的条数
	private String zcmc;
	private String zcjb;
	private int zcbh;
	private List<Jszc> jszclist;
	private String queryType;	//查询功能时的查询参数，所要根据查询的类型
	private String queryWord;	//查询功能时的查询参数，所要根据查询的关键字
	private List<String> zcjy = new ArrayList<String>();
	private List<String> zc = new ArrayList<String>();
	private int b;
	private int total1=0;
	private int a;
	
	@JSON(serialize=false)
	public List<String> getZc() {
		return zc;
	}
	public void setZc(List<String> zc) {
		this.zc = zc;
	}
	public int getA() {
		return a;
	}
	public void setA(int a) {
		this.a = a;
	}

	@JSON(serialize=false)
	public List<String> getZcjy() {
		return zcjy;
	}
	public void setZcjy(List<String> zcjy) {
		this.zcjy = zcjy;
	}
	public int getB() {
		return b;
	}
	public void setB(int b) {
		this.b = b;
	}
	@JSON(serialize=false)
	public List<Jszc> getJszclist() {
		return jszclist;
	}
	public void setJszclist(List<Jszc> jszclist) {
		this.jszclist = jszclist;
	}
	@JSON(serialize=false)
	public String getZcmc() {
		return zcmc;
	}
	
	public void setZcmc(String zcmc) {
		this.zcmc = zcmc;
	}
	
	@JSON(serialize=false)
	public String getZcjb() {
		return zcjb;
	}
	
	public void setZcjb(String zcjb) {
		this.zcjb = zcjb;
	}
	
	@JSON(serialize=false)
	public int getZcbh() {
		return zcbh;
	}
	@JSON(deserialize=true)
	public void setZcbh(int zcbh) {
		this.zcbh = zcbh;
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
	public Jszc getJszc() {
		return jszc;
	}
	public void setJszc(Jszc jszc) {
		this.jszc = jszc;
	}
	
	@JSON(serialize=false)
	public BaseService getBaseservice() {
		return baseservice;
	}
	public void setBaseservice(BaseService baseservice) {
		this.baseservice = baseservice;
	}

    public List<Jszc> getRows() {
		return rows;
	}
	public void setRows(List<Jszc> rows) {
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
	 * @{insert.action}
	 * @{添加功能}
	 * @return {/tables_zxl/datagrid_jszc.jsp} {显示所有教师职称信息页面}
	 * @exception {说明在某情况下,将发生什么异常}
	*/
	@Action(value="insert",results={@Result(name="success",type="json")})
	public String insert(){
		Jszc jszc= new Jszc();
		//jszc.setZcbh(zcbh);
		jszc.setZcjb(zcjb);
		jszc.setZcmc(zcmc);
		this.baseservice.save(jszc);//添加数据
		return SUCCESS;
	}
	
	/**
	 * @{jszclist.action}
	 * @param {jszclist} {显示所有教师职称信息}
	 * @return {/tables_zxl/datagrid_jszc.jsp} {显示所有教师职称信息页面}
	 * @{显示所有教师职称信息}
	 * @exception {说明在某情况下,将发生什么异常}
	*/
	@Action(value="/jszclist",results={@Result(name="root",type="json")})
	public String jszclist(){		
        rows = this.baseservice.findAll(Jszc.class,"Jszc", page, rows_s); //把所有数据放在rows里面以实现分页查询    
        total=this.baseservice.getTotal("Jszc");  //记录条数的记录  
        return "root";  
	}
	/**
	 * @{delete.action}
	 * @param {jszclist.action} {显示教师职称信息}
	 * @return {jszclist.action} {显示所有教师职称信息} 
	 * @{删除教师职称信息}
	 * @exception {说明在某情况下,将发生什么异常}
	*/
	@Action(value="delete",results={@Result(name="success",type="json")})
	public String delete(){
		String hql = "from Jszcbh";
		List<Integer> zcbh1=new ArrayList<Integer>();
		List<Jszcbh> hh=this.baseservice.findHql(Jszcbh.class, hql);
		if(hh.size()>0){
			for(int i=0;i<hh.size();i++){
				zcbh1.add(hh.get(i).getId().getZcbh());
			}
			if(!zcbh1.contains(zcbh)){
				jszc=this.baseservice.find(Jszc.class,zcbh);//通过zcbh删除一条数据，zcbh要进行反序列化。
				this.baseservice.delete(jszc);// 删除数据
				a=1;
			}else{
				a=0;
			}
		}else{
			jszc=this.baseservice.find(Jszc.class,zcbh);//通过zcbh删除一条数据，zcbh要进行反序列化。
			this.baseservice.delete(jszc);// 删除数据
			a=1;
		}
		
		
		return SUCCESS;
	}

	/**
	 * @{update}
	 * @param {jszclist.action} {显示教师职称信息}
	 * @return {jszclist.action} {显示所有教师职称信息}
	 * @{修改教师职称信息}
	 * @exception {说明在某情况下,将发生什么异常}
	 */
	@Action(value="update",results={@Result(name="success",type="json")})
	public String update(){
		Jszc jszc= new Jszc();
		jszc.setZcbh(zcbh);
		jszc.setZcjb(zcjb);
		jszc.setZcmc(zcmc);
		this.baseservice.update(jszc);	//更新数据
		return SUCCESS;		
	}	

	//检验查询是否为空,并得出数据条数
	@Action(value="/queryzc1",results={@Result(name="root",type="json")})
	public String queryzc1(){
		String hql = "select count(*) from Jszc where "+queryType+" like '%"+queryWord+"%'";
		total1 = this.baseservice.getTotalSql(hql);
		HttpSession hs = ServletActionContext.getRequest().getSession();
		hs.setAttribute("jszctotal", total1);
		if(total1!=0){
			a=1;
		}else{
			a=0;
		}
		return "root";
	}
	/**
	 * @{queryzc.action}
	 * 该方法是用来根据关键字查询职称信息的，查询时会从页面接收两个参数，queryType，queryWord
	 * queryType是要查询时所根据的类型，queryWord是关键字
	 * 查询出的结果页要以分页的形式显示
	*/
	@Action(value="/queryzc",results={@Result(name="root",type="json")})
	public String queryzc(){
		rows = this.baseservice.findByTypage(Jszc.class, "Jszc", queryType, queryWord, "order by zcbh asc", page, rows_s);//按关键字查询
		HttpSession hs = ServletActionContext.getRequest().getSession();
		total=(Integer)hs.getAttribute("jszctotal");
		return "root";
	}
	
	//页面校验
	@Action(value="/zcjy",results={@Result(name="success",type="json")})
	public String  zcjy(){
		zcjy = this.baseservice.find(String.class, "Jszc", "zcmc");
		if(zcjy.contains(zcmc)){
		b = 0;
		}else{b=1;}
		return SUCCESS;
	}
	
	
	//页面校验
	@Action(value="/zcjy1",results={@Result(name="success",type="json")})
	public String  zcjy1(){
		zcjy = this.baseservice.find(String.class, "Jszc", "zcmc");
		jszc=this.baseservice.find(Jszc.class, zcbh);
		for(int i=0;i<zcjy.size();i++){
			if(!zcjy.get(i).equals(jszc.getZcmc())){
				zc.add(zcjy.get(i));
			}
		}
		if(!zc.contains(zcmc)){
			b=0;
		}else if(!zcjy.contains(zcmc)){
			b=0;
		}else{
			b=1;
		}
		return SUCCESS;
	}
	/**
	 * @{jszc}
	 * @return {/tables_zxl/datagrid_jszc.jsp} {显示教师职称信息}
	 * @{教学  教师职称管理}
	 * 在main.jsp中点击教师职称管理时跳转到datagrid_jszc.jsp
//	 */	
//	@Action(value="/jszc",results={@Result(name="success",location="/tables_zxl/datagrid_jszc.jsp")})
//	public String jszc(){
//		//this.jszclist=this.baseservice.find(Jszc.class);//通过Jszc_simple.class查到所有教师职称信息
//		return SUCCESS;
//	}

}
