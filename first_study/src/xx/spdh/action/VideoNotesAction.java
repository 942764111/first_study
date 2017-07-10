/*
 *@(#)xx.spdh.action
 *@VideoNotesAction.java.java  
 *@创建时间:2011-11-22下午10:07:21
 *@作者：ZYK
 *@Copyright 2009-2010 河北北方学院信息科学与工程学院科研所 
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

import xx.collection.bean.Dzbj;
import xx.quanxian.service.BaseService;
import xx.spdh.bean.BiJi;
import xx.spdh.bean.DmtZl;

import com.googlecode.jsonplugin.annotations.JSON;
import com.opensymphony.xwork2.ActionSupport;

/**
 * @VideoNotesAction <code>{类名称}</code>
 * @author  {作者}
 * @version {版本,常用时间代替}
 * @{功能描述:} 
 */
@Controller
@Scope("prototype")
@Namespace("")
@ParentPackage("default-package")
@SuppressWarnings("serial")
public class VideoNotesAction extends ActionSupport {
	
	@Resource(name="baseService")
	private BaseService baseservice;
	
	private String zlid;
	private int num;
	
	/**
	 * @return the num
	 */
	public int getNum() {
		return num;
	}

	/**
	 * @param num the num to set
	 */
	public void setNum(int num) {
		this.num = num;
	}

	private List<BiJi> bijilist = new ArrayList<BiJi>();
	
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

	/**
	 * @return the zlid
	 */
	@JSON(serialize=false)
	public String getZlid() {
		return zlid;
	}

	/**
	 * @param zlid the zlid to set
	 */
	public void setZlid(String zlid) {
		this.zlid = zlid;
	}

	/**
	 * @return the bijilist
	 */
	public List<BiJi> getBijilist() {
		return bijilist;
	}

	/**
	 * @param bijilist the bijilist to set
	 */
	public void setBijilist(List<BiJi> bijilist) {
		this.bijilist = bijilist;
	}

	@Action(value="/videoNotes",results={@Result(name="success",type="json")})
	public String videoNotes() {
		
		HttpSession hs = ServletActionContext.getRequest().getSession();
		String userid = (String)hs.getAttribute("uid");
		
		String[] keys1=new String[2];
		keys1[0]="userId";	
		keys1[1] = "zlid";
		Object[] values1=new Object[2];
		values1[0]= userid;
		values1[1] = zlid;
		
		String hql = "select max(id.tmbh) from Dzbj where userId='" + userid + "'";
		List<Integer> tmbh =this.baseservice.findHql(Integer.class,hql);
		if(tmbh.get(0)!=null) {
			num=Integer.parseInt(tmbh.get(0)+"");
			List<Dzbj> dzbjlist = this.baseservice.find(Dzbj.class, "Dzbj", keys1, values1);
			for(int i=0;i<dzbjlist.size();i++) {
				BiJi biji = new BiJi();
				biji.setXxsxh(dzbjlist.get(i).getXssxh());
				biji.setTmnr(dzbjlist.get(i).getTmnr());
				biji.setPath(dzbjlist.get(i).getPath());
				biji.setWeizhi(dzbjlist.get(i).getWeizhi());
				biji.setClassno(dzbjlist.get(i).getId().getClassno());
				biji.setTmbh(dzbjlist.get(i).getId().getTmbh());
				biji.setNum(num);
				bijilist.add(biji);
			}
		} else {
			
		}
		
		return SUCCESS;
	}

}
