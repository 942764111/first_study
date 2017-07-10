package xx.mulu.action;  

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.apache.poi.hssf.record.MulBlankRecord;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;

import xx.collection.bean.MapNode;
import xx.collection.bean.MuLu;
import xx.collection.bean.Scwj;
import xx.collection.bean.Share;
import xx.collection.bean.Studentifno;
import xx.collection.bean.Zsd2;
import xx.quanxian.interceptor.inter;
import xx.quanxian.service.BaseService;

import com.opensymphony.xwork2.ActionSupport;

/** 
 * @author fish 
 */  
public class MuLuAction extends ActionSupport  
{  

	private String id;//分享类型
	private String name;//资源地址

	private String state;
	private String type;
	private String parentid;
	private List<Map<String, String>> list=new ArrayList<Map<String,String>>();
	private List<Map<String, String>> rows=new ArrayList<Map<String,String>>();
	@Resource(name="baseService")
	private BaseService baseservice;

	/**
	 * @return the rows
	 */
	public List<Map<String, String>> getRows() {
		return rows;
	}

	/**
	 * @return the parentid
	 */
	public String getParentid() {
		return parentid;
	}

	/**
	 * @param parentid the parentid to set
	 */
	public void setParentid(String parentid) {
		this.parentid = parentid;
	}

	/**
	 * @param rows the rows to set
	 */
	public void setRows(List<Map<String, String>> rows) {
		this.rows = rows;
	}

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}


	/**
	 * @return the list
	 */
	public List<Map<String, String>> getList() {
		return list;
	}

	/**
	 * @param list the list to set
	 */
	public void setList(List<Map<String, String>> list) {
		this.list = list;
	}


	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}


	/**
	 * @return the state
	 */
	public String getState() {
		return state;
	}


	/**
	 * @param state the state to set
	 */
	public void setState(String state) {
		this.state = state;
	}


	@Action(value="getMulu",results={@Result(name="success",type="json")})
	public String uploadnext() throws Exception  
	{  

		HttpSession session1 = ServletActionContext.getRequest().getSession();
		String userId = (String) session1.getAttribute("userid");
		
		String[] keys2={"userid","id"};
		Object[] values2={userId,Integer.parseInt(id)};
		//先判断父ID存不存在
		List<MuLu> list3=this.baseservice.find(MuLu.class, "MuLu", keys2, values2);
		if (list3==null||list3.size()<=0) {
			parentid="0";
		} else {
			parentid=list3.get(0).getParentid();
		}
		
		
		
		String[] keys={"userid","parent"};
		Object[] values={userId,id};
		//先判断父ID存不存在
		List<MuLu> list2=this.baseservice.find(MuLu.class, "MuLu", keys, values);

		if (list2==null||list2.size()<=0) {
			//父ID不存在，肯定是一级ID，获取对应文件
	    type="1";//代表是资料
		String hql="from Scwj where userId='"+userId+"' and muluid='"+id+"' ";
		System.out.println("hql:"+hql);
		List<Scwj>da=this.baseservice.getList(Scwj.class, hql);
		
		if (da.size()==0) {
			type="0";
			state="1";//1、代表空文件  0、代表有数据
			return SUCCESS;
		}
		state="0";
		for (int i = 0; i <da.size(); i++) {
			Scwj scwj=(Scwj) da.get(i);
			
			if (scwj.getDmtzl()==null||scwj.getDmtzl().equals("")) {
				System.out.println("不能为空");
			} else {
				Map<String, String>map=new HashMap<String, String>();
				map.put("zlid", String.valueOf(scwj.getDmtzl().getZlbh()));
				map.put("fileName", scwj.getFilename());
				map.put("thumbnails", scwj.getThumbnails());
				map.put("zlms", scwj.getZlms());
				String timeString=scwj.getUploadTime();
				timeString=timeString.substring(1, 4)+"年"+timeString.substring(5,8)+"月"+
				map.put("uploadTime", scwj.getUploadTime());
				map.put("shareNum", scwj.getShareNum());
				map.put("downLoadNum", scwj.getDownLoadNum());
				map.put("viewNum", scwj.getViewNum());
				map.put("viewPath", scwj.getFilepath());
				map.put("filePath", scwj.getOldfilepath());
				map.put("zsdmc", scwj.getZsdmc());
				
			
				list.add(map);
			}
			
		}
		}else{
			
			
			state="0";
			type="0";//文件夹
			//不是有文件
			for (int i = 0; i < list2.size(); i++) {
				Map<String, String> map=new HashMap<String, String>();
				MuLu muLu=list2.get(i);
			    map.put("id", String.valueOf(muLu.getId()));
			    map.put("name", muLu.getName());
			    map.put("picurl", "wjj.png");
			    rows.add(map);
			}
			
			
		}


		return SUCCESS;

	}
	
	@Action(value="newMulu",results={@Result(name="success",type="json")})
	public String newMulu() throws Exception  
	{  

		HttpSession session1 = ServletActionContext.getRequest().getSession();
		String userId = (String) session1.getAttribute("userid");
		
		MuLu muLu=new MuLu();
		name=new String(name.getBytes("ISO-8859-1"),"UTF-8");
		muLu.setName(name);
		muLu.setParentid(id);
		muLu.setUserid(userId);
		
		this.baseservice.save(muLu);
		state="0";
		return SUCCESS;

	}
	@Action(value="delMulu",results={@Result(name="success",type="json")})
	public String delMulu() throws Exception  
	{  

		HttpSession session1 = ServletActionContext.getRequest().getSession();
		String userId = (String) session1.getAttribute("userid");
		
		MuLu muLu=new MuLu();
	    muLu.setId(Integer.parseInt(id));
		//muLu.setParentid(id);
		muLu.setUserid(userId);
		
		this.baseservice.delete(muLu);
		state="0";
		return SUCCESS;

	}

}  