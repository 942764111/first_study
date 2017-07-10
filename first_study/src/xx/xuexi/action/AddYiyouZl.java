/*
 *@(#)xx.xuexi.action
 *@AddYiyouZl.java.java  
 *@创建时间:2011-10-10下午04:01:34
 *@作者：tlq
 *@Copyright 2009-2010 河北北方学院信息科学与工程学院科研所 
 */

package xx.xuexi.action;

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


import xx.collection.bean.Dmtzl_sim;

import xx.quanxian.service.BaseService;

import com.googlecode.jsonplugin.annotations.JSON;
import com.opensymphony.xwork2.ActionSupport;

/**
 * @AddYiyouZl <code>{类名称}</code>
 * @author  {tlq}
 * @version {版本,常用时间代替}
 * @{主要处理将资料库中已有的资料添加到教学内容中去:} 
 */
@Controller
@Scope("prototype")
@Namespace("")
@ParentPackage("default-package")
public class AddYiyouZl extends ActionSupport {
	@Resource(name="baseService")
	private BaseService baseService;
	
	private String type;
	private String name;
	private String wjlx;
	List<Dmtzl_sim> rows = new ArrayList<Dmtzl_sim>();
	private int total;
	private int page;								//分页时，当前的页数，从前台接收
	private int rows_s;								//分页时，当前的页面的行数，从前台接收

	private int tip;
	
	
	/**
	 * @return the total
	 */
	public int getTotal() {
		return total;
	}
	/**
	 * @param total the total to set
	 */
	public void setTotal(int total) {
		this.total = total;
	}
	public int getTip() {
		return tip;
	}
	public void setTip(int tip) {
		this.tip = tip;
	}
	@JSON(deserialize=true)
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	@JSON(deserialize=true)
	public int getRows_s() {
		return rows_s;
	}
	public void setRows_s(int rows_s) {
		this.rows_s = rows_s;
	}
	@JSON(deserialize=true)
	public List<Dmtzl_sim> getRows() {
		return rows;
	}
	public void setRows(List<Dmtzl_sim> rows) {
		this.rows = rows;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getWjlx() {
		return wjlx;
	}
	public void setWjlx(String wjlx) {
		this.wjlx = wjlx;
	}

	/**
	 * @{queryZl.action}
	 * 查询呢功能
	 * 在添加资料库中已有的资料的时候，会先将符合条叫的查询出来
	 * 此方法主要是从页面接收查询的条件，放入三个静态变量中
	*/
	@Action(value="/queryZl",results={@Result(name="success",type="json")})
	public String queryzl(){
		HttpSession hs = ServletActionContext.getRequest().getSession();
		hs.setAttribute("s_type", type);
		hs.setAttribute("s_name", name);
		hs.setAttribute("s_wjlx", wjlx);

		String hql="from Dmtzl_sim where "+type+" like '%"+name+"%' and lxm='"+wjlx+"' order by zlbh asc";
		
		rows=this.baseService.findSql(Dmtzl_sim.class, hql, 1, 5);
		
		tip=rows.size();
		return "success";
	}
	
	/**
	 * @{querylistZl.action}
	 * 查询功能
	 * 是真正用来查询的，根据三个静态变量的值
	*/
	@Action(value="/querylistZl",results={@Result(name="success",type="json")})
	public String querylistZl(){
		HttpSession hs = ServletActionContext.getRequest().getSession();
		String s_type=(String) hs.getAttribute("s_type");
		String s_name=(String) hs.getAttribute("s_name");
		String s_wjlx=(String) hs.getAttribute("s_wjlx");
		if(s_type==null||s_name==null||s_wjlx==null){
			
		}else{

			String hql="from Dmtzl_sim where "+s_type+" like '%"+s_name+"%' and lxm='"+s_wjlx+"' order by zlbh asc";
			rows=this.baseService.findSql(Dmtzl_sim.class, hql, page, rows_s);
			hql="select count(*) "+hql;
			total=this.baseService.getTotalSql(hql);
		}
		return "success";
	}
	
}
