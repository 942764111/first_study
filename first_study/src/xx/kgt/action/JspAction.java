/*
 *@(#)xx.kgt.action
 *@JspAction.java.java  
 *@创建时间:2011-10-26下午08:37:07
 *@作者：Administrator
 *@Copyright 2009-2010 河北北方学院信息科学与工程学院科研所 
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
 * @JspAction <code>{类名称}</code>
 * @author  {张晓莉}
 * @version {版本,常用时间代替}
 * @{功能描述:存放跳转页面的action} 
 */

@Controller
@Scope("prototype")
@ParentPackage("default")
@Namespace("")
public class JspAction extends ActionSupport{
	
	
	@Resource(name="baseService")
	private BaseService baseservice;
	
	//节信息所需变量
	private List<CourseChapter> kcxxlist;
	private List<Integer> CIdlist=new ArrayList<Integer>();
	private CourseChapter kcxx;
	private List<String> CNameList=new ArrayList<String>();
	
	//专业信息所需变量
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
	 * 该方法是用来指定跳转到的页面的
	 */
	@Action(value="/panduan",results={@Result(name="success",location="/kgt/pd.jsp")})
	public String panduan(){
		
		return SUCCESS;
	}
	
	/**
	 * @{xuanze.action}
	 * 该方法是用来指定跳转到选择题管理的页面的
	 */
	@Action(value="/xuanze",results={@Result(name="success",location="/kgt/xz.jsp")})
	public String xuanze(){
	
		return SUCCESS;
	}
	
	/**
	 * @{zylx.action}
	 * 该方法是用来指定跳转到的页面是自由测试页面
	 */
	@Action(value="/zylx",results={@Result(name="success",location="/kgt/zytest.jsp")})
	public String zylx(){
		return SUCCESS;
	}
	
	
	//跳转到页面"节 信息管理"
	@Action(value="/zinfo",results={@Result(name="success",location="/tables_zxl/jie.jsp")})
	public String zinfo(){
		this.kcxxlist=this.baseservice.find(CourseChapter.class);
		for(int i=0;i<kcxxlist.size();i++){
				CIdlist.add(kcxxlist.get(i).getZbh());
		}
		//通过CIdlist 中的CId查出CName并放入CNameList
		for(int j=0;j<CIdlist.size();j++){
			kcxx=this.baseservice.find(CourseChapter.class, CIdlist.get(j));
			CNameList.add(kcxx.getCName());
		}
		return SUCCESS;
	}
	
	/**
	 * @{jszc}
	 * @return {/tables_zxl/datagrid_jszc.jsp} {显示教师职称信息}
	 * @{教学  教师职称管理}
	 * 在main.jsp中点击教师职称管理时跳转到datagrid_jszc.jsp
	 */	
	@Action(value="/jszc",results={@Result(name="success",location="/tables_zxl/datagrid_jszc.jsp")})
	public String jszc(){
		return SUCCESS;
	}
	
	/**
	 * @{xlxw}
	 * @return {/tables_zxl/datagrid_xlxw.jsp} {显示学历学位信息}
	 * @{教学  学历学位管理}
	 */	
	@Action(value="xlxw",results={@Result(name="success",location="/tables_zxl/datagrid_xlxw.jsp")})
	public String xlxw(){
		return SUCCESS;
	}
	
	/**
	 * @{zyxx}
	 * @return {/tables_zxl/datagrid_zyxx.jsp} {显示专业信息}
	 * @{教学  专业信息管理}
	 */	
	@Action(value="/zyxx",results={@Result(name="success",location="/tables_zxl/datagrid_zyxx.jsp")})
	public String zyxx(){
		this.zyxxb=this.baseservice.find(Zyxx.class);//通过Zyxx.class查询所有数据并放在zyxxb中。
		for(int i=0;i<zyxxb.size();i++){
			if(!zyxxb1.contains(zyxxb.get(i).getXuyan().getXymc())){
				zyxxb1.add(zyxxb.get(i).getXuyan().getXymc());
			}
		}
		return SUCCESS;
	}
	
	/**
	 * @{zyxx}
	 * @return {/tables_zxl/datagrid_schoollist.jsp} {显示学校信息}
	 * @{教学  学校信息管理}
	 */	
	@Action(value="xxlb",results={@Result(name="success",location="/tables_zxl/datagrid_schoollist.jsp")})
	public String xxlb(){
		return SUCCESS;
	}
	
	
	/**
	 * 模块分类（课程信息管理）
	 */	
	@Action(value="kcxx",results={@Result(name="success",location="/tables/datagrid_Kcxx.jsp",type="redirect")})
	public String kcxx(){
		return SUCCESS;
	}
	
	

	//跳转到学生测试、查看试卷页面
	@Action(value = "/Stest", results = { @Result(name = "success", location="/testxg/Stest.jsp") })
	public String Stest(){
		return SUCCESS;
	}
	//跳转到老师组卷页面
	@Action(value = "/Ttest", results = { @Result(name = "success", location="/testxg/teachertest.jsp") })
	public String Ttest(){
		return SUCCESS;
	}
	
	
	//跳转到查看试卷页面
	@Action(value = "/cksjgl", results = { @Result(name = "success", location="/testxg/sjgl.jsp") })
	public String cksjgl(){
		return SUCCESS;
	}
	
	
	//跳转到学生答卷管理页面
	@Action(value = "/xsdjjlgl", results = { @Result(name = "success", location="/testxg/xsdtjl.jsp") })
	public String xsdjjlgl(){
		return SUCCESS;
	}
}
