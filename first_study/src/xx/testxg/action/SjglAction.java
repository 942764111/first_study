/*
 *@(#)xx.testxg.action
 *@SjglAction.java.java  
 *@创建时间:2011-11-18上午09:10:53
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

import xx.collection.bean.Sjnr;
import xx.quanxian.service.BaseService;

import com.googlecode.jsonplugin.annotations.JSON;
import com.opensymphony.xwork2.ActionSupport;

/**
 * @SjglAction <code>{类名称}</code>
 * @author  {张晓莉}
 * @version {版本,常用时间代替}
 * @{功能描述:学生老师查看试卷} 
 */
@Controller
@Scope("prototype")
@ParentPackage("default-package")
@Namespace("")
public class SjglAction extends ActionSupport {
	@Resource(name="baseService")
	private BaseService baseservice;
	
	
	private List<Sjnr> rows = new ArrayList<Sjnr>();//对象集合    
	private int page;//当前页
	private int rows_s;//每一页显示的条数
	private int total=0;  
	
	
	@JSON(serialize=false)
	public BaseService getBaseservice() {
		return baseservice;
	}



	public void setBaseservice(BaseService baseservice) {
		this.baseservice = baseservice;
	}



	public List<Sjnr> getRows() {
		return rows;
	}



	public void setRows(List<Sjnr> rows) {
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



	/**
	 * @{sjlist.action}
	 * @param {rows} {显示所有试卷信息}
	 * @return {/testxg/sjgl.jsp} {显示所有试卷信息页面}
	 * @{显示所有试卷信息}
	 * @exception {说明在某情况下,将发生什么异常}
	*/
	@Action(value="/sjlist",results={@Result(name="root",type="json")})
	public String sjlist(){		
		HttpSession session = ServletActionContext.getRequest().getSession();
		String userid = (String)session.getAttribute("uid");
		String hql = "select type from Userinfo where userId='"+userid+"'";
		List<String> type = this.baseservice.findHql(String.class, hql);
		if(type.get(0).equals("T")){
			 rows = this.baseservice.findAll(Sjnr.class,"Sjnr", page, rows_s); //把所有数据放在rows里面以实现分页查询    
		     total=this.baseservice.getTotal("Sjnr");  //记录条数的记录  
		}else{
			String hql1 = "select distinct sjnr.sjno from Xsdyjl where userinfo.userId='"+userid+"'";
			List<Integer> sjno = this.baseservice.findHql(Integer.class, hql1);
			for(int i=0;i<sjno.size();i++){
				String hql2 = "from Sjnr where sjno='"+sjno.get(i)+"'";
				List<Sjnr> sjnr = this.baseservice.findHql(Sjnr.class, hql2);
				rows.add(sjnr.get(0));
				total+=1;
			}
			
		}
			
       
        return "root";  
	}
}
