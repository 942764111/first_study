/*
 *@(#)xx.spdh.action
 *@onlineUser.java.java  
 *@����ʱ��:2016-4-19����9:37:11
 *@���ߣ�������
 *@Copyright 2009-2010 �ӱ�����ѧԺ��Ϣ��ѧ�빤��ѧԺ������ 
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
 * @onlineUser <code>{������}</code>
 * @author  {������}
 * @version {�汾,����ʱ�����}
 * @{��������:} 
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
