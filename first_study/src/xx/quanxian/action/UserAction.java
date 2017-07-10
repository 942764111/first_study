/*
 *@(#)xx.quanxian.action
 *@UserAction.java.java  
 *@����ʱ��:2011-4-3����09:03:00
 *@���ߣ�guoqiang
 *@Copyright 2009-2010 �ӱ�����ѧԺ��Ϣ��ѧ�빤��ѧԺ������ 
 */

package xx.quanxian.action;



import java.util.HashMap;
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


import xx.collection.bean.Roles;
import xx.collection.bean.Studentifno;
import xx.collection.bean.Teacher;


import xx.collection.bean.Userinfo;


import xx.quanxian.service.BaseService;


import com.googlecode.jsonplugin.annotations.JSON;
import com.opensymphony.xwork2.ActionSupport;

/**
 * @UserAction <code>{������}</code>
 * @author  {guoqiang}
 * @version {�汾,����ʱ�����}
 * @{...��������} 
 */
@Controller
@Scope("prototype")
@Namespace("")
@ParentPackage("default")
public class UserAction extends ActionSupport {
	@Resource(name="baseService")
	
	private BaseService baseService; // ��ҳ�����࣬���������ú�������PageModule
	private Studentifno student;
	private Teacher teacher;

	private Userinfo userInfo;
    private HashMap<String, String> leixing=new HashMap<String,String>();
    private Userinfo edu;
    private List<Roles> role;
    
    
	/**
	 * @return the role
	 */
	public List<Roles> getRole() {
		return role;
	}
	/**
	 * @param role the role to set
	 */
	public void setRole(List<Roles> role) {
		this.role = role;
	}
	/**
	 * @return the edu
	 */
	public Userinfo getEdu() {
		return edu;
	}
	/**
	 * @param edu the edu to set
	 */
	public void setEdu(Userinfo edu) {
		this.edu = edu;
	}
	
	/**
	 * @return the leixing
	 */
	public HashMap<String, String> getLeixing() {
		return leixing;
	}
	/**
	 * @param leixing the leixing to set
	 */
	public void setLeixing(HashMap<String, String> leixing) {
		this.leixing = leixing;
	}
	
	/**
	 * @return the student
	 */
	 
	public Studentifno getStudent() {
		return student;
	}
	/**
	 * @param student the student to set
	 */
	public void setStudent(Studentifno student) {
		this.student = student;
	}
	
	/**
	 * @return the teacher
	 */
	
	public Teacher getTeacher() {
		return teacher;
	}
	/**
	 * @param teacher the teacher to set
	 */
	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}
	
	/**
	 * @return the userInfo
	 */
	 @JSON(serialize=false)
	public Userinfo getUserInfo() {
		return userInfo;
	}
	/**
	 * @param userInfo the userInfo to set
	 */
	public void setUserInfo(Userinfo userInfo) {
		this.userInfo = userInfo;
	}
	
	/**
	 * @{userAction ��ʾ�û���ϸ��Ϣ��action}
	 * @param {} {�������˵��}
	 * @return {SUCCESS} {���ز���˵��}
	 * @{��ת��user.jsp}
	 * @exception {˵����ĳ�����,������ʲô�쳣}
	*/
	@Action(value="userAction",results={@Result(name="success",type="json"),
			                             @Result(name="fail",location="/page/user/owninfo4t.jsp") })
	public String userAction(){
		HttpSession hs = ServletActionContext.getRequest().getSession();
		userInfo=(Userinfo) hs.getAttribute("userifno");
		String userId;String type;
		if(userInfo!=null){
			 userId=userInfo.getUserId();
			 type=userInfo.getType();
		}else{
			 userId=(String)hs.getAttribute("uid");
			userInfo=this.baseService.find(Userinfo.class, userId);
			type=userInfo.getType();
		}
        
	    if(type.equals("S")){/*ѧ��*/
	    	String[] keys=new String[1];
	    	keys[0]="UserId";
	    	String[] values=new String[1];
	    	values[0]=userId;
	    	List<Studentifno> studentlist=this.baseService.find(Studentifno.class, "Studentifno", keys, values);
	    	int size=studentlist.size();
	    	if(size!=0){
	    		
	    		student=studentlist.get(0);
	    		hs.setAttribute("student", student);
	    	}else{
        			 student=new Studentifno();
				     student.setUserinfo(userInfo);
				     student.setSNo("");
				     hs.setAttribute("student", student);

	    	}
		   
		    return "success";
		}else{                /*��ʦ*/
			String[] keys=new String[1];
	    	keys[0]="UserId";
	    	String[] values=new String[1];
	    	values[0]=userId;
	    	List<Teacher> teacherlist=this.baseService.find(Teacher.class, "Teacher", keys, values);
	    	int size=teacherlist.size();
	    	if(size!=0){
	    		
	    		teacher=teacherlist.get(0);
	    		hs.setAttribute("teacher", teacher);
	    	}else{
                    teacher=new Teacher();
				    teacher.setUserinfo(userInfo);
				    teacher.setJsbh("");
				    hs.setAttribute("teacher", teacher);

	    	}
			
			return "fail";
		}
	}
	
	/**
	 * @{yhgl.jsp}
	 * @param {} {�������˵��}
	 * @return {���ز�����} {���ز���˵��}
	 * @{��ѯ�û��б�}
	 * @exception {˵����ĳ�����,������ʲô�쳣}
	*/
	@Action(value="/yhgl",results={@Result(name = "success",location="/page/user/yhgl.jsp")})
	public String yhgl(){
		leixing.put("S","ѧ��");
		leixing.put("T","��ʦ");
		leixing.put( "A","����Ա");
		role=this.baseService.find(Roles.class);
		return SUCCESS;
	}
	/**
	 * 
	 * @{��ת���޸��û��������ҳ��}
	 * @param {newPass} {�µ�����}
	 * @return {���ز�����} {���ز���˵��}
	 * @{�����Ĺ���/��������}
	 * @exception {˵����ĳ�����,������ʲô�쳣}
	 */
	@Action(value="/updatePassword",results = {@Result(name = "success",location ="/page/user/updatePass.jsp")})
	public String updatePassword(){
		return SUCCESS;
	}
	
	
	


}
