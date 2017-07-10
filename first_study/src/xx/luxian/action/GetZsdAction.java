/*
 *@(#)xx.spdh.action
 *@UploadAction.java.java  
 *@����ʱ��:2011-11-12����08:43:29
 *@���ߣ�ZYK
 *@Copyright 2009-2010 �ӱ�����ѧԺ��Ϣ��ѧ�빤��ѧԺ������ 
 */

package xx.luxian.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import java.util.List;

import javax.annotation.Resource;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import xx.collection.bean.Collect;
import xx.collection.bean.CourseChapter;
import xx.collection.bean.Jie;
import xx.collection.bean.Scwj;
import xx.collection.bean.Zsd2;

import xx.quanxian.service.BaseService;

import com.opensymphony.xwork2.ActionSupport;

@Controller
@Scope("prototype")
@Namespace("")
@ParentPackage("default-package")
@SuppressWarnings("serial")
public class GetZsdAction extends ActionSupport{
	
	@Resource(name="baseService")
	private BaseService baseservice;
	private String zsdid;
	
	private Map<String, String>map=new HashMap<String, String>();
	


	/**
	 * @return the map
	 */
	public Map<String, String> getMap() {
		return map;
	}




	/**
	 * @param map the map to set
	 */
	public void setMap(Map<String, String> map) {
		this.map = map;
	}




	/**
	 * @return the zsdid
	 */
	public String getZsdid() {
		return zsdid;
	}




	/**
	 * @param zsdid the zsdid to set
	 */
	public void setZsdid(String zsdid) {
		this.zsdid = zsdid;
	}




	@Action(value="/getzsdxinxi",results={@Result(name="success",type="json")})
	public String geCollect(){
		
		String[] keys={"zsdid"};
		Object[] values={zsdid};
		List<Zsd2> list2=this.baseservice.find(Zsd2.class, "Zsd2", keys, values);
		
		if (list2==null||list2.size()<=0) {
			return null;
		}else {
			map.put("zsdmc", list2.get(0).getZsdmc());
			map.put("zsdms", list2.get(0).getZsdms());
		}
		return SUCCESS;
	}
	
	
	
}
