/*
 *@(#)xx.xuexi.dzbj.action
 *@Get.java.java  
 *@创建时间:2011-11-13下午09:45:10
 *@作者：guangge
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
 * @Get <code>{类名称}</code>
 * @author  {作者}
 * @version {版本,常用时间代替}
 * @{功能描述:} 
 */
@Controller
@Scope("prototype")
@Namespace("")
@ParentPackage("default-package")
public class Upnote extends ActionSupport {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Resource(name="baseService")
	private BaseService baseService;
	
	


	/**
	 * @return the baseService
	 */
	@JSON(serialize=false)
	public BaseService getBaseService() {
		return baseService;
	}


	/**
	 * @param baseService the baseService to set
	 */
	public void setBaseService(BaseService baseService) {
		this.baseService = baseService;
	}
	private String note;
	private String tmbh;
	
	private String zlmc;
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


	/**
	 * @return the tmbh3
	 */
	

		/**
	 * @return the note
	 */
	public String getNote() {
		return note;
	}


	public String getTmbh() {
			return tmbh;
		}


		public void setTmbh(String tmbh) {
			this.tmbh = tmbh;
		}


	/**
	 * @param note the note to set
	 */
	public void setNote(String note) {
		this.note = note;
	}


		@Action(value="/savenote",results={@Result(name="success",type="json")})
		public String Updata(){
			
			HttpSession hs = ServletActionContext.getRequest().getSession();
			String userid = (String)hs.getAttribute("uid");


			
			Dzbj dzbj = new Dzbj();
			String hql="";
			if(classno.equals("")){

				
				hql = "from Dzbj d where d.id.userId='"+userid+"' and d.id.tmbh='"+tmbh+"'and d.zlid='"+zlmc+"'";
			}else{
				hql = "from Dzbj d where d.id.userId='"+userid+"' and d.id.tmbh='"+tmbh+"'and d.id.classno='"+classno+"'";
				
			}
			
			System.out.println("Sql:"+hql);
			List l= (List) this.baseService.findHql(Dzbj.class, hql);
			System.out.println("l:"+l);
			dzbj =(Dzbj) l.get(0);
			dzbj.setTmnr(note);
			this.baseService.update(dzbj);
			
			
			
			
			//System.out.println(d.getZuobiaoX()+"'"+d.getZuobiaoX1()+"'");
			return "success";
			
		}
		@Action(value="/savenote1",results={@Result(name="success",type="json")})
		public String Updata1(){
			
			HttpSession hs = ServletActionContext.getRequest().getSession();
			String userid = (String)hs.getAttribute("uid");


			
			Dzbj dzbj = new Dzbj();
			String hql="";
			if(classno.equals("")){

				
				hql = "from Dzbj d where d.id.userId='"+userid+"' and d.id.tmbh='"+tmbh+"'and d.zlid='"+zlmc+"'";
			}else{
				hql = "from Dzbj d where d.id.userId='"+userid+"' and d.id.tmbh='"+tmbh+"'and d.zlid='"+classno+"'";
				
			}
			
			List l= (List) this.baseService.findHql(Dzbj.class, hql);
			dzbj =(Dzbj) l.get(0);
			dzbj.setTmnr(note);
			this.baseService.update(dzbj);
			
			
			
			
			//System.out.println(d.getZuobiaoX()+"'"+d.getZuobiaoX1()+"'");
			return "success";
			
		}
		
}
