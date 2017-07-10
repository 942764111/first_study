/*
 *@(#)xx.kgt.action
 *@XzTestAction.java.java  
 *@����ʱ��:2011-8-9����09:20:58
 *@���ߣ�������
 *@Copyright 2009-2010 �ӱ�����ѧԺ��Ϣ��ѧ�빤��ѧԺ������ 
 */

package xx.kgt.action;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;


import xx.collection.bean.Pd;
import xx.collection.bean.Xz;
import xx.kgt.bean.Da;
import xx.kgt.bean.PanDuan;
import xx.kgt.bean.XuanZ;
import xx.quanxian.service.BaseService;

import com.googlecode.jsonplugin.annotations.JSON;
import com.opensymphony.xwork2.ActionSupport;

/**
 * @XzTestAction <code>{������}</code>
 * @author  {������}
 * @version {�汾,����ʱ�����}
 * @{��������:} 
 */

@Controller
@Scope("prototype")
@ParentPackage("default-package")
@Namespace("")
public class TestAction extends ActionSupport {
	
	@Resource(name="baseService")
	private BaseService baseservice;
	
	private List<Xz> xzlist = new ArrayList<Xz>();
	private List<XuanZ> xzlist1=new ArrayList<XuanZ>();
	private List<XuanZ> xzlist2=new ArrayList<XuanZ>();
	private Integer ddx;//�жϵ�����ѡʱ��
	private List<String> da1;//���ڽ���ѡ������Ŀ��ȷ�Ĵ𰸡�
	private List<String> tg1=new ArrayList<String>();
	
	
	private List<Pd> pdlist=new ArrayList<Pd>();
	private List<PanDuan> pdlist1=new ArrayList<PanDuan>();
	private List<PanDuan> panduan=new ArrayList<PanDuan>();
	private List<Integer> da2;//���ڽ����ж���Ŀ��ȷ�Ĵ𰸡�
	private List<String> tg;
	
	private List<Da> xzd=new ArrayList<Da>();//���������ύ�Ĵ��Լ���Ӧ����Ŀ����Ŀ�Ĵ𰸵�
	private List<Da> templist1=new ArrayList<Da>();
	private List<Da> templist2=new ArrayList<Da>();
	private List<List> answer=new ArrayList<List>();//���ڽ���ǰ̨�����Ĳ����ߵĴ𰸡�
	private int score = 0;//��ʼ���÷�
	private int zql;//������Ŀ����ȷ��
	private List<Integer> zsdz=new ArrayList<Integer>();//֪ʶ����ȷ��
	private List<Integer> zsdbh1;//���ڽ���֪ʶ���� 
	private List<String> zsdmc1=new ArrayList<String>();
	private List<String> zsdmc2=new ArrayList<String>();//��֪ʶ�����ƴ���ǰ̨
	private List<Integer> zsdz2=new ArrayList<Integer>();//��֪ʶ����ȷ�ʴ���ǰ̨
	private int page=1;//��ʼҳ
	private String zmc;//����
	private String CName;//����
	private String TCName;//�γ���
	private String zsdmc;
	private int total;//�ܵ�����
	private int a;//����ǰ̨����a��ֵ�ж�ѡ��Χ���Ƿ�����
	private int zy;//��ҳ
	private int pages;
	private int pagex;
	private int paget;
	private int p;
	private int b;
	private int x;//�����жϽ��еĲ���������ѡ�⡢��һҳ����һҳ������ת
	private int tip;//������־ʹ����һ�ֲ�ѯ������Ŀ�ķ�ʽ
	
	private int zbh=0;
	private int jbh=0;
	Cookie cookie = null;
	Cookie cookiep = null;
	


	public int getZbh() {
		return zbh;
	}
	public void setZbh(int zbh) {
		this.zbh = zbh;
	}
	public int getJbh() {
		return jbh;
	}
	public void setJbh(int jbh) {
		this.jbh = jbh;
	}
	public int getTip() {
		return tip;
	}
	public void setTip(int tip) {
		this.tip = tip;
	}
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getB() {
		return b;
	}
	public void setB(int b) {
		this.b = b;
	}
	public int getP() {
		return p;
	}
	public void setP(int p) {
		this.p = p;
	}
	public int getPages() {
		return pages;
	}
	public void setPages(int pages) {
		this.pages = pages;
	}
	public int getPagex() {
		return pagex;
	}
	public void setPagex(int pagex) {
		this.pagex = pagex;
	}
	public int getPaget() {
		return paget;
	}
	public void setPaget(int paget) {
		this.paget = paget;
	}
	public int getZy() {
		return zy;
	}
	public void setZy(int zy) {
		this.zy = zy;
	}
	public int getA() {
		return a;
	}
	public void setA(int a) {
		this.a = a;
	}
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	@JSON(serialize=false)
	public String getZmc() {
		return zmc;
	}
	public void setZmc(String zmc) {
		this.zmc = zmc;
	}
	@JSON(serialize=false)
	public String getCName() {
		return CName;
	}
	public void setCName(String name) {
		CName = name;
	}
	@JSON(serialize=false)
	public String getTCName() {
		return TCName;
	}
	public void setTCName(String name) {
		TCName = name;
	}
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	@JSON(serialize=false)
	public List<Integer> getZsdbh1() {
		return zsdbh1;
	}
	public void setZsdbh1(List<Integer> zsdbh1) {
		this.zsdbh1 = zsdbh1;
	}
	
	public List<String> getZsdmc1() {
		return zsdmc1;
	}
	public void setZsdmc1(List<String> zsdmc1) {
		this.zsdmc1 = zsdmc1;
	}
	@JSON(serialize=false)
	public List<String> getZsdmc2() {
		return zsdmc2;
	}
	public void setZsdmc2(List<String> zsdmc2) {
		this.zsdmc2 = zsdmc2;
	}
	@JSON(serialize=false)
	public List<Integer> getZsdz2() {
		return zsdz2;
	}
	public void setZsdz2(List<Integer> zsdz2) {
		this.zsdz2 = zsdz2;
	}
	@JSON(serialize=false)
	public List<String> getDa1() {
		return da1;
	}
	public void setDa1(List<String> da1) {
		this.da1 = da1;
	}
	@JSON(serialize=false)
	public List<Integer> getDa2() {
		return da2;
	}
	public void setDa2(List<Integer> da2) {
		this.da2 = da2;
	}
	@JSON(serialize=false)
	public List<String> getTg() {
		return tg;
	}
	public void setTg(List<String> tg) {
		this.tg = tg;
	}
	@JSON(serialize=false)
	public List<Pd> getPdlist() {
		return pdlist;
	}
	public void setPdlist(List<Pd> pdlist) {
		this.pdlist = pdlist;
	}
	@JSON
	public List<PanDuan> getPdlist1() {
		return pdlist1;
	}
	public void setPdlist1(List<PanDuan> pdlist1) {
		this.pdlist1 = pdlist1;
	}
	@JSON(serialize=false)
	public int getZql() {
		return zql;
	}
	public void setZql(int zql) {
		this.zql = zql;
	}
	@JSON(serialize=false)
	public List<Integer> getZsdz() {
		return zsdz;
	}
	public void setZsdz(List<Integer> zsdz) {
		this.zsdz = zsdz;
	}
	@JSON(serialize=false)
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
	@JSON(serialize=false)
	public List<List> getAnswer() {
		return answer;
	}
	public void setAnswer(List<List> answer) {
		this.answer = answer;
	}
	@JSON(serialize=false)
	public BaseService getBaseservice() {
		return baseservice;
	}
	public void setBaseservice(BaseService baseservice) {
		this.baseservice = baseservice;
	}
	@JSON(serialize=false)
	public List<Xz> getXzlist() {
		return xzlist;
	}
	public void setXzlist(List<Xz> xzlist) {
		this.xzlist = xzlist;
	}
	
	@JSON
	public List<XuanZ> getXzlist1() {
		return xzlist1;
	}
	public void setXzlist1(List<XuanZ> xzlist1) {
		this.xzlist1 = xzlist1;
	}
	
	
	@JSON(serialize=false)
	public Integer getDdx() {
		return ddx;
	}
	public void setDdx(Integer ddx) {
		this.ddx = ddx;
	}
	public String getZsdmc() {
		return zsdmc;
	}
	public void setZsdmc(String zsdmc) {
		this.zsdmc = zsdmc;
	}
	
	
	
//**********************ѡ������ԵĹ���*****************************
	
	/**
	 * @{xzcs2.action}
	 * �÷���������ָ����ת����ҳ����ѡ�����ҳ��
	 */
	@Action(value="/xzcs2",results={@Result(name="root",type="json")})
	public String xzcs2(){
		Cookie[] cookies;
		HttpServletRequest request=ServletActionContext.getRequest();
		HttpServletResponse response=ServletActionContext.getResponse();
		HttpSession session = ServletActionContext.getRequest().getSession();
		//��ȡ������ص�cookie
		cookies = request.getCookies();
		if (cookie == null){
			  cookie = new Cookie("VisitTimes","1");
			  response.addCookie(cookie);
			  }
		if(x==1){//�������ѡ��, xzd���.
			for(int i=0;i<cookies.length;i++){
				if(cookies[i].getName().equals("VisitTimes")){
					cookies[i].setValue(Integer.toString(0));
				}
			}
			xzd=(List<Da>) session.getAttribute("templ2");
			if(xzd!=null){
				session.setAttribute("templ2", null);
			}
		}
		
		  //�ж�Cookie VisitTimes�Ƿ����
		  //������ڣ���ֵ��1
		  if (cookies != null){
			  for (int i = 0; i < cookies.length; i++){
				  if (cookies[i].getName( ).equals("VisitTimes")){
				  String v=cookies[i].getValue();
				  int value=Integer.parseInt(v)+1;
				  cookies[i].setValue(Integer.toString(value));
				  response.addCookie(cookies[i]);
				  cookie=cookies[i];
				  }  
			  }
		  }
		int tempp=Integer.parseInt(cookie.getValue());
		session.setAttribute("testzbh", 0);
		session.setAttribute("testjbh", 0);
		session.setAttribute("Zsdmc", "");
		session.setAttribute("testkcmc", null);
		session.setAttribute("testxz",null);
		session.setAttribute("zytemppage", tempp);
		session.setAttribute("tempx", x);
		//ѡ����
		if(zsdmc!=null&&!zsdmc.equals("")){
			session.setAttribute("Zsdmc", zsdmc);
		}else{
			session.setAttribute("Zsdmc", null);
			if(!TCName.equals("--��ѡ��--")&&!zmc.equals("--��ѡ��--")&&!CName.equals("--��ѡ��--")){
				session.setAttribute("testkcmc", TCName);
				session.setAttribute("testzbh", zbh);
				session.setAttribute("testjbh", jbh);
			}else if(!TCName.equals("--��ѡ��--")&&zmc.equals("--��ѡ��--")&&!CName.equals("--��ѡ��--")){
				session.setAttribute("testkcmc", TCName);
				session.setAttribute("testzbh", zbh);
				session.setAttribute("testjbh", 0);
			}else if(!TCName.equals("--��ѡ��--")&&zmc.equals("--��ѡ��--")&&CName.equals("--��ѡ��--")){
				session.setAttribute("testkcmc", TCName);
				session.setAttribute("testzbh", 0);
				session.setAttribute("testjbh", 0);
			}
		}
		String zsdmc1=(String)session.getAttribute("Zsdmc");
		int zbh1=(Integer)session.getAttribute("testzbh");
		int jbh1=(Integer)session.getAttribute("testjbh");
		String kcmc1=(String)session.getAttribute("testkcmc");
		List<Integer> zsdbh=new  ArrayList<Integer>();
		if(zsdmc1!=null&&!zsdmc1.equals("")){
			String[] keys1=new String[1];
			keys1[0]="zsdmc";
			Object[] values1=new Object[1];
			values1[0]=zsdmc1;
			zsdbh=this.baseservice.find(Integer.class, "Zsd", "id.zsdbh", keys1, values1);
		}
		
		
		String hql1="";
		if(zsdmc1!=null&&!zsdmc1.equals("")){
			hql1="select count(*) from Xz where zsdbh="+zsdbh.get(0).toString();
			total=this.baseservice.getTotalSql(hql1);
		}else{
			if(jbh1!=0){
				hql1="select count(*) from Xz where zbh="+zbh1+ " and c_id="+jbh1;
				total=this.baseservice.getTotalSql(hql1);
			}else if(zbh1!=0&&jbh1==0){
				hql1="select count(*) from Xz where zbh="+zbh1;
				total=this.baseservice.getTotalSql(hql1);
			}else if(kcmc1!=null&&zbh1==0&&jbh1==0){
				String[] keys=new String[1];
				keys[0]="TCName";
				Object[] values=new Object[1];
				values[0]=kcmc1;
				List<Integer> zbh2=this.baseservice.find(Integer.class, "CourseChapter", "zbh", keys, values);
				hql1="select count(*) from Xz where zbh>='"+zbh2.get(0).toString()+ "' and zbh<='"+zbh2.get(zbh2.size()-1).toString()+"'";
				total=this.baseservice.getTotalSql(hql1);
			}
		}
		session.setAttribute("testtt", (int)Math.ceil((float)total/3));//����ȡ��,�ܵ�ҳ��
		zy=(int)Math.ceil((float)total/3);// ��ҳ 
		
		 
		pagex=tempp+1;//�����ж��Ƿ��������һҳ
//		if(page==-1&&pagE>=2){
//			pagE=pagE-1;//��һҳ
//			pages=pagE;
//		}
//		else if(page==0&&pagE<zy&&pagE>0){
//			pagE=pagE+1;//��һҳ
//			pagex=pagE;
//		}else if(pagE<=zy&&pagE>=0&&page>0&&page<=zy){
//			pagE=page;//��ת���̶�ҳ
//			paget=page;
//		}
//		paget=page;//�����ж���תʱ�Ƿ��ѳ������ҳ
		if(tempp<=zy&&tempp>=0){
			String hql="";
			if(zsdmc1!=null&&!zsdmc1.equals("")){
				hql="from Xz where zsdbh="+zsdbh.get(0).toString();
			}else{
				if(jbh1!=0){
					hql = "from Xz where zbh="+zbh1+ " and c_id="+jbh1;
				}else if(zbh1!=0&&jbh1==0){
					hql = "from Xz where zbh="+zbh1;
				}else if(kcmc1!=null&&zbh1==0&&jbh1==0){
					String[] keys=new String[1];
					keys[0]="TCName";
					Object[] values=new Object[1];
					values[0]=kcmc1;
					List<Integer> zbh=this.baseservice.find(Integer.class, "CourseChapter", "zbh", keys, values);
					hql = "from Xz where  zbh>="+zbh.get(0).toString()+ " and zbh<="+zbh.get(zbh.size()-1).toString();
				}
			}
			
			if(total>0){//���������ݼ���ִ�У����򷵻�a=0
				a=1;
				if(total>3){
					xzlist = this.baseservice.findSql(Xz.class, hql, tempp, 3);//��ҳ��ѯ,��ʱ��������������
				}else{
					xzlist=this.baseservice.findHql(Xz.class, hql);
				}
				for(int i=0;i<xzlist.size();i++){
		        	XuanZ element = new XuanZ();//����xx.kgt.bean�е�XuanZ.java���õ���Ҫ��ǰ̨��ʾ�����ݡ�
		        	element.setTg(xzlist.get(i).getTg());      	
		        	element.setXx1(xzlist.get(i).getXx1());
		        	element.setXx2(xzlist.get(i).getXx2());
		        	element.setXx3(xzlist.get(i).getXx3());
		        	element.setXx4(xzlist.get(i).getXx4());
		        	element.setDa(xzlist.get(i).getDa());
		        	element.setZsdbh(xzlist.get(i).getZsd().getId().getZsdbh()); 
		        	element.setDdx(xzlist.get(i).getDdx());
		        	element.setZhangname(xzlist.get(i).getZsd().getJie().getCourseChapter().getCName());
		        	element.setTh(xzlist.get(i).getTh());
		        		xzlist2.add(element);   //��ʱ�����ݶ�����Ҫ������
		        }
				session.setAttribute("testxz", xzlist2);
			}else{
				a=0;
			}
		}
		return "root";
	}
	
	
	//��ʾѡ�����ҳ��
	@Action(value="/xzcs3",results={@Result(name="root",location="/kgt/xztest.jsp")})
	public String xzcs3(){
		HttpSession session = ServletActionContext.getRequest().getSession();
		zy=(Integer)session.getAttribute("testtt");
		p=(Integer)session.getAttribute("zytemppage");
		xzlist1=(List)session.getAttribute("testxz");
		session.removeAttribute("testxz");
		return "root";
	}
	
	//flex�˵ķ�������,��ʾѡ�����ҳ��
	@Action(value="/selectTest",results={@Result(name="root",type="json")})
	public String selectTest(){
		HttpSession session = ServletActionContext.getRequest().getSession();
		zy=(Integer)session.getAttribute("testtt");
		p=(Integer)session.getAttribute("zytemppage");
		xzlist1=(List)session.getAttribute("testxz");
		session.removeAttribute("testxz");
		return "root";
	}
	
	//����ѡ�����(��ҳ����)
	@Action(value="/saveanswer",results={@Result(name="root",type="json")})
	public String saveanswer(){
		HttpSession hs = ServletActionContext.getRequest().getSession();
		String an="";
		for(int b=0;b<answer.size();b++){//answer.size()����ѡ�����Ŀz��
			if(answer.get(b)!=null){
				String strResult="";
				int i = answer.get(b).toString().length();//����iΪ3*[����ĳ��ȣ���Ԫ�صĸ���������ÿ����ѡ�еĸ�����]		
				for(int a=0;a<i/3;a++){
					strResult+=answer.get(b).get(a);//��answer�еĵ�b��Ԫ�����a����������������	
				}				
				an = strResult;//��ʱ����forѭ���⣩��strResult����b������Ԫ�ص����ӡ�
				Da da2 = new Da();
				da2.setXzanswer(da1.get(b));
				da2.setXzda(an);
				da2.setTg(tg.get(b));
				da2.setZsdbh(zsdbh1.get(b));
				String[] keys1=new String[1];
				keys1[0]="zsdbh";
				Object[] values1=new Object[1];
				values1[0]=zsdbh1.get(b);
				List<String> zsdmc=this.baseservice.find(String.class, "Zsd", "zsdmc", keys1, values1);
				da2.setZsdmc(zsdmc.get(0));
				if(templist1.size()==0){
					templist1.add(da2);
					tg1.add(tg.get(b));
				}else{
					if(!tg1.contains(tg.get(b))){
						templist1.add(da2);
						tg1.add(tg.get(b));
					}
				}
			}else{
				an = "";
			}
		}
		templist2.addAll(templist1);
		List<Da> templist3=new ArrayList<Da>();
		templist3=(List<Da>) hs.getAttribute("templ2");
		if(templist3!=null){
			templist2.addAll(templist3);
		}
		hs.setAttribute("templ2", templist2);
		return "root";
	}
	//ѡ������Խ������
	@Action(value="/xzfx3",results={@Result(name="root",location="/kgt/fx.jsp")})
	public String xzfx3(){
		HttpSession session = ServletActionContext.getRequest().getSession();
		String x1=(Integer)session.getAttribute("tempx")+"";
		xzd=(List<Da>) session.getAttribute("templ2");
		if(x1.equals("null")){
			xzd.clear();
			x=2;
		}
		if(xzd.size()!=0){
			for(int n=0;n<xzd.size();n++){
				if(xzd.get(n).getXzda().equals(xzd.get(n).getXzanswer())){
					score=score+1; //�������ȷ��������һ.
				}
			}
			//���������ȷ��
			zql=Integer.parseInt(new java.text.DecimalFormat("0").format(score*100/xzd.size()));//�ó���ȷ��(���е�score��Ϊ��Ե�����)
			//֪ʶ����ȷ��
			for(int c=0;c<xzd.size();c++){
				zsdmc1.add(xzd.get(c).getZsdmc());
				int t=0;
				int z=0;
				for(int e=0;e<xzd.size();e++){
					if(xzd.get(c).getZsdmc().equals(xzd.get(e).getZsdmc())){
						t=t+1;	//֪ʶ����ͬ������
						if(xzd.get(e).getXzda().equals(xzd.get(e).getXzanswer())){
							z=z+1;	//ͬһ֪ʶ����ȷ������
						}
					}
				}
				int zz=0;
				zz =Integer.parseInt(new java.text.DecimalFormat("0").format(z*100/t));// ֪ʶ����ȷ��
						zsdz.add(zz);
			}
			//ȡ���ظ������� 
			for(int x=0;x<xzd.size();x++){
				if(zsdmc2.size()==0){
					zsdmc2.add(xzd.get(x).getZsdmc());
					zsdz2.add(zsdz.get(x));
				}else if(!zsdmc2.contains(xzd.get(x).getZsdmc())){
					zsdmc2.add(xzd.get(x).getZsdmc());
					zsdz2.add(zsdz.get(x));
				}
			}
		}
		session.setAttribute("tempx", null);
		return "root";
	}	
	

//**********************�ж�����ԵĹ���***********************************
	/**
	 * @{pdcs.action}
	 * �÷���������ָ����ת����ҳ�����жϲ���ҳ��
	 */
	//��������ѡ��ҳ��
	@Action(value="/pdcs",results={@Result(name="root",type="json")})
	public String pdcs(){
		//�ж���
		Cookie[] cookiesp;
		HttpServletRequest request=ServletActionContext.getRequest();
		HttpServletResponse response=ServletActionContext.getResponse();
		HttpSession session = ServletActionContext.getRequest().getSession();
		//��ȡ������ص�cookie
		cookiesp = request.getCookies();
		if (cookiep == null){
			cookiep = new Cookie("VisitTimesp","1");
			  response.addCookie(cookiep);
			  }
		if(x==1){//�������ѡ��, xzd���.
			for(int i=0;i<cookiesp.length;i++){
				if(cookiesp[i].getName().equals("VisitTimesp")){
					cookiesp[i].setValue(Integer.toString(0));
				}
			}
			xzd=(List<Da>) session.getAttribute("temppdlist");
			if(xzd!=null){
				session.setAttribute("templ2", null);
			}
		}
		
		  //�ж�Cookie VisitTimes�Ƿ����
		  //������ڣ���ֵ��1
		  if (cookiesp != null){
			  for (int i = 0; i < cookiesp.length; i++){
				  if (cookiesp[i].getName( ).equals("VisitTimesp")){
				  String v=cookiesp[i].getValue();
				  int value=Integer.parseInt(v)+1;
				  cookiesp[i].setValue(Integer.toString(value));
				  response.addCookie(cookiesp[i]);
				  cookiep=cookiesp[i];
				  }  
			  }
		  }
		 int tempp=Integer.parseInt(cookiep.getValue());
		 
		
		session.setAttribute("testzbh", 0);
		session.setAttribute("testjbh", 0);
		session.setAttribute("Zsdmc", "");
		session.setAttribute("testkcmc", null);
		session.setAttribute("testxz",null);
		session.setAttribute("zytemppagep", tempp);
		session.setAttribute("temppd", x);
		
		if(zsdmc!=null&&!zsdmc.equals("")){
			session.setAttribute("Zsdmc", zsdmc);
		}else{
			session.setAttribute("Zsdmc", null);
			if(!TCName.equals("--��ѡ��--")&&!zmc.equals("--��ѡ��--")&&!CName.equals("--��ѡ��--")){
				session.setAttribute("testkcmc", TCName);
				session.setAttribute("testzbh", zbh);
				session.setAttribute("testjbh", jbh);
			}else if(!TCName.equals("--��ѡ��--")&&zmc.equals("--��ѡ��--")&&!CName.equals("--��ѡ��--")){
				session.setAttribute("testkcmc", TCName);
				session.setAttribute("testzbh", zbh);
				session.setAttribute("testjbh", 0);
			}else if(!TCName.equals("--��ѡ��--")&&zmc.equals("--��ѡ��--")&&CName.equals("--��ѡ��--")){
				session.setAttribute("testkcmc", TCName);
				session.setAttribute("testzbh", 0);
				session.setAttribute("testjbh", 0);
			}
		}
		String zsdmc1=(String)session.getAttribute("Zsdmc");
		int zbh1=(Integer)session.getAttribute("testzbh");
		int jbh1=(Integer)session.getAttribute("testjbh");
		String kcmc1=(String)session.getAttribute("testkcmc");
		List<Integer> zbh=new  ArrayList<Integer>();
		List<Integer> zsdbh=new  ArrayList<Integer>();
		if(zsdmc1!=null&&!zsdmc1.equals("")){
			String[] keys1=new String[1];
			keys1[0]="zsdmc";
			Object[] values1=new Object[1];
			values1[0]=zsdmc1;
			zsdbh=this.baseservice.find(Integer.class, "Zsd", "id.zsdbh", keys1, values1);
		}
		
		String hql1="";
		if(zsdmc1!=null&&!zsdmc1.equals("")){
			hql1="select count(*) from Pd where zsdbh="+zsdbh.get(0).toString();
			total=this.baseservice.getTotalSql(hql1);
		}else{
			if(jbh1!=0){
				hql1="select count(*) from Pd where zbh="+zbh1+ " and c_id="+jbh1;
				total=this.baseservice.getTotalSql(hql1);
			}else if(zbh1!=0&&jbh1==0){
				hql1="select count(*) from Pd where zbh="+zbh1;
				total=this.baseservice.getTotalSql(hql1);
			}else if(kcmc1!=null&&zbh1==0&&jbh1==0){
				String[] keys=new String[1];
				keys[0]="TCName";
				Object[] values=new Object[1];
				values[0]=kcmc1;
				List<Integer> zbh2=this.baseservice.find(Integer.class, "CourseChapter", "zbh", keys, values);
				hql1="select count(*) from Pd where zbh>='"+zbh2.get(0).toString()+ "' and zbh<='"+zbh2.get(zbh2.size()-1).toString()+"'";
				total=this.baseservice.getTotalSql(hql1);
			}
		}
		session.setAttribute("testtt", (int)Math.ceil((float)total/3));//����ȡ��,�ܵ�ҳ��
		zy=(int)Math.ceil((float)total/3);// ��ҳ 
		
		pagex=tempp+1;//�����ж��Ƿ��������һҳ
//		if(page==0&&pagE<zy&&pagE>0){
//			pagE=pagE+1;//��һҳ
//		}else if(pagE<=zy&&pagE>=0&&page>0&&page<=zy){
//			pagE=page;
//		}
//		pagex=pagE+1;//�����ж��������һҳ
//		if(page==-1&&pagE>=2){
//			pagE=pagE-1;//��һҳ
//			pages=pagE;
//		}else if(page==0&&pagE<zy&&pagE>0){
//			pagE=pagE+1;//��һҳ
//			pagex=pagE;
//		}else if(pagE<=zy&&pagE>=0&&page>0&&page<=zy){
//			pagE=page;//��ת���̶�ҳ
//			paget=page;
//		}
//		paget=page;//�����ж���תʱ�ѳ������ҳ
		if(tempp<=zy&&tempp>=0){
			String hql="";
			if(zsdmc1!=null&&!zsdmc1.equals("")){
				hql="from Pd where zsdbh="+zsdbh.get(0).toString();
			}else{
				if(jbh1!=0){
					hql = "from Pd where zbh="+zbh1+ " and c_id="+jbh1;
				}else if(zbh1!=0&&jbh1==0){
					hql = "from Pd where zbh="+zbh1;
				}else if(kcmc1!=null&&zbh1==0&&jbh1==0){
					String[] keys=new String[1];
					keys[0]="TCName";
					Object[] values=new Object[1];
					values[0]=kcmc1;
					zbh=this.baseservice.find(Integer.class, "CourseChapter", "zbh", keys, values);
					hql = "from Pd where  zbh>="+zbh.get(0).toString()+ " and zbh<="+zbh.get(zbh.size()-1).toString();
				}
			}
			
			if(total>0){//���������ݼ���ִ�У����򷵻�a=0
				a=1;
				if(total>3){
					pdlist = this.baseservice.findSql(Pd.class, hql, tempp, 3);//��ҳ��ѯ,��ʱ��������������
				}else{
					pdlist=this.baseservice.findHql(Pd.class, hql);
				}
				for(int i=0;i<pdlist.size();i++){
		        	PanDuan element = new PanDuan();//����xx.kgt.bean�е�PanDuan.java���õ���Ҫ��ǰ̨��ʾ�����ݡ�
		        	element.setStr1(pdlist.get(i).getTg());      	
		        	element.setInt1(pdlist.get(i).getDa());
		        	element.setInt2(pdlist.get(i).getZsd().getId().getZsdbh()); 
		        	element.setZmc(pdlist.get(i).getZsd().getJie().getCourseChapter().getCName());
		        	element.setInt4(pdlist.get(i).getTh());
		        	panduan.add(element);   //��ʱ�����ݶ�����Ҫ������
		        }
				session.setAttribute("testpd", panduan);
			}else{
				a=0;
			}
		}
		return "root";
	}
	
	
	//��ѯ����֪ʶ������(�Զ����ʱ��)
	@Action(value="/queryzsdmc",results={@Result(name="root",type="json")})
	public String queryzsdmc(){
		String hql="select zsdmc from Zsd";
		zsdmc1=this.baseservice.findHql(String.class, hql);
		return "root";
	}
	
	
	
//��ʾ�ж����ҳ�� 
	@Action(value="/pdcs1",results={@Result(name="root",location="/kgt/pdtest.jsp")})
	public String pdcs1(){
		HttpSession session = ServletActionContext.getRequest().getSession();
		zy=(Integer)session.getAttribute("testtt");
		pdlist1=(List)session.getAttribute("testpd");
		p=(Integer)session.getAttribute("zytemppagep");
		return "root";
	}
	
	//��ʾ�ж����ҳ�� 
	@Action(value="/pdTest",results={@Result(name="root",type="json")})
	public String pdTest(){
		HttpSession session = ServletActionContext.getRequest().getSession();
		zy=(Integer)session.getAttribute("testtt");
		pdlist1=(List)session.getAttribute("testpd");
		p=(Integer)session.getAttribute("zytemppagep");
		return "root";
	}
	
//�����ж����(��ҳ����)
	@Action(value="/saveanswerp",results={@Result(name="root",type="json")})
	public String saveanswerp(){
		HttpSession hs = ServletActionContext.getRequest().getSession();
		for(int b=0;b<answer.size();b++){//answer.size()����ѡ�����Ŀ��
			if(answer.get(b)!=null){
				Da da3 = new Da();
				da3.setPdanswer(da2.get(b));//����Ĵ�
				da3.setPdda(Integer.parseInt(answer.get(b).get(0).toString()));//�ύ�Ĵ�
				da3.setTg(tg.get(b));
				da3.setZsdbh(zsdbh1.get(b));
				//��zsdbh�ó�zsdmc
				String[] keys1=new String[1];
				keys1[0]="zsdbh";
				Object[] values1=new Object[1];
				values1[0]=zsdbh1.get(b);
				List<String> zsdmc=this.baseservice.find(String.class, "Zsd", "zsdmc", keys1, values1);
				
				da3.setZsdmc(zsdmc.get(0));
				System.out.println(tg.get(b));
				System.out.println(Integer.parseInt(answer.get(b).get(0).toString()));
				System.out.println(da2.get(b));
				System.out.println(zsdbh1.get(b));
				
				if(templist1.size()==0){
					templist1.add(da3);
					tg1.add(tg.get(b));
				}else{
					if(!tg1.contains(tg.get(b))){
						templist1.add(da3);
						tg1.add(tg.get(b));
					}
				}
//				if(xzd.size()==0){
//					xzd.add(da3);
//					tg1.add(tg.get(b));
//				}else{
//					if(!tg1.contains(tg.get(b))){
//						xzd.add(da3);
//						tg1.add(tg.get(b));
//					}
//				}
			}
		}
		templist2.addAll(templist1);
		List<Da> templist3=new ArrayList<Da>();
		templist3=(List<Da>) hs.getAttribute("temppdlist");
		if(templist3!=null){
			templist2.addAll(templist3);
		}
		hs.setAttribute("temppdlist", templist2);
		return "root";
	}	
	
	//�ж�����Խ������
	@Action(value="/pdfx3",results={@Result(name="root",location="/kgt/fx.jsp")})
	public String pdfx3(){
		HttpSession session = ServletActionContext.getRequest().getSession();
		String x1=(Integer)session.getAttribute("temppd")+"";
		xzd=(List<Da>) session.getAttribute("temppdlist");
		if(x1.equals("null")){
			xzd.clear();
			x=2;
		}
		if(xzd.size()!=0){
			for(int n=0;n<xzd.size();n++){
				if(xzd.get(n).getPdda()==(xzd.get(n).getPdanswer())){
					score=score+1; //�������ȷ��������һ.
				}
			}
			//���������ȷ��
			zql=Integer.parseInt(new java.text.DecimalFormat("0").format(score*100/xzd.size()));//�ó���ȷ��(���е�score��Ϊ��Ե�����)
			
			//֪ʶ����ȷ��
			for(int c=0;c<xzd.size();c++){
				zsdmc1.add(xzd.get(c).getZsdmc());
				int t=0;
				int z=0;
				for(int e=0;e<xzd.size();e++){
					if(xzd.get(c).getZsdmc().equals(xzd.get(e).getZsdmc())){
						t=t+1;	//֪ʶ����ͬ������
						if(xzd.get(e).getPdda()==(xzd.get(e).getPdanswer())){
							z=z+1;	//ͬһ֪ʶ����ȷ������
						}
					}
				}
				int zz=0;
				zz =Integer.parseInt(new java.text.DecimalFormat("0").format(z*100/t));// ֪ʶ����ȷ��
						zsdz.add(zz);
			}
			//ȡ���ظ������� 
			for(int x=0;x<xzd.size();x++){
				if(zsdmc2.size()==0){
					zsdmc2.add(xzd.get(x).getZsdmc());
					zsdz2.add(zsdz.get(x));
				}else if(!zsdmc2.contains(xzd.get(x).getZsdmc())){
					zsdmc2.add(xzd.get(x).getZsdmc());
					zsdz2.add(zsdz.get(x));
				}
			}
		}
		session.setAttribute("temppd", null);
		return "root";
	}		
}
