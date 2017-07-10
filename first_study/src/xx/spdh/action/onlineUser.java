/*
 *@(#)xx.spdh.action
 *@onlineUser.java.java  
 *@创建时间:2016-4-19下午9:37:11
 *@作者：朱永科
 *@Copyright 2009-2010 河北北方学院信息科学与工程学院科研所 
 */

package xx.spdh.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;

import com.opensymphony.xwork2.ActionSupport;

import cn.mina.ChatServer;

/**
 * @onlineUser <code>{类名称}</code>
 * @author  {朱永科}
 * @version {版本,常用时间代替}
 * @{功能描述:} 
 */

public class onlineUser extends ActionSupport  {

	private List<String> list=new ArrayList<String>();
	
	public List<String> getList() {
		return list;
	}

	public void setList(List<String> list) {
		this.list = list;
	}

	@Action(value="onlineUser",results={@Result(name="success",type="json")})
	public String userAction(){
		Map<Long, String> da=ChatServer.userInfo;
		Object s[] = da.keySet().toArray();
		for(int i = 0; i < da.size(); i++) {
			
			list.add(da.get(s[i]));
		}
		return SUCCESS;
		
	}

}
