/*
 *@(#)xx.spdh.action
 *@UploadAction.java.java  
 *@创建时间:2011-11-12上午08:43:29
 *@作者：ZYK
 *@Copyright 2009-2010 河北北方学院信息科学与工程学院科研所 
 */

package xx.collect.action;

import java.util.ArrayList;
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

import xx.collection.bean.Scwj;
import xx.quanxian.service.BaseService;

import com.opensymphony.xwork2.ActionSupport;

/**
 * @UploadAction <code>{类名称}</code>
 * @author  {作者}
 * @version {版本,常用时间代替}
 * @{功能描述:} 
 */
@Controller
@Scope("prototype")
@Namespace("")
@ParentPackage("default-package")
@SuppressWarnings("serial")
public class ZiliaoAction extends ActionSupport{
	
	@Resource(name="baseService")
	private BaseService baseservice;
	 
	private String state;
	private String no;

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getNo() {
		return no;
	}

	public void setNo(String no) {
		this.no = no;
	}

	private List<Map<String, String>>list=new ArrayList<Map<String,String>>();
	

	public List<Map<String, String>> getList() {
		return list;
	}

	public void setList(List<Map<String, String>> list) {
		this.list = list;
	}



	@Action(value="/getZiliao",results={@Result(name="success",type="json")})
	public String getZiliao(){
		HttpSession hs = ServletActionContext.getRequest().getSession();
		String userid = (String)hs.getAttribute("uid");

		String hql="from Scwj where userId='"+userid+"'";
		System.out.println("hql:"+hql);
		List<Scwj>da=this.baseservice.getList(Scwj.class, hql);
		System.err.println(da.size());
		if (da.size()==0) {
			return null;
		}
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
		
		return SUCCESS;
	}
	
	@Action(value="/delZiliao",results={@Result(name="success",type="json")})
	public String delZiliao(){
		state="0";
		Scwj swcj=new Scwj();
		
		try {
			String[] keys={"zlbh"};
			Object[] values={no};
			
			Integer integer=this.baseservice.find(Scwj.class, "Scwj", keys, values).get(0).getNo();
			swcj.setNo(integer);
			this.baseservice.delete(swcj);
			
		} catch (Exception e) {
			// TODO: handle exception
			state="1";
		}
		
		
		return SUCCESS;
	}

}
