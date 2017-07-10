/*
 *@(#)xx.tables.action
 *@JslbActions.java.java  
 *@����ʱ��:2011-5-18����09:29:14
 *@���ߣ�ylj
 *@Copyright 2009-2010 �ӱ�����ѧԺ��Ϣ��ѧ�빤��ѧԺ������ 
 */

package xx.tables.action;
/**
 * @JslbActions <code>{Jslb_simple}</code>
 * @author  {ylj}
 * @version {2011-5-18����09:29:14}
 * @{ʵ�ֽ�ʦ�������ɾ�Ĳ�} 
 */

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import xx.collection.bean.Jslb;
import xx.quanxian.service.BaseService;

import com.googlecode.jsonplugin.annotations.JSON;
import com.opensymphony.xwork2.ActionSupport;

@Controller
@Scope("prototype")
@Namespace("")
@ParentPackage("default-package")
@SuppressWarnings("serial")
public class JslbActions extends ActionSupport {
	
	@Resource(name="baseService")
	private BaseService baseService;
	private Jslb jslb1;
	private List<Jslb> rows=new ArrayList<Jslb>();
	private List<String> lbjy=new ArrayList<String>();
	private int page;
	private int rows_s;
	private String lbmc;
	private String lbbz;
	private String jslbtype;
	private String jslbword;
	private int b;
	private int a;
	private static int total1=0;
	private String tip;	
	
	
	
	public String getTip() {
		return tip;
	}
	public void setTip(String tip) {
		this.tip = tip;
	}
	public int getA() {
		return a;
	}
	public void setA(int a) {
		this.a = a;
	}
	public static int getTotal1() {
		return total1;
	}
	public static void setTotal1(int total1) {
		JslbActions.total1 = total1;
	}
	@JSON(deserialize=true)
	public List<String> getLbjy() {
		return lbjy;
	}
	public void setLbjy(List<String> lbjy) {
		this.lbjy = lbjy;
	}
	@JSON(deserialize=true)
	public int getB() {
		return b;
	}
	public void setB(int b) {
		this.b = b;
	}
	@JSON(deserialize=true)
	public String getJslbtype() {
		return jslbtype;
	}
	public void setJslbtype(String jslbtype) {
		this.jslbtype = jslbtype;
	}
	@JSON(deserialize=true)
	public String getJslbword() {
		return jslbword;
	}
	public void setJslbword(String jslbword) {
		this.jslbword = jslbword;
	}
	public String getLbmc() {
		return lbmc;
	}
	@JSON(deserialize=true)
	public void setLbmc(String lbmc) {
		this.lbmc = lbmc;
	}
	public String getLbbz() {
		return lbbz;
	}
	@JSON(deserialize=true)
	public void setLbbz(String lbbz) {
		this.lbbz = lbbz;
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
	public BaseService getBaseService() {
		return baseService;
	}
	public void setBaseService(BaseService baseService) {
		this.baseService = baseService;
	}
	
    //��¼����  
    private int total=0;  
  
    public int getTotal() {  
        return total;  
    } 
	@JSON(serialize=false)
    public Jslb getJslb1() {
		return jslb1;
	}
	public void setJslb1(Jslb jslb1) {
		this.jslb1 = jslb1;
	}
	public List<Jslb> getRows() {
		return rows;
	}
	public void setRows(List<Jslb> rows) {
		this.rows = rows;
	}
	public void setTotal(int total) {  
        this.total = total;  
    }  
	
    private String jslb;
    public String getJslb()  {
       return jslb;	
    }
	@JSON(deserialize=true)
	public void setJslb(String jslb){
		this.jslb=jslb;
	}
	/**
	 * @{insertJslb.action}
	 * @param {jslb} {jslbʵ����Ķ���ʵ��}
	 * @return {success} {��Ϣ��ӳɹ�}
	 * @{ʵ����ӹ���}
	 * @exception {˵����ĳ�����,������ʲô�쳣}
	*/
	@Action(value="/insertJslb",results={@Result(name="success",location="/tables/datagrid_Jslb.jsp",type="redirect")})	
	public String insertJslb(){
		Jslb jslb1 =new Jslb();
		jslb1.setLbmc(lbmc);
		jslb1.setLbbz(lbbz);
		this.baseService.save(jslb1);
		return SUCCESS;
	}
	/**
	 * @{listJslb.action}
	 * @param {rows_s,page,total} {findAll������Ӧ�Ĳ���}
	 * @return {/tables/datagrid_Jslb.jsp} {���ؽ�ʦ���ҳ��}
	 * @{ʵ�ֲ�ѯ����}
	 * @exception {˵����ĳ�����,������ʲô�쳣}
	*/
	@Action(value="/listJslb",results={@Result(name="root",type="json")})
	//��������json����
	public String listJslb(){
        rows = this.baseService.findAll(Jslb.class, "Jslb", page, rows_s);
        //��¼�����ļ�¼  
        total=this.baseService.getTotal("Jslb");  
        return "root";  
	}
	/**
	 * @{deleteJslb.action}
	 * @param {jslb} {��Ӧ���ݱ�����}
	 * @return {success} {��Ϣɾ���ɹ�}
	 * @{ʵ��ɾ������}
	 * @exception {˵����ĳ�����,������ʲô�쳣}
	*/
	@Action(value="deleteJslb",results={@Result(name="success",type="json")})
	public String deleteJslb(){
		int id = Integer.parseInt(jslb);//����ʦ���ǿ��ת����int��
		jslb1=this.baseService.find(Jslb.class,id);
		this.baseService.delete(jslb1);
		return SUCCESS;
	}
	/**
	 * @{updateJslb.action}
	 * @param {jslb} {jslbʵ����Ķ���ʵ��}
	 * @return {updateJslb.action} {��ʾ����}
	 * @{ʵ���޸Ĺ���}
	 * @exception {˵����ĳ�����,������ʲô�쳣}
	*/
	@Action(value="updateJslb",results={@Result(name="success",location="/tables/datagrid_Jslb.jsp",type="redirect")})
	public String updateJslb(){
		int id = Integer.parseInt(jslb);//����ʦ���ǿ��ת����int��
		this.jslb1=this.baseService.find(Jslb.class,id);
		jslb1.setLbmc(lbmc);
		jslb1.setLbbz(lbbz);
		this.baseService.update(jslb1);
		return SUCCESS;
	}
	//ʵ�ֲ�ѯ����
	@Action(value="/searchJslb",results={@Result(name="root",type="json")})
	public String searchJslb(){
		rows = this.baseService.findByTypage(Jslb.class,"Jslb",jslbtype,jslbword, "order by jslb asc", page, rows_s);
		total=total1;
		return "root";
	}
	//�����ѯ�Ƿ�Ϊ��,���ó���������
	@Action(value="/searchJslb1",results={@Result(name="root",type="json")})
	public String searchJslb1(){
		String hql = "select count(*) from Jslb where "+jslbtype+" like '%"+jslbword+"%'";
		total1 = this.baseService.getTotalSql(hql);
		if(total1!=0){
			a=1;
		}else{
			a=0;
		}
		return "root";
	}
	
	//ҳ��У��
	@Action(value="/Lbjy",results={@Result(name="success",type="json")})
	public String  Lbjy(){
		lbjy = this.baseService.find(String.class, "Jslb", "lbmc");
		if(lbjy.contains(lbmc)){
		b = 0;
		}else{b=1;}
		return SUCCESS;
	}
//	/**
//	 * ģ����ࣨ��ʦ���
//	 */
//	@Action(value="jslb",results={@Result(name="success",location="/tables/datagrid_Jslb.jsp",type="redirect")})
//	public String jslb(){
//		return SUCCESS;
//	}
}

