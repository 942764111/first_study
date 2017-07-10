/*
 *@(#)xx.spdh.action
 *@ZsdlbAction.java.java  
 *@创建时间:2011-9-10上午10:27:24
 *@作者：朱永科
 *@Copyright 2009-2010 河北北方学院信息科学与工程学院科研所 
 */

package xx.spdh.action;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.annotation.Resource;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import xx.collection.bean.Zlzsddy;
import xx.quanxian.service.BaseService;
import xx.spdh.bean.MySort;
import xx.spdh.bean.Spxx;

import com.googlecode.jsonplugin.annotations.JSON;
import com.opensymphony.xwork2.ActionSupport;

/**
 * @ZsdlbAction <code>{类名称}</code>
 * @author  {作者}
 * @version {版本,常用时间代替}
 * @{功能描述:} 
 */

@Controller
@Scope("prototype")
@ParentPackage("default-package")
@Namespace("")
@SuppressWarnings("serial")
public class ZsdlbAction extends ActionSupport {
	
	@Resource
	private BaseService baseservie;
	
	private List<String> zsdmclist = new ArrayList<String>();
	private List<String> weizhilist = new ArrayList<String>();
	private List<Spxx> spxxlist = new ArrayList<Spxx>();
	
	private String zlid;
	
	@JSON(serialize = false)
	public BaseService getBaseservie() {
		return baseservie;
	}

	public void setBaseservie(BaseService baseservie) {
		this.baseservie = baseservie;
	}

	@JSON(serialize = false)
	public List<String> getZsdmclist() {
		return zsdmclist;
	}

	public void setZsdmclist(List<String> zsdmclist) {
		this.zsdmclist = zsdmclist;
	}
	
	@JSON(serialize = false)
	public List<String> getWeizhilist() {
		return weizhilist;
	}

	public void setWeizhilist(List<String> weizhilist) {
		this.weizhilist = weizhilist;
	}
	
	public List<Spxx> getSpxxlist() {
		return spxxlist;
	}

	public void setSpxxlist(List<Spxx> spxxlist) {
		this.spxxlist = spxxlist;
	}

	/**
	 * @return the zlid
	 */
	@JSON(serialize = false)
	public String getZlid() {
		return zlid;
	}

	/**
	 * @param zlid the zlid to set
	 */
	public void setZlid(String zlid) {
		this.zlid = zlid;
	}

	@Action(value="/spxx",results={@Result(name="success",type="json")})
	public String spxx() {
		
		
		String[] keys1 = new String[1];
		keys1[0] = "zlbh";
		Object[] values1 = new Object[1];
		values1[0] = zlid;
		List<Zlzsddy> zlzsddylist = this.baseservie.find(Zlzsddy.class, "Zlzsddy", keys1, values1);
		
		for(int i=0;i<zlzsddylist.size();i++) {
			
			int zsdbh = zlzsddylist.get(i).getId().getZsdbh();
			int zbh = zlzsddylist.get(i).getId().getZbh();
			int c_id = zlzsddylist.get(i).getId().getCId();
			
			String[] keys2 = new String[3];
			keys2[0] = "zsdbh";
			keys2[1] = "zbh";
			keys2[2] = "c_id";
			Object[] values2 = new Object[3];
			values2[0] = zsdbh;
			values2[1] = zbh;
			values2[2] = c_id;
			
			List<String> zsdmc = this.baseservie.find(String.class, "Zsd", "zsdmc", keys2, values2);
			
			String[] keys3 = new String[3];
			keys3[0] = "zsdbh";
			keys3[1] = "zbh";
			keys3[2] = "c_id";
			Object[] values3 = new Object[3];
			values3[0] = zsdbh;
			values3[1] = zbh;
			values3[2] = c_id;
			
			List<String> weizhi = this.baseservie.find(String.class, "Zlzsddy", "weizhi", keys3, values3);
			
			int zlid1 = Integer.valueOf(zlid).intValue();
			Spxx element = new Spxx();
			element.setStr1(zsdmc.get(0));
			element.setStr2(weizhi.get(0));
			element.setZlid(zlid1);
			spxxlist.add(element);
			Comparator com = new MySort();
			Collections.sort(spxxlist, com);
		}
		
		return SUCCESS;
	}
	

}
