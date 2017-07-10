/*
 *@(#)xx.quanxian.action
 *@Bzxsjld.java.java  
 *@创建时间:2011-10-2上午08:26:06
 *@作者：hp
 *@Copyright 2009-2010 河北北方学院信息科学与工程学院科研所 
 */

package xx.quanxian.action;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import xx.collection.bean.Bjxx;

import xx.collection.bean.Xuyan;

import xx.collection.bean.Zyxx;
import xx.quanxian.service.BaseService;

import com.googlecode.jsonplugin.annotations.JSON;
import com.opensymphony.xwork2.ActionSupport;

/**
 * @Bzxsjld <code>{类名称}</code>
 * @author  {作者}
 * @version {版本,常用时间代替}
 * @{功能描述:班级、专业、学院的下拉联动的action} 
 */
@Controller
@Scope("prototype")
@ParentPackage("default-package")
@Namespace("")
public class Bzxsjld extends ActionSupport {
	@Resource(name="baseService")
	private BaseService baseservice;
	
	private List<Xuyan> list1;
	private List<Zyxx> list2;
	private List<Bjxx> list3;
	
	private String xymc;
	private String zymc;
	private String bjmc;
	
	private List<String> x=new ArrayList<String>();
	private List<String> z=new ArrayList<String>();
	private List<String> b=new ArrayList<String>();
	/**
	 * @return the list1
	 */
	@JSON(serialize=false)
	public List<Xuyan> getList1() {
		return list1;
	}
	/**
	 * @param list1 the list1 to set
	 */
	public void setList1(List<Xuyan> list1) {
		this.list1 = list1;
	}
	/**
	 * @return the list2
	 */
	@JSON(serialize=false)
	public List<Zyxx> getList2() {
		return list2;
	}
	/**
	 * @param list2 the list2 to set
	 */
	public void setList2(List<Zyxx> list2) {
		this.list2 = list2;
	}
	/**
	 * @return the list3
	 */
	public List<Bjxx> getList3() {
		return list3;
	}
	/**
	 * @param list3 the list3 to set
	 */
	public void setList3(List<Bjxx> list3) {
		this.list3 = list3;
	}
	/**
	 * @return the xymc
	 */
	public String getXymc() {
		return xymc;
	}
	/**
	 * @param xymc the xymc to set
	 */
	public void setXymc(String xymc) {
		this.xymc = xymc;
	}
	/**
	 * @return the zymc
	 */
	public String getZymc() {
		return zymc;
	}
	/**
	 * @param zymc the zymc to set
	 */
	public void setZymc(String zymc) {
		this.zymc = zymc;
	}
	/**
	 * @return the bjmc
	 */
	public String getBjmc() {
		return bjmc;
	}
	/**
	 * @param bjmc the bjmc to set
	 */
	public void setBjmc(String bjmc) {
		this.bjmc = bjmc;
	}
	/**
	 * @return the x
	 */
	public List<String> getX() {
		return x;
	}
	/**
	 * @param x the x to set
	 */
	public void setX(List<String> x) {
		this.x = x;
	}
	/**
	 * @return the z
	 */
	public List<String> getZ() {
		return z;
	}
	/**
	 * @param z the z to set
	 */
	public void setZ(List<String> z) {
		this.z = z;
	}
	/**
	 * @return the b
	 */
	@JSON(serialize=false)
	public List<String> getB() {
		return b;
	}
	/**
	 * @param b the b to set
	 */
	public void setB(List<String> b) {
		this.b = b;
	}
	//获得一级数据源
	@Action(value="/bindXAction",results={@Result(name="success",type="json")})
	public String bindX(){
		list1=this.baseservice.find(Xuyan.class);
		for(int i=0;i<list1.size();i++){
			String b=list1.get(i).getXymc();
			if(!x.contains(b)){
				x.add(b);
			}
		}
	 	return SUCCESS;
	}
	//获得二级数据源
	@Action(value="/bindZAction",results={@Result(name="success",type="json")})
	public String bindZ() throws UnsupportedEncodingException{

		xymc=new String(xymc.getBytes("ISO-8859-1"),"utf-8");
		if(xymc!=null){
			
			//通过xymc查找xybh
			String[] keys=new String[1];
			keys[0]="xymc";
			Object[] values=new Object[1];
			values[0]=xymc;
			List<Integer> xybh=this.baseservice.find(Integer.class, "Xuyan", "xybh", keys, values);
			String[] keys1 = {"xybh"};
			Object[] values1 = {xybh.get(0)};
			list2=this.baseservice.find(Zyxx.class, "Zyxx", keys1, values1);
			for(int j=0;j<list2.size();j++){
				String c = list2.get(j).getZymc();
					z.add(c);
			}
		}
	 return SUCCESS;
	}
	//获得三级数据源 
	@Action(value="/bindBAction",results={@Result(name="success",type="json")})
	public String bindB() throws UnsupportedEncodingException{
		
		zymc=new String(zymc.getBytes("ISO-8859-1"),"utf-8");
		if(zymc!=null){
			
			//通过zymc查找zybh
			String[] keys=new String[1];
			keys[0]="zymc";
			Object[] values=new Object[1];
			values[0]=zymc;
			List<Integer> zybh=this.baseservice.find(Integer.class, "Zyxx", "zybh", keys, values);
			String[] keys1 = {"zybh"};
			Object[] values1 = {zybh.get(0)};
			list3=this.baseservice.find(Bjxx.class, "Bjxx", keys1, values1);
			
		}
	 return SUCCESS;
	}
	
}
