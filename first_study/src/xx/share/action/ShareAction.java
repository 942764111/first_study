package xx.share.action;  

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;

import xx.collection.bean.MapNode;
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
public class ShareAction extends ActionSupport  
{  

	private String gaiyao;//分享类型
	private String filename;//资源地址
	private String filepath;//文件类型
	private String sharetype;
	private String state;
	private String zlid;
	private Integer id;
	private List<Map<String, Object>>list=new ArrayList<Map<String, Object>>();
	@Resource(name="baseService")
	private BaseService baseservice;
	
	public String getGaiyao() {
		return gaiyao;
	}


	public void setGaiyao(String gaiyao) {
		this.gaiyao = gaiyao;
	}


	public String getFilename() {
		return filename;
	}


	public void setFilename(String filename) {
		this.filename = filename;
	}


	public String getFilepath() {
		return filepath;
	}


	public void setFilepath(String filepath) {
		this.filepath = filepath;
	}


	public String getSharetype() {
		return sharetype;
	}


	public void setSharetype(String sharetype) {
		this.sharetype = sharetype;
	}


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


	public String getZlid() {
		return zlid;
	}


	public void setZlid(String zlid) {
		this.zlid = zlid;
	}


	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}


	/**
	 * @param id the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}


	@Action(value="share",results={@Result(name="success",type="json")})
	public String uploadnext() throws Exception  
	{  
		Date now = new Date(); 
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");//可以方便地修改日期格式
		String sharetime = dateFormat.format( now ); 
		System.out.println(sharetime); 
		
		HttpSession session1 = ServletActionContext.getRequest().getSession();
		String userId = (String) session1.getAttribute("userid");
		Share share=new Share();
		gaiyao=new String(gaiyao.getBytes("ISO-8859-1"),"utf-8");
		share.setGaiyao(gaiyao);
		share.setFandui("0");
		share.setZhichi("0");
		
		String[] keys={"zlbh"};
		Object[] values={zlid};
		Scwj scwj=this.baseservice.find(Scwj.class, "Scwj", keys, values).get(0);
		share.setFilename(scwj.getFilename());
		share.setFilepath(scwj.getFilepath());
		share.setUserid(userId);
		share.setSharetype(sharetype);
		share.setSharetime(sharetime);
		share.setZhichiuser(",");
		share.setFanduiuser(",");
		share.setZlid(zlid);
		share.setZsdid(scwj.getZsdid());
		share.setFiletype(scwj.getFiletype());
		state="0";
		
		
		 String kcmc=scwj.getKcmc();
			//知识点和笔记相关联
			List<MapNode> mapNodes=this.baseservice.find(MapNode.class);
			String zsdid=scwj.getZsdid();
			int size=(zsdid.length()-1)/4;
		
			if (mapNodes==null||mapNodes.size()<=0) {

				for (int k = 0; k < size; k++) {
					System.out.println("k："+k);
					String str4=getMap().get(String.valueOf(k+1));
					String nodeid=str4+zsdid.substring(1,4*(k+1)+1);
					MapNode mapNode=new MapNode();
					mapNode.setNodeid(nodeid);
					
					System.out.println("name:"+nodeid);
					String[] keys10={"zsdid"};
					Object[] values10={nodeid};
					String nodename=this.baseservice.find(Zsd2.class, "Zsd2", keys10, values10).get(0).getZsdmc();
					mapNode.setNodename(nodename);
					mapNode.setType(kcmc);
					mapNode.setUserid(userId);

					if (nodeid.length()==5) {
						mapNode.setParentid("");
					}else {
						String str=getMap().get(String.valueOf(k));
						String parentid=str+zsdid.substring(1,4*k+1);
						mapNode.setParentid(parentid);
					}
					this.baseservice.save(mapNode);
				}

			}else {
				for (int k = size; k> 0; k--) {
					String str4=getMap().get(String.valueOf(k));
					String nodeid=str4+zsdid.substring(1, 4*k+1);
					
					String[] keys13={"nodeid","userid"};
					Object[] values13={nodeid,userId};
					List<MapNode> mapNode=this.baseservice.find(MapNode.class, "MapNode", keys13, values13);

					if (mapNode==null||mapNode.size()<=0) {
	                MapNode mapNode2=new MapNode();
	                mapNode2.setNodeid(nodeid);
	  
	                System.out.println("nodeid:"+nodeid);
	                String[] keys10={"zsdid"};
					Object[] values10={nodeid};
					String nodename=this.baseservice.find(Zsd2.class, "Zsd2", keys10, values10).get(0).getZsdmc();
	                mapNode2.setNodename(nodename);
	                if (nodeid.length()==5) {
						mapNode2.setParentid("");
					}else {
						
						String str=getMap().get(String.valueOf(k-1));
						String parentid=str+zsdid.substring(1,4*(k-1)+1);
						mapNode2.setParentid(parentid);
					}
	                mapNode2.setType(kcmc);
	                mapNode2.setUserid(userId);
	                this.baseservice.save(mapNode2);
					} else {
						break;
					}
				}

			}
		try {
			this.baseservice.save(share);
		} catch (Exception e) {
			// TODO: handle exception
			state="1";
		}
		
		
	return "success";
	
	}
	@Action(value="getShare",results={@Result(name="success",type="json")})
	public String getShare() throws Exception  
	{  
		HttpSession session1 = ServletActionContext.getRequest().getSession();
		String userId = (String) session1.getAttribute("userid");
		try {
			List<Share> list2=this.baseservice.find(Share.class);
			
			for (int i = 0; i < list2.size(); i++) {
				Share share=list2.get(i);
				Map<String, Object> map=new HashMap<String, Object>();
				map.put("id", share.getId());
				String[] keys={"userinfo.userId"};
				Object[] values={share.getUserid()};
				String userName=this.baseservice.find(Studentifno.class, "Studentifno", keys, values).get(0).getSName();
				
				map.put("userid", userName);
				map.put("gaiyao", share.getGaiyao());
				map.put("zhichi", share.getZhichi());
				map.put("fandui", share.getFandui());
				map.put("sharetime", share.getSharetime());
				map.put("sharetype", share.getSharetype());
				map.put("filepath", share.getFilepath());
				map.put("filename", share.getFilename());
				map.put("zlid", share.getZlid());
				String zhichiuser=share.getZhichiuser();
				String fanduiuser=share.getFanduiuser();
				if(zhichiuser.indexOf(","+userId+",")>=0){
					map.put("iszhichi", "y");
				}else {
					if(fanduiuser.indexOf(","+userId+",")>=0){
						map.put("iszhichi", "n");
					}else {
						map.put("iszhichi", "o");
					}
				}
				list.add(map);
				
			}
		} catch (Exception e) {
			// TODO: handle exception
			state="1";
		}
		
		
	return "success";
	
	}
	@Action(value="delshare",results={@Result(name="success",type="json")})
	public String delshare() throws Exception  
	{  
		Share share=new Share();
		share.setId(id);
		try {
			this.baseservice.delete(share);
			state="0";
		} catch (Exception e) {
			// TODO: handle exception
			state="1";
		}
		
	    return "success";
	
	}
	
	public static Map<String, String> getMap(){
		
		 
		Map<String, String> map=new HashMap<String, String>();
		map.put("1", "9");
		map.put("2", "b");
		map.put("3", "c");
		map.put("4", "d");
		map.put("5", "e");
		map.put("6", "f");
		map.put("7", "g");
		map.put("8", "h");
		map.put("9", "i");
		map.put("10", "j");
		map.put("11", "k");
		map.put("12", "l");
		map.put("13", "m");
		map.put("14", "n");
		map.put("15", "o");
		return map;
		
	}
}  