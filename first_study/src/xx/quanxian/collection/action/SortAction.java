/*
 *@(#)xx.quanxian.collection.action
 *@Statistics.java.java  
 *@创建时间:2011-8-22下午03:12:47
 *@作者：xupengfei
 *@Copyright 2009-2010 河北北方学院信息科学与工程学院科研所 
 */
package xx.quanxian.collection.action;

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

import xx.collection.bean.Userinfo;
import xx.collection.bean.Xz;
import xx.collection.bean.Yhzdymc;
import xx.collection.bean.Zysc;
import xx.quanxian.service.BaseService;

import com.googlecode.jsonplugin.annotations.JSON;
import com.opensymphony.xwork2.ActionSupport;

/**
 * @Statistics <code>{类名称}</code>
 * @author  {徐鹏飞}
 * @version {2011-8-22下午03:12:47}
 * @{实现操作yhzdyfl表} 
 */
@Controller
@Scope("prototype")
@Namespace("")
@ParentPackage("default-package")
@SuppressWarnings("serial")
public class SortAction extends ActionSupport {
	
	@Resource(name="baseService")
	private BaseService service;
	private Yhzdymc yhzdymc;
	private String zdyflmc;
	private int zdyflno;
	private List<Yhzdymc> rows= new ArrayList<Yhzdymc>();
	private int total;//数据总数total
	//datagrid参数
	private int page;//当前页数
	private int rows_s;//每页显示row_s条数据
	private String message = "";
	private static String userId;
	@JSON(serialize=false)
	public BaseService getService() {
		return service;
	}
	public void setService(BaseService service) {
		this.service = service;
	}
	@JSON(serialize=false)
	public Yhzdymc getYhzdymc() {
		return yhzdymc;
	}
	@JSON(deserialize=true)
	public void setYhzdymc(Yhzdymc yhzdymc) {
		this.yhzdymc = yhzdymc;
	}
	
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	@JSON(serialize=false)
	public String getZdyflmc() {
		return zdyflmc;
	}
	public void setZdyflmc(String zdyflmc) {
		this.zdyflmc = zdyflmc;
	}
	@JSON(serialize=false)
	public int getZdyflno() {
		return zdyflno;
	}
	public void setZdyflno(int zdyflno) {
		this.zdyflno = zdyflno;
	}
	public List<Yhzdymc> getRows() {
		return rows;
	}
	public void setRows(List<Yhzdymc> rows) {
		this.rows = rows;
	}
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	@JSON(serialize=false)
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	@JSON(serialize=false)
	public int getRows_s() {
		return rows_s;
	}
	public void setRows_s(int rows_s) {
		this.rows_s = rows_s;
	}
	/**
	 * @Statistics <code>{listy}</code>
	 * @author  {徐鹏飞}
	 * @version {2011-8-22下午03:12:47}
	 * @{实现页面的跳转} 
	 */
	@Action(value="/listy",results={@Result(name="success",location="/page/collection/listyhzdy.jsp")})
	public String listy(){
		return SUCCESS;
	}

	/**
	 * @Statistics <code>{listyhzdymc}</code>
	 * @author  {徐鹏飞}
	 * @version {2011-8-22下午03:12:47}
	 * @{实现yhzdyfl表的内容全部列出} 
	 */
	@Action(value="/listyhzdymc",results={@Result(name="success",type="json")})
	public String listyhzdymc(){
		HttpSession session1 = ServletActionContext.getRequest().getSession();
		userId = (String) session1.getAttribute("userid");
		List<Yhzdymc> list = new ArrayList<Yhzdymc>();
		list = this.service.findByProperty( "from Yhzdymc where userId =?", userId);
		rows = this.service.findBPfenye("from Yhzdymc where userId =?", userId, page, rows_s);
		total = list.size();
		return SUCCESS;
	}
	/**
	 * @Statistics <code>{addyhzdymc}</code>
	 * @author  {徐鹏飞}
	 * @version {2011-8-22下午03:12:47}
	 * @{实现yhzdyfl表内容的添加} 
	 */
	@Action(value="/addyhzdymc",results={@Result(name="success",type="json")})
	public String addyhzdymc(){
		Userinfo userinfo = new Userinfo();
		userinfo.setUserId(userId);
		yhzdymc.setUserinfo(userinfo);
		this.service.save(yhzdymc);
		return SUCCESS;
	}
	/**
	 * @Statistics <code>{deleteyhzdymc}</code>
	 * @author  {徐鹏飞}
	 * @version {2011-8-22下午03:12:47}
	 * @{实现yhzdyfl表内容的删除} 
	 */
	@Action(value="/deleteyhzdymc",results={@Result(name="success",type="json")})
	public String deleteyhzdymc(){
		//System.out.println("++++++++++++++++++++++"+zdyflno);
		String[] keys = new String[1];
		Object [] values = new Object[1];
		keys[0] = "zdyflno";
		values[0] = zdyflno;
		List <Zysc> ll =new ArrayList<Zysc>(); 
		ll = this.service.find(Zysc.class, "Zysc", keys, values);
		for(Zysc m:ll){
			//m.setYhzdymc(null);
			this.service.delete(m);
		}
		yhzdymc = this.service.find(Yhzdymc.class,zdyflno);
		this.service.delete(yhzdymc);
		return SUCCESS;
	}
	/**
	 * @Statistics <code>{updateyhzdymc}</code>
	 * @author  {徐鹏飞}
	 * @version {2011-8-22下午03:12:47}
	 * @{实现yhzdyfl表内容的修改} 
	 */
	@Action(value="/updateyhzdymc",results={@Result(name="success",type="json")})
	public String updateyhzdymc(){
		this.service.update(yhzdymc);
		return SUCCESS;
	}
	/**
	 * @Statistics <code>{updateyhzdymc}</code>
	 * @author  {徐鹏飞}
	 * @version {2011-8-22下午03:12:47}
	 * @{实现yhzdyfl表内容的修改} 
	 */
//	@Action(value="/findyhzdymc",results={@Result(name="success",type="json")})
//	public String findyhzdymc(){
//		this.service.find(Yhzdymc.class,zdyflno);
//		return SUCCESS;
//	}
}
