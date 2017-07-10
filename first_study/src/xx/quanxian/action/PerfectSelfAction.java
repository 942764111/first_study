/*
 *@(#)xx.quanxian.action
 *@PerfectSelfAction.java.java  
 *@����ʱ��:2012-4-6����11:16:25
 *@����:{guoqiang}
 *@Copyright 2009-2010 �ӱ�����ѧԺ��Ϣ��ѧ�빤��ѧԺ������ 
 */

package xx.quanxian.action;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import xx.collection.bean.Teacher;
import xx.quanxian.service.BaseService;

import com.opensymphony.xwork2.ActionSupport;

/**
 * @PerfectSelfAction <code>{������}</code>
 * @author  {gq}
 * @version {�汾,����ʱ�����}
 * @{��������:index.jspҳ��̽���û��Ƿ���Ҫ���Ƹ�����Ϣ} 
 */
@Controller
@Scope("prototype")
@Namespace("")
@ParentPackage("default-package")
@SuppressWarnings("serial")
public class PerfectSelfAction extends ActionSupport {
	
	@Resource(name="baseService")
	private BaseService baseService;
	private Long count;
	
	
	
	/**
	 * @return the count
	 */
	public Long getCount() {
		return count;
	}



	/**
	 * @param count the count to set
	 */
	public void setCount(Long count) {
		this.count = count;
	}
    /**
	 * 
	 * @{������:perfect()}
	 * @param {���������} {�������˵��}
	 * @return {count} {���ز���˵��:��Ǽ�¼�Ƿ�Ϊ0}
	 * @{̽���û��Ƿ���Ҫ���Ƹ�����Ϣ}
	 
	 */
	@Action(value="perfect",results={@Result(name="success",type="json")})
	public String perfect(){
		HttpSession hs = ServletActionContext.getRequest().getSession();
		String uid=(String)hs.getAttribute("uid");
		Integer roleid=(Integer)hs.getAttribute("role");
		String hql="select count(*) from ";
		if(roleid==108){
			//����ѧ��
			hql=hql+"Studentifno where userinfo.userId='"+uid+"'";
			//ѧ���Ļ��ǲ鿴ѧ���������޼�¼
			count=this.baseService.findHql(Long.class, hql).get(0);
		}else{
			//������ʦ�����Ա������ԱҲ����ʦ��
			hql="from Teacher where userinfo.userId='"+uid+"'";
			//��ʦ�Ļ��ǲ鿴��ʦ�������޽�ʦ��������Ϊ��ʦע�����Ѿ�����һ����¼��ѧ��ֻ����userinfo���м���һ����
			String teaname=this.baseService.findHql(Teacher.class, hql).get(0).getJsxm();
			if(StringUtils.isEmpty(teaname)){
				count = new Long(0);				
			}else{
				count =new Long(1);
			}
		}
		
		
		return SUCCESS;
	}
	
	

}
