package xx.mindMap.action;  

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;

import xx.collection.bean.MindMapDetail;
import xx.collection.bean.Scwj;
import xx.quanxian.service.BaseService;

import com.opensymphony.xwork2.ActionSupport;

/** 
 * @author fish 
 */  
public class GetFileDeatil extends ActionSupport  
{  
	private String state;
	private String mapid;
	private String nodeid;
	private List<Map<String, Object>>list=new ArrayList<Map<String,Object>>();
	@Resource(name="baseService")
	private BaseService baseservice;

	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}

	public List<Map<String, Object>> getList() {
		return list;
	}
	public void setList(List<Map<String, Object>> list) {
		this.list = list;
	}


	//根据id
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
	@Action(value="getZhiDingFile",results={@Result(name="success",type="json")})
	public String getDetail() throws Exception  
	{  
		String[] keys={"nodeid","mapid"};
		String[] values={nodeid,mapid};
		List<MindMapDetail> mDetails=this.baseservice.find(MindMapDetail.class, "MindMapDetail",keys,values);
		String zlid=mDetails.get(0).getGaishu();
		String[] a=zlid.split(",");
		System.out.println("长度:"+a.length);
		for (int i = 0; i < a.length; i++) {
			//根据资料id取出资料详情
			Scwj scwj=this.baseservice.find(Scwj.class, Integer.parseInt(a[i]));
			Map<String,Object> map=new HashMap<String, Object>();
			map.put("name", scwj.getFilename());
			map.put("filePath", scwj.getFilepath());
			map.put("gaishu", scwj.getZlms());
			list.add(map);
		}
		return SUCCESS;
	}

}