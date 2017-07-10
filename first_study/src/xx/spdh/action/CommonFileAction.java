/*
 *@(#)xx.spdh.action
 *@UploadAction.java.java  
 *@创建时间:2011-8-3下午04:27:42
 *@作者：朱永科
 *@Copyright 2009-2010 河北北方学院信息科学与工程学院科研所 
 */

package xx.spdh.action;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import xx.collection.bean.CommonFile;
import xx.collection.bean.RecentlyViewed;
import xx.collection.bean.Scwj;
import xx.quanxian.service.BaseService;

import com.googlecode.jsonplugin.annotations.JSON;
import com.opensymphony.xwork2.ActionSupport;

/**
 * @ScwjAction <code>{类名称}</code>
 * @author  {作者}
 * @version {版本,常用时间代替}
 * @{功能描述:视频动画的增删改查} 
 */

@Controller
@Scope("prototype")
@Namespace("")
@ParentPackage("default-package")
@SuppressWarnings("serial")
public class CommonFileAction extends ActionSupport{

	private String jsessionid;
	private List<CommonFile>list=new ArrayList<CommonFile>();
	private List<RecentlyViewed>list2=new ArrayList<RecentlyViewed>();
    private int id;
    private String state;
    private Integer zlid;
    
	


	/**
	 * @return the zlid
	 */
	public Integer getZlid() {
		return zlid;
	}

	/**
	 * @param zlid the zlid to set
	 */
	public void setZlid(Integer zlid) {
		this.zlid = zlid;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public List<RecentlyViewed> getList2() {
		return list2;
	}

	public void setList2(List<RecentlyViewed> list2) {
		this.list2 = list2;
	}

	@Resource(name="baseService")
	private BaseService baseservice;
	
	@JSON(serialize=false)
	public String getJsessionid() {
		return jsessionid;
	}

	public void setJsessionid(String jsessionid) {
		this.jsessionid = jsessionid;
	}


	public List<CommonFile> getList() {
		return list;
	}

	public void setList(List<CommonFile> list) {
		this.list = list;
	}

	@Action(value="getCommonFile",results={@Result(name="success",type="json")})
	public String uploadFile() {
		HttpSession hs = ServletActionContext.getRequest().getSession();
		String userid = (String)hs.getAttribute("uid");
		System.out.println("userid:"+userid);
		String[] keys={"uid"};
		Object[] values={userid};
	    
		list=this.baseservice.find(CommonFile.class, "CommonFile", keys, values);
		
		return SUCCESS;
	}
	
	@Action(value="getRecentview",results={@Result(name="success",type="json")})
	public String getRecentview() {
		HttpSession hs = ServletActionContext.getRequest().getSession();
		String userid = (String)hs.getAttribute("uid");
		System.out.println("userid:"+userid);
		String[] keys={"uid"};
		Object[] values={userid};
	    
		list2=this.baseservice.find(RecentlyViewed.class, "RecentlyViewed", keys, values);
		
		return SUCCESS;
	}
	
	@Action(value="delCommonFile",results={@Result(name="success",type="json")})
	public String delCommonFile() {
		CommonFile c=new CommonFile();
		c.setId(id);
		try {
			this.baseservice.delete(c);
			state="0";
		} catch (Exception e) {
			// TODO: handle exception
			state="-1";
		}
		
		return SUCCESS;
	}
	
	@Action(value="addCommonFile",results={@Result(name="success",type="json")})
	public String addCommonFile() {
		HttpSession hs = ServletActionContext.getRequest().getSession();
		String userid = (String)hs.getAttribute("uid");
		String[] keys2={"uid","zlid"};
		Object[] values2={userid,zlid};
		List<CommonFile> lists=this.baseservice.find(CommonFile.class, "CommonFile", keys2, values2);
		
		if (lists.size()!=0) {
			state="1";
			return SUCCESS;
		}
		CommonFile c=new CommonFile();
		String[] keys={"dmtzl.zlbh"};
		Object[] values={zlid};
		Scwj scwj=this.baseservice.find(Scwj.class, "Scwj", keys, values).get(0);
		c.setZlid(zlid);
		c.setPath(scwj.getFilepath());
		c.setUid(userid);
		c.setZlmc(scwj.getFilename());
		
		try {
			this.baseservice.save(c);
			state="0";
		} catch (Exception e) {
			// TODO: handle exception
			state="-1";
		}
		
		return SUCCESS;
	}
	
	@Action(value="addRecentlyviewed",results={@Result(name="success",type="json")})
	public String addRecentlyviewed() {
		HttpSession hs = ServletActionContext.getRequest().getSession();
		String userid = (String)hs.getAttribute("uid");
		RecentlyViewed c=new RecentlyViewed();
		String[] keys={"dmtzl.zlbh"};
		Object[] values={zlid};
		
		System.out.println("资料id："+zlid);
		Scwj scwj=this.baseservice.find(Scwj.class, "Scwj", keys, values).get(0);
		c.setZlid(zlid);
		c.setPath(scwj.getFilepath());
		c.setUid(userid);
		c.setZlmc(scwj.getFilename());
		SimpleDateFormat df = new SimpleDateFormat("MM-dd HH:mm");//设置日期格式
		System.out.println(df.format(new Date()));// new Date()为获取当前系统时间
		c.setViewtime(df.format(new Date()));
		List<RecentlyViewed> list=this.baseservice.find(RecentlyViewed.class);
		if (list==null||list.size()==0) {		
		}else {
			if (list.size()>=6) {
				this.baseservice.delete(list.get(0));
			}
			
		}
		
		this.baseservice.save(c);
		return SUCCESS;
	}
}
