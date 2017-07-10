
	/**
	 * 文件名：Jixuxuexi.java
	 *
	 * 版本信息：
	 * 日期：2011-8-14
	 * 作者：tlq
	 * Copyright 河北北方学院信息科学与工程学院科研所 Corporation 2011 
	 * 版权所有
	 *
	 */
	
package xx.xuexi.action;

import java.text.SimpleDateFormat;
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

import xx.collection.bean.Dmtzl;
import xx.collection.bean.Jxjh;
import xx.collection.bean.Zljl;
import xx.quanxian.service.BaseService;

import com.googlecode.jsonplugin.annotations.JSON;
import com.opensymphony.xwork2.ActionSupport;


	/**
 * 此类描述的是：主要是处理对资料的继续学习的
 * @author: tlq
 * @version: 2011-8-14 下午06:45:45 
 */
@Controller
@Scope("prototype")
@Namespace("")
@ParentPackage("default-package")
public class Jixuxuexi extends ActionSupport {
	
	List<Zljl> zljl = new ArrayList<Zljl>();
	List<Zljl_z> rows = new ArrayList<Zljl_z>();
	
	@Resource(name="baseService")
	private BaseService baseService;
	private int page;								//分页时，当前的页数，从前台接收
	private int rows_s;								//分页时，当前的页面的行数，从前台接收
	private int total;								//查询出记录的总条数
	private List<String> jxjh = new ArrayList<String>();
	private String jhmc;
	private String queryWord;						//查询功能时的查询参数，所要根据查询的关键字
	
	@JSON(serialize=false)
	public String getQueryWord() {
		return queryWord;
	}
	@JSON(deserialize=true)
	public void setQueryWord(String queryWord) {
		this.queryWord = queryWord;
	}
	public String getJhmc() {
		return jhmc;
	}
	public void setJhmc(String jhmc) {
		this.jhmc = jhmc;
	}
	public List<String> getJxjh() {
		return jxjh;
	}
	public void setJxjh(List<String> jxjh) {
		this.jxjh = jxjh;
	}
	public int getPage() {
		return page;
	}
	@JSON(deserialize=true)
	public void setPage(int page) {
		this.page = page;
	}
	public int getRows_s() {
		return rows_s;
	}
	@JSON(deserialize=true)
	public void setRows_s(int rows_s) {
		this.rows_s = rows_s;
	}
	public int getTotal() {
		return total;
	}
	@JSON(deserialize=true)
	public void setTotal(int total) {
		this.total = total;
	}
	
	@JSON(serialize=false)
	public List<Zljl> getZljl() {
		return zljl;
	}

	public void setZljl(List<Zljl> zljl) {
		this.zljl = zljl;
	}

	
	@JSON(deserialize=true)
	public List<Zljl_z> getRows() {
		return rows;
	}
	public void setRows(List<Zljl_z> rows) {
		this.rows = rows;
	}
	
	/**
	 * @{JiXuXueXi.action}
	 * 跳转功能
	 * 跳转到继续学习的页面
	 * 
	*/
	@Action(value="JiXuXueXi",results={@Result(name="root",location="/page/xuexi/Zljl.jsp")})
	public String Zljl(){
		return "root";
	}
	
	/**
	 * @{ZljlJson.action}
	 * 显示功能
	 * 根据当前登录的用户将该用户上次没看完的资料列出来
	 * 
	*/
	@Action(value="ZljlJson",results={@Result(name="root",type="json")})
	public String JiXuxuexi(){
		
		HttpSession hs = ServletActionContext.getRequest().getSession();
		String userid = (String)hs.getAttribute("userid");
		String[] keys = {"UserId"};
		Object[] values = {userid};
		zljl = this.baseService.findByOne(Zljl.class, "Zljl", keys, values, page, rows_s);
		total = this.baseService.getTotalPages("Zljl", keys, values);
		SimpleDateFormat sdf=new SimpleDateFormat("EE yyyy年MM月 dd日  hh:mm:ss ");

		for(Zljl z:zljl){
			Dmtzl d = new Dmtzl();
			d = this.baseService.find(Dmtzl.class, z.getDmtzl().getZlbh());
			Zljl_z zl = new Zljl_z();
			zl.setZlmc(d.getZlmc());
			String opentimes=sdf.format(z.getOpenTime());
			zl.setTime(opentimes);
			zl.setZlbh(z.getDmtzl().getZlbh());
			rows.add(zl);
		}
		
		return "root";
	}
	/**
	 * @{queryJxjh1.action}
	 * 查询功能
	 * 将所有的教学计划查询出来，传到前台用来完成自动添加组件
	 * 
	*/
	@Action(value="queryJxjh1",results={@Result(name="root",type="json")})
	public String queryJxjh(){
		this.jxjh=this.baseService.find(String.class, "Jxjh", "jhmc");
		return "root";
	}
	/**
	 * @{queryJxnr.action}
	 * 查询功能
	 * 根据当前登录的用户和用户选择的教学计划，将该用户上次没看完的且属于所选教学计划的资料列出来
	 * 
	*/
	@Action(value="queryJxnr",results={@Result(name="root",type="json")})
	public String queryJxnr(){
		HttpSession hs = ServletActionContext.getRequest().getSession();
		String userid = (String)hs.getAttribute("userid");
		String[] keys1 = {"jhmc"};
		Object[] values1 = {queryWord};
		int jxjh_id = this.baseService.find(Jxjh.class, "Jxjh", keys1, values1).get(0).getId();
		String[] keys = {"UserId","jxjh_id"};
		Object[] values = {userid,jxjh_id};
		zljl = this.baseService.find(Zljl.class, "Zljl", keys, values);
		total = zljl.size();
		SimpleDateFormat sdf=new SimpleDateFormat("EE yyyy年MM月 dd日  hh:mm:ss ");
		
		for(Zljl z:zljl){
			Dmtzl d = new Dmtzl();
			d = this.baseService.find(Dmtzl.class, z.getDmtzl().getZlbh());
			Zljl_z zl = new Zljl_z();
			zl.setZlmc(d.getZlmc());
			String opentimes=sdf.format(z.getOpenTime());
			zl.setTime(opentimes);
			zl.setZlbh(z.getDmtzl().getZlbh());
			rows.add(zl);
		}
		return "root";
	}
}
