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

import xx.collection.bean.Collect;
import xx.collection.bean.Scwj;
import xx.collection.bean.Share;
import xx.collection.bean.Zysc;
import xx.collection.bean.ZyscId;
import xx.quanxian.service.BaseService;

import com.opensymphony.xwork2.ActionSupport;

/** 
 * @author fish 
 */  
public class GetShareAction extends ActionSupport  
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
	@Action(value="getshare3",results={@Result(name="success",type="json")})
	public String getuploadzl() throws Exception  
	{  
		HttpSession hs = ServletActionContext.getRequest().getSession();
		String uid = (String)hs.getAttribute("uid");
		String[] keys={"zsdid","userid"};
		String[] values={nodeid,uid};
		List<Share> mDetails=this.baseservice.find(Share.class, "Share",keys,values);
		
		if (mDetails==null||mDetails.size()<=0) {
			state="1";
			return SUCCESS;
		}
		state="0";
		for (int i = 0; i < mDetails.size(); i++) {
			Map<String, String> map= new HashMap<String, String>();
		
			Share collect=mDetails.get(i);
			
			map.put("zlmc", collect.getFilename());
			String type=collect.getFiletype();
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
			map.put("zlms", collect.getGaiyao());
			map.put("zlid", collect.getZlid());
			map.put("filepath", collect.getFilepath());
			map.put("time", collect.getSharetime());
			map.put("id", String.valueOf(collect.getId()));

			if (list2.size()<mDetails.size()) {
				list2.add(map);
			}
		
           
		}

		return SUCCESS;
		
	}
	
	
	
}