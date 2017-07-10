
	/**
	 * 文件名：Fq.java
	 *
	 * 版本信息：
	 * 日期：2011-8-18
	 * 作者：tlq
	 * Copyright 河北北方学院信息科学与工程学院科研所 Corporation 2011 
	 * 版权所有
	 *
	 */
	
package xx.roleAndfunction.action;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.stereotype.Controller;

import com.googlecode.jsonplugin.annotations.JSON;
import com.opensymphony.xwork2.ActionSupport;

import xx.adminservice.AdminService;
import xx.collection.bean.Functions;
import xx.collection.bean.Modules;
import xx.collection.bean.Rolefunction;
import xx.collection.bean.RolefunctionId;
import xx.collection.bean.Roles;
import xx.quanxian.service.BaseService;

	/**
 * 此类描述的是：是用来对用户的赋权行为进行的一系列操作
 * @author: tlq
 * @version: 2011-8-18 下午05:00:06 
 */
public class Fq extends ActionSupport implements ServletRequestAware {

	/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
	@Resource(name="adminService")
	private AdminService adminService;
	@Resource(name="baseService")
	private BaseService baseService;
	private int page;								//分页时，当前的页数，从前台接收
	private int rows_s;								//分页时，当前的页面的行数，从前台接收
	private int total;								//查询出记录的总条数
	private static int ID;
	public int roleid;
	private List<Modules> modulelist;
	List<Function> rows = new ArrayList<Function>();
	String funId;
	private static List<Rolefunction> rolefuns = new ArrayList<Rolefunction>();
	
	private HttpServletRequest request;
	
	public void setServletRequest(HttpServletRequest request) {
		  this.request = request;
	}
	public static List<Rolefunction> getRolefuns() {
		return rolefuns;
	}
	public static void setRolefuns(List<Rolefunction> rolefuns) {
		Fq.rolefuns = rolefuns;
	}
	@JSON(serialize=false)
	public String getFunId() {
		return funId;
	}
	public void setFunId(String funId) {
		this.funId = funId;
	}
	public List<Function> getRows() {
		return rows;
	}
	public void setRows(List<Function> rows) {
		this.rows = rows;
	}
	public int getPage() {
		return page;
	}
	@JSON(serialize=false)
	public List<Modules> getModulelist() {
		return modulelist;
	}
	public void setModulelist(List<Modules> modulelist) {
		this.modulelist = modulelist;
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
	public int getRoleid() {
		return roleid;
	}
	public void setRoleid(int roleid) {
		this.roleid = roleid;
	}
	/**
	 * @{fuquanRole.action}
	 * 用来接收对某一角色赋权的角色id
	*/
	@Action(value="/fuquanRole",results={@Result(name="root",type="json")})
	public String fuquanRole(){
		ID = roleid;
		return "root"; 
	}
	/**
	 * @{Fqjson.action}
	 *该方法是当在页面上分页将功能给列出来
	 *里面会用到一个中间类，因为模块分类名称和模块名称，功能名称不在一个表中，所以使用中间类将它们放到一起
	 *也可以在数据库中写一个存储过程，在这里直接调用
	 *  tlq
	*/
	@Action(value="/Fqjson",results={@Result(name="root",type="json")})
	public String jsonFunc(){
		List<Rolefunction> rolefunction = new ArrayList<Rolefunction>();
		List<Functions> fs = new ArrayList<Functions>(); 
		fs = this.baseService.findAll(Functions.class, "Functions", page, rows_s);
		total = this.baseService.getTotal("Functions");
		List<Rolefunction> rfs=this.baseService.findRoleAndFunctionsChecked(ID);
		List<String> ls = new ArrayList<String>();
		for(Rolefunction rf:rfs){
			ls.add(rf.getId().getActionname());//ls中存放roleid是ID的所有actionname例如：“yhgl”
		}
		for(Functions f:fs){
			Function ft = new Function();
			
			String[] keys = {"mname"};
			Object[] values = {f.getModules().getMname()};
			List<Modules> mdlcs = this.baseService.find(Modules.class, "Modules", keys, values);
			ft.setModuleclass(mdlcs.get(0).getModuleclass().getMclassname());
			
			ft.setModule(f.getModules().getMname());
			ft.setAction(f.getActionname());
			ft.setGongneng(f.getFunctionname());
			ft.setComments(f.getComments());
			if(ls.contains(f.getActionname())){
				Rolefunction rrr = new Rolefunction();
				RolefunctionId id = new RolefunctionId();
				id.setRoleid(ID);
				id.setActionname(f.getActionname());
				rrr.setId(id);
				rolefunction.add(rrr);
				
				ft.setCk(f.getActionname());
				ft.setShifou("已拥有");
			}
			rolefuns = rolefunction;
			rows.add(ft);
			Comparator comp = new Mycomparator();
			Collections.sort(rows,comp);
		}
		return "root";
	}
	/**
	 * @{ToFq.action}
	 *该方法是负责跳转页面的，当在角色管理页面的时候，点击赋权后就会跳转到赋权页面
	 *  tlq
	*/
	@Action(value="/ToFq",results={@Result(name="success",location="/page/role/Fq.jsp")})
	public String To(){
		this.modulelist = this.baseService.find(Modules.class);
		return "success";
	}
	/**
	 * @{ShouQuan.action}
	 *该方法是在对用户的权限修改之后，点击确定后将角色的权限加以修改
	 *用户的权限会放在finId这个变量中，这是一个字符串，所以传过来后遍历一下使用“，”将其转化为字符数组
	 *  tlq
	*/
	@Action(value="/ShouQuan",results={@Result(name="root",type="json")})
	public String Sq(){
		List<String> roleIDs=new ArrayList<String>();
		String[] s = funId.split(",");
		for(int i=0;i<s.length;i++){
			roleIDs.add(s[i]);
		}
		Roles role = this.baseService.find(Roles.class, ID);
		this.baseService.changeRoleAndFunction(role, roleIDs,rolefuns);
		return "root";
	}
	
	/**
	 * @{Fqqueryfunction.action}
	 * 查询功能
	 * 在对角色赋权时，对功能进行查询
	 * 查到所得结果后将其放入rows中，有分页功能和排序
	*/
	@Action(value="/Fqqueryfunction",results={@Result(name="root",type="json")})
	public String queryFunction(){
		
		List<Rolefunction> rolefunction = new ArrayList<Rolefunction>();
//		String type = request.getParameter("queryType");
		String word = request.getParameter("queryWord");
		List<Functions> lfs = new ArrayList<Functions>();
		
		List<Rolefunction> rfs=this.baseService.findRoleAndFunctionsChecked(ID);
		List<String> ls = new ArrayList<String>();
		for(Rolefunction rf:rfs){
			ls.add(rf.getId().getActionname());
		}
		String[] keys = {"functionname"};
		Object[] values = {word};
		List<Functions> fts = this.baseService.find(Functions.class, "Functions", keys, values);
		for(Functions f:fts){
			String[] keys1 = {"mname"};
			Object[] values1 = {f.getModules().getMname()};
			String mcname = this.baseService.find(Modules.class, "Modules", keys1, values1).get(0).getMname();
			Function fun = new Function();
			fun.setAction(f.getActionname());
			fun.setComments(f.getComments());
			fun.setModule(f.getModules().getMname());
			fun.setModuleclass(mcname);
			fun.setGongneng(f.getFunctionname());
			
			if(ls.contains(f.getActionname())){
				Rolefunction rrr = new Rolefunction();
				RolefunctionId id = new RolefunctionId();
				id.setRoleid(ID);
				id.setActionname(f.getActionname());
				rrr.setId(id);
				rolefunction.add(rrr);
				
				fun.setCk(f.getActionname());
				fun.setShifou("已拥有");
			}
			rolefuns = rolefunction;
			System.out.println(rolefuns.size());
			rows.add(fun);
		}
		total=fts.size();
		return "root";
	}
}
