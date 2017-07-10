package xx.spdh.action;  

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;

import xx.collection.bean.Scwj;
import xx.collection.bean.Share;
import xx.collection.bean.Studentifno;
import xx.quanxian.service.BaseService;

import com.opensymphony.xwork2.ActionSupport;

/** 
 * @author fish 
 */  
public class Guanlian extends ActionSupport  
{  

	
	@Resource(name="baseService")
	private BaseService baseservice;
	
	private int zlid;
	private List<Map<String, Object>>list=new ArrayList<Map<String,Object>>();
	
	/**
	 * @return the zlid
	 */
	
	/**
	 * @return the list
	 */
	
	
	
	@Action(value="guanlian",results={@Result(name="success",type="json")})
	public String guanlian() throws Exception  
	{  
		
		String[] keys={"dmtzl.zlbh"};
		Object[] values={zlid};
		List<Scwj> scwj=this.baseservice.find(Scwj.class, "Scwj", keys, values);
		if (scwj==null||scwj.size()==0) {
			return "success";
		}
		
		String guanlian=scwj.get(0).getGuanlian();
		
		String[] keys1={"guanlian"};
		Object[] values1={guanlian};
		List<Scwj> list2=this.baseservice.find(Scwj.class, "Scwj", keys1, values1);
	    for (int i = 0; i < list2.size(); i++) {
			Scwj scwj2=list2.get(i);
			if (!scwj2.getDmtzl().getZlbh().equals(zlid)) {
				Map<String, Object> map=new HashMap<String, Object>();
				map.put("zlid", scwj2.getDmtzl().getZlbh());
				map.put("fileName", scwj2.getFilename());
				map.put("viewPath", scwj2.getFilepath());
				
				list.add(map);
			}
		}
		return SUCCESS;
	
	}
	/**
	 * @return the zlid
	 */
	public int getZlid() {
		return zlid;
	}
	/**
	 * @param zlid the zlid to set
	 */
	public void setZlid(int zlid) {
		this.zlid = zlid;
	}
	/**
	 * @return the list
	 */
	public List<Map<String, Object>> getList() {
		return list;
	}
	/**
	 * @param list the list to set
	 */
	public void setList(List<Map<String, Object>> list) {
		this.list = list;
	}
	
}  