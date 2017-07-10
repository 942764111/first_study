/*
 *@(#)xx.kgt.action
 *@JspAction.java.java  
 *@����ʱ��:2011-10-26����08:37:07
 *@���ߣ�Administrator
 *@Copyright 2009-2010 �ӱ�����ѧԺ��Ϣ��ѧ�빤��ѧԺ������ 
 */

package xx.kgt.action;

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

import xx.collection.bean.CourseChapter;
import xx.collection.bean.Yhzdymc;
import xx.collection.bean.Zyxx;
import xx.quanxian.service.BaseService;

import com.googlecode.jsonplugin.annotations.JSON;
import com.opensymphony.xwork2.ActionSupport;

/**
 * @JspAction <code>{������}</code>
 * @author  {������}
 * @version {�汾,����ʱ�����}
 * @{��������:�����תҳ���action} 
 */

@Controller
@Scope("prototype")
@ParentPackage("default")
@Namespace("")
public class JspAction extends ActionSupport{
	
	
	@Resource(name="baseService")
	private BaseService baseservice;
	
	//����Ϣ�������
	private List<CourseChapter> kcxxlist;
	private List<Integer> CIdlist=new ArrayList<Integer>();
	private CourseChapter kcxx;
	private List<String> CNameList=new ArrayList<String>();
	
	//רҵ��Ϣ�������
	private List<Zyxx> zyxxb;
	private List<String> zyxxb1=new ArrayList<String>();
	
	
	private String userId;

	@JSON(serialize=false)
	public BaseService getBaseservice() {
		return baseservice;
	}

	public void setBaseservice(BaseService baseservice) {
		this.baseservice = baseservice;
	}
	@JSON(serialize=false)
	public List<CourseChapter> getKcxxlist() {
		return kcxxlist;
	}

	public void setKcxxlist(List<CourseChapter> kcxxlist) {
		this.kcxxlist = kcxxlist;
	}
	@JSON(serialize=false)
	public List<Integer> getCIdlist() {
		return CIdlist;
	}

	public void setCIdlist(List<Integer> idlist) {
		CIdlist = idlist;
	}
	@JSON(serialize=false)
	public CourseChapter getKcxx() {
		return kcxx;
	}

	public void setKcxx(CourseChapter kcxx) {
		this.kcxx = kcxx;
	}
	@JSON(serialize=false)
	public List<String> getCNameList() {
		return CNameList;
	}

	public void setCNameList(List<String> nameList) {
		CNameList = nameList;
	}
	@JSON(serialize=false)
	public List<Zyxx> getZyxxb() {
		return zyxxb;
	}

	public void setZyxxb(List<Zyxx> zyxxb) {
		this.zyxxb = zyxxb;
	}
	@JSON(serialize=false)
	public List<String> getZyxxb1() {
		return zyxxb1;
	}

	public void setZyxxb1(List<String> zyxxb1) {
		this.zyxxb1 = zyxxb1;
	}
	
	
	/**
	 * @{panduan.action}
	 * �÷���������ָ����ת����ҳ���
	 */
	@Action(value="/panduan",results={@Result(name="success",location="/kgt/pd.jsp")})
	public String panduan(){
		
		return SUCCESS;
	}
	
	/**
	 * @{xuanze.action}
	 * �÷���������ָ����ת��ѡ��������ҳ���
	 */
	@Action(value="/xuanze",results={@Result(name="success",location="/kgt/xz.jsp")})
	public String xuanze(){
	
		return SUCCESS;
	}
	
	/**
	 * @{zylx.action}
	 * �÷���������ָ����ת����ҳ�������ɲ���ҳ��
	 */
	@Action(value="/zylx",results={@Result(name="success",location="/kgt/zytest.jsp")})
	public String zylx(){
		return SUCCESS;
	}
	
	
	//��ת��ҳ��"�� ��Ϣ����"
	@Action(value="/zinfo",results={@Result(name="success",location="/tables_zxl/jie.jsp")})
	public String zinfo(){
		this.kcxxlist=this.baseservice.find(CourseChapter.class);
		for(int i=0;i<kcxxlist.size();i++){
				CIdlist.add(kcxxlist.get(i).getZbh());
		}
		//ͨ��CIdlist �е�CId���CName������CNameList
		for(int j=0;j<CIdlist.size();j++){
			kcxx=this.baseservice.find(CourseChapter.class, CIdlist.get(j));
			CNameList.add(kcxx.getCName());
		}
		return SUCCESS;
	}
	
	/**
	 * @{jszc}
	 * @return {/tables_zxl/datagrid_jszc.jsp} {��ʾ��ʦְ����Ϣ}
	 * @{��ѧ  ��ʦְ�ƹ���}
	 * ��main.jsp�е����ʦְ�ƹ���ʱ��ת��datagrid_jszc.jsp
	 */	
	@Action(value="/jszc",results={@Result(name="success",location="/tables_zxl/datagrid_jszc.jsp")})
	public String jszc(){
		return SUCCESS;
	}
	
	/**
	 * @{xlxw}
	 * @return {/tables_zxl/datagrid_xlxw.jsp} {��ʾѧ��ѧλ��Ϣ}
	 * @{��ѧ  ѧ��ѧλ����}
	 */	
	@Action(value="xlxw",results={@Result(name="success",location="/tables_zxl/datagrid_xlxw.jsp")})
	public String xlxw(){
		return SUCCESS;
	}
	
	/**
	 * @{zyxx}
	 * @return {/tables_zxl/datagrid_zyxx.jsp} {��ʾרҵ��Ϣ}
	 * @{��ѧ  רҵ��Ϣ����}
	 */	
	@Action(value="/zyxx",results={@Result(name="success",location="/tables_zxl/datagrid_zyxx.jsp")})
	public String zyxx(){
		this.zyxxb=this.baseservice.find(Zyxx.class);//ͨ��Zyxx.class��ѯ�������ݲ�����zyxxb�С�
		for(int i=0;i<zyxxb.size();i++){
			if(!zyxxb1.contains(zyxxb.get(i).getXuyan().getXymc())){
				zyxxb1.add(zyxxb.get(i).getXuyan().getXymc());
			}
		}
		return SUCCESS;
	}
	
	/**
	 * @{zyxx}
	 * @return {/tables_zxl/datagrid_schoollist.jsp} {��ʾѧУ��Ϣ}
	 * @{��ѧ  ѧУ��Ϣ����}
	 */	
	@Action(value="xxlb",results={@Result(name="success",location="/tables_zxl/datagrid_schoollist.jsp")})
	public String xxlb(){
		return SUCCESS;
	}
	
	
	/**
	 * ģ����ࣨ�γ���Ϣ����
	 */	
	@Action(value="kcxx",results={@Result(name="success",location="/tables/datagrid_Kcxx.jsp",type="redirect")})
	public String kcxx(){
		return SUCCESS;
	}
	
	

	//��ת��ѧ�����ԡ��鿴�Ծ�ҳ��
	@Action(value = "/Stest", results = { @Result(name = "success", location="/testxg/Stest.jsp") })
	public String Stest(){
		return SUCCESS;
	}
	//��ת����ʦ���ҳ��
	@Action(value = "/Ttest", results = { @Result(name = "success", location="/testxg/teachertest.jsp") })
	public String Ttest(){
		return SUCCESS;
	}
	
	
	//��ת���鿴�Ծ�ҳ��
	@Action(value = "/cksjgl", results = { @Result(name = "success", location="/testxg/sjgl.jsp") })
	public String cksjgl(){
		return SUCCESS;
	}
	
	
	//��ת��ѧ��������ҳ��
	@Action(value = "/xsdjjlgl", results = { @Result(name = "success", location="/testxg/xsdtjl.jsp") })
	public String xsdjjlgl(){
		return SUCCESS;
	}
}
