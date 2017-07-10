/*
 *@(#)xx.tables.action
 *@JslbActions.java.java  
 *@创建时间:2011-5-18上午09:29:14
 *@作者：ylj
 *@Copyright 2009-2010 河北北方学院信息科学与工程学院科研所 
 */

package xx.tables.action;
/**
 * @JslbActions <code>{Jslb_simple}</code>
 * @author  {ylj}
 * @version {2011-5-18上午09:29:14}
 * @{实现教师类别表的增删改查} 
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

import xx.collection.bean.Jslb;
import xx.quanxian.service.BaseService;

import com.googlecode.jsonplugin.annotations.JSON;
import com.opensymphony.xwork2.ActionSupport;

@Controller
@Scope("prototype")
@Namespace("")
@ParentPackage("default-package")
@SuppressWarnings("serial")
public class JslbActions extends ActionSupport {
	
	@Resource(name="baseService")
	private BaseService baseService;
	private Jslb jslb1;
	private List<Jslb> rows=new ArrayList<Jslb>();
	private List<String> lbjy=new ArrayList<String>();
	private int page;
	private int rows_s;
	private String lbmc;
	private String lbbz;
	private String jslbtype;
	private String jslbword;
	private int b;
	private int a;
	private static int total1=0;
	private String tip;	
	
	
	
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
		JslbActions.total1 = total1;
	}
	@JSON(deserialize=true)
	public List<String> getLbjy() {
		return lbjy;
	}
	public void setLbjy(List<String> lbjy) {
		this.lbjy = lbjy;
	}
	@JSON(deserialize=true)
	public int getB() {
		return b;
	}
	public void setB(int b) {
		this.b = b;
	}
	@JSON(deserialize=true)
	public String getJslbtype() {
		return jslbtype;
	}
	public void setJslbtype(String jslbtype) {
		this.jslbtype = jslbtype;
	}
	@JSON(deserialize=true)
	public String getJslbword() {
		return jslbword;
	}
	public void setJslbword(String jslbword) {
		this.jslbword = jslbword;
	}
	public String getLbmc() {
		return lbmc;
	}
	@JSON(deserialize=true)
	public void setLbmc(String lbmc) {
		this.lbmc = lbmc;
	}
	public String getLbbz() {
		return lbbz;
	}
	@JSON(deserialize=true)
	public void setLbbz(String lbbz) {
		this.lbbz = lbbz;
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
	
    //记录数量  
    private int total=0;  
  
    public int getTotal() {  
        return total;  
    } 
	@JSON(serialize=false)
    public Jslb getJslb1() {
		return jslb1;
	}
	public void setJslb1(Jslb jslb1) {
		this.jslb1 = jslb1;
	}
	public List<Jslb> getRows() {
		return rows;
	}
	public void setRows(List<Jslb> rows) {
		this.rows = rows;
	}
	public void setTotal(int total) {  
        this.total = total;  
    }  
	
    private String jslb;
    public String getJslb()  {
       return jslb;	
    }
	@JSON(deserialize=true)
	public void setJslb(String jslb){
		this.jslb=jslb;
	}
	/**
	 * @{insertJslb.action}
	 * @param {jslb} {jslb实体类的对象实体}
	 * @return {success} {信息添加成功}
	 * @{实现添加功能}
	 * @exception {说明在某情况下,将发生什么异常}
	*/
	@Action(value="/insertJslb",results={@Result(name="success",location="/tables/datagrid_Jslb.jsp",type="redirect")})	
	public String insertJslb(){
		Jslb jslb1 =new Jslb();
		jslb1.setLbmc(lbmc);
		jslb1.setLbbz(lbbz);
		this.baseService.save(jslb1);
		return SUCCESS;
	}
	/**
	 * @{listJslb.action}
	 * @param {rows_s,page,total} {findAll方法对应的参数}
	 * @return {/tables/datagrid_Jslb.jsp} {返回教师类别页面}
	 * @{实现查询功能}
	 * @exception {说明在某情况下,将发生什么异常}
	*/
	@Action(value="/listJslb",results={@Result(name="root",type="json")})
	//用来产生json数据
	public String listJslb(){
        rows = this.baseService.findAll(Jslb.class, "Jslb", page, rows_s);
        //记录条数的记录  
        total=this.baseService.getTotal("Jslb");  
        return "root";  
	}
	/**
	 * @{deleteJslb.action}
	 * @param {jslb} {对应数据表主键}
	 * @return {success} {信息删除成功}
	 * @{实现删除功能}
	 * @exception {说明在某情况下,将发生什么异常}
	*/
	@Action(value="deleteJslb",results={@Result(name="success",type="json")})
	public String deleteJslb(){
		int id = Integer.parseInt(jslb);//将教师类别强制转换成int型
		jslb1=this.baseService.find(Jslb.class,id);
		this.baseService.delete(jslb1);
		return SUCCESS;
	}
	/**
	 * @{updateJslb.action}
	 * @param {jslb} {jslb实体类的对象实体}
	 * @return {updateJslb.action} {显示功能}
	 * @{实现修改功能}
	 * @exception {说明在某情况下,将发生什么异常}
	*/
	@Action(value="updateJslb",results={@Result(name="success",location="/tables/datagrid_Jslb.jsp",type="redirect")})
	public String updateJslb(){
		int id = Integer.parseInt(jslb);//将教师类别强制转换成int型
		this.jslb1=this.baseService.find(Jslb.class,id);
		jslb1.setLbmc(lbmc);
		jslb1.setLbbz(lbbz);
		this.baseService.update(jslb1);
		return SUCCESS;
	}
	//实现查询功能
	@Action(value="/searchJslb",results={@Result(name="root",type="json")})
	public String searchJslb(){
		rows = this.baseService.findByTypage(Jslb.class,"Jslb",jslbtype,jslbword, "order by jslb asc", page, rows_s);
		total=total1;
		return "root";
	}
	//检验查询是否为空,并得出数据条数
	@Action(value="/searchJslb1",results={@Result(name="root",type="json")})
	public String searchJslb1(){
		String hql = "select count(*) from Jslb where "+jslbtype+" like '%"+jslbword+"%'";
		total1 = this.baseService.getTotalSql(hql);
		if(total1!=0){
			a=1;
		}else{
			a=0;
		}
		return "root";
	}
	
	//页面校验
	@Action(value="/Lbjy",results={@Result(name="success",type="json")})
	public String  Lbjy(){
		lbjy = this.baseService.find(String.class, "Jslb", "lbmc");
		if(lbjy.contains(lbmc)){
		b = 0;
		}else{b=1;}
		return SUCCESS;
	}
//	/**
//	 * 模块分类（教师类别）
//	 */
//	@Action(value="jslb",results={@Result(name="success",location="/tables/datagrid_Jslb.jsp",type="redirect")})
//	public String jslb(){
//		return SUCCESS;
//	}
}

