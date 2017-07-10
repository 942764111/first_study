/*
 *@(#)xx.tables_zxl.action
 *@ListZinfoAction.java.java  
 *@����ʱ��:2011-6-2����10:07:03
 *@���ߣ�zxl
 *@Copyright 2009-2010 �ӱ�����ѧԺ��Ϣ��ѧ�빤��ѧԺ������ 
 */

package xx.tables_zxl.action;

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
import xx.collection.bean.Jie;
import xx.collection.bean.JieId;
import xx.collection.bean.Sjnr;
import xx.collection.bean.Zjgz;
import xx.collection.bean.Zsd;
import xx.quanxian.service.BaseService;
 
import com.googlecode.jsonplugin.annotations.JSON;
import com.opensymphony.xwork2.ActionSupport;

/**
 * @ListZinfoAction <code>{������}</code>
 * @author  {zxl}
 * @version {�汾,����ʱ�����}
 * @{...��������} 
 */


@Controller
@Scope("prototype")
@ParentPackage("default-package")
@Namespace("")
public class ListJieAction extends ActionSupport {
	
	@Resource(name="baseService")
	private BaseService baseservice;
	
	private List<Jie> jie = new ArrayList<Jie>();
	private List<Jxx> rows = new ArrayList<Jxx>();
	private int page;//��ǰҳ
	private int rows_s;//ÿҳ��ʾ������
	private int total;//��¼��������
	private List<Jie> zinfolist;
	private List<CourseChapter> kcxxlist;
	private String queryType;	//��ѯ����ʱ�Ĳ�ѯ��������Ҫ���ݲ�ѯ������
	private String queryWord;	//��ѯ����ʱ�Ĳ�ѯ��������Ҫ���ݲ�ѯ�Ĺؼ���
	private String CName;
	private CourseChapter kcxx;
	private String zmc;
	private String zms;
	private int CId;
	private int zbh;
	private int b;
	private int a;
	private int totalo1;
	private List<Integer> CIdlist=new ArrayList<Integer>();
	private List<Integer> CIdlist1=new ArrayList<Integer>();
	private List<String> CNameList=new ArrayList<String>();
	private List<String> zjy=new ArrayList<String>();
	private List<String> xx=new ArrayList<String>();
	
	public int getA() {
		return a;
	}
	public void setA(int a) {
		this.a = a;
	}
	
	@JSON(serialize=false)
	public List<String> getZjy() {
		return zjy;
	}
	public void setZjy(List<String> zjy) {
		this.zjy = zjy;
	}
	@JSON(serialize=false)
	public List<Jie> getJie() {
		return jie;
	}
	public void setJie(List<Jie> jie) {
		this.jie = jie;
	}
	@JSON(serialize=false)
	public List<Jie> getZinfolist() {
		return zinfolist;
	}
	public void setZinfolist(List<Jie> zinfolist) {
		this.zinfolist = zinfolist;
	}
	@JSON(serialize=false)
	public List<CourseChapter> getKcxxlist() {
		return kcxxlist;
	}
	public void setKcxxlist(List<CourseChapter> kcxxlist) {
		this.kcxxlist = kcxxlist;
	}
	@JSON(serialize=false)
	public CourseChapter getKcxx() {
		return kcxx;
	}
	public void setKcxx(CourseChapter kcxx) {
		this.kcxx = kcxx;
	}
	@JSON(serialize=false)
	public List<String> getXx() {
		return xx;
	}
	public void setXx(List<String> xx) {
		this.xx = xx;
	}
	
	public int getB() {
		return b;
	}
	public void setB(int b) {
		this.b = b;
	}
	@JSON(serialize=false)
	public String getQueryType() {
		return queryType;
	}
	public void setQueryType(String queryType) {
		this.queryType = queryType;
	}
	@JSON(serialize=false)
	public String getQueryWord() {
		return queryWord;
	}
	public void setQueryWord(String queryWord) {
		this.queryWord = queryWord;
	}
	@JSON(serialize=false)
	public BaseService getBaseservice() {
		return baseservice;
	}
	public void setBaseservice(BaseService baseservice) {
		this.baseservice = baseservice;
	}
	
	public List<Jxx> getRows() {
		return rows;
	}
	public void setRows(List<Jxx> rows) {
		this.rows = rows;
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
	public String getCName() {
		return CName;
	}
	public void setCName(String name) {
		CName = name;
	}
	
	@JSON(serialize=false)
	public String getZmc() {
		return zmc;
	}
	public void setZmc(String zmc) {
		this.zmc = zmc;
	}
	
	@JSON(serialize=false)
	public String getZms() {
		return zms; 
	}
	public void setZms(String zms) {
		this.zms = zms;
	}
	
	@JSON(serialize=false)
	public int getCId() {
		return CId;
	}
	
	public void setCId(int id) {
		CId = id;
	}
	
	@JSON(serialize=false)
	public List<Integer> getCIdlist() {
		return CIdlist;
	}
	public void setCIdlist(List<Integer> idlist) {
		CIdlist = idlist;
	}
	@JSON(serialize=false)
	public List<String> getCNameList() {
		return CNameList;
	}
	public void setCNameList(List<String> nameList) {
		CNameList = nameList;
	}
	
	@JSON(serialize=false)
	public int getZbh() {
		return zbh;
	}
	@JSON(deserialize=true)
	public void setZbh(int zbh) {
		this.zbh = zbh;
	}
	@JSON(deserialize=true)
	public List<Integer> getCIdlist1() {
		return CIdlist1;
	}
	public void setCIdlist1(List<Integer> idlist1) {
		CIdlist1 = idlist1;
	}
	
	
		//��ѯ����
	@Action(value="/jielist",results={@Result(name="root",type="json")})
	public String jielist(){
		jie = this.baseservice.findAll(Jie.class, "Jie", page, rows_s);
		total=this.baseservice.getTotal("Jie");  //��¼����
		 for(int i=0;i<jie.size();i++){
	        	Jxx element = new Jxx();
	        	element.setJbh(jie.get(i).getId().getCId());
	        	element.setZhangm(jie.get(i).getCourseChapter().getCName());
	        	element.setJmc(jie.get(i).getZmc());
	        	element.setJms(jie.get(i).getZms());
	        	rows.add(element);   //�����ݷŽ�rows��ʵ�ַ�ҳ��ѯ��ʾ    	
	        }
		 return "root";
	}
	
	//�������
	@Action(value="insertjie",results={@Result(name="success",type="json")})
	public String insertjie(){
		String[] keys=new String[1];
		keys[0]="CName";
		Object[] values=new Object[1];
		values[0]=CName;
		List<Integer> zbh=this.baseservice.find(Integer.class, "CourseChapter", "zbh", keys, values);
		
		//����zbh�Զ�����CId
		String hql ="select count(*) from Jie where zbh="+zbh.get(0);
		int t=this.baseservice.getTotalSql(hql);
		//intת����string����
		String a =String.valueOf(t+1);
		String c = String.valueOf(zbh.get(0));
		String b =String.valueOf(0);
		String hql1 ="from Jie where zbh="+zbh.get(0);
		jie=this.baseservice.findHql(Jie.class, hql1);
		//if(t==0){
			CId=Integer.parseInt(c+a);
//		}
//		else if(t+1<10){
//			for(int i=0;i<jie.size();i++){
//				if(jie.get(i).getId().getCId()!=(Integer.parseInt(c+b+i)+1)){
//					CId=Integer.parseInt(c+b+i)+1;
//					break;
//				}else{
//					CId=Integer.parseInt(c+b+a);//ƴ���ַ���,��ת��Ϊint����
//				}
//			}
//			
//		}
//		else if(t+1>=10){
//			for(int i=0;i<jie.size();i++){
//				if(i<10){
//					if(jie.get(i).getId().getCId()!=(Integer.parseInt(c+b+i)+1)){
//						CId=Integer.parseInt(c+b+i)+1;
//						break;
//					}else{
//						CId=Integer.parseInt(c+a);//ƴ���ַ���,��ת��Ϊint����
//					}
//				}else if(i>=10){
//					if(jie.get(i).getId().getCId()!=(Integer.parseInt(c+i)+1)){
//						CId=Integer.parseInt(c+i)+1;
//						break;
//					}else{
//						CId=Integer.parseInt(c+a);//ƴ���ַ���,��ת��Ϊint����
//					}
//				}
//				
			
			//CId=Integer.parseInt(c+a);
		
		
		JieId id=new JieId();
		id.setCId(CId);
		id.setZbh(zbh.get(0));
		Jie jie = new Jie();		
		jie.setZmc(zmc);
		jie.setZms(zms);
		jie.setId(id);
		this.baseservice.save(jie);
		
		return SUCCESS;
	}
	


	//�����ѯ�Ƿ�Ϊ��,���ó���������
	@Action(value="/queryjie1",results={@Result(name="root",type="json")})
	public String queryjie1(){
		String hql = "select count(*) from Jie where "+queryType+" like '%"+queryWord+"%'";
		totalo1 = this.baseservice.getTotalSql(hql);
		HttpSession hs = ServletActionContext.getRequest().getSession();
		hs.setAttribute("jietotal", totalo1);
		if(totalo1!=0){
			a=1;
		}else{
			a=0;
		}
		return "root";
	}
	/**
	 * @{queryzinfo.action}
	 * �÷������������ݹؼ��ֲ�ѯ����Ϣ�ģ���ѯʱ���ҳ���������������queryType��queryWord
	 * queryType��Ҫ��ѯʱ�����ݵ����ͣ�queryWord�ǹؼ���
	 * ��ѯ���Ľ��ҳҪ�Է�ҳ����ʽ��ʾ
	*/
	@Action(value="/queryjie",results={@Result(name="root",type="json")})
	public String queryjie(){
		jie = this.baseservice.findByTypage(Jie.class, "Jie", queryType, queryWord, "order by c_id asc", page, rows_s);//���ؼ��ֲ�ѯ
		HttpSession hs = ServletActionContext.getRequest().getSession();
		total=(Integer)hs.getAttribute("jietotal");
		for(int i=0;i<jie.size();i++){
        	Jxx element = new Jxx();
        	element.setJbh(jie.get(i).getId().getCId());
        	element.setJmc(jie.get(i).getZmc());
        	element.setJms(jie.get(i).getZms());
        	element.setZhangm(jie.get(i).getCourseChapter().getCName());
        		        	   	
        	rows.add(element);   //�����ݷŽ�rows��ʵ�ַ�ҳ��ѯ��ʾ    	
        }
		return "root";
	}
	
	@Action(value="deletejie",results={@Result(name="success",type="json")})
	public String deletejie(){
		if(zmc!=null){
			String[] keys=new String[1];
			keys[0]="zmc";		
			Object[] values=new Object[1];
			values[0]=zmc;	
			List<Jie> j = this.baseservice.find(Jie.class, "Jie", keys, values);
			String hql2="from Zjgz";
			String hql3="from Zsd";
			List<Zjgz> zjgz=this.baseservice.findHql(Zjgz.class, hql2);
			List<Zsd> zsd=this.baseservice.findHql(Zsd.class, hql3);
			List<Integer> d=new ArrayList<Integer>();
			List<Integer> c=new ArrayList<Integer>();
			for(int n=0;n<zjgz.size();n++){
				d.add(zjgz.get(n).getJie().getId().getCId());
			}
			for(int m=0;m<zsd.size();m++){
				c.add(zsd.get(m).getJie().getId().getCId());
			}
			if(!d.contains(j.get(0).getId().getCId())){
				if(!c.contains(j.get(0).getId().getCId())){
					Jie jie1 = new Jie();
					jie1=j.get(0); 
					this.baseservice.delete(jie1);
					b=1;
				}else{
					b=0;
				}
			}else{
				b=0;
			}
		}
		return SUCCESS;
	}
	
	
	@Action(value="updatejie",results={@Result(name="success",type="json")})
	public String updatejie(){
		
		String[] keys=new String[1];
		keys[0]="CName";
		Object[] values=new Object[1];
		values[0]=CName;
		List<Integer> zbh=this.baseservice.find(Integer.class, "CourseChapter", "zbh", keys, values);
		JieId id=new JieId();
		id.setCId(CId);
		id.setZbh(zbh.get(0));
		Jie jie = new Jie();		
		jie.setZmc(zmc);
		jie.setZms(zms);
		jie.setId(id);
		this.baseservice.update(jie);
		return SUCCESS;
	}
	
	//�����ϢУ��
	@Action(value="/zjya",results={@Result(name="success",type="json")})
	public String  zjya(){
		zjy = this.baseservice.find(String.class, "Jie", "zmc");
		if(!zjy.contains(zmc)){
			b=0;
		}else{
			b=1;
		}
		return SUCCESS;
	}
	//�޸���ϢУ��
	@Action(value="/zjy2",results={@Result(name="success",type="json")})
	public String  zjy2(){
		zjy = this.baseservice.find(String.class, "Jie", "zmc");
			String[] keys=new String[1];
			keys[0]="c_id";		
			Object[] values=new Object[1];
			values[0]=CId;	
			List<Jie> j = this.baseservice.find(Jie.class, "Jie", keys, values);
			System.out.println(j.get(0).getZmc());
			for(int i=0;i<zjy.size();i++){
				if(!zjy.get(i).equals(j.get(0).getZmc())){
					xx.add(zjy.get(i));
				}
			}
			if(!xx.contains(zmc)){
				b=0;
			}else if(!zjy.contains(zmc)){
				b=0;
			}else{
				b=1;
			}
		
		return SUCCESS;
	}
	//��ת��ҳ��"�� ��Ϣ����"
//	@Action(value="/zinfo",results={@Result(name="success",location="/tables_zxl/jie.jsp")})
//	public String zinfo(){
////		this.zinfolist=this.baseservice.find(Jie.class);//ͨ��Zinfo.class��ѯ�������ݲ�����zinfolist�С�
//		this.kcxxlist=this.baseservice.find(CourseChapter.class);
////		for(int n=0;n<zinfolist.size();n++){
////			CIdlist1.add(zinfolist.get(n).getId().getZbh());
////		}
//		//���ڽ���Ϣ����û�ж����γ̡����¡���Ϣ�����е��±�ŷ���CIdlist
//		for(int i=0;i<kcxxlist.size();i++){
//			//if(!CIdlist1.contains(kcxxlist.get(i).getZbh())){
//				CIdlist.add(kcxxlist.get(i).getZbh());
//			//}
//		}
//		//ͨ��CIdlist �е�CId���CName������CNameList
//		for(int j=0;j<CIdlist.size();j++){
//			kcxx=this.baseservice.find(CourseChapter.class, CIdlist.get(j));
//			CNameList.add(kcxx.getCName());
//		}
//		return SUCCESS;
//	}
	
}
