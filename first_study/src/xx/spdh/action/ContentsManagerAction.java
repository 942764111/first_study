/*
 *@(#)xx.spdh.action
 *@HuoqumuluAction.java.java  
 *@创建时间:2011-11-20下午01:14:23
 *@作者：ZYK
 *@Copyright 2009-2010 河北北方学院信息科学与工程学院科研所 
 */

package xx.spdh.action;

import java.util.ArrayList;
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

import xx.collection.bean.Zidingyibijimulu;
import xx.quanxian.service.BaseService;

import com.googlecode.jsonplugin.annotations.JSON;
import com.opensymphony.xwork2.ActionSupport;

/**
 * @HuoqumuluAction <code>{类名称}</code>
 * @author  {作者}
 * @version {版本,常用时间代替}
 * @{功能描述:获取数据库中目录结构} 
 */
@Controller
@Scope("prototype")
@Namespace("")
@ParentPackage("default-package")
@SuppressWarnings("serial")
public class ContentsManagerAction extends ActionSupport {

	@Resource(name="baseService")
	private BaseService baseservice;
	
	private List<Zidingyibijimulu> zdybjml_list = new ArrayList<Zidingyibijimulu>();
	private String treedata;
	
	private String two_content_name;
	private String three_content_name;
	
	private String beizhu2;
	private String beizhu3;
	
	/**
	 * @return the baseservice
	 */
	@JSON(serialize=false)
	public BaseService getBaseservice() {
		return baseservice;
	}

	/**
	 * @param baseservice the baseservice to set
	 */
	public void setBaseservice(BaseService baseservice) {
		this.baseservice = baseservice;
	}

	/**
	 * @return the zdybjml_list
	 */
	@JSON(serialize=false)
	public List<Zidingyibijimulu> getZdybjml_list() {
		return zdybjml_list;
	}

	/**
	 * @param zdybjml_list the zdybjml_list to set
	 */
	public void setZdybjml_list(List<Zidingyibijimulu> zdybjml_list) {
		this.zdybjml_list = zdybjml_list;
	}

	/**
	 * @return the treedata
	 */
	public String getTreedata() {
		return treedata;
	}

	/**
	 * @param treedata the treedata to set
	 */
	public void setTreedata(String treedata) {
		this.treedata = treedata;
	}

	/**
	 * @return the two_content_name
	 */
	@JSON(serialize=false)
	public String getTwo_content_name() {
		return two_content_name;
	}

	/**
	 * @param two_content_name the two_content_name to set
	 */
	public void setTwo_content_name(String two_content_name) {
		this.two_content_name = two_content_name;
	}

	/**
	 * @return the three_content_name
	 */
	@JSON(serialize=false)
	public String getThree_content_name() {
		return three_content_name;
	}

	/**
	 * @param three_content_name the three_content_name to set
	 */
	public void setThree_content_name(String three_content_name) {
		this.three_content_name = three_content_name;
	}

	/**
	 * @return the beizhu2
	 */
	@JSON(serialize=false)
	public String getBeizhu2() {
		return beizhu2;
	}

	/**
	 * @param beizhu2 the beizhu2 to set
	 */
	public void setBeizhu2(String beizhu2) {
		this.beizhu2 = beizhu2;
	}

	/**
	 * @return the beizhu3
	 */
	@JSON(serialize=false)
	public String getBeizhu3() {
		return beizhu3;
	}

	/**
	 * @param beizhu3 the beizhu3 to set
	 */
	public void setBeizhu3(String beizhu3) {
		this.beizhu3 = beizhu3;
	}

	/**
	 * 此action用来处理用户进入笔记目录管理时，先查询该用户建立了什么目录
	 * 并将以创建的目录显示在前台。
	*/
	@Action(value="/search_mulu", results={@Result(name="success", type="json")})
	public String search_mulu() {
		
		HttpSession hs = ServletActionContext.getRequest().getSession();
		String userid = (String)hs.getAttribute("uid");
		
		System.out.println("userid:"+userid);
		//根据userid查询含有该userid的这些记录
		String[] keys1 = new String[1];
		keys1[0] = "userinfo.userId";
		Object[] values1 = new Object[1];
		values1[0] = userid;
		zdybjml_list = this.baseservice.find(Zidingyibijimulu.class, "Zidingyibijimulu", keys1, values1);
		
		List<String> list1=new ArrayList<String>();
		List<String> list2=new ArrayList<String>();
		List<String> list3=new ArrayList<String>();
		System.out.println("zdybjml_list:"+zdybjml_list);
		for(int i=0;i<zdybjml_list.size();i++) {
			
			//获取目录classno
			String classno=new String();
			classno= zdybjml_list.get(i).getId().getClassno();
			
			if(classno.length()== 3){
				//将一级目录classno放入list1中
				list1.add(classno);
			}else if(classno.length()== 7){
				//将二级目录classno放入list2中
				list2.add(classno);
			}else if(classno.length()== 11){
				//将三级目录classno放入list3中
				list3.add(classno);
			}
		}		
		//根据一级目录classno查找第二级目录classno
		treedata="<?xml version='1.0' encoding='utf-8'?>\n";  
		for(int x=0;x<list1.size();x++){
			
			String[] keys2 = new String[1];
			keys2[0] = "classno";
			Object[] values2 = new Object[1];
			values2[0] = list1.get(x);
			//List<String> classno1 = this.baseservice.find(String.class, "Zidingyibijimulu", "classname", keys2, values2);
			String[] keys={"id.userId","id.classno"};
			Object[] values={userid,list1.get(x)};
			List<Zidingyibijimulu> classno1 = this.baseservice.find(Zidingyibijimulu.class, "Zidingyibijimulu", keys, values);
			String name=classno1.get(0).getClassname();
			//treedata+="<node id='1' value='"+list1.get(x)+"'>\n"; 
			treedata+="<node id='"+ list1.get(x).toString().replaceAll("\t", "").replaceAll(" ", "").replaceAll("\n", "").replaceAll("\r", "") +"' value='"+name.replaceAll("\t", "").replaceAll(" ", "").replaceAll("\n", "").replaceAll("\r", "")+"' classno='"+ list1.get(x).replaceAll("\t", "").replaceAll(" ", "").replaceAll("\n", "").replaceAll("\r", "") +"'>\n";
			for(int y=0;y<list2.size();y++){
				if(list2.get(y).substring(0,3).equals(list1.get(x)))
				{
					String[] keys3 = new String[1];
					keys3[0] = "classno";
					Object[] values3 = new Object[1];
					values3[0] = list2.get(y);
					
					String[] keys4={"id.userId","id.classno"};
					Object[] values4={userid,list2.get(y)};
					List<Zidingyibijimulu> classno2 = this.baseservice.find(Zidingyibijimulu.class, "Zidingyibijimulu", keys4, values4);
					String name2=classno2.get(0).getClassname();
					//treedata+="<node id='2' value='"+list2.get(y).substring(4, 7)+"'>\n"; 
					treedata+="    <node id='"+ list2.get(y).substring(4, 7).replaceAll("\t", "").replaceAll(" ", "").replaceAll("\n", "").replaceAll("\r", "") +"' value='"+name2.replaceAll("\t", "").replaceAll(" ", "").replaceAll("\n", "").replaceAll("\r", "")+"' classno='"+ list2.get(y).replaceAll("\t", "").replaceAll(" ", "").replaceAll("\n", "").replaceAll("\r", "") +"' >\n"; 
				    for(int z=0;z<list3.size();z++){
					     if(list3.get(z).substring(0,7).equals(list2.get(y)))
					     {
//					    	String[] keys4 = new String[1];
//							keys4[0] = "classno";
//							Object[] values4 = new Object[1];
//							values4[0] = list3.get(z);
//							List<String> classno3 = this.baseservice.find(String.class, "Zidingyibijimulu", "classname", keys4, values4);
					    	  String[] keys5={"id.userId","id.classno"};
								Object[] values5={userid,list3.get(z)};
								List<Zidingyibijimulu> classno3 = this.baseservice.find(Zidingyibijimulu.class, "Zidingyibijimulu", keys5, values5);
								String name3=classno3.get(0).getClassname();
					    	 //treedata+="<node id='3' value='"+list3.get(z).substring(8, 11)+"'>";
					    	 treedata+="        <node id='"+ list3.get(z).substring(8, 11).replaceAll("\t", "").replaceAll(" ", "").replaceAll("\n", "").replaceAll("\r", "") +"' value='"+name3.replaceAll("\t", "").replaceAll(" ", "").replaceAll("\n", "").replaceAll("\r", "")+"' classno='"+ list3.get(z).replaceAll("\t", "").replaceAll(" ", "").replaceAll("\n", "").replaceAll("\r", "") +"'>";
					    	 treedata+="</node>\n";
					     }
					}
				    treedata+="    </node>\n";
				}
			}
			treedata+="</node>\n";
		}
		return SUCCESS;
	}
}
