/*
  
 *@创建时间:2011-11-12下午02:31:57
 *@作者：ZYK
 *@Copyright 2009-2010 河北北方学院信息科学与工程学院科研所 
 */

package xx.xuexi.dzbj.action;



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

import xx.collection.bean.Dzbj;
import xx.quanxian.service.BaseService;


import com.googlecode.jsonplugin.annotations.JSON;
import com.opensymphony.xwork2.ActionSupport;

/**
 * @BiJiAction <code>{类名称}</code>
 * @author  {作者}
 * @version {版本,常用时间代替}
 * @{功能描述:} 
 */
@Controller
@Scope("prototype")
@Namespace("")
@ParentPackage("default-package")
@SuppressWarnings("serial")
public class DeleteBJ extends ActionSupport {

	@Resource(name="baseService")
	private BaseService baseservice;
	
	

	/**
	 * @return the baseservice
	 */
	@JSON(serialize=false)
	public BaseService getBaseservice() {
		return baseservice;
	}

	/**
	 * @param baseservice the baseservice to set
	 */
	public void setBaseservice(BaseService baseservice) {
		this.baseservice = baseservice;
	}

	private String zlmc;

	

	/**
	 * @return the zlmc
	 */
	@JSON(serialize=false)
	public String getZlmc() {
		return zlmc;
	}

	/**
	 * @param zlmc the zlmc to set
	 */
	public void setZlmc(String zlmc) {
		this.zlmc = zlmc;
	}

	private int tmbh11;
	private String classno="";
	
	/**
	 * @return the classno
	 */
	public String getClassno() {
		return classno;
	}

	/**
	 * @param classno the classno to set
	 */
	public void setClassno(String classno) {
		this.classno = classno;
	}

	/**
	 * @return the tmbh11
	 */
	public int getTmbh11() {
		return tmbh11;
	}

	/**
	 * @param tmbh11 the tmbh11 to set
	 */
	public void setTmbh11(int tmbh11) {
		this.tmbh11 = tmbh11;
	}

	@Action(value="/delete11", results={@Result(name="success", type="json")})
	public String biji() {
		
		
		HttpSession hs = ServletActionContext.getRequest().getSession();
		String userid = (String)hs.getAttribute("uid");


		;
		
		Dzbj dzbj = new Dzbj();
		String hql ="";
		//System.out.println(classno);
		if(classno.equals("")){
			
			hql = "from Dzbj d where d.id.userId='"+userid+"' and d.id.tmbh='"+tmbh11+"'and d.zlid='"+zlmc+"'";
		}else{
			hql = "from Dzbj d where d.id.userId='"+userid+"' and d.id.tmbh='"+tmbh11+"'and d.id.classno='"+classno+"'";
			
		}
		
		
		//String hql = "from Dzbj d where d.id.userId='"+userid+"' and d.id.tmbh='"+tmbh11+"'and d.zlid='"+zlid+"'";
		List<Dzbj> l=this.baseservice.findHql(Dzbj.class, hql);
		dzbj=l.get(0);
		this.baseservice.delete(dzbj);
		return SUCCESS;
	}
	
	@Action(value="/delete111", results={@Result(name="success", type="json")})
	public String biji1() {
		
		
		HttpSession hs = ServletActionContext.getRequest().getSession();
		String userid = (String)hs.getAttribute("uid");


		;
		
		Dzbj dzbj = new Dzbj();
		String hql ="";
		//System.out.println(classno);
		if(classno.equals("")){
			
			hql = "from Dzbj d where d.id.userId='"+userid+"' and d.id.tmbh='"+tmbh11+"'and d.zlid='"+zlmc+"'";
		}else{
			hql = "from Dzbj d where d.id.userId='"+userid+"' and d.id.tmbh='"+tmbh11+"'and d.zlid='"+classno+"'";
			
		}
		
		
		//String hql = "from Dzbj d where d.id.userId='"+userid+"' and d.id.tmbh='"+tmbh11+"'and d.zlid='"+zlid+"'";
		List<Dzbj> l=this.baseservice.findHql(Dzbj.class, hql);
		dzbj=l.get(0);
		this.baseservice.delete(dzbj);
		return SUCCESS;
	}
	
}
