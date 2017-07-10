/*
 * @(#)xx.tables_zxl.action.{file_name} 
 * @ListXlxwAction.java.java 
 * @ ����ʱ�䣺2011-4-18     
 * @ ���ߣ�zxl
 * @ Copyright 2009-2010 �ӱ�����ѧԺ��Ϣ��ѧ�빤��ѧԺ������ 
 */
package xx.tables_zxl.action;


/**
 * @ListJszcAction <code>{������}</code>
 * @author  {zxl}
 * @version {�汾,����ʱ�����}
 * @{ѧ��ѧλ��Ϣ����ɾ�Ĳ�} 
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

import xx.collection.bean.Xlxw;
import xx.collection.bean.Xlxwbh;

import xx.quanxian.service.BaseService;

import com.googlecode.jsonplugin.annotations.JSON;

import com.opensymphony.xwork2.ActionSupport;
@Controller
@Scope("prototype")
@ParentPackage("default-package")
@Namespace("")
public class ListXlxwAction extends ActionSupport {	
	
	@Resource(name="baseService")
	private BaseService baseservice;
	
	private List<Xlxw> xlxwlist;
	private Xlxw xlxw;
	private List<Xlxw> rows = new ArrayList<Xlxw>();
	private int page;//��ǰҳ
	private int rows_s;//ÿҳ��ʾ������
	private String xlmc;
	private String type;	  
    private int xlbh;
    private String queryType;	//��ѯ����ʱ�Ĳ�ѯ��������Ҫ���ݲ�ѯ������
	private String queryWord;	//��ѯ����ʱ�Ĳ�ѯ��������Ҫ���ݲ�ѯ�Ĺؼ���
	private List<String> xljy = new ArrayList<String>();
	private List<String> xx = new ArrayList<String>();
	private int b;
	private int a;
	private int total1;
   
	
	public int getA() {
		return a;
	}
	public void setA(int a) {
		this.a = a;
	}
	
	@JSON(serialize=false)
    public List<String> getXljy() {
		return xljy;
	}
	public void setXljy(List<String> xljy) {
		this.xljy = xljy;
	}
	@JSON(serialize=false)
	public List<String> getXx() {
		return xx;
	}
	public void setXx(List<String> xx) {
		this.xx = xx;
	}
	public int getB() {
		return b;
	}
	public void setB(int b) {
		this.b = b;
	}
	@JSON(serialize=false)
	public int getXlbh() {
		return xlbh;
	}
    @JSON(deserialize=true)
	public void setXlbh(int xlbh) {
		this.xlbh = xlbh;
	}
    
	@JSON(serialize=false)
	public String getXlmc() {
		return xlmc;
	}
	
	public void setXlmc(String xlmc) {
		this.xlmc = xlmc;
	}
	
	@JSON(serialize=false)
	public String getType() {
		return type;
	}
	
	public void setType(String type) {
		this.type = type;
	}
	@JSON(serialize=false)
	public List<Xlxw> getXlxwlist() {
		return xlxwlist;
	}
	public void setXlxwlist(List<Xlxw> xlxwlist) {
		this.xlxwlist = xlxwlist;
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
	public void setBaseservice(BaseService baseservice) {
		this.baseservice = baseservice;
	}
	
	@JSON(serialize=false)
	public Xlxw getXlxw() {
		return xlxw;
	}
	
	public void setXlxw(Xlxw xlxw) {
		this.xlxw = xlxw;
	}

    public List<Xlxw> getRows() {
		return rows;
	}
	public void setRows(List<Xlxw> rows) {
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
	 * @{insertxlxw.action}
	 * @param {xlxwlist} {��ʾѧ��ѧλ��Ϣ}
	 * @return {xlxwlist} {��ʾѧ��ѧλ��Ϣ}
	 * @{���ѧ��ѧλ��Ϣ}
	 * @exception {˵����ĳ�����,������ʲô�쳣}
	*/
	@Action(value="insertxlxw",results={@Result(name="success",type="json")})
	public String insertxlxw(){	
		Xlxw xlxw = new Xlxw();		
		//xlxw.setXlbh(xlbh);
		xlxw.setXlmc(xlmc);		
		xlxw.setType(type);
		this.baseservice.save(xlxw);//�������
		return SUCCESS;
	}
	
	/**
	 * @{xlxwlist.action}
	 * @param {xlxwlist} {��ʾѧ��ѧλ��Ϣ}
	 * @return {/tables_zxl/datagrid_xlxw.jsp} {��ʾѧ��ѧλ��Ϣҳ��}
	 * @{��ʾ����}
	 * @exception {˵����ĳ�����,������ʲô�쳣}
	*/
	@Action(value="/xlxwlist",results={@Result(name="root",type="json")})
	public String xlxwlist(){
		rows=this.baseservice.findAll(Xlxw.class, "Xlxw", page, rows_s);//���������ݷ���rows������ʵ�ַ�ҳ��ѯ    
        total=this.baseservice.getTotal("Xlxw") ;  //��¼�����ļ�¼    
        return "root";  
	}
	/**
	 * @{deletexlxw.action}
	 * @param {xlxwlist.action} {��ʾѧ��ѧλ��Ϣ}
	 * @return {xlxwlist.action} {��ѧ��ѧλ��Ϣ}
	 * @{ɾ��ѧ��ѧλ��Ϣ}
	 * @exception {˵����ĳ�����,������ʲô�쳣}
	*/
	@Action(value="deletexlxw",results={@Result(name="success",type="json")})
	public String deletexlxw(){
		String hql ="from Xlxwbh";
		List<Xlxwbh> xlbh2=this.baseservice.findHql(Xlxwbh.class, hql);
		List<Integer> xlbh1=new ArrayList<Integer>();
		if(xlbh2.size()>0){
			for(int i=0;i<xlbh2.size();i++){
				xlbh1.add(xlbh2.get(i).getXlxw().getXlbh());
			}
			if(!xlbh1.contains(xlbh)){
				xlxw=this.baseservice.find(Xlxw.class,xlbh);//ͨ��xlbhɾ��һ�����ݣ�xlbhҪ���з����л���
				this.baseservice.delete(xlxw);//ɾ������
				b=1;
			}else{
				b=0;
			}
		}else{
			xlxw=this.baseservice.find(Xlxw.class,xlbh);//ͨ��xlbhɾ��һ�����ݣ�xlbhҪ���з����л���
			this.baseservice.delete(xlxw);//ɾ������
			b=1;
		}
		
		
		return SUCCESS;
	}

	/**
	 * @{updatexlxw}
	 * @param {xlxwlist.action} {��ʾѧ��ѧλ��Ϣ}
	 * @return {xlxwlist.action} {��ʾѧ��ѧλ��Ϣ}
	 * @{�޸�ѧ��ѧλ��Ϣ}
	 * @exception {˵����ĳ�����,������ʲô�쳣}
	*/
	@Action(value="updatexlxw",results={@Result(name="success",type="json")})
	public String updatexlxw(){
		Xlxw xlxw = new Xlxw();		
		xlxw.setXlbh(xlbh);
		xlxw.setXlmc(xlmc);		
		xlxw.setType(type);
		this.baseservice.update(xlxw);	//��������
		return SUCCESS;		
	}	
	//�����ѯ�Ƿ�Ϊ��,���ó���������
	@Action(value="/queryxl1",results={@Result(name="root",type="json")})
	public String queryxl1(){
		String hql = "select count(*) from Xlxw where "+queryType+" like '%"+queryWord+"%'";
		total1 = this.baseservice.getTotalSql(hql);
		HttpSession hs = ServletActionContext.getRequest().getSession();
		hs.setAttribute("xlxwtotal", total1);
		if(total1!=0){
			a=1;
		}else{
			a=0;
		}
		return "root";
	}
	/**
	 * @{queryxl.action}
	 * �÷������������ݹؼ��ֲ�ѯѧ����Ϣ�ģ���ѯʱ���ҳ���������������queryType��queryWord
	 * queryType��Ҫ��ѯʱ�����ݵ����ͣ�queryWord�ǹؼ���
	 * ��ѯ���Ľ��ҳҪ�Է�ҳ����ʽ��ʾ
	*/
	@Action(value="/queryxl",results={@Result(name="root",type="json")})
	public String queryxl(){
		rows = this.baseservice.findByTypage(Xlxw.class, "Xlxw", queryType, queryWord, "order by xlbh asc", page, rows_s);//���ؼ��ֲ�ѯ
		HttpSession hs = ServletActionContext.getRequest().getSession();
		total=(Integer)hs.getAttribute("xlxwtotal");
		return "root";
	}
	
	
	//ҳ��У��
	@Action(value="/xljy",results={@Result(name="success",type="json")})
	public String  xljy(){
		xljy = this.baseservice.find(String.class, "Xlxw", "xlmc");
		if(xljy.contains(xlmc)){
		b = 0;
		}else{b=1;}
		return SUCCESS;
	}
	//�޸���ϢУ��
	@Action(value="/xljy1",results={@Result(name="success",type="json")})
	public String  xljy1(){
		xljy = this.baseservice.find(String.class, "Xlxw", "xlmc");
		xlxw=this.baseservice.find(Xlxw.class, xlbh);
		for(int i=0;i<xljy.size();i++){
			if(!xljy.get(i).equals(xlxw.getXlmc())){
				xx.add(xljy.get(i));
			}
		}
		if(!xx.contains(xlmc)){
			b=0;
		}else if(!xljy.contains(xlmc)){
			b=0;
		}else{
			b=1;
		}
		
		return SUCCESS;
	}
	/**
	 * @{xlxw}
	 * @return {/tables_zxl/datagrid_xlxw.jsp} {��ʾѧ��ѧλ��Ϣ}
	 * @{��ѧ  ѧ��ѧλ����}
	 */	
//	@Action(value="xlxw",results={@Result(name="success",location="/tables_zxl/datagrid_xlxw.jsp")})
//	public String xlxw(){
//		//this.xlxwlist = this.baseservice.find(Xlxw.class);//ͨ��Xlxw_simple.class��ѯ�������ݲ�����xlxwlist�С�
//		return SUCCESS;
//	}
}
