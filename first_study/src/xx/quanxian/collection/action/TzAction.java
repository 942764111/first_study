/*
 *@(#)xx.kgt.action
 *@JspAction.java.java  
 *@����ʱ��:2011-10-26����08:37:07
 *@���ߣ�Administrator
 *@Copyright 2009-2010 �ӱ�����ѧԺ��Ϣ��ѧ�빤��ѧԺ������ 
 */

package xx.quanxian.collection.action;


import javax.annotation.Resource;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import xx.quanxian.service.BaseService;

import com.googlecode.jsonplugin.annotations.JSON;
import com.opensymphony.xwork2.ActionSupport;

/**
 * @JspAction <code>{������}</code>
 * @author  {������}
 * @version {�汾,����ʱ�����}
 * @{��������:�����תҳ���action} 
 */

@Controller
@Scope("prototype")
@ParentPackage("default")
@Namespace("")
public class TzAction extends ActionSupport{
	
	
	@Resource(name="baseService")
	private BaseService baseservice;
	
	private String userId;
	
	@JSON(serialize=false)
	public BaseService getBaseservice() {
		return baseservice;
	}
	public void setBaseservice(BaseService baseservice) {
		this.baseservice = baseservice;
	}
	/**
	 * listSclt <code>listSclt</code>
	 * @author  ������
	 * @version 2011-8-21����11:25:21
	 * @ʵ���������� 
	 */
	@Action(value="/listSclt",results={@Result(name="success",location="/page/collection/sourcecollection.jsp")})
	public String listSclt(){
		return SUCCESS;
	}
	

	/**
	 * ������תҳ���
	 */
	@Action(value="/jxjhscym",results={@Result(name="success",location="/page/collection/jxjhshoucang.jsp")})
	public String jxjhscym(){
		return SUCCESS;
	}
	/**
	 * @Statistics <code>{list_zycx}</code>
	 * @author  {������}
	 * @version {2011-9-20����02:43:27}
	 * @{ʵ����ת����} 
	 */
	@Action(value="/zysearch",results={@Result(name="success",location="/page/resource/zysearch.jsp")})
	public String zysearch(){
		return SUCCESS;
	}
	/**
	 * @Statistics <code>{listy}</code>
	 * @author  {������}
	 * @version {2011-8-22����03:12:47}
	 * @{ʵ��ҳ�����ת} 
	 */
	@Action(value="/listy",results={@Result(name="success",location="/page/collection/listyhzdy.jsp")})
	public String listy(){
		return SUCCESS;
	}
	/**
	 * Rsjop <code>{Rsjop}</code>
	 * @author  {������}
	 * @version {2011-8-22����03:12:47}
	 * @{statisticsҳ����ת����} 
	 */
	@Action(value="/Rsjop",results={@Result(name="success",location="/page/resource/statistics.jsp")})
	public String Rsjop(){
		return SUCCESS;
	}
}
