/*
 *@(#)xx.testxg.action
 *@Xsdjjl.java.java  
 *@创建时间:2011-11-18下午01:10:56
 *@作者：张晓莉
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

import xx.collection.bean.CourseChapter;
import xx.collection.bean.Schoollist;
import xx.collection.bean.Sjnr;
import xx.collection.bean.Studentifno;
import xx.collection.bean.Xlxwbh;
import xx.collection.bean.Xsdyjl;
import xx.collection.bean.Zyxx;
import xx.kgt.bean.Dtjl;
import xx.quanxian.service.BaseService;
import xx.tables_zxl.action.Zyxxi;

import com.googlecode.jsonplugin.annotations.JSON;
import com.opensymphony.xwork2.ActionSupport;

/**
 * @Xsdjjl <code>{类名称}</code>
 * @author  {张晓莉}
 * @version {版本,常用时间代替}
 * @{功能描述:学生答卷记录的删除、查看所有} 
 */

@Controller
@Scope("prototype")
@ParentPackage("default-package")
@Namespace("")
public class XsdjjlAction extends ActionSupport {
	
	@Resource(name="baseService")
	private BaseService baseservice;
	
	
	private List<Dtjl> rows = new ArrayList<Dtjl>();//对象集合    
	private List<Xsdyjl> rows1 = new ArrayList<Xsdyjl>();//对象集合    
	private int page;//当前页
	private int rows_s;//每一页显示的条数
	private int total=0;
	private Xsdyjl xsdjjl;
	private int sjno;
	private String userid;
	private String queryType;	//查询功能时的查询参数，所要根据查询的类型
	private String queryWord;	//查询功能时的查询参数，所要根据查询的关键字
	private int a;
	private int total1=0;
	
	private Sjnr sj;
	
	
	//试卷名称
	private String papername;
	
	//测试者名称
	private String studentname;
	
	
	@JSON(serialize=false)
	public BaseService getBaseservice() {
		return baseservice;
	}
	public void setBaseservice(BaseService baseservice) {
		this.baseservice = baseservice;
	}
	
	
	/**
	 * @return the rows
	 */
	public List<Dtjl> getRows() {
		return rows;
	}
	/**
	 * @param rows the rows to set
	 */
	public void setRows(List<Dtjl> rows) {
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
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
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
	public int getA() {
		return a;
	}
	public void setA(int a) {
		this.a = a;
	}
	@JSON(serialize=false)
	public Xsdyjl getXsdjjl() {
		return xsdjjl;
	}
	public void setXsdjjl(Xsdyjl xsdjjl) {
		this.xsdjjl = xsdjjl;
	}
	@JSON(serialize=false)
	public int getSjno() {
		return sjno;
	}
	public void setSjno(int sjno) {
		this.sjno = sjno;
	}
	
	@JSON(serialize=false)
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	
	
	/**
	 * @return the papername
	 */
	@JSON(serialize=false)
	public String getPapername() {
		return papername;
	}
	/**
	 * @param papername the papername to set
	 */
	public void setPapername(String papername) {
		this.papername = papername;
	}
	/**
	 * @return the studentname
	 */
	@JSON(serialize=false)
	public String getStudentname() {
		return studentname;
	}
	/**
	 * @param studentname the studentname to set
	 */
	public void setStudentname(String studentname) {
		this.studentname = studentname;
	}
	/**
	 * @{djjlgl.action}
	 * @param {rows} {显示所有答卷记录信息}
	 * @return {/testxg/xsdtjl.jsp} {显示所有答卷记录信息页面}
	 * @{显示答卷记录信息}
	 * @exception {说明在某情况下,将发生什么异常}
	*/
	@Action(value="/djjlgl",results={@Result(name="root",type="json")})
	public String djjlgl(){		
        rows1 = this.baseservice.findAll(Xsdyjl.class,"Xsdyjl", page, rows_s); //把所有数据放在rows里面以实现分页查询    
        for(int i=0;i<rows1.size();i++){
        	Dtjl dtjl = new Dtjl();
        	sj = this.baseservice.find(Sjnr.class, rows1.get(i).getId().getSjno());
        	if(null!=sj.getZxx()&!"".equals(sj.getZxx())){
        		dtjl.setPapername(sj.getZxx());
        	}
        	String hql = "from Studentifno where UserId = '"+rows1.get(i).getId().getUserId()+"'";
        	List<Studentifno> sinfo = this.baseservice.findHql(Studentifno.class, hql);
        	if(null!=sinfo.get(0).getSName()&!"".equals(sinfo.get(0).getSName())){
        		dtjl.setStudentname(sinfo.get(0).getSName());
        	}
        	rows.add(dtjl);
        	
        }
        total=this.baseservice.getTotal("Xsdyjl");  //记录条数的记录  
        return "root";  
	}
	
	
	/**
	 * @{deletexsdjjl.action}
	 * @return {djjlgl.action} {显示答卷记录信息}
	 * @{删除答卷记录信息}
	 * @exception {说明在某情况下,将发生什么异常}
	*/
	@Action(value="deletexsdjjl",results={@Result(name="success",type="json")})
	public String deletexsdjjl(){
		String[] keys=new String[1];
		keys[0]="zxx";
		Object[] values=new Object[1];
		values[0]=papername;
		List<Integer> paperno=this.baseservice.find(Integer.class, "Sjnr", "sjno", keys, values);
		
		String hql1="from Studentifno where s_name='"+studentname+"'";
		List<Studentifno> studentno= this.baseservice.findHql(Studentifno.class, hql1);
		String hql="from Xsdyjl where sjno='"+paperno.get(0)+"' and UserId='"+studentno.get(0).getUserinfo().getUserId()+"'";
		List<Xsdyjl> x=this.baseservice.findHql(Xsdyjl.class, hql);
		for (int i=0;i<x.size();i++){
			this.baseservice.delete(x.get(i));
		}
		return SUCCESS;
	}

	
	
	//检验查询是否为空,并得出数据条数 
	@Action(value="/querydtjl1",results={@Result(name="root",type="json")})
	public String querydtjl1(){
		String hql1="select distinct userinfo.userId from Studentifno where SName like '%"+queryWord+"%'";
		List<String> studentno= this.baseservice.findHql(String.class, hql1);
		
		String hql2 = "select userinfo.userId from Xsdyjl";
		List<String> no = this.baseservice.findHql(String.class, hql2);
		if(studentno.size()>0){
			if(no.size()>0){
				for(int i=0;i<no.size();i++){
					if(studentno.contains(no.get(i))){
						total1+=1;
					}
				}
			}
		}
		
		HttpSession session = ServletActionContext.getRequest().getSession();
		session.setAttribute("dtjltotal", total1);
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
	@Action(value="/querydtjl",results={@Result(name="root",type="json")})
	public String querydtjl(){
		HttpSession session = ServletActionContext.getRequest().getSession();
		total = (Integer)session.getAttribute("dtjltotal");
		
		//通过测试者名称从Studentifno表里模糊查询测试者id
		String hql1="select distinct userinfo.userId from Studentifno where SName like '%"+queryWord+"%'";
		List<String> studentno= this.baseservice.findHql(String.class, hql1);
		
		//查生答题记录里面的学生id
		String hql2="select distinct userinfo.userId from Xsdyjl";
		List<String> userid= this.baseservice.findHql(String.class, hql2);
		
		//得出要查询的测试者id,并根据id获得答题记录信息
		List<Xsdyjl> datizhe =  new ArrayList<Xsdyjl>();
		for(int j=0;j<studentno.size();j++){
			if(userid.contains(studentno.get(j))){
				String hql3="from Xsdyjl where userinfo.userId='"+studentno.get(j)+"'";
				List<Xsdyjl> dtz= this.baseservice.findHql(Xsdyjl.class, hql3);
				for(int i=0;i<dtz.size();i++){
					datizhe.add(dtz.get(i));
				}
				
			}
		}
		
		//通过获得的答题记录信息里的UserId（测试者id）、sjno（试卷编号）查到对应的SName（测试者名称）、zxx（试卷名称）
		Dtjl dtjl =new Dtjl();
		for(int i=0;i<datizhe.size();i++){
			String h1 =  "select SName from Studentifno where userinfo.userId = '"+datizhe.get(i).getUserinfo().getUserId()+"'";
			String h2 =  "select zxx from Sjnr where sjno = '"+datizhe.get(i).getSjnr().getSjno()+"'";
			List<String> username= this.baseservice.findHql(String.class,h1);
			List<String> papername1 = this.baseservice.findHql(String.class,h2);
			dtjl.setPapername(papername1.get(0));
			dtjl.setStudentname(username.get(0));
			rows.add(dtjl);
		}
		return "root";
	}
	
	
}
