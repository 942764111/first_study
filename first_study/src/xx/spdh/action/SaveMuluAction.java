/*
 *@(#)xx.spdh.action
 *@SaveMuluAction.java.java  
 *@创建时间:2011-11-23下午08:36:34
 *@作者：ZYK
 *@Copyright 2009-2010 河北北方学院信息科学与工程学院科研所 
 */

package xx.spdh.action;

import java.io.UnsupportedEncodingException;
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

import xx.collection.bean.Zidingyibijimulu;
import xx.collection.bean.ZidingyibijimuluId;
import xx.quanxian.service.BaseService;

import com.googlecode.jsonplugin.annotations.JSON;
import com.opensymphony.xwork2.ActionSupport;

/**
 * @SaveMuluAction <code>{类名称}</code>
 * @author  {作者}
 * @version {版本,常用时间代替}
 * @{功能描述:} 
 */
@Controller
@Scope("prototype")
@Namespace("")
@ParentPackage("default-package")
@SuppressWarnings("serial")
public class SaveMuluAction extends ActionSupport {

	@Resource(name="baseService")
	private BaseService baseservice;

	private String s;
	private ArrayList<Map<String, String>> mulu1list = new ArrayList<Map<String, String>>();
	private ArrayList<Map<String, String>> mulu2list = new ArrayList<Map<String, String>>();
	private ArrayList<String> searchyymllist = new ArrayList<String>();

	private String one_classno;
	private String one_content_name;
	private String beizhu1;

	private String two_classno;
	private String two_content_name;
	private String beizhu2;

	private String three_classno;
	private String three_content_name;
	private String beizhu3;

	private String mulu1SelectItem;//根据用户选择一级目录的变量

	private String classno;
	private String classname;

	private String state;
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
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
	 * @return the s
	 */
	public String getS() {
		return s;
	}
	/**
	 * @param s the s to set
	 */
	public void setS(String s) {
		this.s = s;
	}


	/**
	 * @return the mulu1list
	 */

	/**
	 * @return the mulu2list
	 */

	public ArrayList<Map<String, String>> getMulu1list() {
		return mulu1list;
	}
	public void setMulu1list(ArrayList<Map<String, String>> mulu1list) {
		this.mulu1list = mulu1list;
	}


	public ArrayList<Map<String, String>> getMulu2list() {
		return mulu2list;
	}
	public void setMulu2list(ArrayList<Map<String, String>> mulu2list) {
		this.mulu2list = mulu2list;
	}
	/**
	 * @return the searchyymllist
	 */
	public ArrayList<String> getSearchyymllist() {
		return searchyymllist;
	}
	/**
	 * @param searchyymllist the searchyymllist to set
	 */
	public void setSearchyymllist(ArrayList<String> searchyymllist) {
		this.searchyymllist = searchyymllist;
	}
	/**
	 * @return the one_classno
	 */
	@JSON(serialize=false)
	public String getOne_classno() {
		return one_classno;
	}
	/**
	 * @param one_classno the one_classno to set
	 */
	public void setOne_classno(String one_classno) {
		this.one_classno = one_classno;
	}

	/**
	 * @return the one_content_name
	 */
	@JSON(serialize=false)
	public String getOne_content_name() {
		return one_content_name;
	}

	/**
	 * @param one_content_name the one_content_name to set
	 */
	public void setOne_content_name(String one_content_name) {
		this.one_content_name = one_content_name;
	}
	/**
	 * @return the beizhu1
	 */
	@JSON(serialize=false)
	public String getBeizhu1() {
		return beizhu1;
	}
	/**
	 * @param beizhu1 the beizhu1 to set
	 */
	public void setBeizhu1(String beizhu1) {
		this.beizhu1 = beizhu1;
	}

	/**
	 * @return the two_classno
	 */
	@JSON(serialize=false)
	public String getTwo_classno() {
		return two_classno;
	}
	/**
	 * @param two_classno the two_classno to set
	 */
	public void setTwo_classno(String two_classno) {
		this.two_classno = two_classno;
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
	 * @return the three_classno
	 */
	public String getThree_classno() {
		return three_classno;
	}
	/**
	 * @param three_classno the three_classno to set
	 */
	public void setThree_classno(String three_classno) {
		this.three_classno = three_classno;
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
	 * @return the mulu1SelectItem
	 */
	@JSON(serialize=false)
	public String getMulu1SelectItem() {
		return mulu1SelectItem;
	}
	/**
	 * @param mulu1SelectItem the mulu1SelectItem to set
	 */
	public void setMulu1SelectItem(String mulu1SelectItem) {
		this.mulu1SelectItem = mulu1SelectItem;
	}

	/**
	 * @return the classno
	 */
	@JSON(serialize=false)
	public String getClassno() {
		return classno;
	}
	/**
	 * @param classno the classno to set
	 */
	public void setClassno(String classno) {
		this.classno = classno;
	}

	/**
	 * @return the classname
	 */
	@JSON(serialize=false)
	public String getClassname() {
		return classname;
	}
	/**
	 * @param classname the classname to set
	 */
	public void setClassname(String classname) {
		this.classname = classname;
	}

	/**
	 * 此action用来查看该用户已建一级目录
	 */
	@Action(value="/searchyyml", results={@Result(name="success", type="json")})
	public String searchyyml() {
		HttpSession hs = ServletActionContext.getRequest().getSession();
		String userid = (String)hs.getAttribute("uid");

		String hql = "select classname from Zidingyibijimulu where userId='" + userid + "'and classno like'___'";
		List<String> classname = this.baseservice.findHql(String.class, hql);
		for(int i=0;i<classname.size();i++) {
			searchyymllist.add(classname.get(i));
		}
		return SUCCESS;
	}
	/**
	 * 此action用来处理每一位用户输入的一级目录名称是否已存在
	 */
	@Action(value="/checkmulu1", results={@Result(name="success", type="json")})
	public String chheckmulu1(){
		HttpSession hs = ServletActionContext.getRequest().getSession();
		String userid = (String)hs.getAttribute("uid");

		try {
			one_content_name = new String(one_content_name.getBytes("ISO-8859-1"),"utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		String hql = "select classname from Zidingyibijimulu where userId='" + userid + "'and classno like '___'";
		List<String> classname = this.baseservice.findHql(String.class, hql);
		if(classname.contains(one_content_name.replaceAll("\t", "").replaceAll(" ", "").replaceAll("\n", "").replaceAll("\r", ""))) {
			s = "0";
		} else {
			s = "1";
		}
		return SUCCESS;
	}

	/**
	 * 此action用来处理用户添加一级目录
	 */
	@Action(value="/savemulu1", results={@Result(name="success", type="json")})
	public String savemulu1(){
		HttpSession hs = ServletActionContext.getRequest().getSession();
		String userid = (String)hs.getAttribute("uid");
		String hql = "select count(*) from Zidingyibijimulu where userId='"+userid + "'and classno like '___'";
		int total = this.baseservice.getTotalSql(hql);
		int total1 = total + 1;
		if(total<10) {
			one_classno = "00" + total1;
		} else if(total>10&&total<100) {
			one_classno = "0" + total1;
		} else if(total>=100&&total<1000) {
			one_classno = String.valueOf(total);
		}

		try {
			one_content_name = new String(one_content_name.getBytes("ISO-8859-1"),"utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		ZidingyibijimuluId zdybjmlId = new ZidingyibijimuluId();
		zdybjmlId.setUserId(userid);
		zdybjmlId.setClassno(one_classno);
		Zidingyibijimulu zdybjml = new Zidingyibijimulu();
		zdybjml.setId(zdybjmlId);
		zdybjml.setClassname(one_content_name.replaceAll("\t", "").replaceAll(" ", "").replaceAll("\n", "").replaceAll("\r", ""));
		zdybjml.setBeizhu(beizhu1);
		classno=one_classno;
		this.baseservice.save(zdybjml);
		
		//更新 
		return SUCCESS;
	}

	/**
	 * 此action用来处理用户添加二级目录时，供用户选择的一级目录列表
	 */
	@Action(value="/huoqumulu1", results={@Result(name="success", type="json")})
	public String huoqumulu1() {
		HttpSession hs = ServletActionContext.getRequest().getSession();
		String userid = (String)hs.getAttribute("uid");

		String hql = "from Zidingyibijimulu where userId='" + userid + "'and classno like '___'";
		//List<Map> classnamelist = this.baseservice.findHql(Map.class, hql);
		List<Zidingyibijimulu> list=this.baseservice.findHql(Zidingyibijimulu.class, hql);
		System.out.println("classnamelist:"+list);
		for (int i = 0; i < list.size(); i++) {
			Zidingyibijimulu zidingyibijimulu=list.get(i);
			Map<String, String> map=new HashMap<String, String>();
			map.put("classname", zidingyibijimulu.getClassname());
			map.put("classno", zidingyibijimulu.getId().getClassno());
			mulu1list.add(map);
		}

		return SUCCESS;
	}

	/**
	 * 此action用来处理每一位用户输入的二级目录名称是否已存在
	 */
	@Action(value="/checkmulu2", results={@Result(name="success", type="json")})
	public String chheckmulu2() {
		HttpSession hs = ServletActionContext.getRequest().getSession();
		String userid = (String)hs.getAttribute("uid");

		try {
			one_content_name = new String(one_content_name.getBytes("ISO-8859-1"),"utf-8");
			two_content_name = new String(two_content_name.getBytes("ISO-8859-1"),"utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		String[] keys1 = new String[2];
		keys1[0] = "userId";
		keys1[1] = "classname";
		Object[] values1 = new Object[2];
		values1[0] = userid;
		values1[1] = one_content_name.replaceAll("\t", "").replaceAll(" ", "").replaceAll("\n", "").replaceAll("\r", "");
		List<String> classno = this.baseservice.find(String.class, "Zidingyibijimulu", "id.classno", keys1, values1);
		String s1 = new String();
		s1 = classno.get(0);

		String hql = "select classname from Zidingyibijimulu where userId='" + userid + "'and classno like '_______' and substr(classno,1,3)=" + s1;
		List<String> classname = this.baseservice.findHql(String.class, hql);
		if(classname.contains(two_content_name.replaceAll("\t", "").replaceAll(" ", "").replaceAll("\n", "").replaceAll("\r", ""))) {
			s = "0";
		} else {
			s = "1";
		}
		return SUCCESS;
	}

	/**
	 * 此action用来处理用户添加二级目录
	 */
	@Action(value="/savemulu2", results={@Result(name="success", type="json")})
	public String savemulu2() {

		HttpSession hs = ServletActionContext.getRequest().getSession();
		String userid = (String)hs.getAttribute("uid");

		try {
			one_content_name = new String(one_content_name.getBytes("ISO-8859-1"),"utf-8");
			two_content_name = new String(two_content_name.getBytes("ISO-8859-1"),"utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		String[] keys1 = new String[2];
		keys1[0] = "userId";
		keys1[1] = "classname";
		Object[] values1 = new Object[2];
		values1[0] = userid;
		values1[1] = one_content_name.replaceAll("\t", "").replaceAll(" ", "").replaceAll("\n", "").replaceAll("\r", "");
		List<String> classno2 = this.baseservice.find(String.class, "Zidingyibijimulu", "id.classno", keys1, values1);
		String s1 = new String();
		s1 = classno2.get(0);

		String hql = "select id.classno from Zidingyibijimulu where userId='" + userid + "'and classno like '_______'";
		List<String> classno1 = this.baseservice.findHql(String.class, hql);
		int total = 0;
		for(int i=0;i<classno1.size();i++) {
			String s2 = new String();
			s2 = classno1.get(i).substring(0, 3);
			if(s2.equals(s1)) {
				total++;
			}
		}
		int total1 = total + 1;
		if(total<10) {
			two_classno = classno2.get(0) + "," +"00" + total1;
		} else if(total>10&&total<100) {
			two_classno = classno2.get(0) + "," +"0" + total1;
		} else if(total>=100&&total<1000) {
			two_classno = classno2.get(0) + "," + String.valueOf(total);
		}

		ZidingyibijimuluId zdybjmlId = new ZidingyibijimuluId();
		zdybjmlId.setUserId(userid);
		zdybjmlId.setClassno(two_classno);
		Zidingyibijimulu zdybjml = new Zidingyibijimulu();
		zdybjml.setId(zdybjmlId);
		zdybjml.setClassname(two_content_name.replaceAll("\t", "").replaceAll(" ", "").replaceAll("\n", "").replaceAll("\r", ""));
		zdybjml.setBeizhu(beizhu2);
		this.baseservice.save(zdybjml);
		classno=two_classno;
		return SUCCESS;
	}



	@Action(value="/newmulu", results={@Result(name="success", type="json")})
	public String newmulu() {

		HttpSession hs = ServletActionContext.getRequest().getSession();
		String userid = (String)hs.getAttribute("uid");

		//检查一级目录是否重复
		try {
			one_content_name = new String(one_content_name.getBytes("ISO-8859-1"),"utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		String hql5 = "select classname from Zidingyibijimulu where userId='" + userid + "'and classno like '___'";
		List<String> classname = this.baseservice.findHql(String.class, hql5);
		if(classname.contains(one_content_name.replaceAll("\t", "").replaceAll(" ", "").replaceAll("\n", "").replaceAll("\r", ""))) {
			state = "1";//一级目录已存在
			return SUCCESS;
		} 


		//保存一级目录
		String hql = "select count(*) from Zidingyibijimulu where userId='"+userid + "'and classno like '___'";
		int total = this.baseservice.getTotalSql(hql);
		int total1 = total + 1;
		if(total<10) {
			one_classno = "00" + total1;
		} else if(total>10&&total<100) {
			one_classno = "0" + total1;
		} else if(total>=100&&total<1000) {
			one_classno = String.valueOf(total);
		}


		ZidingyibijimuluId zdybjmlId = new ZidingyibijimuluId();
		zdybjmlId.setUserId(userid);
		zdybjmlId.setClassno(one_classno);
		Zidingyibijimulu zdybjml = new Zidingyibijimulu();
		zdybjml.setId(zdybjmlId);
		zdybjml.setClassname(one_content_name.replaceAll("\t", "").replaceAll(" ", "").replaceAll("\n", "").replaceAll("\r", ""));
		zdybjml.setBeizhu(beizhu1);
		classno=one_classno;
		this.baseservice.save(zdybjml);

		state="0";
		//判断二级目录是否为空
		if (two_content_name==null||two_content_name.equals("")) {

			return SUCCESS;
		}

		//检查二级目录是否重复
		try {
			two_content_name = new String(two_content_name.getBytes("ISO-8859-1"),"utf-8");
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		String[] keys2 = new String[2];
		keys2[0] = "userId";
		keys2[1] = "classname";
		Object[] values2 = new Object[2];
		values2[0] = userid;
		values2[1] = one_content_name.replaceAll("\t", "").replaceAll(" ", "").replaceAll("\n", "").replaceAll("\r", "");
		List<String> classno3 = this.baseservice.find(String.class, "Zidingyibijimulu", "id.classno", keys2, values2);
		String s4 = new String();
		s4 = classno3.get(0);

		String hql6 = "select classname from Zidingyibijimulu where userId='" + userid + "'and classno like '_______' and substr(classno,1,3)=" + s4;
		List<String> classname3 = this.baseservice.findHql(String.class, hql6);
		if(classname3.contains(two_content_name.replaceAll("\t", "").replaceAll(" ", "").replaceAll("\n", "").replaceAll("\r", ""))) {
			state="2";
			return SUCCESS;
		} 

		//保存二级目录


		String[] keys1 = new String[2];
		keys1[0] = "userId";
		keys1[1] = "classname";
		Object[] values1 = new Object[2];
		values1[0] = userid;
		values1[1] = one_content_name.replaceAll("\t", "").replaceAll(" ", "").replaceAll("\n", "").replaceAll("\r", "");
		List<String> classno2 = this.baseservice.find(String.class, "Zidingyibijimulu", "id.classno", keys1, values1);
		String s1 = new String();
		s1 = classno2.get(0);

		String hql2 = "select id.classno from Zidingyibijimulu where userId='" + userid + "'and classno like '_______'";
		List<String> classno1 = this.baseservice.findHql(String.class, hql2);
		int total2 = 0;
		for(int i=0;i<classno1.size();i++) {
			String s2 = new String();
			s2 = classno1.get(i).substring(0, 3);
			if(s2.equals(s1)) {
				total2++;
			}
		}
		int total4 = total2 + 1;
		if(total2<10) {
			two_classno = classno2.get(0) + "," +"00" + total4;
		} else if(total2>10&&total2<100) {
			two_classno = classno2.get(0) + "," +"0" + total4;
		} else if(total2>=100&&total2<1000) {
			two_classno = classno2.get(0) + "," + String.valueOf(total2);
		}

		ZidingyibijimuluId zdybjmlId2 = new ZidingyibijimuluId();
		zdybjmlId2.setUserId(userid);
		zdybjmlId2.setClassno(two_classno);
		Zidingyibijimulu zdybjml2 = new Zidingyibijimulu();
		zdybjml2.setId(zdybjmlId2);
		zdybjml2.setClassname(two_content_name.replaceAll("\t", "").replaceAll(" ", "").replaceAll("\n", "").replaceAll("\r", ""));
		zdybjml2.setBeizhu(beizhu2);
		this.baseservice.save(zdybjml2);
		classno=two_classno;
		return SUCCESS;
	}

	/**
	 * 此action用来处理用户添加三级目录时，供用户选择的二级目录列表(根据一级目录查询二级目录)
	 */
	@Action(value="/huoqumulu2", results={@Result(name="success", type="json")})
	public String huoqumulu12() {
		HttpSession hs = ServletActionContext.getRequest().getSession();
		String userid = (String)hs.getAttribute("uid");

		try {
			mulu1SelectItem = new String(mulu1SelectItem.getBytes("ISO-8859-1"),"utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		String s1 = mulu1SelectItem;
		
		//根据选择的一级目录名查找一级目录classno
//		String[] keys1 = new String[2];
//		keys1[0] = "userId";
//		keys1[1] = "classname";
//		Object[] values1 = new Object[2];
//		values1[0] = userid;
//		values1[1] = s1;
//		List<String> classnolist = this.baseservice.find(String.class, "Zidingyibijimulu", "id.classno", keys1, values1);

//		String s2 = classnolist.get(0);
		String hql2 = "from Zidingyibijimulu where userId='" + userid + "' and substr(classno,1,3)=" + s1 + " and classno like '_______'" ;
		List<Zidingyibijimulu> list=this.baseservice.findHql(Zidingyibijimulu.class, hql2);
		System.out.println("classnamelist:"+list);
		for (int i = 0; i < list.size(); i++) {
			Zidingyibijimulu zidingyibijimulu=list.get(i);
			Map<String, String> map=new HashMap<String, String>();
			map.put("classname", zidingyibijimulu.getClassname());
			map.put("classno", zidingyibijimulu.getId().getClassno());
			mulu2list.add(map);
		}
		return SUCCESS;
	}

	/**
	 * 此action用来处理每一位用户输入的三级目录名称是否已存在
	 */
	@Action(value="/checkmulu3", results={@Result(name="success", type="json")})
	public String checkmulu3() {
		HttpSession hs = ServletActionContext.getRequest().getSession();
		String userid = (String)hs.getAttribute("uid");

		try {
			one_content_name = new String(one_content_name.getBytes("ISO-8859-1"),"utf-8");
			two_content_name = new String(two_content_name.getBytes("ISO-8859-1"),"utf-8");
			three_content_name = new String(three_content_name.getBytes("ISO-8859-1"),"utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		String[] keys1 = new String[2];
		keys1[0] = "userId";
		keys1[1] = "classname";
		Object[] values1 = new Object[2];
		values1[0] = userid;
		values1[1] = one_content_name.replaceAll("\t", "").replaceAll(" ", "").replaceAll("\n", "").replaceAll("\r", "");
		List<String> classno11 = this.baseservice.find(String.class, "Zidingyibijimulu", "id.classno", keys1, values1);

		String[] keys2 = new String[2];
		keys2[0] = "userId";
		keys2[1] = "classname";
		Object[] values2 = new Object[2];
		values2[0] = userid;
		values2[1] = two_content_name.replaceAll("\t", "").replaceAll(" ", "").replaceAll("\n", "").replaceAll("\r", "");
		List<String> classno = this.baseservice.find(String.class, "Zidingyibijimulu", "id.classno", keys2, values2);
		String s1 = new String();
		String s2 = new String();
		for(int x=0;x<classno.size();x++) {
			s1 = classno.get(x).substring(0, 3);
			if(s1.equals(classno11.get(0))) {
				s2 = classno.get(x);
			}
		}

		String hql = "select classname from Zidingyibijimulu where userId='" + userid + "' and substr(classno,1,7)='" + s2 + "' and classno like '___________'" ;
		String hql1 = "select id.classno from Zidingyibijimulu where userId='" + userid + "' and substr(classno,1,7)='" + s2 + "' and classno like '___________'" ;
		List<String> classname = this.baseservice.findHql(String.class, hql);
		List<Integer> classno_1 = this.baseservice.findHql(Integer.class, hql1);
		if(classno_1.size()==0) {
			s = "1";
		} else {
			if(classname.contains(three_content_name.replaceAll("\t", "").replaceAll(" ", "").replaceAll("\n", "").replaceAll("\r", ""))) {
				hs.setAttribute("classno", classno_1.get(0));
				s = "0";
			} else {
				s = "1";
			}
		}


		return SUCCESS;
	}

	/**
	 * 此action用来处理用户添加三级目录
	 */
	@Action(value="/savemulu3", results={@Result(name="success", type="json")})
	public String savemulu3() {
		HttpSession hs = ServletActionContext.getRequest().getSession();
		String userid = (String)hs.getAttribute("uid");

		try {
			one_content_name = new String(one_content_name.getBytes("ISO-8859-1"),"utf-8");
			two_content_name = new String(two_content_name.getBytes("ISO-8859-1"),"utf-8");
			three_content_name = new String(three_content_name.getBytes("ISO-8859-1"),"utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		String[] keys1 = new String[2];
		keys1[0] = "userId";
		keys1[1] = "classname";
		Object[] values1 = new Object[2];
		values1[0] = userid;
		values1[1] = one_content_name.replaceAll("\t", "").replaceAll(" ", "").replaceAll("\n", "").replaceAll("\r", "");
		List<String> classno11 = this.baseservice.find(String.class, "Zidingyibijimulu", "id.classno", keys1, values1);
		String sss = classno11.get(0);

		String[] keys2 = new String[2];
		keys2[0] = "userId";
		keys2[1] = "classname";
		Object[] values2 = new Object[2];
		values2[0] = userid;
		values2[1] = two_content_name.replaceAll("\t", "").replaceAll(" ", "").replaceAll("\n", "").replaceAll("\r", "");
		List<String> classno = this.baseservice.find(String.class, "Zidingyibijimulu", "id.classno", keys2, values2);

		String s1 = new String();
		String s3 = new String();
		for(int x=0;x<classno.size();x++) {
			String a = classno.get(x).substring(0, 3);
			if(a.equals(sss)) {
				s1=classno.get(x);
				s3 = s1.substring(0, 3);
			}
		}

		String hql = "select id.classno from Zidingyibijimulu where userId='" + userid + "' and substr(classno,1,7)='" + s1 + "' and classno like '___________'";
		List<String> classno1 = this.baseservice.findHql(String.class, hql);
		int total = 0;
		for(int i=0;i<classno1.size();i++) {
			String s2 = new String();
			s2 = classno1.get(i).substring(0, 3);
			if(s2.equals(s3)) {
				total++;
			}
		}
		int total1 = total + 1;
		if(total<10) {
			three_classno = s1 + "," +"00" + total1;
		} else if(total>10&&total<100) {
			three_classno = s1 + "," +"0" + total1;
		} else if(total>=100&&total<1000) {
			three_classno = s1 + "," + String.valueOf(total);
		}

		hs.setAttribute("classno", three_classno);

		ZidingyibijimuluId zdybjmlId = new ZidingyibijimuluId();
		zdybjmlId.setUserId(userid);
		zdybjmlId.setClassno(three_classno);
		Zidingyibijimulu zdybjml = new Zidingyibijimulu();
		zdybjml.setId(zdybjmlId);
		zdybjml.setClassname(three_content_name.replaceAll("\t", "").replaceAll(" ", "").replaceAll("\n", "").replaceAll("\r", ""));
		zdybjml.setBeizhu(beizhu3);
		this.baseservice.save(zdybjml);
		return SUCCESS;
	}

	/**
	 * 此action用来处理用户删除目录
	 */
	@Action(value="/deletemulu", results={@Result(name="success", type="json")})
	public String deletemulu() {
		HttpSession hs = ServletActionContext.getRequest().getSession();
		String userid = (String)hs.getAttribute("uid");

		String[] keys1 = new String[2];
		keys1[0] = "userId";
		keys1[1] = "classno";
		Object[] values1 = new Object[2];
		values1[0] = userid;
		values1[1] = classno;
		List<Zidingyibijimulu> zdybjml = this.baseservice.find(Zidingyibijimulu.class, "Zidingyibijimulu", keys1, values1);
		this.baseservice.delete(zdybjml.get(0));
		return SUCCESS;
	}

	/**
	 * 此action用来处理用户修改目录时是否有重复
	 */
	@Action(value="/checkUpdate", results={@Result(name="success", type="json")})
	public String checkUpdate() {
		HttpSession hs = ServletActionContext.getRequest().getSession();
		String userid = (String)hs.getAttribute("uid");

		try {
			classname = new String(classname.getBytes("ISO-8859-1"),"utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		String _classno = "";
		String hql = "";
		if(classno.length()==3) {
			_classno = classno;
			hql = "select classname from Zidingyibijimulu where userId='" + userid + "' and classno like '___'" ;
		} else if(classno.length()==7) {
			_classno = classno.substring(0, 3);
			hql = "select classname from Zidingyibijimulu where userId='" + userid + "' and substr(classno,1,3)='" + _classno + "' and classno like '_______'" ;
		} else {
			_classno = classno.substring(0, 7);
			hql = "select classname from Zidingyibijimulu where userId='" + userid + "' and substr(classno,1,7)='" + _classno + "' and classno like '___________'" ;
		}

		List<String> classname1= this.baseservice.findHql(String.class, hql);

		if(classname1.contains(classname)) {
			s = "0";
		} else {
			s = "1";
		}

		return SUCCESS;
	}

	/**
	 * 此action用来处理用户修改目录
	 */
	@Action(value="/updatemulu", results={@Result(name="success", type="json")})
	public String updatemulu() {
		HttpSession hs = ServletActionContext.getRequest().getSession();
		String userid = (String)hs.getAttribute("uid");

		try {
			classname = new String(classname.getBytes("ISO-8859-1"),"utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		String[] keys1 = new String[2];
		keys1[0] = "userId";
		keys1[1] = "classno";
		Object[] values1 = new Object[2];
		values1[0] = userid;
		values1[1] = classno;
		List<Zidingyibijimulu> zdybjmllist = this.baseservice.find(Zidingyibijimulu.class, "Zidingyibijimulu", keys1, values1);

		Zidingyibijimulu zdybjml = zdybjmllist.get(0);
		zdybjml.setClassname(classname);

		this.baseservice.update(zdybjml);


		return SUCCESS;
	}

}
