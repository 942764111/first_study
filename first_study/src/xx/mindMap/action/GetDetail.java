package xx.mindMap.action;  

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;

import xx.collection.bean.MindMap;
import xx.collection.bean.MindMapDetail;
import xx.quanxian.service.BaseService;

import com.opensymphony.xwork2.ActionSupport;

/** 
 * @author fish 
 */  
public class GetDetail extends ActionSupport  
{  
	private String state;
	private String mapid;
	private String nodeid;
	private MindMapDetail mDetail;
	private List<MindMap> list=new ArrayList<MindMap>();
	
	@Resource(name="baseService")
	private BaseService baseservice;

	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}


	public MindMapDetail getmDetail() {
		return mDetail;
	}
	public void setmDetail(MindMapDetail mDetail) {
		this.mDetail = mDetail;
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


		//¸ù¾Ýid
	@Action(value="getDetail",results={@Result(name="success",type="json")})
	public String getDetail() throws Exception  
	{  
		String[] keys={"nodeid"};
		String[] values={nodeid};
		List<MindMapDetail> mDetails=this.baseservice.find(MindMapDetail.class, "MindMapDetail",keys,values);
		mDetail=mDetails.get(0);
		return SUCCESS;
	}
	
}