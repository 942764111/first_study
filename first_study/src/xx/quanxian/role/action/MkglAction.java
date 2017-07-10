/*
 *@(#)xx.quanxian.role.action
 *@MkglAction.java.java  
 *@创建时间:2011-5-3下午10:11:53
 *@作者：guoqiang
 *@Copyright 2009-2010 河北北方学院信息科学与工程学院科研所 
 */

package xx.quanxian.role.action;


import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;



import xx.collection.bean.Functions;
import xx.collection.bean.Modules;
import xx.page.module.Conmen;
import xx.quanxian.service.BaseService;

import com.googlecode.jsonplugin.annotations.JSON;
import com.opensymphony.xwork2.ActionSupport;

/**
 * @ManagerModules <code>{类名称}</code>
 * @author  {gq}
 * @version {版本,常用时间代替}
 * @{模块管理} 
 */

@Controller
@Scope("prototype")
@Namespace("")
@ParentPackage("default-package")
@SuppressWarnings("serial")
public class MkglAction extends ActionSupport {
	@Resource(name="baseService")
		
	private BaseService baseService;
	private String mname;
	private String[] mnames;
	private String mclassname;
	private static String m_name;
	private static String m_classname;
	
	private List<Conmen> rows=new ArrayList<Conmen>();            //通用rows,为转换为标准的datagrid中json数组
	private List<Modules> list=new ArrayList<Modules>(); 
	private List<Conmen> listConmen=new ArrayList<Conmen>();   //通用类
	private Modules module;
	private List<String> mokuai=new ArrayList<String>();
	private int total;
	private String tip;
	private int page;            //
	private int rows_s;
	private List<String> mfunction=new ArrayList<String>();
	private List<String> mfunction1=new ArrayList<String>();
	private String functionname;
	
	private String[] arrays5=new String[10];
	private String[] arrays6=new String[10];
	private List<Functions> function;
    
    

	/**
	 * @return the mnames
	 */
	public String[] getMnames() {
		return mnames;
	}

	/**
	 * @param mnames the mnames to set
	 */
	public void setMnames(String[] mnames) {
		this.mnames = mnames;
	}

	/**
	 * @return the function
	 */
	public List<Functions> getFunction() {
		return function;
	}

	/**
	 * @param function the function to set
	 */
	public void setFunction(List<Functions> function) {
		this.function = function;
	}

	/**
	 * @return the arrays6
	 */
	@JSON(serialize=false)
	public String[] getArrays6() {
		return arrays6;
	}

	/**
	 * @param arrays6 the arrays6 to set
	 */
	public void setArrays6(String[] arrays6) {
		this.arrays6 = arrays6;
	}

	/**
	 * @return the arrays5
	 */
	@JSON(serialize=false)
	public String[] getArrays5() {
		return arrays5;
	}

	/**
	 * @param arrays5 the arrays5 to set
	 */
	public void setArrays5(String[] arrays5) {
		this.arrays5 = arrays5;
	}


	/**
	 * @return the functionname
	 */
	 @JSON(serialize=false)
	public String getFunctionname() {
		return functionname;
	}

	/**
	 * @param functionname the functionname to set
	 */
	public void setFunctionname(String functionname) {
		this.functionname = functionname;
	}

	/**
	 * @return the mfunction
	 */
	 @JSON(serialize=false)
	public List<String> getMfunction() {
		return mfunction;
	}

	/**
	 * @return the mfunction1
	 */
	 @JSON(serialize=false)
	public List<String> getMfunction1() {
		return mfunction1;
	}

	/**
	 * @param mfunction1 the mfunction1 to set
	 */
	public void setMfunction1(List<String> mfunction1) {
		this.mfunction1 = mfunction1;
	}

	/**
	 * @param mfunction the mfunction to set
	 */
	public void setMfunction(List<String> mfunction) {
		this.mfunction = mfunction;
	}

	/**
	 * @return the page
	 */
	public int getPage() {
		return page;
	}

	/**
	 * @param page the page to set
	 */
	public void setPage(int page) {
		this.page = page;
	}

	/**
	 * @return the rows_s
	 */
	public int getRows_s() {
		return rows_s;
	}

	/**
	 * @param rows_s the rows_s to set
	 */
	public void setRows_s(int rows_s) {
		this.rows_s = rows_s;
	}

	/**
	 * @return the tip
	 */
	public String getTip() {
		return tip;
	}

	/**
	 * @param tip the tip to set
	 */
	public void setTip(String tip) {
		this.tip = tip;
	}

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

	/**
	 * @return the mokuai
	 */
	
	public List<String> getMokuai() {
		return mokuai;
	}

	/**
	 * @param mokuai the mokuai to set
	 */
	public void setMokuai(List<String> mokuai) {
		this.mokuai = mokuai;
	}

	/**
	 * @return the module
	 */
	public Modules getModule() {
		return module;
	}

	/**
	 * @param module the module to set
	 */
	public void setModule(Modules module) {
		this.module = module;
	}

	/**
	 * @return the listConmen
	 */
	 @JSON(serialize=false)
	public List<Conmen> getListConmen() {
		return listConmen;
	}

	/**
	 * @param listConmen the listConmen to set
	 */
	public void setListConmen(List<Conmen> listConmen) {
		this.listConmen = listConmen;
	}

	/**
	 * @return the rows
	 */
	public List<Conmen> getRows() {
		return rows;
	}

	/**
	 * @param rows the rows to set
	 */
	public void setRows(List<Conmen> rows) {
		this.rows = rows;
	}

	/**
	 * @return the list
	 */
	@JSON(serialize=false)
	public List<Modules> getList() {
		return list;
	}

	/**
	 * @param list the list to set
	 */
	public void setList(List<Modules> list) {
		this.list = list;
	}

	/**
	 * @return the mname
	 */
	 @JSON(serialize=false)
	public String getMname() {
		return mname;
	}

	/**
	 * @param mname the mname to set
	 */
	public void setMname(String mname) {
		this.mname = mname;
	}
 
	
	/**
	 * @return the mclassname
	 */
	 @JSON(serialize=false)
	public String getMclassname() {
		return mclassname;
	}

	/**
	 * @param mclassname the mclassname to set
	 */
	public void setMclassname(String mclassname) {
		this.mclassname = mclassname;
	}
	
	/**
	 * @{异步为模块管理页面查询模块名}
	 * @param {} {引入参数说明}
	 * @return {SUCCESS} {返回参数说明}
	 * @{}
	 * @exception {说明在某情况下,将发生什么异常}
	**/
	
	@Action(value="/mk",results={@Result(name="success",type="json")})
	public String mk(){
		
		 mokuai=this.baseService.find(String.class, "Modules", "mname");
		 
		return SUCCESS;
	}
	/**
	 * @{根据分类名模块名联动（属于自动填充）}
	 * @param {mclassname} {引入参数说明}
	 * @return {SUCCESS} {返回参数说明}
	 * @{跳转到listModules.jsp}
	 * @exception {说明在某情况下,将发生什么异常}
	**/
	
	@Action(value="/mkNameld",results={@Result(name="success",type="json")})
	public String mkNameld(){
		String[] keys=new String[1];
		keys[0]="mclassname";
		Object[] values=new Object[1];
		values[0]=mclassname;
	     mokuai=this.baseService.find(String.class, "Modules", "mname",keys,values);
		
		return SUCCESS;
	}

	/**
	 * @{查询并列出模块}
	 * @param {} {引入参数说明}
	 * @return {SUCCESS} {返回参数说明}
	 * @{跳转到}
	 * @exception {说明在某情况下,将发生什么异常}
	**/
	
	@SuppressWarnings("unchecked")
	@Action(value="/listModules",results={@Result(name="root",type="json")})
	public String listModules()throws Exception{
		
		HttpSession hs = ServletActionContext.getRequest().getSession();
		if(mclassname==null&&mname==null){
			mclassname=m_classname;
			mname=m_name;
			
		}
		String[] keys;
		Object[] values;
		if(mname==null||mname.equals("")){
			keys=new String[1];
			values=new Object[1];
			keys[0]="mclassname";
			values[0]=mclassname;
			
		}else{
			keys=new String[2];
		    values=new Object[2];
			keys[0]="mclassname";
			keys[1]="mname";
			values[0]=mclassname;
			values[1]=mname;
		}
		m_classname=mclassname;
		m_name=mname;
		
		list=this.baseService.findLike(Modules.class, "Modules", keys, values,"order by molderid asc",page,rows_s);
		for(int i=0;i<list.size();i++){
			
				Conmen element=new Conmen();
				element.setStr1(list.get(i).getModuleclass().getMclassname());
				element.setStr2(list.get(i).getMname());
				element.setInt1(list.get(i).getMolderid());
				element.setStr3(list.get(i).getMimage());
				element.setStr4(list.get(i).getComments());
				listConmen.add(element);
			
		}
		
		
		rows=listConmen;
	   total=this.baseService.getTotalPages("Modules",keys,values);
	  // this.mkgljsp();
	   return "root";
	}

	
	/**
	 * @{添加模块}
	 * @param {} {引入参数说明}
	 * @return {SUCCESS} {返回参数说明}
	 * @{跳转到}
	 * @exception {说明在某情况下,将发生什么异常}
	**/
	
	@Action(value="/addModule",results={@Result(name="success",type="json")})
	public String addModule(){
		this.baseService.save(module);
		tip="true";
		return SUCCESS;
	}

	/**
	 * @{修改模块}
	 * @param {} {引入参数说明}
	 * @return {SUCCESS} {返回参数说明}
	 * @{跳转到}
	 * @exception {说明在某情况下,将发生什么异常}
	**/
	
	@Action(value="/updateModule",results={@Result(name="success",type="json")})
	public String updateModule(){
		this.baseService.update(module);
		tip="true";
		return SUCCESS;
	}
	/**
	 * @{删除模块}
	 * @param {} {引入参数说明}
	 * @return {SUCCESS} {返回参数说明}
	 * @{跳转到}
	 * @exception {说明在某情况下,将发生什么异常}
	**/
	
	@Action(value="/delModule",results={@Result(name="success",type="json")})
	public String delModule(){
		tip="";
		String[] keys=new String[1];
		keys[0]="mname";
		Object[] values=new Object[1];
		for(int i=0;i<mnames.length;i++){
			module=this.baseService.find(Modules.class,mnames[i]);
			values[0]=mnames[i];
			List<Functions> li=this.baseService.find(Functions.class, "Functions", keys, values);
			if(li.size()==0){
				this.baseService.delete(module);
				tip="true";
			}else{
				for(int j=0;j<li.size();j++){
					tip+=" "+li.get(j).getFunctionname();
				}
				
			}
			
		}
		
		
		return SUCCESS;
	}
	/**
	 * @{维护功能--左边的datagrid}
	 * @param {} {引入参数说明}
	 * @return {SUCCESS} {返回参数说明}
	 * @{跳转到}
	 * @exception {说明在某情况下,将发生什么异常}
	**/
	
	@SuppressWarnings("unchecked")
	@Action(value="/mfunction",results={@Result(name="success",type="json")})
	public String mfunction(){
		HttpSession hs = ServletActionContext.getRequest().getSession();
		if(mname==null){
			
			mname=(String) hs.getAttribute("mname1");
		}
		hs.setAttribute("mname1", mname);
		String[] keys=new String[1];
		Object[] values=new Object[1];
		keys[0]="mname";
		values[0]=mname;
		
		mfunction=this.baseService.find(String.class, "Functions", "functionname", keys, values);
		for(int i=0;i<mfunction.size();i++){
			Conmen element=new Conmen();
			element.setStr1(mfunction.get(i));
			rows.add(element);
		}
      
		tip="true";
		return SUCCESS;
	}
	
	/**
	 * @{维护功能中的查询--右边的datagrid}
	 * @param {} {引入参数说明}
	 * @return {SUCCESS} {返回参数说明}
	 * @{跳转到}
	 * @exception {说明在某情况下,将发生什么异常}
	**/
	
	@SuppressWarnings("unchecked")
	@Action(value="/listfunctions",results={@Result(name="success",type="json")})
	public String listfunctions(){
		boolean flag=true;
		HttpSession hs = ServletActionContext.getRequest().getSession();
		if(mname==null&&functionname==null){
			mname=(String) hs.getAttribute("mname");
			functionname=(String) hs.getAttribute("functionname");
			hs.removeAttribute("mname");
			hs.removeAttribute("functionname");
			flag=false;
		}
		if(flag){
			hs.setAttribute("mname",mname);
			hs.setAttribute("functionname",functionname);
		}
		
		String[] keys;
		Object[] values;
		if(functionname==null||functionname.equals("")){
			keys=new String[1];
			values=new Object[1];
			keys[0]="mname";
			values[0]=mname;
			
		}else{
			keys=new String[2];
		    values=new Object[2];
			keys[0]="mname";
			keys[1]="functionname";
			values[0]=mname;
			values[1]=functionname;
		}
		
		mfunction1=this.baseService.find(String.class, "Functions", "functionname", keys, values);
		for(int i=0;i<mfunction1.size();i++){
			Conmen element=new Conmen();
			element.setStr1(mfunction1.get(i));
			rows.add(element);
		}
		tip="true";
		return SUCCESS;
	}
	
	/**
	 * @{维护功能中的添加和删除}
	 * @param {} {引入参数说明}
	 * @return {SUCCESS} {返回参数说明}
	 * @{跳转到}
	 * @exception {说明在某情况下,将发生什么异常}
	**/
	
	@SuppressWarnings("unchecked")
	@Action(value="/weiHuAction",results={@Result(name="success",type="json")})
	public String weiHuAction(){
		tip="";
		HttpSession hs = ServletActionContext.getRequest().getSession();
		mname=(String) hs.getAttribute("mname1");
		hs.removeAttribute("mname1");
		String[] keys=new String[1];
		keys[0]="functionname";
		String[] values=new String[1];
		if(StringUtils.isNotBlank(arrays6[0])){//不是null且不是“”
			for(int i=0;i<arrays6.length;i++){
				values[0]=arrays6[i];
				function=this.baseService.find(Functions.class,"Functions",keys,values);
				function.get(0).getModules().setMname(mname);  //gai
				this.baseService.update(function.get(0));
			}
			tip="add";
		}
		if(StringUtils.isNotBlank(arrays5[0])){//不是null且不是“”
			for(int i=0;i<arrays5.length;i++){
				values[0]=arrays5[i];
				function=this.baseService.find(Functions.class,"Functions",keys,values);
			Modules m=null;
			function.get(0).setModules(m);
				
				
				this.baseService.update(function.get(0));
				
			}
			tip=tip+"del";
		}
		
		return SUCCESS;
	}

}
