/*
 *@(#)xx.kgt.action
 *@Sjld.java.java  
 *@����ʱ��:2011-8-24����04:19:11
 *@���ߣ�������
 *@Copyright 2009-2010 �ӱ�����ѧԺ��Ϣ��ѧ�빤��ѧԺ������ 
 */

package xx.kgt.action;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import xx.collection.bean.CourseChapter;
import xx.collection.bean.Jie;
import xx.collection.bean.Zsd;
import xx.quanxian.service.BaseService;

import com.googlecode.jsonplugin.annotations.JSON;
import com.opensymphony.xwork2.ActionSupport;

/**
 * @Sjld <code>{������}</code>
 * @author  {zxl}
 * @version {�汾,����ʱ�����}
 * @{��������:ʵ���ļ�����������} 
 */
@Controller
@Scope("prototype")
@ParentPackage("default-package")
@Namespace("")
public class Sjld extends ActionSupport {
	@Resource(name="baseService")
	private BaseService baseservice;
	
	private List<CourseChapter> list2;
	private String TCName;//�γ�����
	
	private List<String> w=new ArrayList<String>();
	private List<CourseChapter> second=new ArrayList<CourseChapter>();
	private List<Jie> third=new ArrayList<Jie>();
	private List<Zsd> fourth=new ArrayList<Zsd>();
	private int zbh=0;//�±��
	private int CId=0;//�ڱ��

	
	public int getCId() {
		return CId;
	}

	public void setCId(int id) {
		CId = id;
	}

	public List<Zsd> getFourth() {
		return fourth;
	}

	public void setFourth(List<Zsd> fourth) {
		this.fourth = fourth;
	}

	public int getZbh() {
		return zbh;
	}

	public void setZbh(int zbh) {
		this.zbh = zbh;
	}

	public List<CourseChapter> getSecond() {
		return second;
	}

	public void setSecond(List<CourseChapter> second) {
		this.second = second;
	}

	public List<Jie> getThird() {
		return third;
	}

	public void setThird(List<Jie> third) {
		this.third = third;
	}

	public List<String> getW() {
		return w;
	}

	public void setW(List<String> w) {
		this.w = w;
	}


	public List<CourseChapter> getList2() {
		return list2;
	}

	public void setList2(List<CourseChapter> list2) {
		this.list2 = list2;
	}
	@JSON(serialize=false)
	public BaseService getBaseservice() {
		return baseservice;
	}

	public void setBaseservice(BaseService baseservice) {
		this.baseservice = baseservice;
	}	

	public String getTCName() {
		return TCName;
	}

	public void setTCName(String name) {
		TCName = name;
	}


	//���һ������Դ�γ�����
	@Action(value="/bindTCName",results={@Result(name="success",type="json")})
	public String bindTCName(){
		list2=this.baseservice.find(CourseChapter.class);
		for(int i=0;i<list2.size();i++){
			String b=list2.get(i).getTCName();
			if(!w.contains(b)){
				w.add(b);
			}
		}
		
	 	return SUCCESS;
	}
	//��ö�������Դ�����Ƽ����
	@Action(value="/bindZh",results={@Result(name="success",type="json")})
	public String bindZh(){
		if(TCName!=null){
			String hql="select new CourseChapter(CName,zbh) from CourseChapter where t_c_name='"+TCName+"'";
			second=this.baseservice.findHql(CourseChapter.class, hql);
		}
	 	return SUCCESS;
	}
	
	
	
	
	//�����������Դ�����Ƽ����
		@Action(value="/bindK",results={@Result(name="success",type="json")})
		public String bindK(){
			if(zbh!=0){
				String hql="select new Jie(zmc,id) from Jie where zbh = '"+zbh+"'";
				third=this.baseservice.findHql(Jie.class, hql);
			}
		 return SUCCESS;
		}
		
		
		//����ļ�����Դ ֪ʶ�����Ƽ����
		@Action(value="/bindZsd",results={@Result(name="success",type="json")})
		public String bindZsd(){
			if(CId!=0){
				String hql="select new Zsd(id,zsdmc) from Zsd where id.CId = '"+CId+"'";
				fourth=this.baseservice.findHql(Zsd.class, hql);
			}
		 return SUCCESS;
		}
}
