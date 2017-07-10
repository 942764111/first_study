/*
 *@(#)xx.tables.action
 *@MzActions.java.java  
 *@����ʱ��:2011-5-18����09:29:14
 *@���ߣ�ylj
 *@Copyright 2009-2010 �ӱ�����ѧԺ��Ϣ��ѧ�빤��ѧԺ������ 
 */

package xx.tables.action;
/**
 * @MzActions <code>{Mz_simple}</code>
 * @author  {ylj}
 * @version {2011-5-18����09:29:14}
 * @{ʵ��������Ϣ�����ɾ�Ĳ�} 
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
import xx.collection.bean.Mz;

import xx.quanxian.service.BaseService;

import com.googlecode.jsonplugin.annotations.JSON;
import com.opensymphony.xwork2.ActionSupport;
@Controller
@Scope("prototype")
@Namespace("")
@ParentPackage("default-package")
@SuppressWarnings("serial")
public class MzActions extends ActionSupport {
	
	@Resource(name="baseService")
	private BaseService baseService;
	private Mz mz;
	private List<Mz> rows=new ArrayList<Mz>();
	private List<String> mzjy=new ArrayList<String>();
	private int page;//��¼��ҳ
	private int rows_s;
	private String mzmc;
	private String mztype;//��ѯ����
	private String mzword;//��ѯ�ؼ���
	private int b;//����У�����
	private int a;
	private static int total1=0;
	private String tip;								//ʹ��ajax�첽�ύʱ�����ڱ�Ƿ��ص�ֵ
	
	
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
		MzActions.total1 = total1;
	}
	@JSON(serialize=true)
	public List<String> getMzjy() {
		return mzjy;
	}
	public void setMzjy(List<String> mzjy) {
		this.mzjy = mzjy;
	}
	@JSON(serialize=true)
	public int getB() {
		return b;
	}
	public void setB(int b) {
		this.b = b;
	}
	@JSON(serialize=false)
	public String getMztype() {
		return mztype;
	}
	public void setMztype(String mztype) {
		this.mztype = mztype;
	}
	@JSON(serialize=false)
	public String getMzword() {
		return mzword;
	}
	public void setMzword(String mzword) {
		this.mzword = mzword;
	}
	public String getMzmc() {
		return mzmc;
	}
	@JSON(deserialize=true)
	public void setMzmc(String mzmc) {
		this.mzmc = mzmc;
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
	@JSON(serialize=false)
	public Mz getMz() {
		return mz;
	}
	public void setMz(Mz mz) {
		this.mz = mz;
	}
	public List<Mz> getRows() {
		return rows;
	}
	public void setRows(List<Mz> rows) {
		this.rows = rows;
	}
    //�ܼ�¼����  
    private int total=0;  
      
   
    public int getTotal() {  
        return total;  
    }  
    public void setTotal(int total) {  
        this.total = total;  
    } 
    
    private String mzbh;
    public String getMzbh()  {
       return mzbh;	
    }
	@JSON(deserialize=true)
	public void setMzbh(String mzbh){
		this.mzbh=mzbh;
	}
	/**
	 * @{insertMz.action}
	 * @param {Mz} {Mzʵ����Ķ���ʵ��}
	 * @return {SUCCESS} {�����Ϣ�ɹ�}
	 * @{ʵ����ӹ���}
	 * @exception {˵����ĳ�����,������ʲô�쳣}
	*/
	@Action(value="/insertMz",results={@Result(name="success",type="redirect",location="/tables/datagrid_Mz.jsp")})			
	public String insertMz(){
		Mz mz=new Mz();
		mz.setMzmc(mzmc);
		this.baseService.save(mz);
		return SUCCESS;
	}
	/**
	 * @{listMz.action}
	 * @param {rows_s,page,total} {��ӦfindAll��������Ҫ�Ĳ���}
	 * @return {/tables/datagrid_Mz.jsp} {����������Ϣ����}
	 * @{ʵ�ֲ�ѯ����}
	 * @exception {˵����ĳ�����,������ʲô�쳣}
	*/
	@Action(value="/listMz",results={@Result(name="root",type="json")})
	//��������json����
	public String listMz(){
        rows = this.baseService.findAll(Mz.class, "Mz", page, rows_s);
        //��¼�����ļ�¼  
        total=this.baseService.getTotal("Mz"); //��¼���� 
        return "root";  
	}
	/**
	 * @{deleteMz.action}
	 * @param {mzbh} {��Ӧ���ݱ�����}
	 * @return {SUCCESS} {ɾ�����ݳɹ�}
	 * @{ʵ��ɾ������}
	 * @exception {˵����ĳ�����,������ʲô�쳣}
	*/
	@Action(value="deleteMz",results={@Result(name="success",type="json")})
	public String deleteMz(){
		int id = Integer.parseInt(mzbh);//ǿ��ת��
		mz=this.baseService.find(Mz.class,id);//ͨ��id���������
		this.baseService.delete(mz);
		return SUCCESS;
	}
	/**
	 * @{updateMz}
	 * @param {mz} {Mz��Ķ���ʵ��}
	 * @return {updateMz.action} {}
	 * @{�޸Ĺ���}
	 * @exception {˵����ĳ�����,������ʲô�쳣}
	*/
	@Action(value="updateMz",results={@Result(name="success",location="/tables/datagrid_Mz.jsp",type="redirect")})
	public String updateMz(){
		int id = Integer.parseInt(mzbh);//ǿ��ת��
		this.mz=this.baseService.find(Mz.class,id);//ͨ��id���������
		//this.mz =this.baseService.find(Mz.class,mzbh);
		mz.setMzmc(mzmc);
		this.baseService.update(mz);
		return SUCCESS;
	}
	//ʵ�ֲ�ѯ����
	@Action(value="/searchMz",results={@Result(name="root",type="json")})
	public String searchMz(){
		rows = this.baseService.findByTypage(Mz.class,"Mz",mztype,mzword, "order by mzbh asc", page, rows_s);
		total=total1;//��¼������
		return "root";
	}
	//�����ѯ�Ƿ�Ϊ��,���ó���������
	@Action(value="/searchMz1",results={@Result(name="root",type="json")})
	public String searchMz1(){
		String hql = "select count(*) from Mz where "+mztype+" like '%"+mzword+"%'";//ͨ���ؼ��ֲ�ѯ
		total1 = this.baseService.getTotalSql(hql);
		if(total1!=0){
			a=1;
		}else{
			a=0;
		}
		return "root";
	}
	
	//ҳ��У��
	@Action(value="/Mzjy",results={@Result(name="success",type="json")})
	public String  Mzjy(){
		mzjy = this.baseService.find(String.class, "Mz", "mzmc");
		if(mzjy.contains(mzmc)){
		b = 0;
		}else{b=1;}
		return SUCCESS;
	}
//	/**
//	 * ģ����ࣨ�������
//	 */	
//	@Action(value="mz",results={@Result(name="success",location="/tables/datagrid_Mz.jsp",type="redirect")})
//	public String mz(){
//		total=this.baseService.getTotal("Mz"); 
//		return SUCCESS;
//	}
	
}
