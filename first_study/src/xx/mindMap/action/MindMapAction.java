package xx.mindMap.action;  

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;

import xx.collection.bean.MindMap;
import xx.collection.bean.MindMapDetail;
import xx.quanxian.service.BaseService;

import com.opensymphony.xwork2.ActionSupport;

/** 
 * @author fish 
 */  
public class MindMapAction extends ActionSupport  
{  
	private List<Map<String, String>> rows=new ArrayList<Map<String,String>>();
	private String mapid;//思维导图id
	private String nodeid;//节点id
	private String url;//类别地址
	private String type;
	private String datas;
	private String gaishu;
	private String state;
	private String fileName;
	private String imgurl;
	private String name;
	private String teacher;
	private String jianyi;
	private String coursetime;
	private String jsondata;

	@Resource(name="baseService")
	private BaseService baseservice;

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


	public String getUrl() {
		return url;
	}


	public void setUrl(String url) {
		this.url = url;
	}
	

	/**
	 * @return the datas
	 */
	public String getDatas() {
		return datas;
	}


	/**
	 * @param datas the datas to set
	 */
	public void setDatas(String datas) {
		this.datas = datas;
	}


	public String getType() {
		return type;
	}


	public void setType(String type) {
		this.type = type;
	}


	public String getGaishu() {
		return gaishu;
	}


	public void setGaishu(String gaishu) {
		this.gaishu = gaishu;
	}


	public String getState() {
		return state;
	}


	public void setState(String state) {
		this.state = state;
	}




	public String getFileName() {
		return fileName;
	}


	public void setFileName(String fileName) {
		this.fileName = fileName;
	}


	public String getImgurl() {
		return imgurl;
	}


	public void setImgurl(String imgurl) {
		this.imgurl = imgurl;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getTeacher() {
		return teacher;
	}


	public void setTeacher(String teacher) {
		this.teacher = teacher;
	}


	public String getJianyi() {
		return jianyi;
	}


	public void setJianyi(String jianyi) {
		this.jianyi = jianyi;
	}


	public String getCoursetime() {
		return coursetime;
	}


	public void setCoursetime(String coursetime) {
		this.coursetime = coursetime;
	}


	public String getJsondata() {
		return jsondata;
	}


	public void setJsondata(String jsondata) {
		this.jsondata = jsondata;
	}


	public List<Map<String, String>> getRows() {
		return rows;
	}


	public void setRows(List<Map<String, String>> rows) {
		this.rows = rows;
	}


	@Action(value="setMapId",results={@Result(name="success",type="json")})
	public String setMapId() throws Exception  
	{  
		System.out.println("mapId:"+mapid);
		HttpSession hs = ServletActionContext.getRequest().getSession();
		hs.setAttribute("mapid", mapid);
		return "success";	
	}
	@Action(value="newMindMap",results={@Result(name="success",type="json")})
	public String newMindMap() throws Exception  
	{  
		HttpSession hs = ServletActionContext.getRequest().getSession();
		mapid=(String) hs.getAttribute("mapid");
		
		System.out.println("map12113：：：：：：：：：：：：：："+mapid);
		if (mapid==null||mapid.equals("")) {
			System.out.println("执行的是这里1");
			Map<String, Object>map4=new HashMap<String, Object>();
			String j=String.valueOf(System.currentTimeMillis());
			Map<String, String> map=new HashMap<String, String>();
			map.put("name", "jsMind remote");
			map.put("author", "hizzgdev@163.com");
			map.put("version", "0.2");
			map4.put("meta", map);

			map4.put("format", "node_tree");

			Map<String, String>map3=new HashMap<String, String>();
			map3.put("id", String.valueOf(j));
			map3.put("topic","C语言基础");
			map4.put("data", map3);
            JSONObject jsonObject=JSONObject.fromObject(map4);
			mapid=String.valueOf(j);
			
			datas=jsonObject.toString();
			datas=datas.replace("\"", "\'");
			System.out.println("datas:"+datas);
			return "success";	
		} else {
			
			System.out.println("执行的是这里2");
			MindMap mindMap=this.baseservice.find(MindMap.class,mapid);
			
			System.out.println("MindMap:"+mindMap);
			if (mindMap==null||mindMap.equals("")) {
				state="1";//没有数据
			}else {
				state="0";
				datas=mindMap.getData();
				System.out.println("datas:"+datas);
				datas=datas.replace("\"", "\'");
			}
		
			return "success";	
		}
		
		
	}
	@Action(value="newMindMap2",results={@Result(name="success",type="json")})
	public String newMindMap2() throws Exception  
	{  
		
	
			Map<String, Object>map4=new HashMap<String, Object>();
			String j=String.valueOf(System.currentTimeMillis());
			Map<String, String> map=new HashMap<String, String>();
			map.put("name", "jsMind remote");
			map.put("author", "hizzgdev@163.com");
			map.put("version", "0.2");
			map4.put("meta", map);

			map4.put("format", "node_tree");

			Map<String, String>map3=new HashMap<String, String>();
			map3.put("id", String.valueOf(j));
			map3.put("topic","C语言基础");
			map4.put("data", map3);
            JSONObject jsonObject=JSONObject.fromObject(map4);
			mapid=String.valueOf(j);
			
			datas=jsonObject.toString();
			datas=datas.replace("\"", "\'");
			System.out.println("datas:"+datas);
			return "success";	
		
		
		
	}

	//不同节点跳转不同页面

	@Action(value="checkNode",results={@Result(name="success",type="json")})
	public String uploadnext() throws Exception  
	{  
		String hql = "select a from MindMapDetail as a where a.nodeid='"+nodeid+"' and a.mapid='"+mapid+"'";
		List<MindMapDetail> d=baseservice.findHql(MindMapDetail.class, hql);

		if (d.size()==0) {
			type="4";

		}else{
			MindMapDetail mDetail=d.get(0);
			type=mDetail.getType();
		}
		return "success";	
	}


	@Action(value="saveKecheng",results={@Result(name="success",type="json")})
	public String saveKecheng() throws Exception  
	{  
		state="1";//表示失败
		HttpSession hs = ServletActionContext.getRequest().getSession();
		String uid = (String)hs.getAttribute("uid");

		//判断该思维导图是否属于自己的。不是自己的重新建立
		MindMap map=this.baseservice.find(MindMap.class,mapid);
		String ouid=map.getUid();
		if (uid.equals(ouid)) {
			MindMapDetail mapDetail=new MindMapDetail();
			mapDetail.setGaishu(gaishu);
			mapDetail.setMapid(mapid);
			mapDetail.setNodeid(nodeid);
			mapDetail.setType(type);
			mapDetail.setCoursetime(coursetime);
			mapDetail.setImgurl(imgurl);
			mapDetail.setJianyi(jianyi);
			mapDetail.setName(name);
			mapDetail.setTeacher(teacher);
			mapDetail.setUserid(uid);
			
			String[] keys={"nodeid","mapid"};
			String[] values={nodeid,mapid};
			List<MindMapDetail> mDetail=this.baseservice.find(MindMapDetail.class, "MindMapDetail",keys,values);

			if (mDetail.size()==0) {
				this.baseservice.save(mapDetail);
			} else {
				mapDetail.setId(mDetail.get(0).getId());
				this.baseservice.update(mapDetail);
			}
		} else {
			System.out.println("不是自己的map");
			//不是自己的mapid
			String newmapId=String.valueOf(System.currentTimeMillis());
			MindMap map2=new MindMap();
			map2.setId(newmapId);
			map2.setUid(uid);
			SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");//设置日期格式
			map2.setName(df.format(new Date()));
			map2.setData(jsondata);
			this.baseservice.save(map2);

			//然后再把原始mapid里面所有nodeid复制一份（除了最新的nodeid）；
			String[] keys={"userid","mapid"};
			String[] values={ouid,mapid};
			List<MindMapDetail>rList= this.baseservice.find(MindMapDetail.class,"MindMapDetail",keys,values);
			if (rList.size()!=0) {
				for (int i = 0; i < rList.size(); i++) {
					MindMapDetail mapDetail=rList.get(i);
					if(!mapDetail.getNodeid().equals(nodeid)){
						mapDetail.setMapid(newmapId);
						mapDetail.setUserid(uid);
						this.baseservice.save(mapDetail);
					}
				}
				//保存自己修改的nodeid
				MindMapDetail mapDetail=new MindMapDetail();
				mapDetail.setGaishu(gaishu);
				mapDetail.setMapid(newmapId);
				mapDetail.setNodeid(nodeid);
				mapDetail.setType(type);
				mapDetail.setCoursetime(coursetime);
				mapDetail.setImgurl(imgurl);
				mapDetail.setJianyi(jianyi);
				mapDetail.setName(name);
				mapDetail.setTeacher(teacher);
				mapDetail.setUserid(uid);
				this.baseservice.save(mapDetail);
				mapid=newmapId;
			}
		}

		state="0";//表示成功

		return SUCCESS;
	}
	@Action(value="saveZhangjie",results={@Result(name="success",type="json")})
	public String saveZhangjie() throws Exception  
	{  
		state="1";//表示失败
		HttpSession hs = ServletActionContext.getRequest().getSession();
		String uid = (String)hs.getAttribute("uid");
		MindMap map=this.baseservice.find(MindMap.class,mapid);
		String ouid=map.getUid();

		if (uid.equals(ouid)) {
			MindMapDetail mapDetail=new MindMapDetail();
			mapDetail.setGaishu(gaishu);
			mapDetail.setMapid(mapid);
			mapDetail.setNodeid(nodeid);
			mapDetail.setType(type);
			mapDetail.setUserid(uid);
			String[] keys={"nodeid","mapid"};
			String[] values={nodeid,mapid};
			List<MindMapDetail> mDetail=this.baseservice.find(MindMapDetail.class, "MindMapDetail",keys,values);

			if (mDetail.size()==0) {
				this.baseservice.save(mapDetail);
			} else {
				mapDetail.setId(mDetail.get(0).getId());
				this.baseservice.update(mapDetail);
			}

		}else {

			//不是自己的mapid
			String newmapId=String.valueOf(System.currentTimeMillis());
			MindMap map2=new MindMap();
			map2.setId(newmapId);
			map2.setUid(uid);
			SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");//设置日期格式
			map2.setName(df.format(new Date()));
			map2.setData(jsondata);
			this.baseservice.save(map2);

			//然后再把原始mapid里面所有nodeid复制一份（除了最新的nodeid）；
			String[] keys={"userid","mapid"};
			String[] values={ouid,mapid};
			List<MindMapDetail>rList= this.baseservice.find(MindMapDetail.class,"MindMapDetail",keys,values);
			if (rList.size()!=0) {
				for (int i = 0; i < rList.size(); i++) {
					MindMapDetail mapDetail=rList.get(i);
					if(!mapDetail.getNodeid().equals(nodeid)){
						mapDetail.setMapid(newmapId);
						mapDetail.setUserid(uid);
						this.baseservice.save(mapDetail);
					}
				}
				//保存自己修改的nodeid
				MindMapDetail mapDetail=new MindMapDetail();
				mapDetail.setGaishu(gaishu);
				mapDetail.setMapid(newmapId);
				mapDetail.setNodeid(nodeid);
				mapDetail.setType(type);
				mapDetail.setUserid(uid);
				this.baseservice.save(mapDetail);
				mapid=newmapId;
			}
		}
		state="0";//表示成功
		return SUCCESS;
	}
	@Action(value="saveZhishidian",results={@Result(name="success",type="json")})
	public String saveZhishidian() throws Exception  
	{  
		state="1";//表示失败
		HttpSession hs = ServletActionContext.getRequest().getSession();
		String uid = (String)hs.getAttribute("uid");
		MindMap map=this.baseservice.find(MindMap.class,mapid);
		String ouid=map.getUid();
		if (uid.equals(ouid)) {
			MindMapDetail mapDetail=new MindMapDetail();
			mapDetail.setGaishu(gaishu);
			mapDetail.setMapid(mapid);
			mapDetail.setNodeid(nodeid);
			mapDetail.setType(type);
			mapDetail.setUserid(uid);
			String[] keys={"nodeid","mapid"};
			String[] values={nodeid,mapid};
			List<MindMapDetail> mDetail=this.baseservice.find(MindMapDetail.class, "MindMapDetail",keys,values);

			if (mDetail.size()==0) {
				this.baseservice.save(mapDetail);
			} else {
				mapDetail.setId(mDetail.get(0).getId());
				this.baseservice.update(mapDetail);
			}
		} else {
			//不是自己的mapid
			String newmapId=String.valueOf(System.currentTimeMillis());
			MindMap map2=new MindMap();
			map2.setId(newmapId);
			map2.setUid(uid);
			SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");//设置日期格式
			map2.setName(df.format(new Date()));
			map2.setData(jsondata);
			this.baseservice.save(map2);

			//然后再把原始mapid里面所有nodeid复制一份（除了最新的nodeid）；
			String[] keys={"userid","mapid"};
			String[] values={ouid,mapid};
			List<MindMapDetail>rList= this.baseservice.find(MindMapDetail.class,"MindMapDetail",keys,values);
			if (rList.size()!=0) {
				for (int i = 0; i < rList.size(); i++) {
					MindMapDetail mapDetail=rList.get(i);
					if(!mapDetail.getNodeid().equals(nodeid)){
						mapDetail.setMapid(newmapId);
						mapDetail.setUserid(uid);
						this.baseservice.save(mapDetail);
					}
				}
				//保存自己修改的nodeid
				MindMapDetail mapDetail=new MindMapDetail();
				mapDetail.setGaishu(gaishu);
				mapDetail.setMapid(newmapId);
				mapDetail.setNodeid(nodeid);
				mapDetail.setType(type);
				mapDetail.setUserid(uid);
				this.baseservice.save(mapDetail);
				mapid=newmapId;
			}
		}

		state="0";//表示成功

		return SUCCESS;
	}

	@Action(value="savemap",results={@Result(name="success",type="json")})
	public String savemap() throws Exception  
	{  
		state="1";//表示失败
		System.out.println("mapid:"+mapid);
		String hql="select a from MindMap as a where a.id='"+mapid+"'";
		List<MindMap> list=this.baseservice.findHql(MindMap.class, hql);
		HttpSession hs = ServletActionContext.getRequest().getSession();
		String uid = (String)hs.getAttribute("uid");
		MindMap mindMap=new MindMap();
		if (list.size()==0) {
			if (name==null||name.equals("")) {
				mindMap.setId(mapid);
				mindMap.setData(jsondata);
				mindMap.setUid(uid);
				SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");//设置日期格式
				mindMap.setName(df.format(new Date()));
			}else{
				mindMap.setId(mapid);
				mindMap.setData(jsondata);
				mindMap.setUid(uid);
				mindMap.setName(name);

			}
			this.baseservice.save(mindMap);
		}else {
			//判断mapid是否是自己的
			String ouid=list.get(0).getUid();
			if (uid.equals(ouid)) {
				
				String mindName=list.get(0).getName();
				if (name==null||name.equals("")) {
					mindMap.setId(mapid);
					mindMap.setData(jsondata);
					mindMap.setUid(uid);
					mindMap.setName(mindName);
				}else{
					mindMap.setId(mapid);
					mindMap.setData(jsondata);
					mindMap.setUid(uid);
					mindMap.setName(name);
				}
				this.baseservice.update(mindMap);
			} else {
				String newmapId=String.valueOf(System.currentTimeMillis());
				MindMap map2=new MindMap();
				map2.setId(newmapId);
				map2.setUid(uid);
				SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");//设置日期格式
				map2.setName(df.format(new Date()));
				map2.setData(jsondata);
				this.baseservice.save(map2);
				//然后再把原始mapid里面所有nodeid复制一份（除了最新的nodeid）；
				String[] keys={"userid","mapid"};
				String[] values={ouid,mapid};
				List<MindMapDetail>rList= this.baseservice.find(MindMapDetail.class,"MindMapDetail",keys,values);
				if (rList.size()!=0) {
					for (int i = 0; i < rList.size(); i++) {
						MindMapDetail mapDetail=rList.get(i);
						mapDetail.setMapid(newmapId);
						mapDetail.setUserid(uid);
						this.baseservice.save(mapDetail);
					}
				}
				mapid=newmapId;
			}
		}
		
		state="0";//表示成功
		return SUCCESS;

	}

}  