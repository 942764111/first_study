/*
 *@(#)xx.testxg.action
 *@ChangePdAction.java.java  
 *@����ʱ��:2011-11-30����08:12:03
 *@���ߣ�Administrator
 *@Copyright 2009-2010 �ӱ�����ѧԺ��Ϣ��ѧ�빤��ѧԺ������ 
 */

package xx.testxg.action;

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

import xx.collection.bean.Pd_sim;
import xx.quanxian.service.BaseService;

import com.googlecode.jsonplugin.annotations.JSON;
import com.opensymphony.xwork2.ActionSupport;

/**
 * @ChangePdAction <code>{������}</code>
 * @author  {������}
 * @version {�汾,����ʱ�����}
 * @{��������:} 
 */
@Controller
@Scope("prototype")
@ParentPackage("default-package")
@Namespace("")
public class ChangePdAction extends ActionSupport {
	
	
	@Resource(name = "baseService")
	private BaseService baseservice;
	private int ghth;
	private int page;//��ǰҳ
	private int rows_s;//ÿҳ��ʾ������
	private int total;//��¼��������
	private List<Pd_sim> rows =new ArrayList<Pd_sim>();
	private String queryType;	//��ѯ����ʱ�Ĳ�ѯ��������Ҫ���ݲ�ѯ������
	private String queryWord;	//��ѯ����ʱ�Ĳ�ѯ��������Ҫ���ݲ�ѯ�Ĺؼ���
	private int a;
	private List<Pd_sim> pdlist = new ArrayList<Pd_sim>();
	
	@JSON(serialize=false)
	public BaseService getBaseservice() {
		return baseservice;
	}


	public void setBaseservice(BaseService baseservice) {
		this.baseservice = baseservice;
	}


	public int getGhth() {
		return ghth;
	}


	public void setGhth(int ghth) {
		this.ghth = ghth;
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


	public int getTotal() {
		return total;
	}


	public void setTotal(int total) {
		this.total = total;
	}


	public List<Pd_sim> getRows() {
		return rows;
	}


	public void setRows(List<Pd_sim> rows) {
		this.rows = rows;
	}


	public List<Pd_sim> getPdlist() {
		return pdlist;
	}


	public void setPdlist(List<Pd_sim> pdlist) {
		this.pdlist = pdlist;
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


	public int getA() {
		return a;
	}


	public void setA(int a) {
		this.a = a;
	}


	//�����������������ʱ�ı��Ծ�����ж���ʱ��,
	@Action(value="/changepd",results={@Result(name="root",type="json")})
	public String changepd(){
		HttpSession hs = ServletActionContext.getRequest().getSession();
		int zbh1=(Integer)hs.getAttribute("ghzno1");
		int zbh2=(Integer)hs.getAttribute("ghzno2");
		rows=this.baseservice.findSql(Pd_sim.class,"from Pd_sim where zbh>='"+zbh1+"' and zbh<='"+zbh2+"'", page, rows_s);
		total = this.baseservice.getTotalSql("select count(*) from Pd_sim where zbh>='"+zbh1+"' and zbh<='"+zbh2+"'");
		return "root";
	}
	
	
	
	//�����ѯ�Ƿ�Ϊ��,���ó���������
	@Action(value="/ghquerypd1",results={@Result(name="root",type="json")})
	public String ghquerypd1(){
		HttpSession hs = ServletActionContext.getRequest().getSession();
		int zbh1=(Integer)hs.getAttribute("ghzno1");
		int zbh2=(Integer)hs.getAttribute("ghzno2");
		String hql = "select count(*) from Pd_sim where "+queryType+" like '%"+queryWord+"%' and zbh>='"+zbh1+"' and zbh<='"+zbh2+"'";
		int to1 = this.baseservice.getTotalSql(hql);
		hs.setAttribute("tempto", to1);
		if(to1!=0){
			a=1;
		}else{
			a=0;
		}
		return "root";
	}
	
	
	/**
	 * @{ghquerypd.action}
	 * �÷������������ݹؼ��ֲ�ѯ�ж���ģ���ѯʱ���ҳ���������������queryType��queryWord
	 * queryType��Ҫ��ѯʱ�����ݵ����ͣ�queryWord�ǹؼ���
	 * ��ѯ���Ľ��ҳҪ�Է�ҳ����ʽ��ʾ
	*/
	@Action(value="/ghquerypd",results={@Result(name="root",type="json")})
	public String ghquerypd(){
		HttpSession hs = ServletActionContext.getRequest().getSession();
		total=(Integer)hs.getAttribute("tempto");
		int zbh1=(Integer)hs.getAttribute("ghzno1");
		int zbh2=(Integer)hs.getAttribute("ghzno2");
		rows=this.baseservice.findSql(Pd_sim.class,"from Pd_sim where "+queryType+" like '%"+queryWord+"%' and zbh>='"+zbh1+"' and zbh<='"+zbh2+"'", page, rows_s);
//		for(int i=0;i<pdlist.size();i++){
//			Pd_sim element = new Pd_sim();
//        	element.setTg(pdlist.get(i).getTg());
//        	element.setTh(pdlist.get(i).getTh());
//        	element.setDa(pdlist.get(i).getDa());
//        	rows.add(element);   //�����ݷŽ�rows��ʵ�ַ�ҳ��ѯ��ʾ    	
//        	
//        }
		
		return "root";
	}
}
