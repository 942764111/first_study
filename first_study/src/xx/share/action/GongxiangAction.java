package xx.share.action;  

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;

import xx.collection.bean.Gongxiang;
import xx.collection.bean.Scwj;
import xx.collection.bean.Share;
import xx.collection.bean.Studentifno;
import xx.quanxian.service.BaseService;

import com.opensymphony.xwork2.ActionSupport;

/** 
 * @author fish 
 */  
public class GongxiangAction extends ActionSupport  
{  
	private String zlid;
	private String filename;
	private String filepath;
	private String userid;
	private String state;
	private String picurl;
	private List<Map<String, Object>>list=new ArrayList<Map<String,Object>>();
	private List<Map<String, Object>>row=new ArrayList<Map<String,Object>>();
	@Resource(name="baseService")
	private BaseService baseservice;
	/**
	 * @return the zlid
	 */
	public String getZlid() {
		return zlid;
	}

	/**
	 * @return the picurl
	 */
	public String getPicurl() {
		return picurl;
	}

	/**
	 * @param picurl the picurl to set
	 */
	public void setPicurl(String picurl) {
		this.picurl = picurl;
	}

	/**
	 * @return the row
	 */
	public List<Map<String, Object>> getRow() {
		return row;
	}

	/**
	 * @param row the row to set
	 */
	public void setRow(List<Map<String, Object>> row) {
		this.row = row;
	}

	/**
	 * @param zlid the zlid to set
	 */
	public void setZlid(String zlid) {
		this.zlid = zlid;
	}

	/**
	 * @return the filename
	 */
	public String getFilename() {
		return filename;
	}

	/**
	 * @param filename the filename to set
	 */
	public void setFilename(String filename) {
		this.filename = filename;
	}

	/**
	 * @return the filepath
	 */
	public String getFilepath() {
		return filepath;
	}

	/**
	 * @param filepath the filepath to set
	 */
	public void setFilepath(String filepath) {
		this.filepath = filepath;
	}

	/**
	 * @return the userid
	 */
	public String getUserid() {
		return userid;
	}

	/**
	 * @param userid the userid to set
	 */
	public void setUserid(String userid) {
		this.userid = userid;
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

	/**
	 * @return the list
	 */
	public List<Map<String, Object>> getList() {
		return list;
	}

	/**
	 * @param list the list to set
	 */
	public void setList(List<Map<String, Object>> list) {
		this.list = list;
	}





	@Action(value="getshareuser",results={@Result(name="success",type="json")})
	public String uploadnext() throws Exception  
	{  

		List<String> lStrings=this.baseservice.findHql(String.class, "select distinct a.userid from Gongxiang as a");
		if (lStrings==null||lStrings.size()==0) {
			return "success";
		}
        System.out.println(lStrings);
		for (int i = 0; i < lStrings.size(); i++) {
			Map<String, Object> map=new HashMap<String, Object>();
			map.put("userid", lStrings.get(i));
			
			String[] keys={"userinfo.userId"};
			
			Object[] values={lStrings.get(i)};
			Studentifno studentifno=this.baseservice.find(Studentifno.class, "Studentifno", keys,values).get(0);
			map.put("username", studentifno.getSName());
			map.put("type", "0");
			map.put("picurl", "wjj.png");
			list.add(map);
		}

		return "success";

	}

	@Action(value="getshare",results={@Result(name="success",type="json")})
	public String getshare() throws Exception  
	{  
		HttpSession session1 = ServletActionContext.getRequest().getSession();
		String userId = (String) session1.getAttribute("userid");
		
		HttpServletRequest request=ServletActionContext.getRequest();
		String path=request.getScheme();
		System.out.println("path:"+path);
		String[] keys={"userid"};
		Object[] values={userid};
		List<Gongxiang> roList=this.baseservice.find(Gongxiang.class, "Gongxiang", keys, values);

		if (roList==null||roList.size()==0) {
			return SUCCESS;
		}

		for (int i = 0; i < roList.size(); i++) {
			Gongxiang gongxiang =roList.get(i);
			Map<String, Object> map=new HashMap<String, Object>();
			map.put("zlid", gongxiang.getZlid());
			map.put("username", gongxiang.getFilename());
			map.put("filepath", gongxiang.getFilepath());
			map.put("type", "1");
			map.put("picurl", gongxiang.getPicurl());
			map.put("userid", userid);
			list.add(map);
		}

		return SUCCESS;

	}

	@Action(value="addgongxiang",results={@Result(name="success",type="json")})
	public String addgongxiang() throws Exception  
	{  
		HttpSession session1 = ServletActionContext.getRequest().getSession();
		String userId = (String) session1.getAttribute("userid");
		Gongxiang gongxiang=new Gongxiang();
		gongxiang.setFilepath("\\"+filepath);
		filename=new String(filename.getBytes("ISO-8859-1"),"utf-8");
		gongxiang.setFilename(filename);
		gongxiang.setUserid(userId);
		gongxiang.setZlid(zlid);
        gongxiang.setPicurl(picurl);

		String[] keys={"zlid","userid"};
		Object[] values={zlid,userId};
//		List<Gongxiang> xx=this.baseservice.find(Gongxiang.class, "Gongxiang", keys, values);
//		if (xx!=null||xx.size()!=0) {
//			state="1";
//			return SUCCESS;
//		}
		try {

			this.baseservice.save(gongxiang);
			state="0";
		} catch (Exception e) {
			// TODO: handle exception
			state="-1";
		}

		return SUCCESS;

	}


	//	@Action(value="getShare",results={@Result(name="success",type="json")})
	//	public String getShare() throws Exception  
	//	{  
	//		HttpSession session1 = ServletActionContext.getRequest().getSession();
	//		String userId = (String) session1.getAttribute("userid");
	//		try {
	//			List<Share> list2=this.baseservice.find(Share.class);
	//			
	//			for (int i = 0; i < list2.size(); i++) {
	//				Share share=list2.get(i);
	//				Map<String, Object> map=new HashMap<String, Object>();
	//				map.put("id", share.getId());
	//				String[] keys={"userinfo.userId"};
	//				Object[] values={userId};
	//				String userName=this.baseservice.find(Studentifno.class, "Studentifno", keys, values).get(0).getSName();
	//				
	//				map.put("userid", userName);
	//				map.put("gaiyao", share.getGaiyao());
	//				map.put("zhichi", share.getZhichi());
	//				map.put("fandui", share.getFandui());
	//				map.put("sharetime", share.getSharetime());
	//				map.put("sharetype", share.getSharetype());
	//				map.put("filepath", share.getFilepath());
	//				map.put("filename", share.getFilename());
	//				map.put("zlid", share.getZlid());
	//				String zhichiuser=share.getZhichiuser();
	//				String fanduiuser=share.getFanduiuser();
	//				if(zhichiuser.indexOf(","+userId+",")>=0){
	//					map.put("iszhichi", "y");
	//				}else {
	//					if(fanduiuser.indexOf(","+userId+",")>=0){
	//						map.put("iszhichi", "n");
	//					}else {
	//						map.put("iszhichi", "o");
	//					}
	//				}
	//				list.add(map);
	//				
	//			}
	//		} catch (Exception e) {
	//			// TODO: handle exception
	//			state="1";
	//		}
	//		
	//		
	//	return "success";
	//	
	//	}
	//	

}  