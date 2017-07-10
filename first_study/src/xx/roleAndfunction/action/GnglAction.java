
	/**
	 * 文件名：FunctionAction.java
	 *
	 * 版本信息：
	 * 日期：2011-5-11
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

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.InterceptorRef;
import org.apache.struts2.convention.annotation.InterceptorRefs;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.springframework.stereotype.Controller;

import xx.collection.bean.Functions;
import xx.collection.bean.Modules;
import xx.collection.bean.Rolefunction;
import xx.collection.bean.RolefunctionId;
import xx.quanxian.service.BaseService;
import com.googlecode.jsonplugin.annotations.JSON;
import com.opensymphony.xwork2.ActionSupport;

	/**
 * 此类描述的是：
 * @author: tlq
 * @version: 2011-5-11 上午10:26:09 
 */
public class GnglAction extends ActionSupport implements ServletRequestAware{
	
	/**
	 * serialVersionUID:TODO（用一句话描述这个变量表示什么）
	 *
	 * @since Ver 1.1
	 */
			
	private static final long serialVersionUID = 1L;
	@Resource(name="baseService")
	private BaseService baseService;			//将service注入到actioin层
	private List<Function> rows = new ArrayList<Function>();				
	private  HttpServletRequest request;       //查询时用来接收页面传来的参数
	
	private Functions function;
	private String actionname;
	private int total;
	private int page;
	private int rows_s;
	private String module;
	private String functionname;
	private String comments;
	private String tip;
	private List<String> MCName = new ArrayList<String>();
	private List<String> MName = new ArrayList<String>();
	private String mname;
	private List<String> functions = new ArrayList<String>();
	
	public List<String> getFunctions() {
		return functions;
	}
	public void setFunctions(List<String> functions) {
		this.functions = functions;
	}
	public String getMname() {
		return mname;
	}
	@JSON(deserialize=true)
	public void setMname(String mname) {
		this.mname = mname;
	}
	public List<String> getMCName() {
		return MCName;
	}
	public void setMCName(List<String> name) {
		MCName = name;
	}
	public List<String> getMName() {
		return MName;
	}
	public void setMName(List<String> name) {
		MName = name;
	}
	public String getTip() {
		return tip;
	}
	
	public void setTip(String tip) {
		this.tip = tip;
	}
	@JSON(serialize=false)
	public String getComments() {
		return comments;
	}
	@JSON(deserialize=true)
	public void setComments(String comments) {
		this.comments = comments;
	}
	@JSON(serialize=false)
	public String getModule() {
		return module;
	}
	@JSON(deserialize=true)
	public void setModule(String module) {
		this.module = module;
	}
	@JSON(serialize=false)
	public String getFunctionname() {
		return functionname;
	}
	@JSON(deserialize=true)
	public void setFunctionname(String functionname) {
		this.functionname = functionname;
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

	public void setServletRequest(HttpServletRequest request) {
		  this.request = request;
	}
	
	@JSON(serialize=false)
	public String getActionname() {
		return actionname;
	}
	@JSON(deserialize=true)
	public void setActionname(String actionname) {
		this.actionname = actionname;
	}

	@JSON(serialize=false)
	public Functions getFunction() {
		return function;
	}
	public void setFunction(Functions function) {
		this.function = function;
	}

	
	@JSON(serialize=false)
	public BaseService getBaseService() {
		return baseService;
	}
	public void setBaseService(BaseService baseService) {
		this.baseService = baseService;
	}
	
	public List<Function> getRows() {
		return rows;
	}
	public void setRows(List<Function> rows) {
		this.rows = rows;
	}
	
	
	/**
	 * @{listfunc.action}
	 *该方法是当在main.jsp页面中点击功能管理时，跳转到gngl.jsp
	 *在跳转的时候，将所有的module查询出来，在添加功能的时候，以下拉框的形式将其显示出来
	 *  tlq
	*/
	@Action(value="/queryFun",results={@Result(name="success",type="json")})
	public String queryFunc(){
		String[] keys = {"mname"};
		Object[] values = {mname};
		this.functions = this.baseService.find(String.class, "Functions", "functionname", keys, values);
		return SUCCESS;
	}
	/**
	 * @{jsonFunc.action}
	 *将所有的funciton查询出来，放在rows中，并且根据页面传过来的当前页数和每页条数分页
	 * total是所有记录的条数
	*/
	@Action(value="/jsonFunc",results={@Result(name="root",type="json")})
	public String jsonFunc(){
		List<Functions> fs = new ArrayList<Functions>(); 
		fs = this.baseService.findAll(Functions.class, "Functions", page, rows_s);
		total = this.baseService.getTotal("Functions");
		
		for(Functions f:fs){
			Function ft = new Function();
			
//			String[] keys = {"mname"};
//			Object[] values = {f.getModules().getMname()};
//			List<Modules> mdlcs = this.baseService.find(Modules.class, "Modules", keys, values);
			
			ft.setModuleclass(f.getModules().getModuleclass().getMclassname());
			ft.setModule(f.getModules().getMname());
			ft.setAction(f.getActionname());
			ft.setGongneng(f.getFunctionname());
			ft.setComments(f.getComments());
			
			rows.add(ft);
			Comparator comp = new Mycomparator();
			Collections.sort(rows,comp);
		}
		return "root";
	}
	
	/**
	 * @{insertFunctions.action}
	 * @param {listFunctions} {显示功能}
	 * 添加功能
	 * 当添加失败后return {/page/role/addORupdateFunctions.jsp} {添加功能页面}
	*/
	@Action(value="/insertFunctions",results={@Result(name="root",type="json"),@Result(name="input",location="/page/role/listrole.jsp")})
	public String insertFunctions() throws Exception {
		Functions f = new Functions();
		f.setActionname(actionname);
		f.setComments(comments);
		f.setFunctionname(functionname);
		Modules m = new Modules();
		m.setMname(module);
		f.setModules(m);
		this.baseService.save(f);
		Rolefunction rf = new Rolefunction();
		RolefunctionId rfid = new RolefunctionId();
		rfid.setRoleid(106);
		rfid.setActionname(actionname);
		rf.setId(rfid);
		this.baseService.save(rf);
		return "root";
	}
	/**
	 * @{beforeInsert.action}
	 * 该方法是在提交新功能前或修改功能前对其验证，看功能是否已存在
	 * 如果存在的话会将功能名称放入到tip中返回到前台
	 * 不存在的话tip为null
	*/
	@Action(value="/beforeInsert",results={@Result(name="root",type="json")})
	public String beforeInsert(){
		List<Functions> fs = new ArrayList<Functions>();
		fs = this.baseService.find(Functions.class);
		if(functionname!=null){
			for(Functions f:fs){
				if(functionname.equals(f.getFunctionname())){
					tip = f.getFunctionname();
					break;
				}
			}
		}else{
			for(Functions f:fs){
				if(actionname.equals(f.getActionname())){
					tip = f.getActionname();
					break;
				}
			}
		}
		return "root";
	}
	
	/**
	 * @{deleteFunctions.action}
	 * 删除功能
	 * 前台会传过来要删除的function的actionname，根据actionname将功能删除
	 * 删除功能时，会先将rolefunction删除
	*/
	@Action(value="/deleteFunctions",results={@Result(name="success",type="json")})
	public String deleteFunctions(){
		tip="";
		Functions ft = this.baseService.find(Functions.class, actionname);
		String[] keys = {"actionname"};
		Object[] values = {actionname};
		List<Rolefunction> rfs = this.baseService.find(Rolefunction.class, "Rolefunction", keys, values);
		if(rfs.size()!=0){
			for(Rolefunction rf:rfs){
			  tip+="'"+rf.getRoles().getRolename()+"'角色  ";
		    }
			
		}else{
			tip="true";
			this.baseService.delete(ft);
		}

		
		return SUCCESS;
	}
	/**
	 * @{queryfunction.action}
	 * 查询功能
	 * 前台会传过来要查询所根据的类型和关键字
	 * 查到所得结果后将其放入rows中，有分页功能和排序
	*/
	@Action(value="/queryfunction",results={@Result(name="root",type="json")})
	public String queryFunction(){
		String word = request.getParameter("queryWord");
		String[] keys1 = {"functionname"};
		Object[] values1 = {word};
		Functions f = this.baseService.find(Functions.class, "Functions", keys1, values1).get(0);
		Function fc = new Function();
		fc.setAction(f.getActionname());
		fc.setComments(comments);
		fc.setGongneng(f.getFunctionname());
		fc.setModule(f.getModules().getMname());
		
		String[] keys = {"mname"};
		Object[] values = {f.getModules().getMname()};
		List<Modules> mdlcs = this.baseService.find(Modules.class, "Modules", keys, values);
		fc.setModuleclass(mdlcs.get(0).getModuleclass().getMclassname());
		
		rows.add(fc);
		return "root";
	}
	/**
	 * @{updataFunction.action}
	 * 更新功能
	 * 前台会传过来要更新的function，首先根据前台传来的actionname查出所对应的function
	 * 然后将页面修改后的function的各项属性赋给该function
	 *  tlq
	*/
	@Action(value="/updateFunction",results={@Result(name="root",type="json")})
	public String updateFunction(){
		Functions ft = this.baseService.find(Functions.class, actionname);
		ft.setComments(comments);
		ft.setFunctionname(functionname);
		Modules m = new Modules();
		m.setMname(module);
		ft.setModules(m);
		this.baseService.update(ft);
		return "root";
	}
	/**
	 * @{bindMclassname.action}
	 *  tlq
	 *  绑定模块分类名称，在页面添加或修改功能时会根据所选的模块分类将所对应的模块放入第二层下拉框中
	*/
	@Action(value="/bindMclassname",results={@Result(name="root",type="json")})
	public String bindMclassname(){
		this.MCName=this.baseService.find(String.class, "Moduleclass", "mclassname");
		System.out.println("aaaaa");
		return "root";
	}
	/**
	 * @{bindMName.action}
	 *  tlq
	 *  绑定模块名称，在页面添加或修改功能时会根据所选的模块分类将所对应的模块放入第二层下拉框中
	*/
	@Action(value="/bindMName",results={@Result(name="root",type="json")})
	public String bindMname(){
		String[] keys = {"mclassname"};
		Object[] values = {mname};
		this.MName=this.baseService.find(String.class, "Modules", "mname", keys, values);
		return "root";
	}
}
