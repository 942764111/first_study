/*
 *@(#)xx.testxg.action
 *@ZjgzAction.java.java  
 *@����ʱ��:2011-8-6����03:16:03
 *@���ߣ�Administrator
 *@Copyright 2009-2010 �ӱ�����ѧԺ��Ϣ��ѧ�빤��ѧԺ������ 
 */

package xx.testxg.action;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
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

import com.googlecode.jsonplugin.annotations.JSON;
import com.opensymphony.xwork2.ActionSupport;

import xx.adminservice.AdminService;

import xx.collection.bean.CourseChapter;
import xx.collection.bean.Jxjh;
import xx.collection.bean.Pd;
import xx.collection.bean.Sjnr;
import xx.collection.bean.Xsdyjl;
import xx.collection.bean.XsdyjlId;
import xx.collection.bean.Xz;
import xx.quanxian.service.BaseService;
import xx.xuexi.action.JxjhAction;

/**
 * @ZjgzAction <code>{������}</code>
 * @author {ţ��ΰ}
 * @version {�汾,����ʱ�����}
 * @{��������:������ʦ�趨��������
 */

@Controller
@Scope("prototype")
@ParentPackage("default-package")
@Namespace("")
public class ZjgzAction extends ActionSupport {
	
	@Resource(name = "baseService")
	private BaseService baseservice;
	@Resource(name = "adminService")
    private AdminService adminservice;
	
	private int no;
	private String sjname;
	private String no1;
	private int zbh;
	public String TCName;
	public String CName;
	public String CName1;
    private int dxz;
	private int xz;
	private int pd;
	private int cz;
	private String tm;
	private int th;
	private int th1;
	private int th2;
    private String tip;	//ʹ��ajax�첽�ύʱ�����ڱ�Ƿ��ص�ֵ
	private Sjnr sjno;
	private List<String> tm1 = new ArrayList<String>();
	//*****************�з�*******************
	private List<List> answer=new ArrayList<List>();//���ڽ���ǰ̨�����Ķ�ѡ��𰸡�
	private List<List> answer1=new ArrayList<List>();//���ڽ���ǰ̨�����ĵ�ѡ��𰸡�
	private List<List> answer2=new ArrayList<List>();//���ڽ���ǰ̨�������ж���𰸡�
	private List<String> da1;//���ڽ��յ�ѡ��Ĵ𰸡�
	private List<String> da;//���ڽ��ն�ѡ��Ĵ𰸡�
	private List<String> da2;//���ڽ����ж���Ĵ𰸡�
    private List<Xz> xz1=new ArrayList<Xz>();
    private List<Xz> dxz1=new ArrayList<Xz>();
    private List<Pd> pd1=new ArrayList<Pd>();
    private List<Cztzj> czt1=new ArrayList<Cztzj>();
    private List<Cztzj> ckcz1=new ArrayList<Cztzj>();
    //********************�鿴�Ծ�******************
    private List<Cztzj> testczt=new ArrayList<Cztzj>();
	private List<Xz> ckxz1=new ArrayList<Xz>();
	private List<Xz> testxz1=new ArrayList<Xz>();
	private List<Pd> ckpd1=new ArrayList<Pd>();
	private List<Pd> testpd1=new ArrayList<Pd>();
	private List<Xz> ckxz2=new ArrayList<Xz>();
	private List<Xz> testxz2=new ArrayList<Xz>();
	private List<Pd> ckpd2=new ArrayList<Pd>();
	private List<cksjczt> ckcz2=new ArrayList<cksjczt>();
	private int total1;
	private int total2;
	private int total3;
	private int total4;
	private int zbh1=0;//��ʼ�±��
	private int zbh2=0;//��ֹ�±��
	
	//����ѧ�������¼
	private String sjbh2;
	private int sjbh1;
	private List  dcqk=new ArrayList();
	
	
	//������Ŀʱ����
	private int ghth;//Ҫ���������
	private int xinth;//ǰ̨�������µ����
	

   
	public int getZbh1() {
		return zbh1;
	}
	public void setZbh1(int zbh1) {
		this.zbh1 = zbh1;
	}
	public int getZbh2() {
		return zbh2;
	}
	public void setZbh2(int zbh2) {
		this.zbh2 = zbh2;
	}
	public List<Cztzj> getTestczt() {
		return testczt;
	}
	public void setTestczt(List<Cztzj> testczt) {
		this.testczt = testczt;
	}
	public List<Xz> getTestxz1() {
		return testxz1;
	}
	public void setTestxz1(List<Xz> testxz1) {
		this.testxz1 = testxz1;
	}
	public List<Pd> getTestpd1() {
		return testpd1;
	}
	public void setTestpd1(List<Pd> testpd1) {
		this.testpd1 = testpd1;
	}
	public List<Xz> getTestxz2() {
		return testxz2;
	}
	public void setTestxz2(List<Xz> testxz2) {
		this.testxz2 = testxz2;
	}
	public String getSjbh2() {
		return sjbh2;
	}
	public void setSjbh2(String sjbh2) {
		this.sjbh2 = sjbh2;
	}
	public String getSjname() {
		return sjname;
	}
	public void setSjname(String sjname) {
		this.sjname = sjname;
	}
	public List getDcqk() {
		return dcqk;
	}
	public void setDcqk(List dcqk) {
		this.dcqk = dcqk;
	}
	public int getSjbh1() {
		return sjbh1;
	}
	public void setSjbh1(int sjbh1) {
		this.sjbh1 = sjbh1;
	}
	public List<String> getDa() {
		return da;
	}
	public void setDa(List<String> da) {
		this.da = da;
	}
	public List<String> getDa2() {
		return da2;
	}
	public void setDa2(List<String> da2) {
		this.da2 = da2;
	}
	public List<List> getAnswer1() {
		return answer1;
	}
	public void setAnswer1(List<List> answer1) {
		this.answer1 = answer1;
	}
	public List<List> getAnswer2() {
		return answer2;
	}
	public void setAnswer2(List<List> answer2) {
		this.answer2 = answer2;
	}
	public int getTotal1() {
		return total1;
	}
	public void setTotal1(int total1) {
		this.total1 = total1;
	}

	public int getTotal2() {
		return total2;
	}
	public void setTotal2(int total2) {
		this.total2 = total2;
	}
	
	public int getTotal3() {
		return total3;
	}
	public void setTotal3(int total3) {
		this.total3 = total3;
	}

	public int getTotal4() {
		return total4;
	}
	public void setTotal4(int total4) {
		this.total4 = total4;
	}
	@JSON(serialize=false)
	public String getNo1() {
		return no1;
	}
	public void setNo1(String no1) {
		this.no1 = no1;
	}
	public String getTip() {
		return tip;
	}
	@JSON(serialize=false)
	public Sjnr getSjno() {
		return sjno;
	}
	public void setSjno(Sjnr sjno) {
		this.sjno = sjno;
	}
	@JSON(deserialize=true)
	public void setTip(String tip) {
		this.tip = tip;
	}
	@JSON(serialize=false)
	public List<Xz> getCkxz2() {
		return ckxz2;
	}
	public void setCkxz2(List<Xz> ckxz2) {
		this.ckxz2 = ckxz2;
	}
	@JSON(serialize=false)
	public List<Pd> getCkpd2() {
		return ckpd2;
	}
	public void setCkpd2(List<Pd> ckpd2) {
		this.ckpd2 = ckpd2;
	}
	@JSON(serialize=false)
	public List<cksjczt> getCkcz2() {
		return ckcz2;
	}
	public void setCkcz2(List<cksjczt> ckcz2) {
		this.ckcz2 = ckcz2;
	}
	@JSON(serialize=false)
	public List<Cztzj> getCkcz1() {
		return ckcz1;
	}
	public void setCkcz1(List<Cztzj> ckcz1) {
		this.ckcz1 = ckcz1;
	}
	@JSON(serialize=false)
	public List<Pd> getCkpd1() {
		return ckpd1;
	}
	public void setCkpd1(List<Pd> ckpd1) {
		this.ckpd1 = ckpd1;
	}
	@JSON(serialize=false)
	public List<Xz> getCkxz1() {
		return ckxz1;
	}
	public void setCkxz1(List<Xz> ckxz1) {
		this.ckxz1 = ckxz1;
	}
	@JSON(serialize=false)
	public List<Cztzj> getCzt1() {
		return czt1;
	}
	public void setCzt1(List<Cztzj> czt1) {
		this.czt1 = czt1;
	}
	@JSON(serialize=false)
	public List<Xz> getXz1() {
		return xz1;
	}
	public void setXz1(List<Xz> xz1) {
		this.xz1 = xz1;
	}
	@JSON(serialize=false)
	public List<Xz> getDxz1() {
		return dxz1;
	}
	public void setDxz1(List<Xz> dxz1) {
		this.dxz1 = dxz1;
	}
	@JSON(serialize=false)
	public List<Pd> getPd1() {
		return pd1;
	}
	public void setPd1(List<Pd> pd1) {
		this.pd1 = pd1;
	}
	
	@JSON(serialize=false)
	public List<String> getDa1() {
		return da1;
	}
	public void setDa1(List<String> da1) {
		this.da1 = da1;
	}
	public List<List> getAnswer() {
		return answer;
	}
	public void setAnswer(List<List> answer) {
		this.answer = answer;
	}
	
	@JSON(serialize=false)
	public int getTh1() {
		return th1;
	}
	@JSON(serialize=false)
	public List<String> getTm1() {
		return tm1;
	}
	public void setTm1(List<String> tm1) {
		this.tm1 = tm1;
	}

	public void setTh1(int th1) {
		this.th1 = th1;
	}
	@JSON(serialize=false)
	public int getTh2() {
		return th2;
	}

	public void setTh2(int th2) {
		this.th2 = th2;
	}
	@JSON(serialize=false)
	public int getTh() {
		return th;
	}

	public void setTh(int th) {
		this.th = th;
	}

	
	@JSON(serialize=false)
	public String getTm() {
		return tm;
	}

	public void setTm(String tm) {
		this.tm = tm;
	}
	@JSON(serialize=false)
	public AdminService getAdminservice() {
		return adminservice;
	}
	public void setAdminservice(AdminService adminservice) {
		this.adminservice = adminservice;
	}

	@JSON(serialize=false)
	public BaseService getBaseservice() {
		return baseservice;
	}

	public void setBaseservice(BaseService baseservice) {
		this.baseservice = baseservice;
	}
	@JSON(serialize=false)
	public int getNo() {
		return no;
	}
	@JSON(deserialize=true)
	public void setNo(int no) {
		this.no = no;
	}
	@JSON(serialize=false)
	public int getZbh() {
		return zbh;
	}
	@JSON(deserialize=true)
	public void setZbh(int zbh) {
		this.zbh = zbh;
	}
	
	@JSON(serialize=false)
	public String getTCName() {
		return TCName;
	}
	public void setTCName(String name) {
		TCName = name;
	}
	@JSON(serialize=false)
	public String getCName() {
		return CName;
	}
	public void setCName(String name) {
		CName = name;
	}
	@JSON(serialize=false)
	public String getCName1() {
		return CName1;
	}
	public void setCName1(String name1) {
		CName1 = name1;
	}
	@JSON(serialize=false)
	public int getDxz() {
		return dxz;
	}
	public void setDxz(int dxz) {
		this.dxz = dxz;
	}
	@JSON(serialize=false)
	public int getXz() {
		return xz;
	}

	public void setXz(int xz) {
		this.xz = xz;
	}
	@JSON(serialize=false)
	public int getPd() {
		return pd;
	}

	public void setPd(int pd) {
		this.pd = pd;
	}
	@JSON(serialize=false)
	public int getCz() {
		return cz;
	}

	public void setCz(int cz) {
		this.cz = cz;
	}
	
	@JSON(serialize=false)
	public int getGhth() {
		return ghth;
	}
	public void setGhth(int ghth) {
		this.ghth = ghth;
	}
	@JSON(serialize=false)
	public int getXinth() {
		return xinth;
	}
	public void setXinth(int xinth) {
		this.xinth = xinth;
	}
//*********************�������ص�action**********************

	/**
	 * @{zylx.action}
	 * �÷���������ָ����ת����ҳ�������ɲ���ҳ��
	 */
	@Action(value="/zylx1",results={@Result(name="success",location="/kgt/zytest1.jsp")})
	public String zylx1(){
		return SUCCESS;
	}
	
	
	//  ���ݿγ̡��½��������
	@Action(value = "/zjgz", results = { @Result(name = "success", type="json")})
	public String zjgz() {
		if(zbh1==0){
			HttpSession session = ServletActionContext.getRequest().getSession();
			zbh1=(Integer)session.getAttribute("kkch");
		}
		if(CName!=null){
			HttpSession session = ServletActionContext.getRequest().getSession();
			total1=(Integer)session.getAttribute("tt1");
			total2=(Integer)session.getAttribute("tt2");
			total3=(Integer)session.getAttribute("tt3");
			total4=(Integer)session.getAttribute("tt4");
			List<Integer> czth=(List<Integer>)session.getAttribute("cztth");
			List<Cztzj> cztd=new ArrayList<Cztzj>();
			
			if(xz<total1||total1==xz){//��������ĵ�ѡ������С�����ݿ�������������¼���ִ�У����򷵻�tip="xz"
				if(dxz<total2||dxz==total2){//��������Ķ�ѡ������С�����ݿ�������������¼���ִ�У����򷵻�tip="dxz"
					if(total3>pd||total3==pd){//���������ж�����С�����ݿ��������������ִ�У����򷵻�tip="pd"
						if(total4>cz||total4==cz){
							//*********��ѡ��****************
							String s="";
							if(xz>0){
								Integer a[] = new Integer[xz];//xzΪǰ̨���ʱ�����ĵ�ѡ��ĸ���
								List<Integer> xzth=this.adminservice.xzth(zbh1);//��÷��������ĵ�ѡ�����
								List<Integer> xtemp=new ArrayList<Integer>();
								int tsx=xzth.size();
								for (int i = 0;; i++) {
									int b =(int) (Math.random()*tsx); //����Щ��ѡ������������ȡһ�����
									if(xtemp.size()==0){
										xtemp.add(b);
									}else if(!xtemp.contains(b)){
										xtemp.add(b);
									}
									 if(xtemp.size()==xz)
										{
											break;
										}
								}
								for(int jj=0;jj<xtemp.size();jj++){
									a[jj] = xzth.get(xtemp.get(jj));
								}
								int temp=0;
								for(int i=0;i<a.length;i++) {
								     for(int j=0;j<a.length-i-1;j++) {
								      if(a[j]>a[j+1]) {
								       temp=a[j];
								       a[j]=a[j+1];
								       a[j+1]=temp;
								      }
								     }
								    }//��ð�����򷨶�a[]���д�С��������
								String jj=Arrays.asList(a).toString();
								if(jj.length()>0){
									s=jj.substring(1, jj.length()-1).replaceAll(" ", "");//Ҫ����ĵ�ѡ�������
								}
							}
							
				
							//��ѡ��
							String ds="";
							if(dxz>0){
								Integer da[] = new Integer[dxz];
								List<Integer> dxzth=this.adminservice.dxzth(zbh1);
								int ts=dxzth.size();
								List<Integer> dxtemp = new ArrayList<Integer>();
								for (int di = 0;;di++) {
								    int d=(int)(Math.random()*ts);
								    if(dxtemp.size()==0){
								    	dxtemp.add(d);
								    }else if(!dxtemp.contains(d)){
								    	 dxtemp.add(d);
								    }
								    if(dxtemp.size()==dxz)
									{
										break;
									}
								}
								for(int x=0;x<dxtemp.size();x++){
									da[x] =dxzth.get(dxtemp.get(x));
								}
								int tempd=0;
								for(int i=0;i<da.length;i++) {
								     for(int j=0;j<da.length-i-1;j++) {
								      if(da[j]>da[j+1]) {
								       tempd=da[j];
								       da[j]=da[j+1];
								       da[j+1]=tempd;
								      }
								     }
								    }//��ð�����򷨶�da[]���д�С��������
								//�����ѡ�����
								String dd=Arrays.asList(da).toString();
								ds=dd.substring(1, dd.length()-1).replaceAll(" ", "");//Ҫ����Ķ�ѡ�������
							}
								
								Sjnr sjnr=new Sjnr();
								String hqlno="select count(*) from Sjnr";
								String hqlsj="select sjno from Sjnr order by sjno asc";
								List<Integer> sj=this.baseservice.findHql(Integer.class, hqlsj);
								int notemp=this.baseservice.getTotalSql(hqlno)+1;
								if(sj.size()>0){
									for(int h=0;h<sj.size();h++){
										if(sj.get(h)!=h+1){
											no=h+1;
											break;
										}else{
											no=notemp;
										}
									}//�����Ծ���
								}else{
									no=1;
								}
								
								sjnr.setSjno(no);
								if(!s.equals("")&!ds.equals("")){
									sjnr.setXzsz(s+","+ds);
								}else if(s.equals("")&!ds.equals("")){
									sjnr.setXzsz(ds);
								}else{
									sjnr.setXzsz(s);
								}

				//******************�ж��ⲿ��***********************	
								String sp="";
								if(pd>0){
									Integer b[] =new Integer [pd];
									List<Integer> pdth=this.adminservice.pdth(zbh1);
									List<Integer> pdtemp=new ArrayList<Integer>();
									int tsp=pdth.size();
									for (int j=0;;j++){
										int d=(int)(Math.random()*tsp);
										if(pdtemp.size()==0){
											pdtemp.add(d);
										}else if(!pdtemp.contains(d)){
											pdtemp.add(d);
										}
										  if(pdtemp.size()==pd)
											{
												break;
											}
									}
									for(int x=0;x<pdtemp.size();x++){
										b[x] =pdth.get(pdtemp.get(x));
									}
									int tempp=0;
									for(int i=0;i<b.length;i++) {
									     for(int j=0;j<b.length-i-1;j++) {
									      if(b[j]>b[j+1]) {
									    	  tempp=b[j];
									       b[j]=b[j+1];
									       b[j+1]=tempp;
									      }
									     }
									    }//��ð�����򷨶�b[]���д�С��������
									//�����ж������
									String pp=Arrays.asList(b).toString();
									if(pp.length()>0){
										sp=pp.substring(1, pp.length()-1).replaceAll(" ", "");//Ҫ������ж��������
									}
									
								}
								sjnr.setPdsz(sp);
					//******************�����ⲿ��***********************	
								String cp="";
								if(cz>0){
									Integer c[]=new Integer[cz];
									int tsczt=czth.size();
									List<Integer> cztemp=new ArrayList<Integer>();
									for (int k=0;;k++){
										int d=(int)(Math.random()*tsczt);
										if(cztemp.size()==0){
											c[k] =czth.get(d);
											cztemp.add(d);
										}else if(!cztemp.contains(d)){
											cztemp.add(d);
										}
										if(cztemp.size()==cz){
											break;
										}
									}
									for(int x=0;x<cztemp.size();x++){
										c[x] =czth.get(cztemp.get(x));
									}
									for(int u=0;u<c.length;u++){
										cztd.add(this.adminservice.cztnr(c[u]));
									}
									
									//������������
									int k=0;
									for (Integer sjcz:c){
										if(c.length-1>k){
											cp=cp+sjcz.toString();
											for(int x=0;x<cztd.size();x++){
												if(cztd.get(x).getDtth()==sjcz&&cztd.get(x).getXtth().size()>0){
													for(int y=0;y<cztd.get(x).getXtth().size();y++){
														cp=cp+"|"+cztd.get(x).getXtth().get(y);//С��֮����"|"����
													}
												}
											}
											cp=cp+",";//����֮���á���������
										}else{
											cp=cp+sjcz.toString();
											for(int x=0;x<cztd.size();x++){
												if(cztd.get(x).getDtth()==sjcz&&cztd.get(x).getXtth().size()>0){
													for(int y=0;y<cztd.get(x).getXtth().size();y++){
														cp=cp+"|"+cztd.get(x).getXtth().get(y);
													}
												}
											}
										}
										k=k+1;
									}
									
								}
								//���Ծ��š���ʼ�±�š���ֹ�±�Ŵ���session
								session.setAttribute("testpaperno",no);
								session.setAttribute("ghzno1",zbh1);
								session.setAttribute("ghzno2",zbh1);
								
								sjnr.setCztsz(cp);
								sjname=CName+"���ò���"+"("+no+")";
								session.setAttribute("testpapername",sjname);
								CourseChapter cc=new CourseChapter();
								cc.setZbh(zbh1);
								sjnr.setCjsj(new Date());
								sjnr.setDq(false);
								sjnr.setCourseChapter(cc);
								sjnr.setZxx(sjname);
								String tempid=(Integer)session.getAttribute("i_d")+"";
								if(tempid!=null&&!tempid.equals("")&&!tempid.equals("null")&&!tempid.equals("0")){
									Jxjh jxjh=new Jxjh();
									int jxjhid=(Integer)session.getAttribute("i_d");
									jxjh.setId(jxjhid);
									sjnr.setJxjh(jxjh);
									session.setAttribute("i_d", 0);
								}
								this.baseservice.save(sjnr);
							}else{
								tip="cz";
							}
						}else{
							tip="pd";
						}
						}else{
							tip="dxz";
						}
					}else{
						tip="xz";
					}
			}
		return SUCCESS;
		}

	//�����ʱ��������������Χ�ڸ������͵�����
	@Action(value="/jyts1",results={@Result(name="root",type="json")})
	public String jyts1(){
		if(zbh1==0){
			HttpSession session = ServletActionContext.getRequest().getSession();
			zbh1=(Integer)session.getAttribute("kkch");
		}
		List<Integer> czth=this.adminservice.searchcztth(zbh1, zbh1);
		String hql2="select count(*) from  Xz where zbh = '"+zbh1+"' and ddx= '"+1+"'";
		String hql3="select count(*) from  Xz where zbh = '"+zbh1+"' and ddx= '"+0+"'";
		String hql4="select count(*) from  Pd where zbh = '"+zbh1+"' ";
		total4=czth.size();//���������Ĳ���������
		total2=this.baseservice.getTotalSql(hql2);//���������Ķ�ѡ������
		total1=this.baseservice.getTotalSql(hql3);//���������ĵ�ѡ������
		total3=this.baseservice.getTotalSql(hql4);//�����������ж�������
		HttpSession session = ServletActionContext.getRequest().getSession();
		session.setAttribute("tt1",total1);
		session.setAttribute("tt2",total2);
		session.setAttribute("tt3",total3);
		session.setAttribute("tt4",total4);
		session.setAttribute("cztth",czth);
		return "root";
	}	

	// ���ݿγ̡��½��н׶����
	@Action(value = "/jdzjgz", results = { @Result(name = "success", type="json"), })
	public String jdzjgz() {
		if(zbh1!=0&&zbh2!=0&&CName!=null&&CName1!=null&&!CName.equals("")&&!CName1.equals("")){
			String zxx1;
			if(CName.equals(CName1)){
				zxx1=CName;
			}else{
				zxx1=CName+"��"+CName1;
			}
			HttpSession session = ServletActionContext.getRequest().getSession();
			total1=(Integer)session.getAttribute("tt1");
			total2=(Integer)session.getAttribute("tt2");
			total3=(Integer)session.getAttribute("tt3");
			total4=(Integer)session.getAttribute("tt4");
			List<Integer> czth=(List<Integer>)session.getAttribute("cztth");

			List<Cztzj> cztd=new ArrayList<Cztzj>();
			Sjnr sjnr=new Sjnr();
			if(xz<total1||total1==xz){//��������ĵ�ѡ������С�����ݿ�������������¼���ִ�У����򷵻�tip="xz"
				if(dxz<total2||dxz==total2){//��������Ķ�ѡ������С�����ݿ�������������¼���ִ�У����򷵻�tip="dxz"
					if(total3>pd||total3==pd){//���������ж�����С�����ݿ��������������ִ�У����򷵻�tip="pd"
						if(total4>cz||total4==cz){//�������Ĳ�������С�����ݿ��������������ִ�У����򷵻�tip="cz"
							//******************��ѡ��***********************
							String s="";
							if(xz>0){
								Integer a[] = new Integer[xz];
								List<Integer> jdxzth=this.adminservice.jdxzth(zbh1, zbh2);
								List<Integer> jdxtemp=new ArrayList<Integer>();
								int ts=jdxzth.size();
								for (int i = 0;; i++) {
									int b=(int) (Math.random()*ts);
									if(jdxtemp.size()==0){
										jdxtemp.add(b);
									}else if(!jdxtemp.contains(b)){
										jdxtemp.add(b);
									}
									if(jdxtemp.size()==xz){
										break;
									}
								}
								for(int x=0;x<jdxtemp.size();x++){
									a[x] =  jdxzth.get(jdxtemp.get(x));  //�������id��ȥ���ݿ��е�ȡ����
								}
								int temp=0;
								for(int i=0;i<a.length;i++) {
								     for(int j=0;j<a.length-i-1;j++) {
								      if(a[j]>a[j+1]) {
								       temp=a[j];
								       a[j]=a[j+1];
								       a[j+1]=temp;
								      }
								     }
								    }//��ð�����򷨶�a[]���д�С��������
								String jj=Arrays.asList(a).toString();
								s=jj.substring(1, jj.length()-1).replaceAll(" ", "");//Ҫ����ĵ�ѡ�������
							}
							//********************��ѡ��*************************************
							String ds="";
							if(dxz>0){
								Integer da[] = new Integer[dxz];
								List<Integer> dxzth=this.adminservice.jdxzthd(zbh1, zbh2);
								List<Integer> dxtemp =new ArrayList<Integer>();
								int tsdx=dxzth.size();
								for (int di = 0;; di++) {
								    int d=(int)(Math.random()*tsdx);
								    if(dxtemp.size()==0){
								    	dxtemp.add(d);
								    }else if(!dxtemp.contains(d)){
								    	dxtemp.add(d);
								    }
									if(dxtemp.size()==dxz){
										break;
									}
								}
								for(int x=0;x<dxtemp.size();x++){
									da[x] =dxzth.get(dxtemp.get(x));
								}
								int tempd=0;
								for(int i=0;i<da.length;i++) {
								     for(int j=0;j<da.length-i-1;j++) {
								      if(da[j]>da[j+1]) {
								       tempd=da[j];
								       da[j]=da[j+1];
								       da[j+1]=tempd;
								      }
								     }
								    }//��ð�����򷨶�da[]���д�С��������
								//�����ѡ�����
								String dd=Arrays.asList(da).toString();
								ds=dd.substring(1, dd.length()-1).replaceAll(" ", "");//Ҫ����Ķ�ѡ�������
							}
							String hqlno="select count(*) from Sjnr";
							String hqlsj="select sjno from Sjnr order by sjno asc";
							List<Integer> sj=this.baseservice.findHql(Integer.class, hqlsj);
							int notemp=this.baseservice.getTotalSql(hqlno)+1;
							if(sj.size()==0){
								no=1;
							}else{
								for(int h=0;h<sj.size();h++){
									if(sj.get(h)!=h+1){
										no=h+1;
										break;
									}else{
										no=notemp;
									}
								}
							}
							sjnr.setSjno(no);
							if(!s.equals("")&!ds.equals("")){
								sjnr.setXzsz(s+","+ds);
							}else if(s.equals("")&!ds.equals("")){
								sjnr.setXzsz(ds);
							}else{
								sjnr.setXzsz(s);
							}
							
							//******************�ж��ⲿ��***********************	
							String sp="";
							if(pd>0){
								Integer b[] = new Integer[pd];
								String hqlpd="select th from Pd where zbh >= '"+zbh1+"' and zbh <= '"+zbh2+"' and tg!='' and da is not null";
								List<Integer> pdts=this.baseservice.findHql(Integer.class, hqlpd);
								List<Integer> pdtemp =new ArrayList<Integer>();
								int tsp=pdts.size();
								for (int j=0;;j++){
									int d=(int)(Math.random()*tsp);
									if(pdtemp.size()==0){
										pdtemp.add(d);
									}else if(!pdtemp.contains(d)){
										pdtemp.add(d);
									}
									if(pdtemp.size()==pd){
										break;
									}
								}
								for(int x=0;x<pdtemp.size();x++){
									b[x] =pdts.get(pdtemp.get(x));
								}
								int tempp=0;
								for(int i=0;i<b.length;i++) {
								     for(int j=0;j<b.length-i-1;j++) {
								      if(b[j]>b[j+1]) {
								    	  tempp=b[j];
								       b[j]=b[j+1];
								       b[j+1]=tempp;
								      }
								     }
								    }//��ð�����򷨶�b[]���д�С��������
								
								//�����ж������
								String pp=Arrays.asList(b).toString();
								sp=pp.substring(1, pp.length()-1).replaceAll(" ", "");//Ҫ������ж��������
							}
							sjnr.setPdsz(sp);
						//******************�����ⲿ��***********************	
							//���Ծ��š���ʼ�±�š���ֹ�±�Ŵ���session
							session.setAttribute("testpaperno",no);
							session.setAttribute("ghzno1",zbh1);
							session.setAttribute("ghzno2",zbh2);
							String cp="";
							if(cz>0){
								Integer c[]=new Integer[cz];
								int tsczt=czth.size();
								List<Integer> cztemp = new ArrayList<Integer>();
								for (int k=0;;k++){
									int d=(int)(Math.random()*tsczt);
									if(cztemp.size()==0){
										cztemp.add(d);
									}else if(!cztemp.contains(d)){
										cztemp.add(d);
									}
									if(cztemp.size()==cz){
										break;
									}
									}
								for(int x=0;x<cztemp.size();x++){
									c[x] =czth.get(cztemp.get(x));
								}
								for(int u=0;u<c.length;u++){
									cztd.add(this.adminservice.cztnr(c[u]));
								}

								int k=0;
								for (Integer sjcz:c){
//									System.out.println("Ҫ����Ĳ�������ǣ�"+sjcz);
									if(c.length-1>k){
										cp=cp+sjcz.toString();
										for(int x=0;x<cztd.size();x++){
											if(cztd.get(x).getDtth()==sjcz&&cztd.get(x).getXtth().size()>0){
												for(int y=0;y<cztd.get(x).getXtth().size();y++){
													cp=cp+"|"+cztd.get(x).getXtth().get(y);//С��֮����"|"����
												}
											}
										}
										cp=cp+",";//����֮���á���������
									}else{
										cp=cp+sjcz.toString();
										for(int x=0;x<cztd.size();x++){
											if(cztd.get(x).getDtth()==sjcz&&cztd.get(x).getXtth().size()>0){
												for(int y=0;y<cztd.get(x).getXtth().size();y++){
													cp=cp+"|"+cztd.get(x).getXtth().get(y);
												}
											}
										}
									}
									k=k+1;
								}
							}
							sjnr.setCztsz(cp);
							sjname=zxx1+"���ò���"+"("+String.valueOf(no)+")";
							session.setAttribute("testpapername",sjname);
							CourseChapter cc=new CourseChapter();
							cc.setZbh(zbh2);
							sjnr.setCourseChapter(cc);
							sjnr.setDq(false);
							sjnr.setCjsj(new Date());
							sjnr.setZxx(sjname);
							String tempid=(Integer)session.getAttribute("i_d")+"";
							if(tempid!=null&&!tempid.equals("")&&!tempid.equals("null")&&!tempid.equals("0")){
								Jxjh jxjh=new Jxjh();
								int jxjhid=(Integer)session.getAttribute("i_d");
								jxjh.setId(jxjhid);
								sjnr.setJxjh(jxjh);
								session.setAttribute("i_d", 0);
							}
							this.baseservice.save(sjnr);
						}else{
							tip="cz";
						}
					}else{
						tip="pd";
					}
				}else{
					tip="dxz";
				}
			}else{
				tip="xz";
			}
			
		}
		
		return SUCCESS;
		}	
	
	//���ʱ�� ,У���Ծ����Ƿ��Ѿ����ڡ�
	@Action(value="/beforezj",results={@Result(name="root",type="json")})
	public String beforezj(){
		if(sjname==null||sjname.equals("")){
			   tip="bbb";
			}else{
				List<Sjnr> rs = new ArrayList<Sjnr>();
				rs = this.baseservice.find(Sjnr.class);
				for(Sjnr r:rs){
					if(sjname.equals(r.getZxx())){
						tip = "aaa";
						break;
					}
				}
			}
		return "root";
	}	

	//�׶����ʱ��������������Χ�ڸ������͵�����
	@Action(value="/jyts2",results={@Result(name="root",type="json")})
	public String jyts2(){
		if(zbh1!=0&&zbh2!=0){
			List<Integer> czth=this.adminservice.searchcztth(zbh1, zbh2);
			String hql2="select count(*) from  Xz where zbh >= '"+zbh1+"' and zbh <= '"+zbh2+"' and ddx= '"+1+"'";
			String hql3="select count(*) from  Xz where zbh >= '"+zbh1+"' and zbh <= '"+zbh2+"' and ddx= '"+0+"'";
			String hql4="select count(*) from  Pd where zbh >= '"+zbh1+"' and zbh <= '"+zbh2+"'";
			total4=czth.size();//���������Ĳ���������
			total2=this.baseservice.getTotalSql(hql2);//���������Ķ�ѡ������
			total1=this.baseservice.getTotalSql(hql3);//���������ĵ�ѡ������
			total3=this.baseservice.getTotalSql(hql4);//�����������ж�������
			HttpSession session = ServletActionContext.getRequest().getSession();
			session.setAttribute("tt1",total1);
			session.setAttribute("tt2",total2);
			session.setAttribute("tt3",total3);
			session.setAttribute("tt4",total4);
			session.setAttribute("cztth",czth);
		}
		return "root";
	}	


//**********************�����Ծ������************************
	
	
	//����������
	@Action(value="/ghczttm",results={@Result(name="root",type="json")})
	public String ghczttm(){
		HttpSession hs = ServletActionContext.getRequest().getSession();
		int ghsjno=(Integer) hs.getAttribute("testpaperno");
		List<Integer> czth=(List<Integer>) hs.getAttribute("cztth");//ȡ�����������Ĳ�����������
		sjname=(String)hs.getAttribute("testpapername");
		
		//����Ծ��в�����������
		List<List> llc =this.adminservice.cksjczt(ghsjno);
		List<Integer> cztdtth=new ArrayList<Integer>();
 		for(int k=0;k<llc.size();k++){
 			if(!cztdtth.contains(Integer.parseInt(llc.get(k).get(2).toString()))){
 				cztdtth.add(Integer.parseInt(llc.get(k).get(2).toString()));
 			}
 		}

		List<Integer> th=new ArrayList<Integer>();
		for(int t=0;t<cztdtth.size();t++){
			if(cztdtth.get(t)!=ghth){
				th.add(cztdtth.get(t));
			}
		}
		//�Ծ���û�еġ�����Ҫ��Ĵ������
		List<Integer> cztth1=new ArrayList<Integer>();
		for(int i=0;i<czth.size();i++){
			if(!cztdtth.contains(czth.get(i))){
				cztth1.add(czth.get(i));
			}
		}
		List<Cztzj> cztd=new ArrayList<Cztzj>();
		if(cztth1.size()>0){
			int ts=cztth1.size();
			int d=(int)(Math.random()*ts);
			th.add(cztth1.get(d));
			for (int g=0;g<th.size();g++){
				cztd.add(this.adminservice.cztnr(th.get(g)));
			}
				
			String cp="";
			int k=0;
			for (Integer sjcz:th){
//				System.out.println("Ҫ����Ĳ�������ǣ�"+sjcz);
				if(th.size()-1>k){
					cp=cp+sjcz.toString();
					for(int x=0;x<cztd.size();x++){
						if(cztd.get(x).getDtth()==sjcz&&cztd.get(x).getXtth().size()>0){
							for(int y=0;y<cztd.get(x).getXtth().size();y++){
								cp=cp+"|"+cztd.get(x).getXtth().get(y);//С��֮����"|"����
							}
						}
					}
					cp=cp+",";//����֮���á���������
				}else{
					cp=cp+sjcz.toString();
					for(int x=0;x<cztd.size();x++){
						if(cztd.get(x).getDtth()==sjcz&&cztd.get(x).getXtth().size()>0){
							for(int y=0;y<cztd.get(x).getXtth().size();y++){
								cp=cp+"|"+cztd.get(x).getXtth().get(y);
							}
						}
					}
				}
				k=k+1;
			}
			
			String hqlsjnr="from Sjnr where sjno="+ghsjno;
			List<Sjnr> gxsjnr=this.baseservice.findHql(Sjnr.class, hqlsjnr);
			Sjnr ss=gxsjnr.get(0);
			ss.setCztsz(cp);//���������cztsz�ֶ�
			this.baseservice.update(ss);
		}else{
			tip="myt";
		}
		return "root";
	}
	
	//�����ж���	
	@Action(value="/ghpdtm",results={@Result(name="root",type="json")})
	public String ghpdtm(){
		
		HttpSession hs = ServletActionContext.getRequest().getSession();
		int ghsjno=(Integer) hs.getAttribute("testpaperno");
		sjname=(String)hs.getAttribute("testpapername");
		List<List> llp =this.adminservice.cksjpdt(ghsjno);
		List<Integer> pdtth=new ArrayList<Integer>();
 		for(int j=0;j<llp.size();j++){
 			pdtth.add(Integer.parseInt(llp.get(j).get(0).toString()));

 		}
		
			List<Pd> jdpdd = new ArrayList<Pd>();
			List<Integer> th=new ArrayList<Integer>();
			for(int t=0;t<pdtth.size();t++){
				if(pdtth.get(t)!=ghth){
					th.add(pdtth.get(t));
				}
			}
			if(!pdtth.contains(xinth)){
				th.add(xinth);
				int tempp=0;
				Integer iarr[]=new Integer[th.size()];
				for(int i=0;i<th.size();i++){ 
					iarr[i]=th.get(i); 
					} 
				for(int i=0;i<th.size();i++) {
				     for(int j=0;j<th.size()-i-1;j++) {
				      if(iarr[j]>iarr[j+1]) {
				    	  tempp=iarr[j];
				    	  iarr[j]=iarr[j+1];
				    	  iarr[j+1]=tempp;
				      }
				     }
				    }//��ð�����򷨶�b[]���д�С��������
				
				//�����ж������
				List<Integer> thp=Arrays.asList(iarr);//��������ת����list
				for (int g=0;g<thp.size();g++){
					jdpdd.add(this.adminservice.pdnr(thp.get(g)));
				}
				String sp="";
				String pp=thp.toString();
				sp=pp.substring(1, pp.length()-1).replaceAll(" ", "");//Ҫ������ж��������
				String hqlsjnr="from Sjnr where sjno="+ghsjno;
				List<Sjnr> gxsjnr=this.baseservice.findHql(Sjnr.class, hqlsjnr);
				Sjnr ss=gxsjnr.get(0);
				ss.setPdsz(sp);//����sjnr���pdsz�ֶ�
				this.baseservice.update(ss);
			}else{
				tip="yy";
			}
		return "root";
	}
	
	//������ѡ��
	@Action(value="/ghdanxtm",results={@Result(name="root",type="json")})
	public String ghdanxtm(){
		HttpSession hs = ServletActionContext.getRequest().getSession();
		int ghsjno=(Integer) hs.getAttribute("testpaperno");
		sjname=(String)hs.getAttribute("testpapername");
		List<Integer> danxth=new ArrayList<Integer>();
		String duox="";
		List<List> ll =this.adminservice.cksjxzt(ghsjno);
		for(int i=0;i<ll.size();i++){
			if(Integer.parseInt(ll.get(i).get(7).toString())==0){
				danxth.add(Integer.parseInt(ll.get(i).get(0).toString()));
			}else{
				duox=duox+ll.get(i).get(0).toString()+",";
			}
		}
			List<Xz> jdxzz = new ArrayList<Xz>();
			List<Integer> th=new ArrayList<Integer>();
			for(int j=0;j<danxth.size();j++){
				if(danxth.get(j)!=ghth){
					th.add(danxth.get(j));
				}
			}
			if(!danxth.contains(xinth)){
					th.add(xinth);
					int tempp=0;
					Integer iarr[]=new Integer[th.size()];
					for(int i=0;i<th.size();i++){ 
						iarr[i]=th.get(i); 
						} 
					for(int i=0;i<th.size();i++) {
					     for(int j=0;j<th.size()-i-1;j++) {
					      if(iarr[j]>iarr[j+1]) {
					    	  tempp=iarr[j];
					    	  iarr[j]=iarr[j+1];
					    	  iarr[j+1]=tempp;
					      }
					     }
					    }//��ð�����򷨶�b[]���д�С��������
					//����ѡ�������
					List<Integer> thp=Arrays.asList(iarr);//��������ת����list
					for(int h=0;h<thp.size();h++){
						jdxzz.add(this.adminservice.xznr(thp.get(h)));
					}
					String s="";
					String pp=thp.toString();
					s=pp.substring(1, pp.length()-1).replaceAll(" ", "");//Ҫ������ж��������
				String g="";
				if(!duox.equals("")){
					g=s+","+duox.substring(0, duox.length()-1);
				}else{
					g=s;
				}
				
				String hqlsjnr="from Sjnr where sjno="+ghsjno;
				List<Sjnr> gxsjnr=this.baseservice.findHql(Sjnr.class, hqlsjnr);
				Sjnr ss=gxsjnr.get(0);
				ss.setXzsz(g);//����sjnr���xzsz�ֶ�
				this.baseservice.update(ss);
				danxth=th;
				}else{
					tip="myt";
				}
		return "root";
	}
	
	//������ѡ��
	@Action(value="/ghduoxtm",results={@Result(name="root",type="json")})
	public String ghduoxtm(){
		HttpSession hs = ServletActionContext.getRequest().getSession();
		int ghsjno=(Integer) hs.getAttribute("testpaperno");
		sjname=(String)hs.getAttribute("testpapername");
		List<Integer> duoxth=new ArrayList<Integer>();
		String danx="";
		List<List> ll =this.adminservice.cksjxzt(ghsjno);
		for(int i=0;i<ll.size();i++){
			if(Integer.parseInt(ll.get(i).get(7).toString())==1){
				duoxth.add(Integer.parseInt(ll.get(i).get(0).toString()));
			}else{
				danx=danx+ll.get(i).get(0).toString()+",";
			}
		}
			List<Xz> jdxzz = new ArrayList<Xz>();
			List<Integer> th=new ArrayList<Integer>();
			for(int j=0;j<duoxth.size();j++){
				if(duoxth.get(j)!=ghth){
					th.add(duoxth.get(j));
				}
			}
			if(!duoxth.contains(xinth)){
					th.add(xinth);
					int tempp=0;
					Integer iarr[]=new Integer[th.size()];
					for(int i=0;i<th.size();i++){ 
						iarr[i]=th.get(i); 
						} 
					for(int i=0;i<th.size();i++) {
					     for(int j=0;j<th.size()-i-1;j++) {
					      if(iarr[j]>iarr[j+1]) {
					    	  tempp=iarr[j];
					    	  iarr[j]=iarr[j+1];
					    	  iarr[j+1]=tempp;
					      }
					     }
					    }//��ð�����򷨶�b[]���д�С��������
					
					//����ѡ�������
					List<Integer> thp=Arrays.asList(iarr);//��������ת����list
					for(int h=0;h<thp.size();h++){
						jdxzz.add(this.adminservice.xznr(thp.get(h)));
					}
					String s="";
					String pp=thp.toString();
					s=pp.substring(1, pp.length()-1).replaceAll(" ", "");//Ҫ������ж��������
					String g="";
					if(!danx.equals("")){
						g=danx.substring(0, danx.length()-1)+","+s;
					}else{
						g=s;
					}
					String hqlsjnr="from Sjnr where sjno="+ghsjno;
					List<Sjnr> gxsjnr=this.baseservice.findHql(Sjnr.class, hqlsjnr);
					Sjnr ss=gxsjnr.get(0);
					ss.setXzsz(g);//����sjnr���xzsz�ֶ�
					this.baseservice.update(ss);
				}else{
					tip="myt";
				}
		return "root";
	}
	
	//������֮����ʾ��ҳ��
	@Action(value = "/xztnr", results = { @Result(name = "success", location="/testxg/zjwc.jsp") })
	public String xztnr(){
		HttpSession hs = ServletActionContext.getRequest().getSession();
		int ghsjno=(Integer) hs.getAttribute("testpaperno");
		sjname=(String)hs.getAttribute("testpapername");
		List<List> ll =this.adminservice.cksjxzt(ghsjno);
		if(ll.size()>0){
			for(int i=0;i<ll.size();i++){
				Xz ckxzt=new Xz();
				ckxzt.setTh(Integer.parseInt(ll.get(i).get(0).toString()));
				ckxzt.setTg(ll.get(i).get(1).toString());
				ckxzt.setXx1(ll.get(i).get(2).toString());
				ckxzt.setXx2(ll.get(i).get(3).toString());
				ckxzt.setXx3(ll.get(i).get(4).toString());
				ckxzt.setXx4(ll.get(i).get(5).toString());
				ckxzt.setDa(ll.get(i).get(6).toString());
				ckxzt.setDdx(Integer.parseInt(ll.get(i).get(7).toString()));
				if(Integer.parseInt(ll.get(i).get(7).toString())==0){
					xz1.add(ckxzt);
				}else{
					dxz1.add(ckxzt);
				}
			}
		}
		
		
		List<List> llp =this.adminservice.cksjpdt(ghsjno);
		if(llp.size()>0){
			for(int j=0;j<llp.size();j++){
	 			Pd ckpdt=new Pd();
	 			ckpdt.setTg(llp.get(j).get(1).toString());
	 			ckpdt.setDa(Integer.parseInt(llp.get(j).get(2).toString()));
	 			ckpdt.setTh(Integer.parseInt(llp.get(j).get(0).toString()));
	 			pd1.add(ckpdt);

	 		}
		}
 		
 		
 		List<List> llc =this.adminservice.cksjczt(ghsjno);
 		List<cksjczt> ckc=new ArrayList<cksjczt>();
 		if(llc.size()>0){
 	 		for(int k=0;k<llc.size();k++){
 	 			cksjczt ckczt=new cksjczt();
 	 			ckczt.setSjcztth(llc.get(k).get(0).toString());//������С�����
 	 			ckczt.setSjczdttg(llc.get(k).get(1).toString());//������������
 	 			ckczt.setSjczdtth(llc.get(k).get(2).toString());//������������
 	 			ckczt.setSjczttg(llc.get(k).get(3).toString());//������С�����
 	 			ckc.add(ckczt);

 	 		}
 		}
 		if(ckc.size()>0){
 			for(int i=0;i<ckc.size();i++){
 				 List<String> xttg = new ArrayList<String>();
 				 List<Integer> xtth =new ArrayList<Integer>();
 				 for(int j=0;j<ckc.size();j++){
 					 if(ckc.get(i).getSjczdttg().equals(ckc.get(j).getSjczdttg())){
 						 //�Ѳ�������ͬһ�����С����ɡ�С����ŷֱ����xttg��xtth����
 							 xttg.add(ckc.get(j).getSjczttg());
 							 xtth.add(Integer.parseInt(ckc.get(j).getSjcztth()));
 					 }
 				 }
 				Cztzj cc=new Cztzj();
 				cc.setDttg(ckc.get(i).getSjczdttg());
 				cc.setDtth(Integer.parseInt(ckc.get(i).getSjczdtth()));
 				cc.setXttg(xttg);
 				cc.setXtth(xtth);
 				if(czt1.size()==0){
 					czt1.add(cc);
 				}else{
 					List<String> tg = new ArrayList<String>();
 					for(int n=0;n<czt1.size();n++){
 						tg.add(czt1.get(n).getDttg());
 					}
 					if(!tg.contains(ckc.get(i).getSjczdttg())){//ȡ����������ظ�������
 						czt1.add(cc);
 					}
 				}
 			 }
 		}
		return SUCCESS;
	}

	
	//����ɾ���Ծ�
	@Action(value = "/delsj", results = { @Result(name = "success", type="json") })
	public String delsj(){
		if(sjbh2!=null&&!sjbh2.equals("")){
			int sjbh1=Integer.parseInt(sjbh2);
			String hql1="select sjno from Sjfx";
			List<Integer> sjfx=this.baseservice.findHql(Integer.class, hql1);
			String hql2="select distinct id.sjno from Xsdyjl";
			List<Integer> xsdyjl=this.baseservice.findHql(Integer.class, hql2);
			if(sjfx.size()==0&&xsdyjl.size()==0){
				sjno=(Sjnr) this.baseservice.find(Sjnr.class, sjbh1);
				this.baseservice.delete(sjno);
				tip="k";
			}else{
				if(!sjfx.contains(sjbh1)||sjfx.size()==0){
					if(!xsdyjl.contains(sjbh1)||xsdyjl.size()==0){
						sjno=(Sjnr) this.baseservice.find(Sjnr.class,sjbh1);
						this.baseservice.delete(sjno);
						tip="k";
					}else{
						tip="b";
					}
				}else{
					tip="b";
				}
			}
		}
		return SUCCESS;
	}
	
	
	//****************************�鿴�Ծ�**********************
	
	//��ʦ��ѧ���鿴�Ծ�ʱ�Լ������Ծ�ʱ���ݴ��������Ծ��������Ծ���������Ծ������
	@Action(value = "/sjxztnr", results = { @Result(name = "success", type="json" )})
	public String sjxztnr(){
		HttpSession session = ServletActionContext.getRequest().getSession();
		if(sjname!=null&&sjbh2!=null&&!sjname.equals("")&&!sjbh2.equals("")){
			no=Integer.parseInt(sjbh2);
		}
		session.setAttribute("testpapername",sjname);
		List<List> ll =this.adminservice.cksjxzt(no);
		List<Xz> ckx=new ArrayList<Xz>();
		for(int i=0;i<ll.size();i++){
			Xz ckxzt=new Xz();
			ckxzt.setTh(Integer.parseInt(ll.get(i).get(0).toString()));
			ckxzt.setTg(ll.get(i).get(1).toString());
			ckxzt.setXx1(ll.get(i).get(2).toString());
			ckxzt.setXx2(ll.get(i).get(3).toString());
			ckxzt.setXx3(ll.get(i).get(4).toString());
			ckxzt.setXx4(ll.get(i).get(5).toString());
			ckxzt.setDa(ll.get(i).get(6).toString());
			ckxzt.setDdx(Integer.parseInt(ll.get(i).get(7).toString()));
			ckx.add(ckxzt);
		}
    	List<List> llp =this.adminservice.cksjpdt(no);
 		List<Pd> ckp=new ArrayList<Pd>();
 		for(int j=0;j<llp.size();j++){
 			Pd ckpdt=new Pd();
 			ckpdt.setTg(llp.get(j).get(1).toString());
 			ckpdt.setDa(Integer.parseInt(llp.get(j).get(2).toString()));
 			ckpdt.setTh(Integer.parseInt(llp.get(j).get(0).toString()));
 			ckp.add(ckpdt);

 		}
 		List<List> llc =this.adminservice.cksjczt(no);
 		List<cksjczt> ckc=new ArrayList<cksjczt>();
 		for(int k=0;k<llc.size();k++){
 			cksjczt ckczt=new cksjczt();
 			ckczt.setSjcztth(llc.get(k).get(0).toString());//������С�����
 			ckczt.setSjczdttg(llc.get(k).get(1).toString());//������������
 			ckczt.setSjczdtth(llc.get(k).get(2).toString());//������������
 			ckczt.setSjczttg(llc.get(k).get(3).toString());//������С�����
 			ckc.add(ckczt);

 		}
 		session.setAttribute("testpaperno",no);//���ڰѲ��Ե��Ծ��ű�����ѧ�������¼����
		session.setAttribute("begantime",new Date());//���⿪ʼʱ��
		return SUCCESS;
	}
	
	
	//�鿴�Ծ�ҳ��(�Ծ�����Լ�ѧ����������Ĳ鿴�Ծ���ʱ�鿴û�д�)
	@Action(value = "/sj", results = { @Result(name = "success",location="/testxg/cksj.jsp") })
	public String sj(){
		HttpSession hs = ServletActionContext.getRequest().getSession();
		int ghsjno=(Integer) hs.getAttribute("testpaperno");
		sjname= (String)hs.getAttribute("testpapername");
		List<List> ll =this.adminservice.cksjxzt(ghsjno);
		List<Xz> ckx=new ArrayList<Xz>();
		for(int i=0;i<ll.size();i++){
			Xz ckxzt=new Xz();
			ckxzt.setTh(Integer.parseInt(ll.get(i).get(0).toString()));
			ckxzt.setTg(ll.get(i).get(1).toString());
			ckxzt.setXx1(ll.get(i).get(2).toString());
			ckxzt.setXx2(ll.get(i).get(3).toString());
			ckxzt.setXx3(ll.get(i).get(4).toString());
			ckxzt.setXx4(ll.get(i).get(5).toString());
			ckxzt.setDa(ll.get(i).get(6).toString());
			ckxzt.setDdx(Integer.parseInt(ll.get(i).get(7).toString()));
			ckx.add(ckxzt);
		}
    	List<List> llp =this.adminservice.cksjpdt(ghsjno);
 		List<Pd> ckp=new ArrayList<Pd>();
 		for(int j=0;j<llp.size();j++){
 			Pd ckpdt=new Pd();
 			ckpdt.setTg(llp.get(j).get(1).toString());
 			ckpdt.setDa(Integer.parseInt(llp.get(j).get(2).toString()));
 			ckpdt.setTh(Integer.parseInt(llp.get(j).get(0).toString()));
 			ckp.add(ckpdt);

 		}
 		List<List> llc =this.adminservice.cksjczt(ghsjno);
 		List<cksjczt> ckc=new ArrayList<cksjczt>();
 		for(int k=0;k<llc.size();k++){
 			cksjczt ckczt=new cksjczt();
 			ckczt.setSjcztth(llc.get(k).get(0).toString());//������С�����
 			ckczt.setSjczdttg(llc.get(k).get(1).toString());//������������
 			ckczt.setSjczdtth(llc.get(k).get(2).toString());//������������
 			ckczt.setSjczttg(llc.get(k).get(3).toString());//������С�����
 			ckc.add(ckczt);

 		}
	for(int i=0;i<ckx.size();i++){
		if(ckx.get(i).getDdx()==0){
			ckxz1.add(ckx.get(i));//����ǵ�ѡ�����ckxz1����
		}else if(ckx.get(i).getDdx()==1){
			ckxz2.add(ckx.get(i));//����Ƕ�ѡ�������ckxz2����
		}
	}
		ckpd1=ckp;
		for(int i=0;i<ckc.size();i++){
			 List<String> xttg = new ArrayList<String>();
			 List<Integer> xtth =new ArrayList<Integer>();
			 for(int j=0;j<ckc.size();j++){
				 if(ckc.get(i).getSjczdttg().equals(ckc.get(j).getSjczdttg())){
					 //�Ѳ�������ͬһ�����С����ɡ�С����ŷֱ����xttg��xtth����
						 xttg.add(ckc.get(j).getSjczttg());
						 xtth.add(Integer.parseInt(ckc.get(j).getSjcztth()));
				 }
			 }
			Cztzj cc=new Cztzj();
			cc.setDttg(ckc.get(i).getSjczdttg());
			cc.setDtth(Integer.parseInt(ckc.get(i).getSjczdtth()));
			cc.setXttg(xttg);
			cc.setXtth(xtth);
			if(czt1.size()==0){
				czt1.add(cc);
			}else{
				List<String> tg = new ArrayList<String>();
				for(int n=0;n<czt1.size();n++){
					tg.add(czt1.get(n).getDttg());
				}
				if(!tg.contains(ckc.get(i).getSjczdttg())){//ȡ����������ظ�������
					czt1.add(cc);
				}
			}
		 }
		return SUCCESS;
	}
	


	//�����Ծ�ҳ��
	@Action(value = "/csj", results = { @Result(name = "success",location="/testxg/cssj.jsp") })
	public String csj(){
		HttpSession hs = ServletActionContext.getRequest().getSession();
		int ghsjno=(Integer) hs.getAttribute("testpaperno");
		sjname= (String)hs.getAttribute("testpapername");
		List<List> ll =this.adminservice.cksjxzt(ghsjno);
		List<Xz> ckx=new ArrayList<Xz>();
		for(int i=0;i<ll.size();i++){
			Xz ckxzt=new Xz();
			ckxzt.setTh(Integer.parseInt(ll.get(i).get(0).toString()));
			ckxzt.setTg(ll.get(i).get(1).toString());
			ckxzt.setXx1(ll.get(i).get(2).toString());
			ckxzt.setXx2(ll.get(i).get(3).toString());
			ckxzt.setXx3(ll.get(i).get(4).toString());
			ckxzt.setXx4(ll.get(i).get(5).toString());
			ckxzt.setDa(ll.get(i).get(6).toString());
			ckxzt.setDdx(Integer.parseInt(ll.get(i).get(7).toString()));
			ckx.add(ckxzt);
		}
    	List<List> llp =this.adminservice.cksjpdt(ghsjno);
 		List<Pd> ckp=new ArrayList<Pd>();
 		for(int j=0;j<llp.size();j++){
 			Pd ckpdt=new Pd();
 			ckpdt.setTg(llp.get(j).get(1).toString());
 			ckpdt.setDa(Integer.parseInt(llp.get(j).get(2).toString()));
 			ckpdt.setTh(Integer.parseInt(llp.get(j).get(0).toString()));
 			ckp.add(ckpdt);

 		}
 		List<List> llc =this.adminservice.cksjczt(ghsjno);
 		List<cksjczt> ckc=new ArrayList<cksjczt>();
 		for(int k=0;k<llc.size();k++){
 			cksjczt ckczt=new cksjczt();
 			ckczt.setSjcztth(llc.get(k).get(0).toString());//������С�����
 			ckczt.setSjczdttg(llc.get(k).get(1).toString());//������������
 			ckczt.setSjczdtth(llc.get(k).get(2).toString());//������������
 			ckczt.setSjczttg(llc.get(k).get(3).toString());//������С�����
 			ckc.add(ckczt);

 		}
		if(ckx.size()>0){
			for(int i=0;i<ckx.size();i++){
				if(ckx.get(i).getDdx()==0){
					ckxz1.add(ckx.get(i));//����ǵ�ѡ�����ckxz1����
				}else if(ckx.get(i).getDdx()==1){
					ckxz2.add(ckx.get(i));//����Ƕ�ѡ�������ckxz2����
				}
			}
		}
		
		ckpd2=ckp;
		if(ckc.size()>0){
			for(int i=0;i<ckc.size();i++){
				 List<String> xttg = new ArrayList<String>();
				 List<Integer> xtth =new ArrayList<Integer>();
				 for(int j=0;j<ckc.size();j++){
					 if(ckc.get(i).getSjczdttg().equals(ckc.get(j).getSjczdttg())){
						 //�Ѳ�������ͬһ�����С����ɡ�С����ŷֱ����xttg��xtth����
							 xttg.add(ckc.get(j).getSjczttg());
							 xtth.add(Integer.parseInt(ckc.get(j).getSjcztth()));
					 }
				 }
				Cztzj cc=new Cztzj();
				cc.setDttg(ckc.get(i).getSjczdttg());
				cc.setDtth(Integer.parseInt(ckc.get(i).getSjczdtth()));
				cc.setXttg(xttg);
				cc.setXtth(xtth);
				if(czt1.size()==0){
					czt1.add(cc);
				}else{
					List<String> tg = new ArrayList<String>();
					for(int n=0;n<czt1.size();n++){
						tg.add(czt1.get(n).getDttg());
					}
					if(!tg.contains(ckc.get(i).getSjczdttg())){//ȡ����������ظ�������
						czt1.add(cc);
					}
				}
			 }
		}
		
		return SUCCESS;
	}
	
	//�����Բ�����Դ�������з���
	@Action(value="/cztfxczt",results={@Result(name="root",type="json")})
	public String cztfxczt(){
		HttpSession hs = ServletActionContext.getRequest().getSession();
		String userid=(String) hs.getAttribute("uid");
		int ghsjno=(Integer) hs.getAttribute("testpaperno");
		String hql="select count(*) from Xsdyjl where id.userId='"+userid+"' and id.sjno='"+ghsjno+"'";
		int sl=this.baseservice.getTotalSql(hql);
		if(sl==0){
			int ts=0;
			int score1=0;
			String[] s1=null;
			String[] s2=null;
			String cw="";//�������������
			String cztdtqk="";//����������������
			if(dcqk.size()>0&&!dcqk.get(0).equals("")){
				total2=dcqk.size();
				for(int i=0;i<dcqk.size();i++){
					String ss=(String)this.dcqk.get(i);
					int start=ss.indexOf("|");//��һ��"|"��λ��
					int last=ss.lastIndexOf("|");//�ڶ���"|"��λ��
					String xt=ss.substring(start+1, last);//��ȡ��С�����
					String dc = ss.substring(last+1, 2*last-start);//��ȡ��С��ĶԴ����
					s1=xt.split(",");//�ѽس���С������ַ���ת�����ַ�������
					s2=dc.split(",");//�ѽس���С��Դ�����ַ���ת�����ַ�������
					
					cztdtqk=cztdtqk+ss.substring(0, start)+"|"+dc.replaceAll(",", "|")+",";//����������������
					ts=ts+s1.length;//������С�� ����
					for(int j=0;j<s1.length;j++){
						if(s2[j].equals("F")){
							cw=cw+ss.substring(0, start)+"|"+s1[j]+",";
						}else{
							score1=score1+1;//��ȷ�ĸ���
						}
					}
				}
			}
			//����ѧ�������¼
			//�жϲ�����Ե�����ʦ����ѧ�����������ʦ�򲻱�������¼�����򱣴�
			String hqlid="select distinct type from Userinfo where UserId='"+userid+"'";
			String type="";
			type=this.baseservice.findHql(String.class, hqlid).get(0);
			int no2=(Integer) hs.getAttribute("testpaperno");
			if(!"T".equals(type)){
				Date b_time=(Date)hs.getAttribute("begantime");
				XsdyjlId id1=new XsdyjlId();
				id1.setSjno(no2);
				id1.setUserId(userid);
				Xsdyjl xx=new Xsdyjl();
				xx.setBTime(b_time);
				if(cw.length()>0){
					xx.setCztcwsz(cw.substring(0, cw.length()-1));
				}else{
					xx.setCztcwsz("");
				}
				if(cztdtqk.length()>0){
					xx.setCztsz(cztdtqk.substring(0, cztdtqk.length()-1));
				}else{
					xx.setCztsz("");
				}
				xx.setId(id1);
				this.baseservice.save(xx);
			}
			hs.setAttribute("score", score1);//��Ų�����ķ���
			hs.setAttribute("czts", ts);//��Ų������ܵ�С������
		}else{
			tip="yc";
		}
		
		return "root";
	}

	/**
	 * @{fx.action}
	 * �÷���������ָ������ύ����ת����ҳ���ǽ������ҳ�档
	 * ʵ�����ֹ���
	 */
	
	//�������Խ�������ѽ���������ݿ�
	@Action(value="/fx",results={@Result(name="success",type="json")})
	public String fx(){		
		
		HttpSession hs = ServletActionContext.getRequest().getSession();
		String userid=(String) hs.getAttribute("uid");
		int ghsjno=(Integer) hs.getAttribute("testpaperno");
		List<List> ll =this.adminservice.cksjxzt(ghsjno);
		List<Xz> ckx=new ArrayList<Xz>();
		for(int i=0;i<ll.size();i++){
			Xz ckxzt=new Xz();
			ckxzt.setTh(Integer.parseInt(ll.get(i).get(0).toString()));
			ckxzt.setTg(ll.get(i).get(1).toString());
			ckxzt.setXx1(ll.get(i).get(2).toString());
			ckxzt.setXx2(ll.get(i).get(3).toString());
			ckxzt.setXx3(ll.get(i).get(4).toString());
			ckxzt.setXx4(ll.get(i).get(5).toString());
			ckxzt.setDa(ll.get(i).get(6).toString());
			ckxzt.setDdx(Integer.parseInt(ll.get(i).get(7).toString()));
			ckx.add(ckxzt);
		}
    	List<List> llp =this.adminservice.cksjpdt(ghsjno);
 		List<Pd> ckp=new ArrayList<Pd>();
 		for(int j=0;j<llp.size();j++){
 			Pd ckpdt=new Pd();
 			ckpdt.setTg(llp.get(j).get(1).toString());
 			ckpdt.setDa(Integer.parseInt(llp.get(j).get(2).toString()));
 			ckpdt.setTh(Integer.parseInt(llp.get(j).get(0).toString()));
 			ckp.add(ckpdt);

 		}
 	
		for(int i=0;i<ckx.size();i++){
			if(ckx.get(i).getDdx()==0){
				ckxz1.add(ckx.get(i));//����ǵ�ѡ�����ckxz1����
			}else if(ckx.get(i).getDdx()==1){
				ckxz2.add(ckx.get(i));//����Ƕ�ѡ�������ckxz2����
			}
		}
		//�ύ�� 
		hs.setAttribute("AnsweR", answer);
		hs.setAttribute("AnsweR1", answer1);
		hs.setAttribute("AnsweR2", answer2);
		int scoretempx =0;
		String dxsz1="";//�Դ�����
		String dxsz2="";//�������
		String dxsz3="";
		String dxsz4="";
		//��ѡ���ύ��
		if(ckxz2.size()>0){
			if(answer.size()>0){
				for(int d=0;d<answer.size();d++){//answer.size()�����Ķ�ѡ�����Ŀ��
					String strResult="";
					if(answer.get(d)!=null){
						int i = answer.get(d).toString().length();//����iΪ3*[����ĳ��ȣ���Ԫ�صĸ���������ÿ����ѡ�еĸ�����]		
						for(int a=0;a<i/3;a++){
							strResult+=answer.get(d).get(a);//��answer�еĵ�b��Ԫ�����a����������������	
						}				
						String an = strResult;//��ʱ����forѭ���⣩��strResult����b������Ԫ�ص����ӡ�
						//�������÷���
						if(an.equals(da.get(d))){
							scoretempx=scoretempx+1; //�������ȷ��������һ.
						}else{
							dxsz4=dxsz4+ckxz2.get(d).getTh().toString()+",";
						}
						dxsz3=dxsz3+an+",";
					}else if(answer.get(d)==null){
						dxsz3=dxsz3+""+",";
						dxsz4=dxsz4+ckxz2.get(d).getTh().toString()+",";
					}
				}
				if(answer.size()==ckxz2.size()){
					if(dxsz3.length()>0){
						dxsz1=dxsz3.substring(0, dxsz3.length()-1);
					}else{
						dxsz1=dxsz3;
					}
					if(dxsz4.length()>0){
						dxsz2=dxsz4.substring(0, dxsz4.length()-1);
					}else{
						dxsz2=dxsz4;
					}
					
				}else{
					if(ckxz2.size()-answer.size()==1){
						dxsz1=dxsz3;
					}else{
						dxsz1=dxsz3;
						for(int h=0;h<ckxz2.size()-answer.size()-1;h++){
							dxsz1=dxsz1+",";
						}
					}
					for(int z=answer.size();z<ckxz2.size();z++){
						dxsz4=dxsz4+ckxz2.get(z).getTh()+",";
					}
					if(dxsz4.length()>0){
						dxsz2=dxsz4.substring(0, dxsz4.length()-1);
					}else{
						dxsz2=dxsz4;
					}
					
					
				}
			}else{//һ����ѡ��Ҳû������������;�����Ծ�����û�ж�ѡ��
					for(int i=0;i<ckxz2.size();i++){
						dxsz3=dxsz3+""+",";
						if(i<ckxz2.size()-1){
							dxsz2=dxsz2+ckxz2.get(i).getTh().toString()+",";
						}else{
							dxsz2=dxsz2+ckxz2.get(i).getTh().toString();
						}
					}
				if(dxsz3.length()>0){
					dxsz1=dxsz3.substring(0, dxsz3.length()-1);
				}
			}
			
		}
		
		String danx="";//��ѡ�Դ�����
		String danx1="";
		String danxcw="";//��ѡ��������
		String danxcw1="";
		int scoretempdan=0;
		//��ѡ���ύ��
		if(ckxz1.size()>0){
			if(answer1.size()>0){
				for(int i=0;i<answer1.size();i++){
					if(answer1.get(i)!=null){
						if(answer1.get(i).get(0).equals(da1.get(i))){
							scoretempdan=scoretempdan+1;
						}else{
							danxcw1=danxcw1+ckxz1.get(i).getTh().toString()+",";
						}
						danx1=danx1+answer1.get(i).get(0)+",";
					}else if(answer1.get(i)==null){
						danxcw1=danxcw1+ckxz1.get(i).getTh().toString()+",";
						danx1=danx1+""+",";
					}
				}
				if(answer1.size()==ckxz1.size()){
					if(danx1.length()>0){
						danx=danx1.substring(0, danx1.length()-1);
					}
					if(danxcw1.length()>0){
						danxcw=danxcw1.substring(0, danxcw1.length()-1);
					}
				
				}else{
					if(ckxz1.size()-answer1.size()==1){
						danx=danx1;
					}else{
						danx=danx1;
						for(int h=0;h<ckxz1.size()-answer1.size()-1;h++){
							danx=danx+",";
						}
						for(int z=answer1.size();z<ckxz1.size();z++){
							danxcw1=danxcw1+ckxz1.get(z).getTh().toString()+",";
						}
						if(danxcw1.length()>0){
							danxcw=danxcw1.substring(0, danxcw1.length()-1);
						}else{
							danxcw=danxcw1;
						}
						
					}
				}
			}else{//һ����ѡҲû������������;�������Ծ���û�е�ѡ��
					for(int j=0;j<ckxz1.size();j++){
						danx1=danx1+""+",";
						if(j<ckxz1.size()-1){
							danxcw=danxcw+ckxz1.get(j).getTh().toString()+",";
						}else{
							danxcw=danxcw+ckxz1.get(j).getTh().toString();
						}
					}
				if(danx1.length()>0){
					danx=danx1.substring(0, danx1.length()-1);
				}
			}
		}
		
		String xcwsz="";
		if(danxcw!=""&&dxsz2!=""){
			xcwsz=danxcw+","+dxsz2;
		}
		if(danxcw!=""&&dxsz2==""){
			xcwsz=danxcw;
		}
		if(danxcw==""&&dxsz2!=""){
			xcwsz=dxsz2;
		}
		String xzsz="";
		if(danx.equals("")&&!dxsz1.equals("")){
			xzsz=dxsz1;
		}else if(!danx.equals("")&&!dxsz1.equals("")){
			xzsz=danx+","+dxsz1;
		}else if(!danx.equals("")&&dxsz1.equals("")){
			xzsz=danx;
		}
		
		//�ж����ύ��
		int scoretemp=0;
		String temppdcw="";//��������
		String temppd="";//�Դ��������
		String temppd1="";
		String temppdcw1="";
		if(ckp.size()>0){
			if(answer2.size()>0){
				for(int j=0;j<answer2.size();j++){
					if(answer2.get(j)!=null){
						if(answer2.get(j).get(0).equals(da2.get(j))){
							scoretemp=scoretemp+1;
						}else{
							temppdcw1=temppdcw1+ckp.get(j).getTh().toString()+",";
						}
						temppd1=temppd1+answer2.get(j).get(0)+",";
					}else{
						temppdcw1=temppdcw1+ckp.get(j).getTh().toString()+",";
						temppd1=temppd1+""+",";
					}
				}
				if(answer2.size()==ckp.size()){
					if(temppd1.length()>0){
						temppd=temppd1.substring(0, temppd1.length()-1);
					}
					if(temppdcw1.length()>0){
						temppdcw=temppdcw1.substring(0, temppdcw1.length()-1);
					}
					
				}else{
					if(ckp.size()-answer2.size()==1){
						temppd=temppd1;
					}else{
						temppd=temppd1;
						for(int h=0;h<ckp.size()-answer2.size()-1;h++){
							temppd=temppd+",";
						}
					}
					for(int z=answer2.size();z<ckp.size();z++){
						temppdcw1=temppdcw1+ckp.get(z).getTh().toString()+",";
					}
					if(temppdcw1.length()>0){
						temppdcw=temppdcw1.substring(0, temppdcw1.length()-1);
					}
				
					
				}
			}else{//һ���ж���Ҳû������������
				for(int n=0;n<ckp.size();n++){
					temppd1=temppd1+""+",";
					if(n<ckp.size()-1){
						temppdcw=temppdcw+ckp.get(n).getTh().toString()+",";
					}else{
						temppdcw=temppdcw+ckp.get(n).getTh().toString();
					}
				}
				if(temppd1.length()>0){
					temppd=temppd1.substring(0, temppd1.length()-1);
				}
			}
		}
		
		int score1=(Integer)hs.getAttribute("score");
		int czts1=(Integer)hs.getAttribute("czts");
		total1=ckx.size()+ckp.size()+czts1;//�ܵ�����
		int zql=Integer.parseInt(new java.text.DecimalFormat("0").format((score1+scoretempdan+scoretempx+scoretemp)*100/total1));//�ó���ȷ��
		
		//�жϲ�����Ե�����ʦ����ѧ�����������ʦ�򲻱�������¼�����򱣴�
		String hqlid="select distinct type from Userinfo where UserId='"+userid+"'";
		String type="";
		type=this.baseservice.findHql(String.class, hqlid).get(0);
		int no2=(Integer) hs.getAttribute("testpaperno");
		if(!"T".equals(type)){
			List<Xsdyjl> xsdtjl=this.baseservice.findHql(Xsdyjl.class, "from Xsdyjl where id.userId='"+userid+"' and id.sjno="+no2 );
			if(xsdtjl.size()>0){
				Xsdyjl xx=xsdtjl.get(0);
				xx.setETime(new Date());
				xx.setCwpdsz(temppdcw);
				xx.setCwxzsz(xcwsz);
				xx.setPd(temppd);
				xx.setXzsz(xzsz);
				this.baseservice.update(xx);
			}else{
				Xsdyjl xx=new Xsdyjl();
				xx.setETime(new Date());
				xx.setCwpdsz(temppdcw);
				xx.setCwxzsz(xcwsz);
				xx.setPd(temppd);
				xx.setXzsz(xzsz);
				Date b_time=(Date)hs.getAttribute("begantime");
				XsdyjlId id1=new XsdyjlId();
				id1.setSjno(ghsjno);
				id1.setUserId(userid);
				xx.setBTime(b_time);
				this.baseservice.save(xx);
			}
		}
		hs.setAttribute("zqlfx", zql);
		return SUCCESS; 
	}
	
	
	//�����Ծ���ɺ���ʾ����
	@Action(value="/dhxs",results={@Result(name="success",location="/testxg/testjg.jsp")})
	public String dhxs(){
		HttpSession hs = ServletActionContext.getRequest().getSession();
		int zqbfb=(Integer)hs.getAttribute("zqlfx");
		if(zqbfb>85){
			tip="a";
		}else if(zqbfb>75){
			tip="b";
		}else if(zqbfb>60){
			tip="c";
		}else if(zqbfb>30){
			tip="d";
		}else{
			tip="e";
		}
		return SUCCESS;
	}
	
	//�������֮����Կ��Լ��Ĵ��Լ���ȷ��ִ�е�action
	@Action(value = "/finishtest", results = { @Result(name = "success",location="/testxg/finishtest.jsp") })
	public String finishtest(){
		
		HttpSession hs = ServletActionContext.getRequest().getSession();
		int ghsjno=(Integer) hs.getAttribute("testpaperno");
		sjname= (String)hs.getAttribute("testpapername");
		List<List> Answer=(List<List>)hs.getAttribute("AnsweR");
		List<List> Answer1=(List<List>)hs.getAttribute("AnsweR1");
		List<List> Answer2=(List<List>)hs.getAttribute("AnsweR2");
		List<List> ll =this.adminservice.cksjxzt(ghsjno);
		List<Xz> ckx=new ArrayList<Xz>();
		for(int i=0;i<ll.size();i++){
			Xz ckxzt=new Xz();
			ckxzt.setTh(Integer.parseInt(ll.get(i).get(0).toString()));
			ckxzt.setTg(ll.get(i).get(1).toString());
			ckxzt.setXx1(ll.get(i).get(2).toString());
			ckxzt.setXx2(ll.get(i).get(3).toString());
			ckxzt.setXx3(ll.get(i).get(4).toString());
			ckxzt.setXx4(ll.get(i).get(5).toString());
			ckxzt.setDa(ll.get(i).get(6).toString());
			ckxzt.setDdx(Integer.parseInt(ll.get(i).get(7).toString()));
			ckx.add(ckxzt);
		}
    	List<List> llp =this.adminservice.cksjpdt(ghsjno);
 		List<Pd> ckp=new ArrayList<Pd>();
 		for(int j=0;j<llp.size();j++){
 			Pd ckpdt=new Pd();
 			ckpdt.setTg(llp.get(j).get(1).toString());
 			ckpdt.setDa(Integer.parseInt(llp.get(j).get(2).toString()));
 			ckpdt.setTh(Integer.parseInt(llp.get(j).get(0).toString()));
 			ckp.add(ckpdt);

 		}
 		List<List> llc =this.adminservice.cksjczt(ghsjno);
 		List<cksjczt> ckc=new ArrayList<cksjczt>();
 		for(int k=0;k<llc.size();k++){
 			cksjczt ckczt=new cksjczt();
 			ckczt.setSjcztth(llc.get(k).get(0).toString());//������С�����
 			ckczt.setSjczdttg(llc.get(k).get(1).toString());//������������
 			ckczt.setSjczdtth(llc.get(k).get(2).toString());//������������
 			ckczt.setSjczttg(llc.get(k).get(3).toString());//������С�����
 			ckc.add(ckczt);

 		}
 		for(int i=0;i<ckx.size();i++){
			if(ckx.get(i).getDdx()==0){
				ckxz1.add(ckx.get(i));//����ǵ�ѡ�����ckxz1����
			}else if(ckx.get(i).getDdx()==1){
				ckxz2.add(ckx.get(i));//����Ƕ�ѡ�������ckxz2����
			}
		}
		String a1[] = new String[ckxz1.size()];
		String b1[] = new String[ckxz2.size()];
		String c1[] = new String[ckp.size()];
		if(Answer1.size()>0){
			for(int i=0;i<Answer1.size();i++){
				if(Answer1.get(i)!=null){
					a1[i]=(Answer1.get(i).get(0).toString());
				}
			}
		}
		if(Answer.size()>0){
			for(int i=0;i<Answer.size();i++){
				String strResult="";
				if(Answer.get(i)!=null){
					for(int j=0;j< Answer.get(i).size();j++){
						strResult+=Answer.get(i).get(j);//��answer�еĵ�b��Ԫ�����a����������������	
					}
					b1[i]=strResult;
				}
			}
		}
		if(Answer2.size()>0){
			for(int i=0;i<Answer2.size();i++){
				if(Answer2.get(i)!=null){
					c1[i]=Answer2.get(i).get(0).toString();
				}
			}
		}
		for(int i=0;i<ckxz1.size();i++){
				Xz xzt=new Xz();
				xzt.setDa(ckxz1.get(i).getDa());
				xzt.setTg(ckxz1.get(i).getTg());
				xzt.setXx1(ckxz1.get(i).getXx1());
				xzt.setXx2(ckxz1.get(i).getXx2());
				xzt.setXx3(ckxz1.get(i).getXx3());
				xzt.setXx4(ckxz1.get(i).getXx4());
				if(a1[i]==null){
					xzt.setMd5("����û����");//��ʱ������Ų����ߵĴ�
				}else{
					xzt.setMd5(a1[i]);//��ʱ������Ų����ߵĴ�
				}
				testxz1.add(xzt);//��ѡ
			}
		for(int i=0;i<ckxz2.size();i++){
			Xz xzt=new Xz();
			xzt.setDa(ckxz2.get(i).getDa());
			xzt.setTg(ckxz2.get(i).getTg());
			xzt.setXx1(ckxz2.get(i).getXx1());
			xzt.setXx2(ckxz2.get(i).getXx2());
			xzt.setXx3(ckxz2.get(i).getXx3());
			xzt.setXx4(ckxz2.get(i).getXx4());
			if(b1[i]==null){
				xzt.setMd5("����û����");//��ʱ������Ų����ߵĴ�
			}else{
				xzt.setMd5(b1[i]);//��ʱ������Ų����ߵĴ�
			}
			testxz2.add(xzt);//��ѡ
		}
		for(int i=0;i<ckp.size();i++){
			Pd pdt =new Pd();
			pdt.setDa(ckp.get(i).getDa());
			pdt.setTg(ckp.get(i).getTg());
			if(c1[i]==null){
				pdt.setMd5("����û����");//��ʱ������Ų����ߵĴ�
			}else{
				pdt.setMd5(c1[i]);//��ʱ������Ų����ߵĴ�
			}
			testpd1.add(pdt);
		}
		for(int i=0;i<ckc.size();i++){
			 List<String> xttg = new ArrayList<String>();
			 List<Integer> xtth =new ArrayList<Integer>();
			 for(int j=0;j<ckc.size();j++){
				 if(ckc.get(i).getSjczdttg().equals(ckc.get(j).getSjczdttg())){
					 //�Ѳ�������ͬһ�����С����ɡ�С����ŷֱ����xttg��xtth����
						 xttg.add(ckc.get(j).getSjczttg());
						 xtth.add(Integer.parseInt(ckc.get(j).getSjcztth()));
				 }
			 }
			Cztzj cc=new Cztzj();
			cc.setDttg(ckc.get(i).getSjczdttg());
			cc.setDtth(Integer.parseInt(ckc.get(i).getSjczdtth()));
			cc.setXttg(xttg);
			cc.setXtth(xtth);
			if(testczt.size()==0){
				testczt.add(cc);
			}else{
				List<String> tg = new ArrayList<String>();
				for(int n=0;n<testczt.size();n++){
					tg.add(testczt.get(n).getDttg());
				}
				if(!tg.contains(ckc.get(i).getSjczdttg())){//ȡ����������ظ�������
					testczt.add(cc);
				}
			}
		 }
		return SUCCESS;
	}
		
	//�鿴�Ծ����(���˵���:����->�鿴�Ծ����)
	@Action(value = "/sjda", results = { @Result(name = "success",location="/testxg/stda.jsp") })
	public String sjda(){
		
		HttpSession hs = ServletActionContext.getRequest().getSession();
		int ghsjno=(Integer) hs.getAttribute("testpaperno");
		sjname= (String)hs.getAttribute("testpapername");
		List<List> ll =this.adminservice.cksjxzt(ghsjno);
		List<Xz> ckx=new ArrayList<Xz>();
		for(int i=0;i<ll.size();i++){
			Xz ckxzt=new Xz();
			ckxzt.setTh(Integer.parseInt(ll.get(i).get(0).toString()));
			ckxzt.setTg(ll.get(i).get(1).toString());
			ckxzt.setXx1(ll.get(i).get(2).toString());
			ckxzt.setXx2(ll.get(i).get(3).toString());
			ckxzt.setXx3(ll.get(i).get(4).toString());
			ckxzt.setXx4(ll.get(i).get(5).toString());
			ckxzt.setDa(ll.get(i).get(6).toString());
			ckxzt.setDdx(Integer.parseInt(ll.get(i).get(7).toString()));
			ckx.add(ckxzt);
		}
    	List<List> llp =this.adminservice.cksjpdt(ghsjno);
 		List<Pd> ckp=new ArrayList<Pd>();
 		for(int j=0;j<llp.size();j++){
 			Pd ckpdt=new Pd();
 			ckpdt.setTg(llp.get(j).get(1).toString());
 			ckpdt.setDa(Integer.parseInt(llp.get(j).get(2).toString()));
 			ckpdt.setTh(Integer.parseInt(llp.get(j).get(0).toString()));
 			ckp.add(ckpdt);

 		}
 		List<List> llc =this.adminservice.cksjczt(ghsjno);
 		List<cksjczt> ckc=new ArrayList<cksjczt>();
 		for(int k=0;k<llc.size();k++){
 			cksjczt ckczt=new cksjczt();
 			ckczt.setSjcztth(llc.get(k).get(0).toString());//������С�����
 			ckczt.setSjczdttg(llc.get(k).get(1).toString());//������������
 			ckczt.setSjczdtth(llc.get(k).get(2).toString());//������������
 			ckczt.setSjczttg(llc.get(k).get(3).toString());//������С�����
 			ckc.add(ckczt);

 		}
		for(int i=0;i<ckx.size();i++){
			if(ckx.get(i).getDdx()==0){
				ckxz1.add(ckx.get(i));//����ǵ�ѡ�����ckxz1����
			}else if(ckx.get(i).getDdx()==1){
				ckxz2.add(ckx.get(i));//����Ƕ�ѡ�������ckxz2����
			}
		}
		ckpd1=ckp;
		for(int i=0;i<ckc.size();i++){
			 List<String> xttg = new ArrayList<String>();
			 List<Integer> xtth =new ArrayList<Integer>();
			 for(int j=0;j<ckc.size();j++){
				 if(ckc.get(i).getSjczdttg().equals(ckc.get(j).getSjczdttg())){
					 //�Ѳ�������ͬһ�����С����ɡ�С����ŷֱ����xttg��xtth����
						 xttg.add(ckc.get(j).getSjczttg());
						 xtth.add(Integer.parseInt(ckc.get(j).getSjcztth()));
				 }
			 }
			Cztzj cc=new Cztzj();
			cc.setDttg(ckc.get(i).getSjczdttg());
			cc.setDtth(Integer.parseInt(ckc.get(i).getSjczdtth()));
			cc.setXttg(xttg);
			cc.setXtth(xtth);
			if(czt1.size()==0){
				czt1.add(cc);
			}else{
				List<String> tg = new ArrayList<String>();
				for(int n=0;n<czt1.size();n++){
					tg.add(czt1.get(n).getDttg());
				}
				if(!tg.contains(ckc.get(i).getSjczdttg())){//ȡ����������ظ�������
					czt1.add(cc);
				}
			}
		 }
		
		return SUCCESS;
	}
		
	
}


