/*
 *@(#)xx.xuexi.action
 *@Xgfx.java.java  
 *@����ʱ��:2011-8-5����11:15:59
 *@���ߣ�hp
 *@Copyright 2009-2010 �ӱ�����ѧԺ��Ϣ��ѧ�빤��ѧԺ������ 
 */

package xx.xuexi.action;



import java.text.DecimalFormat;
import java.text.NumberFormat;
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
import xx.collection.bean.Studentifno;
import xx.collection.bean.Wjlx;
import xx.collection.bean.Xz;
import xx.collection.bean.XzpdXg;
import xx.collection.bean.Zlzsddy;
import xx.collection.bean.Zsd;
import xx.collection.bean.Zsdjjdy;
import xx.page.module.Conmen;
import xx.page.module.TuxingClass;
import xx.quanxian.service.BaseService;

import com.googlecode.jsonplugin.annotations.JSON;
import com.opensymphony.xwork2.ActionSupport;

/**
 * @Xgfx <code>{������}</code>
 * @author  {gq}
 * @version {�汾,����ʱ�����}
 * @{ѧϰЧ������} 
 */
@Controller
@Scope("prototype")
@Namespace("")
@ParentPackage("default-package")
public class Xgfx extends ActionSupport {
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
	private String xzda;
	
	private List<Conmen> rows=new ArrayList<Conmen>();
	private List<XzpdXg> sj=new ArrayList<XzpdXg>();
	private int total;
	private int page;            //
	private int rows_s;
	
	List<Object> list=new ArrayList<Object>();
	private String tip;
	private String text1;
	private String text2;
	
	private int total6;
	private int total8;
	private int total10;
	
	
	private List<Wjlx> wjlxlist=new ArrayList<Wjlx>();
	private String glzsd;
	
	
	
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
	 * @return the glzsd
	 */
	@JSON(serialize=false)
	public String getGlzsd() {
		return glzsd;
	}


	/**
	 * @param glzsd the glzsd to set
	 */
	public void setGlzsd(String glzsd) {
		this.glzsd = glzsd;
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
	 * @return the wjlxlist
	 */
	public List<Wjlx> getWjlxlist() {
		return wjlxlist;
	}


	/**
	 * @param wjlxlist the wjlxlist to set
	 */
	public void setWjlxlist(List<Wjlx> wjlxlist) {
		this.wjlxlist = wjlxlist;
	}

	/**
	 * @return the total6
	 */
	public int getTotal6() {
		return total6;
	}


	/**
	 * @param total6 the total6 to set
	 */
	public void setTotal6(int total6) {
		this.total6 = total6;
	}


	/**
	 * @return the total8
	 */
	public int getTotal8() {
		return total8;
	}


	/**
	 * @param total8 the total8 to set
	 */
	public void setTotal8(int total8) {
		this.total8 = total8;
	}


	/**
	 * @return the total10
	 */
	public int getTotal10() {
		return total10;
	}


	/**
	 * @param total10 the total10 to set
	 */
	public void setTotal10(int total10) {
		this.total10 = total10;
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
	public List<Object> getList() {
		return list;
	}


	/**
	 * @param list the list to set
	 */
	public void setList(List<Object> list) {
		this.list = list;
	}


	
	/**
	 * @return the sjnno
	 */
	public int getSjnno() {
		return sjnno;
	}


	/**
	 * @param sjno the sjnno to set
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
	 * @{Ϊѡ���Ծ�ҳ�����json����}
	 * @param {page, rows_s} {�������˵��}
	 * @return {SUCCESS} {���ز���˵��}
	 * @{}
	 * @exception {˵����ĳ�����,������ʲô�쳣}
	**/
	@Action(value="/listSjnr",results={@Result(name="success",type="json")})
	public String listSjnr(){
		
		//List<Sjnr> t=this.baseService.findAll(Sjnr.class, "Sjnr", page, rows_s);
		String hql="from Sjnr order by dq asc,cjsj desc";
		List<Sjnr> t=this.baseService.findSql(Sjnr.class, hql, page, rows_s);
		SimpleDateFormat sDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); //ʱ���ʽ���ĸ�ʽ 
		sDateFormat.setLenient(false);
	   
		for(int i=0;i<t.size();i++){
			
			Conmen element=new Conmen();
			
			String cjsj = sDateFormat.format(t.get(i).getCjsj()); //����ʱ��
			element.setInt1(t.get(i).getSjno());
			element.setStr4(t.get(i).getZxx());
			element.setStr1(cjsj);
				String[] keys=new String[1]; keys[0]="sjno";
				Object[] values=new Object[1];values[0]=t.get(i).getSjno();
				int int2=this.baseService.getTotalP("Xsdyjl", keys, values);
			element.setInt2(int2);
			if(t.get(i).getDq()){
				element.setStr2("��");
			}else{
				element.setStr2("��");
			}
			rows.add(element);
		
	    }
		total=this.baseService.getTotal("Sjnr");
		return SUCCESS;
	}
	
	/**
	 * @{��ת���Ծ���Ŀҳ��/page/xgfx/sjtm.jsp}
	 * @param {sjno} {�������˵��}
	 * @return {SUCCESS} {���ز���˵��}
	 * @{}
	 * @exception {˵����ĳ�����,������ʲô�쳣}
	**/
	@Action(value="/xzsj",results={@Result(name="success",location="/page/xgfx/sjtm.jsp")})
	public String xzsj(){
		int sjno=sjnno;
		
		HttpServletRequest rq=ServletActionContext.getRequest();
		rq.setAttribute("sjnooo", sjno);
		HttpSession hs =rq.getSession();
		hs.setAttribute("sjno", sjno);
		
		String[] keys=new String[1];
		keys[0]="sjno1";
		Integer[] values=new Integer[1];
		values[0]=sjno;
		String[] prop=new String[2];
		prop[0]="zqno";
		prop[1]="cwno";
		 //�ж��Ծ�sjno�Ƿ��ѱ���������
		
		System.out.println("xzsj:"+sjno);
		List<List<Integer>> xzpdxg=this.baseService.find(Integer.class, "XzpdXg", prop, keys, values);
		int size=xzpdxg.size();
		System.out.println("size:"+size);
		if(size==0){
			
			//���ô洢����,����Ӧ�Ծ�Ĵ����������xzpd_xg����
			this.adminService.proc_Sjno(sjno);
			System.out.println("���ô洢����,����Ӧ�Ծ�Ĵ����������xzpd_xg����");
			//��Sjnr���dq�ֶ�����Ϊtrue����ʾ���Ծ��ѱ���������Ҳ��ʾ�þ����ѹ��ڣ�����
			String hql="update Sjnr set dq='1' where sjno="+sjno;//�����ֱ����д
			this.baseService.bulkUpdate(hql);
			
		}
		System.out.println("123");
		//Ϊ������ز�div���������ļ������б�
		this.wjlxlist=this.baseService.find(Wjlx.class);
		return SUCCESS;
	}
	
	/**
	 * @{Ϊ/page/xgfx/sjtm.jspҳ�����json����}
	 * @param {page, rows_s} {�������˵��}
	 * @return {SUCCESS} {���ز���˵��}
	 * @{}
	 * @exception {˵����ĳ�����,������ʲô�쳣}
	**/
	@Action(value="/listXz",results={@Result(name="success",type="json")})
	public String listXz(){
		HttpSession hs =ServletActionContext.getRequest().getSession();
		int sjno=(Integer) hs.getAttribute("sjno");
		List<Xz> t=this.adminService.chaTm1(sjno, 1, page, rows_s);
		
		String hql="from XzpdXg where id.lx='1' and id.sjno1='"+sjno+"' and id.th in(";
		for(int i=0;i<t.size();i++){
			if(i<t.size()-1){
				hql=hql+"'"+t.get(i).getTh()+"',";
			}else{
				hql=hql+"'"+t.get(i).getTh()+"')";
			}
		}
		@SuppressWarnings("unused")
		List<XzpdXg> lis=this.baseService.findHql(XzpdXg.class, hql);
		
        for(int i=0;i<t.size();i++){
        	List<Object> listt=new ArrayList<Object>();
        	Double   zqj=null;
        	Double   cwj=null;
        	String th=t.get(i).getTh().toString();
        	for(XzpdXg xzpdxg:lis){
        		if(th.equals(xzpdxg.getId().getTh())){
        			zqj=   new   Double((double)xzpdxg.getZqno()); 
        			cwj=   new   Double((double)xzpdxg.getCwno()); 
        			break;
        		}
        	}
			
            Double total=zqj+cwj;
			double z1 = zqj/total;
			double z2=cwj/total;
            DecimalFormat myformat = null;
            myformat= (DecimalFormat)NumberFormat.getPercentInstance();
            myformat.applyPattern("00.0%");//���ðٷ��ʵ������ʽ������00.*,������Ҫ�趨��
            //  System.out.println(myformat.format(z));
            TuxingClass arr1=new TuxingClass("zql",myformat.format(z1));
			TuxingClass arr2=new TuxingClass("cwl",myformat.format(z2));
			listt.add(arr1);
			listt.add(arr2);
			
			Conmen element=new Conmen();
			
			element.setInt1(t.get(i).getTh());//����˼·adminservice��д4��
			element.setStr4(t.get(i).getTg());
			element.setStr6(t.get(i).getZsd().getZsdmc());
			element.setArr1(listt);
			rows.add(element);
		
	    }
		total=this.adminService.totalAdmin(sjno, 1);
		return SUCCESS;
	}
	/**
	 * @{Ϊ/page/xgfx/sjtm.jspҳ�����json����}
	 * @param {page, rows_s} {�������˵��}
	 * @return {SUCCESS} {���ز���˵��}
	 * @{}
	 * @exception {˵����ĳ�����,������ʲô�쳣}
	**/
	@Action(value="/listPd",results={@Result(name="success",type="json")})
	public String listPd(){
		HttpSession hs =ServletActionContext.getRequest().getSession();
		int sjno=(Integer) hs.getAttribute("sjno");
		
		List<Pd> t=this.adminService.chaTm2(sjno, 2, page, rows_s);
		
		String hql="from XzpdXg where id.lx='2' and id.sjno1='"+sjno+"' and id.th in(";
		for(int i=0;i<t.size();i++){
			if(i<t.size()-1){
				hql=hql+"'"+t.get(i).getTh()+"',";
			}else{
				hql=hql+"'"+t.get(i).getTh()+"')";
			}
		}
		@SuppressWarnings("unused")
		List<XzpdXg> lis=this.baseService.findHql(XzpdXg.class, hql);
		
        for(int i=0;i<t.size();i++){
        	List<Object> listt=new ArrayList<Object>();
        	
        	Double   zqj=null;
        	Double   cwj=null;
        	String th=t.get(i).getTh().toString();
        	for(XzpdXg xzpdxg:lis){
        		if(th.equals(xzpdxg.getId().getTh())){
        			zqj=   new   Double((double)xzpdxg.getZqno()); 
        			cwj=   new   Double((double)xzpdxg.getCwno()); 
        			break;
        		}
        	}
        	
            Double total=zqj+cwj;
			double z1 = zqj/total;
			double z2=cwj/total;
            DecimalFormat myformat = null;
            myformat= (DecimalFormat)NumberFormat.getPercentInstance();
            myformat.applyPattern("00.0%");//���ðٷ��ʵ������ʽ������00.*,������Ҫ�趨��
            //  System.out.println(myformat.format(z));
            TuxingClass arr1=new TuxingClass("zql",myformat.format(z1));
			TuxingClass arr2=new TuxingClass("cwl",myformat.format(z2));
			listt.add(arr1);
			listt.add(arr2);
			Conmen element=new Conmen();
			
			element.setInt1(t.get(i).getTh());//����˼·adminservice��д4��
			element.setStr4(t.get(i).getTg());
			element.setStr6(t.get(i).getZsd().getZsdmc());
			element.setArr1(listt);
			rows.add(element);
		
	    }
		total=this.adminService.totalAdmin(sjno, 2);

		return SUCCESS;
	}
	/**
	 * @{Ϊ/page/xgfx/sjtm.jspҳ�����json����}
	 * @param {page, rows_s} {�������˵��}
	 * @return {SUCCESS} {���ز���˵��}
	 * @{}
	 * @exception {˵����ĳ�����,������ʲô�쳣}
	**/
	@Action(value="/listCzt",results={@Result(name="success",type="json")})
	public String listCzt(){
		HttpSession hs =ServletActionContext.getRequest().getSession();
		int sjno=(Integer) hs.getAttribute("sjno");
		
		List<Czt> t=this.adminService.chaTm3(sjno, 3, page, rows_s);
		
		String hql="from XzpdXg where id.lx='3' and id.sjno1='"+sjno+"' and id.th in(";
		for(int i=0;i<t.size();i++){
			String str=t.get(i).getId().getDtTh()+"|"+t.get(i).getId().getSxh();
			if(i<t.size()-1){
				hql=hql+"'"+str+"',";
			}else{
				hql=hql+"'"+str+"')";
			}
		}
		@SuppressWarnings("unused")
		List<XzpdXg> lis=this.baseService.findHql(XzpdXg.class, hql);
		
        for(int i=0;i<t.size();i++){
        	//��ʾ����֪ʶ��
          	String hstr="from Cztda where id.sxh='"+t.get(i).getId().getSxh()+"' and id.dtTh='"+t.get(i).getId().getDtTh()+"'";
        	List<Cztda> listcztda=this.baseService.findHql(Cztda.class, hstr);
	    	String zsdmc="";
	    	for(int j=0;j<listcztda.size();j++){
	    		if(j==0){
					zsdmc=zsdmc+listcztda.get(j).getZsd().getZsdmc();;
				}else{
					zsdmc=zsdmc+","+listcztda.get(j).getZsd().getZsdmc();;
				}
				
	    	}
        	//��ʾ����֪ʶ�����
        	List<Object> listt=new ArrayList<Object>();
       	
        	Double   zqj=null;
        	Double   cwj=null;
        	String th=t.get(i).getId().getDtTh()+"|"+t.get(i).getId().getSxh();
        	for(XzpdXg xzpdxg:lis){
        		if(th.equals(xzpdxg.getId().getTh())){
        			zqj=   new   Double((double)xzpdxg.getZqno()); 
        			cwj=   new   Double((double)xzpdxg.getCwno()); 
        			break;
        		}
        	}
        	
        	
            Double total=zqj+cwj;
			double z1 = zqj/total;
			double z2=cwj/total;
            DecimalFormat myformat = null;
            myformat= (DecimalFormat)NumberFormat.getPercentInstance();
            myformat.applyPattern("00.0%");//���ðٷ��ʵ������ʽ������00.*,������Ҫ�趨��
            //  System.out.println(myformat.format(z));
            TuxingClass arr1=new TuxingClass("zql",myformat.format(z1));
			TuxingClass arr2=new TuxingClass("cwl",myformat.format(z2));
			listt.add(arr1);
			listt.add(arr2);
			Conmen element=new Conmen();
			
			element.setInt1(t.get(i).getId().getSxh());//����˼·adminservice��д4��
			element.setStr4(t.get(i).getTg());
			element.setInt2(t.get(i).getId().getDtTh());
			element.setStr5(t.get(i).getCztd().getTg());
			element.setStr6(zsdmc);
			element.setArr1(listt);
			rows.add(element);
		
	    }
		total=this.adminService.totalAdmin(sjno, 3);

		return SUCCESS;
	}
	/**
	 * @{Ϊ/page/xgfx/sjtm.jspҳ�����json����}
	 * @param {page, rows_s} {�������˵��}
	 * @return {SUCCESS} {���ز���˵��}
	 * @{}
	 * @exception {˵����ĳ�����,������ʲô�쳣}
	**/
	@Action(value="/listZsd",results={@Result(name="success",type="json")})
	public String listZsd(){
		HttpSession hs =ServletActionContext.getRequest().getSession();
		int sjno=(Integer) hs.getAttribute("sjno");
		
		System.out.println("sjno:"+sjno);
		List<Zsd> t=this.adminService.chaTm4(sjno, 4, page, rows_s);
		
		String hql="from XzpdXg where id.sjno1='"+sjno+"' and id.zsdbh in(";
		for(int i=0;i<t.size();i++){
			int str=t.get(i).getId().getZsdbh();
			if(i<t.size()-1){
				hql=hql+"'"+str+"',";
			}else{
				hql=hql+"'"+str+"')";
			}
		}
		@SuppressWarnings("unused")
		List<XzpdXg> lis=this.baseService.findHql(XzpdXg.class, hql);
		
        for(int i=0;i<t.size();i++){
        	List<Object> listt=new ArrayList<Object>();
       	
        	int zq=0;
        	int cw=0;
        	int th=t.get(i).getId().getZsdbh();
        	for(XzpdXg xzpdxg:lis){
        		if(th==xzpdxg.getId().getZsdbh()){
        			zq=zq+xzpdxg.getZqno();
        			cw=cw+xzpdxg.getCwno();
        		}
        	}
        	
			//���ô洢���̲�������ⲿ�ֵ�֪ʶ�����ȷ�������
			List<Integer> cztzsd=this.adminService.proc_czt_zsd(sjno, t.get(i).getId().getZsdbh());
			zq=zq+cztzsd.get(0);
			cw=cw+cztzsd.get(1);
			
			//int zq=sj.get(0).getZqno();
			Double   zqj=   new   Double((double)zq); 
			//int cw=sj.get(0).getCwno();
			Double   cwj=   new   Double((double)cw); 
            Double total=zqj+cwj;
			double z1 = zqj/total;
			double z2=cwj/total;
            DecimalFormat myformat = null;
            myformat= (DecimalFormat)NumberFormat.getPercentInstance();
            myformat.applyPattern("00.0%");//���ðٷ��ʵ������ʽ������00.*,������Ҫ�趨��
            //  System.out.println(myformat.format(z));
            TuxingClass arr1=new TuxingClass("zql",myformat.format(z1));
			TuxingClass arr2=new TuxingClass("cwl",myformat.format(z2));
			listt.add(arr1);
			listt.add(arr2);
			Conmen element=new Conmen();
			
			element.setInt1(t.get(i).getId().getZsdbh());//����˼·adminservice��д4��
			element.setStr4(t.get(i).getZsdmc());
			element.setStr5(t.get(i).getZsdms());
			element.setArr1(listt);
			rows.add(element);
		
	    }
		total=this.adminService.totalAdmin(sjno, 4);

		return SUCCESS;
	}
	
	/**
	 * @{Ϊ/page/xgfx/sjtm.jspҳ�����json����}
	 * @param {page, rows_s} {�������˵��}
	 * @return {SUCCESS} {���ز���˵��}
	 * @{}
	 * @exception {˵����ĳ�����,������ʲô�쳣}
	**/
	@Action(value="/listFs",results={@Result(name="success",type="json")})
	public String listFs(){
		HttpSession hs =ServletActionContext.getRequest().getSession();
		int sjno=(Integer) hs.getAttribute("sjno");
		
		List<Integer> t=this.adminService.proc_fsd(sjno, 2, 2, 5);
		
        Conmen element=new Conmen();
			
			element.setInt1(t.get(0));//����˼·adminservice��д4��
			element.setInt2(t.get(1));
			element.setInt3(t.get(2));
			element.setInt4(t.get(3));
			element.setStr4(t.get(4).toString());//�ܷ�
			element.setStr5(t.get(5).toString());//ƽ����
			
			rows.add(element);
		
	    
		total=1;

		return SUCCESS;
	}
	

	/**
	 * @{Ϊ����������ز��ϡ��Ĵ���ҳ�����json����}
	 * @param {page, rows_s} {�������˵��}
	 * @return {SUCCESS} {���ز���˵��}
	 * @{}
	 * @exception {˵����ĳ�����,������ʲô�쳣}
	**/
	@Action(value="/listZl",results={@Result(name="success",type="json")})
	public String listZl(){
		HttpSession hss =ServletActionContext.getRequest().getSession();
		int sjno=(Integer) hss.getAttribute("sjno");
		int xztH=(Integer)hss.getAttribute("xztH");
		int pdtH=(Integer)hss.getAttribute("pdtH");
		String cztH =(String)hss.getAttribute("cztH");
		
		//һ��zlbh��һ������
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

			
		}else if(cztH!=null&&!cztH.equals("0|0")){
			int index=cztH.indexOf("|");
			int dtth=Integer.parseInt(cztH.substring(0, index));
			int sxh=Integer.parseInt(cztH.substring(index+1));
			List<Scwj> listscwj=this.baseService.findScwj(sjno, sxh, dtth);
				HttpSession hs=ServletActionContext.getRequest().getSession();
				List<String> arr=(List<String>) hs.getAttribute("arr");//��session��������baseService.findScwj()���������õ�
				hs.removeAttribute("arr");//ȥ����arr����ռsession�ռ�
			if(listscwj.size()!=0){
				//�����List����---����ʵ�ַ�ҳ
				
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
						
						//ע���ˣ�sublist()�����Ǵ�fromIndex�±꿪ʼ������toIndex����
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
					//element.setStr6(th2.get(i).getFjxx());//setStr6�����ù���֪ʶ�����ƣ��˴���ʱ���ˡ�scwj�ࡱ��fjxx���Դ����zsdmc
					element.setStr6(arr.get(i));
					rows.add(element);
				}
				total=listscwj.size();
				
			}

			
		}else{//֪ʶ��
			int zsdbH=(Integer)hss.getAttribute("zsdbH");
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
	 * @{Ϊxtfx.jspҳ�����json����}
	 * @param {} {�������˵��}
	 * @return {SUCCESS} {���ز���˵��}
	 * @{}
	 * @exception {˵����ĳ�����,������ʲô�쳣}
	**/
	
	@Action(value="/ksfxjson",results={@Result(name="success",type="json")})
	public String ksfxjson(){
		HttpSession hs =ServletActionContext.getRequest().getSession();
		int sjno=(Integer) hs.getAttribute("sjno");
		
		String c1=String.valueOf(czdth);
		String c2=String.valueOf(czth);
		String cth=c1.concat("|").concat(c2);
		XzpdXg sj=new XzpdXg();
		//zsdbH=zsdbh;
		hs.setAttribute("zsdbH",zsdbh);
        if(xzth!=0){
			String[] keys=new String[3];
			keys[0]="th";
			keys[1]="lx";
			keys[2]="sjno1";
			Integer[] values=new Integer[3];
			values[0]=xzth;
			values[1]=1;
			values[2]=sjno;
			sj=this.baseService.find(XzpdXg.class, "XzpdXg",keys, values).get(0);
			String[] key_s=new String[1];key_s[0]="th";
			Object[] value_s=new Object[1];value_s[0]=xzth;
			List<Xz> xz=this.baseService.find(Xz.class, "Xz", key_s, value_s);
			xzda="A."+xz.get(0).getXx1()+" B."+xz.get(0).getXx2()+" C."+xz.get(0).getXx3()+" D."+xz.get(0).getXx4();
			text1=sj.getFxdnr();
			text2=sj.getJjdy();
			if(text1==null||text1.equals("")){
				text1="δ��������";
			}
			if(text2==null||text2.equals("")){
				text2="δ�����ɣ�";
			}
		}else if(pdth!=0){
			String[] keys=new String[3];
			keys[0]="th";
			keys[1]="lx";
			keys[2]="sjno1";
			Integer[] values=new Integer[3];
			values[0]=pdth;
			values[1]=2;
			values[2]=sjno;
			sj=this.baseService.find(XzpdXg.class, "XzpdXg",keys, values).get(0);

			text1=sj.getFxdnr();
			text2=sj.getJjdy();
			if(text1==null||text1.equals("")){
				text1="δ��������";
			}
			if(text2==null||text2.equals("")){
				text2="δ�����ɣ�";
			}
		}else if(!cth.equals("0|0")){
			String[] keys=new String[3];
			keys[0]="th";
			keys[1]="lx";
			keys[2]="sjno1";
			Object[] values=new Object[3];
			values[0]=cth;
			values[1]=3;
			values[2]=sjno;
			sj=this.baseService.find(XzpdXg.class, "XzpdXg",keys, values).get(0);

			text1=sj.getFxdnr();
			text2=sj.getJjdy();
			if(text1==null||text1.equals("")){
				text1="δ��������";
			}
			if(text2==null||text2.equals("")){
				text2="δ�����ɣ�";
			}
		}else{//֪ʶ��
			Zsdjjdy jjdy=this.baseService.find(Zsdjjdy.class, zsdbh);
			if(jjdy!=null){
				text1=jjdy.getFxdnr();
				text2=jjdy.getJjdy();
				if(text1==null||text1.equals("")){
					text1="δ��������";
				}
				if(text2==null||text2.equals("")){
					text2="δ�����ɣ�";
				}
			}else{
				text1="δ��������";
				text2="δ�����ɣ�";
			}
			
		}
       hs.setAttribute("sj", sj);
		tip="true";
		return SUCCESS;
	}
	/**
	 * @{Ϊ/page/xgfx/xtfx.jspҳ���������ѧ��������json����}
	 * @param {page, rows_s} {�������˵��}
	 * @return {SUCCESS} {���ز���˵��}
	 * @{}
	 * @exception {˵����ĳ�����,������ʲô�쳣}
	**/
	@Action(value="/listCstuSJ",results={@Result(name="success",type="json")})
	public String listCstuSJ(){
		HttpSession hs = ServletActionContext.getRequest().getSession();
		hs.setAttribute("xztH",xzth);//Ϊ����listzl.action��listCstu.action��ȡֵ
		hs.setAttribute("pdtH",pdth);
//		userId = (String)hs.getAttribute("uid");
		//xztH=xzth;
		//pdtH=pdth;
		
		String c1=String.valueOf(czdth);
		String c2=String.valueOf(czth);
		String cth=c1.concat("|").concat(c2);
		//cztH=cth;
		hs.setAttribute("cztH", cth);
		//zsdbH=zsdbh;
		hs.setAttribute("zsdbH", zsdbh);
		System.out.println(glzsd);
		if(glzsd==null||glzsd.equals("")){
			
		}else{
            String[] keys=new String[1]; keys[0]="zsdmc";Object[] values=new Object[1];
			
			while(glzsd.indexOf(",")!=-1){
				int no=glzsd.indexOf(",");
				
				values[0]=glzsd.substring(0,no);
				list.add(this.baseService.find(Zsd.class, "Zsd", keys, values).get(0));
				glzsd=glzsd.substring(no+1);
			
			}
			values[0]=glzsd;
			list.add(this.baseService.find(Zsd.class, "Zsd", keys, values).get(0));
		}
		
		return SUCCESS;
	}
	/**
	 * @{Ϊ/page/xgfx/xtfx.jspҳ���������ѧ��������json����}
	 * @param {page, rows_s} {�������˵��}
	 * @return {SUCCESS} {���ز���˵��}
	 * @{}
	 * @exception {˵����ĳ�����,������ʲô�쳣}
	**/
	@Action(value="/listCstu",results={@Result(name="success",type="json")})
	public String listCstu(){
		HttpSession hs =ServletActionContext.getRequest().getSession();
		int sjno=(Integer) hs.getAttribute("sjno");
		int xztH=(Integer)hs.getAttribute("xztH");
		
		int pdtH=(Integer)hs.getAttribute("pdtH");
		String cztH=(String)hs.getAttribute("cztH");
        if(xztH!=0){
			String th=String.valueOf(xztH);
			List<Studentifno> s=this.adminService.proc_cwstu(sjno,th, 1, page, rows_s);
			if(s!=null){
				for(int i=0;i<s.size();i++){
					Conmen element=new Conmen();
					element.setInt1(s.get(i).getBjxx().getBjbh());
					element.setStr1(s.get(i).getSName());
					rows.add(element);
				}
				total=this.adminService.totalStu(sjno, 1, th);
			}
				
		}else if(pdtH!=0){
			String th=String.valueOf(pdtH);
			List<Studentifno> s=this.adminService.proc_cwstu(sjno,th, 2, page, rows_s);
			if(s!=null){
				for(int i=0;i<s.size();i++){
					Conmen element=new Conmen();
					element.setInt1(s.get(i).getBjxx().getBjbh());
					element.setStr1(s.get(i).getSName());
					rows.add(element);
				}
				total=this.adminService.totalStu(sjno, 2, th);
			}
		}else if(!cztH.equals("0|0")){
			String th=cztH;
			List<Studentifno> s=this.adminService.proc_cwstu(sjno, th, 3, page, rows_s);
			if(s!=null){
				for(int i=0;i<s.size();i++){
					Conmen element=new Conmen();
					element.setInt1(s.get(i).getBjxx().getBjbh());
					element.setStr1(s.get(i).getSName());
					rows.add(element);
				}
				total=this.adminService.totalStu(sjno, 3, th);
			}
		}
        
		return SUCCESS;
	}
	
	/**
	 * @{��ת��ϰ�������Ծ�ĵ÷�����ķ���ҳ��}
	 * @param {xzth,pdth,czth,zsdbh} {�������˵��}
	 * @return {SUCCESS} {���ز���˵��}
	 * @{��ת��xtfx.jsp}
	 * @exception {˵����ĳ�����,������ʲô�쳣}
	**/
	@Action(value="/fsfx",results={@Result(name="success",location="/page/xgfx/fsfx.jsp")})
	public String fsfx(){
		HttpSession hs =ServletActionContext.getRequest().getSession();
		int sjno=(Integer) hs.getAttribute("sjno");
		
		HttpServletRequest rq=ServletActionContext.getRequest();
		rq.setAttribute("sjnoo", sjno);
		hs.setAttribute("total6", total6);
		hs.setAttribute("total8",total8);
		hs.setAttribute("total10", total10);
		
		return SUCCESS;
	}
	/**
	 * @{Ϊ/page/xgfx/fsfx.jspҳ�����json����}
	 * @param {} {�������˵��}
	 * @return {SUCCESS} {���ز���˵��}
	 * @{}
	 * @exception {˵����ĳ�����,������ʲô�쳣}
	**/
	
	@Action(value="/fsfxjson",results={@Result(name="success",type="json")})
	public String fsfxjson(){
		HttpSession hs =ServletActionContext.getRequest().getSession();
		int sjno=(Integer) hs.getAttribute("sjno");
		
		int total6=(Integer)hs.getAttribute("total6");
		hs.removeAttribute("total6");
		int total8=(Integer)hs.getAttribute("total8");
		hs.removeAttribute("total8");
		int total10=(Integer)hs.getAttribute("total10");
		hs.removeAttribute("total10");
		Object[] arr1={"0~60��",total6};//total6
		Object[] arr2={"60~80��",total8};//total8
		Object[] arr3={"80~100��",total10};//total10
		list.add(arr1);
		list.add(arr2);
		list.add(arr3);
		
		Sjfx sjfx=this.baseService.find(Sjfx.class, sjno);
		if(sjfx==null||sjfx.getSjfx().equals("")){
			text1="δ���ۣ�";
		}else{
			text1=sjfx.getSjfx();
		}
		
		tip="true";
		return SUCCESS;
	}
	
	
	
	/**
	 * @{��ʦ�ύ�Ծ�÷�����ķ���}
	 * @param {} {�������˵��}
	 * @return {SUCCESS} {���ز���˵��}
	 * @{}
	 * @exception {˵����ĳ�����,������ʲô�쳣}
	**/
	@Action(value="/tjfsfx",results={@Result(name="success",type="json")})
	public String tjfsfx(){
		HttpSession hs =ServletActionContext.getRequest().getSession();
		int sjno=(Integer) hs.getAttribute("sjno");
		
		
		Sjfx sjfx=this.baseService.find(Sjfx.class, sjno);
		if(sjfx!=null){
			sjfx.setSjfx(text1);
			this.baseService.update(sjfx);
		}else{
			Sjfx sjf=new Sjfx();
			sjf.setSjno(sjno);
			sjf.setSjfx(text1);
			this.baseService.save(sjf);
		}
		tip="true";
		return SUCCESS;
	}
	
	/**
	 * @{��ʦ�ύÿ����ķ����뽲�����}
	 * @param {zsdbH��text1��text2} {�������˵��}
	 * @return {SUCCESS} {���ز���˵��}
	 * @{}
	 * @exception {˵����ĳ�����,������ʲô�쳣}
	**/
	@Action(value="/tjfx",results={@Result(name="success",type="json")})
	public String tjfx(){
		HttpSession hs =ServletActionContext.getRequest().getSession();
		int zsdbH=(Integer) hs.getAttribute("zsdbH");
		XzpdXg sj=(XzpdXg)hs.getAttribute("sj");
		
		if(zsdbH==0){
			  if(text1!=null){
				
				   XzpdXg xgfx=new XzpdXg();
					xgfx=sj;
					xgfx.setFxdnr(text1);
					this.baseService.update(xgfx);
			  }else if(text2!=null){
				 
				   XzpdXg xgfx=new XzpdXg();
					xgfx=sj;
					xgfx.setJjdy(text2);
					this.baseService.update(xgfx);
			  }
			   
		}else{
			if(text1!=null){
				
				  Zsdjjdy zsdjjdy=this.baseService.find(Zsdjjdy.class, zsdbH);
				  if(zsdjjdy!=null){
					  zsdjjdy.setFxdnr(text1);
					  this.baseService.update(zsdjjdy);
				  }else{
					  zsdjjdy=new Zsdjjdy();
					  zsdjjdy.setZsdbh(zsdbH);
					  zsdjjdy.setFxdnr(text1);
					  this.baseService.save(zsdjjdy);
				  }
				    
				    
			  }else if(text2!=null){
				 
					Zsdjjdy zsdjjdy=this.baseService.find(Zsdjjdy.class, zsdbH);
					  if(zsdjjdy!=null){
						  zsdjjdy.setJjdy(text2);
						  this.baseService.update(zsdjjdy);
					  }else{
						  zsdjjdy=new Zsdjjdy();
						  zsdjjdy.setZsdbh(zsdbH);
						  zsdjjdy.setJjdy(text2);
						  this.baseService.save(zsdjjdy);
					  }
			  }
				
		}
	  
		tip="true";
		return SUCCESS;
	}

	
}
