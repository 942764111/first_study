/*
 *@(#)xx.spdh.action
 *@SpdhAction.java.java  
 *@����ʱ��:2011-10-26����09:43:34
 *@���ߣ�ZYK
 *@Copyright 2009-2010 �ӱ�����ѧԺ��Ϣ��ѧ�빤��ѧԺ������ 
 */

package xx.spdh.action;

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
import xx.collection.bean.Userinfo;
import xx.collection.bean.Wjlx;
import xx.collection.bean.Yhzdymc;
import xx.collection.bean.Zsd;
import xx.quanxian.service.BaseService;

import com.googlecode.jsonplugin.annotations.JSON;
import com.opensymphony.xwork2.ActionSupport;

/**
 * @SpdhAction <code>{������}</code>
 * @author  {����}
 * @version {�汾,����ʱ�����}
 * @{��������:} 
 */
@Controller
@Scope("prototype")
@Namespace("")
@ParentPackage("default")
@SuppressWarnings("serial")
public class SpdhAction extends ActionSupport {
	
	@Resource(name="baseService")
	private BaseService baseservice;
	
	private List<Dmtzl> dmtzllist;
	private List<Wjlx> wjlxlist;
	private List<Userinfo> userinfolist;
	private List<Zsd> listzsd;
	
	private List<Yhzdymc> listy = new ArrayList<Yhzdymc>();	
	private String userId;
	
	@JSON(serialize=false)
	public List<Yhzdymc> getListy() {
		return listy;
	}

	public void setListy(List<Yhzdymc> listy) {
		this.listy = listy;
	}

	@JSON(serialize=false)
	public List<Dmtzl> getDmtzllist() {
		return dmtzllist;
	}
	public void setDmtzllist(List<Dmtzl> dmtzllist) {
		this.dmtzllist = dmtzllist;
	}

	@JSON(serialize=false)
	public List<Wjlx> getWjlxlist() {
		return wjlxlist;
	}
	public void setWjlxlist(List<Wjlx> wjlxlist) {
		this.wjlxlist = wjlxlist;
	}

	@JSON(serialize=false)
	public List<Userinfo> getUserinfolist() {
		return userinfolist;
	}
	public void setUserinfolist(List<Userinfo> userinfolist) {
		this.userinfolist = userinfolist;
	}
	
	@JSON(serialize=false)
	public List<Zsd> getListzsd() {
		return listzsd;
	}
	public void setListzsd(List<Zsd> listzsd) {
		this.listzsd = listzsd;
	}
	/**
	 * @{scwj2}
	 * @return {/spdh/datagrid_scwj.jsp} {��ʾ�ز���Ϣ}
	 * @{��ѧ  �ز��ļ�����}
	 */	
	@Action(value="/scwj1",results={@Result(name="success",location="/spdh/scwj.jsp")})
	public String scwj1(){
		this.dmtzllist=this.baseservice.find(Dmtzl.class);
		this.wjlxlist=this.baseservice.find(Wjlx.class);//ͨ��Dmtzl.class��ѯ�������ݲ�����listdmtzl�С�
		this.userinfolist=this.baseservice.find(Userinfo.class);
		return SUCCESS;
	}
	
	/**
	 * @{dmtzl}
	 * @return {/spdh/datagrid_dmtzl.jsp} {��ʾ��ý��������Ϣ}
	 * @{��ѧ  ��ý������}
	 * ��main.jsp�е����ý������ʱ��ת��datagrid_wjlx.jsp
	 */	
	@Action(value="/dmtzl1",results={@Result(name="success",location="/spdh/dmtzl.jsp")})
	public String dmtzl1() {
		this.wjlxlist=this.baseservice.find(Wjlx.class);//ͨ��Dmtzl.class��ѯ�������ݲ�����listdmtzl�С�
		this.userinfolist=this.baseservice.find(Userinfo.class);
		HttpSession session1 = ServletActionContext.getRequest().getSession();
		userId = (String) session1.getAttribute("userid");
		listy = this.baseservice.findHql(Yhzdymc.class,"from Yhzdymc y where y.userinfo.userId='"+userId+"'");
		return SUCCESS;
	}
	
	/**
	 * @{wjlx}
	 * @return {/spdh/datagrid_wjlx.jsp} {��ʾ�ļ�������Ϣ}
	 * @{��ѧ  �ļ�����}
	 * ��main.jsp�е���ļ�����ʱ��ת��datagrid_wjlx.jsp
	 */	
	@Action(value="/wjlx1",results={@Result(name="success",location="/spdh/wjlx.jsp")})
	public String wjlx1() {
		//this.wjlxlist=this.baseservice.find(Wjlx.class);//ͨ��Wjlx.class�鵽�����ļ�������Ϣ
		return SUCCESS;
	}
	
	/**
	 * @{zxsplb1}
	 * @return {/spdh/zxsplb.jsp} {��ʾ�ز���Ϣ}
	 * @{��ѧ  �ز��ļ�����}
	 */	
	@Action(value="/zxsplb1",results={@Result(name="success",location="/spdh/zxsplb.jsp")})
	public String zxsplb1(){
		return SUCCESS;
	}
	
	/**
	 * @zlzsddy{������}
	 * @param {���������} {�������˵��}
	 * @return spdh/zlzsddy.jsp{���ز�����} {���ز���˵��}
	 * @����jsp{�����Ĺ���/��������}
	 * @exception {˵����ĳ�����,������ʲô�쳣}
	*/
	@Action(value="/zlzsddy1",results={@Result(name="success",location="/spdh/zlzsddy.jsp")})
	public String zlzsddy11(){
		this.dmtzllist=this.baseservice.find(Dmtzl.class);
		this.listzsd = this.baseservice.find(Zsd.class);
		return SUCCESS;
	}
	
	@Action(value="/yhmlgl",results={@Result(name="success",location="/spdh/contentManager.jsp")})
	public String yhmlgl(){
		return SUCCESS;
	}
	
	/**
	 * ��action���������˳���¼�����رղ�����Ƶҳ��ʱ�ᴥ����action
	 * ���û��ٴε�¼�ۿ�����Ƶʱ���ܹ���ʾ�ϴδ򿪵�ʱ��
	*/
	@Action(value="/jxksp",results={@Result(name="success",location="/spdh/zljl.jsp")})
	public String jxksp(){
		return SUCCESS;
	}
	
}
