/*
 *@(#)xx.note.action
 *@ContentManagerAction.java.java  
 *@����ʱ��:2012-11-17����09:46:41
 *@���ߣ�������
 *@Copyright 2009-2010 �ӱ�����ѧԺ��Ϣ��ѧ�빤��ѧԺ������ 
 */

package xx.note.action;

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

import xx.collection.bean.DirManagement;
import xx.collection.bean.Userinfo;
import xx.collection.bean.Zidingyibijimulu;
import xx.quanxian.service.BaseService;

import com.googlecode.jsonplugin.annotations.JSON;
import com.opensymphony.xwork2.ActionSupport;

/**
 * @ContentManagerAction <code>{������}</code>
 * @author  {������}
 * @version {�汾,����ʱ�����}
 * @{��������:} 
 */
@Controller
@Scope("prototype")
@Namespace("")
@ParentPackage("default-package")
@SuppressWarnings("serial")
public class DirectoryManagerAction extends ActionSupport {

	@Resource(name="baseService")
	private BaseService baseservice;
	
	private String s;	//�ж�Ŀ¼�Ƿ����
	
	private String one_content_name;	//һ��Ŀ¼����
	private String beizhu1;	//һ��Ŀ¼��ע
	
	private String two_content_name;	//����Ŀ¼����
	private String beizhu2;	//����Ŀ¼��ע
	
	private String three_content_name;	//����Ŀ¼����
	private String beizhu3;	//����Ŀ¼��ע
	
	private String mulu1SelectItem;//�����û�ѡ��һ��Ŀ¼�ı���
	
	private int parent_id;	//Ŀ¼���ĸ��ڵ�ID
	
	private int classno;	//Ŀ¼id
	
	private DirManagement dirManagement = new DirManagement();	//Ŀ¼����
	
	private List<DirManagement> dirManagements = new ArrayList<DirManagement>();	//���Ŀ¼��
	
	private ArrayList<String> mulu1list = new ArrayList<String>();	//һ��Ŀ¼����
	private ArrayList<String> mulu2list = new ArrayList<String>();	//����Ŀ¼����
	
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
	 * @return the parent_id
	 */
	@JSON(serialize=false)
	public int getParent_id() {
		return parent_id;
	}

	/**
	 * @param parent_id the parent_id to set
	 */
	public void setParent_id(int parent_id) {
		this.parent_id = parent_id;
	}

	/**
	 * @return the classno
	 */
	@JSON(serialize=false)
	public int getClassno() {
		return classno;
	}

	/**
	 * @param classno the classno to set
	 */
	public void setClassno(int classno) {
		this.classno = classno;
	}

	/**
	 * @return the dirManagement
	 */
	public DirManagement getDirManagement() {
		return dirManagement;
	}

	/**
	 * @param dirManagement the dirManagement to set
	 */
	public void setDirManagement(DirManagement dirManagement) {
		this.dirManagement = dirManagement;
	}
	

	/**
	 * @return the dirManagements
	 */
	public List<DirManagement> getDirManagements() {
		return dirManagements;
	}

	/**
	 * @param dirManagements the dirManagements to set
	 */
	public void setDirManagements(List<DirManagement> dirManagements) {
		this.dirManagements = dirManagements;
	}

	/**
	 * @return the mulu1list
	 */
	public ArrayList<String> getMulu1list() {
		return mulu1list;
	}

	/**
	 * @param mulu1list the mulu1list to set
	 */
	public void setMulu1list(ArrayList<String> mulu1list) {
		this.mulu1list = mulu1list;
	}

	/**
	 * @return the mulu2list
	 */
	public ArrayList<String> getMulu2list() {
		return mulu2list;
	}

	/**
	 * @param mulu2list the mulu2list to set
	 */
	public void setMulu2list(ArrayList<String> mulu2list) {
		this.mulu2list = mulu2list;
	}
	
	/**
	 * @{������listDir}
	 * @param {���������} {�������˵��}
	 * @return {���ز�����} {���ز���˵��}
	 * @{�����Ĺ���/��������}
	 * @exception {˵����ĳ�����,������ʲô�쳣}
	*/
	@Action(value="/listDir", results={@Result(name="success", type="json")})
	public String listDir() {
		HttpSession hs = ServletActionContext.getRequest().getSession();
		String userid = (String)hs.getAttribute("uid");
		
		String hql = "from DirManagement where userId='" + userid + "'and parent_id is null";
		dirManagements = this.baseservice.findHql(DirManagement.class, hql);
		
		return SUCCESS;
	}

	/**
	 * @{������:addLevel1Dir}
	 * @param {���������} {�������˵��}
	 * @return {���ز�������s} {���ز���˵������s = "0"������ӵ�һ��Ŀ¼�Ѿ����ڣ���s = "1"����һ��Ŀ¼��ӳɹ�}
	 * @{�����Ĺ���:����û��ʼǵ�һ��Ŀ¼}
	 * @exception {˵����ĳ�����,������ʲô�쳣}
	*/
	@Action(value="/addLevel1Dir", results={@Result(name="success", type="json")})
	public String addLevel1Dir() {
		HttpSession hs = ServletActionContext.getRequest().getSession();
		String userid = (String)hs.getAttribute("uid");
		
		String hql = "select classname from DirManagement where userId='" + userid + "'and parent_id is null";
		List<String> classname = this.baseservice.findHql(String.class, hql);
		String dir1 = one_content_name.replaceAll("\t", "").replaceAll(" ", "").replaceAll("\n", "").replaceAll("\r", "");
		if(classname.contains(dir1)) {
			s = "0";
		} else {
			s = "1";
			Userinfo userinfo = new Userinfo();
			userinfo.setUserId(userid);
			
			dirManagement.setUserinfo(userinfo);
			dirManagement.setClassname(dir1);
			dirManagement.setBeizhu(beizhu1);
			
			this.baseservice.save(dirManagement);
			
		}
		return SUCCESS;
	}
	
	/**
	 * ��action���������û���Ӷ���Ŀ¼ʱ�����û�ѡ���һ��Ŀ¼�б�
	*/
	@Action(value="/select1Level", results={@Result(name="success", type="json")})
	public String select1Level() {
		HttpSession hs = ServletActionContext.getRequest().getSession();
		String userid = (String)hs.getAttribute("uid");
		
		String hql = "select classname from DirManagement where userId='" + userid + "'and parent_id is null";
		List<String> classnamelist = this.baseservice.findHql(String.class, hql);
		for(int i=0;i<classnamelist.size();i++) {
			if(!mulu1list.contains(classnamelist.get(i))){
				mulu1list.add(classnamelist.get(i));
			}
		}
		return SUCCESS;
	}
	
	/**
	 * @{������:addLevel2Dir}
	 * @param {���������} {�������˵��}
	 * @return {���ز�������s} {���ز���˵������s = "0"������ӵ�һ��Ŀ¼�Ѿ����ڣ���s = "1"����һ��Ŀ¼��ӳɹ�}
	 * @{�����Ĺ���:����û��ʼǵĶ���Ŀ¼}
	 * @exception {˵����ĳ�����,������ʲô�쳣}
	*/
	@Action(value="/addLevel2Dir", results={@Result(name="success", type="json")})
	public String addLevel2Dir() {
		
		HttpSession hs = ServletActionContext.getRequest().getSession();
		String userid = (String)hs.getAttribute("uid");
		
		String dir1 = one_content_name.replaceAll("\t", "").replaceAll(" ", "").replaceAll("\n", "").replaceAll("\r", "");
		String dir2 = two_content_name.replaceAll("\t", "").replaceAll(" ", "").replaceAll("\n", "").replaceAll("\r", "");
		
		String hql = "from DirManagement where userId='" + userid + "' and classname = '" + dir1 + "' and parent_id is null";
		List<DirManagement> dm = this.baseservice.findHql(DirManagement.class, hql);
		
		if(dm.get(0).getClassname().contains(dir2)) {
			s = "0";
		} else {
			s = "1";
			
			Userinfo userinfo = new Userinfo();
			userinfo.setUserId(userid);
			
			dirManagement.setClassname(dir2);
			dirManagement.setBeizhu(beizhu2);
			dirManagement.setUserinfo(userinfo);
			dirManagement.setParent(dm.get(0));
			
			this.baseservice.save(dirManagement);
		}
		return SUCCESS;
	}
	
	/**
	 * ��action���������û��������Ŀ¼ʱ�����û�ѡ��Ķ���Ŀ¼�б�(����һ��Ŀ¼��ѯ����Ŀ¼)
	*/
	@Action(value="/select2Level", results={@Result(name="success", type="json")})
	public String select2Level() {
		HttpSession hs = ServletActionContext.getRequest().getSession();
		String userid = (String)hs.getAttribute("uid");
		
		String s1 = mulu1SelectItem;
		//����ѡ���һ��Ŀ¼������һ��Ŀ¼classno
		String[] keys1 = new String[2];
		keys1[0] = "userId";
		keys1[1] = "classname";
		Object[] values1 = new Object[2];
		values1[0] = userid;
		values1[1] = s1;
		List<Integer> classnolist = this.baseservice.find(Integer.class, "DirManagement", "id.classno", keys1, values1);
		
		int i1 = classnolist.get(0);
		String hql2 = "select classname from DirManagement where userId='" + userid + "' and parent_id =" + i1 ;
		List<String> classnamelist = this.baseservice.findHql(String.class, hql2);
		
		for(int i=0;i<classnamelist.size();i++) {
			if(!mulu2list.contains(classnamelist.get(i))){
				mulu2list.add(classnamelist.get(i));
			}
		}
		return SUCCESS;
	}
	
	/**
	 * @{������:addLevel3Dir}
	 * @param {���������} {�������˵��}
	 * @return {���ز�������s} {���ز���˵������s = "0"������ӵ�һ��Ŀ¼�Ѿ����ڣ���s = "1"����һ��Ŀ¼��ӳɹ�}
	 * @{�����Ĺ���:����û��ʼǵ�����Ŀ¼}
	 * @exception {˵����ĳ�����,������ʲô�쳣}
	*/
	@Action(value="/addLevel3Dir", results={@Result(name="success", type="json")})
	public String addLevel3Dir() {
		HttpSession hs = ServletActionContext.getRequest().getSession();
		String userid = (String)hs.getAttribute("uid");
		
		String dir3 = three_content_name.replaceAll("\t", "").replaceAll(" ", "").replaceAll("\n", "").replaceAll("\r", "");
		String dir2 = two_content_name.replaceAll("\t", "").replaceAll(" ", "").replaceAll("\n", "").replaceAll("\r", "");
		
		String hql = "from DirManagement where userId='" + userid + "' and classname = '" + dir2 + "' and parent_id is not null";
		List<DirManagement> dm = this.baseservice.findHql(DirManagement.class, hql);
		
		if(dm.get(0).getClassname().contains(dir3)) {
			s = "0";
		} else {
			s = "1";
			Userinfo userinfo = new Userinfo();
			userinfo.setUserId(userid);
			
			dirManagement.setClassname(dir3);
			dirManagement.setBeizhu(beizhu3);
			dirManagement.setUserinfo(userinfo);
			dirManagement.setParent(dm.get(0));
			
			this.baseservice.save(dirManagement);
		}
		return SUCCESS;
	}
	
	/**
	 * ��action���������û�ɾ��Ŀ¼
	*/
	@Action(value="/deleteDir", results={@Result(name="success", type="json")})
	public String deleteDir() {
		DirManagement dm = this.baseservice.find(DirManagement.class, classno);
		this.baseservice.delete(dm);
		return SUCCESS;
	}
}
