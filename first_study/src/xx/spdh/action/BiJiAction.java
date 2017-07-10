/*
 *@(#)xx.spdh.action
 *@BiJiAction.java.java  
 *@创建时间:2011-11-12下午02:31:57
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
import xx.collection.bean.ZidingyibijimuluId;
import xx.quanxian.service.BaseService;

import com.googlecode.jsonplugin.annotations.JSON;
import com.opensymphony.xwork2.ActionSupport;

/**
 * @BiJiAction <code>{类名称}</code>
 * @author  {作者}
 * @version {版本,常用时间代替}
 * @{功能描述:} 
 */
@Controller
@Scope("prototype")
@Namespace("")
@ParentPackage("default-package")
@SuppressWarnings("serial")
public class BiJiAction extends ActionSupport {

	@Resource(name="baseService")
	private BaseService baseservice;
	private List<Zidingyibijimulu> zdybjmllist = new ArrayList<Zidingyibijimulu>();
	private List<String> class_name = new ArrayList<String>();
	
	//返回值
	private String check;
	
	private String course;
	private String chapter;
	private String section;
	private String zlid;
    private String classno;
    
	public String getClassno() {
		return classno;
	}

	public void setClassno(String classno) {
		this.classno = classno;
	}

	public String getZlid() {
		return zlid;
	}

	public void setZlid(String zlid) {
		this.zlid = zlid;
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
	 * @return the zdybjmllist
	 */
	@JSON(serialize=false)
	public List<Zidingyibijimulu> getZdybjmllist() {
		return zdybjmllist;
	}

	/**
	 * @param zdybjmllist the zdybjmllist to set
	 */
	public void setZdybjmllist(List<Zidingyibijimulu> zdybjmllist) {
		this.zdybjmllist = zdybjmllist;
	}

	/**
	 * @return the class_name
	 */
	@JSON(serialize=false)
	public List<String> getClass_name() {
		return class_name;
	}

	/**
	 * @param class_name the class_name to set
	 */
	public void setClass_name(List<String> class_name) {
		this.class_name = class_name;
	}

	/**
	 * @return the check
	 */
	public String getCheck() {
		return check;
	}

	/**
	 * @param check the check to set
	 */
	public void setCheck(String check) {
		this.check = check;
	}

	/**
	 * @return the course
	 */
	@JSON(serialize=false)
	public String getCourse() {
		return course;
	}

	/**
	 * @param course the course to set
	 */
	public void setCourse(String course) {
		this.course = course;
	}

	/**
	 * @return the chapter
	 */
	@JSON(serialize=false)
	public String getChapter() {
		return chapter;
	}

	/**
	 * @param chapter the chapter to set
	 */
	public void setChapter(String chapter) {
		this.chapter = chapter;
	}

	/**
	 * @return the section
	 */
	@JSON(serialize=false)
	public String getSection() {
		return section;
	}

	/**
	 * @param section the section to set
	 */
	public void setSection(String section) {
		this.section = section;
	}
	/**
	 * 此action用来处理用户做笔记时，判定该用户是否在该课程的章目录是否存在
	 * 如何存在，则返回“true”，否则返回“false”
	*/
	@Action(value="/checkmulu", results={@Result(name="success", type="json")})
	public String checkmulu() {
		return SUCCESS;
	}
	@Action(value="/checkml", results={@Result(name="success", type="json")})
	public String checkmulu1() {
		HttpSession hs = ServletActionContext.getRequest().getSession();
		String userid = (String)hs.getAttribute("uid");
		String[] keys={"id.userId","zlid"};
		Object[] values={userid,zlid};
	    List<Zidingyibijimulu>classno1=this.baseservice.find(Zidingyibijimulu.class, "Zidingyibijimulu", keys, values);
		
	    System.out.println("classno1:"+classno1+" "+classno1.size()+" "+userid+" "+zlid);
	    if (classno1==null||classno1.size()==0) {
			classno="no";
			 return SUCCESS;
		} 
	    classno=classno1.get(0).getId().getClassno();
	   // hs.setAttribute("classno", classno);
		
	    return SUCCESS;
	}
	
	@Action(value="/savemulu", results={@Result(name="success", type="json")})
	public String savemulu() {
		HttpSession hs = ServletActionContext.getRequest().getSession();
		String userid = (String)hs.getAttribute("uid");
		
		ZidingyibijimuluId  zdymjmlId = new ZidingyibijimuluId();
		zdymjmlId.setUserId(userid);
		zdymjmlId.setClassno("001");
		Zidingyibijimulu zdymjml = new Zidingyibijimulu();
		zdymjml.setId(zdymjmlId);
		zdymjml.setClassname(course);
		this.baseservice.save(zdymjml);
		
		
		ZidingyibijimuluId  zdymjmlId1 = new ZidingyibijimuluId();
		zdymjmlId1.setUserId(userid);
		zdymjmlId1.setClassno("001,001");
		Zidingyibijimulu zdymjml1 = new Zidingyibijimulu();
		zdymjml1.setId(zdymjmlId1);
		zdymjml1.setClassname(chapter);
		this.baseservice.save(zdymjml1);
		
		
		ZidingyibijimuluId  zdymjmlId2 = new ZidingyibijimuluId();
		zdymjmlId2.setUserId(userid);
		zdymjmlId2.setClassno("001,001,001");
		Zidingyibijimulu zdymjml2 = new Zidingyibijimulu();
		zdymjml2.setId(zdymjmlId2);
		zdymjml2.setClassname(section);
		this.baseservice.save(zdymjml2);
		
		
		return SUCCESS;
	}
	
}
