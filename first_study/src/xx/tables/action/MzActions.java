/*
 *@(#)xx.tables.action
 *@MzActions.java.java  
 *@创建时间:2011-5-18上午09:29:14
 *@作者：ylj
 *@Copyright 2009-2010 河北北方学院信息科学与工程学院科研所 
 */

package xx.tables.action;
/**
 * @MzActions <code>{Mz_simple}</code>
 * @author  {ylj}
 * @version {2011-5-18上午09:29:14}
 * @{实现民族信息表的增删改查} 
 */

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import xx.collection.bean.Mz;

import xx.quanxian.service.BaseService;

import com.googlecode.jsonplugin.annotations.JSON;
import com.opensymphony.xwork2.ActionSupport;
@Controller
@Scope("prototype")
@Namespace("")
@ParentPackage("default-package")
@SuppressWarnings("serial")
public class MzActions extends ActionSupport {
	
	@Resource(name="baseService")
	private BaseService baseService;
	private Mz mz;
	private List<Mz> rows=new ArrayList<Mz>();
	private List<String> mzjy=new ArrayList<String>();
	private int page;//记录分页
	private int rows_s;
	private String mzmc;
	private String mztype;//查询类型
	private String mzword;//查询关键字
	private int b;//定义校验变量
	private int a;
	private static int total1=0;
	private String tip;								//使用ajax异步提交时，用于标记返回的值
	
	
	public String getTip() {
		return tip;
	}
	public void setTip(String tip) {
		this.tip = tip;
	}
	public int getA() {
		return a;
	}
	public void setA(int a) {
		this.a = a;
	}
	public static int getTotal1() {
		return total1;
	}
	public static void setTotal1(int total1) {
		MzActions.total1 = total1;
	}
	@JSON(serialize=true)
	public List<String> getMzjy() {
		return mzjy;
	}
	public void setMzjy(List<String> mzjy) {
		this.mzjy = mzjy;
	}
	@JSON(serialize=true)
	public int getB() {
		return b;
	}
	public void setB(int b) {
		this.b = b;
	}
	@JSON(serialize=false)
	public String getMztype() {
		return mztype;
	}
	public void setMztype(String mztype) {
		this.mztype = mztype;
	}
	@JSON(serialize=false)
	public String getMzword() {
		return mzword;
	}
	public void setMzword(String mzword) {
		this.mzword = mzword;
	}
	public String getMzmc() {
		return mzmc;
	}
	@JSON(deserialize=true)
	public void setMzmc(String mzmc) {
		this.mzmc = mzmc;
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
	public BaseService getBaseService() {
		return baseService;
	}
	
	public void setBaseService(BaseService baseService) {
		this.baseService = baseService;
	}
	@JSON(serialize=false)
	public Mz getMz() {
		return mz;
	}
	public void setMz(Mz mz) {
		this.mz = mz;
	}
	public List<Mz> getRows() {
		return rows;
	}
	public void setRows(List<Mz> rows) {
		this.rows = rows;
	}
    //总记录条数  
    private int total=0;  
      
   
    public int getTotal() {  
        return total;  
    }  
    public void setTotal(int total) {  
        this.total = total;  
    } 
    
    private String mzbh;
    public String getMzbh()  {
       return mzbh;	
    }
	@JSON(deserialize=true)
	public void setMzbh(String mzbh){
		this.mzbh=mzbh;
	}
	/**
	 * @{insertMz.action}
	 * @param {Mz} {Mz实体类的对象实体}
	 * @return {SUCCESS} {添加信息成功}
	 * @{实现添加功能}
	 * @exception {说明在某情况下,将发生什么异常}
	*/
	@Action(value="/insertMz",results={@Result(name="success",type="redirect",location="/tables/datagrid_Mz.jsp")})			
	public String insertMz(){
		Mz mz=new Mz();
		mz.setMzmc(mzmc);
		this.baseService.save(mz);
		return SUCCESS;
	}
	/**
	 * @{listMz.action}
	 * @param {rows_s,page,total} {对应findAll方法所需要的参数}
	 * @return {/tables/datagrid_Mz.jsp} {返回民族信息界面}
	 * @{实现查询功能}
	 * @exception {说明在某情况下,将发生什么异常}
	*/
	@Action(value="/listMz",results={@Result(name="root",type="json")})
	//用来产生json数据
	public String listMz(){
        rows = this.baseService.findAll(Mz.class, "Mz", page, rows_s);
        //记录条数的记录  
        total=this.baseService.getTotal("Mz"); //记录条数 
        return "root";  
	}
	/**
	 * @{deleteMz.action}
	 * @param {mzbh} {对应数据表主键}
	 * @return {SUCCESS} {删除数据成功}
	 * @{实现删除功能}
	 * @exception {说明在某情况下,将发生什么异常}
	*/
	@Action(value="deleteMz",results={@Result(name="success",type="json")})
	public String deleteMz(){
		int id = Integer.parseInt(mzbh);//强制转换
		mz=this.baseService.find(Mz.class,id);//通过id获得民族编号
		this.baseService.delete(mz);
		return SUCCESS;
	}
	/**
	 * @{updateMz}
	 * @param {mz} {Mz类的对象实体}
	 * @return {updateMz.action} {}
	 * @{修改功能}
	 * @exception {说明在某情况下,将发生什么异常}
	*/
	@Action(value="updateMz",results={@Result(name="success",location="/tables/datagrid_Mz.jsp",type="redirect")})
	public String updateMz(){
		int id = Integer.parseInt(mzbh);//强制转换
		this.mz=this.baseService.find(Mz.class,id);//通过id获得民族编号
		//this.mz =this.baseService.find(Mz.class,mzbh);
		mz.setMzmc(mzmc);
		this.baseService.update(mz);
		return SUCCESS;
	}
	//实现查询功能
	@Action(value="/searchMz",results={@Result(name="root",type="json")})
	public String searchMz(){
		rows = this.baseService.findByTypage(Mz.class,"Mz",mztype,mzword, "order by mzbh asc", page, rows_s);
		total=total1;//记录总条数
		return "root";
	}
	//检验查询是否为空,并得出数据条数
	@Action(value="/searchMz1",results={@Result(name="root",type="json")})
	public String searchMz1(){
		String hql = "select count(*) from Mz where "+mztype+" like '%"+mzword+"%'";//通过关键字查询
		total1 = this.baseService.getTotalSql(hql);
		if(total1!=0){
			a=1;
		}else{
			a=0;
		}
		return "root";
	}
	
	//页面校验
	@Action(value="/Mzjy",results={@Result(name="success",type="json")})
	public String  Mzjy(){
		mzjy = this.baseService.find(String.class, "Mz", "mzmc");
		if(mzjy.contains(mzmc)){
		b = 0;
		}else{b=1;}
		return SUCCESS;
	}
//	/**
//	 * 模块分类（民族管理）
//	 */	
//	@Action(value="mz",results={@Result(name="success",location="/tables/datagrid_Mz.jsp",type="redirect")})
//	public String mz(){
//		total=this.baseService.getTotal("Mz"); 
//		return SUCCESS;
//	}
	
}
