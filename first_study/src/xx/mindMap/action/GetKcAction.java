package xx.mindMap.action;  

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;

import xx.collection.bean.MapNode;
import xx.collection.bean.MindMap;
import xx.quanxian.service.BaseService;

import com.opensymphony.xwork2.ActionSupport;

/** 
 * @author fish 
 */  
public class GetKcAction extends ActionSupport  
{  
    private String state;
    private String mapid;
    private String nodeid;

    /**
	 * @return the list
	 */
	public List<MapNode> getList() {
		return list;
	}
	/**
	 * @param list the list to set
	 */
	public void setList(List<MapNode> list) {
		this.list = list;
	}

	private List<MapNode> list=new ArrayList<MapNode>();
	@Resource(name="baseService")
	private BaseService baseservice;
	
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	
	public String getMapid() {
		return mapid;
	}
	public void setMapid(String mapid) {
		this.mapid = mapid;
	}
	public String getNodeid() {
		return nodeid;
	}
	public void setNodeid(String nodeid) {
		this.nodeid = nodeid;
	}
	
	@Action(value="getkcbiji",results={@Result(name="success",type="json")})
	public String getmap() throws Exception  
	{  
		HttpSession hs = ServletActionContext.getRequest().getSession();
		String uid = (String)hs.getAttribute("uid");
		String[] keys={"userid"};
		String [] values={uid};
		List<MapNode> list2=this.baseservice.find(MapNode.class,"MapNode", keys,values);
		
		System.out.println("uid:"+uid);
		for (int i = 0; i < list2.size(); i++) {
			MapNode mapNode=list2.get(i);
			String nodeid=mapNode.getNodeid();
			if (nodeid.length()==5) {
				list.add(mapNode);
			}
		}
		
		
		return SUCCESS;
	}
}