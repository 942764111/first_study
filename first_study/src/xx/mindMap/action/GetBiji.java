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
import xx.collection.bean.Dzbj;
import xx.collection.bean.Scwj;
import xx.collection.bean.Share;
import xx.collection.bean.Zysc;
import xx.collection.bean.ZyscId;
import xx.quanxian.service.BaseService;

import com.opensymphony.xwork2.ActionSupport;

/** 
 * @author fish 
 */  
public class GetBiji extends ActionSupport  
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
	@Action(value="getguanlianbiji",results={@Result(name="success",type="json")})
	public String getuploadzl() throws Exception  
	{  
		HttpSession hs = ServletActionContext.getRequest().getSession();
		String uid = (String)hs.getAttribute("uid");
		String[] keys={"zsdid","id.userId"};
		String[] values={nodeid,uid};
		List<Dzbj> mDetails=this.baseservice.find(Dzbj.class, "Dzbj",keys,values);
		
		if (mDetails==null||mDetails.size()<=0) {
			state="1";
			return SUCCESS;
		}
		state="0";
		for (int i = 0; i < mDetails.size(); i++) {
			Map<String, String> map= new HashMap<String, String>();
		
			Dzbj collect=mDetails.get(i);
			
			map.put("biji", collect.getTmnr());
			map.put("path", "/upload/dzbj/"+collect.getPath());
			map.put("id", String.valueOf(collect.getId().getTmbh()));
		    map.put("zlid", collect.getZlid());
		    
		    String[] keys7={"zlbh"};

			Object[] values7={collect.getZlid()};
			Scwj scwj=this.baseservice.find(Scwj.class, "Scwj", keys7, values7).get(0);

		    map.put("filepath", scwj.getFilepath());
		    map.put("zlmc", scwj.getFilename());
			if (list2.size()<mDetails.size()) {
				list2.add(map);
			}
		
           
		}

		return SUCCESS;
		
	}
	
	
	
}