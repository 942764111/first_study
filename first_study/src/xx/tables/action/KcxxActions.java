/*
 *@(#)xx.tables.action
 *@KcxxActions.java.java  
 *@创建时间:2011-6-2下午05:53:44
 *@作者：zxl
 *@Copyright 2009-2010 河北北方学院信息科学与工程学院科研所 
 */

package xx.tables.action;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
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
import xx.collection.bean.CourseChapter;
import xx.collection.bean.Jie;
import xx.collection.bean.Jxjh;
import xx.collection.bean.Sjnr;
import xx.quanxian.service.BaseService;

import com.googlecode.jsonplugin.annotations.JSON;
import com.opensymphony.xwork2.ActionSupport;
/**
 * @KcxxActions <code>{KcxxActions}</code>
 * @author  {zxl}
 * @version {版本,常用时间代替}
 * @{实现课程信息表的增删改查} 
 */
@Controller
@Scope("prototype")
@Namespace("")
@ParentPackage("default-package")
public class KcxxActions extends ActionSupport {

	@Resource(name="baseService")
	private BaseService baseService;

	private CourseChapter kcxx;
	private List<CourseChapter> rows=new ArrayList<CourseChapter>();
	private List<String> kcjy=new ArrayList<String>();
	private List<String> kc=new ArrayList<String>();
	private CourseChapter kcjy1;
	private int page;
	private int rows_s;
	private String kcxxtype;
	private String kcxxword;
	private int b;
	private int c;
	private int a;
	private static int totalo1;
	private String CName;
	private String TCName;
	private int zbh;
	private String kcms;
	private String q;
	private List<String>cNamelList=new ArrayList<String>();
	private List<Map<String, String>> ZCList=new ArrayList<Map<String,String>>();
	


	public List<Map<String, String>> getZCList() {
		return ZCList;
	}
	public void setZCList(List<Map<String, String>> zCList) {
		ZCList = zCList;
	}
	public String getQ() {
		return q;
	}
	public void setQ(String q) {
		this.q = q;
	}


	@Resource(name="adminService")
	private AdminService adminservice;
	@JSON(serialize=false)
	public List<String> getKc() {
		return kc;
	}
	public void setKc(List<String> kc) {
		this.kc = kc;
	}
	@JSON(serialize=true)
	public int getA() {
		return a;
	}
	public void setA(int a) {
		this.a = a;
	}
	@JSON(serialize=true)
	public static int getTotalo1() {
		return totalo1;
	}
	public static void setTotalo1(int totalo1) {
		KcxxActions.totalo1 = totalo1;
	}
	@JSON(serialize=false)
	public CourseChapter getKcxx() {
		return kcxx;
	}
	public void setKcxx(CourseChapter kcxx) {
		this.kcxx = kcxx;
	}
	
	public String getTCName() {
		return TCName;
	}
	public void setTCName(String name) {
		TCName = name;
	}

	public int getZbh() {
		return zbh;
	}
	@JSON(deserialize=true)
	public void setZbh(int zbh) {
		this.zbh = zbh;
	}

	@JSON(serialize=true)
	public int getC() {
		return c;
	}
	public void setC(int c) {
		this.c = c;
	}
	@JSON(serialize=true)
	public String getKcms() {
		return kcms;
	}
	public void setKcms(String kcms) {
		this.kcms = kcms;
	}
	
	public String getCName() {
		return CName;
	}
	public void setCName(String name) {
		CName = name;
	}
	@JSON(serialize=false)
	public List<String> getKcjy() {
		return kcjy;
	}
	public void setKcjy(List<String> kcjy) {
		this.kcjy = kcjy;
	}
	@JSON(serialize=false)
	public CourseChapter getKcjy1() {
		return kcjy1;
	}
	public void setKcjy1(CourseChapter kcjy1) {
		this.kcjy1 = kcjy1;
	}
	@JSON(serialize=true)
	public int getB() {
		return b;
	}
	public void setB(int b) {
		this.b = b;
	}
	@JSON(serialize=false)
	public String getKcxxtype() {
		return kcxxtype;
	}
	public void setKcxxtype(String kcxxtype) {
		this.kcxxtype = kcxxtype;
	}
	@JSON(serialize=false)
	public String getKcxxword() {
		return kcxxword;
	}
	public void setKcxxword(String kcxxword) {
		this.kcxxword = kcxxword;
	}
	@JSON(serialize=false)
	public BaseService getBaseService() {
		return baseService;
	}
	public void setBaseService(BaseService baseService) {
		this.baseService = baseService;
	}

	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}

	public List<CourseChapter> getRows() {
		return rows;
	}
	public void setRows(List<CourseChapter> rows) {
		this.rows = rows;
	}
	public int getRows_s() {
		return rows_s;
	}
	public void setRows_s(int rows_s) {
		this.rows_s = rows_s;
	}


	//记录数据数量
	private int total=0;

	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}



	public List<String> getcNamelList() {
		return cNamelList;
	}
	public void setcNamelList(List<String> cNamelList) {
		this.cNamelList = cNamelList;
	}

//
//	//查询所有课程名称
	@Action(value="/getCNames",results={@Result(name="success",type="json")})
	public String suggest(){
		String queryword = q;
		

		try {
			queryword = new String(queryword.getBytes("iso8859-1"),
					"utf-8");
			System.out.println("查询:"+queryword);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		String qw = "%"+queryword+"%";

		List<String> ll = this.adminservice.findsName(qw);
		if(ll.size()>0){
			for(String s:ll)
			{
				if(s.length()>0){
					cNamelList.add(s);
				}
			}

		}
		else {
			cNamelList.add(null);
		}
		
		System.out.println("测试:"+cNamelList);
		return SUCCESS;
	}
//
////	//根据课程名称查出章节名称
	@Action(value="/getZCName",results={@Result(name="success",type="json")})
	public String getZC(){
		System.out.println("课程名称:"+TCName);
		String[] keys=new String[1];
		keys[0]="TCName";		
		Object[] values=new Object[1];
		values[0]=TCName;	
		List<CourseChapter> j = this.baseService.find(CourseChapter.class, "CourseChapter", keys, values);

		for (int i = 0; i < j.size(); i++) {
			Map<String, String>map=new HashMap<String, String>();
			CourseChapter chapter=j.get(i);
			map.put("CName", chapter.getCName());
			map.put("zbh", String.valueOf(chapter.getZbh()));
			
			ZCList.add(map);
			
		}
		return SUCCESS;
	}

	/**
	 * @{insertKcxx.action}
	 * @param {Kcxx} {Kcxx实体类的对象实体}
	 * @return {SUCCESS} {添加信息成功}
	 * @{实现添加功能}
	 * @exception {说明在某情况下,将发生什么异常}
	 */
	@Action(value="/insertKcxx",results={@Result(name="success",type="json")})			
	public String insertKcxx(){

		//自动产生zbh,生成七位数
		String hql ="from CourseChapter as a order by a.zbh desc limt 1";//算出有多少章
		List<CourseChapter> z=this.baseService.getList(CourseChapter.class, hql);
		System.out.println("z:"+z);
		if(z==null||z.size()==0){
			zbh=1000001;
		}else {
			
			zbh=z.get(0).getZbh()+1;
		}
		System.out.println("zbh:"+zbh);
//		if(TCName.equals("C语言程序设计")&&z>9){
//			String hql1 ="from CourseChapter where t_c_name='"+TCName+"'";
//			rows=this.baseService.findHql(CourseChapter.class, hql1);
//			for(int i=9;i<rows.size();i++){
//				if(rows.get(i).getZbh()!=(1001001+i-9)){
//					zbh=1001001+i-9;
//					break;
//				}else{
//					zbh=rows.get(rows.size()-1).getZbh()+1;
//				}
//			}
//		}
//
//		if(!TCName.equals("C语言程序设计")){
//			kc= this.baseService.find(String.class, "CourseChapter", "TCName");//算出课程数量(已取消重复课程)
//
//			String e=String.valueOf(kc.size()+1);
//			String b =String.valueOf(0);
//			String c =String.valueOf(1);
//			String hql1 ="from CourseChapter where t_c_name='"+TCName+"'";
//			rows=this.baseService.findHql(CourseChapter.class, hql1);// 得到同一课程的所有编号
//			if(kc.size()==1){
//				zbh=1002001;
//			}else{
//				if(kc.contains(TCName)){//如果添加的课程已存在
//					for(int n=0;n<kc.size();n++){
//						if(kc.get(n).equals(TCName)){
//							if(n+1<10){
//								if(rows.size()!=0){
//									for(int j=0;j<rows.size();j++){
//										String d=String.valueOf(n+1);
//										if(j<10){
//											if(rows.get(j).getZbh()!=(Integer.parseInt(c+b+b+d+b+b+j)+1)){
//												//判断中间是否存在不连续的编号，如果有，则优先占用此编号
//												zbh=Integer.parseInt(c+b+b+d+b+b+j)+1;
//												break;
//											}else{
//												//如果中间没有间断的编号，则最大的编号加1，生成新的编号
//												zbh=rows.get(rows.size()-1).getZbh()+1;
//											}
//										}else if(j>=10&&j<100){
//											if(rows.get(j).getZbh()!=(Integer.parseInt(c+b+b+d+b+j)+1)){
//												//判断中间是否存在不连续的编号，如果有，则优先占用此编号
//												zbh=Integer.parseInt(c+b+b+d+b+j)+1;
//												break;
//											}else{
//												//如果中间没有间断的编号，则最大的编号加1，生成新的编号
//												zbh=rows.get(rows.size()-1).getZbh()+1;
//											}
//										}else if(j>=100){
//											if(rows.get(j).getZbh()!=(Integer.parseInt(c+b+b+d+j)+1)){
//												//判断中间是否存在不连续的编号，如果有，则优先占用此编号
//												zbh=Integer.parseInt(c+b+b+d+j)+1;
//												break;
//											}else{
//												//如果中间没有间断的编号，则最大的编号加1，生成新的编号
//												zbh=rows.get(rows.size()-1).getZbh()+1;
//											}
//										}
//									}
//								}
//							}else if(n+1>=10&&n+1<100){
//								if(rows.size()!=0){
//									for(int j=0;j<rows.size();j++){
//										String d=String.valueOf(n+1);
//										if(j<10){
//											if(rows.get(j).getZbh()!=(Integer.parseInt(c+b+d+b+b+j)+1)){
//												//判断中间是否存在不连续的编号，如果有，则优先占用此编号
//												zbh=Integer.parseInt(c+b+d+b+b+j)+1;
//												break;
//											}else{
//												//如果中间没有间断的编号，则最大的编号加1，生成新的编号
//												zbh=rows.get(rows.size()-1).getZbh()+1;
//											}
//										}else if(j>=10&&j<100){
//											if(rows.get(j).getZbh()!=(Integer.parseInt(c+b+d+b+j)+1)){
//												//判断中间是否存在不连续的编号，如果有，则优先占用此编号
//												zbh=Integer.parseInt(c+b+d+b+j)+1;
//												break;
//											}else{
//												//如果中间没有间断的编号，则最大的编号加1，生成新的编号
//												zbh=rows.get(rows.size()-1).getZbh()+1;
//											}
//										}else if(j>=100){
//											if(rows.get(j).getZbh()!=(Integer.parseInt(c+b+d+j)+1)){
//												//判断中间是否存在不连续的编号，如果有，则优先占用此编号
//												zbh=Integer.parseInt(c+b+d+j)+1;
//												break;
//											}else{
//												//如果中间没有间断的编号，则最大的编号加1，生成新的编号
//												zbh=rows.get(rows.size()-1).getZbh()+1;
//											}
//										}
//
//
//									}
//								}
//							}else if(n+1>=100&&n+1<1000){
//								if(rows.size()!=0){
//									for(int j=0;j<rows.size();j++){
//										String d=String.valueOf(n+1);
//										if(j<10){
//											if(rows.get(j).getZbh()!=(Integer.parseInt(c+d+b+b+j)+1)){
//												//判断中间是否存在不连续的编号，如果有，则优先占用此编号
//												zbh=Integer.parseInt(c+d+b+b+j)+1;
//												break;
//											}else{
//												//如果中间没有间断的编号，则最大的编号加1，生成新的编号
//												zbh=rows.get(rows.size()-1).getZbh()+1;
//											}
//										}else if(j>=10&&j<100){
//											if(rows.get(j).getZbh()!=(Integer.parseInt(c+d+b+j)+1)){
//												//判断中间是否存在不连续的编号，如果有，则优先占用此编号
//												zbh=Integer.parseInt(c+d+b+j)+1;
//												break;
//											}else{
//												//如果中间没有间断的编号，则最大的编号加1，生成新的编号
//												zbh=rows.get(rows.size()-1).getZbh()+1;
//											}
//										}else{
//											if(rows.get(j).getZbh()!=(Integer.parseInt(c+d+j)+1)){
//												//判断中间是否存在不连续的编号，如果有，则优先占用此编号
//												zbh=Integer.parseInt(c+d+j)+1;
//												break;
//											}else{
//												//如果中间没有间断的编号，则最大的编号加1，生成新的编号
//												zbh=rows.get(rows.size()-1).getZbh()+1;
//											}
//										}
//									}
//								}
//							}
//
//						}
//					}
//				}else{
//					//如果添加的课程还不存在，则按下面的方法生成编号(直接拼接字符串)
//					if(kc.size()+1<10){
//						zbh=Integer.parseInt(c+b+b+e+b+b+c);
//					}
//					if(kc.size()+1>=10&&kc.size()+1<100){
//						zbh=Integer.parseInt(c+b+e+b+b+c);
//					}
//					if(kc.size()+1>=100&&kc.size()+11<1000){
//						zbh=Integer.parseInt(c+e+b+b+c);
//					}
//				}
//			}
//		}	
		CourseChapter cc = new CourseChapter();
		cc.setCName(CName);
		cc.setZbh(zbh);
		cc.setKcms(kcms);
		cc.setTCName(TCName);
		this.baseService.save(cc);
		return SUCCESS;
	}
	/**
	 * @{listKcxx.action}
	 * @param {rows_s,page,total} {对应findAll方法所需要的参数}
	 * @return {/tables/datagrid_Kcxx.jsp} {返回课程信息界面}
	 * @{实现查询功能}
	 * @exception {说明在某情况下,将发生什么异常}
	 */
	@Action(value="/listKcxx",results={@Result(name="root",type="json")})
	//用来产生json数据
	public String listKcxx(){
		rows = this.baseService.findAll(CourseChapter.class, "CourseChapter", page, rows_s);
		//记录条数的记录  
		total=this.baseService.getTotal("CourseChapter"); //记录条数 
		return "root";  
	}
	/**
	 * @{deleteKcxx.action}
	 * @param {c_id} {对应数据表主键}
	 * @return {SUCCESS} {删除数据成功}
	 * @{实现删除功能}
	 * @exception {说明在某情况下,将发生什么异常}
	 */
	@Action(value="deleteKcxx",results={@Result(name="success",type="json")})
	public String deleteKcxx(){
		String hql1="from Jie";
		String hql2="from Sjnr";
		String hql3="from Jxjh";
		List<Jxjh> jxjh=this.baseService.findHql(Jxjh.class, hql3);
		List<Jie> jie=this.baseService.findHql(Jie.class, hql1);
		List<Sjnr> sjnr=this.baseService.findHql(Sjnr.class, hql2);
		List<Integer> zbh1=new ArrayList<Integer>();
		List<Integer> zbh2=new ArrayList<Integer>();
		List<Integer> zbh3=new ArrayList<Integer>();
		for(int i=0;i<jie.size();i++){
			zbh1.add(jie.get(i).getId().getZbh());
		}
		for(int j=0;j<sjnr.size();j++){
			zbh2.add(sjnr.get(j).getCourseChapter().getZbh());
		}
		for(int n=0;n<jxjh.size();n++){
			zbh3.add(jxjh.get(n).getCourseChapter().getZbh());
		}
		if(!zbh1.contains(zbh)){
			if(!zbh2.contains(zbh)){
				if(!zbh3.contains(zbh)){
					kcxx=this.baseService.find(CourseChapter.class,zbh);//通过id获得课程编号
					this.baseService.delete(kcxx);
					a=1;
				}else{
					a=0;
				}
			}else{
				a=0;
			}
		}else{
			a=0;
		}

		return SUCCESS;
	}


	/**
	 * @{updateKcxx}
	 * @param {kcxx} {Kcxx类的对象实体}
	 * @return {SUCCESS} {}
	 * @{修改功能}
	 * @exception {说明在某情况下,将发生什么异常}
	 */
	@Action(value="updateKcxx",results={@Result(name="success",type="json")})
	public String updateKcxx(){
		CourseChapter cc = new CourseChapter();
		cc.setCName(CName);
		cc.setZbh(zbh);
		cc.setKcms(kcms);
		cc.setTCName(TCName);
		this.baseService.update(cc);
		return SUCCESS;
	}

	//检验查询是否为空,并得出数据条数
	@Action(value="/querykz",results={@Result(name="root",type="json")})
	public String querykz(){
		String hql = "select count(*) from CourseChapter where "+kcxxtype+" like '%"+kcxxword+"%'";
		totalo1 = this.baseService.getTotalSql(hql);
		if(totalo1!=0){
			a=1;
		}else{
			a=0;
		}
		return "root";
	}
	//实现查询功能
	@Action(value="/searchKcxx",results={@Result(name="root",type="json")})
	public String searchKcxx(){
		rows = this.baseService.findByTypage(CourseChapter.class,"CourseChapter",kcxxtype,kcxxword, "order by zbh asc", page, rows_s);
		total=totalo1; //记录条数 
		return "root";
	}
	//添加时的信息校验
	@Action(value="/Kcxxjy",results={@Result(name="success",type="json")})
	public String  Kcxxjy(){
		kcjy = this.baseService.find(String.class, "CourseChapter", "CName");
		if(kcjy.contains(CName)){
			b = 0;
		}else{b=1;}

		return SUCCESS;
	}


	//修改时的信息校验
	@Action(value="/Kcxxjy2",results={@Result(name="success",type="json")})
	public String  Kcxxjy2(){
		kcjy = this.baseService.find(String.class, "CourseChapter", "CName");
		kcjy1=this.baseService.find(CourseChapter.class, zbh);
		for(int i=0;i<kcjy.size();i++){
			if(!kcjy.get(i).equals(kcjy1.getCName())){
				kc.add(kcjy.get(i));
			}
		}
		if(!kc.contains(CName)){
			b=0;
		}else if(!kcjy.contains(CName)){
			b=0;
		}else{
			b=1;
		}

		return SUCCESS;
	}
	/**
	 * 模块分类（课程信息管理）
	 */	
	//@Action(value="kcxx",results={@Result(name="success",location="/tables/datagrid_Kcxx.jsp",type="redirect")})
	//public String kcxx(){
	//	return SUCCESS;
	//}
}
