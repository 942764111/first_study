/*
 *@(#)xx.quanxian.collection.action
 *@Statistics.java.java  
 *@����ʱ��:2011-8-22����03:12:47
 *@���ߣ�xupengfei
 *@Copyright 2009-2010 �ӱ�����ѧԺ��Ϣ��ѧ�빤��ѧԺ������ 
 */

package xx.quanxian.collection.action;


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

import xx.collection.bean.Zysc;
import xx.quanxian.service.BaseService;

import com.googlecode.jsonplugin.annotations.JSON;
import com.opensymphony.xwork2.ActionSupport;
@Controller
@Scope("prototype")
@Namespace("")
@ParentPackage("default-package")
@SuppressWarnings("serial")
public class ZyscAction extends ActionSupport {
	
	@Resource(name="baseService")
	private BaseService service;
	private List<ZyscDto> rows = new ArrayList<ZyscDto>();
	private String userId;
	private int total;//��������total
	//datagrid����
	private int page;//��ǰҳ��
	private int rows_s;//ÿҳ��ʾrow_s������
	private String queryw;
	private int a;
	@JSON(serialize=false)
	public String getQueryw() {
		return queryw;
	}
	public void setQueryw(String queryw) {
		this.queryw = queryw;
	}
	public int getA() {
		return a;
	}
	public void setA(int a) {
		this.a = a;
	}
	public List<ZyscDto> getRows() {
		return rows;
	}

	public void setRows(List<ZyscDto> rows) {
		this.rows = rows;
	}
	@JSON(serialize=false)
	public BaseService getService() {
		return service;
	}

	public void setService(BaseService service) {
		this.service = service;
	}
	
	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
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

	public void setRows_s(int rowsS) {
		rows_s = rowsS;
	}

	/**
	 * @{xuanze.action}
	 * �÷���������ʾ��ѧ�ƻ���Դ�ղص�ҳ���
	 */
	@Action(value="/jxjhsc",results={@Result(name="success",type="json")})
	public String jxjhsc(){
		HttpSession session1 = ServletActionContext.getRequest().getSession();
		userId = (String) session1.getAttribute("userid");
		List<Zysc> splist = new ArrayList<Zysc>();
		splist = this.service.findSql(Zysc.class, "from Zysc z where z.zylx=15 and z.id.userId='"+userId+"'",page,rows_s);
		if(splist.size()>0){
			for(Zysc zz:splist){
				String ss[] = new String[5];
				ss = zz.getZyms().split("`");
				ZyscDto zdto = new ZyscDto();
				zdto.setZybh(zz.getZybh());
				zdto.setSskcmc(zz.getSskcmc());
				zdto.setSszmc(ss[0]);
				zdto.setSskcxh(Integer.parseInt(ss[1]));
				zdto.setScsj(ss[3]);
				zdto.setZymc(ss[2]);
				zdto.setZylx(15);
				zdto.setZyms(ss[4]);
				rows.add(zdto);
			}
		}
		String hql = "select count(*) from Zysc z where z.zylx=15 and z.id.userId='"+userId+"'";
		total = this.service.getTotalSql(hql);
		return SUCCESS;
	}
	
	
	//�����ѯ�Ƿ�Ϊ��,���ó���������
	@Action(value="/searchjxjhsc",results={@Result(name="root",type="json")})
	public String searchjxjhsc(){
		HttpSession session1 = ServletActionContext.getRequest().getSession();
		userId = (String) session1.getAttribute("userid");
		int cxsl = 0;
		String hql = "select count(*) from Zysc z where z.zylx=15 and z.id.userId='"+userId+"' and z.zybh="+queryw;
		cxsl = this.service.getTotalSql(hql);
		if(cxsl!=0){
			a=1;
		}else{
			a=0;
		}
		return "root";
	}
	
	//��ý�����ϲ�ѯ
	@Action(value="/searchjxjhsc1",results={@Result(name="success",type="json")})
	public String searchjxjhsc1(){
		List<Zysc> dmtzllist = new ArrayList<Zysc>();
		dmtzllist = this.service.findHql(Zysc.class, "from Zysc z where z.zybh="+queryw);
		total = 1;//��¼����
		for(int i=0;i<dmtzllist.size();i++){
			Zysc zz = new Zysc();
			String ss[] = new String[5];
			ss = zz.getZyms().split("`");
			ZyscDto zdto = new ZyscDto();
			zdto.setZybh(zz.getZybh());
			zdto.setZyms(ss[4]);
			zdto.setSskcmc(zz.getSskcmc());
			zdto.setSszmc(ss[0]);
			zdto.setSskcxh(Integer.parseInt(ss[1]));
			zdto.setScsj(ss[3]);
			zdto.setZymc(ss[2]);
			rows.add(zdto);   //�����ݷŽ�rows��ʵ�ַ�ҳ��ѯ��ʾ
		}
		return SUCCESS;
	}
	
	
}