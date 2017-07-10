/*
 *@(#)xx.testxg.action
 *@SjglAction.java.java  
 *@����ʱ��:2011-11-18����09:10:53
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

import xx.collection.bean.Sjnr;
import xx.quanxian.service.BaseService;

import com.googlecode.jsonplugin.annotations.JSON;
import com.opensymphony.xwork2.ActionSupport;

/**
 * @SjglAction <code>{������}</code>
 * @author  {������}
 * @version {�汾,����ʱ�����}
 * @{��������:ѧ����ʦ�鿴�Ծ�} 
 */
@Controller
@Scope("prototype")
@ParentPackage("default-package")
@Namespace("")
public class SjglAction extends ActionSupport {
	@Resource(name="baseService")
	private BaseService baseservice;
	
	
	private List<Sjnr> rows = new ArrayList<Sjnr>();//���󼯺�    
	private int page;//��ǰҳ
	private int rows_s;//ÿһҳ��ʾ������
	private int total=0;  
	
	
	@JSON(serialize=false)
	public BaseService getBaseservice() {
		return baseservice;
	}



	public void setBaseservice(BaseService baseservice) {
		this.baseservice = baseservice;
	}



	public List<Sjnr> getRows() {
		return rows;
	}



	public void setRows(List<Sjnr> rows) {
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



	public int getTotal() {
		return total;
	}



	public void setTotal(int total) {
		this.total = total;
	}



	/**
	 * @{sjlist.action}
	 * @param {rows} {��ʾ�����Ծ���Ϣ}
	 * @return {/testxg/sjgl.jsp} {��ʾ�����Ծ���Ϣҳ��}
	 * @{��ʾ�����Ծ���Ϣ}
	 * @exception {˵����ĳ�����,������ʲô�쳣}
	*/
	@Action(value="/sjlist",results={@Result(name="root",type="json")})
	public String sjlist(){		
		HttpSession session = ServletActionContext.getRequest().getSession();
		String userid = (String)session.getAttribute("uid");
		String hql = "select type from Userinfo where userId='"+userid+"'";
		List<String> type = this.baseservice.findHql(String.class, hql);
		if(type.get(0).equals("T")){
			 rows = this.baseservice.findAll(Sjnr.class,"Sjnr", page, rows_s); //���������ݷ���rows������ʵ�ַ�ҳ��ѯ    
		     total=this.baseservice.getTotal("Sjnr");  //��¼�����ļ�¼  
		}else{
			String hql1 = "select distinct sjnr.sjno from Xsdyjl where userinfo.userId='"+userid+"'";
			List<Integer> sjno = this.baseservice.findHql(Integer.class, hql1);
			for(int i=0;i<sjno.size();i++){
				String hql2 = "from Sjnr where sjno='"+sjno.get(i)+"'";
				List<Sjnr> sjnr = this.baseservice.findHql(Sjnr.class, hql2);
				rows.add(sjnr.get(0));
				total+=1;
			}
			
		}
			
       
        return "root";  
	}
}
