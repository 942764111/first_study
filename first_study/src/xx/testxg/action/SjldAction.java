/*
 *@(#)xx.testxg.action
 *@SjldAction.java.java  
 *@����ʱ��:2011-11-7����06:54:01
 *@���ߣ�������
 *@Copyright 2009-2010 �ӱ�����ѧԺ��Ϣ��ѧ�빤��ѧԺ������ 
 */

package xx.testxg.action;

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
import xx.quanxian.service.BaseService;

import com.googlecode.jsonplugin.annotations.JSON;
import com.opensymphony.xwork2.ActionSupport;

/**
 * @SjldAction <code>{������}</code>
 * @author  {������}
 * @version {�汾,����ʱ�����}
 * @{��������:�׶������������} 
 */
@Controller
@Scope("prototype")
@ParentPackage("default-package")
@Namespace("")
public class SjldAction extends ActionSupport {
	@Resource(name="baseService")
	private BaseService baseservice;
	
	private List<CourseChapter> list2;
//	private List<CourseChapter> listz;
	private String TCName;//�γ�����
//	private String CName;//������
	
	private List<String> w=new ArrayList<String>();
	//private List<String> k=new ArrayList<String>();
//	private List<String> z=new ArrayList<String>();
	private List<CourseChapter> secondz=new ArrayList<CourseChapter>();
	private List<CourseChapter> thirdz=new ArrayList<CourseChapter>();
	private int zbh=0;
	
	
	
	public List<CourseChapter> getThirdz() {
		return thirdz;
	}

	public void setThirdz(List<CourseChapter> thirdz) {
		this.thirdz = thirdz;
	}

	public int getZbh() {
		return zbh;
	}

	public void setZbh(int zbh) {
		this.zbh = zbh;
	}

	public List<CourseChapter> getSecondz() {
		return secondz;
	}

	public void setSecondz(List<CourseChapter> secondz) {
		this.secondz = secondz;
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
	@Action(value="/zjTCName",results={@Result(name="success",type="json")})
	public String zjTCName(){
//		String hql="select distinct t_c_name from CourseChapter";
//		w=this.baseservice.findHql(String.class, hql);
		list2=this.baseservice.find(CourseChapter.class);
		for(int i=0;i<list2.size();i++){
			String b=list2.get(i).getTCName();
			if(!w.contains(b)){
				w.add(b);
			}
		}
	
	 	return SUCCESS;
	}
	//��ö�������Դ������
	@Action(value="/zjz1",results={@Result(name="success",type="json")})
	public String zjz1(){
		if(TCName!=null){
			String hql="select new CourseChapter(CName,zbh) from CourseChapter where t_c_name='"+TCName+"'";
			secondz=this.baseservice.findHql(CourseChapter.class, hql);
//			System.out.println(secondz.get(0).getCName());
//			System.out.println(secondz.get(0).getZbh());
//			String[] keys=new String[1];
//			keys[0]="TCName";
//			Object[] values=new Object[1];
//			values[0]=TCName;
//			List<Integer> zbh=this.baseservice.find(Integer.class, "CourseChapter", "zbh", keys, values);
//			
//			for(int j=0;j<zbh.size();j++){
//				String[] keys1 = {"zbh"};
//				Object[] values1 = {zbh.get(j)};
//				listz=this.baseservice.find(CourseChapter.class, "CourseChapter", keys1, values1);
//				for(int m=0;m<listz.size();m++){
//					String e = listz.get(m).getCName();
//					z.add(e);
//				}
//			}
		}
	 	return SUCCESS;
	}
	
	//�����������Դ��ֹ������
	@Action(value="/zjz2",results={@Result(name="success",type="json")})
	public String zjz2(){
		if(TCName!=null&&zbh!=0){
			String hql="select new CourseChapter(CName,zbh) from CourseChapter where zbh >= '"+zbh+"' and t_c_name='"+TCName+"'";
			thirdz=this.baseservice.findHql(CourseChapter.class, hql);
//			for(int m=0;m<cc.size();m++){
//				String d = cc.get(m).getCName();
//				k.add(d);
//			}
		}
	 	return SUCCESS;
	}
	
	
}
