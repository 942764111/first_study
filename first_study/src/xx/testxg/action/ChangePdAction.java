/*
 *@(#)xx.testxg.action
 *@ChangePdAction.java.java  
 *@创建时间:2011-11-30下午08:12:03
 *@作者：Administrator
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

import xx.collection.bean.Pd_sim;
import xx.quanxian.service.BaseService;

import com.googlecode.jsonplugin.annotations.JSON;
import com.opensymphony.xwork2.ActionSupport;

/**
 * @ChangePdAction <code>{类名称}</code>
 * @author  {张晓莉}
 * @version {版本,常用时间代替}
 * @{功能描述:} 
 */
@Controller
@Scope("prototype")
@ParentPackage("default-package")
@Namespace("")
public class ChangePdAction extends ActionSupport {
	
	
	@Resource(name = "baseService")
	private BaseService baseservice;
	private int ghth;
	private int page;//当前页
	private int rows_s;//每页显示的条数
	private int total;//记录数据条数
	private List<Pd_sim> rows =new ArrayList<Pd_sim>();
	private String queryType;	//查询功能时的查询参数，所要根据查询的类型
	private String queryWord;	//查询功能时的查询参数，所要根据查询的关键字
	private int a;
	private List<Pd_sim> pdlist = new ArrayList<Pd_sim>();
	
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


	public List<Pd_sim> getRows() {
		return rows;
	}


	public void setRows(List<Pd_sim> rows) {
		this.rows = rows;
	}


	public List<Pd_sim> getPdlist() {
		return pdlist;
	}


	public void setPdlist(List<Pd_sim> pdlist) {
		this.pdlist = pdlist;
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


	//本方法用来出卷完成时改变试卷里的判断题时用,
	@Action(value="/changepd",results={@Result(name="root",type="json")})
	public String changepd(){
		HttpSession hs = ServletActionContext.getRequest().getSession();
		int zbh1=(Integer)hs.getAttribute("ghzno1");
		int zbh2=(Integer)hs.getAttribute("ghzno2");
		rows=this.baseservice.findSql(Pd_sim.class,"from Pd_sim where zbh>='"+zbh1+"' and zbh<='"+zbh2+"'", page, rows_s);
		total = this.baseservice.getTotalSql("select count(*) from Pd_sim where zbh>='"+zbh1+"' and zbh<='"+zbh2+"'");
		return "root";
	}
	
	
	
	//检验查询是否为空,并得出数据条数
	@Action(value="/ghquerypd1",results={@Result(name="root",type="json")})
	public String ghquerypd1(){
		HttpSession hs = ServletActionContext.getRequest().getSession();
		int zbh1=(Integer)hs.getAttribute("ghzno1");
		int zbh2=(Integer)hs.getAttribute("ghzno2");
		String hql = "select count(*) from Pd_sim where "+queryType+" like '%"+queryWord+"%' and zbh>='"+zbh1+"' and zbh<='"+zbh2+"'";
		int to1 = this.baseservice.getTotalSql(hql);
		hs.setAttribute("tempto", to1);
		if(to1!=0){
			a=1;
		}else{
			a=0;
		}
		return "root";
	}
	
	
	/**
	 * @{ghquerypd.action}
	 * 该方法是用来根据关键字查询判断题的，查询时会从页面接收两个参数，queryType，queryWord
	 * queryType是要查询时所根据的类型，queryWord是关键字
	 * 查询出的结果页要以分页的形式显示
	*/
	@Action(value="/ghquerypd",results={@Result(name="root",type="json")})
	public String ghquerypd(){
		HttpSession hs = ServletActionContext.getRequest().getSession();
		total=(Integer)hs.getAttribute("tempto");
		int zbh1=(Integer)hs.getAttribute("ghzno1");
		int zbh2=(Integer)hs.getAttribute("ghzno2");
		rows=this.baseservice.findSql(Pd_sim.class,"from Pd_sim where "+queryType+" like '%"+queryWord+"%' and zbh>='"+zbh1+"' and zbh<='"+zbh2+"'", page, rows_s);
//		for(int i=0;i<pdlist.size();i++){
//			Pd_sim element = new Pd_sim();
//        	element.setTg(pdlist.get(i).getTg());
//        	element.setTh(pdlist.get(i).getTh());
//        	element.setDa(pdlist.get(i).getDa());
//        	rows.add(element);   //把数据放进rows，实现分页查询显示    	
//        	
//        }
		
		return "root";
	}
}
