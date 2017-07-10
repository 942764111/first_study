/*
 *@(#)xx.spdh.action
 *@UploadAction.java.java  
 *@����ʱ��:2011-11-12����08:43:29
 *@���ߣ�ZYK
 *@Copyright 2009-2010 �ӱ�����ѧԺ��Ϣ��ѧ�빤��ѧԺ������ 
 */

package xx.collect.action;

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
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import xx.collection.bean.Collect;
import xx.collection.bean.MapNode;
import xx.collection.bean.Scwj;
import xx.collection.bean.Zsd2;
import xx.quanxian.service.BaseService;

import com.opensymphony.xwork2.ActionSupport;

/**
 * @UploadAction <code>{�����}</code>
 * @author  {����}
 * @version {�汾,����ʱ�����}
 * @{��������:} 
 */
@Controller
@Scope("prototype")
@Namespace("")
@ParentPackage("default-package")
@SuppressWarnings("serial")
public class CollectAction2 extends ActionSupport{
	
	@Resource(name="baseService")
	private BaseService baseservice;
	private String state;
	private String scsj;
	private String zymc;
	private String zyms;
	private String zlid;
	private String path;
	private Integer id;
	private List<Collect>rows=new ArrayList<Collect>();
	public String getState() {
		return state;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public void setState(String state) {
		this.state = state;
	}
	public String getScsj() {
		return scsj;
	}

	public void setScsj(String scsj) {
		this.scsj = scsj;
	}

	public String getZymc() {
		return zymc;
	}

	public void setZymc(String zymc) {
		this.zymc = zymc;
	}

	public String getZyms() {
		return zyms;
	}

	public void setZyms(String zyms) {
		this.zyms = zyms;
	}

	public String getZlid() {
		return zlid;
	}

	public void setZlid(String zlid) {
		this.zlid = zlid;
	}

	public List<Collect> getRows() {
		return rows;
	}

	public void setRows(List<Collect> rows) {
		this.rows = rows;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Action(value="/addCollect",results={@Result(name="success",type="json")})
	public String uploadJieTu1(){
		
		HttpSession hs = ServletActionContext.getRequest().getSession();
		String userid = (String)hs.getAttribute("uid");
        
		List<Collect> lString=this.baseservice.find(Collect.class);
		System.out.println("Isting��"+lString.size());
		if (lString.size()==0) {
			System.out.println("ִ��1");
			Collect collect=new Collect();
	        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//�������ڸ�ʽ
	        collect.setScsj(df.format(new Date()));
	        collect.setUserid(userid);
	        collect.setZlid(zlid);
	        collect.setPath(path);
	        
	        String[] keys={"zlbh"};
			Object[] values={zlid};
			List<Scwj> swc=this.baseservice.find(Scwj.class, "Scwj", keys, values);
			Scwj scwj=swc.get(0);
			zyms=scwj.getZlms();
			zymc=scwj.getFilename();
			collect.setZymc(zymc);
	        collect.setZyms(zyms);
	        collect.setFiletype(scwj.getFiletype());
	        collect.setZsdid(scwj.getZsdid());
	        try {
				this.baseservice.save(collect);
				state="0";
			} catch (Exception e) {
				// TODO: handle exception
				state="-1";
			}
		} else {
			System.out.println("ִ��2");
			String[] keys2={"zlid","userid"};
			Object[] value2={zlid,userid};
			List<Collect>list=this.baseservice.find(Collect.class, "Collect", keys2, value2);		
			System.out.println("list.size:"+list.size());
			if (list.size()!=0) {
				state="1";
				return SUCCESS;
			}
			
			Collect collect=new Collect();
	        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//�������ڸ�ʽ
	        collect.setScsj(df.format(new Date()));
	        collect.setUserid(userid);
	        collect.setZlid(zlid);
	        collect.setPath(path);
	        
	        String[] keys={"zlbh"};
			Object[] values={zlid};
			List<Scwj> swc=this.baseservice.find(Scwj.class, "Scwj", keys, values);
			Scwj scwj=swc.get(0);
			zyms=scwj.getZlms();
			zymc=scwj.getFilename();
			
			collect.setZymc(zymc);
	        collect.setZyms(zyms);
	        collect.setZsdid(scwj.getZsdid());
	        collect.setFiletype(scwj.getFiletype());
	        
	        String zsdid=scwj.getZsdid();
	        
	        
	        String kcmc=scwj.getKcmc();
			//֪ʶ��ͱʼ������
			List<MapNode> mapNodes=this.baseservice.find(MapNode.class);
			
			int size=(zsdid.length()-1)/4;
		
			if (mapNodes==null||mapNodes.size()<=0) {

				for (int k = 0; k < size; k++) {
					System.out.println("k��"+k);
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
					mapNode.setUserid(userid);

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
					Object[] values13={nodeid,userid};
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
	                mapNode2.setUserid(userid);
	                this.baseservice.save(mapNode2);
					} else {
						break;
					}
				}

			}
			
			
			
			
			
			
	        
	        
	        
	        
	        
	        
	        
	        
	        
	        try {
				this.baseservice.save(collect);
				state="0";
			} catch (Exception e) {
				// TODO: handle exception
				state="-1";
			}
		}
		
		
		
		return SUCCESS;
	}
	
	@Action(value="/getCollect",results={@Result(name="success",type="json")})
	public String getCollect(){
		
		HttpSession hs = ServletActionContext.getRequest().getSession();
		String userid = (String)hs.getAttribute("uid");
        String[] keys={"userid"};
		Object[] values={userid};
		rows=this.baseservice.find(Collect.class, "Collect", keys, values);
		
		if (rows==null||rows.size()==0) {
			return SUCCESS;
		}
		for (int i = 0; i < rows.size(); i++) {
			Collect collect=rows.get(i);
			collect.setFilepath(collect.getPath());
			String ofileName=collect.getPath();
			String type=ofileName.substring(ofileName.lastIndexOf("."));
			
			
			if(type.equals(".flv")){
				collect.setPath("http://80516da.nat123.net/page/share/shareVideo.jsp?filepath="+collect.getPath());
			
			}else{
				collect.setPath("http://80516da.nat123.net/page/share/shareFile.jsp?filepath="+collect.getPath());
					
			}
			}
		
		return SUCCESS;
	}
	
	@Action(value="/delCollect",results={@Result(name="success",type="json")})
	public String delCollect(){
		Collect collect=new Collect();
		collect.setId(id);
		try {
			this.baseservice.delete(collect);
			state="0";
		} catch (Exception e) {
			// TODO: handle exception
			state="1";
		}
		return SUCCESS;
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
