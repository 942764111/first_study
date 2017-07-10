/*
 *@(#)xx.quanxian.action
 *@UserAction.java.java  
 *@创建时间:2011-4-3上午09:03:00
 *@作者：guoqiang
 *@Copyright 2009-2010 河北北方学院信息科学与工程学院科研所 
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
 * @UserAction <code>{类名称}</code>
 * @author  {guoqiang}
 * @version {版本,常用时间代替}
 * @{...功能描述} 
 */
@Controller
@Scope("prototype")
@Namespace("")
@ParentPackage("default")
public class UserAction extends ActionSupport {
	@Resource(name="baseService")
	
	private BaseService baseService; // 分页管理类，用它来调用函数返回PageModule
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
	 * @{userAction 显示用户详细信息的action}
	 * @param {} {引入参数说明}
	 * @return {SUCCESS} {返回参数说明}
	 * @{跳转到user.jsp}
	 * @exception {说明在某情况下,将发生什么异常}
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
        
	    if(type.equals("S")){/*学生*/
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
		}else{                /*教师*/
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
	 * @param {} {引入参数说明}
	 * @return {返回参数名} {返回参数说明}
	 * @{查询用户列表}
	 * @exception {说明在某情况下,将发生什么异常}
	*/
	@Action(value="/yhgl",results={@Result(name = "success",location="/page/user/yhgl.jsp")})
	public String yhgl(){
		leixing.put("S","学生");
		leixing.put("T","教师");
		leixing.put( "A","管理员");
		role=this.baseService.find(Roles.class);
		return SUCCESS;
	}
	/**
	 * 
	 * @{跳转到修改用户的密码的页面}
	 * @param {newPass} {新的密码}
	 * @return {返回参数名} {返回参数说明}
	 * @{方法的功能/动作描述}
	 * @exception {说明在某情况下,将发生什么异常}
	 */
	@Action(value="/updatePassword",results = {@Result(name = "success",location ="/page/user/updatePass.jsp")})
	public String updatePassword(){
		return SUCCESS;
	}
	
	
	


}
