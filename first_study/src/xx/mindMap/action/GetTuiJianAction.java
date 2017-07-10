package xx.mindMap.action;  

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;

import xx.adminservice.AdminService;
import xx.collection.bean.Scwj;
import xx.quanxian.service.BaseService;

import com.opensymphony.xwork2.ActionSupport;

/** 
 * @author fish 
 */  
public class GetTuiJianAction extends ActionSupport  
{  
	private String state;
	
	private String nodeid;
	private List<Map<String, String>>list2= new ArrayList<Map<String,String>>();
	@Resource(name="baseService")
	private BaseService baseservice;
	@Resource(name="adminService")
	private AdminService adminService;
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}


	
	
	/**
	 * @return the list2
	 */
	public List<Map<String, String>> getList2() {
		return list2;
	}
	/**
	 * @param list2 the list2 to set
	 */
	public void setList2(List<Map<String, String>> list2) {
		this.list2 = list2;
	}
	public String getNodeid() {
		return nodeid;
	}
	public void setNodeid(String nodeid) {
		this.nodeid = nodeid;
	}



	@Action(value="gettuijian",results={@Result(name="success",type="json")})
	public String gettuijian() throws Exception  
	{  
	
		List<Map<String, String>> mDetails=this.adminService.querytuijian(nodeid);
		
		if (mDetails==null||mDetails.size()<=0) {
			state="1";
			return SUCCESS;
		}
		state="0";
		list2=mDetails;
		return SUCCESS;

		
	}
	
}