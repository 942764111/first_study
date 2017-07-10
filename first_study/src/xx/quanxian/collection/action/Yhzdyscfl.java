/*

 *@(#)xx.quanxian.collection.action
 *@Yhzdyscfl.java.java  
 *@创建时间:2011-8-13下午03:16:55
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

import xx.collection.bean.Zysc;
import xx.quanxian.service.BaseService;

import com.googlecode.jsonplugin.annotations.JSON;
import com.opensymphony.xwork2.ActionSupport;

/**
 * @Yhzdyscfl <code>Yhzdyscfl</code>
 * @author  {徐鹏飞}
 * @version {2011-08-13}
 * @{实现用户对自定义内容的操作} 
 */
@Controller
@Scope("prototype")
@Namespace("")
@ParentPackage("default-package")
@SuppressWarnings("serial")
public class Yhzdyscfl extends ActionSupport {
	@Resource(name="baseService")
	private BaseService service;
	private List<Zysc> rows  = new ArrayList<Zysc>();
	private int total;//数据总数total
	//datagrid参数
	private int page;//当前页数
	private int rows_s;//每页显示row_s条数据
	private int zdyflno;
	private static int IDs;
	private int dmtzybh;
	private String sskcmc1;
	private String sszmc1;
	private Integer sskcxh1;
	
	@JSON(serialize=false)
	public BaseService getService() {
		return service;
	}
	public void setService(BaseService service) {
		this.service = service;
	}
	
	public int getDmtzybh() {
		return dmtzybh;
	}
	public void setDmtzybh(int dmtzybh) {
		this.dmtzybh = dmtzybh;
	}
	public List<Zysc> getRows() {
		return rows;
	}
	public void setRows(List<Zysc> rows) {
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
	@JSON(serialize=false)
	public int getZdyflno() {
		return zdyflno;
	}
	public void setZdyflno(int zdyflno) {
		this.zdyflno = zdyflno;
	}
	
	/**
	 * @return the sskcmc1
	 */
	public String getSskcmc1() {
		return sskcmc1;
	}
	/**
	 * @param sskcmc1 the sskcmc1 to set
	 */
	public void setSskcmc1(String sskcmc1) {
		this.sskcmc1 = sskcmc1;
	}
	/**
	 * @return the sszmc1
	 */
	public String getSszmc1() {
		return sszmc1;
	}
	/**
	 * @param sszmc1 the sszmc1 to set
	 */
	public void setSszmc1(String sszmc1) {
		this.sszmc1 = sszmc1;
	}
	/**
	 * @return the sskcxh1
	 */
	/**
	 * @return the sskcxh1
	 */
	public Integer getSskcxh1() {
		return sskcxh1;
	}
	/**
	 * @param sskcxh1 the sskcxh1 to set
	 */
	public void setSskcxh1(Integer sskcxh1) {
		this.sskcxh1 = sskcxh1;
	}
	
	/**
	 * zdyfl_list <code>zdyfl_list</code>
	 * @author  {徐鹏飞}
	 * @version {2011-08-13}
	 * @{实现页面的跳转} 
	 */
	@Action(value="/zdyfl_list",results={@Result(name="success",type="json")})
	public String zdyfl_list(){
		//System.out.println(zdyflno);
		this.IDs=zdyflno;
		return SUCCESS;
	}
	
	/**
	 * @Statistics <code>{tiaozh}</code>
	 * @author  {徐鹏飞}
	 * @version {2011-8-22下午03:12:47}
	 * @{实现页面的跳转} 
	 */
	@Action(value="/tiaozh",results={@Result(name="success",location="/page/collection/yhzdy_sc.jsp")})
	public String tiaozh(){
		return SUCCESS;
	}
	/**
	 * @Statistics <code>{yhzdyfl_xx}</code>
	 * @author  {徐鹏飞}
	 * @version {2011-8-22下午03:12:47}
	 * @{查看该分类下的收藏内容} 
	 */
	@Action(value="/yhzdyfl_xx",results={@Result(name="success",type="json")})
	public String yhzdyfl_xx(){
//		System.out.println(IDs+".............................");
		String[] keys = new String[1];
		Object [] values = new Object[1];
		keys[0] = "zdyflno";
		values[0] = IDs;
		List<Zysc> list = new ArrayList<Zysc>();
		list = this.service.find(Zysc.class, "Zysc", keys, values);
		rows = this.service.findBPfenye("from Zysc where zylx<10 and zdyflno=? ", IDs, page, rows_s);
		total = list.size();
		return SUCCESS;
	}
	/**
	 * @Statistics <code>{tiaozh}</code>
	 * @author  {}
	 * @version {2011-8-22下午03:12:47}
	 * @{实现页面的跳转} 
	 */
	@Action(value="/dakaidmt",results={@Result(name="success",type="json")})
	public String dakaidmt(){
//		this.dmtzybh = dmtzybh;
//		int aa = Integer.parseInt(s)
		HttpSession session1 = ServletActionContext.getRequest().getSession();
		session1.setAttribute("courseName",sskcmc1);
		session1.setAttribute("chapterName",sszmc1);
		session1.setAttribute("keci",sskcxh1);
		return SUCCESS;
	}
}
