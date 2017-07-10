/*
 *@(#)xx.tables_zxl.action
 *@ListJszcAction.java.java  
 *@����ʱ��:2011-5-18����12:47:45
 *@���ߣ�zxl
 *@Copyright 2009-2010 �ӱ�����ѧԺ��Ϣ��ѧ�빤��ѧԺ������ 
 */

package xx.tables_zxl.action;

/**
 * @ListJszcAction <code>{������}</code>
 * @author  {zxl}
 * @version {�汾,����ʱ�����}
 * @{��ʦְ����Ϣ����ɾ�Ĳ�} 
 */
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


import xx.collection.bean.CourseChapter;
import xx.collection.bean.Jszc;
import xx.collection.bean.Jszcbh;
import xx.collection.bean.Xlxw;
import xx.quanxian.service.BaseService;

import com.googlecode.jsonplugin.annotations.JSON;
import com.opensymphony.xwork2.ActionSupport;
@Controller
@Scope("prototype")
@ParentPackage("default-package")
@Namespace("")
public class ListJszcAction extends ActionSupport {
	
	@Resource(name="baseService")
	private BaseService baseservice;
	
	
	private Jszc jszc;//����ʵ��  	
	private List<Jszc> rows = new ArrayList<Jszc>();//���󼯺�    
	private int page;//��ǰҳ
	private int rows_s;//ÿһҳ��ʾ������
	private String zcmc;
	private String zcjb;
	private int zcbh;
	private List<Jszc> jszclist;
	private String queryType;	//��ѯ����ʱ�Ĳ�ѯ��������Ҫ���ݲ�ѯ������
	private String queryWord;	//��ѯ����ʱ�Ĳ�ѯ��������Ҫ���ݲ�ѯ�Ĺؼ���
	private List<String> zcjy = new ArrayList<String>();
	private List<String> zc = new ArrayList<String>();
	private int b;
	private int total1=0;
	private int a;
	
	@JSON(serialize=false)
	public List<String> getZc() {
		return zc;
	}
	public void setZc(List<String> zc) {
		this.zc = zc;
	}
	public int getA() {
		return a;
	}
	public void setA(int a) {
		this.a = a;
	}

	@JSON(serialize=false)
	public List<String> getZcjy() {
		return zcjy;
	}
	public void setZcjy(List<String> zcjy) {
		this.zcjy = zcjy;
	}
	public int getB() {
		return b;
	}
	public void setB(int b) {
		this.b = b;
	}
	@JSON(serialize=false)
	public List<Jszc> getJszclist() {
		return jszclist;
	}
	public void setJszclist(List<Jszc> jszclist) {
		this.jszclist = jszclist;
	}
	@JSON(serialize=false)
	public String getZcmc() {
		return zcmc;
	}
	
	public void setZcmc(String zcmc) {
		this.zcmc = zcmc;
	}
	
	@JSON(serialize=false)
	public String getZcjb() {
		return zcjb;
	}
	
	public void setZcjb(String zcjb) {
		this.zcjb = zcjb;
	}
	
	@JSON(serialize=false)
	public int getZcbh() {
		return zcbh;
	}
	@JSON(deserialize=true)
	public void setZcbh(int zcbh) {
		this.zcbh = zcbh;
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
	@JSON(serialize=false)
	public Jszc getJszc() {
		return jszc;
	}
	public void setJszc(Jszc jszc) {
		this.jszc = jszc;
	}
	
	@JSON(serialize=false)
	public BaseService getBaseservice() {
		return baseservice;
	}
	public void setBaseservice(BaseService baseservice) {
		this.baseservice = baseservice;
	}

    public List<Jszc> getRows() {
		return rows;
	}
	public void setRows(List<Jszc> rows) {
		this.rows = rows;
	}

	//��¼����  
    private int total=0;  
 
    public int getTotal() {  
        return total;  
    }  
    public void setTotal(int total) {  
        this.total = total;  
    }  
    @JSON(serialize=false)
	public String getQueryType() {
		return queryType;
	}
	public void setQueryType(String queryType) {
		this.queryType = queryType;
	}
	@JSON(serialize=false)
	public String getQueryWord() {
		return queryWord;
	}
	public void setQueryWord(String queryWord) {
		this.queryWord = queryWord;
	}
	/**
	 * @{insert.action}
	 * @{��ӹ���}
	 * @return {/tables_zxl/datagrid_jszc.jsp} {��ʾ���н�ʦְ����Ϣҳ��}
	 * @exception {˵����ĳ�����,������ʲô�쳣}
	*/
	@Action(value="insert",results={@Result(name="success",type="json")})
	public String insert(){
		Jszc jszc= new Jszc();
		//jszc.setZcbh(zcbh);
		jszc.setZcjb(zcjb);
		jszc.setZcmc(zcmc);
		this.baseservice.save(jszc);//�������
		return SUCCESS;
	}
	
	/**
	 * @{jszclist.action}
	 * @param {jszclist} {��ʾ���н�ʦְ����Ϣ}
	 * @return {/tables_zxl/datagrid_jszc.jsp} {��ʾ���н�ʦְ����Ϣҳ��}
	 * @{��ʾ���н�ʦְ����Ϣ}
	 * @exception {˵����ĳ�����,������ʲô�쳣}
	*/
	@Action(value="/jszclist",results={@Result(name="root",type="json")})
	public String jszclist(){		
        rows = this.baseservice.findAll(Jszc.class,"Jszc", page, rows_s); //���������ݷ���rows������ʵ�ַ�ҳ��ѯ    
        total=this.baseservice.getTotal("Jszc");  //��¼�����ļ�¼  
        return "root";  
	}
	/**
	 * @{delete.action}
	 * @param {jszclist.action} {��ʾ��ʦְ����Ϣ}
	 * @return {jszclist.action} {��ʾ���н�ʦְ����Ϣ} 
	 * @{ɾ����ʦְ����Ϣ}
	 * @exception {˵����ĳ�����,������ʲô�쳣}
	*/
	@Action(value="delete",results={@Result(name="success",type="json")})
	public String delete(){
		String hql = "from Jszcbh";
		List<Integer> zcbh1=new ArrayList<Integer>();
		List<Jszcbh> hh=this.baseservice.findHql(Jszcbh.class, hql);
		if(hh.size()>0){
			for(int i=0;i<hh.size();i++){
				zcbh1.add(hh.get(i).getId().getZcbh());
			}
			if(!zcbh1.contains(zcbh)){
				jszc=this.baseservice.find(Jszc.class,zcbh);//ͨ��zcbhɾ��һ�����ݣ�zcbhҪ���з����л���
				this.baseservice.delete(jszc);// ɾ������
				a=1;
			}else{
				a=0;
			}
		}else{
			jszc=this.baseservice.find(Jszc.class,zcbh);//ͨ��zcbhɾ��һ�����ݣ�zcbhҪ���з����л���
			this.baseservice.delete(jszc);// ɾ������
			a=1;
		}
		
		
		return SUCCESS;
	}

	/**
	 * @{update}
	 * @param {jszclist.action} {��ʾ��ʦְ����Ϣ}
	 * @return {jszclist.action} {��ʾ���н�ʦְ����Ϣ}
	 * @{�޸Ľ�ʦְ����Ϣ}
	 * @exception {˵����ĳ�����,������ʲô�쳣}
	 */
	@Action(value="update",results={@Result(name="success",type="json")})
	public String update(){
		Jszc jszc= new Jszc();
		jszc.setZcbh(zcbh);
		jszc.setZcjb(zcjb);
		jszc.setZcmc(zcmc);
		this.baseservice.update(jszc);	//��������
		return SUCCESS;		
	}	

	//�����ѯ�Ƿ�Ϊ��,���ó���������
	@Action(value="/queryzc1",results={@Result(name="root",type="json")})
	public String queryzc1(){
		String hql = "select count(*) from Jszc where "+queryType+" like '%"+queryWord+"%'";
		total1 = this.baseservice.getTotalSql(hql);
		HttpSession hs = ServletActionContext.getRequest().getSession();
		hs.setAttribute("jszctotal", total1);
		if(total1!=0){
			a=1;
		}else{
			a=0;
		}
		return "root";
	}
	/**
	 * @{queryzc.action}
	 * �÷������������ݹؼ��ֲ�ѯְ����Ϣ�ģ���ѯʱ���ҳ���������������queryType��queryWord
	 * queryType��Ҫ��ѯʱ�����ݵ����ͣ�queryWord�ǹؼ���
	 * ��ѯ���Ľ��ҳҪ�Է�ҳ����ʽ��ʾ
	*/
	@Action(value="/queryzc",results={@Result(name="root",type="json")})
	public String queryzc(){
		rows = this.baseservice.findByTypage(Jszc.class, "Jszc", queryType, queryWord, "order by zcbh asc", page, rows_s);//���ؼ��ֲ�ѯ
		HttpSession hs = ServletActionContext.getRequest().getSession();
		total=(Integer)hs.getAttribute("jszctotal");
		return "root";
	}
	
	//ҳ��У��
	@Action(value="/zcjy",results={@Result(name="success",type="json")})
	public String  zcjy(){
		zcjy = this.baseservice.find(String.class, "Jszc", "zcmc");
		if(zcjy.contains(zcmc)){
		b = 0;
		}else{b=1;}
		return SUCCESS;
	}
	
	
	//ҳ��У��
	@Action(value="/zcjy1",results={@Result(name="success",type="json")})
	public String  zcjy1(){
		zcjy = this.baseservice.find(String.class, "Jszc", "zcmc");
		jszc=this.baseservice.find(Jszc.class, zcbh);
		for(int i=0;i<zcjy.size();i++){
			if(!zcjy.get(i).equals(jszc.getZcmc())){
				zc.add(zcjy.get(i));
			}
		}
		if(!zc.contains(zcmc)){
			b=0;
		}else if(!zcjy.contains(zcmc)){
			b=0;
		}else{
			b=1;
		}
		return SUCCESS;
	}
	/**
	 * @{jszc}
	 * @return {/tables_zxl/datagrid_jszc.jsp} {��ʾ��ʦְ����Ϣ}
	 * @{��ѧ  ��ʦְ�ƹ���}
	 * ��main.jsp�е����ʦְ�ƹ���ʱ��ת��datagrid_jszc.jsp
//	 */	
//	@Action(value="/jszc",results={@Result(name="success",location="/tables_zxl/datagrid_jszc.jsp")})
//	public String jszc(){
//		//this.jszclist=this.baseservice.find(Jszc.class);//ͨ��Jszc_simple.class�鵽���н�ʦְ����Ϣ
//		return SUCCESS;
//	}

}
