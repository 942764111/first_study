/*
 *@(#)xx.spdh.action
 *@WjlxAction.java.java  
 *@创建时间:2011-8-6上午09:27:23
 *@作者：朱永科
 *@Copyright 2009-2010 河北北方学院信息科学与工程学院科研所 
 */

package xx.spdh.action;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import xx.collection.bean.Wjlx;
import xx.quanxian.service.BaseService;

import com.googlecode.jsonplugin.annotations.JSON;
import com.opensymphony.xwork2.ActionSupport;

/**
 * @WjlxAction <code>{类名称}</code>
 * @author  {作者}
 * @version {版本,常用时间代替}
 * @{功能描述:文件类型的增删改查} 
 */
@Controller
@Scope("prototype")
@ParentPackage("default-package")
@Namespace("")
@SuppressWarnings("serial")
public class WjlxAction extends ActionSupport {
	@Resource(name="baseService")
	private BaseService baseservice;
	
	private Wjlx wjlx;//对象实例  	
	private List<Wjlx> rows = new ArrayList<Wjlx>();//对象集合    
	private int page;//当前页
	private int rows_s;//每一页显示的条数
	private int total;//记录数量
	
	private String lxm;
	private String ms;
	private String kzm;
	private List<Wjlx> wjlxlist;
	
	private String a;
	
	@JSON(serialize=false)
	public BaseService getBaseservice() {
		return baseservice;
	}
	public void setBaseservice(BaseService baseservice) {
		this.baseservice = baseservice;
	}
	
	@JSON(serialize=false)
	public Wjlx getWjlx() {
		return wjlx;
	}
	public void setWjlx(Wjlx wjlx) {
		this.wjlx = wjlx;
	}
	
	public List<Wjlx> getRows() {
		return rows;
	}
	public void setRows(List<Wjlx> rows) {
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
	
	public String getLxm() {
		return lxm;
	}
	@JSON(deserialize=true)
	public void setLxm(String lxm) {
		this.lxm = lxm;
	}
	
	public String getMs() {
		return ms;
	}
	public void setMs(String ms) {
		this.ms = ms;
	}
	
	public String getKzm() {
		return kzm;
	}
	public void setKzm(String kzm) {
		this.kzm = kzm;
	}
	
	@JSON(serialize=false)
	public List<Wjlx> getWjlxlist() {
		return wjlxlist;
	}
	public void setWjlxlist(List<Wjlx> wjlxlist) {
		this.wjlxlist = wjlxlist;
	}
	
	public String getA() {
		return a;
	}
	public void setA(String a) {
		this.a = a;
	}
	/**
	 * @{insertwjlx1.action}
	 * @{校验类型名是否为空}
	 * @return {/spdh/datagrid_wjlx.jsp} {显示所有文件类型信息页面}
	 * @exception {说明在某情况下,将发生什么异常}
	*/
	@Action(value="/insertwjlx1",results={@Result(name="success",type="json")})
	public String insertwjlx1() {
		List<String> lxmlist = this.baseservice.find(String.class, "Wjlx", "lxm");
		if(lxmlist.contains(lxm)) {
			a = "0";
		} else {
			a = "1";
		}
		
		return SUCCESS;
	}
	/**
	 * @{insertwjlx.action}
	 * @{添加}
	 * @return {/spdh/datagrid_wjlx.jsp} {显示所有文件类型信息页面}
	 * @exception {说明在某情况下,将发生什么异常}
	*/
	@Action(value="/insertwjlx",results={@Result(name="success",type="json")})
	public String insertwjlx() {
		Wjlx wjlx = new Wjlx();
		wjlx.setLxm(lxm);
		wjlx.setMs(ms);
		wjlx.setKzm(kzm);
		this.baseservice.save(wjlx);
		return SUCCESS;
	}
	
	/**
	 * @{deletewjlx.action}
	 * @param {wjlxlist.action} {显示文件类型信息}
	 * @return {wjlxlist.action} {显示所有文件类型信息} 
	 * @{删除文件类型信息}
	 * @exception {说明在某情况下,将发生什么异常}
	*/
	@Action(value="deletewjlx",results={@Result(name="success",type="json")})
	public String deletewjlx(){
		wjlx=this.baseservice.find(Wjlx.class,lxm);//通过lxm删除一条数据，lxm要进行反序列化。
		this.baseservice.delete(wjlx);// 删除数据
		return SUCCESS;
	}
	
	/**
	 * @{updatewjlx}
	 * @param {wjlxlist.action} {显示文件类型信息}
	 * @return {wjlxlist.action} {显示所有文件类型信息}
	 * @{修改文件类型信息}
	 * @exception {说明在某情况下,将发生什么异常}
	 */
	@Action(value="/updatewjlx",results={@Result(name="success",type="json")})
	public String updatewjlx() {
		Wjlx wjlx = new Wjlx();
		wjlx.setLxm(lxm);
		wjlx.setMs(ms);
		wjlx.setKzm(kzm);
		this.baseservice.update(wjlx);
		return SUCCESS;
	}

	/**
	 * @{wjlxlist.action}
	 * @param {wjlxlist} {显示所有文件类型信息}
	 * @return {/spdh/datagrid_wjlx.jsp} {显示所有文件类型信息页面}
	 * @{显示所有文件类型信息}
	 * @exception {说明在某情况下,将发生什么异常}
	*/
	@Action(value="/wjlxlist",results={@Result(name="success",type="json")})
	public String wjlxlist() {
		rows = this.baseservice.findAll(Wjlx.class,"Wjlx", page, rows_s); //把所有数据放在rows里面以实现分页查询    
        total=this.baseservice.getTotal("Wjlx");  //记录条数的记录  
        return SUCCESS; 
	}
	
}
