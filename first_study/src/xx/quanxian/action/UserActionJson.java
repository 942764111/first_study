/*
 *@(#)xx.quanxian.action
 *@UserAction.java.java  
 *@����ʱ��:2011-4-3����09:03:00
 *@���ߣ�guoqiang
 *@Copyright 2009-2010 �ӱ�����ѧԺ��Ϣ��ѧ�빤��ѧԺ������ 
 */

package xx.quanxian.action;
import java.io.UnsupportedEncodingException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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

import xx.collection.bean.Bjxx;
import xx.collection.bean.Jslb;
import xx.collection.bean.Mz;
import xx.collection.bean.Xuyan;
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
@ParentPackage("default-package")
public class UserActionJson extends ActionSupport {
	@Resource(name="baseService")
	
	private BaseService baseService; // ��ҳ�����࣬���������ú�������PageModule
	private Studentifno student;
	private Teacher teacher;

	private Userinfo userInfo;
    private String tip;
    private String tip2;
    private String UserId;
    private int RoleId;
    private String Type;
    private static String U_serId;
    private static String T_ype;
    private List<Userinfo> rows;
    private int page;
    private int rows_s;
    
    private int total;
    private String[] userid;
    private Userinfo edu;
    private List<Roles> role;
    private List<Mz> m;
    private List<Xuyan> x;
    private List<Jslb> l;
    private String newPass;
    
    private String userId1;
    private String SName;
    private String SNo;
    private String SSex;
    private String xymc;
    private String zymc;
    private String bjmc;
    private int bjbh;
    
    private String xszw;
    private String email;
 	private String handphone;
 	private String rxny;
 	
 	
    
    
    
	public String getUserId1() {
		return userId1;
	}
	public void setUserId1(String userId1) {
		this.userId1 = userId1;
	}
	public String getSName() {
		return SName;
	}
	public void setSName(String name) {
		SName = name;
	}
	public String getSNo() {
		return SNo;
	}
	public void setSNo(String no) {
		SNo = no;
	}
	public String getSSex() {
		return SSex;
	}
	public void setSSex(String sex) {
		SSex = sex;
	}
	public String getXymc() {
		return xymc;
	}
	public void setXymc(String xymc) {
		this.xymc = xymc;
	}
	public String getZymc() {
		return zymc;
	}
	public void setZymc(String zymc) {
		this.zymc = zymc;
	}
	public String getBjmc() {
		return bjmc;
	}
	public void setBjmc(String bjmc) {
		this.bjmc = bjmc;
	}
	
	public int getBjbh() {
		return bjbh;
	}
	public void setBjbh(int bjbh) {
		this.bjbh = bjbh;
	}
	public String getXszw() {
		return xszw;
	}
	public void setXszw(String xszw) {
		this.xszw = xszw;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getHandphone() {
		return handphone;
	}
	public void setHandphone(String handphone) {
		this.handphone = handphone;
	}
	public String getRxny() {
		return rxny;
	}
	public void setRxny(String rxny) {
		this.rxny = rxny;
	}
	
	
	/**
	 * @return the newPass
	 */
	public String getNewPass() {
		return newPass;
	}
	/**
	 * @param newPass the newPass to set
	 */
	public void setNewPass(String newPass) {
		this.newPass = newPass;
	}
	/**
	 * @return the tip2
	 */
	public String getTip2() {
		return tip2;
	}
	/**
	 * @param tip2 the tip2 to set
	 */
	public void setTip2(String tip2) {
		this.tip2 = tip2;
	}
	/**
	 * @return the m
	 */
	public List<Mz> getM() {
		return m;
	}
	/**
	 * @param m the m to set
	 */
	public void setM(List<Mz> m) {
		this.m = m;
	}
	/**
	 * @return the x
	 */
	public List<Xuyan> getX() {
		return x;
	}
	/**
	 * @param x the x to set
	 */
	public void setX(List<Xuyan> x) {
		this.x = x;
	}
	/**
	 * @return the l
	 */
	public List<Jslb> getL() {
		return l;
	}
	/**
	 * @param l the l to set
	 */
	public void setL(List<Jslb> l) {
		this.l = l;
	}
	/**
	 * @return the roleId
	 */
	public int getRoleId() {
		return RoleId;
	}
	/**
	 * @param roleId the roleId to set
	 */
	public void setRoleId(int roleId) {
		RoleId = roleId;
	}
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
	 * @return the userid
	 */
	public String[] getUserid() {
		return userid;
	}
	/**
	 * @param userid the userid to set
	 */
	public void setUserid(String[] userid) {
		this.userid = userid;
	}
	/**
	 * @return the total
	 */
	public int getTotal() {
		return total;
	}
	/**
	 * @param total the total to set
	 */
	public void setTotal(int total) {
		this.total = total;
	}
	
	/**
	 * @return the page
	 */
	public int getPage() {
		return page;
	}
	/**
	 * @param page the page to set
	 */
	public void setPage(int page) {
		this.page = page;
	}
	/**
	 * @return the rows_s
	 */
	public int getRows_s() {
		return rows_s;
	}
	/**
	 * @param rows_s the rows_s to set
	 */
	public void setRows_s(int rows_s) {
		this.rows_s = rows_s;
	}
	/**
	 * @return the rows
	 */
	public List<Userinfo> getRows() {
		return rows;
	}
	/**
	 * @param rows the rows to set
	 */
	public void setRows(List<Userinfo> rows) {
		this.rows = rows;
	}
	/**
	 * @return the userId
	 */
	public String getUserId() {
		return UserId;
	}
	/**
	 * @param userId the userId to set
	 */
	public void setUserId(String userId) {
		UserId = userId;
	}
	/**
	 * @return the type
	 */
	public String getType() {
		return Type;
	}
	/**
	 * @param type the type to set
	 */
	public void setType(String type) {
		Type = type;
	}
/**
	 * @return the tip
	 */
	public String getTip() {
		return tip;
	}
	/**
	 * @param tip the tip to set
	 */
	public void setTip(String tip) {
		this.tip = tip;
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
	 * @{registerjsp}
	 * @param {login.jsp} {�������˵��}
	 * @return {SUCCESS} {���ز���˵��}
	 * @{��ת��/LoginAction/register.action}
	 * @exception {˵����ĳ�����,������ʲô�쳣}
	*/
	@Action(value="/registerjsp",results={@Result(name="success",location="/register.action",type="redirect")})
	public String registerjsp(){
		return SUCCESS;
	}
	
	/**
	 * @{imagejsp}
	 * @param {login.jsp} {�������˵��}
	 * @return {���ز�����} {���ز���˵��}
	 * @{��½ҳ���ע����}
	 * @exception {˵����ĳ�����,������ʲô�쳣}
	*/
	@Action(value="/imagejsp",results={@Result(name = "success",location="/page/user/image.jsp")})
	public String imagejsp(){
		return SUCCESS;
	}
	
	/**
	 * @{updatPass�޸��û������action}
	 * @param {newPass} 
	 * @return {���ز�����} {���ز���˵��}
	 * 
	*/
    @Action(value = "/updatPass",results = {@Result(name = "success",type = "json")})
    public String updatPass(){
    	HttpSession hs = ServletActionContext.getRequest().getSession();
		userInfo=(Userinfo) hs.getAttribute("userifno");
		userInfo.setUserPw(newPass);
		hs.setAttribute("userifno", userInfo);
		hs.setAttribute("pass", newPass);
		
		this.baseService.update(userInfo);
		
    	return SUCCESS;
    }
	
	/**
	 * @{updateUser�޸��û���ϸ��Ϣ}
	 * @param {owninfo4s.jsp��owninfo4t.jsp} {�������˵��}
	 * @return {SUCCESS} {���ز���˵��}
	 * @{��ת��updateSuccess.jsp}
	 * @exception {˵����ĳ�����,������ʲô�쳣}
	*/
	
	@Action(value="updateUser",results={@Result(name="success",type="json")})
	public String updateUser(){
		HttpSession hs = ServletActionContext.getRequest().getSession();

		userInfo=(Userinfo) hs.getAttribute("userifno");
        String type=userInfo.getType();
		if(type.equals("S")){/*ѧ��*/
			
			String id1=student.getUserinfo().getUserId();
			Studentifno stu=(Studentifno)hs.getAttribute("student");//����userAction.action
			hs.removeAttribute("student");
			if(id1.equals(userInfo.getUserId())){
				
				    if(!stu.getSNo().equals("")){
				    	if(student.getSNo().equals(stu.getSNo())){
				    		this.baseService.update(student);
				    	}else{
				    		this.baseService.delete(stu);
					    	this.baseService.save(student);
				    	}
				    	
				    }else{
				    	this.baseService.save(student);
				    }
					
			 }else{
				 String hql="update Userinfo set roleid='210',TypeId='0' where UserId='"+userInfo.getUserId()+"'";
		 		 this.baseService.bulkUpdate(hql);//ɾ��ԭ�ȵ�user
				 userInfo.setUserId(id1);
				 this.baseService.save(userInfo);//������user
				   if(!stu.getSNo().equals("")){//��ʾԭ��ѧ������������
					   
					   
					   if(student.getSNo().equals(stu.getSNo())){
				    		this.baseService.update(student);
				    	}else{
				    		this.baseService.delete(stu);
					    	this.baseService.save(student);
				    	}
					   
				    }else{
				    	this.baseService.save(student);
				    }
				   userInfo.setUserId(id1);
				   hs.setAttribute("uid", id1);
				   hs.setAttribute("userifno",userInfo);
			   }
			
		}else{           /*��ʦ*/
			 String id2=teacher.getUserinfo().getUserId();
			 String filepath = "upload/teaImages/"+ id2+".jpg"; 
			 teacher.setJszp(filepath);
			 
			 Teacher tea=(Teacher)hs.getAttribute("teacher");//����userAction.action
			 hs.removeAttribute("teacher");
			if(id2.equals(userInfo.getUserId())){

			    if(!tea.getJsbh().equals("")){
			    	if(teacher.getJsbh().equals(tea.getJsbh())){
			    		this.baseService.update(teacher);
			    	}else{
			    		this.baseService.save(teacher);
			    		String hql="update Jxjh set teacher.jsbh='"+teacher.getJsbh()+"' where teacher.jsbh='"+tea.getJsbh()+"'";
				 		this.baseService.bulkUpdate(hql);//�޸Ķ�Ӧ�Ľ�ѧ�ƻ�(�鿴��û�н�ѧ�ƻ��᲻�ᱨ��)
			    		this.baseService.delete(tea);
				    	
			    	}
			    	
			    }else{
			    	this.baseService.save(teacher);
			    }
				
			
			}else{
				    
				    String hql="update Userinfo set roleid='210',TypeId='0' where UserId='"+userInfo.getUserId()+"'";
			 		 this.baseService.bulkUpdate(hql);//ɾ��ԭ�ȵ�user
					 userInfo.setUserId(id2);
					 this.baseService.save(userInfo);//������user
					 
					 if(!tea.getJsbh().equals("")){
					    	if(teacher.getJsbh().equals(tea.getJsbh())){
					    		this.baseService.update(teacher);
					    	}else{
					    		this.baseService.save(teacher);
					    		String hqll="update Jxjh set teacher.jsbh='"+teacher.getJsbh()+"' where teacher.jsbh='"+tea.getJsbh()+"'";
						 		this.baseService.bulkUpdate(hqll);//�޸Ķ�Ӧ�Ľ�ѧ�ƻ�(�鿴��û�н�ѧ�ƻ��᲻�ᱨ��)
					    		this.baseService.delete(tea);
						    	
					    	}
					    	
					    }else{
					    	this.baseService.save(teacher);
					    }
								   
						userInfo.setUserId(id2);
						hs.setAttribute("uid", id2);
						hs.setAttribute("userifno",userInfo);
			
			}
		}
	    tip="true";
		return SUCCESS;
	}
	
	
	@Action(value="updateUser1",results={@Result(name="success",type="json")})
	public String updateUser1() throws UnsupportedEncodingException{
		HttpSession hs = ServletActionContext.getRequest().getSession();
		student = new Studentifno();
		userInfo=(Userinfo) hs.getAttribute("userifno");
        String type=userInfo.getType();
		if(type.equals("S")){/*ѧ��*/
			
			Studentifno stu=(Studentifno)hs.getAttribute("student");//����userAction.action
			hs.removeAttribute("student");
			Bjxx bjxx = new Bjxx();
			bjbh=Integer.parseInt(bjmc);
    		bjxx.setBjbh(bjbh);
    		
    		Userinfo userinfo = new Userinfo();
    		userinfo.setUserId(userId1);
    		
    		DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    		Date rxny1;
			try {
				rxny1 = sdf.parse(rxny);
				student.setRxny(rxny1);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

    		
    		student.setBjxx(bjxx);
    		student.setEmail(email);
    		student.setHandphone(handphone);
    		SName=new String(SName.getBytes("ISO-8859-1"),"utf-8");
    		student.setSName(SName);
    		student.setSNo(SNo);
    		SSex=new String(SSex.getBytes("ISO-8859-1"),"utf-8");
    		student.setSSex(SSex);
    		student.setUserinfo(userinfo);
    		xszw=new String(xszw.getBytes("ISO-8859-1"),"utf-8");
    		student.setXszw(xszw);
    		
    		if(!stu.getSNo().equals("")){
		    	if(SNo.equals(stu.getSNo())){
		    		this.baseService.update(student);
		    	}else{
		    		this.baseService.delete(stu);
			    	this.baseService.save(student);
		    	}
		    	
		    }else{
		    	this.baseService.save(student);
		    }
		}
	    tip="true";
		return SUCCESS;
	}
	
	/**
	 * @{owninfo4t.jsp}
	 * @param {} {�������˵��}
	 * @return {���ز�����} {���ز���˵��}
	 * @{Ϊ��owninfo4t.jspҳ����������б������}
	 * @exception {˵����ĳ�����,������ʲô�쳣}
	*/
	@Action(value="/listmz",results={@Result(name = "success",type="json")})
	public String listmz(){
		m=this.baseService.find(Mz.class);
		return SUCCESS;
	}
	/**
	 * @{owninfo4t.jsp}
	 * @param {} {�������˵��}
	 * @return {���ز�����} {���ز���˵��}
	 * @{Ϊ��owninfo4t.jspҳ����������б������}
	 * @exception {˵����ĳ�����,������ʲô�쳣}
	*/
	@Action(value="/listxy",results={@Result(name = "success",type="json")})
	public String listxy(){
		x=this.baseService.find(Xuyan.class);
		return SUCCESS;
	}
	/**
	 * @{owninfo4t.jsp}
	 * @param {} {�������˵��}
	 * @return {���ز�����} {���ز���˵��}
	 * @{Ϊ��owninfo4t.jspҳ����������б������}
	 * @exception {˵����ĳ�����,������ʲô�쳣}
	*/
	@Action(value="/listlb",results={@Result(name = "success",type="json")})
	public String listjslb(){
		l=this.baseService.find(Jslb.class);
		return SUCCESS;
	}
	
	/**
	 * @{select}
	 * @param {} {�������˵��}
	 * @return {���ز�����} {���ز���˵��}
	 * @{��ѯ�û��б�}
	 * @exception {˵����ĳ�����,������ʲô�쳣}
	*/
	@Action(value="/select",results={@Result(name = "success",type="json")})
	public String select(){
		U_serId=UserId;
		T_ype=Type;
		return SUCCESS;
	}
	/**
	 * @{listUser}
	 * @param {} {�������˵��}
	 * @return {���ز�����} {���ز���˵��}
	 * @{��ѯ�û��б�}
	 * @exception {˵����ĳ�����,������ʲô�쳣}
	*/
	@Action(value="/listUser",results={@Result(name = "success",type="json")})
	public String listUser(){
		String[] keys;
		Object[] values;
		if(T_ype.equals("A")){
			keys=new String[2];
			keys[0]="UserId";
			//keys[1]="Type";
			keys[1]="roleid";
			values=new Object[2];
			values[0]=U_serId;
			//values[1]="T";
			values[1]=106;
		}else{
			keys=new String[2];
			keys[0]="UserId";
			keys[1]="Type";
			values=new Object[2];
			values[0]=U_serId;
			values[1]=T_ype;
			
		}
		
		rows=this.baseService.findLike(Userinfo.class, "Userinfo", keys, values, "order by UserId asc", page, rows_s);
		
		if(T_ype.equals("S")){
			for(int i=0;i<rows.size();i++){
				String[] key_s=new String[1];key_s[0]="UserId"; 
				Object[] value_s=new Object[1];value_s[0]=rows.get(i).getUserId();
				List<Studentifno> stu=this.baseService.find(Studentifno.class, "Studentifno", key_s, value_s);
				String sname="�գ�";
				if(stu.size()==0){
				}else{
					sname=stu.get(0).getSName();
				}
				rows.get(i).setUserPw(sname);//�˴���ʱ���ˡ�userPw���Ӷδ���û������Ѽ�ⲻ��Ӱ�����ݿ��е����ݣ���ԭ���ǣ����Ķ���yhgl.jsp�е���ദ��Ҫ�Ķ�����������ܴ󣩡�
			}
		}else{
			for(int i=0;i<rows.size();i++){
				String[] key_s=new String[1];key_s[0]="UserId"; 
				Object[] value_s=new Object[1];value_s[0]=rows.get(i).getUserId();
				List<Teacher> tea=this.baseService.find(Teacher.class, "Teacher", key_s, value_s);
				List<Studentifno> stu=this.baseService.find(Studentifno.class, "Studentifno", key_s, value_s);
				String tname="�գ�";
				if(tea.size()==0){
				}else{
					tname=tea.get(0).getJsxm();
				}
				if(stu.size()==0){
				}else{
					tname=stu.get(0).getSName();
				}
				rows.get(i).setUserPw(tname);//�˴���ʱ���ˡ�userPw���Ӷδ���û������Ѽ�ⲻ��Ӱ�����ݿ��е����ݣ���ԭ���ǣ����Ķ���yhgl.jsp�е���ദ��Ҫ�Ķ���
			}
		}
		
		total=this.baseService.getTotalPages("Userinfo", keys, values);
		return SUCCESS;
	}
	/**
	 * @{ȡ���û���¼Ȩ��}
	 * @param {} {�������˵��}
	 * @return {SUCCESS} {���ز���˵��}
	 * @{��ת��}
	 * @exception {˵����ĳ�����,������ʲô�쳣}
	**/
	
	@Action(value="/delUser",results={@Result(name="success",type="json")})
	public String delUser(){//һ��ϵͳ���ǲ�����ɾ���û��ģ���action������ȡ�����û���¼Ȩ��
		for(int i=0;i<userid.length;i++){
			String hql="update Userinfo set roleid='210',TypeId='0' where UserId='"+userid[i]+"'";
 			this.baseService.bulkUpdate(hql);
		}
		
		tip="true";
		return SUCCESS;
	}
	/**
	 * @{����û�}
	 * @param {} {�������˵��}
	 * @return {SUCCESS} {���ز���˵��}
	 * @{��ת��}
	 * @exception {˵����ĳ�����,������ʲô�쳣}
	**/
	
	@Action(value="/addUser",results={@Result(name="success",type="json")})
	public String addUser(){
		if(edu.getType().equals("S")){
			edu.setTypeId("1");
			Roles ro=new Roles();ro.setRoleid(108);
			edu.setRoles(ro);
			
		}else if(edu.getType().equals("T")){
			Roles ro=new Roles();ro.setRoleid(109);
			edu.setRoles(ro);
			edu.setTypeId("2");
			
		}else if(edu.getType().equals("A")){//��A���������ԱҲ����ʦ��"A"ֻ����ǰ̨��ʾ�ǡ�����Ա������ݣ������ݿ���Type�ǡ�T��,��roleid�ǡ�106����
			edu.setType("T");
			Roles ro=new Roles();ro.setRoleid(106);
			edu.setRoles(ro);
			edu.setTypeId("2");
			
		}
		this.baseService.save(edu);
		tip="true";
		return SUCCESS;
	}
	/**
	 * @{�޸��û�}
	 * @param {} {�������˵��}
	 * @return {SUCCESS} {���ز���˵��}
	 * @{��ת��}
	 * @exception {˵����ĳ�����,������ʲô�쳣}
	**/
	
	@Action(value="/updatUser",results={@Result(name="success",type="json")})
	public String updatUser(){
		Roles ro=new Roles();
		if(edu.getType().equals("S")){
			edu.setTypeId("1");
			ro.setRoleid(108);
			edu.setRoles(ro);
			
		}else if(edu.getType().equals("T")){
			ro.setRoleid(109);
			edu.setTypeId("2");
			edu.setRoles(ro);
		}else if(edu.getType().equals("A")){//��A���������ԱҲ����ʦ��"A"ֻ����ǰ̨��ʾ�ǡ�����Ա������ݣ������ݿ���Type�ǡ�T��,��roleid�ǡ�106����
			edu.setType("T");
			ro.setRoleid(106);
			edu.setTypeId("2");
			edu.setRoles(ro);
		}
		String hql="update Userinfo set roleid='"+edu.getRoles().getRoleid()+"', SafeQuestion='"+edu.getSafeQuestion()+"', SafeAnswer='"+edu.getSafeAnswer()+"', Type='"+edu.getType()+"', TypeId='"+edu.getTypeId()+"' where UserId='"+edu.getUserId()+"'";
		this.baseService.bulkUpdate(hql);
		tip="true";
		return SUCCESS;
	}
	
	/**
	 * @{ָ����ɫ}
	 * @param {} {�������˵��}
	 * @return {SUCCESS} {���ز���˵��}
	 * @{��ת��}
	 * @exception {˵����ĳ�����,������ʲô�쳣}
	**/
	
	@Action(value="/rolesure",results={@Result(name="success",type="json")})
	public String rolesure(){
		String hql="";
		if(RoleId==210){
			hql="update Userinfo set roleid='"+RoleId+"',TypeId='0' where UserId='"+UserId+"'";
		}else{
			if(Type.equals("S")){
				hql="update Userinfo set roleid='"+RoleId+"',TypeId='1' where UserId='"+UserId+"'";
			}else{
				hql="update Userinfo set roleid='"+RoleId+"',TypeId='2' where UserId='"+UserId+"'";
			}
		}
		
        this.baseService.bulkUpdate(hql);
		tip="true";
		return SUCCESS;
	}

	
	/**
	 * @{listSingle��ѯ�����û�����ϸ��Ϣ}
	 * @param {} {�������˵��}
	 * @return {SUCCESS} {���ز���˵��}
	 * @{��ת��}
	 * @exception {˵����ĳ�����,������ʲô�쳣}
	*/
    @Action(value="listSingle",results={@Result(name="success",type="json")})
	public String listSingle(){
    	
		String[] keys=new String[1];
		keys[0]="UserId";
		String[] values=new String[1];
		values[0]=UserId;
		if(Type.equals("S")){
			List<Studentifno> stu=this.baseService.find(Studentifno.class, "Studentifno", keys, values);
			if(stu.size()!=0){
				student=stu.get(0);
				
			}else{
				tip2="��";
				
			}
		
			tip="student";
		}else{//����ԱҲ�ǽ�ʦ
			List<Teacher> tea=this.baseService.find(Teacher.class, "Teacher", keys, values);
			if(tea.size()!=0){
				teacher=tea.get(0);
			
			}else{
				tip2="��";
			}
			
			tip="teacher";
		}
		return SUCCESS;
	}
	
	


}
