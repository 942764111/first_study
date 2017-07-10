/*
 *@(#)xx.tables.action
 *@XuyanActions.java.java  
 *@����ʱ��:2011-5-18����09:29:14
 *@���ߣ�ylj
 *@Copyright 2009-2010 �ӱ�����ѧԺ��Ϣ��ѧ�빤��ѧԺ������ 
 */

package xx.tables.action;
/**
 * @XuyanActions <code>{Xuyan_simple}</code>
 * @author  {ylj}
 * @version {2011-5-18����09:29:14}
 * @{ʵ�ֶ���ѧԺ�����ɾ�Ĳ�} 
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

import xx.collection.bean.Xuyan;
import xx.quanxian.service.BaseService;

import com.googlecode.jsonplugin.annotations.JSON;
import com.opensymphony.xwork2.ActionSupport;
@Controller
@Scope("prototype")
@Namespace("")
@ParentPackage("default-package")
@SuppressWarnings("serial")
public class XuyanActions extends ActionSupport {
	
	@Resource(name="baseService")
	private BaseService baseService;
	private Xuyan xuyan;
	private List<Xuyan> rows=new ArrayList<Xuyan>();
	private List<String> xyjy=new ArrayList<String>();
	private int page;//�������
	private int rows_s;
	private String xymc;
	private String xytype;
	private String xyword;
	private int b;
	private int a;
	private static int total1=0;
	
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
		XuyanActions.total1 = total1;
	}
	@JSON(deserialize=true)
	public List<String> getXyjy() {
		return xyjy;
	}
	public void setXyjy(List<String> xyjy) {
		this.xyjy = xyjy;
	}
	@JSON(deserialize=true)
	public int getB() {
		return b;
	}
	public void setB(int b) {
		this.b = b;
	}
	@JSON(deserialize=true)
	public String getXytype() {
		return xytype;
	}
	public void setXytype(String xytype) {
		this.xytype = xytype;
	}
	@JSON(deserialize=true)
	public String getXyword() {
		return xyword;
	}
	public void setXyword(String xyword) {
		this.xyword = xyword;
	}
	public String getXymc() {
		return xymc;
	}
	@JSON(deserialize=true)
	public void setXymc(String xymc) {
		this.xymc = xymc;
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
	public Xuyan getXuyan() {
		return xuyan;
	}
	public void setXuyan(Xuyan xuyan) {
		this.xuyan = xuyan;
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
      
  
    public List<Xuyan> getRows() {
		return rows;
	}
	public void setRows(List<Xuyan> rows) {
		this.rows = rows;
	}
	public int getTotal() {  
        return total;  
    }  
    public void setTotal(int total) {  
        this.total = total;  
    } 
    private String xybh;
    public String getXybh()  {
       return xybh;	
    }
	@JSON(deserialize=true)
	public void setXybh(String xybh){
		this.xybh=xybh;
	}
	/**
	 * @{insertXuyan.action}
	 * @param {xuyan} {xuyanʵ����Ķ���ʵ��}
	 * @return {success} {��Ϣ��ӳɹ�}
	 * @{ʵ����ӹ���}
	 * @exception {˵����ĳ�����,������ʲô�쳣}
	*/
	@Action(value="/insertXuyan",results={@Result(name="success",location="/tables/datagrid_Xuyan.jsp",type="redirect")})			
	public String insertXuyan(){
		Xuyan xuyan=new Xuyan();
		xuyan.setXymc(xymc);
		this.baseService.save(xuyan);
		return SUCCESS;
	}
	/**
	 * @{listXuyan.action}
	 * @param {rows_s,page,total} {findAll������Ӧ�Ĳ���}
	 * @return {/tables/datagrid_Xuyan.jsp} {���ض���ѧԺ��Ϣ����}
	 * @{ʵ�ֲ�ѯ����}
	 * @exception {˵����ĳ�����,������ʲô�쳣}
	*/
	@Action(value="/listXuyan",results={@Result(name="root",type="json")})
	//��������json����
	public String listXuyan(){
        rows = this.baseService.findAll(Xuyan.class,"Xuyan",page,rows_s); 
        //��¼�����ļ�¼  
        total=this.baseService.getTotal("Xuyan");  
        return "root";  
	}
	/**
	 * @{deleteXuyan.action}
	 * @param {xuyan} {��Ӧ���ݱ�����}
	 * @return {success} {��Ϣɾ���ɹ�}
	 * @{ʵ��ɾ������}
	 * @exception {˵����ĳ�����,������ʲô�쳣}
	*/
	@Action(value="deleteXuyan",results={@Result(name="success",type="json")})
	public String deleteXuyan(){
		int id = Integer.parseInt(xybh);
		xuyan=this.baseService.find(Xuyan.class,id);
		this.baseService.delete(xuyan);
		return SUCCESS;
	}
	/**
	 * @{updateXuyan.action}
	 * @param {xuyan} {xuyanʵ����Ķ���ʵ��}
	 * @return {updateXuyan} {��ʾ����}
	 * @{ʵ���޸Ĺ���}
	 * @exception {˵����ĳ�����,������ʲô�쳣}
	*/
	@Action(value="updateXuyan",results={@Result(name="success",location="/tables/datagrid_Xuyan.jsp",type="redirect")})
	public String updateXuyan(){
		int id = Integer.parseInt(xybh);
		this.xuyan=this.baseService.find(Xuyan.class,id);
		xuyan.setXymc(xymc);
		this.baseService.update(xuyan);
		return SUCCESS;
	}
	//ʵ�ֲ�ѯ����
	@Action(value="/searchXuyan",results={@Result(name="root",type="json")})
	public String searchXuyan(){
		rows = this.baseService.findByTypage(Xuyan.class,"Xuyan",xytype,xyword, "order by xybh asc", page, rows_s);
		total=total1; 
		return "root";
	}
	//�����ѯ�Ƿ�Ϊ��,���ó���������
	@Action(value="/searchXuyan1",results={@Result(name="root",type="json")})
	public String searchXuyan1(){
		String hql = "select count(*) from Xuyan where "+xytype+" like '%"+xyword+"%'";
		total1 = this.baseService.getTotalSql(hql);
		if(total1!=0){
			a=1;
		}else{
			a=0;
		}
		return "root";
	}
	
	//ҳ��У��
	@Action(value="/Xyjy",results={@Result(name="success",type="json")})
	public String  Xyjy(){
		xyjy = this.baseService.find(String.class, "Xuyan", "xymc");
		if(xyjy.contains(xymc)){
		b = 0;
		}else{b=1;}
		return SUCCESS;
	}
//	/**
//	 * ģ����ࣨѧԺ����
//	 */	
//	@Action(value="xuyan",results={@Result(name="success",location="/tables/datagrid_Xuyan.jsp",type="redirect")})
//	public String xuyan(){
//		total=this.baseService.getTotal("Xuyan"); 
//		return SUCCESS;
//	}
}
