/*
 *@(#)xx.spdh.action
 *@WjlxAction.java.java  
 *@����ʱ��:2011-8-6����09:27:23
 *@���ߣ�������
 *@Copyright 2009-2010 �ӱ�����ѧԺ��Ϣ��ѧ�빤��ѧԺ������ 
 */

package xx.spdh.action;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import xx.collection.bean.Wjlx;
import xx.quanxian.service.BaseService;

import com.googlecode.jsonplugin.annotations.JSON;
import com.opensymphony.xwork2.ActionSupport;

/**
 * @WjlxAction <code>{������}</code>
 * @author  {����}
 * @version {�汾,����ʱ�����}
 * @{��������:�ļ����͵���ɾ�Ĳ�} 
 */
@Controller
@Scope("prototype")
@ParentPackage("default-package")
@Namespace("")
@SuppressWarnings("serial")
public class WjlxAction extends ActionSupport {
	@Resource(name="baseService")
	private BaseService baseservice;
	
	private Wjlx wjlx;//����ʵ��  	
	private List<Wjlx> rows = new ArrayList<Wjlx>();//���󼯺�    
	private int page;//��ǰҳ
	private int rows_s;//ÿһҳ��ʾ������
	private int total;//��¼����
	
	private String lxm;
	private String ms;
	private String kzm;
	private List<Wjlx> wjlxlist;
	
	private String a;
	
	@JSON(serialize=false)
	public BaseService getBaseservice() {
		return baseservice;
	}
	public void setBaseservice(BaseService baseservice) {
		this.baseservice = baseservice;
	}
	
	@JSON(serialize=false)
	public Wjlx getWjlx() {
		return wjlx;
	}
	public void setWjlx(Wjlx wjlx) {
		this.wjlx = wjlx;
	}
	
	public List<Wjlx> getRows() {
		return rows;
	}
	public void setRows(List<Wjlx> rows) {
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
	
	public String getLxm() {
		return lxm;
	}
	@JSON(deserialize=true)
	public void setLxm(String lxm) {
		this.lxm = lxm;
	}
	
	public String getMs() {
		return ms;
	}
	public void setMs(String ms) {
		this.ms = ms;
	}
	
	public String getKzm() {
		return kzm;
	}
	public void setKzm(String kzm) {
		this.kzm = kzm;
	}
	
	@JSON(serialize=false)
	public List<Wjlx> getWjlxlist() {
		return wjlxlist;
	}
	public void setWjlxlist(List<Wjlx> wjlxlist) {
		this.wjlxlist = wjlxlist;
	}
	
	public String getA() {
		return a;
	}
	public void setA(String a) {
		this.a = a;
	}
	/**
	 * @{insertwjlx1.action}
	 * @{У���������Ƿ�Ϊ��}
	 * @return {/spdh/datagrid_wjlx.jsp} {��ʾ�����ļ�������Ϣҳ��}
	 * @exception {˵����ĳ�����,������ʲô�쳣}
	*/
	@Action(value="/insertwjlx1",results={@Result(name="success",type="json")})
	public String insertwjlx1() {
		List<String> lxmlist = this.baseservice.find(String.class, "Wjlx", "lxm");
		if(lxmlist.contains(lxm)) {
			a = "0";
		} else {
			a = "1";
		}
		
		return SUCCESS;
	}
	/**
	 * @{insertwjlx.action}
	 * @{���}
	 * @return {/spdh/datagrid_wjlx.jsp} {��ʾ�����ļ�������Ϣҳ��}
	 * @exception {˵����ĳ�����,������ʲô�쳣}
	*/
	@Action(value="/insertwjlx",results={@Result(name="success",type="json")})
	public String insertwjlx() {
		Wjlx wjlx = new Wjlx();
		wjlx.setLxm(lxm);
		wjlx.setMs(ms);
		wjlx.setKzm(kzm);
		this.baseservice.save(wjlx);
		return SUCCESS;
	}
	
	/**
	 * @{deletewjlx.action}
	 * @param {wjlxlist.action} {��ʾ�ļ�������Ϣ}
	 * @return {wjlxlist.action} {��ʾ�����ļ�������Ϣ} 
	 * @{ɾ���ļ�������Ϣ}
	 * @exception {˵����ĳ�����,������ʲô�쳣}
	*/
	@Action(value="deletewjlx",results={@Result(name="success",type="json")})
	public String deletewjlx(){
		wjlx=this.baseservice.find(Wjlx.class,lxm);//ͨ��lxmɾ��һ�����ݣ�lxmҪ���з����л���
		this.baseservice.delete(wjlx);// ɾ������
		return SUCCESS;
	}
	
	/**
	 * @{updatewjlx}
	 * @param {wjlxlist.action} {��ʾ�ļ�������Ϣ}
	 * @return {wjlxlist.action} {��ʾ�����ļ�������Ϣ}
	 * @{�޸��ļ�������Ϣ}
	 * @exception {˵����ĳ�����,������ʲô�쳣}
	 */
	@Action(value="/updatewjlx",results={@Result(name="success",type="json")})
	public String updatewjlx() {
		Wjlx wjlx = new Wjlx();
		wjlx.setLxm(lxm);
		wjlx.setMs(ms);
		wjlx.setKzm(kzm);
		this.baseservice.update(wjlx);
		return SUCCESS;
	}

	/**
	 * @{wjlxlist.action}
	 * @param {wjlxlist} {��ʾ�����ļ�������Ϣ}
	 * @return {/spdh/datagrid_wjlx.jsp} {��ʾ�����ļ�������Ϣҳ��}
	 * @{��ʾ�����ļ�������Ϣ}
	 * @exception {˵����ĳ�����,������ʲô�쳣}
	*/
	@Action(value="/wjlxlist",results={@Result(name="success",type="json")})
	public String wjlxlist() {
		rows = this.baseservice.findAll(Wjlx.class,"Wjlx", page, rows_s); //���������ݷ���rows������ʵ�ַ�ҳ��ѯ    
        total=this.baseservice.getTotal("Wjlx");  //��¼�����ļ�¼  
        return SUCCESS; 
	}
	
}
