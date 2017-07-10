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

import xx.collection.bean.Scwj;
import xx.quanxian.service.BaseService;

import com.opensymphony.xwork2.ActionSupport;

/** 
 * @author fish 
 */  
public class GetGuanLianAction extends ActionSupport  
{  
	private String state;
	
	private String nodeid;
	private List<Map<String, String>>list2= new ArrayList<Map<String,String>>();
	@Resource(name="baseService")
	private BaseService baseservice;

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



		//¸ù¾Ýid
	@Action(value="getuploadzl",results={@Result(name="success",type="json")})
	public String getuploadzl() throws Exception  
	{  
		HttpSession hs = ServletActionContext.getRequest().getSession();
		String uid = (String)hs.getAttribute("uid");
		String[] keys={"zsdid","dmtzl.userinfo.userId"};
		String[] values={nodeid,uid};
		List<Scwj> mDetails=this.baseservice.find(Scwj.class, "Scwj",keys,values);
		System.out.println("¹þ¹þ¹þ£º"+mDetails.size());
		if (mDetails==null||mDetails.size()<=0) {
			state="1";
			return SUCCESS;
		}
		state="0";
		for (int i = 0; i < mDetails.size(); i++) {
			Map<String, String> map= new HashMap<String, String>();
		
			Scwj scwj=mDetails.get(i);
			
			map.put("zlmc", scwj.getFilename());
			
			String type=scwj.getFiletype();
			String tubiao="";
			if (type.equals("1")) {
				tubiao="/assets/avatars/word.jpg";
			}else if (type.equals("2")) {
				tubiao="/assets/avatars/shipin.jpg";
			}else if (type.equals("3")) {
				tubiao="/assets/avatars/tupian.jpg";
			}else if (type.equals("4")) {
				tubiao="/assets/avatars/tuzhi.jpg";
			}else if (type.equals("5")) {
				tubiao="/assets/avatars/yinpin.jpg";
			}
			map.put("tubiao", tubiao);
			map.put("zlms", scwj.getZlms());
			map.put("zlid", String.valueOf(scwj.getDmtzl().getZlbh()));
			map.put("filepath", scwj.getFilepath());
			map.put("time", scwj.getUploadTime());
			if (list2.size()<mDetails.size()) {
				list2.add(map);
			}
		
           
		}

		return SUCCESS;
		
	}
	
	
	
}