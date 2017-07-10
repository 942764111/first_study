/*
 *@(#)xx.xuexi.dzbj
 *@SaveAllPage.java.java  
 *@创建时间:2011-11-4下午07:55:25
 *@作者：guangge
 *@Copyright 2009-2010 河北北方学院信息科学与工程学院科研所 
 */

package xx.xuexi.dzbj.action;



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
import xx.collection.bean.DzbjId;
import xx.quanxian.service.BaseService;

import com.googlecode.jsonplugin.annotations.JSON;
import com.opensymphony.xwork2.ActionSupport;

/**
 * @SaveAllPage <code>{类名称}</code>
 * @author  {作者}
 * @version {版本,常用时间代替}
 * @{功能描述:} 
 */
@Controller
@Scope("prototype")
@Namespace("")
@ParentPackage("default-package")
public class Savefuzhu extends ActionSupport {
	
	
	
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


	


	


	
	
	private int currentPage;
	private int x3;
	private int tmbh;


	/**
	 * @return the tmbh
	 */
	public int getTmbh() {
		return tmbh;
	}


	/**
	 * @param tmbh the tmbh to set
	 */
	public void setTmbh(int tmbh) {
		this.tmbh = tmbh;
	}


	/**
	 * @return the x3
	 */
	public int getX3() {
		return x3;
	}


	/**
	 * @param x3 the x3 to set
	 */
	public void setX3(int x3) {
		this.x3 = x3;
	}


	/**
	 * @return the y3
	 */
	public int getY3() {
		return y3;
	}


	/**
	 * @param y3 the y3 to set
	 */
	public void setY3(int y3) {
		this.y3 = y3;
	}


	/**
	 * @return the neirong
	 */
	public String getNeirong() {
		return neirong;
	}


	/**
	 * @param neirong the neirong to set
	 */
	public void setNeirong(String neirong) {
		this.neirong = neirong;
	}



	private int y3;
	private String neirong;
	private String zlmc;
	
	/**
	 * @return the zlmc
	 */
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
	 * @return the currentPage
	 */
	public int getCurrentPage() {
		return currentPage;
	}


	/**
	 * @param currentPage the currentPage to set
	 */
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}


	@Action(value="/savefuzhu",results={@Result(name="success",type="json")})
	public String execute(){
		
		HttpSession hs = ServletActionContext.getRequest().getSession();
		String userid = (String)hs.getAttribute("uid");
		String classno=(String)hs.getAttribute("classno");
		
		DzbjId dzbjid=new DzbjId();
		dzbjid.setUserId(userid);
		dzbjid.setClassno(classno);
		dzbjid.setTmbh(tmbh);
		Dzbj dzbj=new Dzbj();
		dzbj.setId(dzbjid);
		dzbj.setZlid(zlmc);
		dzbj.setTmnr(neirong);
		dzbj.setBiaojilx("fuzhu");
		dzbj.setWeizhi(currentPage+"");
		dzbj.setZuobiaoX2(x3);
		dzbj.setZuobiaoY2(y3);
		dzbj.setXssxh(tmbh*20);
		
		this.baseService.save(dzbj);
		
		/*String[] gh=framePoint1.split(",");
		for(int q=0;q<gh.length;q++){
			String[] zz=gh[q].split("=");
			if(q%2==0){
				
				dzbj.setZuobiaoX(Integer.parseInt(zz[1]));
				
				
				System.out.println(zz[1]);
			}else if(q%2==1){
				dzbj.setZuobiaoY(Integer.parseInt(zz[1].substring(0, zz[1].length()-1)));
				System.out.println(zz[1].substring(0, zz[1].length()-1));
				
			}
			
			
			
		}
		String[] gh1=framePoint2.split(",");
		for(int q=0;q<gh1.length;q++){
			String[] zz=gh1[q].split("=");
			if(q%2==0){
				dzbj.setZuobiaoX1(Integer.parseInt(zz[1]));
				
				System.out.println(zz[1]);
			}else if(q%2==1){
				dzbj.setZuobiaoY(Integer.parseInt(zz[1].substring(0, zz[1].length()-1)));
				System.out.println(zz[1].substring(0, zz[1].length()-1));
				
			}
			
			
			
		}
		
		String[] gh2=markingPoint.split(",");
		for(int q=0;q<gh2.length;q++){
			String[] zz=gh2[q].split("=");
			if(q%2==0){
				
				
				System.out.println(zz[1]);
			}else if(q%2==1){
				
				System.out.println(zz[1].substring(0, zz[1].length()-1));
				
			}
			
			
			
		}*/
		
		
		
		
		
		return "success";
	}

	
}
