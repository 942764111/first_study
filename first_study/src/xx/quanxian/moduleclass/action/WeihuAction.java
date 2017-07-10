/*
 *@(#)xx.quanxian.moduleclass.action
 *@WeihuAction.java.java  
 *@创建时间:2011-8-23上午11:55:34
 *@作者：xupengfei
 *@Copyright 2009-2010 河北北方学院信息科学与工程学院科研所 
 */

package xx.quanxian.moduleclass.action;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import xx.collection.bean.Moduleclass;
import xx.collection.bean.Modules;
import xx.collection.bean.Roles;
import xx.quanxian.service.BaseService;

import com.googlecode.jsonplugin.annotations.JSON;
import com.opensymphony.xwork2.ActionSupport;

/**
 * @WeihuAction <code>{类名称}</code>
 * @author  {作者}
 * @version {版本,常用时间代替}
 * @{功能描述:} 
 */
@Controller
@Scope("prototype")
@Namespace("")
@ParentPackage("default-package")
public class WeihuAction extends ActionSupport {
	
	@Resource(name="baseService")
	private BaseService service;
	private List<Module> rows = new ArrayList<Module>();
	private static List<Modules> LM = new ArrayList<Modules>();
	private int total;
	private int page;
	private int rows_s;
	private static String Mclassname;
	private String mclassname;
	private String mnames;
	@JSON(serialize=false)
	public BaseService getService() {
		return service;
	}
	public void setService(BaseService service) {
		this.service = service;
	}
	public List<Module> getRows() {
		return rows;
	}
	public void setRows(List<Module> rows) {
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
	public String getMclassname() {
		return mclassname;
	}
	public void setMclassname(String mclassname) {
		this.mclassname = mclassname;
	}
	@JSON(serialize=false)
	public String getMnames() {
		return mnames;
	}
	public void setMnames(String mnames) {
		this.mnames = mnames;
	}
	@Action(value="/weihulist_tz",results={@Result(name="success",location="/page/moduleclass/weih.jsp")})
	public String weihulist_tz() throws Exception{
			return SUCCESS;
		}
	@Action(value="/weihuchuanzhi",results={@Result(name="success",type="json")})
	public String weihuchuanzhi() throws Exception{
		Mclassname = mclassname;
			return SUCCESS;
		}
	@Action(value="/weihulist",results={@Result(name="success",type="json")})
	public String weihulist() throws Exception{
		List<Modules> list = new ArrayList<Modules>();
		List<Modules> list_mm = new ArrayList<Modules>();
		List<Modules> list_all = new ArrayList<Modules>();
		list = this.service.findByProperty("from Modules where mclassname=?", Mclassname);
		list_all = this.service.findAll(Modules.class,"Modules", page,rows_s);
		total = this.service.getTotal("Modules");
		List<String> ll = new ArrayList<String>();
		for(Modules m:list){
		ll.add(m.getMname());
		}
		for(Modules M:list_all){
			Module module = new Module();
			module.setMname(M.getMname());
			module.setMolderid(M.getMolderid());
			if(ll.contains(M.getMname())){
				
				list_mm.add(M);
			module.setShifou("Y");
			}else{module.setShifou("N");
				}
			rows.add(module);
		}
		LM = list_mm;
		return SUCCESS;
		}
	@Action(value="/weihutijiao",results={@Result(name="success",type="json")})
	public String weihutijiao(){
		List<String> mname1=new ArrayList<String>();
		String[] s = mnames.split(",");
		for(int i=0;i<s.length;i++){
			mname1.add(s[i]);
		}
		Modules modules = new Modules();
		Moduleclass moduleclass = new Moduleclass();
		List<Modules> all = new ArrayList<Modules>();
//		all = this.service.findByProperty("from Modules where mclassname =?",Mclassname);
		all = LM;
		if(all.isEmpty()){
			if(mname1.isEmpty()==false){
				
				for(String m:mname1){
					modules.setMname(m);
					moduleclass.setMclassname(Mclassname);
					modules.setModuleclass(moduleclass);
					this.service.update(modules);
					}
			}else{}
		}else{
			if(mname1.isEmpty()){
				for(Modules a:all){
					this.service.delete(a);
				}
			}else{
				for(String m:mname1){
					modules = this.service.find(Modules.class,m);
					if(!all.contains(modules)){
						moduleclass.setMclassname(Mclassname);
						modules.setModuleclass(moduleclass);
						this.service.update(modules);
					}else{}
				}
				for(Modules a:all){
					if(mname1.contains(a.getMname())==false){
						a.setModuleclass(null);
//						a.setMclassname(mclassname);
						this.service.update(a);
					}else{}
				}
					
			}
		}
		return SUCCESS;
	}
}
