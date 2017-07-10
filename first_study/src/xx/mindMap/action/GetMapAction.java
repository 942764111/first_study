package xx.mindMap.action;  

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;

import xx.collection.bean.MindMap;
import xx.quanxian.service.BaseService;

import com.opensymphony.xwork2.ActionSupport;

/** 
 * @author fish 
 */  
public class GetMapAction extends ActionSupport  
{  
    private String state;
    private String mapid;
    private String nodeid;
 //   private MindMapDetail mDetail;
    private List<MindMap> list=new ArrayList<MindMap>();
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
	public List<MindMap> getList() {
		return list;
	}
	public void setList(List<MindMap> list) {
		this.list = list;
	}
	@Action(value="getmap",results={@Result(name="success",type="json")})
	public String getmap() throws Exception  
	{  
		HttpSession hs = ServletActionContext.getRequest().getSession();
		String uid = (String)hs.getAttribute("uid");
		
		String[] keys={"uid"};
		String [] values={uid};
		list=this.baseservice.find(MindMap.class,"MindMap", keys,values);
		System.out.println("list："+list.size());
		if (list.size()==0) {
			state="1";//没有数据
		}
		
		return SUCCESS;
	}
	
//	//根据id
//	@Action(value="getDetail",results={@Result(name="success",type="json")})
//	public String getDetail() throws Exception  
//	{  
//		mDetail=this.baseservice.find(MindMapDetail.class, nodeid);
//		return SUCCESS;
//	}
}  