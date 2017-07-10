/*
 *@(#)xx.xuexi.action
 *@Xgfx.java.java  
 *@创建时间:2011-8-5上午11:15:59
 *@作者：hp
 *@Copyright 2009-2010 河北北方学院信息科学与工程学院科研所 
 */

package xx.xuexi.action;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
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
import xx.collection.bean.Czt;
import xx.collection.bean.Cztda;
import xx.collection.bean.Pd;
import xx.collection.bean.Scwj;
import xx.collection.bean.Sjfx;
import xx.collection.bean.Sjnr;
import xx.collection.bean.Xsdyjl;
import xx.collection.bean.Xz;
import xx.collection.bean.XzpdXg;
import xx.collection.bean.Zlzsddy;
import xx.collection.bean.Zsd;
import xx.collection.bean.Zsdjjdy;
import xx.page.module.Conmen;
import xx.quanxian.service.BaseService;

import com.googlecode.jsonplugin.annotations.JSON;
import com.opensymphony.xwork2.ActionSupport;

/**
 * @Xgfx <code>{类名称}</code>
 * @author  {gq}
 * @version {版本,常用时间代替}
 * @{学习效果分析} 
 */
@Controller
@Scope("prototype")
@Namespace("")
@ParentPackage("default-package")
public class StudentCk extends ActionSupport {
	@Resource(name="baseService")
	
	private BaseService baseService;
	@Resource(name="adminService")
	private AdminService adminService;
	private int sjnno;
	
    private int xzth;
	private int pdth;
	private int czth;
	private int czdth;
	private int zsdbh;
		
	private List<Conmen> rows=new ArrayList<Conmen>();
	private List<XzpdXg> sj=new ArrayList<XzpdXg>();
	private int total;
	private int page;            //
	private int rows_s;
	
	List<Object[]> list=new ArrayList<Object[]>();
	private String tip;
	private String text1;
	private String text2;
	private String xzda;
	private Sjnr sjnr;
	
	/**
	 * @return the sjnr
	 */
	public Sjnr getSjnr() {
		return sjnr;
	}


	/**
	 * @param sjnr the sjnr to set
	 */
	public void setSjnr(Sjnr sjnr) {
		this.sjnr = sjnr;
	}


	/**
	 * @return the xzda
	 */
	public String getXzda() {
		return xzda;
	}


	/**
	 * @param xzda the xzda to set
	 */
	public void setXzda(String xzda) {
		this.xzda = xzda;
	}


	/**
	 * @return the text2
	 */
	public String getText2() {
		return text2;
	}


	/**
	 * @param text2 the text2 to set
	 */
	public void setText2(String text2) {
		this.text2 = text2;
	}


	/**
	 * @return the text1
	 */
	public String getText1() {
		return text1;
	}


	/**
	 * @param text1 the text1 to set
	 */
	public void setText1(String text1) {
		this.text1 = text1;
	}


	/**
	 * @return the czdth
	 */
	public int getCzdth() {
		return czdth;
	}


	/**
	 * @param czdth the czdth to set
	 */
	public void setCzdth(int czdth) {
		this.czdth = czdth;
	}


	/**
	 * @return the sj
	 */
	@JSON(serialize=false)
	public List<XzpdXg> getSj() {
		return sj;
	}


	/**
	 * @param sj the sj to set
	 */
	public void setSj(List<XzpdXg> sj) {
		this.sj = sj;
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

    

	/**
	 * @return the list
	 */
	public List<Object[]> getList() {
		return list;
	}


	/**
	 * @param list the list to set
	 */
	public void setList(List<Object[]> list) {
		this.list = list;
	}


	/**
	 * @return the sjnno
	 */
	public int getSjnno() {
		return sjnno;
	}


	/**
	 * @param sjno the sjno to set
	 */
	public void setSjnno(int sjnno) {
		this.sjnno = sjnno;
	}
	/**
	 * @return the rows
	 */
	public List<Conmen> getRows() {
		return rows;
	}


	/**
	 * @param rows the rows to set
	 */
	public void setRows(List<Conmen> rows) {
		this.rows = rows;
	}


	/**
	 * @return the total
	 */
	public int getTotal() {
		return total;
	}


	/**
	 * @param total the total to set
	 */
	public void setTotal(int total) {
		this.total = total;
	}


	/**
	 * @return the page
	 */
	public int getPage() {
		return page;
	}


	/**
	 * @param page the page to set
	 */
	public void setPage(int page) {
		this.page = page;
	}


	/**
	 * @return the rows_s
	 */
	public int getRows_s() {
		return rows_s;
	}


	/**
	 * @param rows_s the rows_s to set
	 */
	public void setRows_s(int rows_s) {
		this.rows_s = rows_s;
	}


	/**
	 * @return the xzth
	 */
	@JSON(serialize=false)
	public int getXzth() {
		return xzth;
	}


	/**
	 * @param xzth the xzth to set
	 */
	public void setXzth(int xzth) {
		this.xzth = xzth;
	}


	/**
	 * @return the pdth
	 */
	@JSON(serialize=false)
	public int getPdth() {
		return pdth;
	}


	/**
	 * @param pdth the pdth to set
	 */
	public void setPdth(int pdth) {
		this.pdth = pdth;
	}


	/**
	 * @return the czth
	 */
	@JSON(serialize=false)
	public int getCzth() {
		return czth;
	}


	/**
	 * @param czth the czth to set
	 */
	public void setCzth(int czth) {
		this.czth = czth;
	}


	/**
	 * @return the zsdbh
	 */
	@JSON(serialize=false)
	public int getZsdbh() {
		return zsdbh;
	}


	/**
	 * @param zsdbh the zsdbh to set
	 */
	public void setZsdbh(int zsdbh) {
		this.zsdbh = zsdbh;
	}

	
	/**
	 * @{为选择试卷页面产生json数据}
	 * @param {page, rows_s} {引入参数说明}
	 * @return {SUCCESS} {返回参数说明}
	 * @{}
	 * @exception {说明在某情况下,将发生什么异常}
	**/
	@Action(value="/stulistSj",results={@Result(name="success",type="json")})
	public String stulistSj(){
		SimpleDateFormat sDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); //时间格式化的格式 
		sDateFormat.setLenient(false);
		
		HttpSession hs = ServletActionContext.getRequest().getSession();
		String uid=(String) hs.getAttribute("uid");
		String[] keys=new String[1];
		keys[0]="UserId";
		String[] values=new String[1];
		values[0]=uid;
		List<Xsdyjl> t=this.baseService.find(Xsdyjl.class, "Xsdyjl", keys, values, "order by e_time desc", page, rows_s);
		for(int i=0;i<t.size();i++){
			
			Conmen element=new Conmen();
			
			element.setInt1(t.get(i).getSjnr().getSjno());
			element.setStr4(t.get(i).getSjnr().getZxx());
			String cjsj = sDateFormat.format(t.get(i).getETime()); //答卷完成时间
			element.setStr1(cjsj);
			if(t.get(i).getSjnr().getDq()){
				element.setStr2("0");
			}else{
				element.setStr2("1");
			}
			rows.add(element);
		
	    }
		total=this.baseService.getTotalPages("Xsdyjl", keys, values);
		return SUCCESS;
	}
	
	/**
	 * @{学生选择试卷}
	 * @param {sjno} {引入参数说明}
	 * @return {SUCCESS} {返回参数说明}
	 * @{跳转到stusjtm.jsp}
	 * @exception {说明在某情况下,将发生什么异常}
	**/
	@Action(value="/stuxzsj",results={@Result(name="success",location="/page/xgfx/stusjtm.jsp"),
			                         @Result(name="input",location="/page/xgfx/stuSj.jsp")})
	public String stuxzsj(){
		int sjno=sjnno;
		
		HttpServletRequest rq=ServletActionContext.getRequest();
		rq.setAttribute("sjnooo", sjno);
		HttpSession  hs=rq.getSession();
		hs.setAttribute("sjno", sjno);
		
		sjnr=this.baseService.find(Sjnr.class, sjno);
		Sjfx sjfx=this.baseService.find(Sjfx.class, sjno);
		if(sjfx==null||sjfx.getSjfx().equals("")){
			text1="未评价！";
		}else{
			text1=sjfx.getSjfx();
		}
		rq.setAttribute("sjfx", text1);
		
		String[] keys=new String[1];
		keys[0]="sjno1";
		Integer[] values=new Integer[1];
		values[0]=sjno;
		String[] prop=new String[2];
		prop[0]="zqno";
		prop[1]="cwno";
		 //判断试卷sjno是否已被分析过了
		
		List<List<Integer>> xzpdxg=this.baseService.find(Integer.class, "XzpdXg", prop, keys, values);
		int size=xzpdxg.size();
		if(size==0){
			rq.setAttribute("thwfx", "老师还未评分");
			return INPUT;//表示老师还未评分
		}else{
			return SUCCESS;//表示老师已分析过
		}
		
		
	}
	
	/**
	 * @{为/page/xgfx/stusjtm.jsp页面产生json数据}
	 * @param {page, rows_s} {引入参数说明}
	 * @return {SUCCESS} {返回参数说明}
	 * @{}
	 * @exception {说明在某情况下,将发生什么异常}
	**/
	@Action(value="/stulistXz",results={@Result(name="success",type="json")})
	public String stulistXz(){
		HttpSession hs = ServletActionContext.getRequest().getSession();
		int sjno=(Integer)hs.getAttribute("sjno");
		
		String userid=(String) hs.getAttribute("uid");
		List<Xz> t=this.adminService.chaTm1(sjno, 1, page, rows_s);
		//调存储过程查出所有错的选择题号
		List<String> st=this.adminService.proc_cwth(sjno, userid, 1);
        for(int i=0;i<t.size();i++){
        	String th=t.get(i).getTh().toString();
        	Conmen element=new Conmen();
        	
        	String[] keys=new String[3];keys[0]="th";keys[1]="lx";keys[2]="sjno1";
	    	Object[] values=new Object[3];values[0]=th;values[1]=1;values[2]=sjno;
	    	XzpdXg xg=this.baseService.find(XzpdXg.class, "XzpdXg", keys, values).get(0);
		    	if(xg.getFxdnr()==null||xg.getFxdnr().equals("")){//设置element的是否有分析内容
			    	   element.setStr1("未分析！");
			    }else{
			    	   
			    	   element.setStr1(xg.getFxdnr());
			    }
		    	if(xg.getJjdy()==null||xg.getJjdy().equals("")){//设置element的是否有答疑内容
			    	   element.setStr5("未答疑！");
			    }else{
			    	   
			    	   element.setStr5(xg.getJjdy());
			    }
	    	element.setInt1(t.get(i).getTh());//初步思路adminservice中写4个
			element.setStr4(t.get(i).getTg());
			element.setStr6(t.get(i).getZsd().getZsdmc());
			String xzda="A."+t.get(i).getXx1()+" B."+t.get(i).getXx2()+" C."+t.get(i).getXx3()+" D."+t.get(i).getXx4();
			element.setStr7(xzda);
		    if(st.contains(th)){
		    	element.setStr2("错");
			}else{
		    	element.setStr2("对");
			}
			element.setStr8(t.get(i).getDa());
			
			rows.add(element);
		
	    }
		total=this.adminService.totalAdmin(sjno, 1);
		return SUCCESS;
	}
	/**
	 * @{为/page/xgfx/stusjtm.jsp页面产生json数据}
	 * @param {page, rows_s} {引入参数说明}
	 * @return {SUCCESS} {返回参数说明}
	 * @{}
	 * @exception {说明在某情况下,将发生什么异常}
	**/
	@Action(value="/stulistPd",results={@Result(name="success",type="json")})
	public String stulistPd(){
		HttpSession hs = ServletActionContext.getRequest().getSession();
		int sjno=(Integer)hs.getAttribute("sjno");
		
		String userid=(String) hs.getAttribute("uid");
		List<Pd> t=this.adminService.chaTm2(sjno, 2, page, rows_s);
		List<String> st=this.adminService.proc_cwth(sjno, userid, 2);
        for(int i=0;i<t.size();i++){
        	String th=t.get(i).getTh().toString();
        	Conmen element=new Conmen();
        	
        	String[] keys=new String[3];keys[0]="th";keys[1]="lx";keys[2]="sjno1";
	    	Object[] values=new Object[3];values[0]=th;values[1]=2;values[2]=sjno;
	    	XzpdXg xg=this.baseService.find(XzpdXg.class, "XzpdXg", keys, values).get(0);
		    	if(xg.getFxdnr()==null||xg.getFxdnr().equals("")){//设置element的是否有分析内容
			    	   element.setStr1("未分析！");
			    }else{
			    	   
			    	   element.setStr1(xg.getFxdnr());
			    }
		    	if(xg.getJjdy()==null||xg.getJjdy().equals("")){//设置element的是否有答疑内容
			    	   element.setStr5("未答疑！");
			    }else{
			    	   
			    	   element.setStr5(xg.getJjdy());
			    }
	    	element.setInt1(t.get(i).getTh());//初步思路adminservice中写4个
			element.setStr4(t.get(i).getTg());
			element.setStr6(t.get(i).getZsd().getZsdmc());
		    if(st.contains(th)){
		    	element.setStr2("错");
			}else{
		      element.setStr2("对");
			}
		    
			if(t.get(i).getDa()!=1){
			   element.setStr8("错");
			}else{
			   element.setStr8("对");
			}
			rows.add(element);
		
	    }
		total=this.adminService.totalAdmin(sjno, 2);

		return SUCCESS;
	}
	/**
	 * @{为/page/xgfx/stusjtm.jsp页面产生json数据}
	 * @param {page, rows_s} {引入参数说明}
	 * @return {SUCCESS} {返回参数说明}
	 * @{}
	 * @exception {说明在某情况下,将发生什么异常}
	**/
	@Action(value="/stulistCzt",results={@Result(name="success",type="json")})
	public String stulistCzt(){
		HttpSession hs = ServletActionContext.getRequest().getSession();
		int sjno=(Integer)hs.getAttribute("sjno");
		
		String userid=(String) hs.getAttribute("uid");
		List<Czt> t=this.adminService.chaTm3(sjno, 3, page, rows_s);
		List<String> st=this.adminService.proc_cwth(sjno, userid, 3);
        for(int i=0;i<t.size();i++){
        	String c1=String.valueOf(t.get(i).getId().getDtTh());
 			String c2=String.valueOf(t.get(i).getId().getSxh());
 			String th=c1.concat("|").concat(c2);
        	String[] keys=new String[2];
        	keys[0]="sxh";
        	keys[1]="dt_th";
        	Integer[] values= new Integer[2];
        	values[0]=t.get(i).getId().getSxh();
        	values[1]=t.get(i).getId().getDtTh();
        	Conmen element=new Conmen();
        	
        	String[] key_s=new String[3];key_s[0]="th";key_s[1]="lx";key_s[2]="sjno1";
	    	Object[] value_s=new Object[3];value_s[0]=th;value_s[1]=3;value_s[2]=sjno;
	    	XzpdXg xg=this.baseService.find(XzpdXg.class, "XzpdXg", key_s, value_s).get(0);
		    	if(xg.getFxdnr()==null||xg.getFxdnr().equals("")){//设置element的是否有分析内容
			    	   element.setStr1("未分析！");
			    }else{
			    	  
			    	   element.setStr1(xg.getFxdnr());
			    }
		    	if(xg.getJjdy()==null||xg.getJjdy().equals("")){//设置element的是否有答疑内容
			    	   element.setStr7("未答疑！");
			    }else{
			    	  
			    	   element.setStr7(xg.getJjdy());
			    }
		    if(st.contains(th)){
		    	List<Cztda> listcztda=this.baseService.find(Cztda.class, "Cztda", keys, values);
		    	String zsdmc="";
		    	for(int j=0;j<listcztda.size();j++){
		    		int zsdbh=listcztda.get(j).getZsd().getId().getZsdbh();
			    	String[] keys1=new String[1];
					keys1[0]="zsdbh";
					Integer[] values1=new Integer[1];
					values1[0]=zsdbh;
					if(j==0){
						zsdmc=zsdmc+this.baseService.find(Zsd.class, "Zsd", keys1, values1).get(0).getZsdmc();
					}else{
						zsdmc=zsdmc+","+this.baseService.find(Zsd.class, "Zsd", keys1, values1).get(0).getZsdmc();
					}
					
		    	}
		    	element.setInt1(t.get(i).getId().getSxh());//初步思路adminservice中写4个
				element.setInt2(t.get(i).getId().getDtTh());
				element.setStr4(t.get(i).getTg());
				element.setStr5(t.get(i).getCztd().getTg());
				element.setStr2("错");
				element.setStr6(zsdmc);
		    }else{
		    	List<Cztda> listcztda=this.baseService.find(Cztda.class, "Cztda", keys, values);
		    	String zsdmc="";
		    	for(int j=0;j<listcztda.size();j++){
		    		int zsdbh=listcztda.get(j).getZsd().getId().getZsdbh();
			    	String[] keys1=new String[1];
					keys1[0]="zsdbh";
					Integer[] values1=new Integer[1];
					values1[0]=zsdbh;
					if(j==0){
						zsdmc=zsdmc+this.baseService.find(Zsd.class, "Zsd", keys1, values1).get(0).getZsdmc();
					}else{
						zsdmc=zsdmc+","+this.baseService.find(Zsd.class, "Zsd", keys1, values1).get(0).getZsdmc();
					}
					
		    	}
		    	element.setInt1(t.get(i).getId().getSxh());//初步思路adminservice中写4个
				element.setInt2(t.get(i).getId().getDtTh());
				element.setStr4(t.get(i).getTg());
				element.setStr5(t.get(i).getCztd().getTg());
				element.setStr2("对");
				element.setStr6(zsdmc);
		    }
		    
			
			rows.add(element);
		
	    }
		total=this.adminService.totalAdmin(sjno, 3);

		return SUCCESS;
	}
	/**
	 * @{为/page/xgfx/stusjtm.jsp页面产生json数据}
	 * @param {page, rows_s} {引入参数说明}
	 * @return {SUCCESS} {返回参数说明}
	 * @{}
	 * @exception {说明在某情况下,将发生什么异常}
	**/
	@Action(value="/stulistZsd",results={@Result(name="success",type="json")})
	public String stulistZsd(){
		HttpSession hs = ServletActionContext.getRequest().getSession();
		int sjno=(Integer)hs.getAttribute("sjno");
		
		List<Zsd> t=this.adminService.chaTm4(sjno, 4, page, rows_s);
		
        for(int i=0;i<t.size();i++){
			
			Conmen element=new Conmen();
			
	    	Zsdjjdy xg=this.baseService.find(Zsdjjdy.class, t.get(i).getId().getZsdbh());
	    	if(xg!=null){
	    		if(xg.getFxdnr()==null||xg.getFxdnr().equals("")){//设置element的是否有分析内容
			    	   element.setStr1("未分析！");
			    }else{
			    	   
			    	   element.setStr1(xg.getFxdnr());
			    }
		    	if(xg.getJjdy()==null||xg.getJjdy().equals("")){//设置element的是否有答疑内容
			    	   element.setStr7("未答疑！");
			    }else{
			    	  
			    	   element.setStr7(xg.getJjdy());
			    }
	    	}else{
	    		element.setStr1("未分析！");
	    		element.setStr7("未答疑！");
	    	}
		    	
			element.setInt1(t.get(i).getId().getZsdbh());//初步思路adminservice中写4个
			element.setStr4(t.get(i).getZsdmc());
			element.setStr5(t.get(i).getZsdms());
			
			rows.add(element);
		
	    }
		total=this.adminService.totalAdmin(sjno, 4);

		return SUCCESS;
	}

	
	/**
	 * @{为/page/xgfx/stuCk.jsp页面产生json数据}
	 * @param {xzth，pdth，cth，zsdbh} {引入参数说明}
	 * @return {SUCCESS} {返回参数说明}
	 * @{}
	 * @exception {说明在某情况下,将发生什么异常}
	**/
	
	@Action(value="/ListZl",results={@Result(name="success",type="json")})
	public String ListZl(){
		HttpSession hs = ServletActionContext.getRequest().getSession();
		hs.setAttribute("xztH", xzth);
		hs.setAttribute("pdtH", pdth);
		//xztH=xzth;
		//pdtH=pdth;
		String c1=String.valueOf(czdth);
		String c2=String.valueOf(czth);
		String cth=c1.concat("|").concat(c2);
		hs.setAttribute("cztH", cth);
		hs.setAttribute("zsdbH", zsdbh);
		//cztH=cth;
		//zsdbH=zsdbh;
		return SUCCESS;
	}
	
	/**
	 * @{为/page/xgfx/stuCk.jsp页面产生json数据}
	 * @param {xzth，pdth，cth，zsdbh} {引入参数说明}
	 * @return {SUCCESS} {返回参数说明}
	 * @{}
	 * @exception {说明在某情况下,将发生什么异常}
	**/
	
	@Action(value="/stuCkListZl",results={@Result(name="success",type="json")})
	public String stuCkListZl(){
		HttpSession hss = ServletActionContext.getRequest().getSession();
		int sjno=(Integer)hss.getAttribute("sjno");
		int xztH=(Integer)hss.getAttribute("xztH");
		int pdtH=(Integer)hss.getAttribute("pdtH");
		String cztH=(String)hss.getAttribute("cztH");
		int zsdbH=(Integer)hss.getAttribute("zsdbH");
		
		int to=0;
        if(xztH!=0){
			String[] keys=new String[3];
			keys[0]="th";
			keys[1]="lx";
			keys[2]="sjno1";
			Integer[] values=new Integer[3];
			values[0]=xztH;
			values[1]=1;
			values[2]=sjno;
			sj=this.baseService.find(XzpdXg.class, "XzpdXg",keys, values);
			int zsdbh=sj.get(0).getId().getZsdbh();
			String[] key_s=new String[1];key_s[0]="zsdbh";Object[] value_s=new Object[1];value_s[0]=zsdbh;
			List<Zlzsddy> zlzsddy=this.baseService.find(Zlzsddy.class, "Zlzsddy",key_s, value_s,"order by zlbh asc",page, rows_s);
			for(int m=0;m<zlzsddy.size();m++){
				key_s[0]="zlbh";
				value_s[0]=zlzsddy.get(m).getId().getZlbh();
				List<Scwj> scwj=this.baseService.find(Scwj.class, "Scwj", key_s, value_s);
				    Conmen element=new Conmen();
					element.setInt1(scwj.get(0).getDmtzl().getZlbh());
					element.setInt2(scwj.get(0).getNo());
					String filename=scwj.get(0).getFilename();
					element.setStr2(scwj.get(0).getDmtzl().getZlmc());
					element.setStr4(scwj.get(0).getFilepath());
//					int j=filename.indexOf(".");
//					filename=filename.substring(0,j);
					element.setStr1(filename);
					element.setStr6(zlzsddy.get(m).getZsd().getZsdmc());
					rows.add(element);
				
			}
			key_s[0]="zsdbh";value_s[0]=zsdbh;
			total=this.baseService.find(Zlzsddy.class, "Zlzsddy", key_s, value_s).size();

		}else if(pdtH!=0){
			String[] keys=new String[3];
			keys[0]="th";
			keys[1]="lx";
			keys[2]="sjno1";
			Integer[] values=new Integer[3];
			values[0]=pdtH;
			values[1]=2;
			values[2]=sjno;
			sj=this.baseService.find(XzpdXg.class, "XzpdXg",keys, values);
			int zsdbh=sj.get(0).getId().getZsdbh();
			String[] key_s=new String[1];key_s[0]="zsdbh";Object[] value_s=new Object[1];value_s[0]=zsdbh;
			List<Zlzsddy> zlzsddy=this.baseService.find(Zlzsddy.class, "Zlzsddy",key_s, value_s,"order by zlbh asc",page, rows_s);
			for(int m=0;m<zlzsddy.size();m++){
				key_s[0]="zlbh";
				value_s[0]=zlzsddy.get(m).getId().getZlbh();
				List<Scwj> scwj=this.baseService.find(Scwj.class, "Scwj", key_s, value_s);
				    Conmen element=new Conmen();
					element.setInt1(scwj.get(0).getDmtzl().getZlbh());
					element.setInt2(scwj.get(0).getNo());
					String filename=scwj.get(0).getFilename();
					element.setStr2(scwj.get(0).getDmtzl().getZlmc());
					element.setStr4(scwj.get(0).getFilepath());
//					int j=filename.indexOf(".");
//					filename=filename.substring(0,j);
					element.setStr1(filename);
					element.setStr6(zlzsddy.get(m).getZsd().getZsdmc());
					rows.add(element);
				
			}
			key_s[0]="zsdbh";value_s[0]=zsdbh;
			total=this.baseService.find(Zlzsddy.class, "Zlzsddy", key_s, value_s).size();

		}else if(!cztH.equals("0|0")){
			int index=cztH.indexOf("|");
			int dtth=Integer.parseInt(cztH.substring(0, index));
			int sxh=Integer.parseInt(cztH.substring(index+1));
			List<Scwj> listscwj=this.baseService.findScwj(sjno, sxh, dtth);
				HttpSession hs=ServletActionContext.getRequest().getSession();
				List<String> arr=(List<String>) hs.getAttribute("arr");
				hs.removeAttribute("arr");//去除“arr”所占session空间
			if(listscwj.size()!=0){
				//对题号List处理---进而实现分页
				
				int fromIndex=rows_s*(page - 1);
				int toIndex=page*rows_s - 1;
				int length=listscwj.size()- 1;
				int mq=listscwj.size() - fromIndex;
				List<Scwj> th2=new ArrayList<Scwj>();
				if(toIndex<=length){
					 th2=listscwj.subList(fromIndex, rows_s);
					 arr=arr.subList(fromIndex, rows_s);
				}else{
					if(length>fromIndex){
						
						//注意了，sublist()方法是从fromIndex下标开始，数上toIndex个。
						th2=listscwj.subList(fromIndex, length+1);
						arr=arr.subList(fromIndex, length+1);
					}else if(length==fromIndex){
						th2.add(listscwj.get(fromIndex));
						arr.add(arr.get(fromIndex));
					}
					 
				}
				for(int i=0;i<th2.size();i++){
					Conmen element=new Conmen();
					element.setInt1(th2.get(i).getDmtzl().getZlbh());
					element.setInt2(th2.get(i).getNo());
					String filename=th2.get(i).getFilename();
					element.setStr2(th2.get(i).getDmtzl().getZlmc());
					element.setStr4(th2.get(i).getFilepath());
//					int j=filename.indexOf(".");
//					filename=filename.substring(0,j);
					element.setStr1(filename);
					element.setStr6(arr.get(i));
					rows.add(element);
				}
				total=listscwj.size();
				
			}

		}else{
			String[] key_s=new String[1];key_s[0]="zsdbh";Object[] value_s=new Object[1];value_s[0]=zsdbH;
			List<Zlzsddy> zlzsddy=this.baseService.find(Zlzsddy.class, "Zlzsddy",key_s, value_s,"order by zlbh asc",page, rows_s);
			for(int m=0;m<zlzsddy.size();m++){
				key_s[0]="zlbh";
				value_s[0]=zlzsddy.get(m).getId().getZlbh();
				List<Scwj> scwj=this.baseService.find(Scwj.class, "Scwj", key_s, value_s);
				    Conmen element=new Conmen();
					element.setInt1(scwj.get(0).getDmtzl().getZlbh());
					element.setInt2(scwj.get(0).getNo());
					String filename=scwj.get(0).getFilename();
					element.setStr2(scwj.get(0).getDmtzl().getZlmc());
					element.setStr4(scwj.get(0).getFilepath());
//					int j=filename.indexOf(".");
//					filename=filename.substring(0,j);
					element.setStr1(filename);
					element.setStr6(zlzsddy.get(m).getZsd().getZsdmc());
					rows.add(element);
				
			}
			key_s[0]="zsdbh";value_s[0]=zsdbh;
			total=this.baseService.find(Zlzsddy.class, "Zlzsddy", key_s, value_s).size();

		}
        
		return SUCCESS;
	}
	/**
	 * @{为/page/xgfx/stusjtm.jsp页面<div id="zsdst">产生json数据}
	 * @param {zsdbH} {引入参数说明}
	 * @return {SUCCESS} {返回参数说明}
	 * @{}
	 * @exception {说明在某情况下,将发生什么异常}
	**/
	
	@Action(value="/zsdst_xz",results={@Result(name="success",type="json")})
	public String zsdst_xz(){
		HttpSession hs = ServletActionContext.getRequest().getSession();
		int zsdbH=(Integer)hs.getAttribute("zsdbH");
		 
		String[] keys=new String[1];keys[0]="zsdbh";
		Object[] values=new Object[1];values[0]=zsdbH;
		List<Xz> list=this.baseService.find(Xz.class, "Xz", keys, values, "order by th desc", page, rows_s);
		for(int i=0;i<list.size();i++){
			Conmen element=new Conmen();
			String s=list.get(i).getTg();
			
			element.setStr1(s);
			String xzda="A."+list.get(i).getXx1()+" B."+list.get(i).getXx2()+" C."+list.get(i).getXx3()+" D."+list.get(i).getXx4();
			element.setStr2(xzda);
			element.setStr8(list.get(i).getDa());
			rows.add(element);
		}
		total=this.baseService.find(Xz.class, "Xz", keys, values).size();
		return SUCCESS;
	}
	/**
	 * @{为/page/xgfx/stusjtm.jsp页面<div id="zsdst">产生json数据}
	 * @param {zsdbH} {引入参数说明}
	 * @return {SUCCESS} {返回参数说明}
	 * @{}
	 * @exception {说明在某情况下,将发生什么异常}
	**/
	
	@Action(value="/zsdst_pd",results={@Result(name="success",type="json")})
	public String zsdst_pd(){
		HttpSession hs = ServletActionContext.getRequest().getSession();
		int zsdbH=(Integer)hs.getAttribute("zsdbH");
		
		String[] keys=new String[1];keys[0]="zsdbh";
		Object[] values=new Object[1];values[0]=zsdbH;
		List<Pd> list=this.baseService.find(Pd.class, "Pd", keys, values, "order by th asc", page, rows_s);
		for(int i=0;i<list.size();i++){
			Conmen element=new Conmen();
			element.setStr1(list.get(i).getTg());
			if(list.get(i).getDa()!=1){
				element.setStr8("错");
			}else{
				element.setStr8("对");
			}
			
			rows.add(element);
		}
		total=this.baseService.find(Pd.class, "Pd", keys, values).size();
		return SUCCESS;
	}
	/**
	 * @{为/page/xgfx/stusjtm.jsp页面<div id="zsdst">产生json数据}
	 * @param {zsdbH} {引入参数说明}
	 * @return {SUCCESS} {返回参数说明}
	 * @{}
	 * @exception {说明在某情况下,将发生什么异常}
	**/
	
	@Action(value="/zsdst_czt",results={@Result(name="success",type="json")})
	public String zsdst_czt(){
		HttpSession hs = ServletActionContext.getRequest().getSession();
		int zsdbH=(Integer)hs.getAttribute("zsdbH");
		
		String[] keys=new String[1];keys[0]="zsdbh";
		Object[] values=new Object[1];values[0]=zsdbH;
		List<Cztda> list=this.baseService.find(Cztda.class, "Cztda", keys, values, "order by dt_th asc", page, rows_s);
		for(int i=0;i<list.size();i++){
			Conmen element=new Conmen();
			element.setStr1(list.get(i).getCzt().getTg());
			element.setStr2(list.get(i).getCzt().getCztd().getTg());
			
			rows.add(element);
		}
		total=this.baseService.find(Cztda.class, "Cztda", keys, values).size();
		return SUCCESS;
	}

}
