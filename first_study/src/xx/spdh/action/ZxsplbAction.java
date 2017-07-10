/*
 *@(#)xx.spdh.action
 *@Zxsplb.java.java  
 *@创建时间:2011-8-20上午10:39:33
 *@作者：朱永科
 *@Copyright 2009-2010 河北北方学院信息科学与工程学院科研所 
 */

package xx.spdh.action;

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

import xx.collection.bean.Dmtzl;
import xx.collection.bean.Scwj;
import xx.collection.bean.Zlzsddy;
import xx.quanxian.service.BaseService;
import xx.spdh.bean.ZxspLb;


import com.googlecode.jsonplugin.annotations.JSON;
import com.opensymphony.xwork2.ActionSupport;

/**
 * @ZxsplbAction <code>{类名称}</code>
 * @author  {作者}
 * @version {版本,常用时间代替}
 * @{功能描述:} 
 */
@Controller
@Scope("prototype")
@ParentPackage("default-package")
@Namespace("")
@SuppressWarnings("serial")
public class ZxsplbAction extends ActionSupport {

	@Resource(name="baseService")
	private BaseService baseservice;
	
	private List<Dmtzl> dmtzllist = new ArrayList<Dmtzl>();
	private List<Dmtzl> dmtzllist1 = new ArrayList<Dmtzl>();
	private List<Scwj> scwjlist = new ArrayList<Scwj>();
	private List<ZxspLb> rows = new ArrayList<ZxspLb>();//对象集合  
	private List<Zlzsddy> zlzsddylist = new ArrayList<Zlzsddy>();
	
	private int page;//当前页
	private int rows_s;//每一页显示的条数
    private int total;//记录数量
    
    private String fileName;
    private int no;
    private int zlbh;
    private String filename;
    private int zsdbh;
    private int zbh;
    private int c_id;
    private String zlmc;
    
    private String scwjword;
    private String scwjtype;
    
	private String a;
	private static int total1;
	
    @JSON(serialize=false)
	public BaseService getBaseservice() {
		return baseservice;
	}
	public void setBaseservice(BaseService baseservice) {
		this.baseservice = baseservice;
	}
	
	@JSON(serialize=false)
	public List<Dmtzl> getDmtzllist() {
		return dmtzllist;
	}
	public void setDmtzllist(List<Dmtzl> dmtzllist) {
		this.dmtzllist = dmtzllist;
	}
	
	@JSON(serialize=false)
	public List<Dmtzl> getDmtzllist1() {
		return dmtzllist1;
	}
	public void setDmtzllist1(List<Dmtzl> dmtzllist1) {
		this.dmtzllist1 = dmtzllist1;
	}
	
	@JSON(serialize=false)
	public List<Scwj> getScwjlist() {
		return scwjlist;
	}
	public void setScwjlist(List<Scwj> scwjlist) {
		this.scwjlist = scwjlist;
	}
	
	public List<ZxspLb> getRows() {
		return rows;
	}
	public void setRows(List<ZxspLb> rows) {
		this.rows = rows;
	}
	
	@JSON(serialize=false)
	public List<Zlzsddy> getZlzsddylist() {
		return zlzsddylist;
	}
	public void setZlzsddylist(List<Zlzsddy> zlzsddylist) {
		this.zlzsddylist = zlzsddylist;
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
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	
	@JSON(serialize=false)
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	
	@JSON(serialize=false)
	public int getZlbh() {
		return zlbh;
	}
	public void setZlbh(int zlbh) {
		this.zlbh = zlbh;
	}
	
	public String getFilename() {
		return filename;
	}
	public void setFilename(String filename) {
		this.filename = filename;
	}
	
	@JSON(serialize=false)
	public int getZsdbh() {
		return zsdbh;
	}
	public void setZsdbh(int zsdbh) {
		this.zsdbh = zsdbh;
	}
	
	@JSON(serialize=false)
	public int getZbh() {
		return zbh;
	}
	public void setZbh(int zbh) {
		this.zbh = zbh;
	}
	
	@JSON(serialize=false)
	public int getC_id() {
		return c_id;
	}
	public void setC_id(int c_id) {
		this.c_id = c_id;
	}
	
	@JSON(serialize=false)
	public String getZlmc() {
		return zlmc;
	}
	
	public void setZlmc(String zlmc) {
		this.zlmc = zlmc;
	}
	
	public String getScwjword() {
		return scwjword;
	}
	public void setScwjword(String scwjword) {
		this.scwjword = scwjword;
	}
	
	public String getScwjtype() {
		return scwjtype;
	}
	public void setScwjtype(String scwjtype) {
		this.scwjtype = scwjtype;
	}
	
	public String getA() {
		return a;
	}
	public void setA(String a) {
		this.a = a;
	}
	public static int getTotal1() {
		return total1;
	}
	public static void setTotal1(int total1) {
		ZxsplbAction.total1 = total1;
	}
	@Action(value="/zxsp",results={@Result(name="root",location="/spdh/video.jsp")})
	public String zxsp() {
		System.out.println(filename);
		System.out.println(zlbh);
		System.out.println(no);
//		String[] keys1 = new String[1];
//		keys1[0] = "filename";
//		Object[] values1 = new Object[1];
//		values1[0] =this.getFilename();
//		List<String> fp1 = this.baseservice.find(String.class, "Scwj", "id.filepath", keys1, values1);
		
		String[] keys1 = new String[1];
		keys1[0] = "zlbh";
		Object[] values1 = new Object[1];
		values1[0] =zlbh;
		List<String> fp1 = this.baseservice.find(String.class, "Scwj", "id.filepath", keys1, values1);
		
		String[] keys2 = new String[1];
		keys2[0] = "zlmc";
		Object[] values2 = new Object[1];
		values2[0] =zlmc;
		List<Integer> bb = this.baseservice.find(Integer.class, "Dmtzl", "zlbh", keys2, values2);
		
		int m = fp1.get(0).indexOf("\\") + 1;
		int n = fp1.get(0).lastIndexOf("\\");
		int j = fp1.get(0).lastIndexOf("\\") + 1;
		String fp2 = fp1.get(0).substring(m, n);
		String fp3 = fp1.get(0).substring(j, fp1.get(0).length());
		String fp4 = fp2 + "/" + fp3;
		
		HttpSession hs = ServletActionContext.getRequest().getSession();
		hs.setAttribute("fileName", fp4);
		hs.setAttribute("_zlbh",bb.get(0));
		
		return "root";
	}
	/**
	 * @{方法名：zxsplblist}
	 * @param {引入参数名} {引入参数说明}
	 * @return {返回参数名} {返回参数说明}
	 * @{方法的功能/动作描述}
	 * @exception {说明在某情况下,将发生什么异常}
	*/
	@Action(value="/zxsplblist",results={@Result(name="root",type="json")})
	public String ZxspLb(){
		String hql1 = "from Dmtzl where filename like '%.flv'";
		String hql2 = "select count(*)from Dmtzl where filename like '%.flv'";
		dmtzllist = this.baseservice.findSql(Dmtzl.class, hql1, page, rows_s);
		total=this.baseservice.getTotalSql(hql2);  //记录条数的记录
		String hql = "from Scwj where filename like '%"+".flv"+"%'";
		scwjlist = this.baseservice.findHql(Scwj.class, hql);
	
		for(int i=0; i<scwjlist.size(); i++) {
//			int j = dmtzllist.get(i).getFilename().indexOf(".");
//			filename = dmtzllist.get(i).getFilename().substring(0, j);
			ZxspLb element = new ZxspLb();
			element.setInt1(scwjlist.get(i).getNo());
			element.setInt2(scwjlist.get(i).getDmtzl().getZlbh());
			element.setStr1(scwjlist.get(i).getFilename());
			element.setStr2(dmtzllist.get(i).getZlms());
			element.setStr3(scwjlist.get(i).getDmtzl().getZlmc());
			element.setStr4(scwjlist.get(i).getDmtzl().getZlly());
			element.setStr5(scwjlist.get(i).getDmtzl().getZlscm());
			element.setStr6(scwjlist.get(i).getDmtzl().getWjlx().getLxm());
			element.setStr7(scwjlist.get(i).getDmtzl().getUserinfo().getUserId());
			element.setInt3(scwjlist.get(i).getDmtzl().getLlcs());
			element.setInt4(scwjlist.get(i).getDmtzl().getCssl());
			element.setInt5(scwjlist.get(i).getDmtzl().getChangdu());
			element.setDt(scwjlist.get(i).getDmtzl().getScrq());
			rows.add(element);
		}
        return "root";
	}
	
	//检验查询是否为空,并得出数据条数
	@Action(value="/searchscwj2",results={@Result(name="root",type="json")})
	public String searchscwj2(){
		String hql = "select count(*) from Dmtzl where "+scwjtype+" like '%"+scwjword+"%'";
		total1 = this.baseservice.getTotalSql(hql);
		if(total1!=0){
			a="1";
		}else{
			a="0";
		}
		return "root";
	}
	
	//知识点关键词查询
	@Action(value="/searchscwj3",results={@Result(name="success",type="json")})
	public String searchdmtzl3(){
		scwjlist = this.baseservice.findByTypage(Scwj.class,"Scwj",scwjtype,scwjword, "order by no asc", page, rows_s);
		total=this.baseservice.getTotal("Scwj");//记录条数
		for(int i=0;i<scwjlist.size();i++){
			ZxspLb element = new ZxspLb();
			element.setInt1(scwjlist.get(i).getNo());
			element.setInt2(scwjlist.get(i).getDmtzl().getZlbh());
			element.setStr1(scwjlist.get(i).getFilename());
			element.setStr2(scwjlist.get(i).getDmtzl().getZlms());
			element.setStr3(scwjlist.get(i).getDmtzl().getZlmc());
			element.setStr4(scwjlist.get(i).getDmtzl().getZlly());
			element.setStr5(scwjlist.get(i).getDmtzl().getZlscm());
			element.setStr6(scwjlist.get(i).getDmtzl().getWjlx().getLxm());
			element.setStr7(scwjlist.get(i).getDmtzl().getUserinfo().getUserId());
			element.setInt3(scwjlist.get(i).getDmtzl().getLlcs());
			element.setInt4(scwjlist.get(i).getDmtzl().getCssl());
			element.setInt5(scwjlist.get(i).getDmtzl().getChangdu());
			element.setDt(scwjlist.get(i).getDmtzl().getScrq());
			rows.add(element);   //把数据放进rows，实现分页查询显示
		}
		return SUCCESS;
	}
	
}
