/*
 * @(#)xx.tables_zxl.action.{file_name} 
 * @SchoollistAction.java.java 
 * @ ����ʱ�䣺2011-4-18     
 * @ ���ߣ�zxl
 * @ Copyright 2009-2010 �ӱ�����ѧԺ��Ϣ��ѧ�빤��ѧԺ������ 
 */

package xx.tables_zxl.action;



/**
 * @ListJszcAction <code>{������}</code>
 * @author  {zxl}
 * @version {�汾,����ʱ�����}
 * @{ѧУ��Ϣ����ɾ�Ĳ�} 
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
import xx.collection.bean.Schoollist;
import xx.collection.bean.Xlxw;
import xx.collection.bean.Xlxwbh;
import xx.quanxian.service.BaseService;

import com.googlecode.jsonplugin.annotations.JSON;
import com.opensymphony.xwork2.ActionSupport;
@Controller
@Scope("prototype")
@ParentPackage("default-package")
@Namespace("")
public class SchoolListAction extends ActionSupport {	
	
	@Resource(name="baseService")
	private BaseService baseservice;
	
	
	private Schoollist schoollist;
	private List<Schoollist> rows = new ArrayList<Schoollist>();
	private int page;//��ǰҳ
	private int rows_s;//ÿҳ��ʾ������
	private List<Schoollist> xxlist;
	private String xxmc;
	private String xxms;
	private int xxbh;
	private String queryType;	//��ѯ����ʱ�Ĳ�ѯ��������Ҫ���ݲ�ѯ������
	private String queryWord;	//��ѯ����ʱ�Ĳ�ѯ��������Ҫ���ݲ�ѯ�Ĺؼ���
	private List<String> xxjy = new ArrayList<String>();
	private List<String> xx = new ArrayList<String>();
	private int b;
	private int a;
    private int total=0; //��¼����  
    private int total1=0;
 
	
	
	public String getQueryType() {
		return queryType;
	}

	public void setQueryType(String queryType) {
		this.queryType = queryType;
	}
	public String getQueryWord() {
		return queryWord;
	}

	public void setQueryWord(String queryWord) {
		this.queryWord = queryWord;
	}

	@JSON(serialize=false)
	public List<Schoollist> getXxlist() {
		return xxlist;
	}

	public void setXxlist(List<Schoollist> xxlist) {
		this.xxlist = xxlist;
	}
	@JSON(serialize=false)
	public List<String> getXx() {
		return xx;
	}

	public void setXx(List<String> xx) {
		this.xx = xx;
	}

	@JSON(serialize=false)
	public String getXxmc() {
		return xxmc;
	}
	
	public void setXxmc(String xxmc) {
		this.xxmc = xxmc;
	}
	
	@JSON(serialize=false)
	public String getXxms() {
		return xxms;
	}
	
	public void setXxms(String xxms) {
		this.xxms = xxms;
	}
	
	@JSON(serialize=false)
	public int getXxbh() {
		return xxbh;
	}
	@JSON(deserialize=true)
	public void setXxbh(int xxbh) {
		this.xxbh = xxbh;
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
	public BaseService getBaseservice() {
		return baseservice;
	}
	
	@JSON(serialize=false)
	public Schoollist getSchoollist() {
		return schoollist;
	}

	public void setSchoollist(Schoollist schoollist) {
		this.schoollist = schoollist;
	}


	public List<Schoollist> getRows() {
		return rows;
	}

	public void setRows(List<Schoollist> rows) {
		this.rows = rows;
	}

	public void setBaseservice(BaseService baseservice) {
		this.baseservice = baseservice;
	}
   
    public int getTotal() {  
        return total;  
    }  
    public void setTotal(int total) {  
        this.total = total;  
    }  
    @JSON(serialize=false)
	public List<String> getXxjy() {
		return xxjy;
	}

	public void setXxjy(List<String> xxjy) {
		this.xxjy = xxjy;
	}

	public int getB() {
		return b;
	}

	public void setB(int b) {
		this.b = b;
	}
	
	public int getA() {
		return a;
	}

	public void setA(int a) {
		this.a = a;
	}


	/**
	 * @{insertxx.action}
	 * @param {schoollist} {��ʾѧУ��Ϣ}
	 * @return {schoollist} {��ʾѧУ��Ϣ}
	 * @{���ѧУ��Ϣ}
	 * @exception {˵����ĳ�����,������ʲô�쳣}
	*/
	@Action(value="insertxx",results={@Result(name="success",type="json")})
	public String insertxx(){
		Schoollist schoollist = new Schoollist();
		//schoollist.setXxbh(xxbh);
		schoollist.setXxmc(xxmc);
		schoollist.setXxms(xxms);
		this.baseservice.save(schoollist);
		return SUCCESS;
	}
	
	
	/**
	 * @{schoollist.action}
	 * @param {schoollist} {��ʾѧУ��Ϣ}
	 * @return {/tables_zxl/datagrid_schoollist.jsp} {��ʾѧУ��Ϣҳ��}
	 * @{��ʾѧУ��Ϣ}
	 * @exception {˵����ĳ�����,������ʲô�쳣}
	*/
	@Action(value="/schoollist",results={@Result(name="root",type="json")})
	public String schoollist(){		
        rows=this.baseservice.findAll(Schoollist.class, "Schoollist", page, rows_s);//��ҳ��ѯ
        total=this.baseservice.getTotal("Schoollist");        //��¼�����ļ�¼  
        return "root";  
	}
	
	
	//�����ѯ�Ƿ�Ϊ��,���ó���������
	@Action(value="/queryschool1",results={@Result(name="root",type="json")})
	public String queryschool1(){
		String hql = "select count(*) from Schoollist where "+queryType+" like '%"+queryWord+"%'";
		total1 = this.baseservice.getTotalSql(hql);
		HttpSession hs = ServletActionContext.getRequest().getSession();
		hs.setAttribute("schooltotal", total1);
		if(total1!=0){
			a=1;
		}else{
			a=0;
		}
		return "root";
	}
	
	/**
	 * @{queryschool.action}
	 * �÷������������ݹؼ��ֲ�ѯѧУ��Ϣ�ģ���ѯʱ���ҳ���������������queryType��queryWord
	 * queryType��Ҫ��ѯʱ�����ݵ����ͣ�queryWord�ǹؼ���
	 * ��ѯ���Ľ��ҳҪ�Է�ҳ����ʽ��ʾ
	*/
	@Action(value="/queryschool",results={@Result(name="root",type="json")})
	public String queryschool(){
		rows = this.baseservice.findByTypage(Schoollist.class, "Schoollist", queryType, queryWord, "order by xxbh asc", page, rows_s);//���ؼ��ֲ�ѯ
		HttpSession hs = ServletActionContext.getRequest().getSession();
		total=(Integer)hs.getAttribute("schooltotal");
		return "root";
	}
	
	/**
	 * @{deletexx.action}
	 * @param {schoollist} {��ʾѧУ��Ϣ}
	 * @return {schoollist.action} {��ʾѧУ��Ϣ}
	 * @{ɾ��ѧУ��Ϣ}
	 * @exception {˵����ĳ�����,������ʲô�쳣}
	*/
	@Action(value="deletexx",results={@Result(name="success",type="json")})
	public String deletexx(){
		String hql ="from Xlxwbh";
		List<Xlxwbh> xlbh2=this.baseservice.findHql(Xlxwbh.class, hql);
		List<Integer> xlbh1=new ArrayList<Integer>();
		if(xlbh2.size()>0){
			for(int i=0;i<xlbh2.size();i++){
				xlbh1.add(xlbh2.get(i).getSchoollist().getXxbh());
			}
			if(!xlbh1.contains(xxbh)){
				schoollist=this.baseservice.find(Schoollist.class,xxbh);
				this.baseservice.delete(schoollist);
				b=1;
			}else{
				b=0;
			}
		}else{
			schoollist=this.baseservice.find(Schoollist.class,xxbh);
			this.baseservice.delete(schoollist);
			b=1;
		}
		return SUCCESS;
	}

	
	/**
	 * @{updatexx}
	 * @param {schoollist} {��ʾѧУ��Ϣ}
	 * @return {schoollist.action} {��ʾѧУ��Ϣ}
	 * @{�޸�ѧУ��Ϣ}
	 * @exception {˵����ĳ�����,������ʲô�쳣}
	*/
	@Action(value="updatexx",results={@Result(name="success",type="json")})
	public String updatexx(){
		Schoollist schoollist = new Schoollist();
		schoollist.setXxbh(xxbh);
		schoollist.setXxmc(xxmc);
		schoollist.setXxms(xxms);
		this.baseservice.update(schoollist);	
		return SUCCESS;		
	}	
	
	
	//ҳ��У��
	@Action(value="/xxjy",results={@Result(name="success",type="json")})
	public String  xxjy(){
		xxjy = this.baseservice.find(String.class, "Schoollist", "xxmc");
		if(xxjy.contains(xxmc)){
		b = 0;
		}else{b=1;}
		return SUCCESS;
	}
	//�޸���ϢУ��
	@Action(value="/xxjy1",results={@Result(name="success",type="json")})
	public String  xxjy1(){
		xxjy = this.baseservice.find(String.class, "Schoollist", "xxmc");
		schoollist=this.baseservice.find(Schoollist.class, xxbh);
		for(int i=0;i<xxjy.size();i++){
			if(!xxjy.get(i).equals(schoollist.getXxmc())){
				xx.add(xxjy.get(i));
			}
		}
		if(!xx.contains(xxmc)){
			b=0;
		}else if(!xxjy.contains(xxmc)){
			b=0;
		}else{
			b=1;
		}
		
		return SUCCESS;
	}
	/**
	 * @{zyxx}
	 * @return {/tables_zxl/datagrid_schoollist.jsp} {��ʾѧУ��Ϣ}
	 * @{��ѧ  ѧУ��Ϣ����}
	 */	
//	@Action(value="xxlb",results={@Result(name="success",location="/tables_zxl/datagrid_schoollist.jsp")})
//	public String xxlb(){
//		//this.xxlist=this.baseservice.find(Schoollist.class);//ͨ��Schoollist_simple.class��ѯ�������ݲ�����xxlist�С�
//		return SUCCESS;
//	}
}
