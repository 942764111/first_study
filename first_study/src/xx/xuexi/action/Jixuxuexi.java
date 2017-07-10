
	/**
	 * �ļ�����Jixuxuexi.java
	 *
	 * �汾��Ϣ��
	 * ���ڣ�2011-8-14
	 * ���ߣ�tlq
	 * Copyright �ӱ�����ѧԺ��Ϣ��ѧ�빤��ѧԺ������ Corporation 2011 
	 * ��Ȩ����
	 *
	 */
	
package xx.xuexi.action;

import java.text.SimpleDateFormat;
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

import xx.collection.bean.Dmtzl;
import xx.collection.bean.Jxjh;
import xx.collection.bean.Zljl;
import xx.quanxian.service.BaseService;

import com.googlecode.jsonplugin.annotations.JSON;
import com.opensymphony.xwork2.ActionSupport;


	/**
 * �����������ǣ���Ҫ�Ǵ�������ϵļ���ѧϰ��
 * @author: tlq
 * @version: 2011-8-14 ����06:45:45 
 */
@Controller
@Scope("prototype")
@Namespace("")
@ParentPackage("default-package")
public class Jixuxuexi extends ActionSupport {
	
	List<Zljl> zljl = new ArrayList<Zljl>();
	List<Zljl_z> rows = new ArrayList<Zljl_z>();
	
	@Resource(name="baseService")
	private BaseService baseService;
	private int page;								//��ҳʱ����ǰ��ҳ������ǰ̨����
	private int rows_s;								//��ҳʱ����ǰ��ҳ�����������ǰ̨����
	private int total;								//��ѯ����¼��������
	private List<String> jxjh = new ArrayList<String>();
	private String jhmc;
	private String queryWord;						//��ѯ����ʱ�Ĳ�ѯ��������Ҫ���ݲ�ѯ�Ĺؼ���
	
	@JSON(serialize=false)
	public String getQueryWord() {
		return queryWord;
	}
	@JSON(deserialize=true)
	public void setQueryWord(String queryWord) {
		this.queryWord = queryWord;
	}
	public String getJhmc() {
		return jhmc;
	}
	public void setJhmc(String jhmc) {
		this.jhmc = jhmc;
	}
	public List<String> getJxjh() {
		return jxjh;
	}
	public void setJxjh(List<String> jxjh) {
		this.jxjh = jxjh;
	}
	public int getPage() {
		return page;
	}
	@JSON(deserialize=true)
	public void setPage(int page) {
		this.page = page;
	}
	public int getRows_s() {
		return rows_s;
	}
	@JSON(deserialize=true)
	public void setRows_s(int rows_s) {
		this.rows_s = rows_s;
	}
	public int getTotal() {
		return total;
	}
	@JSON(deserialize=true)
	public void setTotal(int total) {
		this.total = total;
	}
	
	@JSON(serialize=false)
	public List<Zljl> getZljl() {
		return zljl;
	}

	public void setZljl(List<Zljl> zljl) {
		this.zljl = zljl;
	}

	
	@JSON(deserialize=true)
	public List<Zljl_z> getRows() {
		return rows;
	}
	public void setRows(List<Zljl_z> rows) {
		this.rows = rows;
	}
	
	/**
	 * @{JiXuXueXi.action}
	 * ��ת����
	 * ��ת������ѧϰ��ҳ��
	 * 
	*/
	@Action(value="JiXuXueXi",results={@Result(name="root",location="/page/xuexi/Zljl.jsp")})
	public String Zljl(){
		return "root";
	}
	
	/**
	 * @{ZljlJson.action}
	 * ��ʾ����
	 * ���ݵ�ǰ��¼���û������û��ϴ�û����������г���
	 * 
	*/
	@Action(value="ZljlJson",results={@Result(name="root",type="json")})
	public String JiXuxuexi(){
		
		HttpSession hs = ServletActionContext.getRequest().getSession();
		String userid = (String)hs.getAttribute("userid");
		String[] keys = {"UserId"};
		Object[] values = {userid};
		zljl = this.baseService.findByOne(Zljl.class, "Zljl", keys, values, page, rows_s);
		total = this.baseService.getTotalPages("Zljl", keys, values);
		SimpleDateFormat sdf=new SimpleDateFormat("EE yyyy��MM�� dd��  hh:mm:ss ");

		for(Zljl z:zljl){
			Dmtzl d = new Dmtzl();
			d = this.baseService.find(Dmtzl.class, z.getDmtzl().getZlbh());
			Zljl_z zl = new Zljl_z();
			zl.setZlmc(d.getZlmc());
			String opentimes=sdf.format(z.getOpenTime());
			zl.setTime(opentimes);
			zl.setZlbh(z.getDmtzl().getZlbh());
			rows.add(zl);
		}
		
		return "root";
	}
	/**
	 * @{queryJxjh1.action}
	 * ��ѯ����
	 * �����еĽ�ѧ�ƻ���ѯ����������ǰ̨��������Զ�������
	 * 
	*/
	@Action(value="queryJxjh1",results={@Result(name="root",type="json")})
	public String queryJxjh(){
		this.jxjh=this.baseService.find(String.class, "Jxjh", "jhmc");
		return "root";
	}
	/**
	 * @{queryJxnr.action}
	 * ��ѯ����
	 * ���ݵ�ǰ��¼���û����û�ѡ��Ľ�ѧ�ƻ��������û��ϴ�û�������������ѡ��ѧ�ƻ��������г���
	 * 
	*/
	@Action(value="queryJxnr",results={@Result(name="root",type="json")})
	public String queryJxnr(){
		HttpSession hs = ServletActionContext.getRequest().getSession();
		String userid = (String)hs.getAttribute("userid");
		String[] keys1 = {"jhmc"};
		Object[] values1 = {queryWord};
		int jxjh_id = this.baseService.find(Jxjh.class, "Jxjh", keys1, values1).get(0).getId();
		String[] keys = {"UserId","jxjh_id"};
		Object[] values = {userid,jxjh_id};
		zljl = this.baseService.find(Zljl.class, "Zljl", keys, values);
		total = zljl.size();
		SimpleDateFormat sdf=new SimpleDateFormat("EE yyyy��MM�� dd��  hh:mm:ss ");
		
		for(Zljl z:zljl){
			Dmtzl d = new Dmtzl();
			d = this.baseService.find(Dmtzl.class, z.getDmtzl().getZlbh());
			Zljl_z zl = new Zljl_z();
			zl.setZlmc(d.getZlmc());
			String opentimes=sdf.format(z.getOpenTime());
			zl.setTime(opentimes);
			zl.setZlbh(z.getDmtzl().getZlbh());
			rows.add(zl);
		}
		return "root";
	}
}
