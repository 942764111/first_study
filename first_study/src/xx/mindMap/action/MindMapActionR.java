package xx.mindMap.action;  

import javax.annotation.Resource;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;

import xx.collection.bean.MindMap;
import xx.quanxian.service.BaseService;

import com.opensymphony.xwork2.ActionSupport;

/** 
 * @author fish 
 */  
public class MindMapActionR extends ActionSupport  
{  
    private String state;
  
	private String datas;
	private String mapid;
	@Resource(name="baseService")
	private BaseService baseservice;

	
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}

	
	public String getDatas() {
		return datas;
	}
	public void setDatas(String datas) {
		this.datas = datas;
	}


	public String getMapid() {
		return mapid;
	}
	public void setMapid(String mapid) {
		this.mapid = mapid;
	}
	@Action(value="getmindJson2",results={@Result(name="success",type="json")})
	public String getmindJson2() throws Exception  
	{  

		MindMap mindMap=this.baseservice.find(MindMap.class,"1461727527756");
		if (mindMap==null||mindMap.equals("")) {
			state="1";//没有数据
		}else {
			state="0";
			datas=mindMap.getData();
			System.out.println("datas:"+datas);
			datas=datas.replace("\"", "\'");
		}
		mapid="1461727527756";
		return SUCCESS;
	}
	

	
}  