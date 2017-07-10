/*
 *@(#)xx.quanxian.action
 *@PerfectSelfAction.java.java  
 *@创建时间:2012-4-6上午11:16:25
 *@作者:{guoqiang}
 *@Copyright 2009-2010 河北北方学院信息科学与工程学院科研所 
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
 * @PerfectSelfAction <code>{类名称}</code>
 * @author  {gq}
 * @version {版本,常用时间代替}
 * @{功能描述:index.jsp页面探测用户是否需要完善个人信息} 
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
	 * @{方法名:perfect()}
	 * @param {引入参数名} {引入参数说明}
	 * @return {count} {返回参数说明:标记记录是否为0}
	 * @{探测用户是否需要完善个人信息}
	 
	 */
	@Action(value="perfect",results={@Result(name="success",type="json")})
	public String perfect(){
		HttpSession hs = ServletActionContext.getRequest().getSession();
		String uid=(String)hs.getAttribute("uid");
		Integer roleid=(Integer)hs.getAttribute("role");
		String hql="select count(*) from ";
		if(roleid==108){
			//若是学生
			hql=hql+"Studentifno where userinfo.userId='"+uid+"'";
			//学生的话是查看学生表中有无记录
			count=this.baseService.findHql(Long.class, hql).get(0);
		}else{
			//若是老师或管理员（管理员也是老师）
			hql="from Teacher where userinfo.userId='"+uid+"'";
			//老师的话是查看老师表中有无教师姓名，因为老师注册是已经填了一条记录而学生只是向userinfo表中加入一条。
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
