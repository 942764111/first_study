/*
 * @(#)xx.tables_zxl.action.{file_name} 
 * @ListZyxxAction.java.java 
 * @ ����ʱ�䣺2011-4-18     
 * @ ���ߣ�zxl
 * @ Copyright 2009-2010 �ӱ�����ѧԺ��Ϣ��ѧ�빤��ѧԺ������ 
 */
package xx.tables_zxl.action;


/**
 * @ListJszcAction <code>{������}</code>
 * @author  {zxl}
 * @version {�汾,����ʱ�����}
 * @{רҵ��Ϣ����ɾ�Ĳ�} 
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



import xx.bean.Teacher;
import xx.collection.bean.Bjxx;
import xx.collection.bean.Pd;
import xx.collection.bean.Schoollist;
import xx.collection.bean.Xuyan;
import xx.collection.bean.Zyxx;
import xx.kgt.bean.PanDuan;
import xx.quanxian.service.BaseService;

import com.googlecode.jsonplugin.annotations.JSON;
import com.opensymphony.xwork2.ActionSupport;
@Controller
@Scope("prototype")
@ParentPackage("default-package")
@Namespace("")
public class ListZyxxAction extends ActionSupport {	
	
	@Resource(name="baseService")
	private BaseService baseservice;
	
	
	private Zyxx zyxx;
	private List<Zyxx> listzyxx = new ArrayList<Zyxx>();
	private List<Zyxxi> rows =new ArrayList<Zyxxi>();
	private int page;//��ǰҳ
	private int rows_s;//ÿҳ��ʾ������
	private int zybh;
	private Xuyan xuyan;
	private String zymc;
	private List<Zyxx> zyxxb;
	private List<String> zyxxb1=new ArrayList<String>();
	private String xymc;
	private String queryType;	//��ѯ����ʱ�Ĳ�ѯ��������Ҫ���ݲ�ѯ������
	private String queryWord;	//��ѯ����ʱ�Ĳ�ѯ��������Ҫ���ݲ�ѯ�Ĺؼ���
	private List<String> zyjy = new ArrayList<String>();
	private List<Integer> zyjy1 = new ArrayList<Integer>();
	private List<String> xx = new ArrayList<String>();
	private int b;
	private int a;
	private int total1=0;

	
	public int getA() {
		return a;
	}
	public void setA(int a) {
		this.a = a;
	}
	
	@JSON(serialize=false)
	public List<Integer> getZyjy1() {
		return zyjy1;
	}
	public void setZyjy1(List<Integer> zyjy1) {
		this.zyjy1 = zyjy1;
	}
	@JSON(serialize=false)
	public List<String> getZyjy() {
		return zyjy;
	}
	public void setZyjy(List<String> zyjy) {
		this.zyjy = zyjy;
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
	public List<String> getZyxxb1() {
		return zyxxb1;
	}
	public void setZyxxb1(List<String> zyxxb1) {
		this.zyxxb1 = zyxxb1;
	}
	@JSON(serialize=false)
	public String getXymc() {
		return xymc;
	}
	public void setXymc(String xymc) {
		this.xymc = xymc;
	}
	
	@JSON(serialize=false)
	public List<Zyxx> getZyxxb() {
		return zyxxb;
	}
	public void setZyxxb(List<Zyxx> zyxxb) {
		this.zyxxb = zyxxb;
	}
	
	@JSON(serialize=false)
	public int getZybh() {
		return zybh;
	}
	 //�����л����������ܹ����ص����ݿ�
	@JSON(deserialize=true)
	public void setZybh(int zybh) {
		this.zybh = zybh;
	}
	
	
	@JSON(serialize=false)
	public Xuyan getXuyan() {
		return xuyan;
	}

	public void setXuyan(Xuyan xuyan) {
		this.xuyan = xuyan;
	}
	
	
	@JSON(serialize=false)
	public String getZymc() {
		return zymc;
	}
	
	public void setZymc(String zymc) {
		this.zymc = zymc;
	}
	
	
	@JSON(serialize=false)
	public List<Zyxx> getListzyxx() {
		return listzyxx;
	}
	public void setListzyxx(List<Zyxx> listzyxx) {
		this.listzyxx = listzyxx;
	}
	
	
	public List<Zyxxi> getRows() {
		return rows;
	}
	public void setRows(List<Zyxxi> rows) {
		this.rows = rows;
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
	public Zyxx getZyxx() {
		return zyxx;
	}
	public void setZyxx(Zyxx zyxx) {
		this.zyxx = zyxx;
	}

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


	//��¼����  
    private int total=0;  
      
    public int getTotal() {  
        return total;  
    }  
    public void setTotal(int total) {  
        this.total = total;  
    }  
 
	/**
	 * @{insertzyxx.action}
	 * @param {zyxxlist} {��ʾרҵ��Ϣ}
	 * @return {zyxxlist} {��ʾרҵ��Ϣ}
	 * @{���רҵ��Ϣ}
	 * @exception {˵����ĳ�����,������ʲô�쳣}
	*/
	@Action(value="insertzyxx",results={@Result(name="success",type="json")})
	public String insertzyxx(){
		
		String[] keys=new String[1];
		keys[0]="xymc";
		Object[] values=new Object[1];
		values[0]=xymc;
		List<Integer> xybh=this.baseservice.find(Integer.class, "Xuyan", "xybh", keys, values);//ͨ��xymc��values�����xybh������list��
		Xuyan xuyan=this.baseservice.find(Xuyan.class,xybh.get(0));//ͨ����õĵ�ǰxybh���xuyan��ѧԺ����Ϣ��		
		Zyxx zyxx = new Zyxx();
		zyxx.setXuyan(xuyan);
		zyxx.setZybh(zybh);
		zyxx.setZymc(zymc);
		this.baseservice.save(zyxx);//��������
		return SUCCESS;
	}
	/**
	 * @{zyxxlist.action}
	 * @param {zyxxlist} {��ʾרҵ��Ϣ}
	 * @return {zyxxlist} {��ʾרҵ��Ϣ}
	 * @{��ʾ����}
	*/
	@Action(value="/zyxxlist",results={@Result(name="root",type="json")})
	public String zyxxlist(){
		
		listzyxx=this.baseservice.findAll(Zyxx.class, "Zyxx", page, rows_s);	//�����������	
        total=this.baseservice.getTotal("Zyxx");  //��¼����
        for(int i=0;i<listzyxx.size();i++){
        	Zyxxi element = new Zyxxi();
        	element.setInt1(listzyxx.get(i).getZybh());
        	element.setInt2(listzyxx.get(i).getXuyan().getXybh());       	
        	element.setStr1(listzyxx.get(i).getZymc());  
        	element.setStr2(listzyxx.get(i).getXuyan().getXymc());
        	rows.add(element);   //�����ݷŽ�rows��ʵ�ַ�ҳ��ѯ��ʾ    	
        	
        }
        return "root";  
	}
	/**
	 * @{deletezyxx.action}
	 * @param {zyxxlist} {��ʾרҵ��Ϣ}
	 * @return {zyxxlist.action} {��ʾרҵ��Ϣ}
	 * @{ɾ��רҵ��Ϣ}
	 * @exception {˵����ĳ�����,������ʲô�쳣}
	*/
	@Action(value="deletezyxx",results={@Result(name="success",type="json")})
	public String deletezyxx(){
		String hql1="from Bjxx";
		List<Bjxx> bjxx=this.baseservice.findHql(Bjxx.class, hql1);
		List<Integer> bjxx1=new ArrayList<Integer>();
		if(bjxx.size()>0){
			for(int i=0;i<bjxx.size();i++){
				bjxx1.add(bjxx.get(i).getZyxx().getZybh());
			}
			if(!bjxx1.contains(zybh)){
				zyxx=this.baseservice.find(Zyxx.class,zybh);//ͨ��id���Ҫɾ��������
				this.baseservice.delete(zyxx);
				a=1;
			}else{
				a=0;
			}
		}else{
			zyxx=this.baseservice.find(Zyxx.class,zybh);//ͨ��id���Ҫɾ��������
			this.baseservice.delete(zyxx);
			a=1;
		}
		
		
		return SUCCESS;
	}

	/**
	 * @{updatezyxx}
	 * @param {zyxxlist} {��ʾרҵ��Ϣ}
	 * @return {zyxxlist.action} {��ʾרҵ��Ϣ}
	 * @{�޸�רҵ��Ϣ}
	 * @exception {˵����ĳ�����,������ʲô�쳣}
	*/
	@Action(value="updatezyxx",results={@Result(name="success",type="json")})
	public String updatezyxx(){
		Zyxx zyxx=this.baseservice.find(Zyxx.class, zybh);
		String[] keys=new String[1];
		keys[0]="xymc";
		Object[] values=new Object[1];
		values[0]=xymc;
		List<Integer> xybh=this.baseservice.find(Integer.class, "Xuyan", "xybh", keys, values);//ͨ��xymc��values�����xybh������list��
		Xuyan xuyan=this.baseservice.find(Xuyan.class,xybh.get(0));
		zyxx.setXuyan(xuyan);
		zyxx.setZybh(zybh);
		zyxx.setZymc(zymc);

		this.baseservice.update(zyxx);	
		return SUCCESS;		
	}	
	
	
	//�����ѯ�Ƿ�Ϊ��,���ó��������� 
	@Action(value="/queryzy2",results={@Result(name="root",type="json")})
	public String queryzy2(){
		String hql = "select count(*) from Zyxx where "+queryType+" like '%"+queryWord+"%'";
		total1 = this.baseservice.getTotalSql(hql);
		HttpSession hs = ServletActionContext.getRequest().getSession();
		hs.setAttribute("zyxxtotal", total1);
		if(total1!=0){
			a=1;
		}else{
			a=0;
		}
		return "root";
	}
	/**
	 * @{queryzy.action}
	 * �÷������������ݹؼ��ֲ�ѯרҵ��Ϣ�ģ���ѯʱ���ҳ���������������queryType��queryWord
	 * queryType��Ҫ��ѯʱ�����ݵ����ͣ�queryWord�ǹؼ���
	 * ��ѯ���Ľ��ҳҪ�Է�ҳ����ʽ��ʾ
	*/
	@Action(value="/queryzyxx",results={@Result(name="root",type="json")})
	public String queryzyxx(){
		HttpSession hs = ServletActionContext.getRequest().getSession();
		total=(Integer)hs.getAttribute("zyxxtotal");
		listzyxx = this.baseservice.findByTypage(Zyxx.class, "Zyxx", queryType, queryWord, "order by zybh asc", page, rows_s);//���ؼ��ֲ�ѯ
		for(int i=0;i<listzyxx.size();i++){
        	Zyxxi element = new Zyxxi();
        	element.setInt1(listzyxx.get(i).getZybh());
        	element.setInt2(listzyxx.get(i).getXuyan().getXybh());       	
        	element.setStr1(listzyxx.get(i).getZymc());  
        	element.setStr2(listzyxx.get(i).getXuyan().getXymc());
        	rows.add(element);   //�����ݷŽ�rows��ʵ�ַ�ҳ��ѯ��ʾ    	
        }
		
		return "root";
	}
	
	//ҳ��У��
	@Action(value="/zyjy",results={@Result(name="success",type="json")})
	public String  zyjy(){
		zyjy = this.baseservice.find(String.class, "Zyxx", "zymc");
		zyjy1 = this.baseservice.find(Integer.class, "Zyxx", "zybh");
		if(zyjy.contains(zymc)){
		b = 0;
		}else{b=1;}
		if(zyjy1.contains(zybh)){
			a=0;
		}else{
			a=1;
		}
		return SUCCESS;
	}
	
	//�޸���ϢУ��
	@Action(value="/zyjy1",results={@Result(name="success",type="json")})
	public String  zyjy1(){
		zyjy = this.baseservice.find(String.class, "Zyxx", "zymc");
		zyxx=this.baseservice.find(Zyxx.class, zybh);
		for(int i=0;i<zyjy.size();i++){
			if(!zyjy.get(i).equals(zyxx.getZymc())){
				xx.add(zyjy.get(i));
			}
		}
		if(!xx.contains(zymc)){
			b=0;
		}else if(!zyjy.contains(zymc)){
			b=0;
		}else{
			b=1;
		}
		
		return SUCCESS;
	}
	
	/**
	 * @{zyxx}
	 * @return {/tables_zxl/datagrid_zyxx.jsp} {��ʾרҵ��Ϣ}
	 * @{��ѧ  רҵ��Ϣ����}
	 */	
//	@Action(value="/zyxx",results={@Result(name="success",location="/tables_zxl/datagrid_zyxx.jsp")})
//	public String zyxx(){
//		this.zyxxb=this.baseservice.find(Zyxx.class);//ͨ��Zyxx.class��ѯ�������ݲ�����zyxxb�С�
//		for(int i=0;i<zyxxb.size();i++){
//			if(!zyxxb1.contains(zyxxb.get(i).getXuyan().getXymc())){
//				zyxxb1.add(zyxxb.get(i).getXuyan().getXymc());
//			}
//		}
//		return SUCCESS;
//	}
}
