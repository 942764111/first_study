
	/**
	 * 文件名：JxnrAction.java
	 *
	 * 版本信息：
	 * 日期：2011-8-7
	 * 作者：tlq
	 * Copyright 河北北方学院信息科学与工程学院科研所 Corporation 2011 
	 * 版权所有
	 *
	 */
	
package xx.xuexi.action;


import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import xx.adminservice.AdminService;
import xx.adminservice.JxjhService;
import xx.collection.bean.CourseChapter;
import xx.collection.bean.Dmtzl;
import xx.collection.bean.Jie;
import xx.collection.bean.JxjhYck;
import xx.collection.bean.Sjnr;

import xx.collection.bean.Scwj;
import xx.collection.bean.Jxjh;
import xx.collection.bean.Jxnr;

import xx.collection.bean.Wjlx;
import xx.collection.bean.Zsd;

import xx.quanxian.service.BaseService;

import com.googlecode.jsonplugin.annotations.JSON;
import com.opensymphony.xwork2.ActionSupport;


	/**
 * 此类描述的是：
 * @author: tlq
 * @version: 2011-8-7 下午05:20:57 
 */
@Controller
@Scope("prototype")
@Namespace("")
@ParentPackage("default-package")
public class JxnrAction extends ActionSupport{

	@Resource(name="baseService")
	private BaseService baseService;
	@Resource(name="adminService")
	private AdminService adminService;
	@Resource(name="jxjhservice")
	private JxjhService jxjhService;
	//public static int ID;
	private int jhid;
	private int page;								//分页时，当前的页数，从前台接收
	private int rows_s;								//分页时，当前的页面的行数，从前台接收
	private int total;								//查询出记录的总条数
	List<Jxnr_z> rows = new ArrayList<Jxnr_z>();
	private String nr;
	private int zc;
	private int jxnr_id;

	private int ks;
	private String mc;
	private String bdwjms;

	private int zlbh;
	private List<String> zsds = new ArrayList<String>();

	private String kmc;
	private String zmc;
	private String jmc;
	private static int kkch;
	
	List<Sjnr> listsjname=new ArrayList<Sjnr>();
	
	private String chapterName;
	private String chapterbh;
	private List<Jxnr_z> _jxnrlist = new ArrayList<Jxnr_z>();
	private String tip;
	
	private String s1;//学生身份登录时flex端传过来的参数
	private List<Map<String, String>> list=new ArrayList<Map<String,String>>();;
	
	
	public List<Map<String, String>> getList() {
		return list;
	}
	public void setList(List<Map<String, String>> list) {
		this.list = list;
	}
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

	public List<Sjnr> getListsjname() {
		return listsjname;
	}

	public void setListsjname(List<Sjnr> listsjname) {
		this.listsjname = listsjname;
	}
	public String getJmc() {
		return jmc;
	}
	public void setJmc(String jmc) {
		this.jmc = jmc;
	}
//	public String getZsdmc() {
//		return zsdmc;
//	}
//	public void setZsdmc(String zsdmc) {
//		this.zsdmc = zsdmc;
//	}
	public String getKmc() {
		return kmc;
	}
	public void setKmc(String kmc) {
		this.kmc = kmc;
	}
	public String getZmc() {
		return zmc;
	}
	public void setZmc(String zmc) {
		this.zmc = zmc;
	}
	public List<String> getZsds() {
		return zsds;
	}
	public void setZsds(List<String> zsds) {
		this.zsds = zsds;
	}
	public int getZlbh() {
		return zlbh;
	}
	public void setZlbh(int zlbh) {
		this.zlbh = zlbh;
	}
	public String getBdwjms() {
		return bdwjms;
	}
	public void setBdwjms(String bdwjms) {
		this.bdwjms = bdwjms;
	}
	public String getMc() {
		return mc;
	}
	public void setMc(String mc) {
		this.mc = mc;
	}
	@JSON(serialize=false)
	public int getKs() {
		return ks;
	}
	public void setKs(int ks) {
		this.ks = ks;
	}
//	@JSON(serialize=false)
//	public List<Wjlx> getWjlxlist() {
//		return wjlxlist;
//	}
//	public void setWjlxlist(List<Wjlx> wjlxlist) {
//		this.wjlxlist = wjlxlist;
//	}
//	@JSON(serialize=false)
//	public List<Scwj> getScwj() {
//		return scwj;
//	}
//	public void setScwj(List<Scwj> scwj) {
//		this.scwj = scwj;
//	}
	
	
	@JSON(serialize=false)
	public int getJxnr_id() {
		return jxnr_id;
	}
	public void setJxnr_id(int jxnr_id) {
		this.jxnr_id = jxnr_id;
	}
	
	
	@JSON(serialize=false)
	public int getZc() {
		return zc;
	}
	public void setZc(int zc) {
		this.zc = zc;
	}
	@JSON(deserialize=true)
	public String getNr() {
		return nr;
	}
	
	public void setNr(String nr) {
		this.nr = nr;
	}
	@JSON(deserialize=true)
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	@JSON(deserialize=true)
	public int getRows_s() {
		return rows_s;
	}
	public void setRows_s(int rows_s) {
		this.rows_s = rows_s;
	}
	@JSON(deserialize=true)
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	@JSON(deserialize=true)
	public List<Jxnr_z> getRows() {
		return rows;
	}
	public void setRows(List<Jxnr_z> rows) {
		this.rows = rows;
	}
	public int getJhid() {
		return jhid;
	}
	@JSON(deserialize=true)
	public void setJhid(int jhid) {
		this.jhid = jhid;
	}

	/**
	 * @return the chapterName
	 */
	@JSON(deserialize=true)
	public String getChapterName() {
		return chapterName;
	}

	/**
	 * @param chapterName the chapterName to set
	 */
	public void setChapterName(String chapterName) {
		this.chapterName = chapterName;
	}

	/**
	 * @return the chapterbh
	 */
	@JSON(deserialize=true) 
	public String getChapterbh() {
		return chapterbh;
	}

	/**
	 * @param chapterbh the chapterbh to set
	 */
	public void setChapterbh(String chapterbh) {
		this.chapterbh = chapterbh;
	}

	/**
	 * @return the _jxnrlist
	 */
	public List<Jxnr_z> get_jxnrlist() {
		return _jxnrlist;
	}

	/**
	 * @param _jxnrlist the _jxnrlist to set
	 */
	public void set_jxnrlist(List<Jxnr_z> _jxnrlist) {
		this._jxnrlist = _jxnrlist;
	}

	/**
	 * @{ReceiveJhId.action}
	 * 接收功能
	 * 在跳转到教学内容的时候，需要接收教学计划的id，此action接收到后放在一个静态变量中供下面的action用
	*/
	/**
	 * @{方法名}
	 * @param {引入参数名} {引入参数说明}
	 * @return {返回参数名} {返回参数说明}
	 * @{方法的功能/动作描述}
	 * @exception {说明在某情况下,将发生什么异常}
	*/
	@Action(value="/ReceiveJhId",results={@Result(name="root",type="json")})
	public String ReceId(){
		if(jhid!=0){
			//ID = jhid;
			HttpSession hs= ServletActionContext.getRequest().getSession();
			hs.setAttribute("chapterName", chapterName);
			hs.setAttribute("chapterbh", chapterbh);
			hs.setAttribute("keci", jhid);
		}
		return "root";
	}
	
	/**
	 * @{ToJxnr.action}
	 * 跳转功能
	 * 当需要查看教学计划的教学内容时调用此action，会跳转到对应的教学内容页面
	 * 方法的两个变量主要是为了添加教学内容时服务的
	*/
	@Action(value="/ToJxnr",results={@Result(name="success",location="/page/xuexi/jxnr.jsp")})
	public String TOJxnr(){
		HttpSession hs = ServletActionContext.getRequest().getSession();
		HttpServletRequest  rq = ServletActionContext.getRequest();
		//kkch=jhid;//仅作章
		hs.setAttribute("kkch", jhid);
		zmc=this.baseService.find(CourseChapter.class, jhid).getCName();
		rq.setAttribute("zmc", zmc);
		//this.scwj = this.baseService.find(Scwj.class);
		//this.wjlxlist=this.baseService.find(Wjlx.class);
		return SUCCESS;
	}
	/**
	 * @{ToJxnrXlsj.action}
	 * 为教学内容页面产生测试试卷的下拉列表
	 * 
	*/
	@Action(value="/ToJxnrXlsj",results={@Result(name="success",type="json")})
	public String ToJxnrXlsj(){
		HttpSession hs = ServletActionContext.getRequest().getSession();
		int kkch=(Integer)hs.getAttribute("kkch");
		String uid=(String) hs.getAttribute("uid");
		String[] key_s={"UserId"};Object[] value_s={uid};
		List<Integer> listsjno=this.baseService.find(Integer.class, "Xsdyjl", "id.sjno", key_s, value_s);
		String[] keys={"zbh"};//////////////////////////////////////////////////////////////////////////
		Object[] values={kkch};
		List<Sjnr> listsjnr=this.baseService.find(Sjnr.class, "Sjnr",keys, values);
		for(Sjnr sjnr:listsjnr){//去掉学生已做过的试卷
			if(!listsjno.contains(sjnr.getSjno())){
				listsjname.add(sjnr);
			}
			
		}
		
		return SUCCESS;
	}
	
	/**
	 * @{JxnrJson.action}
	 * 显示功能
	 * 加载页面的时候回加载此action，将对应的教学内容显示在页面中即“资料列表”
	*/
	@Action(value="/JxnrJson",results={@Result(name="root",type="json")})
	public String Jxnrjson(){
		if("student".equals(s1)) {
			HttpSession hs= ServletActionContext.getRequest().getSession();
			hs.setAttribute("chapterName", chapterName);
			hs.setAttribute("chapterbh", chapterbh);
			hs.setAttribute("keci", jhid);
		}
		
		HttpSession hs = ServletActionContext.getRequest().getSession();
		int i_d=(Integer)hs.getAttribute("i_d");
		int ID=(Integer)hs.getAttribute("keci");
		String id1=i_d+""; String id2=i_d+",%"; String id3="%,"+i_d; String id4="%,"+i_d+",%";
		String hql="from Jxnr where (jxjhSz like '"+id1+"' or jxjhSz like '"+id2+"' or jxjhSz like '"+id3+"' or jxjhSz like '"+id4+"') and (jxjhYck.no ='"+ID+"')";

		rows=this.jxjhService.proc_jxnr(i_d, ID, page, rows_s);
		hql="select count(*) "+hql;
		total=this.baseService.getTotalSql(hql);
		return "root";
	}
	
	/**
	 * @{Ziliao.action}
	 * 跳转功能
	 * 负责跳转到教学内容的页面
	*/
	@Action(value="/Ziliao",results={@Result(name="success",location="/page/xuexi/ziliao.jsp")})
	public String Ziliao(){
		String name=nr;
		this.baseService.find(Scwj.class, "Scwj",name);
		return SUCCESS;
	}
	
	/**
	 * @{AddJxnr.action}
	 * 添加功能
	 * 添加教学内容。当把教学资料上传完后，将资料的信息存入到教学内容的表里和多媒体资料的表里
	 * 
	*/
	/**
	 * @{DeleteJxnr.action}
	 * 删除功能
	 * 根据教学内容的ID将对应的叫教学内容删除
	 * 
	*/
	@Action(value="/DeleteJxnr",results={@Result(name="success",type="json")})
	public String delete(){
		Jxnr jn = new Jxnr();
		jn = this.baseService.find(Jxnr.class, jxnr_id);
		this.baseService.delete(jn);
		return "success";
	}
	
	/**
	 * @{Find.action}
	 * 查找功能
	 * 当添加失败后return {/page/role/addORupdateFunctions.jsp} {添加功能页面}
	*/
	@Action(value="/Find",results={@Result(name="success",type="json")})
	public String find(){
		String[] keys = {"zlmc"};
		Object[] values = {mc};
		List<Dmtzl> j = this.baseService.find(Dmtzl.class, "Dmtzl", keys, values);
		bdwjms = j.get(0).getZlms();
		return "success";
	}
	
	/**
	 * @{BdAdd.action}
	 * 本地添加功能
	 * 将资料库中已有的功能添加到教学内容中
	*/
	@Action(value="/BdAdd",results={@Result(name="success",type="json")})
	public String bdadd(){
		HttpSession hs = ServletActionContext.getRequest().getSession();
		int i_d=(Integer)hs.getAttribute("i_d");
		int ID=(Integer)hs.getAttribute("keci");
		String hql="select count(*) from Jxnr where jxjhYck.no ='"+ID+"' and zlid='"+zlbh+"' and (jxjhSz like '"+i_d+"' or jxjhSz like '%"+i_d+"' or jxjhSz like '"+i_d+"%' or jxjhSz like '%"+i_d+"%')";
		int size=this.baseService.getTotalSql(hql);
		if(size!=0){
			tip="true";
		}else{
			Jxnr j = new Jxnr();
			Dmtzl d = new Dmtzl();
			d=this.baseService.find(Dmtzl.class, zlbh);
			j.setWjms(d.getZlms());
			j.setWjmc(d.getFilename());
			j.setZlid(d.getZlbh());
			JxjhYck jh = new JxjhYck();
			jh.setNo(ID);
			j.setJxjhYck(jh);
			j.setJxjhSz(i_d+"");
			String[] keys = {"zlbh"};
			Object[] values = {zlbh};
			j.setFilepath(this.baseService.find(String.class, "Scwj", "filepath", keys, values).get(0));
			this.baseService.save(j);
		}
		
		return "success";

	}
	
	/**
	 * @{queryZsd.action}
	 * 添加知识点功能
	 * 在添加完一个教学内容后会添加其对应的知识点，此action就是将知识点找出来
	*/
	@Action(value="/queryZsd",results={@Result(name="success",type="json")})
	public String query(){
		HttpSession hs = ServletActionContext.getRequest().getSession();
		int kkch= (Integer)hs.getAttribute("kkch");
		String[] keys={"zbh"};Object[] values={kkch};
		
		this.zsds=this.baseService.find(String.class, "Zsd", "zsdmc", keys, values);
		
		return "success";
	}
	
	@Action(value="/queryZsd2",results={@Result(name="success",type="json")})
	public String query1(){
//		HttpSession hs = ServletActionContext.getRequest().getSession();
//		int kkch= (Integer)hs.getAttribute("kkch");
//		
//		System.out.println("kkch："+kkch);
//		String[] keys={"zbh"};Object[] values={kkch};
//		
//		zsds=this.baseService.find(String.class, "Zsd", "zsdmc", keys, values);
		
		List<Zsd> zsds=this.baseService.find(Zsd.class);
		System.out.println("zsds："+zsds);
		for (int i = 0; i < zsds.size(); i++) {
			Zsd zsd=zsds.get(i);
			Map<String, String>map=new HashMap<String, String>();
			map.put("name", zsd.getZsdmc());
			list.add(map);
		}
		return "success";
	}
	
	/**
	 * @{queryKname.action}
	 * 查询课程名称功能
	 * 根据知识点名称查询出对应的课程名称，章名称，节名称显示到页面
	*/
	@Action(value="/queryKname",results={@Result(name="success",type="json")})
	public String queryKname(){
		String zsdmc=mc;
		if(zsdmc!=null&&zsdmc!=""){
			String[] keys = {"zsdmc"};
			Object[] values = {zsdmc};
			List<Zsd> z = this.baseService.find(Zsd.class, "Zsd", keys, values);
			int zbh = z.get(0).getId().getZbh();
			int kbh = z.get(0).getId().getCId();
//			CourseChapter c = this.baseService.find(CourseChapter.class, zbh);
//			this.kmc = c.getTCName();
//			this.zmc = c.getCName();
			
			String[] keys1 = {"zbh","c_id"};
			Object[] values1 = {zbh,kbh};
			Jie j = this.baseService.find(Jie.class, "Jie", keys1, values1).get(0);
			this.jmc = j.getZmc();
		}
		return "success";
	}
	
}
