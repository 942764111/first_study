/*
 * @(#)xx.tables_zxl.action.{file_name} 
 * @ListZyxxAction.java.java 
 * @ 创建时间：2011-4-18     
 * @ 作者：zxl
 * @ Copyright 2009-2010 河北北方学院信息科学与工程学院科研所 
 */
package xx.tables_zxl.action;


/**
 * @ListJszcAction <code>{类名称}</code>
 * @author  {zxl}
 * @version {版本,常用时间代替}
 * @{专业信息的增删改查} 
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



import xx.bean.Teacher;
import xx.collection.bean.Bjxx;
import xx.collection.bean.Pd;
import xx.collection.bean.Schoollist;
import xx.collection.bean.Xuyan;
import xx.collection.bean.Zyxx;
import xx.kgt.bean.PanDuan;
import xx.quanxian.service.BaseService;

import com.googlecode.jsonplugin.annotations.JSON;
import com.opensymphony.xwork2.ActionSupport;
@Controller
@Scope("prototype")
@ParentPackage("default-package")
@Namespace("")
public class ListZyxxAction extends ActionSupport {	
	
	@Resource(name="baseService")
	private BaseService baseservice;
	
	
	private Zyxx zyxx;
	private List<Zyxx> listzyxx = new ArrayList<Zyxx>();
	private List<Zyxxi> rows =new ArrayList<Zyxxi>();
	private int page;//当前页
	private int rows_s;//每页显示的条数
	private int zybh;
	private Xuyan xuyan;
	private String zymc;
	private List<Zyxx> zyxxb;
	private List<String> zyxxb1=new ArrayList<String>();
	private String xymc;
	private String queryType;	//查询功能时的查询参数，所要根据查询的类型
	private String queryWord;	//查询功能时的查询参数，所要根据查询的关键字
	private List<String> zyjy = new ArrayList<String>();
	private List<Integer> zyjy1 = new ArrayList<Integer>();
	private List<String> xx = new ArrayList<String>();
	private int b;
	private int a;
	private int total1=0;

	
	public int getA() {
		return a;
	}
	public void setA(int a) {
		this.a = a;
	}
	
	@JSON(serialize=false)
	public List<Integer> getZyjy1() {
		return zyjy1;
	}
	public void setZyjy1(List<Integer> zyjy1) {
		this.zyjy1 = zyjy1;
	}
	@JSON(serialize=false)
	public List<String> getZyjy() {
		return zyjy;
	}
	public void setZyjy(List<String> zyjy) {
		this.zyjy = zyjy;
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
	public List<String> getZyxxb1() {
		return zyxxb1;
	}
	public void setZyxxb1(List<String> zyxxb1) {
		this.zyxxb1 = zyxxb1;
	}
	@JSON(serialize=false)
	public String getXymc() {
		return xymc;
	}
	public void setXymc(String xymc) {
		this.xymc = xymc;
	}
	
	@JSON(serialize=false)
	public List<Zyxx> getZyxxb() {
		return zyxxb;
	}
	public void setZyxxb(List<Zyxx> zyxxb) {
		this.zyxxb = zyxxb;
	}
	
	@JSON(serialize=false)
	public int getZybh() {
		return zybh;
	}
	 //反序列化，这样才能够返回到数据库
	@JSON(deserialize=true)
	public void setZybh(int zybh) {
		this.zybh = zybh;
	}
	
	
	@JSON(serialize=false)
	public Xuyan getXuyan() {
		return xuyan;
	}

	public void setXuyan(Xuyan xuyan) {
		this.xuyan = xuyan;
	}
	
	
	@JSON(serialize=false)
	public String getZymc() {
		return zymc;
	}
	
	public void setZymc(String zymc) {
		this.zymc = zymc;
	}
	
	
	@JSON(serialize=false)
	public List<Zyxx> getListzyxx() {
		return listzyxx;
	}
	public void setListzyxx(List<Zyxx> listzyxx) {
		this.listzyxx = listzyxx;
	}
	
	
	public List<Zyxxi> getRows() {
		return rows;
	}
	public void setRows(List<Zyxxi> rows) {
		this.rows = rows;
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
	public Zyxx getZyxx() {
		return zyxx;
	}
	public void setZyxx(Zyxx zyxx) {
		this.zyxx = zyxx;
	}

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


	//记录数量  
    private int total=0;  
      
    public int getTotal() {  
        return total;  
    }  
    public void setTotal(int total) {  
        this.total = total;  
    }  
 
	/**
	 * @{insertzyxx.action}
	 * @param {zyxxlist} {显示专业信息}
	 * @return {zyxxlist} {显示专业信息}
	 * @{添加专业信息}
	 * @exception {说明在某情况下,将发生什么异常}
	*/
	@Action(value="insertzyxx",results={@Result(name="success",type="json")})
	public String insertzyxx(){
		
		String[] keys=new String[1];
		keys[0]="xymc";
		Object[] values=new Object[1];
		values[0]=xymc;
		List<Integer> xybh=this.baseservice.find(Integer.class, "Xuyan", "xybh", keys, values);//通过xymc（values）查出xybh，放在list中
		Xuyan xuyan=this.baseservice.find(Xuyan.class,xybh.get(0));//通过获得的当前xybh查出xuyan（学院）信息。		
		Zyxx zyxx = new Zyxx();
		zyxx.setXuyan(xuyan);
		zyxx.setZybh(zybh);
		zyxx.setZymc(zymc);
		this.baseservice.save(zyxx);//增加数据
		return SUCCESS;
	}
	/**
	 * @{zyxxlist.action}
	 * @param {zyxxlist} {显示专业信息}
	 * @return {zyxxlist} {显示专业信息}
	 * @{显示功能}
	*/
	@Action(value="/zyxxlist",results={@Result(name="root",type="json")})
	public String zyxxlist(){
		
		listzyxx=this.baseservice.findAll(Zyxx.class, "Zyxx", page, rows_s);	//查出所有数据	
        total=this.baseservice.getTotal("Zyxx");  //记录条数
        for(int i=0;i<listzyxx.size();i++){
        	Zyxxi element = new Zyxxi();
        	element.setInt1(listzyxx.get(i).getZybh());
        	element.setInt2(listzyxx.get(i).getXuyan().getXybh());       	
        	element.setStr1(listzyxx.get(i).getZymc());  
        	element.setStr2(listzyxx.get(i).getXuyan().getXymc());
        	rows.add(element);   //把数据放进rows，实现分页查询显示    	
        	
        }
        return "root";  
	}
	/**
	 * @{deletezyxx.action}
	 * @param {zyxxlist} {显示专业信息}
	 * @return {zyxxlist.action} {显示专业信息}
	 * @{删除专业信息}
	 * @exception {说明在某情况下,将发生什么异常}
	*/
	@Action(value="deletezyxx",results={@Result(name="success",type="json")})
	public String deletezyxx(){
		String hql1="from Bjxx";
		List<Bjxx> bjxx=this.baseservice.findHql(Bjxx.class, hql1);
		List<Integer> bjxx1=new ArrayList<Integer>();
		if(bjxx.size()>0){
			for(int i=0;i<bjxx.size();i++){
				bjxx1.add(bjxx.get(i).getZyxx().getZybh());
			}
			if(!bjxx1.contains(zybh)){
				zyxx=this.baseservice.find(Zyxx.class,zybh);//通过id获得要删除的数据
				this.baseservice.delete(zyxx);
				a=1;
			}else{
				a=0;
			}
		}else{
			zyxx=this.baseservice.find(Zyxx.class,zybh);//通过id获得要删除的数据
			this.baseservice.delete(zyxx);
			a=1;
		}
		
		
		return SUCCESS;
	}

	/**
	 * @{updatezyxx}
	 * @param {zyxxlist} {显示专业信息}
	 * @return {zyxxlist.action} {显示专业信息}
	 * @{修改专业信息}
	 * @exception {说明在某情况下,将发生什么异常}
	*/
	@Action(value="updatezyxx",results={@Result(name="success",type="json")})
	public String updatezyxx(){
		Zyxx zyxx=this.baseservice.find(Zyxx.class, zybh);
		String[] keys=new String[1];
		keys[0]="xymc";
		Object[] values=new Object[1];
		values[0]=xymc;
		List<Integer> xybh=this.baseservice.find(Integer.class, "Xuyan", "xybh", keys, values);//通过xymc（values）查出xybh，放在list中
		Xuyan xuyan=this.baseservice.find(Xuyan.class,xybh.get(0));
		zyxx.setXuyan(xuyan);
		zyxx.setZybh(zybh);
		zyxx.setZymc(zymc);

		this.baseservice.update(zyxx);	
		return SUCCESS;		
	}	
	
	
	//检验查询是否为空,并得出数据条数 
	@Action(value="/queryzy2",results={@Result(name="root",type="json")})
	public String queryzy2(){
		String hql = "select count(*) from Zyxx where "+queryType+" like '%"+queryWord+"%'";
		total1 = this.baseservice.getTotalSql(hql);
		HttpSession hs = ServletActionContext.getRequest().getSession();
		hs.setAttribute("zyxxtotal", total1);
		if(total1!=0){
			a=1;
		}else{
			a=0;
		}
		return "root";
	}
	/**
	 * @{queryzy.action}
	 * 该方法是用来根据关键字查询专业信息的，查询时会从页面接收两个参数，queryType，queryWord
	 * queryType是要查询时所根据的类型，queryWord是关键字
	 * 查询出的结果页要以分页的形式显示
	*/
	@Action(value="/queryzyxx",results={@Result(name="root",type="json")})
	public String queryzyxx(){
		HttpSession hs = ServletActionContext.getRequest().getSession();
		total=(Integer)hs.getAttribute("zyxxtotal");
		listzyxx = this.baseservice.findByTypage(Zyxx.class, "Zyxx", queryType, queryWord, "order by zybh asc", page, rows_s);//按关键字查询
		for(int i=0;i<listzyxx.size();i++){
        	Zyxxi element = new Zyxxi();
        	element.setInt1(listzyxx.get(i).getZybh());
        	element.setInt2(listzyxx.get(i).getXuyan().getXybh());       	
        	element.setStr1(listzyxx.get(i).getZymc());  
        	element.setStr2(listzyxx.get(i).getXuyan().getXymc());
        	rows.add(element);   //把数据放进rows，实现分页查询显示    	
        }
		
		return "root";
	}
	
	//页面校验
	@Action(value="/zyjy",results={@Result(name="success",type="json")})
	public String  zyjy(){
		zyjy = this.baseservice.find(String.class, "Zyxx", "zymc");
		zyjy1 = this.baseservice.find(Integer.class, "Zyxx", "zybh");
		if(zyjy.contains(zymc)){
		b = 0;
		}else{b=1;}
		if(zyjy1.contains(zybh)){
			a=0;
		}else{
			a=1;
		}
		return SUCCESS;
	}
	
	//修改信息校验
	@Action(value="/zyjy1",results={@Result(name="success",type="json")})
	public String  zyjy1(){
		zyjy = this.baseservice.find(String.class, "Zyxx", "zymc");
		zyxx=this.baseservice.find(Zyxx.class, zybh);
		for(int i=0;i<zyjy.size();i++){
			if(!zyjy.get(i).equals(zyxx.getZymc())){
				xx.add(zyjy.get(i));
			}
		}
		if(!xx.contains(zymc)){
			b=0;
		}else if(!zyjy.contains(zymc)){
			b=0;
		}else{
			b=1;
		}
		
		return SUCCESS;
	}
	
	/**
	 * @{zyxx}
	 * @return {/tables_zxl/datagrid_zyxx.jsp} {显示专业信息}
	 * @{教学  专业信息管理}
	 */	
//	@Action(value="/zyxx",results={@Result(name="success",location="/tables_zxl/datagrid_zyxx.jsp")})
//	public String zyxx(){
//		this.zyxxb=this.baseservice.find(Zyxx.class);//通过Zyxx.class查询所有数据并放在zyxxb中。
//		for(int i=0;i<zyxxb.size();i++){
//			if(!zyxxb1.contains(zyxxb.get(i).getXuyan().getXymc())){
//				zyxxb1.add(zyxxb.get(i).getXuyan().getXymc());
//			}
//		}
//		return SUCCESS;
//	}
}
