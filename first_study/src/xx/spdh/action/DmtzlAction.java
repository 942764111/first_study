/*
 *@(#)xx.spdh.action
 *@DmtzlAction.java.java  
 *@创建时间:2011-8-6下午02:42:38
 *@作者：朱永科
 *@Copyright 2009-2010 河北北方学院信息科学与工程学院科研所 
 */

package xx.spdh.action;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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

import xx.adminservice.AdminService;
import xx.collection.bean.Dmtzl;

import xx.collection.bean.Scwj;
import xx.collection.bean.Userinfo;
import xx.collection.bean.Wjlx;
import xx.collection.bean.Xz;
import xx.collection.bean.Zysc;
import xx.md5.module.MD5Builder;
import xx.quanxian.service.BaseService;
import xx.spdh.bean.DmtZl;

import com.googlecode.jsonplugin.annotations.JSON;
import com.opensymphony.xwork2.ActionSupport;

/**
 * @DmtzlAction <code>{类名称}</code>
 * @author  {作者}
 * @version {版本,常用时间代替}
 * @{功能描述:多媒体资料的增删查改} 
 */
@Controller
@Namespace("")
@Scope("prototype")
@ParentPackage("default-package")
@SuppressWarnings("serial")
public class DmtzlAction extends ActionSupport {
	@Resource(name="baseService")
	private BaseService baseservice;
	@Resource(name="adminService")
	private AdminService adminservice;
	private List<DmtZl> rows = new ArrayList<DmtZl>();//对象集合 
	private List<Dmtzl> dmtzllist = new ArrayList<Dmtzl>();
	
	private int page;//当前页
	private int rows_s;//每一页显示的条数
	private int total;//记录数量
	
	private List<Wjlx> listdmtzl;
	private List<Userinfo> u;
	private Dmtzl dmtzl;
	private Scwj scwj;
	
	private Integer zlbh;
	private String userId;
	private String lxm;
	private String zlmc;
	private String filename;
	private String zlms;
	private String zlly;
	private String zlscm;
	private String zmfilename;
	private Date scrq;
	private Integer llcs;
	private Integer cssl;
	private Integer changdu;
	
	private String dmtzltype;
	private String dmtzlword;
	private int a;
	private String message = "";
	
	
	private static int total1;
	private int queryw;
	/**
	 * 用户查询代码begin
	 */
	private String QueryWord;
	private int Radio;
	private String filemess;
	
	public String getFilemess() {
		return filemess;
	}
	public void setFilemess(String filemess) {
		this.filemess = filemess;
	}
	/**
	 * 用户查询代码end
	 */
	
	@JSON(serialize=false)
	public BaseService getBaseservice() {
		return baseservice;
	}
	public void setBaseservice(BaseService baseservice) {
		this.baseservice = baseservice;
	}
	@JSON(serialize=false)
	public AdminService getAdminservice() {
		return adminservice;
	}

	public void setAdminservice(AdminService adminservice) {
		this.adminservice = adminservice;
	}

	

	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public List<DmtZl> getRows() {
		return rows;
	}

	public void setRows(List<DmtZl> rows) {
		this.rows = rows;
	}

	@JSON(serialize=false)
	public List<Dmtzl> getDmtzllist() {
		return dmtzllist;
	}

	public void setDmtzllist(List<Dmtzl> dmtzllist) {
		this.dmtzllist = dmtzllist;
	}
	
	@JSON(serialize=false)
	public Integer getZlbh() {
		return zlbh;
	}

	@JSON(serialize=true)
	public void setZlbh(Integer zlbh) {
		this.zlbh = zlbh;
	}

	@JSON(serialize=false)
	public List<Userinfo> getU() {
		return u;
	}

	public void setU(List<Userinfo> u) {
		this.u = u;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getRows_s() {
		return rows_s;
	}

	public void setRows_s(int rows_s) {
		this.rows_s = rows_s;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	@JSON(serialize=false)
	public List<Wjlx> getListdmtzl() {
		return listdmtzl;
	}

	public void setListdmtzl(List<Wjlx> listdmtzl) {
		this.listdmtzl = listdmtzl;
	}

	@JSON(serialize=false)
	public Dmtzl getDmtzl() {
		return dmtzl;
	}

	public void setDmtzl(Dmtzl dmtzl) {
		this.dmtzl = dmtzl;
	}

	@JSON(serialize=false)
	public Scwj getScwj() {
		return scwj;
	}

	public void setScwj(Scwj scwj) {
		this.scwj = scwj;
	}


	@JSON(serialize=false)
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	@JSON(serialize=false)
	public String getLxm() {
		return lxm;
	}

	public void setLxm(String lxm) {
		this.lxm = lxm;
	}

	public String getZlmc() {
		return zlmc;
	}

	public void setZlmc(String zlmc) {
		this.zlmc = zlmc;
	}

	@JSON(serialize=false)
	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	@JSON(serialize=false)
	public String getZlms() {
		return zlms;
	}

	public void setZlms(String zlms) {
		this.zlms = zlms;
	}

	@JSON(serialize=false)
	public String getZlly() {
		return zlly;
	}

	public void setZlly(String zlly) {
		this.zlly = zlly;
	}

	@JSON(serialize=false)
	public String getZlscm() {
		return zlscm;
	}

	public void setZlscm(String zlscm) {
		this.zlscm = zlscm;
	}

	@JSON(serialize=false)
	public String getZmfilename() {
		return zmfilename;
	}

	public void setZmfilename(String zmfilename) {
		this.zmfilename = zmfilename;
	}

	@JSON(serialize=false)
	public Date getScrq() {
		return scrq;
	}

	public void setScrq(Date scrq) {
		this.scrq = scrq;
	}

	@JSON(serialize=false)
	public Integer getLlcs() {
		return llcs;
	}

	public void setLlcs(Integer llcs) {
		this.llcs = llcs;
	}

	@JSON(serialize=false)
	public Integer getCssl() {
		return cssl;
	}

	public void setCssl(Integer cssl) {
		this.cssl = cssl;
	}

	@JSON(serialize=false)
	public Integer getChangdu() {
		return changdu;
	}

	public void setChangdu(Integer changdu) {
		this.changdu = changdu;
	}

	public String getDmtzltype() {
		return dmtzltype;
	}

	public void setDmtzltype(String dmtzltype) {
		this.dmtzltype = dmtzltype;
	}

	public String getDmtzlword() {
		return dmtzlword;
	}

	public void setDmtzlword(String dmtzlword) {
		this.dmtzlword = dmtzlword;
	}

	public int getA() {
		return a;
	}

	public void setA(int a) {
		this.a = a;
	}
	@JSON(serialize=false)
	public int getQueryw() {
		return queryw;
	}
	public void setQueryw(int queryw) {
		this.queryw = queryw;
	}
	
	/**
	 * @{dmtzlcxlist.action}
	 * @param {dmtzllist.action} {显示多媒体资料信息}
	 * @return {dmtzllist.action} {显示所有多媒体资料信息} 
	 * @{查询多媒体的保存路径}
	 * @exception {说明在某情况下,将发生什么异常}
	*/
	@Action(value="/dmtzlljcx",results={@Result(name="root",type="json",params={"includeProperties","message,filemess"})})
	public String dmtzlljcx(){
		List<Scwj> scwjlist = new ArrayList<Scwj>();
		scwjlist = this.baseservice.findHql(Scwj.class, "from Scwj s where s.dmtzl.zlbh = "+zlbh);
		for(Scwj ss:scwjlist)
		{
			message = ss.getFilepath();
			filemess = ss.getDmtzl().getZlmc();
		}
		return "root";
	}
	
	/**
	 * @{dmtzlcxlist.action}
	 * @param {dmtzllist.action} {显示多媒体资料信息}
	 * @return {dmtzllist.action} {显示所有多媒体资料信息} 
	 * @{调出用户查询的多媒体资料}
	 * @exception {说明在某情况下,将发生什么异常}
	*/
	@Action(value="/dmtzlcxlist",results={@Result(name="root",type="json",params={"includeProperties","rows.*,total"})})
	public String dmtzlcxlist() {
		HttpSession session1 = ServletActionContext.getRequest().getSession();
		QueryWord = (String) session1.getAttribute("querywordzy");
		Radio = Integer.parseInt((String) session1.getAttribute("radiozy"));
		String qw = "%"+QueryWord+"%";  //修改成存储过程所需要的数据格式
		List<String> ll = new ArrayList<String>();
		int a = (page-1)*rows_s;
		ll = this.adminservice.dmtcx(Radio, qw, a, rows_s); //接收存储过程的结果集
		if(ll.size()>1){
			for(int i=0;i<ll.size()-1;i++){
				String s = ll.get(i);
				String ss[] = new String[14];
				ss = s.split(",");
				
				DmtZl element = new DmtZl();
				element.setStr1(ss[1]);
				element.setStr2(ss[2]);
				element.setStr3(ss[3]);
				element.setStr4(ss[4]);
				element.setStr5(ss[5]);
				element.setStr6(ss[6]);
				element.setStr7(ss[7]);
				element.setStr8(ss[8]);
				element.setStr9(ss[13]);
				//将获取到的String类型转换成Date类型
				DateFormat fmt =new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");    
		        Date date = null;
				try {
					
					date = fmt.parse(ss[9]);
				} catch (ParseException e) {
					e.printStackTrace();
				}
				element.setDt(date);
//				System.out.println(element.getDt());
				element.setInt1(Integer.parseInt(ss[0]));
				element.setInt2(Integer.parseInt(ss[10]));
				element.setInt3(Integer.parseInt(ss[11]));
				element.setInt4(Integer.parseInt(ss[12]));
				rows.add(element);   //把数据放进rows，实现分页查询显示
			}
			total = Integer.parseInt(ll.get(ll.size()-1));
		}
		
		return "root";
	}
	/**
	  
	 * @{检查已收藏却被删除的多媒体资料}
	*/
	@Action(value="/jcdmtzl",results={@Result(name="success",type="json",params={"includeProperties","message"})})
	public String jcdmtzl(){
		HttpSession session1 = ServletActionContext.getRequest().getSession();
		userId = (String) session1.getAttribute("userid");
		List<Zysc> list = this.baseservice.findHql(Zysc.class, "from Zysc z where z.zylx=14 and z.id.userId='"+userId+"'");
		if(list.size()>0){
			for(Zysc z:list){
				message += z.getZybh();
				this.baseservice.delete(z);
			}
		}
		return SUCCESS;
	}

	/**
	 * @{dmtzlsclist.action}
	 * @param {dmtzllist.action} {显示多媒体资料信息}
	 * @return {dmtzllist.action} {显示所有多媒体资料信息} 
	 * @{调出用户收藏的多媒体资料}
	 * @exception {说明在某情况下,将发生什么异常}
	*/
	@Action(value="/dmtzlsclist",results={@Result(name="root",type="json",params={"includeProperties","rows.*,total"})})
	public String dmtzlsclist() {
		HttpSession session1 = ServletActionContext.getRequest().getSession();
		userId = (String) session1.getAttribute("userid");
		List<Zysc> list = this.baseservice.findSql(Zysc.class, "from Zysc z where z.zylx=4 and z.id.userId='"+userId+"'",page, rows_s);//分页查询
		for(int i=0;i<list.size();i++){
			List<Dmtzl> lxz = new ArrayList<Dmtzl>();
			lxz = this.baseservice.findHql(Dmtzl.class, "from Dmtzl d where d.zlbh="+list.get(i).getZybh());
			if(lxz.size()!=0){
				dmtzllist.add(lxz.get(0));
			}
		}
		List<Zysc> list1 = this.baseservice.findHql(Zysc.class, "from Zysc z where z.zylx=4 and z.id.userId='"+userId+"'");
		total = list1.size();
		for(int i=0; i<dmtzllist.size(); i++ ) {
			DmtZl element = new DmtZl();
			element.setStr1(dmtzllist.get(i).getUserinfo().getUserId());
			element.setStr2(dmtzllist.get(i).getWjlx().getLxm());
			element.setStr3(dmtzllist.get(i).getZlmc());
			element.setStr4(dmtzllist.get(i).getFilename());
			element.setStr5(dmtzllist.get(i).getZlms());
			element.setStr6(dmtzllist.get(i).getZlly());
			element.setStr7(dmtzllist.get(i).getZlscm());
			element.setStr8(dmtzllist.get(i).getZmfilename());
			element.setStr9(dmtzllist.get(i).getZlmd5());
			element.setDt(dmtzllist.get(i).getScrq());
			element.setInt1(dmtzllist.get(i).getZlbh());
			element.setInt2(dmtzllist.get(i).getLlcs());
			element.setInt3(dmtzllist.get(i).getCssl());
			element.setInt4(dmtzllist.get(i).getChangdu());
			rows.add(element);   //把数据放进rows，实现分页查询显示
		}
		return "root";
	}

	@Action(value="/dmtzllist",results={@Result(name="root",type="json")})
	public String dmtzllist() {
		dmtzllist = this.baseservice.findAll(Dmtzl.class, "Dmtzl", page, rows_s);
		total=this.baseservice.getTotal("Dmtzl");  //记录条数的记录  
		for(int i=0; i<dmtzllist.size(); i++ ) {
			DmtZl element = new DmtZl();
			element.setStr1(dmtzllist.get(i).getUserinfo().getUserId());
			element.setStr2(dmtzllist.get(i).getWjlx().getLxm());
			element.setStr3(dmtzllist.get(i).getZlmc());
			element.setStr4(dmtzllist.get(i).getFilename());
			element.setStr5(dmtzllist.get(i).getZlms());
			element.setStr6(dmtzllist.get(i).getZlly());
			element.setStr7(dmtzllist.get(i).getZlscm());
			element.setStr8(dmtzllist.get(i).getZmfilename());
			element.setStr9(dmtzllist.get(i).getZlmd5());
			element.setDt(dmtzllist.get(i).getScrq());
			element.setInt1(dmtzllist.get(i).getZlbh());
			element.setInt2(dmtzllist.get(i).getLlcs());
			element.setInt3(dmtzllist.get(i).getCssl());
			element.setInt4(dmtzllist.get(i).getChangdu());
			rows.add(element);   //把数据放进rows，实现分页查询显示
		}
		return "root";
	}
	
	/**
	 * @{deletedmtzl.action}
	 * @param {dmtzllist.action} {显示多媒体资料信息}
	 * @return {dmtzllist.action} {显示所有多媒体资料信息} 
	 * @{删除多媒体资料信息}
	 * @exception {说明在某情况下,将发生什么异常}
	*/
	@Action(value="/deletedmtzl",results={@Result(name="success",type="json")})
	public String deletedmtzl(){
		
		String[] keys2 = new String[1];
		keys2[0] = "zlmc";
		Object[] values2 = new Object[1];
		values2[0]=this.getZlmc();
		List<Dmtzl> dmtzl2 = this.baseservice.find(Dmtzl.class, "Dmtzl", keys2, values2);
		int zlbh2 = dmtzl2.get(0).getZlbh();
		
		dmtzl=this.baseservice.find(Dmtzl.class, zlbh2);//通过zlbh删除一条数据，zlbh要进行反序列化。
		
		this.baseservice.delete(dmtzl);// 删除数据
		return SUCCESS;
	}
	
	/**
	 * @{updatedmtzl}
	 * @param {wjlxlist.action} {显示文件类型信息}
	 * @return {wjlxlist.action} {显示所有文件类型信息}
	 * @{修改文件类型信息}
	 * @exception {说明在某情况下,将发生什么异常}
	 */
	@Action(value="/updatedmtzl",results={@Result(name="success",type="json")})
	public String updatedmtzl() {
		String md5 = this.getZlmc();
		MD5Builder md5b = new MD5Builder();
		String zlmd5;
		try {
			zlmd5 = md5b.EncoderByMd5(md5);
			
			Userinfo userinfo = new Userinfo();
			userinfo.setUserId(userId);
			
			Wjlx wjlx = new Wjlx();
			wjlx.setLxm(lxm);
			
			String[] keys1 = new String[1];
			keys1[0] = "zlmc";
			Object[] values1 = new Object[1];
			values1[0]=this.getZlmc();
			List<Dmtzl> dmtzl1 = this.baseservice.find(Dmtzl.class, "Dmtzl", keys1, values1);
			int zlbh1 = dmtzl1.get(0).getZlbh();
			
			Dmtzl dmtzl = new Dmtzl();
			dmtzl.setZlbh(zlbh1);
			dmtzl.setUserinfo(userinfo);
			dmtzl.setWjlx(wjlx);
			dmtzl.setZlmc(zlmc);
			dmtzl.setFilename(filename);
			dmtzl.setZlms(zlms);
			dmtzl.setZlly(zlly);
			dmtzl.setZlscm(zlscm);
			dmtzl.setZmfilename(zmfilename);
			dmtzl.setScrq(scrq);
			dmtzl.setLlcs(llcs);
			dmtzl.setCssl(cssl);
			dmtzl.setChangdu(changdu);			
			dmtzl.setZlmd5(zlmd5);
			this.baseservice.update(dmtzl);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	//检验查询是否为空,并得出数据条数
	@Action(value="/searchdmtzl1",results={@Result(name="root",type="json")})
	public String searchdmtzl1(){
		String hql = "select count(*) from Dmtzl where "+dmtzltype+" like '%"+dmtzlword+"%'";
		total1 = this.baseservice.getTotalSql(hql);
		if(total1!=0){
			a=1;
		}else{
			a=0;
		}
		return "root";
	}
	
	//多媒体资料查询
	@Action(value="/searchdmtzl",results={@Result(name="success",type="json")})
	public String searchdmtzl(){
		//dmtzllist = this.baseservice.findByTypage(Dmtzl.class,"Dmtzl",dmtzltype,dmtzlword, "order by zlbh asc", page, rows_s);
		dmtzllist = this.baseservice.findByTypage(Dmtzl.class, "Dmtzl", dmtzltype, dmtzlword, "order by lxm asc", page, rows_s);
		total = total1;//记录条数
		for(int i=0;i<dmtzllist.size();i++){
			DmtZl element = new DmtZl();
			element.setStr1(dmtzllist.get(i).getUserinfo().getUserId());
			element.setStr2(dmtzllist.get(i).getWjlx().getLxm());
			element.setStr3(dmtzllist.get(i).getZlmc());
			element.setStr4(dmtzllist.get(i).getFilename());
			element.setStr5(dmtzllist.get(i).getZlms());
			element.setStr6(dmtzllist.get(i).getZlly());
			element.setStr7(dmtzllist.get(i).getZlscm());
			element.setStr8(dmtzllist.get(i).getZmfilename());
			element.setStr9(dmtzllist.get(i).getZlmd5());
			element.setDt(dmtzllist.get(i).getScrq());
			element.setInt1(dmtzllist.get(i).getZlbh());
			element.setInt2(dmtzllist.get(i).getLlcs());
			element.setInt3(dmtzllist.get(i).getCssl());
			element.setInt4(dmtzllist.get(i).getChangdu());
			rows.add(element);   //把数据放进rows，实现分页查询显示
		}
		return SUCCESS;
	}
	//检验查询是否为空,并得出数据条数
	@Action(value="/searchdmtsc1",results={@Result(name="root",type="json")})
	public String searchdmtsc1(){
		HttpSession session1 = ServletActionContext.getRequest().getSession();
		userId = (String) session1.getAttribute("userid");
		int cxsl = 0;
		String hql = "select count(*) from Zysc z where z.zylx=4 and z.id.userId='"+userId+"' and z.zybh="+queryw;
//		System.out.println(queryw);
		cxsl = this.baseservice.getTotalSql(hql);
		if(cxsl!=0){
			a=1;
		}else{
			a=0;
		}
		return "root";
	}
	
	//多媒体资料查询
	@Action(value="/searchdmtsc",results={@Result(name="success",type="json")})
	public String searchdmtsc(){
		//dmtzllist = this.baseservice.findByTypage(Dmtzl.class,"Dmtzl",dmtzltype,dmtzlword, "order by zlbh asc", page, rows_s);
		dmtzllist = this.baseservice.findHql(Dmtzl.class, "from Dmtzl d where d.zlbh="+queryw);
		total = 1;//记录条数
		for(int i=0;i<dmtzllist.size();i++){
			DmtZl element = new DmtZl();
			element.setStr1(dmtzllist.get(i).getUserinfo().getUserId());
			element.setStr2(dmtzllist.get(i).getWjlx().getLxm());
			element.setStr3(dmtzllist.get(i).getZlmc());
			element.setStr4(dmtzllist.get(i).getFilename());
			element.setStr5(dmtzllist.get(i).getZlms());
			element.setStr6(dmtzllist.get(i).getZlly());
			element.setStr7(dmtzllist.get(i).getZlscm());
			element.setStr8(dmtzllist.get(i).getZmfilename());
			element.setStr9(dmtzllist.get(i).getZlmd5());
			element.setDt(dmtzllist.get(i).getScrq());
			element.setInt1(dmtzllist.get(i).getZlbh());
			element.setInt2(dmtzllist.get(i).getLlcs());
			element.setInt3(dmtzllist.get(i).getCssl());
			element.setInt4(dmtzllist.get(i).getChangdu());
			rows.add(element);   //把数据放进rows，实现分页查询显示
		}
		return SUCCESS;
	}
	
}
