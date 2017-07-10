
	/**
	 * 文件名：RoleAction.java
	 *
	 * 版本信息：
	 * 日期：2011-4-4
	 * 作者：tlq
	 * Copyright 河北北方学院信息科学与工程学院科研所 Corporation 2011 
	 * 版权所有
	 * 该类是处理所有关于角色的一些操作；
	 */
	
package xx.roleAndfunction.action;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.InterceptorRefs;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import xx.collection.bean.Functions;
import xx.collection.bean.Modules;
import xx.collection.bean.Rolefunction;
import xx.collection.bean.Roles;
import xx.quanxian.service.BaseService;

import com.googlecode.jsonplugin.annotations.JSON;
import com.opensymphony.xwork2.ActionSupport;

/**
 * 此类描述的是：该类对角色进行增，删，对角色权限的增，删，改
 * @author: tlq
 * @version: 2011-4-4 上午10:21:37 
 */
@Controller
@Scope("prototype")
@Namespace("")
@ParentPackage("default-package")
public class RoleAction extends ActionSupport{
	
	@Resource(name="baseService")
	private BaseService baseService;   //将service注入
	private Roles role;						//用来接收前台传来的值
	private Rolefunction rf;				//接收对角色赋权时，角色的权利
	List<Roles> rows = new ArrayList<Roles>();			//将所有的role放入rows这个变量中，以在页面中显示；
	private List<String> funIds=new ArrayList<String>();			//接收页面传来的复选框的值
	private List<String> roleIDs=new ArrayList<String>();
	List<Functions> functions = new ArrayList<Functions>();
	List<Modules> mds = new ArrayList<Modules>();					//用来接收所有的module
	public int roleid;
	private static final long serialVersionUID = 1L; 
	private static int ID;
	private int page;								//分页时，当前的页数，从前台接收
	private int rows_s;								//分页时，当前的页面的行数，从前台接收
	private int total;								//查询出记录的总条数
	private String queryType;						//查询功能时的查询参数，所要根据查询的类型
	private String queryWord;						//查询功能时的查询参数，所要根据查询的关键字
	private String rolename;	
	private String miaoshu;
	private String tip;								//使用ajax异步提交时，用于标记返回的值
	
	
	public String getTip() {
		return tip;
	}
	
	public void setTip(String tip) {
		this.tip = tip;
	}
	@JSON(serialize=false)
	public String getRolename() {
		return rolename;
	}
	@JSON(deserialize=true)
	public void setRolename(String rolename) {
		this.rolename = rolename;
	}
	public String getMiaoshu() {
		return miaoshu;
	}
	@JSON(deserialize=true)
	public void setMiaoshu(String miaoshu) {
		this.miaoshu = miaoshu;
	}
	@JSON(serialize=false)
	public String getQueryWord() {
		return queryWord;
	}
	@JSON(deserialize=true)
	public void setQueryWord(String queryWord) {
		this.queryWord = queryWord;
	}
	@JSON(serialize=false)
	public String getQueryType() {
		return queryType;
	}
	@JSON(deserialize=true)
	public void setQueryType(String queryType) {
		this.queryType = queryType;
	}
	public int getTotal() {
		return total;
	}
	@JSON(deserialize=true)
	public void setTotal(int total) {
		this.total = total;
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

	@JSON(serialize=false)
	public BaseService getBaseService() {
		return baseService;
	}

	public int getRoleid() {
		return roleid;
	}
	@JSON(deserialize=true)
	public void setRoleid(int roleid) {
		this.roleid = roleid;
	}
	public void setBaseService(BaseService baseService) {
		this.baseService = baseService;
	}
	  
	@JSON(serialize=false)
	public List<Modules> getMds() {
		return mds;
	}

	public void setMds(List<Modules> mds) {
		this.mds = mds;
	}
	@JSON(serialize=false)
	public List<Functions> getFunctions() {
		return functions;
	}

	public void setFunctions(List<Functions> functions) {
		this.functions = functions;
	}

	@JSON(serialize=false)
	public List<String> getFunIds() {
		return funIds;
	}

	public void setFunIds(List<String> funIds) {
		this.funIds = funIds;
	}
	@JSON(serialize=false)
	public List<String> getRoleIDs() {
		return roleIDs;
	}

	public void setRoleIDs(List<String> roleIDs) {
		this.roleIDs = roleIDs;
	}
	@JSON(serialize=false)
	public List<Functions> getFunction() {
		return functions;
	}

	public void setFunction(List<Functions> function) {
		this.functions = function;
	}

	public List<Roles> getRows() {
		return rows;
	}

	public void setRows(List<Roles> rows) {
		this.rows = rows;
	}

	@JSON(serialize=false)
	public Roles getRole() {
		return role;
	}

	public void setRole(Roles role) {
		this.role = role;
	}
	@JSON(serialize=false)
	public Rolefunction getRf() {
		return rf;
	}

	public void setRf(Rolefunction rf) {
		this.rf = rf;
	}

	
	/**
	 * @{creatRole.action}
	 * @param {/role} 
	 * @{创建角色}
	 * @exception {说明在某情况下,将发生什么异常}
	 * 该方法中有一个roleid，此变量时返回页面的，使页面在添加完角色后不刷新，直接在表格最后加一行数据
	 * 角色的roleid是自增的
	*/
	@Action(value="/creatRole",results={@Result(name="root",type="json"),
											@Result(name="input",location="/page/role/listrole.jsp")})
	public String addRoles() throws Exception {
		
		Roles role = new Roles();
		role.setMiaoshu(miaoshu);
		role.setRolename(rolename);
		this.baseService.save(role);
		List<Roles> list = this.baseService.find(Roles.class);		
		int last = list.size();
		this.roleid = list.get(last-1).getRoleid();
		return "root";
	}
	
	/**
	 * @{beforeCreat.action}
	 * 该方法是提交创建角色，对角色进行验证，验证将要提交的角色名称是否已存在
	 * 前台是ajax方式接收，若该角色名称已经在数据库中存在，则将该名称以json形式返回
	 * @exception {说明在某情况下,将发生什么异常}
	*/
	@Action(value="/beforeCreat",results={@Result(name="root",type="json")})
	public String beforeInsert(){
		List<Roles> rs = new ArrayList<Roles>();
		rs = this.baseService.find(Roles.class);
			for(Roles r:rs){
				if(rolename.equals(r.getRolename())){
					tip = r.getRolename();
					break;
				}
			}
		return "root";
	}	
	
	/**
	 * @{json.action}
	 * 以json形式查询出所有的角色，查询时要分页
	 * page，rows_s是分页的信息
	 * page是当前页数
	 * rows_s是每页的行数
	 * total是总的记录数
	*/
	@Action(value="/json",results={@Result(name="root",type="json")})
	public String listRoles() throws Exception {
		
		rows = this.baseService.findAll(Roles.class, "Roles", page, rows_s);
		total = this.baseService.getTotal("Roles");
		return "root";
	}
	/**
	 * @{deleteRole.action}
	 * 该方法是用来删除角色的，删除时会从页面接收一个参数，roleid
	 * 删除角色时，会先将角色所拥有的权利删除
	*/
	@Action(value="/deleteRole",results={@Result(name="success",type="json")})
	public String deleteRole() throws Exception {
		tip="";
		role = this.baseService.find(Roles.class, roleid);
		List<Rolefunction> rfs = this.baseService.findRoleAndFunctionsChecked(roleid);
		if(rfs.size()!=0){
			tip="删除 '"+role.getRolename()+"' 必须先取消该角色的所有权限";
		}else{
			tip="true";
			this.baseService.delete(role);
		}
			
		return SUCCESS;
	}
	/**
	 * @{queryroles.action}
	 * 该方法是用来查询角色的，查询时会从页面接收两个参数，queryType，queryWord
	 * queryType是要查询时所根据的类型，queryWord是关键字
	 * 查询出的结果页要以分页的形式显示
	*/
	@Action(value="/queryroles",results={@Result(name="root",type="json")})
	public String queryRole(){
		String[] keys = {queryType};
		if(queryType.equals("roleid")){
			int ss = Integer.parseInt(queryWord);
			Object[] values = {ss};
			rows = this.baseService.find(Roles.class, "Roles", keys, values);
			total = rows.size();
		}else{
			Object[] values = {queryWord};
			rows = this.baseService.find(Roles.class, "Roles", keys, values);
			total = rows.size();
		}
		return "root";
	}
	/**
	 * @{updateRole.action}
	 * 该方法是用来更新角色的
	 * 页面以ajax方式将数据提交过来，该方法接收后将其放进role中，然后update
	 * 角色roleid是自增的，所以不能改
	*/
	@Action(value="/updateRole",results={@Result(name="root",type="json"),
											@Result(name="input",location="/page/role/updatePRole.jsp")})
	public String updateRole(){
		
		this.role = this.baseService.find(Roles.class, roleid);
		role.setRolename(rolename);
		role.setMiaoshu(miaoshu);
		this.baseService.update(role);
		return "root";
	}
	
	/*
	 * 列出所有function，在fuquanRole和updateRoleToFunction会调用
	 */
	
	@Action(value="/listFunctions",results={@Result(name="success",location="/main.jsp")})
	public String listFunctions(){
		mds = this.baseService.find(Modules.class);
		functions = this.baseService.find(Functions.class);
		return SUCCESS;
	}
	/**
	 * @{SHOW.action}
	 * 该方法是用来查看用户详细信息的
	 * 页面以ajax方式将角色id提交过来，然后查询出来
	 * 此方法只是将角色描述返回，角色id和角色名称从页面中直接取
	*/
	@Action(value="/Show",results={@Result(name="root",type="json")})
	public String show(){
		this.miaoshu = this.baseService.find(Roles.class, roleid).getMiaoshu();
		return "root";
	}
} 
