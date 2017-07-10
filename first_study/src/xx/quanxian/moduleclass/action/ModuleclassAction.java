/*
 *@(#)xx.quanxian.moduleclass.action
 *@ModuleclassAction.java.java  
 *@����ʱ��:2011-4-5����12:49:55
 *@���ߣ�xupengfei
 *@Copyright 2009-2010 �ӱ�����ѧԺ��Ϣ��ѧ�빤��ѧԺ������ 
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
import xx.quanxian.service.BaseService;

import com.googlecode.jsonplugin.annotations.JSON;
import com.opensymphony.xwork2.ActionSupport;

/**
 * @ModuleclassAction <code>{ModuleclassAction}</code>
 * @author  {������}
 * @version {2011-5-22}
 * @{ģ�����} 
 */

@Controller
@Scope("prototype")
@Namespace("")
@ParentPackage("default-package")
public class ModuleclassAction extends ActionSupport {
	
	private Moduleclass moduleclass;//����һ��moduleclass����
	@Resource(name="baseService")
	private BaseService service;
	private List<Modules> all = new ArrayList<Modules>();
	private List<Moduleclass> rows = new ArrayList<Moduleclass>();
	//У�����
	private List<String> mkjy=new ArrayList<String>();
	private List<String> mname1=new ArrayList<String>();
	private int b;
	private int total;//��������total
	//datagrid����
	private int page;//��ǰҳ��
	private int rows_s;//ÿҳ��ʾrow_s������
	private String tip;
	
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
	/*
	 * ɾ��������
	 * ������Ҫ�Ĳ���
	 */
	private String mclassname;
	//����Ϊʵ�ֵ�get.set����
//	@JSON(serialize=false)
	public Moduleclass getModuleclass() {
		return moduleclass;
	}
	@JSON(deserialize=true)
	public void setModuleclass(Moduleclass moduleclass) {
		this.moduleclass = moduleclass;
	}
	@JSON(serialize=false)
	public BaseService getService() {
		return service;
	}
	public void setService(BaseService service) {
		this.service = service;
	}
	
	public List<Moduleclass> getRows() {
		return rows;
	}
	public void setRows(List<Moduleclass> rows) {
		this.rows = rows;
	}
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	@JSON(serialize=false)
	public List<String> getMkfenlei() {
		return mkjy;
	}
	public void setMkfenlei(List<String> mkfenlei) {
		this.mkjy = mkfenlei;
	}
	//===============����datagrid����=====================
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
	//==================mclassname��get set����===============================
	public String getMclassname() {
		return mclassname;
	}
	@JSON(deserialize=true)
	public void setMclassname(String mclassname) {
		this.mclassname = mclassname;
	}
	
	
	public List<Modules> getAll() {
		return all;
	}
	public void setAll(List<Modules> all) {
		this.all = all;
	}
	//У�����
	public int getB() {
		return b;
	}
	public void setB(int b) {
		this.b = b;
	}
	
	public List<String> getMname1() {
		return mname1;
	}
	public void setMname1(List<String> mname1) {
		this.mname1 = mname1;
	}
	/**
	 * @{saveModuleclass.action}
	 * @param {moduleclass} {Moduleclass��Ķ���ʵ��}
	 * @return {success} {}
	 * @{ʵ����ӹ���}
	 * @exception {˵����ĳ�����,������ʲô�쳣}
	*/
//	@Action(value="saveModuleclass",results={@Result(name="success",location="/listModuleclass.action",type="redirect"),
//			@Result(name="input",location="/tables/moduleclass/add.jsp")})
	@Action(value="/saveModuleclass",results={@Result(name="success",type="json")})
	public String saveModuleclass(){
		this.service.save(moduleclass);
		return SUCCESS;
	}
	
	/**
	 * @{update}
	 * @param {moduleclass} {Moduleclass��Ķ���ʵ��}
	 * @return {success} {}
	 * @{ʵ���޸Ĺ���}
	 * @exception {˵����ĳ�����,������ʲô�쳣}
	*/
	@Action(value="/updateModuleclass",results={@Result(name="success",type="json")})
	public String updateModuleclass(){
		this.service.update(moduleclass);
		return SUCCESS;
	}
	/**
	 * @{findModuleclass}
	 * @param {mclassname} {��Ӧ���ݱ�����}
	 * @return {moduleclass} {��json��ʽ���ص�Moduleclass��Ķ���ʵ��}
	 * @{ʵ�ֲ�ѯ����}
	 * @exception {˵����ĳ�����,������ʲô�쳣}
	*/
	@Action(value="/findModuleclass",results={@Result(name="success",type="json")})
	public String findModuleclass(){
		moduleclass = this.service.find(Moduleclass.class,mclassname);
		return SUCCESS;
	}
	
	/**
	 * @{removeModuleclass}
	 * @param {mclassname} {��Ӧ���ݱ�����}
	 * @return {success} {}
	 * @{ʵ��ɾ������}
	 * @exception {˵����ĳ�����,������ʲô�쳣}
	*/
	@Action(value="/removeModuleclass",results={@Result(name="success",type="json")})
	public String removeModuleclass(){
		tip="";
		moduleclass = this.service.find(Moduleclass.class,mclassname);
		List<Modules> L = new ArrayList<Modules>();
		L = this.service.findByProperty("from Modules where mclassname =?", mclassname);
		if(L.size()==0){
			this.service.delete(moduleclass);
			tip="true";
		}else{
			 for(Modules m:L){
				 tip+="'"+m.getMname()+"' ";
			 }
		}

		return SUCCESS;
	}
	/**
	 * @{listModuleclass.action}
	 * @param {Moduleclass.class,Moduleclass, page, rows_s} {��ӦfindAll������Ҫ�ĸ��ֲ���}
	 * @return {total,rows} {��������ʾ��ҳ��}
	 * @{�г�ȫ����Ҫ����}
	 * @exception {˵����ĳ�����,������ʲô�쳣}
	*/
	@Action(value="/listModuleclass",results={@Result(name="root",type="json"),@Result(name="input",location="/login.jsp")})
	public String listModuleclass(){
		
		rows = this.service.findAll(Moduleclass.class,"Moduleclass", page, rows_s);	
		total = this.service.getTotal("Moduleclass");
        return "root";  

	}
	
	
	/**
	 * @{listModuleclass.action}
	 * @param {Moduleclass.class,Moduleclass, page, rows_s} {��ӦfindAll������Ҫ�ĸ��ֲ���}
	 * @return {total,rows} {��������ʾ��ҳ��}
	 * @{�г�ȫ����Ҫ����}
	 * @exception {˵����ĳ�����,������ʲô�쳣}
	*/
	@Action(value="/verifyModuleclass",results={@Result(name="success",type="json")})
	public String  verifyModuleclass(){
		mkjy = this.service.find(String.class, "Moduleclass", "mclassname");
		if(mkjy.contains(mclassname)){
		b = 0;
		}else{b=1;}
		return SUCCESS;
	}
	
}

