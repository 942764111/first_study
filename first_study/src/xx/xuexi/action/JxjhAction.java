



	/**
	 * 文件名：JxjhAction.java
	 *
	 * 版本信息：
	 * 日期：2011-8-5
	 * 作者：tlq
	 * Copyright 河北北方学院信息科学与工程学院科研所 Corporation 2011 
	 * 版权所有
	 *
	 */
	
package xx.xuexi.action;

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

import xx.adminservice.JxjhService;
import xx.collection.bean.CourseChapter;
import xx.collection.bean.Jxjh;
import xx.collection.bean.JxjhYck;
import xx.collection.bean.Teacher;
import xx.quanxian.service.BaseService;

import com.googlecode.jsonplugin.annotations.JSON;
import com.opensymphony.xwork2.ActionSupport;
	/**
 * 此类描述的是：
 * @author: tlq
 * @version: 2011-8-5 下午04:56:26 
 */
@Controller
@Scope("prototype")
@Namespace("")
@ParentPackage("default-package")
public class JxjhAction extends ActionSupport {
    
	@Resource(name="baseService")
	private BaseService baseService;
	@Resource(name="jxjhservice")
	private JxjhService jxjhService;
	
	private Jxjh jxjh;
	private int page;								//分页时，当前的页数，从前台接收
	private int rows_s;								//分页时，当前的页面的行数，从前台接收
	private int total;								//查询出记录的总条数
	List<Object> rows = new ArrayList<Object>();
	
	List<Teacher> teachers = new ArrayList<Teacher>();
	List<CourseChapter> kcxxs = new ArrayList<CourseChapter>();
	
	private String kname;
	private String jhmc;
	private String xq;
	private int jhid;

	private int xsh=0;
	private String tip;
	private int kch;
	private String jsbh;
	private String queryword;
	
	
	private String courseName;
	private String tip1;
	private String[] arr;
	
	private String s1;//学生身份登录时flex端传过来的参数
	
	
	/**
	 * @return the s1
	 */
	@JSON(serialize=false)
	public String getS1() {
		return s1;
	}
	/**
	 * @param s1 the s1 to set
	 */
	public void setS1(String s1) {
		this.s1 = s1;
	}
	/**
	 * @return the arr
	 */
	@JSON(serialize=false)
	public String[] getArr() {
		return arr;
	}
	/**
	 * @param arr the arr to set
	 */
	public void setArr(String[] arr) {
		this.arr = arr;
	}
	/**
	 * @return the tip1
	 */
	public String getTip1() {
		return tip1;
	}
	/**
	 * @param tip1 the tip1 to set
	 */
	public void setTip1(String tip1) {
		this.tip1 = tip1;
	}

	
	/**
	 * @return the queryword
	 */
	@JSON(serialize=false)
	public String getQueryword() {
		return queryword;
	}
	/**
	 * @param queryword the queryword to set
	 */
	public void setQueryword(String queryword) {
		this.queryword = queryword;
	}
	/**
	 * @return the kch
	 */
	@JSON(serialize=false)
	public int getKch() {
		return kch;
	}
	/**
	 * @param kch the kch to set
	 */
	public void setKch(int kch) {
		this.kch = kch;
	}
	/**
	 * @return the jsbh
	 */
	@JSON(serialize=false)
	public String getJsbh() {
		return jsbh;
	}
	/**
	 * @param jsbh the jsbh to set
	 */
	public void setJsbh(String jsbh) {
		this.jsbh = jsbh;
	}
	public String getTip() {
		return tip;
	}
	public void setTip(String tip) {
		this.tip = tip;
	}
	
	@JSON(serialize=false)
	public int getXsh() {
		return xsh;
	}

	public void setXsh(int xsh) {
		this.xsh = xsh;
	}

	
	@JSON(serialize=false)
	public String getXq() {
		return xq;
	}
	public void setXq(String xq) {
		this.xq = xq;
	}

	public int getJhid() {
		return jhid;
	}
	
	public void setJhid(int jhid) {
		this.jhid = jhid;
	}
	
	@JSON(serialize=false)
	public String getKname() {
		return kname;
	}
	public void setKname(String kname) {
		this.kname = kname;
	}
	@JSON(serialize=false)
	public String getJhmc() {
		return jhmc;
	}
	public void setJhmc(String jhmc) {
		this.jhmc = jhmc;
	}
	@JSON(serialize=false)
	public List<CourseChapter> getKcxxs() {
		return kcxxs;
	}
	public void setKcxxs(List<CourseChapter> kcxxs) {
		this.kcxxs = kcxxs;
	}
	@JSON(serialize=false)
	public List<Teacher> getTeachers() {
		return teachers;
	}
	public void setTeachers(List<Teacher> teachers) {
		this.teachers = teachers;
	}
	@JSON(serialize=false)
	public Jxjh getJxjh() {
		return jxjh;
	}
	public void setJxjh(Jxjh jxjh) {
		this.jxjh = jxjh;
	}
	
	public int getPage() {
		return page;
	}
	@JSON(deserialize=true)
	public void setPage(int page) {
		this.page = page;
	}
	public int getRows_s() {
		return rows_s;
	}
	@JSON(deserialize=true)
	public void setRows_s(int rows_s) {
		this.rows_s = rows_s;
	}
	public int getTotal() {
		return total;
	}
	@JSON(deserialize=true)
	public void setTotal(int total) {
		this.total = total;
	}
	public List<Object> getRows() {
		return rows;
	}
	@JSON(deserialize=true)
	public void setRows(List<Object> rows) {
		this.rows = rows;
	}
	
	/**
	 * @return the courseName
	 */
	@JSON(serialize=false)
	public String getCourseName() {
		return courseName;
	}
	/**
	 * @param courseName the courseName to set
	 */
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
	
	/**
	 * @{jxjh_tj_json.action}
	 * @param {} {为教学计划统计页面提供json数据}
	 * 
	*/
	@Action(value="/jxjh_tj_json",results={@Result(name="success",type="json")})
	public String jxjh_tj_json(){
		HttpSession hs = ServletActionContext.getRequest().getSession();
		int kch=(Integer)hs.getAttribute("kkch");
		String word=(String) hs.getAttribute("wword");
		if(kch==0||word==null||word.equals("")){
			rows=this.jxjhService.proc_jxjh_tj(page,rows_s);
			total=this.jxjhService.proc_jxjh_tjTotal();
			  
		}else{
			rows=this.jxjhService.proc_jxjh_tj_search(kch, word, page, rows_s);
			total=this.jxjhService.proc_jxjh_tj_search(kch, word);
			hs.setAttribute("kkch", 0);hs.setAttribute("wword", "");
		}
		
		return SUCCESS;
	}
	
	/**
	 * @{jxjh_tj_json1.action}
	 * @param {} {为教学计划统计页面提供json数据}
	 * 针对flex端
	*/
	@Action(value="/jxjh_tj_json1",results={@Result(name="success",type="json")})
	public String jxjh_tj_json1(){
		HttpSession hs = ServletActionContext.getRequest().getSession();
		int kch=(Integer)hs.getAttribute("kkch");
		String word=(String) hs.getAttribute("wword");
		if(kch==0||word==null||word.equals("")){
			rows=this.jxjhService.proc_jxjh_tj(page,rows_s);
			total=this.jxjhService.proc_jxjh_tjTotal();
			  
		}else{
			rows=this.jxjhService.proc_jxjh_tj_search(kch, word, page, rows_s);
			total=this.jxjhService.proc_jxjh_tj_search(kch, word);
			hs.setAttribute("kkch", 0);hs.setAttribute("wword", "");
		}
		
		return SUCCESS;
	}
	
	/**
	 * 
	 * @{jxjh.action方法名}
	 * @param {引入参数名} {引入参数说明}
	 * @return {返回参数名} {返回参数说明}
	 * @{主要功能就是跳转到教学计划管理的页面，跳转的时候会将已有的老师的姓名和课程名称加载到页面}
	 * @exception {说明在某情况下,将发生什么异常}
	 */
	@Action(value="/jxjh",results={@Result(name="success",location="/page/xuexi/jxjh.jsp")})
	public String jxjh() throws Exception{
		HttpSession hs = ServletActionContext.getRequest().getSession();
		if(tip==null||tip.equals("")){//表示回退
		}else{
			//i_d=jhid;
			//kkch=kch;
			//将课程编号和课程名称存入session
            hs.setAttribute("coursebh", kch);
            hs.setAttribute("courseName", courseName);
			hs.setAttribute("i_d", jhid);//i_d表示是教学计划的编号
			hs.setAttribute("kkch", kch);//仅作书名
			hs.setAttribute("tip", tip);//标记请求是否来自jxjh_tj.jsp或jxjh_tj_stu.jsp
		}
		kch=(Integer)hs.getAttribute("kkch");
		String hql1="from CourseChapter where TCName in (select TCName from CourseChapter where zbh='"+kch+"')";
		kcxxs=this.baseService.findHql(CourseChapter.class, hql1);

		return SUCCESS;
	}
	/**
	 * @{blukaddyck.action}
	 * @param {listFunctions} {显示功能}
	 * 跳转功能
	 * 主要功能就是跳转到教学计划管理的页面，跳转的时候会将已有的老师的姓名和课程名称加载到页面
	*/
	@Action(value="/blukaddyck",results={@Result(name="success",type="json")})
	public String blukaddyck() throws Exception{
		HttpSession hs = ServletActionContext.getRequest().getSession();
		int i_d=(Integer)hs.getAttribute("i_d");
		List<JxjhYck> list=new ArrayList<JxjhYck>();
		
		for(int i=0;i<arr.length;){
			JxjhYck yck=new JxjhYck();
			yck.setYckms(arr[i]);
			yck.setXsh(Integer.valueOf(arr[i+1]));
			CourseChapter k = new CourseChapter();k.setZbh(Integer.valueOf(arr[i+2]));
			yck.setCourseChapter(k);
			yck.setJxjhSz(i_d+"");
			list.add(yck);
			i=i+3;
		}
		this.jxjhService.batchInsert(list);
		return SUCCESS;
	}
	/**
	 * @{AddJxjh.action}
	 * @param {} {显示功能}
	 * 添加功能
	 * 主要功能就是添加教学计划
	*/
	@Action(value="/AddJxjh",results={@Result(name="success",type="json")})
	public String AddJxjh(){
		HttpSession hs = ServletActionContext.getRequest().getSession();
		
		CourseChapter k = new CourseChapter();
		
		if(tip!=null&&tip.equals("jxjh_tj")){//从教学计划管理统计页面添加“教学计划”的
			jsbh=(String)hs.getAttribute("jsbh");
			String hql="from CourseChapter where TCName ='"+kname+"'";
			kch=this.baseService.findSql(CourseChapter.class, hql, 1, 1).get(0).getZbh();//此处的kch应该都是每门课的第一章才对
			int tt=this.jxjhService.proc_addjxjh(jsbh, kch, xq);System.out.println("tt:"+tt);
			if(tt==0){
				tip1="cuowu";
			}else{
				tip1="zq";
				hs.setAttribute("i_d", tt);//批量插入yck时用的。
			}
			
		}else{                                 //从教学计划管理统计或内容概况页面添加“一次课”的
			
			JxjhYck jxjhyck=new JxjhYck();
			k.setZbh(kch);
			jxjhyck.setCourseChapter(k);
			jxjhyck.setYckms(jhmc);
			jxjhyck.setXsh(xsh);
			if(jhid==0){
				jhid=(Integer)hs.getAttribute("i_d");
				jxjhyck.setJxjhSz(jhid+"");//从内容概况页面里添加“一次课”(教学计划的编号从jxjh.action中获得)
			}else{
				jxjhyck.setJxjhSz(jhid+"");//从教学计划管理统计里添加“一次课”（教学计划的编号也从页面取得）
			}
			
			this.baseService.save(jxjhyck);

		}
		
		return SUCCESS;
	}
	/**
	 * @{JxjhJson.action}
	 * @param {} {显示功能}
	 * 无实际意义
	 * 在加载教学计划的时候，datagrid会自动加载这个action
	*/
	@Action(value="/JxjhJson",results={@Result(name="root",type="json")})
	public String listJxjh() throws Exception {
		HttpSession hs = ServletActionContext.getRequest().getSession();
		
		//判断是否是学生登录
		if("student".equals(s1)) {
			if(tip==null||tip.equals("")){//表示回退
			}else{
				//i_d=jhid;
				//kkch=kch;
				//将课程编号和课程名称存入session
	            hs.setAttribute("coursebh", kch);
	            hs.setAttribute("courseName", courseName);
				hs.setAttribute("i_d", jhid);//i_d表示是教学计划的编号
				hs.setAttribute("kkch", kch);//仅作书名
			}
//			kch=(Integer)hs.getAttribute("kkch");
//			String hql1="from CourseChapter where TCName in (select TCName from CourseChapter where zbh='"+kch+"')";
//			kcxxs=this.baseService.findHql(CourseChapter.class, hql1);
		}
		
        int i_d=(Integer)hs.getAttribute("i_d");
		String hql;
		String id1=i_d+"";
		String id2=i_d+",%";
		String id3="%,"+i_d;
		String id4="%,"+i_d+",%";
		hql="select count(*) from JxjhYck where (jxjhSz like '"+id1+"' or jxjhSz like '"+id2+"' or jxjhSz like '"+id3+"' or jxjhSz like '"+id4+"')";
		if(queryword==null||queryword.equals("")){
			
			total=this.baseService.getTotalSql(hql);
			hql="from JxjhYck where (jxjhSz like '"+id1+"' or jxjhSz like '"+id2+"' or jxjhSz like '"+id3+"' or jxjhSz like '"+id4+"') order by zbh asc,no asc";

		}else{
			hql=hql+" and (yckms like '%"+queryword+"%')";
			total=this.baseService.getTotalSql(hql);
			hql="from JxjhYck where (jxjhSz like '"+id1+"' or jxjhSz like '"+id2+"' or jxjhSz like '"+id3+"' or jxjhSz like '"+id4+"') and (yckms like '%"+queryword+"%') order by zbh asc,no asc";
			queryword=null;

		}
		 List<JxjhYck> jxjhlist=this.baseService.findSql(JxjhYck.class, hql, page, rows_s);

		 for(int i=0;i<jxjhlist.size();i++) {
			 Jxjh_z jh = new Jxjh_z();
				jh.setId(jxjhlist.get(i).getNo());
				jh.setJhmc(jxjhlist.get(i).getYckms());
				jh.setKname(jxjhlist.get(i).getCourseChapter().getCName());
				jh.setXsh(jxjhlist.get(i).getXsh());
				jh.setZbh(jxjhlist.get(i).getCourseChapter().getZbh());
				jh.setLabel("第" + (i + 1) + "次课");
				rows.add(jh);
			 
		 }
		
		return "root";
	}
	/**
	 * @{GetType.action}
	 * 判断用户角色类别功能
	 * 系统要根据用户的角色给用户赋予不同的权限，此action就是在页面刚加载的时候判断用户的角色类型
	*/
	@Action(value="/GetType",results={@Result(name="root",type="json")})
	public String gettype(){
		HttpSession hs = ServletActionContext.getRequest().getSession();
		String userid = (String)hs.getAttribute("uid");
		String hql="select distinct type from Userinfo where UserId='"+userid+"'";

		tip=this.baseService.findHql(String.class, hql).get(0);
		return "root";
	}
	/**
	 * @{deleteJxjh.action}
	 * 删除功能
	 * 主要功能就是根据前台传过来的计划id值将对应的教学计划删除
	*/
	@Action(value="/deleteJxjh",results={@Result(name="root",type="json")})
	public String DelJxjh(){
		JxjhYck j = this.baseService.find(JxjhYck.class, jhid);
		
		String hql="select count(*) from Jxnr where jxjhYck.no='"+jhid+"'";
		int shu=this.baseService.getTotalSql(hql);
		if(shu==0){
			this.baseService.delete(j);
			tip="true";
		}else{
			tip="false";
		}
		
		return "root";
	}
	/**
	 * @{updateJxjh.action}
	 * 更新功能
	 * 主要负责更新教学计划
	*/
	@Action(value="/updateJxjh",results={@Result(name="root",type="json")})
	public String UpdateJxjh(){

		String hql="update JxjhYck set yckms='"+jhmc+"',xsh='"+xsh+"',courseChapter.zbh='"+kch+"' where no='"+jhid+"'";
		this.baseService.bulkUpdate(hql);
		return "root";
	}
	
//	/**
//	 * @{ToJxnr.action}
//	 * 跳转功能
//	 * 当需要查看教学计划的教学内容时调用此action，会跳转到对应的教学内容页面
//	 * 方法的两个变量主要是为了添加教学内容时服务的
//	*/
//	@Action(value="/ToUploadFile",results={@Result(name="success",location="/page/xuexi/uploadFile.jsp")})
//	public String ToUploadFile(){
//		this.scwj = this.baseService.find(Scwj.class);
//		this.wjlxlist=this.baseService.find(Wjlx.class);
//		return SUCCESS;
//	}
	/**
	 * @{queryJxjh.action}
	 * 查询功能
	 * 负责查询教学计划，根据课程名称和学期将对应的教学计划查出来
	 * 几个if和else语句主要是根据用户输入的信息不同，查询的条件不同
	*/
	@Action(value="/queryJxjh",results={@Result(name="root",type="json")})
	public String queryFunction(){
		HttpSession hs = ServletActionContext.getRequest().getSession();
        int i_d=(Integer)hs.getAttribute("i_d");
        
		//queryWord=queryword;
		String id1=i_d+""; String id2=i_d+",%"; String id3="%,"+i_d; String id4="%,"+i_d+",%";
		String hql="select count(*) from JxjhYck where (jxjhSz like '"+id1+"' or jxjhSz like '"+id2+"' or jxjhSz like '"+id3+"' or jxjhSz like '"+id4+"')";
		hql=hql+" and (yckms like '%"+queryword+"%')";
		int size=this.baseService.getTotalSql(hql);
		
		if(size!=0){
			tip="true";
		}else{
			tip="kong";
		}
		
		return "root";
	}
}
